package com.moengage.core;

import android.content.Context;
import android.util.Log;
import com.moengage.core.executor.TaskProcessor;

public class Logger {
    public static final int DEBUG = 4;
    public static final int ERROR = 2;
    public static final int INFO = 1;
    private static int LOG_LEVEL = 1;
    private static boolean MOE_DEBUG_ENABLED = false;
    public static final int NO_LOGS = 0;
    private static final String TAG = "MoEngage_v8403";
    public static final int VERBOSE = 5;
    public static final int WARN = 3;
    private static Context mContext;

    private Logger() {
    }

    public static void v(String str) {
        if (isDebugEnabled() && LOG_LEVEL >= 5) {
            Log.v(TAG, str);
        }
    }

    public static void v(String str, Throwable th) {
        if (isDebugEnabled() && LOG_LEVEL >= 5) {
            Log.v(TAG, str, th);
        }
    }

    public static void d(String str) {
        if (isDebugEnabled() && LOG_LEVEL >= 4) {
            Log.d(TAG, str);
        }
    }

    public static void d(String str, Throwable th) {
        if (isDebugEnabled() && LOG_LEVEL >= 4) {
            Log.d(TAG, str, th);
        }
    }

    public static void i(String str) {
        if (isDebugEnabled() && LOG_LEVEL >= 1) {
            Log.i(TAG, str);
        }
    }

    public static void i(String str, Throwable th) {
        if (isDebugEnabled() && LOG_LEVEL >= 1) {
            Log.i(TAG, str, th);
        }
    }

    public static void w(String str) {
        if (isDebugEnabled() && LOG_LEVEL >= 3) {
            Log.w(TAG, str);
        }
    }

    public static void w(String str, Throwable th) {
        if (isDebugEnabled() && LOG_LEVEL >= 3) {
            Log.w(TAG, str, th);
        }
    }

    public static void e(String str) {
        if (isDebugEnabled() && LOG_LEVEL >= 2) {
            Log.e(TAG, str);
        }
    }

    public static void e(String str, Throwable th) {
        if (isDebugEnabled() && LOG_LEVEL >= 2) {
            Log.e(TAG, str, th);
        }
    }

    public static void f(String str) {
        if (mContext != null) {
            TaskProcessor.getInstance().addTask(new SendLogEntriesTask(mContext, str, null, "error"));
        }
        if (isDebugEnabled() && LOG_LEVEL >= 2) {
            Log.e(TAG, str);
        }
    }

    public static void f(String str, Throwable th) {
        if (mContext != null) {
            TaskProcessor.getInstance().addTask(new SendLogEntriesTask(mContext, str, th, "error"));
        }
        if (isDebugEnabled() && LOG_LEVEL >= 2) {
            Log.e(TAG, str, th);
        }
    }

    public static void setLogLevel(int i) {
        LOG_LEVEL = i;
    }

    public static boolean isDebugEnabled() {
        return MOE_DEBUG_ENABLED;
    }

    public static void enableDebugLog(Context context) {
        if (context != null) {
            try {
                boolean z = false;
                if (((context.getApplicationInfo().flags & 2) != 0) || ConfigurationProvider.getInstance(context).isDebugLogEnabled()) {
                    z = true;
                }
                setLogStatus(z);
                mContext = context;
            } catch (Exception e) {
                e("Logger : enableDebugLog", e);
            }
        }
    }

    public static void setLogStatus(boolean z) {
        MOE_DEBUG_ENABLED = z;
    }
}
