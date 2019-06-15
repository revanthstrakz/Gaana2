package com.google.ads.interactivemedia.v3.internal;

import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import java.io.IOException;

public final class dr implements cc {
    private static final int a = ft.c("ID3");
    private final long b;
    private final fp c;
    private ds d;
    private boolean e;

    public dr() {
        this(0);
    }

    public void c() {
    }

    public dr(long j) {
        this.b = j;
        this.c = new fp(200);
    }

    public boolean a(cd cdVar) throws IOException, InterruptedException {
        int i;
        fp fpVar = new fp(10);
        fo foVar = new fo(fpVar.a);
        int i2 = 0;
        while (true) {
            cdVar.c(fpVar.a, 0, 10);
            fpVar.c(0);
            if (fpVar.j() != a) {
                break;
            }
            i = ((((fpVar.a[6] & 127) << 21) | ((fpVar.a[7] & 127) << 14)) | ((fpVar.a[8] & 127) << 7)) | (fpVar.a[9] & 127);
            i2 += 10 + i;
            cdVar.c(i);
        }
        cdVar.a();
        cdVar.c(i2);
        i = 0;
        int i3 = i;
        int i4 = i2;
        while (true) {
            cdVar.c(fpVar.a, 0, 2);
            fpVar.c(0);
            if ((fpVar.g() & 65526) != 65520) {
                cdVar.a();
                i4++;
                if (i4 - i2 >= 8192) {
                    return false;
                }
                cdVar.c(i4);
                i = 0;
                i3 = i;
            } else {
                i++;
                if (i >= 4 && i3 > TsExtractor.TS_PACKET_SIZE) {
                    return true;
                }
                cdVar.c(fpVar.a, 0, 4);
                foVar.a(14);
                int c = foVar.c(13);
                if (c <= 6) {
                    return false;
                }
                cdVar.c(c - 6);
                i3 += c;
            }
        }
    }

    public void a(ce ceVar) {
        this.d = new ds(ceVar.d(0), ceVar.d(1));
        ceVar.f();
        ceVar.a(cj.f);
    }

    public void b() {
        this.e = false;
        this.d.a();
    }

    public int a(cd cdVar, ch chVar) throws IOException, InterruptedException {
        int a = cdVar.a(this.c.a, 0, 200);
        if (a == -1) {
            return -1;
        }
        this.c.c(0);
        this.c.b(a);
        if (!this.e) {
            this.d.a(this.b, true);
            this.e = true;
        }
        this.d.a(this.c);
        return 0;
    }
}
