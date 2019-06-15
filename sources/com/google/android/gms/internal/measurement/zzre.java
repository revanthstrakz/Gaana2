package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzre extends zzyc<zzre> {
    public long zzbqc;
    public zzo zzbqd;
    public zzl zzqg;

    public zzre() {
        this.zzbqc = 0;
        this.zzqg = null;
        this.zzbqd = null;
        this.zzcet = null;
        this.zzcfd = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzre)) {
            return false;
        }
        zzre zzre = (zzre) obj;
        if (this.zzbqc != zzre.zzbqc) {
            return false;
        }
        if (this.zzqg == null) {
            if (zzre.zzqg != null) {
                return false;
            }
        } else if (!this.zzqg.equals(zzre.zzqg)) {
            return false;
        }
        if (this.zzbqd == null) {
            if (zzre.zzbqd != null) {
                return false;
            }
        } else if (!this.zzbqd.equals(zzre.zzbqd)) {
            return false;
        }
        if (this.zzcet == null || this.zzcet.isEmpty()) {
            return zzre.zzcet == null || zzre.zzcet.isEmpty();
        } else {
            return this.zzcet.equals(zzre.zzcet);
        }
    }

    public final int hashCode() {
        int i;
        int hashCode = ((527 + getClass().getName().hashCode()) * 31) + ((int) (this.zzbqc ^ (this.zzbqc >>> 32)));
        zzl zzl = this.zzqg;
        hashCode *= 31;
        int i2 = 0;
        if (zzl == null) {
            i = 0;
        } else {
            i = zzl.hashCode();
        }
        hashCode += i;
        zzo zzo = this.zzbqd;
        hashCode *= 31;
        if (zzo == null) {
            i = 0;
        } else {
            i = zzo.hashCode();
        }
        hashCode = (hashCode + i) * 31;
        if (!(this.zzcet == null || this.zzcet.isEmpty())) {
            i2 = this.zzcet.hashCode();
        }
        return hashCode + i2;
    }

    public final void zza(zzya zzya) throws IOException {
        zzya.zzi(1, this.zzbqc);
        if (this.zzqg != null) {
            zzya.zza(2, this.zzqg);
        }
        if (this.zzbqd != null) {
            zzya.zza(3, this.zzbqd);
        }
        super.zza(zzya);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzf() {
        int zzf = super.zzf() + zzya.zzd(1, this.zzbqc);
        if (this.zzqg != null) {
            zzf += zzya.zzb(2, this.zzqg);
        }
        return this.zzbqd != null ? zzf + zzya.zzb(3, this.zzbqd) : zzf;
    }
}
