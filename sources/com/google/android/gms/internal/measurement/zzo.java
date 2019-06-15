package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzo extends zzyc<zzo> {
    public zzn[] zzqf;
    public zzl zzqg;
    public String zzqh;

    public zzo() {
        this.zzqf = zzn.zzj();
        this.zzqg = null;
        this.zzqh = "";
        this.zzcet = null;
        this.zzcfd = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzo)) {
            return false;
        }
        zzo zzo = (zzo) obj;
        if (!zzyg.equals(this.zzqf, zzo.zzqf)) {
            return false;
        }
        if (this.zzqg == null) {
            if (zzo.zzqg != null) {
                return false;
            }
        } else if (!this.zzqg.equals(zzo.zzqg)) {
            return false;
        }
        if (this.zzqh == null) {
            if (zzo.zzqh != null) {
                return false;
            }
        } else if (!this.zzqh.equals(zzo.zzqh)) {
            return false;
        }
        if (this.zzcet == null || this.zzcet.isEmpty()) {
            return zzo.zzcet == null || zzo.zzcet.isEmpty();
        } else {
            return this.zzcet.equals(zzo.zzcet);
        }
    }

    public final int hashCode() {
        int i;
        int hashCode = ((527 + getClass().getName().hashCode()) * 31) + zzyg.hashCode(this.zzqf);
        zzl zzl = this.zzqg;
        hashCode *= 31;
        int i2 = 0;
        if (zzl == null) {
            i = 0;
        } else {
            i = zzl.hashCode();
        }
        hashCode = (((hashCode + i) * 31) + (this.zzqh == null ? 0 : this.zzqh.hashCode())) * 31;
        if (!(this.zzcet == null || this.zzcet.isEmpty())) {
            i2 = this.zzcet.hashCode();
        }
        return hashCode + i2;
    }

    public final void zza(zzya zzya) throws IOException {
        if (this.zzqf != null && this.zzqf.length > 0) {
            for (zzyi zzyi : this.zzqf) {
                if (zzyi != null) {
                    zzya.zza(1, zzyi);
                }
            }
        }
        if (this.zzqg != null) {
            zzya.zza(2, this.zzqg);
        }
        if (!(this.zzqh == null || this.zzqh.equals(""))) {
            zzya.zzb(3, this.zzqh);
        }
        super.zza(zzya);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzf() {
        int zzf = super.zzf();
        if (this.zzqf != null && this.zzqf.length > 0) {
            for (zzyi zzyi : this.zzqf) {
                if (zzyi != null) {
                    zzf += zzya.zzb(1, zzyi);
                }
            }
        }
        if (this.zzqg != null) {
            zzf += zzya.zzb(2, this.zzqg);
        }
        return (this.zzqh == null || this.zzqh.equals("")) ? zzf : zzf + zzya.zzc(3, this.zzqh);
    }
}
