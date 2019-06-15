package com.til.colombia.android.commons.a;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.a.h;
import com.til.colombia.android.utils.DeviceUtils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class a {
    public static String a = null;
    static final String b = "colombia-disk-cache";
    private static final int c = 1;
    private static final int d = 1;
    private static final int e = 0;
    private static b f;
    private static File g;

    public interface a {
        void a(byte[] bArr);
    }

    private static class b extends AsyncTask<Void, Void, byte[]> {
        private final a a;
        private final String b;

        /* Access modifiers changed, original: protected|final|synthetic */
        public final /* synthetic */ void onPostExecute(Object obj) {
            byte[] bArr = (byte[]) obj;
            if (isCancelled()) {
                onCancelled();
                return;
            }
            if (this.a != null) {
                this.a.a(bArr);
            }
        }

        b(String str, a aVar) {
            this.a = aVar;
            this.b = str;
        }

        private byte[] a() {
            return a.b(this.b);
        }

        private void a(byte[] bArr) {
            if (isCancelled()) {
                onCancelled();
                return;
            }
            if (this.a != null) {
                this.a.a(bArr);
            }
        }

        /* Access modifiers changed, original: protected|final */
        public final void onCancelled() {
            if (this.a != null) {
                this.a.a(null);
            }
        }

        /* Access modifiers changed, original: protected|final|synthetic */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            return a.b(this.b);
        }
    }

    private static class c extends AsyncTask<Void, Void, Void> {
        private final String a;
        private final byte[] b;

        c(String str, byte[] bArr) {
            this.a = str;
            this.b = bArr;
        }

        private Void a() {
            a.a(this.a, this.b);
            return null;
        }

        /* Access modifiers changed, original: protected|final|synthetic */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            a.a(this.a, this.b);
            return null;
        }
    }

    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        stringBuilder.append(File.separator);
        stringBuilder.append(".ColombiaMedia");
        a = stringBuilder.toString();
    }

    private static boolean b(Context context) {
        if (context == null) {
            return false;
        }
        File file = new File(a);
        g = file;
        if (!file.exists()) {
            g.mkdir();
        }
        if (f == null) {
            File cacheDir = context.getCacheDir();
            if (cacheDir == null) {
                cacheDir = null;
            } else {
                String path = cacheDir.getPath();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(path);
                stringBuilder.append(File.separator);
                stringBuilder.append(b);
                cacheDir = new File(stringBuilder.toString());
            }
            if (cacheDir == null) {
                return false;
            }
            long a = DeviceUtils.a(cacheDir, 31457280);
            String canonicalName = a.class.getCanonicalName();
            StringBuilder stringBuilder2 = new StringBuilder("cache size: ");
            stringBuilder2.append(a);
            Log.a(canonicalName, stringBuilder2.toString());
            try {
                f = b.a(cacheDir, 1, 1, a);
            } catch (IOException e) {
                Log.a("CacheService", "Unable to create DiskLruCache", e);
                return false;
            }
        }
        return true;
    }

    public static File a() {
        return g;
    }

    public static void a(Context context) {
        b(context);
    }

    private static String c(String str) {
        return h.c(str);
    }

    private static File c(Context context) {
        File cacheDir = context.getCacheDir();
        if (cacheDir == null) {
            return null;
        }
        String path = cacheDir.getPath();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(path);
        stringBuilder.append(File.separator);
        stringBuilder.append(b);
        return new File(stringBuilder.toString());
    }

    public static boolean a(String str) {
        if (f == null) {
            return false;
        }
        try {
            if (f.a(h.c(str)) != null) {
                return true;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    private static String d(String str) {
        if (f == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(f.h);
        stringBuilder.append(File.separator);
        stringBuilder.append(h.c(str));
        stringBuilder.append(".0");
        return stringBuilder.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0053  */
    /* JADX WARNING: Missing block: B:20:0x0038, code skipped:
            if (r4 != null) goto L_0x003a;
     */
    /* JADX WARNING: Missing block: B:21:0x003a, code skipped:
            r4.close();
     */
    /* JADX WARNING: Missing block: B:29:0x004c, code skipped:
            if (r4 != null) goto L_0x003a;
     */
    /* JADX WARNING: Missing block: B:30:0x004f, code skipped:
            return r1;
     */
    public static byte[] b(java.lang.String r4) {
        /*
        r0 = f;
        r1 = 0;
        if (r0 != 0) goto L_0x0006;
    L_0x0005:
        return r1;
    L_0x0006:
        r0 = f;	 Catch:{ Throwable -> 0x0043, all -> 0x0040 }
        r4 = com.til.colombia.android.internal.a.h.c(r4);	 Catch:{ Throwable -> 0x0043, all -> 0x0040 }
        r4 = r0.a(r4);	 Catch:{ Throwable -> 0x0043, all -> 0x0040 }
        if (r4 != 0) goto L_0x0018;
    L_0x0012:
        if (r4 == 0) goto L_0x0017;
    L_0x0014:
        r4.close();
    L_0x0017:
        return r1;
    L_0x0018:
        r0 = r4.a;	 Catch:{ Throwable -> 0x003e }
        r2 = 0;
        r0 = r0[r2];	 Catch:{ Throwable -> 0x003e }
        if (r0 == 0) goto L_0x0038;
    L_0x001f:
        r3 = r4.b;	 Catch:{ Throwable -> 0x003e }
        r2 = r3[r2];	 Catch:{ Throwable -> 0x003e }
        r2 = (int) r2;	 Catch:{ Throwable -> 0x003e }
        r2 = new byte[r2];	 Catch:{ Throwable -> 0x003e }
        r3 = new java.io.BufferedInputStream;	 Catch:{ Throwable -> 0x003e }
        r3.<init>(r0);	 Catch:{ Throwable -> 0x003e }
        com.til.colombia.android.commons.a.a(r3, r2);	 Catch:{ all -> 0x0033 }
        com.til.colombia.android.commons.a.a(r3);	 Catch:{ Throwable -> 0x003e }
        r1 = r2;
        goto L_0x0038;
    L_0x0033:
        r0 = move-exception;
        com.til.colombia.android.commons.a.a(r3);	 Catch:{ Throwable -> 0x003e }
        throw r0;	 Catch:{ Throwable -> 0x003e }
    L_0x0038:
        if (r4 == 0) goto L_0x004f;
    L_0x003a:
        r4.close();
        goto L_0x004f;
    L_0x003e:
        r0 = move-exception;
        goto L_0x0045;
    L_0x0040:
        r0 = move-exception;
        r4 = r1;
        goto L_0x0051;
    L_0x0043:
        r0 = move-exception;
        r4 = r1;
    L_0x0045:
        r2 = "CacheService";
        r3 = "Unable to get from DiskLruCache";
        com.til.colombia.android.internal.Log.a(r2, r3, r0);	 Catch:{ all -> 0x0050 }
        if (r4 == 0) goto L_0x004f;
    L_0x004e:
        goto L_0x003a;
    L_0x004f:
        return r1;
    L_0x0050:
        r0 = move-exception;
    L_0x0051:
        if (r4 == 0) goto L_0x0056;
    L_0x0053:
        r4.close();
    L_0x0056:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.commons.a.a.b(java.lang.String):byte[]");
    }

    public static void a(String str, a aVar) {
        new b(str, aVar).execute(new Void[0]);
    }

    public static boolean a(String str, byte[] bArr) {
        return a(str, new ByteArrayInputStream(bArr));
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0056 A:{SYNTHETIC, Splitter:B:20:0x0056} */
    public static boolean a(java.lang.String r5, java.io.InputStream r6) {
        /*
        r0 = f;
        r1 = 0;
        if (r0 != 0) goto L_0x0006;
    L_0x0005:
        return r1;
    L_0x0006:
        r0 = 0;
        r2 = f;	 Catch:{ Exception -> 0x004b }
        r5 = com.til.colombia.android.internal.a.h.c(r5);	 Catch:{ Exception -> 0x004b }
        r3 = -1;
        r5 = r2.a(r5, r3);	 Catch:{ Exception -> 0x004b }
        if (r5 != 0) goto L_0x0016;
    L_0x0015:
        return r1;
    L_0x0016:
        r0 = new java.io.BufferedOutputStream;	 Catch:{ Exception -> 0x0049 }
        r2 = r5.a(r1);	 Catch:{ Exception -> 0x0049 }
        r0.<init>(r2);	 Catch:{ Exception -> 0x0049 }
        com.til.colombia.android.commons.a.a(r6, r0);	 Catch:{ Exception -> 0x0049 }
        r0.flush();	 Catch:{ Exception -> 0x0049 }
        r0.close();	 Catch:{ Exception -> 0x0049 }
        r6 = f;	 Catch:{ Exception -> 0x0049 }
        r6.b();	 Catch:{ Exception -> 0x0049 }
        r6 = r5.c;	 Catch:{ Exception -> 0x0049 }
        r0 = 1;
        if (r6 == 0) goto L_0x0041;
    L_0x0032:
        r6 = com.til.colombia.android.commons.a.b.this;	 Catch:{ Exception -> 0x0049 }
        r6.a(r5, r1);	 Catch:{ Exception -> 0x0049 }
        r6 = com.til.colombia.android.commons.a.b.this;	 Catch:{ Exception -> 0x0049 }
        r2 = r5.a;	 Catch:{ Exception -> 0x0049 }
        r2 = r2.a;	 Catch:{ Exception -> 0x0049 }
        r6.c(r2);	 Catch:{ Exception -> 0x0049 }
        goto L_0x0046;
    L_0x0041:
        r6 = com.til.colombia.android.commons.a.b.this;	 Catch:{ Exception -> 0x0049 }
        r6.a(r5, r0);	 Catch:{ Exception -> 0x0049 }
    L_0x0046:
        r5.d = r0;	 Catch:{ Exception -> 0x0049 }
        return r0;
    L_0x0049:
        r6 = move-exception;
        goto L_0x004d;
    L_0x004b:
        r6 = move-exception;
        r5 = r0;
    L_0x004d:
        r0 = "CacheService";
        r2 = "Unable to put to DiskLruCache";
        com.til.colombia.android.internal.Log.a(r0, r2, r6);
        if (r5 == 0) goto L_0x0059;
    L_0x0056:
        r5.b();	 Catch:{ IOException -> 0x0059 }
    L_0x0059:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.commons.a.a.a(java.lang.String, java.io.InputStream):boolean");
    }

    private static void b(String str, byte[] bArr) {
        new c(str, bArr).execute(new Void[0]);
    }

    @Deprecated
    private static void b() {
        if (f != null) {
            try {
                f.c();
                f = null;
            } catch (IOException unused) {
                f = null;
            }
        }
    }

    @Deprecated
    private static b c() {
        return f;
    }
}
