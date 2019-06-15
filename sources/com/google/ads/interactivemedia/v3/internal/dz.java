package com.google.ads.interactivemedia.v3.internal;

final class dz extends du {
    private final fp b = new fp(4);
    private final fm c;
    private int d = 0;
    private int e;
    private boolean f;
    private boolean g;
    private long h;
    private int i;
    private long j;

    public dz(ck ckVar) {
        super(ckVar);
        this.b.a[0] = (byte) -1;
        this.c = new fm();
    }

    public void b() {
    }

    public void a() {
        this.d = 0;
        this.e = 0;
        this.g = false;
    }

    public void a(long j, boolean z) {
        this.j = j;
    }

    public void a(fp fpVar) {
        while (fpVar.b() > 0) {
            switch (this.d) {
                case 0:
                    b(fpVar);
                    break;
                case 1:
                    c(fpVar);
                    break;
                case 2:
                    d(fpVar);
                    break;
                default:
                    break;
            }
        }
    }

    private void b(fp fpVar) {
        byte[] bArr = fpVar.a;
        int c = fpVar.c();
        for (int d = fpVar.d(); d < c; d++) {
            boolean z = (bArr[d] & 255) == 255;
            int i = (this.g && (bArr[d] & 224) == 224) ? 1 : false;
            this.g = z;
            if (i != 0) {
                fpVar.c(d + 1);
                this.g = false;
                this.b.a[1] = bArr[d];
                this.e = 2;
                this.d = 1;
                return;
            }
        }
        fpVar.c(c);
    }

    private void c(fp fpVar) {
        int min = Math.min(fpVar.b(), 4 - this.e);
        fpVar.a(this.b.a, this.e, min);
        this.e += min;
        if (this.e >= 4) {
            this.b.c(0);
            if (fm.a(this.b.m(), this.c)) {
                this.i = this.c.c;
                if (!this.f) {
                    this.h = (1000000 * ((long) this.c.g)) / ((long) this.c.d);
                    this.a.a(bj.a(null, this.c.b, -1, 4096, -1, this.c.e, this.c.d, null, null));
                    this.f = true;
                }
                this.b.c(0);
                this.a.a(this.b, 4);
                this.d = 2;
                return;
            }
            this.e = 0;
            this.d = 1;
        }
    }

    private void d(fp fpVar) {
        int min = Math.min(fpVar.b(), this.i - this.e);
        this.a.a(fpVar, min);
        this.e += min;
        if (this.e >= this.i) {
            this.a.a(this.j, 1, this.i, 0, null);
            this.j += this.h;
            this.e = 0;
            this.d = 0;
        }
    }
}
