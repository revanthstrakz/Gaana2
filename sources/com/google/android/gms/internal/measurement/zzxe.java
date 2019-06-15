package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzuo.zze;
import java.io.IOException;
import java.util.Arrays;

public final class zzxe {
    private static final zzxe zzccf = new zzxe(0, new int[0], new Object[0], false);
    private int count;
    private boolean zzbtl;
    private int zzbye;
    private Object[] zzcar;
    private int[] zzccg;

    public static zzxe zzyl() {
        return zzccf;
    }

    static zzxe zzym() {
        return new zzxe();
    }

    static zzxe zza(zzxe zzxe, zzxe zzxe2) {
        int i = zzxe.count + zzxe2.count;
        int[] copyOf = Arrays.copyOf(zzxe.zzccg, i);
        System.arraycopy(zzxe2.zzccg, 0, copyOf, zzxe.count, zzxe2.count);
        Object[] copyOf2 = Arrays.copyOf(zzxe.zzcar, i);
        System.arraycopy(zzxe2.zzcar, 0, copyOf2, zzxe.count, zzxe2.count);
        return new zzxe(i, copyOf, copyOf2, true);
    }

    private zzxe() {
        this(0, new int[8], new Object[8], true);
    }

    private zzxe(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zzbye = -1;
        this.count = i;
        this.zzccg = iArr;
        this.zzcar = objArr;
        this.zzbtl = z;
    }

    public final void zzsw() {
        this.zzbtl = false;
    }

    /* Access modifiers changed, original: final */
    public final void zza(zzxy zzxy) throws IOException {
        int i;
        if (zzxy.zzvm() == zze.zzbyw) {
            for (i = this.count - 1; i >= 0; i--) {
                zzxy.zza(this.zzccg[i] >>> 3, this.zzcar[i]);
            }
            return;
        }
        for (i = 0; i < this.count; i++) {
            zzxy.zza(this.zzccg[i] >>> 3, this.zzcar[i]);
        }
    }

    public final void zzb(zzxy zzxy) throws IOException {
        if (this.count != 0) {
            int i;
            if (zzxy.zzvm() == zze.zzbyv) {
                for (i = 0; i < this.count; i++) {
                    zzb(this.zzccg[i], this.zzcar[i], zzxy);
                }
                return;
            }
            for (i = this.count - 1; i >= 0; i--) {
                zzb(this.zzccg[i], this.zzcar[i], zzxy);
            }
        }
    }

    private static void zzb(int i, Object obj, zzxy zzxy) throws IOException {
        int i2 = i >>> 3;
        i &= 7;
        if (i != 5) {
            switch (i) {
                case 0:
                    zzxy.zzi(i2, ((Long) obj).longValue());
                    return;
                case 1:
                    zzxy.zzc(i2, ((Long) obj).longValue());
                    return;
                case 2:
                    zzxy.zza(i2, (zzte) obj);
                    return;
                case 3:
                    if (zzxy.zzvm() == zze.zzbyv) {
                        zzxy.zzbm(i2);
                        ((zzxe) obj).zzb(zzxy);
                        zzxy.zzbn(i2);
                        return;
                    }
                    zzxy.zzbn(i2);
                    ((zzxe) obj).zzb(zzxy);
                    zzxy.zzbm(i2);
                    return;
                default:
                    throw new RuntimeException(zzuv.zzwu());
            }
        }
        zzxy.zzg(i2, ((Integer) obj).intValue());
    }

    public final int zzyn() {
        int i = this.zzbye;
        if (i != -1) {
            return i;
        }
        i = 0;
        int i2 = 0;
        while (i < this.count) {
            i2 += zztv.zzd(this.zzccg[i] >>> 3, (zzte) this.zzcar[i]);
            i++;
        }
        this.zzbye = i2;
        return i2;
    }

    public final int zzvx() {
        int i = this.zzbye;
        if (i != -1) {
            return i;
        }
        i = 0;
        int i2 = 0;
        while (i < this.count) {
            int i3 = this.zzccg[i];
            int i4 = i3 >>> 3;
            i3 &= 7;
            if (i3 != 5) {
                switch (i3) {
                    case 0:
                        i3 = zztv.zze(i4, ((Long) this.zzcar[i]).longValue());
                        break;
                    case 1:
                        i3 = zztv.zzg(i4, ((Long) this.zzcar[i]).longValue());
                        break;
                    case 2:
                        i3 = zztv.zzc(i4, (zzte) this.zzcar[i]);
                        break;
                    case 3:
                        i3 = (zztv.zzbd(i4) << 1) + ((zzxe) this.zzcar[i]).zzvx();
                        break;
                    default:
                        throw new IllegalStateException(zzuv.zzwu());
                }
            }
            i3 = zztv.zzk(i4, ((Integer) this.zzcar[i]).intValue());
            i2 += i3;
            i++;
        }
        this.zzbye = i2;
        return i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzxe)) {
            return false;
        }
        zzxe zzxe = (zzxe) obj;
        if (this.count == zzxe.count) {
            boolean z;
            int[] iArr = this.zzccg;
            int[] iArr2 = zzxe.zzccg;
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
                Object[] objArr = this.zzcar;
                Object[] objArr2 = zzxe.zzcar;
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
        int[] iArr = this.zzccg;
        int i2 = 0;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < this.count; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        i = (i + i4) * 31;
        Object[] objArr = this.zzcar;
        while (i2 < this.count) {
            i3 = (i3 * 31) + objArr[i2].hashCode();
            i2++;
        }
        return i + i3;
    }

    /* Access modifiers changed, original: final */
    public final void zzb(StringBuilder stringBuilder, int i) {
        for (int i2 = 0; i2 < this.count; i2++) {
            zzvy.zzb(stringBuilder, i, String.valueOf(this.zzccg[i2] >>> 3), this.zzcar[i2]);
        }
    }

    /* Access modifiers changed, original: final */
    public final void zzb(int i, Object obj) {
        if (this.zzbtl) {
            if (this.count == this.zzccg.length) {
                int i2 = this.count + (this.count < 4 ? 8 : this.count >> 1);
                this.zzccg = Arrays.copyOf(this.zzccg, i2);
                this.zzcar = Arrays.copyOf(this.zzcar, i2);
            }
            this.zzccg[this.count] = i;
            this.zzcar[this.count] = obj;
            this.count++;
            return;
        }
        throw new UnsupportedOperationException();
    }
}
