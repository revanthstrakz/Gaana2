package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

final class zzeg extends zzb {
    private final /* synthetic */ zzea zzadv;
    private final /* synthetic */ String zzaee;

    zzeg(zzea zzea, String str) {
        this.zzadv = zzea;
        this.zzaee = str;
        super(zzea);
    }

    /* Access modifiers changed, original: final */
    public final void zzgd() throws RemoteException {
        this.zzadv.zzadr.beginAdUnitExposure(this.zzaee, this.zzaev);
    }
}
