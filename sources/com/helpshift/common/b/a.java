package com.helpshift.common.b;

import java.util.concurrent.TimeUnit;

public class a {
    public final long a;
    public final TimeUnit b;

    public static a a(long j, TimeUnit timeUnit) {
        return new a(j, timeUnit);
    }

    private a(long j, TimeUnit timeUnit) {
        this.a = j;
        this.b = timeUnit;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.a);
        stringBuilder.append(" ");
        stringBuilder.append(this.b);
        return stringBuilder.toString();
    }
}
