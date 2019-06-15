package com.google.android.gms.internal.ads;

import android.util.Pair;

public final class zzpp {
    private static final byte[] zzbhi = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 1};
    private static final int[] zzbhj = new int[]{96000, 88200, 64000, 48000, 44100, 32000, 24000, 22050, 16000, 12000, 11025, 8000, 7350};
    private static final int[] zzbhk = new int[]{0, 1, 2, 3, 4, 5, 6, 8, -1, -1, -1, 7, 8, -1, 8, -1};

    public static Pair<Integer, Integer> zzf(byte[] bArr) {
        zzpw zzpw = new zzpw(bArr);
        int zza = zza(zzpw);
        int zzb = zzb(zzpw);
        int zzbj = zzpw.zzbj(4);
        if (zza == 5 || zza == 29) {
            zzb = zzb(zzpw);
            if (zza(zzpw) == 22) {
                zzbj = zzpw.zzbj(4);
            }
        }
        zza = zzbhk[zzbj];
        zzpo.checkArgument(zza != -1);
        return Pair.create(Integer.valueOf(zzb), Integer.valueOf(zza));
    }

    public static byte[] zzc(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[(zzbhi.length + i2)];
        System.arraycopy(zzbhi, 0, bArr2, 0, zzbhi.length);
        System.arraycopy(bArr, i, bArr2, zzbhi.length, i2);
        return bArr2;
    }

    private static int zza(zzpw zzpw) {
        int zzbj = zzpw.zzbj(5);
        return zzbj == 31 ? 32 + zzpw.zzbj(6) : zzbj;
    }

    private static int zzb(zzpw zzpw) {
        int zzbj = zzpw.zzbj(4);
        if (zzbj == 15) {
            return zzpw.zzbj(24);
        }
        zzpo.checkArgument(zzbj < 13);
        return zzbhj[zzbj];
    }
}
