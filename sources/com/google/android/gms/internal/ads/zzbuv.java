package com.google.android.gms.internal.ads;

public final class zzbuv implements Cloneable {
    private static final zzbuw zzfwm = new zzbuw();
    private int mSize;
    private boolean zzfwn;
    private int[] zzfwo;
    private zzbuw[] zzfwp;

    zzbuv() {
        this(10);
    }

    private zzbuv(int i) {
        this.zzfwn = false;
        i = idealIntArraySize(i);
        this.zzfwo = new int[i];
        this.zzfwp = new zzbuw[i];
        this.mSize = 0;
    }

    /* Access modifiers changed, original: final */
    public final zzbuw zzgf(int i) {
        i = zzgh(i);
        return (i < 0 || this.zzfwp[i] == zzfwm) ? null : this.zzfwp[i];
    }

    /* Access modifiers changed, original: final */
    public final void zza(int i, zzbuw zzbuw) {
        int zzgh = zzgh(i);
        if (zzgh >= 0) {
            this.zzfwp[zzgh] = zzbuw;
            return;
        }
        zzgh ^= -1;
        if (zzgh >= this.mSize || this.zzfwp[zzgh] != zzfwm) {
            if (this.mSize >= this.zzfwo.length) {
                int idealIntArraySize = idealIntArraySize(this.mSize + 1);
                int[] iArr = new int[idealIntArraySize];
                zzbuw[] zzbuwArr = new zzbuw[idealIntArraySize];
                System.arraycopy(this.zzfwo, 0, iArr, 0, this.zzfwo.length);
                System.arraycopy(this.zzfwp, 0, zzbuwArr, 0, this.zzfwp.length);
                this.zzfwo = iArr;
                this.zzfwp = zzbuwArr;
            }
            if (this.mSize - zzgh != 0) {
                int i2 = zzgh + 1;
                System.arraycopy(this.zzfwo, zzgh, this.zzfwo, i2, this.mSize - zzgh);
                System.arraycopy(this.zzfwp, zzgh, this.zzfwp, i2, this.mSize - zzgh);
            }
            this.zzfwo[zzgh] = i;
            this.zzfwp[zzgh] = zzbuw;
            this.mSize++;
            return;
        }
        this.zzfwo[zzgh] = i;
        this.zzfwp[zzgh] = zzbuw;
    }

    /* Access modifiers changed, original: final */
    public final int size() {
        return this.mSize;
    }

    /* Access modifiers changed, original: final */
    public final zzbuw zzgg(int i) {
        return this.zzfwp[i];
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbuv)) {
            return false;
        }
        zzbuv zzbuv = (zzbuv) obj;
        if (this.mSize != zzbuv.mSize) {
            return false;
        }
        boolean z;
        int[] iArr = this.zzfwo;
        int[] iArr2 = zzbuv.zzfwo;
        int i = this.mSize;
        for (int i2 = 0; i2 < i; i2++) {
            if (iArr[i2] != iArr2[i2]) {
                z = false;
                break;
            }
        }
        z = true;
        if (z) {
            boolean z2;
            zzbuw[] zzbuwArr = this.zzfwp;
            zzbuw[] zzbuwArr2 = zzbuv.zzfwp;
            int i3 = this.mSize;
            for (i = 0; i < i3; i++) {
                if (!zzbuwArr[i].equals(zzbuwArr2[i])) {
                    z2 = false;
                    break;
                }
            }
            z2 = true;
            if (z2) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = 17;
        for (int i2 = 0; i2 < this.mSize; i2++) {
            i = (((i * 31) + this.zzfwo[i2]) * 31) + this.zzfwp[i2].hashCode();
        }
        return i;
    }

    private static int idealIntArraySize(int i) {
        i <<= 2;
        for (int i2 = 4; i2 < 32; i2++) {
            int i3 = (1 << i2) - 12;
            if (i <= i3) {
                i = i3;
                break;
            }
        }
        return i / 4;
    }

    private final int zzgh(int i) {
        int i2 = this.mSize - 1;
        int i3 = 0;
        while (i3 <= i2) {
            int i4 = (i3 + i2) >>> 1;
            int i5 = this.zzfwo[i4];
            if (i5 < i) {
                i3 = i4 + 1;
            } else if (i5 <= i) {
                return i4;
            } else {
                i2 = i4 - 1;
            }
        }
        return i3 ^ -1;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        int i = this.mSize;
        zzbuv zzbuv = new zzbuv(i);
        int i2 = 0;
        System.arraycopy(this.zzfwo, 0, zzbuv.zzfwo, 0, i);
        while (i2 < i) {
            if (this.zzfwp[i2] != null) {
                zzbuv.zzfwp[i2] = (zzbuw) this.zzfwp[i2].clone();
            }
            i2++;
        }
        zzbuv.mSize = i;
        return zzbuv;
    }
}
