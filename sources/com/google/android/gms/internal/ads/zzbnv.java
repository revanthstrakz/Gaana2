package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class zzbnv implements zzbjm {
    private final SecretKeySpec zzfil;
    private final byte[] zzfio;
    private final byte[] zzfip;
    private final int zzfiq;

    public zzbnv(byte[] bArr, int i) throws GeneralSecurityException {
        if (i == 12 || i == 16) {
            this.zzfiq = i;
            zzbpd.zzeh(bArr.length);
            this.zzfil = new SecretKeySpec(bArr, "AES");
            Cipher instance = Cipher.getInstance("AES/ECB/NOPADDING");
            instance.init(1, this.zzfil);
            this.zzfio = zzo(instance.doFinal(new byte[16]));
            this.zzfip = zzo(this.zzfio);
            return;
        }
        throw new IllegalArgumentException("IV size should be either 12 or 16 bytes");
    }

    private static byte[] zzd(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        byte[] bArr3 = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr3[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
        return bArr3;
    }

    private static byte[] zzo(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        int i = 0;
        int i2 = 0;
        while (i2 < 15) {
            int i3 = i2 + 1;
            bArr2[i2] = (byte) ((bArr[i2] << 1) ^ ((bArr[i3] & 255) >>> 7));
            i2 = i3;
        }
        i2 = bArr[15] << 1;
        if ((bArr[0] & 128) != 0) {
            i = TsExtractor.TS_STREAM_TYPE_E_AC3;
        }
        bArr2[15] = (byte) (i2 ^ i);
        return bArr2;
    }

    private final byte[] zza(Cipher cipher, int i, byte[] bArr, int i2, int i3) throws IllegalBlockSizeException, BadPaddingException {
        byte[] bArr2 = new byte[16];
        bArr2[15] = (byte) i;
        if (i3 == 0) {
            return cipher.doFinal(zzd(bArr2, this.zzfio));
        }
        byte[] doFinal = cipher.doFinal(bArr2);
        int i4 = 0;
        byte[] bArr3 = doFinal;
        i = 0;
        while (i3 - i > 16) {
            for (int i5 = 0; i5 < 16; i5++) {
                bArr3[i5] = (byte) (bArr3[i5] ^ bArr[(i2 + i) + i5]);
            }
            bArr3 = cipher.doFinal(bArr3);
            i += 16;
        }
        doFinal = Arrays.copyOfRange(bArr, i + i2, i2 + i3);
        if (doFinal.length == 16) {
            doFinal = zzd(doFinal, this.zzfio);
        } else {
            bArr = Arrays.copyOf(this.zzfip, 16);
            while (i4 < doFinal.length) {
                bArr[i4] = (byte) (bArr[i4] ^ doFinal[i4]);
                i4++;
            }
            bArr[doFinal.length] = (byte) (bArr[doFinal.length] ^ 128);
            doFinal = bArr;
        }
        return cipher.doFinal(zzd(bArr3, doFinal));
    }

    public final byte[] zzc(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArr3 = bArr;
        if (bArr3.length > (Integer.MAX_VALUE - this.zzfiq) - 16) {
            throw new GeneralSecurityException("plaintext too long");
        }
        int i = 0;
        byte[] bArr4 = new byte[((this.zzfiq + bArr3.length) + 16)];
        byte[] zzeg = zzboy.zzeg(this.zzfiq);
        System.arraycopy(zzeg, 0, bArr4, 0, this.zzfiq);
        Cipher instance = Cipher.getInstance("AES/ECB/NOPADDING");
        instance.init(1, this.zzfil);
        byte[] zza = zza(instance, 0, zzeg, 0, zzeg.length);
        zzeg = bArr2 == null ? new byte[0] : bArr2;
        byte[] zza2 = zza(instance, 1, zzeg, 0, zzeg.length);
        Cipher instance2 = Cipher.getInstance("AES/CTR/NOPADDING");
        instance2.init(1, this.zzfil, new IvParameterSpec(zza));
        instance2.doFinal(bArr3, 0, bArr3.length, bArr4, this.zzfiq);
        byte[] zza3 = zza(instance, 2, bArr4, this.zzfiq, bArr3.length);
        int length = bArr3.length + this.zzfiq;
        while (i < 16) {
            bArr4[length + i] = (byte) ((zza2[i] ^ zza[i]) ^ zza3[i]);
            i++;
        }
        return bArr4;
    }
}
