package com.google.ads.interactivemedia.v3.internal;

public final class mc<L, R> extends md<L, R> {
    private static final mc c = a(null, null);
    public final L a;
    public final R b;

    public static <L, R> mc<L, R> a(L l, R r) {
        return new mc(l, r);
    }

    public mc(L l, R r) {
        this.a = l;
        this.b = r;
    }

    public L a() {
        return this.a;
    }

    public R b() {
        return this.b;
    }

    public R setValue(R r) {
        throw new UnsupportedOperationException();
    }
}
