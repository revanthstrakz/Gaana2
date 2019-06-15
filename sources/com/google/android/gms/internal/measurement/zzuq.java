package com.google.android.gms.internal.measurement;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class zzuq {
    private static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final byte[] zzbza;
    private static final ByteBuffer zzbzb;
    private static final zztq zzbzc;

    public static int zzbd(long j) {
        return (int) (j ^ (j >>> 32));
    }

    static boolean zzf(zzvv zzvv) {
        return false;
    }

    public static int zzu(boolean z) {
        return z ? 1231 : 1237;
    }

    static <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    static <T> T zza(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static boolean zzl(byte[] bArr) {
        return zzxl.zzl(bArr);
    }

    public static String zzm(byte[] bArr) {
        return new String(bArr, UTF_8);
    }

    public static int hashCode(byte[] bArr) {
        int length = bArr.length;
        int zza = zza(length, bArr, 0, length);
        return zza == 0 ? 1 : zza;
    }

    static int zza(int i, byte[] bArr, int i2, int i3) {
        int i4 = i;
        for (i = i2; i < i2 + i3; i++) {
            i4 = (i4 * 31) + bArr[i];
        }
        return i4;
    }

    static Object zzb(Object obj, Object obj2) {
        return ((zzvv) obj).zzwh().zza((zzvv) obj2).zzwn();
    }

    static {
        byte[] bArr = new byte[0];
        zzbza = bArr;
        zzbzb = ByteBuffer.wrap(bArr);
        bArr = zzbza;
        zzbzc = zztq.zza(bArr, 0, bArr.length, false);
    }
}
