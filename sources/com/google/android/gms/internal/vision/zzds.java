package com.google.android.gms.internal.vision;

import java.io.IOException;

public final class zzds extends zzjn<zzds> {
    private static volatile zzds[] zzpu;
    public Integer zzpv;
    public Integer zzpw;

    public static zzds[] zzcc() {
        if (zzpu == null) {
            synchronized (zzjr.zzado) {
                if (zzpu == null) {
                    zzpu = new zzds[0];
                }
            }
        }
        return zzpu;
    }

    public zzds() {
        this.zzpv = null;
        this.zzpw = null;
        this.zzadp = -1;
    }

    public final void zza(zzjl zzjl) throws IOException {
        if (this.zzpv != null) {
            zzjl.zze(1, this.zzpv.intValue());
        }
        if (this.zzpw != null) {
            zzjl.zze(2, this.zzpw.intValue());
        }
        super.zza(zzjl);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int zzt = super.zzt();
        if (this.zzpv != null) {
            zzt += zzjl.zzi(1, this.zzpv.intValue());
        }
        return this.zzpw != null ? zzt + zzjl.zzi(2, this.zzpw.intValue()) : zzt;
    }
}
