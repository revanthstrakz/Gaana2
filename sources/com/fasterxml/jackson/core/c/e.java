package com.fasterxml.jackson.core.c;

public final class e extends c {
    final int c;
    final int d;

    public boolean a(int i) {
        return false;
    }

    e(String str, int i, int i2, int i3) {
        super(str, i);
        this.c = i2;
        this.d = i3;
    }

    public boolean a(int i, int i2) {
        return i == this.c && i2 == this.d;
    }

    public boolean a(int[] iArr, int i) {
        return i == 2 && iArr[0] == this.c && iArr[1] == this.d;
    }
}
