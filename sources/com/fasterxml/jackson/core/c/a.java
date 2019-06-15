package com.fasterxml.jackson.core.c;

import android.support.v4.view.InputDeviceCompat;
import com.comscore.utils.Constants;
import com.fasterxml.jackson.core.util.InternCache;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public final class a {
    protected final a a;
    protected final AtomicReference<b> b;
    protected final boolean c;
    protected int d;
    protected int e;
    protected int f;
    protected int[] g;
    protected c[] h;
    protected a[] i;
    protected int j;
    protected int k;
    private final int l;
    private transient boolean m;
    private boolean n;
    private boolean o;
    private boolean p;

    static final class a {
        protected final c a;
        protected final a b;
        private final int c;

        a(c cVar, a aVar) {
            this.a = cVar;
            this.b = aVar;
            int i = 1;
            if (aVar != null) {
                i = 1 + aVar.c;
            }
            this.c = i;
        }

        public int a() {
            return this.c;
        }

        public c a(int i, int i2, int i3) {
            if (this.a.hashCode() == i && this.a.a(i2, i3)) {
                return this.a;
            }
            for (a aVar = this.b; aVar != null; aVar = aVar.b) {
                c cVar = aVar.a;
                if (cVar.hashCode() == i && cVar.a(i2, i3)) {
                    return cVar;
                }
            }
            return null;
        }

        public c a(int i, int[] iArr, int i2) {
            if (this.a.hashCode() == i && this.a.a(iArr, i2)) {
                return this.a;
            }
            for (a aVar = this.b; aVar != null; aVar = aVar.b) {
                c cVar = aVar.a;
                if (cVar.hashCode() == i && cVar.a(iArr, i2)) {
                    return cVar;
                }
            }
            return null;
        }
    }

    private static final class b {
        public final int a;
        public final int b;
        public final int[] c;
        public final c[] d;
        public final a[] e;
        public final int f;
        public final int g;
        public final int h;

        public b(int i, int i2, int[] iArr, c[] cVarArr, a[] aVarArr, int i3, int i4, int i5) {
            this.a = i;
            this.b = i2;
            this.c = iArr;
            this.d = cVarArr;
            this.e = aVarArr;
            this.f = i3;
            this.g = i4;
            this.h = i5;
        }

        public b(a aVar) {
            this.a = aVar.d;
            this.b = aVar.f;
            this.c = aVar.g;
            this.d = aVar.h;
            this.e = aVar.i;
            this.f = aVar.j;
            this.g = aVar.k;
            this.h = aVar.e;
        }
    }

    private a(int i, boolean z, int i2) {
        this.a = null;
        this.l = i2;
        this.c = z;
        int i3 = 16;
        if (i >= 16) {
            if (((i - 1) & i) != 0) {
                while (i3 < i) {
                    i3 += i3;
                }
            } else {
                i3 = i;
            }
        }
        this.b = new AtomicReference(e(i3));
    }

    private a(a aVar, boolean z, int i, b bVar) {
        this.a = aVar;
        this.l = i;
        this.c = z;
        this.b = null;
        this.d = bVar.a;
        this.f = bVar.b;
        this.g = bVar.c;
        this.h = bVar.d;
        this.i = bVar.e;
        this.j = bVar.f;
        this.k = bVar.g;
        this.e = bVar.h;
        this.m = false;
        this.n = true;
        this.o = true;
        this.p = true;
    }

    private b e(int i) {
        return new b(0, i - 1, new int[i], new c[i], null, 0, 0, 0);
    }

    public static a a() {
        long currentTimeMillis = System.currentTimeMillis();
        return a((((int) currentTimeMillis) + ((int) (currentTimeMillis >>> 32))) | 1);
    }

    protected static a a(int i) {
        return new a(64, true, i);
    }

    public a a(boolean z, boolean z2) {
        return new a(this, z2, this.l, (b) this.b.get());
    }

    public void b() {
        if (this.a != null && c()) {
            this.a.a(new b(this));
            this.n = true;
            this.o = true;
            this.p = true;
        }
    }

    private void a(b bVar) {
        int i = bVar.a;
        b bVar2 = (b) this.b.get();
        if (i > bVar2.a) {
            Object bVar3;
            if (i > Constants.EVENTS_LIMIT_PER_DAY || bVar3.h > 63) {
                bVar3 = e(64);
            }
            this.b.compareAndSet(bVar2, bVar3);
        }
    }

    public boolean c() {
        return this.n ^ 1;
    }

    public static c d() {
        return d.b();
    }

    public c b(int i) {
        int c = c(i);
        int i2 = this.f & c;
        int i3 = this.g[i2];
        if ((((i3 >> 8) ^ c) << 8) == 0) {
            c cVar = this.h[i2];
            if (cVar == null) {
                return null;
            }
            if (cVar.a(i)) {
                return cVar;
            }
        } else if (i3 == 0) {
            return null;
        }
        i2 = i3 & 255;
        if (i2 > 0) {
            a aVar = this.i[i2 - 1];
            if (aVar != null) {
                return aVar.a(c, i, 0);
            }
        }
        return null;
    }

    public c a(int i, int i2) {
        int c = i2 == 0 ? c(i) : b(i, i2);
        int i3 = this.f & c;
        int i4 = this.g[i3];
        if ((((i4 >> 8) ^ c) << 8) == 0) {
            c cVar = this.h[i3];
            if (cVar == null) {
                return null;
            }
            if (cVar.a(i, i2)) {
                return cVar;
            }
        } else if (i4 == 0) {
            return null;
        }
        i3 = i4 & 255;
        if (i3 > 0) {
            a aVar = this.i[i3 - 1];
            if (aVar != null) {
                return aVar.a(c, i, i2);
            }
        }
        return null;
    }

    public c a(int[] iArr, int i) {
        int i2;
        int i3;
        if (i < 3) {
            i2 = 0;
            i3 = iArr[0];
            if (i >= 2) {
                i2 = iArr[1];
            }
            return a(i3, i2);
        }
        i2 = b(iArr, i);
        i3 = this.f & i2;
        int i4 = this.g[i3];
        if ((((i4 >> 8) ^ i2) << 8) == 0) {
            c cVar = this.h[i3];
            if (cVar == null || cVar.a(iArr, i)) {
                return cVar;
            }
        } else if (i4 == 0) {
            return null;
        }
        i3 = i4 & 255;
        if (i3 > 0) {
            a aVar = this.i[i3 - 1];
            if (aVar != null) {
                return aVar.a(i2, iArr, i);
            }
        }
        return null;
    }

    public c a(String str, int[] iArr, int i) {
        if (this.c) {
            str = InternCache.a.a(str);
        }
        int c = i < 3 ? i == 1 ? c(iArr[0]) : b(iArr[0], iArr[1]) : b(iArr, i);
        c a = a(c, str, iArr, i);
        a(c, a);
        return a;
    }

    public int c(int i) {
        i ^= this.l;
        i += i >>> 15;
        return i ^ (i >>> 9);
    }

    public int b(int i, int i2) {
        i = ((i ^ (i >>> 15)) + (i2 * 33)) ^ this.l;
        return i + (i >>> 7);
    }

    public int b(int[] iArr, int i) {
        int i2 = 3;
        if (i < 3) {
            throw new IllegalArgumentException();
        }
        int i3 = iArr[0] ^ this.l;
        i3 = (((i3 + (i3 >>> 9)) * 33) + iArr[1]) * 65599;
        i3 = (i3 + (i3 >>> 15)) ^ iArr[2];
        i3 += i3 >>> 17;
        while (i2 < i) {
            i3 = (i3 * 31) ^ iArr[i2];
            i3 += i3 >>> 3;
            i3 ^= i3 << 7;
            i2++;
        }
        i3 += i3 >>> 15;
        return (i3 << 9) ^ i3;
    }

    private void a(int i, c cVar) {
        if (this.n) {
            h();
        }
        if (this.m) {
            e();
        }
        this.d++;
        int i2 = this.f & i;
        if (this.h[i2] == null) {
            this.g[i2] = i << 8;
            if (this.o) {
                j();
            }
            this.h[i2] = cVar;
        } else {
            if (this.p) {
                i();
            }
            this.j++;
            i = this.g[i2];
            int i3 = i & 255;
            if (i3 == 0) {
                if (this.k <= 254) {
                    i3 = this.k;
                    this.k++;
                    if (i3 >= this.i.length) {
                        k();
                    }
                } else {
                    i3 = g();
                }
                this.g[i2] = (i & InputDeviceCompat.SOURCE_ANY) | (i3 + 1);
            } else {
                i3--;
            }
            a aVar = new a(cVar, this.i[i3]);
            this.i[i3] = aVar;
            this.e = Math.max(aVar.a(), this.e);
            if (this.e > 255) {
                d(255);
            }
        }
        i = this.g.length;
        if (this.d > (i >> 1)) {
            int i4 = i >> 2;
            if (this.d > i - i4) {
                this.m = true;
            } else if (this.j >= i4) {
                this.m = true;
            }
        }
    }

    private void e() {
        int i = 0;
        this.m = false;
        this.o = false;
        int length = this.g.length;
        int i2 = length + length;
        if (i2 > 65536) {
            f();
            return;
        }
        int i3;
        this.g = new int[i2];
        this.f = i2 - 1;
        c[] cVarArr = this.h;
        this.h = new c[i2];
        i2 = 0;
        int i4 = i2;
        while (i2 < length) {
            c cVar = cVarArr[i2];
            if (cVar != null) {
                i4++;
                int hashCode = cVar.hashCode();
                i3 = this.f & hashCode;
                this.h[i3] = cVar;
                this.g[i3] = hashCode << 8;
            }
            i2++;
        }
        length = this.k;
        if (length == 0) {
            this.e = 0;
            return;
        }
        this.j = 0;
        this.k = 0;
        this.p = false;
        a[] aVarArr = this.i;
        this.i = new a[aVarArr.length];
        i2 = 0;
        while (i < length) {
            for (a aVar = aVarArr[i]; aVar != null; aVar = aVar.b) {
                i4++;
                c cVar2 = aVar.a;
                i3 = cVar2.hashCode();
                int i5 = this.f & i3;
                int i6 = this.g[i5];
                if (this.h[i5] == null) {
                    this.g[i5] = i3 << 8;
                    this.h[i5] = cVar2;
                } else {
                    this.j++;
                    i3 = i6 & 255;
                    if (i3 == 0) {
                        if (this.k <= 254) {
                            i3 = this.k;
                            this.k++;
                            if (i3 >= this.i.length) {
                                k();
                            }
                        } else {
                            i3 = g();
                        }
                        this.g[i5] = (i6 & InputDeviceCompat.SOURCE_ANY) | (i3 + 1);
                    } else {
                        i3--;
                    }
                    a aVar2 = new a(cVar2, this.i[i3]);
                    this.i[i3] = aVar2;
                    i2 = Math.max(i2, aVar2.a());
                }
            }
            i++;
        }
        this.e = i2;
        if (i4 != this.d) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Internal error: count after rehash ");
            stringBuilder.append(i4);
            stringBuilder.append("; should be ");
            stringBuilder.append(this.d);
            throw new RuntimeException(stringBuilder.toString());
        }
    }

    private void f() {
        this.d = 0;
        this.e = 0;
        Arrays.fill(this.g, 0);
        Arrays.fill(this.h, null);
        Arrays.fill(this.i, null);
        this.j = 0;
        this.k = 0;
    }

    private int g() {
        a[] aVarArr = this.i;
        int i = this.k;
        int i2 = Integer.MAX_VALUE;
        int i3 = -1;
        for (int i4 = 0; i4 < i; i4++) {
            int a = aVarArr[i4].a();
            if (a < i2) {
                if (a == 1) {
                    return i4;
                }
                i3 = i4;
                i2 = a;
            }
        }
        return i3;
    }

    private void h() {
        int[] iArr = this.g;
        int length = this.g.length;
        this.g = new int[length];
        System.arraycopy(iArr, 0, this.g, 0, length);
        this.n = false;
    }

    private void i() {
        a[] aVarArr = this.i;
        if (aVarArr == null) {
            this.i = new a[32];
        } else {
            int length = aVarArr.length;
            this.i = new a[length];
            System.arraycopy(aVarArr, 0, this.i, 0, length);
        }
        this.p = false;
    }

    private void j() {
        c[] cVarArr = this.h;
        int length = cVarArr.length;
        this.h = new c[length];
        System.arraycopy(cVarArr, 0, this.h, 0, length);
        this.o = false;
    }

    private void k() {
        a[] aVarArr = this.i;
        int length = aVarArr.length;
        this.i = new a[(length + length)];
        System.arraycopy(aVarArr, 0, this.i, 0, length);
    }

    private static c a(int i, String str, int[] iArr, int i2) {
        int i3 = 0;
        if (i2 < 4) {
            switch (i2) {
                case 1:
                    return new d(str, i, iArr[0]);
                case 2:
                    return new e(str, i, iArr[0], iArr[1]);
                case 3:
                    return new f(str, i, iArr[0], iArr[1], iArr[2]);
            }
        }
        int[] iArr2 = new int[i2];
        while (i3 < i2) {
            iArr2[i3] = iArr[i3];
            i3++;
        }
        return new g(str, i, iArr2, i2);
    }

    /* Access modifiers changed, original: protected */
    public void d(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Longest collision chain in symbol table (of size ");
        stringBuilder.append(this.d);
        stringBuilder.append(") now exceeds maximum, ");
        stringBuilder.append(i);
        stringBuilder.append(" -- suspect a DoS attack based on hash collisions");
        throw new IllegalStateException(stringBuilder.toString());
    }
}
