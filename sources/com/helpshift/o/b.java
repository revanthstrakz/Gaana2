package com.helpshift.o;

import com.comscore.measurement.MeasurementDispatcher;
import java.util.concurrent.TimeUnit;

public class b implements d {
    private final String a;
    private long b;
    private long c = MeasurementDispatcher.MILLIS_PER_DAY;

    public b(int i, TimeUnit timeUnit, String str) {
        this.b = TimeUnit.MILLISECONDS.convert((long) i, timeUnit);
        this.a = str;
    }

    public String a() {
        return this.a;
    }

    public boolean a(int i, long j) {
        return i > 0 && Math.abs(j) > this.b;
    }

    public void b() {
        this.b = (long) (1.618d * ((double) this.b));
        if (this.b > this.c) {
            this.b = this.c;
        }
    }
}
