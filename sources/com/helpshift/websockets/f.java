package com.helpshift.websockets;

class f {
    f() {
    }

    public static void a(c cVar, c cVar2) throws FormatException {
        while (a(cVar, new int[]{0}, cVar2)) {
        }
    }

    private static boolean a(c cVar, int[] iArr, c cVar2) throws FormatException {
        boolean a = cVar.a(iArr);
        switch (cVar.a(iArr, 2)) {
            case 0:
                b(cVar, iArr, cVar2);
                break;
            case 1:
                c(cVar, iArr, cVar2);
                break;
            case 2:
                d(cVar, iArr, cVar2);
                break;
            default:
                throw new FormatException(String.format("[%s] Bad compression type '11' at the bit index '%d'.", new Object[]{f.class.getSimpleName(), Integer.valueOf(iArr[0])}));
        }
        if (cVar.a() <= iArr[0] / 8) {
            a = true;
        }
        if (a) {
            return false;
        }
        return true;
    }

    private static void b(c cVar, int[] iArr, c cVar2) {
        int i = ((iArr[0] + 7) & -8) / 8;
        int a = (cVar.a(i) & 255) + ((cVar.a(i + 1) & 255) * 256);
        i += 4;
        cVar2.a(cVar, i, a);
        iArr[0] = (i + a) * 8;
    }

    private static void c(c cVar, int[] iArr, c cVar2) throws FormatException {
        a(cVar, iArr, cVar2, k.a(), j.a());
    }

    private static void d(c cVar, int[] iArr, c cVar2) throws FormatException {
        n[] nVarArr = new n[2];
        g.a(cVar, iArr, nVarArr);
        a(cVar, iArr, cVar2, nVarArr[0], nVarArr[1]);
    }

    private static void a(c cVar, int[] iArr, c cVar2, n nVar, n nVar2) throws FormatException {
        while (true) {
            int a = nVar.a(cVar, iArr);
            if (a != 256) {
                if (a < 0 || a > 255) {
                    a(g.a(cVar, iArr, a), g.a(cVar, iArr, nVar2), cVar2);
                } else {
                    cVar2.b(a);
                }
            } else {
                return;
            }
        }
    }

    private static void a(int i, int i2, c cVar) {
        int a = cVar.a();
        byte[] bArr = new byte[i];
        i2 = a - i2;
        int i3 = 0;
        int i4 = i2;
        while (i3 < i) {
            if (a <= i4) {
                i4 = i2;
            }
            bArr[i3] = cVar.a(i4);
            i3++;
            i4++;
        }
        cVar.a(bArr);
    }
}
