package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzuw.zza;
import java.io.IOException;

public final class zzvm extends zzbut<zzvm> {
    public String zzcgx;
    private zza[] zzcgy;
    private zzvc zzcgz;
    private zzvc zzcha;
    private zzvc zzchb;

    public zzvm() {
        this.zzcgx = null;
        this.zzcgy = new zza[0];
        this.zzcgz = null;
        this.zzcha = null;
        this.zzchb = null;
        this.zzfwk = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        if (this.zzcgx != null) {
            zzbur.zzf(1, this.zzcgx);
        }
        if (this.zzcgy != null && this.zzcgy.length > 0) {
            for (zzbsl zzbsl : this.zzcgy) {
                if (zzbsl != null) {
                    zzbur.zze(2, zzbsl);
                }
            }
        }
        if (!(this.zzcgz == null || this.zzcgz == null)) {
            zzbur.zzv(3, this.zzcgz.zzom());
        }
        if (!(this.zzcha == null || this.zzcha == null)) {
            zzbur.zzv(4, this.zzcha.zzom());
        }
        if (!(this.zzchb == null || this.zzchb == null)) {
            zzbur.zzv(5, this.zzchb.zzom());
        }
        super.zza(zzbur);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int zzt = super.zzt();
        if (this.zzcgx != null) {
            zzt += zzbur.zzg(1, this.zzcgx);
        }
        if (this.zzcgy != null && this.zzcgy.length > 0) {
            for (zzbsl zzbsl : this.zzcgy) {
                if (zzbsl != null) {
                    zzt += zzbqk.zzc(2, zzbsl);
                }
            }
        }
        if (!(this.zzcgz == null || this.zzcgz == null)) {
            zzt += zzbur.zzz(3, this.zzcgz.zzom());
        }
        if (!(this.zzcha == null || this.zzcha == null)) {
            zzt += zzbur.zzz(4, this.zzcha.zzom());
        }
        return (this.zzchb == null || this.zzchb == null) ? zzt : zzt + zzbur.zzz(5, this.zzchb.zzom());
    }
}
