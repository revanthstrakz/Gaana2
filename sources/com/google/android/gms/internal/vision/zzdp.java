package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzcz.zzg;
import java.io.IOException;

public final class zzdp extends zzjn<zzdp> {
    public zzdq zzpi;
    private zzg zzpj;
    public zzdm[] zzpk;

    public zzdp() {
        this.zzpi = null;
        this.zzpk = zzdm.zzcb();
        this.zzadp = -1;
    }

    public final void zza(zzjl zzjl) throws IOException {
        if (this.zzpi != null) {
            zzjl.zza(1, this.zzpi);
        }
        if (this.zzpj != null) {
            zzjl.zze(2, this.zzpj);
        }
        if (this.zzpk != null && this.zzpk.length > 0) {
            for (zzjt zzjt : this.zzpk) {
                if (zzjt != null) {
                    zzjl.zza(3, zzjt);
                }
            }
        }
        super.zza(zzjl);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int zzt = super.zzt();
        if (this.zzpi != null) {
            zzt += zzjl.zzb(1, this.zzpi);
        }
        if (this.zzpj != null) {
            zzt += zzfe.zzc(2, this.zzpj);
        }
        if (this.zzpk != null && this.zzpk.length > 0) {
            for (zzjt zzjt : this.zzpk) {
                if (zzjt != null) {
                    zzt += zzjl.zzb(3, zzjt);
                }
            }
        }
        return zzt;
    }
}
