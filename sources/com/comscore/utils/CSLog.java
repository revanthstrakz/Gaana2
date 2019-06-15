package com.comscore.utils;

import android.util.Log;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;

public class CSLog {
    public static void d(Class<? extends Object> cls, String str) {
        if (Constants.DEBUG) {
            Log.d(cls.getSimpleName(), str);
        }
    }

    public static void d(Object obj, String str) {
        if (Constants.DEBUG) {
            d(obj.getClass(), str);
        }
    }

    public static void e(Class<? extends Object> cls, String str) {
        if (Constants.DEBUG) {
            Log.e(cls.getSimpleName(), str);
        }
    }

    public static void e(Object obj, String str) {
        if (Constants.DEBUG) {
            e(obj.getClass(), str);
        }
    }

    public static void printStackTrace(Exception exception) {
        if (Constants.DEBUG) {
            ThrowableExtension.printStackTrace(exception);
        }
    }

    public static void v(Class<? extends Object> cls, String str) {
        if (Constants.DEBUG) {
            Log.v(cls.getSimpleName(), str);
        }
    }

    public static void v(Object obj, String str) {
        if (Constants.DEBUG) {
            v(obj.getClass(), str);
        }
    }
}
