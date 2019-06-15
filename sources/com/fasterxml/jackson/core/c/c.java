package com.fasterxml.jackson.core.c;

public abstract class c {
    protected final String a;
    protected final int b;

    public abstract boolean a(int i);

    public abstract boolean a(int i, int i2);

    public abstract boolean a(int[] iArr, int i);

    public boolean equals(Object obj) {
        return obj == this;
    }

    protected c(String str, int i) {
        this.a = str;
        this.b = i;
    }

    public String a() {
        return this.a;
    }

    public String toString() {
        return this.a;
    }

    public final int hashCode() {
        return this.b;
    }
}
