package com.google.android.gms.internal.vision;

import java.io.IOException;

public final class zzdu extends zzjn<zzdu> {
    private zzdl zzqc;
    public zzdr zzqd;
    public zzdp zzqe;
    private Integer zzqf;

    public zzdu() {
        this.zzqc = null;
        this.zzqd = null;
        this.zzqe = null;
        this.zzqf = null;
        this.zzadp = -1;
    }

    public final void zza(zzjl zzjl) throws IOException {
        if (this.zzqc != null) {
            zzjl.zza(1, this.zzqc);
        }
        if (this.zzqd != null) {
            zzjl.zza(2, this.zzqd);
        }
        if (this.zzqe != null) {
            zzjl.zza(3, this.zzqe);
        }
        if (this.zzqf != null) {
            zzjl.zze(4, this.zzqf.intValue());
        }
        super.zza(zzjl);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int zzt = super.zzt();
        if (this.zzqc != null) {
            zzt += zzjl.zzb(1, this.zzqc);
        }
        if (this.zzqd != null) {
            zzt += zzjl.zzb(2, this.zzqd);
        }
        if (this.zzqe != null) {
            zzt += zzjl.zzb(3, this.zzqe);
        }
        return this.zzqf != null ? zzt + zzjl.zzi(4, this.zzqf.intValue()) : zzt;
    }
}
