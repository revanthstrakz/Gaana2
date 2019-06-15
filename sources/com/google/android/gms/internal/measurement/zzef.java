package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

final class zzef extends zzb {
    private final /* synthetic */ zzea zzadv;
    private final /* synthetic */ boolean zzaed;

    zzef(zzea zzea, boolean z) {
        this.zzadv = zzea;
        this.zzaed = z;
        super(zzea);
    }

    /* Access modifiers changed, original: final */
    public final void zzgd() throws RemoteException {
        this.zzadv.zzadr.setMeasurementEnabled(this.zzaed, this.timestamp);
    }
}
