package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.internal.bh.b;
import com.google.ads.interactivemedia.v3.internal.bn.a;
import java.io.IOException;
import java.util.Arrays;

public abstract class bo extends bq {
    private final a[] a;
    private int[] b;
    private int[] c;
    private a d;
    private int e;
    private long f;

    public bo(bn... bnVarArr) {
        this.a = new a[bnVarArr.length];
        for (int i = 0; i < bnVarArr.length; i++) {
            this.a[i] = bnVarArr[i].a();
        }
    }

    public abstract void a(long j) throws az;

    public abstract void a(long j, long j2, boolean z) throws az;

    public abstract boolean a(bj bjVar) throws b;

    /* Access modifiers changed, original: protected */
    public long e(long j) {
        return j;
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean c(long j) throws az {
        int i;
        boolean z = false;
        int i2 = 1;
        for (a a : this.a) {
            i2 &= a.a(j);
        }
        if (i2 == 0) {
            return false;
        }
        i = 0;
        i2 = i;
        while (i < this.a.length) {
            i2 += this.a[i].c();
            i++;
        }
        int[] iArr = new int[i2];
        int[] iArr2 = new int[i2];
        int length = this.a.length;
        long j2 = 0;
        int i3 = 0;
        int i4 = i3;
        while (i3 < length) {
            a aVar = this.a[i3];
            int c = aVar.c();
            long j3 = j2;
            int i5 = i4;
            i4 = z;
            while (i4 < c) {
                bj a2 = aVar.a(i4);
                try {
                    if (a(a2)) {
                        iArr[i5] = i3;
                        iArr2[i5] = i4;
                        i5++;
                        if (j3 != -1) {
                            long j4 = a2.e;
                            if (j4 == -1) {
                                j3 = -1;
                            } else if (j4 != -2) {
                                j3 = Math.max(j3, j4);
                            }
                        }
                    }
                    i4++;
                } catch (b e) {
                    throw new az(e);
                }
            }
            i3++;
            i4 = i5;
            j2 = j3;
            z = false;
        }
        this.f = j2;
        this.b = Arrays.copyOf(iArr, i4);
        this.c = Arrays.copyOf(iArr2, i4);
        return true;
    }

    /* Access modifiers changed, original: protected */
    public void a(int i, long j, boolean z) throws az {
        j = e(j);
        this.d = this.a[this.b[i]];
        this.e = this.c[i];
        this.d.a(this.e, j);
        a(j);
    }

    /* Access modifiers changed, original: protected */
    public void d(long j) throws az {
        j = e(j);
        this.d.b(j);
        b(j);
    }

    /* Access modifiers changed, original: protected|final */
    public final void b(long j, long j2) throws az {
        j = e(j);
        a(b(j), j2, this.d.b(this.e, j));
    }

    /* Access modifiers changed, original: protected */
    public long q() {
        return this.d.d();
    }

    /* Access modifiers changed, original: protected */
    public long r() {
        return this.f;
    }

    /* Access modifiers changed, original: protected */
    public void s() throws az {
        if (this.d != null) {
            a(this.d);
            return;
        }
        for (a a : this.a) {
            a(a);
        }
    }

    /* Access modifiers changed, original: protected */
    public void g() throws az {
        this.d.c(this.e);
        this.d = null;
    }

    /* Access modifiers changed, original: protected */
    public void t() throws az {
        for (a e : this.a) {
            e.e();
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final int u() {
        return this.c.length;
    }

    /* Access modifiers changed, original: protected|final */
    public final bj b(int i) {
        return this.a[this.b[i]].a(this.c[i]);
    }

    /* Access modifiers changed, original: protected|final */
    public final int a(long j, bk bkVar, bm bmVar) {
        return this.d.a(this.e, j, bkVar, bmVar);
    }

    private long b(long j) throws az {
        long b = this.d.b(this.e);
        if (b == Long.MIN_VALUE) {
            return j;
        }
        a(b);
        return b;
    }

    private void a(a aVar) throws az {
        try {
            aVar.b();
        } catch (IOException e) {
            throw new az(e);
        }
    }
}
