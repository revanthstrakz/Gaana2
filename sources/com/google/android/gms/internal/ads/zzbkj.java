package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbmv.zzb;
import java.security.GeneralSecurityException;

final class zzbkj implements zzbjt<zzbjm> {
    zzbkj() {
    }

    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key";
    }

    public final int getVersion() {
        return 0;
    }

    private final zzbjm zzd(zzbpu zzbpu) throws GeneralSecurityException {
        try {
            zzblv zzu = zzblv.zzu(zzbpu);
            if (zzu instanceof zzblv) {
                zzu = zzu;
                zzbpd.zzs(zzu.getVersion(), 0);
                if (zzu.zzagf().size() == 32) {
                    return new zzbnz(zzu.zzagf().toByteArray());
                }
                throw new GeneralSecurityException("invalid ChaCha20Poly1305Key: incorrect key length");
            }
            throw new GeneralSecurityException("expected ChaCha20Poly1305Key proto");
        } catch (zzbrl e) {
            throw new GeneralSecurityException("invalid ChaCha20Poly1305 key", e);
        }
    }

    public final zzbsl zzb(zzbpu zzbpu) throws GeneralSecurityException {
        return zzafv();
    }

    public final zzbsl zzb(zzbsl zzbsl) throws GeneralSecurityException {
        return zzafv();
    }

    public final zzbmv zzc(zzbpu zzbpu) throws GeneralSecurityException {
        return (zzbmv) ((zzbrd) zzbmv.zzaij().zzfl("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key").zzai(zzafv().zzakf()).zzb(zzb.SYMMETRIC).zzana());
    }

    private static zzblv zzafv() throws GeneralSecurityException {
        return (zzblv) ((zzbrd) zzblv.zzagx().zzdo(0).zzv(zzbpu.zzr(zzboy.zzeg(32))).zzana());
    }
}
