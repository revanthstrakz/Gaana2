package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzm extends zzyc<zzm> {
    private static volatile zzm[] zzpi;
    public int[] zzpj;
    public int[] zzpk;
    public int[] zzpl;
    public int[] zzpm;
    public int[] zzpn;
    public int[] zzpo;
    public int[] zzpp;
    public int[] zzpq;
    public int[] zzpr;
    public int[] zzps;

    public static zzm[] zzi() {
        if (zzpi == null) {
            synchronized (zzyg.zzcfc) {
                if (zzpi == null) {
                    zzpi = new zzm[0];
                }
            }
        }
        return zzpi;
    }

    public zzm() {
        this.zzpj = zzyl.zzcao;
        this.zzpk = zzyl.zzcao;
        this.zzpl = zzyl.zzcao;
        this.zzpm = zzyl.zzcao;
        this.zzpn = zzyl.zzcao;
        this.zzpo = zzyl.zzcao;
        this.zzpp = zzyl.zzcao;
        this.zzpq = zzyl.zzcao;
        this.zzpr = zzyl.zzcao;
        this.zzps = zzyl.zzcao;
        this.zzcet = null;
        this.zzcfd = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzm)) {
            return false;
        }
        zzm zzm = (zzm) obj;
        if (!zzyg.equals(this.zzpj, zzm.zzpj) || !zzyg.equals(this.zzpk, zzm.zzpk) || !zzyg.equals(this.zzpl, zzm.zzpl) || !zzyg.equals(this.zzpm, zzm.zzpm) || !zzyg.equals(this.zzpn, zzm.zzpn) || !zzyg.equals(this.zzpo, zzm.zzpo) || !zzyg.equals(this.zzpp, zzm.zzpp) || !zzyg.equals(this.zzpq, zzm.zzpq) || !zzyg.equals(this.zzpr, zzm.zzpr) || !zzyg.equals(this.zzps, zzm.zzps)) {
            return false;
        }
        if (this.zzcet == null || this.zzcet.isEmpty()) {
            return zzm.zzcet == null || zzm.zzcet.isEmpty();
        } else {
            return this.zzcet.equals(zzm.zzcet);
        }
    }

    public final int hashCode() {
        int hashCode = (((((((((((((((((((((527 + getClass().getName().hashCode()) * 31) + zzyg.hashCode(this.zzpj)) * 31) + zzyg.hashCode(this.zzpk)) * 31) + zzyg.hashCode(this.zzpl)) * 31) + zzyg.hashCode(this.zzpm)) * 31) + zzyg.hashCode(this.zzpn)) * 31) + zzyg.hashCode(this.zzpo)) * 31) + zzyg.hashCode(this.zzpp)) * 31) + zzyg.hashCode(this.zzpq)) * 31) + zzyg.hashCode(this.zzpr)) * 31) + zzyg.hashCode(this.zzps)) * 31;
        int hashCode2 = (this.zzcet == null || this.zzcet.isEmpty()) ? 0 : this.zzcet.hashCode();
        return hashCode + hashCode2;
    }

    public final void zza(zzya zzya) throws IOException {
        int i = 0;
        if (this.zzpj != null && this.zzpj.length > 0) {
            for (int zzd : this.zzpj) {
                zzya.zzd(1, zzd);
            }
        }
        if (this.zzpk != null && this.zzpk.length > 0) {
            for (int zzd2 : this.zzpk) {
                zzya.zzd(2, zzd2);
            }
        }
        if (this.zzpl != null && this.zzpl.length > 0) {
            for (int zzd22 : this.zzpl) {
                zzya.zzd(3, zzd22);
            }
        }
        if (this.zzpm != null && this.zzpm.length > 0) {
            for (int zzd222 : this.zzpm) {
                zzya.zzd(4, zzd222);
            }
        }
        if (this.zzpn != null && this.zzpn.length > 0) {
            for (int zzd2222 : this.zzpn) {
                zzya.zzd(5, zzd2222);
            }
        }
        if (this.zzpo != null && this.zzpo.length > 0) {
            for (int zzd22222 : this.zzpo) {
                zzya.zzd(6, zzd22222);
            }
        }
        if (this.zzpp != null && this.zzpp.length > 0) {
            for (int zzd222222 : this.zzpp) {
                zzya.zzd(7, zzd222222);
            }
        }
        if (this.zzpq != null && this.zzpq.length > 0) {
            for (int zzd2222222 : this.zzpq) {
                zzya.zzd(8, zzd2222222);
            }
        }
        if (this.zzpr != null && this.zzpr.length > 0) {
            for (int zzd22222222 : this.zzpr) {
                zzya.zzd(9, zzd22222222);
            }
        }
        if (this.zzps != null && this.zzps.length > 0) {
            while (i < this.zzps.length) {
                zzya.zzd(10, this.zzps[i]);
                i++;
            }
        }
        super.zza(zzya);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzf() {
        int i;
        int i2;
        int zzf = super.zzf();
        int i3 = 0;
        if (this.zzpj != null && this.zzpj.length > 0) {
            i = 0;
            i2 = i;
            while (i < this.zzpj.length) {
                i2 += zzya.zzbe(this.zzpj[i]);
                i++;
            }
            zzf = (zzf + i2) + (this.zzpj.length * 1);
        }
        if (this.zzpk != null && this.zzpk.length > 0) {
            i = 0;
            i2 = i;
            while (i < this.zzpk.length) {
                i2 += zzya.zzbe(this.zzpk[i]);
                i++;
            }
            zzf = (zzf + i2) + (this.zzpk.length * 1);
        }
        if (this.zzpl != null && this.zzpl.length > 0) {
            i = 0;
            i2 = i;
            while (i < this.zzpl.length) {
                i2 += zzya.zzbe(this.zzpl[i]);
                i++;
            }
            zzf = (zzf + i2) + (this.zzpl.length * 1);
        }
        if (this.zzpm != null && this.zzpm.length > 0) {
            i = 0;
            i2 = i;
            while (i < this.zzpm.length) {
                i2 += zzya.zzbe(this.zzpm[i]);
                i++;
            }
            zzf = (zzf + i2) + (this.zzpm.length * 1);
        }
        if (this.zzpn != null && this.zzpn.length > 0) {
            i = 0;
            i2 = i;
            while (i < this.zzpn.length) {
                i2 += zzya.zzbe(this.zzpn[i]);
                i++;
            }
            zzf = (zzf + i2) + (this.zzpn.length * 1);
        }
        if (this.zzpo != null && this.zzpo.length > 0) {
            i = 0;
            i2 = i;
            while (i < this.zzpo.length) {
                i2 += zzya.zzbe(this.zzpo[i]);
                i++;
            }
            zzf = (zzf + i2) + (this.zzpo.length * 1);
        }
        if (this.zzpp != null && this.zzpp.length > 0) {
            i = 0;
            i2 = i;
            while (i < this.zzpp.length) {
                i2 += zzya.zzbe(this.zzpp[i]);
                i++;
            }
            zzf = (zzf + i2) + (this.zzpp.length * 1);
        }
        if (this.zzpq != null && this.zzpq.length > 0) {
            i = 0;
            i2 = i;
            while (i < this.zzpq.length) {
                i2 += zzya.zzbe(this.zzpq[i]);
                i++;
            }
            zzf = (zzf + i2) + (this.zzpq.length * 1);
        }
        if (this.zzpr != null && this.zzpr.length > 0) {
            i = 0;
            i2 = i;
            while (i < this.zzpr.length) {
                i2 += zzya.zzbe(this.zzpr[i]);
                i++;
            }
            zzf = (zzf + i2) + (this.zzpr.length * 1);
        }
        if (this.zzps == null || this.zzps.length <= 0) {
            return zzf;
        }
        i = 0;
        while (i3 < this.zzps.length) {
            i += zzya.zzbe(this.zzps[i3]);
            i3++;
        }
        return (zzf + i) + (1 * this.zzps.length);
    }
}
