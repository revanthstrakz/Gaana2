package com.google.ads.interactivemedia.v3.internal;

import android.util.SparseArray;
import com.google.ads.interactivemedia.v3.internal.fn.b;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final class dw extends du {
    private boolean b;
    private final ed c;
    private final boolean[] d = new boolean[3];
    private final a e;
    private final ea f;
    private final ea g;
    private final ea h;
    private long i;
    private long j;
    private final fp k;

    private static final class a {
        private final ck a;
        private final boolean b;
        private final boolean c;
        private final fo d = new fo();
        private final SparseArray<b> e = new SparseArray();
        private final SparseArray<com.google.ads.interactivemedia.v3.internal.fn.a> f = new SparseArray();
        private byte[] g = new byte[128];
        private int h;
        private int i;
        private long j;
        private boolean k;
        private long l;
        private a m = new a();
        private a n = new a();
        private boolean o;
        private long p;
        private long q;
        private boolean r;

        private static final class a {
            private boolean a;
            private boolean b;
            private b c;
            private int d;
            private int e;
            private int f;
            private int g;
            private boolean h;
            private boolean i;
            private boolean j;
            private boolean k;
            private int l;
            private int m;
            private int n;
            private int o;
            private int p;

            private a() {
            }

            public void a() {
                this.b = false;
                this.a = false;
            }

            public void a(int i) {
                this.e = i;
                this.b = true;
            }

            public void a(b bVar, int i, int i2, int i3, int i4, boolean z, boolean z2, boolean z3, boolean z4, int i5, int i6, int i7, int i8, int i9) {
                this.c = bVar;
                this.d = i;
                this.e = i2;
                this.f = i3;
                this.g = i4;
                this.h = z;
                this.i = z2;
                this.j = z3;
                this.k = z4;
                this.l = i5;
                this.m = i6;
                this.n = i7;
                this.o = i8;
                this.p = i9;
                this.a = true;
                this.b = true;
            }

            public boolean b() {
                return this.b && (this.e == 7 || this.e == 2);
            }

            private boolean a(a aVar) {
                if (this.a) {
                    if (!aVar.a || this.f != aVar.f || this.g != aVar.g || this.h != aVar.h) {
                        return true;
                    }
                    if (this.i && aVar.i && this.j != aVar.j) {
                        return true;
                    }
                    if (this.d != aVar.d && (this.d == 0 || aVar.d == 0)) {
                        return true;
                    }
                    if (this.c.h == 0 && aVar.c.h == 0 && (this.m != aVar.m || this.n != aVar.n)) {
                        return true;
                    }
                    if ((this.c.h == 1 && aVar.c.h == 1 && (this.o != aVar.o || this.p != aVar.p)) || this.k != aVar.k) {
                        return true;
                    }
                    if (this.k && aVar.k && this.l != aVar.l) {
                        return true;
                    }
                }
                return false;
            }
        }

        public a(ck ckVar, boolean z, boolean z2) {
            this.a = ckVar;
            this.b = z;
            this.c = z2;
            b();
        }

        public boolean a() {
            return this.c;
        }

        public void a(b bVar) {
            this.e.append(bVar.a, bVar);
        }

        public void a(com.google.ads.interactivemedia.v3.internal.fn.a aVar) {
            this.f.append(aVar.a, aVar);
        }

        public void b() {
            this.k = false;
            this.o = false;
            this.n.a();
        }

        public void a(long j, int i, long j2) {
            this.i = i;
            this.l = j2;
            this.j = j;
            if (!(this.b && this.i == 1)) {
                if (!this.c) {
                    return;
                }
                if (!(this.i == 5 || this.i == 1 || this.i == 2)) {
                    return;
                }
            }
            a aVar = this.m;
            this.m = this.n;
            this.n = aVar;
            this.n.a();
            this.h = 0;
            this.k = true;
        }

        /* JADX WARNING: Removed duplicated region for block: B:52:0x0104  */
        /* JADX WARNING: Removed duplicated region for block: B:51:0x0102  */
        /* JADX WARNING: Removed duplicated region for block: B:58:0x0117  */
        /* JADX WARNING: Removed duplicated region for block: B:54:0x0107  */
        /* JADX WARNING: Removed duplicated region for block: B:72:0x0150  */
        /* JADX WARNING: Removed duplicated region for block: B:61:0x011c  */
        public void a(byte[] r21, int r22, int r23) {
            /*
            r20 = this;
            r0 = r20;
            r1 = r22;
            r2 = r0.k;
            if (r2 != 0) goto L_0x0009;
        L_0x0008:
            return;
        L_0x0009:
            r2 = r23 - r1;
            r3 = r0.g;
            r4 = 2;
            r3 = r3.length;
            r5 = r0.h;
            r5 = r5 + r2;
            if (r3 >= r5) goto L_0x0020;
        L_0x0014:
            r3 = r0.g;
            r5 = r0.h;
            r5 = r5 + r2;
            r5 = r5 * r4;
            r3 = java.util.Arrays.copyOf(r3, r5);
            r0.g = r3;
        L_0x0020:
            r3 = r0.g;
            r5 = r0.h;
            r6 = r21;
            java.lang.System.arraycopy(r6, r1, r3, r5, r2);
            r1 = r0.h;
            r1 = r1 + r2;
            r0.h = r1;
            r1 = r0.d;
            r2 = r0.g;
            r3 = r0.h;
            r1.a(r2, r3);
            r1 = r0.d;
            r1 = r1.a();
            r2 = 8;
            if (r1 >= r2) goto L_0x0042;
        L_0x0041:
            return;
        L_0x0042:
            r1 = r0.d;
            r2 = 1;
            r1.b(r2);
            r1 = r0.d;
            r7 = r1.c(r4);
            r1 = r0.d;
            r3 = 5;
            r1.b(r3);
            r1 = r0.d;
            r1 = r1.c();
            if (r1 != 0) goto L_0x005d;
        L_0x005c:
            return;
        L_0x005d:
            r1 = r0.d;
            r1.d();
            r1 = r0.d;
            r1 = r1.c();
            if (r1 != 0) goto L_0x006b;
        L_0x006a:
            return;
        L_0x006b:
            r1 = r0.d;
            r8 = r1.d();
            r1 = r0.c;
            r15 = 0;
            if (r1 != 0) goto L_0x007e;
        L_0x0076:
            r0.k = r15;
            r1 = r0.n;
            r1.a(r8);
            return;
        L_0x007e:
            r1 = r0.d;
            r1 = r1.c();
            if (r1 != 0) goto L_0x0087;
        L_0x0086:
            return;
        L_0x0087:
            r1 = r0.d;
            r10 = r1.d();
            r1 = r0.f;
            r1 = r1.indexOfKey(r10);
            if (r1 >= 0) goto L_0x0098;
        L_0x0095:
            r0.k = r15;
            return;
        L_0x0098:
            r1 = r0.f;
            r1 = r1.get(r10);
            r1 = (com.google.ads.interactivemedia.v3.internal.fn.a) r1;
            r5 = r0.e;
            r6 = r1.b;
            r5 = r5.get(r6);
            r6 = r5;
            r6 = (com.google.ads.interactivemedia.v3.internal.fn.b) r6;
            r5 = r6.e;
            if (r5 == 0) goto L_0x00bd;
        L_0x00af:
            r5 = r0.d;
            r5 = r5.a();
            if (r5 >= r4) goto L_0x00b8;
        L_0x00b7:
            return;
        L_0x00b8:
            r5 = r0.d;
            r5.b(r4);
        L_0x00bd:
            r4 = r0.d;
            r4 = r4.a();
            r5 = r6.g;
            if (r4 >= r5) goto L_0x00c8;
        L_0x00c7:
            return;
        L_0x00c8:
            r4 = r0.d;
            r5 = r6.g;
            r9 = r4.c(r5);
            r4 = r6.f;
            if (r4 != 0) goto L_0x00fb;
        L_0x00d4:
            r4 = r0.d;
            r4 = r4.a();
            if (r4 >= r2) goto L_0x00dd;
        L_0x00dc:
            return;
        L_0x00dd:
            r4 = r0.d;
            r4 = r4.b();
            if (r4 == 0) goto L_0x00f8;
        L_0x00e5:
            r5 = r0.d;
            r5 = r5.a();
            if (r5 >= r2) goto L_0x00ee;
        L_0x00ed:
            return;
        L_0x00ee:
            r5 = r0.d;
            r5 = r5.b();
            r12 = r2;
            r11 = r4;
            r13 = r5;
            goto L_0x00fe;
        L_0x00f8:
            r11 = r4;
            r12 = r15;
            goto L_0x00fd;
        L_0x00fb:
            r11 = r15;
            r12 = r11;
        L_0x00fd:
            r13 = r12;
        L_0x00fe:
            r4 = r0.i;
            if (r4 != r3) goto L_0x0104;
        L_0x0102:
            r14 = r2;
            goto L_0x0105;
        L_0x0104:
            r14 = r15;
        L_0x0105:
            if (r14 == 0) goto L_0x0117;
        L_0x0107:
            r3 = r0.d;
            r3 = r3.c();
            if (r3 != 0) goto L_0x0110;
        L_0x010f:
            return;
        L_0x0110:
            r3 = r0.d;
            r3 = r3.d();
            goto L_0x0118;
        L_0x0117:
            r3 = r15;
        L_0x0118:
            r4 = r6.h;
            if (r4 != 0) goto L_0x0150;
        L_0x011c:
            r2 = r0.d;
            r2 = r2.a();
            r4 = r6.i;
            if (r2 >= r4) goto L_0x0127;
        L_0x0126:
            return;
        L_0x0127:
            r2 = r0.d;
            r4 = r6.i;
            r2 = r2.c(r4);
            r1 = r1.c;
            if (r1 == 0) goto L_0x014b;
        L_0x0133:
            if (r11 != 0) goto L_0x014b;
        L_0x0135:
            r1 = r0.d;
            r1 = r1.c();
            if (r1 != 0) goto L_0x013e;
        L_0x013d:
            return;
        L_0x013e:
            r1 = r0.d;
            r1 = r1.e();
            r17 = r1;
            r16 = r2;
            r18 = r15;
            goto L_0x0194;
        L_0x014b:
            r16 = r2;
            r17 = r15;
            goto L_0x0192;
        L_0x0150:
            r4 = r6.h;
            if (r4 != r2) goto L_0x018e;
        L_0x0154:
            r2 = r6.j;
            if (r2 != 0) goto L_0x018e;
        L_0x0158:
            r2 = r0.d;
            r2 = r2.c();
            if (r2 != 0) goto L_0x0161;
        L_0x0160:
            return;
        L_0x0161:
            r2 = r0.d;
            r2 = r2.e();
            r1 = r1.c;
            if (r1 == 0) goto L_0x0185;
        L_0x016b:
            if (r11 != 0) goto L_0x0185;
        L_0x016d:
            r1 = r0.d;
            r1 = r1.c();
            if (r1 != 0) goto L_0x0176;
        L_0x0175:
            return;
        L_0x0176:
            r1 = r0.d;
            r1 = r1.e();
            r19 = r1;
            r18 = r2;
            r16 = r15;
            r17 = r16;
            goto L_0x0196;
        L_0x0185:
            r18 = r2;
            r16 = r15;
            r17 = r16;
            r19 = r17;
            goto L_0x0196;
        L_0x018e:
            r16 = r15;
            r17 = r16;
        L_0x0192:
            r18 = r17;
        L_0x0194:
            r19 = r18;
        L_0x0196:
            r5 = r0.n;
            r1 = r15;
            r15 = r3;
            r5.a(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19);
            r0.k = r1;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.dw$a.a(byte[], int, int):void");
        }

        public void a(long j, int i) {
            int i2 = 0;
            if (this.i == 9 || (this.c && this.n.a(this.m))) {
                if (this.o) {
                    a(i + ((int) (j - this.j)));
                }
                this.p = this.j;
                this.q = this.l;
                this.r = false;
                this.o = true;
            }
            boolean z = this.r;
            if (this.i == 5 || (this.b && this.i == 1 && this.n.b())) {
                i2 = 1;
            }
            this.r = z | i2;
        }

        private void a(int i) {
            this.a.a(this.q, this.r, (int) (this.j - this.p), i, null);
        }
    }

    public dw(ck ckVar, ed edVar, boolean z, boolean z2) {
        super(ckVar);
        this.c = edVar;
        this.e = new a(ckVar, z, z2);
        this.f = new ea(7, 128);
        this.g = new ea(8, 128);
        this.h = new ea(6, 128);
        this.k = new fp();
    }

    public void b() {
    }

    public void a() {
        fn.a(this.d);
        this.f.a();
        this.g.a();
        this.h.a();
        this.e.b();
        this.i = 0;
    }

    public void a(long j, boolean z) {
        this.j = j;
    }

    public void a(fp fpVar) {
        if (fpVar.b() > 0) {
            int d = fpVar.d();
            int c = fpVar.c();
            byte[] bArr = fpVar.a;
            this.i += (long) fpVar.b();
            this.a.a(fpVar, fpVar.b());
            while (true) {
                int a = fn.a(bArr, d, c, this.d);
                if (a == c) {
                    a(bArr, d, c);
                    return;
                }
                int b = fn.b(bArr, a);
                int i = a - d;
                if (i > 0) {
                    a(bArr, d, a);
                }
                int i2 = c - a;
                long j = this.i - ((long) i2);
                int i3 = i < 0 ? -i : 0;
                long j2 = j;
                a(j2, i2, i3, this.j);
                a(j2, b, this.j);
                d = a + 3;
            }
        }
    }

    private void a(long j, int i, long j2) {
        if (!this.b || this.e.a()) {
            this.f.a(i);
            this.g.a(i);
        }
        this.h.a(i);
        this.e.a(j, i, j2);
    }

    private void a(byte[] bArr, int i, int i2) {
        if (!this.b || this.e.a()) {
            this.f.a(bArr, i, i2);
            this.g.a(bArr, i, i2);
        }
        this.h.a(bArr, i, i2);
        this.e.a(bArr, i, i2);
    }

    private void a(long j, int i, int i2, long j2) {
        int i3 = i2;
        if (!this.b || this.e.a()) {
            this.f.b(i3);
            this.g.b(i3);
            if (this.b) {
                if (this.f.b()) {
                    this.e.a(fn.a(a(this.f)));
                    this.f.a();
                } else if (this.g.b()) {
                    this.e.a(fn.b(a(this.g)));
                    this.g.a();
                }
            } else if (this.f.b() && this.g.b()) {
                List arrayList = new ArrayList();
                arrayList.add(Arrays.copyOf(this.f.a, this.f.b));
                arrayList.add(Arrays.copyOf(this.g.a, this.g.b));
                b a = fn.a(a(this.f));
                com.google.ads.interactivemedia.v3.internal.fn.a b = fn.b(a(this.g));
                this.a.a(bj.a(null, MimeTypes.VIDEO_H264, -1, -1, -1, a.b, a.c, arrayList, -1, a.d));
                this.b = true;
                this.e.a(a);
                this.e.a(b);
                this.f.a();
                this.g.a();
            }
        }
        if (this.h.b(i3)) {
            this.k.a(this.h.a, fn.a(this.h.a, this.h.b));
            this.k.c(4);
            this.c.a(j2, this.k);
        }
        this.e.a(j, i);
    }

    private static fo a(ea eaVar) {
        fo foVar = new fo(eaVar.a, fn.a(eaVar.a, eaVar.b));
        foVar.b(32);
        return foVar;
    }
}
