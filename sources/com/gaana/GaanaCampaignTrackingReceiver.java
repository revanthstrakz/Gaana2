package com.gaana;

import android.content.BroadcastReceiver;

public class GaanaCampaignTrackingReceiver extends BroadcastReceiver {
    /* JADX WARNING: Removed duplicated region for block: B:22:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x003c  */
    public void onReceive(android.content.Context r5, android.content.Intent r6) {
        /*
        r4 = this;
        r0 = new com.google.android.gms.analytics.CampaignTrackingReceiver;
        r0.<init>();
        r0.onReceive(r5, r6);
        r0 = 0;
        r1 = "referrer";
        r6 = r6.getStringExtra(r1);	 Catch:{ Exception -> 0x0022 }
        if (r6 != 0) goto L_0x0019;
    L_0x0011:
        r0 = "";
        goto L_0x001a;
    L_0x0014:
        r0 = move-exception;
        r3 = r0;
        r0 = r6;
        r6 = r3;
        goto L_0x0023;
    L_0x0019:
        r0 = r6;
    L_0x001a:
        r6 = com.gaana.application.GaanaApplication.getContext();	 Catch:{ Exception -> 0x0022 }
        com.g.a.a.a(r6, r0);	 Catch:{ Exception -> 0x0022 }
        goto L_0x0026;
    L_0x0022:
        r6 = move-exception;
    L_0x0023:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r6);
    L_0x0026:
        r6 = com.services.d.a();
        r1 = "PREFERENCE_KEY_INSTALL_REFERRER";
        r2 = 0;
        r6.a(r1, r0, r2);
        r6 = android.net.Uri.decode(r0);
        r0 = "refer.gaana.com";
        r0 = r6.contains(r0);
        if (r0 == 0) goto L_0x0053;
    L_0x003c:
        r0 = "utm_content=";
        r6 = r6.split(r0);
        r0 = "";
        if (r6 == 0) goto L_0x004c;
    L_0x0046:
        r1 = 1;
        r2 = r6.length;
        if (r2 <= r1) goto L_0x004c;
    L_0x004a:
        r0 = r6[r1];
    L_0x004c:
        r6 = com.managers.d.a();
        r6.a(r5, r0);
    L_0x0053:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.GaanaCampaignTrackingReceiver.onReceive(android.content.Context, android.content.Intent):void");
    }
}
