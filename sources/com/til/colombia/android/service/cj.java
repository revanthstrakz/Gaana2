package com.til.colombia.android.service;

import org.json.JSONObject;

final class cj implements Runnable {
    final /* synthetic */ JSONObject a;
    final /* synthetic */ LeadGenActivity b;

    cj(LeadGenActivity leadGenActivity, JSONObject jSONObject) {
        this.b = leadGenActivity;
        this.a = jSONObject;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0091  */
    public final void run() {
        /*
        r6 = this;
        r0 = 0;
        r1 = new java.lang.String;	 Catch:{ Exception -> 0x007c, all -> 0x0077 }
        r2 = r6.b;	 Catch:{ Exception -> 0x007c, all -> 0x0077 }
        r2 = r2.lparser;	 Catch:{ Exception -> 0x007c, all -> 0x0077 }
        r2 = r2.c;	 Catch:{ Exception -> 0x007c, all -> 0x0077 }
        r1.<init>(r2);	 Catch:{ Exception -> 0x007c, all -> 0x0077 }
        r2 = new java.util.ArrayList;	 Catch:{ Exception -> 0x007c, all -> 0x0077 }
        r3 = r6.b;	 Catch:{ Exception -> 0x007c, all -> 0x0077 }
        r3 = r3.lparser;	 Catch:{ Exception -> 0x007c, all -> 0x0077 }
        r3 = r3.d;	 Catch:{ Exception -> 0x007c, all -> 0x0077 }
        r2.<init>(r3);	 Catch:{ Exception -> 0x007c, all -> 0x0077 }
        r3 = new java.net.URL;	 Catch:{ Exception -> 0x007c, all -> 0x0077 }
        r3.<init>(r1);	 Catch:{ Exception -> 0x007c, all -> 0x0077 }
        r1 = r3.openConnection();	 Catch:{ Exception -> 0x007c, all -> 0x0077 }
        r1 = (java.net.HttpURLConnection) r1;	 Catch:{ Exception -> 0x007c, all -> 0x0077 }
        r0 = 1;
        r1.setDoOutput(r0);	 Catch:{ Exception -> 0x0075 }
        r0 = "Content-Type";
        r3 = "application/json; charset=UTF-8";
        r1.setRequestProperty(r0, r3);	 Catch:{ Exception -> 0x0075 }
        r0 = "POST";
        r1.setRequestMethod(r0);	 Catch:{ Exception -> 0x0075 }
        r0 = 0;
        r1.setUseCaches(r0);	 Catch:{ Exception -> 0x0075 }
        r0 = new java.io.OutputStreamWriter;	 Catch:{ Exception -> 0x0075 }
        r3 = r1.getOutputStream();	 Catch:{ Exception -> 0x0075 }
        r0.<init>(r3);	 Catch:{ Exception -> 0x0075 }
        r3 = r6.a;	 Catch:{ Exception -> 0x0075 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x0075 }
        r0.write(r3);	 Catch:{ Exception -> 0x0075 }
        r0.flush();	 Catch:{ Exception -> 0x0075 }
        r1.connect();	 Catch:{ Exception -> 0x0075 }
        r0 = "Col:aos:4.0.0";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0075 }
        r4 = "code:";
        r3.<init>(r4);	 Catch:{ Exception -> 0x0075 }
        r4 = r1.getResponseCode();	 Catch:{ Exception -> 0x0075 }
        r3.append(r4);	 Catch:{ Exception -> 0x0075 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x0075 }
        com.til.colombia.android.internal.Log.a(r0, r3);	 Catch:{ Exception -> 0x0075 }
        r0 = 5;
        r3 = "LeadGen conversion tracked.";
        com.til.colombia.android.network.n.a(r2, r0, r3);	 Catch:{ Exception -> 0x0075 }
        if (r1 == 0) goto L_0x008d;
    L_0x0071:
        r1.disconnect();
        return;
    L_0x0075:
        r0 = move-exception;
        goto L_0x0080;
    L_0x0077:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
        goto L_0x008f;
    L_0x007c:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
    L_0x0080:
        r2 = "Col:aos:4.0.0";
        r3 = "";
        com.til.colombia.android.internal.Log.a(r2, r3, r0);	 Catch:{ all -> 0x008e }
        if (r1 == 0) goto L_0x008d;
    L_0x0089:
        r1.disconnect();
        return;
    L_0x008d:
        return;
    L_0x008e:
        r0 = move-exception;
    L_0x008f:
        if (r1 == 0) goto L_0x0094;
    L_0x0091:
        r1.disconnect();
    L_0x0094:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.service.cj.run():void");
    }
}
