package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbh extends zzbut<zzbh> {
    private String stackTrace;
    public String zzdh;
    public Long zzdi;
    private String zzdj;
    private String zzdk;
    private Long zzdl;
    private Long zzdm;
    private String zzdn;
    private Long zzdo;
    private String zzdp;

    public zzbh() {
        this.zzdh = null;
        this.zzdi = null;
        this.stackTrace = null;
        this.zzdj = null;
        this.zzdk = null;
        this.zzdl = null;
        this.zzdm = null;
        this.zzdn = null;
        this.zzdo = null;
        this.zzdp = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        if (this.zzdh != null) {
            zzbur.zzf(1, this.zzdh);
        }
        if (this.zzdi != null) {
            zzbur.zzr(2, this.zzdi.longValue());
        }
        if (this.stackTrace != null) {
            zzbur.zzf(3, this.stackTrace);
        }
        if (this.zzdj != null) {
            zzbur.zzf(4, this.zzdj);
        }
        if (this.zzdk != null) {
            zzbur.zzf(5, this.zzdk);
        }
        if (this.zzdl != null) {
            zzbur.zzr(6, this.zzdl.longValue());
        }
        if (this.zzdm != null) {
            zzbur.zzr(7, this.zzdm.longValue());
        }
        if (this.zzdn != null) {
            zzbur.zzf(8, this.zzdn);
        }
        if (this.zzdo != null) {
            zzbur.zzr(9, this.zzdo.longValue());
        }
        if (this.zzdp != null) {
            zzbur.zzf(10, this.zzdp);
        }
        super.zza(zzbur);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int zzt = super.zzt();
        if (this.zzdh != null) {
            zzt += zzbur.zzg(1, this.zzdh);
        }
        if (this.zzdi != null) {
            zzt += zzbur.zzm(2, this.zzdi.longValue());
        }
        if (this.stackTrace != null) {
            zzt += zzbur.zzg(3, this.stackTrace);
        }
        if (this.zzdj != null) {
            zzt += zzbur.zzg(4, this.zzdj);
        }
        if (this.zzdk != null) {
            zzt += zzbur.zzg(5, this.zzdk);
        }
        if (this.zzdl != null) {
            zzt += zzbur.zzm(6, this.zzdl.longValue());
        }
        if (this.zzdm != null) {
            zzt += zzbur.zzm(7, this.zzdm.longValue());
        }
        if (this.zzdn != null) {
            zzt += zzbur.zzg(8, this.zzdn);
        }
        if (this.zzdo != null) {
            zzt += zzbur.zzm(9, this.zzdo.longValue());
        }
        return this.zzdp != null ? zzt + zzbur.zzg(10, this.zzdp) : zzt;
    }
}
