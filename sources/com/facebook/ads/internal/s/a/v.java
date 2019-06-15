package com.facebook.ads.internal.s.a;

import java.util.Locale;

public class v {
    public static String a(double d) {
        return String.format(Locale.US, "%.3f", new Object[]{Double.valueOf(d)});
    }

    public static String a(long j) {
        return Long.toString(j);
    }

    @Deprecated
    public static String b(long j) {
        return a(((double) j) / 1000.0d);
    }
}
