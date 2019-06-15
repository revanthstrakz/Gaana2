package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.util.a;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import java.lang.ref.SoftReference;

public final class d {
    protected static final ThreadLocal<SoftReference<d>> a = new ThreadLocal();
    private static final char[] d = b.g();
    private static final byte[] e = b.h();
    protected a b;
    protected final char[] c = new char[6];

    public d() {
        this.c[0] = '\\';
        this.c[2] = '0';
        this.c[3] = '0';
    }

    public static d a() {
        d dVar;
        SoftReference softReference = (SoftReference) a.get();
        if (softReference == null) {
            dVar = null;
        } else {
            dVar = (d) softReference.get();
        }
        if (dVar != null) {
            return dVar;
        }
        dVar = new d();
        a.set(new SoftReference(dVar));
        return dVar;
    }

    public byte[] a(String str) {
        a aVar = this.b;
        if (aVar == null) {
            aVar = new a(null);
            this.b = aVar;
        }
        int length = str.length();
        byte[] c = aVar.c();
        int length2 = c.length;
        byte[] bArr = c;
        int i = 0;
        int i2 = length2;
        length2 = i;
        loop0:
        while (i < length) {
            byte[] d;
            int i3;
            int i4 = i + 1;
            i = str.charAt(i);
            while (i <= 127) {
                if (length2 >= i2) {
                    d = aVar.d();
                    i2 = d.length;
                    bArr = d;
                    length2 = 0;
                }
                i3 = length2 + 1;
                bArr[length2] = (byte) i;
                if (i4 >= length) {
                    length2 = i3;
                    break loop0;
                }
                i = i4 + 1;
                char charAt = str.charAt(i4);
                i4 = i;
                char c2 = charAt;
                length2 = i3;
            }
            if (length2 >= i2) {
                bArr = aVar.d();
                i2 = bArr.length;
                length2 = 0;
            }
            if (i < 2048) {
                i3 = length2 + 1;
                bArr[length2] = (byte) (PsExtractor.AUDIO_STREAM | (i >> 6));
                length2 = i3;
            } else if (i < 55296 || i > 57343) {
                i3 = length2 + 1;
                bArr[length2] = (byte) (224 | (i >> 12));
                if (i3 >= i2) {
                    bArr = aVar.d();
                    i3 = 0;
                    i2 = bArr.length;
                }
                length2 = i3 + 1;
                bArr[i3] = (byte) (((i >> 6) & 63) | 128);
            } else {
                if (i > 56319) {
                    a(i);
                }
                if (i4 >= length) {
                    a(i);
                }
                i3 = i4 + 1;
                i = a(i, str.charAt(i4));
                if (i > 1114111) {
                    a(i);
                }
                i4 = length2 + 1;
                bArr[length2] = (byte) (PsExtractor.VIDEO_STREAM_MASK | (i >> 18));
                if (i4 >= i2) {
                    bArr = aVar.d();
                    i2 = bArr.length;
                    i4 = 0;
                }
                length2 = i4 + 1;
                bArr[i4] = (byte) (((i >> 12) & 63) | 128);
                if (length2 >= i2) {
                    d = aVar.d();
                    i2 = d.length;
                    bArr = d;
                    length2 = 0;
                }
                i4 = length2 + 1;
                bArr[length2] = (byte) (((i >> 6) & 63) | 128);
                length2 = i4;
                i4 = i3;
            }
            if (length2 >= i2) {
                d = aVar.d();
                i2 = d.length;
                bArr = d;
                length2 = 0;
            }
            i3 = length2 + 1;
            bArr[length2] = (byte) ((i & 63) | 128);
            i = i4;
            length2 = i3;
        }
        return this.b.b(length2);
    }

    private int a(int i, int i2) {
        if (i2 >= 56320 && i2 <= 57343) {
            return (65536 + ((i - 55296) << 10)) + (i2 - 56320);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Broken surrogate pair: first char 0x");
        stringBuilder.append(Integer.toHexString(i));
        stringBuilder.append(", second 0x");
        stringBuilder.append(Integer.toHexString(i2));
        stringBuilder.append("; illegal combination");
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    private void a(int i) {
        StringBuilder stringBuilder;
        if (i > 1114111) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Illegal character point (0x");
            stringBuilder.append(Integer.toHexString(i));
            stringBuilder.append(") to output; max is 0x10FFFF as per RFC 4627");
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (i < 55296) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Illegal character point (0x");
            stringBuilder.append(Integer.toHexString(i));
            stringBuilder.append(") to output");
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (i <= 56319) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Unmatched first part of surrogate pair (0x");
            stringBuilder.append(Integer.toHexString(i));
            stringBuilder.append(")");
            throw new IllegalArgumentException(stringBuilder.toString());
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Unmatched second part of surrogate pair (0x");
            stringBuilder.append(Integer.toHexString(i));
            stringBuilder.append(")");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }
}
