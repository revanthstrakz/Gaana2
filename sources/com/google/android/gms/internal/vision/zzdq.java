package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzcz.zzf.zzb;
import java.io.IOException;

public final class zzdq extends zzjn<zzdq> {
    private zzb zzpl;
    public Long zzpm;
    public Long zzpn;
    public Long zzpo;
    public Long zzpp;

    public zzdq() {
        this.zzpm = null;
        this.zzpn = null;
        this.zzpo = null;
        this.zzpp = null;
        this.zzadp = -1;
    }

    public final void zza(zzjl zzjl) throws IOException {
        if (!(this.zzpl == null || this.zzpl == null)) {
            zzjl.zze(1, this.zzpl.zzr());
        }
        if (this.zzpm != null) {
            zzjl.zzi(2, this.zzpm.longValue());
        }
        if (this.zzpn != null) {
            zzjl.zzi(3, this.zzpn.longValue());
        }
        if (this.zzpp != null) {
            zzjl.zzi(4, this.zzpp.longValue());
        }
        if (this.zzpo != null) {
            zzjl.zzi(5, this.zzpo.longValue());
        }
        super.zza(zzjl);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int zzt = super.zzt();
        if (!(this.zzpl == null || this.zzpl == null)) {
            zzt += zzjl.zzi(1, this.zzpl.zzr());
        }
        if (this.zzpm != null) {
            zzt += zzjl.zzd(2, this.zzpm.longValue());
        }
        if (this.zzpn != null) {
            zzt += zzjl.zzd(3, this.zzpn.longValue());
        }
        if (this.zzpp != null) {
            zzt += zzjl.zzd(4, this.zzpp.longValue());
        }
        return this.zzpo != null ? zzt + zzjl.zzd(5, this.zzpo.longValue()) : zzt;
    }
}
