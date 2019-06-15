package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.internal.ki;

@ki(a = g.class)
public abstract class n {
    public abstract double end();

    public abstract boolean played();

    public abstract double start();

    private static n create(double d, double d2, boolean z) {
        return new g(d, d2, z);
    }
}
