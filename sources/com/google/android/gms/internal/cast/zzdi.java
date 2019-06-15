package com.google.android.gms.internal.cast;

final class zzdi implements Runnable {
    private final /* synthetic */ zzdd zzyy;
    private final /* synthetic */ zzct zzzb;

    zzdi(zzdf zzdf, zzdd zzdd, zzct zzct) {
        this.zzyy = zzdd;
        this.zzzb = zzct;
    }

    public final void run() {
        this.zzyy.zza(this.zzzb);
    }
}
