package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

final class zzbkz implements zzbjn<zzbjx> {
    public final zzbjt<zzbjx> zzb(String str, String str2, int i) throws GeneralSecurityException {
        String toLowerCase = str2.toLowerCase();
        int i2 = -1;
        int i3 = (toLowerCase.hashCode() == 107855 && toLowerCase.equals("mac")) ? 0 : -1;
        if (i3 != 0) {
            throw new GeneralSecurityException(String.format("No support for primitive '%s'.", new Object[]{str2}));
        }
        if (str.hashCode() == 836622442 && str.equals("type.googleapis.com/google.crypto.tink.HmacKey")) {
            i2 = 0;
        }
        if (i2 != 0) {
            throw new GeneralSecurityException(String.format("No support for primitive 'Mac' with key type '%s'.", new Object[]{str}));
        }
        zzbkx zzbkx = new zzbkx();
        if (i <= 0) {
            return zzbkx;
        }
        throw new GeneralSecurityException(String.format("No key manager for key type '%s' with version at least %d.", new Object[]{str, Integer.valueOf(i)}));
    }
}
