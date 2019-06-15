package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.internal.dl.a;
import com.google.ads.interactivemedia.v3.internal.dl.b;
import java.io.IOException;

final class dj {
    private final b a = new b();
    private final fp b = new fp(282);
    private final a c = new a();
    private int d = -1;
    private long e;

    dj() {
    }

    public void a() {
        this.a.a();
        this.b.a();
        this.d = -1;
    }

    public boolean a(cd cdVar, fp fpVar) throws IOException, InterruptedException {
        boolean z = (cdVar == null || fpVar == null) ? false : true;
        fe.b(z);
        int i = 0;
        while (i == 0) {
            int i2;
            if (this.d < 0) {
                if (!dl.a(cdVar, this.a, this.b, true)) {
                    return false;
                }
                int i3;
                i2 = this.a.h;
                if ((this.a.b & 1) == 1 && fpVar.c() == 0) {
                    dl.a(this.a, 0, this.c);
                    i3 = this.c.b + 0;
                    i2 += this.c.a;
                } else {
                    i3 = 0;
                }
                cdVar.b(i2);
                this.d = i3;
            }
            dl.a(this.a, this.d, this.c);
            i2 = this.d + this.c.b;
            if (this.c.a > 0) {
                cdVar.b(fpVar.a, fpVar.c(), this.c.a);
                fpVar.b(fpVar.c() + this.c.a);
                i = this.a.j[i2 + -1] != 255 ? true : 0;
            }
            if (i2 == this.a.g) {
                i2 = -1;
            }
            this.d = i2;
        }
        return true;
    }

    public long a(cd cdVar) throws IOException, InterruptedException {
        fe.a(cdVar.d() != -1);
        dl.a(cdVar);
        this.a.a();
        while ((this.a.b & 4) != 4 && cdVar.c() < cdVar.d()) {
            dl.a(cdVar, this.a, this.b, false);
            cdVar.b(this.a.h + this.a.i);
        }
        return this.a.c;
    }

    public long a(cd cdVar, long j) throws IOException, InterruptedException {
        dl.a(cdVar);
        dl.a(cdVar, this.a, this.b, false);
        while (this.a.c < j) {
            cdVar.b(this.a.h + this.a.i);
            this.e = this.a.c;
            dl.a(cdVar, this.a, this.b, false);
        }
        if (this.e == 0) {
            throw new bl();
        }
        cdVar.a();
        long j2 = this.e;
        this.e = 0;
        this.d = -1;
        return j2;
    }
}
