package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzj extends zzyc<zzj> {
    public zzp[] zzoo;
    public zzp[] zzop;
    public zzi[] zzoq;

    public zzj() {
        this.zzoo = zzp.zzk();
        this.zzop = zzp.zzk();
        this.zzoq = zzi.zzg();
        this.zzcet = null;
        this.zzcfd = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzj)) {
            return false;
        }
        zzj zzj = (zzj) obj;
        if (!zzyg.equals(this.zzoo, zzj.zzoo) || !zzyg.equals(this.zzop, zzj.zzop) || !zzyg.equals(this.zzoq, zzj.zzoq)) {
            return false;
        }
        if (this.zzcet == null || this.zzcet.isEmpty()) {
            return zzj.zzcet == null || zzj.zzcet.isEmpty();
        } else {
            return this.zzcet.equals(zzj.zzcet);
        }
    }

    public final int hashCode() {
        int hashCode = (((((((527 + getClass().getName().hashCode()) * 31) + zzyg.hashCode(this.zzoo)) * 31) + zzyg.hashCode(this.zzop)) * 31) + zzyg.hashCode(this.zzoq)) * 31;
        int hashCode2 = (this.zzcet == null || this.zzcet.isEmpty()) ? 0 : this.zzcet.hashCode();
        return hashCode + hashCode2;
    }

    public final void zza(zzya zzya) throws IOException {
        int i = 0;
        if (this.zzoo != null && this.zzoo.length > 0) {
            for (zzyi zzyi : this.zzoo) {
                if (zzyi != null) {
                    zzya.zza(1, zzyi);
                }
            }
        }
        if (this.zzop != null && this.zzop.length > 0) {
            for (zzyi zzyi2 : this.zzop) {
                if (zzyi2 != null) {
                    zzya.zza(2, zzyi2);
                }
            }
        }
        if (this.zzoq != null && this.zzoq.length > 0) {
            while (i < this.zzoq.length) {
                zzyi zzyi3 = this.zzoq[i];
                if (zzyi3 != null) {
                    zzya.zza(3, zzyi3);
                }
                i++;
            }
        }
        super.zza(zzya);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzf() {
        int i;
        int zzf = super.zzf();
        int i2 = 0;
        if (this.zzoo != null && this.zzoo.length > 0) {
            i = zzf;
            for (zzyi zzyi : this.zzoo) {
                if (zzyi != null) {
                    i += zzya.zzb(1, zzyi);
                }
            }
            zzf = i;
        }
        if (this.zzop != null && this.zzop.length > 0) {
            i = zzf;
            for (zzyi zzyi2 : this.zzop) {
                if (zzyi2 != null) {
                    i += zzya.zzb(2, zzyi2);
                }
            }
            zzf = i;
        }
        if (this.zzoq != null && this.zzoq.length > 0) {
            while (i2 < this.zzoq.length) {
                zzyi zzyi3 = this.zzoq[i2];
                if (zzyi3 != null) {
                    zzf += zzya.zzb(3, zzyi3);
                }
                i2++;
            }
        }
        return zzf;
    }
}
