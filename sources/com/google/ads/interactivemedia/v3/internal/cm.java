package com.google.ads.interactivemedia.v3.internal;

import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import java.io.IOException;

public final class cm implements cc, cj {
    private static final int d = ft.c("FLV");
    public int a;
    public int b;
    public long c;
    private final fp e = new fp(4);
    private final fp g = new fp(9);
    private final fp h = new fp(11);
    private final fp i = new fp();
    private ce j;
    private int k = 1;
    private int l;
    private cl m;
    private cp n;
    private cn o;

    public boolean a() {
        return false;
    }

    public long b(long j) {
        return 0;
    }

    public void c() {
    }

    public boolean a(cd cdVar) throws IOException, InterruptedException {
        boolean z = false;
        cdVar.c(this.e.a, 0, 3);
        this.e.c(0);
        if (this.e.j() != d) {
            return false;
        }
        cdVar.c(this.e.a, 0, 2);
        this.e.c(0);
        if ((this.e.g() & Callback.DEFAULT_SWIPE_ANIMATION_DURATION) != 0) {
            return false;
        }
        cdVar.c(this.e.a, 0, 4);
        this.e.c(0);
        int m = this.e.m();
        cdVar.a();
        cdVar.c(m);
        cdVar.c(this.e.a, 0, 4);
        this.e.c(0);
        if (this.e.m() == 0) {
            z = true;
        }
        return z;
    }

    public void a(ce ceVar) {
        this.j = ceVar;
    }

    public void b() {
        this.k = 1;
        this.l = 0;
    }

    public int a(cd cdVar, ch chVar) throws IOException, InterruptedException {
        while (true) {
            switch (this.k) {
                case 1:
                    if (b(cdVar)) {
                        break;
                    }
                    return -1;
                case 2:
                    c(cdVar);
                    break;
                case 3:
                    if (d(cdVar)) {
                        break;
                    }
                    return -1;
                case 4:
                    if (!e(cdVar)) {
                        break;
                    }
                    return 0;
                default:
                    break;
            }
        }
    }

    private boolean b(cd cdVar) throws IOException, InterruptedException {
        int i = 0;
        if (!cdVar.a(this.g.a, 0, 9, true)) {
            return false;
        }
        this.g.c(0);
        this.g.d(4);
        int f = this.g.f();
        boolean z = (f & 4) != 0;
        if ((f & 1) != 0) {
            i = 1;
        }
        if (z && this.m == null) {
            this.m = new cl(this.j.d(8));
        }
        if (i != 0 && this.n == null) {
            this.n = new cp(this.j.d(9));
        }
        if (this.o == null) {
            this.o = new cn(null);
        }
        this.j.f();
        this.j.a((cj) this);
        this.l = (this.g.m() - 9) + 4;
        this.k = 2;
        return true;
    }

    private void c(cd cdVar) throws IOException, InterruptedException {
        cdVar.b(this.l);
        this.l = 0;
        this.k = 3;
    }

    private boolean d(cd cdVar) throws IOException, InterruptedException {
        if (!cdVar.a(this.h.a, 0, 11, true)) {
            return false;
        }
        this.h.c(0);
        this.a = this.h.f();
        this.b = this.h.j();
        this.c = (long) this.h.j();
        this.c = (((long) (this.h.f() << 24)) | this.c) * 1000;
        this.h.d(3);
        this.k = 4;
        return true;
    }

    private boolean e(cd cdVar) throws IOException, InterruptedException {
        boolean z;
        if (this.a == 8 && this.m != null) {
            this.m.b(f(cdVar), this.c);
        } else if (this.a == 9 && this.n != null) {
            this.n.b(f(cdVar), this.c);
        } else if (this.a != 18 || this.o == null) {
            cdVar.b(this.b);
            z = false;
            this.l = 4;
            this.k = 2;
            return z;
        } else {
            this.o.b(f(cdVar), this.c);
            if (this.o.a() != -1) {
                if (this.m != null) {
                    this.m.a(this.o.a());
                }
                if (this.n != null) {
                    this.n.a(this.o.a());
                }
            }
        }
        z = true;
        this.l = 4;
        this.k = 2;
        return z;
    }

    private fp f(cd cdVar) throws IOException, InterruptedException {
        if (this.b > this.i.e()) {
            this.i.a(new byte[Math.max(this.i.e() * 2, this.b)], 0);
        } else {
            this.i.c(0);
        }
        this.i.b(this.b);
        cdVar.b(this.i.a, 0, this.b);
        return this.i;
    }
}
