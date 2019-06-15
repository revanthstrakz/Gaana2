package io.branch.referral;

import com.google.android.exoplayer2.C;
import java.io.UnsupportedEncodingException;

class c {

    static abstract class a {
        public byte[] a;
        public int b;

        a() {
        }
    }

    static class b extends a {
        private static final int[] c = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        private static final int[] d = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        private int e;
        private int f;
        private final int[] g;

        public b(int i, byte[] bArr) {
            this.a = bArr;
            this.g = (i & 8) == 0 ? c : d;
            this.e = 0;
            this.f = 0;
        }

        /* JADX WARNING: Removed duplicated region for block: B:54:0x00ec  */
        /* JADX WARNING: Removed duplicated region for block: B:52:0x00e5  */
        /* JADX WARNING: Missing block: B:20:0x0074, code skipped:
            r0 = r13;
     */
        /* JADX WARNING: Missing block: B:45:0x00d8, code skipped:
            r6 = r13;
     */
        public boolean a(byte[] r12, int r13, int r14, boolean r15) {
            /*
            r11 = this;
            r0 = r11.e;
            r1 = 0;
            r2 = 6;
            if (r0 != r2) goto L_0x0007;
        L_0x0006:
            return r1;
        L_0x0007:
            r14 = r14 + r13;
            r0 = r11.e;
            r3 = r11.f;
            r4 = r11.a;
            r5 = r11.g;
            r6 = r3;
            r3 = r1;
        L_0x0012:
            r7 = 4;
            if (r13 >= r14) goto L_0x00e2;
        L_0x0015:
            if (r0 != 0) goto L_0x005c;
        L_0x0017:
            r8 = r13 + 4;
            if (r8 > r14) goto L_0x0058;
        L_0x001b:
            r6 = r12[r13];
            r6 = r6 & 255;
            r6 = r5[r6];
            r6 = r6 << 18;
            r9 = r13 + 1;
            r9 = r12[r9];
            r9 = r9 & 255;
            r9 = r5[r9];
            r9 = r9 << 12;
            r6 = r6 | r9;
            r9 = r13 + 2;
            r9 = r12[r9];
            r9 = r9 & 255;
            r9 = r5[r9];
            r9 = r9 << r2;
            r6 = r6 | r9;
            r9 = r13 + 3;
            r9 = r12[r9];
            r9 = r9 & 255;
            r9 = r5[r9];
            r6 = r6 | r9;
            if (r6 < 0) goto L_0x0058;
        L_0x0043:
            r13 = r3 + 2;
            r9 = (byte) r6;
            r4[r13] = r9;
            r13 = r3 + 1;
            r9 = r6 >> 8;
            r9 = (byte) r9;
            r4[r13] = r9;
            r13 = r6 >> 16;
            r13 = (byte) r13;
            r4[r3] = r13;
            r3 = r3 + 3;
            r13 = r8;
            goto L_0x0017;
        L_0x0058:
            if (r13 < r14) goto L_0x005c;
        L_0x005a:
            goto L_0x00e2;
        L_0x005c:
            r8 = r13 + 1;
            r13 = r12[r13];
            r13 = r13 & 255;
            r13 = r5[r13];
            r9 = -2;
            r10 = -1;
            switch(r0) {
                case 0: goto L_0x00d4;
                case 1: goto L_0x00c7;
                case 2: goto L_0x00ae;
                case 3: goto L_0x007c;
                case 4: goto L_0x0070;
                case 5: goto L_0x006b;
                default: goto L_0x0069;
            };
        L_0x0069:
            goto L_0x00df;
        L_0x006b:
            if (r13 == r10) goto L_0x00df;
        L_0x006d:
            r11.e = r2;
            return r1;
        L_0x0070:
            if (r13 != r9) goto L_0x0077;
        L_0x0072:
            r13 = r0 + 1;
        L_0x0074:
            r0 = r13;
            goto L_0x00df;
        L_0x0077:
            if (r13 == r10) goto L_0x00df;
        L_0x0079:
            r11.e = r2;
            return r1;
        L_0x007c:
            if (r13 < 0) goto L_0x0097;
        L_0x007e:
            r0 = r6 << 6;
            r13 = r13 | r0;
            r0 = r3 + 2;
            r6 = (byte) r13;
            r4[r0] = r6;
            r0 = r3 + 1;
            r6 = r13 >> 8;
            r6 = (byte) r6;
            r4[r0] = r6;
            r0 = r13 >> 16;
            r0 = (byte) r0;
            r4[r3] = r0;
            r3 = r3 + 3;
            r6 = r13;
            r0 = r1;
            goto L_0x00df;
        L_0x0097:
            if (r13 != r9) goto L_0x00a9;
        L_0x0099:
            r13 = r3 + 1;
            r0 = r6 >> 2;
            r0 = (byte) r0;
            r4[r13] = r0;
            r13 = r6 >> 10;
            r13 = (byte) r13;
            r4[r3] = r13;
            r3 = r3 + 2;
            r13 = 5;
            goto L_0x0074;
        L_0x00a9:
            if (r13 == r10) goto L_0x00df;
        L_0x00ab:
            r11.e = r2;
            return r1;
        L_0x00ae:
            if (r13 < 0) goto L_0x00b6;
        L_0x00b0:
            r6 = r6 << 6;
            r13 = r13 | r6;
            r0 = r0 + 1;
            goto L_0x00d8;
        L_0x00b6:
            if (r13 != r9) goto L_0x00c2;
        L_0x00b8:
            r13 = r3 + 1;
            r0 = r6 >> 4;
            r0 = (byte) r0;
            r4[r3] = r0;
            r3 = r13;
            r0 = r7;
            goto L_0x00df;
        L_0x00c2:
            if (r13 == r10) goto L_0x00df;
        L_0x00c4:
            r11.e = r2;
            return r1;
        L_0x00c7:
            if (r13 < 0) goto L_0x00cf;
        L_0x00c9:
            r6 = r6 << 6;
            r13 = r13 | r6;
            r0 = r0 + 1;
            goto L_0x00d8;
        L_0x00cf:
            if (r13 == r10) goto L_0x00df;
        L_0x00d1:
            r11.e = r2;
            return r1;
        L_0x00d4:
            if (r13 < 0) goto L_0x00da;
        L_0x00d6:
            r0 = r0 + 1;
        L_0x00d8:
            r6 = r13;
            goto L_0x00df;
        L_0x00da:
            if (r13 == r10) goto L_0x00df;
        L_0x00dc:
            r11.e = r2;
            return r1;
        L_0x00df:
            r13 = r8;
            goto L_0x0012;
        L_0x00e2:
            r12 = 1;
            if (r15 != 0) goto L_0x00ec;
        L_0x00e5:
            r11.e = r0;
            r11.f = r6;
            r11.b = r3;
            return r12;
        L_0x00ec:
            switch(r0) {
                case 0: goto L_0x010e;
                case 1: goto L_0x010b;
                case 2: goto L_0x0102;
                case 3: goto L_0x00f3;
                case 4: goto L_0x00f0;
                default: goto L_0x00ef;
            };
        L_0x00ef:
            goto L_0x010e;
        L_0x00f0:
            r11.e = r2;
            return r1;
        L_0x00f3:
            r13 = r3 + 1;
            r14 = r6 >> 10;
            r14 = (byte) r14;
            r4[r3] = r14;
            r3 = r13 + 1;
            r14 = r6 >> 2;
            r14 = (byte) r14;
            r4[r13] = r14;
            goto L_0x010e;
        L_0x0102:
            r13 = r3 + 1;
            r14 = r6 >> 4;
            r14 = (byte) r14;
            r4[r3] = r14;
            r3 = r13;
            goto L_0x010e;
        L_0x010b:
            r11.e = r2;
            return r1;
        L_0x010e:
            r11.e = r0;
            r11.b = r3;
            return r12;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.c$b.a(byte[], int, int, boolean):boolean");
        }
    }

    static class c extends a {
        private static final byte[] g = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 43, (byte) 47};
        private static final byte[] h = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 45, (byte) 95};
        int c;
        public final boolean d;
        public final boolean e;
        public final boolean f;
        private final byte[] i;
        private int j;
        private final byte[] k;

        public c(int i, byte[] bArr) {
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
            r10 = r19[r16];
            r10 = r10 & 255;
            r10 = r10 << 8;
            r10 = r10 | r11;
            r7 = r7 + 2;
            r7 = r19[r7];
            r7 = r7 & 255;
            r7 = r7 | r10;
            r10 = r7 >> 18;
            r10 = r10 & 63;
            r10 = r3[r10];
            r4[r5] = r10;
            r10 = r5 + 1;
            r11 = r7 >> 12;
            r11 = r11 & 63;
            r11 = r3[r11];
            r4[r10] = r11;
            r10 = r5 + 2;
            r11 = r7 >> 6;
            r11 = r11 & 63;
            r11 = r3[r11];
            r4[r10] = r11;
            r10 = r5 + 3;
            r7 = r7 & 63;
            r7 = r3[r7];
            r4[r10] = r7;
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
            r10 = 1;
            goto L_0x0092;
        L_0x00eb:
            if (r22 == 0) goto L_0x01ca;
        L_0x00ed:
            r8 = r0.c;
            r8 = r7 - r8;
            r10 = r6 + -1;
            if (r8 != r10) goto L_0x013f;
        L_0x00f5:
            r6 = r0.c;
            if (r6 <= 0) goto L_0x00ff;
        L_0x00f9:
            r1 = r0.i;
            r1 = r1[r9];
            r9 = 1;
            goto L_0x0101;
        L_0x00ff:
            r1 = r19[r7];
        L_0x0101:
            r1 = r1 & 255;
            r1 = r1 << r12;
            r6 = r0.c;
            r6 = r6 - r9;
            r0.c = r6;
            r6 = r5 + 1;
            r7 = r1 >> 6;
            r7 = r7 & 63;
            r7 = r3[r7];
            r4[r5] = r7;
            r5 = r6 + 1;
            r1 = r1 & 63;
            r1 = r3[r1];
            r4[r6] = r1;
            r1 = r0.d;
            if (r1 == 0) goto L_0x0129;
        L_0x011f:
            r1 = r5 + 1;
            r3 = 61;
            r4[r5] = r3;
            r5 = r1 + 1;
            r4[r1] = r3;
        L_0x0129:
            r1 = r0.e;
            if (r1 == 0) goto L_0x013c;
        L_0x012d:
            r1 = r0.f;
            if (r1 == 0) goto L_0x0136;
        L_0x0131:
            r1 = r5 + 1;
            r4[r5] = r13;
            goto L_0x0137;
        L_0x0136:
            r1 = r5;
        L_0x0137:
            r3 = r1 + 1;
            r4[r1] = r14;
        L_0x013b:
            r5 = r3;
        L_0x013c:
            r6 = 1;
            goto L_0x01f9;
        L_0x013f:
            r8 = r0.c;
            r8 = r7 - r8;
            r6 = r6 - r15;
            if (r8 != r6) goto L_0x01b0;
        L_0x0146:
            r6 = r0.c;
            r8 = 1;
            if (r6 <= r8) goto L_0x0151;
        L_0x014b:
            r6 = r0.i;
            r6 = r6[r9];
            r9 = 1;
            goto L_0x015a;
        L_0x0151:
            r6 = r7 + 1;
            r7 = r19[r7];
            r17 = r7;
            r7 = r6;
            r6 = r17;
        L_0x015a:
            r6 = r6 & 255;
            r6 = r6 << r14;
            r8 = r0.c;
            if (r8 <= 0) goto L_0x0169;
        L_0x0161:
            r1 = r0.i;
            r7 = r9 + 1;
            r1 = r1[r9];
            r9 = r7;
            goto L_0x016b;
        L_0x0169:
            r1 = r19[r7];
        L_0x016b:
            r1 = r1 & 255;
            r1 = r1 << r15;
            r1 = r1 | r6;
            r6 = r0.c;
            r6 = r6 - r9;
            r0.c = r6;
            r6 = r5 + 1;
            r7 = r1 >> 12;
            r7 = r7 & 63;
            r7 = r3[r7];
            r4[r5] = r7;
            r5 = r6 + 1;
            r7 = r1 >> 6;
            r7 = r7 & 63;
            r7 = r3[r7];
            r4[r6] = r7;
            r6 = r5 + 1;
            r1 = r1 & 63;
            r1 = r3[r1];
            r4[r5] = r1;
            r1 = r0.d;
            if (r1 == 0) goto L_0x019b;
        L_0x0194:
            r1 = r6 + 1;
            r3 = 61;
            r4[r6] = r3;
            goto L_0x019c;
        L_0x019b:
            r1 = r6;
        L_0x019c:
            r3 = r0.e;
            if (r3 == 0) goto L_0x01ae;
        L_0x01a0:
            r3 = r0.f;
            if (r3 == 0) goto L_0x01a9;
        L_0x01a4:
            r3 = r1 + 1;
            r4[r1] = r13;
            r1 = r3;
        L_0x01a9:
            r3 = r1 + 1;
            r4[r1] = r14;
            goto L_0x013b;
        L_0x01ae:
            r5 = r1;
            goto L_0x013c;
        L_0x01b0:
            r1 = r0.e;
            if (r1 == 0) goto L_0x013c;
        L_0x01b4:
            if (r5 <= 0) goto L_0x013c;
        L_0x01b6:
            r1 = 19;
            if (r2 == r1) goto L_0x013c;
        L_0x01ba:
            r1 = r0.f;
            if (r1 == 0) goto L_0x01c3;
        L_0x01be:
            r1 = r5 + 1;
            r4[r5] = r13;
            goto L_0x01c4;
        L_0x01c3:
            r1 = r5;
        L_0x01c4:
            r5 = r1 + 1;
            r4[r1] = r14;
            goto L_0x013c;
        L_0x01ca:
            r3 = r6 + -1;
            if (r7 != r3) goto L_0x01dc;
        L_0x01ce:
            r3 = r0.i;
            r4 = r0.c;
            r6 = r4 + 1;
            r0.c = r6;
            r1 = r19[r7];
            r3[r4] = r1;
            goto L_0x013c;
        L_0x01dc:
            r6 = r6 - r15;
            if (r7 != r6) goto L_0x013c;
        L_0x01df:
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
            r6 = 1;
            r7 = r7 + r6;
            r1 = r19[r7];
            r3[r4] = r1;
        L_0x01f9:
            r0.b = r5;
            r0.j = r2;
            return r6;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.c$c.a(byte[], int, int, boolean):boolean");
        }
    }

    public static byte[] a(byte[] bArr, int i) {
        return a(bArr, 0, bArr.length, i);
    }

    public static byte[] a(byte[] bArr, int i, int i2, int i3) {
        b bVar = new b(i3, new byte[((i2 * 3) / 4)]);
        if (!bVar.a(bArr, i, i2, true)) {
            throw new IllegalArgumentException("bad base-64");
        } else if (bVar.b == bVar.a.length) {
            return bVar.a;
        } else {
            bArr = new byte[bVar.b];
            System.arraycopy(bVar.a, 0, bArr, 0, bVar.b);
            return bArr;
        }
    }

    public static String b(byte[] bArr, int i) {
        try {
            return new String(c(bArr, i), C.ASCII_NAME);
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public static byte[] c(byte[] bArr, int i) {
        return b(bArr, 0, bArr.length, i);
    }

    public static byte[] b(byte[] bArr, int i, int i2, int i3) {
        c cVar = new c(i3, null);
        i3 = (i2 / 3) * 4;
        if (!cVar.d) {
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
        if (cVar.e && i2 > 0) {
            i3 += (((i2 - 1) / 57) + 1) * (cVar.f ? 2 : 1);
        }
        cVar.a = new byte[i3];
        cVar.a(bArr, i, i2, true);
        return cVar.a;
    }
}
