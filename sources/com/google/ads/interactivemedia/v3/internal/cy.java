package com.google.ads.interactivemedia.v3.internal;

final class cy {

    public static final class a {
        public final long[] a;
        public final int[] b;
        public final int c;
        public final long[] d;
        public final int[] e;

        public a(long[] jArr, int[] iArr, int i, long[] jArr2, int[] iArr2) {
            this.a = jArr;
            this.b = iArr;
            this.c = i;
            this.d = jArr2;
            this.e = iArr2;
        }
    }

    public static a a(int i, long[] jArr, int[] iArr, long j) {
        int[] iArr2 = iArr;
        int i2 = 8192 / i;
        int i3 = 0;
        int i4 = 0;
        int i5 = i4;
        while (i4 < iArr2.length) {
            i5 += ft.a(iArr2[i4], i2);
            i4++;
        }
        long[] jArr2 = new long[i5];
        int[] iArr3 = new int[i5];
        long[] jArr3 = new long[i5];
        int[] iArr4 = new int[i5];
        int i6 = 0;
        i4 = i6;
        int i7 = i4;
        while (i3 < iArr2.length) {
            i5 = iArr2[i3];
            long j2 = jArr[i3];
            while (i5 > 0) {
                int min = Math.min(i2, i5);
                jArr2[i6] = j2;
                iArr3[i6] = i * min;
                i7 = Math.max(i7, iArr3[i6]);
                jArr3[i6] = ((long) i4) * j;
                iArr4[i6] = 1;
                i4 += min;
                i5 -= min;
                i6++;
                j2 += (long) iArr3[i6];
                iArr2 = iArr;
            }
            i3++;
            iArr2 = iArr;
        }
        return new a(jArr2, iArr3, i7, jArr3, iArr4);
    }
}
