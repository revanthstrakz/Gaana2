package com.fasterxml.jackson.core.b;

import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.b;
import com.fasterxml.jackson.core.io.c;
import com.fasterxml.jackson.core.io.e;
import com.fasterxml.jackson.core.io.h;
import java.io.ByteArrayInputStream;
import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public final class a {
    protected final c a;
    protected final InputStream b;
    protected final byte[] c;
    protected int d;
    protected boolean e = true;
    protected int f = 0;
    private int g;
    private int h;
    private final boolean i;

    public a(c cVar, InputStream inputStream) {
        this.a = cVar;
        this.b = inputStream;
        this.c = cVar.e();
        this.g = 0;
        this.h = 0;
        this.d = 0;
        this.i = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0070  */
    /* JADX WARNING: Missing block: B:7:0x0049, code skipped:
            if (d(r1 >>> 16) != false) goto L_0x006e;
     */
    /* JADX WARNING: Missing block: B:11:0x006a, code skipped:
            if (d(((r7.c[r7.g] & 255) << 8) | (r7.c[r7.g + 1] & 255)) != false) goto L_0x006e;
     */
    public com.fasterxml.jackson.core.JsonEncoding a() throws java.io.IOException, com.fasterxml.jackson.core.JsonParseException {
        /*
        r7 = this;
        r0 = 4;
        r1 = r7.a(r0);
        r2 = 2;
        r3 = 1;
        r4 = 0;
        if (r1 == 0) goto L_0x004c;
    L_0x000a:
        r1 = r7.c;
        r5 = r7.g;
        r1 = r1[r5];
        r1 = r1 << 24;
        r5 = r7.c;
        r6 = r7.g;
        r6 = r6 + r3;
        r5 = r5[r6];
        r5 = r5 & 255;
        r5 = r5 << 16;
        r1 = r1 | r5;
        r5 = r7.c;
        r6 = r7.g;
        r6 = r6 + r2;
        r2 = r5[r6];
        r2 = r2 & 255;
        r2 = r2 << 8;
        r1 = r1 | r2;
        r2 = r7.c;
        r5 = r7.g;
        r5 = r5 + 3;
        r2 = r2[r5];
        r2 = r2 & 255;
        r1 = r1 | r2;
        r2 = r7.b(r1);
        if (r2 == 0) goto L_0x003c;
    L_0x003b:
        goto L_0x006e;
    L_0x003c:
        r2 = r7.c(r1);
        if (r2 == 0) goto L_0x0043;
    L_0x0042:
        goto L_0x006e;
    L_0x0043:
        r1 = r1 >>> 16;
        r1 = r7.d(r1);
        if (r1 == 0) goto L_0x006d;
    L_0x004b:
        goto L_0x006e;
    L_0x004c:
        r1 = r7.a(r2);
        if (r1 == 0) goto L_0x006d;
    L_0x0052:
        r1 = r7.c;
        r2 = r7.g;
        r1 = r1[r2];
        r1 = r1 & 255;
        r1 = r1 << 8;
        r2 = r7.c;
        r5 = r7.g;
        r5 = r5 + r3;
        r2 = r2[r5];
        r2 = r2 & 255;
        r1 = r1 | r2;
        r1 = r7.d(r1);
        if (r1 == 0) goto L_0x006d;
    L_0x006c:
        goto L_0x006e;
    L_0x006d:
        r3 = r4;
    L_0x006e:
        if (r3 != 0) goto L_0x0073;
    L_0x0070:
        r0 = com.fasterxml.jackson.core.JsonEncoding.UTF8;
        goto L_0x0098;
    L_0x0073:
        r1 = r7.f;
        if (r1 == r0) goto L_0x008f;
    L_0x0077:
        switch(r1) {
            case 1: goto L_0x008c;
            case 2: goto L_0x0082;
            default: goto L_0x007a;
        };
    L_0x007a:
        r0 = new java.lang.RuntimeException;
        r1 = "Internal error";
        r0.<init>(r1);
        throw r0;
    L_0x0082:
        r0 = r7.e;
        if (r0 == 0) goto L_0x0089;
    L_0x0086:
        r0 = com.fasterxml.jackson.core.JsonEncoding.UTF16_BE;
        goto L_0x0098;
    L_0x0089:
        r0 = com.fasterxml.jackson.core.JsonEncoding.UTF16_LE;
        goto L_0x0098;
    L_0x008c:
        r0 = com.fasterxml.jackson.core.JsonEncoding.UTF8;
        goto L_0x0098;
    L_0x008f:
        r0 = r7.e;
        if (r0 == 0) goto L_0x0096;
    L_0x0093:
        r0 = com.fasterxml.jackson.core.JsonEncoding.UTF32_BE;
        goto L_0x0098;
    L_0x0096:
        r0 = com.fasterxml.jackson.core.JsonEncoding.UTF32_LE;
    L_0x0098:
        r1 = r7.a;
        r1.a(r0);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.b.a.a():com.fasterxml.jackson.core.JsonEncoding");
    }

    public Reader b() throws IOException {
        JsonEncoding b = this.a.b();
        switch (b) {
            case UTF32_BE:
            case UTF32_LE:
                return new h(this.a, this.b, this.c, this.g, this.h, this.a.b().isBigEndian());
            case UTF16_BE:
            case UTF16_LE:
            case UTF8:
                InputStream inputStream = this.b;
                if (inputStream == null) {
                    inputStream = new ByteArrayInputStream(this.c, this.g, this.h);
                } else if (this.g < this.h) {
                    inputStream = new e(this.a, inputStream, this.c, this.g, this.h);
                }
                return new InputStreamReader(inputStream, b.getJavaName());
            default:
                throw new RuntimeException("Internal error");
        }
    }

    public JsonParser a(int i, b bVar, com.fasterxml.jackson.core.c.a aVar, com.fasterxml.jackson.core.c.b bVar2, boolean z, boolean z2) throws IOException, JsonParseException {
        boolean z3 = z;
        if (a() == JsonEncoding.UTF8 && z3) {
            return new g(this.a, i, this.b, bVar, aVar.a(z3, z2), this.c, this.g, this.h, this.i);
        }
        boolean z4 = z2;
        return new e(this.a, i, b(), bVar, bVar2.a(z, z2));
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003a  */
    private boolean b(int r7) throws java.io.IOException {
        /*
        r6 = this;
        r0 = -16842752; // 0xfffffffffeff0000 float:-1.6947657E38 double:NaN;
        r1 = 65534; // 0xfffe float:9.1833E-41 double:3.2378E-319;
        r2 = 65279; // 0xfeff float:9.1475E-41 double:3.2252E-319;
        r3 = 0;
        r4 = 1;
        if (r7 == r0) goto L_0x0030;
    L_0x000c:
        r0 = -131072; // 0xfffffffffffe0000 float:NaN double:NaN;
        r5 = 4;
        if (r7 == r0) goto L_0x0026;
    L_0x0011:
        if (r7 == r2) goto L_0x001c;
    L_0x0013:
        if (r7 == r1) goto L_0x0016;
    L_0x0015:
        goto L_0x0035;
    L_0x0016:
        r0 = "2143";
        r6.a(r0);
        goto L_0x0030;
    L_0x001c:
        r6.e = r4;
        r7 = r6.g;
        r7 = r7 + r5;
        r6.g = r7;
        r6.f = r5;
        return r4;
    L_0x0026:
        r7 = r6.g;
        r7 = r7 + r5;
        r6.g = r7;
        r6.f = r5;
        r6.e = r3;
        return r4;
    L_0x0030:
        r0 = "3412";
        r6.a(r0);
    L_0x0035:
        r0 = r7 >>> 16;
        r5 = 2;
        if (r0 != r2) goto L_0x0044;
    L_0x003a:
        r7 = r6.g;
        r7 = r7 + r5;
        r6.g = r7;
        r6.f = r5;
        r6.e = r4;
        return r4;
    L_0x0044:
        if (r0 != r1) goto L_0x0050;
    L_0x0046:
        r7 = r6.g;
        r7 = r7 + r5;
        r6.g = r7;
        r6.f = r5;
        r6.e = r3;
        return r4;
    L_0x0050:
        r7 = r7 >>> 8;
        r0 = 15711167; // 0xefbbbf float:2.2016034E-38 double:7.762348E-317;
        if (r7 != r0) goto L_0x0062;
    L_0x0057:
        r7 = r6.g;
        r7 = r7 + 3;
        r6.g = r7;
        r6.f = r4;
        r6.e = r4;
        return r4;
    L_0x0062:
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.b.a.b(int):boolean");
    }

    private boolean c(int i) throws IOException {
        if ((i >> 8) == 0) {
            this.e = true;
        } else if ((ViewCompat.MEASURED_SIZE_MASK & i) == 0) {
            this.e = false;
        } else if ((-16711681 & i) == 0) {
            a("3412");
        } else if ((i & -65281) != 0) {
            return false;
        } else {
            a("2143");
        }
        this.f = 4;
        return true;
    }

    private boolean d(int i) {
        if ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & i) == 0) {
            this.e = true;
        } else if ((i & 255) != 0) {
            return false;
        } else {
            this.e = false;
        }
        this.f = 2;
        return true;
    }

    private void a(String str) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unsupported UCS-4 endianness (");
        stringBuilder.append(str);
        stringBuilder.append(") detected");
        throw new CharConversionException(stringBuilder.toString());
    }

    /* Access modifiers changed, original: protected */
    public boolean a(int i) throws IOException {
        int i2 = this.h - this.g;
        while (i2 < i) {
            int i3;
            if (this.b == null) {
                i3 = -1;
            } else {
                i3 = this.b.read(this.c, this.h, this.c.length - this.h);
            }
            if (i3 < 1) {
                return false;
            }
            this.h += i3;
            i2 += i3;
        }
        return true;
    }
}
