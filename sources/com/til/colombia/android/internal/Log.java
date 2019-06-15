package com.til.colombia.android.internal;

public final class Log {
    private static INTERNAL_LOG_LEVEL a = INTERNAL_LOG_LEVEL.NOT_SET;

    public enum INTERNAL_LOG_LEVEL {
        NOT_SET(0),
        NONE(0),
        DEBUG(1),
        INTERNAL(2);
        
        private final int ll;

        private INTERNAL_LOG_LEVEL(int i) {
            this.ll = i;
        }

        public final int getValue() {
            return this.ll;
        }
    }

    public static void a(INTERNAL_LOG_LEVEL internal_log_level) {
        a = internal_log_level;
    }

    public static void a(String str, String str2) {
        if (a.getValue() >= INTERNAL_LOG_LEVEL.INTERNAL.getValue()) {
            StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(stackTraceElement.getFileName());
            stringBuilder.append(": ");
            stringBuilder.append(stackTraceElement.getMethodName());
            stringBuilder.append(" ");
            stringBuilder.append(str2);
            android.util.Log.v(str, stringBuilder.toString());
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (a.getValue() >= INTERNAL_LOG_LEVEL.INTERNAL.getValue()) {
            StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(stackTraceElement.getFileName());
            stringBuilder.append(": ");
            stringBuilder.append(stackTraceElement.getMethodName());
            stringBuilder.append(" ");
            stringBuilder.append(str2);
            android.util.Log.e(str, stringBuilder.toString(), th);
        }
    }

    public static void b(String str, String str2) {
        if (a.getValue() >= INTERNAL_LOG_LEVEL.DEBUG.getValue()) {
            android.util.Log.d(str, str2);
        }
    }

    public static void b(String str, String str2, Throwable th) {
        switch (f.a[a.ordinal()]) {
            case 1:
                b(str, str2);
                return;
            case 2:
                a(str, str2, th);
                break;
        }
    }
}
