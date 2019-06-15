package com.google.ads.interactivemedia.v3.internal;

import java.util.ListIterator;

public abstract class lo<E> extends ln<E> implements ListIterator<E> {
    protected lo() {
    }

    @Deprecated
    public final void add(E e) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void set(E e) {
        throw new UnsupportedOperationException();
    }
}
