package com.google.android.gms.internal.cast;

final class zzdh implements Runnable {
    private final /* synthetic */ zzdd zzyy;
    private final /* synthetic */ zzdl zzza;

    zzdh(zzdf zzdf, zzdd zzdd, zzdl zzdl) {
        this.zzyy = zzdd;
        this.zzza = zzdl;
    }

    public final void run() {
        this.zzyy.zza(this.zzza);
    }
}
