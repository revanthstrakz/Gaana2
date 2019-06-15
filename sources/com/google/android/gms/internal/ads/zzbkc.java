package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbne.zzb;
import com.google.android.gms.internal.ads.zzbng.zza;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;

final class zzbkc {
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    public static zzbng zzb(zzbne zzbne) {
        zza zzea = zzbng.zzaje().zzea(zzbne.zzaiu());
        for (zzb zzb : zzbne.zzaiv()) {
            zzea.zzb((zzbng.zzb) ((zzbrd) zzbng.zzb.zzajg().zzfr(zzb.zzaiz().zzaig()).zzb(zzb.zzaja()).zzb(zzb.zzajc()).zzec(zzb.zzajb()).zzana()));
        }
        return (zzbng) ((zzbrd) zzea.zzana());
    }

    public static void zzc(zzbne zzbne) throws GeneralSecurityException {
        if (zzbne.zzaiw() == 0) {
            throw new GeneralSecurityException("empty keyset");
        }
        int zzaiu = zzbne.zzaiu();
        int i = 1;
        int i2 = 0;
        for (zzb zzb : zzbne.zzaiv()) {
            if (!zzb.zzaiy()) {
                throw new GeneralSecurityException(String.format("key %d has no key data", new Object[]{Integer.valueOf(zzb.zzajb())}));
            } else if (zzb.zzajc() == zzbnq.UNKNOWN_PREFIX) {
                throw new GeneralSecurityException(String.format("key %d has unknown prefix", new Object[]{Integer.valueOf(zzb.zzajb())}));
            } else if (zzb.zzaja() == zzbmy.UNKNOWN_STATUS) {
                throw new GeneralSecurityException(String.format("key %d has unknown status", new Object[]{Integer.valueOf(zzb.zzajb())}));
            } else {
                if (zzb.zzaja() == zzbmy.ENABLED && zzb.zzajb() == zzaiu) {
                    if (i2 != 0) {
                        throw new GeneralSecurityException("keyset contains multiple primary keys");
                    }
                    i2 = 1;
                }
                if (zzb.zzaiz().zzaii() != zzbmv.zzb.ASYMMETRIC_PUBLIC) {
                    i = 0;
                }
            }
        }
        if (i2 == 0 && i == 0) {
            throw new GeneralSecurityException("keyset doesn't contain a valid primary key");
        }
    }
}
