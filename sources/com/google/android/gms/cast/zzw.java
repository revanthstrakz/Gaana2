package com.google.android.gms.cast;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import com.google.android.gms.cast.CastRemoteDisplayLocalService.Callbacks;
import com.google.android.gms.cast.CastRemoteDisplayLocalService.NotificationSettings;
import com.google.android.gms.cast.CastRemoteDisplayLocalService.Options;
import com.google.android.gms.common.api.Status;

final class zzw implements ServiceConnection {
    private final /* synthetic */ String zzag;
    private final /* synthetic */ CastDevice zzcj;
    private final /* synthetic */ Options zzck;
    private final /* synthetic */ NotificationSettings zzcl;
    private final /* synthetic */ Context zzcm;
    private final /* synthetic */ Callbacks zzcn;

    zzw(String str, CastDevice castDevice, Options options, NotificationSettings notificationSettings, Context context, Callbacks callbacks) {
        this.zzag = str;
        this.zzcj = castDevice;
        this.zzck = options;
        this.zzcl = notificationSettings;
        this.zzcm = context;
        this.zzcn = callbacks;
    }

    /* JADX WARNING: Missing block: B:3:0x0017, code skipped:
            if (r0.zza(r8.zzag, r8.zzcj, r8.zzck, r8.zzcl, r8.zzcm, r8, r8.zzcn) == false) goto L_0x0019;
     */
    public final void onServiceConnected(android.content.ComponentName r9, android.os.IBinder r10) {
        /*
        r8 = this;
        r10 = (com.google.android.gms.cast.CastRemoteDisplayLocalService.zza) r10;
        r0 = com.google.android.gms.cast.CastRemoteDisplayLocalService.this;
        if (r0 == 0) goto L_0x0019;
    L_0x0006:
        r1 = r8.zzag;
        r2 = r8.zzcj;
        r3 = r8.zzck;
        r4 = r8.zzcl;
        r5 = r8.zzcm;
        r7 = r8.zzcn;
        r6 = r8;
        r9 = r0.zza(r1, r2, r3, r4, r5, r6, r7);
        if (r9 != 0) goto L_0x0049;
    L_0x0019:
        r9 = com.google.android.gms.cast.CastRemoteDisplayLocalService.zzbf;
        r10 = "Connected but unable to get the service instance";
        r0 = 0;
        r1 = new java.lang.Object[r0];
        r9.e(r10, r1);
        r9 = r8.zzcn;
        r10 = new com.google.android.gms.common.api.Status;
        r1 = 2200; // 0x898 float:3.083E-42 double:1.087E-320;
        r10.<init>(r1);
        r9.onRemoteDisplaySessionError(r10);
        r9 = com.google.android.gms.cast.CastRemoteDisplayLocalService.zzbr;
        r9.set(r0);
        r9 = r8.zzcm;	 Catch:{ IllegalArgumentException -> 0x003e }
        r9.unbindService(r8);	 Catch:{ IllegalArgumentException -> 0x003e }
        return;
    L_0x003e:
        r9 = com.google.android.gms.cast.CastRemoteDisplayLocalService.zzbf;
        r10 = "No need to unbind service, already unbound";
        r0 = new java.lang.Object[r0];
        r9.d(r10, r0);
    L_0x0049:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.zzw.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        CastRemoteDisplayLocalService.zzbf.d("onServiceDisconnected", new Object[0]);
        this.zzcn.onRemoteDisplaySessionError(new Status(CastStatusCodes.ERROR_SERVICE_DISCONNECTED, "Service Disconnected"));
        CastRemoteDisplayLocalService.zzbr.set(false);
        try {
            this.zzcm.unbindService(this);
        } catch (IllegalArgumentException unused) {
            CastRemoteDisplayLocalService.zzbf.d("No need to unbind service, already unbound", new Object[0]);
        }
    }
}
