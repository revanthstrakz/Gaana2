package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.internal.dp.b;
import com.google.ads.interactivemedia.v3.internal.dp.c;
import com.google.ads.interactivemedia.v3.internal.dp.d;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.IOException;
import java.util.ArrayList;

final class do extends dm implements cj {
    private a e;
    private int g;
    private long h;
    private boolean i;
    private final dk j = new dk();
    private long k = -1;
    private d l;
    private b m;
    private long n;
    private long o;
    private long p;
    private long q;

    static final class a {
        public final d a;
        public final b b;
        public final byte[] c;
        public final c[] d;
        public final int e;

        public a(d dVar, b bVar, byte[] bArr, c[] cVarArr, int i) {
            this.a = dVar;
            this.b = bVar;
            this.c = bArr;
            this.d = cVarArr;
            this.e = i;
        }
    }

    do() {
    }

    static boolean a(fp fpVar) {
        try {
            return dp.a(1, fpVar, true);
        } catch (bl unused) {
            return false;
        }
    }

    public void b() {
        super.b();
        this.g = 0;
        this.h = 0;
        this.i = false;
    }

    public int a(cd cdVar, ch chVar) throws IOException, InterruptedException {
        long j;
        cd cdVar2 = cdVar;
        ch chVar2 = chVar;
        if (this.p == 0) {
            long j2;
            if (this.e == null) {
                this.n = cdVar.d();
                this.e = a(cdVar2, this.a);
                this.o = cdVar.c();
                this.d.a(this);
                if (this.n != -1) {
                    chVar2.a = Math.max(0, cdVar.d() - 8000);
                    return 1;
                }
            }
            if (this.n == -1) {
                j2 = -1;
            } else {
                j2 = this.b.a(cdVar2);
            }
            this.p = j2;
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.e.a.j);
            arrayList.add(this.e.c);
            if (this.n == -1) {
                j = -1;
            } else {
                j = (this.p * 1000000) / this.e.a.c;
            }
            this.q = j;
            this.c.a(bj.a(null, MimeTypes.AUDIO_VORBIS, this.e.a.e, OggPageHeader.MAX_PAGE_PAYLOAD, this.q, this.e.a.b, (int) this.e.a.c, arrayList, null));
            if (this.n != -1) {
                this.j.a(this.n - this.o, this.p);
                chVar2.a = this.o;
                return 1;
            }
        }
        if (!this.i && this.k > -1) {
            dl.a(cdVar);
            long a = this.j.a(this.k, cdVar2);
            if (a != -1) {
                chVar2.a = a;
                return 1;
            }
            this.h = this.b.a(cdVar2, this.k);
            this.g = this.l.g;
            this.i = true;
        }
        if (!this.b.a(cdVar2, this.a)) {
            return -1;
        }
        if ((this.a.a[0] & 1) != 1) {
            int a2 = a(this.a.a[0], this.e);
            j = (long) (this.i ? (this.g + a2) / 4 : 0);
            if (this.h + j >= this.k) {
                a(this.a, j);
                long j3 = (this.h * 1000000) / this.e.a.c;
                this.c.a(this.a, this.a.c());
                this.c.a(j3, 1, this.a.c(), 0, null);
                this.k = -1;
            }
            this.i = true;
            this.h += j;
            this.g = a2;
        }
        this.a.a();
        return 0;
    }

    /* Access modifiers changed, original: 0000 */
    public a a(cd cdVar, fp fpVar) throws IOException, InterruptedException {
        if (this.l == null) {
            this.b.a(cdVar, fpVar);
            this.l = dp.a(fpVar);
            fpVar.a();
        }
        if (this.m == null) {
            this.b.a(cdVar, fpVar);
            this.m = dp.b(fpVar);
            fpVar.a();
        }
        this.b.a(cdVar, fpVar);
        byte[] bArr = new byte[fpVar.c()];
        System.arraycopy(fpVar.a, 0, bArr, 0, fpVar.c());
        c[] a = dp.a(fpVar, this.l.b);
        int a2 = dp.a(a.length - 1);
        fpVar.a();
        return new a(this.l, this.m, bArr, a, a2);
    }

    static void a(fp fpVar, long j) {
        fpVar.b(fpVar.c() + 4);
        fpVar.a[fpVar.c() - 4] = (byte) ((int) (j & 255));
        fpVar.a[fpVar.c() - 3] = (byte) ((int) ((j >>> 8) & 255));
        fpVar.a[fpVar.c() - 2] = (byte) ((int) ((j >>> 16) & 255));
        fpVar.a[fpVar.c() - 1] = (byte) ((int) ((j >>> 24) & 255));
    }

    private static int a(byte b, a aVar) {
        if (aVar.d[dl.a(b, aVar.e, 1)].a) {
            return aVar.a.h;
        }
        return aVar.a.g;
    }

    public boolean a() {
        return (this.e == null || this.n == -1) ? false : true;
    }

    public long b(long j) {
        if (j == 0) {
            this.k = -1;
            return this.o;
        }
        this.k = (this.e.a.c * j) / 1000000;
        return Math.max(this.o, (((this.n - this.o) * j) / this.q) - 4000);
    }
}
