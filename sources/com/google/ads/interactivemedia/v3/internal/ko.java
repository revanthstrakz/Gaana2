package com.google.ads.interactivemedia.v3.internal;

import java.util.Arrays;

public final class ko extends km {
    public static boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static int a(Object... objArr) {
        return Arrays.hashCode(objArr);
    }
}
