package com.google.ads.interactivemedia.v3.internal;

import android.support.v4.internal.view.SupportMenu;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class cw {
    private static final int a = ft.c(C.CENC_TYPE_cenc);

    private static final class a {
        public final List<byte[]> a;
        public final int b;
        public final float c;

        public a(List<byte[]> list, int i, float f) {
            this.a = list;
            this.b = i;
            this.c = f;
        }
    }

    private static final class b {
        public final int a;
        public int b;
        public int c;
        public long d;
        private final boolean e;
        private final fp f;
        private final fp g;
        private int h;
        private int i;

        public b(fp fpVar, fp fpVar2, boolean z) {
            this.g = fpVar;
            this.f = fpVar2;
            this.e = z;
            fpVar2.c(12);
            this.a = fpVar2.s();
            fpVar.c(12);
            this.i = fpVar.s();
            boolean z2 = true;
            if (fpVar.m() != 1) {
                z2 = false;
            }
            fe.b(z2, "first_chunk must be 1");
            this.b = -1;
        }

        public boolean a() {
            int i = this.b + 1;
            this.b = i;
            if (i == this.a) {
                return false;
            }
            long u;
            if (this.e) {
                u = this.f.u();
            } else {
                u = this.f.k();
            }
            this.d = u;
            if (this.b == this.h) {
                this.c = this.g.s();
                this.g.d(4);
                i = this.i - 1;
                this.i = i;
                this.h = i > 0 ? this.g.s() - 1 : -1;
            }
            return true;
        }
    }

    private interface c {
        int a();

        int b();

        boolean c();
    }

    private static final class d {
        public final de[] a;
        public bj b;
        public int c = -1;

        public d(int i) {
            this.a = new de[i];
        }
    }

    static final class e implements c {
        private final int a = this.c.s();
        private final int b = this.c.s();
        private final fp c;

        public e(b bVar) {
            this.c = bVar.aP;
            this.c.c(12);
        }

        public int a() {
            return this.b;
        }

        public int b() {
            return this.a == 0 ? this.c.s() : this.a;
        }

        public boolean c() {
            return this.a != 0;
        }
    }

    static final class f implements c {
        private final fp a;
        private final int b = this.a.s();
        private final int c = (this.a.s() & 255);
        private int d;
        private int e;

        public f(b bVar) {
            this.a = bVar.aP;
            this.a.c(12);
        }

        public boolean c() {
            return false;
        }

        public int a() {
            return this.b;
        }

        public int b() {
            if (this.c == 8) {
                return this.a.f();
            }
            if (this.c == 16) {
                return this.a.g();
            }
            int i = this.d;
            this.d = i + 1;
            if (i % 2 != 0) {
                return this.e & 15;
            }
            this.e = this.a.f();
            return (this.e & PsExtractor.VIDEO_STREAM_MASK) >> 4;
        }
    }

    private static final class g {
        private final int a;
        private final long b;
        private final int c;

        public g(int i, long j, int i2) {
            this.a = i;
            this.b = j;
            this.c = i2;
        }
    }

    public static dd a(a aVar, b bVar, long j, boolean z) {
        a aVar2 = aVar;
        a e = aVar2.e(cv.E);
        int e2 = e(e.d(cv.S).aP);
        if (e2 != dd.b && e2 != dd.a && e2 != dd.c && e2 != dd.d && e2 != dd.e && e2 != dd.f) {
            return null;
        }
        long a;
        b bVar2;
        dd ddVar;
        g d = d(aVar2.d(cv.O).aP);
        long j2 = -1;
        if (j == -1) {
            a = d.b;
            bVar2 = bVar;
        } else {
            bVar2 = bVar;
            a = j;
        }
        long c = c(bVar2.aP);
        if (a != -1) {
            j2 = ft.a(a, 1000000, c);
        }
        long j3 = j2;
        a e3 = e.e(cv.F).e(cv.G);
        Pair f = f(e.d(cv.R).aP);
        d a2 = a(e3.d(cv.T).aP, d.a, j3, d.c, (String) f.second, z);
        Pair a3 = a(aVar2.e(cv.P));
        if (a2.b == null) {
            ddVar = null;
        } else {
            dd ddVar2 = new dd(d.a, e2, ((Long) f.first).longValue(), c, j3, a2.b, a2.a, a2.c, (long[]) a3.first, (long[]) a3.second);
        }
        return ddVar;
    }

    public static dg a(dd ddVar, a aVar) throws bl {
        c eVar;
        dd ddVar2 = ddVar;
        a aVar2 = aVar;
        b d = aVar2.d(cv.aq);
        if (d != null) {
            eVar = new e(d);
        } else {
            d = aVar2.d(cv.ar);
            if (d == null) {
                throw new bl("Track has no sample table size information");
            }
            eVar = new f(d);
        }
        int a = eVar.a();
        if (a == 0) {
            return new dg(new long[0], new int[0], 0, new long[0], new int[0]);
        }
        boolean z;
        int s;
        int s2;
        Object obj;
        Object obj2;
        long[] jArr;
        Object obj3;
        int i;
        int i2;
        int i3;
        int i4;
        long j;
        int i5;
        b d2 = aVar2.d(cv.as);
        if (d2 == null) {
            d2 = aVar2.d(cv.at);
            z = true;
        } else {
            z = false;
        }
        fp fpVar = d2.aP;
        fp fpVar2 = aVar2.d(cv.ap).aP;
        fp fpVar3 = aVar2.d(cv.am).aP;
        b d3 = aVar2.d(cv.an);
        fp fpVar4 = d3 != null ? d3.aP : null;
        b d4 = aVar2.d(cv.ao);
        fp fpVar5 = d4 != null ? d4.aP : null;
        b bVar = new b(fpVar2, fpVar, z);
        fpVar3.c(12);
        int s3 = fpVar3.s() - 1;
        int s4 = fpVar3.s();
        int s5 = fpVar3.s();
        if (fpVar5 != null) {
            fpVar5.c(12);
            s = fpVar5.s();
        } else {
            s = 0;
        }
        int i6 = -1;
        if (fpVar4 != null) {
            fpVar4.c(12);
            s2 = fpVar4.s();
            if (s2 > 0) {
                i6 = fpVar4.s() - 1;
            } else {
                fpVar4 = null;
            }
        } else {
            s2 = 0;
        }
        int i7 = (eVar.c() && MimeTypes.AUDIO_RAW.equals(ddVar2.l.b) && s3 == 0 && s == 0 && s2 == 0) ? 1 : 0;
        long j2 = 0;
        if (i7 == 0) {
            int i8;
            Object obj4;
            Object obj5;
            obj = new long[a];
            obj2 = new int[a];
            jArr = new long[a];
            int i9 = s2;
            obj3 = new int[a];
            fp fpVar6 = fpVar3;
            int i10 = s;
            long j3 = 0;
            i = i9;
            s = 0;
            int i11 = 0;
            i2 = 0;
            int i12 = 0;
            i3 = s3;
            long j4 = j3;
            s3 = 0;
            int i13 = s5;
            s5 = s4;
            s4 = i13;
            while (s3 < a) {
                int i14;
                long j5;
                fp fpVar7;
                fp fpVar8;
                while (i2 == 0) {
                    i4 = a;
                    fe.b(bVar.a());
                    i14 = s4;
                    i8 = i3;
                    j = bVar.d;
                    i2 = bVar.c;
                    j4 = j;
                    a = i4;
                    s4 = i14;
                    i3 = i8;
                }
                i4 = a;
                i14 = s4;
                i8 = i3;
                if (fpVar5 != null) {
                    while (i12 == 0 && i10 > 0) {
                        i12 = fpVar5.s();
                        i11 = fpVar5.m();
                        i10--;
                    }
                    i12--;
                }
                a = i11;
                obj[s3] = j4;
                obj2[s3] = eVar.b();
                if (obj2[s3] > s) {
                    s = obj2[s3];
                }
                jArr[s3] = j3 + ((long) a);
                obj3[s3] = fpVar4 == null ? 1 : 0;
                if (s3 == i6) {
                    obj3[s3] = 1;
                    i--;
                    if (i > 0) {
                        s4 = i;
                        obj4 = obj;
                        obj5 = obj3;
                        i6 = fpVar4.s() - 1;
                        i = i14;
                        j5 = j3 + ((long) i);
                        s5--;
                        if (s5 == 0 || i8 <= 0) {
                            fpVar7 = fpVar6;
                            i3 = i8;
                        } else {
                            fpVar7 = fpVar6;
                            i3 = i8 - 1;
                            s5 = fpVar7.s();
                            i = fpVar7.s();
                        }
                        fpVar8 = fpVar7;
                        i2--;
                        s3++;
                        i11 = a;
                        j4 += (long) obj2[s3];
                        a = i4;
                        j3 = j5;
                        obj = obj4;
                        obj3 = obj5;
                        fpVar6 = fpVar8;
                        i13 = s4;
                        s4 = i;
                        i = i13;
                    }
                }
                s4 = i;
                obj4 = obj;
                obj5 = obj3;
                i = i14;
                j5 = j3 + ((long) i);
                s5--;
                if (s5 == 0) {
                }
                fpVar7 = fpVar6;
                i3 = i8;
                fpVar8 = fpVar7;
                i2--;
                s3++;
                i11 = a;
                j4 += (long) obj2[s3];
                a = i4;
                j3 = j5;
                obj = obj4;
                obj3 = obj5;
                fpVar6 = fpVar8;
                i13 = s4;
                s4 = i;
                i = i13;
            }
            i4 = a;
            obj4 = obj;
            obj5 = obj3;
            i8 = i3;
            fe.a(i12 == 0);
            while (i10 > 0) {
                fe.a(fpVar5.s() == 0);
                fpVar5.m();
                i10--;
            }
            if (i == 0 && s5 == 0 && i2 == 0 && i8 == 0) {
                ddVar2 = ddVar;
            } else {
                s4 = i;
                ddVar2 = ddVar;
                a = ddVar2.g;
                StringBuilder stringBuilder = new StringBuilder(215);
                stringBuilder.append("Inconsistent stbl box for track ");
                stringBuilder.append(a);
                stringBuilder.append(": remainingSynchronizationSamples ");
                stringBuilder.append(s4);
                stringBuilder.append(", remainingSamplesAtTimestampDelta ");
                stringBuilder.append(s5);
                stringBuilder.append(", remainingSamplesInChunk ");
                stringBuilder.append(i2);
                stringBuilder.append(", remainingTimestampDeltaChanges ");
                stringBuilder.append(i8);
                Log.w("AtomParsers", stringBuilder.toString());
            }
            i5 = s;
            obj = obj4;
            obj3 = obj5;
        } else {
            i4 = a;
            long[] jArr2 = new long[bVar.a];
            int[] iArr = new int[bVar.a];
            while (bVar.a()) {
                jArr2[bVar.b] = bVar.d;
                iArr[bVar.b] = bVar.c;
            }
            com.google.ads.interactivemedia.v3.internal.cy.a a2 = cy.a(eVar.b(), jArr2, iArr, (long) s5);
            obj = a2.a;
            obj2 = a2.b;
            a = a2.c;
            jArr = a2.d;
            obj3 = a2.e;
            i5 = a;
        }
        if (ddVar2.n == null) {
            ft.a(jArr, 1000000, ddVar2.i);
            return new dg(obj, obj2, i5, jArr, obj3);
        }
        int i15;
        long j6;
        Object obj6;
        Object obj7;
        long[] jArr3;
        Object obj8;
        if (ddVar2.n.length == 1) {
            int i16 = 0;
            if (ddVar2.n[0] == 0) {
                i15 = 0;
                while (i15 < jArr.length) {
                    jArr[i15] = ft.a(jArr[i15] - ddVar2.o[i16], 1000000, ddVar2.i);
                    i15++;
                    i16 = 0;
                }
                return new dg(obj, obj2, i5, jArr, obj3);
            }
        }
        i15 = 0;
        a = 0;
        int i17 = 0;
        s3 = 0;
        while (true) {
            j6 = -1;
            if (i15 >= ddVar2.n.length) {
                break;
            }
            long j7 = ddVar2.o[i15];
            if (j7 != -1) {
                j = ft.a(ddVar2.n[i15], ddVar2.i, ddVar2.j);
                s = ft.b(jArr, j7, true, true);
                obj6 = obj;
                obj7 = obj3;
                i7 = ft.b(jArr, j7 + j, true, false);
                i17 += i7 - s;
                a |= s3 != s ? 1 : 0;
                s3 = i7;
            } else {
                obj6 = obj;
                obj7 = obj3;
            }
            i15++;
            obj = obj6;
            obj3 = obj7;
        }
        obj6 = obj;
        obj7 = obj3;
        i15 = (i17 != i4 ? 1 : 0) | a;
        obj = i15 != 0 ? new long[i17] : obj6;
        Object obj9 = i15 != 0 ? new int[i17] : obj2;
        if (i15 != 0) {
            i5 = 0;
        }
        obj3 = i15 != 0 ? new int[i17] : obj7;
        long[] jArr4 = new long[i17];
        i2 = i5;
        s3 = 0;
        s4 = 0;
        while (s3 < ddVar2.n.length) {
            Object obj10;
            Object obj11;
            long j8 = ddVar2.o[s3];
            long j9 = ddVar2.n[s3];
            if (j8 != j6) {
                Object obj12;
                Object obj13;
                Object obj14 = obj9;
                jArr3 = jArr4;
                j6 = j8 + ft.a(j9, ddVar2.i, ddVar2.j);
                i17 = ft.b(jArr, j8, true, true);
                i3 = ft.b(jArr, j6, true, false);
                if (i15 != 0) {
                    a = i3 - i17;
                    obj12 = obj6;
                    System.arraycopy(obj12, i17, obj, s4, a);
                    obj8 = obj14;
                    System.arraycopy(obj2, i17, obj8, s4, a);
                    obj13 = obj7;
                    System.arraycopy(obj13, i17, obj3, s4, a);
                } else {
                    obj12 = obj6;
                    obj13 = obj7;
                    obj8 = obj14;
                }
                a = i2;
                while (i17 < i3) {
                    int i18 = i3;
                    obj10 = obj12;
                    obj11 = obj13;
                    long j10 = j8;
                    jArr3[s4] = ft.a(j2, 1000000, ddVar2.j) + ft.a(jArr[i17] - j8, 1000000, ddVar2.i);
                    if (i15 != 0 && r15[s4] > a) {
                        a = obj2[i17];
                    }
                    s4++;
                    i17++;
                    i3 = i18;
                    obj12 = obj10;
                    j8 = j10;
                    obj13 = obj11;
                }
                obj10 = obj12;
                obj11 = obj13;
                i2 = a;
            } else {
                obj8 = obj9;
                jArr3 = jArr4;
                obj10 = obj6;
                obj11 = obj7;
            }
            s3++;
            j2 += j9;
            obj9 = obj8;
            jArr4 = jArr3;
            obj6 = obj10;
            obj7 = obj11;
            j6 = -1;
        }
        obj8 = obj9;
        jArr3 = jArr4;
        i15 = 0;
        for (i = 0; i < obj3.length && i15 == 0; i++) {
            i15 |= (obj3[i] & 1) != 0 ? 1 : 0;
        }
        if (i15 != 0) {
            return new dg(obj, obj8, i2, jArr3, obj3);
        }
        throw new bl("The edited sample sequence does not contain a sync sample.");
    }

    public static cg a(b bVar, boolean z) {
        if (z) {
            return null;
        }
        fp fpVar = bVar.aP;
        fpVar.c(8);
        while (fpVar.b() >= 8) {
            int m = fpVar.m();
            if (fpVar.m() == cv.aA) {
                fpVar.c(fpVar.d() - 8);
                fpVar.b(fpVar.d() + m);
                return a(fpVar);
            }
            fpVar.d(m - 8);
        }
        return null;
    }

    private static cg a(fp fpVar) {
        fpVar.d(12);
        fp fpVar2 = new fp();
        while (fpVar.b() >= 8) {
            int m = fpVar.m() - 8;
            if (fpVar.m() == cv.aB) {
                fpVar2.a(fpVar.a, fpVar.d() + m);
                fpVar2.c(fpVar.d());
                cg b = b(fpVar2);
                if (b != null) {
                    return b;
                }
            }
            fpVar.d(m);
        }
        return null;
    }

    private static cg b(fp fpVar) {
        while (true) {
            String str = null;
            if (fpVar.b() <= 0) {
                return null;
            }
            int d = fpVar.d() + fpVar.m();
            if (fpVar.m() == cv.aN) {
                String str2 = null;
                Object obj = str2;
                while (fpVar.d() < d) {
                    int m = fpVar.m() - 12;
                    int m2 = fpVar.m();
                    fpVar.d(4);
                    if (m2 == cv.aC) {
                        obj = fpVar.e(m);
                    } else if (m2 == cv.aD) {
                        str = fpVar.e(m);
                    } else if (m2 == cv.aE) {
                        fpVar.d(4);
                        str2 = fpVar.e(m - 4);
                    } else {
                        fpVar.d(m);
                    }
                }
                if (!(str == null || str2 == null || !"com.apple.iTunes".equals(obj))) {
                    return cg.a(str, str2);
                }
            }
            fpVar.c(d);
        }
    }

    private static long c(fp fpVar) {
        int i = 8;
        fpVar.c(8);
        if (cv.a(fpVar.m()) != 0) {
            i = 16;
        }
        fpVar.d(i);
        return fpVar.k();
    }

    private static g d(fp fpVar) {
        int i = 8;
        fpVar.c(8);
        int a = cv.a(fpVar.m());
        fpVar.d(a == 0 ? 8 : 16);
        int m = fpVar.m();
        fpVar.d(4);
        int d = fpVar.d();
        if (a == 0) {
            i = 4;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            if (fpVar.a[d + i3] != (byte) -1) {
                d = 0;
                break;
            }
        }
        d = 1;
        long j = -1;
        if (d != 0) {
            fpVar.d(i);
        } else {
            long k = a == 0 ? fpVar.k() : fpVar.u();
            if (k != 0) {
                j = k;
            }
        }
        fpVar.d(16);
        i = fpVar.m();
        a = fpVar.m();
        fpVar.d(4);
        int m2 = fpVar.m();
        int m3 = fpVar.m();
        if (i == 0 && a == 65536 && m2 == SupportMenu.CATEGORY_MASK && m3 == 0) {
            i2 = 90;
        } else if (i == 0 && a == SupportMenu.CATEGORY_MASK && m2 == 65536 && m3 == 0) {
            i2 = 270;
        } else if (i == SupportMenu.CATEGORY_MASK && a == 0 && m2 == 0 && m3 == SupportMenu.CATEGORY_MASK) {
            i2 = 180;
        }
        return new g(m, j, i2);
    }

    private static int e(fp fpVar) {
        fpVar.c(16);
        return fpVar.m();
    }

    private static Pair<Long, String> f(fp fpVar) {
        int i = 8;
        fpVar.c(8);
        int a = cv.a(fpVar.m());
        fpVar.d(a == 0 ? 8 : 16);
        long k = fpVar.k();
        if (a == 0) {
            i = 4;
        }
        fpVar.d(i);
        int g = fpVar.g();
        char c = (char) (((g >> 10) & 31) + 96);
        char c2 = (char) (((g >> 5) & 31) + 96);
        char c3 = (char) ((g & 31) + 96);
        StringBuilder stringBuilder = new StringBuilder(3);
        stringBuilder.append(c);
        stringBuilder.append(c2);
        stringBuilder.append(c3);
        return Pair.create(Long.valueOf(k), stringBuilder.toString());
    }

    private static d a(fp fpVar, int i, long j, int i2, String str, boolean z) {
        fp fpVar2 = fpVar;
        fpVar2.c(12);
        int m = fpVar.m();
        d dVar = new d(m);
        for (int i3 = 0; i3 < m; i3++) {
            int d = fpVar.d();
            int m2 = fpVar.m();
            fe.a(m2 > 0, "childAtomSize should be positive");
            int m3 = fpVar.m();
            if (m3 == cv.b || m3 == cv.c || m3 == cv.Z || m3 == cv.al || m3 == cv.d || m3 == cv.e || m3 == cv.f || m3 == cv.aJ || m3 == cv.aK) {
                a(fpVar2, m3, d, m2, i, j, i2, dVar, i3);
            } else if (m3 == cv.i || m3 == cv.aa || m3 == cv.n || m3 == cv.p || m3 == cv.r || m3 == cv.u || m3 == cv.s || m3 == cv.t || m3 == cv.ax || m3 == cv.ay || m3 == cv.l || m3 == cv.m || m3 == cv.j) {
                a(fpVar2, m3, d, m2, i, j, str, z, dVar, i3);
            } else if (m3 == cv.aj) {
                dVar.b = bj.a(Integer.toString(i), MimeTypes.APPLICATION_TTML, -1, j, str);
            } else if (m3 == cv.au) {
                dVar.b = bj.a(Integer.toString(i), MimeTypes.APPLICATION_TX3G, -1, j, str);
            } else if (m3 == cv.av) {
                dVar.b = bj.a(Integer.toString(i), "application/x-mp4vtt", -1, j, str);
            } else if (m3 == cv.aw) {
                dVar.b = bj.a(Integer.toString(i), MimeTypes.APPLICATION_TTML, -1, j, str, 0);
            } else if (m3 == cv.aM) {
                dVar.b = bj.a(Integer.toString(i), MimeTypes.APPLICATION_CAMERA_MOTION, -1, j);
            } else {
                long j2 = j;
            }
            fpVar2.c(d + m2);
        }
        return dVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:67:0x0127  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0126 A:{RETURN} */
    private static void a(com.google.ads.interactivemedia.v3.internal.fp r21, int r22, int r23, int r24, int r25, long r26, int r28, com.google.ads.interactivemedia.v3.internal.cw.d r29, int r30) {
        /*
        r0 = r21;
        r1 = r22;
        r2 = r23;
        r3 = r24;
        r4 = r29;
        r5 = r2 + 8;
        r0.c(r5);
        r5 = 24;
        r0.d(r5);
        r12 = r21.g();
        r13 = r21.g();
        r5 = 50;
        r0.d(r5);
        r5 = r21.d();
        r6 = com.google.ads.interactivemedia.v3.internal.cv.Z;
        if (r1 != r6) goto L_0x0031;
    L_0x0029:
        r6 = r30;
        a(r0, r2, r3, r4, r6);
        r0.c(r5);
    L_0x0031:
        r6 = -1;
        r7 = 0;
        r9 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r18 = r6;
        r14 = r7;
        r17 = r14;
        r16 = r9;
        r6 = 0;
    L_0x003d:
        r9 = r5 - r2;
        if (r9 >= r3) goto L_0x0124;
    L_0x0041:
        r0.c(r5);
        r9 = r21.d();
        r10 = r21.m();
        if (r10 != 0) goto L_0x0057;
    L_0x004e:
        r11 = r21.d();
        r11 = r11 - r2;
        if (r11 != r3) goto L_0x0057;
    L_0x0055:
        goto L_0x0124;
    L_0x0057:
        if (r10 <= 0) goto L_0x005b;
    L_0x0059:
        r15 = 1;
        goto L_0x005c;
    L_0x005b:
        r15 = 0;
    L_0x005c:
        r8 = "childAtomSize should be positive";
        com.google.ads.interactivemedia.v3.internal.fe.a(r15, r8);
        r8 = r21.m();
        r15 = com.google.ads.interactivemedia.v3.internal.cv.H;
        r11 = 3;
        if (r8 != r15) goto L_0x0086;
    L_0x006a:
        if (r7 != 0) goto L_0x006e;
    L_0x006c:
        r7 = 1;
        goto L_0x006f;
    L_0x006e:
        r7 = 0;
    L_0x006f:
        com.google.ads.interactivemedia.v3.internal.fe.b(r7);
        r7 = "video/avc";
        r8 = a(r0, r9);
        r14 = r8.a;
        r9 = r8.b;
        r4.c = r9;
        if (r6 != 0) goto L_0x0121;
    L_0x0080:
        r8 = r8.c;
        r16 = r8;
        goto L_0x0121;
    L_0x0086:
        r15 = com.google.ads.interactivemedia.v3.internal.cv.I;
        if (r8 != r15) goto L_0x00a9;
    L_0x008a:
        if (r7 != 0) goto L_0x008e;
    L_0x008c:
        r7 = 1;
        goto L_0x008f;
    L_0x008e:
        r7 = 0;
    L_0x008f:
        com.google.ads.interactivemedia.v3.internal.fe.b(r7);
        r7 = "video/hevc";
        r8 = b(r0, r9);
        r9 = r8.first;
        r14 = r9;
        r14 = (java.util.List) r14;
        r8 = r8.second;
        r8 = (java.lang.Integer) r8;
        r8 = r8.intValue();
        r4.c = r8;
        goto L_0x0121;
    L_0x00a9:
        r15 = com.google.ads.interactivemedia.v3.internal.cv.g;
        if (r8 != r15) goto L_0x00b9;
    L_0x00ad:
        if (r7 != 0) goto L_0x00b1;
    L_0x00af:
        r7 = 1;
        goto L_0x00b2;
    L_0x00b1:
        r7 = 0;
    L_0x00b2:
        com.google.ads.interactivemedia.v3.internal.fe.b(r7);
        r7 = "video/3gpp";
        goto L_0x0121;
    L_0x00b9:
        r15 = com.google.ads.interactivemedia.v3.internal.cv.J;
        if (r8 != r15) goto L_0x00d7;
    L_0x00bd:
        if (r7 != 0) goto L_0x00c1;
    L_0x00bf:
        r7 = 1;
        goto L_0x00c2;
    L_0x00c1:
        r7 = 0;
    L_0x00c2:
        com.google.ads.interactivemedia.v3.internal.fe.b(r7);
        r7 = d(r0, r9);
        r8 = r7.first;
        r8 = (java.lang.String) r8;
        r7 = r7.second;
        r7 = (byte[]) r7;
        r14 = java.util.Collections.singletonList(r7);
        r7 = r8;
        goto L_0x0121;
    L_0x00d7:
        r15 = com.google.ads.interactivemedia.v3.internal.cv.ai;
        if (r8 != r15) goto L_0x00e1;
    L_0x00db:
        r16 = c(r0, r9);
        r6 = 1;
        goto L_0x0121;
    L_0x00e1:
        r15 = com.google.ads.interactivemedia.v3.internal.cv.aL;
        if (r8 != r15) goto L_0x00f7;
    L_0x00e5:
        if (r7 != 0) goto L_0x00e9;
    L_0x00e7:
        r7 = 1;
        goto L_0x00ea;
    L_0x00e9:
        r7 = 0;
    L_0x00ea:
        com.google.ads.interactivemedia.v3.internal.fe.b(r7);
        r7 = com.google.ads.interactivemedia.v3.internal.cv.aJ;
        if (r1 != r7) goto L_0x00f4;
    L_0x00f1:
        r7 = "video/x-vnd.on2.vp8";
        goto L_0x0121;
    L_0x00f4:
        r7 = "video/x-vnd.on2.vp9";
        goto L_0x0121;
    L_0x00f7:
        r15 = com.google.ads.interactivemedia.v3.internal.cv.aH;
        if (r8 != r15) goto L_0x0100;
    L_0x00fb:
        r17 = d(r0, r9, r10);
        goto L_0x0121;
    L_0x0100:
        r9 = com.google.ads.interactivemedia.v3.internal.cv.aG;
        if (r8 != r9) goto L_0x0121;
    L_0x0104:
        r8 = r21.f();
        r0.d(r11);
        if (r8 != 0) goto L_0x0121;
    L_0x010d:
        r8 = r21.f();
        switch(r8) {
            case 0: goto L_0x011f;
            case 1: goto L_0x011c;
            case 2: goto L_0x0118;
            case 3: goto L_0x0115;
            default: goto L_0x0114;
        };
    L_0x0114:
        goto L_0x0121;
    L_0x0115:
        r18 = r11;
        goto L_0x0121;
    L_0x0118:
        r8 = 2;
        r18 = r8;
        goto L_0x0121;
    L_0x011c:
        r18 = 1;
        goto L_0x0121;
    L_0x011f:
        r18 = 0;
    L_0x0121:
        r5 = r5 + r10;
        goto L_0x003d;
    L_0x0124:
        if (r7 != 0) goto L_0x0127;
    L_0x0126:
        return;
    L_0x0127:
        r6 = java.lang.Integer.toString(r25);
        r8 = -1;
        r9 = -1;
        r19 = 0;
        r10 = r26;
        r15 = r28;
        r0 = com.google.ads.interactivemedia.v3.internal.bj.a(r6, r7, r8, r9, r10, r12, r13, r14, r15, r16, r17, r18, r19);
        r4.b = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.cw.a(com.google.ads.interactivemedia.v3.internal.fp, int, int, int, int, long, int, com.google.ads.interactivemedia.v3.internal.cw$d, int):void");
    }

    private static a a(fp fpVar, int i) {
        fpVar.c((i + 8) + 4);
        i = (fpVar.f() & 3) + 1;
        if (i == 3) {
            throw new IllegalStateException();
        }
        int i2;
        ArrayList arrayList = new ArrayList();
        float f = 1.0f;
        int f2 = fpVar.f() & 31;
        for (i2 = 0; i2 < f2; i2++) {
            arrayList.add(fn.a(fpVar));
        }
        i2 = fpVar.f();
        for (int i3 = 0; i3 < i2; i3++) {
            arrayList.add(fn.a(fpVar));
        }
        if (f2 > 0) {
            fo foVar = new fo((byte[]) arrayList.get(0));
            foVar.a(8 * (i + 1));
            f = fn.a(foVar).d;
        }
        return new a(arrayList, i, f);
    }

    private static Pair<List<byte[]>, Integer> b(fp fpVar, int i) {
        int i2;
        int g;
        Object obj;
        fpVar.c((i + 8) + 21);
        i = fpVar.f() & 3;
        int f = fpVar.f();
        int d = fpVar.d();
        int i3 = 0;
        int i4 = i3;
        while (i3 < f) {
            fpVar.d(1);
            int g2 = fpVar.g();
            i2 = i4;
            for (i4 = 0; i4 < g2; i4++) {
                g = fpVar.g();
                i2 += 4 + g;
                fpVar.d(g);
            }
            i3++;
            i4 = i2;
        }
        fpVar.c(d);
        byte[] bArr = new byte[i4];
        i3 = 0;
        i2 = i3;
        while (i3 < f) {
            fpVar.d(1);
            g = fpVar.g();
            int i5 = i2;
            for (i2 = 0; i2 < g; i2++) {
                int g3 = fpVar.g();
                System.arraycopy(fn.a, 0, bArr, i5, fn.a.length);
                i5 += fn.a.length;
                System.arraycopy(fpVar.a, fpVar.d(), bArr, i5, g3);
                i5 += g3;
                fpVar.d(g3);
            }
            i3++;
            i2 = i5;
        }
        if (i4 == 0) {
            obj = null;
        } else {
            obj = Collections.singletonList(bArr);
        }
        return Pair.create(obj, Integer.valueOf(i + 1));
    }

    private static Pair<long[], long[]> a(a aVar) {
        if (aVar != null) {
            b d = aVar.d(cv.Q);
            if (d != null) {
                fp fpVar = d.aP;
                fpVar.c(8);
                int a = cv.a(fpVar.m());
                int s = fpVar.s();
                long[] jArr = new long[s];
                long[] jArr2 = new long[s];
                for (int i = 0; i < s; i++) {
                    jArr[i] = a == 1 ? fpVar.u() : fpVar.k();
                    jArr2[i] = a == 1 ? fpVar.o() : (long) fpVar.m();
                    if (fpVar.i() != (short) 1) {
                        throw new IllegalArgumentException("Unsupported media rate.");
                    }
                    fpVar.d(2);
                }
                return Pair.create(jArr, jArr2);
            }
        }
        return Pair.create(null, null);
    }

    private static float c(fp fpVar, int i) {
        fpVar.c(i + 8);
        return ((float) fpVar.s()) / ((float) fpVar.s());
    }

    private static void a(fp fpVar, int i, int i2, int i3, int i4, long j, String str, boolean z, d dVar, int i5) {
        int g;
        int g2;
        int q;
        int i6;
        Object obj;
        String str2;
        int i7;
        d dVar2;
        fp fpVar2 = fpVar;
        int i8 = i2;
        int i9 = i3;
        long j2 = j;
        String str3 = str;
        d dVar3 = dVar;
        fpVar2.c(i8 + 8);
        int i10 = 8;
        if (z) {
            fpVar2.d(8);
            g = fpVar.g();
            fpVar2.d(6);
        } else {
            fpVar2.d(16);
            g = 0;
        }
        int i11 = 2;
        boolean z2 = true;
        if (g == 0 || g == 1) {
            g2 = fpVar.g();
            fpVar2.d(6);
            q = fpVar.q();
            if (g == 1) {
                fpVar2.d(16);
            }
            i6 = g2;
        } else if (g == 2) {
            fpVar2.d(16);
            q = (int) Math.round(fpVar.v());
            i6 = fpVar.s();
            fpVar2.d(20);
        } else {
            return;
        }
        g = fpVar.d();
        int i12 = i;
        if (i12 == cv.aa) {
            g2 = a(fpVar2, i8, i9, dVar3, i5);
            fpVar2.c(g);
        } else {
            g2 = i12;
        }
        String str4 = g2 == cv.n ? MimeTypes.AUDIO_AC3 : g2 == cv.p ? MimeTypes.AUDIO_E_AC3 : g2 == cv.r ? MimeTypes.AUDIO_DTS : (g2 == cv.s || g2 == cv.t) ? MimeTypes.AUDIO_DTS_HD : g2 == cv.u ? MimeTypes.AUDIO_DTS_EXPRESS : g2 == cv.ax ? MimeTypes.AUDIO_AMR_NB : g2 == cv.ay ? MimeTypes.AUDIO_AMR_WB : (g2 == cv.l || g2 == cv.m) ? MimeTypes.AUDIO_RAW : g2 == cv.j ? MimeTypes.AUDIO_MPEG : null;
        int i13 = q;
        int i14 = i6;
        i12 = g;
        String str5 = str4;
        Object obj2 = null;
        while (i12 - i8 < i9) {
            int i15;
            boolean z3;
            fpVar2.c(i12);
            g = fpVar.m();
            fe.a(g > 0 ? z2 : false, "childAtomSize should be positive");
            q = fpVar.m();
            int i16;
            int i17;
            if (q == cv.J || (z && q == cv.k)) {
                Object obj3;
                i16 = g;
                obj = obj2;
                i17 = i12;
                str2 = str5;
                i7 = i11;
                i15 = i10;
                dVar2 = dVar3;
                z3 = true;
                if (q == cv.J) {
                    q = i16;
                    g = i17;
                    i12 = g;
                } else {
                    q = i16;
                    g = i17;
                    i12 = a(fpVar2, g, q);
                }
                if (i12 != -1) {
                    Pair d = d(fpVar2, i12);
                    str4 = (String) d.first;
                    obj3 = (byte[]) d.second;
                    if (MimeTypes.AUDIO_AAC.equals(str4)) {
                        Pair a = ff.a(obj3);
                        int intValue = ((Integer) a.first).intValue();
                        i14 = ((Integer) a.second).intValue();
                        i13 = intValue;
                    }
                    str5 = str4;
                } else {
                    str5 = str2;
                    obj3 = obj;
                }
                obj2 = obj3;
            } else {
                if (q == cv.o) {
                    fpVar2.c(i10 + i12);
                    dVar3.b = fd.a(fpVar2, Integer.toString(i4), j2, str3);
                } else if (q == cv.q) {
                    fpVar2.c(i10 + i12);
                    dVar3.b = fd.b(fpVar2, Integer.toString(i4), j2, str3);
                } else if (q == cv.v) {
                    i16 = g;
                    i17 = i12;
                    str2 = str5;
                    obj = obj2;
                    z3 = true;
                    i7 = i11;
                    i15 = i10;
                    dVar2 = dVar3;
                    dVar2.b = bj.a(Integer.toString(i4), str5, -1, -1, j2, i14, i13, null, str3);
                    q = i16;
                    g = i17;
                    str5 = str2;
                    obj2 = obj;
                }
                i16 = g;
                obj = obj2;
                i17 = i12;
                str2 = str5;
                i7 = i11;
                i15 = i10;
                dVar2 = dVar3;
                z3 = true;
                q = i16;
                g = i17;
                str5 = str2;
                obj2 = obj;
            }
            i12 = g + q;
            dVar3 = dVar2;
            z2 = z3;
            i11 = i7;
            i10 = i15;
            i9 = i3;
        }
        obj = obj2;
        str2 = str5;
        i7 = i11;
        dVar2 = dVar3;
        if (dVar2.b == null) {
            str4 = str2;
            if (str4 != null) {
                List list;
                i11 = MimeTypes.AUDIO_RAW.equals(str4) ? i7 : -1;
                String num = Integer.toString(i4);
                Object obj4 = obj;
                if (obj4 == null) {
                    list = null;
                } else {
                    list = Collections.singletonList(obj4);
                }
                dVar2.b = bj.a(num, str4, -1, -1, j2, i14, i13, list, str3, i11);
            }
        }
    }

    private static int a(fp fpVar, int i, int i2) {
        int d = fpVar.d();
        while (d - i < i2) {
            fpVar.c(d);
            int m = fpVar.m();
            fe.a(m > 0, "childAtomSize should be positive");
            if (fpVar.m() == cv.J) {
                return d;
            }
            d += m;
        }
        return -1;
    }

    private static Pair<String, byte[]> d(fp fpVar, int i) {
        fpVar.c((i + 8) + 4);
        fpVar.d(1);
        g(fpVar);
        fpVar.d(2);
        int f = fpVar.f();
        if ((f & 128) != 0) {
            fpVar.d(2);
        }
        if ((f & 64) != 0) {
            fpVar.d(fpVar.g());
        }
        if ((f & 32) != 0) {
            fpVar.d(2);
        }
        fpVar.d(1);
        g(fpVar);
        Object obj = null;
        switch (fpVar.f()) {
            case 32:
                obj = MimeTypes.VIDEO_MP4V;
                break;
            case 33:
                obj = MimeTypes.VIDEO_H264;
                break;
            case 35:
                obj = MimeTypes.VIDEO_H265;
                break;
            case 64:
            case 102:
            case 103:
            case 104:
                obj = MimeTypes.AUDIO_AAC;
                break;
            case 107:
                return Pair.create(MimeTypes.AUDIO_MPEG, null);
            case 165:
                obj = MimeTypes.AUDIO_AC3;
                break;
            case 166:
                obj = MimeTypes.AUDIO_E_AC3;
                break;
            case 169:
            case 172:
                return Pair.create(MimeTypes.AUDIO_DTS, null);
            case 170:
            case 171:
                return Pair.create(MimeTypes.AUDIO_DTS_HD, null);
        }
        fpVar.d(12);
        fpVar.d(1);
        i = g(fpVar);
        byte[] bArr = new byte[i];
        fpVar.a(bArr, 0, i);
        return Pair.create(obj, bArr);
    }

    private static int a(fp fpVar, int i, int i2, d dVar, int i3) {
        int d = fpVar.d();
        while (true) {
            boolean z = false;
            if (d - i >= i2) {
                return 0;
            }
            fpVar.c(d);
            int m = fpVar.m();
            if (m > 0) {
                z = true;
            }
            fe.a(z, "childAtomSize should be positive");
            if (fpVar.m() == cv.V) {
                Pair b = b(fpVar, d, m);
                if (b != null) {
                    dVar.a[i3] = (de) b.second;
                    return ((Integer) b.first).intValue();
                }
            }
            d += m;
        }
    }

    private static Pair<Integer, de> b(fp fpVar, int i, int i2) {
        int i3 = i + 8;
        boolean z = false;
        Object obj = null;
        Object obj2 = obj;
        boolean z2 = false;
        while (true) {
            boolean z3 = true;
            if (i3 - i >= i2) {
                break;
            }
            fpVar.c(i3);
            int m = fpVar.m();
            int m2 = fpVar.m();
            if (m2 == cv.ab) {
                obj = Integer.valueOf(fpVar.m());
            } else if (m2 == cv.W) {
                fpVar.d(4);
                if (fpVar.m() != a) {
                    z3 = false;
                }
                z2 = z3;
            } else if (m2 == cv.X) {
                obj2 = c(fpVar, i3, m);
            }
            i3 += m;
        }
        if (!z2) {
            return null;
        }
        fe.a(obj != null, "frma atom is mandatory");
        if (obj2 != null) {
            z = true;
        }
        fe.a(z, "schi->tenc atom is mandatory");
        return Pair.create(obj, obj2);
    }

    private static de c(fp fpVar, int i, int i2) {
        int i3 = i + 8;
        while (i3 - i < i2) {
            fpVar.c(i3);
            int m = fpVar.m();
            if (fpVar.m() == cv.Y) {
                fpVar.d(6);
                boolean z = true;
                if (fpVar.f() != 1) {
                    z = false;
                }
                i = fpVar.f();
                byte[] bArr = new byte[16];
                fpVar.a(bArr, 0, bArr.length);
                return new de(z, i, bArr);
            }
            i3 += m;
        }
        return null;
    }

    private static byte[] d(fp fpVar, int i, int i2) {
        int i3 = i + 8;
        while (i3 - i < i2) {
            fpVar.c(i3);
            int m = fpVar.m();
            if (fpVar.m() == cv.aI) {
                return Arrays.copyOfRange(fpVar.a, i3, m + i3);
            }
            i3 += m;
        }
        return null;
    }

    private static int g(fp fpVar) {
        int f = fpVar.f();
        int i = f & 127;
        while ((f & 128) == 128) {
            f = fpVar.f();
            i = (i << 7) | (f & 127);
        }
        return i;
    }
}
