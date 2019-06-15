package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;

final class zzeq extends zzb {
    private final /* synthetic */ zzea zzadv;
    private final /* synthetic */ zza zzaea;
    private final /* synthetic */ Bundle zzaem;

    zzeq(zzea zzea, Bundle bundle, zza zza) {
        this.zzadv = zzea;
        this.zzaem = bundle;
        this.zzaea = zza;
        super(zzea);
    }

    /* Access modifiers changed, original: final */
    public final void zzgd() throws RemoteException {
        this.zzadv.zzadr.performAction(this.zzaem, this.zzaea, this.timestamp);
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzge() {
        this.zzaea.zzb(null);
    }
}
