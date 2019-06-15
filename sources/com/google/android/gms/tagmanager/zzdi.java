package com.google.android.gms.tagmanager;

import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;

@ShowFirstParty
public final class zzdi {
    @VisibleForTesting
    private static zzdj zzbdm = new zzba();
    static int zzyn;

    public static void setLogLevel(int i) {
        zzyn = i;
        zzbdm.setLogLevel(i);
    }

    public static void e(String str) {
        zzbdm.e(str);
    }

    public static void zza(String str, Throwable th) {
        zzbdm.zza(str, th);
    }

    public static void zzab(String str) {
        zzbdm.zzab(str);
    }

    public static void zzb(String str, Throwable th) {
        zzbdm.zzb(str, th);
    }

    public static void zzdm(String str) {
        zzbdm.zzdm(str);
    }

    public static void zzdn(String str) {
        zzbdm.zzdn(str);
    }

    public static void v(String str) {
        zzbdm.v(str);
    }
}
