package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

final class zzeo extends zzb {
    private final /* synthetic */ String zzads;
    private final /* synthetic */ zzea zzadv;
    private final /* synthetic */ String zzadz;
    private final /* synthetic */ zza zzaea;
    private final /* synthetic */ boolean zzaeg;

    zzeo(zzea zzea, String str, String str2, boolean z, zza zza) {
        this.zzadv = zzea;
        this.zzads = str;
        this.zzadz = str2;
        this.zzaeg = z;
        this.zzaea = zza;
        super(zzea);
    }

    /* Access modifiers changed, original: final */
    public final void zzgd() throws RemoteException {
        this.zzadv.zzadr.getUserProperties(this.zzads, this.zzadz, this.zzaeg, this.zzaea);
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzge() {
        this.zzaea.zzb(null);
    }
}
