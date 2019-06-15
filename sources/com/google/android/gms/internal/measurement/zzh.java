package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzh extends zzyc<zzh> {
    private static volatile zzh[] zzod;
    private int name;
    public int[] zzoe;
    private int zzof;
    private boolean zzog;
    private boolean zzoh;

    public static zzh[] zze() {
        if (zzod == null) {
            synchronized (zzyg.zzcfc) {
                if (zzod == null) {
                    zzod = new zzh[0];
                }
            }
        }
        return zzod;
    }

    public zzh() {
        this.zzoe = zzyl.zzcao;
        this.zzof = 0;
        this.name = 0;
        this.zzog = false;
        this.zzoh = false;
        this.zzcet = null;
        this.zzcfd = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzh)) {
            return false;
        }
        zzh zzh = (zzh) obj;
        if (!zzyg.equals(this.zzoe, zzh.zzoe) || this.zzof != zzh.zzof || this.name != zzh.name || this.zzog != zzh.zzog || this.zzoh != zzh.zzoh) {
            return false;
        }
        if (this.zzcet == null || this.zzcet.isEmpty()) {
            return zzh.zzcet == null || zzh.zzcet.isEmpty();
        } else {
            return this.zzcet.equals(zzh.zzcet);
        }
    }

    public final int hashCode() {
        int i = 1237;
        int hashCode = (((((((((527 + getClass().getName().hashCode()) * 31) + zzyg.hashCode(this.zzoe)) * 31) + this.zzof) * 31) + this.name) * 31) + (this.zzog ? 1231 : 1237)) * 31;
        if (this.zzoh) {
            i = 1231;
        }
        hashCode = (hashCode + i) * 31;
        int hashCode2 = (this.zzcet == null || this.zzcet.isEmpty()) ? 0 : this.zzcet.hashCode();
        return hashCode + hashCode2;
    }

    public final void zza(zzya zzya) throws IOException {
        if (this.zzoh) {
            zzya.zzb(1, this.zzoh);
        }
        zzya.zzd(2, this.zzof);
        if (this.zzoe != null && this.zzoe.length > 0) {
            for (int zzd : this.zzoe) {
                zzya.zzd(3, zzd);
            }
        }
        if (this.name != 0) {
            zzya.zzd(4, this.name);
        }
        if (this.zzog) {
            zzya.zzb(6, this.zzog);
        }
        super.zza(zzya);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzf() {
        int zzf = super.zzf();
        if (this.zzoh) {
            zzf += zzya.zzbd(1) + 1;
        }
        zzf += zzya.zzh(2, this.zzof);
        if (this.zzoe != null && this.zzoe.length > 0) {
            int i = 0;
            int i2 = 0;
            while (i < this.zzoe.length) {
                i2 += zzya.zzbe(this.zzoe[i]);
                i++;
            }
            zzf = (zzf + i2) + (this.zzoe.length * 1);
        }
        if (this.name != 0) {
            zzf += zzya.zzh(4, this.name);
        }
        return this.zzog ? zzf + (zzya.zzbd(6) + 1) : zzf;
    }
}
