package com.google.android.gms.internal.vision;

import java.io.IOException;

public final class zzdk extends zzjn<zzdk> {
    public zzds[] zzoh;

    public zzdk() {
        this.zzoh = zzds.zzcc();
        this.zzadp = -1;
    }

    public final void zza(zzjl zzjl) throws IOException {
        if (this.zzoh != null && this.zzoh.length > 0) {
            for (zzjt zzjt : this.zzoh) {
                if (zzjt != null) {
                    zzjl.zza(1, zzjt);
                }
            }
        }
        super.zza(zzjl);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int zzt = super.zzt();
        if (this.zzoh != null && this.zzoh.length > 0) {
            for (zzjt zzjt : this.zzoh) {
                if (zzjt != null) {
                    zzt += zzjl.zzb(1, zzjt);
                }
            }
        }
        return zzt;
    }
}
