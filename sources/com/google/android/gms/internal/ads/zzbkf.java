package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbmv.zzb;
import java.security.GeneralSecurityException;
import java.util.logging.Logger;

class zzbkf implements zzbjt<zzbjm> {
    private static final Logger logger = Logger.getLogger(zzbkf.class.getName());

    zzbkf() throws GeneralSecurityException {
        zzbkb.zza(new zzbkg());
    }

    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey";
    }

    public final int getVersion() {
        return 0;
    }

    private final zzbjm zzd(zzbpu zzbpu) throws GeneralSecurityException {
        try {
            zzblb zzi = zzblb.zzi(zzbpu);
            if (zzi instanceof zzblb) {
                zzi = zzi;
                zzbpd.zzs(zzi.getVersion(), 0);
                return new zzbok((zzbov) zzbkb.zzb("type.googleapis.com/google.crypto.tink.AesCtrKey", zzi.zzafx()), (zzbjx) zzbkb.zzb("type.googleapis.com/google.crypto.tink.HmacKey", zzi.zzafy()), zzi.zzafy().zzahw().zzaid());
            }
            throw new GeneralSecurityException("expected AesCtrHmacAeadKey proto");
        } catch (zzbrl e) {
            throw new GeneralSecurityException("expected serialized AesCtrHmacAeadKey proto", e);
        }
    }

    public final zzbsl zzb(zzbpu zzbpu) throws GeneralSecurityException {
        try {
            return zzb(zzbld.zzj(zzbpu));
        } catch (zzbrl e) {
            throw new GeneralSecurityException("expected serialized AesCtrHmacAeadKeyFormat proto", e);
        }
    }

    public final zzbsl zzb(zzbsl zzbsl) throws GeneralSecurityException {
        if (zzbsl instanceof zzbld) {
            zzbld zzbld = (zzbld) zzbsl;
            zzblf zzblf = (zzblf) zzbkb.zza("type.googleapis.com/google.crypto.tink.AesCtrKey", zzbld.zzagb());
            return (zzbrd) zzblb.zzafz().zzb(zzblf).zzb((zzbmp) zzbkb.zza("type.googleapis.com/google.crypto.tink.HmacKey", zzbld.zzagc())).zzdk(0).zzana();
        }
        throw new GeneralSecurityException("expected AesCtrHmacAeadKeyFormat proto");
    }

    public final zzbmv zzc(zzbpu zzbpu) throws GeneralSecurityException {
        return (zzbmv) ((zzbrd) zzbmv.zzaij().zzfl("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey").zzai(((zzblb) zzb(zzbpu)).zzakf()).zzb(zzb.SYMMETRIC).zzana());
    }
}
