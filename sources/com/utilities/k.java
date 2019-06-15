package com.utilities;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class k {
    private IvParameterSpec a;
    private SecretKeySpec b;
    private Cipher c;

    public k(String str, String str2) {
        this.a = new IvParameterSpec(str2.getBytes());
        this.b = new SecretKeySpec(str.getBytes(), "AES");
        try {
            this.c = Cipher.getInstance("AES/CBC/NoPadding");
        } catch (NoSuchAlgorithmException e) {
            ThrowableExtension.printStackTrace(e);
        } catch (NoSuchPaddingException e2) {
            ThrowableExtension.printStackTrace(e2);
        }
    }

    public k(String str) {
        this.b = new SecretKeySpec(str.getBytes(), "AES");
        try {
            this.c = Cipher.getInstance("AES/ECB/NoPadding");
        } catch (NoSuchAlgorithmException e) {
            ThrowableExtension.printStackTrace(e);
        } catch (NoSuchPaddingException e2) {
            ThrowableExtension.printStackTrace(e2);
        }
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length;
        String str = "";
        for (int i = 0; i < length; i++) {
            StringBuilder stringBuilder;
            if ((bArr[i] & 255) < 16) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("0");
                stringBuilder.append(Integer.toHexString(bArr[i] & 255));
                str = stringBuilder.toString();
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(Integer.toHexString(bArr[i] & 255));
                str = stringBuilder.toString();
            }
        }
        return str;
    }

    public static byte[] a(String str) {
        if (str == null || str.length() < 2) {
            return null;
        }
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) Integer.parseInt(str.substring(i2, i2 + 2), 16);
        }
        return bArr;
    }

    private static String e(String str) {
        int length = 16 - (str.length() % 16);
        for (int i = 0; i < length; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(' ');
            str = stringBuilder.toString();
        }
        return str;
    }

    public byte[] b(String str) throws Exception {
        if (str == null || str.length() == 0) {
            throw new Exception("Empty string");
        }
        try {
            this.c.init(1, this.b, this.a);
            return this.c.doFinal(e(str).getBytes());
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[encrypt] ");
            stringBuilder.append(e.getMessage());
            throw new Exception(stringBuilder.toString());
        }
    }

    public byte[] c(String str) throws Exception {
        if (str == null || str.length() == 0) {
            throw new Exception("Empty string");
        }
        try {
            this.c.init(1, this.b);
            return this.c.doFinal(e(str).getBytes());
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[encrypt] ");
            stringBuilder.append(e.getMessage());
            throw new Exception(stringBuilder.toString());
        }
    }

    public byte[] d(String str) throws Exception {
        if (str == null || str.length() == 0) {
            throw new Exception("Empty string");
        }
        try {
            this.c.init(2, this.b);
            return this.c.doFinal(a(str));
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[decrypt] ");
            stringBuilder.append(e.getMessage());
            throw new Exception(stringBuilder.toString());
        }
    }
}
