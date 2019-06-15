package com.facebook.ads.internal.r.a;

import android.os.Build.VERSION;
import android.support.annotation.AnyThread;
import android.support.annotation.WorkerThread;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@WorkerThread
public class a {
    private static int[] f = new int[20];
    private static final String g = "a";
    private static a j;
    protected final q a = new f() {
    };
    protected final d b = new e();
    protected r c = new g();
    protected int d = 2000;
    protected int e = 8000;
    private int h = 3;
    private Map<String, String> i = new TreeMap();
    private boolean k;
    private Set<String> l;
    private Set<String> m;

    public interface a {
        @WorkerThread
        Map<String, String> a();
    }

    static {
        c();
        if (VERSION.SDK_INT > 8) {
            a();
        }
    }

    public static void a() {
        if (CookieHandler.getDefault() == null) {
            CookieHandler.setDefault(new CookieManager());
        }
    }

    public static synchronized void a(a aVar) {
        synchronized (a.class) {
            j = aVar;
        }
    }

    private static void c() {
        if (VERSION.SDK_INT < 8) {
            System.setProperty("http.keepAlive", InternalLogger.EVENT_PARAM_EXTRAS_FALSE);
        }
    }

    private void c(HttpURLConnection httpURLConnection) {
        for (String str : this.i.keySet()) {
            httpURLConnection.setRequestProperty(str, (String) this.i.get(str));
        }
        synchronized (a.class) {
            if (j != null) {
                Map a = j.a();
                for (String str2 : a.keySet()) {
                    httpURLConnection.setRequestProperty(str2, (String) a.get(str2));
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public int a(int i) {
        return 1000 * f[i + 2];
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x001f A:{SYNTHETIC, Splitter:B:17:0x001f} */
    public int a(java.net.HttpURLConnection r3, byte[] r4) {
        /*
        r2 = this;
        r0 = 0;
        r1 = r2.a;	 Catch:{ all -> 0x001b }
        r1 = r1.a(r3);	 Catch:{ all -> 0x001b }
        if (r1 == 0) goto L_0x0011;
    L_0x0009:
        r0 = r2.a;	 Catch:{ all -> 0x000f }
        r0.a(r1, r4);	 Catch:{ all -> 0x000f }
        goto L_0x0011;
    L_0x000f:
        r3 = move-exception;
        goto L_0x001d;
    L_0x0011:
        r3 = r3.getResponseCode();	 Catch:{ all -> 0x000f }
        if (r1 == 0) goto L_0x001a;
    L_0x0017:
        r1.close();	 Catch:{ Exception -> 0x001a }
    L_0x001a:
        return r3;
    L_0x001b:
        r3 = move-exception;
        r1 = r0;
    L_0x001d:
        if (r1 == 0) goto L_0x0022;
    L_0x001f:
        r1.close();	 Catch:{ Exception -> 0x0022 }
    L_0x0022:
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.r.a.a.a(java.net.HttpURLConnection, byte[]):int");
    }

    @AnyThread
    public a a(String str, String str2) {
        this.i.put(str, str2);
        return this;
    }

    public n a(l lVar) {
        m e;
        long currentTimeMillis = System.currentTimeMillis();
        int i = 0;
        while (i < this.h) {
            try {
                c(a(i));
                if (this.c.a()) {
                    r rVar = this.c;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(i + 1);
                    stringBuilder.append("of");
                    stringBuilder.append(this.h);
                    stringBuilder.append(", trying ");
                    stringBuilder.append(lVar.a());
                    rVar.a(stringBuilder.toString());
                }
                long currentTimeMillis2 = System.currentTimeMillis();
                try {
                    n a = a(lVar.a(), lVar.b(), lVar.c(), lVar.d());
                    if (a != null) {
                        return a;
                    }
                    currentTimeMillis = currentTimeMillis2;
                    i++;
                } catch (m e2) {
                    long j = currentTimeMillis2;
                    e = e2;
                    currentTimeMillis = j;
                    if (!a((Throwable) e, currentTimeMillis) && i < this.h - 1) {
                        continue;
                        i++;
                    } else if (this.a.a(e) || i >= this.h - 1) {
                        throw e;
                    } else {
                        try {
                            Thread.sleep((long) this.d);
                            i++;
                        } catch (InterruptedException unused) {
                            throw e;
                        }
                    }
                }
            } catch (m e3) {
                e = e3;
                if (!a((Throwable) e, currentTimeMillis)) {
                }
                if (this.a.a(e)) {
                }
                throw e;
            }
        }
        return null;
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x00cf  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0096 A:{SYNTHETIC, Splitter:B:51:0x0096} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:65:0x00b8 */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x00cf  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x00d6  */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:65|66|67|68|69) */
    /* JADX WARNING: Missing block: B:42:0x0087, code skipped:
            r5 = th;
     */
    /* JADX WARNING: Missing block: B:66:?, code skipped:
            com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r5);
     */
    /* JADX WARNING: Missing block: B:69:0x00c0, code skipped:
            throw new com.facebook.ads.internal.r.a.m(r5, null);
     */
    /* JADX WARNING: Missing block: B:70:0x00c1, code skipped:
            r6 = new com.facebook.ads.internal.r.a.m(r5, null);
     */
    /* JADX WARNING: Missing block: B:75:0x00cf, code skipped:
            r3.c.a(r1);
     */
    /* JADX WARNING: Missing block: B:77:0x00d6, code skipped:
            r4.disconnect();
     */
    public com.facebook.ads.internal.r.a.n a(java.lang.String r4, com.facebook.ads.internal.r.a.j r5, java.lang.String r6, byte[] r7) {
        /*
        r3 = this;
        r0 = 0;
        r1 = 0;
        r3.k = r0;	 Catch:{ Exception -> 0x008e, all -> 0x008b }
        r4 = r3.a(r4);	 Catch:{ Exception -> 0x008e, all -> 0x008b }
        r3.a(r4, r5, r6);	 Catch:{ Exception -> 0x0089 }
        r3.c(r4);	 Catch:{ Exception -> 0x0089 }
        r5 = r3.c;	 Catch:{ Exception -> 0x0089 }
        r5 = r5.a();	 Catch:{ Exception -> 0x0089 }
        if (r5 == 0) goto L_0x001b;
    L_0x0016:
        r5 = r3.c;	 Catch:{ Exception -> 0x0089 }
        r5.a(r4, r7);	 Catch:{ Exception -> 0x0089 }
    L_0x001b:
        r4.connect();	 Catch:{ Exception -> 0x0089 }
        r5 = 1;
        r3.k = r5;	 Catch:{ Exception -> 0x0089 }
        r6 = r3.m;	 Catch:{ Exception -> 0x0089 }
        if (r6 == 0) goto L_0x002f;
    L_0x0025:
        r6 = r3.m;	 Catch:{ Exception -> 0x0089 }
        r6 = r6.isEmpty();	 Catch:{ Exception -> 0x0089 }
        if (r6 != 0) goto L_0x002f;
    L_0x002d:
        r6 = r5;
        goto L_0x0030;
    L_0x002f:
        r6 = r0;
    L_0x0030:
        r2 = r3.l;	 Catch:{ Exception -> 0x0089 }
        if (r2 == 0) goto L_0x003d;
    L_0x0034:
        r2 = r3.l;	 Catch:{ Exception -> 0x0089 }
        r2 = r2.isEmpty();	 Catch:{ Exception -> 0x0089 }
        if (r2 != 0) goto L_0x003d;
    L_0x003c:
        goto L_0x003e;
    L_0x003d:
        r5 = r0;
    L_0x003e:
        r0 = r4 instanceof javax.net.ssl.HttpsURLConnection;	 Catch:{ Exception -> 0x0089 }
        if (r0 == 0) goto L_0x0059;
    L_0x0042:
        if (r6 != 0) goto L_0x0046;
    L_0x0044:
        if (r5 == 0) goto L_0x0059;
    L_0x0046:
        r5 = r4;
        r5 = (javax.net.ssl.HttpsURLConnection) r5;	 Catch:{ Exception -> 0x0051 }
        r6 = r3.m;	 Catch:{ Exception -> 0x0051 }
        r0 = r3.l;	 Catch:{ Exception -> 0x0051 }
        com.facebook.ads.internal.r.a.o.a(r5, r6, r0);	 Catch:{ Exception -> 0x0051 }
        goto L_0x0059;
    L_0x0051:
        r5 = move-exception;
        r6 = g;	 Catch:{ Exception -> 0x0089 }
        r0 = "Unable to validate SSL certificates.";
        android.util.Log.e(r6, r0, r5);	 Catch:{ Exception -> 0x0089 }
    L_0x0059:
        r5 = r4.getDoOutput();	 Catch:{ Exception -> 0x0089 }
        if (r5 == 0) goto L_0x0064;
    L_0x005f:
        if (r7 == 0) goto L_0x0064;
    L_0x0061:
        r3.a(r4, r7);	 Catch:{ Exception -> 0x0089 }
    L_0x0064:
        r5 = r4.getDoInput();	 Catch:{ Exception -> 0x0089 }
        if (r5 == 0) goto L_0x006f;
    L_0x006a:
        r5 = r3.a(r4);	 Catch:{ Exception -> 0x0089 }
        goto L_0x0074;
    L_0x006f:
        r5 = new com.facebook.ads.internal.r.a.n;	 Catch:{ Exception -> 0x0089 }
        r5.<init>(r4, r1);	 Catch:{ Exception -> 0x0089 }
    L_0x0074:
        r6 = r3.c;
        r6 = r6.a();
        if (r6 == 0) goto L_0x0081;
    L_0x007c:
        r6 = r3.c;
        r6.a(r5);
    L_0x0081:
        if (r4 == 0) goto L_0x0086;
    L_0x0083:
        r4.disconnect();
    L_0x0086:
        return r5;
    L_0x0087:
        r5 = move-exception;
        goto L_0x00c7;
    L_0x0089:
        r5 = move-exception;
        goto L_0x0090;
    L_0x008b:
        r5 = move-exception;
        r4 = r1;
        goto L_0x00c7;
    L_0x008e:
        r5 = move-exception;
        r4 = r1;
    L_0x0090:
        r6 = r3.b(r4);	 Catch:{ Exception -> 0x00b8 }
        if (r6 == 0) goto L_0x00b2;
    L_0x0096:
        r7 = r6.a();	 Catch:{ all -> 0x00af }
        if (r7 <= 0) goto L_0x00b2;
    L_0x009c:
        r5 = r3.c;
        r5 = r5.a();
        if (r5 == 0) goto L_0x00a9;
    L_0x00a4:
        r5 = r3.c;
        r5.a(r6);
    L_0x00a9:
        if (r4 == 0) goto L_0x00ae;
    L_0x00ab:
        r4.disconnect();
    L_0x00ae:
        return r6;
    L_0x00af:
        r5 = move-exception;
        r1 = r6;
        goto L_0x00c7;
    L_0x00b2:
        r7 = new com.facebook.ads.internal.r.a.m;	 Catch:{ all -> 0x00af }
        r7.<init>(r5, r6);	 Catch:{ all -> 0x00af }
        throw r7;	 Catch:{ all -> 0x00af }
    L_0x00b8:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r5);	 Catch:{ all -> 0x00c1 }
        r6 = new com.facebook.ads.internal.r.a.m;	 Catch:{ all -> 0x0087 }
        r6.<init>(r5, r1);	 Catch:{ all -> 0x0087 }
        throw r6;	 Catch:{ all -> 0x0087 }
    L_0x00c1:
        r6 = new com.facebook.ads.internal.r.a.m;	 Catch:{ all -> 0x0087 }
        r6.<init>(r5, r1);	 Catch:{ all -> 0x0087 }
        throw r6;	 Catch:{ all -> 0x0087 }
    L_0x00c7:
        r6 = r3.c;
        r6 = r6.a();
        if (r6 == 0) goto L_0x00d4;
    L_0x00cf:
        r6 = r3.c;
        r6.a(r1);
    L_0x00d4:
        if (r4 == 0) goto L_0x00d9;
    L_0x00d6:
        r4.disconnect();
    L_0x00d9:
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.r.a.a.a(java.lang.String, com.facebook.ads.internal.r.a.j, java.lang.String, byte[]):com.facebook.ads.internal.r.a.n");
    }

    public n a(String str, p pVar) {
        return b(new i(str, pVar));
    }

    public n a(String str, String str2, byte[] bArr) {
        return b(new k(str, null, str2, bArr));
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0021 A:{SYNTHETIC, Splitter:B:17:0x0021} */
    public com.facebook.ads.internal.r.a.n a(java.net.HttpURLConnection r4) {
        /*
        r3 = this;
        r0 = 0;
        r1 = r3.a;	 Catch:{ all -> 0x001d }
        r1 = r1.b(r4);	 Catch:{ all -> 0x001d }
        if (r1 == 0) goto L_0x0012;
    L_0x0009:
        r0 = r3.a;	 Catch:{ all -> 0x0010 }
        r0 = r0.a(r1);	 Catch:{ all -> 0x0010 }
        goto L_0x0012;
    L_0x0010:
        r4 = move-exception;
        goto L_0x001f;
    L_0x0012:
        r2 = new com.facebook.ads.internal.r.a.n;	 Catch:{ all -> 0x0010 }
        r2.<init>(r4, r0);	 Catch:{ all -> 0x0010 }
        if (r1 == 0) goto L_0x001c;
    L_0x0019:
        r1.close();	 Catch:{ Exception -> 0x001c }
    L_0x001c:
        return r2;
    L_0x001d:
        r4 = move-exception;
        r1 = r0;
    L_0x001f:
        if (r1 == 0) goto L_0x0024;
    L_0x0021:
        r1.close();	 Catch:{ Exception -> 0x0024 }
    L_0x0024:
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.r.a.a.a(java.net.HttpURLConnection):com.facebook.ads.internal.r.a.n");
    }

    /* Access modifiers changed, original: protected */
    public HttpURLConnection a(String str) {
        try {
            URL url = new URL(str);
            return this.a.a(str);
        } catch (MalformedURLException e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(" is not a valid URL");
            throw new IllegalArgumentException(stringBuilder.toString(), e);
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(l lVar, b bVar) {
        this.b.a(this, bVar).a(lVar);
    }

    public void a(String str, p pVar, b bVar) {
        a(new k(str, pVar), bVar);
    }

    /* Access modifiers changed, original: protected */
    public void a(HttpURLConnection httpURLConnection, j jVar, String str) {
        httpURLConnection.setConnectTimeout(this.d);
        httpURLConnection.setReadTimeout(this.e);
        this.a.a(httpURLConnection, jVar, str);
    }

    @AnyThread
    public void a(Set<String> set) {
        this.m = set;
    }

    /* Access modifiers changed, original: protected */
    public boolean a(Throwable th, long j) {
        long currentTimeMillis = (System.currentTimeMillis() - j) + 10;
        if (this.c.a()) {
            r rVar = this.c;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ELAPSED TIME = ");
            stringBuilder.append(currentTimeMillis);
            stringBuilder.append(", CT = ");
            stringBuilder.append(this.d);
            stringBuilder.append(", RT = ");
            stringBuilder.append(this.e);
            rVar.a(stringBuilder.toString());
        }
        boolean z = false;
        if (this.k) {
            if (currentTimeMillis >= ((long) this.e)) {
                z = true;
            }
            return z;
        }
        if (currentTimeMillis >= ((long) this.d)) {
            z = true;
        }
        return z;
    }

    public n b(l lVar) {
        try {
            return a(lVar.a(), lVar.b(), lVar.c(), lVar.d());
        } catch (m e) {
            this.a.a(e);
            return null;
        } catch (Exception e2) {
            this.a.a(new m(e2, null));
            return null;
        }
    }

    public n b(String str, p pVar) {
        return b(new k(str, pVar));
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x001f A:{SYNTHETIC, Splitter:B:17:0x001f} */
    public com.facebook.ads.internal.r.a.n b(java.net.HttpURLConnection r4) {
        /*
        r3 = this;
        r0 = 0;
        r1 = r4.getErrorStream();	 Catch:{ all -> 0x001b }
        if (r1 == 0) goto L_0x0010;
    L_0x0007:
        r0 = r3.a;	 Catch:{ all -> 0x000e }
        r0 = r0.a(r1);	 Catch:{ all -> 0x000e }
        goto L_0x0010;
    L_0x000e:
        r4 = move-exception;
        goto L_0x001d;
    L_0x0010:
        r2 = new com.facebook.ads.internal.r.a.n;	 Catch:{ all -> 0x000e }
        r2.<init>(r4, r0);	 Catch:{ all -> 0x000e }
        if (r1 == 0) goto L_0x001a;
    L_0x0017:
        r1.close();	 Catch:{ Exception -> 0x001a }
    L_0x001a:
        return r2;
    L_0x001b:
        r4 = move-exception;
        r1 = r0;
    L_0x001d:
        if (r1 == 0) goto L_0x0022;
    L_0x001f:
        r1.close();	 Catch:{ Exception -> 0x0022 }
    L_0x0022:
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.r.a.a.b(java.net.HttpURLConnection):com.facebook.ads.internal.r.a.n");
    }

    public p b() {
        return new p();
    }

    @AnyThread
    public void b(int i) {
        if (i < 1 || i > 18) {
            throw new IllegalArgumentException("Maximum retries must be between 1 and 18");
        }
        this.h = i;
    }

    @AnyThread
    public void b(Set<String> set) {
        this.l = set;
    }

    @AnyThread
    public void c(int i) {
        this.d = i;
    }

    @AnyThread
    public void d(int i) {
        this.e = i;
    }
}
