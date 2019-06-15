package com.google.android.gms.internal.vision;

import java.io.IOException;

public final class zzdr extends zzjn<zzdr> {
    public String name;
    public String zzon;
    public Long zzpq;
    public zzdh zzpr;
    public zzdn zzps;
    private zzdi zzpt;

    public zzdr() {
        this.name = null;
        this.zzpq = null;
        this.zzpr = null;
        this.zzon = null;
        this.zzps = null;
        this.zzpt = null;
        this.zzadp = -1;
    }

    public final void zza(zzjl zzjl) throws IOException {
        if (this.name != null) {
            zzjl.zza(1, this.name);
        }
        if (this.zzpq != null) {
            zzjl.zzi(2, this.zzpq.longValue());
        }
        if (this.zzpr != null) {
            zzjl.zza(3, this.zzpr);
        }
        if (this.zzon != null) {
            zzjl.zza(6, this.zzon);
        }
        if (this.zzps != null) {
            zzjl.zza(16, this.zzps);
        }
        if (this.zzpt != null) {
            zzjl.zza(17, this.zzpt);
        }
        super.zza(zzjl);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int zzt = super.zzt();
        if (this.name != null) {
            zzt += zzjl.zzb(1, this.name);
        }
        if (this.zzpq != null) {
            zzt += zzjl.zzd(2, this.zzpq.longValue());
        }
        if (this.zzpr != null) {
            zzt += zzjl.zzb(3, this.zzpr);
        }
        if (this.zzon != null) {
            zzt += zzjl.zzb(6, this.zzon);
        }
        if (this.zzps != null) {
            zzt += zzjl.zzb(16, this.zzps);
        }
        return this.zzpt != null ? zzt + zzjl.zzb(17, this.zzpt) : zzt;
    }
}
