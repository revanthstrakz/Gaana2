package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

final class zzer extends zzb {
    private final /* synthetic */ String zzads;
    private final /* synthetic */ zzea zzadv;
    private final /* synthetic */ zza zzaea;

    zzer(zzea zzea, String str, zza zza) {
        this.zzadv = zzea;
        this.zzads = str;
        this.zzaea = zza;
        super(zzea);
    }

    /* Access modifiers changed, original: final */
    public final void zzgd() throws RemoteException {
        this.zzadv.zzadr.getMaxUserProperties(this.zzads, this.zzaea);
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzge() {
        this.zzaea.zzb(null);
    }
}
