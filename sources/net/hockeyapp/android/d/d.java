package net.hockeyapp.android.d;

import android.util.Log;

public class d {
    private static int a = 6;

    public static void a(String str, String str2) {
        str = d(str);
        if (a <= 2) {
            Log.v(str, str2);
        }
    }

    public static void a(String str) {
        b(null, str);
    }

    public static void b(String str, String str2) {
        str = d(str);
        if (a <= 3) {
            Log.d(str, str2);
        }
    }

    public static void b(String str) {
        c(null, str);
    }

    public static void c(String str, String str2) {
        str = d(str);
        if (a <= 5) {
            Log.w(str, str2);
        }
    }

    public static void c(String str) {
        d(null, str);
    }

    public static void d(String str, String str2) {
        str = d(str);
        if (a <= 6) {
            Log.e(str, str2);
        }
    }

    public static void a(String str, Throwable th) {
        a(null, str, th);
    }

    public static void a(String str, String str2, Throwable th) {
        str = d(str);
        if (a <= 6) {
            Log.e(str, str2, th);
        }
    }

    static String d(String str) {
        return (str == null || str.length() == 0 || str.length() > 23) ? "HockeyApp" : str;
    }
}
