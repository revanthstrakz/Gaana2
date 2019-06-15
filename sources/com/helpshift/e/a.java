package com.helpshift.e;

import com.helpshift.util.l;
import java.security.GeneralSecurityException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class a {
    public String a(String str, String str2) throws GeneralSecurityException {
        str = a(str, str2, 0);
        if (str != null) {
            return str;
        }
        throw new GeneralSecurityException();
    }

    private String a(String str, String str2, int i) {
        if (i > 1) {
            return null;
        }
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes("UTF-8"), "HmacSHA256");
            Mac instance = Mac.getInstance("HmacSHA256");
            instance.init(secretKeySpec);
            return a(instance.doFinal(str.getBytes("UTF-8")));
        } catch (Exception e) {
            StringBuilder stringBuilder;
            if (i == 1) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Could not generate mac signature: ");
                stringBuilder.append(e.getLocalizedMessage());
                stringBuilder.append(", retryCount: ");
                stringBuilder.append(i);
                l.d("Helpshift_CryptoDM", stringBuilder.toString(), e, new com.helpshift.j.c.a[0]);
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Could not generate mac signature: ");
                stringBuilder.append(e.getLocalizedMessage());
                stringBuilder.append(", retryCount: ");
                stringBuilder.append(i);
                l.c("Helpshift_CryptoDM", stringBuilder.toString(), e);
            }
            return a(str, str2, i + 1);
        }
    }

    private String a(byte[] bArr) {
        char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] cArr2 = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            cArr2[i3] = cArr[i2 >>> 4];
            cArr2[i3 + 1] = cArr[i2 & 15];
        }
        return new String(cArr2);
    }
}
