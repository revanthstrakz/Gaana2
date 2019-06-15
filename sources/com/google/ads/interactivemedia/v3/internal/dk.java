package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.internal.dl.b;
import java.io.IOException;

final class dk {
    private final b a = new b();
    private final fp b = new fp(282);
    private long c = -1;
    private long d;

    dk() {
    }

    public void a(long j, long j2) {
        boolean z = j > 0 && j2 > 0;
        fe.a(z);
        this.c = j;
        this.d = j2;
    }

    public long a(long j, cd cdVar) throws IOException, InterruptedException {
        int i = 1;
        boolean z = (this.c == -1 || this.d == 0) ? false : true;
        fe.b(z);
        dl.a(cdVar, this.a, this.b, false);
        long j2 = j - this.a.c;
        if (j2 <= 0 || j2 > 72000) {
            int i2 = this.a.i + this.a.h;
            if (j2 <= 0) {
                i = 2;
            }
            return (cdVar.c() - ((long) (i2 * i))) + ((j2 * this.c) / this.d);
        }
        cdVar.a();
        return -1;
    }
}
