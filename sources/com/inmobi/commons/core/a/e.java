package com.inmobi.commons.core.a;

import java.lang.Thread.UncaughtExceptionHandler;

public class e implements UncaughtExceptionHandler {
    private static final String b = "e";
    private UncaughtExceptionHandler a;

    e(UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.a = uncaughtExceptionHandler;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        Object obj = null;
        for (StackTraceElement className : th.getStackTrace()) {
            if (className.getClassName().contains("com.inmobi.")) {
                obj = 1;
                break;
            }
        }
        if (obj != null) {
            try {
                a.a().a(new d(thread, th));
            } catch (Exception unused) {
                StringBuilder stringBuilder = new StringBuilder("Error in submitting telemetry event : (");
                stringBuilder.append(th.getMessage());
                stringBuilder.append(")");
            }
        }
        this.a.uncaughtException(thread, th);
    }
}
