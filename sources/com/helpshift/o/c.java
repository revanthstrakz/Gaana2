package com.helpshift.o;

import java.util.concurrent.TimeUnit;

public class c implements d {
    private final int a;
    private final long b;
    private final String c;

    public c(int i, long j, TimeUnit timeUnit, String str) {
        this.a = i;
        this.b = TimeUnit.MILLISECONDS.convert(j, timeUnit);
        this.c = str;
    }

    public String a() {
        return this.c;
    }

    public boolean a(int i, long j) {
        return i >= this.a || Math.abs(j) > this.b;
    }
}
