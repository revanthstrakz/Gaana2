package com.helpshift.websockets;

class n {
    private final int a;
    private final int b;
    private final int[] c;
    private final int[] d;

    public n(int[] iArr) {
        this.a = Math.max(p.a(iArr), 1);
        this.b = p.b(iArr);
        Object[] objArr = new Object[2];
        this.c = a(a(iArr, this.b), this.b, objArr);
        this.d = a(iArr, (int[]) objArr[0], ((Integer) objArr[1]).intValue());
    }

    private static int[] a(int i, int i2) {
        int[] iArr = new int[i];
        for (int i3 = 0; i3 < i; i3++) {
            iArr[i3] = i2;
        }
        return iArr;
    }

    private static int[] a(int[] iArr, int i) {
        int[] iArr2 = new int[(i + 1)];
        for (int i2 : iArr) {
            iArr2[i2] = iArr2[i2] + 1;
        }
        return iArr2;
    }

    private static int[] a(int[] iArr, int i, Object[] objArr) {
        i++;
        int[] a = a(i, -1);
        iArr[0] = 0;
        int[] iArr2 = new int[i];
        int i2 = 0;
        int i3 = i2;
        for (int i4 = 1; i4 < iArr.length; i4++) {
            i2 = (i2 + iArr[i4 - 1]) << 1;
            iArr2[i4] = i2;
            i3 = (iArr[i4] + i2) - 1;
            a[i4] = i3;
        }
        objArr[0] = iArr2;
        objArr[1] = Integer.valueOf(i3);
        return a;
    }

    private static int[] a(int[] iArr, int[] iArr2, int i) {
        int[] iArr3 = new int[(i + 1)];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            int i3 = iArr[i2];
            if (i3 != 0) {
                int i4 = iArr2[i3];
                iArr2[i3] = i4 + 1;
                iArr3[i4] = i2;
            }
        }
        return iArr3;
    }

    public int a(c cVar, int[] iArr) throws FormatException {
        for (int i = this.a; i <= this.b; i++) {
            int i2 = this.c[i];
            if (i2 >= 0) {
                int c = cVar.c(iArr[0], i);
                if (i2 >= c) {
                    int i3 = this.d[c];
                    iArr[0] = iArr[0] + i;
                    return i3;
                }
            }
        }
        throw new FormatException(String.format("[%s] Bad code at the bit index '%d'.", new Object[]{getClass().getSimpleName(), Integer.valueOf(iArr[0])}));
    }
}
