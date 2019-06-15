package com.android.volley.toolbox;

import android.os.SystemClock;
import com.android.volley.l;
import java.io.EOFException;
import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class c implements com.android.volley.a {
    private final Map<String, a> a = new LinkedHashMap(16, 0.75f, true);
    private long b = 0;
    private final File c;
    private final int d;

    static class a {
        public long a;
        public String b;
        public String c;
        public long d;
        public long e;
        public long f;
        public long g;
        public Map<String, String> h;

        private a() {
        }

        public a(String str, com.android.volley.a.a aVar) {
            this.b = str;
            this.a = (long) aVar.a.length;
            this.c = aVar.b;
            this.d = aVar.c;
            this.e = aVar.d;
            this.f = aVar.e;
            this.g = aVar.f;
            this.h = aVar.g;
        }

        public static a a(InputStream inputStream) throws IOException {
            a aVar = new a();
            if (c.a(inputStream) != 538247942) {
                throw new IOException();
            }
            aVar.b = c.c(inputStream);
            aVar.c = c.c(inputStream);
            if (aVar.c.equals("")) {
                aVar.c = null;
            }
            aVar.d = c.b(inputStream);
            aVar.e = c.b(inputStream);
            aVar.f = c.b(inputStream);
            aVar.g = c.b(inputStream);
            aVar.h = c.d(inputStream);
            return aVar;
        }

        public com.android.volley.a.a a(byte[] bArr) {
            com.android.volley.a.a aVar = new com.android.volley.a.a();
            aVar.a = bArr;
            aVar.b = this.c;
            aVar.c = this.d;
            aVar.d = this.e;
            aVar.e = this.f;
            aVar.f = this.g;
            aVar.g = this.h;
            return aVar;
        }

        public boolean a(OutputStream outputStream) {
            try {
                c.a(outputStream, 538247942);
                c.a(outputStream, this.b);
                c.a(outputStream, this.c == null ? "" : this.c);
                c.a(outputStream, this.d);
                c.a(outputStream, this.e);
                c.a(outputStream, this.f);
                c.a(outputStream, this.g);
                c.a(this.h, outputStream);
                outputStream.flush();
                return true;
            } catch (IOException e) {
                l.b("%s", e.toString());
                return false;
            }
        }
    }

    private static class b extends FilterInputStream {
        private int a;

        private b(InputStream inputStream) {
            super(inputStream);
            this.a = 0;
        }

        public int read() throws IOException {
            int read = super.read();
            if (read != -1) {
                this.a++;
            }
            return read;
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int read = super.read(bArr, i, i2);
            if (read != -1) {
                this.a += read;
            }
            return read;
        }
    }

    public c(File file, int i) {
        this.c = file;
        this.d = i;
    }

    public synchronized void b() {
        File[] listFiles = this.c.listFiles();
        if (listFiles != null) {
            for (File delete : listFiles) {
                delete.delete();
            }
        }
        this.a.clear();
        this.b = 0;
        l.b("Cache cleared.", new Object[0]);
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:40:0x0070=Splitter:B:40:0x0070, B:28:0x004e=Splitter:B:28:0x004e} */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0093 A:{SYNTHETIC, Splitter:B:52:0x0093} */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0088 A:{SYNTHETIC, Splitter:B:43:0x0088} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0066 A:{SYNTHETIC, Splitter:B:31:0x0066} */
    /* JADX WARNING: Missing block: B:21:0x0044, code skipped:
            return r0;
     */
    public synchronized com.android.volley.a.a a(java.lang.String r14) {
        /*
        r13 = this;
        monitor-enter(r13);
        r0 = r13.a;	 Catch:{ all -> 0x009a }
        r0 = r0.get(r14);	 Catch:{ all -> 0x009a }
        r0 = (com.android.volley.toolbox.c.a) r0;	 Catch:{ all -> 0x009a }
        r1 = 0;
        if (r0 != 0) goto L_0x000e;
    L_0x000c:
        monitor-exit(r13);
        return r1;
    L_0x000e:
        r2 = r13.c(r14);	 Catch:{ all -> 0x009a }
        r3 = 1;
        r4 = 0;
        r5 = 2;
        r6 = new com.android.volley.toolbox.c$b;	 Catch:{ IOException -> 0x006e, NegativeArraySizeException -> 0x004c, all -> 0x0049 }
        r7 = new java.io.BufferedInputStream;	 Catch:{ IOException -> 0x006e, NegativeArraySizeException -> 0x004c, all -> 0x0049 }
        r8 = new java.io.FileInputStream;	 Catch:{ IOException -> 0x006e, NegativeArraySizeException -> 0x004c, all -> 0x0049 }
        r8.<init>(r2);	 Catch:{ IOException -> 0x006e, NegativeArraySizeException -> 0x004c, all -> 0x0049 }
        r7.<init>(r8);	 Catch:{ IOException -> 0x006e, NegativeArraySizeException -> 0x004c, all -> 0x0049 }
        r6.<init>(r7);	 Catch:{ IOException -> 0x006e, NegativeArraySizeException -> 0x004c, all -> 0x0049 }
        com.android.volley.toolbox.c.a.a(r6);	 Catch:{ IOException -> 0x0047, NegativeArraySizeException -> 0x0045 }
        r7 = r2.length();	 Catch:{ IOException -> 0x0047, NegativeArraySizeException -> 0x0045 }
        r9 = r6.a;	 Catch:{ IOException -> 0x0047, NegativeArraySizeException -> 0x0045 }
        r9 = (long) r9;	 Catch:{ IOException -> 0x0047, NegativeArraySizeException -> 0x0045 }
        r11 = r7 - r9;
        r7 = (int) r11;	 Catch:{ IOException -> 0x0047, NegativeArraySizeException -> 0x0045 }
        r7 = a(r6, r7);	 Catch:{ IOException -> 0x0047, NegativeArraySizeException -> 0x0045 }
        r0 = r0.a(r7);	 Catch:{ IOException -> 0x0047, NegativeArraySizeException -> 0x0045 }
        if (r6 == 0) goto L_0x0043;
    L_0x003d:
        r6.close();	 Catch:{ IOException -> 0x0041 }
        goto L_0x0043;
    L_0x0041:
        monitor-exit(r13);
        return r1;
    L_0x0043:
        monitor-exit(r13);
        return r0;
    L_0x0045:
        r0 = move-exception;
        goto L_0x004e;
    L_0x0047:
        r0 = move-exception;
        goto L_0x0070;
    L_0x0049:
        r14 = move-exception;
        r6 = r1;
        goto L_0x0091;
    L_0x004c:
        r0 = move-exception;
        r6 = r1;
    L_0x004e:
        r7 = "%s: %s";
        r5 = new java.lang.Object[r5];	 Catch:{ all -> 0x0090 }
        r2 = r2.getAbsolutePath();	 Catch:{ all -> 0x0090 }
        r5[r4] = r2;	 Catch:{ all -> 0x0090 }
        r0 = r0.toString();	 Catch:{ all -> 0x0090 }
        r5[r3] = r0;	 Catch:{ all -> 0x0090 }
        com.android.volley.l.b(r7, r5);	 Catch:{ all -> 0x0090 }
        r13.b(r14);	 Catch:{ all -> 0x0090 }
        if (r6 == 0) goto L_0x006c;
    L_0x0066:
        r6.close();	 Catch:{ IOException -> 0x006a }
        goto L_0x006c;
    L_0x006a:
        monitor-exit(r13);
        return r1;
    L_0x006c:
        monitor-exit(r13);
        return r1;
    L_0x006e:
        r0 = move-exception;
        r6 = r1;
    L_0x0070:
        r7 = "%s: %s";
        r5 = new java.lang.Object[r5];	 Catch:{ all -> 0x0090 }
        r2 = r2.getAbsolutePath();	 Catch:{ all -> 0x0090 }
        r5[r4] = r2;	 Catch:{ all -> 0x0090 }
        r0 = r0.toString();	 Catch:{ all -> 0x0090 }
        r5[r3] = r0;	 Catch:{ all -> 0x0090 }
        com.android.volley.l.b(r7, r5);	 Catch:{ all -> 0x0090 }
        r13.b(r14);	 Catch:{ all -> 0x0090 }
        if (r6 == 0) goto L_0x008e;
    L_0x0088:
        r6.close();	 Catch:{ IOException -> 0x008c }
        goto L_0x008e;
    L_0x008c:
        monitor-exit(r13);
        return r1;
    L_0x008e:
        monitor-exit(r13);
        return r1;
    L_0x0090:
        r14 = move-exception;
    L_0x0091:
        if (r6 == 0) goto L_0x0099;
    L_0x0093:
        r6.close();	 Catch:{ IOException -> 0x0097 }
        goto L_0x0099;
    L_0x0097:
        monitor-exit(r13);
        return r1;
    L_0x0099:
        throw r14;	 Catch:{ all -> 0x009a }
    L_0x009a:
        r14 = move-exception;
        monitor-exit(r13);
        throw r14;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.c.a(java.lang.String):com.android.volley.a$a");
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:38:0x0065 */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x005c A:{SYNTHETIC, Splitter:B:33:0x005c} */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x006b A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0068 A:{SYNTHETIC, Splitter:B:41:0x0068} */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:26|27|(2:36|37)|38|39) */
    /* JADX WARNING: Missing block: B:8:0x0023, code skipped:
            return;
     */
    public synchronized void a() {
        /*
        r8 = this;
        monitor-enter(r8);
        r0 = r8.c;	 Catch:{ all -> 0x0070 }
        r0 = r0.exists();	 Catch:{ all -> 0x0070 }
        r1 = 0;
        if (r0 != 0) goto L_0x0024;
    L_0x000a:
        r0 = r8.c;	 Catch:{ all -> 0x0070 }
        r0 = r0.mkdirs();	 Catch:{ all -> 0x0070 }
        if (r0 != 0) goto L_0x0022;
    L_0x0012:
        r0 = "Unable to create cache dir %s";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x0070 }
        r3 = r8.c;	 Catch:{ all -> 0x0070 }
        r3 = r3.getAbsolutePath();	 Catch:{ all -> 0x0070 }
        r2[r1] = r3;	 Catch:{ all -> 0x0070 }
        com.android.volley.l.c(r0, r2);	 Catch:{ all -> 0x0070 }
    L_0x0022:
        monitor-exit(r8);
        return;
    L_0x0024:
        r0 = r8.c;	 Catch:{ all -> 0x0070 }
        r0 = r0.listFiles();	 Catch:{ all -> 0x0070 }
        if (r0 != 0) goto L_0x002e;
    L_0x002c:
        monitor-exit(r8);
        return;
    L_0x002e:
        r2 = r0.length;	 Catch:{ all -> 0x0070 }
    L_0x002f:
        if (r1 >= r2) goto L_0x006e;
    L_0x0031:
        r3 = r0[r1];	 Catch:{ all -> 0x0070 }
        r4 = 0;
        r5 = new java.io.BufferedInputStream;	 Catch:{ IOException -> 0x005a }
        r6 = new java.io.FileInputStream;	 Catch:{ IOException -> 0x005a }
        r6.<init>(r3);	 Catch:{ IOException -> 0x005a }
        r5.<init>(r6);	 Catch:{ IOException -> 0x005a }
        r4 = com.android.volley.toolbox.c.a.a(r5);	 Catch:{ IOException -> 0x0056, all -> 0x0053 }
        r6 = r3.length();	 Catch:{ IOException -> 0x0056, all -> 0x0053 }
        r4.a = r6;	 Catch:{ IOException -> 0x0056, all -> 0x0053 }
        r6 = r4.b;	 Catch:{ IOException -> 0x0056, all -> 0x0053 }
        r8.a(r6, r4);	 Catch:{ IOException -> 0x0056, all -> 0x0053 }
        if (r5 == 0) goto L_0x006b;
    L_0x004f:
        r5.close();	 Catch:{ IOException -> 0x006b }
        goto L_0x006b;
    L_0x0053:
        r0 = move-exception;
        r4 = r5;
        goto L_0x0060;
    L_0x0056:
        r4 = r5;
        goto L_0x005a;
    L_0x0058:
        r0 = move-exception;
        goto L_0x0060;
    L_0x005a:
        if (r3 == 0) goto L_0x0066;
    L_0x005c:
        r3.delete();	 Catch:{ all -> 0x0058 }
        goto L_0x0066;
    L_0x0060:
        if (r4 == 0) goto L_0x0065;
    L_0x0062:
        r4.close();	 Catch:{ IOException -> 0x0065 }
    L_0x0065:
        throw r0;	 Catch:{ all -> 0x0070 }
    L_0x0066:
        if (r4 == 0) goto L_0x006b;
    L_0x0068:
        r4.close();	 Catch:{ IOException -> 0x006b }
    L_0x006b:
        r1 = r1 + 1;
        goto L_0x002f;
    L_0x006e:
        monitor-exit(r8);
        return;
    L_0x0070:
        r0 = move-exception;
        monitor-exit(r8);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.c.a():void");
    }

    public synchronized void a(String str, boolean z) {
        com.android.volley.a.a a = a(str);
        if (a != null) {
            a.f = 0;
            if (z) {
                a.e = 0;
            }
            a(str, a);
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0045 */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:12|13|(1:15)|16|17) */
    /* JADX WARNING: Missing block: B:14:0x0049, code skipped:
            if (r0.delete() == false) goto L_0x004b;
     */
    /* JADX WARNING: Missing block: B:15:0x004b, code skipped:
            com.android.volley.l.b("Could not clean up file %s", r0.getAbsolutePath());
     */
    /* JADX WARNING: Missing block: B:17:0x0059, code skipped:
            return;
     */
    public synchronized void a(java.lang.String r7, com.android.volley.a.a r8) {
        /*
        r6 = this;
        monitor-enter(r6);
        r0 = r8.a;	 Catch:{ all -> 0x005a }
        r0 = r0.length;	 Catch:{ all -> 0x005a }
        r6.a(r0);	 Catch:{ all -> 0x005a }
        r0 = r6.c(r7);	 Catch:{ all -> 0x005a }
        r1 = 0;
        r2 = 1;
        r3 = new java.io.BufferedOutputStream;	 Catch:{ IOException -> 0x0045 }
        r4 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x0045 }
        r4.<init>(r0);	 Catch:{ IOException -> 0x0045 }
        r3.<init>(r4);	 Catch:{ IOException -> 0x0045 }
        r4 = new com.android.volley.toolbox.c$a;	 Catch:{ IOException -> 0x0045 }
        r4.<init>(r7, r8);	 Catch:{ IOException -> 0x0045 }
        r5 = r4.a(r3);	 Catch:{ IOException -> 0x0045 }
        if (r5 != 0) goto L_0x0038;
    L_0x0022:
        r3.close();	 Catch:{ IOException -> 0x0045 }
        r7 = "Failed to write header for %s";
        r8 = new java.lang.Object[r2];	 Catch:{ IOException -> 0x0045 }
        r3 = r0.getAbsolutePath();	 Catch:{ IOException -> 0x0045 }
        r8[r1] = r3;	 Catch:{ IOException -> 0x0045 }
        com.android.volley.l.b(r7, r8);	 Catch:{ IOException -> 0x0045 }
        r7 = new java.io.IOException;	 Catch:{ IOException -> 0x0045 }
        r7.<init>();	 Catch:{ IOException -> 0x0045 }
        throw r7;	 Catch:{ IOException -> 0x0045 }
    L_0x0038:
        r8 = r8.a;	 Catch:{ IOException -> 0x0045 }
        r3.write(r8);	 Catch:{ IOException -> 0x0045 }
        r3.close();	 Catch:{ IOException -> 0x0045 }
        r6.a(r7, r4);	 Catch:{ IOException -> 0x0045 }
        monitor-exit(r6);
        return;
    L_0x0045:
        r7 = r0.delete();	 Catch:{ all -> 0x005a }
        if (r7 != 0) goto L_0x0058;
    L_0x004b:
        r7 = "Could not clean up file %s";
        r8 = new java.lang.Object[r2];	 Catch:{ all -> 0x005a }
        r0 = r0.getAbsolutePath();	 Catch:{ all -> 0x005a }
        r8[r1] = r0;	 Catch:{ all -> 0x005a }
        com.android.volley.l.b(r7, r8);	 Catch:{ all -> 0x005a }
    L_0x0058:
        monitor-exit(r6);
        return;
    L_0x005a:
        r7 = move-exception;
        monitor-exit(r6);
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.c.a(java.lang.String, com.android.volley.a$a):void");
    }

    public synchronized void b(String str) {
        boolean delete = c(str).delete();
        e(str);
        if (!delete) {
            l.b("Could not delete cache entry for key=%s, filename=%s", str, d(str));
        }
    }

    private String d(String str) {
        int length = str.length() / 2;
        String valueOf = String.valueOf(str.substring(0, length).hashCode());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(valueOf);
        stringBuilder.append(String.valueOf(str.substring(length).hashCode()));
        return stringBuilder.toString();
    }

    public File c(String str) {
        return new File(this.c, d(str));
    }

    private void a(int i) {
        long j = (long) i;
        if (this.b + j >= ((long) this.d)) {
            long j2;
            if (l.b) {
                l.a("Pruning old cache entries.", new Object[0]);
            }
            long j3 = this.b;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            Iterator it = this.a.entrySet().iterator();
            int i2 = 0;
            while (it.hasNext()) {
                long j4;
                a aVar = (a) ((Entry) it.next()).getValue();
                if (c(aVar.b).delete()) {
                    j4 = j;
                    j2 = elapsedRealtime;
                    this.b -= aVar.a;
                } else {
                    j4 = j;
                    j2 = elapsedRealtime;
                    l.b("Could not delete cache entry for key=%s, filename=%s", aVar.b, d(aVar.b));
                }
                it.remove();
                i2++;
                if (((float) (this.b + j4)) < ((float) this.d) * 0.9f) {
                    break;
                }
                j = j4;
                elapsedRealtime = j2;
            }
            j2 = elapsedRealtime;
            if (l.b) {
                l.a("pruned %d files, %d bytes, %d ms", Integer.valueOf(i2), Long.valueOf(this.b - j3), Long.valueOf(SystemClock.elapsedRealtime() - j2));
            }
        }
    }

    private void a(String str, a aVar) {
        if (this.a.containsKey(str)) {
            this.b += aVar.a - ((a) this.a.get(str)).a;
        } else {
            this.b += aVar.a;
        }
        this.a.put(str, aVar);
    }

    private void e(String str) {
        a aVar = (a) this.a.get(str);
        if (aVar != null) {
            this.b -= aVar.a;
            this.a.remove(str);
        }
    }

    private static byte[] a(InputStream inputStream, int i) throws IOException {
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int read = inputStream.read(bArr, i2, i - i2);
            if (read == -1) {
                break;
            }
            i2 += read;
        }
        if (i2 == i) {
            return bArr;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Expected ");
        stringBuilder.append(i);
        stringBuilder.append(" bytes, read ");
        stringBuilder.append(i2);
        stringBuilder.append(" bytes");
        throw new IOException(stringBuilder.toString());
    }

    private static int e(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (read != -1) {
            return read;
        }
        throw new EOFException();
    }

    static void a(OutputStream outputStream, int i) throws IOException {
        outputStream.write((i >> 0) & 255);
        outputStream.write((i >> 8) & 255);
        outputStream.write((i >> 16) & 255);
        outputStream.write((i >> 24) & 255);
    }

    static int a(InputStream inputStream) throws IOException {
        return (e(inputStream) << 24) | ((((e(inputStream) << 0) | 0) | (e(inputStream) << 8)) | (e(inputStream) << 16));
    }

    static void a(OutputStream outputStream, long j) throws IOException {
        outputStream.write((byte) ((int) (j >>> null)));
        outputStream.write((byte) ((int) (j >>> 8)));
        outputStream.write((byte) ((int) (j >>> 16)));
        outputStream.write((byte) ((int) (j >>> 24)));
        outputStream.write((byte) ((int) (j >>> 32)));
        outputStream.write((byte) ((int) (j >>> 40)));
        outputStream.write((byte) ((int) (j >>> 48)));
        outputStream.write((byte) ((int) (j >>> 56)));
    }

    static long b(InputStream inputStream) throws IOException {
        return (((((((0 | ((((long) e(inputStream)) & 255) << null)) | ((((long) e(inputStream)) & 255) << 8)) | ((((long) e(inputStream)) & 255) << 16)) | ((((long) e(inputStream)) & 255) << 24)) | ((((long) e(inputStream)) & 255) << 32)) | ((((long) e(inputStream)) & 255) << 40)) | ((((long) e(inputStream)) & 255) << 48)) | ((((long) e(inputStream)) & 255) << 56);
    }

    static void a(OutputStream outputStream, String str) throws IOException {
        byte[] bytes = str.getBytes("UTF-8");
        a(outputStream, (long) bytes.length);
        outputStream.write(bytes, 0, bytes.length);
    }

    static String c(InputStream inputStream) throws IOException {
        return new String(a(inputStream, (int) b(inputStream)), "UTF-8");
    }

    static void a(Map<String, String> map, OutputStream outputStream) throws IOException {
        if (map != null) {
            a(outputStream, map.size());
            for (Entry entry : map.entrySet()) {
                a(outputStream, (String) entry.getKey());
                a(outputStream, (String) entry.getValue());
            }
            return;
        }
        a(outputStream, 0);
    }

    static Map<String, String> d(InputStream inputStream) throws IOException {
        int a = a(inputStream);
        Map emptyMap = a == 0 ? Collections.emptyMap() : new HashMap(a);
        for (int i = 0; i < a; i++) {
            emptyMap.put(c(inputStream).intern(), c(inputStream).intern());
        }
        return emptyMap;
    }
}
