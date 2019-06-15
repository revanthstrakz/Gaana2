package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbvd.zzb.zzd.zzb;
import java.io.IOException;

public final class zzbvq extends zzbut<zzbvq> {
    private zzb zzgbl;
    public zzbvp[] zzgbm;
    private byte[] zzgbn;
    private byte[] zzgbo;
    private Integer zzgbp;

    public zzbvq() {
        this.zzgbl = null;
        this.zzgbm = zzbvp.zzaqd();
        this.zzgbn = null;
        this.zzgbo = null;
        this.zzgbp = null;
        this.zzfwk = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        if (this.zzgbl != null) {
            zzbur.zze(1, this.zzgbl);
        }
        if (this.zzgbm != null && this.zzgbm.length > 0) {
            for (zzbuz zzbuz : this.zzgbm) {
                if (zzbuz != null) {
                    zzbur.zza(2, zzbuz);
                }
            }
        }
        if (this.zzgbn != null) {
            zzbur.zza(3, this.zzgbn);
        }
        if (this.zzgbo != null) {
            zzbur.zza(4, this.zzgbo);
        }
        if (this.zzgbp != null) {
            zzbur.zzv(5, this.zzgbp.intValue());
        }
        super.zza(zzbur);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int zzt = super.zzt();
        if (this.zzgbl != null) {
            zzt += zzbqk.zzc(1, this.zzgbl);
        }
        if (this.zzgbm != null && this.zzgbm.length > 0) {
            for (zzbuz zzbuz : this.zzgbm) {
                if (zzbuz != null) {
                    zzt += zzbur.zzb(2, zzbuz);
                }
            }
        }
        if (this.zzgbn != null) {
            zzt += zzbur.zzb(3, this.zzgbn);
        }
        if (this.zzgbo != null) {
            zzt += zzbur.zzb(4, this.zzgbo);
        }
        return this.zzgbp != null ? zzt + zzbur.zzz(5, this.zzgbp.intValue()) : zzt;
    }
}
