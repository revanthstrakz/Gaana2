package com.comscore.utils;

import android.content.Context;
import android.os.Build.VERSION;

public class API4 {
    public static boolean isPackageInstalledFromGooglePlayStore(Context context) {
        if (VERSION.SDK_INT > 4) {
            try {
                String installerPackageName = context.getPackageManager().getInstallerPackageName(context.getPackageName());
                return "com.android.vending".equals(installerPackageName) || "com.google.play".equals(installerPackageName);
            } catch (Exception unused) {
            }
        }
        return false;
    }
}
