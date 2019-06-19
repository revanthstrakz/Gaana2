package com.facebook.internal;

import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidParameterException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public final class FileLruCache {
    private static final String HEADER_CACHEKEY_KEY = "key";
    private static final String HEADER_CACHE_CONTENT_TAG_KEY = "tag";
    static final String TAG = "FileLruCache";
    private static final AtomicLong bufferIndex = new AtomicLong();
    private final File directory;
    private boolean isTrimInProgress;
    private boolean isTrimPending;
    private AtomicLong lastClearCacheTime = new AtomicLong(0);
    private final Limits limits;
    private final Object lock;
    private final String tag;

    private interface StreamCloseCallback {
        void onClose();
    }

    private static class BufferFile {
        private static final String FILE_NAME_PREFIX = "buffer";
        private static final FilenameFilter filterExcludeBufferFiles = new FilenameFilter() {
            public boolean accept(File file, String str) {
                return str.startsWith(BufferFile.FILE_NAME_PREFIX) ^ 1;
            }
        };
        private static final FilenameFilter filterExcludeNonBufferFiles = new FilenameFilter() {
            public boolean accept(File file, String str) {
                return str.startsWith(BufferFile.FILE_NAME_PREFIX);
            }
        };

        private BufferFile() {
        }

        static void deleteAll(File file) {
            File[] listFiles = file.listFiles(excludeNonBufferFiles());
            if (listFiles != null) {
                for (File delete : listFiles) {
                    delete.delete();
                }
            }
        }

        static FilenameFilter excludeBufferFiles() {
            return filterExcludeBufferFiles;
        }

        static FilenameFilter excludeNonBufferFiles() {
            return filterExcludeNonBufferFiles;
        }

        static File newFile(File file) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(FILE_NAME_PREFIX);
            stringBuilder.append(Long.valueOf(FileLruCache.bufferIndex.incrementAndGet()).toString());
            return new File(file, stringBuilder.toString());
        }
    }

    private static class CloseCallbackOutputStream extends OutputStream {
        final StreamCloseCallback callback;
        final OutputStream innerStream;

        CloseCallbackOutputStream(OutputStream outputStream, StreamCloseCallback streamCloseCallback) {
            this.innerStream = outputStream;
            this.callback = streamCloseCallback;
        }

        public void close() throws IOException {
            try {
                this.innerStream.close();
            } finally {
                this.callback.onClose();
            }
        }

        public void flush() throws IOException {
            this.innerStream.flush();
        }

        public void write(byte[] bArr, int i, int i2) throws IOException {
            this.innerStream.write(bArr, i, i2);
        }

        public void write(byte[] bArr) throws IOException {
            this.innerStream.write(bArr);
        }

        public void write(int i) throws IOException {
            this.innerStream.write(i);
        }
    }

    private static final class CopyingInputStream extends InputStream {
        final InputStream input;
        final OutputStream output;

        public boolean markSupported() {
            return false;
        }

        CopyingInputStream(InputStream inputStream, OutputStream outputStream) {
            this.input = inputStream;
            this.output = outputStream;
        }

        public int available() throws IOException {
            return this.input.available();
        }

        public void close() throws IOException {
            try {
                this.input.close();
            } finally {
                this.output.close();
            }
        }

        public void mark(int i) {
            throw new UnsupportedOperationException();
        }

        public int read(byte[] bArr) throws IOException {
            int read = this.input.read(bArr);
            if (read > 0) {
                this.output.write(bArr, 0, read);
            }
            return read;
        }

        public int read() throws IOException {
            int read = this.input.read();
            if (read >= 0) {
                this.output.write(read);
            }
            return read;
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            i2 = this.input.read(bArr, i, i2);
            if (i2 > 0) {
                this.output.write(bArr, i, i2);
            }
            return i2;
        }

        public synchronized void reset() {
            throw new UnsupportedOperationException();
        }

        public long skip(long j) throws IOException {
            byte[] bArr = new byte[1024];
            long j2 = 0;
            while (j2 < j) {
                int read = read(bArr, 0, (int) Math.min(j - j2, (long) bArr.length));
                if (read < 0) {
                    return j2;
                }
                j2 += (long) read;
            }
            return j2;
        }
    }

    public static final class Limits {
        private int byteCount = 1048576;
        private int fileCount = 1024;

        /* Access modifiers changed, original: 0000 */
        public int getByteCount() {
            return this.byteCount;
        }

        /* Access modifiers changed, original: 0000 */
        public int getFileCount() {
            return this.fileCount;
        }

        /* Access modifiers changed, original: 0000 */
        public void setByteCount(int i) {
            if (i < 0) {
                throw new InvalidParameterException("Cache byte-count limit must be >= 0");
            }
            this.byteCount = i;
        }

        /* Access modifiers changed, original: 0000 */
        public void setFileCount(int i) {
            if (i < 0) {
                throw new InvalidParameterException("Cache file count limit must be >= 0");
            }
            this.fileCount = i;
        }
    }

    private static final class ModifiedFile implements Comparable<ModifiedFile> {
        private static final int HASH_MULTIPLIER = 37;
        private static final int HASH_SEED = 29;
        private final File file;
        private final long modified;

        ModifiedFile(File file) {
            this.file = file;
            this.modified = file.lastModified();
        }

        /* Access modifiers changed, original: 0000 */
        public File getFile() {
            return this.file;
        }

        /* Access modifiers changed, original: 0000 */
        public long getModified() {
            return this.modified;
        }

        public int compareTo(ModifiedFile modifiedFile) {
            if (getModified() < modifiedFile.getModified()) {
                return -1;
            }
            if (getModified() > modifiedFile.getModified()) {
                return 1;
            }
            return getFile().compareTo(modifiedFile.getFile());
        }

        public boolean equals(Object obj) {
            return (obj instanceof ModifiedFile) && compareTo((ModifiedFile) obj) == 0;
        }

        public int hashCode() {
            return ((1073 + this.file.hashCode()) * 37) + ((int) (this.modified % 2147483647L));
        }
    }

    private static final class StreamHeader {
        private static final int HEADER_VERSION = 0;

        private StreamHeader() {
        }

        static void writeHeader(OutputStream outputStream, JSONObject jSONObject) throws IOException {
            byte[] bytes = jSONObject.toString().getBytes();
            outputStream.write(0);
            outputStream.write((bytes.length >> 16) & 255);
            outputStream.write((bytes.length >> 8) & 255);
            outputStream.write((bytes.length >> 0) & 255);
            outputStream.write(bytes);
        }

        static JSONObject readHeader(InputStream inputStream) throws IOException {
            if (inputStream.read() != 0) {
                return null;
            }
            int read;
            int i = 0;
            int i2 = 0;
            int i3 = i2;
            while (i2 < 3) {
                read = inputStream.read();
                if (read == -1) {
                    Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, "readHeader: stream.read returned -1 while reading header size");
                    return null;
                }
                i3 = (i3 << 8) + (read & 255);
                i2++;
            }
            byte[] bArr = new byte[i3];
            while (i < bArr.length) {
                read = inputStream.read(bArr, i, bArr.length - i);
                if (read < 1) {
                    LoggingBehavior loggingBehavior = LoggingBehavior.CACHE;
                    String str = FileLruCache.TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("readHeader: stream.read stopped at ");
                    stringBuilder.append(Integer.valueOf(i));
                    stringBuilder.append(" when expected ");
                    stringBuilder.append(bArr.length);
                    Logger.log(loggingBehavior, str, stringBuilder.toString());
                    return null;
                }
                i += read;
            }
            try {
                Object nextValue = new JSONTokener(new String(bArr)).nextValue();
                if (nextValue instanceof JSONObject) {
                    return (JSONObject) nextValue;
                }
                LoggingBehavior loggingBehavior2 = LoggingBehavior.CACHE;
                String str2 = FileLruCache.TAG;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("readHeader: expected JSONObject, got ");
                stringBuilder2.append(nextValue.getClass().getCanonicalName());
                Logger.log(loggingBehavior2, str2, stringBuilder2.toString());
                return null;
            } catch (JSONException e) {
                throw new IOException(e.getMessage());
            }
        }
    }

    public FileLruCache(String str, Limits limits) {
        this.tag = str;
        this.limits = limits;
        this.directory = new File(FacebookSdk.getCacheDir(), str);
        this.lock = new Object();
        if (this.directory.mkdirs() || this.directory.isDirectory()) {
            BufferFile.deleteAll(this.directory);
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:2:0x0003 A:{LOOP_START, SYNTHETIC, LOOP:0: B:2:0x0003->B:22:0x0003} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x0003 */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    public long sizeInBytesForTest() {
        /*
        r9 = this;
        r0 = r9.lock;
        monitor-enter(r0);
    L_0x0003:
        r1 = r9.isTrimPending;	 Catch:{ all -> 0x002e }
        if (r1 != 0) goto L_0x0028;
    L_0x0007:
        r1 = r9.isTrimInProgress;	 Catch:{ all -> 0x002e }
        if (r1 == 0) goto L_0x000c;
    L_0x000b:
        goto L_0x0028;
    L_0x000c:
        monitor-exit(r0);	 Catch:{ all -> 0x002e }
        r0 = r9.directory;
        r0 = r0.listFiles();
        r1 = 0;
        if (r0 == 0) goto L_0x0027;
    L_0x0017:
        r3 = r0.length;
        r4 = 0;
    L_0x0019:
        if (r4 >= r3) goto L_0x0027;
    L_0x001b:
        r5 = r0[r4];
        r5 = r5.length();
        r7 = r1 + r5;
        r4 = r4 + 1;
        r1 = r7;
        goto L_0x0019;
    L_0x0027:
        return r1;
    L_0x0028:
        r1 = r9.lock;	 Catch:{ InterruptedException -> 0x0003 }
        r1.wait();	 Catch:{ InterruptedException -> 0x0003 }
        goto L_0x0003;
    L_0x002e:
        r1 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x002e }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.FileLruCache.sizeInBytesForTest():long");
    }

    public InputStream get(String str) throws IOException {
        return get(str, null);
    }

    public InputStream get(String str, String str2) throws IOException {
        File file = new File(this.directory, Utility.md5hash(str));
        InputStream inputStream = null;
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file), 8192);
            try {
                JSONObject readHeader = StreamHeader.readHeader(bufferedInputStream);
                if (readHeader == null) {
                    return inputStream;
                }
                String optString = readHeader.optString("key");
                if (optString != null) {
                    if (optString.equals(str)) {
                        str = readHeader.optString(HEADER_CACHE_CONTENT_TAG_KEY, null);
                        if ((str2 != null || str == null) && (str2 == null || str2.equals(str))) {
                            long time = new Date().getTime();
                            inputStream = LoggingBehavior.CACHE;
                            String str3 = TAG;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("Setting lastModified to ");
                            stringBuilder.append(Long.valueOf(time));
                            stringBuilder.append(" for ");
                            stringBuilder.append(file.getName());
                            Logger.log(inputStream, str3, stringBuilder.toString());
                            file.setLastModified(time);
                            return bufferedInputStream;
                        }
                        bufferedInputStream.close();
                        return null;
                    }
                }
                bufferedInputStream.close();
                return null;
            } finally {
                bufferedInputStream.close();
            }
        } catch (IOException unused) {
            return null;
        }
    }

    public OutputStream openPutStream(String str) throws IOException {
        return openPutStream(str, null);
    }

    public OutputStream openPutStream(String str, String str2) throws IOException {
        LoggingBehavior loggingBehavior;
        String str3;
        final File newFile = BufferFile.newFile(this.directory);
        newFile.delete();
        if (newFile.createNewFile()) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(newFile);
                final long currentTimeMillis = System.currentTimeMillis();
                final String str4 = str;
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new CloseCallbackOutputStream(fileOutputStream, new StreamCloseCallback() {
                    public void onClose() {
                        if (currentTimeMillis < FileLruCache.this.lastClearCacheTime.get()) {
                            newFile.delete();
                        } else {
                            FileLruCache.this.renameToTargetAndTrim(str4, newFile);
                        }
                    }
                }), 8192);
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("key", str);
                    if (!Utility.isNullOrEmpty(str2)) {
                        jSONObject.put(HEADER_CACHE_CONTENT_TAG_KEY, str2);
                    }
                    StreamHeader.writeHeader(bufferedOutputStream, jSONObject);
                    return bufferedOutputStream;
                } catch (JSONException e) {
                    loggingBehavior = LoggingBehavior.CACHE;
                    str3 = TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Error creating JSON header for cache file: ");
                    stringBuilder.append(e);
                    Logger.log(loggingBehavior, 5, str3, stringBuilder.toString());
                    throw new IOException(e.getMessage());
                } catch (Throwable th) {
                    bufferedOutputStream.close();
                }
            } catch (FileNotFoundException e2) {
                loggingBehavior = LoggingBehavior.CACHE;
                str3 = TAG;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Error creating buffer output stream: ");
                stringBuilder2.append(e2);
                Logger.log(loggingBehavior, 5, str3, stringBuilder2.toString());
                throw new IOException(e2.getMessage());
            }
        }
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("Could not create file at ");
        stringBuilder3.append(newFile.getAbsolutePath());
        throw new IOException(stringBuilder3.toString());
    }

    public void clearCache() {
        final File[] listFiles = this.directory.listFiles(BufferFile.excludeBufferFiles());
        this.lastClearCacheTime.set(System.currentTimeMillis());
        if (listFiles != null) {
            FacebookSdk.getExecutor().execute(new Runnable() {
                public void run() {
                    for (File delete : listFiles) {
                        delete.delete();
                    }
                }
            });
        }
    }

    public String getLocation() {
        return this.directory.getPath();
    }

    private void renameToTargetAndTrim(String str, File file) {
        if (!file.renameTo(new File(this.directory, Utility.md5hash(str)))) {
            file.delete();
        }
        postTrim();
    }

    public InputStream interceptAndPut(String str, InputStream inputStream) throws IOException {
        return new CopyingInputStream(inputStream, openPutStream(str));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{FileLruCache: tag:");
        stringBuilder.append(this.tag);
        stringBuilder.append(" file:");
        stringBuilder.append(this.directory.getName());
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private void postTrim() {
        synchronized (this.lock) {
            if (!this.isTrimPending) {
                this.isTrimPending = true;
                FacebookSdk.getExecutor().execute(new Runnable() {
                    public void run() {
                        FileLruCache.this.trim();
                    }
                });
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x00f2  */
    private void trim() {
        /*
        r17 = this;
        r1 = r17;
        r2 = r1.lock;
        monitor-enter(r2);
        r3 = 0;
        r1.isTrimPending = r3;	 Catch:{ all -> 0x0100 }
        r4 = 1;
        r1.isTrimInProgress = r4;	 Catch:{ all -> 0x0100 }
        monitor-exit(r2);	 Catch:{ all -> 0x0100 }
        r2 = com.facebook.LoggingBehavior.CACHE;	 Catch:{ all -> 0x00ed }
        r4 = TAG;	 Catch:{ all -> 0x00ed }
        r5 = "trim started";
        com.facebook.internal.Logger.log(r2, r4, r5);	 Catch:{ all -> 0x00ed }
        r2 = new java.util.PriorityQueue;	 Catch:{ all -> 0x00ed }
        r2.<init>();	 Catch:{ all -> 0x00ed }
        r4 = r1.directory;	 Catch:{ all -> 0x00ed }
        r5 = com.facebook.internal.FileLruCache.BufferFile.excludeBufferFiles();	 Catch:{ all -> 0x00ed }
        r4 = r4.listFiles(r5);	 Catch:{ all -> 0x00ed }
        r7 = 0;
        if (r4 == 0) goto L_0x0088;
    L_0x0028:
        r9 = r4.length;	 Catch:{ all -> 0x00ed }
        r10 = r7;
        r12 = r10;
        r7 = r3;
    L_0x002c:
        if (r7 >= r9) goto L_0x0084;
    L_0x002e:
        r8 = r4[r7];	 Catch:{ all -> 0x00ed }
        r14 = new com.facebook.internal.FileLruCache$ModifiedFile;	 Catch:{ all -> 0x00ed }
        r14.<init>(r8);	 Catch:{ all -> 0x00ed }
        r2.add(r14);	 Catch:{ all -> 0x00ed }
        r15 = com.facebook.LoggingBehavior.CACHE;	 Catch:{ all -> 0x00ed }
        r3 = TAG;	 Catch:{ all -> 0x00ed }
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00ed }
        r5.<init>();	 Catch:{ all -> 0x00ed }
        r6 = "  trim considering time=";
        r5.append(r6);	 Catch:{ all -> 0x00ed }
        r16 = r2;
        r1 = r14.getModified();	 Catch:{ all -> 0x007e }
        r1 = java.lang.Long.valueOf(r1);	 Catch:{ all -> 0x007e }
        r5.append(r1);	 Catch:{ all -> 0x007e }
        r1 = " name=";
        r5.append(r1);	 Catch:{ all -> 0x007e }
        r1 = r14.getFile();	 Catch:{ all -> 0x007e }
        r1 = r1.getName();	 Catch:{ all -> 0x007e }
        r5.append(r1);	 Catch:{ all -> 0x007e }
        r1 = r5.toString();	 Catch:{ all -> 0x007e }
        com.facebook.internal.Logger.log(r15, r3, r1);	 Catch:{ all -> 0x007e }
        r1 = r8.length();	 Catch:{ all -> 0x007e }
        r5 = r10 + r1;
        r1 = 1;
        r10 = r12 + r1;
        r7 = r7 + 1;
        r12 = r10;
        r2 = r16;
        r1 = r17;
        r3 = 0;
        r10 = r5;
        goto L_0x002c;
    L_0x007e:
        r0 = move-exception;
        r2 = r0;
        r1 = r17;
        goto L_0x00ef;
    L_0x0084:
        r16 = r2;
        r7 = r10;
        goto L_0x008b;
    L_0x0088:
        r16 = r2;
        r12 = r7;
    L_0x008b:
        r2 = r1.limits;	 Catch:{ all -> 0x00ed }
        r2 = r2.getByteCount();	 Catch:{ all -> 0x00ed }
        r2 = (long) r2;	 Catch:{ all -> 0x00ed }
        r4 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1));
        if (r4 > 0) goto L_0x00b3;
    L_0x0096:
        r2 = r1.limits;	 Catch:{ all -> 0x00ed }
        r2 = r2.getFileCount();	 Catch:{ all -> 0x00ed }
        r2 = (long) r2;
        r4 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1));
        if (r4 <= 0) goto L_0x00a2;
    L_0x00a1:
        goto L_0x00b3;
    L_0x00a2:
        r2 = r1.lock;
        monitor-enter(r2);
        r3 = 0;
        r1.isTrimInProgress = r3;	 Catch:{ all -> 0x00af }
        r3 = r1.lock;	 Catch:{ all -> 0x00af }
        r3.notifyAll();	 Catch:{ all -> 0x00af }
        monitor-exit(r2);	 Catch:{ all -> 0x00af }
        return;
    L_0x00af:
        r0 = move-exception;
        r3 = r0;
        monitor-exit(r2);	 Catch:{ all -> 0x00af }
        throw r3;
    L_0x00b3:
        r2 = r16;
        r3 = r2.remove();	 Catch:{ all -> 0x00ed }
        r3 = (com.facebook.internal.FileLruCache.ModifiedFile) r3;	 Catch:{ all -> 0x00ed }
        r3 = r3.getFile();	 Catch:{ all -> 0x00ed }
        r4 = com.facebook.LoggingBehavior.CACHE;	 Catch:{ all -> 0x00ed }
        r5 = TAG;	 Catch:{ all -> 0x00ed }
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00ed }
        r6.<init>();	 Catch:{ all -> 0x00ed }
        r9 = "  trim removing ";
        r6.append(r9);	 Catch:{ all -> 0x00ed }
        r9 = r3.getName();	 Catch:{ all -> 0x00ed }
        r6.append(r9);	 Catch:{ all -> 0x00ed }
        r6 = r6.toString();	 Catch:{ all -> 0x00ed }
        com.facebook.internal.Logger.log(r4, r5, r6);	 Catch:{ all -> 0x00ed }
        r4 = r3.length();	 Catch:{ all -> 0x00ed }
        r9 = r7 - r4;
        r4 = 1;
        r6 = r12 - r4;
        r3.delete();	 Catch:{ all -> 0x00ed }
        r16 = r2;
        r12 = r6;
        r7 = r9;
        goto L_0x008b;
    L_0x00ed:
        r0 = move-exception;
        r2 = r0;
    L_0x00ef:
        r3 = r1.lock;
        monitor-enter(r3);
        r4 = 0;
        r1.isTrimInProgress = r4;	 Catch:{ all -> 0x00fc }
        r4 = r1.lock;	 Catch:{ all -> 0x00fc }
        r4.notifyAll();	 Catch:{ all -> 0x00fc }
        monitor-exit(r3);	 Catch:{ all -> 0x00fc }
        throw r2;
    L_0x00fc:
        r0 = move-exception;
        r2 = r0;
        monitor-exit(r3);	 Catch:{ all -> 0x00fc }
        throw r2;
    L_0x0100:
        r0 = move-exception;
        r3 = r0;
        monitor-exit(r2);	 Catch:{ all -> 0x0100 }
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.FileLruCache.trim():void");
    }
}
