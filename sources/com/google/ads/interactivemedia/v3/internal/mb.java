package com.google.ads.interactivemedia.v3.internal;

final class mb {
    private final Object a;
    private final int b;

    mb(Object obj) {
        this.b = System.identityHashCode(obj);
        this.a = obj;
    }

    public int hashCode() {
        return this.b;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof mb)) {
            return false;
        }
        mb mbVar = (mb) obj;
        if (this.b != mbVar.b) {
            return false;
        }
        if (this.a == mbVar.a) {
            z = true;
        }
        return z;
    }
}
