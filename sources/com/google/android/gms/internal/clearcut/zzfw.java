package com.google.android.gms.internal.clearcut;

public final class zzfw implements Cloneable {
    private static final zzfx zzrl = new zzfx();
    private int mSize;
    private boolean zzrm;
    private int[] zzrn;
    private zzfx[] zzro;

    zzfw() {
        this(10);
    }

    private zzfw(int i) {
        this.zzrm = false;
        i <<= 2;
        for (int i2 = 4; i2 < 32; i2++) {
            int i3 = (1 << i2) - 12;
            if (i <= i3) {
                i = i3;
                break;
            }
        }
        i /= 4;
        this.zzrn = new int[i];
        this.zzro = new zzfx[i];
        this.mSize = 0;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        int i = this.mSize;
        zzfw zzfw = new zzfw(i);
        int i2 = 0;
        System.arraycopy(this.zzrn, 0, zzfw.zzrn, 0, i);
        while (i2 < i) {
            if (this.zzro[i2] != null) {
                zzfw.zzro[i2] = (zzfx) this.zzro[i2].clone();
            }
            i2++;
        }
        zzfw.mSize = i;
        return zzfw;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfw)) {
            return false;
        }
        zzfw zzfw = (zzfw) obj;
        if (this.mSize != zzfw.mSize) {
            return false;
        }
        boolean z;
        int[] iArr = this.zzrn;
        int[] iArr2 = zzfw.zzrn;
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
            zzfx[] zzfxArr = this.zzro;
            zzfx[] zzfxArr2 = zzfw.zzro;
            int i3 = this.mSize;
            for (i = 0; i < i3; i++) {
                if (!zzfxArr[i].equals(zzfxArr2[i])) {
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
            i = (((i * 31) + this.zzrn[i2]) * 31) + this.zzro[i2].hashCode();
        }
        return i;
    }

    public final boolean isEmpty() {
        return this.mSize == 0;
    }

    /* Access modifiers changed, original: final */
    public final int size() {
        return this.mSize;
    }

    /* Access modifiers changed, original: final */
    public final zzfx zzaq(int i) {
        return this.zzro[i];
    }
}
