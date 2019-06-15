package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbq extends zzbut<zzbq> {
    public Long zzhh;
    private String zzhp;
    private byte[] zzhq;

    public zzbq() {
        this.zzhh = null;
        this.zzhp = null;
        this.zzhq = null;
        this.zzfwt = -1;
    }

    public final void zza(zzbur zzbur) throws IOException {
        if (this.zzhh != null) {
            zzbur.zzr(1, this.zzhh.longValue());
        }
        if (this.zzhp != null) {
            zzbur.zzf(3, this.zzhp);
        }
        if (this.zzhq != null) {
            zzbur.zza(4, this.zzhq);
        }
        super.zza(zzbur);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int zzt = super.zzt();
        if (this.zzhh != null) {
            zzt += zzbur.zzm(1, this.zzhh.longValue());
        }
        if (this.zzhp != null) {
            zzt += zzbur.zzg(3, this.zzhp);
        }
        return this.zzhq != null ? zzt + zzbur.zzb(4, this.zzhq) : zzt;
    }
}
