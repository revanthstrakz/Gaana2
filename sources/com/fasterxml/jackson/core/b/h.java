package com.fasterxml.jackson.core.b;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.d;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.b;
import com.fasterxml.jackson.core.io.c;
import com.fasterxml.jackson.core.io.g;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;

public final class h extends b {
    protected static final char[] m = b.g();
    protected final Writer n;
    protected char[] o;
    protected int p = 0;
    protected int q = 0;
    protected int r;
    protected char[] s;
    protected d t;

    public h(c cVar, int i, com.fasterxml.jackson.core.b bVar, Writer writer) {
        super(cVar, i, bVar);
        this.n = writer;
        this.o = cVar.h();
        this.r = this.o.length;
    }

    public void a(String str) throws IOException, JsonGenerationException {
        int a = this.e.a(str);
        if (a == 4) {
            f("Can not write a field name, expecting a value");
        }
        boolean z = true;
        if (a != 1) {
            z = false;
        }
        a(str, z);
    }

    public void c() throws IOException, JsonGenerationException {
        e("start an array");
        this.e = this.e.h();
        if (this.a != null) {
            this.a.e(this);
            return;
        }
        if (this.q >= this.r) {
            k();
        }
        char[] cArr = this.o;
        int i = this.q;
        this.q = i + 1;
        cArr[i] = '[';
    }

    public void d() throws IOException, JsonGenerationException {
        if (!this.e.a()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Current context not an ARRAY but ");
            stringBuilder.append(this.e.d());
            f(stringBuilder.toString());
        }
        if (this.a != null) {
            this.a.b(this, this.e.e());
        } else {
            if (this.q >= this.r) {
                k();
            }
            char[] cArr = this.o;
            int i = this.q;
            this.q = i + 1;
            cArr[i] = ']';
        }
        this.e = this.e.j();
    }

    public void e() throws IOException, JsonGenerationException {
        e("start an object");
        this.e = this.e.i();
        if (this.a != null) {
            this.a.b(this);
            return;
        }
        if (this.q >= this.r) {
            k();
        }
        char[] cArr = this.o;
        int i = this.q;
        this.q = i + 1;
        cArr[i] = '{';
    }

    public void f() throws IOException, JsonGenerationException {
        if (!this.e.c()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Current context not an object but ");
            stringBuilder.append(this.e.d());
            f(stringBuilder.toString());
        }
        if (this.a != null) {
            this.a.a(this, this.e.e());
        } else {
            if (this.q >= this.r) {
                k();
            }
            char[] cArr = this.o;
            int i = this.q;
            this.q = i + 1;
            cArr[i] = '}';
        }
        this.e = this.e.j();
    }

    /* Access modifiers changed, original: protected */
    public void a(String str, boolean z) throws IOException, JsonGenerationException {
        if (this.a != null) {
            b(str, z);
            return;
        }
        char[] cArr;
        int i;
        if (this.q + 1 >= this.r) {
            k();
        }
        if (z) {
            cArr = this.o;
            i = this.q;
            this.q = i + 1;
            cArr[i] = ',';
        }
        if (a(Feature.QUOTE_FIELD_NAMES)) {
            cArr = this.o;
            i = this.q;
            this.q = i + 1;
            cArr[i] = '\"';
            h(str);
            if (this.q >= this.r) {
                k();
            }
            char[] cArr2 = this.o;
            int i2 = this.q;
            this.q = i2 + 1;
            cArr2[i2] = '\"';
            return;
        }
        h(str);
    }

    /* Access modifiers changed, original: protected */
    public void b(String str, boolean z) throws IOException, JsonGenerationException {
        if (z) {
            this.a.c(this);
        } else {
            this.a.h(this);
        }
        if (a(Feature.QUOTE_FIELD_NAMES)) {
            if (this.q >= this.r) {
                k();
            }
            char[] cArr = this.o;
            int i = this.q;
            this.q = i + 1;
            cArr[i] = '\"';
            h(str);
            if (this.q >= this.r) {
                k();
            }
            char[] cArr2 = this.o;
            int i2 = this.q;
            this.q = i2 + 1;
            cArr2[i2] = '\"';
            return;
        }
        h(str);
    }

    public void b(String str) throws IOException, JsonGenerationException {
        e("write text value");
        if (str == null) {
            l();
            return;
        }
        if (this.q >= this.r) {
            k();
        }
        char[] cArr = this.o;
        int i = this.q;
        this.q = i + 1;
        cArr[i] = '\"';
        h(str);
        if (this.q >= this.r) {
            k();
        }
        char[] cArr2 = this.o;
        int i2 = this.q;
        this.q = i2 + 1;
        cArr2[i2] = '\"';
    }

    public void c(String str) throws IOException, JsonGenerationException {
        int length = str.length();
        int i = this.r - this.q;
        if (i == 0) {
            k();
            i = this.r - this.q;
        }
        if (i >= length) {
            str.getChars(0, length, this.o, this.q);
            this.q += length;
            return;
        }
        g(str);
    }

    public void b(d dVar) throws IOException, JsonGenerationException {
        c(dVar.a());
    }

    public void a(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        if (i2 < 32) {
            if (i2 > this.r - this.q) {
                k();
            }
            System.arraycopy(cArr, i, this.o, this.q, i2);
            this.q += i2;
            return;
        }
        k();
        this.n.write(cArr, i, i2);
    }

    public void a(char c) throws IOException, JsonGenerationException {
        if (this.q >= this.r) {
            k();
        }
        char[] cArr = this.o;
        int i = this.q;
        this.q = i + 1;
        cArr[i] = c;
    }

    private void g(String str) throws IOException, JsonGenerationException {
        int i = this.r - this.q;
        str.getChars(0, i, this.o, this.q);
        this.q += i;
        k();
        int length = str.length() - i;
        while (length > this.r) {
            int i2 = this.r;
            int i3 = i + i2;
            str.getChars(i, i3, this.o, 0);
            this.p = 0;
            this.q = i2;
            k();
            length -= i2;
            i = i3;
        }
        str.getChars(i, i + length, this.o, 0);
        this.p = 0;
        this.q = length;
    }

    public void b(int i) throws IOException, JsonGenerationException {
        e("write number");
        if (this.d) {
            c(i);
            return;
        }
        if (this.q + 11 >= this.r) {
            k();
        }
        this.q = g.a(i, this.o, this.q);
    }

    private void c(int i) throws IOException {
        if (this.q + 13 >= this.r) {
            k();
        }
        char[] cArr = this.o;
        int i2 = this.q;
        this.q = i2 + 1;
        cArr[i2] = '\"';
        this.q = g.a(i, this.o, this.q);
        char[] cArr2 = this.o;
        int i3 = this.q;
        this.q = i3 + 1;
        cArr2[i3] = '\"';
    }

    public void a(long j) throws IOException, JsonGenerationException {
        e("write number");
        if (this.d) {
            b(j);
            return;
        }
        if (this.q + 21 >= this.r) {
            k();
        }
        this.q = g.a(j, this.o, this.q);
    }

    private void b(long j) throws IOException {
        if (this.q + 23 >= this.r) {
            k();
        }
        char[] cArr = this.o;
        int i = this.q;
        this.q = i + 1;
        cArr[i] = '\"';
        this.q = g.a(j, this.o, this.q);
        char[] cArr2 = this.o;
        int i2 = this.q;
        this.q = i2 + 1;
        cArr2[i2] = '\"';
    }

    public void a(BigInteger bigInteger) throws IOException, JsonGenerationException {
        e("write number");
        if (bigInteger == null) {
            l();
        } else if (this.d) {
            a((Object) bigInteger);
        } else {
            c(bigInteger.toString());
        }
    }

    public void a(double d) throws IOException, JsonGenerationException {
        if (this.d || ((Double.isNaN(d) || Double.isInfinite(d)) && a(Feature.QUOTE_NON_NUMERIC_NUMBERS))) {
            b(String.valueOf(d));
            return;
        }
        e("write number");
        c(String.valueOf(d));
    }

    public void a(float f) throws IOException, JsonGenerationException {
        if (this.d || ((Float.isNaN(f) || Float.isInfinite(f)) && a(Feature.QUOTE_NON_NUMERIC_NUMBERS))) {
            b(String.valueOf(f));
            return;
        }
        e("write number");
        c(String.valueOf(f));
    }

    public void a(BigDecimal bigDecimal) throws IOException, JsonGenerationException {
        e("write number");
        if (bigDecimal == null) {
            l();
        } else if (this.d) {
            a((Object) bigDecimal);
        } else {
            c(bigDecimal.toString());
        }
    }

    public void d(String str) throws IOException, JsonGenerationException {
        e("write number");
        if (this.d) {
            a((Object) str);
        } else {
            c(str);
        }
    }

    private void a(Object obj) throws IOException {
        if (this.q >= this.r) {
            k();
        }
        char[] cArr = this.o;
        int i = this.q;
        this.q = i + 1;
        cArr[i] = '\"';
        c(obj.toString());
        if (this.q >= this.r) {
            k();
        }
        char[] cArr2 = this.o;
        int i2 = this.q;
        this.q = i2 + 1;
        cArr2[i2] = '\"';
    }

    public void a(boolean z) throws IOException, JsonGenerationException {
        e("write boolean value");
        if (this.q + 5 >= this.r) {
            k();
        }
        int i = this.q;
        char[] cArr = this.o;
        if (z) {
            cArr[i] = 't';
            i++;
            cArr[i] = 'r';
            i++;
            cArr[i] = 'u';
            i++;
            cArr[i] = 'e';
        } else {
            cArr[i] = 'f';
            i++;
            cArr[i] = 'a';
            i++;
            cArr[i] = 'l';
            i++;
            cArr[i] = 's';
            i++;
            cArr[i] = 'e';
        }
        this.q = i + 1;
    }

    public void g() throws IOException, JsonGenerationException {
        e("write null value");
        l();
    }

    /* Access modifiers changed, original: protected */
    public void e(String str) throws IOException, JsonGenerationException {
        int k = this.e.k();
        if (k == 5) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Can not ");
            stringBuilder.append(str);
            stringBuilder.append(", expecting field name");
            f(stringBuilder.toString());
        }
        if (this.a == null) {
            char c;
            switch (k) {
                case 1:
                    c = ',';
                    break;
                case 2:
                    c = ':';
                    break;
                case 3:
                    if (this.l != null) {
                        c(this.l.a());
                    }
                    return;
                default:
                    return;
            }
            if (this.q >= this.r) {
                k();
            }
            this.o[this.q] = c;
            this.q++;
            return;
        }
        a(str, k);
    }

    /* Access modifiers changed, original: protected */
    public void a(String str, int i) throws IOException, JsonGenerationException {
        switch (i) {
            case 0:
                if (this.e.a()) {
                    this.a.g(this);
                    return;
                } else if (this.e.c()) {
                    this.a.h(this);
                    return;
                } else {
                    return;
                }
            case 1:
                this.a.f(this);
                return;
            case 2:
                this.a.d(this);
                return;
            case 3:
                this.a.a(this);
                return;
            default:
                j();
                return;
        }
    }

    public void flush() throws IOException {
        k();
        if (this.n != null && a(Feature.FLUSH_PASSED_TO_STREAM)) {
            this.n.flush();
        }
    }

    public void close() throws IOException {
        super.close();
        if (this.o != null && a(Feature.AUTO_CLOSE_JSON_CONTENT)) {
            while (true) {
                d h = h();
                if (!h.a()) {
                    if (!h.c()) {
                        break;
                    }
                    f();
                } else {
                    d();
                }
            }
        }
        k();
        if (this.n != null) {
            if (this.h.c() || a(Feature.AUTO_CLOSE_TARGET)) {
                this.n.close();
            } else if (a(Feature.FLUSH_PASSED_TO_STREAM)) {
                this.n.flush();
            }
        }
        i();
    }

    /* Access modifiers changed, original: protected */
    public void i() {
        char[] cArr = this.o;
        if (cArr != null) {
            this.o = null;
            this.h.b(cArr);
        }
    }

    private void h(String str) throws IOException, JsonGenerationException {
        int length = str.length();
        if (length > this.r) {
            i(str);
            return;
        }
        if (this.q + length > this.r) {
            k();
        }
        str.getChars(0, length, this.o, this.q);
        if (this.k != null) {
            f(length);
        } else if (this.j != 0) {
            a(length, this.j);
        } else {
            d(length);
        }
    }

    /* JADX WARNING: Missing block: B:7:0x0016, code skipped:
            r2 = r6.q - r6.p;
     */
    /* JADX WARNING: Missing block: B:8:0x001b, code skipped:
            if (r2 <= 0) goto L_0x0026;
     */
    /* JADX WARNING: Missing block: B:9:0x001d, code skipped:
            r6.n.write(r6.o, r6.p, r2);
     */
    /* JADX WARNING: Missing block: B:10:0x0026, code skipped:
            r2 = r6.o;
            r3 = r6.q;
            r6.q = r3 + 1;
            r2 = r2[r3];
            a(r2, r7[r2]);
     */
    private void d(int r7) throws java.io.IOException, com.fasterxml.jackson.core.JsonGenerationException {
        /*
        r6 = this;
        r0 = r6.q;
        r0 = r0 + r7;
        r7 = r6.i;
        r1 = r7.length;
    L_0x0006:
        r2 = r6.q;
        if (r2 >= r0) goto L_0x003e;
    L_0x000a:
        r2 = r6.o;
        r3 = r6.q;
        r2 = r2[r3];
        if (r2 >= r1) goto L_0x0036;
    L_0x0012:
        r2 = r7[r2];
        if (r2 == 0) goto L_0x0036;
    L_0x0016:
        r2 = r6.q;
        r3 = r6.p;
        r2 = r2 - r3;
        if (r2 <= 0) goto L_0x0026;
    L_0x001d:
        r3 = r6.n;
        r4 = r6.o;
        r5 = r6.p;
        r3.write(r4, r5, r2);
    L_0x0026:
        r2 = r6.o;
        r3 = r6.q;
        r4 = r3 + 1;
        r6.q = r4;
        r2 = r2[r3];
        r3 = r7[r2];
        r6.a(r2, r3);
        goto L_0x0006;
    L_0x0036:
        r2 = r6.q;
        r2 = r2 + 1;
        r6.q = r2;
        if (r2 < r0) goto L_0x000a;
    L_0x003e:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.b.h.d(int):void");
    }

    private void i(String str) throws IOException, JsonGenerationException {
        k();
        int length = str.length();
        int i = 0;
        while (true) {
            int i2 = this.r;
            if (i + i2 > length) {
                i2 = length - i;
            }
            int i3 = i + i2;
            str.getChars(i, i3, this.o, 0);
            if (this.k != null) {
                g(i2);
            } else if (this.j != 0) {
                b(i2, this.j);
            } else {
                e(i2);
            }
            if (i3 < length) {
                i = i3;
            } else {
                return;
            }
        }
    }

    private void e(int i) throws IOException, JsonGenerationException {
        int[] iArr = this.i;
        int i2 = 0;
        char length = iArr.length;
        int i3 = 0;
        while (i2 < i) {
            char c;
            do {
                c = this.o[i2];
                if (c < length && iArr[c] != 0) {
                    break;
                }
                i2++;
            } while (i2 < i);
            int i4 = i2 - i3;
            if (i4 > 0) {
                this.n.write(this.o, i3, i4);
                if (i2 >= i) {
                    return;
                }
            }
            i2++;
            i3 = a(this.o, i2, i, c, iArr[c]);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0042 A:{SYNTHETIC} */
    private void a(int r9, int r10) throws java.io.IOException, com.fasterxml.jackson.core.JsonGenerationException {
        /*
        r8 = this;
        r0 = r8.q;
        r0 = r0 + r9;
        r9 = r8.i;
        r1 = r9.length;
        r2 = r10 + 1;
        r1 = java.lang.Math.min(r1, r2);
    L_0x000c:
        r2 = r8.q;
        if (r2 >= r0) goto L_0x0042;
    L_0x0010:
        r2 = r8.o;
        r3 = r8.q;
        r2 = r2[r3];
        if (r2 >= r1) goto L_0x001d;
    L_0x0018:
        r3 = r9[r2];
        if (r3 == 0) goto L_0x003a;
    L_0x001c:
        goto L_0x0020;
    L_0x001d:
        if (r2 <= r10) goto L_0x003a;
    L_0x001f:
        r3 = -1;
    L_0x0020:
        r4 = r8.q;
        r5 = r8.p;
        r4 = r4 - r5;
        if (r4 <= 0) goto L_0x0030;
    L_0x0027:
        r5 = r8.n;
        r6 = r8.o;
        r7 = r8.p;
        r5.write(r6, r7, r4);
    L_0x0030:
        r4 = r8.q;
        r4 = r4 + 1;
        r8.q = r4;
        r8.a(r2, r3);
        goto L_0x000c;
    L_0x003a:
        r2 = r8.q;
        r2 = r2 + 1;
        r8.q = r2;
        if (r2 < r0) goto L_0x0010;
    L_0x0042:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.b.h.a(int, int):void");
    }

    private void b(int i, int i2) throws IOException, JsonGenerationException {
        int[] iArr = this.i;
        int i3 = 0;
        char min = Math.min(iArr.length, i2 + 1);
        int i4 = 0;
        int i5 = i4;
        while (i3 < i) {
            char c;
            do {
                c = this.o[i3];
                if (c >= min) {
                    if (c > i2) {
                        i5 = -1;
                        break;
                    }
                }
                i5 = iArr[c];
                if (i5 != 0) {
                    break;
                }
                i3++;
            } while (i3 < i);
            int i6 = i3 - i4;
            if (i6 > 0) {
                this.n.write(this.o, i4, i6);
                if (i3 >= i) {
                    return;
                }
            }
            i3++;
            i4 = a(this.o, i3, i, c, i5);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0057 A:{SYNTHETIC} */
    private void f(int r12) throws java.io.IOException, com.fasterxml.jackson.core.JsonGenerationException {
        /*
        r11 = this;
        r0 = r11.q;
        r0 = r0 + r12;
        r12 = r11.i;
        r1 = r11.j;
        r2 = 1;
        if (r1 >= r2) goto L_0x000e;
    L_0x000a:
        r1 = 65535; // 0xffff float:9.1834E-41 double:3.23786E-319;
        goto L_0x0010;
    L_0x000e:
        r1 = r11.j;
    L_0x0010:
        r3 = r12.length;
        r4 = r1 + 1;
        r3 = java.lang.Math.min(r3, r4);
        r4 = r11.k;
    L_0x0019:
        r5 = r11.q;
        if (r5 >= r0) goto L_0x0057;
    L_0x001d:
        r5 = r11.o;
        r6 = r11.q;
        r5 = r5[r6];
        if (r5 >= r3) goto L_0x002a;
    L_0x0025:
        r6 = r12[r5];
        if (r6 == 0) goto L_0x0050;
    L_0x0029:
        goto L_0x0037;
    L_0x002a:
        if (r5 <= r1) goto L_0x002e;
    L_0x002c:
        r6 = -1;
        goto L_0x0037;
    L_0x002e:
        r6 = r4.a(r5);
        r11.t = r6;
        if (r6 == 0) goto L_0x0050;
    L_0x0036:
        r6 = -2;
    L_0x0037:
        r7 = r11.q;
        r8 = r11.p;
        r7 = r7 - r8;
        if (r7 <= 0) goto L_0x0047;
    L_0x003e:
        r8 = r11.n;
        r9 = r11.o;
        r10 = r11.p;
        r8.write(r9, r10, r7);
    L_0x0047:
        r7 = r11.q;
        r7 = r7 + r2;
        r11.q = r7;
        r11.a(r5, r6);
        goto L_0x0019;
    L_0x0050:
        r5 = r11.q;
        r5 = r5 + r2;
        r11.q = r5;
        if (r5 < r0) goto L_0x001d;
    L_0x0057:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.b.h.f(int):void");
    }

    private void g(int i) throws IOException, JsonGenerationException {
        int[] iArr = this.i;
        char c = this.j < 1 ? 65535 : this.j;
        int i2 = 0;
        char min = Math.min(iArr.length, c + 1);
        CharacterEscapes characterEscapes = this.k;
        int i3 = 0;
        int i4 = i3;
        while (i2 < i) {
            char c2;
            do {
                c2 = this.o[i2];
                if (c2 >= min) {
                    if (c2 <= c) {
                        d a = characterEscapes.a(c2);
                        this.t = a;
                        if (a != null) {
                            i4 = -2;
                            break;
                        }
                    }
                    i4 = -1;
                    break;
                }
                i4 = iArr[c2];
                if (i4 != 0) {
                    break;
                }
                i2++;
            } while (i2 < i);
            int i5 = i2 - i3;
            if (i5 > 0) {
                this.n.write(this.o, i3, i5);
                if (i2 >= i) {
                    return;
                }
            }
            i2++;
            i3 = a(this.o, i2, i, c2, i4);
        }
    }

    private void l() throws IOException {
        if (this.q + 4 >= this.r) {
            k();
        }
        int i = this.q;
        char[] cArr = this.o;
        cArr[i] = 'n';
        i++;
        cArr[i] = 'u';
        i++;
        cArr[i] = 'l';
        i++;
        cArr[i] = 'l';
        this.q = i + 1;
    }

    private void a(char c, int i) throws IOException, JsonGenerationException {
        int i2;
        int i3;
        int i4;
        char[] cArr;
        if (i >= 0) {
            if (this.q >= 2) {
                i2 = this.q - 2;
                this.p = i2;
                i3 = i2 + 1;
                this.o[i2] = '\\';
                this.o[i3] = (char) i;
                return;
            }
            char[] cArr2 = this.s;
            if (cArr2 == null) {
                cArr2 = m();
            }
            this.p = this.q;
            cArr2[1] = (char) i;
            this.n.write(cArr2, 0, 2);
        } else if (i == -2) {
            String a;
            if (this.t == null) {
                a = this.k.a(c).a();
            } else {
                a = this.t.a();
                this.t = null;
            }
            i = a.length();
            if (this.q >= i) {
                i4 = this.q - i;
                this.p = i4;
                a.getChars(0, i, this.o, i4);
                return;
            }
            this.p = this.q;
            this.n.write(a);
        } else if (this.q >= 6) {
            cArr = this.o;
            i3 = this.q - 6;
            this.p = i3;
            cArr[i3] = '\\';
            i3++;
            cArr[i3] = 'u';
            if (c > 255) {
                int i5 = (c >> 8) & 255;
                i3++;
                cArr[i3] = m[i5 >> 4];
                i3++;
                cArr[i3] = m[i5 & 15];
                i2 = (char) (c & 255);
            } else {
                i3++;
                cArr[i3] = '0';
                i3++;
                cArr[i3] = '0';
            }
            i3++;
            cArr[i3] = m[i2 >> 4];
            cArr[i3 + 1] = m[i2 & 15];
        } else {
            cArr = this.s;
            if (cArr == null) {
                cArr = m();
            }
            this.p = this.q;
            if (c > 255) {
                i4 = (c >> 8) & 255;
                i2 = c & 255;
                cArr[10] = m[i4 >> 4];
                cArr[11] = m[i4 & 15];
                cArr[12] = m[i2 >> 4];
                cArr[13] = m[i2 & 15];
                this.n.write(cArr, 8, 6);
            } else {
                cArr[6] = m[c >> 4];
                cArr[7] = m[c & 15];
                this.n.write(cArr, 2, 6);
            }
        }
    }

    private int a(char[] cArr, int i, int i2, char c, int i3) throws IOException, JsonGenerationException {
        if (i3 >= 0) {
            if (i <= 1 || i >= i2) {
                cArr = this.s;
                if (cArr == null) {
                    cArr = m();
                }
                cArr[1] = (char) i3;
                this.n.write(cArr, 0, 2);
            } else {
                i -= 2;
                cArr[i] = '\\';
                cArr[i + 1] = (char) i3;
            }
            return i;
        } else if (i3 != -2) {
            int i4;
            if (i <= 5 || i >= i2) {
                cArr = this.s;
                if (cArr == null) {
                    cArr = m();
                }
                this.p = this.q;
                if (c > 255) {
                    i3 = (c >> 8) & 255;
                    i4 = c & 255;
                    cArr[10] = m[i3 >> 4];
                    cArr[11] = m[i3 & 15];
                    cArr[12] = m[i4 >> 4];
                    cArr[13] = m[i4 & 15];
                    this.n.write(cArr, 8, 6);
                } else {
                    cArr[6] = m[c >> 4];
                    cArr[7] = m[c & 15];
                    this.n.write(cArr, 2, 6);
                }
            } else {
                i -= 6;
                i2 = i + 1;
                cArr[i] = '\\';
                i = i2 + 1;
                cArr[i2] = 'u';
                if (c > 255) {
                    i2 = (c >> 8) & 255;
                    i3 = i + 1;
                    cArr[i] = m[i2 >> 4];
                    i = i3 + 1;
                    cArr[i3] = m[i2 & 15];
                    i4 = (char) (c & 255);
                } else {
                    i2 = i + 1;
                    cArr[i] = '0';
                    i = i2 + 1;
                    cArr[i2] = '0';
                }
                i2 = i + 1;
                cArr[i] = m[i4 >> 4];
                cArr[i2] = m[i4 & 15];
                i = i2 - 5;
            }
            return i;
        } else {
            String a;
            if (this.t == null) {
                a = this.k.a(c).a();
            } else {
                a = this.t.a();
                this.t = null;
            }
            i3 = a.length();
            if (i < i3 || i >= i2) {
                this.n.write(a);
            } else {
                i -= i3;
                a.getChars(0, i3, cArr, i);
            }
            return i;
        }
    }

    private char[] m() {
        char[] cArr = new char[14];
        cArr[0] = '\\';
        cArr[2] = '\\';
        cArr[3] = 'u';
        cArr[4] = '0';
        cArr[5] = '0';
        cArr[8] = '\\';
        cArr[9] = 'u';
        this.s = cArr;
        return cArr;
    }

    /* Access modifiers changed, original: protected */
    public void k() throws IOException {
        int i = this.q - this.p;
        if (i > 0) {
            int i2 = this.p;
            this.p = 0;
            this.q = 0;
            this.n.write(this.o, i2, i);
        }
    }
}
