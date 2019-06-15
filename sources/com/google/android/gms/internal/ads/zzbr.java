package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbr extends zzbut<zzbr> {
    public String zzdv;

    public zzbr() {
        this.zzdv = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        if (this.zzdv != null) {
            zzbur.zzf(1, this.zzdv);
        }
        super.zza(zzbur);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int zzt = super.zzt();
        return this.zzdv != null ? zzt + zzbur.zzg(1, this.zzdv) : zzt;
    }
}
