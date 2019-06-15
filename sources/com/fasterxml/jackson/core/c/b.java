package com.fasterxml.jackson.core.c;

import com.fasterxml.jackson.core.util.InternCache;
import java.util.Arrays;

public final class b {
    static final b a = new b();
    protected b b;
    protected final boolean c;
    protected final boolean d;
    protected String[] e;
    protected a[] f;
    protected int g;
    protected int h;
    protected int i;
    protected int j;
    protected boolean k;
    private final int l;

    static final class a {
        private final String a;
        private final a b;
        private final int c;

        public a(String str, a aVar) {
            this.a = str;
            this.b = aVar;
            int i = 1;
            if (aVar != null) {
                i = 1 + aVar.c;
            }
            this.c = i;
        }

        public String a() {
            return this.a;
        }

        public a b() {
            return this.b;
        }

        public int c() {
            return this.c;
        }

        public String a(char[] cArr, int i, int i2) {
            String str = this.a;
            a aVar = this.b;
            while (true) {
                if (str.length() == i2) {
                    int i3 = 0;
                    while (str.charAt(i3) == cArr[i + i3]) {
                        i3++;
                        if (i3 >= i2) {
                            break;
                        }
                    }
                    if (i3 == i2) {
                        return str;
                    }
                }
                if (aVar == null) {
                    return null;
                }
                str = aVar.a();
                aVar = aVar.b();
            }
        }
    }

    private static int e(int i) {
        return i - (i >> 2);
    }

    public static b a() {
        long currentTimeMillis = System.currentTimeMillis();
        return a((((int) currentTimeMillis) + ((int) (currentTimeMillis >>> 32))) | 1);
    }

    protected static b a(int i) {
        return a.f(i);
    }

    private b() {
        this.d = true;
        this.c = true;
        this.k = true;
        this.l = 0;
        this.j = 0;
        d(64);
    }

    private void d(int i) {
        this.e = new String[i];
        this.f = new a[(i >> 1)];
        this.i = i - 1;
        this.g = 0;
        this.j = 0;
        this.h = e(i);
    }

    private b(b bVar, boolean z, boolean z2, String[] strArr, a[] aVarArr, int i, int i2, int i3) {
        this.b = bVar;
        this.d = z;
        this.c = z2;
        this.e = strArr;
        this.f = aVarArr;
        this.g = i;
        this.l = i2;
        int length = strArr.length;
        this.h = e(length);
        this.i = length - 1;
        this.j = i3;
        this.k = false;
    }

    public b a(boolean z, boolean z2) {
        String[] strArr;
        a[] aVarArr;
        int i;
        int i2;
        int i3;
        synchronized (this) {
            strArr = this.e;
            aVarArr = this.f;
            i = this.g;
            i2 = this.l;
            i3 = this.j;
        }
        return new b(this, z, z2, strArr, aVarArr, i, i2, i3);
    }

    private b f(int i) {
        return new b(null, true, true, this.e, this.f, this.g, i, this.j);
    }

    private void a(b bVar) {
        if (bVar.c() > 12000 || bVar.j > 63) {
            synchronized (this) {
                d(64);
                this.k = false;
            }
        } else if (bVar.c() > c()) {
            synchronized (this) {
                this.e = bVar.e;
                this.f = bVar.f;
                this.g = bVar.g;
                this.h = bVar.h;
                this.i = bVar.i;
                this.j = bVar.j;
                this.k = false;
            }
        }
    }

    public void b() {
        if (d() && this.b != null) {
            this.b.a(this);
            this.k = false;
        }
    }

    public int c() {
        return this.g;
    }

    public boolean d() {
        return this.k;
    }

    public int e() {
        return this.l;
    }

    public String a(char[] cArr, int i, int i2, int i3) {
        if (i2 < 1) {
            return "";
        }
        if (!this.d) {
            return new String(cArr, i, i2);
        }
        i3 = b(i3);
        String str = this.e[i3];
        if (str != null) {
            if (str.length() == i2) {
                int i4 = 0;
                while (str.charAt(i4) == cArr[i + i4]) {
                    i4++;
                    if (i4 >= i2) {
                        break;
                    }
                }
                if (i4 == i2) {
                    return str;
                }
            }
            a aVar = this.f[i3 >> 1];
            if (aVar != null) {
                str = aVar.a(cArr, i, i2);
                if (str != null) {
                    return str;
                }
            }
        }
        if (!this.k) {
            f();
            this.k = true;
        } else if (this.g >= this.h) {
            g();
            i3 = b(a(cArr, i, i2));
        }
        str = new String(cArr, i, i2);
        if (this.c) {
            str = InternCache.a.a(str);
        }
        this.g++;
        if (this.e[i3] == null) {
            this.e[i3] = str;
        } else {
            int i5 = i3 >> 1;
            a aVar2 = new a(str, this.f[i5]);
            this.f[i5] = aVar2;
            this.j = Math.max(aVar2.c(), this.j);
            if (this.j > 255) {
                c(255);
            }
        }
        return str;
    }

    public int b(int i) {
        return (i + (i >>> 15)) & this.i;
    }

    public int a(char[] cArr, int i, int i2) {
        i = this.l;
        for (int i3 = 0; i3 < i2; i3++) {
            i = (i * 33) + cArr[i3];
        }
        return i == 0 ? 1 : i;
    }

    public int a(String str) {
        int length = str.length();
        int i = this.l;
        for (int i2 = 0; i2 < length; i2++) {
            i = (i * 33) + str.charAt(i2);
        }
        return i == 0 ? 1 : i;
    }

    private void f() {
        String[] strArr = this.e;
        int length = strArr.length;
        this.e = new String[length];
        System.arraycopy(strArr, 0, this.e, 0, length);
        a[] aVarArr = this.f;
        length = aVarArr.length;
        this.f = new a[length];
        System.arraycopy(aVarArr, 0, this.f, 0, length);
    }

    private void g() {
        int i = 0;
        int length = this.e.length;
        int i2 = length + length;
        if (i2 > 65536) {
            this.g = 0;
            Arrays.fill(this.e, null);
            Arrays.fill(this.f, null);
            this.k = true;
            return;
        }
        String[] strArr = this.e;
        a[] aVarArr = this.f;
        this.e = new String[i2];
        this.f = new a[(i2 >> 1)];
        this.i = i2 - 1;
        this.h = e(i2);
        i2 = 0;
        int i3 = i2;
        int i4 = i3;
        while (i2 < length) {
            String str = strArr[i2];
            if (str != null) {
                i3++;
                int b = b(a(str));
                if (this.e[b] == null) {
                    this.e[b] = str;
                } else {
                    b >>= 1;
                    a aVar = new a(str, this.f[b]);
                    this.f[b] = aVar;
                    i4 = Math.max(i4, aVar.c());
                }
            }
            i2++;
        }
        length >>= 1;
        while (i < length) {
            for (a aVar2 = aVarArr[i]; aVar2 != null; aVar2 = aVar2.b()) {
                i3++;
                String a = aVar2.a();
                i2 = b(a(a));
                if (this.e[i2] == null) {
                    this.e[i2] = a;
                } else {
                    i2 >>= 1;
                    a aVar3 = new a(a, this.f[i2]);
                    this.f[i2] = aVar3;
                    i4 = Math.max(i4, aVar3.c());
                }
            }
            i++;
        }
        this.j = i4;
        if (i3 != this.g) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Internal error on SymbolTable.rehash(): had ");
            stringBuilder.append(this.g);
            stringBuilder.append(" entries; now have ");
            stringBuilder.append(i3);
            stringBuilder.append(".");
            throw new Error(stringBuilder.toString());
        }
    }

    /* Access modifiers changed, original: protected */
    public void c(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Longest collision chain in symbol table (of size ");
        stringBuilder.append(this.g);
        stringBuilder.append(") now exceeds maximum, ");
        stringBuilder.append(i);
        stringBuilder.append(" -- suspect a DoS attack based on hash collisions");
        throw new IllegalStateException(stringBuilder.toString());
    }
}
