package com.google.firebase.iid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import com.til.colombia.android.internal.d;

@VisibleForTesting
final class zzaz extends BroadcastReceiver {
    private zzay zzdk;

    public zzaz(zzay zzay) {
        this.zzdk = zzay;
    }

    public final void zzap() {
        if (FirebaseInstanceId.zzl()) {
            Log.d("FirebaseInstanceId", "Connectivity change received registered");
        }
        this.zzdk.getContext().registerReceiver(this, new IntentFilter(d.a));
    }

    public final void onReceive(Context context, Intent intent) {
        if (this.zzdk != null && this.zzdk.zzao()) {
            if (FirebaseInstanceId.zzl()) {
                Log.d("FirebaseInstanceId", "Connectivity changed. Starting background sync.");
            }
            FirebaseInstanceId.zza(this.zzdk, 0);
            this.zzdk.getContext().unregisterReceiver(this);
            this.zzdk = null;
        }
    }
}
