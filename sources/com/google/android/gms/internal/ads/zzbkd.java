package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

final class zzbkd implements zzbjn<zzbjm> {
    public final zzbjt<zzbjm> zzb(String str, String str2, int i) throws GeneralSecurityException {
        String toLowerCase = str2.toLowerCase();
        int i2 = -1;
        int i3 = (toLowerCase.hashCode() == 2989895 && toLowerCase.equals("aead")) ? 0 : -1;
        if (i3 != 0) {
            throw new GeneralSecurityException(String.format("No support for primitive '%s'.", new Object[]{str2}));
        }
        zzbjt zzbkf;
        switch (str.hashCode()) {
            case 360753376:
                if (str.equals("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key")) {
                    i2 = 3;
                    break;
                }
                break;
            case 1215885937:
                if (str.equals("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey")) {
                    i2 = 0;
                    break;
                }
                break;
            case 1469984853:
                if (str.equals("type.googleapis.com/google.crypto.tink.KmsAeadKey")) {
                    i2 = 4;
                    break;
                }
                break;
            case 1797113348:
                if (str.equals("type.googleapis.com/google.crypto.tink.AesEaxKey")) {
                    i2 = 1;
                    break;
                }
                break;
            case 1855890991:
                if (str.equals("type.googleapis.com/google.crypto.tink.AesGcmKey")) {
                    i2 = 2;
                    break;
                }
                break;
            case 2079211877:
                if (str.equals("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey")) {
                    i2 = 5;
                    break;
                }
                break;
        }
        switch (i2) {
            case 0:
                zzbkf = new zzbkf();
                break;
            case 1:
                zzbkf = new zzbkh();
                break;
            case 2:
                zzbkf = new zzbki();
                break;
            case 3:
                zzbkf = new zzbkj();
                break;
            case 4:
                zzbkf = new zzbkk();
                break;
            case 5:
                zzbkf = new zzbkm();
                break;
            default:
                throw new GeneralSecurityException(String.format("No support for primitive 'Aead' with key type '%s'.", new Object[]{str}));
        }
        if (zzbkf.getVersion() >= i) {
            return zzbkf;
        }
        throw new GeneralSecurityException(String.format("No key manager for key type '%s' with version at least %d.", new Object[]{str, Integer.valueOf(i)}));
    }
}
