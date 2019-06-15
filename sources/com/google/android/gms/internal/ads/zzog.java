package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.concurrent.atomic.AtomicReference;

public final class zzog extends zzoj {
    private static final int[] zzbem = new int[0];
    private final zzon zzben;
    private final AtomicReference<zzoh> zzbeo;

    public zzog() {
        this(null);
    }

    private static int zze(int i, int i2) {
        return i == -1 ? i2 == -1 ? 0 : -1 : i2 == -1 ? 1 : i - i2;
    }

    private static boolean zze(int i, boolean z) {
        i &= 3;
        return i == 3 || (z && i == 2);
    }

    private zzog(zzon zzon) {
        this.zzben = null;
        this.zzbeo = new AtomicReference(new zzoh());
    }

    /* Access modifiers changed, original: protected|final */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00bf  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x01a0  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x019a  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x01b0  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x01b6  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x01b4  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x01b9  */
    /* JADX WARNING: Removed duplicated region for block: B:265:0x01ef A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x01dc  */
    /* JADX WARNING: Missing block: B:75:0x0182, code skipped:
            if (r10.zzzf <= r13) goto L_0x0187;
     */
    /* JADX WARNING: Missing block: B:174:0x0348, code skipped:
            r39 = r1;
            r9 = r21;
     */
    /* JADX WARNING: Missing block: B:217:0x03fe, code skipped:
            r44 = r4;
     */
    /* JADX WARNING: Missing block: B:245:0x0470, code skipped:
            r4 = r44;
     */
    /* JADX WARNING: Missing block: B:246:0x0472, code skipped:
            r2 = r2 + 1;
            r21 = r9;
            r1 = r39;
     */
    public final com.google.android.gms.internal.ads.zzom[] zza(com.google.android.gms.internal.ads.zzga[] r49, com.google.android.gms.internal.ads.zzma[] r50, int[][][] r51) throws com.google.android.gms.internal.ads.zzff {
        /*
        r48 = this;
        r0 = r49;
        r4 = r0.length;
        r5 = new com.google.android.gms.internal.ads.zzom[r4];
        r6 = r48;
        r7 = r6.zzbeo;
        r7 = r7.get();
        r7 = (com.google.android.gms.internal.ads.zzoh) r7;
        r8 = 0;
        r9 = 0;
    L_0x0011:
        r10 = 2;
        if (r8 >= r4) goto L_0x025c;
    L_0x0014:
        r14 = r0[r8];
        r14 = r14.getTrackType();
        if (r10 != r14) goto L_0x0246;
    L_0x001c:
        if (r9 != 0) goto L_0x0239;
    L_0x001e:
        r9 = r50[r8];
        r14 = r51[r8];
        r15 = r7.zzbet;
        r12 = r7.zzbeu;
        r10 = r7.zzbev;
        r3 = r7.viewportWidth;
        r11 = r7.viewportHeight;
        r13 = r7.zzbey;
        r6 = r7.zzbew;
        r2 = r7.zzbex;
        r22 = r4;
        r21 = r7;
        r0 = 0;
        r4 = 0;
        r7 = 0;
        r23 = 0;
        r24 = -1;
        r25 = -1;
    L_0x003f:
        r1 = r9.length;
        if (r7 >= r1) goto L_0x021a;
    L_0x0043:
        r1 = r9.zzau(r7);
        r26 = r9;
        r9 = new java.util.ArrayList;
        r27 = r5;
        r5 = r1.length;
        r9.<init>(r5);
        r28 = r8;
        r5 = 0;
    L_0x0055:
        r8 = r1.length;
        if (r5 >= r8) goto L_0x0063;
    L_0x0059:
        r8 = java.lang.Integer.valueOf(r5);
        r9.add(r8);
        r5 = r5 + 1;
        goto L_0x0055;
    L_0x0063:
        r5 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        if (r3 == r5) goto L_0x0136;
    L_0x0068:
        if (r11 != r5) goto L_0x006c;
    L_0x006a:
        goto L_0x0136;
    L_0x006c:
        r29 = r4;
        r8 = 0;
    L_0x006f:
        r4 = r1.length;
        if (r8 >= r4) goto L_0x0100;
    L_0x0073:
        r4 = r1.zzat(r8);
        r30 = r0;
        r0 = r4.width;
        if (r0 <= 0) goto L_0x00e6;
    L_0x007d:
        r0 = r4.height;
        if (r0 <= 0) goto L_0x00e6;
    L_0x0081:
        r0 = r4.width;
        r31 = r6;
        r6 = r4.height;
        if (r13 == 0) goto L_0x00a3;
    L_0x0089:
        if (r0 <= r6) goto L_0x008f;
    L_0x008b:
        r32 = r13;
        r13 = 1;
        goto L_0x0092;
    L_0x008f:
        r32 = r13;
        r13 = 0;
    L_0x0092:
        if (r3 <= r11) goto L_0x0098;
    L_0x0094:
        r33 = r3;
        r3 = 1;
        goto L_0x009b;
    L_0x0098:
        r33 = r3;
        r3 = 0;
    L_0x009b:
        if (r13 == r3) goto L_0x00a7;
    L_0x009d:
        r13 = r11;
        r34 = r13;
        r3 = r33;
        goto L_0x00ac;
    L_0x00a3:
        r33 = r3;
        r32 = r13;
    L_0x00a7:
        r3 = r11;
        r34 = r3;
        r13 = r33;
    L_0x00ac:
        r11 = r0 * r3;
        r35 = r10;
        r10 = r6 * r13;
        if (r11 < r10) goto L_0x00bf;
    L_0x00b4:
        r3 = new android.graphics.Point;
        r0 = com.google.android.gms.internal.ads.zzqe.zzf(r10, r0);
        r3.<init>(r13, r0);
        r0 = r3;
        goto L_0x00c8;
    L_0x00bf:
        r0 = new android.graphics.Point;
        r6 = com.google.android.gms.internal.ads.zzqe.zzf(r11, r6);
        r0.<init>(r6, r3);
    L_0x00c8:
        r3 = r4.width;
        r6 = r4.height;
        r3 = r3 * r6;
        r6 = r4.width;
        r10 = r0.x;
        r10 = (float) r10;
        r11 = 1065017672; // 0x3f7ae148 float:0.98 double:5.26188644E-315;
        r10 = r10 * r11;
        r10 = (int) r10;
        if (r6 < r10) goto L_0x00f0;
    L_0x00d9:
        r4 = r4.height;
        r0 = r0.y;
        r0 = (float) r0;
        r0 = r0 * r11;
        r0 = (int) r0;
        if (r4 < r0) goto L_0x00f0;
    L_0x00e2:
        if (r3 >= r5) goto L_0x00f0;
    L_0x00e4:
        r5 = r3;
        goto L_0x00f0;
    L_0x00e6:
        r33 = r3;
        r31 = r6;
        r35 = r10;
        r34 = r11;
        r32 = r13;
    L_0x00f0:
        r8 = r8 + 1;
        r0 = r30;
        r6 = r31;
        r13 = r32;
        r3 = r33;
        r11 = r34;
        r10 = r35;
        goto L_0x006f;
    L_0x0100:
        r30 = r0;
        r33 = r3;
        r31 = r6;
        r35 = r10;
        r34 = r11;
        r32 = r13;
        r0 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        if (r5 == r0) goto L_0x0144;
    L_0x0111:
        r0 = r9.size();
        r3 = 1;
        r0 = r0 - r3;
    L_0x0117:
        if (r0 < 0) goto L_0x0144;
    L_0x0119:
        r3 = r9.get(r0);
        r3 = (java.lang.Integer) r3;
        r3 = r3.intValue();
        r3 = r1.zzat(r3);
        r3 = r3.zzce();
        r4 = -1;
        if (r3 == r4) goto L_0x0130;
    L_0x012e:
        if (r3 <= r5) goto L_0x0133;
    L_0x0130:
        r9.remove(r0);
    L_0x0133:
        r0 = r0 + -1;
        goto L_0x0117;
    L_0x0136:
        r30 = r0;
        r33 = r3;
        r29 = r4;
        r31 = r6;
        r35 = r10;
        r34 = r11;
        r32 = r13;
    L_0x0144:
        r0 = r14[r7];
        r5 = r23;
        r6 = r24;
        r8 = r25;
        r4 = r29;
        r3 = 0;
    L_0x014f:
        r10 = r1.length;
        if (r3 >= r10) goto L_0x01fb;
    L_0x0153:
        r10 = r0[r3];
        r10 = zze(r10, r2);
        if (r10 == 0) goto L_0x01e7;
    L_0x015b:
        r10 = r1.zzat(r3);
        r11 = java.lang.Integer.valueOf(r3);
        r11 = r9.contains(r11);
        if (r11 == 0) goto L_0x0189;
    L_0x0169:
        r11 = r10.width;
        r13 = -1;
        if (r11 == r13) goto L_0x0172;
    L_0x016e:
        r11 = r10.width;
        if (r11 > r15) goto L_0x0189;
    L_0x0172:
        r11 = r10.height;
        if (r11 == r13) goto L_0x017a;
    L_0x0176:
        r11 = r10.height;
        if (r11 > r12) goto L_0x0189;
    L_0x017a:
        r11 = r10.zzzf;
        if (r11 == r13) goto L_0x0185;
    L_0x017e:
        r11 = r10.zzzf;
        r13 = r35;
        if (r11 > r13) goto L_0x018b;
    L_0x0184:
        goto L_0x0187;
    L_0x0185:
        r13 = r35;
    L_0x0187:
        r11 = 1;
        goto L_0x018c;
    L_0x0189:
        r13 = r35;
    L_0x018b:
        r11 = 0;
    L_0x018c:
        if (r11 != 0) goto L_0x0198;
    L_0x018e:
        if (r31 == 0) goto L_0x0191;
    L_0x0190:
        goto L_0x0198;
    L_0x0191:
        r38 = r0;
        r36 = r1;
        r37 = r2;
        goto L_0x01ef;
    L_0x0198:
        if (r11 == 0) goto L_0x01a0;
    L_0x019a:
        r36 = r1;
        r37 = r2;
        r1 = 2;
        goto L_0x01a5;
    L_0x01a0:
        r36 = r1;
        r37 = r2;
        r1 = 1;
    L_0x01a5:
        r2 = r0[r3];
        r38 = r0;
        r0 = 0;
        r2 = zze(r2, r0);
        if (r2 == 0) goto L_0x01b2;
    L_0x01b0:
        r1 = r1 + 1000;
    L_0x01b2:
        if (r1 <= r5) goto L_0x01b6;
    L_0x01b4:
        r0 = 1;
        goto L_0x01b7;
    L_0x01b6:
        r0 = 0;
    L_0x01b7:
        if (r1 != r5) goto L_0x01da;
    L_0x01b9:
        r0 = r10.zzce();
        if (r0 == r6) goto L_0x01c8;
    L_0x01bf:
        r0 = r10.zzce();
        r0 = zze(r0, r6);
        goto L_0x01ce;
    L_0x01c8:
        r0 = r10.zzzf;
        r0 = zze(r0, r8);
    L_0x01ce:
        if (r2 == 0) goto L_0x01d5;
    L_0x01d0:
        if (r11 == 0) goto L_0x01d5;
    L_0x01d2:
        if (r0 <= 0) goto L_0x01d9;
    L_0x01d4:
        goto L_0x01d7;
    L_0x01d5:
        if (r0 >= 0) goto L_0x01d9;
    L_0x01d7:
        r0 = 1;
        goto L_0x01da;
    L_0x01d9:
        r0 = 0;
    L_0x01da:
        if (r0 == 0) goto L_0x01ef;
    L_0x01dc:
        r8 = r10.zzzf;
        r6 = r10.zzce();
        r5 = r1;
        r4 = r3;
        r30 = r36;
        goto L_0x01ef;
    L_0x01e7:
        r38 = r0;
        r36 = r1;
        r37 = r2;
        r13 = r35;
    L_0x01ef:
        r3 = r3 + 1;
        r35 = r13;
        r1 = r36;
        r2 = r37;
        r0 = r38;
        goto L_0x014f;
    L_0x01fb:
        r37 = r2;
        r13 = r35;
        r7 = r7 + 1;
        r23 = r5;
        r24 = r6;
        r25 = r8;
        r10 = r13;
        r9 = r26;
        r5 = r27;
        r8 = r28;
        r0 = r30;
        r6 = r31;
        r13 = r32;
        r3 = r33;
        r11 = r34;
        goto L_0x003f;
    L_0x021a:
        r30 = r0;
        r29 = r4;
        r27 = r5;
        r28 = r8;
        if (r30 != 0) goto L_0x0226;
    L_0x0224:
        r12 = 0;
        goto L_0x022f;
    L_0x0226:
        r12 = new com.google.android.gms.internal.ads.zzoi;
        r4 = r29;
        r0 = r30;
        r12.<init>(r0, r4);
    L_0x022f:
        r27[r28] = r12;
        r0 = r27[r28];
        if (r0 == 0) goto L_0x0237;
    L_0x0235:
        r9 = 1;
        goto L_0x0241;
    L_0x0237:
        r9 = 0;
        goto L_0x0241;
    L_0x0239:
        r22 = r4;
        r27 = r5;
        r21 = r7;
        r28 = r8;
    L_0x0241:
        r1 = r50[r28];
        r1 = r1.length;
        goto L_0x024e;
    L_0x0246:
        r22 = r4;
        r27 = r5;
        r21 = r7;
        r28 = r8;
    L_0x024e:
        r8 = r28 + 1;
        r7 = r21;
        r4 = r22;
        r5 = r27;
        r0 = r49;
        r6 = r48;
        goto L_0x0011;
    L_0x025c:
        r27 = r5;
        r21 = r7;
        r1 = r4;
        r2 = 0;
        r3 = 0;
        r4 = 0;
    L_0x0264:
        if (r2 >= r1) goto L_0x047a;
    L_0x0266:
        r5 = r49;
        r6 = r5[r2];
        r6 = r6.getTrackType();
        switch(r6) {
            case 1: goto L_0x0350;
            case 2: goto L_0x0348;
            case 3: goto L_0x028c;
            default: goto L_0x0271;
        };
    L_0x0271:
        r39 = r1;
        r44 = r4;
        r9 = r21;
        r4 = -1;
        r12 = 0;
        r17 = 2;
        r1 = r5[r2];
        r1.getTrackType();
        r1 = r50[r2];
        r7 = r51[r2];
        r8 = r9.zzbex;
        r11 = r12;
        r10 = 0;
        r13 = 0;
        r14 = 0;
        goto L_0x0405;
    L_0x028c:
        if (r4 != 0) goto L_0x0348;
    L_0x028e:
        r4 = r50[r2];
        r8 = r51[r2];
        r9 = r21;
        r11 = r9.zzbex;
        r12 = 0;
        r13 = 0;
        r14 = 0;
        r15 = 0;
    L_0x029a:
        r7 = r4.length;
        if (r12 >= r7) goto L_0x032f;
    L_0x029e:
        r7 = r4.zzau(r12);
        r20 = r8[r12];
        r39 = r1;
        r10 = r15;
        r15 = r14;
        r14 = r13;
        r13 = 0;
    L_0x02aa:
        r1 = r7.length;
        if (r13 >= r1) goto L_0x0320;
    L_0x02ae:
        r1 = r20[r13];
        r1 = zze(r1, r11);
        if (r1 == 0) goto L_0x030f;
    L_0x02b6:
        r1 = r7.zzat(r13);
        r40 = r4;
        r4 = r1.zzzz;
        r19 = 1;
        r4 = r4 & 1;
        if (r4 == 0) goto L_0x02c8;
    L_0x02c4:
        r41 = r7;
        r4 = 1;
        goto L_0x02cb;
    L_0x02c8:
        r41 = r7;
        r4 = 0;
    L_0x02cb:
        r7 = r1.zzzz;
        r17 = 2;
        r7 = r7 & 2;
        if (r7 == 0) goto L_0x02d8;
    L_0x02d3:
        r42 = r8;
        r7 = 1;
    L_0x02d6:
        r8 = 0;
        goto L_0x02dc;
    L_0x02d8:
        r42 = r8;
        r7 = 0;
        goto L_0x02d6;
    L_0x02dc:
        r21 = zza(r1, r8);
        if (r21 == 0) goto L_0x02ec;
    L_0x02e2:
        if (r4 == 0) goto L_0x02e6;
    L_0x02e4:
        r1 = 6;
        goto L_0x02fd;
    L_0x02e6:
        if (r7 != 0) goto L_0x02ea;
    L_0x02e8:
        r1 = 5;
        goto L_0x02fd;
    L_0x02ea:
        r1 = 4;
        goto L_0x02fd;
    L_0x02ec:
        if (r4 == 0) goto L_0x02f0;
    L_0x02ee:
        r1 = 3;
        goto L_0x02fd;
    L_0x02f0:
        if (r7 == 0) goto L_0x0317;
    L_0x02f2:
        r4 = 0;
        r1 = zza(r1, r4);
        if (r1 == 0) goto L_0x02fc;
    L_0x02f9:
        r1 = r17;
        goto L_0x02fd;
    L_0x02fc:
        r1 = 1;
    L_0x02fd:
        r4 = r20[r13];
        r7 = 0;
        r4 = zze(r4, r7);
        if (r4 == 0) goto L_0x0308;
    L_0x0306:
        r1 = r1 + 1000;
    L_0x0308:
        if (r1 <= r10) goto L_0x0317;
    L_0x030a:
        r10 = r1;
        r15 = r13;
        r14 = r41;
        goto L_0x0317;
    L_0x030f:
        r40 = r4;
        r41 = r7;
        r42 = r8;
        r17 = 2;
    L_0x0317:
        r13 = r13 + 1;
        r4 = r40;
        r7 = r41;
        r8 = r42;
        goto L_0x02aa;
    L_0x0320:
        r40 = r4;
        r42 = r8;
        r17 = 2;
        r12 = r12 + 1;
        r13 = r14;
        r14 = r15;
        r1 = r39;
        r15 = r10;
        goto L_0x029a;
    L_0x032f:
        r39 = r1;
        r17 = 2;
        if (r13 != 0) goto L_0x0337;
    L_0x0335:
        r12 = 0;
        goto L_0x033c;
    L_0x0337:
        r12 = new com.google.android.gms.internal.ads.zzoi;
        r12.<init>(r13, r14);
    L_0x033c:
        r27[r2] = r12;
        r1 = r27[r2];
        if (r1 == 0) goto L_0x0344;
    L_0x0342:
        r1 = 1;
        goto L_0x0345;
    L_0x0344:
        r1 = 0;
    L_0x0345:
        r4 = r1;
        goto L_0x03f9;
    L_0x0348:
        r39 = r1;
        r9 = r21;
        r17 = 2;
        goto L_0x03fe;
    L_0x0350:
        r39 = r1;
        r9 = r21;
        r17 = 2;
        if (r3 != 0) goto L_0x03fe;
    L_0x0358:
        r1 = r50[r2];
        r3 = r51[r2];
        r7 = r9.zzbex;
        r8 = 0;
        r10 = -1;
        r11 = -1;
        r12 = 0;
    L_0x0362:
        r13 = r1.length;
        if (r8 >= r13) goto L_0x03dc;
    L_0x0366:
        r13 = r1.zzau(r8);
        r14 = r3[r8];
        r43 = r3;
        r15 = r12;
        r12 = r11;
        r11 = r10;
        r10 = 0;
    L_0x0372:
        r3 = r13.length;
        if (r10 >= r3) goto L_0x03cc;
    L_0x0376:
        r3 = r14[r10];
        r3 = zze(r3, r7);
        if (r3 == 0) goto L_0x03bc;
    L_0x037e:
        r3 = r13.zzat(r10);
        r44 = r4;
        r4 = r14[r10];
        r45 = r7;
        r7 = r3.zzzz;
        r19 = 1;
        r7 = r7 & 1;
        if (r7 == 0) goto L_0x0395;
    L_0x0390:
        r46 = r12;
        r7 = 1;
    L_0x0393:
        r12 = 0;
        goto L_0x0399;
    L_0x0395:
        r46 = r12;
        r7 = 0;
        goto L_0x0393;
    L_0x0399:
        r3 = zza(r3, r12);
        if (r3 == 0) goto L_0x03a6;
    L_0x039f:
        if (r7 == 0) goto L_0x03a4;
    L_0x03a1:
        r3 = 4;
    L_0x03a2:
        r7 = 0;
        goto L_0x03ad;
    L_0x03a4:
        r3 = 3;
        goto L_0x03a2;
    L_0x03a6:
        if (r7 == 0) goto L_0x03ab;
    L_0x03a8:
        r3 = r17;
        goto L_0x03a2;
    L_0x03ab:
        r3 = 1;
        goto L_0x03a2;
    L_0x03ad:
        r4 = zze(r4, r7);
        if (r4 == 0) goto L_0x03b5;
    L_0x03b3:
        r3 = r3 + 1000;
    L_0x03b5:
        if (r3 <= r15) goto L_0x03c3;
    L_0x03b7:
        r15 = r3;
        r11 = r8;
        r46 = r10;
        goto L_0x03c3;
    L_0x03bc:
        r44 = r4;
        r45 = r7;
        r46 = r12;
        r12 = 0;
    L_0x03c3:
        r10 = r10 + 1;
        r4 = r44;
        r7 = r45;
        r12 = r46;
        goto L_0x0372;
    L_0x03cc:
        r44 = r4;
        r45 = r7;
        r46 = r12;
        r12 = 0;
        r8 = r8 + 1;
        r10 = r11;
        r12 = r15;
        r3 = r43;
        r11 = r46;
        goto L_0x0362;
    L_0x03dc:
        r44 = r4;
        r4 = -1;
        r12 = 0;
        if (r10 != r4) goto L_0x03e4;
    L_0x03e2:
        r3 = r12;
        goto L_0x03ed;
    L_0x03e4:
        r1 = r1.zzau(r10);
        r3 = new com.google.android.gms.internal.ads.zzoi;
        r3.<init>(r1, r11);
    L_0x03ed:
        r27[r2] = r3;
        r1 = r27[r2];
        if (r1 == 0) goto L_0x03f5;
    L_0x03f3:
        r1 = 1;
        goto L_0x03f6;
    L_0x03f5:
        r1 = 0;
    L_0x03f6:
        r3 = r1;
        r4 = r44;
    L_0x03f9:
        r1 = 0;
        r18 = 1;
        goto L_0x0472;
    L_0x03fe:
        r44 = r4;
        r1 = 0;
        r18 = 1;
        goto L_0x0470;
    L_0x0405:
        r15 = r1.length;
        if (r10 >= r15) goto L_0x0462;
    L_0x0409:
        r15 = r1.zzau(r10);
        r16 = r7[r10];
        r4 = r14;
        r14 = r13;
        r13 = r11;
        r11 = 0;
    L_0x0413:
        r12 = r15.length;
        if (r11 >= r12) goto L_0x0453;
    L_0x0417:
        r12 = r16[r11];
        r12 = zze(r12, r8);
        if (r12 == 0) goto L_0x0449;
    L_0x041f:
        r12 = r15.zzat(r11);
        r12 = r12.zzzz;
        r18 = 1;
        r12 = r12 & 1;
        if (r12 == 0) goto L_0x042e;
    L_0x042b:
        r12 = r18;
        goto L_0x042f;
    L_0x042e:
        r12 = 0;
    L_0x042f:
        if (r12 == 0) goto L_0x0434;
    L_0x0431:
        r12 = r17;
        goto L_0x0436;
    L_0x0434:
        r12 = r18;
    L_0x0436:
        r0 = r16[r11];
        r47 = r1;
        r1 = 0;
        r0 = zze(r0, r1);
        if (r0 == 0) goto L_0x0443;
    L_0x0441:
        r12 = r12 + 1000;
    L_0x0443:
        if (r12 <= r4) goto L_0x044e;
    L_0x0445:
        r14 = r11;
        r4 = r12;
        r13 = r15;
        goto L_0x044e;
    L_0x0449:
        r47 = r1;
        r1 = 0;
        r18 = 1;
    L_0x044e:
        r11 = r11 + 1;
        r1 = r47;
        goto L_0x0413;
    L_0x0453:
        r47 = r1;
        r1 = 0;
        r18 = 1;
        r10 = r10 + 1;
        r11 = r13;
        r13 = r14;
        r1 = r47;
        r12 = 0;
        r14 = r4;
        r4 = -1;
        goto L_0x0405;
    L_0x0462:
        r1 = 0;
        r18 = 1;
        if (r11 != 0) goto L_0x0469;
    L_0x0467:
        r12 = 0;
        goto L_0x046e;
    L_0x0469:
        r12 = new com.google.android.gms.internal.ads.zzoi;
        r12.<init>(r11, r13);
    L_0x046e:
        r27[r2] = r12;
    L_0x0470:
        r4 = r44;
    L_0x0472:
        r2 = r2 + 1;
        r21 = r9;
        r1 = r39;
        goto L_0x0264;
    L_0x047a:
        return r27;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzog.zza(com.google.android.gms.internal.ads.zzga[], com.google.android.gms.internal.ads.zzma[], int[][][]):com.google.android.gms.internal.ads.zzom[]");
    }

    private static boolean zza(zzfs zzfs, String str) {
        return str != null && TextUtils.equals(str, zzqe.zzai(zzfs.zzaaa));
    }
}
