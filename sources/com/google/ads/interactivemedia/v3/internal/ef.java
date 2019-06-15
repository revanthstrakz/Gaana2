package com.google.ads.interactivemedia.v3.internal;

import com.google.android.exoplayer2.util.MimeTypes;
import java.io.IOException;

public final class ef implements cc, cj {
    private ce a;
    private ck b;
    private eg c;
    private int d;
    private int e;

    public boolean a() {
        return true;
    }

    public void c() {
    }

    public boolean a(cd cdVar) throws IOException, InterruptedException {
        return eh.a(cdVar) != null;
    }

    public void a(ce ceVar) {
        this.a = ceVar;
        this.b = ceVar.d(0);
        this.c = null;
        ceVar.f();
    }

    public void b() {
        this.e = 0;
    }

    public int a(cd cdVar, ch chVar) throws IOException, InterruptedException {
        if (this.c == null) {
            this.c = eh.a(cdVar);
            if (this.c == null) {
                throw new bl("Error initializing WavHeader. Did you sniff first?");
            }
            this.d = this.c.b();
        }
        if (!this.c.f()) {
            eh.a(cdVar, this.c);
            this.b.a(bj.a(null, MimeTypes.AUDIO_RAW, this.c.c(), 32768, this.c.a(), this.c.e(), this.c.d(), null, null, this.c.g()));
            this.a.a((cj) this);
        }
        int a = this.b.a(cdVar, 32768 - this.e, true);
        if (a != -1) {
            this.e += a;
        }
        int i = (this.e / this.d) * this.d;
        if (i > 0) {
            long c = cdVar.c() - ((long) this.e);
            this.e -= i;
            this.b.a(this.c.b(c), 1, i, this.e, null);
        }
        if (a == -1) {
            return -1;
        }
        return 0;
    }

    public long b(long j) {
        return this.c.a(j);
    }
}
