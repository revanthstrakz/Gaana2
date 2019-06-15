package com.google.ads.interactivemedia.v3.internal;

final class cu implements a {
    private final long a;
    private final long b;
    private final long c;
    private final long[] d;
    private final long e;
    private final int g;

    public static cu a(fm fmVar, fp fpVar, long j, long j2) {
        fm fmVar2 = fmVar;
        int i = fmVar2.g;
        int i2 = fmVar2.d;
        long j3 = j + ((long) fmVar2.c);
        int m = fpVar.m();
        if ((m & 1) == 1) {
            int s = fpVar.s();
            if (s != 0) {
                long a = ft.a((long) s, ((long) i) * 1000000, (long) i2);
                if ((m & 6) != 6) {
                    return new cu(j3, a, j2);
                }
                long s2 = (long) fpVar.s();
                fpVar.d(1);
                long[] jArr = new long[99];
                for (int i3 = 0; i3 < 99; i3++) {
                    jArr[i3] = (long) fpVar.f();
                }
                return new cu(j3, a, j2, jArr, s2, fmVar2.c);
            }
        }
        return null;
    }

    private cu(long j, long j2, long j3) {
        this(j, j2, j3, null, 0, 0);
    }

    private cu(long j, long j2, long j3, long[] jArr, long j4, int i) {
        this.a = j;
        this.b = j2;
        this.c = j3;
        this.d = jArr;
        this.e = j4;
        this.g = i;
    }

    public boolean a() {
        return this.d != null;
    }

    public long b(long j) {
        if (!a()) {
            return this.a;
        }
        long j2;
        float f = (((float) j) * 100.0f) / ((float) this.b);
        float f2 = 0.0f;
        float f3 = 256.0f;
        if (f > 0.0f) {
            if (f >= 100.0f) {
                f2 = 256.0f;
            } else {
                int i = (int) f;
                if (i != 0) {
                    f2 = (float) this.d[i - 1];
                }
                if (i < 99) {
                    f3 = (float) this.d[i];
                }
                f2 += (f3 - f2) * (f - ((float) i));
            }
        }
        long round = Math.round((0.00390625d * ((double) f2)) * ((double) this.e)) + this.a;
        if (this.c != -1) {
            j2 = this.c - 1;
        } else {
            j2 = ((this.a - ((long) this.g)) + this.e) - 1;
        }
        return Math.min(round, j2);
    }

    public long a(long j) {
        long j2 = 0;
        if (!a() || j < this.a) {
            return 0;
        }
        long j3;
        long j4;
        double d = (256.0d * ((double) (j - this.a))) / ((double) this.e);
        int a = ft.a(this.d, (long) d, true, false) + 1;
        long a2 = a(a);
        if (a == 0) {
            j3 = 0;
        } else {
            j3 = this.d[a - 1];
        }
        if (a == 99) {
            j4 = 256;
        } else {
            j4 = this.d[a];
        }
        j = a(a + 1);
        if (j4 != j3) {
            j2 = (long) ((((double) (j - a2)) * (d - ((double) j3))) / ((double) (j4 - j3)));
        }
        return a2 + j2;
    }

    public long b() {
        return this.b;
    }

    private long a(int i) {
        return (this.b * ((long) i)) / 100;
    }
}
