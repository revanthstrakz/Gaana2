package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzuw.zzd.zzb;
import java.io.IOException;

public final class zzvo extends zzbut<zzvo> {
    private zzb zzchk;
    private zzvq zzchl;
    private String zzchm;
    private String zzchn;

    public zzvo() {
        this.zzchk = null;
        this.zzchl = null;
        this.zzchm = null;
        this.zzchn = null;
        this.zzfwk = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        if (!(this.zzchk == null || this.zzchk == null)) {
            zzbur.zzv(5, this.zzchk.zzom());
        }
        if (this.zzchl != null) {
            zzbur.zza(6, this.zzchl);
        }
        if (this.zzchm != null) {
            zzbur.zzf(7, this.zzchm);
        }
        if (this.zzchn != null) {
            zzbur.zzf(8, this.zzchn);
        }
        super.zza(zzbur);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int zzt = super.zzt();
        if (!(this.zzchk == null || this.zzchk == null)) {
            zzt += zzbur.zzz(5, this.zzchk.zzom());
        }
        if (this.zzchl != null) {
            zzt += zzbur.zzb(6, this.zzchl);
        }
        if (this.zzchm != null) {
            zzt += zzbur.zzg(7, this.zzchm);
        }
        return this.zzchn != null ? zzt + zzbur.zzg(8, this.zzchn) : zzt;
    }
}
