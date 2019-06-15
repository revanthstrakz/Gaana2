package com.google.android.gms.cast;

final class zzv implements Runnable {
    private final /* synthetic */ CastRemoteDisplayLocalService zzci;

    zzv(CastRemoteDisplayLocalService castRemoteDisplayLocalService) {
        this.zzci = castRemoteDisplayLocalService;
    }

    public final void run() {
        CastRemoteDisplayLocalService castRemoteDisplayLocalService = this.zzci;
        boolean zzb = this.zzci.zzcd;
        StringBuilder stringBuilder = new StringBuilder(59);
        stringBuilder.append("onCreate after delay. The local service been started: ");
        stringBuilder.append(zzb);
        castRemoteDisplayLocalService.zzb(stringBuilder.toString());
        if (!this.zzci.zzcd) {
            this.zzci.zzc("The local service has not been been started, stopping it");
            this.zzci.stopSelf();
        }
    }
}
