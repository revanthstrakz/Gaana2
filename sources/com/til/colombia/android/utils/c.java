package com.til.colombia.android.utils;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.i;

public final class c {
    public static final String a = "ColombiaAdsPref";
    public static final String b = "ColombiaExtraPref";
    public static final String c = "firstInstall";
    private static final String d = "data";

    public static boolean a(Context context, String str, String str2) {
        if (context == null || str == null || str2 == null || "".equals(str.trim()) || "".equals(str2.trim())) {
            return false;
        }
        return context.getSharedPreferences(str, 0).contains(str2);
    }

    public static boolean a(Context context, String str, String str2, String str3) {
        if (context == null || str == null || str2 == null || "".equals(str.trim()) || "".equals(str2.trim())) {
            Log.a(i.f, "Failed to set preferences..App context NULL");
            return false;
        }
        Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putString(str2, str3);
        edit.apply();
        return true;
    }

    public static void a(Context context, String str, String str2, boolean z) {
        if (context == null || str == null || str2 == null || "".equals(str.trim()) || "".equals(str2.trim())) {
            Log.b(i.f, "Failed to set preferences..App context NULL");
            return;
        }
        Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putBoolean(str2, true);
        edit.apply();
    }

    public static void a(Context context, String str, String str2, int i) {
        if (context == null || str == null || str2 == null || "".equals(str.trim()) || "".equals(str2.trim())) {
            Log.b(i.f, "Failed to set preferences..App context NULL");
            return;
        }
        Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putInt(str2, i);
        edit.apply();
    }

    public static void a(Context context, String str, String str2, long j) {
        if (context == null || str == null || str2 == null || "".equals(str.trim()) || "".equals(str2.trim())) {
            Log.b(i.f, "Failed to set preferences..App context NULL");
            return;
        }
        Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putLong(str2, j);
        edit.apply();
    }

    private static void a(Context context, String str, String str2, float f) {
        if (context == null || str == null || str2 == null || "".equals(str.trim()) || "".equals(str2.trim())) {
            Log.b(i.f, "Failed to set preferences..App context NULL");
            return;
        }
        Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putFloat(str2, f);
        edit.apply();
    }

    public static String b(Context context, String str, String str2) {
        if (context != null && str != null && str2 != null && !"".equals(str.trim()) && !"".equals(str2.trim())) {
            return context.getSharedPreferences(str, 0).getString(str2, "");
        }
        Log.b(i.f, "Failed to get preferences..App context NULL");
        return null;
    }

    public static boolean c(Context context, String str, String str2) {
        if (context != null && str != null && str2 != null && !"".equals(str.trim()) && !"".equals(str2.trim())) {
            return context.getSharedPreferences(str, 0).getBoolean(str2, false);
        }
        Log.b(i.f, "Failed to get preferences..App context NULL");
        return false;
    }

    public static int d(Context context, String str, String str2) {
        if (context != null && str != null && str2 != null && !"".equals(str.trim()) && !"".equals(str2.trim())) {
            return context.getSharedPreferences(str, 0).getInt(str2, 0);
        }
        Log.b(i.f, "Failed to get preferences..App context NULL");
        return 0;
    }

    public static long e(Context context, String str, String str2) {
        if (context != null && str != null && str2 != null && !"".equals(str.trim()) && !"".equals(str2.trim())) {
            return context.getSharedPreferences(str, 0).getLong(str2, 0);
        }
        Log.b(i.f, "Failed to get preferences..App context NULL");
        return 0;
    }

    public static void a(Context context, String str) {
        context.getSharedPreferences(str, 0).edit().clear().apply();
    }
}
