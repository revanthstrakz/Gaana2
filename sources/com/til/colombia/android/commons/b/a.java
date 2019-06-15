package com.til.colombia.android.commons.b;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.til.colombia.android.internal.Log;

public final class a {
    public static String a = null;
    public static String b = null;
    public static String c = null;
    private static final String d = "Col:aos:4.0.0APP-INFO";

    public static String a() {
        return b;
    }

    private static void a(String str) {
        b = str;
    }

    private static String d() {
        return a;
    }

    private static void b(String str) {
        a = str;
    }

    public static String b() {
        return c;
    }

    private static void c(String str) {
        c = str;
    }

    public static void c() {
        try {
            Context a = com.til.colombia.android.internal.a.a();
            PackageManager packageManager = a.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(a.getPackageName(), 128);
            if (applicationInfo != null) {
                b = applicationInfo.packageName;
                a = applicationInfo.loadLabel(packageManager).toString();
            }
            PackageInfo packageInfo = packageManager.getPackageInfo(a.getPackageName(), 128);
            String str = null;
            if (packageInfo != null) {
                str = packageInfo.versionName;
                if (str == null || str.equals("")) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(packageInfo.versionCode);
                    str = stringBuilder.toString();
                }
            }
            if (!(str == null || str.equals(""))) {
                c = str;
            }
        } catch (Exception e) {
            Log.a(d, "Failed to fill AppInfo", e);
        }
    }
}
