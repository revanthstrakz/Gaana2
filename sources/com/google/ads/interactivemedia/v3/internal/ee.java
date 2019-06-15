package com.google.ads.interactivemedia.v3.internal;

import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.moe.pushlibrary.MoEWorker;
import java.io.IOException;

public final class ee implements cc {
    private static final long d = ((long) ft.c("AC-3"));
    private static final long e = ((long) ft.c("EAC3"));
    private static final long f = ((long) ft.c("HEVC"));
    final SparseArray<d> a;
    final SparseBooleanArray b;
    dy c;
    private final ec g;
    private final int h;
    private final fp i;
    private final fo j;
    private final SparseIntArray k;
    private ce l;
    private boolean m;
    private int n;

    private static abstract class d {
        private d() {
        }

        public abstract void a();

        public abstract void a(fp fpVar, boolean z, ce ceVar);
    }

    private class a extends d {
        private final fp b = new fp();
        private final fo c = new fo(new byte[4]);
        private int d;
        private int e;
        private int f;

        public a() {
            super();
        }

        public void a() {
        }

        public void a(fp fpVar, boolean z, ce ceVar) {
            int i = 0;
            if (z) {
                fpVar.d(fpVar.f());
                fpVar.a(this.c, 3);
                this.c.b(12);
                this.d = this.c.c(12);
                this.e = 0;
                this.f = ft.a(this.c.a, 0, 3, -1);
                this.b.a(this.d);
            }
            int min = Math.min(fpVar.b(), this.d - this.e);
            fpVar.a(this.b.a, this.e, min);
            this.e += min;
            if (this.e >= this.d && ft.a(this.b.a, 0, this.d, this.f) == 0) {
                this.b.d(5);
                int i2 = (this.d - 9) / 4;
                while (i < i2) {
                    this.b.a(this.c, 4);
                    int c = this.c.c(16);
                    this.c.b(3);
                    if (c == 0) {
                        this.c.b(13);
                    } else {
                        c = this.c.c(13);
                        ee.this.a.put(c, new c(c));
                    }
                    i++;
                }
            }
        }
    }

    private static final class b extends d {
        private final du a;
        private final ec b;
        private final fo c = new fo(new byte[10]);
        private int d = 0;
        private int e;
        private boolean f;
        private boolean g;
        private boolean h;
        private int i;
        private int j;
        private boolean k;
        private long l;

        public b(du duVar, ec ecVar) {
            super();
            this.a = duVar;
            this.b = ecVar;
        }

        public void a() {
            this.d = 0;
            this.e = 0;
            this.h = false;
            this.a.a();
        }

        public void a(fp fpVar, boolean z, ce ceVar) {
            int i;
            if (z) {
                switch (this.d) {
                    case 2:
                        Log.w("TsExtractor", "Unexpected start indicator reading extended header");
                        break;
                    case 3:
                        if (this.j != -1) {
                            i = this.j;
                            StringBuilder stringBuilder = new StringBuilder(59);
                            stringBuilder.append("Unexpected start indicator: expected ");
                            stringBuilder.append(i);
                            stringBuilder.append(" more bytes");
                            Log.w("TsExtractor", stringBuilder.toString());
                        }
                        this.a.b();
                        break;
                }
                a(1);
            }
            while (fpVar.b() > 0) {
                i = 0;
                switch (this.d) {
                    case 0:
                        fpVar.d(fpVar.b());
                        break;
                    case 1:
                        if (!a(fpVar, this.c.a, 9)) {
                            break;
                        }
                        if (b()) {
                            i = 2;
                        }
                        a(i);
                        break;
                    case 2:
                        if (a(fpVar, this.c.a, Math.min(10, this.i)) && a(fpVar, null, this.i)) {
                            c();
                            this.a.a(this.l, this.k);
                            a(3);
                            break;
                        }
                    case 3:
                        int b = fpVar.b();
                        if (this.j != -1) {
                            i = b - this.j;
                        }
                        if (i > 0) {
                            b -= i;
                            fpVar.b(fpVar.d() + b);
                        }
                        this.a.a(fpVar);
                        if (this.j == -1) {
                            break;
                        }
                        this.j -= b;
                        if (this.j != 0) {
                            break;
                        }
                        this.a.b();
                        a(1);
                        break;
                    default:
                        break;
                }
            }
        }

        private void a(int i) {
            this.d = i;
            this.e = 0;
        }

        private boolean a(fp fpVar, byte[] bArr, int i) {
            int min = Math.min(fpVar.b(), i - this.e);
            boolean z = true;
            if (min <= 0) {
                return true;
            }
            if (bArr == null) {
                fpVar.d(min);
            } else {
                fpVar.a(bArr, this.e, min);
            }
            this.e += min;
            if (this.e != i) {
                z = false;
            }
            return z;
        }

        private boolean b() {
            this.c.a(0);
            int c = this.c.c(24);
            if (c != 1) {
                StringBuilder stringBuilder = new StringBuilder(41);
                stringBuilder.append("Unexpected start code prefix: ");
                stringBuilder.append(c);
                Log.w("TsExtractor", stringBuilder.toString());
                this.j = -1;
                return false;
            }
            this.c.b(8);
            c = this.c.c(16);
            this.c.b(5);
            this.k = this.c.b();
            this.c.b(2);
            this.f = this.c.b();
            this.g = this.c.b();
            this.c.b(6);
            this.i = this.c.c(8);
            if (c == 0) {
                this.j = -1;
            } else {
                this.j = ((c + 6) - 9) - this.i;
            }
            return true;
        }

        private void c() {
            this.c.a(0);
            this.l = -1;
            if (this.f) {
                this.c.b(4);
                long c = ((long) this.c.c(3)) << 30;
                this.c.b(1);
                long c2 = c | ((long) (this.c.c(15) << 15));
                this.c.b(1);
                long c3 = c2 | ((long) this.c.c(15));
                this.c.b(1);
                if (!this.h && this.g) {
                    this.c.b(4);
                    long c4 = ((long) this.c.c(3)) << 30;
                    this.c.b(1);
                    long c5 = c4 | ((long) (this.c.c(15) << 15));
                    this.c.b(1);
                    long c6 = c5 | ((long) this.c.c(15));
                    this.c.b(1);
                    this.b.a(c6);
                    this.h = true;
                }
                this.l = this.b.a(c3);
            }
        }
    }

    private class c extends d {
        private final fo b = new fo(new byte[5]);
        private final fp c = new fp();
        private final int d;
        private int e;
        private int f;
        private int g;

        public c(int i) {
            super();
            this.d = i;
        }

        public void a() {
        }

        public void a(fp fpVar, boolean z, ce ceVar) {
            fp fpVar2 = fpVar;
            ce ceVar2 = ceVar;
            int i = 3;
            int i2 = 12;
            if (z) {
                fpVar2.d(fpVar.f());
                fpVar2.a(this.b, 3);
                this.b.b(12);
                this.e = this.b.c(12);
                this.f = 0;
                this.g = ft.a(this.b.a, 0, 3, -1);
                this.c.a(this.e);
            }
            int min = Math.min(fpVar.b(), this.e - this.f);
            fpVar2.a(this.c.a, this.f, min);
            this.f += min;
            if (this.f >= this.e && ft.a(this.c.a, 0, this.e, this.g) == 0) {
                this.c.d(7);
                this.c.a(this.b, 2);
                this.b.b(4);
                int c = this.b.c(12);
                this.c.d(c);
                if ((ee.this.h & 16) != 0 && ee.this.c == null) {
                    ee.this.c = new dy(ceVar2.d(21));
                }
                int i3 = ((this.e - 9) - c) - 4;
                while (i3 > 0) {
                    this.c.a(this.b, 5);
                    int c2 = this.b.c(8);
                    this.b.b(i);
                    int c3 = this.b.c(13);
                    this.b.b(4);
                    int c4 = this.b.c(i2);
                    if (c2 == 6) {
                        c2 = a(this.c, c4);
                    } else {
                        this.c.d(c4);
                    }
                    i3 -= c4 + 5;
                    c4 = (ee.this.h & 16) != 0 ? c2 : c3;
                    if (!ee.this.b.get(c4)) {
                        du duVar = null;
                        if (c2 != 15) {
                            if (c2 == 21) {
                                duVar = (ee.this.h & 16) != 0 ? ee.this.c : new dy(ceVar2.d(ee.this.n = ee.this.n + 1));
                            } else if (c2 != 27) {
                                if (c2 == 36) {
                                    duVar = new dx(ceVar2.d(c4), new ed(ceVar2.d(ee.this.n = ee.this.n + 1)));
                                } else if (c2 != TsExtractor.TS_STREAM_TYPE_E_AC3) {
                                    if (c2 != TsExtractor.TS_STREAM_TYPE_DTS) {
                                        switch (c2) {
                                            case 2:
                                                duVar = new dv(ceVar2.d(c4));
                                                break;
                                            case 3:
                                                duVar = new dz(ceVar2.d(c4));
                                                break;
                                            case 4:
                                                duVar = new dz(ceVar2.d(c4));
                                                break;
                                            default:
                                                switch (c2) {
                                                    case TsExtractor.TS_STREAM_TYPE_AC3 /*129*/:
                                                        duVar = new dq(ceVar2.d(c4), false);
                                                        break;
                                                    case TsExtractor.TS_STREAM_TYPE_HDMV_DTS /*130*/:
                                                        break;
                                                }
                                                break;
                                        }
                                    }
                                    duVar = new dt(ceVar2.d(c4));
                                } else {
                                    duVar = new dq(ceVar2.d(c4), true);
                                }
                            } else if ((ee.this.h & 4) == 0) {
                                duVar = new dw(ceVar2.d(c4), new ed(ceVar2.d(ee.this.n = ee.this.n + 1)), (ee.this.h & 1) != 0, (ee.this.h & 8) != 0);
                            }
                        } else if ((ee.this.h & 2) == 0) {
                            duVar = new ds(ceVar2.d(c4), new cb());
                        }
                        if (duVar != null) {
                            ee.this.b.put(c4, true);
                            ee.this.a.put(c3, new b(duVar, ee.this.g));
                            continue;
                        }
                    }
                    i = 3;
                    i2 = 12;
                }
                if ((ee.this.h & 16) == 0) {
                    ee.this.a.remove(0);
                    ee.this.a.remove(this.d);
                    ceVar.f();
                } else if (!ee.this.m) {
                    ceVar.f();
                }
                ee.this.m = true;
            }
        }

        private int a(fp fpVar, int i) {
            int d = fpVar.d() + i;
            i = -1;
            while (fpVar.d() < d) {
                int f = fpVar.f();
                int f2 = fpVar.f();
                if (f == 5) {
                    long k = fpVar.k();
                    if (k == ee.d) {
                        i = TsExtractor.TS_STREAM_TYPE_AC3;
                    } else if (k == ee.e) {
                        i = TsExtractor.TS_STREAM_TYPE_E_AC3;
                    } else if (k == ee.f) {
                        i = 36;
                    }
                    fpVar.c(d);
                    return i;
                }
                if (f == 106) {
                    i = TsExtractor.TS_STREAM_TYPE_AC3;
                } else if (f == 122) {
                    i = TsExtractor.TS_STREAM_TYPE_E_AC3;
                } else if (f == MoEWorker.REQ_CODE_SEND_DATA) {
                    i = TsExtractor.TS_STREAM_TYPE_DTS;
                }
                fpVar.d(f2);
            }
            fpVar.c(d);
            return i;
        }
    }

    public ee() {
        this(new ec(0));
    }

    public void c() {
    }

    public ee(ec ecVar) {
        this(ecVar, 0);
    }

    public ee(ec ecVar, int i) {
        this.g = ecVar;
        this.h = i;
        this.i = new fp(940);
        this.j = new fo(new byte[3]);
        this.a = new SparseArray();
        this.b = new SparseBooleanArray();
        this.k = new SparseIntArray();
        f();
    }

    public boolean a(cd cdVar) throws IOException, InterruptedException {
        byte[] bArr = this.i.a;
        cdVar.c(bArr, 0, 940);
        int i = 0;
        while (i < TsExtractor.TS_PACKET_SIZE) {
            int i2 = 0;
            while (i2 != 5) {
                if (bArr[(i2 * TsExtractor.TS_PACKET_SIZE) + i] != (byte) 71) {
                    i++;
                } else {
                    i2++;
                }
            }
            cdVar.b(i);
            return true;
        }
        return false;
    }

    public void a(ce ceVar) {
        this.l = ceVar;
        ceVar.a(cj.f);
    }

    public void b() {
        this.g.a();
        this.i.a();
        this.k.clear();
        f();
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00d5  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00e2  */
    public int a(com.google.ads.interactivemedia.v3.internal.cd r10, com.google.ads.interactivemedia.v3.internal.ch r11) throws java.io.IOException, java.lang.InterruptedException {
        /*
        r9 = this;
        r11 = r9.i;
        r11 = r11.a;
        r0 = r9.i;
        r0 = r0.d();
        r0 = 940 - r0;
        r1 = 188; // 0xbc float:2.63E-43 double:9.3E-322;
        r2 = 0;
        if (r0 >= r1) goto L_0x0027;
    L_0x0011:
        r0 = r9.i;
        r0 = r0.b();
        if (r0 <= 0) goto L_0x0022;
    L_0x0019:
        r3 = r9.i;
        r3 = r3.d();
        java.lang.System.arraycopy(r11, r3, r11, r2, r0);
    L_0x0022:
        r3 = r9.i;
        r3.a(r11, r0);
    L_0x0027:
        r0 = r9.i;
        r0 = r0.b();
        if (r0 >= r1) goto L_0x0046;
    L_0x002f:
        r0 = r9.i;
        r0 = r0.c();
        r3 = 940 - r0;
        r3 = r10.a(r11, r0, r3);
        r4 = -1;
        if (r3 != r4) goto L_0x003f;
    L_0x003e:
        return r4;
    L_0x003f:
        r4 = r9.i;
        r0 = r0 + r3;
        r4.b(r0);
        goto L_0x0027;
    L_0x0046:
        r10 = r9.i;
        r10 = r10.c();
        r0 = r9.i;
        r0 = r0.d();
    L_0x0052:
        if (r0 >= r10) goto L_0x005d;
    L_0x0054:
        r3 = r11[r0];
        r4 = 71;
        if (r3 == r4) goto L_0x005d;
    L_0x005a:
        r0 = r0 + 1;
        goto L_0x0052;
    L_0x005d:
        r11 = r9.i;
        r11.c(r0);
        r0 = r0 + r1;
        if (r0 <= r10) goto L_0x0066;
    L_0x0065:
        return r2;
    L_0x0066:
        r11 = r9.i;
        r1 = 1;
        r11.d(r1);
        r11 = r9.i;
        r3 = r9.j;
        r4 = 3;
        r11.a(r3, r4);
        r11 = r9.j;
        r11 = r11.b();
        if (r11 == 0) goto L_0x0082;
    L_0x007c:
        r10 = r9.i;
        r10.c(r0);
        return r2;
    L_0x0082:
        r11 = r9.j;
        r11 = r11.b();
        r3 = r9.j;
        r3.b(r1);
        r3 = r9.j;
        r4 = 13;
        r3 = r3.c(r4);
        r4 = r9.j;
        r5 = 2;
        r4.b(r5);
        r4 = r9.j;
        r4 = r4.b();
        r5 = r9.j;
        r5 = r5.b();
        r6 = r9.j;
        r7 = 4;
        r6 = r6.c(r7);
        r7 = r9.h;
        r7 = r7 & 16;
        if (r7 != 0) goto L_0x00d2;
    L_0x00b4:
        r7 = r9.k;
        r8 = r6 + -1;
        r7 = r7.get(r3, r8);
        r8 = r9.k;
        r8.put(r3, r6);
        if (r7 != r6) goto L_0x00cb;
    L_0x00c3:
        if (r5 == 0) goto L_0x00d2;
    L_0x00c5:
        r10 = r9.i;
        r10.c(r0);
        return r2;
    L_0x00cb:
        r7 = r7 + r1;
        r7 = r7 % 16;
        if (r6 == r7) goto L_0x00d2;
    L_0x00d0:
        r6 = r1;
        goto L_0x00d3;
    L_0x00d2:
        r6 = r2;
    L_0x00d3:
        if (r4 == 0) goto L_0x00e0;
    L_0x00d5:
        r4 = r9.i;
        r4 = r4.f();
        r7 = r9.i;
        r7.d(r4);
    L_0x00e0:
        if (r5 == 0) goto L_0x010f;
    L_0x00e2:
        r4 = r9.a;
        r3 = r4.get(r3);
        r3 = (com.google.ads.interactivemedia.v3.internal.ee.d) r3;
        if (r3 == 0) goto L_0x010f;
    L_0x00ec:
        if (r6 == 0) goto L_0x00f1;
    L_0x00ee:
        r3.a();
    L_0x00f1:
        r4 = r9.i;
        r4.b(r0);
        r4 = r9.i;
        r5 = r9.l;
        r3.a(r4, r11, r5);
        r11 = r9.i;
        r11 = r11.d();
        if (r11 > r0) goto L_0x0106;
    L_0x0105:
        goto L_0x0107;
    L_0x0106:
        r1 = r2;
    L_0x0107:
        com.google.ads.interactivemedia.v3.internal.fe.b(r1);
        r11 = r9.i;
        r11.b(r10);
    L_0x010f:
        r10 = r9.i;
        r10.c(r0);
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.ee.a(com.google.ads.interactivemedia.v3.internal.cd, com.google.ads.interactivemedia.v3.internal.ch):int");
    }

    private void f() {
        this.b.clear();
        this.a.clear();
        this.a.put(0, new a());
        this.c = null;
        this.n = 8192;
    }
}
