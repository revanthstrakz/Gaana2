package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

final class zzbkq implements zzbjn<zzbjr> {
    public final zzbjt<zzbjr> zzb(String str, String str2, int i) throws GeneralSecurityException {
        String toLowerCase = str2.toLowerCase();
        int i2 = -1;
        int i3 = (toLowerCase.hashCode() == 275448849 && toLowerCase.equals("hybriddecrypt")) ? 0 : -1;
        if (i3 != 0) {
            throw new GeneralSecurityException(String.format("No support for primitive '%s'.", new Object[]{str2}));
        }
        if (str.hashCode() == -80133005 && str.equals("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey")) {
            i2 = 0;
        }
        if (i2 != 0) {
            throw new GeneralSecurityException(String.format("No support for primitive 'HybridEncrypt' with key type '%s'.", new Object[]{str}));
        }
        zzbkn zzbkn = new zzbkn();
        if (i <= 0) {
            return zzbkn;
        }
        throw new GeneralSecurityException(String.format("No key manager for key type '%s' with version at least %d.", new Object[]{str, Integer.valueOf(i)}));
    }
}
