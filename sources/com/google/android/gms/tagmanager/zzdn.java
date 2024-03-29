package com.google.android.gms.tagmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.util.VisibleForTesting;
import com.til.colombia.android.internal.d;

class zzdn extends BroadcastReceiver {
    @VisibleForTesting
    private static final String zzabi = "com.google.android.gms.tagmanager.zzdn";
    private final zzfm zzbdn;

    zzdn(zzfm zzfm) {
        this.zzbdn = zzfm;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (d.a.equals(action)) {
            Bundle extras = intent.getExtras();
            Boolean bool = Boolean.FALSE;
            if (extras != null) {
                bool = Boolean.valueOf(intent.getExtras().getBoolean("noConnectivity"));
            }
            this.zzbdn.zzp(bool.booleanValue() ^ 1);
            return;
        }
        if ("com.google.analytics.RADIO_POWERED".equals(action) && !intent.hasExtra(zzabi)) {
            this.zzbdn.zzqd();
        }
    }

    public static void zzw(Context context) {
        Intent intent = new Intent("com.google.analytics.RADIO_POWERED");
        intent.addCategory(context.getPackageName());
        intent.putExtra(zzabi, true);
        context.sendBroadcast(intent);
    }
}
