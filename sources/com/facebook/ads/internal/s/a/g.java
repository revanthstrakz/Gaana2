package com.facebook.ads.internal.s.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.Signature;
import android.os.Build;
import android.support.annotation.Nullable;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;

public class g {
    private static final String a = "g";

    public enum a {
        UNKNOWN(0),
        UNROOTED(1),
        ROOTED(2);
        
        public final int d;

        private a(int i) {
            this.d = i;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x001d A:{Catch:{ Throwable -> 0x0020 }} */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x001a A:{Catch:{ Throwable -> 0x0020 }} */
    public static com.facebook.ads.internal.s.a.g.a a() {
        /*
        r0 = c();	 Catch:{ Throwable -> 0x0020 }
        if (r0 != 0) goto L_0x0017;
    L_0x0006:
        r0 = b();	 Catch:{ Throwable -> 0x0020 }
        if (r0 != 0) goto L_0x0017;
    L_0x000c:
        r0 = "su";
        r0 = a(r0);	 Catch:{ Throwable -> 0x0020 }
        if (r0 == 0) goto L_0x0015;
    L_0x0014:
        goto L_0x0017;
    L_0x0015:
        r0 = 0;
        goto L_0x0018;
    L_0x0017:
        r0 = 1;
    L_0x0018:
        if (r0 == 0) goto L_0x001d;
    L_0x001a:
        r0 = com.facebook.ads.internal.s.a.g.a.ROOTED;	 Catch:{ Throwable -> 0x0020 }
        return r0;
    L_0x001d:
        r0 = com.facebook.ads.internal.s.a.g.a.UNROOTED;	 Catch:{ Throwable -> 0x0020 }
        return r0;
    L_0x0020:
        r0 = com.facebook.ads.internal.s.a.g.a.UNKNOWN;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.s.a.g.a():com.facebook.ads.internal.s.a.g$a");
    }

    @Nullable
    public static String a(Context context) {
        try {
            return b(context);
        } catch (Exception unused) {
            return null;
        }
    }

    private static PublicKey a(Signature signature) {
        return CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signature.toByteArray())).getPublicKey();
    }

    private static boolean a(String str) {
        for (String file : System.getenv("PATH").split(":")) {
            File file2 = new File(file);
            if (file2.exists() && file2.isDirectory()) {
                File[] listFiles = file2.listFiles();
                if (listFiles == null) {
                    continue;
                } else {
                    for (File name : listFiles) {
                        if (name.getName().equals(str)) {
                            return true;
                        }
                    }
                    continue;
                }
            }
        }
        return false;
    }

    @SuppressLint({"PackageManagerGetSignatures"})
    private static String b(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Signature a : context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures) {
            stringBuilder.append(i.a(MessageDigest.getInstance("SHA1").digest(a(a).getEncoded())));
            stringBuilder.append(";");
        }
        return stringBuilder.toString();
    }

    private static boolean b() {
        String str = Build.TAGS;
        return str != null && str.contains("test-keys");
    }

    private static boolean c() {
        return new File("/system/app/Superuser.apk").exists();
    }
}
