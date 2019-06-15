package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbmv.zzb;
import java.security.GeneralSecurityException;

final class zzbkg implements zzbjt<zzbov> {
    zzbkg() {
    }

    public final String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesCtrKey";
    }

    public final int getVersion() {
        return 0;
    }

    private final zzbnu zze(zzbpu zzbpu) throws GeneralSecurityException {
        try {
            zzblf zzl = zzblf.zzl(zzbpu);
            if (zzl instanceof zzblf) {
                zzl = zzl;
                zzbpd.zzs(zzl.getVersion(), 0);
                zzbpd.zzeh(zzl.zzagf().size());
                zza(zzl.zzage());
                return new zzbnu(zzl.zzagf().toByteArray(), zzl.zzage().zzagl());
            }
            throw new GeneralSecurityException("expected AesCtrKey proto");
        } catch (zzbrl e) {
            throw new GeneralSecurityException("expected serialized AesCtrKey proto", e);
        }
    }

    public final zzbsl zzb(zzbpu zzbpu) throws GeneralSecurityException {
        try {
            return zzb(zzblh.zzn(zzbpu));
        } catch (zzbrl e) {
            throw new GeneralSecurityException("expected serialized AesCtrKeyFormat proto", e);
        }
    }

    public final zzbsl zzb(zzbsl zzbsl) throws GeneralSecurityException {
        if (zzbsl instanceof zzblh) {
            zzblh zzblh = (zzblh) zzbsl;
            zzbpd.zzeh(zzblh.getKeySize());
            zza(zzblh.zzage());
            return (zzbrd) zzblf.zzagg().zzc(zzblh.zzage()).zzm(zzbpu.zzr(zzboy.zzeg(zzblh.getKeySize()))).zzdl(0).zzana();
        }
        throw new GeneralSecurityException("expected AesCtrKeyFormat proto");
    }

    public final zzbmv zzc(zzbpu zzbpu) throws GeneralSecurityException {
        return (zzbmv) ((zzbrd) zzbmv.zzaij().zzfl("type.googleapis.com/google.crypto.tink.AesCtrKey").zzai(((zzblf) zzb(zzbpu)).zzakf()).zzb(zzb.SYMMETRIC).zzana());
    }

    private static void zza(zzblj zzblj) throws GeneralSecurityException {
        if (zzblj.zzagl() < 12 || zzblj.zzagl() > 16) {
            throw new GeneralSecurityException("invalid IV size");
        }
    }
}
