package com.facebook.ads.internal.s.d;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import com.facebook.ads.internal.h.e;
import com.facebook.ads.internal.k.c;
import com.gaana.login.LoginManager;
import java.util.Map;
import java.util.Set;

public class a {
    public static void a(Context context, String str, int i, Exception exception) {
        if (a(context, str, i, Math.random())) {
            b(context, str, i, exception);
        }
    }

    @VisibleForTesting
    static boolean a(Context context, String str, int i, double d) {
        double m;
        double d2;
        Set k = com.facebook.ads.internal.n.a.k(context);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(":");
        stringBuilder.append(i);
        if (k.contains(stringBuilder.toString())) {
            m = (double) (com.facebook.ads.internal.n.a.m(context) * com.facebook.ads.internal.n.a.l(context));
            d2 = 10000.0d;
        } else {
            m = (double) com.facebook.ads.internal.n.a.m(context);
            d2 = 100.0d;
        }
        return d >= 1.0d - (m / d2);
    }

    private static void b(Context context, String str, int i, Exception exception) {
        Map b = new c(context, false).b();
        b.put(LoginManager.TAG_SUBTYPE, str);
        b.put("subtype_code", String.valueOf(i));
        e.a(exception, context, b);
    }
}
