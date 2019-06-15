package com.facebook.ads.internal.s.a;

import android.support.annotation.Nullable;
import java.io.File;

public class i {
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x002a */
    @android.support.annotation.Nullable
    public static java.lang.String a(java.io.File r4) {
        /*
        r0 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x003e }
        r0.<init>(r4);	 Catch:{ FileNotFoundException -> 0x003e }
        r4 = "MD5";
        r4 = java.security.MessageDigest.getInstance(r4);	 Catch:{ NoSuchAlgorithmException -> 0x0032, IOException -> 0x002a }
        r1 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r1 = new byte[r1];	 Catch:{ NoSuchAlgorithmException -> 0x0032, IOException -> 0x002a }
    L_0x000f:
        r2 = r0.read(r1);	 Catch:{ NoSuchAlgorithmException -> 0x0032, IOException -> 0x002a }
        if (r2 <= 0) goto L_0x0019;
    L_0x0015:
        r3 = 0;
        r4.update(r1, r3, r2);	 Catch:{ NoSuchAlgorithmException -> 0x0032, IOException -> 0x002a }
    L_0x0019:
        r3 = -1;
        if (r2 != r3) goto L_0x000f;
    L_0x001c:
        r4 = r4.digest();	 Catch:{ NoSuchAlgorithmException -> 0x0032, IOException -> 0x002a }
        r4 = b(r4);	 Catch:{ NoSuchAlgorithmException -> 0x0032, IOException -> 0x002a }
        r0.close();	 Catch:{ IOException -> 0x0027 }
    L_0x0027:
        return r4;
    L_0x0028:
        r4 = move-exception;
        goto L_0x003a;
    L_0x002a:
        r4 = new java.lang.Exception;	 Catch:{ all -> 0x0028 }
        r1 = "IO exception.";
        r4.<init>(r1);	 Catch:{ all -> 0x0028 }
        throw r4;	 Catch:{ all -> 0x0028 }
    L_0x0032:
        r4 = new java.lang.Exception;	 Catch:{ all -> 0x0028 }
        r1 = "No such algorithm.";
        r4.<init>(r1);	 Catch:{ all -> 0x0028 }
        throw r4;	 Catch:{ all -> 0x0028 }
    L_0x003a:
        r0.close();	 Catch:{ IOException -> 0x003d }
    L_0x003d:
        throw r4;
    L_0x003e:
        r4 = new java.lang.Exception;
        r0 = "File not found or not accessible.";
        r4.<init>(r0);
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.s.a.i.a(java.io.File):java.lang.String");
    }

    @Nullable
    public static final String a(String str) {
        return a(new File(str));
    }

    public static String a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bArr) {
            int i = (b >>> 4) & 15;
            int i2 = 0;
            while (true) {
                int i3;
                if (i < 0 || i > 9) {
                    i3 = 97;
                    i -= 10;
                } else {
                    i3 = 48;
                }
                stringBuilder.append((char) (i3 + i));
                i = b & 15;
                i3 = i2 + 1;
                if (i2 >= 1) {
                    break;
                }
                i2 = i3;
            }
        }
        return stringBuilder.toString();
    }

    private static String b(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bArr) {
            stringBuilder.append(Integer.toString((b & 255) + 256, 16).substring(1));
        }
        return stringBuilder.toString();
    }
}
