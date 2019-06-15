package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbo extends zzbut<zzbo> {
    private Long zzhh;
    private Integer zzhi;
    private Boolean zzhj;
    private int[] zzhk;
    private Long zzhl;

    public zzbo() {
        this.zzhh = null;
        this.zzhi = null;
        this.zzhj = null;
        this.zzhk = zzbvc.zzfsg;
        this.zzhl = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        if (this.zzhh != null) {
            zzbur.zzr(1, this.zzhh.longValue());
        }
        if (this.zzhi != null) {
            zzbur.zzv(2, this.zzhi.intValue());
        }
        if (this.zzhj != null) {
            zzbur.zzj(3, this.zzhj.booleanValue());
        }
        if (this.zzhk != null && this.zzhk.length > 0) {
            for (int zzv : this.zzhk) {
                zzbur.zzv(4, zzv);
            }
        }
        if (this.zzhl != null) {
            zzbur.zzj(5, this.zzhl.longValue());
        }
        super.zza(zzbur);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int zzt = super.zzt();
        if (this.zzhh != null) {
            zzt += zzbur.zzm(1, this.zzhh.longValue());
        }
        if (this.zzhi != null) {
            zzt += zzbur.zzz(2, this.zzhi.intValue());
        }
        if (this.zzhj != null) {
            this.zzhj.booleanValue();
            zzt += zzbur.zzfd(3) + 1;
        }
        if (this.zzhk != null && this.zzhk.length > 0) {
            int i = 0;
            int i2 = 0;
            while (i < this.zzhk.length) {
                i2 += zzbur.zzfe(this.zzhk[i]);
                i++;
            }
            zzt = (zzt + i2) + (1 * this.zzhk.length);
        }
        return this.zzhl != null ? zzt + zzbur.zzn(5, this.zzhl.longValue()) : zzt;
    }
}
