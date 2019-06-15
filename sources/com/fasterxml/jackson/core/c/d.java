package com.fasterxml.jackson.core.c;

public final class d extends c {
    static final d c = new d("", 0, 0);
    final int d;

    d(String str, int i, int i2) {
        super(str, i);
        this.d = i2;
    }

    static d b() {
        return c;
    }

    public boolean a(int i) {
        return i == this.d;
    }

    public boolean a(int i, int i2) {
        return i == this.d && i2 == 0;
    }

    public boolean a(int[] iArr, int i) {
        return i == 1 && iArr[0] == this.d;
    }
}
