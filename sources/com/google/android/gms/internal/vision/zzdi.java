package com.google.android.gms.internal.vision;

import java.io.IOException;

public final class zzdi extends zzjn<zzdi> {
    private int[] zzoe;

    public zzdi() {
        this.zzoe = zzjw.zzzb;
        this.zzadp = -1;
    }

    public final void zza(zzjl zzjl) throws IOException {
        if (this.zzoe != null && this.zzoe.length > 0) {
            for (int zze : this.zzoe) {
                zzjl.zze(1, zze);
            }
        }
        super.zza(zzjl);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzt() {
        int zzt = super.zzt();
        if (this.zzoe == null || this.zzoe.length <= 0) {
            return zzt;
        }
        int i = 0;
        int i2 = 0;
        while (i < this.zzoe.length) {
            i2 += zzjl.zzaw(this.zzoe[i]);
            i++;
        }
        return (zzt + i2) + (1 * this.zzoe.length);
    }

    private final zzdi zzb(zzjk zzjk) throws IOException {
        while (true) {
            int zzdq = zzjk.zzdq();
            if (zzdq == 0) {
                return this;
            }
            int i;
            if (zzdq == 8) {
                int zzb = zzjw.zzb(zzjk, 8);
                int[] iArr = new int[zzb];
                i = 0;
                int i2 = i;
                while (i < zzb) {
                    if (i != 0) {
                        zzjk.zzdq();
                    }
                    int position = zzjk.getPosition();
                    try {
                        iArr[i2] = zzeb.zzx(zzjk.zzdt());
                        i2++;
                    } catch (IllegalArgumentException unused) {
                        zzjk.zzbt(position);
                        zza(zzjk, zzdq);
                    }
                    i++;
                }
                if (i2 != 0) {
                    zzdq = this.zzoe == null ? 0 : this.zzoe.length;
                    if (zzdq == 0 && i2 == iArr.length) {
                        this.zzoe = iArr;
                    } else {
                        int[] iArr2 = new int[(zzdq + i2)];
                        if (zzdq != 0) {
                            System.arraycopy(this.zzoe, 0, iArr2, 0, zzdq);
                        }
                        System.arraycopy(iArr, 0, iArr2, zzdq, i2);
                        this.zzoe = iArr2;
                    }
                }
            } else if (zzdq == 10) {
                zzdq = zzjk.zzan(zzjk.zzdt());
                int position2 = zzjk.getPosition();
                i = 0;
                while (zzjk.zzhq() > 0) {
                    try {
                        zzeb.zzx(zzjk.zzdt());
                        i++;
                    } catch (IllegalArgumentException unused2) {
                    }
                }
                if (i != 0) {
                    zzjk.zzbt(position2);
                    position2 = this.zzoe == null ? 0 : this.zzoe.length;
                    int[] iArr3 = new int[(i + position2)];
                    if (position2 != 0) {
                        System.arraycopy(this.zzoe, 0, iArr3, 0, position2);
                    }
                    while (zzjk.zzhq() > 0) {
                        int position3 = zzjk.getPosition();
                        try {
                            iArr3[position2] = zzeb.zzx(zzjk.zzdt());
                            position2++;
                        } catch (IllegalArgumentException unused3) {
                            zzjk.zzbt(position3);
                            zza(zzjk, 8);
                        }
                    }
                    this.zzoe = iArr3;
                }
                zzjk.zzao(zzdq);
            } else if (!super.zza(zzjk, zzdq)) {
                return this;
            }
        }
    }
}
