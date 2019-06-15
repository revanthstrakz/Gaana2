package com.google.android.gms.cast;

final class zzx implements Runnable {
    private final /* synthetic */ CastRemoteDisplayLocalService zzci;
    private final /* synthetic */ boolean zzco;

    zzx(CastRemoteDisplayLocalService castRemoteDisplayLocalService, boolean z) {
        this.zzci = castRemoteDisplayLocalService;
        this.zzco = z;
    }

    public final void run() {
        this.zzci.zza(this.zzco);
    }
}
