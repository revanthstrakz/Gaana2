package com.helpshift.util;

import android.os.SystemClock;
import com.helpshift.k.b;

public class x {
    @Deprecated
    public static String a(Float f) {
        String format = i.c.format(((double) System.currentTimeMillis()) / 1000.0d);
        if (f == null || f.floatValue() == 0.0f) {
            return format;
        }
        return i.c.format(Double.valueOf(((double) System.currentTimeMillis()) / 1000.0d).doubleValue() + ((double) f.floatValue()));
    }

    public static long b(Float f) {
        long currentTimeMillis = System.currentTimeMillis();
        return (f == null || f.floatValue() == 0.0f) ? currentTimeMillis : (long) (((float) currentTimeMillis) + (f.floatValue() * 1000.0f));
    }

    public static long a() {
        return b(b.a().b.a());
    }

    public static String b() {
        return a(b.a().b.a());
    }

    public long c() {
        return SystemClock.elapsedRealtime();
    }

    public static String a(long j) {
        long currentTimeMillis = (System.currentTimeMillis() / 1000) - j;
        StringBuilder stringBuilder;
        StringBuilder stringBuilder2;
        if (currentTimeMillis < 60) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(currentTimeMillis);
            stringBuilder.append("s");
            return stringBuilder.toString();
        } else if (currentTimeMillis < 3600) {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(currentTimeMillis / 60);
            stringBuilder2.append("m");
            return stringBuilder2.toString();
        } else if (currentTimeMillis < 86400) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(currentTimeMillis / 3600);
            stringBuilder.append("h");
            return stringBuilder.toString();
        } else {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(currentTimeMillis / 86400);
            stringBuilder2.append("d");
            return stringBuilder2.toString();
        }
    }
}
