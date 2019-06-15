package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzn extends zzyc<zzn> {
    private static volatile zzn[] zzqc;
    public String name;
    private zzp zzqd;
    public zzj zzqe;

    public static zzn[] zzj() {
        if (zzqc == null) {
            synchronized (zzyg.zzcfc) {
                if (zzqc == null) {
                    zzqc = new zzn[0];
                }
            }
        }
        return zzqc;
    }

    public zzn() {
        this.name = "";
        this.zzqd = null;
        this.zzqe = null;
        this.zzcet = null;
        this.zzcfd = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzn)) {
            return false;
        }
        zzn zzn = (zzn) obj;
        if (this.name == null) {
            if (zzn.name != null) {
                return false;
            }
        } else if (!this.name.equals(zzn.name)) {
            return false;
        }
        if (this.zzqd == null) {
            if (zzn.zzqd != null) {
                return false;
            }
        } else if (!this.zzqd.equals(zzn.zzqd)) {
            return false;
        }
        if (this.zzqe == null) {
            if (zzn.zzqe != null) {
                return false;
            }
        } else if (!this.zzqe.equals(zzn.zzqe)) {
            return false;
        }
        if (this.zzcet == null || this.zzcet.isEmpty()) {
            return zzn.zzcet == null || zzn.zzcet.isEmpty();
        } else {
            return this.zzcet.equals(zzn.zzcet);
        }
    }

    public final int hashCode() {
        int i;
        int i2 = 0;
        int hashCode = ((527 + getClass().getName().hashCode()) * 31) + (this.name == null ? 0 : this.name.hashCode());
        zzp zzp = this.zzqd;
        hashCode *= 31;
        if (zzp == null) {
            i = 0;
        } else {
            i = zzp.hashCode();
        }
        hashCode += i;
        zzj zzj = this.zzqe;
        hashCode *= 31;
        if (zzj == null) {
            i = 0;
        } else {
            i = zzj.hashCode();
        }
        hashCode = (hashCode + i) * 31;
        if (!(this.zzcet == null || this.zzcet.isEmpty())) {
            i2 = this.zzcet.hashCode();
        }
        return hashCode + i2;
    }

    public final void zza(zzya zzya) throws IOException {
        if (!(this.name == null || this.name.equals(""))) {
            zzya.zzb(1, this.name);
        }
        if (this.zzqd != null) {
            zzya.zza(2, this.zzqd);
        }
        if (this.zzqe != null) {
            zzya.zza(3, this.zzqe);
        }
        super.zza(zzya);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzf() {
        int zzf = super.zzf();
        if (!(this.name == null || this.name.equals(""))) {
            zzf += zzya.zzc(1, this.name);
        }
        if (this.zzqd != null) {
            zzf += zzya.zzb(2, this.zzqd);
        }
        return this.zzqe != null ? zzf + zzya.zzb(3, this.zzqe) : zzf;
    }
}
