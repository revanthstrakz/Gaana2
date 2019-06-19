package com.facebook.ads.internal.k;

import android.content.Context;
import android.util.Log;
import com.facebook.ads.internal.h.c;

public class a {
    private static final String a = "com.facebook.ads.internal.k.a";
    private static a b;
    private static boolean c;
    private Context d;

    private a(Context context) {
        this.d = context;
    }

    public static a a(Context context) {
        if (b == null) {
            context = context.getApplicationContext();
            synchronized (context) {
                if (b == null) {
                    b = new a(context);
                }
            }
        }
        return b;
    }

    public synchronized void a() {
        if (!c) {
            if (com.facebook.ads.internal.n.a.j(this.d)) {
                try {
                    Thread.setDefaultUncaughtExceptionHandler(new c(Thread.getDefaultUncaughtExceptionHandler(), this.d, new c(this.d, false).b()));
                } catch (SecurityException e) {
                    Log.e(a, "No permissions to set the default uncaught exception handler", e);
                }
            }
            c = true;
        }
    }
}
