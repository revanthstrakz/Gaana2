package com.fasterxml.jackson.core.io;

import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import java.util.Arrays;

public final class b {
    static final int[] a;
    static final int[] b;
    static final int[] c;
    static final int[] d;
    static final int[] e = new int[256];
    static final int[] f;
    static final int[] g = new int[128];
    private static final char[] h = "0123456789ABCDEF".toCharArray();
    private static final byte[] i;

    static {
        int i;
        int i2;
        int i3;
        int i4 = 0;
        int length = h.length;
        i = new byte[length];
        for (i = 0; i < length; i++) {
            i[i] = (byte) h[i];
        }
        int[] iArr = new int[256];
        for (i2 = 0; i2 < 32; i2++) {
            iArr[i2] = -1;
        }
        iArr[34] = 1;
        iArr[92] = 1;
        a = iArr;
        iArr = new int[a.length];
        System.arraycopy(a, 0, iArr, 0, a.length);
        for (i3 = 128; i3 < 256; i3++) {
            int i5 = (i3 & 224) == PsExtractor.AUDIO_STREAM ? 2 : (i3 & PsExtractor.VIDEO_STREAM_MASK) == 224 ? 3 : (i3 & 248) == PsExtractor.VIDEO_STREAM_MASK ? 4 : -1;
            iArr[i3] = i5;
        }
        b = iArr;
        iArr = new int[256];
        Arrays.fill(iArr, -1);
        for (i3 = 33; i3 < 256; i3++) {
            if (Character.isJavaIdentifierPart((char) i3)) {
                iArr[i3] = 0;
            }
        }
        iArr[64] = 0;
        iArr[35] = 0;
        iArr[42] = 0;
        iArr[45] = 0;
        iArr[43] = 0;
        c = iArr;
        iArr = new int[256];
        System.arraycopy(c, 0, iArr, 0, c.length);
        Arrays.fill(iArr, 128, 128, 0);
        d = iArr;
        System.arraycopy(b, 128, e, 128, 128);
        Arrays.fill(e, 0, 32, -1);
        e[9] = 0;
        e[10] = 10;
        e[13] = 13;
        e[42] = 42;
        int[] iArr2 = new int[128];
        for (i = 0; i < 32; i++) {
            iArr2[i] = -1;
        }
        iArr2[34] = 34;
        iArr2[92] = 92;
        iArr2[8] = 98;
        iArr2[9] = 116;
        iArr2[12] = 102;
        iArr2[10] = 110;
        iArr2[13] = 114;
        f = iArr2;
        Arrays.fill(g, -1);
        for (length = 0; length < 10; length++) {
            g[48 + length] = length;
        }
        while (i4 < 6) {
            i2 = 10 + i4;
            g[97 + i4] = i2;
            g[65 + i4] = i2;
            i4++;
        }
    }

    public static int[] a() {
        return a;
    }

    public static int[] b() {
        return b;
    }

    public static int[] c() {
        return c;
    }

    public static int[] d() {
        return d;
    }

    public static int[] e() {
        return e;
    }

    public static int[] f() {
        return f;
    }

    public static int a(int i) {
        return i > 127 ? -1 : g[i];
    }

    public static void a(StringBuilder stringBuilder, String str) {
        int[] iArr = f;
        char length = iArr.length;
        int length2 = str.length();
        for (int i = 0; i < length2; i++) {
            char charAt = str.charAt(i);
            if (charAt >= length || iArr[charAt] == 0) {
                stringBuilder.append(charAt);
            } else {
                stringBuilder.append('\\');
                int i2 = iArr[charAt];
                if (i2 < 0) {
                    stringBuilder.append('u');
                    stringBuilder.append('0');
                    stringBuilder.append('0');
                    i2 = -(i2 + 1);
                    stringBuilder.append(h[i2 >> 4]);
                    stringBuilder.append(h[i2 & 15]);
                } else {
                    stringBuilder.append((char) i2);
                }
            }
        }
    }

    public static char[] g() {
        return (char[]) h.clone();
    }

    public static byte[] h() {
        return (byte[]) i.clone();
    }
}
