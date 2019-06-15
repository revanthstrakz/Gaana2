package com.google.android.gms.internal.measurement;

final class zztj extends zzto {
    private final int zzbtu;
    private final int zzbtv;

    zztj(byte[] bArr, int i, int i2) {
        super(bArr);
        zzte.zzb(i, i + i2, bArr.length);
        this.zzbtu = i;
        this.zzbtv = i2;
    }

    public final byte zzam(int i) {
        int size = size();
        if (((size - (i + 1)) | i) >= 0) {
            return this.zzbtx[this.zzbtu + i];
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
    public final byte zzan(int i) {
        return this.zzbtx[this.zzbtu + i];
    }

    public final int size() {
        return this.zzbtv;
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzug() {
        return this.zzbtu;
    }
}
