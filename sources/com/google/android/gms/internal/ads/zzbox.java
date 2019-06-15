package com.google.android.gms.internal.ads;

import java.util.Arrays;

final class zzbox {
    private static long zzf(byte[] bArr, int i) {
        return ((long) (((bArr[i + 3] & 255) << 24) | (((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8)) | ((bArr[i + 2] & 255) << 16)))) & 4294967295L;
    }

    private static long zzg(byte[] bArr, int i, int i2) {
        return (zzf(bArr, i) >> i2) & 67108863;
    }

    private static void zza(byte[] bArr, long j, int i) {
        int i2 = 0;
        while (i2 < 4) {
            bArr[i + i2] = (byte) ((int) (j & 255));
            i2++;
            j >>= 8;
        }
    }

    static byte[] zze(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = bArr;
        Object obj = bArr2;
        if (bArr3.length != 32) {
            throw new IllegalArgumentException("The key length in bytes must be 32.");
        }
        long zzg;
        long zzg2 = zzg(bArr3, 0, 0) & 67108863;
        int i = 2;
        int i2 = 3;
        long zzg3 = zzg(bArr3, 3, 2) & 67108611;
        long zzg4 = zzg(bArr3, 6, 4) & 67092735;
        long zzg5 = zzg(bArr3, 9, 6) & 66076671;
        long zzg6 = zzg(bArr3, 12, 8) & 1048575;
        long j = zzg3 * 5;
        long j2 = zzg4 * 5;
        long j3 = zzg5 * 5;
        long j4 = zzg6 * 5;
        byte[] bArr4 = new byte[17];
        long j5 = 0;
        int i3 = 0;
        long j6 = 0;
        long j7 = j6;
        long j8 = j7;
        long j9 = j8;
        while (i3 < obj.length) {
            int min = Math.min(16, obj.length - i3);
            System.arraycopy(obj, i3, bArr4, 0, min);
            bArr4[min] = (byte) 1;
            if (min != 16) {
                Arrays.fill(bArr4, min + 1, 17, (byte) 0);
            }
            long zzg7 = j9 + zzg(bArr4, 0, 0);
            long zzg8 = j5 + zzg(bArr4, i2, i);
            j9 = j6 + zzg(bArr4, 6, 4);
            j6 = j7 + zzg(bArr4, 9, 6);
            zzg = j8 + (zzg(bArr4, 12, 8) | ((long) (bArr4[16] << 24)));
            j7 = ((((zzg7 * zzg2) + (zzg8 * j4)) + (j9 * j3)) + (j6 * j2)) + (zzg * j);
            j8 = ((((zzg7 * zzg3) + (zzg8 * zzg2)) + (j9 * j4)) + (j6 * j3)) + (zzg * j2);
            long j10 = ((((zzg7 * zzg4) + (zzg8 * zzg3)) + (j9 * zzg2)) + (j6 * j4)) + (zzg * j3);
            long j11 = ((((zzg7 * zzg5) + (zzg8 * zzg4)) + (j9 * zzg3)) + (j6 * zzg2)) + (zzg * j4);
            j6 = ((((zzg7 * zzg6) + (zzg8 * zzg5)) + (j9 * zzg4)) + (j6 * zzg3)) + (zzg * zzg2);
            j5 = j7 & 67108863;
            j7 = j8 + (j7 >> 26);
            j8 = j7 & 67108863;
            j7 = j10 + (j7 >> 26);
            j9 = j7 & 67108863;
            j7 = j11 + (j7 >> 26);
            j10 = j7 & 67108863;
            j7 = j6 + (j7 >> 26);
            j6 = j7 & 67108863;
            j7 = j5 + ((j7 >> 26) * 5);
            j5 = j7 & 67108863;
            j7 = j8 + (j7 >> 26);
            i3 += 16;
            j8 = j6;
            j6 = j9;
            i = 2;
            i2 = 3;
            j9 = j5;
            j5 = j7;
            j7 = j10;
        }
        long j12 = j6 + (j5 >> 26);
        zzg2 = j12 & 67108863;
        j12 = j7 + (j12 >> 26);
        long j13 = j12 & 67108863;
        j12 = j8 + (j12 >> 26);
        zzg3 = j12 & 67108863;
        j12 = j9 + ((j12 >> 26) * 5);
        zzg4 = j12 & 67108863;
        j12 = (j5 & 67108863) + (j12 >> 26);
        long j14 = zzg4 + 5;
        long j15 = j14 & 67108863;
        j14 = j12 + (j14 >> 26);
        j = j14 & 67108863;
        j14 = zzg2 + (j14 >> 26);
        zzg5 = j14 & 67108863;
        j14 = j13 + (j14 >> 26);
        zzg6 = j14 & 67108863;
        j2 = (zzg3 + (j14 >> 26)) - 67108864;
        j14 = j2 >> 63;
        zzg = zzg4 & j14;
        zzg4 = j12 & j14;
        j12 = zzg2 & j14;
        zzg2 = j13 & j14;
        j13 = zzg3 & j14;
        j3 = j14 ^ -1;
        zzg3 = zzg | (j15 & j3);
        zzg = zzg4 | (j & j3);
        j15 = j12 | (zzg5 & j3);
        j12 = zzg2 | (zzg6 & j3);
        zzg3 = (zzg3 | (zzg << 26)) & 4294967295L;
        long j16 = ((j15 >> 12) | (j12 << 14)) & 4294967295L;
        j12 = ((j12 >> 18) | ((j13 | (j2 & j3)) << 8)) & 4294967295L;
        zzg2 = zzg3 + zzf(bArr3, 16);
        long j17 = zzg2 & 4294967295L;
        long zzf = ((((zzg >> 6) | (j15 << 20)) & 4294967295L) + zzf(bArr3, 20)) + (zzg2 >> 32);
        zzg2 = zzf & 4294967295L;
        zzf = (j16 + zzf(bArr3, 24)) + (zzf >> 32);
        j16 = zzf & 4294967295L;
        zzg = ((j12 + zzf(bArr3, 28)) + (zzf >> 32)) & 4294967295L;
        bArr3 = new byte[16];
        zza(bArr3, j17, 0);
        zza(bArr3, zzg2, 4);
        zza(bArr3, j16, 8);
        zza(bArr3, zzg, 12);
        return bArr3;
    }
}
