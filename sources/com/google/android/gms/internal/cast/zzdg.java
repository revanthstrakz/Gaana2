package com.google.android.gms.internal.cast;

final class zzdg implements Runnable {
    private final /* synthetic */ zzdd zzyy;
    private final /* synthetic */ int zzyz;

    zzdg(zzdf zzdf, zzdd zzdd, int i) {
        this.zzyy = zzdd;
        this.zzyz = i;
    }

    public final void run() {
        this.zzyy.zzak.onApplicationDisconnected(this.zzyz);
    }
}
