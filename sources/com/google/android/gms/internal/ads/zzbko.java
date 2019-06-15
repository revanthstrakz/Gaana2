package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

final class zzbko implements zzbjt<zzbjs> {
    zzbko() {
    }

    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey";
    }

    public final int getVersion() {
        return 0;
    }

    private final zzbjs zzg(zzbpu zzbpu) throws GeneralSecurityException {
        try {
            zzbmh zzab = zzbmh.zzab(zzbpu);
            if (zzab instanceof zzbmh) {
                zzab = zzab;
                zzbpd.zzs(zzab.getVersion(), 0);
                zzbku.zza(zzab.zzahc());
                zzbmd zzahc = zzab.zzahc();
                zzbmj zzahe = zzahc.zzahe();
                return new zzboc(zzbog.zza(zzbku.zza(zzahe.zzahr()), zzab.zzahm().toByteArray(), zzab.zzahn().toByteArray()), zzahe.zzaht().toByteArray(), zzbku.zza(zzahe.zzahs()), zzbku.zza(zzahc.zzahg()), new zzbkw(zzahc.zzahf().zzagz()));
            }
            throw new GeneralSecurityException("expected EciesAeadHkdfPublicKey proto");
        } catch (zzbrl e) {
            throw new GeneralSecurityException("expected serialized EciesAeadHkdfPublicKey proto", e);
        }
    }

    public final zzbsl zzb(zzbpu zzbpu) throws GeneralSecurityException {
        throw new GeneralSecurityException("Not implemented.");
    }

    public final zzbsl zzb(zzbsl zzbsl) throws GeneralSecurityException {
        throw new GeneralSecurityException("Not implemented.");
    }

    public final zzbmv zzc(zzbpu zzbpu) throws GeneralSecurityException {
        throw new GeneralSecurityException("Not implemented.");
    }
}
