package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.measurement.internal.zzcx;

final class zzei extends zzb {
    private final /* synthetic */ zzea zzadv;
    private final /* synthetic */ zzcx zzaef;

    zzei(zzea zzea, zzcx zzcx) {
        this.zzadv = zzea;
        this.zzaef = zzcx;
        super(zzea);
    }

    /* Access modifiers changed, original: final */
    public final void zzgd() throws RemoteException {
        this.zzadv.zzadr.setEventInterceptor(new zzc(this.zzaef));
    }
}
