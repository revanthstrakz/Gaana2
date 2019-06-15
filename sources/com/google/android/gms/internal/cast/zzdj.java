package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.Cast.MessageReceivedCallback;

final class zzdj implements Runnable {
    private final /* synthetic */ String zzae;
    private final /* synthetic */ zzdd zzyy;
    private final /* synthetic */ String zzzc;

    zzdj(zzdf zzdf, zzdd zzdd, String str, String str2) {
        this.zzyy = zzdd;
        this.zzae = str;
        this.zzzc = str2;
    }

    public final void run() {
        MessageReceivedCallback messageReceivedCallback;
        synchronized (this.zzyy.zzya) {
            messageReceivedCallback = (MessageReceivedCallback) this.zzyy.zzya.get(this.zzae);
        }
        if (messageReceivedCallback != null) {
            messageReceivedCallback.onMessageReceived(this.zzyy.zzie, this.zzae, this.zzzc);
            return;
        }
        zzdd.zzbf.d("Discarded message for unknown namespace '%s'", this.zzae);
    }
}
