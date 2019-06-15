package com.til.colombia.android.network;

public final class i {
    String a;
    public int b;
    public int c = 1;

    public i(String str, int i, int i2) {
        this.a = str;
        this.b = i;
    }

    public final int a() {
        return this.c;
    }

    public final i a(int i) {
        this.c = i;
        return this;
    }

    public final int b() {
        return this.b;
    }

    public final void b(int i) {
        this.b = 10;
    }

    /* Access modifiers changed, original: protected|final */
    public final String c() {
        return this.a;
    }

    private void a(String str) {
        if (str != null && !"".equals(str.trim())) {
            this.a = str;
        }
    }
}
