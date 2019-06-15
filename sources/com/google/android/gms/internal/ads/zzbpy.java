package com.google.android.gms.internal.ads;

final class zzbpy extends zzbqd {
    private final int zzflm;
    private final int zzfln;

    zzbpy(byte[] bArr, int i, int i2) {
        super(bArr);
        zzbpu.zzg(i, i + i2, bArr.length);
        this.zzflm = i;
        this.zzfln = i2;
    }

    public final byte zzem(int i) {
        int size = size();
        if (((size - (i + 1)) | i) >= 0) {
            return this.zzflp[this.zzflm + i];
        }
        if (i < 0) {
            StringBuilder stringBuilder = new StringBuilder(22);
            stringBuilder.append("Index < 0: ");
            stringBuilder.append(i);
            throw new ArrayIndexOutOfBoundsException(stringBuilder.toString());
        }
        StringBuilder stringBuilder2 = new StringBuilder(40);
        stringBuilder2.append("Index > length: ");
        stringBuilder2.append(i);
        stringBuilder2.append(", ");
        stringBuilder2.append(size);
        throw new ArrayIndexOutOfBoundsException(stringBuilder2.toString());
    }

    public final int size() {
        return this.zzfln;
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzakr() {
        return this.zzflm;
    }

    /* Access modifiers changed, original: protected|final */
    public final void zza(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zzflp, zzakr(), bArr, 0, i3);
    }
}
