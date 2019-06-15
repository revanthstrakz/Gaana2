package com.appsflyer;

import java.io.IOException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;

public abstract class o implements Runnable {
    String a;
    private a b;
    private h c;

    public static class a {
        static HttpsURLConnection a(String str) throws IOException {
            return (HttpsURLConnection) new URL(str).openConnection();
        }
    }

    public abstract String a();

    public abstract void a(String str);

    public abstract void a(HttpsURLConnection httpsURLConnection) throws JSONException, IOException;

    public abstract void b();

    o(h hVar) {
        this.c = hVar;
    }

    public void a(a aVar) {
        this.b = aVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x00c8  */
    public void run() {
        /*
        r10 = this;
        r0 = java.lang.System.currentTimeMillis();
        r2 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r0 = r0 / r2;
        r2 = "";
        r3 = "";
        r4 = r10.a();
        r5 = "oneLinkUrl: ";
        r6 = java.lang.String.valueOf(r4);
        r5 = r5.concat(r6);
        com.appsflyer.AFLogger.a(r5);
        r5 = com.appsflyer.o.a.a(r4);	 Catch:{ Throwable -> 0x0088 }
        r6 = "content-type";
        r7 = "application/json";
        r5.addRequestProperty(r6, r7);	 Catch:{ Throwable -> 0x0088 }
        r6 = "authorization";
        r7 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0088 }
        r7.<init>();	 Catch:{ Throwable -> 0x0088 }
        r8 = com.appsflyer.i.a();	 Catch:{ Throwable -> 0x0088 }
        r9 = "AppsFlyerKey";
        r8 = r8.a(r9);	 Catch:{ Throwable -> 0x0088 }
        r7.append(r8);	 Catch:{ Throwable -> 0x0088 }
        r7.append(r0);	 Catch:{ Throwable -> 0x0088 }
        r7 = r7.toString();	 Catch:{ Throwable -> 0x0088 }
        r7 = com.appsflyer.ad.a(r7);	 Catch:{ Throwable -> 0x0088 }
        r5.addRequestProperty(r6, r7);	 Catch:{ Throwable -> 0x0088 }
        r6 = "af-timestamp";
        r0 = java.lang.String.valueOf(r0);	 Catch:{ Throwable -> 0x0088 }
        r5.addRequestProperty(r6, r0);	 Catch:{ Throwable -> 0x0088 }
        r0 = 3000; // 0xbb8 float:4.204E-42 double:1.482E-320;
        r5.setReadTimeout(r0);	 Catch:{ Throwable -> 0x0088 }
        r5.setConnectTimeout(r0);	 Catch:{ Throwable -> 0x0088 }
        r10.a(r5);	 Catch:{ Throwable -> 0x0088 }
        r0 = r5.getResponseCode();	 Catch:{ Throwable -> 0x0088 }
        r1 = com.appsflyer.h.a(r5);	 Catch:{ Throwable -> 0x0088 }
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r0 != r2) goto L_0x0071;
    L_0x0069:
        r0 = "Status 200 ok";
        com.appsflyer.AFLogger.d(r0);	 Catch:{ Throwable -> 0x006f }
        goto L_0x00b1;
    L_0x006f:
        r0 = move-exception;
        goto L_0x008a;
    L_0x0071:
        r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x006f }
        r3 = "Response code = ";
        r2.<init>(r3);	 Catch:{ Throwable -> 0x006f }
        r2.append(r0);	 Catch:{ Throwable -> 0x006f }
        r0 = " content = ";
        r2.append(r0);	 Catch:{ Throwable -> 0x006f }
        r2.append(r1);	 Catch:{ Throwable -> 0x006f }
        r3 = r2.toString();	 Catch:{ Throwable -> 0x006f }
        goto L_0x00b1;
    L_0x0088:
        r0 = move-exception;
        r1 = r2;
    L_0x008a:
        r2 = "Error while calling ";
        r3 = java.lang.String.valueOf(r4);
        r2 = r2.concat(r3);
        com.appsflyer.AFLogger.a(r2, r0);
        r2 = new java.lang.StringBuilder;
        r3 = "Error while calling ";
        r2.<init>(r3);
        r2.append(r4);
        r3 = " stacktrace: ";
        r2.append(r3);
        r0 = r0.toString();
        r2.append(r0);
        r3 = r2.toString();
    L_0x00b1:
        r0 = android.text.TextUtils.isEmpty(r3);
        if (r0 == 0) goto L_0x00c8;
    L_0x00b7:
        r0 = "Connection call succeeded: ";
        r2 = java.lang.String.valueOf(r1);
        r0 = r0.concat(r2);
        com.appsflyer.AFLogger.d(r0);
        r10.a(r1);
        return;
    L_0x00c8:
        r0 = "Connection error: ";
        r1 = java.lang.String.valueOf(r3);
        r0 = r0.concat(r1);
        com.appsflyer.AFLogger.e(r0);
        r10.b();
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.o.run():void");
    }
}
