package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

final class zzel extends zzb {
    private final /* synthetic */ zzea zzadv;
    private final /* synthetic */ zza zzaea;

    zzel(zzea zzea, zza zza) {
        this.zzadv = zzea;
        this.zzaea = zza;
        super(zzea);
    }

    /* Access modifiers changed, original: final */
    public final void zzgd() throws RemoteException {
        this.zzadv.zzadr.generateEventId(this.zzaea);
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzge() {
        this.zzaea.zzb(null);
    }
}
