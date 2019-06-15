package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.internal.ba.a;

public abstract class bq implements a {
    private int a;

    /* Access modifiers changed, original: protected */
    public void a(int i, long j, boolean z) throws az {
    }

    public void a(int i, Object obj) throws az {
    }

    /* Access modifiers changed, original: protected */
    public bd b() {
        return null;
    }

    public abstract bj b(int i);

    public abstract void b(long j, long j2) throws az;

    /* Access modifiers changed, original: protected */
    public void c() throws az {
    }

    public abstract boolean c(long j) throws az;

    /* Access modifiers changed, original: protected */
    public void d() throws az {
    }

    public abstract void d(long j) throws az;

    public abstract boolean e();

    public abstract boolean f();

    /* Access modifiers changed, original: protected */
    public void g() throws az {
    }

    public abstract long q();

    public abstract long r();

    public abstract void s() throws az;

    /* Access modifiers changed, original: protected */
    public void t() throws az {
    }

    public abstract int u();

    /* Access modifiers changed, original: protected|final */
    public final int v() {
        return this.a;
    }

    /* Access modifiers changed, original: final */
    public final int f(long j) throws az {
        fe.b(this.a == 0);
        this.a = c(j);
        return this.a;
    }

    /* Access modifiers changed, original: final */
    public final void b(int i, long j, boolean z) throws az {
        boolean z2 = true;
        if (this.a != 1) {
            z2 = false;
        }
        fe.b(z2);
        this.a = 2;
        a(i, j, z);
    }

    /* Access modifiers changed, original: final */
    public final void w() throws az {
        fe.b(this.a == 2);
        this.a = 3;
        c();
    }

    /* Access modifiers changed, original: final */
    public final void x() throws az {
        fe.b(this.a == 3);
        this.a = 2;
        d();
    }

    /* Access modifiers changed, original: final */
    public final void y() throws az {
        fe.b(this.a == 2);
        this.a = 1;
        g();
    }

    /* Access modifiers changed, original: final */
    public final void z() throws az {
        boolean z = (this.a == 2 || this.a == 3 || this.a == -1) ? false : true;
        fe.b(z);
        this.a = -1;
        t();
    }
}
