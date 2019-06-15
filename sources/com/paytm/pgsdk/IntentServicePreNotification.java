package com.paytm.pgsdk;

import android.app.IntentService;
import android.content.Intent;

public class IntentServicePreNotification extends IntentService {
    public IntentServicePreNotification() {
        super("IntentServicePreNotification");
    }

    /* Access modifiers changed, original: protected */
    public void onHandleIntent(Intent intent) {
        if (intent != null) {
            callHttp(intent.getExtras().getString("url"));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0042  */
    public void callHttp(java.lang.String r6) {
        /*
        r5 = this;
        r0 = 0;
        r1 = new java.net.URL;	 Catch:{ Exception -> 0x0032, all -> 0x002d }
        r1.<init>(r6);	 Catch:{ Exception -> 0x0032, all -> 0x002d }
        r6 = r1.openConnection();	 Catch:{ Exception -> 0x0032, all -> 0x002d }
        r6 = (java.net.HttpURLConnection) r6;	 Catch:{ Exception -> 0x0032, all -> 0x002d }
        r0 = r6.getInputStream();	 Catch:{ Exception -> 0x002b }
        r1 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x002b }
        r1.<init>(r0);	 Catch:{ Exception -> 0x002b }
        r0 = r1.read();	 Catch:{ Exception -> 0x002b }
    L_0x0019:
        r2 = -1;
        if (r0 == r2) goto L_0x0028;
    L_0x001c:
        r0 = (char) r0;	 Catch:{ Exception -> 0x002b }
        r2 = r1.read();	 Catch:{ Exception -> 0x002b }
        r3 = java.lang.System.out;	 Catch:{ Exception -> 0x002b }
        r3.print(r0);	 Catch:{ Exception -> 0x002b }
        r0 = r2;
        goto L_0x0019;
    L_0x0028:
        if (r6 == 0) goto L_0x003e;
    L_0x002a:
        goto L_0x003b;
    L_0x002b:
        r0 = move-exception;
        goto L_0x0036;
    L_0x002d:
        r6 = move-exception;
        r4 = r0;
        r0 = r6;
        r6 = r4;
        goto L_0x0040;
    L_0x0032:
        r6 = move-exception;
        r4 = r0;
        r0 = r6;
        r6 = r4;
    L_0x0036:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);	 Catch:{ all -> 0x003f }
        if (r6 == 0) goto L_0x003e;
    L_0x003b:
        r6.disconnect();
    L_0x003e:
        return;
    L_0x003f:
        r0 = move-exception;
    L_0x0040:
        if (r6 == 0) goto L_0x0045;
    L_0x0042:
        r6.disconnect();
    L_0x0045:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.paytm.pgsdk.IntentServicePreNotification.callHttp(java.lang.String):void");
    }
}
