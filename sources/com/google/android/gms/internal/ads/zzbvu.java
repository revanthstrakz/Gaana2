package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbvu extends zzbut<zzbvu> {
    public String zzgcc;
    public Long zzgcd;
    public Boolean zzgce;

    public zzbvu() {
        this.zzgcc = null;
        this.zzgcd = null;
        this.zzgce = null;
        this.zzfwk = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        if (this.zzgcc != null) {
            zzbur.zzf(1, this.zzgcc);
        }
        if (this.zzgcd != null) {
            zzbur.zzr(2, this.zzgcd.longValue());
        }
        if (this.zzgce != null) {
            zzbur.zzj(3, this.zzgce.booleanValue());
        }
        super.zza(zzbur);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int zzt = super.zzt();
        if (this.zzgcc != null) {
            zzt += zzbur.zzg(1, this.zzgcc);
        }
        if (this.zzgcd != null) {
            zzt += zzbur.zzm(2, this.zzgcd.longValue());
        }
        if (this.zzgce == null) {
            return zzt;
        }
        this.zzgce.booleanValue();
        return zzt + (zzbur.zzfd(3) + 1);
    }
}
