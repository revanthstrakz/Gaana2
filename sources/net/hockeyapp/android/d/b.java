package net.hockeyapp.android.d;

import com.google.android.exoplayer2.C;
import java.io.UnsupportedEncodingException;

public class b {

    static abstract class a {
        public byte[] a;
        public int b;

        a() {
        }
    }

    static class b extends a {
        private static final byte[] g = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 43, (byte) 47};
        private static final byte[] h = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 45, (byte) 95};
        int c;
        public final boolean d;
        public final boolean e;
        public final boolean f;
        private final byte[] i;
        private int j;
        private final byte[] k;

        public b(int i, byte[] bArr) {
            this.a = bArr;
            boolean z = true;
            this.d = (i & 1) == 0;
            this.e = (i & 2) == 0;
            if ((i & 4) == 0) {
                z = false;
            }
            this.f = z;
            this.k = (i & 8) == 0 ? g : h;
            this.i = new byte[2];
            this.c = 0;
            this.j = this.e ? 19 : -1;
        }

        public boolean a(byte[] r19, int r20, int r21, boolean r22) {
            /*
            r18 = this;
            r0 = r18;
            r3 = r0.k;
            r4 = r0.a;
            r5 = r0.j;
            r6 = r21 + r20;
            r7 = r0.c;
            r8 = -1;
            r9 = 0;
            r10 = 1;
            switch(r7) {
                case 0: goto L_0x004f;
                case 1: goto L_0x0030;
                case 2: goto L_0x0013;
                default: goto L_0x0012;
            };
        L_0x0012:
            goto L_0x004f;
        L_0x0013:
            r7 = r20 + 1;
            if (r7 > r6) goto L_0x004f;
        L_0x0017:
            r11 = r0.i;
            r11 = r11[r9];
            r11 = r11 & 255;
            r11 = r11 << 16;
            r12 = r0.i;
            r12 = r12[r10];
            r12 = r12 & 255;
            r12 = r12 << 8;
            r11 = r11 | r12;
            r2 = r19[r20];
            r2 = r2 & 255;
            r2 = r2 | r11;
            r0.c = r9;
            goto L_0x0052;
        L_0x0030:
            r7 = r20 + 2;
            if (r7 > r6) goto L_0x004f;
        L_0x0034:
            r7 = r0.i;
            r7 = r7[r9];
            r7 = r7 & 255;
            r7 = r7 << 16;
            r11 = r20 + 1;
            r2 = r19[r20];
            r2 = r2 & 255;
            r2 = r2 << 8;
            r2 = r2 | r7;
            r7 = r11 + 1;
            r11 = r19[r11];
            r11 = r11 & 255;
            r2 = r2 | r11;
            r0.c = r9;
            goto L_0x0052;
        L_0x004f:
            r7 = r20;
            r2 = r8;
        L_0x0052:
            r12 = 4;
            r13 = 13;
            r14 = 10;
            r15 = 2;
            if (r2 == r8) goto L_0x0090;
        L_0x005a:
            r8 = r2 >> 18;
            r8 = r8 & 63;
            r8 = r3[r8];
            r4[r9] = r8;
            r8 = r2 >> 12;
            r8 = r8 & 63;
            r8 = r3[r8];
            r4[r10] = r8;
            r8 = r2 >> 6;
            r8 = r8 & 63;
            r8 = r3[r8];
            r4[r15] = r8;
            r2 = r2 & 63;
            r2 = r3[r2];
            r8 = 3;
            r4[r8] = r2;
            r5 = r5 + -1;
            if (r5 != 0) goto L_0x008d;
        L_0x007d:
            r2 = r0.f;
            if (r2 == 0) goto L_0x0085;
        L_0x0081:
            r2 = 5;
            r4[r12] = r13;
            goto L_0x0086;
        L_0x0085:
            r2 = r12;
        L_0x0086:
            r5 = r2 + 1;
            r4[r2] = r14;
            r2 = 19;
            goto L_0x0092;
        L_0x008d:
            r2 = r5;
            r5 = r12;
            goto L_0x0092;
        L_0x0090:
            r2 = r5;
            r5 = r9;
        L_0x0092:
            r8 = r7 + 3;
            if (r8 > r6) goto L_0x00eb;
        L_0x0096:
            r11 = r19[r7];
            r11 = r11 & 255;
            r11 = r11 << 16;
            r16 = r7 + 1;
            r15 = r19[r16];
            r15 = r15 & 255;
            r15 = r15 << 8;
            r11 = r11 | r15;
            r7 = r7 + 2;
            r7 = r19[r7];
            r7 = r7 & 255;
            r7 = r7 | r11;
            r11 = r7 >> 18;
            r11 = r11 & 63;
            r11 = r3[r11];
            r4[r5] = r11;
            r11 = r5 + 1;
            r15 = r7 >> 12;
            r15 = r15 & 63;
            r15 = r3[r15];
            r4[r11] = r15;
            r11 = r5 + 2;
            r15 = r7 >> 6;
            r15 = r15 & 63;
            r15 = r3[r15];
            r4[r11] = r15;
            r11 = r5 + 3;
            r7 = r7 & 63;
            r7 = r3[r7];
            r4[r11] = r7;
            r5 = r5 + 4;
            r2 = r2 + -1;
            if (r2 != 0) goto L_0x00e8;
        L_0x00d6:
            r2 = r0.f;
            if (r2 == 0) goto L_0x00df;
        L_0x00da:
            r2 = r5 + 1;
            r4[r5] = r13;
            goto L_0x00e0;
        L_0x00df:
            r2 = r5;
        L_0x00e0:
            r5 = r2 + 1;
            r4[r2] = r14;
            r7 = r8;
            r2 = 19;
            goto L_0x00e9;
        L_0x00e8:
            r7 = r8;
        L_0x00e9:
            r15 = 2;
            goto L_0x0092;
        L_0x00eb:
            if (r22 == 0) goto L_0x01e4;
        L_0x00ed:
            r8 = r0.c;
            r8 = r7 - r8;
            r11 = r6 + -1;
            if (r8 != r11) goto L_0x0140;
        L_0x00f5:
            r8 = r0.c;
            if (r8 <= 0) goto L_0x00ff;
        L_0x00f9:
            r1 = r0.i;
            r1 = r1[r9];
            r9 = r10;
            goto L_0x0104;
        L_0x00ff:
            r8 = r7 + 1;
            r1 = r19[r7];
            r7 = r8;
        L_0x0104:
            r1 = r1 & 255;
            r1 = r1 << r12;
            r8 = r0.c;
            r8 = r8 - r9;
            r0.c = r8;
            r8 = r5 + 1;
            r9 = r1 >> 6;
            r9 = r9 & 63;
            r9 = r3[r9];
            r4[r5] = r9;
            r5 = r8 + 1;
            r1 = r1 & 63;
            r1 = r3[r1];
            r4[r8] = r1;
            r1 = r0.d;
            if (r1 == 0) goto L_0x012c;
        L_0x0122:
            r1 = r5 + 1;
            r3 = 61;
            r4[r5] = r3;
            r5 = r1 + 1;
            r4[r1] = r3;
        L_0x012c:
            r1 = r0.e;
            if (r1 == 0) goto L_0x01cf;
        L_0x0130:
            r1 = r0.f;
            if (r1 == 0) goto L_0x0139;
        L_0x0134:
            r1 = r5 + 1;
            r4[r5] = r13;
            goto L_0x013a;
        L_0x0139:
            r1 = r5;
        L_0x013a:
            r5 = r1 + 1;
            r4[r1] = r14;
            goto L_0x01cf;
        L_0x0140:
            r8 = r0.c;
            r8 = r7 - r8;
            r11 = r6 + -2;
            if (r8 != r11) goto L_0x01b6;
        L_0x0148:
            r8 = r0.c;
            if (r8 <= r10) goto L_0x0157;
        L_0x014c:
            r8 = r0.i;
            r8 = r8[r9];
            r9 = r10;
            r17 = r8;
            r8 = r7;
            r7 = r17;
            goto L_0x015b;
        L_0x0157:
            r8 = r7 + 1;
            r7 = r19[r7];
        L_0x015b:
            r7 = r7 & 255;
            r7 = r7 << r14;
            r11 = r0.c;
            if (r11 <= 0) goto L_0x016b;
        L_0x0162:
            r1 = r0.i;
            r11 = r9 + 1;
            r1 = r1[r9];
            r9 = r11;
            r11 = r8;
            goto L_0x016f;
        L_0x016b:
            r11 = r8 + 1;
            r1 = r19[r8];
        L_0x016f:
            r1 = r1 & 255;
            r8 = 2;
            r1 = r1 << r8;
            r1 = r1 | r7;
            r7 = r0.c;
            r7 = r7 - r9;
            r0.c = r7;
            r7 = r5 + 1;
            r8 = r1 >> 12;
            r8 = r8 & 63;
            r8 = r3[r8];
            r4[r5] = r8;
            r5 = r7 + 1;
            r8 = r1 >> 6;
            r8 = r8 & 63;
            r8 = r3[r8];
            r4[r7] = r8;
            r7 = r5 + 1;
            r1 = r1 & 63;
            r1 = r3[r1];
            r4[r5] = r1;
            r1 = r0.d;
            if (r1 == 0) goto L_0x01a0;
        L_0x0199:
            r1 = r7 + 1;
            r3 = 61;
            r4[r7] = r3;
            goto L_0x01a1;
        L_0x01a0:
            r1 = r7;
        L_0x01a1:
            r3 = r0.e;
            if (r3 == 0) goto L_0x01b3;
        L_0x01a5:
            r3 = r0.f;
            if (r3 == 0) goto L_0x01ae;
        L_0x01a9:
            r3 = r1 + 1;
            r4[r1] = r13;
            r1 = r3;
        L_0x01ae:
            r3 = r1 + 1;
            r4[r1] = r14;
            r1 = r3;
        L_0x01b3:
            r5 = r1;
            r7 = r11;
            goto L_0x01cf;
        L_0x01b6:
            r1 = r0.e;
            if (r1 == 0) goto L_0x01cf;
        L_0x01ba:
            if (r5 <= 0) goto L_0x01cf;
        L_0x01bc:
            r1 = 19;
            if (r2 == r1) goto L_0x01cf;
        L_0x01c0:
            r1 = r0.f;
            if (r1 == 0) goto L_0x01c9;
        L_0x01c4:
            r1 = r5 + 1;
            r4[r5] = r13;
            goto L_0x01ca;
        L_0x01c9:
            r1 = r5;
        L_0x01ca:
            r3 = r1 + 1;
            r4[r1] = r14;
            r5 = r3;
        L_0x01cf:
            r1 = r0.c;
            if (r1 == 0) goto L_0x01da;
        L_0x01d3:
            r1 = "BASE64";
            r3 = "Error during encoding";
            net.hockeyapp.android.d.d.d(r1, r3);
        L_0x01da:
            if (r7 == r6) goto L_0x0212;
        L_0x01dc:
            r1 = "BASE64";
            r3 = "Error during encoding";
            net.hockeyapp.android.d.d.d(r1, r3);
            goto L_0x0212;
        L_0x01e4:
            r3 = r6 + -1;
            if (r7 != r3) goto L_0x01f5;
        L_0x01e8:
            r3 = r0.i;
            r4 = r0.c;
            r6 = r4 + 1;
            r0.c = r6;
            r1 = r19[r7];
            r3[r4] = r1;
            goto L_0x0212;
        L_0x01f5:
            r3 = 2;
            r6 = r6 - r3;
            if (r7 != r6) goto L_0x0212;
        L_0x01f9:
            r3 = r0.i;
            r4 = r0.c;
            r6 = r4 + 1;
            r0.c = r6;
            r6 = r19[r7];
            r3[r4] = r6;
            r3 = r0.i;
            r4 = r0.c;
            r6 = r4 + 1;
            r0.c = r6;
            r7 = r7 + r10;
            r1 = r19[r7];
            r3[r4] = r1;
        L_0x0212:
            r0.b = r5;
            r0.j = r2;
            return r10;
            */
            throw new UnsupportedOperationException("Method not decompiled: net.hockeyapp.android.d.b$b.a(byte[], int, int, boolean):boolean");
        }
    }

    public static String a(byte[] bArr, int i) {
        try {
            return new String(b(bArr, i), C.ASCII_NAME);
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public static byte[] b(byte[] bArr, int i) {
        return a(bArr, 0, bArr.length, i);
    }

    public static byte[] a(byte[] bArr, int i, int i2, int i3) {
        b bVar = new b(i3, null);
        i3 = (i2 / 3) * 4;
        if (!bVar.d) {
            switch (i2 % 3) {
                case 1:
                    i3 += 2;
                    break;
                case 2:
                    i3 += 3;
                    break;
            }
        } else if (i2 % 3 > 0) {
            i3 += 4;
        }
        if (bVar.e && i2 > 0) {
            i3 += (((i2 - 1) / 57) + 1) * (bVar.f ? 2 : 1);
        }
        bVar.a = new byte[i3];
        bVar.a(bArr, i, i2, true);
        if (bVar.b == i3) {
            return bVar.a;
        }
        throw new AssertionError();
    }
}
