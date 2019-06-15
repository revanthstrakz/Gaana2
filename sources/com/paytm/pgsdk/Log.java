package com.paytm.pgsdk;

public class Log {
    private static boolean ENABLE_DEBUG_LOG = false;

    public static void i(String str, String str2) {
        android.util.Log.i(str, str2);
    }

    public static void e(String str, String str2) {
        if (ENABLE_DEBUG_LOG) {
            android.util.Log.e(str, str2);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (ENABLE_DEBUG_LOG) {
            android.util.Log.e(str, str2, th);
        }
    }

    public static void d(String str, String str2) {
        if (ENABLE_DEBUG_LOG) {
            android.util.Log.d(str, str2);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (ENABLE_DEBUG_LOG) {
            android.util.Log.d(str, str2, th);
        }
    }

    public static void v(String str, String str2) {
        if (ENABLE_DEBUG_LOG) {
            android.util.Log.v(str, str2);
        }
    }

    public static void w(String str, String str2) {
        if (ENABLE_DEBUG_LOG) {
            android.util.Log.w(str, str2);
        }
    }

    public static void w(String str, String str2, Throwable th) {
        if (ENABLE_DEBUG_LOG) {
            android.util.Log.w(str, str2, th);
        }
    }

    public static void setEnableDebugLog(boolean z) {
        ENABLE_DEBUG_LOG = z;
    }
}
