package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

public class js {
    public String deviceId = "";
    public String idType = "";
    public boolean isLimitedAdTracking = false;

    /* JADX WARNING: Removed duplicated region for block: B:7:0x003d A:{Catch:{ SettingNotFoundException -> 0x0041 }} */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x003b A:{Catch:{ SettingNotFoundException -> 0x0041 }} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0023 */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|(1:6)(1:7)|8|13) */
    /* JADX WARNING: Missing block: B:9:0x0041, code skipped:
            android.util.Log.w("IMASDK", "Failed to get advertising ID.");
            r3.deviceId = "";
            r3.idType = "";
            r3.isLimitedAdTracking = false;
     */
    /* JADX WARNING: Missing block: B:11:?, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:12:?, code skipped:
            return;
     */
    public js(android.content.Context r4) {
        /*
        r3 = this;
        r3.<init>();
        r0 = "";
        r3.deviceId = r0;
        r0 = "";
        r3.idType = r0;
        r0 = 0;
        r3.isLimitedAdTracking = r0;
        r1 = r3.getInfo(r4);	 Catch:{ Exception -> 0x0023 }
        r2 = r1.getId();	 Catch:{ Exception -> 0x0023 }
        r3.deviceId = r2;	 Catch:{ Exception -> 0x0023 }
        r2 = "adid";
        r3.idType = r2;	 Catch:{ Exception -> 0x0023 }
        r1 = r1.isLimitAdTrackingEnabled();	 Catch:{ Exception -> 0x0023 }
        r3.isLimitedAdTracking = r1;	 Catch:{ Exception -> 0x0023 }
        goto L_0x0052;
    L_0x0023:
        r4 = r4.getContentResolver();	 Catch:{ SettingNotFoundException -> 0x0041 }
        r1 = "advertising_id";
        r1 = android.provider.Settings.Secure.getString(r4, r1);	 Catch:{ SettingNotFoundException -> 0x0041 }
        r3.deviceId = r1;	 Catch:{ SettingNotFoundException -> 0x0041 }
        r1 = "afai";
        r3.idType = r1;	 Catch:{ SettingNotFoundException -> 0x0041 }
        r1 = "limit_ad_tracking";
        r4 = android.provider.Settings.Secure.getInt(r4, r1);	 Catch:{ SettingNotFoundException -> 0x0041 }
        if (r4 != 0) goto L_0x003d;
    L_0x003b:
        r4 = 1;
        goto L_0x003e;
    L_0x003d:
        r4 = r0;
    L_0x003e:
        r3.isLimitedAdTracking = r4;	 Catch:{ SettingNotFoundException -> 0x0041 }
        goto L_0x0052;
    L_0x0041:
        r4 = "IMASDK";
        r1 = "Failed to get advertising ID.";
        android.util.Log.w(r4, r1);
        r4 = "";
        r3.deviceId = r4;
        r4 = "";
        r3.idType = r4;
        r3.isLimitedAdTracking = r0;
    L_0x0052:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.js.<init>(android.content.Context):void");
    }

    /* Access modifiers changed, original: protected */
    public Info getInfo(Context context) throws IOException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        return AdvertisingIdClient.getAdvertisingIdInfo(context);
    }
}
