package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.Cast.MessageReceivedCallback;
import com.google.android.gms.cast.CastDevice;

final class zzcd implements MessageReceivedCallback {
    private final /* synthetic */ zzcc zzwj;

    zzcd(zzcc zzcc) {
        this.zzwj = zzcc;
    }

    public final void onMessageReceived(CastDevice castDevice, String str, String str2) {
        this.zzwj.zzwi.zzo(str2);
    }
}
