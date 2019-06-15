package com.google.android.gms.internal.clearcut;

import java.io.IOException;

public final class zzgy extends zzfu<zzgy> implements Cloneable {
    private String[] zzbiw;
    private String[] zzbix;
    private int[] zzbiy;
    private long[] zzbiz;
    private long[] zzbja;

    public zzgy() {
        this.zzbiw = zzgb.zzsc;
        this.zzbix = zzgb.zzsc;
        this.zzbiy = zzgb.zzrx;
        this.zzbiz = zzgb.zzry;
        this.zzbja = zzgb.zzry;
        this.zzrj = null;
        this.zzrs = -1;
    }

    private final zzgy zzgb() {
        try {
            zzgy zzgy = (zzgy) super.clone();
            if (this.zzbiw != null && this.zzbiw.length > 0) {
                zzgy.zzbiw = (String[]) this.zzbiw.clone();
            }
            if (this.zzbix != null && this.zzbix.length > 0) {
                zzgy.zzbix = (String[]) this.zzbix.clone();
            }
            if (this.zzbiy != null && this.zzbiy.length > 0) {
                zzgy.zzbiy = (int[]) this.zzbiy.clone();
            }
            if (this.zzbiz != null && this.zzbiz.length > 0) {
                zzgy.zzbiz = (long[]) this.zzbiz.clone();
            }
            if (this.zzbja != null && this.zzbja.length > 0) {
                zzgy.zzbja = (long[]) this.zzbja.clone();
            }
            return zzgy;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzgb();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgy)) {
            return false;
        }
        zzgy zzgy = (zzgy) obj;
        return (zzfy.equals(this.zzbiw, zzgy.zzbiw) && zzfy.equals(this.zzbix, zzgy.zzbix) && zzfy.equals(this.zzbiy, zzgy.zzbiy) && zzfy.equals(this.zzbiz, zzgy.zzbiz) && zzfy.equals(this.zzbja, zzgy.zzbja)) ? (this.zzrj == null || this.zzrj.isEmpty()) ? zzgy.zzrj == null || zzgy.zzrj.isEmpty() : this.zzrj.equals(zzgy.zzrj) : false;
    }

    public final int hashCode() {
        int hashCode = (((((((((((527 + getClass().getName().hashCode()) * 31) + zzfy.hashCode(this.zzbiw)) * 31) + zzfy.hashCode(this.zzbix)) * 31) + zzfy.hashCode(this.zzbiy)) * 31) + zzfy.hashCode(this.zzbiz)) * 31) + zzfy.hashCode(this.zzbja)) * 31;
        int hashCode2 = (this.zzrj == null || this.zzrj.isEmpty()) ? 0 : this.zzrj.hashCode();
        return hashCode + hashCode2;
    }

    public final void zza(zzfs zzfs) throws IOException {
        int i = 0;
        if (this.zzbiw != null && this.zzbiw.length > 0) {
            for (String str : this.zzbiw) {
                if (str != null) {
                    zzfs.zza(1, str);
                }
            }
        }
        if (this.zzbix != null && this.zzbix.length > 0) {
            for (String str2 : this.zzbix) {
                if (str2 != null) {
                    zzfs.zza(2, str2);
                }
            }
        }
        if (this.zzbiy != null && this.zzbiy.length > 0) {
            for (int zzc : this.zzbiy) {
                zzfs.zzc(3, zzc);
            }
        }
        if (this.zzbiz != null && this.zzbiz.length > 0) {
            for (long zzi : this.zzbiz) {
                zzfs.zzi(4, zzi);
            }
        }
        if (this.zzbja != null && this.zzbja.length > 0) {
            while (i < this.zzbja.length) {
                zzfs.zzi(5, this.zzbja[i]);
                i++;
            }
        }
        super.zza(zzfs);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzen() {
        int i;
        int i2;
        int i3;
        String str;
        int zzen = super.zzen();
        int i4 = 0;
        if (this.zzbiw != null && this.zzbiw.length > 0) {
            i = 0;
            i2 = i;
            i3 = i2;
            while (i < this.zzbiw.length) {
                str = this.zzbiw[i];
                if (str != null) {
                    i3++;
                    i2 += zzfs.zzh(str);
                }
                i++;
            }
            zzen = (zzen + i2) + (i3 * 1);
        }
        if (this.zzbix != null && this.zzbix.length > 0) {
            i = 0;
            i2 = i;
            i3 = i2;
            while (i < this.zzbix.length) {
                str = this.zzbix[i];
                if (str != null) {
                    i3++;
                    i2 += zzfs.zzh(str);
                }
                i++;
            }
            zzen = (zzen + i2) + (i3 * 1);
        }
        if (this.zzbiy != null && this.zzbiy.length > 0) {
            i = 0;
            i2 = i;
            while (i < this.zzbiy.length) {
                i2 += zzfs.zzs(this.zzbiy[i]);
                i++;
            }
            zzen = (zzen + i2) + (this.zzbiy.length * 1);
        }
        if (this.zzbiz != null && this.zzbiz.length > 0) {
            i = 0;
            i2 = i;
            while (i < this.zzbiz.length) {
                i2 += zzfs.zzo(this.zzbiz[i]);
                i++;
            }
            zzen = (zzen + i2) + (this.zzbiz.length * 1);
        }
        if (this.zzbja == null || this.zzbja.length <= 0) {
            return zzen;
        }
        i = 0;
        while (i4 < this.zzbja.length) {
            i += zzfs.zzo(this.zzbja[i4]);
            i4++;
        }
        return (zzen + i) + (1 * this.zzbja.length);
    }

    public final /* synthetic */ zzfu zzeo() throws CloneNotSupportedException {
        return (zzgy) clone();
    }

    public final /* synthetic */ zzfz zzep() throws CloneNotSupportedException {
        return (zzgy) clone();
    }
}
