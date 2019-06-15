package com.simpl.android.fingerprint.a;

import java.util.Random;

final class b {
    static byte[] a(byte[] bArr) {
        int length = bArr.length + 1;
        byte[] bArr2 = new byte[length];
        int nextInt = new Random().nextInt(7) + 1;
        int i = 0;
        int i2 = i;
        while (i < length) {
            int length2 = bArr.length;
            int i3 = i + 1;
            if (length2 % 2 != 0) {
                length2++;
            }
            if ((length2 / 2 == i3 ? 1 : 0) != 0) {
                bArr2[i] = (byte) nextInt;
            } else {
                length2 = bArr[i2] & 255;
                bArr2[i] = (byte) ((length2 << (8 - nextInt)) | (length2 >>> nextInt));
                i2++;
            }
            i = i3;
        }
        return bArr2;
    }
}
