package com.google.android.gms.internal.vision;

import java.io.IOException;

public final class zzdo extends zzjn<zzdo> {
    public Float zzpc;
    public Float zzpd;
    public Float zzpe;
    public Float zzpf;
    public Float zzpg;
    public Float zzph;

    public zzdo() {
        this.zzpc = null;
        this.zzpd = null;
        this.zzpe = null;
        this.zzpf = null;
        this.zzpg = null;
        this.zzph = null;
        this.zzadp = -1;
    }

    public final void zza(zzjl zzjl) throws IOException {
        if (this.zzpc != null) {
            zzjl.zza(1, this.zzpc.floatValue());
        }
        if (this.zzpd != null) {
            zzjl.zza(2, this.zzpd.floatValue());
        }
        if (this.zzpe != null) {
            zzjl.zza(3, this.zzpe.floatValue());
        }
        if (this.zzpf != null) {
            zzjl.zza(4, this.zzpf.floatValue());
        }
        if (this.zzpg != null) {
            zzjl.zza(5, this.zzpg.floatValue());
        }
        if (this.zzph != null) {
            zzjl.zza(6, this.zzph.floatValue());
        }
        super.zza(zzjl);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int zzt = super.zzt();
        if (this.zzpc != null) {
            this.zzpc.floatValue();
            zzt += zzjl.zzav(1) + 4;
        }
        if (this.zzpd != null) {
            this.zzpd.floatValue();
            zzt += zzjl.zzav(2) + 4;
        }
        if (this.zzpe != null) {
            this.zzpe.floatValue();
            zzt += zzjl.zzav(3) + 4;
        }
        if (this.zzpf != null) {
            this.zzpf.floatValue();
            zzt += zzjl.zzav(4) + 4;
        }
        if (this.zzpg != null) {
            this.zzpg.floatValue();
            zzt += zzjl.zzav(5) + 4;
        }
        if (this.zzph == null) {
            return zzt;
        }
        this.zzph.floatValue();
        return zzt + (zzjl.zzav(6) + 4);
    }
}
