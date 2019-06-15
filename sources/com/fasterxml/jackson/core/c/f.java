package com.fasterxml.jackson.core.c;

public final class f extends c {
    final int c;
    final int d;
    final int e;

    public boolean a(int i) {
        return false;
    }

    public boolean a(int i, int i2) {
        return false;
    }

    f(String str, int i, int i2, int i3, int i4) {
        super(str, i);
        this.c = i2;
        this.d = i3;
        this.e = i4;
    }

    public boolean a(int[] iArr, int i) {
        return i == 3 && iArr[0] == this.c && iArr[1] == this.d && iArr[2] == this.e;
    }
}
