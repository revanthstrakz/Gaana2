package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzfy.zzg;
import java.io.IOException;
import java.util.Arrays;

public final class zzip {
    private static final zzip zzaas = new zzip(0, new int[0], new Object[0], false);
    private int count;
    private int[] zzaat;
    private boolean zzrl;
    private int zzwk;
    private Object[] zzze;

    public static zzip zzhe() {
        return zzaas;
    }

    static zzip zzhf() {
        return new zzip();
    }

    static zzip zza(zzip zzip, zzip zzip2) {
        int i = zzip.count + zzip2.count;
        int[] copyOf = Arrays.copyOf(zzip.zzaat, i);
        System.arraycopy(zzip2.zzaat, 0, copyOf, zzip.count, zzip2.count);
        Object[] copyOf2 = Arrays.copyOf(zzip.zzze, i);
        System.arraycopy(zzip2.zzze, 0, copyOf2, zzip.count, zzip2.count);
        return new zzip(i, copyOf, copyOf2, true);
    }

    private zzip() {
        this(0, new int[8], new Object[8], true);
    }

    private zzip(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zzwk = -1;
        this.count = i;
        this.zzaat = iArr;
        this.zzze = objArr;
        this.zzrl = z;
    }

    public final void zzci() {
        this.zzrl = false;
    }

    /* Access modifiers changed, original: final */
    public final void zza(zzjj zzjj) throws IOException {
        int i;
        if (zzjj.zzed() == zzg.zzxj) {
            for (i = this.count - 1; i >= 0; i--) {
                zzjj.zza(this.zzaat[i] >>> 3, this.zzze[i]);
            }
            return;
        }
        for (i = 0; i < this.count; i++) {
            zzjj.zza(this.zzaat[i] >>> 3, this.zzze[i]);
        }
    }

    public final void zzb(zzjj zzjj) throws IOException {
        if (this.count != 0) {
            int i;
            if (zzjj.zzed() == zzg.zzxi) {
                for (i = 0; i < this.count; i++) {
                    zzb(this.zzaat[i], this.zzze[i], zzjj);
                }
                return;
            }
            for (i = this.count - 1; i >= 0; i--) {
                zzb(this.zzaat[i], this.zzze[i], zzjj);
            }
        }
    }

    private static void zzb(int i, Object obj, zzjj zzjj) throws IOException {
        int i2 = i >>> 3;
        i &= 7;
        if (i != 5) {
            switch (i) {
                case 0:
                    zzjj.zzi(i2, ((Long) obj).longValue());
                    return;
                case 1:
                    zzjj.zzc(i2, ((Long) obj).longValue());
                    return;
                case 2:
                    zzjj.zza(i2, (zzeo) obj);
                    return;
                case 3:
                    if (zzjj.zzed() == zzg.zzxi) {
                        zzjj.zzbe(i2);
                        ((zzip) obj).zzb(zzjj);
                        zzjj.zzbf(i2);
                        return;
                    }
                    zzjj.zzbf(i2);
                    ((zzip) obj).zzb(zzjj);
                    zzjj.zzbe(i2);
                    return;
                default:
                    throw new RuntimeException(zzgf.zzfm());
            }
        }
        zzjj.zzh(i2, ((Integer) obj).intValue());
    }

    public final int zzhg() {
        int i = this.zzwk;
        if (i != -1) {
            return i;
        }
        i = 0;
        int i2 = 0;
        while (i < this.count) {
            i2 += zzfe.zzd(this.zzaat[i] >>> 3, (zzeo) this.zzze[i]);
            i++;
        }
        this.zzwk = i2;
        return i2;
    }

    public final int zzeq() {
        int i = this.zzwk;
        if (i != -1) {
            return i;
        }
        i = 0;
        int i2 = 0;
        while (i < this.count) {
            int i3 = this.zzaat[i];
            int i4 = i3 >>> 3;
            i3 &= 7;
            if (i3 != 5) {
                switch (i3) {
                    case 0:
                        i3 = zzfe.zze(i4, ((Long) this.zzze[i]).longValue());
                        break;
                    case 1:
                        i3 = zzfe.zzg(i4, ((Long) this.zzze[i]).longValue());
                        break;
                    case 2:
                        i3 = zzfe.zzc(i4, (zzeo) this.zzze[i]);
                        break;
                    case 3:
                        i3 = (zzfe.zzav(i4) << 1) + ((zzip) this.zzze[i]).zzeq();
                        break;
                    default:
                        throw new IllegalStateException(zzgf.zzfm());
                }
            }
            i3 = zzfe.zzl(i4, ((Integer) this.zzze[i]).intValue());
            i2 += i3;
            i++;
        }
        this.zzwk = i2;
        return i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzip)) {
            return false;
        }
        zzip zzip = (zzip) obj;
        if (this.count == zzip.count) {
            boolean z;
            int[] iArr = this.zzaat;
            int[] iArr2 = zzip.zzaat;
            int i = this.count;
            for (int i2 = 0; i2 < i; i2++) {
                if (iArr[i2] != iArr2[i2]) {
                    z = false;
                    break;
                }
            }
            z = true;
            if (z) {
                boolean z2;
                Object[] objArr = this.zzze;
                Object[] objArr2 = zzip.zzze;
                int i3 = this.count;
                for (i = 0; i < i3; i++) {
                    if (!objArr[i].equals(objArr2[i])) {
                        z2 = false;
                        break;
                    }
                }
                z2 = true;
                return z2;
            }
        }
    }

    public final int hashCode() {
        int i = (527 + this.count) * 31;
        int[] iArr = this.zzaat;
        int i2 = 0;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < this.count; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        i = (i + i4) * 31;
        Object[] objArr = this.zzze;
        while (i2 < this.count) {
            i3 = (i3 * 31) + objArr[i2].hashCode();
            i2++;
        }
        return i + i3;
    }

    /* Access modifiers changed, original: final */
    public final void zza(StringBuilder stringBuilder, int i) {
        for (int i2 = 0; i2 < this.count; i2++) {
            zzhi.zza(stringBuilder, i, String.valueOf(this.zzaat[i2] >>> 3), this.zzze[i2]);
        }
    }

    /* Access modifiers changed, original: final */
    public final void zzb(int i, Object obj) {
        if (this.zzrl) {
            if (this.count == this.zzaat.length) {
                int i2 = this.count + (this.count < 4 ? 8 : this.count >> 1);
                this.zzaat = Arrays.copyOf(this.zzaat, i2);
                this.zzze = Arrays.copyOf(this.zzze, i2);
            }
            this.zzaat[this.count] = i;
            this.zzze[this.count] = obj;
            this.count++;
            return;
        }
        throw new UnsupportedOperationException();
    }
}
