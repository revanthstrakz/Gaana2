package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzc.zza;
import java.io.IOException;

public final class zzl extends zzyc<zzl> {
    public String version;
    private String[] zzos;
    public String[] zzot;
    public zzp[] zzou;
    public zzk[] zzov;
    public zzh[] zzow;
    public zzh[] zzox;
    public zzh[] zzoy;
    public zzm[] zzoz;
    private String zzpa;
    private String zzpb;
    private String zzpc;
    private zza zzpd;
    private float zzpe;
    private boolean zzpf;
    private String[] zzpg;
    public int zzph;

    public zzl() {
        this.zzos = zzyl.zzcfm;
        this.zzot = zzyl.zzcfm;
        this.zzou = zzp.zzk();
        this.zzov = zzk.zzh();
        this.zzow = zzh.zze();
        this.zzox = zzh.zze();
        this.zzoy = zzh.zze();
        this.zzoz = zzm.zzi();
        this.zzpa = "";
        this.zzpb = "";
        this.zzpc = "0";
        this.version = "";
        this.zzpd = null;
        this.zzpe = 0.0f;
        this.zzpf = false;
        this.zzpg = zzyl.zzcfm;
        this.zzph = 0;
        this.zzcet = null;
        this.zzcfd = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzl)) {
            return false;
        }
        zzl zzl = (zzl) obj;
        if (!zzyg.equals(this.zzos, zzl.zzos) || !zzyg.equals(this.zzot, zzl.zzot) || !zzyg.equals(this.zzou, zzl.zzou) || !zzyg.equals(this.zzov, zzl.zzov) || !zzyg.equals(this.zzow, zzl.zzow) || !zzyg.equals(this.zzox, zzl.zzox) || !zzyg.equals(this.zzoy, zzl.zzoy) || !zzyg.equals(this.zzoz, zzl.zzoz)) {
            return false;
        }
        if (this.zzpa == null) {
            if (zzl.zzpa != null) {
                return false;
            }
        } else if (!this.zzpa.equals(zzl.zzpa)) {
            return false;
        }
        if (this.zzpb == null) {
            if (zzl.zzpb != null) {
                return false;
            }
        } else if (!this.zzpb.equals(zzl.zzpb)) {
            return false;
        }
        if (this.zzpc == null) {
            if (zzl.zzpc != null) {
                return false;
            }
        } else if (!this.zzpc.equals(zzl.zzpc)) {
            return false;
        }
        if (this.version == null) {
            if (zzl.version != null) {
                return false;
            }
        } else if (!this.version.equals(zzl.version)) {
            return false;
        }
        if (this.zzpd == null) {
            if (zzl.zzpd != null) {
                return false;
            }
        } else if (!this.zzpd.equals(zzl.zzpd)) {
            return false;
        }
        if (Float.floatToIntBits(this.zzpe) != Float.floatToIntBits(zzl.zzpe) || this.zzpf != zzl.zzpf || !zzyg.equals(this.zzpg, zzl.zzpg) || this.zzph != zzl.zzph) {
            return false;
        }
        if (this.zzcet == null || this.zzcet.isEmpty()) {
            return zzl.zzcet == null || zzl.zzcet.isEmpty();
        } else {
            return this.zzcet.equals(zzl.zzcet);
        }
    }

    public final int hashCode() {
        int i;
        int i2 = 0;
        int hashCode = ((((((((((((((((((((((((527 + getClass().getName().hashCode()) * 31) + zzyg.hashCode(this.zzos)) * 31) + zzyg.hashCode(this.zzot)) * 31) + zzyg.hashCode(this.zzou)) * 31) + zzyg.hashCode(this.zzov)) * 31) + zzyg.hashCode(this.zzow)) * 31) + zzyg.hashCode(this.zzox)) * 31) + zzyg.hashCode(this.zzoy)) * 31) + zzyg.hashCode(this.zzoz)) * 31) + (this.zzpa == null ? 0 : this.zzpa.hashCode())) * 31) + (this.zzpb == null ? 0 : this.zzpb.hashCode())) * 31) + (this.zzpc == null ? 0 : this.zzpc.hashCode())) * 31) + (this.version == null ? 0 : this.version.hashCode());
        zza zza = this.zzpd;
        hashCode *= 31;
        if (zza == null) {
            i = 0;
        } else {
            i = zza.hashCode();
        }
        hashCode = (((((((((hashCode + i) * 31) + Float.floatToIntBits(this.zzpe)) * 31) + (this.zzpf ? 1231 : 1237)) * 31) + zzyg.hashCode(this.zzpg)) * 31) + this.zzph) * 31;
        if (!(this.zzcet == null || this.zzcet.isEmpty())) {
            i2 = this.zzcet.hashCode();
        }
        return hashCode + i2;
    }

    public final void zza(zzya zzya) throws IOException {
        int i = 0;
        if (this.zzot != null && this.zzot.length > 0) {
            for (String str : this.zzot) {
                if (str != null) {
                    zzya.zzb(1, str);
                }
            }
        }
        if (this.zzou != null && this.zzou.length > 0) {
            for (zzyi zzyi : this.zzou) {
                if (zzyi != null) {
                    zzya.zza(2, zzyi);
                }
            }
        }
        if (this.zzov != null && this.zzov.length > 0) {
            for (zzyi zzyi2 : this.zzov) {
                if (zzyi2 != null) {
                    zzya.zza(3, zzyi2);
                }
            }
        }
        if (this.zzow != null && this.zzow.length > 0) {
            for (zzyi zzyi22 : this.zzow) {
                if (zzyi22 != null) {
                    zzya.zza(4, zzyi22);
                }
            }
        }
        if (this.zzox != null && this.zzox.length > 0) {
            for (zzyi zzyi222 : this.zzox) {
                if (zzyi222 != null) {
                    zzya.zza(5, zzyi222);
                }
            }
        }
        if (this.zzoy != null && this.zzoy.length > 0) {
            for (zzyi zzyi2222 : this.zzoy) {
                if (zzyi2222 != null) {
                    zzya.zza(6, zzyi2222);
                }
            }
        }
        if (this.zzoz != null && this.zzoz.length > 0) {
            for (zzyi zzyi22222 : this.zzoz) {
                if (zzyi22222 != null) {
                    zzya.zza(7, zzyi22222);
                }
            }
        }
        if (!(this.zzpa == null || this.zzpa.equals(""))) {
            zzya.zzb(9, this.zzpa);
        }
        if (!(this.zzpb == null || this.zzpb.equals(""))) {
            zzya.zzb(10, this.zzpb);
        }
        if (!(this.zzpc == null || this.zzpc.equals("0"))) {
            zzya.zzb(12, this.zzpc);
        }
        if (!(this.version == null || this.version.equals(""))) {
            zzya.zzb(13, this.version);
        }
        if (this.zzpd != null) {
            zzya.zze(14, this.zzpd);
        }
        if (Float.floatToIntBits(this.zzpe) != Float.floatToIntBits(0.0f)) {
            zzya.zza(15, this.zzpe);
        }
        if (this.zzpg != null && this.zzpg.length > 0) {
            for (String str2 : this.zzpg) {
                if (str2 != null) {
                    zzya.zzb(16, str2);
                }
            }
        }
        if (this.zzph != 0) {
            zzya.zzd(17, this.zzph);
        }
        if (this.zzpf) {
            zzya.zzb(18, this.zzpf);
        }
        if (this.zzos != null && this.zzos.length > 0) {
            while (i < this.zzos.length) {
                String str3 = this.zzos[i];
                if (str3 != null) {
                    zzya.zzb(19, str3);
                }
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
        if (this.zzot != null && this.zzot.length > 0) {
            i = 0;
            int i4 = i;
            i2 = i4;
            while (i < this.zzot.length) {
                String str = this.zzot[i];
                if (str != null) {
                    i2++;
                    i4 += zzya.zzgc(str);
                }
                i++;
            }
            zzf = (zzf + i4) + (i2 * 1);
        }
        if (this.zzou != null && this.zzou.length > 0) {
            i = zzf;
            for (zzyi zzyi : this.zzou) {
                if (zzyi != null) {
                    i += zzya.zzb(2, zzyi);
                }
            }
            zzf = i;
        }
        if (this.zzov != null && this.zzov.length > 0) {
            i = zzf;
            for (zzyi zzyi2 : this.zzov) {
                if (zzyi2 != null) {
                    i += zzya.zzb(3, zzyi2);
                }
            }
            zzf = i;
        }
        if (this.zzow != null && this.zzow.length > 0) {
            i = zzf;
            for (zzyi zzyi3 : this.zzow) {
                if (zzyi3 != null) {
                    i += zzya.zzb(4, zzyi3);
                }
            }
            zzf = i;
        }
        if (this.zzox != null && this.zzox.length > 0) {
            i = zzf;
            for (zzyi zzyi32 : this.zzox) {
                if (zzyi32 != null) {
                    i += zzya.zzb(5, zzyi32);
                }
            }
            zzf = i;
        }
        if (this.zzoy != null && this.zzoy.length > 0) {
            i = zzf;
            for (zzyi zzyi322 : this.zzoy) {
                if (zzyi322 != null) {
                    i += zzya.zzb(6, zzyi322);
                }
            }
            zzf = i;
        }
        if (this.zzoz != null && this.zzoz.length > 0) {
            i = zzf;
            for (zzyi zzyi3222 : this.zzoz) {
                if (zzyi3222 != null) {
                    i += zzya.zzb(7, zzyi3222);
                }
            }
            zzf = i;
        }
        if (!(this.zzpa == null || this.zzpa.equals(""))) {
            zzf += zzya.zzc(9, this.zzpa);
        }
        if (!(this.zzpb == null || this.zzpb.equals(""))) {
            zzf += zzya.zzc(10, this.zzpb);
        }
        if (!(this.zzpc == null || this.zzpc.equals("0"))) {
            zzf += zzya.zzc(12, this.zzpc);
        }
        if (!(this.version == null || this.version.equals(""))) {
            zzf += zzya.zzc(13, this.version);
        }
        if (this.zzpd != null) {
            zzf += zztv.zzc(14, this.zzpd);
        }
        if (Float.floatToIntBits(this.zzpe) != Float.floatToIntBits(0.0f)) {
            zzf += zzya.zzbd(15) + 4;
        }
        if (this.zzpg != null && this.zzpg.length > 0) {
            i = 0;
            i2 = i;
            int i5 = i2;
            while (i < this.zzpg.length) {
                String str2 = this.zzpg[i];
                if (str2 != null) {
                    i5++;
                    i2 += zzya.zzgc(str2);
                }
                i++;
            }
            zzf = (zzf + i2) + (i5 * 2);
        }
        if (this.zzph != 0) {
            zzf += zzya.zzh(17, this.zzph);
        }
        if (this.zzpf) {
            zzf += zzya.zzbd(18) + 1;
        }
        if (this.zzos == null || this.zzos.length <= 0) {
            return zzf;
        }
        i = 0;
        int i6 = i;
        while (i3 < this.zzos.length) {
            String str3 = this.zzos[i3];
            if (str3 != null) {
                i6++;
                i += zzya.zzgc(str3);
            }
            i3++;
        }
        return (zzf + i) + (2 * i6);
    }
}
