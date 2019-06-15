package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbj extends zzbut<zzbj> {
    public String zzdq;
    private String zzdr;
    private String zzds;
    private String zzdt;
    private String zzdu;
    public String zzdv;

    public final void zza(zzbur zzbur) throws IOException {
        if (this.zzdq != null) {
            zzbur.zzf(1, this.zzdq);
        }
        if (this.zzdr != null) {
            zzbur.zzf(2, this.zzdr);
        }
        if (this.zzds != null) {
            zzbur.zzf(3, this.zzds);
        }
        if (this.zzdt != null) {
            zzbur.zzf(4, this.zzdt);
        }
        if (this.zzdu != null) {
            zzbur.zzf(5, this.zzdu);
        }
        if (this.zzdv != null) {
            zzbur.zzf(6, this.zzdv);
        }
        super.zza(zzbur);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int zzt = super.zzt();
        if (this.zzdq != null) {
            zzt += zzbur.zzg(1, this.zzdq);
        }
        if (this.zzdr != null) {
            zzt += zzbur.zzg(2, this.zzdr);
        }
        if (this.zzds != null) {
            zzt += zzbur.zzg(3, this.zzds);
        }
        if (this.zzdt != null) {
            zzt += zzbur.zzg(4, this.zzdt);
        }
        if (this.zzdu != null) {
            zzt += zzbur.zzg(5, this.zzdu);
        }
        return this.zzdv != null ? zzt + zzbur.zzg(6, this.zzdv) : zzt;
    }
}
