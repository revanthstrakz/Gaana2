package com.helpshift.util;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import com.helpshift.b.b;
import com.helpshift.common.platform.k;
import com.helpshift.common.platform.p;
import com.helpshift.d;

public class o {
    private static final Object a = new Object();
    private static final b b = new b();
    private static Context c;
    private static com.helpshift.b d;
    private static p e;

    public static b a() {
        return b;
    }

    @TargetApi(14)
    private static void b(Context context) {
        ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(b);
    }

    public static Context b() {
        return c;
    }

    public static void a(Context context) {
        synchronized (a) {
            if (c == null) {
                c = context;
                b(context);
            }
        }
    }

    public static void a(String str, String str2, String str3) {
        if (e == null) {
            e = new k(c, str, str2, str3);
        }
        if (d == null) {
            d = new d(e);
        }
    }

    public static p c() {
        return e;
    }

    public static com.helpshift.b d() {
        return d;
    }
}
