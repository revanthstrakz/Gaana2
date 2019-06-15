package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbmv.zzb;
import java.security.GeneralSecurityException;
import javax.crypto.spec.SecretKeySpec;

final class zzbkx implements zzbjt<zzbjx> {
    zzbkx() {
    }

    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.HmacKey";
    }

    public final int getVersion() {
        return 0;
    }

    private final zzbjx zzh(zzbpu zzbpu) throws GeneralSecurityException {
        try {
            zzbmp zzae = zzbmp.zzae(zzbpu);
            if (zzae instanceof zzbmp) {
                zzae = zzae;
                zzbpd.zzs(zzae.getVersion(), 0);
                if (zzae.zzagf().size() < 16) {
                    throw new GeneralSecurityException("key too short");
                }
                zzbow zzbow;
                zza(zzae.zzahw());
                zzbmn zzaic = zzae.zzahw().zzaic();
                SecretKeySpec secretKeySpec = new SecretKeySpec(zzae.zzagf().toByteArray(), "HMAC");
                int zzaid = zzae.zzahw().zzaid();
                switch (zzaic) {
                    case SHA1:
                        zzbow = new zzbow("HMACSHA1", secretKeySpec, zzaid);
                        break;
                    case SHA256:
                        zzbow = new zzbow("HMACSHA256", secretKeySpec, zzaid);
                        break;
                    case SHA512:
                        zzbow = new zzbow("HMACSHA512", secretKeySpec, zzaid);
                        break;
                    default:
                        throw new GeneralSecurityException("unknown hash");
                }
                return zzbow;
            }
            throw new GeneralSecurityException("expected HmacKey proto");
        } catch (zzbrl e) {
            throw new GeneralSecurityException("expected serialized HmacKey proto", e);
        }
    }

    public final zzbsl zzb(zzbpu zzbpu) throws GeneralSecurityException {
        try {
            return zzb(zzbmr.zzag(zzbpu));
        } catch (zzbrl e) {
            throw new GeneralSecurityException("expected serialized HmacKeyFormat proto", e);
        }
    }

    public final zzbsl zzb(zzbsl zzbsl) throws GeneralSecurityException {
        if (zzbsl instanceof zzbmr) {
            zzbmr zzbmr = (zzbmr) zzbsl;
            if (zzbmr.getKeySize() < 16) {
                throw new GeneralSecurityException("key too short");
            }
            zza(zzbmr.zzahw());
            return (zzbrd) zzbmp.zzahx().zzdu(0).zzc(zzbmr.zzahw()).zzaf(zzbpu.zzr(zzboy.zzeg(zzbmr.getKeySize()))).zzana();
        }
        throw new GeneralSecurityException("expected HmacKeyFormat proto");
    }

    public final zzbmv zzc(zzbpu zzbpu) throws GeneralSecurityException {
        return (zzbmv) ((zzbrd) zzbmv.zzaij().zzfl("type.googleapis.com/google.crypto.tink.HmacKey").zzai(((zzbmp) zzb(zzbpu)).zzakf()).zzb(zzb.SYMMETRIC).zzana());
    }

    private static void zza(zzbmt zzbmt) throws GeneralSecurityException {
        if (zzbmt.zzaid() < 10) {
            throw new GeneralSecurityException("tag size too small");
        }
        switch (zzbmt.zzaic()) {
            case SHA1:
                if (zzbmt.zzaid() > 20) {
                    throw new GeneralSecurityException("tag size too big");
                }
                return;
            case SHA256:
                if (zzbmt.zzaid() > 32) {
                    throw new GeneralSecurityException("tag size too big");
                }
                return;
            case SHA512:
                if (zzbmt.zzaid() > 64) {
                    throw new GeneralSecurityException("tag size too big");
                }
                return;
            default:
                throw new GeneralSecurityException("unknown hash type");
        }
    }
}
