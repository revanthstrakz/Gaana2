package com.inmobi.commons.core.utilities.a;

import android.annotation.SuppressLint;
import android.util.Base64;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class b {
    private static final String a = "b";
    private static String b = "AES";
    private static String c = "AES/CBC/PKCS7Padding";
    private static byte[] d;

    public static String a(String str, byte[] bArr, byte[] bArr2, byte[] bArr3, String str2, String str3) {
        try {
            byte[] b = b(str.getBytes("UTF-8"), bArr, bArr2);
            byte[] b2 = b(b, bArr3);
            b = a(b);
            b2 = a(b2);
            return new String(Base64.encode(a(a(a(a(a(a(bArr), a(bArr3)), a(bArr2)), str3, str2)), a(b, b2)), 8));
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in generating secret message; ").append(e.getMessage());
            return null;
        }
    }

    @SuppressLint({"TrulyRandom"})
    private static byte[] b(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, b);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr3);
        try {
            Cipher instance = Cipher.getInstance(c);
            instance.init(1, secretKeySpec, ivParameterSpec);
            instance.init(1, secretKeySpec, ivParameterSpec);
            instance.init(1, secretKeySpec, ivParameterSpec);
            return instance.doFinal(bArr);
        } catch (Throwable th) {
            new StringBuilder("SDK encountered unexpected error in getting encrypted AES bytes; ").append(th.getMessage());
            return null;
        }
    }

    private static byte[] b() {
        try {
            KeyGenerator instance = KeyGenerator.getInstance("AES");
            instance.init(128, new SecureRandom());
            SecretKey generateKey = instance.generateKey();
            if (generateKey != null) {
                return generateKey.getEncoded();
            }
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in generating AES public key; ").append(e.getMessage());
        }
        return null;
    }

    private static byte[] b(byte[] bArr, byte[] bArr2) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, 0, bArr2.length, "HmacSHA1");
        try {
            Mac instance = Mac.getInstance("HmacSHA1");
            instance.init(secretKeySpec);
            return instance.doFinal(bArr);
        } catch (InvalidKeyException | NoSuchAlgorithmException unused) {
            return null;
        }
    }

    public static byte[] a(byte[] bArr) {
        long length = (long) bArr.length;
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        allocate.putLong(length);
        byte[] array = allocate.array();
        byte[] bArr2 = new byte[(array.length + bArr.length)];
        System.arraycopy(array, 0, bArr2, 0, array.length);
        System.arraycopy(bArr, 0, bArr2, array.length, bArr.length);
        return bArr2;
    }

    public static byte[] a(byte[] bArr, String str, String str2) {
        try {
            RSAPublicKey rSAPublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(new BigInteger(str2, 16), new BigInteger(str, 16)));
            Cipher instance = Cipher.getInstance("RSA/ECB/nopadding");
            instance.init(1, rSAPublicKey);
            return instance.doFinal(bArr);
        } catch (Throwable th) {
            new StringBuilder("SDK encountered unexpected error in getting encrypted RSA bytes; ").append(th.getMessage());
            return null;
        }
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0044 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0051 */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:2|3|4|(2:6|7)(2:8|(5:10|11|12|13|14))|15|16|17) */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:10|11|12|13|14) */
    public static synchronized byte[] a() {
        /*
        r0 = com.inmobi.commons.core.utilities.a.b.class;
        monitor-enter(r0);
        r1 = new com.inmobi.commons.core.utilities.a.a;	 Catch:{ all -> 0x0055 }
        r1.<init>();	 Catch:{ all -> 0x0055 }
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0055 }
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r2 = r2 / r4;
        r4 = r1.a;	 Catch:{ all -> 0x0055 }
        r5 = "last_generated_ts";
        r6 = 0;
        r4 = r4.b(r5, r6);	 Catch:{ all -> 0x0055 }
        r6 = r2 - r4;
        r2 = 86400; // 0x15180 float:1.21072E-40 double:4.26873E-319;
        r4 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));
        r2 = 0;
        if (r4 <= 0) goto L_0x0031;
    L_0x0023:
        r3 = b();	 Catch:{ Exception -> 0x0051 }
        d = r3;	 Catch:{ Exception -> 0x0051 }
        r2 = android.util.Base64.encodeToString(r3, r2);	 Catch:{ Exception -> 0x0051 }
        r1.a(r2);	 Catch:{ Exception -> 0x0051 }
        goto L_0x0051;
    L_0x0031:
        r3 = d;	 Catch:{ Exception -> 0x0051 }
        if (r3 != 0) goto L_0x0051;
    L_0x0035:
        r3 = r1.a;	 Catch:{ Exception -> 0x0051 }
        r4 = "aes_public_key";
        r3 = r3.c(r4);	 Catch:{ Exception -> 0x0051 }
        r3 = android.util.Base64.decode(r3, r2);	 Catch:{ IllegalArgumentException -> 0x0044 }
        d = r3;	 Catch:{ IllegalArgumentException -> 0x0044 }
        goto L_0x0051;
    L_0x0044:
        r3 = b();	 Catch:{ Exception -> 0x0051 }
        d = r3;	 Catch:{ Exception -> 0x0051 }
        r2 = android.util.Base64.encodeToString(r3, r2);	 Catch:{ Exception -> 0x0051 }
        r1.a(r2);	 Catch:{ Exception -> 0x0051 }
    L_0x0051:
        r1 = d;	 Catch:{ all -> 0x0055 }
        monitor-exit(r0);
        return r1;
    L_0x0055:
        r1 = move-exception;
        monitor-exit(r0);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.commons.core.utilities.a.b.a():byte[]");
    }

    public static byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, b);
        try {
            Cipher instance = Cipher.getInstance(c);
            instance.init(2, secretKeySpec, new IvParameterSpec(bArr3));
            return instance.doFinal(bArr);
        } catch (Throwable th) {
            new StringBuilder("SDK encountered unexpected error in decrypting AES response; ").append(th.getMessage());
            return null;
        }
    }

    public static byte[] a(int i) {
        byte[] bArr = new byte[i];
        try {
            new SecureRandom().nextBytes(bArr);
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in generating crypto key; ").append(e.getMessage());
        }
        return bArr;
    }
}
