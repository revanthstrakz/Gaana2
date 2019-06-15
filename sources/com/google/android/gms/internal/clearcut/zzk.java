package com.google.android.gms.internal.clearcut;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class zzk {
    private static int zza(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8)) | ((bArr[i + 2] & 255) << 16));
    }

    private static long zza(long j, long j2, long j3) {
        long j4 = (j ^ j2) * j3;
        j4 = (j2 ^ (j4 ^ (j4 >>> 47))) * j3;
        return (j4 ^ (j4 >>> 47)) * j3;
    }

    public static long zza(byte[] bArr) {
        byte[] bArr2 = bArr;
        int length = bArr2.length;
        if (length < 0 || length > bArr2.length) {
            StringBuilder stringBuilder = new StringBuilder(67);
            stringBuilder.append("Out of bound index with offput: 0 and length: ");
            stringBuilder.append(length);
            throw new IndexOutOfBoundsException(stringBuilder.toString());
        }
        int i = 37;
        int i2 = 0;
        long zzb;
        long zzb2;
        long zzb3;
        long j;
        long zzb4;
        if (length <= 32) {
            long j2;
            if (length > 16) {
                j2 = -7286425919675154353L + ((long) (length << 1));
                zzb = zzb(bArr2, 0) * -5435081209227447693L;
                zzb2 = zzb(bArr2, 8);
                length += 0;
                zzb3 = zzb(bArr2, length - 8) * j2;
                return zza((Long.rotateRight(zzb + zzb2, 43) + Long.rotateRight(zzb3, 30)) + (zzb(bArr2, length - 16) * -7286425919675154353L), (zzb + Long.rotateRight(zzb2 - 7286425919675154353L, 18)) + zzb3, j2);
            } else if (length >= 8) {
                j2 = -7286425919675154353L + ((long) (length << 1));
                long zzb5 = zzb(bArr2, 0) - 7286425919675154353L;
                long zzb6 = zzb(bArr2, (length + 0) - 8);
                return zza((Long.rotateRight(zzb6, 37) * j2) + zzb5, (Long.rotateRight(zzb5, 25) + zzb6) * j2, j2);
            } else if (length >= 4) {
                return zza(((long) length) + ((((long) zza(bArr2, 0)) & 4294967295L) << 3), ((long) zza(bArr2, (length + 0) - 4)) & 4294967295L, -7286425919675154353L + ((long) (length << 1)));
            } else if (length <= 0) {
                return -7286425919675154353L;
            } else {
                j = (((long) ((bArr2[0] & 255) + ((bArr2[(length >> 1) + 0] & 255) << 8))) * -7286425919675154353L) ^ (((long) (length + ((bArr2[0 + (length - 1)] & 255) << 2))) * -4348849565147123417L);
                return (j ^ (j >>> 47)) * -7286425919675154353L;
            }
        } else if (length <= 64) {
            zzb2 = -7286425919675154353L + ((long) (length << 1));
            zzb = zzb(bArr2, 0) * -7286425919675154353L;
            long zzb7 = zzb(bArr2, 8);
            length += 0;
            zzb4 = zzb(bArr2, length - 8) * zzb2;
            long rotateRight = (Long.rotateRight(zzb + zzb7, 43) + Long.rotateRight(zzb4, 30)) + (zzb(bArr2, length - 16) * -7286425919675154353L);
            zzb4 = zza(rotateRight, (zzb + Long.rotateRight(zzb7 - 7286425919675154353L, 18)) + zzb4, zzb2);
            bArr2 = bArr;
            long zzb8 = zzb(bArr2, 16) * zzb2;
            zzb3 = zzb(bArr2, 24);
            rotateRight = (rotateRight + zzb(bArr2, length - 32)) * zzb2;
            return zza((Long.rotateRight(zzb8 + zzb3, 43) + Long.rotateRight(rotateRight, 30)) + ((zzb4 + zzb(bArr2, length - 24)) * zzb2), (zzb8 + Long.rotateRight(zzb3 + zzb, 18)) + rotateRight, zzb2);
        } else {
            zzb4 = 2480279821605975764L;
            j = 1390051526045402406L;
            long[] jArr = new long[2];
            long[] jArr2 = new long[2];
            long zzb9 = 95310865018149119L + zzb(bArr2, 0);
            int i3 = 1;
            length--;
            int i4 = 0 + ((length / 64) << 6);
            int i5 = length & 63;
            int i6 = (i4 + i5) - 63;
            int i7 = 0;
            while (true) {
                zzb9 = (Long.rotateRight(((zzb9 + zzb4) + jArr[i2]) + zzb(bArr2, i7 + 8), i) * -5435081209227447693L) ^ jArr2[1];
                long rotateRight2 = (Long.rotateRight((zzb4 + jArr[i3]) + zzb(bArr2, i7 + 48), 42) * -5435081209227447693L) + (jArr[0] + zzb(bArr2, i7 + 40));
                long rotateRight3 = Long.rotateRight(j + jArr2[0], 33) * -5435081209227447693L;
                int i8 = i5;
                i = i4;
                zza(bArr2, i7, jArr[1] * -5435081209227447693L, zzb9 + jArr2[0], jArr);
                zza(bArr2, i7 + 32, rotateRight3 + jArr2[1], rotateRight2 + zzb(bArr2, i7 + 16), jArr2);
                length = i7 + 64;
                if (length == i) {
                    long j3 = -5435081209227447693L + ((zzb9 & 255) << 1);
                    jArr2[0] = jArr2[0] + ((long) i8);
                    jArr[0] = jArr[0] + jArr2[0];
                    jArr2[0] = jArr2[0] + jArr[0];
                    long rotateRight4 = (Long.rotateRight(((rotateRight3 + rotateRight2) + jArr[0]) + zzb(bArr2, i6 + 8), 37) * j3) ^ (jArr2[1] * 9);
                    rotateRight3 = (Long.rotateRight((rotateRight2 + jArr[1]) + zzb(bArr2, i6 + 48), 42) * j3) + ((jArr[0] * 9) + zzb(bArr2, i6 + 40));
                    rotateRight2 = Long.rotateRight(zzb9 + jArr2[0], 33) * j3;
                    zza(bArr2, i6, jArr[1] * j3, rotateRight4 + jArr2[0], jArr);
                    zza(bArr2, i6 + 32, rotateRight2 + jArr2[1], rotateRight3 + zzb(bArr2, i6 + 16), jArr2);
                    long j4 = j3;
                    return zza((zza(jArr[0], jArr2[0], j4) + ((rotateRight3 ^ (rotateRight3 >>> 47)) * -4348849565147123417L)) + rotateRight4, zza(jArr[1], jArr2[1], j4) + rotateRight2, j4);
                }
                i7 = length;
                i4 = i;
                i5 = i8;
                j = zzb9;
                zzb9 = rotateRight3;
                i = 37;
                i8 = 64;
                zzb4 = rotateRight2;
                i3 = 1;
                i2 = 0;
            }
        }
    }

    private static void zza(byte[] bArr, int i, long j, long j2, long[] jArr) {
        long zzb = zzb(bArr, i);
        long zzb2 = zzb(bArr, i + 8);
        long zzb3 = zzb(bArr, i + 16);
        long zzb4 = zzb(bArr, i + 24);
        long j3 = j + zzb;
        zzb = (j3 + zzb2) + zzb3;
        zzb2 = Long.rotateRight((j2 + j3) + zzb4, 21) + Long.rotateRight(zzb, 44);
        jArr[0] = zzb + zzb4;
        jArr[1] = zzb2 + j3;
    }

    private static long zzb(byte[] bArr, int i) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr, i, 8);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        return wrap.getLong();
    }
}
