package com.google.ads.interactivemedia.v3.internal;

import android.util.Log;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import java.nio.ByteBuffer;
import java.util.Arrays;

public final class fn {
    public static final byte[] a = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 1};
    public static final float[] b = new float[]{1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 2.1818182f, 1.8181819f, 2.909091f, 2.4242425f, 1.6363636f, 1.3636364f, 1.939394f, 1.6161616f, 1.3333334f, 1.5f, 2.0f};
    private static final Object c = new Object();
    private static int[] d = new int[10];

    public static final class a {
        public final int a;
        public final int b;
        public final boolean c;

        public a(int i, int i2, boolean z) {
            this.a = i;
            this.b = i2;
            this.c = z;
        }
    }

    public static final class b {
        public final int a;
        public final int b;
        public final int c;
        public final float d;
        public final boolean e;
        public final boolean f;
        public final int g;
        public final int h;
        public final int i;
        public final boolean j;

        public b(int i, int i2, int i3, float f, boolean z, boolean z2, int i4, int i5, int i6, boolean z3) {
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = f;
            this.e = z;
            this.f = z2;
            this.g = i4;
            this.h = i5;
            this.i = i6;
            this.j = z3;
        }
    }

    public static int a(byte[] bArr, int i) {
        synchronized (c) {
            int i2;
            int i3 = 0;
            int i4 = i3;
            while (i3 < i) {
                try {
                    i3 = a(bArr, i3, i);
                    if (i3 < i) {
                        if (d.length <= i4) {
                            d = Arrays.copyOf(d, d.length * 2);
                        }
                        i2 = i4 + 1;
                        d[i4] = i3;
                        i3 += 3;
                        i4 = i2;
                    }
                } catch (Throwable th) {
                }
            }
            i -= i4;
            i3 = 0;
            int i5 = i3;
            i2 = i5;
            while (i3 < i4) {
                int i6 = d[i3] - i2;
                System.arraycopy(bArr, i2, bArr, i5, i6);
                i5 += i6;
                int i7 = i5 + 1;
                bArr[i5] = (byte) 0;
                i5 = i7 + 1;
                bArr[i7] = (byte) 0;
                i2 += i6 + 3;
                i3++;
            }
            System.arraycopy(bArr, i2, bArr, i5, i - i5);
        }
        return i;
    }

    public static void a(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        int i = 0;
        int i2 = i;
        while (true) {
            int i3 = i + 1;
            if (i3 < position) {
                int i4 = byteBuffer.get(i) & 255;
                if (i2 == 3) {
                    if (i4 == 1 && (byteBuffer.get(i3) & 31) == 7) {
                        ByteBuffer duplicate = byteBuffer.duplicate();
                        duplicate.position(i - 3);
                        duplicate.limit(position);
                        byteBuffer.position(0);
                        byteBuffer.put(duplicate);
                        return;
                    }
                } else if (i4 == 0) {
                    i2++;
                }
                if (i4 != 0) {
                    i2 = 0;
                }
                i = i3;
            } else {
                byteBuffer.clear();
                return;
            }
        }
    }

    public static byte[] a(fp fpVar) {
        int g = fpVar.g();
        int d = fpVar.d();
        fpVar.d(g);
        return ff.a(fpVar.a, d, g);
    }

    public static int b(byte[] bArr, int i) {
        return bArr[i + 3] & 31;
    }

    public static int c(byte[] bArr, int i) {
        return (bArr[i + 3] & 126) >> 1;
    }

    public static b a(fo foVar) {
        boolean b;
        int i;
        boolean z;
        int i2;
        boolean z2;
        int d;
        int i3;
        float f;
        fo foVar2 = foVar;
        int c = foVar2.c(8);
        foVar2.b(16);
        int d2 = foVar.d();
        int i4 = 1;
        if (c == 100 || c == 110 || c == 122 || c == 244 || c == 44 || c == 83 || c == 86 || c == 118 || c == 128 || c == TsExtractor.TS_STREAM_TYPE_DTS) {
            c = foVar.d();
            b = c == 3 ? foVar.b() : false;
            foVar.d();
            foVar.d();
            foVar2.b(1);
            if (foVar.b()) {
                int i5 = c != 3 ? 8 : 12;
                i = 0;
                while (i < i5) {
                    if (foVar.b()) {
                        a(foVar2, i < 6 ? 16 : 64);
                    }
                    i++;
                }
            }
            z = b;
        } else {
            z = false;
            c = 1;
        }
        int d3 = foVar.d() + 4;
        int d4 = foVar.d();
        if (d4 == 0) {
            i2 = d2;
            z2 = false;
            d = foVar.d() + 4;
        } else if (d4 == 1) {
            b = foVar.b();
            foVar.e();
            foVar.e();
            long d5 = (long) foVar.d();
            i2 = d2;
            for (i = 0; ((long) i) < d5; i++) {
                foVar.d();
            }
            d = 0;
            z2 = b;
        } else {
            i2 = d2;
            d = 0;
            z2 = d;
        }
        foVar.d();
        foVar2.b(1);
        int d6 = foVar.d() + 1;
        d2 = foVar.d() + 1;
        boolean b2 = foVar.b();
        int i6 = (2 - b2) * d2;
        if (!b2) {
            foVar2.b(1);
        }
        foVar2.b(1);
        d6 *= 16;
        i6 *= 16;
        if (foVar.b()) {
            int i7;
            d2 = foVar.d();
            int d7 = foVar.d();
            int d8 = foVar.d();
            int d9 = foVar.d();
            if (c == 0) {
                i3 = 2 - b2;
                i7 = 1;
            } else {
                i7 = c == 3 ? 1 : 2;
                if (c == 1) {
                    i4 = 2;
                }
                i3 = (2 - b2) * i4;
            }
            d6 -= (d2 + d7) * i7;
            i6 -= (d8 + d9) * i3;
        }
        i3 = d6;
        i4 = i6;
        float f2 = 1.0f;
        if (foVar.b() && foVar.b()) {
            int c2 = foVar2.c(8);
            if (c2 == 255) {
                c2 = foVar2.c(16);
                int c3 = foVar2.c(16);
                if (!(c2 == 0 || c3 == 0)) {
                    f2 = ((float) c2) / ((float) c3);
                }
            } else if (c2 < b.length) {
                f = b[c2];
                return new b(i2, i3, i4, f, z, b2, d3, d4, d, z2);
            } else {
                StringBuilder stringBuilder = new StringBuilder(46);
                stringBuilder.append("Unexpected aspect_ratio_idc value: ");
                stringBuilder.append(c2);
                Log.w("NalUnitUtil", stringBuilder.toString());
            }
        }
        f = f2;
        return new b(i2, i3, i4, f, z, b2, d3, d4, d, z2);
    }

    public static a b(fo foVar) {
        int d = foVar.d();
        int d2 = foVar.d();
        foVar.b(1);
        return new a(d, d2, foVar.b());
    }

    /* JADX WARNING: Removed duplicated region for block: B:79:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x00ba  */
    public static int a(byte[] r7, int r8, int r9, boolean[] r10) {
        /*
        r0 = r9 - r8;
        r1 = 0;
        r2 = 1;
        if (r0 < 0) goto L_0x0008;
    L_0x0006:
        r3 = r2;
        goto L_0x0009;
    L_0x0008:
        r3 = r1;
    L_0x0009:
        com.google.ads.interactivemedia.v3.internal.fe.b(r3);
        if (r0 != 0) goto L_0x000f;
    L_0x000e:
        return r9;
    L_0x000f:
        r3 = 2;
        if (r10 == 0) goto L_0x0040;
    L_0x0012:
        r4 = r10[r1];
        if (r4 == 0) goto L_0x001c;
    L_0x0016:
        a(r10);
        r8 = r8 + -3;
        return r8;
    L_0x001c:
        if (r0 <= r2) goto L_0x002b;
    L_0x001e:
        r4 = r10[r2];
        if (r4 == 0) goto L_0x002b;
    L_0x0022:
        r4 = r7[r8];
        if (r4 != r2) goto L_0x002b;
    L_0x0026:
        a(r10);
        r8 = r8 - r3;
        return r8;
    L_0x002b:
        if (r0 <= r3) goto L_0x0040;
    L_0x002d:
        r4 = r10[r3];
        if (r4 == 0) goto L_0x0040;
    L_0x0031:
        r4 = r7[r8];
        if (r4 != 0) goto L_0x0040;
    L_0x0035:
        r4 = r8 + 1;
        r4 = r7[r4];
        if (r4 != r2) goto L_0x0040;
    L_0x003b:
        a(r10);
        r8 = r8 - r2;
        return r8;
    L_0x0040:
        r4 = r9 + -1;
        r8 = r8 + r3;
    L_0x0043:
        if (r8 >= r4) goto L_0x0067;
    L_0x0045:
        r5 = r7[r8];
        r5 = r5 & 254;
        if (r5 == 0) goto L_0x004c;
    L_0x004b:
        goto L_0x0064;
    L_0x004c:
        r5 = r8 + -2;
        r6 = r7[r5];
        if (r6 != 0) goto L_0x0062;
    L_0x0052:
        r6 = r8 + -1;
        r6 = r7[r6];
        if (r6 != 0) goto L_0x0062;
    L_0x0058:
        r6 = r7[r8];
        if (r6 != r2) goto L_0x0062;
    L_0x005c:
        if (r10 == 0) goto L_0x0061;
    L_0x005e:
        a(r10);
    L_0x0061:
        return r5;
    L_0x0062:
        r8 = r8 + -2;
    L_0x0064:
        r8 = r8 + 3;
        goto L_0x0043;
    L_0x0067:
        if (r10 == 0) goto L_0x00bd;
    L_0x0069:
        if (r0 <= r3) goto L_0x007f;
    L_0x006b:
        r8 = r9 + -3;
        r8 = r7[r8];
        if (r8 != 0) goto L_0x007d;
    L_0x0071:
        r8 = r9 + -2;
        r8 = r7[r8];
        if (r8 != 0) goto L_0x007d;
    L_0x0077:
        r8 = r7[r4];
        if (r8 != r2) goto L_0x007d;
    L_0x007b:
        r8 = r2;
        goto L_0x0099;
    L_0x007d:
        r8 = r1;
        goto L_0x0099;
    L_0x007f:
        if (r0 != r3) goto L_0x0090;
    L_0x0081:
        r8 = r10[r3];
        if (r8 == 0) goto L_0x007d;
    L_0x0085:
        r8 = r9 + -2;
        r8 = r7[r8];
        if (r8 != 0) goto L_0x007d;
    L_0x008b:
        r8 = r7[r4];
        if (r8 != r2) goto L_0x007d;
    L_0x008f:
        goto L_0x007b;
    L_0x0090:
        r8 = r10[r2];
        if (r8 == 0) goto L_0x007d;
    L_0x0094:
        r8 = r7[r4];
        if (r8 != r2) goto L_0x007d;
    L_0x0098:
        goto L_0x007b;
    L_0x0099:
        r10[r1] = r8;
        if (r0 <= r2) goto L_0x00ab;
    L_0x009d:
        r8 = r9 + -2;
        r8 = r7[r8];
        if (r8 != 0) goto L_0x00a9;
    L_0x00a3:
        r8 = r7[r4];
        if (r8 != 0) goto L_0x00a9;
    L_0x00a7:
        r8 = r2;
        goto L_0x00b4;
    L_0x00a9:
        r8 = r1;
        goto L_0x00b4;
    L_0x00ab:
        r8 = r10[r3];
        if (r8 == 0) goto L_0x00a9;
    L_0x00af:
        r8 = r7[r4];
        if (r8 != 0) goto L_0x00a9;
    L_0x00b3:
        goto L_0x00a7;
    L_0x00b4:
        r10[r2] = r8;
        r7 = r7[r4];
        if (r7 != 0) goto L_0x00bb;
    L_0x00ba:
        r1 = r2;
    L_0x00bb:
        r10[r3] = r1;
    L_0x00bd:
        return r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.fn.a(byte[], int, int, boolean[]):int");
    }

    public static void a(boolean[] zArr) {
        zArr[0] = false;
        zArr[1] = false;
        zArr[2] = false;
    }

    private static int a(byte[] bArr, int i, int i2) {
        while (i < i2 - 2) {
            if (bArr[i] == (byte) 0 && bArr[i + 1] == (byte) 0 && bArr[i + 2] == (byte) 3) {
                return i;
            }
            i++;
        }
        return i2;
    }

    private static void a(fo foVar, int i) {
        int i2 = 8;
        int i3 = 8;
        for (int i4 = 0; i4 < i; i4++) {
            if (i2 != 0) {
                i2 = ((foVar.e() + i3) + 256) % 256;
            }
            if (i2 != 0) {
                i3 = i2;
            }
        }
    }
}
