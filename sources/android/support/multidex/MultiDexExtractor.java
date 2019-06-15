package android.support.multidex;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.util.Log;
import com.facebook.internal.AnalyticsEvents;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

final class MultiDexExtractor {
    private static final int BUFFER_SIZE = 16384;
    private static final String DEX_PREFIX = "classes";
    private static final String DEX_SUFFIX = ".dex";
    private static final String EXTRACTED_NAME_EXT = ".classes";
    private static final String EXTRACTED_SUFFIX = ".zip";
    private static final String KEY_CRC = "crc";
    private static final String KEY_DEX_CRC = "dex.crc.";
    private static final String KEY_DEX_NUMBER = "dex.number";
    private static final String KEY_DEX_TIME = "dex.time.";
    private static final String KEY_TIME_STAMP = "timestamp";
    private static final String LOCK_FILENAME = "MultiDex.lock";
    private static final int MAX_EXTRACT_ATTEMPTS = 3;
    private static final long NO_VALUE = -1;
    private static final String PREFS_FILE = "multidex.version";
    private static final String TAG = "MultiDex";

    private static class ExtractedDex extends File {
        public long crc = -1;

        public ExtractedDex(File file, String str) {
            super(file, str);
        }
    }

    MultiDexExtractor() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x00c0 A:{SYNTHETIC, Splitter:B:19:0x00c0} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00e2  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0118 A:{SYNTHETIC, Splitter:B:37:0x0118} */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0138  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0118 A:{SYNTHETIC, Splitter:B:37:0x0118} */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0138  */
    static java.util.List<? extends java.io.File> load(android.content.Context r14, java.io.File r15, java.io.File r16, java.lang.String r17, boolean r18) throws java.io.IOException {
        /*
        r2 = r17;
        r1 = r18;
        r3 = "MultiDex";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "MultiDexExtractor.load(";
        r4.append(r5);
        r5 = r15.getPath();
        r4.append(r5);
        r5 = ", ";
        r4.append(r5);
        r4.append(r1);
        r5 = ", ";
        r4.append(r5);
        r4.append(r2);
        r5 = ")";
        r4.append(r5);
        r4 = r4.toString();
        android.util.Log.i(r3, r4);
        r5 = getZipCrc(r15);
        r8 = new java.io.File;
        r3 = "MultiDex.lock";
        r4 = r16;
        r8.<init>(r4, r3);
        r9 = new java.io.RandomAccessFile;
        r3 = "rw";
        r9.<init>(r8, r3);
        r10 = 0;
        r11 = r9.getChannel();	 Catch:{ all -> 0x0112 }
        r3 = "MultiDex";
        r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x010e }
        r7.<init>();	 Catch:{ all -> 0x010e }
        r12 = "Blocking on lock ";
        r7.append(r12);	 Catch:{ all -> 0x010e }
        r12 = r8.getPath();	 Catch:{ all -> 0x010e }
        r7.append(r12);	 Catch:{ all -> 0x010e }
        r7 = r7.toString();	 Catch:{ all -> 0x010e }
        android.util.Log.i(r3, r7);	 Catch:{ all -> 0x010e }
        r12 = r11.lock();	 Catch:{ all -> 0x010e }
        r3 = "MultiDex";
        r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x010b }
        r7.<init>();	 Catch:{ all -> 0x010b }
        r13 = r8.getPath();	 Catch:{ all -> 0x010b }
        r7.append(r13);	 Catch:{ all -> 0x010b }
        r13 = " locked";
        r7.append(r13);	 Catch:{ all -> 0x010b }
        r7 = r7.toString();	 Catch:{ all -> 0x010b }
        android.util.Log.i(r3, r7);	 Catch:{ all -> 0x010b }
        if (r1 != 0) goto L_0x00a9;
    L_0x0086:
        r1 = r14;
        r3 = r15;
        r7 = isModified(r1, r3, r5, r2);	 Catch:{ all -> 0x010b }
        if (r7 != 0) goto L_0x00ab;
    L_0x008e:
        r7 = loadExistingExtractions(r14, r15, r16, r17);	 Catch:{ IOException -> 0x0094 }
        r13 = r7;
        goto L_0x00be;
    L_0x0094:
        r0 = move-exception;
        r7 = "MultiDex";
        r13 = "Failed to reload existing extracted secondary dex files, falling back to fresh extraction";
        android.util.Log.w(r7, r13, r0);	 Catch:{ all -> 0x010b }
        r13 = performExtractions(r15, r16);	 Catch:{ all -> 0x010b }
        r3 = getTimeStamp(r3);	 Catch:{ all -> 0x010b }
        r7 = r13;
        putStoredApkInfo(r1, r2, r3, r5, r7);	 Catch:{ all -> 0x010b }
        goto L_0x00be;
    L_0x00a9:
        r1 = r14;
        r3 = r15;
    L_0x00ab:
        r7 = "MultiDex";
        r13 = "Detected that extraction must be performed.";
        android.util.Log.i(r7, r13);	 Catch:{ all -> 0x010b }
        r13 = performExtractions(r15, r16);	 Catch:{ all -> 0x010b }
        r3 = getTimeStamp(r3);	 Catch:{ all -> 0x010b }
        r7 = r13;
        putStoredApkInfo(r1, r2, r3, r5, r7);	 Catch:{ all -> 0x010b }
    L_0x00be:
        if (r12 == 0) goto L_0x00e0;
    L_0x00c0:
        r12.release();	 Catch:{ IOException -> 0x00c4 }
        goto L_0x00e0;
    L_0x00c4:
        r0 = move-exception;
        r1 = "MultiDex";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Failed to release lock on ";
        r2.append(r3);
        r3 = r8.getPath();
        r2.append(r3);
        r2 = r2.toString();
        android.util.Log.e(r1, r2);
        r10 = r0;
    L_0x00e0:
        if (r11 == 0) goto L_0x00e5;
    L_0x00e2:
        closeQuietly(r11);
    L_0x00e5:
        closeQuietly(r9);
        if (r10 == 0) goto L_0x00eb;
    L_0x00ea:
        throw r10;
    L_0x00eb:
        r1 = "MultiDex";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "load found ";
        r2.append(r3);
        r3 = r13.size();
        r2.append(r3);
        r3 = " secondary dex files";
        r2.append(r3);
        r2 = r2.toString();
        android.util.Log.i(r1, r2);
        return r13;
    L_0x010b:
        r0 = move-exception;
        r1 = r0;
        goto L_0x0116;
    L_0x010e:
        r0 = move-exception;
        r1 = r0;
        r12 = r10;
        goto L_0x0116;
    L_0x0112:
        r0 = move-exception;
        r1 = r0;
        r11 = r10;
        r12 = r11;
    L_0x0116:
        if (r12 == 0) goto L_0x0136;
    L_0x0118:
        r12.release();	 Catch:{ IOException -> 0x011c }
        goto L_0x0136;
    L_0x011c:
        r2 = "MultiDex";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "Failed to release lock on ";
        r3.append(r4);
        r4 = r8.getPath();
        r3.append(r4);
        r3 = r3.toString();
        android.util.Log.e(r2, r3);
    L_0x0136:
        if (r11 == 0) goto L_0x013b;
    L_0x0138:
        closeQuietly(r11);
    L_0x013b:
        closeQuietly(r9);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.multidex.MultiDexExtractor.load(android.content.Context, java.io.File, java.io.File, java.lang.String, boolean):java.util.List");
    }

    private static List<ExtractedDex> loadExistingExtractions(Context context, File file, File file2, String str) throws IOException {
        String str2 = str;
        Log.i(TAG, "loading existing secondary dex files");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(file.getName());
        stringBuilder.append(EXTRACTED_NAME_EXT);
        String stringBuilder2 = stringBuilder.toString();
        SharedPreferences multiDexPreferences = getMultiDexPreferences(context);
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(str2);
        stringBuilder3.append(KEY_DEX_NUMBER);
        int i = multiDexPreferences.getInt(stringBuilder3.toString(), 1);
        ArrayList arrayList = new ArrayList(i - 1);
        int i2 = 2;
        while (i2 <= i) {
            StringBuilder stringBuilder4 = new StringBuilder();
            stringBuilder4.append(stringBuilder2);
            stringBuilder4.append(i2);
            stringBuilder4.append(EXTRACTED_SUFFIX);
            ExtractedDex extractedDex = new ExtractedDex(file2, stringBuilder4.toString());
            if (extractedDex.isFile()) {
                extractedDex.crc = getZipCrc(extractedDex);
                stringBuilder4 = new StringBuilder();
                stringBuilder4.append(str2);
                stringBuilder4.append(KEY_DEX_CRC);
                stringBuilder4.append(i2);
                long j = multiDexPreferences.getLong(stringBuilder4.toString(), -1);
                stringBuilder4 = new StringBuilder();
                stringBuilder4.append(str2);
                stringBuilder4.append(KEY_DEX_TIME);
                stringBuilder4.append(i2);
                long j2 = multiDexPreferences.getLong(stringBuilder4.toString(), -1);
                long lastModified = extractedDex.lastModified();
                if (j2 == lastModified) {
                    String str3 = stringBuilder2;
                    SharedPreferences sharedPreferences = multiDexPreferences;
                    if (j == extractedDex.crc) {
                        arrayList.add(extractedDex);
                        i2++;
                        stringBuilder2 = str3;
                        multiDexPreferences = sharedPreferences;
                    }
                }
                StringBuilder stringBuilder5 = new StringBuilder();
                stringBuilder5.append("Invalid extracted dex: ");
                stringBuilder5.append(extractedDex);
                stringBuilder5.append(" (key \"");
                stringBuilder5.append(str2);
                stringBuilder5.append("\"), expected modification time: ");
                stringBuilder5.append(j2);
                stringBuilder5.append(", modification time: ");
                stringBuilder5.append(lastModified);
                stringBuilder5.append(", expected crc: ");
                stringBuilder5.append(j);
                stringBuilder5.append(", file crc: ");
                stringBuilder5.append(extractedDex.crc);
                throw new IOException(stringBuilder5.toString());
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append("Missing extracted secondary dex file '");
            stringBuilder.append(extractedDex.getPath());
            stringBuilder.append("'");
            throw new IOException(stringBuilder.toString());
        }
        return arrayList;
    }

    private static boolean isModified(Context context, File file, long j, String str) {
        SharedPreferences multiDexPreferences = getMultiDexPreferences(context);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("timestamp");
        if (multiDexPreferences.getLong(stringBuilder.toString(), -1) == getTimeStamp(file)) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str);
            stringBuilder2.append(KEY_CRC);
            if (multiDexPreferences.getLong(stringBuilder2.toString(), -1) == j) {
                return false;
            }
        }
        return true;
    }

    private static long getTimeStamp(File file) {
        long lastModified = file.lastModified();
        return lastModified == -1 ? lastModified - 1 : lastModified;
    }

    private static long getZipCrc(File file) throws IOException {
        long zipCrc = ZipUtil.getZipCrc(file);
        return zipCrc == -1 ? zipCrc - 1 : zipCrc;
    }

    private static List<ExtractedDex> performExtractions(File file, File file2) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(file.getName());
        stringBuilder.append(EXTRACTED_NAME_EXT);
        String stringBuilder2 = stringBuilder.toString();
        prepareDexDir(file2, stringBuilder2);
        ArrayList arrayList = new ArrayList();
        ZipFile zipFile = new ZipFile(file);
        int i = 2;
        ExtractedDex extractedDex;
        int i2;
        String str;
        StringBuilder stringBuilder3;
        try {
            StringBuilder stringBuilder4 = new StringBuilder();
            stringBuilder4.append(DEX_PREFIX);
            stringBuilder4.append(2);
            stringBuilder4.append(DEX_SUFFIX);
            ZipEntry entry = zipFile.getEntry(stringBuilder4.toString());
            while (entry != null) {
                StringBuilder stringBuilder5 = new StringBuilder();
                stringBuilder5.append(stringBuilder2);
                stringBuilder5.append(i);
                stringBuilder5.append(EXTRACTED_SUFFIX);
                extractedDex = new ExtractedDex(file2, stringBuilder5.toString());
                arrayList.add(extractedDex);
                String str2 = TAG;
                StringBuilder stringBuilder6 = new StringBuilder();
                stringBuilder6.append("Extraction is needed for file ");
                stringBuilder6.append(extractedDex);
                Log.i(str2, stringBuilder6.toString());
                int i3 = 0;
                i2 = i3;
                while (i3 < 3 && i2 == 0) {
                    i3++;
                    extract(zipFile, entry, extractedDex, stringBuilder2);
                    extractedDex.crc = getZipCrc(extractedDex);
                    i2 = 1;
                    str = TAG;
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("Extraction ");
                    stringBuilder3.append(i2 != 0 ? AnalyticsEvents.PARAMETER_SHARE_OUTCOME_SUCCEEDED : "failed");
                    stringBuilder3.append(" - length ");
                    stringBuilder3.append(extractedDex.getAbsolutePath());
                    stringBuilder3.append(": ");
                    stringBuilder3.append(extractedDex.length());
                    stringBuilder3.append(" - crc: ");
                    stringBuilder3.append(extractedDex.crc);
                    Log.i(str, stringBuilder3.toString());
                    if (i2 == 0) {
                        extractedDex.delete();
                        if (extractedDex.exists()) {
                            str = TAG;
                            stringBuilder3 = new StringBuilder();
                            stringBuilder3.append("Failed to delete corrupted secondary dex '");
                            stringBuilder3.append(extractedDex.getPath());
                            stringBuilder3.append("'");
                            Log.w(str, stringBuilder3.toString());
                        }
                    }
                }
                if (i2 == 0) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Could not create zip file ");
                    stringBuilder.append(extractedDex.getAbsolutePath());
                    stringBuilder.append(" for secondary dex (");
                    stringBuilder.append(i);
                    stringBuilder.append(")");
                    throw new IOException(stringBuilder.toString());
                }
                i++;
                stringBuilder4 = new StringBuilder();
                stringBuilder4.append(DEX_PREFIX);
                stringBuilder4.append(i);
                stringBuilder4.append(DEX_SUFFIX);
                entry = zipFile.getEntry(stringBuilder4.toString());
            }
            try {
                zipFile.close();
            } catch (IOException e) {
                Log.w(TAG, "Failed to close resource", e);
            }
            return arrayList;
        } catch (IOException e2) {
            str = TAG;
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append("Failed to read crc from ");
            stringBuilder3.append(extractedDex.getAbsolutePath());
            Log.w(str, stringBuilder3.toString(), e2);
            i2 = 0;
        } catch (Throwable th) {
            try {
                zipFile.close();
            } catch (IOException e3) {
                Log.w(TAG, "Failed to close resource", e3);
            }
        }
    }

    private static void putStoredApkInfo(Context context, String str, long j, long j2, List<ExtractedDex> list) {
        Editor edit = getMultiDexPreferences(context).edit();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("timestamp");
        edit.putLong(stringBuilder.toString(), j);
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str);
        stringBuilder2.append(KEY_CRC);
        edit.putLong(stringBuilder2.toString(), j2);
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str);
        stringBuilder2.append(KEY_DEX_NUMBER);
        edit.putInt(stringBuilder2.toString(), list.size() + 1);
        int i = 2;
        for (ExtractedDex extractedDex : list) {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(str);
            stringBuilder3.append(KEY_DEX_CRC);
            stringBuilder3.append(i);
            edit.putLong(stringBuilder3.toString(), extractedDex.crc);
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append(str);
            stringBuilder3.append(KEY_DEX_TIME);
            stringBuilder3.append(i);
            edit.putLong(stringBuilder3.toString(), extractedDex.lastModified());
            i++;
        }
        edit.commit();
    }

    private static SharedPreferences getMultiDexPreferences(Context context) {
        return context.getSharedPreferences(PREFS_FILE, VERSION.SDK_INT < 11 ? 0 : 4);
    }

    private static void prepareDexDir(File file, final String str) {
        File[] listFiles = file.listFiles(new FileFilter() {
            public boolean accept(File file) {
                String name = file.getName();
                return (name.startsWith(str) || name.equals(MultiDexExtractor.LOCK_FILENAME)) ? false : true;
            }
        });
        if (listFiles == null) {
            str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to list secondary dex dir content (");
            stringBuilder.append(file.getPath());
            stringBuilder.append(").");
            Log.w(str, stringBuilder.toString());
            return;
        }
        for (File file2 : listFiles) {
            String str2 = TAG;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Trying to delete old file ");
            stringBuilder2.append(file2.getPath());
            stringBuilder2.append(" of size ");
            stringBuilder2.append(file2.length());
            Log.i(str2, stringBuilder2.toString());
            if (file2.delete()) {
                str2 = TAG;
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Deleted old file ");
                stringBuilder2.append(file2.getPath());
                Log.i(str2, stringBuilder2.toString());
            } else {
                str2 = TAG;
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Failed to delete old file ");
                stringBuilder2.append(file2.getPath());
                Log.w(str2, stringBuilder2.toString());
            }
        }
    }

    private static void extract(ZipFile zipFile, ZipEntry zipEntry, File file, String str) throws IOException, FileNotFoundException {
        InputStream inputStream = zipFile.getInputStream(zipEntry);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("tmp-");
        stringBuilder.append(str);
        File createTempFile = File.createTempFile(stringBuilder.toString(), EXTRACTED_SUFFIX, file.getParentFile());
        String str2 = TAG;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Extracting ");
        stringBuilder2.append(createTempFile.getPath());
        Log.i(str2, stringBuilder2.toString());
        ZipOutputStream zipOutputStream;
        try {
            zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(createTempFile)));
            ZipEntry zipEntry2 = new ZipEntry("classes.dex");
            zipEntry2.setTime(zipEntry.getTime());
            zipOutputStream.putNextEntry(zipEntry2);
            byte[] bArr = new byte[16384];
            for (int read = inputStream.read(bArr); read != -1; read = inputStream.read(bArr)) {
                zipOutputStream.write(bArr, 0, read);
            }
            zipOutputStream.closeEntry();
            zipOutputStream.close();
            if (createTempFile.setReadOnly()) {
                String str3 = TAG;
                stringBuilder = new StringBuilder();
                stringBuilder.append("Renaming to ");
                stringBuilder.append(file.getPath());
                Log.i(str3, stringBuilder.toString());
                if (createTempFile.renameTo(file)) {
                    closeQuietly(inputStream);
                    createTempFile.delete();
                    return;
                }
                stringBuilder = new StringBuilder();
                stringBuilder.append("Failed to rename \"");
                stringBuilder.append(createTempFile.getAbsolutePath());
                stringBuilder.append("\" to \"");
                stringBuilder.append(file.getAbsolutePath());
                stringBuilder.append("\"");
                throw new IOException(stringBuilder.toString());
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to mark readonly \"");
            stringBuilder.append(createTempFile.getAbsolutePath());
            stringBuilder.append("\" (tmp of \"");
            stringBuilder.append(file.getAbsolutePath());
            stringBuilder.append("\")");
            throw new IOException(stringBuilder.toString());
        } catch (Throwable th) {
            closeQuietly(inputStream);
            createTempFile.delete();
        }
    }

    private static void closeQuietly(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            Log.w(TAG, "Failed to close resource", e);
        }
    }
}
