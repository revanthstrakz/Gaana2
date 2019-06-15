package com.simpl.android.zeroClickSdk.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.simpl.android.zeroClickSdk.SimplZeroClickTokenListener;
import com.simpl.approvalsdk.SimplUser;
import java.lang.ref.WeakReference;

public class h {
    public static final String a = "h";
    private WeakReference<Context> b;
    private SimplZeroClickTokenListener c;

    /* renamed from: com.simpl.android.zeroClickSdk.internal.h$1 */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ SimplUser a;
        final /* synthetic */ String b;

        AnonymousClass1(SimplUser simplUser, String str) {
            this.a = simplUser;
            this.b = str;
        }

        /* JADX WARNING: Removed duplicated region for block: B:60:0x023f  */
        /* JADX WARNING: Removed duplicated region for block: B:57:0x0239  */
        public final void run() {
            /*
            r6 = this;
            r0 = 0;
            r1 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0223 }
            r1.<init>();	 Catch:{ Exception -> 0x0223 }
            r2 = "phone_number";
            r3 = r6.a;	 Catch:{ Exception -> 0x0223 }
            r3 = r3.getPhoneNumber();	 Catch:{ Exception -> 0x0223 }
            r1.put(r2, r3);	 Catch:{ Exception -> 0x0223 }
            r2 = com.simpl.android.zeroClickSdk.internal.h.this;	 Catch:{ Exception -> 0x0223 }
            r2 = r2.b;	 Catch:{ Exception -> 0x0223 }
            r2 = r2.get();	 Catch:{ Exception -> 0x0223 }
            if (r2 == 0) goto L_0x0032;
        L_0x001d:
            r2 = "app_hash";
            r3 = com.simpl.android.zeroClickSdk.internal.h.this;	 Catch:{ Exception -> 0x0223 }
            r3 = r3.b;	 Catch:{ Exception -> 0x0223 }
            r3 = r3.get();	 Catch:{ Exception -> 0x0223 }
            r3 = (android.content.Context) r3;	 Catch:{ Exception -> 0x0223 }
            r3 = ((java.lang.String) new com.simpl.android.zeroClickSdk.internal.a(r3.getApplicationContext()).a().get(0));	 Catch:{ Exception -> 0x0223 }
            r1.put(r2, r3);	 Catch:{ Exception -> 0x0223 }
        L_0x0032:
            r1 = java.lang.String.valueOf(r1);	 Catch:{ Exception -> 0x0223 }
            r2 = new java.net.URL;	 Catch:{ Exception -> 0x0223 }
            r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0223 }
            r3.<init>();	 Catch:{ Exception -> 0x0223 }
            r4 = com.simpl.android.zeroClickSdk.internal.i.a();	 Catch:{ Exception -> 0x0223 }
            r4 = (com.simpl.android.zeroClickSdk.internal.i) r4;	 Catch:{ Exception -> 0x0223 }
            r5 = r4.b;	 Catch:{ Exception -> 0x0223 }
            if (r5 == 0) goto L_0x004a;
        L_0x0047:
            r4 = "https://staging-subscriptions-api.getsimpl.com/api/v3/";
            goto L_0x0053;
        L_0x004a:
            r4 = r4.a;	 Catch:{ Exception -> 0x0223 }
            if (r4 == 0) goto L_0x0051;
        L_0x004e:
            r4 = "https://sandbox-subscriptions-api.getsimpl.com/api/v3/";
            goto L_0x0053;
        L_0x0051:
            r4 = "https://subscriptions-api.getsimpl.com/api/v3/";
        L_0x0053:
            r3.append(r4);	 Catch:{ Exception -> 0x0223 }
            r4 = "zero_click_tokens/initiate";
            r3.append(r4);	 Catch:{ Exception -> 0x0223 }
            r3 = r3.toString();	 Catch:{ Exception -> 0x0223 }
            r2.<init>(r3);	 Catch:{ Exception -> 0x0223 }
            r2 = r2.openConnection();	 Catch:{ Exception -> 0x0223 }
            r2 = (javax.net.ssl.HttpsURLConnection) r2;	 Catch:{ Exception -> 0x0223 }
            r0 = "client-id";
            r3 = r6.b;	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r2.setRequestProperty(r0, r3);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r0 = "Content-Type";
            r3 = "application/json";
            r2.setRequestProperty(r0, r3);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r0 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
            r2.setReadTimeout(r0);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r2.setConnectTimeout(r0);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r0 = "Content-length";
            r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3.<init>();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r4 = r1.getBytes();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r4 = r4.length;	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3.append(r4);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3 = r3.toString();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r2.setRequestProperty(r0, r3);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r0 = 1;
            r2.setDoInput(r0);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r2.setDoOutput(r0);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r0 = 0;
            r2.setUseCaches(r0);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3 = r2.getOutputStream();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r4 = "UTF-8";
            r1 = r1.getBytes(r4);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3.write(r1);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3.close();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r1 = r2.getResponseCode();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
            if (r1 != r3) goto L_0x0115;
        L_0x00b7:
            r0 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r1 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3 = r2.getInputStream();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r1.<init>(r3);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r0.<init>(r1);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r1.<init>();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
        L_0x00ca:
            r3 = r0.readLine();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            if (r3 == 0) goto L_0x00d4;
        L_0x00d0:
            r1.append(r3);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            goto L_0x00ca;
        L_0x00d4:
            r0.close();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r0 = new org.json.JSONObject;	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r1 = r1.toString();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r0.<init>(r1);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r1 = "success";
            r1 = r0.optBoolean(r1);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            if (r1 == 0) goto L_0x0103;
        L_0x00e8:
            r1 = new com.simpl.android.zeroClickSdk.SimplZeroClickTokenAuthorization;	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r1.<init>();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3 = "data";
            r0 = r0.optJSONObject(r3);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3 = "verification_url";
            r0 = r0.optString(r3);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r1.setZeroClickVerificationUrl(r0);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r0 = com.simpl.android.zeroClickSdk.internal.h.this;	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            com.simpl.android.zeroClickSdk.internal.h.a(new com.simpl.android.zeroClickSdk.internal.h.AnonymousClass2(r1));	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            goto L_0x0215;
        L_0x0103:
            r1 = com.simpl.android.zeroClickSdk.internal.h.this;	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3 = new java.lang.Throwable;	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r4 = "error_code";
            r0 = r0.optString(r4);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3.<init>(r0);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            com.simpl.android.zeroClickSdk.internal.h.a(new com.simpl.android.zeroClickSdk.internal.h.AnonymousClass3(r3));	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            goto L_0x0215;
        L_0x0115:
            r1 = r2.getResponseCode();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
            if (r1 < r3) goto L_0x01a6;
        L_0x011d:
            r1 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r4 = r2.getErrorStream();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3.<init>(r4);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r1.<init>(r3);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3.<init>();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
        L_0x0130:
            r4 = r1.readLine();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            if (r4 == 0) goto L_0x013a;
        L_0x0136:
            r3.append(r4);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            goto L_0x0130;
        L_0x013a:
            r1.close();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r1 = new org.json.JSONObject;	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3 = r3.toString();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r1.<init>(r3);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3.<init>();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r4 = "errors";
            r4 = r1.getJSONArray(r4);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
        L_0x0151:
            r5 = r4.length();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            if (r0 >= r5) goto L_0x0166;
        L_0x0157:
            r5 = r4.getString(r0);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3.append(r5);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r5 = "\n";
            r3.append(r5);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r0 = r0 + 1;
            goto L_0x0151;
        L_0x0166:
            r0 = com.simpl.android.zeroClickSdk.internal.h.a;	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r5 = "Error while init zeroclick user => ";
            r4.<init>(r5);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r5 = r3.toString();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r4.append(r5);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r4 = r4.toString();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            android.util.Log.e(r0, r4);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r0 = com.simpl.android.zeroClickSdk.internal.h.this;	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r4 = new java.lang.Throwable;	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r5.<init>();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3 = r3.toString();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r5.append(r3);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3 = "\n";
            r5.append(r3);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3 = "error_code";
            r1 = r1.getString(r3);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r5.append(r1);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r1 = r5.toString();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r4.<init>(r1);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
        L_0x01a2:
            com.simpl.android.zeroClickSdk.internal.h.a(new com.simpl.android.zeroClickSdk.internal.h.AnonymousClass3(r4));	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            goto L_0x0215;
        L_0x01a6:
            r1 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r4 = r2.getInputStream();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3.<init>(r4);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r1.<init>(r3);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3.<init>();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
        L_0x01b9:
            r4 = r1.readLine();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            if (r4 == 0) goto L_0x01c3;
        L_0x01bf:
            r3.append(r4);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            goto L_0x01b9;
        L_0x01c3:
            r1.close();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r1 = new org.json.JSONObject;	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3 = r3.toString();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r1.<init>(r3);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3.<init>();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r4 = "errors";
            r4 = r1.getJSONArray(r4);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
        L_0x01da:
            r5 = r4.length();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            if (r0 >= r5) goto L_0x01ef;
        L_0x01e0:
            r5 = r4.getString(r0);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3.append(r5);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r5 = "\n";
            r3.append(r5);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r0 = r0 + 1;
            goto L_0x01da;
        L_0x01ef:
            r0 = com.simpl.android.zeroClickSdk.internal.h.this;	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r4 = new java.lang.Throwable;	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r5.<init>();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3 = r3.toString();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r5.append(r3);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3 = "\n";
            r5.append(r3);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r3 = "error_message";
            r1 = r1.getString(r3);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r5.append(r1);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r1 = r5.toString();	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            r4.<init>(r1);	 Catch:{ Exception -> 0x021d, all -> 0x021b }
            goto L_0x01a2;
        L_0x0215:
            if (r2 == 0) goto L_0x023c;
        L_0x0217:
            r2.disconnect();
            return;
        L_0x021b:
            r1 = move-exception;
            goto L_0x023d;
        L_0x021d:
            r1 = move-exception;
            r0 = r2;
            goto L_0x0224;
        L_0x0220:
            r1 = move-exception;
            r2 = r0;
            goto L_0x023d;
        L_0x0223:
            r1 = move-exception;
        L_0x0224:
            r2 = com.simpl.android.zeroClickSdk.internal.h.a;	 Catch:{ all -> 0x0220 }
            r3 = "Failed while making request";
            android.util.Log.e(r2, r3, r1);	 Catch:{ all -> 0x0220 }
            r2 = com.simpl.android.fingerprint.commons.exception.ExceptionNotifier.getSharedInstance();	 Catch:{ all -> 0x0220 }
            r2.send(r1);	 Catch:{ all -> 0x0220 }
            r2 = com.simpl.android.zeroClickSdk.internal.h.this;	 Catch:{ all -> 0x0220 }
            com.simpl.android.zeroClickSdk.internal.h.a(new com.simpl.android.zeroClickSdk.internal.h.AnonymousClass3(r1));	 Catch:{ all -> 0x0220 }
            if (r0 == 0) goto L_0x023c;
        L_0x0239:
            r0.disconnect();
        L_0x023c:
            return;
        L_0x023d:
            if (r2 == 0) goto L_0x0242;
        L_0x023f:
            r2.disconnect();
        L_0x0242:
            throw r1;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.simpl.android.zeroClickSdk.internal.h$AnonymousClass1.run():void");
        }
    }

    public h(WeakReference<Context> weakReference, SimplZeroClickTokenListener simplZeroClickTokenListener) {
        this.b = weakReference;
        this.c = simplZeroClickTokenListener;
    }

    private static void a(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }
}
