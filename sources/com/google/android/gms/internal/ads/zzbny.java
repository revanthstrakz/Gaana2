package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidKeyException;

final class zzbny extends zzbpa {
    private static final byte[] zzfis = new byte[16];

    zzbny(byte[] bArr, int i) throws InvalidKeyException {
        super(bArr, i);
    }

    /* Access modifiers changed, original: final */
    public final int zzajy() {
        return 12;
    }

    /* Access modifiers changed, original: final */
    public final ByteBuffer zzd(byte[] bArr, int i) {
        int i2 = 16;
        int[] iArr = new int[16];
        int i3 = 13;
        int i4 = 4;
        System.arraycopy(zzbpa.zzfkg, 0, iArr, 0, zzfkg.length);
        int[] zzm = zzbpa.zzm(ByteBuffer.wrap(this.zzfkh.getBytes()));
        System.arraycopy(zzm, 0, iArr, 4, zzm.length);
        iArr[12] = i;
        System.arraycopy(zzbpa.zzm(ByteBuffer.wrap(bArr)), 0, iArr, 13, 3);
        zzm = (int[]) iArr.clone();
        int i5 = 0;
        while (i5 < 10) {
            zza(zzm, 0, i4, 8, 12);
            zza(zzm, 1, 5, 9, i3);
            zza(zzm, 2, 6, 10, 14);
            zza(zzm, 3, 7, 11, 15);
            zza(zzm, 0, 5, 10, 15);
            zza(zzm, 1, 6, 11, 12);
            zza(zzm, 2, 7, 8, 13);
            zza(zzm, 3, 4, 9, 14);
            i5++;
            i4 = 4;
            i3 = 13;
            i2 = 16;
        }
        i2 = 0;
        for (int i6 = i2; i2 < i6; i6 = 16) {
            iArr[i2] = iArr[i2] + zzm[i2];
            i2++;
        }
        ByteBuffer order = ByteBuffer.allocate(64).order(ByteOrder.LITTLE_ENDIAN);
        order.asIntBuffer().put(iArr, 0, 16);
        return order;
    }

    private static void zza(int[] iArr, int i, int i2, int i3, int i4) {
        iArr[i] = iArr[i] + iArr[i2];
        iArr[i4] = zzbpa.rotateLeft(iArr[i4] ^ iArr[i], 16);
        iArr[i3] = iArr[i3] + iArr[i4];
        iArr[i2] = zzbpa.rotateLeft(iArr[i2] ^ iArr[i3], 12);
        iArr[i] = iArr[i] + iArr[i2];
        iArr[i4] = zzbpa.rotateLeft(iArr[i] ^ iArr[i4], 8);
        iArr[i3] = iArr[i3] + iArr[i4];
        iArr[i2] = zzbpa.rotateLeft(iArr[i2] ^ iArr[i3], 7);
    }
}
