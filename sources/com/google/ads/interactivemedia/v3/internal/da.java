package com.google.ads.interactivemedia.v3.internal;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public final class da implements cc, cj {
    private static final int a = ft.c("qt  ");
    private final fp b = new fp(fn.a);
    private final fp c = new fp(4);
    private final fp d = new fp(16);
    private final Stack<a> e = new Stack();
    private int g;
    private int h;
    private long i;
    private int j;
    private fp k;
    private int l;
    private int m;
    private int n;
    private ce o;
    private a[] p;
    private boolean q;

    private static final class a {
        public final dd a;
        public final dg b;
        public final ck c;
        public int d;

        public a(dd ddVar, dg dgVar, ck ckVar) {
            this.a = ddVar;
            this.b = dgVar;
            this.c = ckVar;
        }
    }

    public da() {
        d();
    }

    public boolean a() {
        return true;
    }

    public void c() {
    }

    public boolean a(cd cdVar) throws IOException, InterruptedException {
        return dc.b(cdVar);
    }

    public void a(ce ceVar) {
        this.o = ceVar;
    }

    public void b() {
        this.e.clear();
        this.j = 0;
        this.m = 0;
        this.n = 0;
        this.g = 0;
    }

    public int a(cd cdVar, ch chVar) throws IOException, InterruptedException {
        while (true) {
            switch (this.g) {
                case 0:
                    if (cdVar.c() != 0) {
                        this.g = 3;
                        break;
                    }
                    d();
                    break;
                case 1:
                    if (b(cdVar)) {
                        break;
                    }
                    return -1;
                case 2:
                    if (!b(cdVar, chVar)) {
                        break;
                    }
                    return 1;
                default:
                    return c(cdVar, chVar);
            }
        }
    }

    public long b(long j) {
        long j2 = Long.MAX_VALUE;
        for (int i = 0; i < this.p.length; i++) {
            dg dgVar = this.p[i].b;
            int a = dgVar.a(j);
            if (a == -1) {
                a = dgVar.b(j);
            }
            this.p[i].d = a;
            long j3 = dgVar.b[a];
            if (j3 < j2) {
                j2 = j3;
            }
        }
        return j2;
    }

    private void d() {
        this.g = 1;
        this.j = 0;
    }

    private boolean b(cd cdVar) throws IOException, InterruptedException {
        if (this.j == 0) {
            if (!cdVar.a(this.d.a, 0, 8, true)) {
                return false;
            }
            this.j = 8;
            this.d.c(0);
            this.i = this.d.k();
            this.h = this.d.m();
        }
        if (this.i == 1) {
            cdVar.b(this.d.a, 8, 8);
            this.j += 8;
            this.i = this.d.u();
        }
        if (b(this.h)) {
            long c = (cdVar.c() + this.i) - ((long) this.j);
            this.e.add(new a(this.h, c));
            if (this.i == ((long) this.j)) {
                a(c);
            } else {
                d();
            }
        } else if (a(this.h)) {
            fe.b(this.j == 8);
            fe.b(this.i <= 2147483647L);
            this.k = new fp((int) this.i);
            System.arraycopy(this.d.a, 0, this.k.a, 0, 8);
            this.g = 2;
        } else {
            this.k = null;
            this.g = 2;
        }
        return true;
    }

    private boolean b(cd cdVar, ch chVar) throws IOException, InterruptedException {
        boolean z;
        long j = this.i - ((long) this.j);
        long c = cdVar.c() + j;
        if (this.k != null) {
            cdVar.b(this.k.a, this.j, (int) j);
            if (this.h == cv.a) {
                this.q = a(this.k);
            } else if (!this.e.isEmpty()) {
                ((a) this.e.peek()).a(new b(this.h, this.k));
            }
        } else if (j < PlaybackStateCompat.ACTION_SET_REPEAT_MODE) {
            cdVar.b((int) j);
        } else {
            chVar.a = cdVar.c() + j;
            z = true;
            a(c);
            if (z || this.g == 3) {
                return false;
            }
            return true;
        }
        z = false;
        a(c);
        if (z) {
        }
        return false;
    }

    private void a(long j) throws bl {
        while (!this.e.isEmpty() && ((a) this.e.peek()).aP == j) {
            a aVar = (a) this.e.pop();
            if (aVar.aO == cv.B) {
                a(aVar);
                this.e.clear();
                this.g = 3;
            } else if (!this.e.isEmpty()) {
                ((a) this.e.peek()).a(aVar);
            }
        }
        if (this.g != 3) {
            d();
        }
    }

    private static boolean a(fp fpVar) {
        fpVar.c(8);
        if (fpVar.m() == a) {
            return true;
        }
        fpVar.d(4);
        while (fpVar.b() > 0) {
            if (fpVar.m() == a) {
                return true;
            }
        }
        return false;
    }

    private void a(a aVar) throws bl {
        ArrayList arrayList = new ArrayList();
        b d = aVar.d(cv.az);
        cg a = d != null ? cw.a(d, this.q) : null;
        long j = Long.MAX_VALUE;
        for (int i = 0; i < aVar.aR.size(); i++) {
            a aVar2 = (a) aVar.aR.get(i);
            if (aVar2.aO == cv.D) {
                dd a2 = cw.a(aVar2, aVar.d(cv.C), -1, this.q);
                if (a2 != null) {
                    dg a3 = cw.a(a2, aVar2.e(cv.E).e(cv.F).e(cv.G));
                    if (a3.a != 0) {
                        a aVar3 = new a(a2, a3, this.o.d(i));
                        bj a4 = a2.l.a(a3.d + 30);
                        if (a != null) {
                            a4 = a4.a(a.a, a.b);
                        }
                        aVar3.c.a(a4);
                        arrayList.add(aVar3);
                        long j2 = a3.b[0];
                        if (j2 < j) {
                            j = j2;
                        }
                    }
                }
            }
        }
        this.p = (a[]) arrayList.toArray(new a[0]);
        this.o.f();
        this.o.a((cj) this);
    }

    private int c(cd cdVar, ch chVar) throws IOException, InterruptedException {
        int e = e();
        if (e == -1) {
            return -1;
        }
        a aVar = this.p[e];
        ck ckVar = aVar.c;
        int i = aVar.d;
        long j = aVar.b.b[i];
        long c = (j - cdVar.c()) + ((long) this.m);
        if (c < 0 || c >= PlaybackStateCompat.ACTION_SET_REPEAT_MODE) {
            chVar.a = j;
            return 1;
        }
        cdVar.b((int) c);
        this.l = aVar.b.c[i];
        int i2;
        if (aVar.a.p != -1) {
            byte[] bArr = this.c.a;
            bArr[0] = (byte) 0;
            bArr[1] = (byte) 0;
            bArr[2] = (byte) 0;
            i2 = aVar.a.p;
            int i3 = 4 - aVar.a.p;
            while (this.m < this.l) {
                if (this.n == 0) {
                    cdVar.b(this.c.a, i3, i2);
                    this.c.c(0);
                    this.n = this.c.s();
                    this.b.c(0);
                    ckVar.a(this.b, 4);
                    this.m += 4;
                    this.l += i3;
                } else {
                    int a = ckVar.a(cdVar, this.n, false);
                    this.m += a;
                    this.n -= a;
                }
            }
        } else {
            while (this.m < this.l) {
                i2 = ckVar.a(cdVar, this.l - this.m, false);
                this.m += i2;
                this.n -= i2;
            }
        }
        long j2 = aVar.b.e[i];
        int i4 = aVar.b.f[i];
        ckVar.a(j2, i4, this.l, 0, null);
        aVar.d++;
        this.m = 0;
        this.n = 0;
        return 0;
    }

    private int e() {
        int i = -1;
        long j = Long.MAX_VALUE;
        for (int i2 = 0; i2 < this.p.length; i2++) {
            a aVar = this.p[i2];
            int i3 = aVar.d;
            if (i3 != aVar.b.a) {
                long j2 = aVar.b.b[i3];
                if (j2 < j) {
                    i = i2;
                    j = j2;
                }
            }
        }
        return i;
    }

    private static boolean a(int i) {
        return i == cv.R || i == cv.C || i == cv.S || i == cv.T || i == cv.am || i == cv.an || i == cv.ao || i == cv.Q || i == cv.ap || i == cv.aq || i == cv.ar || i == cv.as || i == cv.at || i == cv.O || i == cv.a || i == cv.az;
    }

    private static boolean b(int i) {
        return i == cv.B || i == cv.D || i == cv.E || i == cv.F || i == cv.G || i == cv.P;
    }
}
