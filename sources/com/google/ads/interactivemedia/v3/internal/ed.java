package com.google.ads.interactivemedia.v3.internal;

final class ed {
    private final ck a;

    public ed(ck ckVar) {
        this.a = ckVar;
        ckVar.a(bj.a(null, "application/eia-608", -1, -1, null));
    }

    public void a(long j, fp fpVar) {
        while (fpVar.b() > 1) {
            int f;
            int i;
            int i2 = 0;
            int i3 = 0;
            do {
                f = fpVar.f();
                i3 += f;
            } while (f == 255);
            while (true) {
                f = fpVar.f();
                i = i2 + f;
                if (f != 255) {
                    break;
                }
                i2 = i;
            }
            if (eo.a(i3, i, fpVar)) {
                this.a.a(fpVar, i);
                this.a.a(j, 1, i, 0, null);
            } else {
                fpVar.d(i);
            }
        }
    }
}
