package com.facebook.ads.internal.s.a;

import android.support.annotation.Nullable;
import android.text.TextUtils;

public class b {
    private static boolean a;
    private static boolean b;

    @Nullable
    public static synchronized String a(String str) {
        synchronized (b.class) {
            if (a()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("fb.e2e.");
                stringBuilder.append(str);
                str = System.getProperty(stringBuilder.toString());
                return str;
            }
            return null;
        }
    }

    public static synchronized boolean a() {
        boolean z;
        synchronized (b.class) {
            if (!b) {
                a = "true".equals(System.getProperty("fb.running_e2e"));
                b = true;
            }
            z = a;
        }
        return z;
    }

    public static synchronized boolean b(String str) {
        int isEmpty;
        synchronized (b.class) {
            isEmpty = TextUtils.isEmpty(a(str)) ^ 1;
        }
        return isEmpty;
    }
}
