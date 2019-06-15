package com.google.ads.interactivemedia.v3.internal;

import java.util.Comparator;

public abstract class lh<T> implements Comparator<T> {
    public static <T> lh<T> a(Comparator<T> comparator) {
        if (comparator instanceof lh) {
            return (lh) comparator;
        }
        return new kx(comparator);
    }

    public abstract int compare(T t, T t2);

    protected lh() {
    }

    public <F> lh<F> a(kn<F, ? extends T> knVar) {
        return new ku(knVar, this);
    }
}
