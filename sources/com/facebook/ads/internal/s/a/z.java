package com.facebook.ads.internal.s.a;

import java.lang.ref.WeakReference;

public abstract class z<T> implements Runnable {
    private final WeakReference<T> a;

    public z(T t) {
        this.a = new WeakReference(t);
    }

    public T a() {
        return this.a.get();
    }
}
