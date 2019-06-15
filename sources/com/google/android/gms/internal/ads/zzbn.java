package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbn extends zzbut<zzbn> {
    private Long zzfk;
    private Long zzfl;
    public Long zzhe;
    public Long zzhf;
    public Long zzhg;

    public zzbn() {
        this.zzfk = null;
        this.zzfl = null;
        this.zzhe = null;
        this.zzhf = null;
        this.zzhg = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        if (this.zzfk != null) {
            zzbur.zzr(1, this.zzfk.longValue());
        }
        if (this.zzfl != null) {
            zzbur.zzr(2, this.zzfl.longValue());
        }
        if (this.zzhe != null) {
            zzbur.zzr(3, this.zzhe.longValue());
        }
        if (this.zzhf != null) {
            zzbur.zzr(4, this.zzhf.longValue());
        }
        if (this.zzhg != null) {
            zzbur.zzr(5, this.zzhg.longValue());
        }
        super.zza(zzbur);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int zzt = super.zzt();
        if (this.zzfk != null) {
            zzt += zzbur.zzm(1, this.zzfk.longValue());
        }
        if (this.zzfl != null) {
            zzt += zzbur.zzm(2, this.zzfl.longValue());
        }
        if (this.zzhe != null) {
            zzt += zzbur.zzm(3, this.zzhe.longValue());
        }
        if (this.zzhf != null) {
            zzt += zzbur.zzm(4, this.zzhf.longValue());
        }
        return this.zzhg != null ? zzt + zzbur.zzm(5, this.zzhg.longValue()) : zzt;
    }
}
