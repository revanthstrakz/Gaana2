package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;

final class zzew extends zzb {
    private final /* synthetic */ zzea zzadv;
    private final /* synthetic */ Bundle zzaem;

    zzew(zzea zzea, Bundle bundle) {
        this.zzadv = zzea;
        this.zzaem = bundle;
        super(zzea);
    }

    /* Access modifiers changed, original: final */
    public final void zzgd() throws RemoteException {
        this.zzadv.zzadr.setConditionalUserProperty(this.zzaem, this.timestamp);
    }
}
