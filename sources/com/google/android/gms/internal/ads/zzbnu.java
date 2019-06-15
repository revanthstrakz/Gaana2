package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class zzbnu implements zzbov {
    private final SecretKeySpec zzfil;
    private final int zzfim;
    private final int zzfin = ((Cipher) zzbol.zzfjr.zzfu("AES/CTR/NoPadding")).getBlockSize();

    public zzbnu(byte[] bArr, int i) throws GeneralSecurityException {
        zzbpd.zzeh(bArr.length);
        this.zzfil = new SecretKeySpec(bArr, "AES");
        if (i < 12 || i > this.zzfin) {
            throw new GeneralSecurityException("invalid IV size");
        }
        this.zzfim = i;
    }

    public final byte[] zzn(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length > Integer.MAX_VALUE - this.zzfim) {
            int i = Integer.MAX_VALUE - this.zzfim;
            StringBuilder stringBuilder = new StringBuilder(43);
            stringBuilder.append("plaintext length can not exceed ");
            stringBuilder.append(i);
            throw new GeneralSecurityException(stringBuilder.toString());
        }
        byte[] bArr2 = new byte[(this.zzfim + bArr.length)];
        byte[] zzeg = zzboy.zzeg(this.zzfim);
        System.arraycopy(zzeg, 0, bArr2, 0, this.zzfim);
        int length = bArr.length;
        int i2 = this.zzfim;
        Cipher cipher = (Cipher) zzbol.zzfjr.zzfu("AES/CTR/NoPadding");
        byte[] bArr3 = new byte[this.zzfin];
        System.arraycopy(zzeg, 0, bArr3, 0, this.zzfim);
        cipher.init(1, this.zzfil, new IvParameterSpec(bArr3));
        if (cipher.doFinal(bArr, 0, length, bArr2, i2) == length) {
            return bArr2;
        }
        throw new GeneralSecurityException("stored output's length does not match input's length");
    }
}
