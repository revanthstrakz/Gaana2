package com.facebook.ads.internal.h;

import android.content.Context;
import android.os.Process;
import android.support.annotation.Nullable;
import com.facebook.ads.BuildConfig;
import com.facebook.ads.internal.s.a.o;
import com.facebook.ads.internal.s.a.s;
import com.gaana.login.LoginManager;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Map;

public class c implements UncaughtExceptionHandler {
    private final UncaughtExceptionHandler a;
    private final Context b;
    private final Map<String, String> c;

    public c(@Nullable UncaughtExceptionHandler uncaughtExceptionHandler, Context context, Map<String, String> map) {
        this.a = uncaughtExceptionHandler;
        if (context == null) {
            throw new IllegalArgumentException("Missing Context");
        }
        this.b = context.getApplicationContext();
        this.c = map;
    }

    private void a(Thread thread, Throwable th) {
        if (this.a != null) {
            this.a.uncaughtException(thread, th);
            return;
        }
        try {
            Process.killProcess(Process.myPid());
        } catch (Throwable unused) {
        }
        try {
            System.exit(10);
        } catch (Throwable unused2) {
        }
    }

    public void uncaughtException(Thread thread, Throwable th) {
        try {
            String a = s.a(th);
            if (a != null && a.contains(BuildConfig.APPLICATION_ID)) {
                Map a2 = new b(a, this.c).a();
                a2.put(LoginManager.TAG_SUBTYPE, "crash");
                a2.put("subtype_code", "0");
                e.a(new d(o.b(), o.c(), a2), this.b);
            }
        } catch (Exception unused) {
        }
        a(thread, th);
    }
}
