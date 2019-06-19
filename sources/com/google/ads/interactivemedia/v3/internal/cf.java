package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.util.SparseArray;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class cf implements bn, com.google.ads.interactivemedia.v3.internal.bn.a, ce, com.google.ads.interactivemedia.v3.internal.fa.a {
    private static final List<Class<? extends cc>> a = new ArrayList();
    private long A;
    private long B;
    private fa C;
    private b D;
    private IOException E;
    private int F;
    private long G;
    private boolean H;
    private int I;
    private int J;
    private final c b;
    private final eq c;
    private final int d;
    private final SparseArray<d> e;
    private final int f;
    private final Uri g;
    private final et h;
    private final Handler i;
    private final a j;
    private final int k;
    private volatile boolean l;
    private volatile cj m;
    private volatile bu n;
    private boolean o;
    private int p;
    private bj[] q;
    private long r;
    private boolean[] s;
    private boolean[] t;
    private boolean[] u;
    private int v;
    private long w;
    private long x;
    private long y;
    private boolean z;

    public interface a {
        void a(int i, IOException iOException);
    }

    private static class b implements com.google.ads.interactivemedia.v3.internal.fa.c {
        private final Uri a;
        private final et b;
        private final c c;
        private final eq d;
        private final int e;
        private final ch f = new ch();
        private volatile boolean g;
        private boolean h;

        public b(Uri uri, et etVar, c cVar, eq eqVar, int i, long j) {
            this.a = (Uri) fe.a((Object) uri);
            this.b = (et) fe.a((Object) etVar);
            this.c = (c) fe.a((Object) cVar);
            this.d = (eq) fe.a((Object) eqVar);
            this.e = i;
            this.f.a = j;
            this.h = true;
        }

        public void a() {
            this.g = true;
        }

        public boolean b() {
            return this.g;
        }

        public void c() throws IOException, InterruptedException {
            Throwable th;
            cd cdVar;
            boolean z = false;
            while (!z && !this.g) {
                try {
                    long j = this.f.a;
                    long a = this.b.a(new eu(this.a, j, -1, null));
                    cd bzVar = new bz(this.b, j, a != -1 ? a + j : a);
                    try {
                        cc a2 = this.c.a(bzVar);
                        if (this.h) {
                            a2.b();
                            this.h = false;
                        }
                        while (!z && !this.g) {
                            this.d.b(this.e);
                            z = a2.a(bzVar, this.f);
                        }
                        if (z) {
                            z = false;
                        } else if (bzVar != null) {
                            this.f.a = bzVar.c();
                        }
                        ft.a(this.b);
                    } catch (Throwable th2) {
                        th = th2;
                        if (!(z || cdVar == null)) {
                            this.f.a = cdVar.c();
                        }
                        ft.a(this.b);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    cdVar = null;
                    this.f.a = cdVar.c();
                    ft.a(this.b);
                    throw th;
                }
            }
        }
    }

    private static final class c {
        private final cc[] a;
        private final ce b;
        private cc c;

        public c(cc[] ccVarArr, ce ceVar) {
            this.a = ccVarArr;
            this.b = ceVar;
        }

        public cc a(cd cdVar) throws e, IOException, InterruptedException {
            if (this.c != null) {
                return this.c;
            }
            cc[] ccVarArr = this.a;
            int length = ccVarArr.length;
            int i = 0;
            loop0:
            while (i < length) {
                cc ccVar = ccVarArr[i];
                try {
                    if (ccVar.a(cdVar)) {
                        this.c = ccVar;
                        cdVar.a();
                        break loop0;
                    }
                    i++;
                } catch (EOFException unused) {
                    i++;
                } finally {
                    cdVar.a();
                }
            }
            if (this.c == null) {
                throw new e(this.a);
            }
            this.c.a(this.b);
            return this.c;
        }

        public void a() {
            if (this.c != null) {
                this.c.c();
                this.c = null;
            }
        }
    }

    private class d extends ca {
        public d(eq eqVar) {
            super(eqVar);
        }

        public void a(long j, int i, int i2, int i3, byte[] bArr) {
            super.a(j, i, i2, i3, bArr);
            cf.this.I = cf.this.I + 1;
        }
    }

    public static final class e extends bl {
        public e(cc[] ccVarArr) {
            String a = ft.a((Object[]) ccVarArr);
            StringBuilder stringBuilder = new StringBuilder(58 + String.valueOf(a).length());
            stringBuilder.append("None of the available extractors (");
            stringBuilder.append(a);
            stringBuilder.append(") could read the stream.");
            super(stringBuilder.toString());
        }
    }

    public cf(Uri uri, et etVar, eq eqVar, int i, Handler handler, a aVar, int i2, cc... ccVarArr) {
        this(uri, etVar, eqVar, i, -1, handler, aVar, i2, ccVarArr);
    }

    public cf(Uri uri, et etVar, eq eqVar, int i, int i2, Handler handler, a aVar, int i3, cc... ccVarArr) {
        this.g = uri;
        this.h = etVar;
        this.j = aVar;
        this.i = handler;
        this.k = i3;
        this.c = eqVar;
        this.d = i;
        this.f = i2;
        if (ccVarArr == null || ccVarArr.length == 0) {
            ccVarArr = new cc[a.size()];
            int i4 = 0;
            while (i4 < ccVarArr.length) {
                try {
                    ccVarArr[i4] = (cc) ((Class) a.get(i4)).newInstance();
                    i4++;
                } catch (InstantiationException e) {
                    throw new IllegalStateException("Unexpected error creating default extractor", e);
                } catch (IllegalAccessException e2) {
                    throw new IllegalStateException("Unexpected error creating default extractor", e2);
                }
            }
        }
        this.b = new c(ccVarArr, this);
        this.e = new SparseArray();
        this.y = Long.MIN_VALUE;
    }

    public com.google.ads.interactivemedia.v3.internal.bn.a a() {
        this.v++;
        return this;
    }

    public boolean a(long j) {
        if (this.o) {
            return true;
        }
        if (this.C == null) {
            this.C = new fa("Loader:ExtractorSampleSource");
        }
        g();
        int i = 0;
        if (this.m == null || !this.l || !i()) {
            return false;
        }
        int size = this.e.size();
        this.u = new boolean[size];
        this.t = new boolean[size];
        this.s = new boolean[size];
        this.q = new bj[size];
        this.r = -1;
        while (i < size) {
            bj c = ((d) this.e.valueAt(i)).c();
            this.q[i] = c;
            if (c.e != -1 && c.e > this.r) {
                this.r = c.e;
            }
            i++;
        }
        this.o = true;
        return true;
    }

    public int c() {
        return this.e.size();
    }

    public bj a(int i) {
        fe.b(this.o);
        return this.q[i];
    }

    public void a(int i, long j) {
        fe.b(this.o);
        fe.b(this.u[i] ^ 1);
        this.p++;
        this.u[i] = true;
        this.s[i] = true;
        this.t[i] = false;
        if (this.p == 1) {
            if (!this.m.a()) {
                j = 0;
            }
            this.w = j;
            this.x = j;
            c(j);
        }
    }

    public void c(int i) {
        fe.b(this.o);
        fe.b(this.u[i]);
        this.p--;
        this.u[i] = false;
        if (this.p == 0) {
            this.w = Long.MIN_VALUE;
            if (this.C.a()) {
                this.C.b();
                return;
            }
            j();
            this.c.a(0);
        }
    }

    public boolean b(int i, long j) {
        fe.b(this.o);
        fe.b(this.u[i]);
        this.w = j;
        e(this.w);
        if (this.H) {
            return true;
        }
        g();
        if (k()) {
            return false;
        }
        return ((d) this.e.valueAt(i)).e() ^ 1;
    }

    public long b(int i) {
        if (!this.t[i]) {
            return Long.MIN_VALUE;
        }
        this.t[i] = false;
        return this.x;
    }

    public int a(int i, long j, bk bkVar, bm bmVar) {
        this.w = j;
        if (this.t[i] || k()) {
            return -2;
        }
        d dVar = (d) this.e.valueAt(i);
        if (this.s[i]) {
            bkVar.a = dVar.c();
            bkVar.b = this.n;
            this.s[i] = false;
            return -4;
        } else if (dVar.a(bmVar)) {
            boolean z = bmVar.e < this.x;
            bmVar.d = (z ? 134217728 : 0) | bmVar.d;
            if (this.z) {
                this.B = this.A - bmVar.e;
                this.z = false;
            }
            bmVar.e += this.B;
            return -3;
        } else if (this.H) {
            return -1;
        } else {
            return -2;
        }
    }

    public void b() throws IOException {
        if (this.E != null) {
            if (l()) {
                throw this.E;
            }
            int i = this.f != -1 ? this.f : (this.m == null || this.m.a()) ? 3 : 6;
            if (this.F > i) {
                throw this.E;
            }
        }
    }

    public void b(long j) {
        fe.b(this.o);
        int i = 0;
        fe.b(this.p > 0);
        if (!this.m.a()) {
            j = 0;
        }
        long j2 = k() ? this.y : this.w;
        this.w = j;
        this.x = j;
        if (j2 != j) {
            int k = k() ^ 1;
            int i2 = 0;
            while (k != 0 && i2 < this.e.size()) {
                k &= ((d) this.e.valueAt(i2)).b(j);
                i2++;
            }
            if (k == 0) {
                c(j);
            }
            while (i < this.t.length) {
                this.t[i] = true;
                i++;
            }
        }
    }

    public long d() {
        if (this.H) {
            return -3;
        }
        if (k()) {
            return this.y;
        }
        long j = Long.MIN_VALUE;
        for (int i = 0; i < this.e.size(); i++) {
            j = Math.max(j, ((d) this.e.valueAt(i)).d());
        }
        if (j == Long.MIN_VALUE) {
            j = this.w;
        }
        return j;
    }

    public void e() {
        fe.b(this.v > 0);
        int i = this.v - 1;
        this.v = i;
        if (i == 0 && this.C != null) {
            this.C.a(new Runnable() {
                public void run() {
                    cf.this.b.a();
                }
            });
            this.C = null;
        }
    }

    public void a(com.google.ads.interactivemedia.v3.internal.fa.c cVar) {
        this.H = true;
    }

    public void b(com.google.ads.interactivemedia.v3.internal.fa.c cVar) {
        if (this.p > 0) {
            c(this.y);
            return;
        }
        j();
        this.c.a(0);
    }

    public void a(com.google.ads.interactivemedia.v3.internal.fa.c cVar, IOException iOException) {
        this.E = iOException;
        int i = 1;
        if (this.I <= this.J) {
            i = 1 + this.F;
        }
        this.F = i;
        this.G = SystemClock.elapsedRealtime();
        a(iOException);
        g();
    }

    public ck d(int i) {
        d dVar = (d) this.e.get(i);
        if (dVar != null) {
            return dVar;
        }
        dVar = new d(this.c);
        this.e.put(i, dVar);
        return dVar;
    }

    public void f() {
        this.l = true;
    }

    public void a(cj cjVar) {
        this.m = cjVar;
    }

    public void a(bu buVar) {
        this.n = buVar;
    }

    private void c(long j) {
        this.y = j;
        this.H = false;
        if (this.C.a()) {
            this.C.b();
            return;
        }
        j();
        g();
    }

    private void g() {
        if (!this.H && !this.C.a()) {
            int i = 0;
            if (this.E == null) {
                this.B = 0;
                this.z = false;
                if (this.o) {
                    fe.b(k());
                    if (this.r == -1 || this.y < this.r) {
                        this.D = d(this.y);
                        this.y = Long.MIN_VALUE;
                    } else {
                        this.H = true;
                        this.y = Long.MIN_VALUE;
                        return;
                    }
                }
                this.D = h();
                this.J = this.I;
                this.C.a(this.D, (com.google.ads.interactivemedia.v3.internal.fa.a) this);
            } else if (!l()) {
                fe.b(this.D != null);
                if (SystemClock.elapsedRealtime() - this.G >= f((long) this.F)) {
                    this.E = null;
                    if (!this.o) {
                        while (i < this.e.size()) {
                            ((d) this.e.valueAt(i)).a();
                            i++;
                        }
                        this.D = h();
                    } else if (!this.m.a() && this.r == -1) {
                        while (i < this.e.size()) {
                            ((d) this.e.valueAt(i)).a();
                            i++;
                        }
                        this.D = h();
                        this.A = this.w;
                        this.z = true;
                    }
                    this.J = this.I;
                    this.C.a(this.D, (com.google.ads.interactivemedia.v3.internal.fa.a) this);
                }
            }
        }
    }

    private b h() {
        return new b(this.g, this.h, this.b, this.c, this.d, 0);
    }

    private b d(long j) {
        return new b(this.g, this.h, this.b, this.c, this.d, this.m.b(j));
    }

    private boolean i() {
        for (int i = 0; i < this.e.size(); i++) {
            if (!((d) this.e.valueAt(i)).b()) {
                return false;
            }
        }
        return true;
    }

    private void e(long j) {
        for (int i = 0; i < this.u.length; i++) {
            if (!this.u[i]) {
                ((d) this.e.valueAt(i)).a(j);
            }
        }
    }

    private void j() {
        for (int i = 0; i < this.e.size(); i++) {
            ((d) this.e.valueAt(i)).a();
        }
        this.D = null;
        this.E = null;
        this.F = 0;
    }

    private boolean k() {
        return this.y != Long.MIN_VALUE;
    }

    private boolean l() {
        return this.E instanceof e;
    }

    private long f(long j) {
        return Math.min((j - 1) * 1000, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
    }

    private void a(final IOException iOException) {
        if (this.i != null && this.j != null) {
            this.i.post(new Runnable() {
                public void run() {
                    cf.this.j.a(cf.this.k, iOException);
                }
            });
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0018 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0029 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x003a */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x004b */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x00a0 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x00b1 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x005c */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x006d */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x007e */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x008f */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Can't wrap try/catch for region: R(24:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|24) */
    /* JADX WARNING: Can't wrap try/catch for region: R(22:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|(3:21|22|24)) */
    /* JADX WARNING: Can't wrap try/catch for region: R(22:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|(3:21|22|24)) */
    /* JADX WARNING: Can't wrap try/catch for region: R(22:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|(3:21|22|24)) */
    /* JADX WARNING: Can't wrap try/catch for region: R(22:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|(3:21|22|24)) */
    /* JADX WARNING: Can't wrap try/catch for region: R(22:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|(3:21|22|24)) */
    /* JADX WARNING: Can't wrap try/catch for region: R(22:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|(3:21|22|24)) */
    /* JADX WARNING: Can't wrap try/catch for region: R(22:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|(3:21|22|24)) */
    /* JADX WARNING: Can't wrap try/catch for region: R(22:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|(3:21|22|24)) */
    static {
        /*
        r0 = new java.util.ArrayList;
        r0.<init>();
        a = r0;
        r0 = a;	 Catch:{ ClassNotFoundException -> 0x0018 }
        r1 = "com.google.ads.interactivemedia.v3.internal.en";
        r1 = java.lang.Class.forName(r1);	 Catch:{ ClassNotFoundException -> 0x0018 }
        r2 = com.google.ads.interactivemedia.v3.internal.cc.class;
        r1 = r1.asSubclass(r2);	 Catch:{ ClassNotFoundException -> 0x0018 }
        r0.add(r1);	 Catch:{ ClassNotFoundException -> 0x0018 }
    L_0x0018:
        r0 = a;	 Catch:{ ClassNotFoundException -> 0x0029 }
        r1 = "com.google.ads.interactivemedia.v3.internal.cz";
        r1 = java.lang.Class.forName(r1);	 Catch:{ ClassNotFoundException -> 0x0029 }
        r2 = com.google.ads.interactivemedia.v3.internal.cc.class;
        r1 = r1.asSubclass(r2);	 Catch:{ ClassNotFoundException -> 0x0029 }
        r0.add(r1);	 Catch:{ ClassNotFoundException -> 0x0029 }
    L_0x0029:
        r0 = a;	 Catch:{ ClassNotFoundException -> 0x003a }
        r1 = "com.google.ads.interactivemedia.v3.internal.da";
        r1 = java.lang.Class.forName(r1);	 Catch:{ ClassNotFoundException -> 0x003a }
        r2 = com.google.ads.interactivemedia.v3.internal.cc.class;
        r1 = r1.asSubclass(r2);	 Catch:{ ClassNotFoundException -> 0x003a }
        r0.add(r1);	 Catch:{ ClassNotFoundException -> 0x003a }
    L_0x003a:
        r0 = a;	 Catch:{ ClassNotFoundException -> 0x004b }
        r1 = "com.google.ads.interactivemedia.v3.internal.cs";
        r1 = java.lang.Class.forName(r1);	 Catch:{ ClassNotFoundException -> 0x004b }
        r2 = com.google.ads.interactivemedia.v3.internal.cc.class;
        r1 = r1.asSubclass(r2);	 Catch:{ ClassNotFoundException -> 0x004b }
        r0.add(r1);	 Catch:{ ClassNotFoundException -> 0x004b }
    L_0x004b:
        r0 = a;	 Catch:{ ClassNotFoundException -> 0x005c }
        r1 = "com.google.ads.interactivemedia.v3.internal.dr";
        r1 = java.lang.Class.forName(r1);	 Catch:{ ClassNotFoundException -> 0x005c }
        r2 = com.google.ads.interactivemedia.v3.internal.cc.class;
        r1 = r1.asSubclass(r2);	 Catch:{ ClassNotFoundException -> 0x005c }
        r0.add(r1);	 Catch:{ ClassNotFoundException -> 0x005c }
    L_0x005c:
        r0 = a;	 Catch:{ ClassNotFoundException -> 0x006d }
        r1 = "com.google.ads.interactivemedia.v3.internal.ee";
        r1 = java.lang.Class.forName(r1);	 Catch:{ ClassNotFoundException -> 0x006d }
        r2 = com.google.ads.interactivemedia.v3.internal.cc.class;
        r1 = r1.asSubclass(r2);	 Catch:{ ClassNotFoundException -> 0x006d }
        r0.add(r1);	 Catch:{ ClassNotFoundException -> 0x006d }
    L_0x006d:
        r0 = a;	 Catch:{ ClassNotFoundException -> 0x007e }
        r1 = "com.google.ads.interactivemedia.v3.internal.cm";
        r1 = java.lang.Class.forName(r1);	 Catch:{ ClassNotFoundException -> 0x007e }
        r2 = com.google.ads.interactivemedia.v3.internal.cc.class;
        r1 = r1.asSubclass(r2);	 Catch:{ ClassNotFoundException -> 0x007e }
        r0.add(r1);	 Catch:{ ClassNotFoundException -> 0x007e }
    L_0x007e:
        r0 = a;	 Catch:{ ClassNotFoundException -> 0x008f }
        r1 = "com.google.ads.interactivemedia.v3.internal.di";
        r1 = java.lang.Class.forName(r1);	 Catch:{ ClassNotFoundException -> 0x008f }
        r2 = com.google.ads.interactivemedia.v3.internal.cc.class;
        r1 = r1.asSubclass(r2);	 Catch:{ ClassNotFoundException -> 0x008f }
        r0.add(r1);	 Catch:{ ClassNotFoundException -> 0x008f }
    L_0x008f:
        r0 = a;	 Catch:{ ClassNotFoundException -> 0x00a0 }
        r1 = "com.google.ads.interactivemedia.v3.internal.eb";
        r1 = java.lang.Class.forName(r1);	 Catch:{ ClassNotFoundException -> 0x00a0 }
        r2 = com.google.ads.interactivemedia.v3.internal.cc.class;
        r1 = r1.asSubclass(r2);	 Catch:{ ClassNotFoundException -> 0x00a0 }
        r0.add(r1);	 Catch:{ ClassNotFoundException -> 0x00a0 }
    L_0x00a0:
        r0 = a;	 Catch:{ ClassNotFoundException -> 0x00b1 }
        r1 = "com.google.ads.interactivemedia.v3.internal.ef";
        r1 = java.lang.Class.forName(r1);	 Catch:{ ClassNotFoundException -> 0x00b1 }
        r2 = com.google.ads.interactivemedia.v3.internal.cc.class;
        r1 = r1.asSubclass(r2);	 Catch:{ ClassNotFoundException -> 0x00b1 }
        r0.add(r1);	 Catch:{ ClassNotFoundException -> 0x00b1 }
    L_0x00b1:
        r0 = a;	 Catch:{ ClassNotFoundException -> 0x00c2 }
        r1 = "com.google.ads.interactivemedia.v3.exoplayer.ext.flac.FlacExtractor";
        r1 = java.lang.Class.forName(r1);	 Catch:{ ClassNotFoundException -> 0x00c2 }
        r2 = com.google.ads.interactivemedia.v3.internal.cc.class;
        r1 = r1.asSubclass(r2);	 Catch:{ ClassNotFoundException -> 0x00c2 }
        r0.add(r1);	 Catch:{ ClassNotFoundException -> 0x00c2 }
    L_0x00c2:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.cf.<clinit>():void");
    }
}
