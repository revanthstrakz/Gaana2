package com.google.android.gms.internal.icing;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.UserManager;
import android.support.annotation.RequiresApi;
import android.support.annotation.VisibleForTesting;

public class zzba {
    private static volatile UserManager zzcr;
    private static volatile boolean zzcs = (zzq() ^ 1);

    private zzba() {
    }

    public static boolean zzq() {
        return VERSION.SDK_INT >= 24;
    }

    public static boolean isUserUnlocked(Context context) {
        return !zzq() || zza(context);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x003b  */
    @android.annotation.TargetApi(24)
    @android.support.annotation.RequiresApi(24)
    private static boolean zza(android.content.Context r7) {
        /*
        r0 = zzcs;
        if (r0 != 0) goto L_0x003d;
    L_0x0004:
        r1 = 1;
        r2 = r0;
        r0 = r1;
    L_0x0007:
        r3 = 2;
        r4 = 0;
        if (r0 > r3) goto L_0x0038;
    L_0x000b:
        r3 = zzb(r7);
        if (r3 != 0) goto L_0x0014;
    L_0x0011:
        zzcs = r1;
        return r1;
    L_0x0014:
        r5 = r3.isUserUnlocked();	 Catch:{ NullPointerException -> 0x002b }
        if (r5 != 0) goto L_0x0027;
    L_0x001a:
        r5 = android.os.Process.myUserHandle();	 Catch:{ NullPointerException -> 0x002b }
        r3 = r3.isUserRunning(r5);	 Catch:{ NullPointerException -> 0x002b }
        if (r3 != 0) goto L_0x0025;
    L_0x0024:
        goto L_0x0027;
    L_0x0025:
        r2 = 0;
        goto L_0x0028;
    L_0x0027:
        r2 = r1;
    L_0x0028:
        zzcs = r2;	 Catch:{ NullPointerException -> 0x002b }
        goto L_0x0038;
    L_0x002b:
        r3 = move-exception;
        r5 = "DirectBootUtils";
        r6 = "Failed to check if user is unlocked";
        android.util.Log.w(r5, r6, r3);
        zzcr = r4;
        r0 = r0 + 1;
        goto L_0x0007;
    L_0x0038:
        r0 = r2;
        if (r0 == 0) goto L_0x003d;
    L_0x003b:
        zzcr = r4;
    L_0x003d:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzba.zza(android.content.Context):boolean");
    }

    @VisibleForTesting
    @TargetApi(24)
    @RequiresApi(24)
    private static UserManager zzb(Context context) {
        UserManager userManager = zzcr;
        if (userManager == null) {
            synchronized (zzba.class) {
                userManager = zzcr;
                if (userManager == null) {
                    UserManager userManager2 = (UserManager) context.getSystemService(UserManager.class);
                    zzcr = userManager2;
                    userManager = userManager2;
                }
            }
        }
        return userManager;
    }
}
