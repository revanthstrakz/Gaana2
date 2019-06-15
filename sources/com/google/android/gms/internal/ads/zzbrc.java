package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zze;

final class zzbrc implements zzbsk {
    private static final zzbrc zzfpt = new zzbrc();

    private zzbrc() {
    }

    public static zzbrc zzamq() {
        return zzfpt;
    }

    public final boolean zzb(Class<?> cls) {
        return zzbrd.class.isAssignableFrom(cls);
    }

    public final zzbsj zzc(Class<?> cls) {
        String valueOf;
        if (zzbrd.class.isAssignableFrom(cls)) {
            try {
                return (zzbsj) zzbrd.zzd(cls.asSubclass(zzbrd.class)).zza(zze.zzfqd, null, null);
            } catch (Exception e) {
                String str = "Unable to get message info for ";
                valueOf = String.valueOf(cls.getName());
                throw new RuntimeException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), e);
            }
        }
        String str2 = "Unsupported message type: ";
        valueOf = String.valueOf(cls.getName());
        throw new IllegalArgumentException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
    }
}
