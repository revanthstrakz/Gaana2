package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzg {

    public static final class zza extends zzyc<zza> {
        public static final zzyd<zzp, zza> zzpt = zzyd.zza(11, zza.class, 810);
        private static final zza[] zzpu = new zza[0];
        public int[] zzpv;
        public int[] zzpw;
        public int[] zzpx;
        private int zzpy;
        public int[] zzpz;
        public int zzqa;
        private int zzqb;

        public zza() {
            this.zzpv = zzyl.zzcao;
            this.zzpw = zzyl.zzcao;
            this.zzpx = zzyl.zzcao;
            this.zzpy = 0;
            this.zzpz = zzyl.zzcao;
            this.zzqa = 0;
            this.zzqb = 0;
            this.zzcet = null;
            this.zzcfd = -1;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (!zzyg.equals(this.zzpv, zza.zzpv) || !zzyg.equals(this.zzpw, zza.zzpw) || !zzyg.equals(this.zzpx, zza.zzpx) || this.zzpy != zza.zzpy || !zzyg.equals(this.zzpz, zza.zzpz) || this.zzqa != zza.zzqa || this.zzqb != zza.zzqb) {
                return false;
            }
            if (this.zzcet == null || this.zzcet.isEmpty()) {
                return zza.zzcet == null || zza.zzcet.isEmpty();
            } else {
                return this.zzcet.equals(zza.zzcet);
            }
        }

        public final int hashCode() {
            int hashCode = (((((((((((((((527 + getClass().getName().hashCode()) * 31) + zzyg.hashCode(this.zzpv)) * 31) + zzyg.hashCode(this.zzpw)) * 31) + zzyg.hashCode(this.zzpx)) * 31) + this.zzpy) * 31) + zzyg.hashCode(this.zzpz)) * 31) + this.zzqa) * 31) + this.zzqb) * 31;
            int hashCode2 = (this.zzcet == null || this.zzcet.isEmpty()) ? 0 : this.zzcet.hashCode();
            return hashCode + hashCode2;
        }

        public final void zza(zzya zzya) throws IOException {
            int i = 0;
            if (this.zzpv != null && this.zzpv.length > 0) {
                for (int zzd : this.zzpv) {
                    zzya.zzd(1, zzd);
                }
            }
            if (this.zzpw != null && this.zzpw.length > 0) {
                for (int zzd2 : this.zzpw) {
                    zzya.zzd(2, zzd2);
                }
            }
            if (this.zzpx != null && this.zzpx.length > 0) {
                for (int zzd22 : this.zzpx) {
                    zzya.zzd(3, zzd22);
                }
            }
            if (this.zzpy != 0) {
                zzya.zzd(4, this.zzpy);
            }
            if (this.zzpz != null && this.zzpz.length > 0) {
                while (i < this.zzpz.length) {
                    zzya.zzd(5, this.zzpz[i]);
                    i++;
                }
            }
            if (this.zzqa != 0) {
                zzya.zzd(6, this.zzqa);
            }
            if (this.zzqb != 0) {
                zzya.zzd(7, this.zzqb);
            }
            super.zza(zzya);
        }

        /* Access modifiers changed, original: protected|final */
        public final int zzf() {
            int i;
            int i2;
            int zzf = super.zzf();
            int i3 = 0;
            if (this.zzpv != null && this.zzpv.length > 0) {
                i = 0;
                i2 = i;
                while (i < this.zzpv.length) {
                    i2 += zzya.zzbe(this.zzpv[i]);
                    i++;
                }
                zzf = (zzf + i2) + (this.zzpv.length * 1);
            }
            if (this.zzpw != null && this.zzpw.length > 0) {
                i = 0;
                i2 = i;
                while (i < this.zzpw.length) {
                    i2 += zzya.zzbe(this.zzpw[i]);
                    i++;
                }
                zzf = (zzf + i2) + (this.zzpw.length * 1);
            }
            if (this.zzpx != null && this.zzpx.length > 0) {
                i = 0;
                i2 = i;
                while (i < this.zzpx.length) {
                    i2 += zzya.zzbe(this.zzpx[i]);
                    i++;
                }
                zzf = (zzf + i2) + (this.zzpx.length * 1);
            }
            if (this.zzpy != 0) {
                zzf += zzya.zzh(4, this.zzpy);
            }
            if (this.zzpz != null && this.zzpz.length > 0) {
                i = 0;
                while (i3 < this.zzpz.length) {
                    i += zzya.zzbe(this.zzpz[i3]);
                    i3++;
                }
                zzf = (zzf + i) + (1 * this.zzpz.length);
            }
            if (this.zzqa != 0) {
                zzf += zzya.zzh(6, this.zzqa);
            }
            return this.zzqb != 0 ? zzf + zzya.zzh(7, this.zzqb) : zzf;
        }
    }
}
