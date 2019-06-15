package com.google.ads.interactivemedia.v3.internal;

import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import java.nio.charset.Charset;

public final class fp {
    public byte[] a;
    private int b;
    private int c;

    public fp(int i) {
        this.a = new byte[i];
        this.c = this.a.length;
    }

    public fp(byte[] bArr) {
        this.a = bArr;
        this.c = bArr.length;
    }

    public fp(byte[] bArr, int i) {
        this.a = bArr;
        this.c = i;
    }

    public void a(int i) {
        a(e() < i ? new byte[i] : this.a, i);
    }

    public void a(byte[] bArr, int i) {
        this.a = bArr;
        this.c = i;
        this.b = 0;
    }

    public void a() {
        this.b = 0;
        this.c = 0;
    }

    public int b() {
        return this.c - this.b;
    }

    public int c() {
        return this.c;
    }

    public void b(int i) {
        boolean z = i >= 0 && i <= this.a.length;
        fe.a(z);
        this.c = i;
    }

    public int d() {
        return this.b;
    }

    public int e() {
        return this.a == null ? 0 : this.a.length;
    }

    public void c(int i) {
        boolean z = i >= 0 && i <= this.c;
        fe.a(z);
        this.b = i;
    }

    public void d(int i) {
        c(this.b + i);
    }

    public void a(fo foVar, int i) {
        a(foVar.a, 0, i);
        foVar.a(0);
    }

    public void a(byte[] bArr, int i, int i2) {
        System.arraycopy(this.a, this.b, bArr, i, i2);
        this.b += i2;
    }

    public int f() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        return bArr[i] & 255;
    }

    public int g() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        int i2 = (bArr[i] & 255) << 8;
        byte[] bArr2 = this.a;
        int i3 = this.b;
        this.b = i3 + 1;
        return i2 | (bArr2[i3] & 255);
    }

    public int h() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        int i2 = bArr[i] & 255;
        byte[] bArr2 = this.a;
        int i3 = this.b;
        this.b = i3 + 1;
        return i2 | ((bArr2[i3] & 255) << 8);
    }

    public short i() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        int i2 = (bArr[i] & 255) << 8;
        byte[] bArr2 = this.a;
        int i3 = this.b;
        this.b = i3 + 1;
        return (short) (i2 | (bArr2[i3] & 255));
    }

    public int j() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        int i2 = (bArr[i] & 255) << 16;
        byte[] bArr2 = this.a;
        int i3 = this.b;
        this.b = i3 + 1;
        i2 |= (bArr2[i3] & 255) << 8;
        bArr2 = this.a;
        i3 = this.b;
        this.b = i3 + 1;
        return i2 | (bArr2[i3] & 255);
    }

    public long k() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        long j = (((long) bArr[i]) & 255) << 24;
        byte[] bArr2 = this.a;
        int i2 = this.b;
        this.b = i2 + 1;
        long j2 = j | ((((long) bArr2[i2]) & 255) << 16);
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        long j3 = j2 | ((((long) bArr[i]) & 255) << 8);
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        return j3 | (((long) bArr[i]) & 255);
    }

    public long l() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        long j = ((long) bArr[i]) & 255;
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        long j2 = j | ((((long) bArr[i]) & 255) << 8);
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        j = j2 | ((((long) bArr[i]) & 255) << 16);
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        return j | ((((long) bArr[i]) & 255) << 24);
    }

    public int m() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        int i2 = (bArr[i] & 255) << 24;
        byte[] bArr2 = this.a;
        int i3 = this.b;
        this.b = i3 + 1;
        i2 |= (bArr2[i3] & 255) << 16;
        bArr2 = this.a;
        i3 = this.b;
        this.b = i3 + 1;
        i2 |= (bArr2[i3] & 255) << 8;
        bArr2 = this.a;
        i3 = this.b;
        this.b = i3 + 1;
        return i2 | (bArr2[i3] & 255);
    }

    public int n() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        int i2 = bArr[i] & 255;
        byte[] bArr2 = this.a;
        int i3 = this.b;
        this.b = i3 + 1;
        i2 |= (bArr2[i3] & 255) << 8;
        bArr2 = this.a;
        i3 = this.b;
        this.b = i3 + 1;
        i2 |= (bArr2[i3] & 255) << 16;
        bArr2 = this.a;
        i3 = this.b;
        this.b = i3 + 1;
        return i2 | ((bArr2[i3] & 255) << 24);
    }

    public long o() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        long j = (((long) bArr[i]) & 255) << 56;
        byte[] bArr2 = this.a;
        int i2 = this.b;
        this.b = i2 + 1;
        long j2 = j | ((((long) bArr2[i2]) & 255) << 48);
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        long j3 = j2 | ((((long) bArr[i]) & 255) << 40);
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        j2 = j3 | ((((long) bArr[i]) & 255) << 32);
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        j3 = j2 | ((((long) bArr[i]) & 255) << 24);
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        j2 = j3 | ((((long) bArr[i]) & 255) << 16);
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        j3 = j2 | ((((long) bArr[i]) & 255) << 8);
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        return j3 | (((long) bArr[i]) & 255);
    }

    public long p() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        long j = ((long) bArr[i]) & 255;
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        long j2 = j | ((((long) bArr[i]) & 255) << 8);
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        j = j2 | ((((long) bArr[i]) & 255) << 16);
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        j2 = j | ((((long) bArr[i]) & 255) << 24);
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        j = j2 | ((((long) bArr[i]) & 255) << 32);
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        j2 = j | ((((long) bArr[i]) & 255) << 40);
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        j = j2 | ((((long) bArr[i]) & 255) << 48);
        bArr = this.a;
        i = this.b;
        this.b = i + 1;
        return j | ((((long) bArr[i]) & 255) << 56);
    }

    public int q() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        int i2 = (bArr[i] & 255) << 8;
        byte[] bArr2 = this.a;
        int i3 = this.b;
        this.b = i3 + 1;
        i2 |= bArr2[i3] & 255;
        this.b += 2;
        return i2;
    }

    public int r() {
        return (((f() << 21) | (f() << 14)) | (f() << 7)) | f();
    }

    public int s() {
        int m = m();
        if (m >= 0) {
            return m;
        }
        StringBuilder stringBuilder = new StringBuilder(29);
        stringBuilder.append("Top bit not zero: ");
        stringBuilder.append(m);
        throw new IllegalStateException(stringBuilder.toString());
    }

    public int t() {
        int n = n();
        if (n >= 0) {
            return n;
        }
        StringBuilder stringBuilder = new StringBuilder(29);
        stringBuilder.append("Top bit not zero: ");
        stringBuilder.append(n);
        throw new IllegalStateException(stringBuilder.toString());
    }

    public long u() {
        long o = o();
        if (o >= 0) {
            return o;
        }
        StringBuilder stringBuilder = new StringBuilder(38);
        stringBuilder.append("Top bit not zero: ");
        stringBuilder.append(o);
        throw new IllegalStateException(stringBuilder.toString());
    }

    public double v() {
        return Double.longBitsToDouble(o());
    }

    public String e(int i) {
        return a(i, Charset.defaultCharset());
    }

    public String a(int i, Charset charset) {
        String str = new String(this.a, this.b, i, charset);
        this.b += i;
        return str;
    }

    public long w() {
        int i;
        long j;
        int i2;
        long j2 = (long) this.a[this.b];
        int i3 = 7;
        while (true) {
            i = 1;
            if (i3 < 0) {
                break;
            }
            int i4 = 1 << i3;
            if ((j2 & ((long) i4)) != 0) {
                i3--;
            } else if (i3 < 6) {
                j = j2 & ((long) (i4 - 1));
                i2 = 7 - i3;
            } else if (i3 == 7) {
                j = j2;
                i2 = 1;
            }
        }
        j = j2;
        i2 = 0;
        StringBuilder stringBuilder;
        if (i2 == 0) {
            stringBuilder = new StringBuilder(55);
            stringBuilder.append("Invalid UTF-8 sequence first byte: ");
            stringBuilder.append(j);
            throw new NumberFormatException(stringBuilder.toString());
        }
        while (i < i2) {
            byte b = this.a[this.b + i];
            if ((b & PsExtractor.AUDIO_STREAM) != 128) {
                stringBuilder = new StringBuilder(62);
                stringBuilder.append("Invalid UTF-8 sequence continuation byte: ");
                stringBuilder.append(j);
                throw new NumberFormatException(stringBuilder.toString());
            }
            j = (j << 6) | ((long) (b & 63));
            i++;
        }
        this.b += i2;
        return j;
    }
}
