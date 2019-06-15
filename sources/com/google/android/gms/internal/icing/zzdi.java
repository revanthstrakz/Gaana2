package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.zzdj.zzd;

final class zzdi implements zzep {
    private static final zzdi zzjs = new zzdi();

    private zzdi() {
    }

    public static zzdi zzbp() {
        return zzjs;
    }

    public final boolean zza(Class<?> cls) {
        return zzdj.class.isAssignableFrom(cls);
    }

    public final zzeo zzb(Class<?> cls) {
        String valueOf;
        if (zzdj.class.isAssignableFrom(cls)) {
            try {
                return (zzeo) zzdj.zzc(cls.asSubclass(zzdj.class)).zza(zzd.zzkc, null, null);
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
