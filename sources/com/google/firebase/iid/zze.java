package com.google.firebase.iid;

import android.content.Intent;
import android.util.Log;

final /* synthetic */ class zze implements Runnable {
    private final zzd zzs;
    private final Intent zzt;

    zze(zzd zzd, Intent intent) {
        this.zzs = zzd;
        this.zzt = intent;
    }

    public final void run() {
        zzd zzd = this.zzs;
        String action = this.zzt.getAction();
        StringBuilder stringBuilder = new StringBuilder(61 + String.valueOf(action).length());
        stringBuilder.append("Service took too long to process intent: ");
        stringBuilder.append(action);
        stringBuilder.append(" App may get closed.");
        Log.w("EnhancedIntentService", stringBuilder.toString());
        zzd.finish();
    }
}
