package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbmv.zzb;
import java.security.GeneralSecurityException;

final class zzbkh implements zzbjt<zzbjm> {
    zzbkh() {
    }

    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesEaxKey";
    }

    public final int getVersion() {
        return 0;
    }

    private final zzbjm zzd(zzbpu zzbpu) throws GeneralSecurityException {
        try {
            zzbll zzo = zzbll.zzo(zzbpu);
            if (zzo instanceof zzbll) {
                zzo = zzo;
                zzbpd.zzs(zzo.getVersion(), 0);
                zzbpd.zzeh(zzo.zzagf().size());
                if (zzo.zzago().zzagl() == 12 || zzo.zzago().zzagl() == 16) {
                    return new zzbnv(zzo.zzagf().toByteArray(), zzo.zzago().zzagl());
                }
                throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
            }
            throw new GeneralSecurityException("expected AesEaxKey proto");
        } catch (zzbrl e) {
            throw new GeneralSecurityException("expected serialized AesEaxKey proto", e);
        }
    }

    public final zzbsl zzb(zzbpu zzbpu) throws GeneralSecurityException {
        try {
            return zzb(zzbln.zzq(zzbpu));
        } catch (zzbrl e) {
            throw new GeneralSecurityException("expected serialized AesEaxKeyFormat proto", e);
        }
    }

    public final zzbsl zzb(zzbsl zzbsl) throws GeneralSecurityException {
        if (zzbsl instanceof zzbln) {
            zzbln zzbln = (zzbln) zzbsl;
            zzbpd.zzeh(zzbln.getKeySize());
            if (zzbln.zzago().zzagl() == 12 || zzbln.zzago().zzagl() == 16) {
                return (zzbrd) zzbll.zzagp().zzp(zzbpu.zzr(zzboy.zzeg(zzbln.getKeySize()))).zzb(zzbln.zzago()).zzdm(0).zzana();
            }
            throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
        }
        throw new GeneralSecurityException("expected AesEaxKeyFormat proto");
    }

    public final zzbmv zzc(zzbpu zzbpu) throws GeneralSecurityException {
        return (zzbmv) ((zzbrd) zzbmv.zzaij().zzfl("type.googleapis.com/google.crypto.tink.AesEaxKey").zzai(((zzbll) zzb(zzbpu)).zzakf()).zzb(zzb.SYMMETRIC).zzana());
    }
}
