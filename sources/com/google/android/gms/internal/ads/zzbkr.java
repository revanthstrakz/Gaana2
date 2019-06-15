package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

final class zzbkr implements zzbjn<zzbjs> {
    public final zzbjt<zzbjs> zzb(String str, String str2, int i) throws GeneralSecurityException {
        String toLowerCase = str2.toLowerCase();
        int i2 = -1;
        int i3 = (toLowerCase.hashCode() == 1420614889 && toLowerCase.equals("hybridencrypt")) ? 0 : -1;
        if (i3 != 0) {
            throw new GeneralSecurityException(String.format("No support for primitive '%s'.", new Object[]{str2}));
        }
        if (str.hashCode() == 396454335 && str.equals("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey")) {
            i2 = 0;
        }
        if (i2 != 0) {
            throw new GeneralSecurityException(String.format("No support for primitive 'HybridEncrypt' with key type '%s'.", new Object[]{str}));
        }
        zzbko zzbko = new zzbko();
        if (i <= 0) {
            return zzbko;
        }
        throw new GeneralSecurityException(String.format("No key manager for key type '%s' with version at least %d.", new Object[]{str, Integer.valueOf(i)}));
    }
}
