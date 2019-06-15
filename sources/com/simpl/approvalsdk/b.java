package com.simpl.approvalsdk;

import android.os.Handler;
import android.os.Looper;

class b {
    private static final String a = "b";
    private SimplUserApprovalListenerV2 b;

    /* renamed from: com.simpl.approvalsdk.b$1 */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ String a;

        AnonymousClass1(String str) {
            this.a = str;
        }

        /* JADX WARNING: Removed duplicated region for block: B:46:0x0260  */
        /* JADX WARNING: Removed duplicated region for block: B:50:0x0267  */
        public final void run() {
            /*
            r7 = this;
            r0 = "TLSv1";
            r0 = javax.net.ssl.SSLContext.getInstance(r0);	 Catch:{ KeyManagementException | NoSuchAlgorithmException -> 0x026b, KeyManagementException | NoSuchAlgorithmException -> 0x026b }
            r1 = 0;
            r0.init(r1, r1, r1);	 Catch:{ KeyManagementException | NoSuchAlgorithmException -> 0x026b, KeyManagementException | NoSuchAlgorithmException -> 0x026b }
            r2 = new com.simpl.approvalsdk.util.NoSSLv3SocketFactory;
            r0 = r0.getSocketFactory();
            r2.<init>(r0);
            javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(r2);
            r0 = new java.net.URL;	 Catch:{ Exception -> 0x0245, all -> 0x0240 }
            r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0245, all -> 0x0240 }
            r2.<init>();	 Catch:{ Exception -> 0x0245, all -> 0x0240 }
            r3 = com.simpl.approvalsdk.SimplApproval.getInstance();	 Catch:{ Exception -> 0x0245, all -> 0x0240 }
            r3 = r3.getApprovalsApiBaseUrl();	 Catch:{ Exception -> 0x0245, all -> 0x0240 }
            r2.append(r3);	 Catch:{ Exception -> 0x0245, all -> 0x0240 }
            r3 = "simpl_buy/approved?phone_number=";
            r2.append(r3);	 Catch:{ Exception -> 0x0245, all -> 0x0240 }
            r3 = com.simpl.approvalsdk.SimplApproval.getInstance();	 Catch:{ Exception -> 0x0245, all -> 0x0240 }
            r3 = r3.getSimplUser();	 Catch:{ Exception -> 0x0245, all -> 0x0240 }
            r3 = r3.getPhoneNumber();	 Catch:{ Exception -> 0x0245, all -> 0x0240 }
            r2.append(r3);	 Catch:{ Exception -> 0x0245, all -> 0x0240 }
            r3 = "&merchant_id=";
            r2.append(r3);	 Catch:{ Exception -> 0x0245, all -> 0x0240 }
            r3 = com.simpl.approvalsdk.SimplApproval.getInstance();	 Catch:{ Exception -> 0x0245, all -> 0x0240 }
            r3 = r3.getMerchantId();	 Catch:{ Exception -> 0x0245, all -> 0x0240 }
            r2.append(r3);	 Catch:{ Exception -> 0x0245, all -> 0x0240 }
            r3 = "&src=android";
            r2.append(r3);	 Catch:{ Exception -> 0x0245, all -> 0x0240 }
            r2 = r2.toString();	 Catch:{ Exception -> 0x0245, all -> 0x0240 }
            r0.<init>(r2);	 Catch:{ Exception -> 0x0245, all -> 0x0240 }
            com.simpl.approvalsdk.b.a;	 Catch:{ Exception -> 0x0245, all -> 0x0240 }
            r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0245, all -> 0x0240 }
            r3 = "Final url is => ";
            r2.<init>(r3);	 Catch:{ Exception -> 0x0245, all -> 0x0240 }
            r3 = r0.toString();	 Catch:{ Exception -> 0x0245, all -> 0x0240 }
            r2.append(r3);	 Catch:{ Exception -> 0x0245, all -> 0x0240 }
            r0 = r0.openConnection();	 Catch:{ Exception -> 0x0245, all -> 0x0240 }
            r0 = (javax.net.ssl.HttpsURLConnection) r0;	 Catch:{ Exception -> 0x0245, all -> 0x0240 }
            r1 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
            r0.setReadTimeout(r1);	 Catch:{ Exception -> 0x023e }
            r0.setConnectTimeout(r1);	 Catch:{ Exception -> 0x023e }
            r1 = 1;
            r0.setDoInput(r1);	 Catch:{ Exception -> 0x023e }
            r0.setDoOutput(r1);	 Catch:{ Exception -> 0x023e }
            r1 = "POST";
            r0.setRequestMethod(r1);	 Catch:{ Exception -> 0x023e }
            r1 = 0;
            r0.setUseCaches(r1);	 Catch:{ Exception -> 0x023e }
            r2 = r0.getOutputStream();	 Catch:{ Exception -> 0x023e }
            r3 = new org.json.JSONObject;	 Catch:{ Exception -> 0x023e }
            r3.<init>();	 Catch:{ Exception -> 0x023e }
            r4 = "payload";
            r5 = r7.a;	 Catch:{ Exception -> 0x023e }
            r3.put(r4, r5);	 Catch:{ Exception -> 0x023e }
            r3 = r3.toString();	 Catch:{ Exception -> 0x023e }
            r3 = r3.getBytes();	 Catch:{ Exception -> 0x023e }
            r2.write(r3);	 Catch:{ Exception -> 0x023e }
            r2.close();	 Catch:{ Exception -> 0x023e }
            r2 = r0.getResponseCode();	 Catch:{ Exception -> 0x023e }
            r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
            if (r2 != r3) goto L_0x0112;
        L_0x00ad:
            r1 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x023e }
            r2 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x023e }
            r3 = r0.getInputStream();	 Catch:{ Exception -> 0x023e }
            r2.<init>(r3);	 Catch:{ Exception -> 0x023e }
            r1.<init>(r2);	 Catch:{ Exception -> 0x023e }
            r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x023e }
            r2.<init>();	 Catch:{ Exception -> 0x023e }
        L_0x00c0:
            r3 = r1.readLine();	 Catch:{ Exception -> 0x023e }
            if (r3 == 0) goto L_0x00ca;
        L_0x00c6:
            r2.append(r3);	 Catch:{ Exception -> 0x023e }
            goto L_0x00c0;
        L_0x00ca:
            com.simpl.approvalsdk.b.a;	 Catch:{ Exception -> 0x023e }
            r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x023e }
            r4 = "response is =>";
            r3.<init>(r4);	 Catch:{ Exception -> 0x023e }
            r4 = r2.toString();	 Catch:{ Exception -> 0x023e }
            r3.append(r4);	 Catch:{ Exception -> 0x023e }
            r1.close();	 Catch:{ Exception -> 0x023e }
            r1 = new org.json.JSONObject;	 Catch:{ Exception -> 0x023e }
            r2 = r2.toString();	 Catch:{ Exception -> 0x023e }
            r1.<init>(r2);	 Catch:{ Exception -> 0x023e }
            r2 = com.simpl.approvalsdk.b.this;	 Catch:{ Exception -> 0x023e }
            r3 = "data";
            r3 = r1.getJSONObject(r3);	 Catch:{ Exception -> 0x023e }
            r4 = "approved";
            r3 = r3.optBoolean(r4);	 Catch:{ Exception -> 0x023e }
            r4 = "data";
            r4 = r1.getJSONObject(r4);	 Catch:{ Exception -> 0x023e }
            r5 = "button_text";
            r4 = r4.optString(r5);	 Catch:{ Exception -> 0x023e }
            r5 = "data";
            r1 = r1.getJSONObject(r5);	 Catch:{ Exception -> 0x023e }
            r5 = "first_transaction";
            r1 = r1.optBoolean(r5);	 Catch:{ Exception -> 0x023e }
            com.simpl.approvalsdk.b.a(new com.simpl.approvalsdk.b.AnonymousClass2(r3, r4, r1));	 Catch:{ Exception -> 0x023e }
            goto L_0x0238;
        L_0x0112:
            r2 = r0.getResponseCode();	 Catch:{ Exception -> 0x023e }
            r3 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
            if (r2 < r3) goto L_0x01b7;
        L_0x011a:
            r2 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x023e }
            r3 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x023e }
            r4 = r0.getErrorStream();	 Catch:{ Exception -> 0x023e }
            r3.<init>(r4);	 Catch:{ Exception -> 0x023e }
            r2.<init>(r3);	 Catch:{ Exception -> 0x023e }
            r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x023e }
            r3.<init>();	 Catch:{ Exception -> 0x023e }
        L_0x012d:
            r4 = r2.readLine();	 Catch:{ Exception -> 0x023e }
            if (r4 == 0) goto L_0x0137;
        L_0x0133:
            r3.append(r4);	 Catch:{ Exception -> 0x023e }
            goto L_0x012d;
        L_0x0137:
            com.simpl.approvalsdk.b.a;	 Catch:{ Exception -> 0x023e }
            r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x023e }
            r5 = "response is =>";
            r4.<init>(r5);	 Catch:{ Exception -> 0x023e }
            r5 = r3.toString();	 Catch:{ Exception -> 0x023e }
            r4.append(r5);	 Catch:{ Exception -> 0x023e }
            r2.close();	 Catch:{ Exception -> 0x023e }
            r2 = new org.json.JSONObject;	 Catch:{ Exception -> 0x023e }
            r3 = r3.toString();	 Catch:{ Exception -> 0x023e }
            r2.<init>(r3);	 Catch:{ Exception -> 0x023e }
            r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x023e }
            r3.<init>();	 Catch:{ Exception -> 0x023e }
            r4 = "errors";
            r4 = r2.getJSONArray(r4);	 Catch:{ Exception -> 0x023e }
        L_0x015f:
            r5 = r4.length();	 Catch:{ Exception -> 0x023e }
            if (r1 >= r5) goto L_0x0174;
        L_0x0165:
            r5 = r4.getString(r1);	 Catch:{ Exception -> 0x023e }
            r3.append(r5);	 Catch:{ Exception -> 0x023e }
            r5 = "\n";
            r3.append(r5);	 Catch:{ Exception -> 0x023e }
            r1 = r1 + 1;
            goto L_0x015f;
        L_0x0174:
            r1 = com.simpl.approvalsdk.b.a;	 Catch:{ Exception -> 0x023e }
            r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x023e }
            r5 = "Error while approving user => ";
            r4.<init>(r5);	 Catch:{ Exception -> 0x023e }
            r5 = r3.toString();	 Catch:{ Exception -> 0x023e }
            r4.append(r5);	 Catch:{ Exception -> 0x023e }
            r4 = r4.toString();	 Catch:{ Exception -> 0x023e }
            android.util.Log.e(r1, r4);	 Catch:{ Exception -> 0x023e }
            r1 = com.simpl.approvalsdk.b.this;	 Catch:{ Exception -> 0x023e }
            r4 = new java.lang.Throwable;	 Catch:{ Exception -> 0x023e }
            r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x023e }
            r5.<init>();	 Catch:{ Exception -> 0x023e }
            r3 = r3.toString();	 Catch:{ Exception -> 0x023e }
            r5.append(r3);	 Catch:{ Exception -> 0x023e }
            r3 = "\n";
            r5.append(r3);	 Catch:{ Exception -> 0x023e }
            r3 = "error_message";
            r2 = r2.getString(r3);	 Catch:{ Exception -> 0x023e }
            r5.append(r2);	 Catch:{ Exception -> 0x023e }
            r2 = r5.toString();	 Catch:{ Exception -> 0x023e }
            r4.<init>(r2);	 Catch:{ Exception -> 0x023e }
        L_0x01b2:
            com.simpl.approvalsdk.b.a(new com.simpl.approvalsdk.b.AnonymousClass3(r4));	 Catch:{ Exception -> 0x023e }
            goto L_0x0238;
        L_0x01b7:
            r2 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x023e }
            r3 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x023e }
            r4 = r0.getInputStream();	 Catch:{ Exception -> 0x023e }
            r3.<init>(r4);	 Catch:{ Exception -> 0x023e }
            r2.<init>(r3);	 Catch:{ Exception -> 0x023e }
            r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x023e }
            r3.<init>();	 Catch:{ Exception -> 0x023e }
        L_0x01ca:
            r4 = r2.readLine();	 Catch:{ Exception -> 0x023e }
            if (r4 == 0) goto L_0x01d4;
        L_0x01d0:
            r3.append(r4);	 Catch:{ Exception -> 0x023e }
            goto L_0x01ca;
        L_0x01d4:
            com.simpl.approvalsdk.b.a;	 Catch:{ Exception -> 0x023e }
            r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x023e }
            r5 = "response is =>";
            r4.<init>(r5);	 Catch:{ Exception -> 0x023e }
            r5 = r3.toString();	 Catch:{ Exception -> 0x023e }
            r4.append(r5);	 Catch:{ Exception -> 0x023e }
            r2.close();	 Catch:{ Exception -> 0x023e }
            r2 = new org.json.JSONObject;	 Catch:{ Exception -> 0x023e }
            r3 = r3.toString();	 Catch:{ Exception -> 0x023e }
            r2.<init>(r3);	 Catch:{ Exception -> 0x023e }
            r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x023e }
            r3.<init>();	 Catch:{ Exception -> 0x023e }
            r4 = "errors";
            r4 = r2.getJSONArray(r4);	 Catch:{ Exception -> 0x023e }
        L_0x01fc:
            r5 = r4.length();	 Catch:{ Exception -> 0x023e }
            if (r1 >= r5) goto L_0x0211;
        L_0x0202:
            r5 = r4.getString(r1);	 Catch:{ Exception -> 0x023e }
            r3.append(r5);	 Catch:{ Exception -> 0x023e }
            r5 = "\n";
            r3.append(r5);	 Catch:{ Exception -> 0x023e }
            r1 = r1 + 1;
            goto L_0x01fc;
        L_0x0211:
            r1 = com.simpl.approvalsdk.b.this;	 Catch:{ Exception -> 0x023e }
            r4 = new java.lang.Throwable;	 Catch:{ Exception -> 0x023e }
            r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x023e }
            r5.<init>();	 Catch:{ Exception -> 0x023e }
            r3 = r3.toString();	 Catch:{ Exception -> 0x023e }
            r5.append(r3);	 Catch:{ Exception -> 0x023e }
            r3 = "\n";
            r5.append(r3);	 Catch:{ Exception -> 0x023e }
            r3 = "error_message";
            r2 = r2.getString(r3);	 Catch:{ Exception -> 0x023e }
            r5.append(r2);	 Catch:{ Exception -> 0x023e }
            r2 = r5.toString();	 Catch:{ Exception -> 0x023e }
            r4.<init>(r2);	 Catch:{ Exception -> 0x023e }
            goto L_0x01b2;
        L_0x0238:
            if (r0 == 0) goto L_0x0263;
        L_0x023a:
            r0.disconnect();
            return;
        L_0x023e:
            r1 = move-exception;
            goto L_0x0249;
        L_0x0240:
            r0 = move-exception;
            r6 = r1;
            r1 = r0;
            r0 = r6;
            goto L_0x0265;
        L_0x0245:
            r0 = move-exception;
            r6 = r1;
            r1 = r0;
            r0 = r6;
        L_0x0249:
            r2 = com.simpl.approvalsdk.b.a;	 Catch:{ all -> 0x0264 }
            r3 = "Failed while making request";
            android.util.Log.e(r2, r3, r1);	 Catch:{ all -> 0x0264 }
            r2 = com.simpl.android.fingerprint.commons.exception.ExceptionNotifier.getSharedInstance();	 Catch:{ all -> 0x0264 }
            r2.send(r1);	 Catch:{ all -> 0x0264 }
            r2 = com.simpl.approvalsdk.b.this;	 Catch:{ all -> 0x0264 }
            com.simpl.approvalsdk.b.a(new com.simpl.approvalsdk.b.AnonymousClass3(r1));	 Catch:{ all -> 0x0264 }
            if (r0 == 0) goto L_0x0263;
        L_0x0260:
            r0.disconnect();
        L_0x0263:
            return;
        L_0x0264:
            r1 = move-exception;
        L_0x0265:
            if (r0 == 0) goto L_0x026a;
        L_0x0267:
            r0.disconnect();
        L_0x026a:
            throw r1;
        L_0x026b:
            r0 = move-exception;
            r1 = com.simpl.android.fingerprint.commons.exception.ExceptionNotifier.getSharedInstance();
            r1.send(r0);
            r1 = com.simpl.approvalsdk.SimplApproval.getInstance();
            r1 = r1.getGlobalSimplUserApprovalListener();
            r1.onError(r0);
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.simpl.approvalsdk.b$AnonymousClass1.run():void");
        }
    }

    public b(SimplUserApprovalListenerV2 simplUserApprovalListenerV2) {
        this.b = simplUserApprovalListenerV2;
    }

    private static void a(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }
}
