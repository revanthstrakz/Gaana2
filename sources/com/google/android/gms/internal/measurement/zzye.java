package com.google.android.gms.internal.measurement;

public final class zzye implements Cloneable {
    private static final zzyf zzcew = new zzyf();
    private int mSize;
    private boolean zzcex;
    private int[] zzcey;
    private zzyf[] zzcez;

    zzye() {
        this(10);
    }

    private zzye(int i) {
        this.zzcex = false;
        i = idealIntArraySize(i);
        this.zzcey = new int[i];
        this.zzcez = new zzyf[i];
        this.mSize = 0;
    }

    /* Access modifiers changed, original: final */
    public final zzyf zzce(int i) {
        i = zzcg(i);
        return (i < 0 || this.zzcez[i] == zzcew) ? null : this.zzcez[i];
    }

    /* Access modifiers changed, original: final */
    public final void zza(int i, zzyf zzyf) {
        int zzcg = zzcg(i);
        if (zzcg >= 0) {
            this.zzcez[zzcg] = zzyf;
            return;
        }
        zzcg ^= -1;
        if (zzcg >= this.mSize || this.zzcez[zzcg] != zzcew) {
            if (this.mSize >= this.zzcey.length) {
                int idealIntArraySize = idealIntArraySize(this.mSize + 1);
                int[] iArr = new int[idealIntArraySize];
                zzyf[] zzyfArr = new zzyf[idealIntArraySize];
                System.arraycopy(this.zzcey, 0, iArr, 0, this.zzcey.length);
                System.arraycopy(this.zzcez, 0, zzyfArr, 0, this.zzcez.length);
                this.zzcey = iArr;
                this.zzcez = zzyfArr;
            }
            if (this.mSize - zzcg != 0) {
                int i2 = zzcg + 1;
                System.arraycopy(this.zzcey, zzcg, this.zzcey, i2, this.mSize - zzcg);
                System.arraycopy(this.zzcez, zzcg, this.zzcez, i2, this.mSize - zzcg);
            }
            this.zzcey[zzcg] = i;
            this.zzcez[zzcg] = zzyf;
            this.mSize++;
            return;
        }
        this.zzcey[zzcg] = i;
        this.zzcez[zzcg] = zzyf;
    }

    /* Access modifiers changed, original: final */
    public final int size() {
        return this.mSize;
    }

    public final boolean isEmpty() {
        return this.mSize == 0;
    }

    /* Access modifiers changed, original: final */
    public final zzyf zzcf(int i) {
        return this.zzcez[i];
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzye)) {
            return false;
        }
        zzye zzye = (zzye) obj;
        if (this.mSize != zzye.mSize) {
            return false;
        }
        boolean z;
        int[] iArr = this.zzcey;
        int[] iArr2 = zzye.zzcey;
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
            zzyf[] zzyfArr = this.zzcez;
            zzyf[] zzyfArr2 = zzye.zzcez;
            int i3 = this.mSize;
            for (i = 0; i < i3; i++) {
                if (!zzyfArr[i].equals(zzyfArr2[i])) {
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
            i = (((i * 31) + this.zzcey[i2]) * 31) + this.zzcez[i2].hashCode();
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

    private final int zzcg(int i) {
        int i2 = this.mSize - 1;
        int i3 = 0;
        while (i3 <= i2) {
            int i4 = (i3 + i2) >>> 1;
            int i5 = this.zzcey[i4];
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
        zzye zzye = new zzye(i);
        int i2 = 0;
        System.arraycopy(this.zzcey, 0, zzye.zzcey, 0, i);
        while (i2 < i) {
            if (this.zzcez[i2] != null) {
                zzye.zzcez[i2] = (zzyf) this.zzcez[i2].clone();
            }
            i2++;
        }
        zzye.mSize = i;
        return zzye;
    }
}
