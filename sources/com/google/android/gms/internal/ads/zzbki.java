package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbmv.zzb;
import java.security.GeneralSecurityException;

final class zzbki implements zzbjt<zzbjm> {
    zzbki() {
    }

    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesGcmKey";
    }

    public final int getVersion() {
        return 0;
    }

    private final zzbjm zzd(zzbpu zzbpu) throws GeneralSecurityException {
        try {
            zzblr zzr = zzblr.zzr(zzbpu);
            if (zzr instanceof zzblr) {
                zzr = zzr;
                zzbpd.zzs(zzr.getVersion(), 0);
                zzbpd.zzeh(zzr.zzagf().size());
                return new zzbnw(zzr.zzagf().toByteArray());
            }
            throw new GeneralSecurityException("expected AesGcmKey proto");
        } catch (zzbrl unused) {
            throw new GeneralSecurityException("expected AesGcmKey proto");
        }
    }

    public final zzbsl zzb(zzbpu zzbpu) throws GeneralSecurityException {
        try {
            return zzb(zzblt.zzt(zzbpu));
        } catch (zzbrl e) {
            throw new GeneralSecurityException("expected serialized AesGcmKeyFormat proto", e);
        }
    }

    public final zzbsl zzb(zzbsl zzbsl) throws GeneralSecurityException {
        if (zzbsl instanceof zzblt) {
            zzblt zzblt = (zzblt) zzbsl;
            zzbpd.zzeh(zzblt.getKeySize());
            return (zzbrd) zzblr.zzagu().zzs(zzbpu.zzr(zzboy.zzeg(zzblt.getKeySize()))).zzdn(0).zzana();
        }
        throw new GeneralSecurityException("expected AesGcmKeyFormat proto");
    }

    public final zzbmv zzc(zzbpu zzbpu) throws GeneralSecurityException {
        return (zzbmv) ((zzbrd) zzbmv.zzaij().zzfl("type.googleapis.com/google.crypto.tink.AesGcmKey").zzai(((zzblr) zzb(zzbpu)).zzakf()).zzb(zzb.SYMMETRIC).zzana());
    }
}
