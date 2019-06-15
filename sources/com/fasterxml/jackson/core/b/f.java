package com.fasterxml.jackson.core.b;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.d;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.b;
import com.fasterxml.jackson.core.io.c;
import com.fasterxml.jackson.core.io.g;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

public class f extends b {
    static final byte[] m = b.h();
    private static final byte[] v = new byte[]{(byte) 110, (byte) 117, (byte) 108, (byte) 108};
    private static final byte[] w = new byte[]{(byte) 116, (byte) 114, (byte) 117, (byte) 101};
    private static final byte[] x = new byte[]{(byte) 102, (byte) 97, (byte) 108, (byte) 115, (byte) 101};
    protected final OutputStream n;
    protected byte[] o;
    protected int p = 0;
    protected final int q;
    protected final int r;
    protected char[] s;
    protected final int t;
    protected boolean u;

    public f(c cVar, int i, com.fasterxml.jackson.core.b bVar, OutputStream outputStream) {
        super(cVar, i, bVar);
        this.n = outputStream;
        this.u = true;
        this.o = cVar.f();
        this.q = this.o.length;
        this.r = this.q >> 3;
        this.s = cVar.h();
        this.t = this.s.length;
        if (a(Feature.ESCAPE_NON_ASCII)) {
            a(127);
        }
    }

    public final void a(String str) throws IOException, JsonGenerationException {
        int a = this.e.a(str);
        if (a == 4) {
            f("Can not write a field name, expecting a value");
        }
        boolean z = true;
        if (this.a != null) {
            if (a != 1) {
                z = false;
            }
            a(str, z);
            return;
        }
        if (a == 1) {
            if (this.p >= this.q) {
                k();
            }
            byte[] bArr = this.o;
            int i = this.p;
            this.p = i + 1;
            bArr[i] = (byte) 44;
        }
        g(str);
    }

    public final void c() throws IOException, JsonGenerationException {
        e("start an array");
        this.e = this.e.h();
        if (this.a != null) {
            this.a.e(this);
            return;
        }
        if (this.p >= this.q) {
            k();
        }
        byte[] bArr = this.o;
        int i = this.p;
        this.p = i + 1;
        bArr[i] = (byte) 91;
    }

    public final void d() throws IOException, JsonGenerationException {
        if (!this.e.a()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Current context not an ARRAY but ");
            stringBuilder.append(this.e.d());
            f(stringBuilder.toString());
        }
        if (this.a != null) {
            this.a.b(this, this.e.e());
        } else {
            if (this.p >= this.q) {
                k();
            }
            byte[] bArr = this.o;
            int i = this.p;
            this.p = i + 1;
            bArr[i] = (byte) 93;
        }
        this.e = this.e.j();
    }

    public final void e() throws IOException, JsonGenerationException {
        e("start an object");
        this.e = this.e.i();
        if (this.a != null) {
            this.a.b(this);
            return;
        }
        if (this.p >= this.q) {
            k();
        }
        byte[] bArr = this.o;
        int i = this.p;
        this.p = i + 1;
        bArr[i] = (byte) 123;
    }

    public final void f() throws IOException, JsonGenerationException {
        if (!this.e.c()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Current context not an object but ");
            stringBuilder.append(this.e.d());
            f(stringBuilder.toString());
        }
        if (this.a != null) {
            this.a.a(this, this.e.e());
        } else {
            if (this.p >= this.q) {
                k();
            }
            byte[] bArr = this.o;
            int i = this.p;
            this.p = i + 1;
            bArr[i] = (byte) 125;
        }
        this.e = this.e.j();
    }

    /* Access modifiers changed, original: protected|final */
    public final void g(String str) throws IOException, JsonGenerationException {
        if (a(Feature.QUOTE_FIELD_NAMES)) {
            if (this.p >= this.q) {
                k();
            }
            byte[] bArr = this.o;
            int i = this.p;
            this.p = i + 1;
            bArr[i] = (byte) 34;
            int length = str.length();
            if (length <= this.t) {
                str.getChars(0, length, this.s, 0);
                if (length <= this.r) {
                    if (this.p + length > this.q) {
                        k();
                    }
                    e(this.s, 0, length);
                } else {
                    d(this.s, 0, length);
                }
            } else {
                i(str);
            }
            if (this.p >= this.q) {
                k();
            }
            byte[] bArr2 = this.o;
            length = this.p;
            this.p = length + 1;
            bArr2[length] = (byte) 34;
            return;
        }
        i(str);
    }

    /* Access modifiers changed, original: protected|final */
    public final void a(String str, boolean z) throws IOException, JsonGenerationException {
        if (z) {
            this.a.c(this);
        } else {
            this.a.h(this);
        }
        if (a(Feature.QUOTE_FIELD_NAMES)) {
            if (this.p >= this.q) {
                k();
            }
            byte[] bArr = this.o;
            int i = this.p;
            this.p = i + 1;
            bArr[i] = (byte) 34;
            int length = str.length();
            if (length <= this.t) {
                str.getChars(0, length, this.s, 0);
                if (length <= this.r) {
                    if (this.p + length > this.q) {
                        k();
                    }
                    e(this.s, 0, length);
                } else {
                    d(this.s, 0, length);
                }
            } else {
                i(str);
            }
            if (this.p >= this.q) {
                k();
            }
            byte[] bArr2 = this.o;
            length = this.p;
            this.p = length + 1;
            bArr2[length] = (byte) 34;
            return;
        }
        i(str);
    }

    public void b(String str) throws IOException, JsonGenerationException {
        e("write text value");
        if (str == null) {
            l();
            return;
        }
        int length = str.length();
        if (length > this.t) {
            h(str);
            return;
        }
        str.getChars(0, length, this.s, 0);
        if (length > this.r) {
            b(this.s, 0, length);
            return;
        }
        if (this.p + length >= this.q) {
            k();
        }
        byte[] bArr = this.o;
        int i = this.p;
        this.p = i + 1;
        bArr[i] = (byte) 34;
        e(this.s, 0, length);
        if (this.p >= this.q) {
            k();
        }
        bArr = this.o;
        length = this.p;
        this.p = length + 1;
        bArr[length] = (byte) 34;
    }

    private void h(String str) throws IOException, JsonGenerationException {
        if (this.p >= this.q) {
            k();
        }
        byte[] bArr = this.o;
        int i = this.p;
        this.p = i + 1;
        bArr[i] = (byte) 34;
        i(str);
        if (this.p >= this.q) {
            k();
        }
        byte[] bArr2 = this.o;
        int i2 = this.p;
        this.p = i2 + 1;
        bArr2[i2] = (byte) 34;
    }

    private void b(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        if (this.p >= this.q) {
            k();
        }
        byte[] bArr = this.o;
        i = this.p;
        this.p = i + 1;
        bArr[i] = (byte) 34;
        d(this.s, 0, i2);
        if (this.p >= this.q) {
            k();
        }
        bArr = this.o;
        i = this.p;
        this.p = i + 1;
        bArr[i] = (byte) 34;
    }

    public void c(String str) throws IOException, JsonGenerationException {
        int length = str.length();
        int i = 0;
        while (length > 0) {
            char[] cArr = this.s;
            int length2 = cArr.length;
            if (length < length2) {
                length2 = length;
            }
            int i2 = i + length2;
            str.getChars(i, i2, cArr, 0);
            a(cArr, 0, length2);
            length -= length2;
            i = i2;
        }
    }

    public void b(d dVar) throws IOException, JsonGenerationException {
        byte[] b = dVar.b();
        if (b.length > 0) {
            a(b);
        }
    }

    /* JADX WARNING: Missing block: B:11:0x001e, code skipped:
            r0 = r7 + 1;
            r7 = r6[r7];
     */
    /* JADX WARNING: Missing block: B:12:0x0024, code skipped:
            if (r7 >= 2048) goto L_0x0047;
     */
    /* JADX WARNING: Missing block: B:13:0x0026, code skipped:
            r1 = r5.o;
            r2 = r5.p;
            r5.p = r2 + 1;
            r1[r2] = (byte) (com.google.android.exoplayer2.extractor.ts.PsExtractor.AUDIO_STREAM | (r7 >> 6));
            r1 = r5.o;
            r2 = r5.p;
            r5.p = r2 + 1;
            r1[r2] = (byte) ((r7 & 63) | 128);
     */
    /* JADX WARNING: Missing block: B:14:0x0047, code skipped:
            a(r7, r6, r0, r8);
     */
    /* JADX WARNING: Missing block: B:15:0x004a, code skipped:
            r7 = r0;
     */
    public final void a(char[] r6, int r7, int r8) throws java.io.IOException, com.fasterxml.jackson.core.JsonGenerationException {
        /*
        r5 = this;
        r0 = r8 + r8;
        r0 = r0 + r8;
        r1 = r5.p;
        r1 = r1 + r0;
        r2 = r5.q;
        if (r1 <= r2) goto L_0x0015;
    L_0x000a:
        r1 = r5.q;
        if (r1 >= r0) goto L_0x0012;
    L_0x000e:
        r5.c(r6, r7, r8);
        return;
    L_0x0012:
        r5.k();
    L_0x0015:
        r8 = r8 + r7;
    L_0x0016:
        if (r7 >= r8) goto L_0x005b;
    L_0x0018:
        r0 = r6[r7];
        r1 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
        if (r0 <= r1) goto L_0x004c;
    L_0x001e:
        r0 = r7 + 1;
        r7 = r6[r7];
        r1 = 2048; // 0x800 float:2.87E-42 double:1.0118E-320;
        if (r7 >= r1) goto L_0x0047;
    L_0x0026:
        r1 = r5.o;
        r2 = r5.p;
        r3 = r2 + 1;
        r5.p = r3;
        r3 = 192; // 0xc0 float:2.69E-43 double:9.5E-322;
        r4 = r7 >> 6;
        r3 = r3 | r4;
        r3 = (byte) r3;
        r1[r2] = r3;
        r1 = r5.o;
        r2 = r5.p;
        r3 = r2 + 1;
        r5.p = r3;
        r3 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r7 = r7 & 63;
        r7 = r7 | r3;
        r7 = (byte) r7;
        r1[r2] = r7;
        goto L_0x004a;
    L_0x0047:
        r5.a(r7, r6, r0, r8);
    L_0x004a:
        r7 = r0;
        goto L_0x0016;
    L_0x004c:
        r1 = r5.o;
        r2 = r5.p;
        r3 = r2 + 1;
        r5.p = r3;
        r0 = (byte) r0;
        r1[r2] = r0;
        r7 = r7 + 1;
        if (r7 < r8) goto L_0x0018;
    L_0x005b:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.b.f.a(char[], int, int):void");
    }

    public void a(char c) throws IOException, JsonGenerationException {
        if (this.p + 3 >= this.q) {
            k();
        }
        byte[] bArr = this.o;
        int i;
        if (c <= 127) {
            i = this.p;
            this.p = i + 1;
            bArr[i] = (byte) c;
        } else if (c < 2048) {
            i = this.p;
            this.p = i + 1;
            bArr[i] = (byte) (PsExtractor.AUDIO_STREAM | (c >> 6));
            i = this.p;
            this.p = i + 1;
            bArr[i] = (byte) ((c & 63) | 128);
        } else {
            a((int) c, null, 0, 0);
        }
    }

    /* JADX WARNING: Missing block: B:5:0x0012, code skipped:
            if ((r7.p + 3) < r7.q) goto L_0x0017;
     */
    /* JADX WARNING: Missing block: B:6:0x0014, code skipped:
            k();
     */
    /* JADX WARNING: Missing block: B:7:0x0017, code skipped:
            r2 = r9 + 1;
            r9 = r8[r9];
     */
    /* JADX WARNING: Missing block: B:8:0x001d, code skipped:
            if (r9 >= 2048) goto L_0x003a;
     */
    /* JADX WARNING: Missing block: B:9:0x001f, code skipped:
            r4 = r7.p;
            r7.p = r4 + 1;
            r1[r4] = (byte) (com.google.android.exoplayer2.extractor.ts.PsExtractor.AUDIO_STREAM | (r9 >> 6));
            r4 = r7.p;
            r7.p = r4 + 1;
            r1[r4] = (byte) ((r9 & 63) | 128);
     */
    /* JADX WARNING: Missing block: B:10:0x003a, code skipped:
            a(r9, r8, r2, r10);
     */
    /* JADX WARNING: Missing block: B:11:0x003d, code skipped:
            r9 = r2;
     */
    private final void c(char[] r8, int r9, int r10) throws java.io.IOException, com.fasterxml.jackson.core.JsonGenerationException {
        /*
        r7 = this;
        r0 = r7.q;
        r1 = r7.o;
    L_0x0004:
        if (r9 >= r10) goto L_0x0053;
    L_0x0006:
        r2 = r8[r9];
        r3 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        if (r2 < r3) goto L_0x003f;
    L_0x000c:
        r2 = r7.p;
        r2 = r2 + 3;
        r4 = r7.q;
        if (r2 < r4) goto L_0x0017;
    L_0x0014:
        r7.k();
    L_0x0017:
        r2 = r9 + 1;
        r9 = r8[r9];
        r4 = 2048; // 0x800 float:2.87E-42 double:1.0118E-320;
        if (r9 >= r4) goto L_0x003a;
    L_0x001f:
        r4 = r7.p;
        r5 = r4 + 1;
        r7.p = r5;
        r5 = 192; // 0xc0 float:2.69E-43 double:9.5E-322;
        r6 = r9 >> 6;
        r5 = r5 | r6;
        r5 = (byte) r5;
        r1[r4] = r5;
        r4 = r7.p;
        r5 = r4 + 1;
        r7.p = r5;
        r9 = r9 & 63;
        r9 = r9 | r3;
        r9 = (byte) r9;
        r1[r4] = r9;
        goto L_0x003d;
    L_0x003a:
        r7.a(r9, r8, r2, r10);
    L_0x003d:
        r9 = r2;
        goto L_0x0004;
    L_0x003f:
        r3 = r7.p;
        if (r3 < r0) goto L_0x0046;
    L_0x0043:
        r7.k();
    L_0x0046:
        r3 = r7.p;
        r4 = r3 + 1;
        r7.p = r4;
        r2 = (byte) r2;
        r1[r3] = r2;
        r9 = r9 + 1;
        if (r9 < r10) goto L_0x0006;
    L_0x0053:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.b.f.c(char[], int, int):void");
    }

    public void b(int i) throws IOException, JsonGenerationException {
        e("write number");
        if (this.p + 11 >= this.q) {
            k();
        }
        if (this.d) {
            c(i);
        } else {
            this.p = g.a(i, this.o, this.p);
        }
    }

    private void c(int i) throws IOException {
        if (this.p + 13 >= this.q) {
            k();
        }
        byte[] bArr = this.o;
        int i2 = this.p;
        this.p = i2 + 1;
        bArr[i2] = (byte) 34;
        this.p = g.a(i, this.o, this.p);
        byte[] bArr2 = this.o;
        int i3 = this.p;
        this.p = i3 + 1;
        bArr2[i3] = (byte) 34;
    }

    public void a(long j) throws IOException, JsonGenerationException {
        e("write number");
        if (this.d) {
            b(j);
            return;
        }
        if (this.p + 21 >= this.q) {
            k();
        }
        this.p = g.a(j, this.o, this.p);
    }

    private void b(long j) throws IOException {
        if (this.p + 23 >= this.q) {
            k();
        }
        byte[] bArr = this.o;
        int i = this.p;
        this.p = i + 1;
        bArr[i] = (byte) 34;
        this.p = g.a(j, this.o, this.p);
        byte[] bArr2 = this.o;
        int i2 = this.p;
        this.p = i2 + 1;
        bArr2[i2] = (byte) 34;
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
        if (this.p >= this.q) {
            k();
        }
        byte[] bArr = this.o;
        int i = this.p;
        this.p = i + 1;
        bArr[i] = (byte) 34;
        c(obj.toString());
        if (this.p >= this.q) {
            k();
        }
        byte[] bArr2 = this.o;
        int i2 = this.p;
        this.p = i2 + 1;
        bArr2[i2] = (byte) 34;
    }

    public void a(boolean z) throws IOException, JsonGenerationException {
        e("write boolean value");
        if (this.p + 5 >= this.q) {
            k();
        }
        Object obj = z ? w : x;
        int length = obj.length;
        System.arraycopy(obj, 0, this.o, this.p, length);
        this.p += length;
    }

    public void g() throws IOException, JsonGenerationException {
        e("write null value");
        l();
    }

    /* Access modifiers changed, original: protected|final */
    public final void e(String str) throws IOException, JsonGenerationException {
        int k = this.e.k();
        if (k == 5) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Can not ");
            stringBuilder.append(str);
            stringBuilder.append(", expecting field name");
            f(stringBuilder.toString());
        }
        if (this.a == null) {
            byte b;
            switch (k) {
                case 1:
                    b = (byte) 44;
                    break;
                case 2:
                    b = (byte) 58;
                    break;
                case 3:
                    if (this.l != null) {
                        byte[] b2 = this.l.b();
                        if (b2.length > 0) {
                            a(b2);
                        }
                    }
                    return;
                default:
                    return;
            }
            if (this.p >= this.q) {
                k();
            }
            this.o[this.p] = b;
            this.p++;
            return;
        }
        a(str, k);
    }

    /* Access modifiers changed, original: protected|final */
    public final void a(String str, int i) throws IOException, JsonGenerationException {
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

    public final void flush() throws IOException {
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
        byte[] bArr = this.o;
        if (bArr != null && this.u) {
            this.o = null;
            this.h.b(bArr);
        }
        char[] cArr = this.s;
        if (cArr != null) {
            this.s = null;
            this.h.b(cArr);
        }
    }

    private final void a(byte[] bArr) throws IOException {
        int length = bArr.length;
        if (this.p + length > this.q) {
            k();
            if (length > 512) {
                this.n.write(bArr, 0, length);
                return;
            }
        }
        System.arraycopy(bArr, 0, this.o, this.p, length);
        this.p += length;
    }

    private final void i(String str) throws IOException, JsonGenerationException {
        int length = str.length();
        char[] cArr = this.s;
        int i = 0;
        while (length > 0) {
            int min = Math.min(this.r, length);
            int i2 = i + min;
            str.getChars(i, i2, cArr, 0);
            if (this.p + min > this.q) {
                k();
            }
            e(cArr, 0, min);
            length -= min;
            i = i2;
        }
    }

    private final void d(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        do {
            int min = Math.min(this.r, i2);
            if (this.p + min > this.q) {
                k();
            }
            e(cArr, i, min);
            i += min;
            i2 -= min;
        } while (i2 > 0);
    }

    private final void e(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        i2 += i;
        int i3 = this.p;
        byte[] bArr = this.o;
        int[] iArr = this.i;
        while (i < i2) {
            char c = cArr[i];
            if (c > 127 || iArr[c] != 0) {
                break;
            }
            int i4 = i3 + 1;
            bArr[i3] = (byte) c;
            i++;
            i3 = i4;
        }
        this.p = i3;
        if (i >= i2) {
            return;
        }
        if (this.k != null) {
            h(cArr, i, i2);
        } else if (this.j == 0) {
            f(cArr, i, i2);
        } else {
            g(cArr, i, i2);
        }
    }

    private final void f(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        if (this.p + (6 * (i2 - i)) > this.q) {
            k();
        }
        int i3 = this.p;
        byte[] bArr = this.o;
        int[] iArr = this.i;
        while (i < i2) {
            int i4 = i + 1;
            char c = cArr[i];
            int i5;
            if (c <= 127) {
                if (iArr[c] == 0) {
                    i5 = i3 + 1;
                    bArr[i3] = (byte) c;
                    i = i4;
                    i3 = i5;
                } else {
                    i5 = iArr[c];
                    if (i5 > 0) {
                        i = i3 + 1;
                        bArr[i3] = (byte) 92;
                        i3 = i + 1;
                        bArr[i] = (byte) i5;
                    } else {
                        i3 = d(c, i3);
                    }
                }
            } else if (c <= 2047) {
                i5 = i3 + 1;
                bArr[i3] = (byte) (PsExtractor.AUDIO_STREAM | (c >> 6));
                i3 = i5 + 1;
                bArr[i5] = (byte) ((c & 63) | 128);
            } else {
                i3 = c(c, i3);
            }
            i = i4;
        }
        this.p = i3;
    }

    private final void g(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        if (this.p + (6 * (i2 - i)) > this.q) {
            k();
        }
        int i3 = this.p;
        byte[] bArr = this.o;
        int[] iArr = this.i;
        char c = this.j;
        while (i < i2) {
            int i4 = i + 1;
            char c2 = cArr[i];
            int i5;
            if (c2 <= 127) {
                if (iArr[c2] == 0) {
                    i5 = i3 + 1;
                    bArr[i3] = (byte) c2;
                    i = i4;
                    i3 = i5;
                } else {
                    i5 = iArr[c2];
                    if (i5 > 0) {
                        i = i3 + 1;
                        bArr[i3] = (byte) 92;
                        i3 = i + 1;
                        bArr[i] = (byte) i5;
                    } else {
                        i3 = d(c2, i3);
                    }
                }
            } else if (c2 > c) {
                i3 = d(c2, i3);
            } else if (c2 <= 2047) {
                i5 = i3 + 1;
                bArr[i3] = (byte) (PsExtractor.AUDIO_STREAM | (c2 >> 6));
                i3 = i5 + 1;
                bArr[i5] = (byte) ((c2 & 63) | 128);
            } else {
                i3 = c(c2, i3);
            }
            i = i4;
        }
        this.p = i3;
    }

    private void h(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        if (this.p + (6 * (i2 - i)) > this.q) {
            k();
        }
        int i3 = this.p;
        byte[] bArr = this.o;
        int[] iArr = this.i;
        char c = this.j <= 0 ? 65535 : this.j;
        CharacterEscapes characterEscapes = this.k;
        while (i < i2) {
            int i4 = i + 1;
            char c2 = cArr[i];
            int i5;
            d a;
            if (c2 <= 127) {
                if (iArr[c2] == 0) {
                    i5 = i3 + 1;
                    bArr[i3] = (byte) c2;
                    i = i4;
                    i3 = i5;
                } else {
                    i5 = iArr[c2];
                    if (i5 > 0) {
                        i = i3 + 1;
                        bArr[i3] = (byte) 92;
                        i3 = i + 1;
                        bArr[i] = (byte) i5;
                    } else if (i5 == -2) {
                        a = characterEscapes.a(c2);
                        if (a == null) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("Invalid custom escape definitions; custom escape not found for character code 0x");
                            stringBuilder.append(Integer.toHexString(c2));
                            stringBuilder.append(", although was supposed to have one");
                            f(stringBuilder.toString());
                        }
                        i3 = a(bArr, i3, a, i2 - i4);
                    } else {
                        i3 = d(c2, i3);
                    }
                }
            } else if (c2 > c) {
                i3 = d(c2, i3);
            } else {
                a = characterEscapes.a(c2);
                if (a != null) {
                    i3 = a(bArr, i3, a, i2 - i4);
                } else if (c2 <= 2047) {
                    i5 = i3 + 1;
                    bArr[i3] = (byte) (PsExtractor.AUDIO_STREAM | (c2 >> 6));
                    i3 = i5 + 1;
                    bArr[i5] = (byte) ((c2 & 63) | 128);
                } else {
                    i3 = c(c2, i3);
                }
            }
            i = i4;
        }
        this.p = i3;
    }

    private int a(byte[] bArr, int i, d dVar, int i2) throws IOException, JsonGenerationException {
        byte[] b = dVar.b();
        int length = b.length;
        if (length > 6) {
            return a(bArr, i, this.q, b, i2);
        }
        System.arraycopy(b, 0, bArr, i, length);
        return i + length;
    }

    private int a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws IOException, JsonGenerationException {
        int length = bArr2.length;
        if (i + length > i2) {
            this.p = i;
            k();
            i = this.p;
            if (length > bArr.length) {
                this.n.write(bArr2, 0, length);
                return i;
            }
            System.arraycopy(bArr2, 0, bArr, i, length);
            i += length;
        }
        if ((6 * i3) + i <= i2) {
            return i;
        }
        k();
        return this.p;
    }

    private int a(int i, char[] cArr, int i2, int i3) throws IOException {
        if (i < 55296 || i > 57343) {
            byte[] bArr = this.o;
            i3 = this.p;
            this.p = i3 + 1;
            bArr[i3] = (byte) (224 | (i >> 12));
            i3 = this.p;
            this.p = i3 + 1;
            bArr[i3] = (byte) (((i >> 6) & 63) | 128);
            i3 = this.p;
            this.p = i3 + 1;
            bArr[i3] = (byte) ((i & 63) | 128);
            return i2;
        }
        if (i2 >= i3) {
            f("Split surrogate on writeRaw() input (last character)");
        }
        a(i, cArr[i2]);
        return i2 + 1;
    }

    /* Access modifiers changed, original: protected|final */
    public final void a(int i, int i2) throws IOException {
        i = b(i, i2);
        if (this.p + 4 > this.q) {
            k();
        }
        byte[] bArr = this.o;
        int i3 = this.p;
        this.p = i3 + 1;
        bArr[i3] = (byte) (PsExtractor.VIDEO_STREAM_MASK | (i >> 18));
        i3 = this.p;
        this.p = i3 + 1;
        bArr[i3] = (byte) (((i >> 12) & 63) | 128);
        i3 = this.p;
        this.p = i3 + 1;
        bArr[i3] = (byte) (((i >> 6) & 63) | 128);
        i3 = this.p;
        this.p = i3 + 1;
        bArr[i3] = (byte) ((i & 63) | 128);
    }

    private int c(int i, int i2) throws IOException {
        byte[] bArr = this.o;
        int i3;
        if (i < 55296 || i > 57343) {
            i3 = i2 + 1;
            bArr[i2] = (byte) (224 | (i >> 12));
            i2 = i3 + 1;
            bArr[i3] = (byte) (((i >> 6) & 63) | 128);
            i3 = i2 + 1;
            bArr[i2] = (byte) ((i & 63) | 128);
            return i3;
        }
        i3 = i2 + 1;
        bArr[i2] = (byte) 92;
        i2 = i3 + 1;
        bArr[i3] = (byte) 117;
        i3 = i2 + 1;
        bArr[i2] = m[(i >> 12) & 15];
        i2 = i3 + 1;
        bArr[i3] = m[(i >> 8) & 15];
        i3 = i2 + 1;
        bArr[i2] = m[(i >> 4) & 15];
        i2 = i3 + 1;
        bArr[i3] = m[i & 15];
        return i2;
    }

    /* Access modifiers changed, original: protected|final */
    public final int b(int i, int i2) throws IOException {
        if (i2 < 56320 || i2 > 57343) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Incomplete surrogate pair: first char 0x");
            stringBuilder.append(Integer.toHexString(i));
            stringBuilder.append(", second 0x");
            stringBuilder.append(Integer.toHexString(i2));
            f(stringBuilder.toString());
        }
        return (65536 + ((i - 55296) << 10)) + (i2 - 56320);
    }

    private void l() throws IOException {
        if (this.p + 4 >= this.q) {
            k();
        }
        System.arraycopy(v, 0, this.o, this.p, 4);
        this.p += 4;
    }

    private int d(int i, int i2) throws IOException {
        byte[] bArr = this.o;
        int i3 = i2 + 1;
        bArr[i2] = (byte) 92;
        i2 = i3 + 1;
        bArr[i3] = (byte) 117;
        if (i > 255) {
            i3 = 255 & (i >> 8);
            int i4 = i2 + 1;
            bArr[i2] = m[i3 >> 4];
            i2 = i4 + 1;
            bArr[i4] = m[i3 & 15];
            i &= 255;
        } else {
            i3 = i2 + 1;
            bArr[i2] = (byte) 48;
            i2 = i3 + 1;
            bArr[i3] = (byte) 48;
        }
        i3 = i2 + 1;
        bArr[i2] = m[i >> 4];
        i2 = i3 + 1;
        bArr[i3] = m[i & 15];
        return i2;
    }

    /* Access modifiers changed, original: protected|final */
    public final void k() throws IOException {
        int i = this.p;
        if (i > 0) {
            this.p = 0;
            this.n.write(this.o, 0, i);
        }
    }
}
