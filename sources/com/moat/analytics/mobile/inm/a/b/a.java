package com.moat.analytics.mobile.inm.a.b;

import java.util.NoSuchElementException;

public final class a<T> {
    private static final a<?> a = new a();
    private final T b;

    private a() {
        this.b = null;
    }

    private a(T t) {
        if (t == null) {
            throw new NullPointerException("Optional of null value.");
        }
        this.b = t;
    }

    public static <T> a<T> a() {
        return a;
    }

    public static <T> a<T> a(T t) {
        return new a(t);
    }

    public static <T> a<T> b(T t) {
        return t == null ? a() : a(t);
    }

    public final T b() {
        if (this.b != null) {
            return this.b;
        }
        throw new NoSuchElementException("No value present");
    }

    public final T c(T t) {
        return this.b != null ? this.b : t;
    }

    public final boolean c() {
        return this.b != null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.b != aVar.b ? (this.b == null || aVar.b == null || !this.b.equals(aVar.b)) ? false : true : true;
    }

    public final int hashCode() {
        return this.b == null ? 0 : this.b.hashCode();
    }

    public final String toString() {
        if (this.b == null) {
            return "Optional.empty";
        }
        return String.format("Optional[%s]", new Object[]{this.b});
    }
}
