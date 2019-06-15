package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class zzboe {
    private ECPublicKey zzfja;

    public zzboe(ECPublicKey eCPublicKey) {
        this.zzfja = eCPublicKey;
    }

    public final zzbof zza(String str, byte[] bArr, byte[] bArr2, int i, zzboj zzboj) throws GeneralSecurityException {
        KeyPair zza = zzbog.zza(this.zzfja.getParams());
        ECPublicKey eCPublicKey = (ECPublicKey) zza.getPublic();
        ECPrivateKey eCPrivateKey = (ECPrivateKey) zza.getPrivate();
        ECPublicKey eCPublicKey2 = this.zzfja;
        try {
            ECParameterSpec params = eCPublicKey2.getParams();
            ECParameterSpec params2 = eCPrivateKey.getParams();
            if (params.getCurve().equals(params2.getCurve()) && params.getGenerator().equals(params2.getGenerator()) && params.getOrder().equals(params2.getOrder()) && params.getCofactor() == params2.getCofactor()) {
                byte[] bArr3;
                byte[] bArr4;
                byte[] zza2 = zzbog.zza(eCPrivateKey, eCPublicKey2.getW());
                EllipticCurve curve = eCPublicKey.getParams().getCurve();
                ECPoint w = eCPublicKey.getW();
                zzbog.zza(w, curve);
                int zzb = zzbog.zzb(curve);
                int i2 = 1;
                int i3;
                switch (zzboh.zzfjd[zzboj.ordinal()]) {
                    case 1:
                        i3 = (2 * zzb) + 1;
                        bArr3 = new byte[i3];
                        byte[] toByteArray = w.getAffineX().toByteArray();
                        byte[] toByteArray2 = w.getAffineY().toByteArray();
                        System.arraycopy(toByteArray2, 0, bArr3, i3 - toByteArray2.length, toByteArray2.length);
                        System.arraycopy(toByteArray, 0, bArr3, (zzb + 1) - toByteArray.length, toByteArray.length);
                        bArr3[0] = (byte) 4;
                        break;
                    case 2:
                        i3 = 2 * zzb;
                        bArr3 = new byte[i3];
                        Object toByteArray3 = w.getAffineX().toByteArray();
                        if (toByteArray3.length > zzb) {
                            toByteArray3 = Arrays.copyOfRange(toByteArray3, toByteArray3.length - zzb, toByteArray3.length);
                        }
                        Object toByteArray4 = w.getAffineY().toByteArray();
                        if (toByteArray4.length > zzb) {
                            toByteArray4 = Arrays.copyOfRange(toByteArray4, toByteArray4.length - zzb, toByteArray4.length);
                        }
                        System.arraycopy(toByteArray4, 0, bArr3, i3 - toByteArray4.length, toByteArray4.length);
                        System.arraycopy(toByteArray3, 0, bArr3, zzb - toByteArray3.length, toByteArray3.length);
                        break;
                    case 3:
                        zzb++;
                        bArr4 = new byte[zzb];
                        bArr3 = w.getAffineX().toByteArray();
                        System.arraycopy(bArr3, 0, bArr4, zzb - bArr3.length, bArr3.length);
                        bArr4[0] = (byte) (w.getAffineY().testBit(0) ? 3 : 2);
                        bArr3 = bArr4;
                        break;
                    default:
                        String valueOf = String.valueOf(zzboj);
                        StringBuilder stringBuilder = new StringBuilder(15 + String.valueOf(valueOf).length());
                        stringBuilder.append("invalid format:");
                        stringBuilder.append(valueOf);
                        throw new GeneralSecurityException(stringBuilder.toString());
                }
                bArr4 = zzbnx.zza(bArr3, zza2);
                Mac mac = (Mac) zzbol.zzfjs.zzfu(str);
                if (i > 255 * mac.getMacLength()) {
                    throw new GeneralSecurityException("size too large");
                }
                if (bArr == null || bArr.length == 0) {
                    mac.init(new SecretKeySpec(new byte[mac.getMacLength()], str));
                } else {
                    mac.init(new SecretKeySpec(bArr, str));
                }
                bArr = mac.doFinal(bArr4);
                bArr4 = new byte[i];
                mac.init(new SecretKeySpec(bArr, str));
                byte[] bArr5 = new byte[0];
                int i4 = 0;
                while (true) {
                    mac.update(bArr5);
                    mac.update(bArr2);
                    mac.update((byte) i2);
                    bArr5 = mac.doFinal();
                    if (bArr5.length + i4 < i) {
                        System.arraycopy(bArr5, 0, bArr4, i4, bArr5.length);
                        i4 += bArr5.length;
                        i2++;
                    } else {
                        System.arraycopy(bArr5, 0, bArr4, i4, i - i4);
                        return new zzbof(bArr3, bArr4);
                    }
                }
            }
            throw new GeneralSecurityException("invalid public key spec");
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new GeneralSecurityException(e.toString());
        }
    }
}
