package com.integralads.avid.library.inmobi.utils;

import android.text.TextUtils;
import android.util.Log;

public class AvidLogs {
    private static final boolean DEBUG = true;
    private static final String TAG = "AVID";

    public static void d(String str) {
        if (!TextUtils.isEmpty(str)) {
            Log.d(TAG, str);
        }
    }

    public static void w(String str) {
        if (!TextUtils.isEmpty(str)) {
            Log.w(TAG, str);
        }
    }

    public static void i(String str) {
        if (!TextUtils.isEmpty(str)) {
            Log.i(TAG, str);
        }
    }

    public static void e(String str) {
        if (!TextUtils.isEmpty(str)) {
            Log.e(TAG, str);
        }
    }

    public static void e(String str, Exception exception) {
        if (!TextUtils.isEmpty(str) || exception != null) {
            Log.e(TAG, str, exception);
        }
    }
}
