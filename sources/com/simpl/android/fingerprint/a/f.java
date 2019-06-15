package com.simpl.android.fingerprint.a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;

final class f {
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0050  */
    static java.lang.String a() {
        /*
        r0 = 0;
        r1 = new java.net.URL;	 Catch:{ SocketTimeoutException -> 0x005b, Exception -> 0x0046, all -> 0x0041 }
        r2 = "https://approvals-api.getsimpl.com/my-ip";
        r1.<init>(r2);	 Catch:{ SocketTimeoutException -> 0x005b, Exception -> 0x0046, all -> 0x0041 }
        r1 = r1.openConnection();	 Catch:{ SocketTimeoutException -> 0x005b, Exception -> 0x0046, all -> 0x0041 }
        r1 = (javax.net.ssl.HttpsURLConnection) r1;	 Catch:{ SocketTimeoutException -> 0x005b, Exception -> 0x0046, all -> 0x0041 }
        r0 = "GET";
        r1.setRequestMethod(r0);	 Catch:{ SocketTimeoutException -> 0x003f, Exception -> 0x003d }
        r0 = 150; // 0x96 float:2.1E-43 double:7.4E-322;
        r1.setReadTimeout(r0);	 Catch:{ SocketTimeoutException -> 0x003f, Exception -> 0x003d }
        r0 = 250; // 0xfa float:3.5E-43 double:1.235E-321;
        r1.setConnectTimeout(r0);	 Catch:{ SocketTimeoutException -> 0x003f, Exception -> 0x003d }
        r0 = r1.getResponseCode();	 Catch:{ SocketTimeoutException -> 0x003f, Exception -> 0x003d }
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r0 != r2) goto L_0x0035;
    L_0x0025:
        r0 = a(r1);	 Catch:{ SocketTimeoutException -> 0x003f, Exception -> 0x003d }
        r2 = "ip";
        r0 = r0.getString(r2);	 Catch:{ SocketTimeoutException -> 0x003f, Exception -> 0x003d }
        if (r1 == 0) goto L_0x0034;
    L_0x0031:
        r1.disconnect();
    L_0x0034:
        return r0;
    L_0x0035:
        r0 = "error";
        if (r1 == 0) goto L_0x003c;
    L_0x0039:
        r1.disconnect();
    L_0x003c:
        return r0;
    L_0x003d:
        r0 = move-exception;
        goto L_0x004a;
    L_0x003f:
        r0 = r1;
        goto L_0x005b;
    L_0x0041:
        r1 = move-exception;
        r3 = r1;
        r1 = r0;
        r0 = r3;
        goto L_0x0055;
    L_0x0046:
        r1 = move-exception;
        r3 = r1;
        r1 = r0;
        r0 = r3;
    L_0x004a:
        r0 = r0.getMessage();	 Catch:{ all -> 0x0054 }
        if (r1 == 0) goto L_0x0053;
    L_0x0050:
        r1.disconnect();
    L_0x0053:
        return r0;
    L_0x0054:
        r0 = move-exception;
    L_0x0055:
        if (r1 == 0) goto L_0x005a;
    L_0x0057:
        r1.disconnect();
    L_0x005a:
        throw r0;
    L_0x005b:
        r1 = "timeout";
        if (r0 == 0) goto L_0x0062;
    L_0x005f:
        r0.disconnect();
    L_0x0062:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.simpl.android.fingerprint.a.f.a():java.lang.String");
    }

    private static JSONObject a(HttpsURLConnection httpsURLConnection) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                stringBuilder.append(readLine);
            } else {
                bufferedReader.close();
                return new JSONObject(stringBuilder.toString());
            }
        }
    }
}
