package com.google.ads.interactivemedia.v3.internal;

import java.io.EOFException;
import java.io.IOException;

public final class cs implements cc {
    private static final int a = ft.c("Xing");
    private static final int b = ft.c("Info");
    private static final int c = ft.c("VBRI");
    private final long d;
    private final fp e;
    private final fm f;
    private ce g;
    private ck h;
    private int i;
    private cg j;
    private a k;
    private long l;
    private long m;
    private int n;

    interface a extends cj {
        long a(long j);

        long b();
    }

    public cs() {
        this(-1);
    }

    public void c() {
    }

    public cs(long j) {
        this.d = j;
        this.e = new fp(4);
        this.f = new fm();
        this.l = -1;
    }

    public boolean a(cd cdVar) throws IOException, InterruptedException {
        return a(cdVar, true);
    }

    public void a(ce ceVar) {
        this.g = ceVar;
        this.h = ceVar.d(0);
        ceVar.f();
    }

    public void b() {
        this.i = 0;
        this.m = 0;
        this.l = -1;
        this.n = 0;
    }

    public int a(cd cdVar, ch chVar) throws IOException, InterruptedException {
        if (this.i == 0 && !d(cdVar)) {
            return -1;
        }
        if (this.k == null) {
            e(cdVar);
            this.g.a(this.k);
            bj a = bj.a(null, this.f.b, -1, 4096, this.k.b(), this.f.e, this.f.d, null, null);
            if (this.j != null) {
                a = a.a(this.j.a, this.j.b);
            }
            this.h.a(a);
        }
        return b(cdVar);
    }

    private int b(cd cdVar) throws IOException, InterruptedException {
        if (this.n == 0) {
            if (!c(cdVar)) {
                return -1;
            }
            if (this.l == -1) {
                this.l = this.k.a(cdVar.c());
                if (this.d != -1) {
                    this.l += this.d - this.k.a(0);
                }
            }
            this.n = this.f.c;
        }
        int a = this.h.a(cdVar, this.n, true);
        if (a == -1) {
            return -1;
        }
        this.n -= a;
        if (this.n > 0) {
            return 0;
        }
        this.h.a(this.l + ((this.m * 1000000) / ((long) this.f.d)), 1, this.f.c, 0, null);
        this.m += (long) this.f.g;
        this.n = 0;
        return 0;
    }

    private boolean c(cd cdVar) throws IOException, InterruptedException {
        cdVar.a();
        if (!cdVar.b(this.e.a, 0, 4, true)) {
            return false;
        }
        this.e.c(0);
        int m = this.e.m();
        if ((m & -128000) != (-128000 & this.i) || fm.a(m) == -1) {
            this.i = 0;
            cdVar.b(1);
            return d(cdVar);
        }
        fm.a(m, this.f);
        return true;
    }

    private boolean d(cd cdVar) throws IOException, InterruptedException {
        try {
            return a(cdVar, false);
        } catch (EOFException unused) {
            return false;
        }
    }

    private boolean a(cd cdVar, boolean z) throws IOException, InterruptedException {
        int b;
        int i;
        int i2;
        int i3;
        cdVar.a();
        if (cdVar.c() == 0) {
            this.j = cr.a(cdVar);
            b = (int) cdVar.b();
            if (!z) {
                cdVar.b(b);
            }
            i = 0;
            i2 = i;
            i3 = b;
            b = i2;
        } else {
            b = 0;
            i = b;
            i2 = i;
            i3 = i2;
        }
        while (true) {
            if (z && b == 4096) {
                return false;
            }
            if (!z && b == 131072) {
                throw new bl("Searched too many bytes.");
            } else if (!cdVar.b(this.e.a, 0, 4, true)) {
                return false;
            } else {
                this.e.c(0);
                int m = this.e.m();
                if (i == 0 || (m & -128000) == (-128000 & i)) {
                    int a = fm.a(m);
                    if (a != -1) {
                        i2++;
                        if (i2 == 1) {
                            fm.a(m, this.f);
                            i = m;
                        } else if (i2 == 4) {
                            if (z) {
                                cdVar.b(i3 + b);
                            } else {
                                cdVar.a();
                            }
                            this.i = i;
                            return true;
                        }
                        cdVar.c(a - 4);
                    }
                }
                b++;
                if (z) {
                    cdVar.a();
                    cdVar.c(i3 + b);
                } else {
                    cdVar.b(1);
                }
                i = 0;
                i2 = i;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:32:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00b6  */
    private void e(com.google.ads.interactivemedia.v3.internal.cd r14) throws java.io.IOException, java.lang.InterruptedException {
        /*
        r13 = this;
        r1 = new com.google.ads.interactivemedia.v3.internal.fp;
        r0 = r13.f;
        r0 = r0.c;
        r1.<init>(r0);
        r0 = r1.a;
        r2 = r13.f;
        r2 = r2.c;
        r6 = 0;
        r14.c(r0, r6, r2);
        r2 = r14.c();
        r11 = r14.d();
        r0 = r13.f;
        r0 = r0.a;
        r4 = 1;
        r0 = r0 & r4;
        r5 = 21;
        r7 = 36;
        if (r0 == 0) goto L_0x0031;
    L_0x0027:
        r0 = r13.f;
        r0 = r0.e;
        if (r0 == r4) goto L_0x002f;
    L_0x002d:
        r8 = r7;
        goto L_0x003b;
    L_0x002f:
        r8 = r5;
        goto L_0x003b;
    L_0x0031:
        r0 = r13.f;
        r0 = r0.e;
        if (r0 == r4) goto L_0x0038;
    L_0x0037:
        goto L_0x002f;
    L_0x0038:
        r5 = 13;
        goto L_0x002f;
    L_0x003b:
        r0 = r1.c();
        r4 = r8 + 4;
        if (r0 < r4) goto L_0x004b;
    L_0x0043:
        r1.c(r8);
        r0 = r1.m();
        goto L_0x004c;
    L_0x004b:
        r0 = r6;
    L_0x004c:
        r4 = a;
        if (r0 == r4) goto L_0x0079;
    L_0x0050:
        r4 = b;
        if (r0 != r4) goto L_0x0055;
    L_0x0054:
        goto L_0x0079;
    L_0x0055:
        r0 = r1.c();
        r4 = 40;
        if (r0 < r4) goto L_0x00b2;
    L_0x005d:
        r1.c(r7);
        r0 = r1.m();
        r4 = c;
        if (r0 != r4) goto L_0x00b2;
    L_0x0068:
        r0 = r13.f;
        r4 = r11;
        r0 = com.google.ads.interactivemedia.v3.internal.ct.a(r0, r1, r2, r4);
        r13.k = r0;
        r0 = r13.f;
        r0 = r0.c;
        r14.b(r0);
        goto L_0x00b2;
    L_0x0079:
        r0 = r13.f;
        r4 = r11;
        r0 = com.google.ads.interactivemedia.v3.internal.cu.a(r0, r1, r2, r4);
        r13.k = r0;
        r0 = r13.k;
        if (r0 == 0) goto L_0x00ab;
    L_0x0086:
        r0 = r13.j;
        if (r0 != 0) goto L_0x00ab;
    L_0x008a:
        r14.a();
        r8 = r8 + 141;
        r14.c(r8);
        r0 = r13.e;
        r0 = r0.a;
        r1 = 3;
        r14.c(r0, r6, r1);
        r0 = r13.e;
        r0.c(r6);
        r0 = r13.e;
        r0 = r0.j();
        r0 = com.google.ads.interactivemedia.v3.internal.cg.a(r0);
        r13.j = r0;
    L_0x00ab:
        r0 = r13.f;
        r0 = r0.c;
        r14.b(r0);
    L_0x00b2:
        r0 = r13.k;
        if (r0 != 0) goto L_0x00e1;
    L_0x00b6:
        r14.a();
        r0 = r13.e;
        r0 = r0.a;
        r1 = 4;
        r14.c(r0, r6, r1);
        r0 = r13.e;
        r0.c(r6);
        r0 = r13.e;
        r0 = r0.m();
        r1 = r13.f;
        com.google.ads.interactivemedia.v3.internal.fm.a(r0, r1);
        r0 = new com.google.ads.interactivemedia.v3.internal.cq;
        r8 = r14.c();
        r14 = r13.f;
        r10 = r14.f;
        r7 = r0;
        r7.<init>(r8, r10, r11);
        r13.k = r0;
    L_0x00e1:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.cs.e(com.google.ads.interactivemedia.v3.internal.cd):void");
    }
}
