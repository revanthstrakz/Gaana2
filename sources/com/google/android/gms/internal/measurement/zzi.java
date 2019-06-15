package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzi extends zzyc<zzi> {
    private static volatile zzi[] zzoi;
    public String zzoj;
    public long zzok;
    public long zzol;
    public boolean zzom;
    public long zzon;

    public static zzi[] zzg() {
        if (zzoi == null) {
            synchronized (zzyg.zzcfc) {
                if (zzoi == null) {
                    zzoi = new zzi[0];
                }
            }
        }
        return zzoi;
    }

    public zzi() {
        this.zzoj = "";
        this.zzok = 0;
        this.zzol = 2147483647L;
        this.zzom = false;
        this.zzon = 0;
        this.zzcet = null;
        this.zzcfd = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzi)) {
            return false;
        }
        zzi zzi = (zzi) obj;
        if (this.zzoj == null) {
            if (zzi.zzoj != null) {
                return false;
            }
        } else if (!this.zzoj.equals(zzi.zzoj)) {
            return false;
        }
        if (this.zzok != zzi.zzok || this.zzol != zzi.zzol || this.zzom != zzi.zzom || this.zzon != zzi.zzon) {
            return false;
        }
        if (this.zzcet == null || this.zzcet.isEmpty()) {
            return zzi.zzcet == null || zzi.zzcet.isEmpty();
        } else {
            return this.zzcet.equals(zzi.zzcet);
        }
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((((((((527 + getClass().getName().hashCode()) * 31) + (this.zzoj == null ? 0 : this.zzoj.hashCode())) * 31) + ((int) (this.zzok ^ (this.zzok >>> 32)))) * 31) + ((int) (this.zzol ^ (this.zzol >>> 32)))) * 31) + (this.zzom ? 1231 : 1237)) * 31) + ((int) (this.zzon ^ (this.zzon >>> 32)))) * 31;
        if (!(this.zzcet == null || this.zzcet.isEmpty())) {
            i = this.zzcet.hashCode();
        }
        return hashCode + i;
    }

    public final void zza(zzya zzya) throws IOException {
        if (!(this.zzoj == null || this.zzoj.equals(""))) {
            zzya.zzb(1, this.zzoj);
        }
        if (this.zzok != 0) {
            zzya.zzi(2, this.zzok);
        }
        if (this.zzol != 2147483647L) {
            zzya.zzi(3, this.zzol);
        }
        if (this.zzom) {
            zzya.zzb(4, this.zzom);
        }
        if (this.zzon != 0) {
            zzya.zzi(5, this.zzon);
        }
        super.zza(zzya);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzf() {
        int zzf = super.zzf();
        if (!(this.zzoj == null || this.zzoj.equals(""))) {
            zzf += zzya.zzc(1, this.zzoj);
        }
        if (this.zzok != 0) {
            zzf += zzya.zzd(2, this.zzok);
        }
        if (this.zzol != 2147483647L) {
            zzf += zzya.zzd(3, this.zzol);
        }
        if (this.zzom) {
            zzf += zzya.zzbd(4) + 1;
        }
        return this.zzon != 0 ? zzf + zzya.zzd(5, this.zzon) : zzf;
    }
}
