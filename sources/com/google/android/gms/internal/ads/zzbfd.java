package com.google.android.gms.internal.ads;

import android.os.Build.VERSION;
import java.util.Arrays;

@zzark
public final class zzbfd implements zzbfr {
    public final zzbfk zza(zzbdz zzbdz, int i, String str, zzbdy zzbdy) {
        if (VERSION.SDK_INT < 16 || i <= 0 || !Arrays.asList(zzbdy.zzeto.split(",")).contains("3")) {
            return new zzbfs(zzbdz);
        }
        i = zzbes.zzacy();
        if (i < zzbdy.zzetr) {
            return new zzbfw(zzbdz, zzbdy);
        }
        if (i < zzbdy.zzetl) {
            return new zzbfv(zzbdz, zzbdy);
        }
        return new zzbft(zzbdz);
    }
}
