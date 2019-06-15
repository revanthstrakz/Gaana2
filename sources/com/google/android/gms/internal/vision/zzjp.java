package com.google.android.gms.internal.vision;

public final class zzjp implements Cloneable {
    private static final zzjq zzadi = new zzjq();
    private int mSize;
    private boolean zzadj;
    private int[] zzadk;
    private zzjq[] zzadl;

    zzjp() {
        this(10);
    }

    private zzjp(int i) {
        this.zzadj = false;
        i = idealIntArraySize(i);
        this.zzadk = new int[i];
        this.zzadl = new zzjq[i];
        this.mSize = 0;
    }

    /* Access modifiers changed, original: final */
    public final zzjq zzbw(int i) {
        i = zzby(i);
        return (i < 0 || this.zzadl[i] == zzadi) ? null : this.zzadl[i];
    }

    /* Access modifiers changed, original: final */
    public final void zza(int i, zzjq zzjq) {
        int zzby = zzby(i);
        if (zzby >= 0) {
            this.zzadl[zzby] = zzjq;
            return;
        }
        zzby ^= -1;
        if (zzby >= this.mSize || this.zzadl[zzby] != zzadi) {
            if (this.mSize >= this.zzadk.length) {
                int idealIntArraySize = idealIntArraySize(this.mSize + 1);
                int[] iArr = new int[idealIntArraySize];
                zzjq[] zzjqArr = new zzjq[idealIntArraySize];
                System.arraycopy(this.zzadk, 0, iArr, 0, this.zzadk.length);
                System.arraycopy(this.zzadl, 0, zzjqArr, 0, this.zzadl.length);
                this.zzadk = iArr;
                this.zzadl = zzjqArr;
            }
            if (this.mSize - zzby != 0) {
                int i2 = zzby + 1;
                System.arraycopy(this.zzadk, zzby, this.zzadk, i2, this.mSize - zzby);
                System.arraycopy(this.zzadl, zzby, this.zzadl, i2, this.mSize - zzby);
            }
            this.zzadk[zzby] = i;
            this.zzadl[zzby] = zzjq;
            this.mSize++;
            return;
        }
        this.zzadk[zzby] = i;
        this.zzadl[zzby] = zzjq;
    }

    /* Access modifiers changed, original: final */
    public final int size() {
        return this.mSize;
    }

    /* Access modifiers changed, original: final */
    public final zzjq zzbx(int i) {
        return this.zzadl[i];
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzjp)) {
            return false;
        }
        zzjp zzjp = (zzjp) obj;
        if (this.mSize != zzjp.mSize) {
            return false;
        }
        boolean z;
        int[] iArr = this.zzadk;
        int[] iArr2 = zzjp.zzadk;
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
            zzjq[] zzjqArr = this.zzadl;
            zzjq[] zzjqArr2 = zzjp.zzadl;
            int i3 = this.mSize;
            for (i = 0; i < i3; i++) {
                if (!zzjqArr[i].equals(zzjqArr2[i])) {
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
            i = (((i * 31) + this.zzadk[i2]) * 31) + this.zzadl[i2].hashCode();
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

    private final int zzby(int i) {
        int i2 = this.mSize - 1;
        int i3 = 0;
        while (i3 <= i2) {
            int i4 = (i3 + i2) >>> 1;
            int i5 = this.zzadk[i4];
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
        zzjp zzjp = new zzjp(i);
        int i2 = 0;
        System.arraycopy(this.zzadk, 0, zzjp.zzadk, 0, i);
        while (i2 < i) {
            if (this.zzadl[i2] != null) {
                zzjp.zzadl[i2] = (zzjq) this.zzadl[i2].clone();
            }
            i2++;
        }
        zzjp.mSize = i;
        return zzjp;
    }
}
