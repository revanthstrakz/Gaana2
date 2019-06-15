package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzp extends zzyc<zzp> {
    private static volatile zzp[] zzqi;
    public String string;
    public int type;
    public zzp[] zzqj;
    public zzp[] zzqk;
    public zzp[] zzql;
    public String zzqm;
    public String zzqn;
    public long zzqo;
    public boolean zzqp;
    public zzp[] zzqq;
    public int[] zzqr;
    public boolean zzqs;

    private static int zzc(int i) {
        if (i > 0 && i <= 17) {
            return i;
        }
        StringBuilder stringBuilder = new StringBuilder(40);
        stringBuilder.append(i);
        stringBuilder.append(" is not a valid enum Escaping");
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public static zzp[] zzk() {
        if (zzqi == null) {
            synchronized (zzyg.zzcfc) {
                if (zzqi == null) {
                    zzqi = new zzp[0];
                }
            }
        }
        return zzqi;
    }

    public zzp() {
        this.type = 1;
        this.string = "";
        this.zzqj = zzk();
        this.zzqk = zzk();
        this.zzql = zzk();
        this.zzqm = "";
        this.zzqn = "";
        this.zzqo = 0;
        this.zzqp = false;
        this.zzqq = zzk();
        this.zzqr = zzyl.zzcao;
        this.zzqs = false;
        this.zzcet = null;
        this.zzcfd = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzp)) {
            return false;
        }
        zzp zzp = (zzp) obj;
        if (this.type != zzp.type) {
            return false;
        }
        if (this.string == null) {
            if (zzp.string != null) {
                return false;
            }
        } else if (!this.string.equals(zzp.string)) {
            return false;
        }
        if (!zzyg.equals(this.zzqj, zzp.zzqj) || !zzyg.equals(this.zzqk, zzp.zzqk) || !zzyg.equals(this.zzql, zzp.zzql)) {
            return false;
        }
        if (this.zzqm == null) {
            if (zzp.zzqm != null) {
                return false;
            }
        } else if (!this.zzqm.equals(zzp.zzqm)) {
            return false;
        }
        if (this.zzqn == null) {
            if (zzp.zzqn != null) {
                return false;
            }
        } else if (!this.zzqn.equals(zzp.zzqn)) {
            return false;
        }
        if (this.zzqo != zzp.zzqo || this.zzqp != zzp.zzqp || !zzyg.equals(this.zzqq, zzp.zzqq) || !zzyg.equals(this.zzqr, zzp.zzqr) || this.zzqs != zzp.zzqs) {
            return false;
        }
        if (this.zzcet == null || this.zzcet.isEmpty()) {
            return zzp.zzcet == null || zzp.zzcet.isEmpty();
        } else {
            return this.zzcet.equals(zzp.zzcet);
        }
    }

    public final int hashCode() {
        int i = 0;
        int i2 = 1237;
        int hashCode = (((((((((((((((((((((((527 + getClass().getName().hashCode()) * 31) + this.type) * 31) + (this.string == null ? 0 : this.string.hashCode())) * 31) + zzyg.hashCode(this.zzqj)) * 31) + zzyg.hashCode(this.zzqk)) * 31) + zzyg.hashCode(this.zzql)) * 31) + (this.zzqm == null ? 0 : this.zzqm.hashCode())) * 31) + (this.zzqn == null ? 0 : this.zzqn.hashCode())) * 31) + ((int) (this.zzqo ^ (this.zzqo >>> 32)))) * 31) + (this.zzqp ? 1231 : 1237)) * 31) + zzyg.hashCode(this.zzqq)) * 31) + zzyg.hashCode(this.zzqr)) * 31;
        if (this.zzqs) {
            i2 = 1231;
        }
        hashCode = (hashCode + i2) * 31;
        if (!(this.zzcet == null || this.zzcet.isEmpty())) {
            i = this.zzcet.hashCode();
        }
        return hashCode + i;
    }

    public final void zza(zzya zzya) throws IOException {
        zzya.zzd(1, this.type);
        if (!(this.string == null || this.string.equals(""))) {
            zzya.zzb(2, this.string);
        }
        int i = 0;
        if (this.zzqj != null && this.zzqj.length > 0) {
            for (zzyi zzyi : this.zzqj) {
                if (zzyi != null) {
                    zzya.zza(3, zzyi);
                }
            }
        }
        if (this.zzqk != null && this.zzqk.length > 0) {
            for (zzyi zzyi2 : this.zzqk) {
                if (zzyi2 != null) {
                    zzya.zza(4, zzyi2);
                }
            }
        }
        if (this.zzql != null && this.zzql.length > 0) {
            for (zzyi zzyi22 : this.zzql) {
                if (zzyi22 != null) {
                    zzya.zza(5, zzyi22);
                }
            }
        }
        if (!(this.zzqm == null || this.zzqm.equals(""))) {
            zzya.zzb(6, this.zzqm);
        }
        if (!(this.zzqn == null || this.zzqn.equals(""))) {
            zzya.zzb(7, this.zzqn);
        }
        if (this.zzqo != 0) {
            zzya.zzi(8, this.zzqo);
        }
        if (this.zzqs) {
            zzya.zzb(9, this.zzqs);
        }
        if (this.zzqr != null && this.zzqr.length > 0) {
            for (int zzd : this.zzqr) {
                zzya.zzd(10, zzd);
            }
        }
        if (this.zzqq != null && this.zzqq.length > 0) {
            while (i < this.zzqq.length) {
                zzyi zzyi3 = this.zzqq[i];
                if (zzyi3 != null) {
                    zzya.zza(11, zzyi3);
                }
                i++;
            }
        }
        if (this.zzqp) {
            zzya.zzb(12, this.zzqp);
        }
        super.zza(zzya);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzf() {
        int i;
        int zzf = super.zzf() + zzya.zzh(1, this.type);
        if (!(this.string == null || this.string.equals(""))) {
            zzf += zzya.zzc(2, this.string);
        }
        int i2 = 0;
        if (this.zzqj != null && this.zzqj.length > 0) {
            i = zzf;
            for (zzyi zzyi : this.zzqj) {
                if (zzyi != null) {
                    i += zzya.zzb(3, zzyi);
                }
            }
            zzf = i;
        }
        if (this.zzqk != null && this.zzqk.length > 0) {
            i = zzf;
            for (zzyi zzyi2 : this.zzqk) {
                if (zzyi2 != null) {
                    i += zzya.zzb(4, zzyi2);
                }
            }
            zzf = i;
        }
        if (this.zzql != null && this.zzql.length > 0) {
            i = zzf;
            for (zzyi zzyi22 : this.zzql) {
                if (zzyi22 != null) {
                    i += zzya.zzb(5, zzyi22);
                }
            }
            zzf = i;
        }
        if (!(this.zzqm == null || this.zzqm.equals(""))) {
            zzf += zzya.zzc(6, this.zzqm);
        }
        if (!(this.zzqn == null || this.zzqn.equals(""))) {
            zzf += zzya.zzc(7, this.zzqn);
        }
        if (this.zzqo != 0) {
            zzf += zzya.zzd(8, this.zzqo);
        }
        if (this.zzqs) {
            zzf += zzya.zzbd(9) + 1;
        }
        if (this.zzqr != null && this.zzqr.length > 0) {
            i = 0;
            int i3 = i;
            while (i < this.zzqr.length) {
                i3 += zzya.zzbe(this.zzqr[i]);
                i++;
            }
            zzf = (zzf + i3) + (this.zzqr.length * 1);
        }
        if (this.zzqq != null && this.zzqq.length > 0) {
            while (i2 < this.zzqq.length) {
                zzyi zzyi3 = this.zzqq[i2];
                if (zzyi3 != null) {
                    zzf += zzya.zzb(11, zzyi3);
                }
                i2++;
            }
        }
        return this.zzqp ? zzf + (zzya.zzbd(12) + 1) : zzf;
    }

    private final zzp zzb(zzxz zzxz) throws IOException {
        int zzvb;
        StringBuilder stringBuilder;
        while (true) {
            int zzuj = zzxz.zzuj();
            int length;
            zzp[] zzpArr;
            int i;
            switch (zzuj) {
                case 0:
                    return this;
                case 8:
                    try {
                        zzvb = zzxz.zzvb();
                        if (zzvb > 0 && zzvb <= 8) {
                            this.type = zzvb;
                            break;
                        }
                        stringBuilder = new StringBuilder(36);
                        stringBuilder.append(zzvb);
                        stringBuilder.append(" is not a valid enum Type");
                        break;
                    } catch (IllegalArgumentException unused) {
                        zzxz.zzcb(zzxz.getPosition());
                        zza(zzxz, zzuj);
                        break;
                    }
                    break;
                case 18:
                    this.string = zzxz.readString();
                    break;
                case 26:
                    zzuj = zzyl.zzb(zzxz, 26);
                    length = this.zzqj == null ? 0 : this.zzqj.length;
                    zzpArr = new zzp[(zzuj + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzqj, 0, zzpArr, 0, length);
                    }
                    while (length < zzpArr.length - 1) {
                        zzpArr[length] = new zzp();
                        zzxz.zza(zzpArr[length]);
                        zzxz.zzuj();
                        length++;
                    }
                    zzpArr[length] = new zzp();
                    zzxz.zza(zzpArr[length]);
                    this.zzqj = zzpArr;
                    break;
                case 34:
                    zzuj = zzyl.zzb(zzxz, 34);
                    length = this.zzqk == null ? 0 : this.zzqk.length;
                    zzpArr = new zzp[(zzuj + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzqk, 0, zzpArr, 0, length);
                    }
                    while (length < zzpArr.length - 1) {
                        zzpArr[length] = new zzp();
                        zzxz.zza(zzpArr[length]);
                        zzxz.zzuj();
                        length++;
                    }
                    zzpArr[length] = new zzp();
                    zzxz.zza(zzpArr[length]);
                    this.zzqk = zzpArr;
                    break;
                case 42:
                    zzuj = zzyl.zzb(zzxz, 42);
                    length = this.zzql == null ? 0 : this.zzql.length;
                    zzpArr = new zzp[(zzuj + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzql, 0, zzpArr, 0, length);
                    }
                    while (length < zzpArr.length - 1) {
                        zzpArr[length] = new zzp();
                        zzxz.zza(zzpArr[length]);
                        zzxz.zzuj();
                        length++;
                    }
                    zzpArr[length] = new zzp();
                    zzxz.zza(zzpArr[length]);
                    this.zzql = zzpArr;
                    break;
                case 50:
                    this.zzqm = zzxz.readString();
                    break;
                case 58:
                    this.zzqn = zzxz.readString();
                    break;
                case 64:
                    this.zzqo = zzxz.zzvc();
                    break;
                case 72:
                    this.zzqs = zzxz.zzup();
                    break;
                case 80:
                    length = zzyl.zzb(zzxz, 80);
                    int[] iArr = new int[length];
                    i = 0;
                    int i2 = i;
                    while (i < length) {
                        if (i != 0) {
                            zzxz.zzuj();
                        }
                        int position = zzxz.getPosition();
                        try {
                            iArr[i2] = zzc(zzxz.zzvb());
                            i2++;
                        } catch (IllegalArgumentException unused2) {
                            zzxz.zzcb(position);
                            zza(zzxz, zzuj);
                        }
                        i++;
                    }
                    if (i2 != 0) {
                        zzuj = this.zzqr == null ? 0 : this.zzqr.length;
                        if (zzuj != 0 || i2 != iArr.length) {
                            int[] iArr2 = new int[(zzuj + i2)];
                            if (zzuj != 0) {
                                System.arraycopy(this.zzqr, 0, iArr2, 0, zzuj);
                            }
                            System.arraycopy(iArr, 0, iArr2, zzuj, i2);
                            this.zzqr = iArr2;
                            break;
                        }
                        this.zzqr = iArr;
                        break;
                    }
                    break;
                case 82:
                    zzuj = zzxz.zzas(zzxz.zzvb());
                    int position2 = zzxz.getPosition();
                    i = 0;
                    while (zzxz.zzyy() > 0) {
                        try {
                            zzc(zzxz.zzvb());
                            i++;
                        } catch (IllegalArgumentException unused3) {
                        }
                    }
                    if (i != 0) {
                        zzxz.zzcb(position2);
                        position2 = this.zzqr == null ? 0 : this.zzqr.length;
                        int[] iArr3 = new int[(i + position2)];
                        if (position2 != 0) {
                            System.arraycopy(this.zzqr, 0, iArr3, 0, position2);
                        }
                        while (zzxz.zzyy() > 0) {
                            zzvb = zzxz.getPosition();
                            try {
                                iArr3[position2] = zzc(zzxz.zzvb());
                                position2++;
                            } catch (IllegalArgumentException unused4) {
                                zzxz.zzcb(zzvb);
                                zza(zzxz, 80);
                            }
                        }
                        this.zzqr = iArr3;
                    }
                    zzxz.zzat(zzuj);
                    break;
                case 90:
                    zzuj = zzyl.zzb(zzxz, 90);
                    length = this.zzqq == null ? 0 : this.zzqq.length;
                    zzpArr = new zzp[(zzuj + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzqq, 0, zzpArr, 0, length);
                    }
                    while (length < zzpArr.length - 1) {
                        zzpArr[length] = new zzp();
                        zzxz.zza(zzpArr[length]);
                        zzxz.zzuj();
                        length++;
                    }
                    zzpArr[length] = new zzp();
                    zzxz.zza(zzpArr[length]);
                    this.zzqq = zzpArr;
                    break;
                case 96:
                    this.zzqp = zzxz.zzup();
                    break;
                default:
                    if (super.zza(zzxz, zzuj)) {
                        break;
                    }
                    return this;
            }
        }
        stringBuilder = new StringBuilder(36);
        stringBuilder.append(zzvb);
        stringBuilder.append(" is not a valid enum Type");
        throw new IllegalArgumentException(stringBuilder.toString());
    }
}
