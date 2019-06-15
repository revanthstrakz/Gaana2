package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.Arrays;

@zzark
public final class zzbdr extends zzbdj {
    @Nullable
    public final zzbdi zza(Context context, zzbdz zzbdz, int i, boolean z, zzaba zzaba, zzbdy zzbdy) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        Object obj = null;
        Object obj2 = (!PlatformVersion.isAtLeastIceCreamSandwich() || (applicationInfo != null && applicationInfo.targetSdkVersion < 11)) ? null : 1;
        if (obj2 == null) {
            return null;
        }
        zzbdy zzbdy2;
        zzbea zzbea = new zzbea(context, zzbdz.zzabz(), zzbdz.zzabx(), zzaba, zzbdz.zzabv());
        if (VERSION.SDK_INT >= 16 && i == 2) {
            obj = 1;
        }
        if (obj != null) {
            zzbdy2 = zzbdy;
            if (Arrays.asList(zzbdy2.zzeto.split(",")).contains("3")) {
                return new zzbee(context, zzbea, zzbdz, z, zzbdj.zza(zzbdz), zzbdy2);
            }
        }
        zzbdy2 = zzbdy;
        return new zzbcx(context, z, zzbdj.zza(zzbdz), zzbdy2, new zzbea(context, zzbdz.zzabz(), zzbdz.zzabx(), zzaba, zzbdz.zzabv()));
    }
}
