package com.til.colombia.android.commons.a;

import android.support.annotation.NonNull;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public final class b implements Closeable {
    static final String a = "journal";
    static final String b = "journal.tmp";
    static final String c = "journal.bkp";
    static final String d = "libcore.io.DiskLruCache";
    static final String e = "1";
    static final long f = -1;
    static final Pattern g = Pattern.compile("[a-z0-9_-]{1,64}");
    private static final String j = "CLEAN";
    private static final String k = "DIRTY";
    private static final String l = "REMOVE";
    private static final String m = "READ";
    private static final OutputStream z = new d();
    final File h;
    final ThreadPoolExecutor i = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
    private final File n;
    private final File o;
    private final File p;
    private final int q;
    private long r;
    private final int s;
    private long t = 0;
    private Writer u;
    private final LinkedHashMap<String, b> v = new LinkedHashMap(0, 0.75f, true);
    private int w;
    private long x = 0;
    private final Callable<Void> y = new c(this);

    public final class a {
        final b a;
        final boolean[] b;
        boolean c;
        boolean d;

        private class a extends FilterOutputStream {
            /* synthetic */ a(a aVar, OutputStream outputStream, byte b) {
                this(outputStream);
            }

            private a(OutputStream outputStream) {
                super(outputStream);
            }

            public final void write(int i) {
                try {
                    this.out.write(i);
                } catch (IOException unused) {
                    a.this.c = true;
                }
            }

            public final void write(@NonNull byte[] bArr, int i, int i2) {
                try {
                    this.out.write(bArr, i, i2);
                } catch (IOException unused) {
                    a.this.c = true;
                }
            }

            public final void close() {
                try {
                    this.out.close();
                } catch (IOException unused) {
                    a.this.c = true;
                }
            }

            public final void flush() {
                try {
                    this.out.flush();
                } catch (IOException unused) {
                    a.this.c = true;
                }
            }
        }

        /* synthetic */ a(b bVar, b bVar2, byte b) {
            this(bVar2);
        }

        private a(b bVar) {
            this.a = bVar;
            this.b = bVar.c ? null : new boolean[b.this.s];
        }

        private InputStream b(int i) throws IOException {
            synchronized (b.this) {
                if (this.a.d != this) {
                    throw new IllegalStateException();
                } else if (this.a.c) {
                    try {
                        FileInputStream fileInputStream = new FileInputStream(this.a.a(i));
                        return fileInputStream;
                    } catch (FileNotFoundException unused) {
                        return null;
                    }
                } else {
                    return null;
                }
            }
        }

        private String c(int i) throws IOException {
            InputStream b = b(i);
            return b != null ? g.a(new InputStreamReader(b, g.b)) : null;
        }

        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0026 */
        /* JADX WARNING: Can't wrap try/catch for region: R(12:7|(1:9)|10|11|12|13|14|15|16|17|18|19) */
        public final java.io.OutputStream a(int r4) throws java.io.IOException {
            /*
            r3 = this;
            r0 = com.til.colombia.android.commons.a.b.this;
            monitor-enter(r0);
            r1 = r3.a;	 Catch:{ all -> 0x0042 }
            r1 = r1.d;	 Catch:{ all -> 0x0042 }
            if (r1 == r3) goto L_0x000f;
        L_0x0009:
            r4 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0042 }
            r4.<init>();	 Catch:{ all -> 0x0042 }
            throw r4;	 Catch:{ all -> 0x0042 }
        L_0x000f:
            r1 = r3.a;	 Catch:{ all -> 0x0042 }
            r1 = r1.c;	 Catch:{ all -> 0x0042 }
            if (r1 != 0) goto L_0x001a;
        L_0x0015:
            r1 = r3.b;	 Catch:{ all -> 0x0042 }
            r2 = 1;
            r1[r4] = r2;	 Catch:{ all -> 0x0042 }
        L_0x001a:
            r1 = r3.a;	 Catch:{ all -> 0x0042 }
            r4 = r1.b(r4);	 Catch:{ all -> 0x0042 }
            r1 = new java.io.FileOutputStream;	 Catch:{ FileNotFoundException -> 0x0026 }
            r1.<init>(r4);	 Catch:{ FileNotFoundException -> 0x0026 }
            goto L_0x0034;
        L_0x0026:
            r1 = com.til.colombia.android.commons.a.b.this;	 Catch:{ all -> 0x0042 }
            r1 = r1.h;	 Catch:{ all -> 0x0042 }
            r1.mkdirs();	 Catch:{ all -> 0x0042 }
            r1 = new java.io.FileOutputStream;	 Catch:{ FileNotFoundException -> 0x003c }
            r1.<init>(r4);	 Catch:{ FileNotFoundException -> 0x003c }
        L_0x0034:
            r4 = new com.til.colombia.android.commons.a.b$a$a;	 Catch:{ all -> 0x0042 }
            r2 = 0;
            r4.<init>(r3, r1, r2);	 Catch:{ all -> 0x0042 }
            monitor-exit(r0);	 Catch:{ all -> 0x0042 }
            return r4;
        L_0x003c:
            r4 = com.til.colombia.android.commons.a.b.z;	 Catch:{ all -> 0x0042 }
            monitor-exit(r0);	 Catch:{ all -> 0x0042 }
            return r4;
        L_0x0042:
            r4 = move-exception;
            monitor-exit(r0);	 Catch:{ all -> 0x0042 }
            throw r4;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.commons.a.b$a.a(int):java.io.OutputStream");
        }

        private void a(int i, String str) throws IOException {
            Throwable th;
            Closeable closeable = null;
            try {
                Closeable outputStreamWriter = new OutputStreamWriter(a(i), g.b);
                try {
                    outputStreamWriter.write(str);
                    g.a(outputStreamWriter);
                } catch (Throwable th2) {
                    th = th2;
                    closeable = outputStreamWriter;
                    g.a(closeable);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                g.a(closeable);
                throw th;
            }
        }

        public final void a() throws IOException {
            if (this.c) {
                b.this.a(this, false);
                b.this.c(this.a.a);
            } else {
                b.this.a(this, true);
            }
            this.d = true;
        }

        public final void b() throws IOException {
            b.this.a(this, false);
        }

        private void c() {
            if (!this.d) {
                try {
                    b();
                } catch (IOException unused) {
                }
            }
        }
    }

    private final class b {
        final String a;
        final long[] b;
        boolean c;
        a d;
        long e;

        /* synthetic */ b(b bVar, String str, byte b) {
            this(str);
        }

        private b(String str) {
            this.a = str;
            this.b = new long[b.this.s];
        }

        public final String a() throws IOException {
            StringBuilder stringBuilder = new StringBuilder();
            for (long j : this.b) {
                stringBuilder.append(' ');
                stringBuilder.append(j);
            }
            return stringBuilder.toString();
        }

        /* Access modifiers changed, original: 0000 */
        public void a(String[] strArr) throws IOException {
            if (strArr.length != b.this.s) {
                throw b(strArr);
            }
            int i = 0;
            while (i < strArr.length) {
                try {
                    this.b[i] = Long.parseLong(strArr[i]);
                    i++;
                } catch (NumberFormatException unused) {
                    throw b(strArr);
                }
            }
        }

        private static IOException b(String[] strArr) throws IOException {
            StringBuilder stringBuilder = new StringBuilder("unexpected journal line: ");
            stringBuilder.append(Arrays.toString(strArr));
            throw new IOException(stringBuilder.toString());
        }

        public final File a(int i) {
            File f = b.this.h;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.a);
            stringBuilder.append(".");
            stringBuilder.append(i);
            return new File(f, stringBuilder.toString());
        }

        public final File b(int i) {
            File f = b.this.h;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.a);
            stringBuilder.append(".");
            stringBuilder.append(i);
            stringBuilder.append(".tmp");
            return new File(f, stringBuilder.toString());
        }
    }

    public final class c implements Closeable {
        final InputStream[] a;
        final long[] b;
        private final String d;
        private final long e;

        /* synthetic */ c(b bVar, String str, long j, InputStream[] inputStreamArr, long[] jArr, byte b) {
            this(str, j, inputStreamArr, jArr);
        }

        private c(String str, long j, InputStream[] inputStreamArr, long[] jArr) {
            this.d = str;
            this.e = j;
            this.a = inputStreamArr;
            this.b = jArr;
        }

        private a a() throws IOException {
            return b.this.a(this.d, this.e);
        }

        public final InputStream a(int i) {
            return this.a[i];
        }

        public final long b(int i) {
            return this.b[0];
        }

        public final void close() {
            for (Closeable a : this.a) {
                g.a(a);
            }
        }

        private String c(int i) throws IOException {
            return g.a(new InputStreamReader(this.a[i], g.b));
        }
    }

    private b(File file, int i, int i2, long j) {
        File file2 = file;
        this.h = file2;
        this.q = i;
        this.n = new File(file2, a);
        this.o = new File(file2, b);
        this.p = new File(file2, c);
        this.s = i2;
        this.r = j;
    }

    public static b a(File file, int i, int i2, long j) throws IOException {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        File file2 = new File(file, c);
        if (file2.exists()) {
            File file3 = new File(file, a);
            if (file3.exists()) {
                file2.delete();
            } else {
                a(file2, file3, false);
            }
        }
        b bVar = new b(file, 1, 1, j);
        if (bVar.n.exists()) {
            try {
                bVar.e();
                bVar.f();
                bVar.u = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(bVar.n, true), g.a));
                return bVar;
            } catch (IOException e) {
                PrintStream printStream = System.out;
                StringBuilder stringBuilder = new StringBuilder("DiskLruCache ");
                stringBuilder.append(file);
                stringBuilder.append(" is corrupt: ");
                stringBuilder.append(e.getMessage());
                stringBuilder.append(", removing");
                printStream.println(stringBuilder.toString());
                bVar.c();
            }
        }
        file.mkdirs();
        bVar = new b(file, 1, 1, j);
        bVar.g();
        return bVar;
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:13:0x0056=Splitter:B:13:0x0056, B:52:0x0116=Splitter:B:52:0x0116} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x0109 */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:49|50|65|51) */
    /* JADX WARNING: Missing block: B:50:?, code skipped:
            r10.w = r2 - r10.v.size();
     */
    /* JADX WARNING: Missing block: B:51:0x0115, code skipped:
            return;
     */
    private void e() throws java.io.IOException {
        /*
        r10 = this;
        r0 = new com.til.colombia.android.commons.a.e;
        r1 = new java.io.FileInputStream;
        r2 = r10.n;
        r1.<init>(r2);
        r2 = com.til.colombia.android.commons.a.g.a;
        r0.<init>(r1, r2);
        r1 = r0.a();	 Catch:{ all -> 0x0147 }
        r2 = r0.a();	 Catch:{ all -> 0x0147 }
        r3 = r0.a();	 Catch:{ all -> 0x0147 }
        r4 = r0.a();	 Catch:{ all -> 0x0147 }
        r5 = r0.a();	 Catch:{ all -> 0x0147 }
        r6 = "libcore.io.DiskLruCache";
        r6 = r6.equals(r1);	 Catch:{ all -> 0x0147 }
        if (r6 == 0) goto L_0x0116;
    L_0x002a:
        r6 = "1";
        r6 = r6.equals(r2);	 Catch:{ all -> 0x0147 }
        if (r6 == 0) goto L_0x0116;
    L_0x0032:
        r6 = r10.q;	 Catch:{ all -> 0x0147 }
        r6 = java.lang.Integer.toString(r6);	 Catch:{ all -> 0x0147 }
        r3 = r6.equals(r3);	 Catch:{ all -> 0x0147 }
        if (r3 == 0) goto L_0x0116;
    L_0x003e:
        r3 = r10.s;	 Catch:{ all -> 0x0147 }
        r3 = java.lang.Integer.toString(r3);	 Catch:{ all -> 0x0147 }
        r3 = r3.equals(r4);	 Catch:{ all -> 0x0147 }
        if (r3 == 0) goto L_0x0116;
    L_0x004a:
        r3 = "";
        r3 = r3.equals(r5);	 Catch:{ all -> 0x0147 }
        if (r3 != 0) goto L_0x0054;
    L_0x0052:
        goto L_0x0116;
    L_0x0054:
        r1 = 0;
        r2 = r1;
    L_0x0056:
        r3 = r0.a();	 Catch:{ EOFException -> 0x0109 }
        r4 = 32;
        r5 = r3.indexOf(r4);	 Catch:{ EOFException -> 0x0109 }
        r6 = -1;
        if (r5 != r6) goto L_0x0077;
    L_0x0063:
        r1 = new java.io.IOException;	 Catch:{ EOFException -> 0x0109 }
        r4 = new java.lang.StringBuilder;	 Catch:{ EOFException -> 0x0109 }
        r5 = "unexpected journal line: ";
        r4.<init>(r5);	 Catch:{ EOFException -> 0x0109 }
        r4.append(r3);	 Catch:{ EOFException -> 0x0109 }
        r3 = r4.toString();	 Catch:{ EOFException -> 0x0109 }
        r1.<init>(r3);	 Catch:{ EOFException -> 0x0109 }
        throw r1;	 Catch:{ EOFException -> 0x0109 }
    L_0x0077:
        r7 = r5 + 1;
        r4 = r3.indexOf(r4, r7);	 Catch:{ EOFException -> 0x0109 }
        if (r4 != r6) goto L_0x0094;
    L_0x007f:
        r7 = r3.substring(r7);	 Catch:{ EOFException -> 0x0109 }
        r8 = 6;
        if (r5 != r8) goto L_0x0098;
    L_0x0086:
        r8 = "REMOVE";
        r8 = r3.startsWith(r8);	 Catch:{ EOFException -> 0x0109 }
        if (r8 == 0) goto L_0x0098;
    L_0x008e:
        r3 = r10.v;	 Catch:{ EOFException -> 0x0109 }
        r3.remove(r7);	 Catch:{ EOFException -> 0x0109 }
        goto L_0x00f1;
    L_0x0094:
        r7 = r3.substring(r7, r4);	 Catch:{ EOFException -> 0x0109 }
    L_0x0098:
        r8 = r10.v;	 Catch:{ EOFException -> 0x0109 }
        r8 = r8.get(r7);	 Catch:{ EOFException -> 0x0109 }
        r8 = (com.til.colombia.android.commons.a.b.b) r8;	 Catch:{ EOFException -> 0x0109 }
        if (r8 != 0) goto L_0x00ac;
    L_0x00a2:
        r8 = new com.til.colombia.android.commons.a.b$b;	 Catch:{ EOFException -> 0x0109 }
        r8.<init>(r10, r7, r1);	 Catch:{ EOFException -> 0x0109 }
        r9 = r10.v;	 Catch:{ EOFException -> 0x0109 }
        r9.put(r7, r8);	 Catch:{ EOFException -> 0x0109 }
    L_0x00ac:
        r7 = 5;
        if (r4 == r6) goto L_0x00cf;
    L_0x00af:
        if (r5 != r7) goto L_0x00cf;
    L_0x00b1:
        r9 = "CLEAN";
        r9 = r3.startsWith(r9);	 Catch:{ EOFException -> 0x0109 }
        if (r9 == 0) goto L_0x00cf;
    L_0x00b9:
        r4 = r4 + 1;
        r3 = r3.substring(r4);	 Catch:{ EOFException -> 0x0109 }
        r4 = " ";
        r3 = r3.split(r4);	 Catch:{ EOFException -> 0x0109 }
        r4 = 1;
        r8.c = r4;	 Catch:{ EOFException -> 0x0109 }
        r4 = 0;
        r8.d = r4;	 Catch:{ EOFException -> 0x0109 }
        r8.a(r3);	 Catch:{ EOFException -> 0x0109 }
        goto L_0x00f1;
    L_0x00cf:
        if (r4 != r6) goto L_0x00e3;
    L_0x00d1:
        if (r5 != r7) goto L_0x00e3;
    L_0x00d3:
        r7 = "DIRTY";
        r7 = r3.startsWith(r7);	 Catch:{ EOFException -> 0x0109 }
        if (r7 == 0) goto L_0x00e3;
    L_0x00db:
        r3 = new com.til.colombia.android.commons.a.b$a;	 Catch:{ EOFException -> 0x0109 }
        r3.<init>(r10, r8, r1);	 Catch:{ EOFException -> 0x0109 }
        r8.d = r3;	 Catch:{ EOFException -> 0x0109 }
        goto L_0x00f1;
    L_0x00e3:
        if (r4 != r6) goto L_0x00f5;
    L_0x00e5:
        r4 = 4;
        if (r5 != r4) goto L_0x00f5;
    L_0x00e8:
        r4 = "READ";
        r4 = r3.startsWith(r4);	 Catch:{ EOFException -> 0x0109 }
        if (r4 != 0) goto L_0x00f1;
    L_0x00f0:
        goto L_0x00f5;
    L_0x00f1:
        r2 = r2 + 1;
        goto L_0x0056;
    L_0x00f5:
        r1 = new java.io.IOException;	 Catch:{ EOFException -> 0x0109 }
        r4 = new java.lang.StringBuilder;	 Catch:{ EOFException -> 0x0109 }
        r5 = "unexpected journal line: ";
        r4.<init>(r5);	 Catch:{ EOFException -> 0x0109 }
        r4.append(r3);	 Catch:{ EOFException -> 0x0109 }
        r3 = r4.toString();	 Catch:{ EOFException -> 0x0109 }
        r1.<init>(r3);	 Catch:{ EOFException -> 0x0109 }
        throw r1;	 Catch:{ EOFException -> 0x0109 }
    L_0x0109:
        r1 = r10.v;	 Catch:{ all -> 0x0147 }
        r1 = r1.size();	 Catch:{ all -> 0x0147 }
        r2 = r2 - r1;
        r10.w = r2;	 Catch:{ all -> 0x0147 }
        com.til.colombia.android.commons.a.g.a(r0);
        return;
    L_0x0116:
        r3 = new java.io.IOException;	 Catch:{ all -> 0x0147 }
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0147 }
        r7 = "unexpected journal header: [";
        r6.<init>(r7);	 Catch:{ all -> 0x0147 }
        r6.append(r1);	 Catch:{ all -> 0x0147 }
        r1 = ", ";
        r6.append(r1);	 Catch:{ all -> 0x0147 }
        r6.append(r2);	 Catch:{ all -> 0x0147 }
        r1 = ", ";
        r6.append(r1);	 Catch:{ all -> 0x0147 }
        r6.append(r4);	 Catch:{ all -> 0x0147 }
        r1 = ", ";
        r6.append(r1);	 Catch:{ all -> 0x0147 }
        r6.append(r5);	 Catch:{ all -> 0x0147 }
        r1 = "]";
        r6.append(r1);	 Catch:{ all -> 0x0147 }
        r1 = r6.toString();	 Catch:{ all -> 0x0147 }
        r3.<init>(r1);	 Catch:{ all -> 0x0147 }
        throw r3;	 Catch:{ all -> 0x0147 }
    L_0x0147:
        r1 = move-exception;
        com.til.colombia.android.commons.a.g.a(r0);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.commons.a.b.e():void");
    }

    private void d(String str) throws IOException {
        int indexOf = str.indexOf(32);
        StringBuilder stringBuilder;
        if (indexOf == -1) {
            stringBuilder = new StringBuilder("unexpected journal line: ");
            stringBuilder.append(str);
            throw new IOException(stringBuilder.toString());
        }
        Object substring;
        int i = indexOf + 1;
        int indexOf2 = str.indexOf(32, i);
        if (indexOf2 == -1) {
            substring = str.substring(i);
            if (indexOf == 6 && str.startsWith(l)) {
                this.v.remove(substring);
                return;
            }
        }
        substring = str.substring(i, indexOf2);
        b bVar = (b) this.v.get(substring);
        if (bVar == null) {
            bVar = new b(this, substring, (byte) 0);
            this.v.put(substring, bVar);
        }
        if (indexOf2 != -1 && indexOf == 5 && str.startsWith(j)) {
            String[] split = str.substring(indexOf2 + 1).split(" ");
            bVar.c = true;
            bVar.d = null;
            bVar.a(split);
        } else if (indexOf2 == -1 && indexOf == 5 && str.startsWith(k)) {
            bVar.d = new a(this, bVar, (byte) 0);
        } else if (indexOf2 != -1 || indexOf != 4 || !str.startsWith(m)) {
            stringBuilder = new StringBuilder("unexpected journal line: ");
            stringBuilder.append(str);
            throw new IOException(stringBuilder.toString());
        }
    }

    private void f() throws IOException {
        a(this.o);
        Iterator it = this.v.values().iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            int i = 0;
            if (bVar.d == null) {
                while (i < this.s) {
                    this.t += bVar.b[i];
                    i++;
                }
            } else {
                bVar.d = null;
                while (i < this.s) {
                    a(bVar.a(i));
                    a(bVar.b(i));
                    i++;
                }
                it.remove();
            }
        }
    }

    private synchronized void g() throws IOException {
        if (this.u != null) {
            this.u.close();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.o), g.a));
        try {
            bufferedWriter.write(d);
            bufferedWriter.write("\n");
            bufferedWriter.write("1");
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.q));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.s));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (b bVar : this.v.values()) {
                StringBuilder stringBuilder;
                if (bVar.d != null) {
                    stringBuilder = new StringBuilder("DIRTY ");
                    stringBuilder.append(bVar.a);
                    stringBuilder.append(10);
                    bufferedWriter.write(stringBuilder.toString());
                } else {
                    stringBuilder = new StringBuilder("CLEAN ");
                    stringBuilder.append(bVar.a);
                    stringBuilder.append(bVar.a());
                    stringBuilder.append(10);
                    bufferedWriter.write(stringBuilder.toString());
                }
            }
            if (this.n.exists()) {
                a(this.n, this.p, true);
            }
            a(this.o, this.n, false);
            this.p.delete();
            this.u = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.n, true), g.a));
        } finally {
            bufferedWriter.close();
        }
    }

    private static void a(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private static void a(File file, File file2, boolean z) throws IOException {
        if (z) {
            a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0065 */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:32|33|28|27) */
    /* JADX WARNING: Missing block: B:21:?, code skipped:
            r11.w++;
            r11.u.append("READ ").append(r12).append(10);
     */
    /* JADX WARNING: Missing block: B:22:0x004d, code skipped:
            if (j() == false) goto L_0x0056;
     */
    /* JADX WARNING: Missing block: B:23:0x004f, code skipped:
            r11.i.submit(r11.y);
     */
    /* JADX WARNING: Missing block: B:26:0x0064, code skipped:
            return new com.til.colombia.android.commons.a.b.c(r11, r12, r0.e, r8, r0.b, (byte) 0);
     */
    /* JADX WARNING: Missing block: B:35:0x0076, code skipped:
            return null;
     */
    public final synchronized com.til.colombia.android.commons.a.b.c a(java.lang.String r12) throws java.io.IOException {
        /*
        r11 = this;
        monitor-enter(r11);
        r11.l();	 Catch:{ all -> 0x0077 }
        e(r12);	 Catch:{ all -> 0x0077 }
        r0 = r11.v;	 Catch:{ all -> 0x0077 }
        r0 = r0.get(r12);	 Catch:{ all -> 0x0077 }
        r0 = (com.til.colombia.android.commons.a.b.b) r0;	 Catch:{ all -> 0x0077 }
        r1 = 0;
        if (r0 != 0) goto L_0x0014;
    L_0x0012:
        monitor-exit(r11);
        return r1;
    L_0x0014:
        r2 = r0.c;	 Catch:{ all -> 0x0077 }
        if (r2 != 0) goto L_0x001a;
    L_0x0018:
        monitor-exit(r11);
        return r1;
    L_0x001a:
        r2 = r11.s;	 Catch:{ all -> 0x0077 }
        r8 = new java.io.InputStream[r2];	 Catch:{ all -> 0x0077 }
        r2 = 0;
        r3 = r2;
    L_0x0020:
        r4 = r11.s;	 Catch:{ FileNotFoundException -> 0x0065 }
        if (r3 >= r4) goto L_0x0032;
    L_0x0024:
        r4 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x0065 }
        r5 = r0.a(r3);	 Catch:{ FileNotFoundException -> 0x0065 }
        r4.<init>(r5);	 Catch:{ FileNotFoundException -> 0x0065 }
        r8[r3] = r4;	 Catch:{ FileNotFoundException -> 0x0065 }
        r3 = r3 + 1;
        goto L_0x0020;
    L_0x0032:
        r1 = r11.w;	 Catch:{ all -> 0x0077 }
        r1 = r1 + 1;
        r11.w = r1;	 Catch:{ all -> 0x0077 }
        r1 = r11.u;	 Catch:{ all -> 0x0077 }
        r2 = "READ ";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0077 }
        r1 = r1.append(r12);	 Catch:{ all -> 0x0077 }
        r2 = 10;
        r1.append(r2);	 Catch:{ all -> 0x0077 }
        r1 = r11.j();	 Catch:{ all -> 0x0077 }
        if (r1 == 0) goto L_0x0056;
    L_0x004f:
        r1 = r11.i;	 Catch:{ all -> 0x0077 }
        r2 = r11.y;	 Catch:{ all -> 0x0077 }
        r1.submit(r2);	 Catch:{ all -> 0x0077 }
    L_0x0056:
        r1 = new com.til.colombia.android.commons.a.b$c;	 Catch:{ all -> 0x0077 }
        r6 = r0.e;	 Catch:{ all -> 0x0077 }
        r9 = r0.b;	 Catch:{ all -> 0x0077 }
        r10 = 0;
        r3 = r1;
        r4 = r11;
        r5 = r12;
        r3.<init>(r4, r5, r6, r8, r9, r10);	 Catch:{ all -> 0x0077 }
        monitor-exit(r11);
        return r1;
    L_0x0065:
        r12 = r11.s;	 Catch:{ all -> 0x0077 }
        if (r2 >= r12) goto L_0x0075;
    L_0x0069:
        r12 = r8[r2];	 Catch:{ all -> 0x0077 }
        if (r12 == 0) goto L_0x0075;
    L_0x006d:
        r12 = r8[r2];	 Catch:{ all -> 0x0077 }
        com.til.colombia.android.commons.a.g.a(r12);	 Catch:{ all -> 0x0077 }
        r2 = r2 + 1;
        goto L_0x0065;
    L_0x0075:
        monitor-exit(r11);
        return r1;
    L_0x0077:
        r12 = move-exception;
        monitor-exit(r11);
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.commons.a.b.a(java.lang.String):com.til.colombia.android.commons.a.b$c");
    }

    public final a b(String str) throws IOException {
        return a(str, -1);
    }

    /* Access modifiers changed, original: declared_synchronized */
    /* JADX WARNING: Missing block: B:9:0x001f, code skipped:
            return null;
     */
    public synchronized com.til.colombia.android.commons.a.b.a a(java.lang.String r6, long r7) throws java.io.IOException {
        /*
        r5 = this;
        monitor-enter(r5);
        r5.l();	 Catch:{ all -> 0x005a }
        e(r6);	 Catch:{ all -> 0x005a }
        r0 = r5.v;	 Catch:{ all -> 0x005a }
        r0 = r0.get(r6);	 Catch:{ all -> 0x005a }
        r0 = (com.til.colombia.android.commons.a.b.b) r0;	 Catch:{ all -> 0x005a }
        r1 = -1;
        r3 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1));
        r1 = 0;
        if (r3 == 0) goto L_0x0020;
    L_0x0016:
        if (r0 == 0) goto L_0x001e;
    L_0x0018:
        r2 = r0.e;	 Catch:{ all -> 0x005a }
        r4 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1));
        if (r4 == 0) goto L_0x0020;
    L_0x001e:
        monitor-exit(r5);
        return r1;
    L_0x0020:
        r7 = 0;
        if (r0 != 0) goto L_0x002e;
    L_0x0023:
        r0 = new com.til.colombia.android.commons.a.b$b;	 Catch:{ all -> 0x005a }
        r0.<init>(r5, r6, r7);	 Catch:{ all -> 0x005a }
        r8 = r5.v;	 Catch:{ all -> 0x005a }
        r8.put(r6, r0);	 Catch:{ all -> 0x005a }
        goto L_0x0034;
    L_0x002e:
        r8 = r0.d;	 Catch:{ all -> 0x005a }
        if (r8 == 0) goto L_0x0034;
    L_0x0032:
        monitor-exit(r5);
        return r1;
    L_0x0034:
        r8 = new com.til.colombia.android.commons.a.b$a;	 Catch:{ all -> 0x005a }
        r8.<init>(r5, r0, r7);	 Catch:{ all -> 0x005a }
        r0.d = r8;	 Catch:{ all -> 0x005a }
        r7 = r5.u;	 Catch:{ all -> 0x005a }
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x005a }
        r1 = "DIRTY ";
        r0.<init>(r1);	 Catch:{ all -> 0x005a }
        r0.append(r6);	 Catch:{ all -> 0x005a }
        r6 = 10;
        r0.append(r6);	 Catch:{ all -> 0x005a }
        r6 = r0.toString();	 Catch:{ all -> 0x005a }
        r7.write(r6);	 Catch:{ all -> 0x005a }
        r6 = r5.u;	 Catch:{ all -> 0x005a }
        r6.flush();	 Catch:{ all -> 0x005a }
        monitor-exit(r5);
        return r8;
    L_0x005a:
        r6 = move-exception;
        monitor-exit(r5);
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.commons.a.b.a(java.lang.String, long):com.til.colombia.android.commons.a.b$a");
    }

    public final File a() {
        return this.h;
    }

    private synchronized long h() {
        return this.r;
    }

    private synchronized void a(long j) {
        this.r = j;
        this.i.submit(this.y);
    }

    private synchronized long i() {
        return this.t;
    }

    private boolean j() {
        return this.w >= 2000 && this.w >= this.v.size();
    }

    /* JADX WARNING: Missing block: B:20:0x007c, code skipped:
            return true;
     */
    /* JADX WARNING: Missing block: B:22:0x007e, code skipped:
            return false;
     */
    public final synchronized boolean c(java.lang.String r10) throws java.io.IOException {
        /*
        r9 = this;
        monitor-enter(r9);
        r9.l();	 Catch:{ all -> 0x007f }
        e(r10);	 Catch:{ all -> 0x007f }
        r0 = r9.v;	 Catch:{ all -> 0x007f }
        r0 = r0.get(r10);	 Catch:{ all -> 0x007f }
        r0 = (com.til.colombia.android.commons.a.b.b) r0;	 Catch:{ all -> 0x007f }
        r1 = 0;
        if (r0 == 0) goto L_0x007d;
    L_0x0012:
        r2 = r0.d;	 Catch:{ all -> 0x007f }
        if (r2 == 0) goto L_0x0017;
    L_0x0016:
        goto L_0x007d;
    L_0x0017:
        r2 = r9.s;	 Catch:{ all -> 0x007f }
        if (r1 >= r2) goto L_0x0052;
    L_0x001b:
        r2 = r0.a(r1);	 Catch:{ all -> 0x007f }
        r3 = r2.exists();	 Catch:{ all -> 0x007f }
        if (r3 == 0) goto L_0x003f;
    L_0x0025:
        r3 = r2.delete();	 Catch:{ all -> 0x007f }
        if (r3 != 0) goto L_0x003f;
    L_0x002b:
        r10 = new java.io.IOException;	 Catch:{ all -> 0x007f }
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x007f }
        r1 = "failed to delete ";
        r0.<init>(r1);	 Catch:{ all -> 0x007f }
        r0.append(r2);	 Catch:{ all -> 0x007f }
        r0 = r0.toString();	 Catch:{ all -> 0x007f }
        r10.<init>(r0);	 Catch:{ all -> 0x007f }
        throw r10;	 Catch:{ all -> 0x007f }
    L_0x003f:
        r2 = r9.t;	 Catch:{ all -> 0x007f }
        r4 = r0.b;	 Catch:{ all -> 0x007f }
        r5 = r4[r1];	 Catch:{ all -> 0x007f }
        r7 = r2 - r5;
        r9.t = r7;	 Catch:{ all -> 0x007f }
        r2 = r0.b;	 Catch:{ all -> 0x007f }
        r3 = 0;
        r2[r1] = r3;	 Catch:{ all -> 0x007f }
        r1 = r1 + 1;
        goto L_0x0017;
    L_0x0052:
        r0 = r9.w;	 Catch:{ all -> 0x007f }
        r1 = 1;
        r0 = r0 + r1;
        r9.w = r0;	 Catch:{ all -> 0x007f }
        r0 = r9.u;	 Catch:{ all -> 0x007f }
        r2 = "REMOVE ";
        r0 = r0.append(r2);	 Catch:{ all -> 0x007f }
        r0 = r0.append(r10);	 Catch:{ all -> 0x007f }
        r2 = 10;
        r0.append(r2);	 Catch:{ all -> 0x007f }
        r0 = r9.v;	 Catch:{ all -> 0x007f }
        r0.remove(r10);	 Catch:{ all -> 0x007f }
        r10 = r9.j();	 Catch:{ all -> 0x007f }
        if (r10 == 0) goto L_0x007b;
    L_0x0074:
        r10 = r9.i;	 Catch:{ all -> 0x007f }
        r0 = r9.y;	 Catch:{ all -> 0x007f }
        r10.submit(r0);	 Catch:{ all -> 0x007f }
    L_0x007b:
        monitor-exit(r9);
        return r1;
    L_0x007d:
        monitor-exit(r9);
        return r1;
    L_0x007f:
        r10 = move-exception;
        monitor-exit(r9);
        throw r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.commons.a.b.c(java.lang.String):boolean");
    }

    private synchronized boolean k() {
        return this.u == null;
    }

    private void l() {
        if (this.u == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public final synchronized void b() throws IOException {
        l();
        m();
        this.u.flush();
    }

    public final synchronized void close() throws IOException {
        if (this.u != null) {
            Iterator it = new ArrayList(this.v.values()).iterator();
            while (it.hasNext()) {
                b bVar = (b) it.next();
                if (bVar.d != null) {
                    bVar.d.b();
                }
            }
            m();
            this.u.close();
            this.u = null;
        }
    }

    private void m() throws IOException {
        while (this.t > this.r) {
            c((String) ((Entry) this.v.entrySet().iterator().next()).getKey());
        }
    }

    public final void c() throws IOException {
        close();
        g.a(this.h);
    }

    private static void e(String str) {
        if (!g.matcher(str).matches()) {
            StringBuilder stringBuilder = new StringBuilder("keys must match regex [a-z0-9_-]{1,64}: \"");
            stringBuilder.append(str);
            stringBuilder.append("\"");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    private static String b(InputStream inputStream) throws IOException {
        return g.a(new InputStreamReader(inputStream, g.b));
    }

    /* JADX WARNING: Missing block: B:45:0x00f2, code skipped:
            return;
     */
    private synchronized void a(com.til.colombia.android.commons.a.b.a r12, boolean r13) throws java.io.IOException {
        /*
        r11 = this;
        monitor-enter(r11);
        r0 = r12.a;	 Catch:{ all -> 0x00f3 }
        r1 = r0.d;	 Catch:{ all -> 0x00f3 }
        if (r1 == r12) goto L_0x000d;
    L_0x0007:
        r12 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x00f3 }
        r12.<init>();	 Catch:{ all -> 0x00f3 }
        throw r12;	 Catch:{ all -> 0x00f3 }
    L_0x000d:
        r1 = 0;
        if (r13 == 0) goto L_0x0048;
    L_0x0010:
        r2 = r0.c;	 Catch:{ all -> 0x00f3 }
        if (r2 != 0) goto L_0x0048;
    L_0x0014:
        r2 = r1;
    L_0x0015:
        r3 = r11.s;	 Catch:{ all -> 0x00f3 }
        if (r2 >= r3) goto L_0x0048;
    L_0x0019:
        r3 = r12.b;	 Catch:{ all -> 0x00f3 }
        r3 = r3[r2];	 Catch:{ all -> 0x00f3 }
        if (r3 != 0) goto L_0x0036;
    L_0x001f:
        r12.b();	 Catch:{ all -> 0x00f3 }
        r12 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x00f3 }
        r13 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00f3 }
        r0 = "Newly created entry didn't create value for index ";
        r13.<init>(r0);	 Catch:{ all -> 0x00f3 }
        r13.append(r2);	 Catch:{ all -> 0x00f3 }
        r13 = r13.toString();	 Catch:{ all -> 0x00f3 }
        r12.<init>(r13);	 Catch:{ all -> 0x00f3 }
        throw r12;	 Catch:{ all -> 0x00f3 }
    L_0x0036:
        r3 = r0.b(r2);	 Catch:{ all -> 0x00f3 }
        r3 = r3.exists();	 Catch:{ all -> 0x00f3 }
        if (r3 != 0) goto L_0x0045;
    L_0x0040:
        r12.b();	 Catch:{ all -> 0x00f3 }
        monitor-exit(r11);
        return;
    L_0x0045:
        r2 = r2 + 1;
        goto L_0x0015;
    L_0x0048:
        r12 = r11.s;	 Catch:{ all -> 0x00f3 }
        if (r1 >= r12) goto L_0x007a;
    L_0x004c:
        r12 = r0.b(r1);	 Catch:{ all -> 0x00f3 }
        if (r13 == 0) goto L_0x0074;
    L_0x0052:
        r2 = r12.exists();	 Catch:{ all -> 0x00f3 }
        if (r2 == 0) goto L_0x0077;
    L_0x0058:
        r2 = r0.a(r1);	 Catch:{ all -> 0x00f3 }
        r12.renameTo(r2);	 Catch:{ all -> 0x00f3 }
        r12 = r0.b;	 Catch:{ all -> 0x00f3 }
        r3 = r12[r1];	 Catch:{ all -> 0x00f3 }
        r5 = r2.length();	 Catch:{ all -> 0x00f3 }
        r12 = r0.b;	 Catch:{ all -> 0x00f3 }
        r12[r1] = r5;	 Catch:{ all -> 0x00f3 }
        r7 = r11.t;	 Catch:{ all -> 0x00f3 }
        r9 = r7 - r3;
        r2 = r9 + r5;
        r11.t = r2;	 Catch:{ all -> 0x00f3 }
        goto L_0x0077;
    L_0x0074:
        a(r12);	 Catch:{ all -> 0x00f3 }
    L_0x0077:
        r1 = r1 + 1;
        goto L_0x0048;
    L_0x007a:
        r12 = r11.w;	 Catch:{ all -> 0x00f3 }
        r1 = 1;
        r12 = r12 + r1;
        r11.w = r12;	 Catch:{ all -> 0x00f3 }
        r12 = 0;
        r0.d = r12;	 Catch:{ all -> 0x00f3 }
        r12 = r0.c;	 Catch:{ all -> 0x00f3 }
        r12 = r12 | r13;
        r2 = 10;
        if (r12 == 0) goto L_0x00b8;
    L_0x008a:
        r0.c = r1;	 Catch:{ all -> 0x00f3 }
        r12 = r11.u;	 Catch:{ all -> 0x00f3 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00f3 }
        r3 = "CLEAN ";
        r1.<init>(r3);	 Catch:{ all -> 0x00f3 }
        r3 = r0.a;	 Catch:{ all -> 0x00f3 }
        r1.append(r3);	 Catch:{ all -> 0x00f3 }
        r3 = r0.a();	 Catch:{ all -> 0x00f3 }
        r1.append(r3);	 Catch:{ all -> 0x00f3 }
        r1.append(r2);	 Catch:{ all -> 0x00f3 }
        r1 = r1.toString();	 Catch:{ all -> 0x00f3 }
        r12.write(r1);	 Catch:{ all -> 0x00f3 }
        if (r13 == 0) goto L_0x00d7;
    L_0x00ad:
        r12 = r11.x;	 Catch:{ all -> 0x00f3 }
        r1 = 1;
        r3 = r12 + r1;
        r11.x = r3;	 Catch:{ all -> 0x00f3 }
        r0.e = r12;	 Catch:{ all -> 0x00f3 }
        goto L_0x00d7;
    L_0x00b8:
        r12 = r11.v;	 Catch:{ all -> 0x00f3 }
        r13 = r0.a;	 Catch:{ all -> 0x00f3 }
        r12.remove(r13);	 Catch:{ all -> 0x00f3 }
        r12 = r11.u;	 Catch:{ all -> 0x00f3 }
        r13 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00f3 }
        r1 = "REMOVE ";
        r13.<init>(r1);	 Catch:{ all -> 0x00f3 }
        r0 = r0.a;	 Catch:{ all -> 0x00f3 }
        r13.append(r0);	 Catch:{ all -> 0x00f3 }
        r13.append(r2);	 Catch:{ all -> 0x00f3 }
        r13 = r13.toString();	 Catch:{ all -> 0x00f3 }
        r12.write(r13);	 Catch:{ all -> 0x00f3 }
    L_0x00d7:
        r12 = r11.u;	 Catch:{ all -> 0x00f3 }
        r12.flush();	 Catch:{ all -> 0x00f3 }
        r12 = r11.t;	 Catch:{ all -> 0x00f3 }
        r0 = r11.r;	 Catch:{ all -> 0x00f3 }
        r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1));
        if (r2 > 0) goto L_0x00ea;
    L_0x00e4:
        r12 = r11.j();	 Catch:{ all -> 0x00f3 }
        if (r12 == 0) goto L_0x00f1;
    L_0x00ea:
        r12 = r11.i;	 Catch:{ all -> 0x00f3 }
        r13 = r11.y;	 Catch:{ all -> 0x00f3 }
        r12.submit(r13);	 Catch:{ all -> 0x00f3 }
    L_0x00f1:
        monitor-exit(r11);
        return;
    L_0x00f3:
        r12 = move-exception;
        monitor-exit(r11);
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.commons.a.b.a(com.til.colombia.android.commons.a.b$a, boolean):void");
    }
}
