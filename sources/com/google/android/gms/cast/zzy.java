package com.google.android.gms.cast;

import com.google.android.gms.cast.CastRemoteDisplayLocalService.NotificationSettings;

final class zzy implements Runnable {
    private final /* synthetic */ CastRemoteDisplayLocalService zzci;
    private final /* synthetic */ NotificationSettings zzcl;

    zzy(CastRemoteDisplayLocalService castRemoteDisplayLocalService, NotificationSettings notificationSettings) {
        this.zzci = castRemoteDisplayLocalService;
        this.zzcl = notificationSettings;
    }

    public final void run() {
        this.zzci.zza(this.zzcl);
    }
}
