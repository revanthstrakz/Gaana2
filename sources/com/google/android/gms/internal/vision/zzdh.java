package com.google.android.gms.internal.vision;

import java.io.IOException;

public final class zzdh extends zzjn<zzdh> {
    public String version;
    public String zzod;

    public zzdh() {
        this.zzod = null;
        this.version = null;
        this.zzadp = -1;
    }

    public final void zza(zzjl zzjl) throws IOException {
        if (this.zzod != null) {
            zzjl.zza(1, this.zzod);
        }
        if (this.version != null) {
            zzjl.zza(2, this.version);
        }
        super.zza(zzjl);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int zzt = super.zzt();
        if (this.zzod != null) {
            zzt += zzjl.zzb(1, this.zzod);
        }
        return this.version != null ? zzt + zzjl.zzb(2, this.version) : zzt;
    }
}
