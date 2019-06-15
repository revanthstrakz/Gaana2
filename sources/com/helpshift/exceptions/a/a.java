package com.helpshift.exceptions.a;

import android.content.Context;
import android.util.Log;
import com.helpshift.util.g;
import com.helpshift.util.l;
import java.lang.Thread.UncaughtExceptionHandler;

public class a {
    private static final CharSequence a = "com.helpshift";

    public static void a(final Context context) {
        final UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            public void uncaughtException(Thread thread, Throwable th) {
                if (a.a(th)) {
                    l.d("UncaughtExceptionHandler", "UNCAUGHT EXCEPTION ", th, (com.helpshift.j.c.a[]) g.a(context, thread).toArray(new com.helpshift.j.c.a[0]));
                }
                if (defaultUncaughtExceptionHandler != null) {
                    defaultUncaughtExceptionHandler.uncaughtException(thread, th);
                }
            }
        });
    }

    static boolean a(Throwable th) {
        if (th != null && Log.getStackTraceString(th).contains(a)) {
            return true;
        }
        return false;
    }
}
