package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zze;
import java.io.IOException;
import java.util.Arrays;

public final class zzbtv {
    private static final zzbtv zzftx = new zzbtv(0, new int[0], new Object[0], false);
    private int count;
    private boolean zzfla;
    private int zzfpv;
    private Object[] zzfsj;
    private int[] zzfty;

    public static zzbtv zzaoz() {
        return zzftx;
    }

    static zzbtv zzapa() {
        return new zzbtv();
    }

    static zzbtv zza(zzbtv zzbtv, zzbtv zzbtv2) {
        int i = zzbtv.count + zzbtv2.count;
        int[] copyOf = Arrays.copyOf(zzbtv.zzfty, i);
        System.arraycopy(zzbtv2.zzfty, 0, copyOf, zzbtv.count, zzbtv2.count);
        Object[] copyOf2 = Arrays.copyOf(zzbtv.zzfsj, i);
        System.arraycopy(zzbtv2.zzfsj, 0, copyOf2, zzbtv.count, zzbtv2.count);
        return new zzbtv(i, copyOf, copyOf2, true);
    }

    private zzbtv() {
        this(0, new int[8], new Object[8], true);
    }

    private zzbtv(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zzfpv = -1;
        this.count = i;
        this.zzfty = iArr;
        this.zzfsj = objArr;
        this.zzfla = z;
    }

    public final void zzakj() {
        this.zzfla = false;
    }

    /* Access modifiers changed, original: final */
    public final void zza(zzbup zzbup) throws IOException {
        int i;
        if (zzbup.zzaly() == zze.zzfqn) {
            for (i = this.count - 1; i >= 0; i--) {
                zzbup.zzb(this.zzfty[i] >>> 3, this.zzfsj[i]);
            }
            return;
        }
        for (i = 0; i < this.count; i++) {
            zzbup.zzb(this.zzfty[i] >>> 3, this.zzfsj[i]);
        }
    }

    public final void zzb(zzbup zzbup) throws IOException {
        if (this.count != 0) {
            int i;
            if (zzbup.zzaly() == zze.zzfqm) {
                for (i = 0; i < this.count; i++) {
                    zzb(this.zzfty[i], this.zzfsj[i], zzbup);
                }
                return;
            }
            for (i = this.count - 1; i >= 0; i--) {
                zzb(this.zzfty[i], this.zzfsj[i], zzbup);
            }
        }
    }

    private static void zzb(int i, Object obj, zzbup zzbup) throws IOException {
        int i2 = i >>> 3;
        i &= 7;
        if (i != 5) {
            switch (i) {
                case 0:
                    zzbup.zzr(i2, ((Long) obj).longValue());
                    return;
                case 1:
                    zzbup.zzl(i2, ((Long) obj).longValue());
                    return;
                case 2:
                    zzbup.zza(i2, (zzbpu) obj);
                    return;
                case 3:
                    if (zzbup.zzaly() == zze.zzfqm) {
                        zzbup.zzfm(i2);
                        ((zzbtv) obj).zzb(zzbup);
                        zzbup.zzfn(i2);
                        return;
                    }
                    zzbup.zzfn(i2);
                    ((zzbtv) obj).zzb(zzbup);
                    zzbup.zzfm(i2);
                    return;
                default:
                    throw new RuntimeException(zzbrl.zzanh());
            }
        }
        zzbup.zzy(i2, ((Integer) obj).intValue());
    }

    public final int zzapb() {
        int i = this.zzfpv;
        if (i != -1) {
            return i;
        }
        i = 0;
        int i2 = 0;
        while (i < this.count) {
            i2 += zzbqk.zzd(this.zzfty[i] >>> 3, (zzbpu) this.zzfsj[i]);
            i++;
        }
        this.zzfpv = i2;
        return i2;
    }

    public final int zzamj() {
        int i = this.zzfpv;
        if (i != -1) {
            return i;
        }
        i = 0;
        int i2 = 0;
        while (i < this.count) {
            int i3 = this.zzfty[i];
            int i4 = i3 >>> 3;
            i3 &= 7;
            if (i3 != 5) {
                switch (i3) {
                    case 0:
                        i3 = zzbqk.zzn(i4, ((Long) this.zzfsj[i]).longValue());
                        break;
                    case 1:
                        i3 = zzbqk.zzp(i4, ((Long) this.zzfsj[i]).longValue());
                        break;
                    case 2:
                        i3 = zzbqk.zzc(i4, (zzbpu) this.zzfsj[i]);
                        break;
                    case 3:
                        i3 = (zzbqk.zzfd(i4) << 1) + ((zzbtv) this.zzfsj[i]).zzamj();
                        break;
                    default:
                        throw new IllegalStateException(zzbrl.zzanh());
                }
            }
            i3 = zzbqk.zzac(i4, ((Integer) this.zzfsj[i]).intValue());
            i2 += i3;
            i++;
        }
        this.zzfpv = i2;
        return i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzbtv)) {
            return false;
        }
        zzbtv zzbtv = (zzbtv) obj;
        if (this.count == zzbtv.count) {
            boolean z;
            int[] iArr = this.zzfty;
            int[] iArr2 = zzbtv.zzfty;
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
                Object[] objArr = this.zzfsj;
                Object[] objArr2 = zzbtv.zzfsj;
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
        int[] iArr = this.zzfty;
        int i2 = 0;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < this.count; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        i = (i + i4) * 31;
        Object[] objArr = this.zzfsj;
        while (i2 < this.count) {
            i3 = (i3 * 31) + objArr[i2].hashCode();
            i2++;
        }
        return i + i3;
    }

    /* Access modifiers changed, original: final */
    public final void zza(StringBuilder stringBuilder, int i) {
        for (int i2 = 0; i2 < this.count; i2++) {
            zzbso.zza(stringBuilder, i, String.valueOf(this.zzfty[i2] >>> 3), this.zzfsj[i2]);
        }
    }

    /* Access modifiers changed, original: final */
    public final void zzc(int i, Object obj) {
        if (this.zzfla) {
            if (this.count == this.zzfty.length) {
                int i2 = this.count + (this.count < 4 ? 8 : this.count >> 1);
                this.zzfty = Arrays.copyOf(this.zzfty, i2);
                this.zzfsj = Arrays.copyOf(this.zzfsj, i2);
            }
            this.zzfty[this.count] = i;
            this.zzfsj[this.count] = obj;
            this.count++;
            return;
        }
        throw new UnsupportedOperationException();
    }
}
