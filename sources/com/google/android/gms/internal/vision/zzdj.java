package com.google.android.gms.internal.vision;

import java.io.IOException;

public final class zzdj extends zzjn<zzdj> {
    private String value;
    private Integer zzof;
    private Integer zzog;

    public zzdj() {
        this.value = null;
        this.zzadp = -1;
    }

    public final void zza(zzjl zzjl) throws IOException {
        if (this.zzof != null) {
            zzjl.zze(1, this.zzof.intValue());
        }
        if (this.zzog != null) {
            zzjl.zze(2, this.zzog.intValue());
        }
        if (this.value != null) {
            zzjl.zza(3, this.value);
        }
        super.zza(zzjl);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int zzt = super.zzt();
        if (this.zzof != null) {
            zzt += zzjl.zzi(1, this.zzof.intValue());
        }
        if (this.zzog != null) {
            zzt += zzjl.zzi(2, this.zzog.intValue());
        }
        return this.value != null ? zzt + zzjl.zzb(3, this.value) : zzt;
    }

    private final zzdj zzc(zzjk zzjk) throws IOException {
        int zzdt;
        StringBuilder stringBuilder;
        while (true) {
            int zzdq = zzjk.zzdq();
            if (zzdq == 0) {
                return this;
            }
            if (zzdq == 8) {
                int position = zzjk.getPosition();
                try {
                    this.zzof = Integer.valueOf(zzeb.zzx(zzjk.zzdt()));
                } catch (IllegalArgumentException unused) {
                    zzjk.zzbt(position);
                    zza(zzjk, zzdq);
                }
            } else if (zzdq == 16) {
                try {
                    zzdt = zzjk.zzdt();
                    if (zzdt <= 0 || zzdt > 12) {
                        stringBuilder = new StringBuilder(50);
                        stringBuilder.append(zzdt);
                        stringBuilder.append(" is not a valid enum BarcodeValueFormat");
                    } else {
                        this.zzog = Integer.valueOf(zzdt);
                    }
                } catch (IllegalArgumentException unused2) {
                    zzjk.zzbt(zzjk.getPosition());
                    zza(zzjk, zzdq);
                }
            } else if (zzdq == 26) {
                this.value = zzjk.readString();
            } else if (!super.zza(zzjk, zzdq)) {
                return this;
            }
        }
        stringBuilder = new StringBuilder(50);
        stringBuilder.append(zzdt);
        stringBuilder.append(" is not a valid enum BarcodeValueFormat");
        throw new IllegalArgumentException(stringBuilder.toString());
    }
}
