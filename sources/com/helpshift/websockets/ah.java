package com.helpshift.websockets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ah {
    private boolean a;
    private boolean b;
    private boolean c;
    private boolean d;
    private int e;
    private boolean f;
    private byte[] g;

    public boolean a() {
        return this.a;
    }

    public ah a(boolean z) {
        this.a = z;
        return this;
    }

    public boolean b() {
        return this.b;
    }

    public ah b(boolean z) {
        this.b = z;
        return this;
    }

    public boolean c() {
        return this.c;
    }

    public ah c(boolean z) {
        this.c = z;
        return this;
    }

    public boolean d() {
        return this.d;
    }

    public ah d(boolean z) {
        this.d = z;
        return this;
    }

    public int e() {
        return this.e;
    }

    public ah a(int i) {
        this.e = i;
        return this;
    }

    public boolean f() {
        return this.e == 0;
    }

    public boolean g() {
        return this.e == 1;
    }

    public boolean h() {
        return this.e == 2;
    }

    public boolean i() {
        return this.e == 8;
    }

    public boolean j() {
        return this.e == 9;
    }

    public boolean k() {
        return this.e == 10;
    }

    public boolean l() {
        return 8 <= this.e && this.e <= 15;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean m() {
        return this.f;
    }

    /* Access modifiers changed, original: 0000 */
    public ah e(boolean z) {
        this.f = z;
        return this;
    }

    public int n() {
        if (this.g == null) {
            return 0;
        }
        return this.g.length;
    }

    public byte[] o() {
        return this.g;
    }

    public String p() {
        if (this.g == null) {
            return null;
        }
        return p.a(this.g);
    }

    public ah a(byte[] bArr) {
        if (bArr != null && bArr.length == 0) {
            bArr = null;
        }
        this.g = bArr;
        return this;
    }

    public ah a(String str) {
        if (str == null || str.length() == 0) {
            return a((byte[]) null);
        }
        return a(p.a(str));
    }

    public ah a(int i, String str) {
        byte[] bArr = new byte[]{(byte) ((i >> 8) & 255), (byte) (i & 255)};
        if (str == null || str.length() == 0) {
            return a(bArr);
        }
        byte[] a = p.a(str);
        byte[] bArr2 = new byte[(a.length + 2)];
        System.arraycopy(bArr, 0, bArr2, 0, 2);
        System.arraycopy(a, 0, bArr2, 2, a.length);
        return a(bArr2);
    }

    public int q() {
        return (this.g == null || this.g.length < 2) ? 1005 : ((this.g[0] & 255) << 8) | (this.g[1] & 255);
    }

    public String r() {
        return (this.g == null || this.g.length < 3) ? null : p.a(this.g, 2, this.g.length - 2);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("WebSocketFrame(FIN=");
        stringBuilder.append(this.a ? "1" : "0");
        stringBuilder.append(",RSV1=");
        stringBuilder.append(this.b ? "1" : "0");
        stringBuilder.append(",RSV2=");
        stringBuilder.append(this.c ? "1" : "0");
        stringBuilder.append(",RSV3=");
        stringBuilder.append(this.d ? "1" : "0");
        stringBuilder.append(",Opcode=");
        stringBuilder.append(p.b(this.e));
        stringBuilder.append(",Length=");
        stringBuilder.append(n());
        int i = this.e;
        if (i != 8) {
            switch (i) {
                case 1:
                    b(stringBuilder);
                    break;
                case 2:
                    d(stringBuilder);
                    break;
            }
        }
        c(stringBuilder);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    private boolean a(StringBuilder stringBuilder) {
        stringBuilder.append(",Payload=");
        if (this.g == null) {
            stringBuilder.append("null");
            return true;
        } else if (!this.b) {
            return false;
        } else {
            stringBuilder.append("compressed");
            return true;
        }
    }

    private void b(StringBuilder stringBuilder) {
        if (!a(stringBuilder)) {
            stringBuilder.append("\"");
            stringBuilder.append(p());
            stringBuilder.append("\"");
        }
    }

    private void c(StringBuilder stringBuilder) {
        stringBuilder.append(",CloseCode=");
        stringBuilder.append(q());
        stringBuilder.append(",Reason=");
        String r = r();
        if (r == null) {
            stringBuilder.append("null");
            return;
        }
        stringBuilder.append("\"");
        stringBuilder.append(r);
        stringBuilder.append("\"");
    }

    private void d(StringBuilder stringBuilder) {
        if (!a(stringBuilder)) {
            for (int i = 0; i < this.g.length; i++) {
                stringBuilder.append(String.format("%02X ", new Object[]{Integer.valueOf(255 & this.g[i])}));
            }
            if (this.g.length != 0) {
                stringBuilder.setLength(stringBuilder.length() - 1);
            }
        }
    }

    public static ah s() {
        return new ah().a(0);
    }

    public static ah b(byte[] bArr) {
        return s().a(bArr);
    }

    public static ah b(String str) {
        return new ah().a(true).a(1).a(str);
    }

    public static ah t() {
        return new ah().a(true).a(8);
    }

    public static ah b(int i, String str) {
        return t().a(i, str);
    }

    public static ah u() {
        return new ah().a(true).a(9);
    }

    public static ah c(byte[] bArr) {
        return u().a(bArr);
    }

    public static ah v() {
        return new ah().a(true).a(10);
    }

    public static ah d(byte[] bArr) {
        return v().a(bArr);
    }

    static byte[] a(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr.length < 4 || bArr2 == null) {
            return bArr2;
        }
        for (int i = 0; i < bArr2.length; i++) {
            bArr2[i] = (byte) (bArr2[i] ^ bArr[i % 4]);
        }
        return bArr2;
    }

    static ah a(ah ahVar, s sVar) {
        if (sVar == null) {
            return ahVar;
        }
        if ((!ahVar.g() && !ahVar.h()) || !ahVar.a() || ahVar.b()) {
            return ahVar;
        }
        byte[] o = ahVar.o();
        if (o == null || o.length == 0) {
            return ahVar;
        }
        ahVar.a(a(o, sVar));
        ahVar.b(true);
        return ahVar;
    }

    private static byte[] a(byte[] bArr, s sVar) {
        try {
            return sVar.b(bArr);
        } catch (WebSocketException unused) {
            return bArr;
        }
    }

    static List<ah> a(ah ahVar, int i, s sVar) {
        if (i == 0 || ahVar.n() <= i) {
            return null;
        }
        if (ahVar.h() || ahVar.g()) {
            ahVar = a(ahVar, sVar);
            if (ahVar.n() <= i) {
                return null;
            }
        } else if (!ahVar.f()) {
            return null;
        }
        return a(ahVar, i);
    }

    private static List<ah> a(ah ahVar, int i) {
        byte[] o = ahVar.o();
        boolean a = ahVar.a();
        ArrayList arrayList = new ArrayList();
        ahVar.a(false).a(Arrays.copyOf(o, i));
        arrayList.add(ahVar);
        int i2 = i;
        while (i2 < o.length) {
            int i3 = i2 + i;
            arrayList.add(b(Arrays.copyOfRange(o, i2, Math.min(i3, o.length))));
            i2 = i3;
        }
        if (a) {
            ((ah) arrayList.get(arrayList.size() - 1)).a(true);
        }
        return arrayList;
    }
}
