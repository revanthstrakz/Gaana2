package com.google.android.gms.internal.icing;

final class zzcj extends zzco {
    private final int zzgc;
    private final int zzgd;

    zzcj(byte[] bArr, int i, int i2) {
        super(bArr);
        zzce.zzb(i, i + i2, bArr.length);
        this.zzgc = i;
        this.zzgd = i2;
    }

    public final byte zzk(int i) {
        int size = size();
        if (((size - (i + 1)) | i) >= 0) {
            return this.zzgf[this.zzgc + i];
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

    /* Access modifiers changed, original: final */
    public final byte zzl(int i) {
        return this.zzgf[this.zzgc + i];
    }

    public final int size() {
        return this.zzgd;
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzar() {
        return this.zzgc;
    }
}
