package com.simpl.android.fingerprint.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.telephony.TelephonyManager;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

final class d {
    Context a;
    TelephonyManager b;

    d(Context context) {
        this.a = context;
        this.b = (TelephonyManager) context.getSystemService("phone");
    }

    static String a(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes(), 0, str.length());
            return new BigInteger(1, instance.digest()).toString(16);
        } catch (Exception | NoSuchAlgorithmException unused) {
            return str;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0039  */
    static boolean a() {
        /*
        r0 = 0;
        r1 = 0;
        r2 = java.lang.Runtime.getRuntime();	 Catch:{ Throwable -> 0x003d, all -> 0x0036 }
        r3 = 2;
        r3 = new java.lang.String[r3];	 Catch:{ Throwable -> 0x003d, all -> 0x0036 }
        r4 = "/system/xbin/which";
        r3[r0] = r4;	 Catch:{ Throwable -> 0x003d, all -> 0x0036 }
        r4 = "su";
        r5 = 1;
        r3[r5] = r4;	 Catch:{ Throwable -> 0x003d, all -> 0x0036 }
        r2 = r2.exec(r3);	 Catch:{ Throwable -> 0x003d, all -> 0x0036 }
        r1 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x0034, all -> 0x0031 }
        r3 = new java.io.InputStreamReader;	 Catch:{ Throwable -> 0x0034, all -> 0x0031 }
        r4 = r2.getInputStream();	 Catch:{ Throwable -> 0x0034, all -> 0x0031 }
        r3.<init>(r4);	 Catch:{ Throwable -> 0x0034, all -> 0x0031 }
        r1.<init>(r3);	 Catch:{ Throwable -> 0x0034, all -> 0x0031 }
        r1 = r1.readLine();	 Catch:{ Throwable -> 0x0034, all -> 0x0031 }
        if (r1 == 0) goto L_0x002b;
    L_0x002a:
        r0 = r5;
    L_0x002b:
        if (r2 == 0) goto L_0x0030;
    L_0x002d:
        r2.destroy();
    L_0x0030:
        return r0;
    L_0x0031:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0037;
    L_0x0034:
        r1 = r2;
        goto L_0x003d;
    L_0x0036:
        r0 = move-exception;
    L_0x0037:
        if (r1 == 0) goto L_0x003c;
    L_0x0039:
        r1.destroy();
    L_0x003c:
        throw r0;
    L_0x003d:
        if (r1 == 0) goto L_0x0042;
    L_0x003f:
        r1.destroy();
    L_0x0042:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.simpl.android.fingerprint.a.d.a():boolean");
    }

    /* Access modifiers changed, original: final */
    public final String b() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (ApplicationInfo applicationInfo : this.a.getPackageManager().getInstalledApplications(0)) {
                if ((applicationInfo.flags & 1) == 0) {
                    stringBuilder.append(applicationInfo.packageName);
                    stringBuilder.append(",");
                }
            }
            return stringBuilder.toString();
        } catch (Throwable unused) {
            return "Apps not available";
        }
    }

    /* Access modifiers changed, original: final */
    public final String c() {
        try {
            return this.a.getPackageManager().getPackageInfo(this.a.getPackageName(), 0).versionName;
        } catch (NameNotFoundException unused) {
            return "p_disabled/p_not_avail";
        }
    }
}
