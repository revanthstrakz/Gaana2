package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

final class dh extends dm {
    private fi e;
    private fh f;
    private boolean g;

    dh() {
    }

    static boolean a(fp fpVar) {
        return fpVar.f() == 127 && fpVar.k() == 1179402563;
    }

    public int a(cd cdVar, ch chVar) throws IOException, InterruptedException {
        long c = cdVar.c();
        if (!this.b.a(cdVar, this.a)) {
            return -1;
        }
        byte[] bArr = this.a.a;
        if (this.e == null) {
            this.e = new fi(bArr, 17);
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 9, this.a.c());
            copyOfRange[4] = Byte.MIN_VALUE;
            this.c.a(bj.a(null, "audio/x-flac", this.e.a(), -1, this.e.b(), this.e.f, this.e.e, Collections.singletonList(copyOfRange), null));
        } else if (bArr[0] == (byte) -1) {
            if (!this.g) {
                if (this.f != null) {
                    this.d.a(this.f.a(c, (long) this.e.e));
                    this.f = null;
                } else {
                    this.d.a(cj.f);
                }
                this.g = true;
            }
            this.c.a(this.a, this.a.c());
            this.a.c(0);
            this.c.a(fj.a(this.e, this.a), 1, this.a.c(), 0, null);
        } else if ((bArr[0] & 127) == 3 && this.f == null) {
            this.f = fh.a(this.a);
        }
        this.a.a();
        return 0;
    }
}
