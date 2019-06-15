package com.google.android.gms.internal.vision;

import java.io.IOException;

public final class zzdt extends zzjn<zzdt> {
    private static volatile zzdt[] zzpx;
    public zzdk zzpy;
    public Integer zzpz;
    public zzdo zzqa;
    private zzdj zzqb;

    public static zzdt[] zzcd() {
        if (zzpx == null) {
            synchronized (zzjr.zzado) {
                if (zzpx == null) {
                    zzpx = new zzdt[0];
                }
            }
        }
        return zzpx;
    }

    public zzdt() {
        this.zzpy = null;
        this.zzpz = null;
        this.zzqa = null;
        this.zzqb = null;
        this.zzadp = -1;
    }

    public final void zza(zzjl zzjl) throws IOException {
        if (this.zzpy != null) {
            zzjl.zza(1, this.zzpy);
        }
        if (this.zzpz != null) {
            zzjl.zze(2, this.zzpz.intValue());
        }
        if (this.zzqa != null) {
            zzjl.zza(16, this.zzqa);
        }
        if (this.zzqb != null) {
            zzjl.zza(17, this.zzqb);
        }
        super.zza(zzjl);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int zzt = super.zzt();
        if (this.zzpy != null) {
            zzt += zzjl.zzb(1, this.zzpy);
        }
        if (this.zzpz != null) {
            zzt += zzjl.zzi(2, this.zzpz.intValue());
        }
        if (this.zzqa != null) {
            zzt += zzjl.zzb(16, this.zzqa);
        }
        return this.zzqb != null ? zzt + zzjl.zzb(17, this.zzqb) : zzt;
    }
}
