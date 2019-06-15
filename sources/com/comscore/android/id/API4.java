package com.comscore.android.id;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;

@SuppressLint({"NewApi"})
public class API4 {
    private static boolean a = false;
    private static boolean b = false;

    public static boolean isPackageInstalledFromGooglePlayStore(Context context) {
        if (a) {
            return b;
        }
        if (VERSION.SDK_INT > 4) {
            try {
                String installerPackageName = context.getPackageManager().getInstallerPackageName(context.getPackageName());
                if ("com.android.vending".equals(installerPackageName) || "com.google.play".equals(installerPackageName)) {
                    a = true;
                    b = true;
                    return true;
                }
            } catch (Exception unused) {
            }
        }
        a = true;
        b = false;
        return false;
    }
}
