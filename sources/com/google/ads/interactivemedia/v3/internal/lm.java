package com.google.ads.interactivemedia.v3.internal;

import java.util.Iterator;

abstract class lm<F, T> implements Iterator<T> {
    final Iterator<? extends F> a;

    public abstract T a(F f);

    public final boolean hasNext() {
        return this.a.hasNext();
    }

    public final T next() {
        return a(this.a.next());
    }

    public final void remove() {
        this.a.remove();
    }
}
