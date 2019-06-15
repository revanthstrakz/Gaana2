package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.internal.dl.b;
import java.io.IOException;

public class di implements cc {
    private dm a;

    public void c() {
    }

    public boolean a(cd cdVar) throws IOException, InterruptedException {
        try {
            fp fpVar = new fp(new byte[27], 0);
            b bVar = new b();
            if (dl.a(cdVar, bVar, fpVar, true) && (bVar.b & 2) == 2) {
                if (bVar.i >= 7) {
                    fpVar.a();
                    cdVar.c(fpVar.a, 0, 7);
                    if (dh.a(fpVar)) {
                        this.a = new dh();
                    } else {
                        fpVar.a();
                        if (!do.a(fpVar)) {
                            return false;
                        }
                        this.a = new do();
                    }
                    return true;
                }
            }
            return false;
        } catch (bl unused) {
            return false;
        }
    }

    public void a(ce ceVar) {
        ck d = ceVar.d(0);
        ceVar.f();
        this.a.a(ceVar, d);
    }

    public void b() {
        this.a.b();
    }

    public int a(cd cdVar, ch chVar) throws IOException, InterruptedException {
        return this.a.a(cdVar, chVar);
    }
}
