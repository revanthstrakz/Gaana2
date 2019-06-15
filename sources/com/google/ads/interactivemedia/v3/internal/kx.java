package com.google.ads.interactivemedia.v3.internal;

import java.io.Serializable;
import java.util.Comparator;

final class kx<T> extends lh<T> implements Serializable {
    final Comparator<T> a;

    kx(Comparator<T> comparator) {
        this.a = (Comparator) kr.a(comparator);
    }

    public int compare(T t, T t2) {
        return this.a.compare(t, t2);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof kx)) {
            return false;
        }
        return this.a.equals(((kx) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public String toString() {
        return this.a.toString();
    }
}
