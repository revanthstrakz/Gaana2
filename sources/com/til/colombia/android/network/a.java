package com.til.colombia.android.network;

import com.til.colombia.android.internal.a.h;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class a {
    ScheduledExecutorService a;
    public List<String> b = new ArrayList();
    public List<String> c = new ArrayList();

    private void c() {
        if (this.a == null || this.a.isShutdown() || this.a.isTerminated()) {
            this.a = Executors.newScheduledThreadPool(1);
        }
        this.a.schedule(new b(this), 3000, TimeUnit.MILLISECONDS);
    }

    public final void a(String str) {
        if (!h.a(str)) {
            if (this.b == null) {
                this.b = new ArrayList();
            }
            if (this.c == null) {
                this.c = new ArrayList();
            }
            new Thread(new c(this, str)).start();
        }
    }

    public final void a() {
        List arrayList = new ArrayList();
        synchronized (this.b) {
            this.c.addAll(this.b);
            arrayList.addAll(this.c);
            this.b.removeAll(this.c);
        }
        a(arrayList);
        synchronized (this.b) {
            b(this.c);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x008d  */
    static boolean a(java.util.List<java.lang.String> r6) {
        /*
        r0 = r6.isEmpty();
        r1 = 0;
        if (r0 == 0) goto L_0x0008;
    L_0x0007:
        return r1;
    L_0x0008:
        r0 = 0;
        r2 = new java.net.URL;	 Catch:{ Exception -> 0x0081 }
        r3 = "https://ade.clmbtech.com/cde/mnotify.htm";
        r2.<init>(r3);	 Catch:{ Exception -> 0x0081 }
        r2 = r2.openConnection();	 Catch:{ Exception -> 0x0081 }
        r2 = (java.net.HttpURLConnection) r2;	 Catch:{ Exception -> 0x0081 }
        r0 = 15000; // 0x3a98 float:2.102E-41 double:7.411E-320;
        r2.setConnectTimeout(r0);	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r2.setReadTimeout(r0);	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r0 = 1;
        r2.setDoOutput(r0);	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r3 = "User-Agent";
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r4.<init>();	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r5 = "http.agent";
        r5 = java.lang.System.getProperty(r5);	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r4.append(r5);	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r5 = "Col:aos:4.0.0";
        r4.append(r5);	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r4 = r4.toString();	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r2.setRequestProperty(r3, r4);	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r3 = "Content-Type";
        r4 = "application/json; charset=UTF-8";
        r2.setRequestProperty(r3, r4);	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r3 = "POST";
        r2.setRequestMethod(r3);	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r2.setUseCaches(r1);	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r3 = new java.io.OutputStreamWriter;	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r4 = r2.getOutputStream();	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r3.<init>(r4);	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r6 = r6.toString();	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r3.write(r6);	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r3.flush();	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r2.connect();	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r6 = r2.getResponseCode();	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r6 = r6 / 10;
        r3 = 20;
        if (r6 != r3) goto L_0x0073;
    L_0x006d:
        if (r2 == 0) goto L_0x0072;
    L_0x006f:
        r2.disconnect();
    L_0x0072:
        return r0;
    L_0x0073:
        if (r2 == 0) goto L_0x008a;
    L_0x0075:
        r2.disconnect();
        goto L_0x008a;
    L_0x0079:
        r6 = move-exception;
        goto L_0x008b;
    L_0x007b:
        r6 = move-exception;
        r0 = r2;
        goto L_0x0082;
    L_0x007e:
        r6 = move-exception;
        r2 = r0;
        goto L_0x008b;
    L_0x0081:
        r6 = move-exception;
    L_0x0082:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r6);	 Catch:{ all -> 0x007e }
        if (r0 == 0) goto L_0x008a;
    L_0x0087:
        r0.disconnect();
    L_0x008a:
        return r1;
    L_0x008b:
        if (r2 == 0) goto L_0x0090;
    L_0x008d:
        r2.disconnect();
    L_0x0090:
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.network.a.a(java.util.List):boolean");
    }

    public final synchronized void b() {
        b(this.b);
        b(this.c);
        if (this.a != null) {
            this.a.shutdownNow();
        }
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized void b(List<String> list) {
        if (list != null) {
            list.clear();
        }
    }
}
