package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;

public final class zzbow implements zzbjx {
    private Mac zzfkb;
    private final int zzfkc;
    private final String zzfkd;
    private final Key zzfke;

    public zzbow(String str, Key key, int i) throws GeneralSecurityException {
        if (i < 10) {
            throw new InvalidAlgorithmParameterException("tag size too small, need at least 10 bytes");
        }
        Object obj = -1;
        int hashCode = str.hashCode();
        if (hashCode != -1823053428) {
            if (hashCode != 392315118) {
                if (hashCode == 392317873 && str.equals("HMACSHA512")) {
                    obj = 2;
                }
            } else if (str.equals("HMACSHA256")) {
                obj = 1;
            }
        } else if (str.equals("HMACSHA1")) {
            obj = null;
        }
        switch (obj) {
            case null:
                if (i > 20) {
                    throw new InvalidAlgorithmParameterException("tag size too big");
                }
                break;
            case 1:
                if (i > 32) {
                    throw new InvalidAlgorithmParameterException("tag size too big");
                }
                break;
            case 2:
                if (i > 64) {
                    throw new InvalidAlgorithmParameterException("tag size too big");
                }
                break;
            default:
                String str2 = "unknown Hmac algorithm: ";
                str = String.valueOf(str);
                throw new NoSuchAlgorithmException(str.length() != 0 ? str2.concat(str) : new String(str2));
        }
        this.zzfkd = str;
        this.zzfkc = i;
        this.zzfke = key;
        this.zzfkb = (Mac) zzbol.zzfjs.zzfu(str);
        this.zzfkb.init(key);
    }

    public final byte[] zzj(byte[] bArr) throws GeneralSecurityException {
        Mac mac;
        try {
            mac = (Mac) this.zzfkb.clone();
        } catch (CloneNotSupportedException unused) {
            mac = (Mac) zzbol.zzfjs.zzfu(this.zzfkd);
            mac.init(this.zzfke);
        }
        mac.update(bArr);
        bArr = new byte[this.zzfkc];
        System.arraycopy(mac.doFinal(), 0, bArr, 0, this.zzfkc);
        return bArr;
    }
}
