package com.a;

import android.view.animation.Interpolator;

public class a implements Interpolator {
    double a = 1.0d;
    double b = 10.0d;

    public a(double d, double d2) {
        this.a = d;
        this.b = d2;
    }

    public float getInterpolation(float f) {
        return (float) (((-1.0d * Math.pow(2.718281828459045d, ((double) (-f)) / this.a)) * Math.cos(this.b * ((double) f))) + 1.0d);
    }
}
