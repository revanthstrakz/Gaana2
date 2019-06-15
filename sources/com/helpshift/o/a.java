package com.helpshift.o;

import android.annotation.TargetApi;
import com.helpshift.k.b;
import java.util.concurrent.TimeUnit;

public class a implements d {
    private final long a;
    private final String b;

    @TargetApi(9)
    public a(int i, String str) {
        this.a = TimeUnit.MILLISECONDS.convert((long) (24 / i), TimeUnit.HOURS);
        this.b = str;
    }

    public String a() {
        return this.b;
    }

    public boolean a(int i, long j) {
        return b.a().b.f().booleanValue() || (i > 0 && Math.abs(j) > this.a);
    }
}
