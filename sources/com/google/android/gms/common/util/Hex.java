package com.google.android.gms.common.util;

import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;

@ShowFirstParty
@KeepForSdk
public class Hex {
    private static final char[] zzgx = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final char[] zzgy = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    @KeepForSdk
    public static String bytesToStringUppercase(byte[] bArr) {
        return bytesToStringUppercase(bArr, false);
    }

    @KeepForSdk
    public static String bytesToStringUppercase(byte[] bArr, boolean z) {
        int i = 0;
        int length = bArr.length;
        StringBuilder stringBuilder = new StringBuilder(length << 1);
        while (i < length && (!z || i != length - 1 || (bArr[i] & 255) != 0)) {
            stringBuilder.append(zzgx[(bArr[i] & PsExtractor.VIDEO_STREAM_MASK) >>> 4]);
            stringBuilder.append(zzgx[bArr[i] & 15]);
            i++;
        }
        return stringBuilder.toString();
    }

    @KeepForSdk
    public static String bytesToStringLowercase(byte[] bArr) {
        int i = 0;
        char[] cArr = new char[(bArr.length << 1)];
        int i2 = 0;
        while (i < bArr.length) {
            int i3 = bArr[i] & 255;
            int i4 = i2 + 1;
            cArr[i2] = zzgy[i3 >>> 4];
            i2 = i4 + 1;
            cArr[i4] = zzgy[i3 & 15];
            i++;
        }
        return new String(cArr);
    }

    @KeepForSdk
    public static byte[] stringToBytes(String str) throws IllegalArgumentException {
        int length = str.length();
        if (length % 2 != 0) {
            throw new IllegalArgumentException("Hex string has odd number of characters");
        }
        byte[] bArr = new byte[(length / 2)];
        int i = 0;
        while (i < length) {
            int i2 = i + 2;
            bArr[i / 2] = (byte) Integer.parseInt(str.substring(i, i2), 16);
            i = i2;
        }
        return bArr;
    }
}
