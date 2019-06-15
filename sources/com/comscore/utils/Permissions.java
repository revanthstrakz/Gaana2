package com.comscore.utils;

import android.content.Context;

public class Permissions {
    private static String[] a;

    public static Boolean check(Context context, String str) {
        int i = 0;
        Boolean valueOf = Boolean.valueOf(false);
        if (a == null) {
            try {
                a = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            } catch (Exception unused) {
            }
        }
        if (a != null) {
            while (i < a.length) {
                if (a[i].equals(str)) {
                    return Boolean.valueOf(true);
                }
                i++;
            }
        }
        return valueOf;
    }
}
