package com.facebook.ads.internal.s.a;

import java.util.Random;

public class q {
    private static String a() {
        return s.a(Thread.currentThread().getStackTrace());
    }

    public static String a(int i) {
        return (i > 0 && new Random().nextFloat() < 1.0f / ((float) i)) ? a() : null;
    }
}
