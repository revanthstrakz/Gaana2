package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzuw.zzh;
import java.io.IOException;

public final class zzvp extends zzbut<zzvp> {
    private Integer zzcho;
    public String zzchp;
    private Integer zzchq;
    private zzvc zzchr;
    private zzvq zzchs;
    public long[] zzcht;
    public zzvn zzchu;
    private zzvo zzchv;
    private zzh zzchw;
    public zzvl zzchx;

    public zzvp() {
        this.zzcho = null;
        this.zzchp = null;
        this.zzchq = null;
        this.zzchr = null;
        this.zzchs = null;
        this.zzcht = zzbvc.zzfwy;
        this.zzchu = null;
        this.zzchv = null;
        this.zzchw = null;
        this.zzchx = null;
        this.zzfwk = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        if (this.zzcho != null) {
            zzbur.zzv(9, this.zzcho.intValue());
        }
        if (this.zzchp != null) {
            zzbur.zzf(10, this.zzchp);
        }
        int i = 0;
        if (this.zzchq != null) {
            int intValue = this.zzchq.intValue();
            zzbur.zzu(11, 0);
            zzbur.zzge(intValue);
        }
        if (!(this.zzchr == null || this.zzchr == null)) {
            zzbur.zzv(12, this.zzchr.zzom());
        }
        if (this.zzchs != null) {
            zzbur.zza(13, this.zzchs);
        }
        if (this.zzcht != null && this.zzcht.length > 0) {
            while (i < this.zzcht.length) {
                zzbur.zzj(14, this.zzcht[i]);
                i++;
            }
        }
        if (this.zzchu != null) {
            zzbur.zza(15, this.zzchu);
        }
        if (this.zzchv != null) {
            zzbur.zza(16, this.zzchv);
        }
        if (this.zzchw != null) {
            zzbur.zze(17, this.zzchw);
        }
        if (this.zzchx != null) {
            zzbur.zza(18, this.zzchx);
        }
        super.zza(zzbur);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int zzt = super.zzt();
        if (this.zzcho != null) {
            zzt += zzbur.zzz(9, this.zzcho.intValue());
        }
        if (this.zzchp != null) {
            zzt += zzbur.zzg(10, this.zzchp);
        }
        if (this.zzchq != null) {
            zzt += zzbur.zzfd(11) + zzbur.zzfl(this.zzchq.intValue());
        }
        if (!(this.zzchr == null || this.zzchr == null)) {
            zzt += zzbur.zzz(12, this.zzchr.zzom());
        }
        if (this.zzchs != null) {
            zzt += zzbur.zzb(13, this.zzchs);
        }
        if (this.zzcht != null && this.zzcht.length > 0) {
            int i = 0;
            int i2 = 0;
            while (i < this.zzcht.length) {
                i2 += zzbur.zzbl(this.zzcht[i]);
                i++;
            }
            zzt = (zzt + i2) + (1 * this.zzcht.length);
        }
        if (this.zzchu != null) {
            zzt += zzbur.zzb(15, this.zzchu);
        }
        if (this.zzchv != null) {
            zzt += zzbur.zzb(16, this.zzchv);
        }
        if (this.zzchw != null) {
            zzt += zzbqk.zzc(17, this.zzchw);
        }
        return this.zzchx != null ? zzt + zzbur.zzb(18, this.zzchx) : zzt;
    }
}
