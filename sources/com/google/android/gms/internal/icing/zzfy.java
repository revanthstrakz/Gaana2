package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.zzdj.zzd;
import java.io.IOException;
import java.util.Arrays;

public final class zzfy {
    private static final zzfy zznu = new zzfy(0, new int[0], new Object[0], false);
    private int count;
    private boolean zzfs;
    private int zzju;
    private Object[] zzmg;
    private int[] zznv;

    public static zzfy zzdp() {
        return zznu;
    }

    static zzfy zza(zzfy zzfy, zzfy zzfy2) {
        int i = zzfy.count + zzfy2.count;
        int[] copyOf = Arrays.copyOf(zzfy.zznv, i);
        System.arraycopy(zzfy2.zznv, 0, copyOf, zzfy.count, zzfy2.count);
        Object[] copyOf2 = Arrays.copyOf(zzfy.zzmg, i);
        System.arraycopy(zzfy2.zzmg, 0, copyOf2, zzfy.count, zzfy2.count);
        return new zzfy(i, copyOf, copyOf2, true);
    }

    private zzfy() {
        this(0, new int[8], new Object[8], true);
    }

    private zzfy(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zzju = -1;
        this.count = i;
        this.zznv = iArr;
        this.zzmg = objArr;
        this.zzfs = z;
    }

    public final void zzaj() {
        this.zzfs = false;
    }

    /* Access modifiers changed, original: final */
    public final void zza(zzgr zzgr) throws IOException {
        int i;
        if (zzgr.zzay() == zzd.zzkm) {
            for (i = this.count - 1; i >= 0; i--) {
                zzgr.zza(this.zznv[i] >>> 3, this.zzmg[i]);
            }
            return;
        }
        for (i = 0; i < this.count; i++) {
            zzgr.zza(this.zznv[i] >>> 3, this.zzmg[i]);
        }
    }

    public final void zzb(zzgr zzgr) throws IOException {
        if (this.count != 0) {
            int i;
            if (zzgr.zzay() == zzd.zzkl) {
                for (i = 0; i < this.count; i++) {
                    zzb(this.zznv[i], this.zzmg[i], zzgr);
                }
                return;
            }
            for (i = this.count - 1; i >= 0; i--) {
                zzb(this.zznv[i], this.zzmg[i], zzgr);
            }
        }
    }

    private static void zzb(int i, Object obj, zzgr zzgr) throws IOException {
        int i2 = i >>> 3;
        i &= 7;
        if (i != 5) {
            switch (i) {
                case 0:
                    zzgr.zzi(i2, ((Long) obj).longValue());
                    return;
                case 1:
                    zzgr.zzc(i2, ((Long) obj).longValue());
                    return;
                case 2:
                    zzgr.zza(i2, (zzce) obj);
                    return;
                case 3:
                    if (zzgr.zzay() == zzd.zzkl) {
                        zzgr.zzab(i2);
                        ((zzfy) obj).zzb(zzgr);
                        zzgr.zzac(i2);
                        return;
                    }
                    zzgr.zzac(i2);
                    ((zzfy) obj).zzb(zzgr);
                    zzgr.zzab(i2);
                    return;
                default:
                    throw new RuntimeException(zzdr.zzcc());
            }
        }
        zzgr.zzf(i2, ((Integer) obj).intValue());
    }

    public final int zzdq() {
        int i = this.zzju;
        if (i != -1) {
            return i;
        }
        i = 0;
        int i2 = 0;
        while (i < this.count) {
            i2 += zzct.zzd(this.zznv[i] >>> 3, (zzce) this.zzmg[i]);
            i++;
        }
        this.zzju = i2;
        return i2;
    }

    public final int zzbi() {
        int i = this.zzju;
        if (i != -1) {
            return i;
        }
        i = 0;
        int i2 = 0;
        while (i < this.count) {
            int i3 = this.zznv[i];
            int i4 = i3 >>> 3;
            i3 &= 7;
            if (i3 != 5) {
                switch (i3) {
                    case 0:
                        i3 = zzct.zze(i4, ((Long) this.zzmg[i]).longValue());
                        break;
                    case 1:
                        i3 = zzct.zzg(i4, ((Long) this.zzmg[i]).longValue());
                        break;
                    case 2:
                        i3 = zzct.zzc(i4, (zzce) this.zzmg[i]);
                        break;
                    case 3:
                        i3 = (zzct.zzs(i4) << 1) + ((zzfy) this.zzmg[i]).zzbi();
                        break;
                    default:
                        throw new IllegalStateException(zzdr.zzcc());
                }
            }
            i3 = zzct.zzj(i4, ((Integer) this.zzmg[i]).intValue());
            i2 += i3;
            i++;
        }
        this.zzju = i2;
        return i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzfy)) {
            return false;
        }
        zzfy zzfy = (zzfy) obj;
        if (this.count == zzfy.count) {
            boolean z;
            int[] iArr = this.zznv;
            int[] iArr2 = zzfy.zznv;
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
                Object[] objArr = this.zzmg;
                Object[] objArr2 = zzfy.zzmg;
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
        int[] iArr = this.zznv;
        int i2 = 0;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < this.count; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        i = (i + i4) * 31;
        Object[] objArr = this.zzmg;
        while (i2 < this.count) {
            i3 = (i3 * 31) + objArr[i2].hashCode();
            i2++;
        }
        return i + i3;
    }

    /* Access modifiers changed, original: final */
    public final void zza(StringBuilder stringBuilder, int i) {
        for (int i2 = 0; i2 < this.count; i2++) {
            zzet.zza(stringBuilder, i, String.valueOf(this.zznv[i2] >>> 3), this.zzmg[i2]);
        }
    }
}
