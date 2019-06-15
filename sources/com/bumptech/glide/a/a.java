package com.bumptech.glide.a;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class a implements Closeable {
    final ThreadPoolExecutor a = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new a());
    private final File b;
    private final File c;
    private final File d;
    private final File e;
    private final int f;
    private long g;
    private final int h;
    private long i = 0;
    private Writer j;
    private final LinkedHashMap<String, c> k = new LinkedHashMap(0, 0.75f, true);
    private int l;
    private long m = 0;
    private final Callable<Void> n = new Callable<Void>() {
        /* JADX WARNING: Missing block: B:11:0x0027, code skipped:
            return null;
     */
        /* renamed from: a */
        public java.lang.Void call() throws java.lang.Exception {
            /*
            r4 = this;
            r0 = com.bumptech.glide.a.a.this;
            monitor-enter(r0);
            r1 = com.bumptech.glide.a.a.this;	 Catch:{ all -> 0x0028 }
            r1 = r1.j;	 Catch:{ all -> 0x0028 }
            r2 = 0;
            if (r1 != 0) goto L_0x000e;
        L_0x000c:
            monitor-exit(r0);	 Catch:{ all -> 0x0028 }
            return r2;
        L_0x000e:
            r1 = com.bumptech.glide.a.a.this;	 Catch:{ all -> 0x0028 }
            r1.g();	 Catch:{ all -> 0x0028 }
            r1 = com.bumptech.glide.a.a.this;	 Catch:{ all -> 0x0028 }
            r1 = r1.e();	 Catch:{ all -> 0x0028 }
            if (r1 == 0) goto L_0x0026;
        L_0x001b:
            r1 = com.bumptech.glide.a.a.this;	 Catch:{ all -> 0x0028 }
            r1.d();	 Catch:{ all -> 0x0028 }
            r1 = com.bumptech.glide.a.a.this;	 Catch:{ all -> 0x0028 }
            r3 = 0;
            r1.l = r3;	 Catch:{ all -> 0x0028 }
        L_0x0026:
            monitor-exit(r0);	 Catch:{ all -> 0x0028 }
            return r2;
        L_0x0028:
            r1 = move-exception;
            monitor-exit(r0);	 Catch:{ all -> 0x0028 }
            throw r1;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.a.a$AnonymousClass1.call():java.lang.Void");
        }
    };

    private static final class a implements ThreadFactory {
        private a() {
        }

        /* synthetic */ a(AnonymousClass1 anonymousClass1) {
            this();
        }

        public synchronized Thread newThread(Runnable runnable) {
            Thread thread;
            thread = new Thread(runnable, "glide-disk-lru-cache-thread");
            thread.setPriority(1);
            return thread;
        }
    }

    public final class b {
        private final c b;
        private final boolean[] c;
        private boolean d;

        /* synthetic */ b(a aVar, c cVar, AnonymousClass1 anonymousClass1) {
            this(cVar);
        }

        private b(c cVar) {
            this.b = cVar;
            this.c = cVar.f ? null : new boolean[a.this.h];
        }

        public File a(int i) throws IOException {
            File b;
            synchronized (a.this) {
                if (this.b.g != this) {
                    throw new IllegalStateException();
                }
                if (!this.b.f) {
                    this.c[i] = true;
                }
                b = this.b.b(i);
                if (!a.this.b.exists()) {
                    a.this.b.mkdirs();
                }
            }
            return b;
        }

        public void a() throws IOException {
            a.this.a(this, true);
            this.d = true;
        }

        public void b() throws IOException {
            a.this.a(this, false);
        }

        public void c() {
            if (!this.d) {
                try {
                    b();
                } catch (IOException unused) {
                }
            }
        }
    }

    private final class c {
        File[] a;
        File[] b;
        private final String d;
        private final long[] e;
        private boolean f;
        private b g;
        private long h;

        /* synthetic */ c(a aVar, String str, AnonymousClass1 anonymousClass1) {
            this(str);
        }

        private c(String str) {
            this.d = str;
            this.e = new long[a.this.h];
            this.a = new File[a.this.h];
            this.b = new File[a.this.h];
            StringBuilder stringBuilder = new StringBuilder(str);
            stringBuilder.append('.');
            int length = stringBuilder.length();
            for (int i = 0; i < a.this.h; i++) {
                stringBuilder.append(i);
                this.a[i] = new File(a.this.b, stringBuilder.toString());
                stringBuilder.append(".tmp");
                this.b[i] = new File(a.this.b, stringBuilder.toString());
                stringBuilder.setLength(length);
            }
        }

        public String a() throws IOException {
            StringBuilder stringBuilder = new StringBuilder();
            for (long j : this.e) {
                stringBuilder.append(' ');
                stringBuilder.append(j);
            }
            return stringBuilder.toString();
        }

        private void a(String[] strArr) throws IOException {
            if (strArr.length != a.this.h) {
                throw b(strArr);
            }
            int i = 0;
            while (i < strArr.length) {
                try {
                    this.e[i] = Long.parseLong(strArr[i]);
                    i++;
                } catch (NumberFormatException unused) {
                    throw b(strArr);
                }
            }
        }

        private IOException b(String[] strArr) throws IOException {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("unexpected journal line: ");
            stringBuilder.append(Arrays.toString(strArr));
            throw new IOException(stringBuilder.toString());
        }

        public File a(int i) {
            return this.a[i];
        }

        public File b(int i) {
            return this.b[i];
        }
    }

    public final class d {
        private final String b;
        private final long c;
        private final long[] d;
        private final File[] e;

        /* synthetic */ d(a aVar, String str, long j, File[] fileArr, long[] jArr, AnonymousClass1 anonymousClass1) {
            this(str, j, fileArr, jArr);
        }

        private d(String str, long j, File[] fileArr, long[] jArr) {
            this.b = str;
            this.c = j;
            this.e = fileArr;
            this.d = jArr;
        }

        public File a(int i) {
            return this.e[i];
        }
    }

    private a(File file, int i, int i2, long j) {
        File file2 = file;
        this.b = file2;
        this.f = i;
        this.c = new File(file2, "journal");
        this.d = new File(file2, "journal.tmp");
        this.e = new File(file2, "journal.bkp");
        this.h = i2;
        this.g = j;
    }

    public static a a(File file, int i, int i2, long j) throws IOException {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i2 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        } else {
            File file2 = new File(file, "journal.bkp");
            if (file2.exists()) {
                File file3 = new File(file, "journal");
                if (file3.exists()) {
                    file2.delete();
                } else {
                    a(file2, file3, false);
                }
            }
            a aVar = new a(file, i, i2, j);
            if (aVar.c.exists()) {
                try {
                    aVar.b();
                    aVar.c();
                    return aVar;
                } catch (IOException e) {
                    PrintStream printStream = System.out;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("DiskLruCache ");
                    stringBuilder.append(file);
                    stringBuilder.append(" is corrupt: ");
                    stringBuilder.append(e.getMessage());
                    stringBuilder.append(", removing");
                    printStream.println(stringBuilder.toString());
                    aVar.a();
                }
            }
            file.mkdirs();
            aVar = new a(file, i, i2, j);
            aVar.d();
            return aVar;
        }
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:23:0x008b=Splitter:B:23:0x008b, B:16:0x005e=Splitter:B:16:0x005e} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x005e */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:16|17|(1:19)(1:20)|21|22) */
    /* JADX WARNING: Missing block: B:17:?, code skipped:
            r8.l = r1 - r8.k.size();
     */
    /* JADX WARNING: Missing block: B:18:0x006b, code skipped:
            if (r0.b() != false) goto L_0x006d;
     */
    /* JADX WARNING: Missing block: B:19:0x006d, code skipped:
            d();
     */
    /* JADX WARNING: Missing block: B:20:0x0071, code skipped:
            r8.j = new java.io.BufferedWriter(new java.io.OutputStreamWriter(new java.io.FileOutputStream(r8.c, true), com.bumptech.glide.a.c.a));
     */
    /* JADX WARNING: Missing block: B:21:0x0087, code skipped:
            com.bumptech.glide.a.c.a(r0);
     */
    /* JADX WARNING: Missing block: B:22:0x008a, code skipped:
            return;
     */
    private void b() throws java.io.IOException {
        /*
        r8 = this;
        r0 = new com.bumptech.glide.a.b;
        r1 = new java.io.FileInputStream;
        r2 = r8.c;
        r1.<init>(r2);
        r2 = com.bumptech.glide.a.c.a;
        r0.<init>(r1, r2);
        r1 = r0.a();	 Catch:{ all -> 0x00bf }
        r2 = r0.a();	 Catch:{ all -> 0x00bf }
        r3 = r0.a();	 Catch:{ all -> 0x00bf }
        r4 = r0.a();	 Catch:{ all -> 0x00bf }
        r5 = r0.a();	 Catch:{ all -> 0x00bf }
        r6 = "libcore.io.DiskLruCache";
        r6 = r6.equals(r1);	 Catch:{ all -> 0x00bf }
        if (r6 == 0) goto L_0x008b;
    L_0x002a:
        r6 = "1";
        r6 = r6.equals(r2);	 Catch:{ all -> 0x00bf }
        if (r6 == 0) goto L_0x008b;
    L_0x0032:
        r6 = r8.f;	 Catch:{ all -> 0x00bf }
        r6 = java.lang.Integer.toString(r6);	 Catch:{ all -> 0x00bf }
        r3 = r6.equals(r3);	 Catch:{ all -> 0x00bf }
        if (r3 == 0) goto L_0x008b;
    L_0x003e:
        r3 = r8.h;	 Catch:{ all -> 0x00bf }
        r3 = java.lang.Integer.toString(r3);	 Catch:{ all -> 0x00bf }
        r3 = r3.equals(r4);	 Catch:{ all -> 0x00bf }
        if (r3 == 0) goto L_0x008b;
    L_0x004a:
        r3 = "";
        r3 = r3.equals(r5);	 Catch:{ all -> 0x00bf }
        if (r3 != 0) goto L_0x0053;
    L_0x0052:
        goto L_0x008b;
    L_0x0053:
        r1 = 0;
    L_0x0054:
        r2 = r0.a();	 Catch:{ EOFException -> 0x005e }
        r8.d(r2);	 Catch:{ EOFException -> 0x005e }
        r1 = r1 + 1;
        goto L_0x0054;
    L_0x005e:
        r2 = r8.k;	 Catch:{ all -> 0x00bf }
        r2 = r2.size();	 Catch:{ all -> 0x00bf }
        r1 = r1 - r2;
        r8.l = r1;	 Catch:{ all -> 0x00bf }
        r1 = r0.b();	 Catch:{ all -> 0x00bf }
        if (r1 == 0) goto L_0x0071;
    L_0x006d:
        r8.d();	 Catch:{ all -> 0x00bf }
        goto L_0x0087;
    L_0x0071:
        r1 = new java.io.BufferedWriter;	 Catch:{ all -> 0x00bf }
        r2 = new java.io.OutputStreamWriter;	 Catch:{ all -> 0x00bf }
        r3 = new java.io.FileOutputStream;	 Catch:{ all -> 0x00bf }
        r4 = r8.c;	 Catch:{ all -> 0x00bf }
        r5 = 1;
        r3.<init>(r4, r5);	 Catch:{ all -> 0x00bf }
        r4 = com.bumptech.glide.a.c.a;	 Catch:{ all -> 0x00bf }
        r2.<init>(r3, r4);	 Catch:{ all -> 0x00bf }
        r1.<init>(r2);	 Catch:{ all -> 0x00bf }
        r8.j = r1;	 Catch:{ all -> 0x00bf }
    L_0x0087:
        com.bumptech.glide.a.c.a(r0);
        return;
    L_0x008b:
        r3 = new java.io.IOException;	 Catch:{ all -> 0x00bf }
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00bf }
        r6.<init>();	 Catch:{ all -> 0x00bf }
        r7 = "unexpected journal header: [";
        r6.append(r7);	 Catch:{ all -> 0x00bf }
        r6.append(r1);	 Catch:{ all -> 0x00bf }
        r1 = ", ";
        r6.append(r1);	 Catch:{ all -> 0x00bf }
        r6.append(r2);	 Catch:{ all -> 0x00bf }
        r1 = ", ";
        r6.append(r1);	 Catch:{ all -> 0x00bf }
        r6.append(r4);	 Catch:{ all -> 0x00bf }
        r1 = ", ";
        r6.append(r1);	 Catch:{ all -> 0x00bf }
        r6.append(r5);	 Catch:{ all -> 0x00bf }
        r1 = "]";
        r6.append(r1);	 Catch:{ all -> 0x00bf }
        r1 = r6.toString();	 Catch:{ all -> 0x00bf }
        r3.<init>(r1);	 Catch:{ all -> 0x00bf }
        throw r3;	 Catch:{ all -> 0x00bf }
    L_0x00bf:
        r1 = move-exception;
        com.bumptech.glide.a.c.a(r0);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.a.a.b():void");
    }

    private void d(String str) throws IOException {
        int indexOf = str.indexOf(32);
        StringBuilder stringBuilder;
        if (indexOf == -1) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("unexpected journal line: ");
            stringBuilder.append(str);
            throw new IOException(stringBuilder.toString());
        }
        Object substring;
        int i = indexOf + 1;
        int indexOf2 = str.indexOf(32, i);
        if (indexOf2 == -1) {
            substring = str.substring(i);
            if (indexOf == "REMOVE".length() && str.startsWith("REMOVE")) {
                this.k.remove(substring);
                return;
            }
        }
        substring = str.substring(i, indexOf2);
        c cVar = (c) this.k.get(substring);
        if (cVar == null) {
            cVar = new c(this, substring, null);
            this.k.put(substring, cVar);
        }
        if (indexOf2 != -1 && indexOf == "CLEAN".length() && str.startsWith("CLEAN")) {
            String[] split = str.substring(indexOf2 + 1).split(" ");
            cVar.f = true;
            cVar.g = null;
            cVar.a(split);
        } else if (indexOf2 == -1 && indexOf == "DIRTY".length() && str.startsWith("DIRTY")) {
            cVar.g = new b(this, cVar, null);
        } else if (!(indexOf2 == -1 && indexOf == "READ".length() && str.startsWith("READ"))) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("unexpected journal line: ");
            stringBuilder.append(str);
            throw new IOException(stringBuilder.toString());
        }
    }

    private void c() throws IOException {
        a(this.d);
        Iterator it = this.k.values().iterator();
        while (it.hasNext()) {
            c cVar = (c) it.next();
            int i = 0;
            if (cVar.g == null) {
                while (i < this.h) {
                    this.i += cVar.e[i];
                    i++;
                }
            } else {
                cVar.g = null;
                while (i < this.h) {
                    a(cVar.a(i));
                    a(cVar.b(i));
                    i++;
                }
                it.remove();
            }
        }
    }

    private synchronized void d() throws IOException {
        if (this.j != null) {
            this.j.close();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.d), c.a));
        try {
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write("\n");
            bufferedWriter.write("1");
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.h));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (c cVar : this.k.values()) {
                StringBuilder stringBuilder;
                if (cVar.g != null) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("DIRTY ");
                    stringBuilder.append(cVar.d);
                    stringBuilder.append(10);
                    bufferedWriter.write(stringBuilder.toString());
                } else {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("CLEAN ");
                    stringBuilder.append(cVar.d);
                    stringBuilder.append(cVar.a());
                    stringBuilder.append(10);
                    bufferedWriter.write(stringBuilder.toString());
                }
            }
            if (this.c.exists()) {
                a(this.c, this.e, true);
            }
            a(this.d, this.c, false);
            this.e.delete();
            this.j = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.c, true), c.a));
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

    public synchronized d a(String str) throws IOException {
        f();
        c cVar = (c) this.k.get(str);
        if (cVar == null) {
            return null;
        }
        if (!cVar.f) {
            return null;
        }
        for (File exists : cVar.a) {
            if (!exists.exists()) {
                return null;
            }
        }
        this.l++;
        this.j.append("READ");
        this.j.append(' ');
        this.j.append(str);
        this.j.append(10);
        if (e()) {
            this.a.submit(this.n);
        }
        return new d(this, str, cVar.h, cVar.a, cVar.e, null);
    }

    public b b(String str) throws IOException {
        return a(str, -1);
    }

    /* JADX WARNING: Missing block: B:9:0x001e, code skipped:
            return null;
     */
    private synchronized com.bumptech.glide.a.a.b a(java.lang.String r6, long r7) throws java.io.IOException {
        /*
        r5 = this;
        monitor-enter(r5);
        r5.f();	 Catch:{ all -> 0x005d }
        r0 = r5.k;	 Catch:{ all -> 0x005d }
        r0 = r0.get(r6);	 Catch:{ all -> 0x005d }
        r0 = (com.bumptech.glide.a.a.c) r0;	 Catch:{ all -> 0x005d }
        r1 = -1;
        r3 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1));
        r1 = 0;
        if (r3 == 0) goto L_0x001f;
    L_0x0013:
        if (r0 == 0) goto L_0x001d;
    L_0x0015:
        r2 = r0.h;	 Catch:{ all -> 0x005d }
        r4 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1));
        if (r4 == 0) goto L_0x001f;
    L_0x001d:
        monitor-exit(r5);
        return r1;
    L_0x001f:
        if (r0 != 0) goto L_0x002c;
    L_0x0021:
        r0 = new com.bumptech.glide.a.a$c;	 Catch:{ all -> 0x005d }
        r0.<init>(r5, r6, r1);	 Catch:{ all -> 0x005d }
        r7 = r5.k;	 Catch:{ all -> 0x005d }
        r7.put(r6, r0);	 Catch:{ all -> 0x005d }
        goto L_0x0034;
    L_0x002c:
        r7 = r0.g;	 Catch:{ all -> 0x005d }
        if (r7 == 0) goto L_0x0034;
    L_0x0032:
        monitor-exit(r5);
        return r1;
    L_0x0034:
        r7 = new com.bumptech.glide.a.a$b;	 Catch:{ all -> 0x005d }
        r7.<init>(r5, r0, r1);	 Catch:{ all -> 0x005d }
        r0.g = r7;	 Catch:{ all -> 0x005d }
        r8 = r5.j;	 Catch:{ all -> 0x005d }
        r0 = "DIRTY";
        r8.append(r0);	 Catch:{ all -> 0x005d }
        r8 = r5.j;	 Catch:{ all -> 0x005d }
        r0 = 32;
        r8.append(r0);	 Catch:{ all -> 0x005d }
        r8 = r5.j;	 Catch:{ all -> 0x005d }
        r8.append(r6);	 Catch:{ all -> 0x005d }
        r6 = r5.j;	 Catch:{ all -> 0x005d }
        r8 = 10;
        r6.append(r8);	 Catch:{ all -> 0x005d }
        r6 = r5.j;	 Catch:{ all -> 0x005d }
        r6.flush();	 Catch:{ all -> 0x005d }
        monitor-exit(r5);
        return r7;
    L_0x005d:
        r6 = move-exception;
        monitor-exit(r5);
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.a.a.a(java.lang.String, long):com.bumptech.glide.a.a$b");
    }

    /* JADX WARNING: Missing block: B:45:0x0110, code skipped:
            return;
     */
    private synchronized void a(com.bumptech.glide.a.a.b r12, boolean r13) throws java.io.IOException {
        /*
        r11 = this;
        monitor-enter(r11);
        r0 = r12.b;	 Catch:{ all -> 0x0111 }
        r1 = r0.g;	 Catch:{ all -> 0x0111 }
        if (r1 == r12) goto L_0x0011;
    L_0x000b:
        r12 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0111 }
        r12.<init>();	 Catch:{ all -> 0x0111 }
        throw r12;	 Catch:{ all -> 0x0111 }
    L_0x0011:
        r1 = 0;
        if (r13 == 0) goto L_0x0053;
    L_0x0014:
        r2 = r0.f;	 Catch:{ all -> 0x0111 }
        if (r2 != 0) goto L_0x0053;
    L_0x001a:
        r2 = r1;
    L_0x001b:
        r3 = r11.h;	 Catch:{ all -> 0x0111 }
        if (r2 >= r3) goto L_0x0053;
    L_0x001f:
        r3 = r12.c;	 Catch:{ all -> 0x0111 }
        r3 = r3[r2];	 Catch:{ all -> 0x0111 }
        if (r3 != 0) goto L_0x0041;
    L_0x0027:
        r12.b();	 Catch:{ all -> 0x0111 }
        r12 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0111 }
        r13 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0111 }
        r13.<init>();	 Catch:{ all -> 0x0111 }
        r0 = "Newly created entry didn't create value for index ";
        r13.append(r0);	 Catch:{ all -> 0x0111 }
        r13.append(r2);	 Catch:{ all -> 0x0111 }
        r13 = r13.toString();	 Catch:{ all -> 0x0111 }
        r12.<init>(r13);	 Catch:{ all -> 0x0111 }
        throw r12;	 Catch:{ all -> 0x0111 }
    L_0x0041:
        r3 = r0.b(r2);	 Catch:{ all -> 0x0111 }
        r3 = r3.exists();	 Catch:{ all -> 0x0111 }
        if (r3 != 0) goto L_0x0050;
    L_0x004b:
        r12.b();	 Catch:{ all -> 0x0111 }
        monitor-exit(r11);
        return;
    L_0x0050:
        r2 = r2 + 1;
        goto L_0x001b;
    L_0x0053:
        r12 = r11.h;	 Catch:{ all -> 0x0111 }
        if (r1 >= r12) goto L_0x0089;
    L_0x0057:
        r12 = r0.b(r1);	 Catch:{ all -> 0x0111 }
        if (r13 == 0) goto L_0x0083;
    L_0x005d:
        r2 = r12.exists();	 Catch:{ all -> 0x0111 }
        if (r2 == 0) goto L_0x0086;
    L_0x0063:
        r2 = r0.a(r1);	 Catch:{ all -> 0x0111 }
        r12.renameTo(r2);	 Catch:{ all -> 0x0111 }
        r12 = r0.e;	 Catch:{ all -> 0x0111 }
        r3 = r12[r1];	 Catch:{ all -> 0x0111 }
        r5 = r2.length();	 Catch:{ all -> 0x0111 }
        r12 = r0.e;	 Catch:{ all -> 0x0111 }
        r12[r1] = r5;	 Catch:{ all -> 0x0111 }
        r7 = r11.i;	 Catch:{ all -> 0x0111 }
        r9 = r7 - r3;
        r2 = r9 + r5;
        r11.i = r2;	 Catch:{ all -> 0x0111 }
        goto L_0x0086;
    L_0x0083:
        a(r12);	 Catch:{ all -> 0x0111 }
    L_0x0086:
        r1 = r1 + 1;
        goto L_0x0053;
    L_0x0089:
        r12 = r11.l;	 Catch:{ all -> 0x0111 }
        r1 = 1;
        r12 = r12 + r1;
        r11.l = r12;	 Catch:{ all -> 0x0111 }
        r12 = 0;
        r0.g = r12;	 Catch:{ all -> 0x0111 }
        r12 = r0.f;	 Catch:{ all -> 0x0111 }
        r12 = r12 | r13;
        r2 = 10;
        r3 = 32;
        if (r12 == 0) goto L_0x00d2;
    L_0x009e:
        r0.f = r1;	 Catch:{ all -> 0x0111 }
        r12 = r11.j;	 Catch:{ all -> 0x0111 }
        r1 = "CLEAN";
        r12.append(r1);	 Catch:{ all -> 0x0111 }
        r12 = r11.j;	 Catch:{ all -> 0x0111 }
        r12.append(r3);	 Catch:{ all -> 0x0111 }
        r12 = r11.j;	 Catch:{ all -> 0x0111 }
        r1 = r0.d;	 Catch:{ all -> 0x0111 }
        r12.append(r1);	 Catch:{ all -> 0x0111 }
        r12 = r11.j;	 Catch:{ all -> 0x0111 }
        r1 = r0.a();	 Catch:{ all -> 0x0111 }
        r12.append(r1);	 Catch:{ all -> 0x0111 }
        r12 = r11.j;	 Catch:{ all -> 0x0111 }
        r12.append(r2);	 Catch:{ all -> 0x0111 }
        if (r13 == 0) goto L_0x00f5;
    L_0x00c6:
        r12 = r11.m;	 Catch:{ all -> 0x0111 }
        r1 = 1;
        r3 = r12 + r1;
        r11.m = r3;	 Catch:{ all -> 0x0111 }
        r0.h = r12;	 Catch:{ all -> 0x0111 }
        goto L_0x00f5;
    L_0x00d2:
        r12 = r11.k;	 Catch:{ all -> 0x0111 }
        r13 = r0.d;	 Catch:{ all -> 0x0111 }
        r12.remove(r13);	 Catch:{ all -> 0x0111 }
        r12 = r11.j;	 Catch:{ all -> 0x0111 }
        r13 = "REMOVE";
        r12.append(r13);	 Catch:{ all -> 0x0111 }
        r12 = r11.j;	 Catch:{ all -> 0x0111 }
        r12.append(r3);	 Catch:{ all -> 0x0111 }
        r12 = r11.j;	 Catch:{ all -> 0x0111 }
        r13 = r0.d;	 Catch:{ all -> 0x0111 }
        r12.append(r13);	 Catch:{ all -> 0x0111 }
        r12 = r11.j;	 Catch:{ all -> 0x0111 }
        r12.append(r2);	 Catch:{ all -> 0x0111 }
    L_0x00f5:
        r12 = r11.j;	 Catch:{ all -> 0x0111 }
        r12.flush();	 Catch:{ all -> 0x0111 }
        r12 = r11.i;	 Catch:{ all -> 0x0111 }
        r0 = r11.g;	 Catch:{ all -> 0x0111 }
        r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1));
        if (r2 > 0) goto L_0x0108;
    L_0x0102:
        r12 = r11.e();	 Catch:{ all -> 0x0111 }
        if (r12 == 0) goto L_0x010f;
    L_0x0108:
        r12 = r11.a;	 Catch:{ all -> 0x0111 }
        r13 = r11.n;	 Catch:{ all -> 0x0111 }
        r12.submit(r13);	 Catch:{ all -> 0x0111 }
    L_0x010f:
        monitor-exit(r11);
        return;
    L_0x0111:
        r12 = move-exception;
        monitor-exit(r11);
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.a.a.a(com.bumptech.glide.a.a$b, boolean):void");
    }

    private boolean e() {
        return this.l >= 2000 && this.l >= this.k.size();
    }

    /* JADX WARNING: Missing block: B:20:0x008b, code skipped:
            return true;
     */
    /* JADX WARNING: Missing block: B:22:0x008d, code skipped:
            return false;
     */
    public synchronized boolean c(java.lang.String r10) throws java.io.IOException {
        /*
        r9 = this;
        monitor-enter(r9);
        r9.f();	 Catch:{ all -> 0x008e }
        r0 = r9.k;	 Catch:{ all -> 0x008e }
        r0 = r0.get(r10);	 Catch:{ all -> 0x008e }
        r0 = (com.bumptech.glide.a.a.c) r0;	 Catch:{ all -> 0x008e }
        r1 = 0;
        if (r0 == 0) goto L_0x008c;
    L_0x000f:
        r2 = r0.g;	 Catch:{ all -> 0x008e }
        if (r2 == 0) goto L_0x0016;
    L_0x0015:
        goto L_0x008c;
    L_0x0016:
        r2 = r9.h;	 Catch:{ all -> 0x008e }
        if (r1 >= r2) goto L_0x0058;
    L_0x001a:
        r2 = r0.a(r1);	 Catch:{ all -> 0x008e }
        r3 = r2.exists();	 Catch:{ all -> 0x008e }
        if (r3 == 0) goto L_0x0041;
    L_0x0024:
        r3 = r2.delete();	 Catch:{ all -> 0x008e }
        if (r3 != 0) goto L_0x0041;
    L_0x002a:
        r10 = new java.io.IOException;	 Catch:{ all -> 0x008e }
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x008e }
        r0.<init>();	 Catch:{ all -> 0x008e }
        r1 = "failed to delete ";
        r0.append(r1);	 Catch:{ all -> 0x008e }
        r0.append(r2);	 Catch:{ all -> 0x008e }
        r0 = r0.toString();	 Catch:{ all -> 0x008e }
        r10.<init>(r0);	 Catch:{ all -> 0x008e }
        throw r10;	 Catch:{ all -> 0x008e }
    L_0x0041:
        r2 = r9.i;	 Catch:{ all -> 0x008e }
        r4 = r0.e;	 Catch:{ all -> 0x008e }
        r5 = r4[r1];	 Catch:{ all -> 0x008e }
        r7 = r2 - r5;
        r9.i = r7;	 Catch:{ all -> 0x008e }
        r2 = r0.e;	 Catch:{ all -> 0x008e }
        r3 = 0;
        r2[r1] = r3;	 Catch:{ all -> 0x008e }
        r1 = r1 + 1;
        goto L_0x0016;
    L_0x0058:
        r0 = r9.l;	 Catch:{ all -> 0x008e }
        r1 = 1;
        r0 = r0 + r1;
        r9.l = r0;	 Catch:{ all -> 0x008e }
        r0 = r9.j;	 Catch:{ all -> 0x008e }
        r2 = "REMOVE";
        r0.append(r2);	 Catch:{ all -> 0x008e }
        r0 = r9.j;	 Catch:{ all -> 0x008e }
        r2 = 32;
        r0.append(r2);	 Catch:{ all -> 0x008e }
        r0 = r9.j;	 Catch:{ all -> 0x008e }
        r0.append(r10);	 Catch:{ all -> 0x008e }
        r0 = r9.j;	 Catch:{ all -> 0x008e }
        r2 = 10;
        r0.append(r2);	 Catch:{ all -> 0x008e }
        r0 = r9.k;	 Catch:{ all -> 0x008e }
        r0.remove(r10);	 Catch:{ all -> 0x008e }
        r10 = r9.e();	 Catch:{ all -> 0x008e }
        if (r10 == 0) goto L_0x008a;
    L_0x0083:
        r10 = r9.a;	 Catch:{ all -> 0x008e }
        r0 = r9.n;	 Catch:{ all -> 0x008e }
        r10.submit(r0);	 Catch:{ all -> 0x008e }
    L_0x008a:
        monitor-exit(r9);
        return r1;
    L_0x008c:
        monitor-exit(r9);
        return r1;
    L_0x008e:
        r10 = move-exception;
        monitor-exit(r9);
        throw r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.a.a.c(java.lang.String):boolean");
    }

    private void f() {
        if (this.j == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void close() throws IOException {
        if (this.j != null) {
            Iterator it = new ArrayList(this.k.values()).iterator();
            while (it.hasNext()) {
                c cVar = (c) it.next();
                if (cVar.g != null) {
                    cVar.g.b();
                }
            }
            g();
            this.j.close();
            this.j = null;
        }
    }

    private void g() throws IOException {
        while (this.i > this.g) {
            c((String) ((Entry) this.k.entrySet().iterator().next()).getKey());
        }
    }

    public void a() throws IOException {
        close();
        c.a(this.b);
    }
}
