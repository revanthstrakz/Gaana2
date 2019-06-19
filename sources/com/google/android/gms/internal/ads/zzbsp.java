package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import sun.misc.Unsafe;

final class zzbsp<T> implements zzbtc<T> {
    private static final int[] zzfsg = new int[0];
    private static final Unsafe zzfsh = zzbua.zzape();
    private final int[] zzfsi;
    private final Object[] zzfsj;
    private final int zzfsk;
    private final int zzfsl;
    private final zzbsl zzfsm;
    private final boolean zzfsn;
    private final boolean zzfso;
    private final boolean zzfsp;
    private final boolean zzfsq;
    private final int[] zzfsr;
    private final int zzfss;
    private final int zzfst;
    private final zzbst zzfsu;
    private final zzbrv zzfsv;
    private final zzbtu<?, ?> zzfsw;
    private final zzbqr<?> zzfsx;
    private final zzbsg zzfsy;

    private zzbsp(int[] iArr, Object[] objArr, int i, int i2, zzbsl zzbsl, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzbst zzbst, zzbrv zzbrv, zzbtu<?, ?> zzbtu, zzbqr<?> zzbqr, zzbsg zzbsg) {
        this.zzfsi = iArr;
        this.zzfsj = objArr;
        this.zzfsk = i;
        this.zzfsl = i2;
        this.zzfso = zzbsl instanceof zzbrd;
        this.zzfsp = z;
        boolean z3 = zzbqr != null && zzbqr.zzh(zzbsl);
        this.zzfsn = z3;
        this.zzfsq = false;
        this.zzfsr = iArr2;
        this.zzfss = i3;
        this.zzfst = i4;
        this.zzfsu = zzbst;
        this.zzfsv = zzbrv;
        this.zzfsw = zzbtu;
        this.zzfsx = zzbqr;
        this.zzfsm = zzbsl;
        this.zzfsy = zzbsg;
    }

    private static boolean zzfv(int i) {
        return (i & C.ENCODING_PCM_A_LAW) != 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:167:0x03ba  */
    static <T> com.google.android.gms.internal.ads.zzbsp<T> zza(java.lang.Class<T> r40, com.google.android.gms.internal.ads.zzbsj r41, com.google.android.gms.internal.ads.zzbst r42, com.google.android.gms.internal.ads.zzbrv r43, com.google.android.gms.internal.ads.zzbtu<?, ?> r44, com.google.android.gms.internal.ads.zzbqr<?> r45, com.google.android.gms.internal.ads.zzbsg r46) {
        /*
        r0 = r41;
        r1 = r0 instanceof com.google.android.gms.internal.ads.zzbta;
        if (r1 == 0) goto L_0x0484;
    L_0x0006:
        r0 = (com.google.android.gms.internal.ads.zzbta) r0;
        r1 = r0.zzanz();
        r2 = com.google.android.gms.internal.ads.zzbrd.zze.zzfqk;
        r3 = 0;
        r4 = 1;
        if (r1 != r2) goto L_0x0014;
    L_0x0012:
        r11 = r4;
        goto L_0x0015;
    L_0x0014:
        r11 = r3;
    L_0x0015:
        r1 = r0.zzaoi();
        r2 = r1.length();
        r5 = r1.charAt(r3);
        r7 = 55296; // 0xd800 float:7.7486E-41 double:2.732E-319;
        if (r5 < r7) goto L_0x003f;
    L_0x0026:
        r5 = r5 & 8191;
        r8 = r5;
        r9 = 13;
        r5 = r4;
    L_0x002c:
        r10 = r5 + 1;
        r5 = r1.charAt(r5);
        if (r5 < r7) goto L_0x003c;
    L_0x0034:
        r5 = r5 & 8191;
        r5 = r5 << r9;
        r8 = r8 | r5;
        r9 = r9 + 13;
        r5 = r10;
        goto L_0x002c;
    L_0x003c:
        r5 = r5 << r9;
        r5 = r5 | r8;
        goto L_0x0040;
    L_0x003f:
        r10 = r4;
    L_0x0040:
        r8 = r10 + 1;
        r9 = r1.charAt(r10);
        if (r9 < r7) goto L_0x005f;
    L_0x0048:
        r9 = r9 & 8191;
        r10 = 13;
    L_0x004c:
        r12 = r8 + 1;
        r8 = r1.charAt(r8);
        if (r8 < r7) goto L_0x005c;
    L_0x0054:
        r8 = r8 & 8191;
        r8 = r8 << r10;
        r9 = r9 | r8;
        r10 = r10 + 13;
        r8 = r12;
        goto L_0x004c;
    L_0x005c:
        r8 = r8 << r10;
        r9 = r9 | r8;
        r8 = r12;
    L_0x005f:
        if (r9 != 0) goto L_0x006d;
    L_0x0061:
        r9 = zzfsg;
        r10 = r3;
        r12 = r10;
        r13 = r12;
        r14 = r13;
        r15 = r14;
        r16 = r9;
        r9 = r15;
        goto L_0x019e;
    L_0x006d:
        r9 = r8 + 1;
        r8 = r1.charAt(r8);
        if (r8 < r7) goto L_0x008c;
    L_0x0075:
        r8 = r8 & 8191;
        r10 = 13;
    L_0x0079:
        r12 = r9 + 1;
        r9 = r1.charAt(r9);
        if (r9 < r7) goto L_0x0089;
    L_0x0081:
        r9 = r9 & 8191;
        r9 = r9 << r10;
        r8 = r8 | r9;
        r10 = r10 + 13;
        r9 = r12;
        goto L_0x0079;
    L_0x0089:
        r9 = r9 << r10;
        r8 = r8 | r9;
        r9 = r12;
    L_0x008c:
        r10 = r9 + 1;
        r9 = r1.charAt(r9);
        if (r9 < r7) goto L_0x00ab;
    L_0x0094:
        r9 = r9 & 8191;
        r12 = 13;
    L_0x0098:
        r13 = r10 + 1;
        r10 = r1.charAt(r10);
        if (r10 < r7) goto L_0x00a8;
    L_0x00a0:
        r10 = r10 & 8191;
        r10 = r10 << r12;
        r9 = r9 | r10;
        r12 = r12 + 13;
        r10 = r13;
        goto L_0x0098;
    L_0x00a8:
        r10 = r10 << r12;
        r9 = r9 | r10;
        r10 = r13;
    L_0x00ab:
        r12 = r10 + 1;
        r10 = r1.charAt(r10);
        if (r10 < r7) goto L_0x00ca;
    L_0x00b3:
        r10 = r10 & 8191;
        r13 = 13;
    L_0x00b7:
        r14 = r12 + 1;
        r12 = r1.charAt(r12);
        if (r12 < r7) goto L_0x00c7;
    L_0x00bf:
        r12 = r12 & 8191;
        r12 = r12 << r13;
        r10 = r10 | r12;
        r13 = r13 + 13;
        r12 = r14;
        goto L_0x00b7;
    L_0x00c7:
        r12 = r12 << r13;
        r10 = r10 | r12;
        r12 = r14;
    L_0x00ca:
        r13 = r12 + 1;
        r12 = r1.charAt(r12);
        if (r12 < r7) goto L_0x00e9;
    L_0x00d2:
        r12 = r12 & 8191;
        r14 = 13;
    L_0x00d6:
        r15 = r13 + 1;
        r13 = r1.charAt(r13);
        if (r13 < r7) goto L_0x00e6;
    L_0x00de:
        r13 = r13 & 8191;
        r13 = r13 << r14;
        r12 = r12 | r13;
        r14 = r14 + 13;
        r13 = r15;
        goto L_0x00d6;
    L_0x00e6:
        r13 = r13 << r14;
        r12 = r12 | r13;
        r13 = r15;
    L_0x00e9:
        r14 = r13 + 1;
        r13 = r1.charAt(r13);
        if (r13 < r7) goto L_0x010a;
    L_0x00f1:
        r13 = r13 & 8191;
        r15 = 13;
    L_0x00f5:
        r16 = r14 + 1;
        r14 = r1.charAt(r14);
        if (r14 < r7) goto L_0x0106;
    L_0x00fd:
        r14 = r14 & 8191;
        r14 = r14 << r15;
        r13 = r13 | r14;
        r15 = r15 + 13;
        r14 = r16;
        goto L_0x00f5;
    L_0x0106:
        r14 = r14 << r15;
        r13 = r13 | r14;
        r14 = r16;
    L_0x010a:
        r15 = r14 + 1;
        r14 = r1.charAt(r14);
        if (r14 < r7) goto L_0x012d;
    L_0x0112:
        r14 = r14 & 8191;
        r16 = 13;
    L_0x0116:
        r17 = r15 + 1;
        r15 = r1.charAt(r15);
        if (r15 < r7) goto L_0x0128;
    L_0x011e:
        r15 = r15 & 8191;
        r15 = r15 << r16;
        r14 = r14 | r15;
        r16 = r16 + 13;
        r15 = r17;
        goto L_0x0116;
    L_0x0128:
        r15 = r15 << r16;
        r14 = r14 | r15;
        r15 = r17;
    L_0x012d:
        r16 = r15 + 1;
        r15 = r1.charAt(r15);
        if (r15 < r7) goto L_0x0159;
    L_0x0135:
        r15 = r15 & 8191;
        r17 = 13;
        r38 = r16;
        r16 = r15;
        r15 = r38;
    L_0x013f:
        r18 = r15 + 1;
        r15 = r1.charAt(r15);
        if (r15 < r7) goto L_0x0152;
    L_0x0147:
        r15 = r15 & 8191;
        r15 = r15 << r17;
        r16 = r16 | r15;
        r17 = r17 + 13;
        r15 = r18;
        goto L_0x013f;
    L_0x0152:
        r15 = r15 << r17;
        r15 = r16 | r15;
        r3 = r18;
        goto L_0x015b;
    L_0x0159:
        r3 = r16;
    L_0x015b:
        r16 = r3 + 1;
        r3 = r1.charAt(r3);
        if (r3 < r7) goto L_0x0186;
    L_0x0163:
        r3 = r3 & 8191;
        r17 = 13;
        r38 = r16;
        r16 = r3;
        r3 = r38;
    L_0x016d:
        r18 = r3 + 1;
        r3 = r1.charAt(r3);
        if (r3 < r7) goto L_0x0180;
    L_0x0175:
        r3 = r3 & 8191;
        r3 = r3 << r17;
        r16 = r16 | r3;
        r17 = r17 + 13;
        r3 = r18;
        goto L_0x016d;
    L_0x0180:
        r3 = r3 << r17;
        r3 = r16 | r3;
        r16 = r18;
    L_0x0186:
        r17 = r3 + r14;
        r15 = r17 + r15;
        r15 = new int[r15];
        r17 = r8 << 1;
        r9 = r17 + r9;
        r38 = r14;
        r14 = r3;
        r3 = r38;
        r39 = r9;
        r9 = r8;
        r8 = r16;
        r16 = r15;
        r15 = r39;
    L_0x019e:
        r6 = zzfsh;
        r17 = r0.zzaoj();
        r7 = r0.zzaob();
        r7 = r7.getClass();
        r22 = r8;
        r8 = r13 * 3;
        r8 = new int[r8];
        r13 = r13 << r4;
        r13 = new java.lang.Object[r13];
        r3 = r3 + r14;
        r23 = r14;
        r20 = r15;
        r15 = r22;
        r18 = 0;
        r19 = 0;
        r22 = r3;
    L_0x01c2:
        if (r15 >= r2) goto L_0x045a;
    L_0x01c4:
        r24 = r15 + 1;
        r15 = r1.charAt(r15);
        r4 = 55296; // 0xd800 float:7.7486E-41 double:2.732E-319;
        if (r15 < r4) goto L_0x01f6;
    L_0x01cf:
        r15 = r15 & 8191;
        r25 = 13;
        r38 = r24;
        r24 = r15;
        r15 = r38;
    L_0x01d9:
        r26 = r15 + 1;
        r15 = r1.charAt(r15);
        if (r15 < r4) goto L_0x01ef;
    L_0x01e1:
        r4 = r15 & 8191;
        r4 = r4 << r25;
        r24 = r24 | r4;
        r25 = r25 + 13;
        r15 = r26;
        r4 = 55296; // 0xd800 float:7.7486E-41 double:2.732E-319;
        goto L_0x01d9;
    L_0x01ef:
        r4 = r15 << r25;
        r15 = r24 | r4;
        r4 = r26;
        goto L_0x01f8;
    L_0x01f6:
        r4 = r24;
    L_0x01f8:
        r24 = r4 + 1;
        r4 = r1.charAt(r4);
        r27 = r2;
        r2 = 55296; // 0xd800 float:7.7486E-41 double:2.732E-319;
        if (r4 < r2) goto L_0x022c;
    L_0x0205:
        r4 = r4 & 8191;
        r25 = 13;
        r38 = r24;
        r24 = r4;
        r4 = r38;
    L_0x020f:
        r26 = r4 + 1;
        r4 = r1.charAt(r4);
        if (r4 < r2) goto L_0x0225;
    L_0x0217:
        r2 = r4 & 8191;
        r2 = r2 << r25;
        r24 = r24 | r2;
        r25 = r25 + 13;
        r4 = r26;
        r2 = 55296; // 0xd800 float:7.7486E-41 double:2.732E-319;
        goto L_0x020f;
    L_0x0225:
        r2 = r4 << r25;
        r4 = r24 | r2;
        r2 = r26;
        goto L_0x022e;
    L_0x022c:
        r2 = r24;
    L_0x022e:
        r28 = r3;
        r3 = r4 & 255;
        r29 = r14;
        r14 = r4 & 1024;
        if (r14 == 0) goto L_0x023e;
    L_0x0238:
        r14 = r18 + 1;
        r16[r18] = r19;
        r18 = r14;
    L_0x023e:
        r14 = com.google.android.gms.internal.ads.zzbqx.MAP;
        r14 = r14.id();
        if (r3 <= r14) goto L_0x0301;
    L_0x0246:
        r14 = r2 + 1;
        r2 = r1.charAt(r2);
        r30 = r14;
        r14 = 55296; // 0xd800 float:7.7486E-41 double:2.732E-319;
        if (r2 < r14) goto L_0x0276;
    L_0x0253:
        r2 = r2 & 8191;
        r24 = r2;
        r2 = r30;
        r25 = 13;
    L_0x025b:
        r26 = r2 + 1;
        r2 = r1.charAt(r2);
        if (r2 < r14) goto L_0x0271;
    L_0x0263:
        r2 = r2 & 8191;
        r2 = r2 << r25;
        r24 = r24 | r2;
        r25 = r25 + 13;
        r2 = r26;
        r14 = 55296; // 0xd800 float:7.7486E-41 double:2.732E-319;
        goto L_0x025b;
    L_0x0271:
        r2 = r2 << r25;
        r2 = r24 | r2;
        goto L_0x0278;
    L_0x0276:
        r26 = r30;
    L_0x0278:
        r14 = com.google.android.gms.internal.ads.zzbqx.MESSAGE;
        r14 = r14.id();
        r14 = r14 + 51;
        if (r3 == r14) goto L_0x02b1;
    L_0x0282:
        r14 = com.google.android.gms.internal.ads.zzbqx.GROUP;
        r14 = r14.id();
        r14 = r14 + 51;
        if (r3 != r14) goto L_0x028d;
    L_0x028c:
        goto L_0x02b1;
    L_0x028d:
        r14 = com.google.android.gms.internal.ads.zzbqx.ENUM;
        r14 = r14.id();
        r14 = r14 + 51;
        if (r3 != r14) goto L_0x02ab;
    L_0x0297:
        r14 = r5 & 1;
        r31 = r11;
        r11 = 1;
        if (r14 != r11) goto L_0x02ad;
    L_0x029e:
        r14 = r19 / 3;
        r14 = r14 << r11;
        r14 = r14 + r11;
        r11 = r20 + 1;
        r20 = r17[r20];
        r13[r14] = r20;
        r24 = r11;
        goto L_0x02af;
    L_0x02ab:
        r31 = r11;
    L_0x02ad:
        r24 = r20;
    L_0x02af:
        r14 = 1;
        goto L_0x02be;
    L_0x02b1:
        r31 = r11;
        r11 = r19 / 3;
        r14 = 1;
        r11 = r11 << r14;
        r11 = r11 + r14;
        r24 = r20 + 1;
        r20 = r17[r20];
        r13[r11] = r20;
    L_0x02be:
        r2 = r2 << r14;
        r11 = r17[r2];
        r14 = r11 instanceof java.lang.reflect.Field;
        if (r14 == 0) goto L_0x02ca;
    L_0x02c5:
        r11 = (java.lang.reflect.Field) r11;
    L_0x02c7:
        r32 = r12;
        goto L_0x02d3;
    L_0x02ca:
        r11 = (java.lang.String) r11;
        r11 = zza(r7, r11);
        r17[r2] = r11;
        goto L_0x02c7;
    L_0x02d3:
        r11 = r6.objectFieldOffset(r11);
        r11 = (int) r11;
        r2 = r2 + 1;
        r12 = r17[r2];
        r14 = r12 instanceof java.lang.reflect.Field;
        if (r14 == 0) goto L_0x02e5;
    L_0x02e0:
        r12 = (java.lang.reflect.Field) r12;
    L_0x02e2:
        r33 = r11;
        goto L_0x02ee;
    L_0x02e5:
        r12 = (java.lang.String) r12;
        r12 = zza(r7, r12);
        r17[r2] = r12;
        goto L_0x02e2;
    L_0x02ee:
        r11 = r6.objectFieldOffset(r12);
        r2 = (int) r11;
        r36 = r9;
        r34 = r10;
        r20 = r24;
        r37 = r26;
        r11 = r33;
        r9 = r2;
        r2 = 0;
        goto L_0x0415;
    L_0x0301:
        r31 = r11;
        r32 = r12;
        r11 = r20 + 1;
        r12 = r17[r20];
        r12 = (java.lang.String) r12;
        r12 = zza(r7, r12);
        r14 = com.google.android.gms.internal.ads.zzbqx.MESSAGE;
        r14 = r14.id();
        if (r3 == r14) goto L_0x03a1;
    L_0x0317:
        r14 = com.google.android.gms.internal.ads.zzbqx.GROUP;
        r14 = r14.id();
        if (r3 != r14) goto L_0x0321;
    L_0x031f:
        goto L_0x03a1;
    L_0x0321:
        r14 = com.google.android.gms.internal.ads.zzbqx.MESSAGE_LIST;
        r14 = r14.id();
        if (r3 == r14) goto L_0x0391;
    L_0x0329:
        r14 = com.google.android.gms.internal.ads.zzbqx.GROUP_LIST;
        r14 = r14.id();
        if (r3 != r14) goto L_0x0332;
    L_0x0331:
        goto L_0x0391;
    L_0x0332:
        r14 = com.google.android.gms.internal.ads.zzbqx.ENUM;
        r14 = r14.id();
        if (r3 == r14) goto L_0x037f;
    L_0x033a:
        r14 = com.google.android.gms.internal.ads.zzbqx.ENUM_LIST;
        r14 = r14.id();
        if (r3 == r14) goto L_0x037f;
    L_0x0342:
        r14 = com.google.android.gms.internal.ads.zzbqx.ENUM_LIST_PACKED;
        r14 = r14.id();
        if (r3 != r14) goto L_0x034b;
    L_0x034a:
        goto L_0x037f;
    L_0x034b:
        r14 = com.google.android.gms.internal.ads.zzbqx.MAP;
        r14 = r14.id();
        if (r3 != r14) goto L_0x037b;
    L_0x0353:
        r14 = r23 + 1;
        r16[r23] = r19;
        r20 = r19 / 3;
        r23 = 1;
        r20 = r20 << 1;
        r23 = r11 + 1;
        r11 = r17[r11];
        r13[r20] = r11;
        r11 = r4 & 2048;
        if (r11 == 0) goto L_0x0374;
    L_0x0367:
        r20 = r20 + 1;
        r11 = r23 + 1;
        r23 = r17[r23];
        r13[r20] = r23;
        r34 = r10;
        r35 = r11;
        goto L_0x0378;
    L_0x0374:
        r34 = r10;
        r35 = r23;
    L_0x0378:
        r23 = r14;
        goto L_0x03b0;
    L_0x037b:
        r34 = r10;
        r10 = 1;
        goto L_0x03ae;
    L_0x037f:
        r14 = r5 & 1;
        r34 = r10;
        r10 = 1;
        if (r14 != r10) goto L_0x03ae;
    L_0x0386:
        r14 = r19 / 3;
        r14 = r14 << r10;
        r14 = r14 + r10;
        r20 = r11 + 1;
        r11 = r17[r11];
        r13[r14] = r11;
        goto L_0x039e;
    L_0x0391:
        r34 = r10;
        r10 = 1;
        r14 = r19 / 3;
        r14 = r14 << r10;
        r14 = r14 + r10;
        r20 = r11 + 1;
        r11 = r17[r11];
        r13[r14] = r11;
    L_0x039e:
        r35 = r20;
        goto L_0x03b0;
    L_0x03a1:
        r34 = r10;
        r10 = 1;
        r14 = r19 / 3;
        r14 = r14 << r10;
        r14 = r14 + r10;
        r20 = r12.getType();
        r13[r14] = r20;
    L_0x03ae:
        r35 = r11;
    L_0x03b0:
        r10 = r6.objectFieldOffset(r12);
        r11 = (int) r10;
        r10 = r5 & 1;
        r12 = 1;
        if (r10 != r12) goto L_0x040d;
    L_0x03ba:
        r10 = com.google.android.gms.internal.ads.zzbqx.GROUP;
        r10 = r10.id();
        if (r3 > r10) goto L_0x040d;
    L_0x03c2:
        r10 = r2 + 1;
        r2 = r1.charAt(r2);
        r12 = 55296; // 0xd800 float:7.7486E-41 double:2.732E-319;
        if (r2 < r12) goto L_0x03e6;
    L_0x03cd:
        r2 = r2 & 8191;
        r14 = 13;
    L_0x03d1:
        r20 = r10 + 1;
        r10 = r1.charAt(r10);
        if (r10 < r12) goto L_0x03e2;
    L_0x03d9:
        r10 = r10 & 8191;
        r10 = r10 << r14;
        r2 = r2 | r10;
        r14 = r14 + 13;
        r10 = r20;
        goto L_0x03d1;
    L_0x03e2:
        r10 = r10 << r14;
        r2 = r2 | r10;
        r10 = r20;
    L_0x03e6:
        r14 = 1;
        r20 = r9 << 1;
        r21 = r2 / 32;
        r20 = r20 + r21;
        r12 = r17[r20];
        r14 = r12 instanceof java.lang.reflect.Field;
        if (r14 == 0) goto L_0x03fa;
    L_0x03f3:
        r12 = (java.lang.reflect.Field) r12;
    L_0x03f5:
        r36 = r9;
        r37 = r10;
        goto L_0x0403;
    L_0x03fa:
        r12 = (java.lang.String) r12;
        r12 = zza(r7, r12);
        r17[r20] = r12;
        goto L_0x03f5;
    L_0x0403:
        r9 = r6.objectFieldOffset(r12);
        r9 = (int) r9;
        r2 = r2 % 32;
        r20 = r35;
        goto L_0x0415;
    L_0x040d:
        r36 = r9;
        r37 = r2;
        r20 = r35;
        r2 = 0;
        r9 = 0;
    L_0x0415:
        r10 = 18;
        if (r3 < r10) goto L_0x0423;
    L_0x0419:
        r10 = 49;
        if (r3 > r10) goto L_0x0423;
    L_0x041d:
        r10 = r22 + 1;
        r16[r22] = r11;
        r22 = r10;
    L_0x0423:
        r10 = r19 + 1;
        r8[r19] = r15;
        r12 = r10 + 1;
        r14 = r4 & 512;
        if (r14 == 0) goto L_0x0430;
    L_0x042d:
        r14 = 536870912; // 0x20000000 float:1.0842022E-19 double:2.652494739E-315;
        goto L_0x0431;
    L_0x0430:
        r14 = 0;
    L_0x0431:
        r4 = r4 & 256;
        if (r4 == 0) goto L_0x0438;
    L_0x0435:
        r4 = 268435456; // 0x10000000 float:2.5243549E-29 double:1.32624737E-315;
        goto L_0x0439;
    L_0x0438:
        r4 = 0;
    L_0x0439:
        r4 = r4 | r14;
        r3 = r3 << 20;
        r3 = r3 | r4;
        r3 = r3 | r11;
        r8[r10] = r3;
        r19 = r12 + 1;
        r2 = r2 << 20;
        r2 = r2 | r9;
        r8[r12] = r2;
        r2 = r27;
        r3 = r28;
        r14 = r29;
        r11 = r31;
        r12 = r32;
        r10 = r34;
        r9 = r36;
        r15 = r37;
        r4 = 1;
        goto L_0x01c2;
    L_0x045a:
        r28 = r3;
        r34 = r10;
        r31 = r11;
        r32 = r12;
        r29 = r14;
        r1 = new com.google.android.gms.internal.ads.zzbsp;
        r10 = r0.zzaob();
        r12 = 0;
        r5 = r1;
        r6 = r8;
        r7 = r13;
        r8 = r34;
        r9 = r32;
        r13 = r16;
        r15 = r28;
        r16 = r42;
        r17 = r43;
        r18 = r44;
        r19 = r45;
        r20 = r46;
        r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20);
        return r1;
    L_0x0484:
        r0 = (com.google.android.gms.internal.ads.zzbtp) r0;
        r0.zzanz();
        r0 = new java.lang.NoSuchMethodError;
        r0.<init>();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbsp.zza(java.lang.Class, com.google.android.gms.internal.ads.zzbsj, com.google.android.gms.internal.ads.zzbst, com.google.android.gms.internal.ads.zzbrv, com.google.android.gms.internal.ads.zzbtu, com.google.android.gms.internal.ads.zzbqr, com.google.android.gms.internal.ads.zzbsg):com.google.android.gms.internal.ads.zzbsp");
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            StringBuilder stringBuilder = new StringBuilder(((40 + String.valueOf(str).length()) + String.valueOf(name).length()) + String.valueOf(arrays).length());
            stringBuilder.append("Field ");
            stringBuilder.append(str);
            stringBuilder.append(" for ");
            stringBuilder.append(name);
            stringBuilder.append(" not found. Known fields are ");
            stringBuilder.append(arrays);
            throw new RuntimeException(stringBuilder.toString());
        }
    }

    public final T newInstance() {
        return this.zzfsu.newInstance(this.zzfsm);
    }

    /* JADX WARNING: Missing block: B:8:0x0038, code skipped:
            if (com.google.android.gms.internal.ads.zzbte.zze(com.google.android.gms.internal.ads.zzbua.zzp(r10, r6), com.google.android.gms.internal.ads.zzbua.zzp(r11, r6)) != false) goto L_0x01b2;
     */
    /* JADX WARNING: Missing block: B:14:0x006a, code skipped:
            if (com.google.android.gms.internal.ads.zzbte.zze(com.google.android.gms.internal.ads.zzbua.zzp(r10, r6), com.google.android.gms.internal.ads.zzbua.zzp(r11, r6)) != false) goto L_0x01b2;
     */
    /* JADX WARNING: Missing block: B:18:0x007e, code skipped:
            if (com.google.android.gms.internal.ads.zzbua.zzl(r10, r6) == com.google.android.gms.internal.ads.zzbua.zzl(r11, r6)) goto L_0x01b2;
     */
    /* JADX WARNING: Missing block: B:22:0x0090, code skipped:
            if (com.google.android.gms.internal.ads.zzbua.zzk(r10, r6) == com.google.android.gms.internal.ads.zzbua.zzk(r11, r6)) goto L_0x01b2;
     */
    /* JADX WARNING: Missing block: B:26:0x00a4, code skipped:
            if (com.google.android.gms.internal.ads.zzbua.zzl(r10, r6) == com.google.android.gms.internal.ads.zzbua.zzl(r11, r6)) goto L_0x01b2;
     */
    /* JADX WARNING: Missing block: B:30:0x00b6, code skipped:
            if (com.google.android.gms.internal.ads.zzbua.zzk(r10, r6) == com.google.android.gms.internal.ads.zzbua.zzk(r11, r6)) goto L_0x01b2;
     */
    /* JADX WARNING: Missing block: B:34:0x00c8, code skipped:
            if (com.google.android.gms.internal.ads.zzbua.zzk(r10, r6) == com.google.android.gms.internal.ads.zzbua.zzk(r11, r6)) goto L_0x01b2;
     */
    /* JADX WARNING: Missing block: B:38:0x00da, code skipped:
            if (com.google.android.gms.internal.ads.zzbua.zzk(r10, r6) == com.google.android.gms.internal.ads.zzbua.zzk(r11, r6)) goto L_0x01b2;
     */
    /* JADX WARNING: Missing block: B:42:0x00f0, code skipped:
            if (com.google.android.gms.internal.ads.zzbte.zze(com.google.android.gms.internal.ads.zzbua.zzp(r10, r6), com.google.android.gms.internal.ads.zzbua.zzp(r11, r6)) != false) goto L_0x01b2;
     */
    /* JADX WARNING: Missing block: B:46:0x0106, code skipped:
            if (com.google.android.gms.internal.ads.zzbte.zze(com.google.android.gms.internal.ads.zzbua.zzp(r10, r6), com.google.android.gms.internal.ads.zzbua.zzp(r11, r6)) != false) goto L_0x01b2;
     */
    /* JADX WARNING: Missing block: B:50:0x011c, code skipped:
            if (com.google.android.gms.internal.ads.zzbte.zze(com.google.android.gms.internal.ads.zzbua.zzp(r10, r6), com.google.android.gms.internal.ads.zzbua.zzp(r11, r6)) != false) goto L_0x01b2;
     */
    /* JADX WARNING: Missing block: B:54:0x012e, code skipped:
            if (com.google.android.gms.internal.ads.zzbua.zzm(r10, r6) == com.google.android.gms.internal.ads.zzbua.zzm(r11, r6)) goto L_0x01b2;
     */
    /* JADX WARNING: Missing block: B:58:0x0140, code skipped:
            if (com.google.android.gms.internal.ads.zzbua.zzk(r10, r6) == com.google.android.gms.internal.ads.zzbua.zzk(r11, r6)) goto L_0x01b2;
     */
    /* JADX WARNING: Missing block: B:62:0x0154, code skipped:
            if (com.google.android.gms.internal.ads.zzbua.zzl(r10, r6) == com.google.android.gms.internal.ads.zzbua.zzl(r11, r6)) goto L_0x01b2;
     */
    /* JADX WARNING: Missing block: B:66:0x0165, code skipped:
            if (com.google.android.gms.internal.ads.zzbua.zzk(r10, r6) == com.google.android.gms.internal.ads.zzbua.zzk(r11, r6)) goto L_0x01b2;
     */
    /* JADX WARNING: Missing block: B:70:0x0178, code skipped:
            if (com.google.android.gms.internal.ads.zzbua.zzl(r10, r6) == com.google.android.gms.internal.ads.zzbua.zzl(r11, r6)) goto L_0x01b2;
     */
    /* JADX WARNING: Missing block: B:74:0x018b, code skipped:
            if (com.google.android.gms.internal.ads.zzbua.zzl(r10, r6) == com.google.android.gms.internal.ads.zzbua.zzl(r11, r6)) goto L_0x01b2;
     */
    /* JADX WARNING: Missing block: B:78:0x019c, code skipped:
            if (com.google.android.gms.internal.ads.zzbua.zzk(r10, r6) == com.google.android.gms.internal.ads.zzbua.zzk(r11, r6)) goto L_0x01b2;
     */
    /* JADX WARNING: Missing block: B:82:0x01af, code skipped:
            if (com.google.android.gms.internal.ads.zzbua.zzl(r10, r6) == com.google.android.gms.internal.ads.zzbua.zzl(r11, r6)) goto L_0x01b2;
     */
    /* JADX WARNING: Missing block: B:83:0x01b1, code skipped:
            r3 = false;
     */
    public final boolean equals(T r10, T r11) {
        /*
        r9 = this;
        r0 = r9.zzfsi;
        r1 = 0;
        r0 = r0.length;
        r2 = r1;
    L_0x0005:
        r3 = 1;
        if (r2 >= r0) goto L_0x01b9;
    L_0x0008:
        r4 = r9.zzft(r2);
        r5 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r6 = r4 & r5;
        r6 = (long) r6;
        r8 = 267386880; // 0xff00000 float:2.3665827E-29 double:1.321066716E-315;
        r4 = r4 & r8;
        r4 = r4 >>> 20;
        switch(r4) {
            case 0: goto L_0x019f;
            case 1: goto L_0x018e;
            case 2: goto L_0x017b;
            case 3: goto L_0x0168;
            case 4: goto L_0x0157;
            case 5: goto L_0x0144;
            case 6: goto L_0x0132;
            case 7: goto L_0x0120;
            case 8: goto L_0x010a;
            case 9: goto L_0x00f4;
            case 10: goto L_0x00de;
            case 11: goto L_0x00cc;
            case 12: goto L_0x00ba;
            case 13: goto L_0x00a8;
            case 14: goto L_0x0094;
            case 15: goto L_0x0082;
            case 16: goto L_0x006e;
            case 17: goto L_0x0058;
            case 18: goto L_0x004a;
            case 19: goto L_0x004a;
            case 20: goto L_0x004a;
            case 21: goto L_0x004a;
            case 22: goto L_0x004a;
            case 23: goto L_0x004a;
            case 24: goto L_0x004a;
            case 25: goto L_0x004a;
            case 26: goto L_0x004a;
            case 27: goto L_0x004a;
            case 28: goto L_0x004a;
            case 29: goto L_0x004a;
            case 30: goto L_0x004a;
            case 31: goto L_0x004a;
            case 32: goto L_0x004a;
            case 33: goto L_0x004a;
            case 34: goto L_0x004a;
            case 35: goto L_0x004a;
            case 36: goto L_0x004a;
            case 37: goto L_0x004a;
            case 38: goto L_0x004a;
            case 39: goto L_0x004a;
            case 40: goto L_0x004a;
            case 41: goto L_0x004a;
            case 42: goto L_0x004a;
            case 43: goto L_0x004a;
            case 44: goto L_0x004a;
            case 45: goto L_0x004a;
            case 46: goto L_0x004a;
            case 47: goto L_0x004a;
            case 48: goto L_0x004a;
            case 49: goto L_0x004a;
            case 50: goto L_0x003c;
            case 51: goto L_0x001c;
            case 52: goto L_0x001c;
            case 53: goto L_0x001c;
            case 54: goto L_0x001c;
            case 55: goto L_0x001c;
            case 56: goto L_0x001c;
            case 57: goto L_0x001c;
            case 58: goto L_0x001c;
            case 59: goto L_0x001c;
            case 60: goto L_0x001c;
            case 61: goto L_0x001c;
            case 62: goto L_0x001c;
            case 63: goto L_0x001c;
            case 64: goto L_0x001c;
            case 65: goto L_0x001c;
            case 66: goto L_0x001c;
            case 67: goto L_0x001c;
            case 68: goto L_0x001c;
            default: goto L_0x001a;
        };
    L_0x001a:
        goto L_0x01b2;
    L_0x001c:
        r4 = r9.zzfu(r2);
        r4 = r4 & r5;
        r4 = (long) r4;
        r8 = com.google.android.gms.internal.ads.zzbua.zzk(r10, r4);
        r4 = com.google.android.gms.internal.ads.zzbua.zzk(r11, r4);
        if (r8 != r4) goto L_0x01b1;
    L_0x002c:
        r4 = com.google.android.gms.internal.ads.zzbua.zzp(r10, r6);
        r5 = com.google.android.gms.internal.ads.zzbua.zzp(r11, r6);
        r4 = com.google.android.gms.internal.ads.zzbte.zze(r4, r5);
        if (r4 != 0) goto L_0x01b2;
    L_0x003a:
        goto L_0x01b1;
    L_0x003c:
        r3 = com.google.android.gms.internal.ads.zzbua.zzp(r10, r6);
        r4 = com.google.android.gms.internal.ads.zzbua.zzp(r11, r6);
        r3 = com.google.android.gms.internal.ads.zzbte.zze(r3, r4);
        goto L_0x01b2;
    L_0x004a:
        r3 = com.google.android.gms.internal.ads.zzbua.zzp(r10, r6);
        r4 = com.google.android.gms.internal.ads.zzbua.zzp(r11, r6);
        r3 = com.google.android.gms.internal.ads.zzbte.zze(r3, r4);
        goto L_0x01b2;
    L_0x0058:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01b1;
    L_0x005e:
        r4 = com.google.android.gms.internal.ads.zzbua.zzp(r10, r6);
        r5 = com.google.android.gms.internal.ads.zzbua.zzp(r11, r6);
        r4 = com.google.android.gms.internal.ads.zzbte.zze(r4, r5);
        if (r4 != 0) goto L_0x01b2;
    L_0x006c:
        goto L_0x01b1;
    L_0x006e:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01b1;
    L_0x0074:
        r4 = com.google.android.gms.internal.ads.zzbua.zzl(r10, r6);
        r6 = com.google.android.gms.internal.ads.zzbua.zzl(r11, r6);
        r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r8 == 0) goto L_0x01b2;
    L_0x0080:
        goto L_0x01b1;
    L_0x0082:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01b1;
    L_0x0088:
        r4 = com.google.android.gms.internal.ads.zzbua.zzk(r10, r6);
        r5 = com.google.android.gms.internal.ads.zzbua.zzk(r11, r6);
        if (r4 == r5) goto L_0x01b2;
    L_0x0092:
        goto L_0x01b1;
    L_0x0094:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01b1;
    L_0x009a:
        r4 = com.google.android.gms.internal.ads.zzbua.zzl(r10, r6);
        r6 = com.google.android.gms.internal.ads.zzbua.zzl(r11, r6);
        r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r8 == 0) goto L_0x01b2;
    L_0x00a6:
        goto L_0x01b1;
    L_0x00a8:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01b1;
    L_0x00ae:
        r4 = com.google.android.gms.internal.ads.zzbua.zzk(r10, r6);
        r5 = com.google.android.gms.internal.ads.zzbua.zzk(r11, r6);
        if (r4 == r5) goto L_0x01b2;
    L_0x00b8:
        goto L_0x01b1;
    L_0x00ba:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01b1;
    L_0x00c0:
        r4 = com.google.android.gms.internal.ads.zzbua.zzk(r10, r6);
        r5 = com.google.android.gms.internal.ads.zzbua.zzk(r11, r6);
        if (r4 == r5) goto L_0x01b2;
    L_0x00ca:
        goto L_0x01b1;
    L_0x00cc:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01b1;
    L_0x00d2:
        r4 = com.google.android.gms.internal.ads.zzbua.zzk(r10, r6);
        r5 = com.google.android.gms.internal.ads.zzbua.zzk(r11, r6);
        if (r4 == r5) goto L_0x01b2;
    L_0x00dc:
        goto L_0x01b1;
    L_0x00de:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01b1;
    L_0x00e4:
        r4 = com.google.android.gms.internal.ads.zzbua.zzp(r10, r6);
        r5 = com.google.android.gms.internal.ads.zzbua.zzp(r11, r6);
        r4 = com.google.android.gms.internal.ads.zzbte.zze(r4, r5);
        if (r4 != 0) goto L_0x01b2;
    L_0x00f2:
        goto L_0x01b1;
    L_0x00f4:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01b1;
    L_0x00fa:
        r4 = com.google.android.gms.internal.ads.zzbua.zzp(r10, r6);
        r5 = com.google.android.gms.internal.ads.zzbua.zzp(r11, r6);
        r4 = com.google.android.gms.internal.ads.zzbte.zze(r4, r5);
        if (r4 != 0) goto L_0x01b2;
    L_0x0108:
        goto L_0x01b1;
    L_0x010a:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01b1;
    L_0x0110:
        r4 = com.google.android.gms.internal.ads.zzbua.zzp(r10, r6);
        r5 = com.google.android.gms.internal.ads.zzbua.zzp(r11, r6);
        r4 = com.google.android.gms.internal.ads.zzbte.zze(r4, r5);
        if (r4 != 0) goto L_0x01b2;
    L_0x011e:
        goto L_0x01b1;
    L_0x0120:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01b1;
    L_0x0126:
        r4 = com.google.android.gms.internal.ads.zzbua.zzm(r10, r6);
        r5 = com.google.android.gms.internal.ads.zzbua.zzm(r11, r6);
        if (r4 == r5) goto L_0x01b2;
    L_0x0130:
        goto L_0x01b1;
    L_0x0132:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01b1;
    L_0x0138:
        r4 = com.google.android.gms.internal.ads.zzbua.zzk(r10, r6);
        r5 = com.google.android.gms.internal.ads.zzbua.zzk(r11, r6);
        if (r4 == r5) goto L_0x01b2;
    L_0x0142:
        goto L_0x01b1;
    L_0x0144:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01b1;
    L_0x014a:
        r4 = com.google.android.gms.internal.ads.zzbua.zzl(r10, r6);
        r6 = com.google.android.gms.internal.ads.zzbua.zzl(r11, r6);
        r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r8 == 0) goto L_0x01b2;
    L_0x0156:
        goto L_0x01b1;
    L_0x0157:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01b1;
    L_0x015d:
        r4 = com.google.android.gms.internal.ads.zzbua.zzk(r10, r6);
        r5 = com.google.android.gms.internal.ads.zzbua.zzk(r11, r6);
        if (r4 == r5) goto L_0x01b2;
    L_0x0167:
        goto L_0x01b1;
    L_0x0168:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01b1;
    L_0x016e:
        r4 = com.google.android.gms.internal.ads.zzbua.zzl(r10, r6);
        r6 = com.google.android.gms.internal.ads.zzbua.zzl(r11, r6);
        r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r8 == 0) goto L_0x01b2;
    L_0x017a:
        goto L_0x01b1;
    L_0x017b:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01b1;
    L_0x0181:
        r4 = com.google.android.gms.internal.ads.zzbua.zzl(r10, r6);
        r6 = com.google.android.gms.internal.ads.zzbua.zzl(r11, r6);
        r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r8 == 0) goto L_0x01b2;
    L_0x018d:
        goto L_0x01b1;
    L_0x018e:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01b1;
    L_0x0194:
        r4 = com.google.android.gms.internal.ads.zzbua.zzk(r10, r6);
        r5 = com.google.android.gms.internal.ads.zzbua.zzk(r11, r6);
        if (r4 == r5) goto L_0x01b2;
    L_0x019e:
        goto L_0x01b1;
    L_0x019f:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01b1;
    L_0x01a5:
        r4 = com.google.android.gms.internal.ads.zzbua.zzl(r10, r6);
        r6 = com.google.android.gms.internal.ads.zzbua.zzl(r11, r6);
        r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r8 == 0) goto L_0x01b2;
    L_0x01b1:
        r3 = r1;
    L_0x01b2:
        if (r3 != 0) goto L_0x01b5;
    L_0x01b4:
        return r1;
    L_0x01b5:
        r2 = r2 + 3;
        goto L_0x0005;
    L_0x01b9:
        r0 = r9.zzfsw;
        r0 = r0.zzag(r10);
        r2 = r9.zzfsw;
        r2 = r2.zzag(r11);
        r0 = r0.equals(r2);
        if (r0 != 0) goto L_0x01cc;
    L_0x01cb:
        return r1;
    L_0x01cc:
        r0 = r9.zzfsn;
        if (r0 == 0) goto L_0x01e1;
    L_0x01d0:
        r0 = r9.zzfsx;
        r10 = r0.zzq(r10);
        r0 = r9.zzfsx;
        r11 = r0.zzq(r11);
        r10 = r10.equals(r11);
        return r10;
    L_0x01e1:
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbsp.equals(java.lang.Object, java.lang.Object):boolean");
    }

    public final int hashCode(T t) {
        int i = 0;
        int length = this.zzfsi.length;
        int i2 = 0;
        while (i < length) {
            int zzft = zzft(i);
            int i3 = this.zzfsi[i];
            long j = (long) (1048575 & zzft);
            int i4 = 37;
            Object zzp;
            switch ((zzft & 267386880) >>> 20) {
                case 0:
                    i2 = (i2 * 53) + zzbrf.zzbi(Double.doubleToLongBits(zzbua.zzo(t, j)));
                    break;
                case 1:
                    i2 = (i2 * 53) + Float.floatToIntBits(zzbua.zzn(t, j));
                    break;
                case 2:
                    i2 = (i2 * 53) + zzbrf.zzbi(zzbua.zzl(t, j));
                    break;
                case 3:
                    i2 = (i2 * 53) + zzbrf.zzbi(zzbua.zzl(t, j));
                    break;
                case 4:
                    i2 = (i2 * 53) + zzbua.zzk(t, j);
                    break;
                case 5:
                    i2 = (i2 * 53) + zzbrf.zzbi(zzbua.zzl(t, j));
                    break;
                case 6:
                    i2 = (i2 * 53) + zzbua.zzk(t, j);
                    break;
                case 7:
                    i2 = (i2 * 53) + zzbrf.zzbf(zzbua.zzm(t, j));
                    break;
                case 8:
                    i2 = (i2 * 53) + ((String) zzbua.zzp(t, j)).hashCode();
                    break;
                case 9:
                    zzp = zzbua.zzp(t, j);
                    if (zzp != null) {
                        i4 = zzp.hashCode();
                    }
                    i2 = (i2 * 53) + i4;
                    break;
                case 10:
                    i2 = (i2 * 53) + zzbua.zzp(t, j).hashCode();
                    break;
                case 11:
                    i2 = (i2 * 53) + zzbua.zzk(t, j);
                    break;
                case 12:
                    i2 = (i2 * 53) + zzbua.zzk(t, j);
                    break;
                case 13:
                    i2 = (i2 * 53) + zzbua.zzk(t, j);
                    break;
                case 14:
                    i2 = (i2 * 53) + zzbrf.zzbi(zzbua.zzl(t, j));
                    break;
                case 15:
                    i2 = (i2 * 53) + zzbua.zzk(t, j);
                    break;
                case 16:
                    i2 = (i2 * 53) + zzbrf.zzbi(zzbua.zzl(t, j));
                    break;
                case 17:
                    zzp = zzbua.zzp(t, j);
                    if (zzp != null) {
                        i4 = zzp.hashCode();
                    }
                    i2 = (i2 * 53) + i4;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i2 = (i2 * 53) + zzbua.zzp(t, j).hashCode();
                    break;
                case 50:
                    i2 = (i2 * 53) + zzbua.zzp(t, j).hashCode();
                    break;
                case 51:
                    if (!zza((Object) t, i3, i)) {
                        break;
                    }
                    i2 = (i2 * 53) + zzbrf.zzbi(Double.doubleToLongBits(zzf(t, j)));
                    break;
                case 52:
                    if (!zza((Object) t, i3, i)) {
                        break;
                    }
                    i2 = (i2 * 53) + Float.floatToIntBits(zzg(t, j));
                    break;
                case 53:
                    if (!zza((Object) t, i3, i)) {
                        break;
                    }
                    i2 = (i2 * 53) + zzbrf.zzbi(zzi(t, j));
                    break;
                case 54:
                    if (!zza((Object) t, i3, i)) {
                        break;
                    }
                    i2 = (i2 * 53) + zzbrf.zzbi(zzi(t, j));
                    break;
                case 55:
                    if (!zza((Object) t, i3, i)) {
                        break;
                    }
                    i2 = (i2 * 53) + zzh(t, j);
                    break;
                case 56:
                    if (!zza((Object) t, i3, i)) {
                        break;
                    }
                    i2 = (i2 * 53) + zzbrf.zzbi(zzi(t, j));
                    break;
                case 57:
                    if (!zza((Object) t, i3, i)) {
                        break;
                    }
                    i2 = (i2 * 53) + zzh(t, j);
                    break;
                case 58:
                    if (!zza((Object) t, i3, i)) {
                        break;
                    }
                    i2 = (i2 * 53) + zzbrf.zzbf(zzj(t, j));
                    break;
                case 59:
                    if (!zza((Object) t, i3, i)) {
                        break;
                    }
                    i2 = (i2 * 53) + ((String) zzbua.zzp(t, j)).hashCode();
                    break;
                case 60:
                    if (!zza((Object) t, i3, i)) {
                        break;
                    }
                    i2 = (i2 * 53) + zzbua.zzp(t, j).hashCode();
                    break;
                case 61:
                    if (!zza((Object) t, i3, i)) {
                        break;
                    }
                    i2 = (i2 * 53) + zzbua.zzp(t, j).hashCode();
                    break;
                case 62:
                    if (!zza((Object) t, i3, i)) {
                        break;
                    }
                    i2 = (i2 * 53) + zzh(t, j);
                    break;
                case 63:
                    if (!zza((Object) t, i3, i)) {
                        break;
                    }
                    i2 = (i2 * 53) + zzh(t, j);
                    break;
                case 64:
                    if (!zza((Object) t, i3, i)) {
                        break;
                    }
                    i2 = (i2 * 53) + zzh(t, j);
                    break;
                case 65:
                    if (!zza((Object) t, i3, i)) {
                        break;
                    }
                    i2 = (i2 * 53) + zzbrf.zzbi(zzi(t, j));
                    break;
                case 66:
                    if (!zza((Object) t, i3, i)) {
                        break;
                    }
                    i2 = (i2 * 53) + zzh(t, j);
                    break;
                case 67:
                    if (!zza((Object) t, i3, i)) {
                        break;
                    }
                    i2 = (i2 * 53) + zzbrf.zzbi(zzi(t, j));
                    break;
                case 68:
                    if (!zza((Object) t, i3, i)) {
                        break;
                    }
                    i2 = (i2 * 53) + zzbua.zzp(t, j).hashCode();
                    break;
                default:
                    break;
            }
            i += 3;
        }
        i2 = (i2 * 53) + this.zzfsw.zzag(t).hashCode();
        return this.zzfsn ? (i2 * 53) + this.zzfsx.zzq(t).hashCode() : i2;
    }

    public final void zzd(T t, T t2) {
        if (t2 == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < this.zzfsi.length; i += 3) {
            int zzft = zzft(i);
            long j = (long) (1048575 & zzft);
            int i2 = this.zzfsi[i];
            switch ((zzft & 267386880) >>> 20) {
                case 0:
                    if (!zzd((Object) t2, i)) {
                        break;
                    }
                    zzbua.zza((Object) t, j, zzbua.zzo(t2, j));
                    zze((Object) t, i);
                    break;
                case 1:
                    if (!zzd((Object) t2, i)) {
                        break;
                    }
                    zzbua.zza((Object) t, j, zzbua.zzn(t2, j));
                    zze((Object) t, i);
                    break;
                case 2:
                    if (!zzd((Object) t2, i)) {
                        break;
                    }
                    zzbua.zza((Object) t, j, zzbua.zzl(t2, j));
                    zze((Object) t, i);
                    break;
                case 3:
                    if (!zzd((Object) t2, i)) {
                        break;
                    }
                    zzbua.zza((Object) t, j, zzbua.zzl(t2, j));
                    zze((Object) t, i);
                    break;
                case 4:
                    if (!zzd((Object) t2, i)) {
                        break;
                    }
                    zzbua.zzb((Object) t, j, zzbua.zzk(t2, j));
                    zze((Object) t, i);
                    break;
                case 5:
                    if (!zzd((Object) t2, i)) {
                        break;
                    }
                    zzbua.zza((Object) t, j, zzbua.zzl(t2, j));
                    zze((Object) t, i);
                    break;
                case 6:
                    if (!zzd((Object) t2, i)) {
                        break;
                    }
                    zzbua.zzb((Object) t, j, zzbua.zzk(t2, j));
                    zze((Object) t, i);
                    break;
                case 7:
                    if (!zzd((Object) t2, i)) {
                        break;
                    }
                    zzbua.zza((Object) t, j, zzbua.zzm(t2, j));
                    zze((Object) t, i);
                    break;
                case 8:
                    if (!zzd((Object) t2, i)) {
                        break;
                    }
                    zzbua.zza((Object) t, j, zzbua.zzp(t2, j));
                    zze((Object) t, i);
                    break;
                case 9:
                    zza((Object) t, (Object) t2, i);
                    break;
                case 10:
                    if (!zzd((Object) t2, i)) {
                        break;
                    }
                    zzbua.zza((Object) t, j, zzbua.zzp(t2, j));
                    zze((Object) t, i);
                    break;
                case 11:
                    if (!zzd((Object) t2, i)) {
                        break;
                    }
                    zzbua.zzb((Object) t, j, zzbua.zzk(t2, j));
                    zze((Object) t, i);
                    break;
                case 12:
                    if (!zzd((Object) t2, i)) {
                        break;
                    }
                    zzbua.zzb((Object) t, j, zzbua.zzk(t2, j));
                    zze((Object) t, i);
                    break;
                case 13:
                    if (!zzd((Object) t2, i)) {
                        break;
                    }
                    zzbua.zzb((Object) t, j, zzbua.zzk(t2, j));
                    zze((Object) t, i);
                    break;
                case 14:
                    if (!zzd((Object) t2, i)) {
                        break;
                    }
                    zzbua.zza((Object) t, j, zzbua.zzl(t2, j));
                    zze((Object) t, i);
                    break;
                case 15:
                    if (!zzd((Object) t2, i)) {
                        break;
                    }
                    zzbua.zzb((Object) t, j, zzbua.zzk(t2, j));
                    zze((Object) t, i);
                    break;
                case 16:
                    if (!zzd((Object) t2, i)) {
                        break;
                    }
                    zzbua.zza((Object) t, j, zzbua.zzl(t2, j));
                    zze((Object) t, i);
                    break;
                case 17:
                    zza((Object) t, (Object) t2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    this.zzfsv.zza(t, t2, j);
                    break;
                case 50:
                    zzbte.zza(this.zzfsy, (Object) t, (Object) t2, j);
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (!zza((Object) t2, i2, i)) {
                        break;
                    }
                    zzbua.zza((Object) t, j, zzbua.zzp(t2, j));
                    zzb((Object) t, i2, i);
                    break;
                case 60:
                    zzb((Object) t, (Object) t2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (!zza((Object) t2, i2, i)) {
                        break;
                    }
                    zzbua.zza((Object) t, j, zzbua.zzp(t2, j));
                    zzb((Object) t, i2, i);
                    break;
                case 68:
                    zzb((Object) t, (Object) t2, i);
                    break;
                default:
                    break;
            }
        }
        if (!this.zzfsp) {
            zzbte.zza(this.zzfsw, (Object) t, (Object) t2);
            if (this.zzfsn) {
                zzbte.zza(this.zzfsx, (Object) t, (Object) t2);
            }
        }
    }

    private final void zza(T t, T t2, int i) {
        long zzft = (long) (zzft(i) & 1048575);
        if (zzd((Object) t2, i)) {
            Object zzp = zzbua.zzp(t, zzft);
            Object zzp2 = zzbua.zzp(t2, zzft);
            if (zzp == null || zzp2 == null) {
                if (zzp2 != null) {
                    zzbua.zza((Object) t, zzft, zzp2);
                    zze((Object) t, i);
                }
                return;
            }
            zzbua.zza((Object) t, zzft, zzbrf.zzb(zzp, zzp2));
            zze((Object) t, i);
        }
    }

    private final void zzb(T t, T t2, int i) {
        int zzft = zzft(i);
        int i2 = this.zzfsi[i];
        long j = (long) (zzft & 1048575);
        if (zza((Object) t2, i2, i)) {
            Object zzp = zzbua.zzp(t, j);
            Object zzp2 = zzbua.zzp(t2, j);
            if (zzp == null || zzp2 == null) {
                if (zzp2 != null) {
                    zzbua.zza((Object) t, j, zzp2);
                    zzb((Object) t, i2, i);
                }
                return;
            }
            zzbua.zza((Object) t, j, zzbrf.zzb(zzp, zzp2));
            zzb((Object) t, i2, i);
        }
    }

    /* JADX WARNING: Missing block: B:410:0x09bd, code skipped:
            r6 = r11;
     */
    /* JADX WARNING: Missing block: B:413:0x09cc, code skipped:
            r9 = 0.0f;
            r18 = 0;
     */
    /* JADX WARNING: Missing block: B:473:0x0af0, code skipped:
            r3 = r3 + 3;
            r11 = r6;
            r6 = r9;
            r9 = r18;
     */
    public final int zzac(T r22) {
        /*
        r21 = this;
        r0 = r21;
        r1 = r22;
        r2 = r0.zzfsp;
        r3 = 267386880; // 0xff00000 float:2.3665827E-29 double:1.321066716E-315;
        r6 = 0;
        r7 = 1;
        r8 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r9 = 0;
        r11 = 0;
        if (r2 == 0) goto L_0x055f;
    L_0x0012:
        r2 = zzfsh;
        r12 = r11;
        r13 = r12;
    L_0x0016:
        r14 = r0.zzfsi;
        r14 = r14.length;
        if (r12 >= r14) goto L_0x0557;
    L_0x001b:
        r14 = r0.zzft(r12);
        r15 = r14 & r3;
        r15 = r15 >>> 20;
        r3 = r0.zzfsi;
        r3 = r3[r12];
        r14 = r14 & r8;
        r4 = (long) r14;
        r14 = com.google.android.gms.internal.ads.zzbqx.DOUBLE_LIST_PACKED;
        r14 = r14.id();
        if (r15 < r14) goto L_0x0041;
    L_0x0031:
        r14 = com.google.android.gms.internal.ads.zzbqx.SINT64_LIST_PACKED;
        r14 = r14.id();
        if (r15 > r14) goto L_0x0041;
    L_0x0039:
        r14 = r0.zzfsi;
        r17 = r12 + 2;
        r14 = r14[r17];
        r14 = r14 & r8;
        goto L_0x0042;
    L_0x0041:
        r14 = r11;
    L_0x0042:
        switch(r15) {
            case 0: goto L_0x0544;
            case 1: goto L_0x0538;
            case 2: goto L_0x0528;
            case 3: goto L_0x0518;
            case 4: goto L_0x0508;
            case 5: goto L_0x04fc;
            case 6: goto L_0x04f0;
            case 7: goto L_0x04e4;
            case 8: goto L_0x04c4;
            case 9: goto L_0x04af;
            case 10: goto L_0x049c;
            case 11: goto L_0x048b;
            case 12: goto L_0x047a;
            case 13: goto L_0x046d;
            case 14: goto L_0x0460;
            case 15: goto L_0x044f;
            case 16: goto L_0x043e;
            case 17: goto L_0x0427;
            case 18: goto L_0x041c;
            case 19: goto L_0x0411;
            case 20: goto L_0x0406;
            case 21: goto L_0x03fb;
            case 22: goto L_0x03f0;
            case 23: goto L_0x03e5;
            case 24: goto L_0x03da;
            case 25: goto L_0x03cf;
            case 26: goto L_0x03c4;
            case 27: goto L_0x03b5;
            case 28: goto L_0x03aa;
            case 29: goto L_0x039f;
            case 30: goto L_0x0394;
            case 31: goto L_0x0389;
            case 32: goto L_0x037e;
            case 33: goto L_0x0373;
            case 34: goto L_0x0368;
            case 35: goto L_0x0347;
            case 36: goto L_0x0326;
            case 37: goto L_0x0305;
            case 38: goto L_0x02e4;
            case 39: goto L_0x02c3;
            case 40: goto L_0x02a2;
            case 41: goto L_0x0281;
            case 42: goto L_0x0260;
            case 43: goto L_0x023f;
            case 44: goto L_0x021e;
            case 45: goto L_0x01fd;
            case 46: goto L_0x01dc;
            case 47: goto L_0x01bb;
            case 48: goto L_0x019a;
            case 49: goto L_0x018b;
            case 50: goto L_0x017a;
            case 51: goto L_0x016b;
            case 52: goto L_0x015e;
            case 53: goto L_0x014d;
            case 54: goto L_0x013c;
            case 55: goto L_0x012b;
            case 56: goto L_0x011e;
            case 57: goto L_0x0111;
            case 58: goto L_0x0104;
            case 59: goto L_0x00e4;
            case 60: goto L_0x00cf;
            case 61: goto L_0x00bc;
            case 62: goto L_0x00ab;
            case 63: goto L_0x009a;
            case 64: goto L_0x008d;
            case 65: goto L_0x0080;
            case 66: goto L_0x006f;
            case 67: goto L_0x005e;
            case 68: goto L_0x0047;
            default: goto L_0x0045;
        };
    L_0x0045:
        goto L_0x0551;
    L_0x0047:
        r14 = r0.zza(r1, r3, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x004d:
        r4 = com.google.android.gms.internal.ads.zzbua.zzp(r1, r4);
        r4 = (com.google.android.gms.internal.ads.zzbsl) r4;
        r5 = r0.zzfq(r12);
        r3 = com.google.android.gms.internal.ads.zzbqk.zzc(r3, r4, r5);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x005e:
        r14 = r0.zza(r1, r3, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x0064:
        r4 = zzi(r1, r4);
        r3 = com.google.android.gms.internal.ads.zzbqk.zzo(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x006f:
        r14 = r0.zza(r1, r3, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x0075:
        r4 = zzh(r1, r4);
        r3 = com.google.android.gms.internal.ads.zzbqk.zzab(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0080:
        r4 = r0.zza(r1, r3, r12);
        if (r4 == 0) goto L_0x0551;
    L_0x0086:
        r3 = com.google.android.gms.internal.ads.zzbqk.zzq(r3, r9);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x008d:
        r4 = r0.zza(r1, r3, r12);
        if (r4 == 0) goto L_0x0551;
    L_0x0093:
        r3 = com.google.android.gms.internal.ads.zzbqk.zzad(r3, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x009a:
        r14 = r0.zza(r1, r3, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x00a0:
        r4 = zzh(r1, r4);
        r3 = com.google.android.gms.internal.ads.zzbqk.zzae(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x00ab:
        r14 = r0.zza(r1, r3, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x00b1:
        r4 = zzh(r1, r4);
        r3 = com.google.android.gms.internal.ads.zzbqk.zzaa(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x00bc:
        r14 = r0.zza(r1, r3, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x00c2:
        r4 = com.google.android.gms.internal.ads.zzbua.zzp(r1, r4);
        r4 = (com.google.android.gms.internal.ads.zzbpu) r4;
        r3 = com.google.android.gms.internal.ads.zzbqk.zzc(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x00cf:
        r14 = r0.zza(r1, r3, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x00d5:
        r4 = com.google.android.gms.internal.ads.zzbua.zzp(r1, r4);
        r5 = r0.zzfq(r12);
        r3 = com.google.android.gms.internal.ads.zzbte.zzc(r3, r4, r5);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x00e4:
        r14 = r0.zza(r1, r3, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x00ea:
        r4 = com.google.android.gms.internal.ads.zzbua.zzp(r1, r4);
        r5 = r4 instanceof com.google.android.gms.internal.ads.zzbpu;
        if (r5 == 0) goto L_0x00fb;
    L_0x00f2:
        r4 = (com.google.android.gms.internal.ads.zzbpu) r4;
        r3 = com.google.android.gms.internal.ads.zzbqk.zzc(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x00fb:
        r4 = (java.lang.String) r4;
        r3 = com.google.android.gms.internal.ads.zzbqk.zzg(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0104:
        r4 = r0.zza(r1, r3, r12);
        if (r4 == 0) goto L_0x0551;
    L_0x010a:
        r3 = com.google.android.gms.internal.ads.zzbqk.zzk(r3, r7);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0111:
        r4 = r0.zza(r1, r3, r12);
        if (r4 == 0) goto L_0x0551;
    L_0x0117:
        r3 = com.google.android.gms.internal.ads.zzbqk.zzac(r3, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x011e:
        r4 = r0.zza(r1, r3, r12);
        if (r4 == 0) goto L_0x0551;
    L_0x0124:
        r3 = com.google.android.gms.internal.ads.zzbqk.zzp(r3, r9);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x012b:
        r14 = r0.zza(r1, r3, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x0131:
        r4 = zzh(r1, r4);
        r3 = com.google.android.gms.internal.ads.zzbqk.zzz(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x013c:
        r14 = r0.zza(r1, r3, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x0142:
        r4 = zzi(r1, r4);
        r3 = com.google.android.gms.internal.ads.zzbqk.zzn(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x014d:
        r14 = r0.zza(r1, r3, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x0153:
        r4 = zzi(r1, r4);
        r3 = com.google.android.gms.internal.ads.zzbqk.zzm(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x015e:
        r4 = r0.zza(r1, r3, r12);
        if (r4 == 0) goto L_0x0551;
    L_0x0164:
        r3 = com.google.android.gms.internal.ads.zzbqk.zzb(r3, r6);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x016b:
        r4 = r0.zza(r1, r3, r12);
        if (r4 == 0) goto L_0x0551;
    L_0x0171:
        r4 = 0;
        r3 = com.google.android.gms.internal.ads.zzbqk.zzc(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x017a:
        r14 = r0.zzfsy;
        r4 = com.google.android.gms.internal.ads.zzbua.zzp(r1, r4);
        r5 = r0.zzfr(r12);
        r3 = r14.zzb(r3, r4, r5);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x018b:
        r4 = zze(r1, r4);
        r5 = r0.zzfq(r12);
        r3 = com.google.android.gms.internal.ads.zzbte.zzd(r3, r4, r5);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x019a:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.ads.zzbte.zzah(r4);
        if (r4 <= 0) goto L_0x0551;
    L_0x01a6:
        r5 = r0.zzfsq;
        if (r5 == 0) goto L_0x01ae;
    L_0x01aa:
        r14 = (long) r14;
        r2.putInt(r1, r14, r4);
    L_0x01ae:
        r3 = com.google.android.gms.internal.ads.zzbqk.zzfd(r3);
        r5 = com.google.android.gms.internal.ads.zzbqk.zzff(r4);
        r3 = r3 + r5;
        r3 = r3 + r4;
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x01bb:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.ads.zzbte.zzal(r4);
        if (r4 <= 0) goto L_0x0551;
    L_0x01c7:
        r5 = r0.zzfsq;
        if (r5 == 0) goto L_0x01cf;
    L_0x01cb:
        r14 = (long) r14;
        r2.putInt(r1, r14, r4);
    L_0x01cf:
        r3 = com.google.android.gms.internal.ads.zzbqk.zzfd(r3);
        r5 = com.google.android.gms.internal.ads.zzbqk.zzff(r4);
        r3 = r3 + r5;
        r3 = r3 + r4;
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x01dc:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.ads.zzbte.zzan(r4);
        if (r4 <= 0) goto L_0x0551;
    L_0x01e8:
        r5 = r0.zzfsq;
        if (r5 == 0) goto L_0x01f0;
    L_0x01ec:
        r14 = (long) r14;
        r2.putInt(r1, r14, r4);
    L_0x01f0:
        r3 = com.google.android.gms.internal.ads.zzbqk.zzfd(r3);
        r5 = com.google.android.gms.internal.ads.zzbqk.zzff(r4);
        r3 = r3 + r5;
        r3 = r3 + r4;
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x01fd:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.ads.zzbte.zzam(r4);
        if (r4 <= 0) goto L_0x0551;
    L_0x0209:
        r5 = r0.zzfsq;
        if (r5 == 0) goto L_0x0211;
    L_0x020d:
        r14 = (long) r14;
        r2.putInt(r1, r14, r4);
    L_0x0211:
        r3 = com.google.android.gms.internal.ads.zzbqk.zzfd(r3);
        r5 = com.google.android.gms.internal.ads.zzbqk.zzff(r4);
        r3 = r3 + r5;
        r3 = r3 + r4;
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x021e:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.ads.zzbte.zzai(r4);
        if (r4 <= 0) goto L_0x0551;
    L_0x022a:
        r5 = r0.zzfsq;
        if (r5 == 0) goto L_0x0232;
    L_0x022e:
        r14 = (long) r14;
        r2.putInt(r1, r14, r4);
    L_0x0232:
        r3 = com.google.android.gms.internal.ads.zzbqk.zzfd(r3);
        r5 = com.google.android.gms.internal.ads.zzbqk.zzff(r4);
        r3 = r3 + r5;
        r3 = r3 + r4;
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x023f:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.ads.zzbte.zzak(r4);
        if (r4 <= 0) goto L_0x0551;
    L_0x024b:
        r5 = r0.zzfsq;
        if (r5 == 0) goto L_0x0253;
    L_0x024f:
        r14 = (long) r14;
        r2.putInt(r1, r14, r4);
    L_0x0253:
        r3 = com.google.android.gms.internal.ads.zzbqk.zzfd(r3);
        r5 = com.google.android.gms.internal.ads.zzbqk.zzff(r4);
        r3 = r3 + r5;
        r3 = r3 + r4;
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0260:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.ads.zzbte.zzao(r4);
        if (r4 <= 0) goto L_0x0551;
    L_0x026c:
        r5 = r0.zzfsq;
        if (r5 == 0) goto L_0x0274;
    L_0x0270:
        r14 = (long) r14;
        r2.putInt(r1, r14, r4);
    L_0x0274:
        r3 = com.google.android.gms.internal.ads.zzbqk.zzfd(r3);
        r5 = com.google.android.gms.internal.ads.zzbqk.zzff(r4);
        r3 = r3 + r5;
        r3 = r3 + r4;
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0281:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.ads.zzbte.zzam(r4);
        if (r4 <= 0) goto L_0x0551;
    L_0x028d:
        r5 = r0.zzfsq;
        if (r5 == 0) goto L_0x0295;
    L_0x0291:
        r14 = (long) r14;
        r2.putInt(r1, r14, r4);
    L_0x0295:
        r3 = com.google.android.gms.internal.ads.zzbqk.zzfd(r3);
        r5 = com.google.android.gms.internal.ads.zzbqk.zzff(r4);
        r3 = r3 + r5;
        r3 = r3 + r4;
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x02a2:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.ads.zzbte.zzan(r4);
        if (r4 <= 0) goto L_0x0551;
    L_0x02ae:
        r5 = r0.zzfsq;
        if (r5 == 0) goto L_0x02b6;
    L_0x02b2:
        r14 = (long) r14;
        r2.putInt(r1, r14, r4);
    L_0x02b6:
        r3 = com.google.android.gms.internal.ads.zzbqk.zzfd(r3);
        r5 = com.google.android.gms.internal.ads.zzbqk.zzff(r4);
        r3 = r3 + r5;
        r3 = r3 + r4;
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x02c3:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.ads.zzbte.zzaj(r4);
        if (r4 <= 0) goto L_0x0551;
    L_0x02cf:
        r5 = r0.zzfsq;
        if (r5 == 0) goto L_0x02d7;
    L_0x02d3:
        r14 = (long) r14;
        r2.putInt(r1, r14, r4);
    L_0x02d7:
        r3 = com.google.android.gms.internal.ads.zzbqk.zzfd(r3);
        r5 = com.google.android.gms.internal.ads.zzbqk.zzff(r4);
        r3 = r3 + r5;
        r3 = r3 + r4;
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x02e4:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.ads.zzbte.zzag(r4);
        if (r4 <= 0) goto L_0x0551;
    L_0x02f0:
        r5 = r0.zzfsq;
        if (r5 == 0) goto L_0x02f8;
    L_0x02f4:
        r14 = (long) r14;
        r2.putInt(r1, r14, r4);
    L_0x02f8:
        r3 = com.google.android.gms.internal.ads.zzbqk.zzfd(r3);
        r5 = com.google.android.gms.internal.ads.zzbqk.zzff(r4);
        r3 = r3 + r5;
        r3 = r3 + r4;
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0305:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.ads.zzbte.zzaf(r4);
        if (r4 <= 0) goto L_0x0551;
    L_0x0311:
        r5 = r0.zzfsq;
        if (r5 == 0) goto L_0x0319;
    L_0x0315:
        r14 = (long) r14;
        r2.putInt(r1, r14, r4);
    L_0x0319:
        r3 = com.google.android.gms.internal.ads.zzbqk.zzfd(r3);
        r5 = com.google.android.gms.internal.ads.zzbqk.zzff(r4);
        r3 = r3 + r5;
        r3 = r3 + r4;
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0326:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.ads.zzbte.zzam(r4);
        if (r4 <= 0) goto L_0x0551;
    L_0x0332:
        r5 = r0.zzfsq;
        if (r5 == 0) goto L_0x033a;
    L_0x0336:
        r14 = (long) r14;
        r2.putInt(r1, r14, r4);
    L_0x033a:
        r3 = com.google.android.gms.internal.ads.zzbqk.zzfd(r3);
        r5 = com.google.android.gms.internal.ads.zzbqk.zzff(r4);
        r3 = r3 + r5;
        r3 = r3 + r4;
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0347:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.ads.zzbte.zzan(r4);
        if (r4 <= 0) goto L_0x0551;
    L_0x0353:
        r5 = r0.zzfsq;
        if (r5 == 0) goto L_0x035b;
    L_0x0357:
        r14 = (long) r14;
        r2.putInt(r1, r14, r4);
    L_0x035b:
        r3 = com.google.android.gms.internal.ads.zzbqk.zzfd(r3);
        r5 = com.google.android.gms.internal.ads.zzbqk.zzff(r4);
        r3 = r3 + r5;
        r3 = r3 + r4;
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0368:
        r4 = zze(r1, r4);
        r3 = com.google.android.gms.internal.ads.zzbte.zzq(r3, r4, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0373:
        r4 = zze(r1, r4);
        r3 = com.google.android.gms.internal.ads.zzbte.zzu(r3, r4, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x037e:
        r4 = zze(r1, r4);
        r3 = com.google.android.gms.internal.ads.zzbte.zzw(r3, r4, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0389:
        r4 = zze(r1, r4);
        r3 = com.google.android.gms.internal.ads.zzbte.zzv(r3, r4, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0394:
        r4 = zze(r1, r4);
        r3 = com.google.android.gms.internal.ads.zzbte.zzr(r3, r4, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x039f:
        r4 = zze(r1, r4);
        r3 = com.google.android.gms.internal.ads.zzbte.zzt(r3, r4, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x03aa:
        r4 = zze(r1, r4);
        r3 = com.google.android.gms.internal.ads.zzbte.zzd(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x03b5:
        r4 = zze(r1, r4);
        r5 = r0.zzfq(r12);
        r3 = com.google.android.gms.internal.ads.zzbte.zzc(r3, r4, r5);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x03c4:
        r4 = zze(r1, r4);
        r3 = com.google.android.gms.internal.ads.zzbte.zzc(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x03cf:
        r4 = zze(r1, r4);
        r3 = com.google.android.gms.internal.ads.zzbte.zzx(r3, r4, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x03da:
        r4 = zze(r1, r4);
        r3 = com.google.android.gms.internal.ads.zzbte.zzv(r3, r4, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x03e5:
        r4 = zze(r1, r4);
        r3 = com.google.android.gms.internal.ads.zzbte.zzw(r3, r4, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x03f0:
        r4 = zze(r1, r4);
        r3 = com.google.android.gms.internal.ads.zzbte.zzs(r3, r4, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x03fb:
        r4 = zze(r1, r4);
        r3 = com.google.android.gms.internal.ads.zzbte.zzp(r3, r4, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0406:
        r4 = zze(r1, r4);
        r3 = com.google.android.gms.internal.ads.zzbte.zzo(r3, r4, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0411:
        r4 = zze(r1, r4);
        r3 = com.google.android.gms.internal.ads.zzbte.zzv(r3, r4, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x041c:
        r4 = zze(r1, r4);
        r3 = com.google.android.gms.internal.ads.zzbte.zzw(r3, r4, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0427:
        r14 = r0.zzd(r1, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x042d:
        r4 = com.google.android.gms.internal.ads.zzbua.zzp(r1, r4);
        r4 = (com.google.android.gms.internal.ads.zzbsl) r4;
        r5 = r0.zzfq(r12);
        r3 = com.google.android.gms.internal.ads.zzbqk.zzc(r3, r4, r5);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x043e:
        r14 = r0.zzd(r1, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x0444:
        r4 = com.google.android.gms.internal.ads.zzbua.zzl(r1, r4);
        r3 = com.google.android.gms.internal.ads.zzbqk.zzo(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x044f:
        r14 = r0.zzd(r1, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x0455:
        r4 = com.google.android.gms.internal.ads.zzbua.zzk(r1, r4);
        r3 = com.google.android.gms.internal.ads.zzbqk.zzab(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0460:
        r4 = r0.zzd(r1, r12);
        if (r4 == 0) goto L_0x0551;
    L_0x0466:
        r3 = com.google.android.gms.internal.ads.zzbqk.zzq(r3, r9);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x046d:
        r4 = r0.zzd(r1, r12);
        if (r4 == 0) goto L_0x0551;
    L_0x0473:
        r3 = com.google.android.gms.internal.ads.zzbqk.zzad(r3, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x047a:
        r14 = r0.zzd(r1, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x0480:
        r4 = com.google.android.gms.internal.ads.zzbua.zzk(r1, r4);
        r3 = com.google.android.gms.internal.ads.zzbqk.zzae(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x048b:
        r14 = r0.zzd(r1, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x0491:
        r4 = com.google.android.gms.internal.ads.zzbua.zzk(r1, r4);
        r3 = com.google.android.gms.internal.ads.zzbqk.zzaa(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x049c:
        r14 = r0.zzd(r1, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x04a2:
        r4 = com.google.android.gms.internal.ads.zzbua.zzp(r1, r4);
        r4 = (com.google.android.gms.internal.ads.zzbpu) r4;
        r3 = com.google.android.gms.internal.ads.zzbqk.zzc(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x04af:
        r14 = r0.zzd(r1, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x04b5:
        r4 = com.google.android.gms.internal.ads.zzbua.zzp(r1, r4);
        r5 = r0.zzfq(r12);
        r3 = com.google.android.gms.internal.ads.zzbte.zzc(r3, r4, r5);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x04c4:
        r14 = r0.zzd(r1, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x04ca:
        r4 = com.google.android.gms.internal.ads.zzbua.zzp(r1, r4);
        r5 = r4 instanceof com.google.android.gms.internal.ads.zzbpu;
        if (r5 == 0) goto L_0x04db;
    L_0x04d2:
        r4 = (com.google.android.gms.internal.ads.zzbpu) r4;
        r3 = com.google.android.gms.internal.ads.zzbqk.zzc(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x04db:
        r4 = (java.lang.String) r4;
        r3 = com.google.android.gms.internal.ads.zzbqk.zzg(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x04e4:
        r4 = r0.zzd(r1, r12);
        if (r4 == 0) goto L_0x0551;
    L_0x04ea:
        r3 = com.google.android.gms.internal.ads.zzbqk.zzk(r3, r7);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x04f0:
        r4 = r0.zzd(r1, r12);
        if (r4 == 0) goto L_0x0551;
    L_0x04f6:
        r3 = com.google.android.gms.internal.ads.zzbqk.zzac(r3, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x04fc:
        r4 = r0.zzd(r1, r12);
        if (r4 == 0) goto L_0x0551;
    L_0x0502:
        r3 = com.google.android.gms.internal.ads.zzbqk.zzp(r3, r9);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0508:
        r14 = r0.zzd(r1, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x050e:
        r4 = com.google.android.gms.internal.ads.zzbua.zzk(r1, r4);
        r3 = com.google.android.gms.internal.ads.zzbqk.zzz(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0518:
        r14 = r0.zzd(r1, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x051e:
        r4 = com.google.android.gms.internal.ads.zzbua.zzl(r1, r4);
        r3 = com.google.android.gms.internal.ads.zzbqk.zzn(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0528:
        r14 = r0.zzd(r1, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x052e:
        r4 = com.google.android.gms.internal.ads.zzbua.zzl(r1, r4);
        r3 = com.google.android.gms.internal.ads.zzbqk.zzm(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0538:
        r4 = r0.zzd(r1, r12);
        if (r4 == 0) goto L_0x0551;
    L_0x053e:
        r3 = com.google.android.gms.internal.ads.zzbqk.zzb(r3, r6);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0544:
        r4 = r0.zzd(r1, r12);
        if (r4 == 0) goto L_0x0551;
    L_0x054a:
        r4 = 0;
        r3 = com.google.android.gms.internal.ads.zzbqk.zzc(r3, r4);
        r13 = r13 + r3;
    L_0x0551:
        r12 = r12 + 3;
        r3 = 267386880; // 0xff00000 float:2.3665827E-29 double:1.321066716E-315;
        goto L_0x0016;
    L_0x0557:
        r2 = r0.zzfsw;
        r1 = zza(r2, r1);
        r13 = r13 + r1;
        return r13;
    L_0x055f:
        r2 = zzfsh;
        r3 = -1;
        r5 = r3;
        r3 = r11;
        r4 = r3;
        r12 = r4;
    L_0x0566:
        r13 = r0.zzfsi;
        r13 = r13.length;
        if (r3 >= r13) goto L_0x0af8;
    L_0x056b:
        r13 = r0.zzft(r3);
        r14 = r0.zzfsi;
        r14 = r14[r3];
        r15 = 267386880; // 0xff00000 float:2.3665827E-29 double:1.321066716E-315;
        r16 = r13 & r15;
        r15 = r16 >>> 20;
        r6 = 17;
        if (r15 > r6) goto L_0x0592;
    L_0x057d:
        r6 = r0.zzfsi;
        r16 = r3 + 2;
        r6 = r6[r16];
        r11 = r6 & r8;
        r16 = r6 >>> 20;
        r16 = r7 << r16;
        if (r11 == r5) goto L_0x05b3;
    L_0x058b:
        r9 = (long) r11;
        r12 = r2.getInt(r1, r9);
        r5 = r11;
        goto L_0x05b3;
    L_0x0592:
        r6 = r0.zzfsq;
        if (r6 == 0) goto L_0x05b0;
    L_0x0596:
        r6 = com.google.android.gms.internal.ads.zzbqx.DOUBLE_LIST_PACKED;
        r6 = r6.id();
        if (r15 < r6) goto L_0x05b0;
    L_0x059e:
        r6 = com.google.android.gms.internal.ads.zzbqx.SINT64_LIST_PACKED;
        r6 = r6.id();
        if (r15 > r6) goto L_0x05b0;
    L_0x05a6:
        r6 = r0.zzfsi;
        r9 = r3 + 2;
        r6 = r6[r9];
        r11 = r6 & r8;
        r6 = r11;
        goto L_0x05b1;
    L_0x05b0:
        r6 = 0;
    L_0x05b1:
        r16 = 0;
    L_0x05b3:
        r9 = r13 & r8;
        r9 = (long) r9;
        switch(r15) {
            case 0: goto L_0x0ae1;
            case 1: goto L_0x0ad1;
            case 2: goto L_0x0abf;
            case 3: goto L_0x0aae;
            case 4: goto L_0x0a9d;
            case 5: goto L_0x0a8e;
            case 6: goto L_0x0a82;
            case 7: goto L_0x0a77;
            case 8: goto L_0x0a59;
            case 9: goto L_0x0a46;
            case 10: goto L_0x0a36;
            case 11: goto L_0x0a28;
            case 12: goto L_0x0a1a;
            case 13: goto L_0x0a0f;
            case 14: goto L_0x0a03;
            case 15: goto L_0x09f5;
            case 16: goto L_0x09e7;
            case 17: goto L_0x09d3;
            case 18: goto L_0x09bf;
            case 19: goto L_0x09b1;
            case 20: goto L_0x09a4;
            case 21: goto L_0x0997;
            case 22: goto L_0x098a;
            case 23: goto L_0x097d;
            case 24: goto L_0x0970;
            case 25: goto L_0x0963;
            case 26: goto L_0x0957;
            case 27: goto L_0x0946;
            case 28: goto L_0x0939;
            case 29: goto L_0x092b;
            case 30: goto L_0x091d;
            case 31: goto L_0x090f;
            case 32: goto L_0x0901;
            case 33: goto L_0x08f3;
            case 34: goto L_0x08e5;
            case 35: goto L_0x08c4;
            case 36: goto L_0x08a3;
            case 37: goto L_0x0882;
            case 38: goto L_0x0861;
            case 39: goto L_0x0840;
            case 40: goto L_0x081f;
            case 41: goto L_0x07fe;
            case 42: goto L_0x07dd;
            case 43: goto L_0x07bc;
            case 44: goto L_0x079b;
            case 45: goto L_0x077a;
            case 46: goto L_0x0759;
            case 47: goto L_0x0738;
            case 48: goto L_0x0717;
            case 49: goto L_0x0706;
            case 50: goto L_0x06f5;
            case 51: goto L_0x06e6;
            case 52: goto L_0x06d8;
            case 53: goto L_0x06c7;
            case 54: goto L_0x06b6;
            case 55: goto L_0x06a5;
            case 56: goto L_0x0696;
            case 57: goto L_0x0688;
            case 58: goto L_0x067b;
            case 59: goto L_0x065b;
            case 60: goto L_0x0646;
            case 61: goto L_0x0633;
            case 62: goto L_0x0622;
            case 63: goto L_0x0611;
            case 64: goto L_0x0603;
            case 65: goto L_0x05f4;
            case 66: goto L_0x05e3;
            case 67: goto L_0x05d2;
            case 68: goto L_0x05bb;
            default: goto L_0x05b9;
        };
    L_0x05b9:
        goto L_0x09cb;
    L_0x05bb:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x05c1:
        r6 = r2.getObject(r1, r9);
        r6 = (com.google.android.gms.internal.ads.zzbsl) r6;
        r9 = r0.zzfq(r3);
        r6 = com.google.android.gms.internal.ads.zzbqk.zzc(r14, r6, r9);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x05d2:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x05d8:
        r9 = zzi(r1, r9);
        r6 = com.google.android.gms.internal.ads.zzbqk.zzo(r14, r9);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x05e3:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x05e9:
        r6 = zzh(r1, r9);
        r6 = com.google.android.gms.internal.ads.zzbqk.zzab(r14, r6);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x05f4:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x05fa:
        r9 = 0;
        r6 = com.google.android.gms.internal.ads.zzbqk.zzq(r14, r9);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0603:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x0609:
        r6 = 0;
        r9 = com.google.android.gms.internal.ads.zzbqk.zzad(r14, r6);
        r4 = r4 + r9;
        goto L_0x09cb;
    L_0x0611:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x0617:
        r6 = zzh(r1, r9);
        r6 = com.google.android.gms.internal.ads.zzbqk.zzae(r14, r6);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0622:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x0628:
        r6 = zzh(r1, r9);
        r6 = com.google.android.gms.internal.ads.zzbqk.zzaa(r14, r6);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0633:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x0639:
        r6 = r2.getObject(r1, r9);
        r6 = (com.google.android.gms.internal.ads.zzbpu) r6;
        r6 = com.google.android.gms.internal.ads.zzbqk.zzc(r14, r6);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0646:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x064c:
        r6 = r2.getObject(r1, r9);
        r9 = r0.zzfq(r3);
        r6 = com.google.android.gms.internal.ads.zzbte.zzc(r14, r6, r9);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x065b:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x0661:
        r6 = r2.getObject(r1, r9);
        r9 = r6 instanceof com.google.android.gms.internal.ads.zzbpu;
        if (r9 == 0) goto L_0x0672;
    L_0x0669:
        r6 = (com.google.android.gms.internal.ads.zzbpu) r6;
        r6 = com.google.android.gms.internal.ads.zzbqk.zzc(r14, r6);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0672:
        r6 = (java.lang.String) r6;
        r6 = com.google.android.gms.internal.ads.zzbqk.zzg(r14, r6);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x067b:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x0681:
        r6 = com.google.android.gms.internal.ads.zzbqk.zzk(r14, r7);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0688:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x068e:
        r6 = 0;
        r9 = com.google.android.gms.internal.ads.zzbqk.zzac(r14, r6);
        r4 = r4 + r9;
        goto L_0x09cb;
    L_0x0696:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x069c:
        r9 = 0;
        r6 = com.google.android.gms.internal.ads.zzbqk.zzp(r14, r9);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x06a5:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x06ab:
        r6 = zzh(r1, r9);
        r6 = com.google.android.gms.internal.ads.zzbqk.zzz(r14, r6);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x06b6:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x06bc:
        r9 = zzi(r1, r9);
        r6 = com.google.android.gms.internal.ads.zzbqk.zzn(r14, r9);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x06c7:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x06cd:
        r9 = zzi(r1, r9);
        r6 = com.google.android.gms.internal.ads.zzbqk.zzm(r14, r9);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x06d8:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x06de:
        r6 = 0;
        r9 = com.google.android.gms.internal.ads.zzbqk.zzb(r14, r6);
        r4 = r4 + r9;
        goto L_0x09cb;
    L_0x06e6:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x06ec:
        r9 = 0;
        r6 = com.google.android.gms.internal.ads.zzbqk.zzc(r14, r9);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x06f5:
        r6 = r0.zzfsy;
        r9 = r2.getObject(r1, r9);
        r10 = r0.zzfr(r3);
        r6 = r6.zzb(r14, r9, r10);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0706:
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r9 = r0.zzfq(r3);
        r6 = com.google.android.gms.internal.ads.zzbte.zzd(r14, r6, r9);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0717:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.ads.zzbte.zzah(r9);
        if (r9 <= 0) goto L_0x09cb;
    L_0x0723:
        r10 = r0.zzfsq;
        if (r10 == 0) goto L_0x072b;
    L_0x0727:
        r10 = (long) r6;
        r2.putInt(r1, r10, r9);
    L_0x072b:
        r6 = com.google.android.gms.internal.ads.zzbqk.zzfd(r14);
        r10 = com.google.android.gms.internal.ads.zzbqk.zzff(r9);
        r6 = r6 + r10;
        r6 = r6 + r9;
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0738:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.ads.zzbte.zzal(r9);
        if (r9 <= 0) goto L_0x09cb;
    L_0x0744:
        r10 = r0.zzfsq;
        if (r10 == 0) goto L_0x074c;
    L_0x0748:
        r10 = (long) r6;
        r2.putInt(r1, r10, r9);
    L_0x074c:
        r6 = com.google.android.gms.internal.ads.zzbqk.zzfd(r14);
        r10 = com.google.android.gms.internal.ads.zzbqk.zzff(r9);
        r6 = r6 + r10;
        r6 = r6 + r9;
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0759:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.ads.zzbte.zzan(r9);
        if (r9 <= 0) goto L_0x09cb;
    L_0x0765:
        r10 = r0.zzfsq;
        if (r10 == 0) goto L_0x076d;
    L_0x0769:
        r10 = (long) r6;
        r2.putInt(r1, r10, r9);
    L_0x076d:
        r6 = com.google.android.gms.internal.ads.zzbqk.zzfd(r14);
        r10 = com.google.android.gms.internal.ads.zzbqk.zzff(r9);
        r6 = r6 + r10;
        r6 = r6 + r9;
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x077a:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.ads.zzbte.zzam(r9);
        if (r9 <= 0) goto L_0x09cb;
    L_0x0786:
        r10 = r0.zzfsq;
        if (r10 == 0) goto L_0x078e;
    L_0x078a:
        r10 = (long) r6;
        r2.putInt(r1, r10, r9);
    L_0x078e:
        r6 = com.google.android.gms.internal.ads.zzbqk.zzfd(r14);
        r10 = com.google.android.gms.internal.ads.zzbqk.zzff(r9);
        r6 = r6 + r10;
        r6 = r6 + r9;
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x079b:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.ads.zzbte.zzai(r9);
        if (r9 <= 0) goto L_0x09cb;
    L_0x07a7:
        r10 = r0.zzfsq;
        if (r10 == 0) goto L_0x07af;
    L_0x07ab:
        r10 = (long) r6;
        r2.putInt(r1, r10, r9);
    L_0x07af:
        r6 = com.google.android.gms.internal.ads.zzbqk.zzfd(r14);
        r10 = com.google.android.gms.internal.ads.zzbqk.zzff(r9);
        r6 = r6 + r10;
        r6 = r6 + r9;
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x07bc:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.ads.zzbte.zzak(r9);
        if (r9 <= 0) goto L_0x09cb;
    L_0x07c8:
        r10 = r0.zzfsq;
        if (r10 == 0) goto L_0x07d0;
    L_0x07cc:
        r10 = (long) r6;
        r2.putInt(r1, r10, r9);
    L_0x07d0:
        r6 = com.google.android.gms.internal.ads.zzbqk.zzfd(r14);
        r10 = com.google.android.gms.internal.ads.zzbqk.zzff(r9);
        r6 = r6 + r10;
        r6 = r6 + r9;
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x07dd:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.ads.zzbte.zzao(r9);
        if (r9 <= 0) goto L_0x09cb;
    L_0x07e9:
        r10 = r0.zzfsq;
        if (r10 == 0) goto L_0x07f1;
    L_0x07ed:
        r10 = (long) r6;
        r2.putInt(r1, r10, r9);
    L_0x07f1:
        r6 = com.google.android.gms.internal.ads.zzbqk.zzfd(r14);
        r10 = com.google.android.gms.internal.ads.zzbqk.zzff(r9);
        r6 = r6 + r10;
        r6 = r6 + r9;
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x07fe:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.ads.zzbte.zzam(r9);
        if (r9 <= 0) goto L_0x09cb;
    L_0x080a:
        r10 = r0.zzfsq;
        if (r10 == 0) goto L_0x0812;
    L_0x080e:
        r10 = (long) r6;
        r2.putInt(r1, r10, r9);
    L_0x0812:
        r6 = com.google.android.gms.internal.ads.zzbqk.zzfd(r14);
        r10 = com.google.android.gms.internal.ads.zzbqk.zzff(r9);
        r6 = r6 + r10;
        r6 = r6 + r9;
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x081f:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.ads.zzbte.zzan(r9);
        if (r9 <= 0) goto L_0x09cb;
    L_0x082b:
        r10 = r0.zzfsq;
        if (r10 == 0) goto L_0x0833;
    L_0x082f:
        r10 = (long) r6;
        r2.putInt(r1, r10, r9);
    L_0x0833:
        r6 = com.google.android.gms.internal.ads.zzbqk.zzfd(r14);
        r10 = com.google.android.gms.internal.ads.zzbqk.zzff(r9);
        r6 = r6 + r10;
        r6 = r6 + r9;
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0840:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.ads.zzbte.zzaj(r9);
        if (r9 <= 0) goto L_0x09cb;
    L_0x084c:
        r10 = r0.zzfsq;
        if (r10 == 0) goto L_0x0854;
    L_0x0850:
        r10 = (long) r6;
        r2.putInt(r1, r10, r9);
    L_0x0854:
        r6 = com.google.android.gms.internal.ads.zzbqk.zzfd(r14);
        r10 = com.google.android.gms.internal.ads.zzbqk.zzff(r9);
        r6 = r6 + r10;
        r6 = r6 + r9;
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0861:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.ads.zzbte.zzag(r9);
        if (r9 <= 0) goto L_0x09cb;
    L_0x086d:
        r10 = r0.zzfsq;
        if (r10 == 0) goto L_0x0875;
    L_0x0871:
        r10 = (long) r6;
        r2.putInt(r1, r10, r9);
    L_0x0875:
        r6 = com.google.android.gms.internal.ads.zzbqk.zzfd(r14);
        r10 = com.google.android.gms.internal.ads.zzbqk.zzff(r9);
        r6 = r6 + r10;
        r6 = r6 + r9;
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0882:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.ads.zzbte.zzaf(r9);
        if (r9 <= 0) goto L_0x09cb;
    L_0x088e:
        r10 = r0.zzfsq;
        if (r10 == 0) goto L_0x0896;
    L_0x0892:
        r10 = (long) r6;
        r2.putInt(r1, r10, r9);
    L_0x0896:
        r6 = com.google.android.gms.internal.ads.zzbqk.zzfd(r14);
        r10 = com.google.android.gms.internal.ads.zzbqk.zzff(r9);
        r6 = r6 + r10;
        r6 = r6 + r9;
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x08a3:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.ads.zzbte.zzam(r9);
        if (r9 <= 0) goto L_0x09cb;
    L_0x08af:
        r10 = r0.zzfsq;
        if (r10 == 0) goto L_0x08b7;
    L_0x08b3:
        r10 = (long) r6;
        r2.putInt(r1, r10, r9);
    L_0x08b7:
        r6 = com.google.android.gms.internal.ads.zzbqk.zzfd(r14);
        r10 = com.google.android.gms.internal.ads.zzbqk.zzff(r9);
        r6 = r6 + r10;
        r6 = r6 + r9;
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x08c4:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.ads.zzbte.zzan(r9);
        if (r9 <= 0) goto L_0x09cb;
    L_0x08d0:
        r10 = r0.zzfsq;
        if (r10 == 0) goto L_0x08d8;
    L_0x08d4:
        r10 = (long) r6;
        r2.putInt(r1, r10, r9);
    L_0x08d8:
        r6 = com.google.android.gms.internal.ads.zzbqk.zzfd(r14);
        r10 = com.google.android.gms.internal.ads.zzbqk.zzff(r9);
        r6 = r6 + r10;
        r6 = r6 + r9;
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x08e5:
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r11 = 0;
        r6 = com.google.android.gms.internal.ads.zzbte.zzq(r14, r6, r11);
        r4 = r4 + r6;
        goto L_0x09bd;
    L_0x08f3:
        r11 = 0;
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.ads.zzbte.zzu(r14, r6, r11);
        r4 = r4 + r6;
        goto L_0x09bd;
    L_0x0901:
        r11 = 0;
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.ads.zzbte.zzw(r14, r6, r11);
        r4 = r4 + r6;
        goto L_0x09bd;
    L_0x090f:
        r11 = 0;
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.ads.zzbte.zzv(r14, r6, r11);
        r4 = r4 + r6;
        goto L_0x09bd;
    L_0x091d:
        r11 = 0;
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.ads.zzbte.zzr(r14, r6, r11);
        r4 = r4 + r6;
        goto L_0x09bd;
    L_0x092b:
        r11 = 0;
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.ads.zzbte.zzt(r14, r6, r11);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0939:
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.ads.zzbte.zzd(r14, r6);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0946:
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r9 = r0.zzfq(r3);
        r6 = com.google.android.gms.internal.ads.zzbte.zzc(r14, r6, r9);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0957:
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.ads.zzbte.zzc(r14, r6);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0963:
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r11 = 0;
        r6 = com.google.android.gms.internal.ads.zzbte.zzx(r14, r6, r11);
        r4 = r4 + r6;
        goto L_0x09bd;
    L_0x0970:
        r11 = 0;
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.ads.zzbte.zzv(r14, r6, r11);
        r4 = r4 + r6;
        goto L_0x09bd;
    L_0x097d:
        r11 = 0;
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.ads.zzbte.zzw(r14, r6, r11);
        r4 = r4 + r6;
        goto L_0x09bd;
    L_0x098a:
        r11 = 0;
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.ads.zzbte.zzs(r14, r6, r11);
        r4 = r4 + r6;
        goto L_0x09bd;
    L_0x0997:
        r11 = 0;
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.ads.zzbte.zzp(r14, r6, r11);
        r4 = r4 + r6;
        goto L_0x09bd;
    L_0x09a4:
        r11 = 0;
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.ads.zzbte.zzo(r14, r6, r11);
        r4 = r4 + r6;
        goto L_0x09bd;
    L_0x09b1:
        r11 = 0;
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.ads.zzbte.zzv(r14, r6, r11);
        r4 = r4 + r6;
    L_0x09bd:
        r6 = r11;
        goto L_0x09cc;
    L_0x09bf:
        r11 = 0;
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.ads.zzbte.zzw(r14, r6, r11);
        r4 = r4 + r6;
    L_0x09cb:
        r6 = 0;
    L_0x09cc:
        r9 = 0;
        r10 = 0;
        r18 = 0;
        goto L_0x0af0;
    L_0x09d3:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x09cb;
    L_0x09d7:
        r6 = r2.getObject(r1, r9);
        r6 = (com.google.android.gms.internal.ads.zzbsl) r6;
        r9 = r0.zzfq(r3);
        r6 = com.google.android.gms.internal.ads.zzbqk.zzc(r14, r6, r9);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x09e7:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x09cb;
    L_0x09eb:
        r9 = r2.getLong(r1, r9);
        r6 = com.google.android.gms.internal.ads.zzbqk.zzo(r14, r9);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x09f5:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x09cb;
    L_0x09f9:
        r6 = r2.getInt(r1, r9);
        r6 = com.google.android.gms.internal.ads.zzbqk.zzab(r14, r6);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0a03:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x09cb;
    L_0x0a07:
        r9 = 0;
        r6 = com.google.android.gms.internal.ads.zzbqk.zzq(r14, r9);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0a0f:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x09cb;
    L_0x0a13:
        r6 = 0;
        r9 = com.google.android.gms.internal.ads.zzbqk.zzad(r14, r6);
        r4 = r4 + r9;
        goto L_0x09cb;
    L_0x0a1a:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x09cb;
    L_0x0a1e:
        r6 = r2.getInt(r1, r9);
        r6 = com.google.android.gms.internal.ads.zzbqk.zzae(r14, r6);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0a28:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x09cb;
    L_0x0a2c:
        r6 = r2.getInt(r1, r9);
        r6 = com.google.android.gms.internal.ads.zzbqk.zzaa(r14, r6);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0a36:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x09cb;
    L_0x0a3a:
        r6 = r2.getObject(r1, r9);
        r6 = (com.google.android.gms.internal.ads.zzbpu) r6;
        r6 = com.google.android.gms.internal.ads.zzbqk.zzc(r14, r6);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0a46:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x09cb;
    L_0x0a4a:
        r6 = r2.getObject(r1, r9);
        r9 = r0.zzfq(r3);
        r6 = com.google.android.gms.internal.ads.zzbte.zzc(r14, r6, r9);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0a59:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x09cb;
    L_0x0a5d:
        r6 = r2.getObject(r1, r9);
        r9 = r6 instanceof com.google.android.gms.internal.ads.zzbpu;
        if (r9 == 0) goto L_0x0a6e;
    L_0x0a65:
        r6 = (com.google.android.gms.internal.ads.zzbpu) r6;
        r6 = com.google.android.gms.internal.ads.zzbqk.zzc(r14, r6);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0a6e:
        r6 = (java.lang.String) r6;
        r6 = com.google.android.gms.internal.ads.zzbqk.zzg(r14, r6);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0a77:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x09cb;
    L_0x0a7b:
        r6 = com.google.android.gms.internal.ads.zzbqk.zzk(r14, r7);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0a82:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x09cb;
    L_0x0a86:
        r6 = 0;
        r9 = com.google.android.gms.internal.ads.zzbqk.zzac(r14, r6);
        r4 = r4 + r9;
        goto L_0x09cc;
    L_0x0a8e:
        r6 = 0;
        r9 = r12 & r16;
        if (r9 == 0) goto L_0x09cc;
    L_0x0a93:
        r9 = 0;
        r11 = com.google.android.gms.internal.ads.zzbqk.zzp(r14, r9);
        r4 = r4 + r11;
        r18 = r9;
        goto L_0x0acf;
    L_0x0a9d:
        r6 = 0;
        r18 = 0;
        r11 = r12 & r16;
        if (r11 == 0) goto L_0x0acf;
    L_0x0aa4:
        r9 = r2.getInt(r1, r9);
        r9 = com.google.android.gms.internal.ads.zzbqk.zzz(r14, r9);
        r4 = r4 + r9;
        goto L_0x0acf;
    L_0x0aae:
        r6 = 0;
        r18 = 0;
        r11 = r12 & r16;
        if (r11 == 0) goto L_0x0acf;
    L_0x0ab5:
        r9 = r2.getLong(r1, r9);
        r9 = com.google.android.gms.internal.ads.zzbqk.zzn(r14, r9);
        r4 = r4 + r9;
        goto L_0x0acf;
    L_0x0abf:
        r6 = 0;
        r18 = 0;
        r11 = r12 & r16;
        if (r11 == 0) goto L_0x0acf;
    L_0x0ac6:
        r9 = r2.getLong(r1, r9);
        r9 = com.google.android.gms.internal.ads.zzbqk.zzm(r14, r9);
        r4 = r4 + r9;
    L_0x0acf:
        r9 = 0;
        goto L_0x0ade;
    L_0x0ad1:
        r6 = 0;
        r18 = 0;
        r9 = r12 & r16;
        if (r9 == 0) goto L_0x0acf;
    L_0x0ad8:
        r9 = 0;
        r10 = com.google.android.gms.internal.ads.zzbqk.zzb(r14, r9);
        r4 = r4 + r10;
    L_0x0ade:
        r10 = 0;
        goto L_0x0af0;
    L_0x0ae1:
        r6 = 0;
        r9 = 0;
        r18 = 0;
        r10 = r12 & r16;
        if (r10 == 0) goto L_0x0ade;
    L_0x0ae9:
        r10 = 0;
        r13 = com.google.android.gms.internal.ads.zzbqk.zzc(r14, r10);
        r4 = r4 + r13;
    L_0x0af0:
        r3 = r3 + 3;
        r11 = r6;
        r6 = r9;
        r9 = r18;
        goto L_0x0566;
    L_0x0af8:
        r2 = r0.zzfsw;
        r2 = zza(r2, r1);
        r4 = r4 + r2;
        r2 = r0.zzfsn;
        if (r2 == 0) goto L_0x0b0e;
    L_0x0b03:
        r2 = r0.zzfsx;
        r1 = r2.zzq(r1);
        r1 = r1.zzamj();
        r4 = r4 + r1;
    L_0x0b0e:
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbsp.zzac(java.lang.Object):int");
    }

    private static <UT, UB> int zza(zzbtu<UT, UB> zzbtu, T t) {
        return zzbtu.zzac(zzbtu.zzag(t));
    }

    private static <E> List<E> zze(Object obj, long j) {
        return (List) zzbua.zzp(obj, j);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x0511  */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x054f  */
    /* JADX WARNING: Removed duplicated region for block: B:331:0x0a27  */
    public final void zza(T r14, com.google.android.gms.internal.ads.zzbup r15) throws java.io.IOException {
        /*
        r13 = this;
        r0 = r15.zzaly();
        r1 = com.google.android.gms.internal.ads.zzbrd.zze.zzfqn;
        r2 = 267386880; // 0xff00000 float:2.3665827E-29 double:1.321066716E-315;
        r3 = 0;
        r4 = 1;
        r5 = 0;
        r6 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        if (r0 != r1) goto L_0x0527;
    L_0x0010:
        r0 = r13.zzfsw;
        zza(r0, r14, r15);
        r0 = r13.zzfsn;
        if (r0 == 0) goto L_0x0030;
    L_0x0019:
        r0 = r13.zzfsx;
        r0 = r0.zzq(r14);
        r1 = r0.isEmpty();
        if (r1 != 0) goto L_0x0030;
    L_0x0025:
        r0 = r0.descendingIterator();
        r1 = r0.next();
        r1 = (java.util.Map.Entry) r1;
        goto L_0x0032;
    L_0x0030:
        r0 = r3;
        r1 = r0;
    L_0x0032:
        r7 = r13.zzfsi;
        r7 = r7.length;
        r7 = r7 + -3;
    L_0x0037:
        if (r7 < 0) goto L_0x050f;
    L_0x0039:
        r8 = r13.zzft(r7);
        r9 = r13.zzfsi;
        r9 = r9[r7];
    L_0x0041:
        if (r1 == 0) goto L_0x005f;
    L_0x0043:
        r10 = r13.zzfsx;
        r10 = r10.zza(r1);
        if (r10 <= r9) goto L_0x005f;
    L_0x004b:
        r10 = r13.zzfsx;
        r10.zza(r15, r1);
        r1 = r0.hasNext();
        if (r1 == 0) goto L_0x005d;
    L_0x0056:
        r1 = r0.next();
        r1 = (java.util.Map.Entry) r1;
        goto L_0x0041;
    L_0x005d:
        r1 = r3;
        goto L_0x0041;
    L_0x005f:
        r10 = r8 & r2;
        r10 = r10 >>> 20;
        switch(r10) {
            case 0: goto L_0x04fc;
            case 1: goto L_0x04ec;
            case 2: goto L_0x04dc;
            case 3: goto L_0x04cc;
            case 4: goto L_0x04bc;
            case 5: goto L_0x04ac;
            case 6: goto L_0x049c;
            case 7: goto L_0x048b;
            case 8: goto L_0x047a;
            case 9: goto L_0x0465;
            case 10: goto L_0x0452;
            case 11: goto L_0x0441;
            case 12: goto L_0x0430;
            case 13: goto L_0x041f;
            case 14: goto L_0x040e;
            case 15: goto L_0x03fd;
            case 16: goto L_0x03ec;
            case 17: goto L_0x03d7;
            case 18: goto L_0x03c6;
            case 19: goto L_0x03b5;
            case 20: goto L_0x03a4;
            case 21: goto L_0x0393;
            case 22: goto L_0x0382;
            case 23: goto L_0x0371;
            case 24: goto L_0x0360;
            case 25: goto L_0x034f;
            case 26: goto L_0x033e;
            case 27: goto L_0x0329;
            case 28: goto L_0x0318;
            case 29: goto L_0x0307;
            case 30: goto L_0x02f6;
            case 31: goto L_0x02e5;
            case 32: goto L_0x02d4;
            case 33: goto L_0x02c3;
            case 34: goto L_0x02b2;
            case 35: goto L_0x02a1;
            case 36: goto L_0x0290;
            case 37: goto L_0x027f;
            case 38: goto L_0x026e;
            case 39: goto L_0x025d;
            case 40: goto L_0x024c;
            case 41: goto L_0x023b;
            case 42: goto L_0x022a;
            case 43: goto L_0x0219;
            case 44: goto L_0x0208;
            case 45: goto L_0x01f7;
            case 46: goto L_0x01e6;
            case 47: goto L_0x01d5;
            case 48: goto L_0x01c4;
            case 49: goto L_0x01af;
            case 50: goto L_0x01a4;
            case 51: goto L_0x0193;
            case 52: goto L_0x0182;
            case 53: goto L_0x0171;
            case 54: goto L_0x0160;
            case 55: goto L_0x014f;
            case 56: goto L_0x013e;
            case 57: goto L_0x012d;
            case 58: goto L_0x011c;
            case 59: goto L_0x010b;
            case 60: goto L_0x00f6;
            case 61: goto L_0x00e3;
            case 62: goto L_0x00d2;
            case 63: goto L_0x00c1;
            case 64: goto L_0x00b0;
            case 65: goto L_0x009f;
            case 66: goto L_0x008e;
            case 67: goto L_0x007d;
            case 68: goto L_0x0068;
            default: goto L_0x0066;
        };
    L_0x0066:
        goto L_0x050b;
    L_0x0068:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x006e:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r10 = r13.zzfq(r7);
        r15.zzb(r9, r8, r10);
        goto L_0x050b;
    L_0x007d:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0083:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = zzi(r14, r10);
        r15.zzk(r9, r10);
        goto L_0x050b;
    L_0x008e:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0094:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = zzh(r14, r10);
        r15.zzx(r9, r8);
        goto L_0x050b;
    L_0x009f:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x00a5:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = zzi(r14, r10);
        r15.zzs(r9, r10);
        goto L_0x050b;
    L_0x00b0:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x00b6:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = zzh(r14, r10);
        r15.zzaf(r9, r8);
        goto L_0x050b;
    L_0x00c1:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x00c7:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = zzh(r14, r10);
        r15.zzag(r9, r8);
        goto L_0x050b;
    L_0x00d2:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x00d8:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = zzh(r14, r10);
        r15.zzw(r9, r8);
        goto L_0x050b;
    L_0x00e3:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x00e9:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r8 = (com.google.android.gms.internal.ads.zzbpu) r8;
        r15.zza(r9, r8);
        goto L_0x050b;
    L_0x00f6:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x00fc:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r10 = r13.zzfq(r7);
        r15.zza(r9, r8, r10);
        goto L_0x050b;
    L_0x010b:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0111:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        zza(r9, r8, r15);
        goto L_0x050b;
    L_0x011c:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0122:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = zzj(r14, r10);
        r15.zzj(r9, r8);
        goto L_0x050b;
    L_0x012d:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0133:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = zzh(r14, r10);
        r15.zzy(r9, r8);
        goto L_0x050b;
    L_0x013e:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0144:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = zzi(r14, r10);
        r15.zzl(r9, r10);
        goto L_0x050b;
    L_0x014f:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0155:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = zzh(r14, r10);
        r15.zzv(r9, r8);
        goto L_0x050b;
    L_0x0160:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0166:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = zzi(r14, r10);
        r15.zzj(r9, r10);
        goto L_0x050b;
    L_0x0171:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0177:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = zzi(r14, r10);
        r15.zzr(r9, r10);
        goto L_0x050b;
    L_0x0182:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0188:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = zzg(r14, r10);
        r15.zza(r9, r8);
        goto L_0x050b;
    L_0x0193:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0199:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = zzf(r14, r10);
        r15.zzb(r9, r10);
        goto L_0x050b;
    L_0x01a4:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r13.zza(r15, r9, r8, r7);
        goto L_0x050b;
    L_0x01af:
        r9 = r13.zzfsi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r8 = (java.util.List) r8;
        r10 = r13.zzfq(r7);
        com.google.android.gms.internal.ads.zzbte.zzb(r9, r8, r15, r10);
        goto L_0x050b;
    L_0x01c4:
        r9 = r13.zzfsi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.ads.zzbte.zze(r9, r8, r15, r4);
        goto L_0x050b;
    L_0x01d5:
        r9 = r13.zzfsi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.ads.zzbte.zzj(r9, r8, r15, r4);
        goto L_0x050b;
    L_0x01e6:
        r9 = r13.zzfsi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.ads.zzbte.zzg(r9, r8, r15, r4);
        goto L_0x050b;
    L_0x01f7:
        r9 = r13.zzfsi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.ads.zzbte.zzl(r9, r8, r15, r4);
        goto L_0x050b;
    L_0x0208:
        r9 = r13.zzfsi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.ads.zzbte.zzm(r9, r8, r15, r4);
        goto L_0x050b;
    L_0x0219:
        r9 = r13.zzfsi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.ads.zzbte.zzi(r9, r8, r15, r4);
        goto L_0x050b;
    L_0x022a:
        r9 = r13.zzfsi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.ads.zzbte.zzn(r9, r8, r15, r4);
        goto L_0x050b;
    L_0x023b:
        r9 = r13.zzfsi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.ads.zzbte.zzk(r9, r8, r15, r4);
        goto L_0x050b;
    L_0x024c:
        r9 = r13.zzfsi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.ads.zzbte.zzf(r9, r8, r15, r4);
        goto L_0x050b;
    L_0x025d:
        r9 = r13.zzfsi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.ads.zzbte.zzh(r9, r8, r15, r4);
        goto L_0x050b;
    L_0x026e:
        r9 = r13.zzfsi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.ads.zzbte.zzd(r9, r8, r15, r4);
        goto L_0x050b;
    L_0x027f:
        r9 = r13.zzfsi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.ads.zzbte.zzc(r9, r8, r15, r4);
        goto L_0x050b;
    L_0x0290:
        r9 = r13.zzfsi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.ads.zzbte.zzb(r9, r8, r15, r4);
        goto L_0x050b;
    L_0x02a1:
        r9 = r13.zzfsi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.ads.zzbte.zza(r9, r8, r15, r4);
        goto L_0x050b;
    L_0x02b2:
        r9 = r13.zzfsi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.ads.zzbte.zze(r9, r8, r15, r5);
        goto L_0x050b;
    L_0x02c3:
        r9 = r13.zzfsi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.ads.zzbte.zzj(r9, r8, r15, r5);
        goto L_0x050b;
    L_0x02d4:
        r9 = r13.zzfsi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.ads.zzbte.zzg(r9, r8, r15, r5);
        goto L_0x050b;
    L_0x02e5:
        r9 = r13.zzfsi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.ads.zzbte.zzl(r9, r8, r15, r5);
        goto L_0x050b;
    L_0x02f6:
        r9 = r13.zzfsi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.ads.zzbte.zzm(r9, r8, r15, r5);
        goto L_0x050b;
    L_0x0307:
        r9 = r13.zzfsi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.ads.zzbte.zzi(r9, r8, r15, r5);
        goto L_0x050b;
    L_0x0318:
        r9 = r13.zzfsi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.ads.zzbte.zzb(r9, r8, r15);
        goto L_0x050b;
    L_0x0329:
        r9 = r13.zzfsi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r8 = (java.util.List) r8;
        r10 = r13.zzfq(r7);
        com.google.android.gms.internal.ads.zzbte.zza(r9, r8, r15, r10);
        goto L_0x050b;
    L_0x033e:
        r9 = r13.zzfsi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.ads.zzbte.zza(r9, r8, r15);
        goto L_0x050b;
    L_0x034f:
        r9 = r13.zzfsi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.ads.zzbte.zzn(r9, r8, r15, r5);
        goto L_0x050b;
    L_0x0360:
        r9 = r13.zzfsi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.ads.zzbte.zzk(r9, r8, r15, r5);
        goto L_0x050b;
    L_0x0371:
        r9 = r13.zzfsi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.ads.zzbte.zzf(r9, r8, r15, r5);
        goto L_0x050b;
    L_0x0382:
        r9 = r13.zzfsi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.ads.zzbte.zzh(r9, r8, r15, r5);
        goto L_0x050b;
    L_0x0393:
        r9 = r13.zzfsi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.ads.zzbte.zzd(r9, r8, r15, r5);
        goto L_0x050b;
    L_0x03a4:
        r9 = r13.zzfsi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.ads.zzbte.zzc(r9, r8, r15, r5);
        goto L_0x050b;
    L_0x03b5:
        r9 = r13.zzfsi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.ads.zzbte.zzb(r9, r8, r15, r5);
        goto L_0x050b;
    L_0x03c6:
        r9 = r13.zzfsi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.ads.zzbte.zza(r9, r8, r15, r5);
        goto L_0x050b;
    L_0x03d7:
        r10 = r13.zzd(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x03dd:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r10 = r13.zzfq(r7);
        r15.zzb(r9, r8, r10);
        goto L_0x050b;
    L_0x03ec:
        r10 = r13.zzd(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x03f2:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = com.google.android.gms.internal.ads.zzbua.zzl(r14, r10);
        r15.zzk(r9, r10);
        goto L_0x050b;
    L_0x03fd:
        r10 = r13.zzd(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0403:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzk(r14, r10);
        r15.zzx(r9, r8);
        goto L_0x050b;
    L_0x040e:
        r10 = r13.zzd(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0414:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = com.google.android.gms.internal.ads.zzbua.zzl(r14, r10);
        r15.zzs(r9, r10);
        goto L_0x050b;
    L_0x041f:
        r10 = r13.zzd(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0425:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzk(r14, r10);
        r15.zzaf(r9, r8);
        goto L_0x050b;
    L_0x0430:
        r10 = r13.zzd(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0436:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzk(r14, r10);
        r15.zzag(r9, r8);
        goto L_0x050b;
    L_0x0441:
        r10 = r13.zzd(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0447:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzk(r14, r10);
        r15.zzw(r9, r8);
        goto L_0x050b;
    L_0x0452:
        r10 = r13.zzd(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0458:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r8 = (com.google.android.gms.internal.ads.zzbpu) r8;
        r15.zza(r9, r8);
        goto L_0x050b;
    L_0x0465:
        r10 = r13.zzd(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x046b:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        r10 = r13.zzfq(r7);
        r15.zza(r9, r8, r10);
        goto L_0x050b;
    L_0x047a:
        r10 = r13.zzd(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0480:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r10);
        zza(r9, r8, r15);
        goto L_0x050b;
    L_0x048b:
        r10 = r13.zzd(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0491:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzm(r14, r10);
        r15.zzj(r9, r8);
        goto L_0x050b;
    L_0x049c:
        r10 = r13.zzd(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x04a2:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzk(r14, r10);
        r15.zzy(r9, r8);
        goto L_0x050b;
    L_0x04ac:
        r10 = r13.zzd(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x04b2:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = com.google.android.gms.internal.ads.zzbua.zzl(r14, r10);
        r15.zzl(r9, r10);
        goto L_0x050b;
    L_0x04bc:
        r10 = r13.zzd(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x04c2:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzk(r14, r10);
        r15.zzv(r9, r8);
        goto L_0x050b;
    L_0x04cc:
        r10 = r13.zzd(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x04d2:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = com.google.android.gms.internal.ads.zzbua.zzl(r14, r10);
        r15.zzj(r9, r10);
        goto L_0x050b;
    L_0x04dc:
        r10 = r13.zzd(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x04e2:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = com.google.android.gms.internal.ads.zzbua.zzl(r14, r10);
        r15.zzr(r9, r10);
        goto L_0x050b;
    L_0x04ec:
        r10 = r13.zzd(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x04f2:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.ads.zzbua.zzn(r14, r10);
        r15.zza(r9, r8);
        goto L_0x050b;
    L_0x04fc:
        r10 = r13.zzd(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0502:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = com.google.android.gms.internal.ads.zzbua.zzo(r14, r10);
        r15.zzb(r9, r10);
    L_0x050b:
        r7 = r7 + -3;
        goto L_0x0037;
    L_0x050f:
        if (r1 == 0) goto L_0x0526;
    L_0x0511:
        r14 = r13.zzfsx;
        r14.zza(r15, r1);
        r14 = r0.hasNext();
        if (r14 == 0) goto L_0x0524;
    L_0x051c:
        r14 = r0.next();
        r14 = (java.util.Map.Entry) r14;
        r1 = r14;
        goto L_0x050f;
    L_0x0524:
        r1 = r3;
        goto L_0x050f;
    L_0x0526:
        return;
    L_0x0527:
        r0 = r13.zzfsp;
        if (r0 == 0) goto L_0x0a42;
    L_0x052b:
        r0 = r13.zzfsn;
        if (r0 == 0) goto L_0x0546;
    L_0x052f:
        r0 = r13.zzfsx;
        r0 = r0.zzq(r14);
        r1 = r0.isEmpty();
        if (r1 != 0) goto L_0x0546;
    L_0x053b:
        r0 = r0.iterator();
        r1 = r0.next();
        r1 = (java.util.Map.Entry) r1;
        goto L_0x0548;
    L_0x0546:
        r0 = r3;
        r1 = r0;
    L_0x0548:
        r7 = r13.zzfsi;
        r7 = r7.length;
        r8 = r1;
        r1 = r5;
    L_0x054d:
        if (r1 >= r7) goto L_0x0a25;
    L_0x054f:
        r9 = r13.zzft(r1);
        r10 = r13.zzfsi;
        r10 = r10[r1];
    L_0x0557:
        if (r8 == 0) goto L_0x0575;
    L_0x0559:
        r11 = r13.zzfsx;
        r11 = r11.zza(r8);
        if (r11 > r10) goto L_0x0575;
    L_0x0561:
        r11 = r13.zzfsx;
        r11.zza(r15, r8);
        r8 = r0.hasNext();
        if (r8 == 0) goto L_0x0573;
    L_0x056c:
        r8 = r0.next();
        r8 = (java.util.Map.Entry) r8;
        goto L_0x0557;
    L_0x0573:
        r8 = r3;
        goto L_0x0557;
    L_0x0575:
        r11 = r9 & r2;
        r11 = r11 >>> 20;
        switch(r11) {
            case 0: goto L_0x0a12;
            case 1: goto L_0x0a02;
            case 2: goto L_0x09f2;
            case 3: goto L_0x09e2;
            case 4: goto L_0x09d2;
            case 5: goto L_0x09c2;
            case 6: goto L_0x09b2;
            case 7: goto L_0x09a1;
            case 8: goto L_0x0990;
            case 9: goto L_0x097b;
            case 10: goto L_0x0968;
            case 11: goto L_0x0957;
            case 12: goto L_0x0946;
            case 13: goto L_0x0935;
            case 14: goto L_0x0924;
            case 15: goto L_0x0913;
            case 16: goto L_0x0902;
            case 17: goto L_0x08ed;
            case 18: goto L_0x08dc;
            case 19: goto L_0x08cb;
            case 20: goto L_0x08ba;
            case 21: goto L_0x08a9;
            case 22: goto L_0x0898;
            case 23: goto L_0x0887;
            case 24: goto L_0x0876;
            case 25: goto L_0x0865;
            case 26: goto L_0x0854;
            case 27: goto L_0x083f;
            case 28: goto L_0x082e;
            case 29: goto L_0x081d;
            case 30: goto L_0x080c;
            case 31: goto L_0x07fb;
            case 32: goto L_0x07ea;
            case 33: goto L_0x07d9;
            case 34: goto L_0x07c8;
            case 35: goto L_0x07b7;
            case 36: goto L_0x07a6;
            case 37: goto L_0x0795;
            case 38: goto L_0x0784;
            case 39: goto L_0x0773;
            case 40: goto L_0x0762;
            case 41: goto L_0x0751;
            case 42: goto L_0x0740;
            case 43: goto L_0x072f;
            case 44: goto L_0x071e;
            case 45: goto L_0x070d;
            case 46: goto L_0x06fc;
            case 47: goto L_0x06eb;
            case 48: goto L_0x06da;
            case 49: goto L_0x06c5;
            case 50: goto L_0x06ba;
            case 51: goto L_0x06a9;
            case 52: goto L_0x0698;
            case 53: goto L_0x0687;
            case 54: goto L_0x0676;
            case 55: goto L_0x0665;
            case 56: goto L_0x0654;
            case 57: goto L_0x0643;
            case 58: goto L_0x0632;
            case 59: goto L_0x0621;
            case 60: goto L_0x060c;
            case 61: goto L_0x05f9;
            case 62: goto L_0x05e8;
            case 63: goto L_0x05d7;
            case 64: goto L_0x05c6;
            case 65: goto L_0x05b5;
            case 66: goto L_0x05a4;
            case 67: goto L_0x0593;
            case 68: goto L_0x057e;
            default: goto L_0x057c;
        };
    L_0x057c:
        goto L_0x0a21;
    L_0x057e:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x0584:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r11 = r13.zzfq(r1);
        r15.zzb(r10, r9, r11);
        goto L_0x0a21;
    L_0x0593:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x0599:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = zzi(r14, r11);
        r15.zzk(r10, r11);
        goto L_0x0a21;
    L_0x05a4:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x05aa:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = zzh(r14, r11);
        r15.zzx(r10, r9);
        goto L_0x0a21;
    L_0x05b5:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x05bb:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = zzi(r14, r11);
        r15.zzs(r10, r11);
        goto L_0x0a21;
    L_0x05c6:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x05cc:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = zzh(r14, r11);
        r15.zzaf(r10, r9);
        goto L_0x0a21;
    L_0x05d7:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x05dd:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = zzh(r14, r11);
        r15.zzag(r10, r9);
        goto L_0x0a21;
    L_0x05e8:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x05ee:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = zzh(r14, r11);
        r15.zzw(r10, r9);
        goto L_0x0a21;
    L_0x05f9:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x05ff:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r9 = (com.google.android.gms.internal.ads.zzbpu) r9;
        r15.zza(r10, r9);
        goto L_0x0a21;
    L_0x060c:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x0612:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r11 = r13.zzfq(r1);
        r15.zza(r10, r9, r11);
        goto L_0x0a21;
    L_0x0621:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x0627:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        zza(r10, r9, r15);
        goto L_0x0a21;
    L_0x0632:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x0638:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = zzj(r14, r11);
        r15.zzj(r10, r9);
        goto L_0x0a21;
    L_0x0643:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x0649:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = zzh(r14, r11);
        r15.zzy(r10, r9);
        goto L_0x0a21;
    L_0x0654:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x065a:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = zzi(r14, r11);
        r15.zzl(r10, r11);
        goto L_0x0a21;
    L_0x0665:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x066b:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = zzh(r14, r11);
        r15.zzv(r10, r9);
        goto L_0x0a21;
    L_0x0676:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x067c:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = zzi(r14, r11);
        r15.zzj(r10, r11);
        goto L_0x0a21;
    L_0x0687:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x068d:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = zzi(r14, r11);
        r15.zzr(r10, r11);
        goto L_0x0a21;
    L_0x0698:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x069e:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = zzg(r14, r11);
        r15.zza(r10, r9);
        goto L_0x0a21;
    L_0x06a9:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x06af:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = zzf(r14, r11);
        r15.zzb(r10, r11);
        goto L_0x0a21;
    L_0x06ba:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r13.zza(r15, r10, r9, r1);
        goto L_0x0a21;
    L_0x06c5:
        r10 = r13.zzfsi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r9 = (java.util.List) r9;
        r11 = r13.zzfq(r1);
        com.google.android.gms.internal.ads.zzbte.zzb(r10, r9, r15, r11);
        goto L_0x0a21;
    L_0x06da:
        r10 = r13.zzfsi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.ads.zzbte.zze(r10, r9, r15, r4);
        goto L_0x0a21;
    L_0x06eb:
        r10 = r13.zzfsi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.ads.zzbte.zzj(r10, r9, r15, r4);
        goto L_0x0a21;
    L_0x06fc:
        r10 = r13.zzfsi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.ads.zzbte.zzg(r10, r9, r15, r4);
        goto L_0x0a21;
    L_0x070d:
        r10 = r13.zzfsi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.ads.zzbte.zzl(r10, r9, r15, r4);
        goto L_0x0a21;
    L_0x071e:
        r10 = r13.zzfsi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.ads.zzbte.zzm(r10, r9, r15, r4);
        goto L_0x0a21;
    L_0x072f:
        r10 = r13.zzfsi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.ads.zzbte.zzi(r10, r9, r15, r4);
        goto L_0x0a21;
    L_0x0740:
        r10 = r13.zzfsi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.ads.zzbte.zzn(r10, r9, r15, r4);
        goto L_0x0a21;
    L_0x0751:
        r10 = r13.zzfsi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.ads.zzbte.zzk(r10, r9, r15, r4);
        goto L_0x0a21;
    L_0x0762:
        r10 = r13.zzfsi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.ads.zzbte.zzf(r10, r9, r15, r4);
        goto L_0x0a21;
    L_0x0773:
        r10 = r13.zzfsi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.ads.zzbte.zzh(r10, r9, r15, r4);
        goto L_0x0a21;
    L_0x0784:
        r10 = r13.zzfsi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.ads.zzbte.zzd(r10, r9, r15, r4);
        goto L_0x0a21;
    L_0x0795:
        r10 = r13.zzfsi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.ads.zzbte.zzc(r10, r9, r15, r4);
        goto L_0x0a21;
    L_0x07a6:
        r10 = r13.zzfsi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.ads.zzbte.zzb(r10, r9, r15, r4);
        goto L_0x0a21;
    L_0x07b7:
        r10 = r13.zzfsi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.ads.zzbte.zza(r10, r9, r15, r4);
        goto L_0x0a21;
    L_0x07c8:
        r10 = r13.zzfsi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.ads.zzbte.zze(r10, r9, r15, r5);
        goto L_0x0a21;
    L_0x07d9:
        r10 = r13.zzfsi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.ads.zzbte.zzj(r10, r9, r15, r5);
        goto L_0x0a21;
    L_0x07ea:
        r10 = r13.zzfsi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.ads.zzbte.zzg(r10, r9, r15, r5);
        goto L_0x0a21;
    L_0x07fb:
        r10 = r13.zzfsi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.ads.zzbte.zzl(r10, r9, r15, r5);
        goto L_0x0a21;
    L_0x080c:
        r10 = r13.zzfsi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.ads.zzbte.zzm(r10, r9, r15, r5);
        goto L_0x0a21;
    L_0x081d:
        r10 = r13.zzfsi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.ads.zzbte.zzi(r10, r9, r15, r5);
        goto L_0x0a21;
    L_0x082e:
        r10 = r13.zzfsi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.ads.zzbte.zzb(r10, r9, r15);
        goto L_0x0a21;
    L_0x083f:
        r10 = r13.zzfsi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r9 = (java.util.List) r9;
        r11 = r13.zzfq(r1);
        com.google.android.gms.internal.ads.zzbte.zza(r10, r9, r15, r11);
        goto L_0x0a21;
    L_0x0854:
        r10 = r13.zzfsi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.ads.zzbte.zza(r10, r9, r15);
        goto L_0x0a21;
    L_0x0865:
        r10 = r13.zzfsi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.ads.zzbte.zzn(r10, r9, r15, r5);
        goto L_0x0a21;
    L_0x0876:
        r10 = r13.zzfsi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.ads.zzbte.zzk(r10, r9, r15, r5);
        goto L_0x0a21;
    L_0x0887:
        r10 = r13.zzfsi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.ads.zzbte.zzf(r10, r9, r15, r5);
        goto L_0x0a21;
    L_0x0898:
        r10 = r13.zzfsi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.ads.zzbte.zzh(r10, r9, r15, r5);
        goto L_0x0a21;
    L_0x08a9:
        r10 = r13.zzfsi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.ads.zzbte.zzd(r10, r9, r15, r5);
        goto L_0x0a21;
    L_0x08ba:
        r10 = r13.zzfsi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.ads.zzbte.zzc(r10, r9, r15, r5);
        goto L_0x0a21;
    L_0x08cb:
        r10 = r13.zzfsi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.ads.zzbte.zzb(r10, r9, r15, r5);
        goto L_0x0a21;
    L_0x08dc:
        r10 = r13.zzfsi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.ads.zzbte.zza(r10, r9, r15, r5);
        goto L_0x0a21;
    L_0x08ed:
        r11 = r13.zzd(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x08f3:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r11 = r13.zzfq(r1);
        r15.zzb(r10, r9, r11);
        goto L_0x0a21;
    L_0x0902:
        r11 = r13.zzd(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x0908:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = com.google.android.gms.internal.ads.zzbua.zzl(r14, r11);
        r15.zzk(r10, r11);
        goto L_0x0a21;
    L_0x0913:
        r11 = r13.zzd(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x0919:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzk(r14, r11);
        r15.zzx(r10, r9);
        goto L_0x0a21;
    L_0x0924:
        r11 = r13.zzd(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x092a:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = com.google.android.gms.internal.ads.zzbua.zzl(r14, r11);
        r15.zzs(r10, r11);
        goto L_0x0a21;
    L_0x0935:
        r11 = r13.zzd(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x093b:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzk(r14, r11);
        r15.zzaf(r10, r9);
        goto L_0x0a21;
    L_0x0946:
        r11 = r13.zzd(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x094c:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzk(r14, r11);
        r15.zzag(r10, r9);
        goto L_0x0a21;
    L_0x0957:
        r11 = r13.zzd(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x095d:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzk(r14, r11);
        r15.zzw(r10, r9);
        goto L_0x0a21;
    L_0x0968:
        r11 = r13.zzd(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x096e:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r9 = (com.google.android.gms.internal.ads.zzbpu) r9;
        r15.zza(r10, r9);
        goto L_0x0a21;
    L_0x097b:
        r11 = r13.zzd(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x0981:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        r11 = r13.zzfq(r1);
        r15.zza(r10, r9, r11);
        goto L_0x0a21;
    L_0x0990:
        r11 = r13.zzd(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x0996:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzp(r14, r11);
        zza(r10, r9, r15);
        goto L_0x0a21;
    L_0x09a1:
        r11 = r13.zzd(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x09a7:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzm(r14, r11);
        r15.zzj(r10, r9);
        goto L_0x0a21;
    L_0x09b2:
        r11 = r13.zzd(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x09b8:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzk(r14, r11);
        r15.zzy(r10, r9);
        goto L_0x0a21;
    L_0x09c2:
        r11 = r13.zzd(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x09c8:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = com.google.android.gms.internal.ads.zzbua.zzl(r14, r11);
        r15.zzl(r10, r11);
        goto L_0x0a21;
    L_0x09d2:
        r11 = r13.zzd(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x09d8:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzk(r14, r11);
        r15.zzv(r10, r9);
        goto L_0x0a21;
    L_0x09e2:
        r11 = r13.zzd(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x09e8:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = com.google.android.gms.internal.ads.zzbua.zzl(r14, r11);
        r15.zzj(r10, r11);
        goto L_0x0a21;
    L_0x09f2:
        r11 = r13.zzd(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x09f8:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = com.google.android.gms.internal.ads.zzbua.zzl(r14, r11);
        r15.zzr(r10, r11);
        goto L_0x0a21;
    L_0x0a02:
        r11 = r13.zzd(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x0a08:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.ads.zzbua.zzn(r14, r11);
        r15.zza(r10, r9);
        goto L_0x0a21;
    L_0x0a12:
        r11 = r13.zzd(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x0a18:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = com.google.android.gms.internal.ads.zzbua.zzo(r14, r11);
        r15.zzb(r10, r11);
    L_0x0a21:
        r1 = r1 + 3;
        goto L_0x054d;
    L_0x0a25:
        if (r8 == 0) goto L_0x0a3c;
    L_0x0a27:
        r1 = r13.zzfsx;
        r1.zza(r15, r8);
        r1 = r0.hasNext();
        if (r1 == 0) goto L_0x0a3a;
    L_0x0a32:
        r1 = r0.next();
        r1 = (java.util.Map.Entry) r1;
        r8 = r1;
        goto L_0x0a25;
    L_0x0a3a:
        r8 = r3;
        goto L_0x0a25;
    L_0x0a3c:
        r0 = r13.zzfsw;
        zza(r0, r14, r15);
        return;
    L_0x0a42:
        r13.zzb(r14, r15);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbsp.zza(java.lang.Object, com.google.android.gms.internal.ads.zzbup):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0523  */
    /* JADX WARNING: Missing block: B:105:0x0344, code skipped:
            r14 = r13;
     */
    /* JADX WARNING: Missing block: B:171:0x051d, code skipped:
            r5 = r12 + 3;
     */
    private final void zzb(T r21, com.google.android.gms.internal.ads.zzbup r22) throws java.io.IOException {
        /*
        r20 = this;
        r0 = r20;
        r1 = r21;
        r2 = r22;
        r3 = r0.zzfsn;
        if (r3 == 0) goto L_0x0021;
    L_0x000a:
        r3 = r0.zzfsx;
        r3 = r3.zzq(r1);
        r5 = r3.isEmpty();
        if (r5 != 0) goto L_0x0021;
    L_0x0016:
        r3 = r3.iterator();
        r5 = r3.next();
        r5 = (java.util.Map.Entry) r5;
        goto L_0x0023;
    L_0x0021:
        r3 = 0;
        r5 = 0;
    L_0x0023:
        r6 = -1;
        r7 = r0.zzfsi;
        r7 = r7.length;
        r9 = zzfsh;
        r10 = r5;
        r5 = 0;
        r11 = 0;
    L_0x002c:
        if (r5 >= r7) goto L_0x0521;
    L_0x002e:
        r12 = r0.zzft(r5);
        r13 = r0.zzfsi;
        r13 = r13[r5];
        r14 = 267386880; // 0xff00000 float:2.3665827E-29 double:1.321066716E-315;
        r14 = r14 & r12;
        r14 = r14 >>> 20;
        r15 = r0.zzfsp;
        r16 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        if (r15 != 0) goto L_0x0061;
    L_0x0042:
        r15 = 17;
        if (r14 > r15) goto L_0x0061;
    L_0x0046:
        r15 = r0.zzfsi;
        r17 = r5 + 2;
        r15 = r15[r17];
        r8 = r15 & r16;
        if (r8 == r6) goto L_0x0059;
    L_0x0050:
        r18 = r5;
        r4 = (long) r8;
        r11 = r9.getInt(r1, r4);
        r6 = r8;
        goto L_0x005b;
    L_0x0059:
        r18 = r5;
    L_0x005b:
        r4 = r15 >>> 20;
        r5 = 1;
        r8 = r5 << r4;
        goto L_0x0064;
    L_0x0061:
        r18 = r5;
        r8 = 0;
    L_0x0064:
        if (r10 == 0) goto L_0x0083;
    L_0x0066:
        r4 = r0.zzfsx;
        r4 = r4.zza(r10);
        if (r4 > r13) goto L_0x0083;
    L_0x006e:
        r4 = r0.zzfsx;
        r4.zza(r2, r10);
        r4 = r3.hasNext();
        if (r4 == 0) goto L_0x0081;
    L_0x0079:
        r4 = r3.next();
        r4 = (java.util.Map.Entry) r4;
        r10 = r4;
        goto L_0x0064;
    L_0x0081:
        r10 = 0;
        goto L_0x0064;
    L_0x0083:
        r4 = r12 & r16;
        r4 = (long) r4;
        switch(r14) {
            case 0: goto L_0x0510;
            case 1: goto L_0x0502;
            case 2: goto L_0x04f4;
            case 3: goto L_0x04e6;
            case 4: goto L_0x04d8;
            case 5: goto L_0x04ca;
            case 6: goto L_0x04bc;
            case 7: goto L_0x04ae;
            case 8: goto L_0x049f;
            case 9: goto L_0x048c;
            case 10: goto L_0x047b;
            case 11: goto L_0x046c;
            case 12: goto L_0x045d;
            case 13: goto L_0x044e;
            case 14: goto L_0x043f;
            case 15: goto L_0x0430;
            case 16: goto L_0x0421;
            case 17: goto L_0x040e;
            case 18: goto L_0x03fc;
            case 19: goto L_0x03ea;
            case 20: goto L_0x03d8;
            case 21: goto L_0x03c6;
            case 22: goto L_0x03b4;
            case 23: goto L_0x03a2;
            case 24: goto L_0x0390;
            case 25: goto L_0x037e;
            case 26: goto L_0x036d;
            case 27: goto L_0x0358;
            case 28: goto L_0x0347;
            case 29: goto L_0x0334;
            case 30: goto L_0x0323;
            case 31: goto L_0x0312;
            case 32: goto L_0x0301;
            case 33: goto L_0x02f0;
            case 34: goto L_0x02df;
            case 35: goto L_0x02cd;
            case 36: goto L_0x02bb;
            case 37: goto L_0x02a9;
            case 38: goto L_0x0297;
            case 39: goto L_0x0285;
            case 40: goto L_0x0273;
            case 41: goto L_0x0261;
            case 42: goto L_0x024f;
            case 43: goto L_0x023d;
            case 44: goto L_0x022b;
            case 45: goto L_0x0219;
            case 46: goto L_0x0207;
            case 47: goto L_0x01f5;
            case 48: goto L_0x01e3;
            case 49: goto L_0x01ce;
            case 50: goto L_0x01c3;
            case 51: goto L_0x01b2;
            case 52: goto L_0x01a1;
            case 53: goto L_0x0190;
            case 54: goto L_0x017f;
            case 55: goto L_0x016e;
            case 56: goto L_0x015d;
            case 57: goto L_0x014c;
            case 58: goto L_0x013b;
            case 59: goto L_0x012a;
            case 60: goto L_0x0115;
            case 61: goto L_0x0102;
            case 62: goto L_0x00f2;
            case 63: goto L_0x00e2;
            case 64: goto L_0x00d2;
            case 65: goto L_0x00c2;
            case 66: goto L_0x00b2;
            case 67: goto L_0x00a2;
            case 68: goto L_0x008e;
            default: goto L_0x0089;
        };
    L_0x0089:
        r12 = r18;
    L_0x008b:
        r14 = 0;
        goto L_0x051d;
    L_0x008e:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x0096:
        r4 = r9.getObject(r1, r4);
        r5 = r0.zzfq(r12);
        r2.zzb(r13, r4, r5);
        goto L_0x008b;
    L_0x00a2:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x00aa:
        r4 = zzi(r1, r4);
        r2.zzk(r13, r4);
        goto L_0x008b;
    L_0x00b2:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x00ba:
        r4 = zzh(r1, r4);
        r2.zzx(r13, r4);
        goto L_0x008b;
    L_0x00c2:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x00ca:
        r4 = zzi(r1, r4);
        r2.zzs(r13, r4);
        goto L_0x008b;
    L_0x00d2:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x00da:
        r4 = zzh(r1, r4);
        r2.zzaf(r13, r4);
        goto L_0x008b;
    L_0x00e2:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x00ea:
        r4 = zzh(r1, r4);
        r2.zzag(r13, r4);
        goto L_0x008b;
    L_0x00f2:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x00fa:
        r4 = zzh(r1, r4);
        r2.zzw(r13, r4);
        goto L_0x008b;
    L_0x0102:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x010a:
        r4 = r9.getObject(r1, r4);
        r4 = (com.google.android.gms.internal.ads.zzbpu) r4;
        r2.zza(r13, r4);
        goto L_0x008b;
    L_0x0115:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x011d:
        r4 = r9.getObject(r1, r4);
        r5 = r0.zzfq(r12);
        r2.zza(r13, r4, r5);
        goto L_0x008b;
    L_0x012a:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x0132:
        r4 = r9.getObject(r1, r4);
        zza(r13, r4, r2);
        goto L_0x008b;
    L_0x013b:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x0143:
        r4 = zzj(r1, r4);
        r2.zzj(r13, r4);
        goto L_0x008b;
    L_0x014c:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x0154:
        r4 = zzh(r1, r4);
        r2.zzy(r13, r4);
        goto L_0x008b;
    L_0x015d:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x0165:
        r4 = zzi(r1, r4);
        r2.zzl(r13, r4);
        goto L_0x008b;
    L_0x016e:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x0176:
        r4 = zzh(r1, r4);
        r2.zzv(r13, r4);
        goto L_0x008b;
    L_0x017f:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x0187:
        r4 = zzi(r1, r4);
        r2.zzj(r13, r4);
        goto L_0x008b;
    L_0x0190:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x0198:
        r4 = zzi(r1, r4);
        r2.zzr(r13, r4);
        goto L_0x008b;
    L_0x01a1:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x01a9:
        r4 = zzg(r1, r4);
        r2.zza(r13, r4);
        goto L_0x008b;
    L_0x01b2:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x01ba:
        r4 = zzf(r1, r4);
        r2.zzb(r13, r4);
        goto L_0x008b;
    L_0x01c3:
        r12 = r18;
        r4 = r9.getObject(r1, r4);
        r0.zza(r2, r13, r4, r12);
        goto L_0x008b;
    L_0x01ce:
        r12 = r18;
        r8 = r0.zzfsi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r5 = r0.zzfq(r12);
        com.google.android.gms.internal.ads.zzbte.zzb(r8, r4, r2, r5);
        goto L_0x008b;
    L_0x01e3:
        r12 = r18;
        r8 = r0.zzfsi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r13 = 1;
        com.google.android.gms.internal.ads.zzbte.zze(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x01f5:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzfsi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.ads.zzbte.zzj(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x0207:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzfsi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.ads.zzbte.zzg(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x0219:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzfsi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.ads.zzbte.zzl(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x022b:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzfsi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.ads.zzbte.zzm(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x023d:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzfsi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.ads.zzbte.zzi(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x024f:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzfsi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.ads.zzbte.zzn(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x0261:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzfsi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.ads.zzbte.zzk(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x0273:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzfsi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.ads.zzbte.zzf(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x0285:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzfsi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.ads.zzbte.zzh(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x0297:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzfsi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.ads.zzbte.zzd(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x02a9:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzfsi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.ads.zzbte.zzc(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x02bb:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzfsi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.ads.zzbte.zzb(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x02cd:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzfsi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.ads.zzbte.zza(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x02df:
        r12 = r18;
        r8 = r0.zzfsi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r13 = 0;
        com.google.android.gms.internal.ads.zzbte.zze(r8, r4, r2, r13);
        goto L_0x0344;
    L_0x02f0:
        r12 = r18;
        r13 = 0;
        r8 = r0.zzfsi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.ads.zzbte.zzj(r8, r4, r2, r13);
        goto L_0x0344;
    L_0x0301:
        r12 = r18;
        r13 = 0;
        r8 = r0.zzfsi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.ads.zzbte.zzg(r8, r4, r2, r13);
        goto L_0x0344;
    L_0x0312:
        r12 = r18;
        r13 = 0;
        r8 = r0.zzfsi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.ads.zzbte.zzl(r8, r4, r2, r13);
        goto L_0x0344;
    L_0x0323:
        r12 = r18;
        r13 = 0;
        r8 = r0.zzfsi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.ads.zzbte.zzm(r8, r4, r2, r13);
        goto L_0x0344;
    L_0x0334:
        r12 = r18;
        r13 = 0;
        r8 = r0.zzfsi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.ads.zzbte.zzi(r8, r4, r2, r13);
    L_0x0344:
        r14 = r13;
        goto L_0x051d;
    L_0x0347:
        r12 = r18;
        r8 = r0.zzfsi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.ads.zzbte.zzb(r8, r4, r2);
        goto L_0x008b;
    L_0x0358:
        r12 = r18;
        r8 = r0.zzfsi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r5 = r0.zzfq(r12);
        com.google.android.gms.internal.ads.zzbte.zza(r8, r4, r2, r5);
        goto L_0x008b;
    L_0x036d:
        r12 = r18;
        r8 = r0.zzfsi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.ads.zzbte.zza(r8, r4, r2);
        goto L_0x008b;
    L_0x037e:
        r12 = r18;
        r8 = r0.zzfsi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r14 = 0;
        com.google.android.gms.internal.ads.zzbte.zzn(r8, r4, r2, r14);
        goto L_0x051d;
    L_0x0390:
        r12 = r18;
        r14 = 0;
        r8 = r0.zzfsi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.ads.zzbte.zzk(r8, r4, r2, r14);
        goto L_0x051d;
    L_0x03a2:
        r12 = r18;
        r14 = 0;
        r8 = r0.zzfsi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.ads.zzbte.zzf(r8, r4, r2, r14);
        goto L_0x051d;
    L_0x03b4:
        r12 = r18;
        r14 = 0;
        r8 = r0.zzfsi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.ads.zzbte.zzh(r8, r4, r2, r14);
        goto L_0x051d;
    L_0x03c6:
        r12 = r18;
        r14 = 0;
        r8 = r0.zzfsi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.ads.zzbte.zzd(r8, r4, r2, r14);
        goto L_0x051d;
    L_0x03d8:
        r12 = r18;
        r14 = 0;
        r8 = r0.zzfsi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.ads.zzbte.zzc(r8, r4, r2, r14);
        goto L_0x051d;
    L_0x03ea:
        r12 = r18;
        r14 = 0;
        r8 = r0.zzfsi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.ads.zzbte.zzb(r8, r4, r2, r14);
        goto L_0x051d;
    L_0x03fc:
        r12 = r18;
        r14 = 0;
        r8 = r0.zzfsi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.ads.zzbte.zza(r8, r4, r2, r14);
        goto L_0x051d;
    L_0x040e:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x0414:
        r4 = r9.getObject(r1, r4);
        r5 = r0.zzfq(r12);
        r2.zzb(r13, r4, r5);
        goto L_0x051d;
    L_0x0421:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x0427:
        r4 = r9.getLong(r1, r4);
        r2.zzk(r13, r4);
        goto L_0x051d;
    L_0x0430:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x0436:
        r4 = r9.getInt(r1, r4);
        r2.zzx(r13, r4);
        goto L_0x051d;
    L_0x043f:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x0445:
        r4 = r9.getLong(r1, r4);
        r2.zzs(r13, r4);
        goto L_0x051d;
    L_0x044e:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x0454:
        r4 = r9.getInt(r1, r4);
        r2.zzaf(r13, r4);
        goto L_0x051d;
    L_0x045d:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x0463:
        r4 = r9.getInt(r1, r4);
        r2.zzag(r13, r4);
        goto L_0x051d;
    L_0x046c:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x0472:
        r4 = r9.getInt(r1, r4);
        r2.zzw(r13, r4);
        goto L_0x051d;
    L_0x047b:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x0481:
        r4 = r9.getObject(r1, r4);
        r4 = (com.google.android.gms.internal.ads.zzbpu) r4;
        r2.zza(r13, r4);
        goto L_0x051d;
    L_0x048c:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x0492:
        r4 = r9.getObject(r1, r4);
        r5 = r0.zzfq(r12);
        r2.zza(r13, r4, r5);
        goto L_0x051d;
    L_0x049f:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x04a5:
        r4 = r9.getObject(r1, r4);
        zza(r13, r4, r2);
        goto L_0x051d;
    L_0x04ae:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x04b4:
        r4 = com.google.android.gms.internal.ads.zzbua.zzm(r1, r4);
        r2.zzj(r13, r4);
        goto L_0x051d;
    L_0x04bc:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x04c2:
        r4 = r9.getInt(r1, r4);
        r2.zzy(r13, r4);
        goto L_0x051d;
    L_0x04ca:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x04d0:
        r4 = r9.getLong(r1, r4);
        r2.zzl(r13, r4);
        goto L_0x051d;
    L_0x04d8:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x04de:
        r4 = r9.getInt(r1, r4);
        r2.zzv(r13, r4);
        goto L_0x051d;
    L_0x04e6:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x04ec:
        r4 = r9.getLong(r1, r4);
        r2.zzj(r13, r4);
        goto L_0x051d;
    L_0x04f4:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x04fa:
        r4 = r9.getLong(r1, r4);
        r2.zzr(r13, r4);
        goto L_0x051d;
    L_0x0502:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x0508:
        r4 = com.google.android.gms.internal.ads.zzbua.zzn(r1, r4);
        r2.zza(r13, r4);
        goto L_0x051d;
    L_0x0510:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x0516:
        r4 = com.google.android.gms.internal.ads.zzbua.zzo(r1, r4);
        r2.zzb(r13, r4);
    L_0x051d:
        r5 = r12 + 3;
        goto L_0x002c;
    L_0x0521:
        if (r10 == 0) goto L_0x0538;
    L_0x0523:
        r4 = r0.zzfsx;
        r4.zza(r2, r10);
        r4 = r3.hasNext();
        if (r4 == 0) goto L_0x0536;
    L_0x052e:
        r4 = r3.next();
        r4 = (java.util.Map.Entry) r4;
        r10 = r4;
        goto L_0x0521;
    L_0x0536:
        r10 = 0;
        goto L_0x0521;
    L_0x0538:
        r3 = r0.zzfsw;
        zza(r3, r1, r2);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbsp.zzb(java.lang.Object, com.google.android.gms.internal.ads.zzbup):void");
    }

    private final <K, V> void zza(zzbup zzbup, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzbup.zza(i, this.zzfsy.zzab(zzfr(i2)), this.zzfsy.zzx(obj));
        }
    }

    private static <UT, UB> void zza(zzbtu<UT, UB> zzbtu, T t, zzbup zzbup) throws IOException {
        zzbtu.zza(zzbtu.zzag(t), zzbup);
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:151:0x05a7 */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:151|152|(1:154)|155|(6:175|157|(2:160|158)|254|(1:162)|163)(1:250)) */
    /* JADX WARNING: Missing block: B:152:?, code skipped:
            r7.zza(r14);
     */
    /* JADX WARNING: Missing block: B:153:0x05aa, code skipped:
            if (r10 == null) goto L_0x05ac;
     */
    /* JADX WARNING: Missing block: B:154:0x05ac, code skipped:
            r10 = r7.zzah(r13);
     */
    /* JADX WARNING: Missing block: B:156:0x05b5, code skipped:
            if (r7.zza(r10, r14) == false) goto L_0x05b7;
     */
    /* JADX WARNING: Missing block: B:157:0x05b7, code skipped:
            r14 = r12.zzfss;
     */
    /* JADX WARNING: Missing block: B:159:0x05bb, code skipped:
            if (r14 < r12.zzfst) goto L_0x05bd;
     */
    /* JADX WARNING: Missing block: B:160:0x05bd, code skipped:
            r10 = zza((java.lang.Object) r13, r12.zzfsr[r14], r10, r7);
            r14 = r14 + 1;
     */
    /* JADX WARNING: Missing block: B:161:0x05c8, code skipped:
            if (r10 != null) goto L_0x05ca;
     */
    /* JADX WARNING: Missing block: B:162:0x05ca, code skipped:
            r7.zzg(r13, r10);
     */
    /* JADX WARNING: Missing block: B:163:0x05cd, code skipped:
            return;
     */
    public final void zza(T r13, com.google.android.gms.internal.ads.zzbtb r14, com.google.android.gms.internal.ads.zzbqq r15) throws java.io.IOException {
        /*
        r12 = this;
        if (r15 != 0) goto L_0x0008;
    L_0x0002:
        r13 = new java.lang.NullPointerException;
        r13.<init>();
        throw r13;
    L_0x0008:
        r7 = r12.zzfsw;
        r8 = r12.zzfsx;
        r9 = 0;
        r0 = r9;
        r10 = r0;
    L_0x000f:
        r1 = r14.zzals();	 Catch:{ all -> 0x05ce }
        r2 = r12.zzfw(r1);	 Catch:{ all -> 0x05ce }
        if (r2 >= 0) goto L_0x007f;
    L_0x0019:
        r2 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        if (r1 != r2) goto L_0x0035;
    L_0x001e:
        r14 = r12.zzfss;
    L_0x0020:
        r15 = r12.zzfst;
        if (r14 >= r15) goto L_0x002f;
    L_0x0024:
        r15 = r12.zzfsr;
        r15 = r15[r14];
        r10 = r12.zza(r13, r15, r10, r7);
        r14 = r14 + 1;
        goto L_0x0020;
    L_0x002f:
        if (r10 == 0) goto L_0x0034;
    L_0x0031:
        r7.zzg(r13, r10);
    L_0x0034:
        return;
    L_0x0035:
        r2 = r12.zzfsn;	 Catch:{ all -> 0x05ce }
        if (r2 != 0) goto L_0x003b;
    L_0x0039:
        r2 = r9;
        goto L_0x0042;
    L_0x003b:
        r2 = r12.zzfsm;	 Catch:{ all -> 0x05ce }
        r1 = r8.zza(r15, r2, r1);	 Catch:{ all -> 0x05ce }
        r2 = r1;
    L_0x0042:
        if (r2 == 0) goto L_0x0058;
    L_0x0044:
        if (r0 != 0) goto L_0x004a;
    L_0x0046:
        r0 = r8.zzr(r13);	 Catch:{ all -> 0x05ce }
    L_0x004a:
        r11 = r0;
        r0 = r8;
        r1 = r14;
        r3 = r15;
        r4 = r11;
        r5 = r10;
        r6 = r7;
        r0 = r0.zza(r1, r2, r3, r4, r5, r6);	 Catch:{ all -> 0x05ce }
        r10 = r0;
        r0 = r11;
        goto L_0x000f;
    L_0x0058:
        r7.zza(r14);	 Catch:{ all -> 0x05ce }
        if (r10 != 0) goto L_0x0062;
    L_0x005d:
        r1 = r7.zzah(r13);	 Catch:{ all -> 0x05ce }
        r10 = r1;
    L_0x0062:
        r1 = r7.zza(r10, r14);	 Catch:{ all -> 0x05ce }
        if (r1 != 0) goto L_0x000f;
    L_0x0068:
        r14 = r12.zzfss;
    L_0x006a:
        r15 = r12.zzfst;
        if (r14 >= r15) goto L_0x0079;
    L_0x006e:
        r15 = r12.zzfsr;
        r15 = r15[r14];
        r10 = r12.zza(r13, r15, r10, r7);
        r14 = r14 + 1;
        goto L_0x006a;
    L_0x0079:
        if (r10 == 0) goto L_0x007e;
    L_0x007b:
        r7.zzg(r13, r10);
    L_0x007e:
        return;
    L_0x007f:
        r3 = r12.zzft(r2);	 Catch:{ all -> 0x05ce }
        r4 = 267386880; // 0xff00000 float:2.3665827E-29 double:1.321066716E-315;
        r4 = r4 & r3;
        r4 = r4 >>> 20;
        r5 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        switch(r4) {
            case 0: goto L_0x057a;
            case 1: goto L_0x056b;
            case 2: goto L_0x055c;
            case 3: goto L_0x054d;
            case 4: goto L_0x053e;
            case 5: goto L_0x052f;
            case 6: goto L_0x0520;
            case 7: goto L_0x0511;
            case 8: goto L_0x0509;
            case 9: goto L_0x04d8;
            case 10: goto L_0x04c9;
            case 11: goto L_0x04ba;
            case 12: goto L_0x0498;
            case 13: goto L_0x0489;
            case 14: goto L_0x047a;
            case 15: goto L_0x046b;
            case 16: goto L_0x045c;
            case 17: goto L_0x042b;
            case 18: goto L_0x041d;
            case 19: goto L_0x040f;
            case 20: goto L_0x0401;
            case 21: goto L_0x03f3;
            case 22: goto L_0x03e5;
            case 23: goto L_0x03d7;
            case 24: goto L_0x03c9;
            case 25: goto L_0x03bb;
            case 26: goto L_0x0399;
            case 27: goto L_0x0387;
            case 28: goto L_0x0379;
            case 29: goto L_0x036b;
            case 30: goto L_0x0355;
            case 31: goto L_0x0347;
            case 32: goto L_0x0339;
            case 33: goto L_0x032b;
            case 34: goto L_0x031d;
            case 35: goto L_0x030f;
            case 36: goto L_0x0301;
            case 37: goto L_0x02f3;
            case 38: goto L_0x02e5;
            case 39: goto L_0x02d7;
            case 40: goto L_0x02c9;
            case 41: goto L_0x02bb;
            case 42: goto L_0x02ad;
            case 43: goto L_0x029f;
            case 44: goto L_0x028a;
            case 45: goto L_0x027c;
            case 46: goto L_0x026e;
            case 47: goto L_0x0260;
            case 48: goto L_0x0252;
            case 49: goto L_0x0240;
            case 50: goto L_0x01fe;
            case 51: goto L_0x01ec;
            case 52: goto L_0x01da;
            case 53: goto L_0x01c8;
            case 54: goto L_0x01b6;
            case 55: goto L_0x01a4;
            case 56: goto L_0x0192;
            case 57: goto L_0x0180;
            case 58: goto L_0x016e;
            case 59: goto L_0x0166;
            case 60: goto L_0x0135;
            case 61: goto L_0x0127;
            case 62: goto L_0x0115;
            case 63: goto L_0x00f0;
            case 64: goto L_0x00de;
            case 65: goto L_0x00cc;
            case 66: goto L_0x00ba;
            case 67: goto L_0x00a8;
            case 68: goto L_0x0096;
            default: goto L_0x008e;
        };
    L_0x008e:
        if (r10 != 0) goto L_0x058a;
    L_0x0090:
        r1 = r7.zzaoy();	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x0589;
    L_0x0096:
        r3 = r3 & r5;
        r3 = (long) r3;	 Catch:{ zzbrm -> 0x05a7 }
        r5 = r12.zzfq(r2);	 Catch:{ zzbrm -> 0x05a7 }
        r5 = r14.zzb(r5, r15);	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x00a8:
        r3 = r3 & r5;
        r3 = (long) r3;	 Catch:{ zzbrm -> 0x05a7 }
        r5 = r14.zzali();	 Catch:{ zzbrm -> 0x05a7 }
        r5 = java.lang.Long.valueOf(r5);	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x00ba:
        r3 = r3 & r5;
        r3 = (long) r3;	 Catch:{ zzbrm -> 0x05a7 }
        r5 = r14.zzalh();	 Catch:{ zzbrm -> 0x05a7 }
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x00cc:
        r3 = r3 & r5;
        r3 = (long) r3;	 Catch:{ zzbrm -> 0x05a7 }
        r5 = r14.zzalg();	 Catch:{ zzbrm -> 0x05a7 }
        r5 = java.lang.Long.valueOf(r5);	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x00de:
        r3 = r3 & r5;
        r3 = (long) r3;	 Catch:{ zzbrm -> 0x05a7 }
        r5 = r14.zzalf();	 Catch:{ zzbrm -> 0x05a7 }
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x00f0:
        r4 = r14.zzale();	 Catch:{ zzbrm -> 0x05a7 }
        r6 = r12.zzfs(r2);	 Catch:{ zzbrm -> 0x05a7 }
        if (r6 == 0) goto L_0x0107;
    L_0x00fa:
        r6 = r6.zzcb(r4);	 Catch:{ zzbrm -> 0x05a7 }
        if (r6 == 0) goto L_0x0101;
    L_0x0100:
        goto L_0x0107;
    L_0x0101:
        r1 = com.google.android.gms.internal.ads.zzbte.zza(r1, r4, r10, r7);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x0368;
    L_0x0107:
        r3 = r3 & r5;
        r5 = (long) r3;	 Catch:{ zzbrm -> 0x05a7 }
        r3 = java.lang.Integer.valueOf(r4);	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zza(r13, r5, r3);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x0115:
        r3 = r3 & r5;
        r3 = (long) r3;	 Catch:{ zzbrm -> 0x05a7 }
        r5 = r14.zzald();	 Catch:{ zzbrm -> 0x05a7 }
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x0127:
        r3 = r3 & r5;
        r3 = (long) r3;	 Catch:{ zzbrm -> 0x05a7 }
        r5 = r14.zzalc();	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x0135:
        r4 = r12.zza(r13, r1, r2);	 Catch:{ zzbrm -> 0x05a7 }
        if (r4 == 0) goto L_0x0151;
    L_0x013b:
        r3 = r3 & r5;
        r3 = (long) r3;	 Catch:{ zzbrm -> 0x05a7 }
        r5 = com.google.android.gms.internal.ads.zzbua.zzp(r13, r3);	 Catch:{ zzbrm -> 0x05a7 }
        r6 = r12.zzfq(r2);	 Catch:{ zzbrm -> 0x05a7 }
        r6 = r14.zza(r6, r15);	 Catch:{ zzbrm -> 0x05a7 }
        r5 = com.google.android.gms.internal.ads.zzbrf.zzb(r5, r6);	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x0161;
    L_0x0151:
        r3 = r3 & r5;
        r3 = (long) r3;	 Catch:{ zzbrm -> 0x05a7 }
        r5 = r12.zzfq(r2);	 Catch:{ zzbrm -> 0x05a7 }
        r5 = r14.zza(r5, r15);	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zze(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
    L_0x0161:
        r12.zzb(r13, r1, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x0166:
        r12.zza(r13, r3, r14);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x016e:
        r3 = r3 & r5;
        r3 = (long) r3;	 Catch:{ zzbrm -> 0x05a7 }
        r5 = r14.zzala();	 Catch:{ zzbrm -> 0x05a7 }
        r5 = java.lang.Boolean.valueOf(r5);	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x0180:
        r3 = r3 & r5;
        r3 = (long) r3;	 Catch:{ zzbrm -> 0x05a7 }
        r5 = r14.zzakz();	 Catch:{ zzbrm -> 0x05a7 }
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x0192:
        r3 = r3 & r5;
        r3 = (long) r3;	 Catch:{ zzbrm -> 0x05a7 }
        r5 = r14.zzaky();	 Catch:{ zzbrm -> 0x05a7 }
        r5 = java.lang.Long.valueOf(r5);	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x01a4:
        r3 = r3 & r5;
        r3 = (long) r3;	 Catch:{ zzbrm -> 0x05a7 }
        r5 = r14.zzakx();	 Catch:{ zzbrm -> 0x05a7 }
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x01b6:
        r3 = r3 & r5;
        r3 = (long) r3;	 Catch:{ zzbrm -> 0x05a7 }
        r5 = r14.zzakv();	 Catch:{ zzbrm -> 0x05a7 }
        r5 = java.lang.Long.valueOf(r5);	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x01c8:
        r3 = r3 & r5;
        r3 = (long) r3;	 Catch:{ zzbrm -> 0x05a7 }
        r5 = r14.zzakw();	 Catch:{ zzbrm -> 0x05a7 }
        r5 = java.lang.Long.valueOf(r5);	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x01da:
        r3 = r3 & r5;
        r3 = (long) r3;	 Catch:{ zzbrm -> 0x05a7 }
        r5 = r14.readFloat();	 Catch:{ zzbrm -> 0x05a7 }
        r5 = java.lang.Float.valueOf(r5);	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x01ec:
        r3 = r3 & r5;
        r3 = (long) r3;	 Catch:{ zzbrm -> 0x05a7 }
        r5 = r14.readDouble();	 Catch:{ zzbrm -> 0x05a7 }
        r5 = java.lang.Double.valueOf(r5);	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x01fe:
        r1 = r12.zzfr(r2);	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r12.zzft(r2);	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r2 & r5;
        r2 = (long) r2;	 Catch:{ zzbrm -> 0x05a7 }
        r4 = com.google.android.gms.internal.ads.zzbua.zzp(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        if (r4 != 0) goto L_0x0218;
    L_0x020e:
        r4 = r12.zzfsy;	 Catch:{ zzbrm -> 0x05a7 }
        r4 = r4.zzaa(r1);	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zza(r13, r2, r4);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x022f;
    L_0x0218:
        r5 = r12.zzfsy;	 Catch:{ zzbrm -> 0x05a7 }
        r5 = r5.zzy(r4);	 Catch:{ zzbrm -> 0x05a7 }
        if (r5 == 0) goto L_0x022f;
    L_0x0220:
        r5 = r12.zzfsy;	 Catch:{ zzbrm -> 0x05a7 }
        r5 = r5.zzaa(r1);	 Catch:{ zzbrm -> 0x05a7 }
        r6 = r12.zzfsy;	 Catch:{ zzbrm -> 0x05a7 }
        r6.zzc(r5, r4);	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zza(r13, r2, r5);	 Catch:{ zzbrm -> 0x05a7 }
        r4 = r5;
    L_0x022f:
        r2 = r12.zzfsy;	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r2.zzw(r4);	 Catch:{ zzbrm -> 0x05a7 }
        r3 = r12.zzfsy;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r3.zzab(r1);	 Catch:{ zzbrm -> 0x05a7 }
        r14.zza(r2, r1, r15);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x0240:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r12.zzfq(r2);	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r12.zzfsv;	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r2.zza(r13, r3);	 Catch:{ zzbrm -> 0x05a7 }
        r14.zzb(r2, r1, r15);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x0252:
        r1 = r12.zzfsv;	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        r14.zzae(r1);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x0260:
        r1 = r12.zzfsv;	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        r14.zzad(r1);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x026e:
        r1 = r12.zzfsv;	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        r14.zzac(r1);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x027c:
        r1 = r12.zzfsv;	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        r14.zzab(r1);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x028a:
        r4 = r12.zzfsv;	 Catch:{ zzbrm -> 0x05a7 }
        r3 = r3 & r5;
        r5 = (long) r3;	 Catch:{ zzbrm -> 0x05a7 }
        r3 = r4.zza(r13, r5);	 Catch:{ zzbrm -> 0x05a7 }
        r14.zzaa(r3);	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r12.zzfs(r2);	 Catch:{ zzbrm -> 0x05a7 }
        r1 = com.google.android.gms.internal.ads.zzbte.zza(r1, r3, r2, r10, r7);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x0368;
    L_0x029f:
        r1 = r12.zzfsv;	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        r14.zzz(r1);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x02ad:
        r1 = r12.zzfsv;	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        r14.zzw(r1);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x02bb:
        r1 = r12.zzfsv;	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        r14.zzv(r1);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x02c9:
        r1 = r12.zzfsv;	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        r14.zzu(r1);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x02d7:
        r1 = r12.zzfsv;	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        r14.zzt(r1);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x02e5:
        r1 = r12.zzfsv;	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        r14.zzr(r1);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x02f3:
        r1 = r12.zzfsv;	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        r14.zzs(r1);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x0301:
        r1 = r12.zzfsv;	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        r14.zzq(r1);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x030f:
        r1 = r12.zzfsv;	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        r14.zzp(r1);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x031d:
        r1 = r12.zzfsv;	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        r14.zzae(r1);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x032b:
        r1 = r12.zzfsv;	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        r14.zzad(r1);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x0339:
        r1 = r12.zzfsv;	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        r14.zzac(r1);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x0347:
        r1 = r12.zzfsv;	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        r14.zzab(r1);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x0355:
        r4 = r12.zzfsv;	 Catch:{ zzbrm -> 0x05a7 }
        r3 = r3 & r5;
        r5 = (long) r3;	 Catch:{ zzbrm -> 0x05a7 }
        r3 = r4.zza(r13, r5);	 Catch:{ zzbrm -> 0x05a7 }
        r14.zzaa(r3);	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r12.zzfs(r2);	 Catch:{ zzbrm -> 0x05a7 }
        r1 = com.google.android.gms.internal.ads.zzbte.zza(r1, r3, r2, r10, r7);	 Catch:{ zzbrm -> 0x05a7 }
    L_0x0368:
        r10 = r1;
        goto L_0x000f;
    L_0x036b:
        r1 = r12.zzfsv;	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        r14.zzz(r1);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x0379:
        r1 = r12.zzfsv;	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        r14.zzy(r1);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x0387:
        r1 = r12.zzfq(r2);	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzbrm -> 0x05a7 }
        r4 = r12.zzfsv;	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r4.zza(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        r14.zza(r2, r1, r15);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x0399:
        r1 = zzfv(r3);	 Catch:{ zzbrm -> 0x05a7 }
        if (r1 == 0) goto L_0x03ad;
    L_0x039f:
        r1 = r12.zzfsv;	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        r14.zzx(r1);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x03ad:
        r1 = r12.zzfsv;	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        r14.readStringList(r1);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x03bb:
        r1 = r12.zzfsv;	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        r14.zzw(r1);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x03c9:
        r1 = r12.zzfsv;	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        r14.zzv(r1);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x03d7:
        r1 = r12.zzfsv;	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        r14.zzu(r1);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x03e5:
        r1 = r12.zzfsv;	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        r14.zzt(r1);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x03f3:
        r1 = r12.zzfsv;	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        r14.zzr(r1);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x0401:
        r1 = r12.zzfsv;	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        r14.zzs(r1);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x040f:
        r1 = r12.zzfsv;	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        r14.zzq(r1);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x041d:
        r1 = r12.zzfsv;	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        r14.zzp(r1);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x042b:
        r1 = r12.zzd(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        if (r1 == 0) goto L_0x0449;
    L_0x0431:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = com.google.android.gms.internal.ads.zzbua.zzp(r13, r3);	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r12.zzfq(r2);	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r14.zzb(r2, r15);	 Catch:{ zzbrm -> 0x05a7 }
        r1 = com.google.android.gms.internal.ads.zzbrf.zzb(r1, r2);	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r1);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x0449:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r12.zzfq(r2);	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r14.zzb(r1, r15);	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r1);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zze(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x045c:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzbrm -> 0x05a7 }
        r5 = r14.zzali();	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zze(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x046b:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r14.zzalh();	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zzb(r13, r3, r1);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zze(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x047a:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzbrm -> 0x05a7 }
        r5 = r14.zzalg();	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zze(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x0489:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r14.zzalf();	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zzb(r13, r3, r1);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zze(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x0498:
        r4 = r14.zzale();	 Catch:{ zzbrm -> 0x05a7 }
        r6 = r12.zzfs(r2);	 Catch:{ zzbrm -> 0x05a7 }
        if (r6 == 0) goto L_0x04af;
    L_0x04a2:
        r6 = r6.zzcb(r4);	 Catch:{ zzbrm -> 0x05a7 }
        if (r6 == 0) goto L_0x04a9;
    L_0x04a8:
        goto L_0x04af;
    L_0x04a9:
        r1 = com.google.android.gms.internal.ads.zzbte.zza(r1, r4, r10, r7);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x0368;
    L_0x04af:
        r1 = r3 & r5;
        r5 = (long) r1;	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zzb(r13, r5, r4);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zze(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x04ba:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r14.zzald();	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zzb(r13, r3, r1);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zze(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x04c9:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r14.zzalc();	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r1);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zze(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x04d8:
        r1 = r12.zzd(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        if (r1 == 0) goto L_0x04f6;
    L_0x04de:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = com.google.android.gms.internal.ads.zzbua.zzp(r13, r3);	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r12.zzfq(r2);	 Catch:{ zzbrm -> 0x05a7 }
        r2 = r14.zza(r2, r15);	 Catch:{ zzbrm -> 0x05a7 }
        r1 = com.google.android.gms.internal.ads.zzbrf.zzb(r1, r2);	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r1);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x04f6:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r12.zzfq(r2);	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r14.zza(r1, r15);	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r1);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zze(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x0509:
        r12.zza(r13, r3, r14);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zze(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x0511:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r14.zzala();	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r1);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zze(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x0520:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r14.zzakz();	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zzb(r13, r3, r1);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zze(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x052f:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzbrm -> 0x05a7 }
        r5 = r14.zzaky();	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zze(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x053e:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r14.zzakx();	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zzb(r13, r3, r1);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zze(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x054d:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzbrm -> 0x05a7 }
        r5 = r14.zzakv();	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zze(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x055c:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzbrm -> 0x05a7 }
        r5 = r14.zzakw();	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zze(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x056b:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzbrm -> 0x05a7 }
        r1 = r14.readFloat();	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r1);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zze(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x057a:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzbrm -> 0x05a7 }
        r5 = r14.readDouble();	 Catch:{ zzbrm -> 0x05a7 }
        com.google.android.gms.internal.ads.zzbua.zza(r13, r3, r5);	 Catch:{ zzbrm -> 0x05a7 }
        r12.zze(r13, r2);	 Catch:{ zzbrm -> 0x05a7 }
        goto L_0x000f;
    L_0x0589:
        r10 = r1;
    L_0x058a:
        r1 = r7.zza(r10, r14);	 Catch:{ zzbrm -> 0x05a7 }
        if (r1 != 0) goto L_0x000f;
    L_0x0590:
        r14 = r12.zzfss;
    L_0x0592:
        r15 = r12.zzfst;
        if (r14 >= r15) goto L_0x05a1;
    L_0x0596:
        r15 = r12.zzfsr;
        r15 = r15[r14];
        r10 = r12.zza(r13, r15, r10, r7);
        r14 = r14 + 1;
        goto L_0x0592;
    L_0x05a1:
        if (r10 == 0) goto L_0x05a6;
    L_0x05a3:
        r7.zzg(r13, r10);
    L_0x05a6:
        return;
    L_0x05a7:
        r7.zza(r14);	 Catch:{ all -> 0x05ce }
        if (r10 != 0) goto L_0x05b1;
    L_0x05ac:
        r1 = r7.zzah(r13);	 Catch:{ all -> 0x05ce }
        r10 = r1;
    L_0x05b1:
        r1 = r7.zza(r10, r14);	 Catch:{ all -> 0x05ce }
        if (r1 != 0) goto L_0x000f;
    L_0x05b7:
        r14 = r12.zzfss;
    L_0x05b9:
        r15 = r12.zzfst;
        if (r14 >= r15) goto L_0x05c8;
    L_0x05bd:
        r15 = r12.zzfsr;
        r15 = r15[r14];
        r10 = r12.zza(r13, r15, r10, r7);
        r14 = r14 + 1;
        goto L_0x05b9;
    L_0x05c8:
        if (r10 == 0) goto L_0x05cd;
    L_0x05ca:
        r7.zzg(r13, r10);
    L_0x05cd:
        return;
    L_0x05ce:
        r14 = move-exception;
        r15 = r12.zzfss;
    L_0x05d1:
        r0 = r12.zzfst;
        if (r15 >= r0) goto L_0x05e0;
    L_0x05d5:
        r0 = r12.zzfsr;
        r0 = r0[r15];
        r10 = r12.zza(r13, r0, r10, r7);
        r15 = r15 + 1;
        goto L_0x05d1;
    L_0x05e0:
        if (r10 == 0) goto L_0x05e5;
    L_0x05e2:
        r7.zzg(r13, r10);
    L_0x05e5:
        throw r14;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbsp.zza(java.lang.Object, com.google.android.gms.internal.ads.zzbtb, com.google.android.gms.internal.ads.zzbqq):void");
    }

    private static zzbtv zzad(Object obj) {
        zzbrd zzbrd = (zzbrd) obj;
        zzbtv zzbtv = zzbrd.zzfpu;
        if (zzbtv != zzbtv.zzaoz()) {
            return zzbtv;
        }
        zzbtv = zzbtv.zzapa();
        zzbrd.zzfpu = zzbtv;
        return zzbtv;
    }

    private static int zza(zzbtc zzbtc, byte[] bArr, int i, int i2, zzbpr zzbpr) throws IOException {
        int i3 = i + 1;
        i = bArr[i];
        if (i < 0) {
            i3 = zzbpq.zza(i, bArr, i3, zzbpr);
            i = zzbpr.zzfld;
        }
        int i4 = i3;
        if (i < 0 || i > i2 - i4) {
            throw zzbrl.zzanc();
        }
        Object newInstance = zzbtc.newInstance();
        i += i4;
        zzbtc.zza(newInstance, bArr, i4, i, zzbpr);
        zzbtc.zzs(newInstance);
        zzbpr.zzflf = newInstance;
        return i;
    }

    private static int zza(zzbtc zzbtc, byte[] bArr, int i, int i2, int i3, zzbpr zzbpr) throws IOException {
        zzbsp zzbsp = (zzbsp) zzbtc;
        Object newInstance = zzbsp.newInstance();
        int zza = zzbsp.zza(newInstance, bArr, i, i2, i3, zzbpr);
        zzbsp.zzs(newInstance);
        zzbpr.zzflf = newInstance;
        return zza;
    }

    private static int zza(zzbtc<?> zzbtc, int i, byte[] bArr, int i2, int i3, zzbrk<?> zzbrk, zzbpr zzbpr) throws IOException {
        i2 = zza((zzbtc) zzbtc, bArr, i2, i3, zzbpr);
        zzbrk.add(zzbpr.zzflf);
        while (i2 < i3) {
            int zza = zzbpq.zza(bArr, i2, zzbpr);
            if (i != zzbpr.zzfld) {
                break;
            }
            i2 = zza((zzbtc) zzbtc, bArr, zza, i3, zzbpr);
            zzbrk.add(zzbpr.zzflf);
        }
        return i2;
    }

    private static int zza(byte[] bArr, int i, int i2, zzbuj zzbuj, Class<?> cls, zzbpr zzbpr) throws IOException {
        int zzb;
        switch (zzbsq.zzfmd[zzbuj.ordinal()]) {
            case 1:
                zzb = zzbpq.zzb(bArr, i, zzbpr);
                zzbpr.zzflf = Boolean.valueOf(zzbpr.zzfle != 0);
                return zzb;
            case 2:
                return zzbpq.zze(bArr, i, zzbpr);
            case 3:
                zzbpr.zzflf = Double.valueOf(zzbpq.zzi(bArr, i));
                return i + 8;
            case 4:
            case 5:
                zzbpr.zzflf = Integer.valueOf(zzbpq.zzg(bArr, i));
                return i + 4;
            case 6:
            case 7:
                zzbpr.zzflf = Long.valueOf(zzbpq.zzh(bArr, i));
                return i + 8;
            case 8:
                zzbpr.zzflf = Float.valueOf(zzbpq.zzj(bArr, i));
                return i + 4;
            case 9:
            case 10:
            case 11:
                zzb = zzbpq.zza(bArr, i, zzbpr);
                zzbpr.zzflf = Integer.valueOf(zzbpr.zzfld);
                return zzb;
            case 12:
            case 13:
                zzb = zzbpq.zzb(bArr, i, zzbpr);
                zzbpr.zzflf = Long.valueOf(zzbpr.zzfle);
                return zzb;
            case 14:
                return zza(zzbsy.zzaog().zzf(cls), bArr, i, i2, zzbpr);
            case 15:
                zzb = zzbpq.zza(bArr, i, zzbpr);
                zzbpr.zzflf = Integer.valueOf(zzbqf.zzeu(zzbpr.zzfld));
                return zzb;
            case 16:
                zzb = zzbpq.zzb(bArr, i, zzbpr);
                zzbpr.zzflf = Long.valueOf(zzbqf.zzax(zzbpr.zzfle));
                return zzb;
            case 17:
                return zzbpq.zzd(bArr, i, zzbpr);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private static int zza(int i, byte[] bArr, int i2, int i3, Object obj, zzbpr zzbpr) throws IOException {
        return zzbpq.zza(i, bArr, i2, i3, zzad(obj), zzbpr);
    }

    /* JADX WARNING: Removed duplicated region for block: B:114:0x022a  */
    /* JADX WARNING: Missing block: B:270:?, code skipped:
            return r4;
     */
    /* JADX WARNING: Missing block: B:273:?, code skipped:
            return r2;
     */
    private final int zza(T r18, byte[] r19, int r20, int r21, int r22, int r23, int r24, int r25, long r26, int r28, long r29, com.google.android.gms.internal.ads.zzbpr r31) throws java.io.IOException {
        /*
        r17 = this;
        r0 = r17;
        r1 = r18;
        r7 = r19;
        r4 = r20;
        r8 = r21;
        r9 = r22;
        r2 = r24;
        r10 = r25;
        r5 = r29;
        r11 = r31;
        r3 = zzfsh;
        r3 = r3.getObject(r1, r5);
        r3 = (com.google.android.gms.internal.ads.zzbrk) r3;
        r12 = r3.zzaki();
        r13 = 1;
        if (r12 != 0) goto L_0x0036;
    L_0x0023:
        r12 = r3.size();
        if (r12 != 0) goto L_0x002c;
    L_0x0029:
        r12 = 10;
        goto L_0x002d;
    L_0x002c:
        r12 = r12 << r13;
    L_0x002d:
        r3 = r3.zzel(r12);
        r12 = zzfsh;
        r12.putObject(r1, r5, r3);
    L_0x0036:
        r12 = r3;
        r3 = 5;
        r5 = 0;
        r14 = 2;
        switch(r28) {
            case 18: goto L_0x03c6;
            case 19: goto L_0x0387;
            case 20: goto L_0x0348;
            case 21: goto L_0x0348;
            case 22: goto L_0x0332;
            case 23: goto L_0x02f3;
            case 24: goto L_0x02b4;
            case 25: goto L_0x025f;
            case 26: goto L_0x01a5;
            case 27: goto L_0x0194;
            case 28: goto L_0x013b;
            case 29: goto L_0x0332;
            case 30: goto L_0x0109;
            case 31: goto L_0x02b4;
            case 32: goto L_0x02f3;
            case 33: goto L_0x00be;
            case 34: goto L_0x0073;
            case 35: goto L_0x03c6;
            case 36: goto L_0x0387;
            case 37: goto L_0x0348;
            case 38: goto L_0x0348;
            case 39: goto L_0x0332;
            case 40: goto L_0x02f3;
            case 41: goto L_0x02b4;
            case 42: goto L_0x025f;
            case 43: goto L_0x0332;
            case 44: goto L_0x0109;
            case 45: goto L_0x02b4;
            case 46: goto L_0x02f3;
            case 47: goto L_0x00be;
            case 48: goto L_0x0073;
            case 49: goto L_0x0040;
            default: goto L_0x003e;
        };
    L_0x003e:
        goto L_0x0405;
    L_0x0040:
        r1 = 3;
        if (r2 != r1) goto L_0x0405;
    L_0x0043:
        r10 = r0.zzfq(r10);
        r1 = r9 & -8;
        r13 = r1 | 4;
        r1 = r10;
        r2 = r7;
        r3 = r4;
        r4 = r8;
        r5 = r13;
        r6 = r11;
        r1 = zza(r1, r2, r3, r4, r5, r6);
        r2 = r11.zzflf;
        r12.add(r2);
    L_0x005a:
        if (r1 >= r8) goto L_0x0406;
    L_0x005c:
        r3 = com.google.android.gms.internal.ads.zzbpq.zza(r7, r1, r11);
        r2 = r11.zzfld;
        if (r9 != r2) goto L_0x0406;
    L_0x0064:
        r1 = r10;
        r2 = r7;
        r4 = r8;
        r5 = r13;
        r6 = r11;
        r1 = zza(r1, r2, r3, r4, r5, r6);
        r2 = r11.zzflf;
        r12.add(r2);
        goto L_0x005a;
    L_0x0073:
        if (r2 != r14) goto L_0x0095;
    L_0x0075:
        r12 = (com.google.android.gms.internal.ads.zzbrz) r12;
        r1 = com.google.android.gms.internal.ads.zzbpq.zza(r7, r4, r11);
        r2 = r11.zzfld;
        r2 = r2 + r1;
    L_0x007e:
        if (r1 >= r2) goto L_0x008e;
    L_0x0080:
        r1 = com.google.android.gms.internal.ads.zzbpq.zzb(r7, r1, r11);
        r3 = r11.zzfle;
        r3 = com.google.android.gms.internal.ads.zzbqf.zzax(r3);
        r12.zzbj(r3);
        goto L_0x007e;
    L_0x008e:
        if (r1 == r2) goto L_0x0406;
    L_0x0090:
        r1 = com.google.android.gms.internal.ads.zzbrl.zzanc();
        throw r1;
    L_0x0095:
        if (r2 != 0) goto L_0x0405;
    L_0x0097:
        r12 = (com.google.android.gms.internal.ads.zzbrz) r12;
        r1 = com.google.android.gms.internal.ads.zzbpq.zzb(r7, r4, r11);
        r2 = r11.zzfle;
        r2 = com.google.android.gms.internal.ads.zzbqf.zzax(r2);
        r12.zzbj(r2);
    L_0x00a6:
        if (r1 >= r8) goto L_0x0406;
    L_0x00a8:
        r2 = com.google.android.gms.internal.ads.zzbpq.zza(r7, r1, r11);
        r3 = r11.zzfld;
        if (r9 != r3) goto L_0x0406;
    L_0x00b0:
        r1 = com.google.android.gms.internal.ads.zzbpq.zzb(r7, r2, r11);
        r2 = r11.zzfle;
        r2 = com.google.android.gms.internal.ads.zzbqf.zzax(r2);
        r12.zzbj(r2);
        goto L_0x00a6;
    L_0x00be:
        if (r2 != r14) goto L_0x00e0;
    L_0x00c0:
        r12 = (com.google.android.gms.internal.ads.zzbre) r12;
        r1 = com.google.android.gms.internal.ads.zzbpq.zza(r7, r4, r11);
        r2 = r11.zzfld;
        r2 = r2 + r1;
    L_0x00c9:
        if (r1 >= r2) goto L_0x00d9;
    L_0x00cb:
        r1 = com.google.android.gms.internal.ads.zzbpq.zza(r7, r1, r11);
        r3 = r11.zzfld;
        r3 = com.google.android.gms.internal.ads.zzbqf.zzeu(r3);
        r12.zzfo(r3);
        goto L_0x00c9;
    L_0x00d9:
        if (r1 == r2) goto L_0x0406;
    L_0x00db:
        r1 = com.google.android.gms.internal.ads.zzbrl.zzanc();
        throw r1;
    L_0x00e0:
        if (r2 != 0) goto L_0x0405;
    L_0x00e2:
        r12 = (com.google.android.gms.internal.ads.zzbre) r12;
        r1 = com.google.android.gms.internal.ads.zzbpq.zza(r7, r4, r11);
        r2 = r11.zzfld;
        r2 = com.google.android.gms.internal.ads.zzbqf.zzeu(r2);
        r12.zzfo(r2);
    L_0x00f1:
        if (r1 >= r8) goto L_0x0406;
    L_0x00f3:
        r2 = com.google.android.gms.internal.ads.zzbpq.zza(r7, r1, r11);
        r3 = r11.zzfld;
        if (r9 != r3) goto L_0x0406;
    L_0x00fb:
        r1 = com.google.android.gms.internal.ads.zzbpq.zza(r7, r2, r11);
        r2 = r11.zzfld;
        r2 = com.google.android.gms.internal.ads.zzbqf.zzeu(r2);
        r12.zzfo(r2);
        goto L_0x00f1;
    L_0x0109:
        if (r2 != r14) goto L_0x0110;
    L_0x010b:
        r2 = com.google.android.gms.internal.ads.zzbpq.zza(r7, r4, r12, r11);
        goto L_0x011b;
    L_0x0110:
        if (r2 != 0) goto L_0x0405;
    L_0x0112:
        r2 = r9;
        r3 = r7;
        r5 = r8;
        r6 = r12;
        r7 = r11;
        r2 = com.google.android.gms.internal.ads.zzbpq.zza(r2, r3, r4, r5, r6, r7);
    L_0x011b:
        r1 = (com.google.android.gms.internal.ads.zzbrd) r1;
        r3 = r1.zzfpu;
        r4 = com.google.android.gms.internal.ads.zzbtv.zzaoz();
        if (r3 != r4) goto L_0x0126;
    L_0x0125:
        r3 = 0;
    L_0x0126:
        r4 = r0.zzfs(r10);
        r5 = r0.zzfsw;
        r6 = r23;
        r3 = com.google.android.gms.internal.ads.zzbte.zza(r6, r12, r4, r3, r5);
        r3 = (com.google.android.gms.internal.ads.zzbtv) r3;
        if (r3 == 0) goto L_0x0138;
    L_0x0136:
        r1.zzfpu = r3;
    L_0x0138:
        r1 = r2;
        goto L_0x0406;
    L_0x013b:
        if (r2 != r14) goto L_0x0405;
    L_0x013d:
        r1 = com.google.android.gms.internal.ads.zzbpq.zza(r7, r4, r11);
        r2 = r11.zzfld;
        if (r2 >= 0) goto L_0x014a;
    L_0x0145:
        r1 = com.google.android.gms.internal.ads.zzbrl.zzand();
        throw r1;
    L_0x014a:
        r3 = r7.length;
        r3 = r3 - r1;
        if (r2 <= r3) goto L_0x0153;
    L_0x014e:
        r1 = com.google.android.gms.internal.ads.zzbrl.zzanc();
        throw r1;
    L_0x0153:
        if (r2 != 0) goto L_0x015b;
    L_0x0155:
        r2 = com.google.android.gms.internal.ads.zzbpu.zzfli;
        r12.add(r2);
        goto L_0x0163;
    L_0x015b:
        r3 = com.google.android.gms.internal.ads.zzbpu.zzi(r7, r1, r2);
        r12.add(r3);
        r1 = r1 + r2;
    L_0x0163:
        if (r1 >= r8) goto L_0x0406;
    L_0x0165:
        r2 = com.google.android.gms.internal.ads.zzbpq.zza(r7, r1, r11);
        r3 = r11.zzfld;
        if (r9 != r3) goto L_0x0406;
    L_0x016d:
        r1 = com.google.android.gms.internal.ads.zzbpq.zza(r7, r2, r11);
        r2 = r11.zzfld;
        if (r2 >= 0) goto L_0x017a;
    L_0x0175:
        r1 = com.google.android.gms.internal.ads.zzbrl.zzand();
        throw r1;
    L_0x017a:
        r3 = r7.length;
        r3 = r3 - r1;
        if (r2 <= r3) goto L_0x0183;
    L_0x017e:
        r1 = com.google.android.gms.internal.ads.zzbrl.zzanc();
        throw r1;
    L_0x0183:
        if (r2 != 0) goto L_0x018b;
    L_0x0185:
        r2 = com.google.android.gms.internal.ads.zzbpu.zzfli;
        r12.add(r2);
        goto L_0x0163;
    L_0x018b:
        r3 = com.google.android.gms.internal.ads.zzbpu.zzi(r7, r1, r2);
        r12.add(r3);
        r1 = r1 + r2;
        goto L_0x0163;
    L_0x0194:
        if (r2 != r14) goto L_0x0405;
    L_0x0196:
        r1 = r0.zzfq(r10);
        r2 = r9;
        r3 = r7;
        r5 = r8;
        r6 = r12;
        r7 = r11;
        r1 = zza(r1, r2, r3, r4, r5, r6, r7);
        goto L_0x0406;
    L_0x01a5:
        if (r2 != r14) goto L_0x0405;
    L_0x01a7:
        r1 = 536870912; // 0x20000000 float:1.0842022E-19 double:2.652494739E-315;
        r15 = r26 & r1;
        r1 = (r15 > r5 ? 1 : (r15 == r5 ? 0 : -1));
        if (r1 != 0) goto L_0x01fb;
    L_0x01b0:
        r1 = com.google.android.gms.internal.ads.zzbpq.zza(r7, r4, r11);
        r2 = r11.zzfld;
        if (r2 >= 0) goto L_0x01bd;
    L_0x01b8:
        r1 = com.google.android.gms.internal.ads.zzbrl.zzand();
        throw r1;
    L_0x01bd:
        if (r2 != 0) goto L_0x01c5;
    L_0x01bf:
        r2 = "";
        r12.add(r2);
        goto L_0x01d0;
    L_0x01c5:
        r3 = new java.lang.String;
        r4 = com.google.android.gms.internal.ads.zzbrf.UTF_8;
        r3.<init>(r7, r1, r2, r4);
        r12.add(r3);
        r1 = r1 + r2;
    L_0x01d0:
        if (r1 >= r8) goto L_0x0406;
    L_0x01d2:
        r2 = com.google.android.gms.internal.ads.zzbpq.zza(r7, r1, r11);
        r3 = r11.zzfld;
        if (r9 != r3) goto L_0x0406;
    L_0x01da:
        r1 = com.google.android.gms.internal.ads.zzbpq.zza(r7, r2, r11);
        r2 = r11.zzfld;
        if (r2 >= 0) goto L_0x01e7;
    L_0x01e2:
        r1 = com.google.android.gms.internal.ads.zzbrl.zzand();
        throw r1;
    L_0x01e7:
        if (r2 != 0) goto L_0x01ef;
    L_0x01e9:
        r2 = "";
        r12.add(r2);
        goto L_0x01d0;
    L_0x01ef:
        r3 = new java.lang.String;
        r4 = com.google.android.gms.internal.ads.zzbrf.UTF_8;
        r3.<init>(r7, r1, r2, r4);
        r12.add(r3);
        r1 = r1 + r2;
        goto L_0x01d0;
    L_0x01fb:
        r1 = com.google.android.gms.internal.ads.zzbpq.zza(r7, r4, r11);
        r2 = r11.zzfld;
        if (r2 >= 0) goto L_0x0208;
    L_0x0203:
        r1 = com.google.android.gms.internal.ads.zzbrl.zzand();
        throw r1;
    L_0x0208:
        if (r2 != 0) goto L_0x0210;
    L_0x020a:
        r2 = "";
        r12.add(r2);
        goto L_0x0228;
    L_0x0210:
        r3 = r1 + r2;
        r4 = com.google.android.gms.internal.ads.zzbuc.zzm(r7, r1, r3);
        if (r4 != 0) goto L_0x021d;
    L_0x0218:
        r1 = com.google.android.gms.internal.ads.zzbrl.zzank();
        throw r1;
    L_0x021d:
        r4 = new java.lang.String;
        r5 = com.google.android.gms.internal.ads.zzbrf.UTF_8;
        r4.<init>(r7, r1, r2, r5);
        r12.add(r4);
    L_0x0227:
        r1 = r3;
    L_0x0228:
        if (r1 >= r8) goto L_0x0406;
    L_0x022a:
        r2 = com.google.android.gms.internal.ads.zzbpq.zza(r7, r1, r11);
        r3 = r11.zzfld;
        if (r9 != r3) goto L_0x0406;
    L_0x0232:
        r1 = com.google.android.gms.internal.ads.zzbpq.zza(r7, r2, r11);
        r2 = r11.zzfld;
        if (r2 >= 0) goto L_0x023f;
    L_0x023a:
        r1 = com.google.android.gms.internal.ads.zzbrl.zzand();
        throw r1;
    L_0x023f:
        if (r2 != 0) goto L_0x0247;
    L_0x0241:
        r2 = "";
        r12.add(r2);
        goto L_0x0228;
    L_0x0247:
        r3 = r1 + r2;
        r4 = com.google.android.gms.internal.ads.zzbuc.zzm(r7, r1, r3);
        if (r4 != 0) goto L_0x0254;
    L_0x024f:
        r1 = com.google.android.gms.internal.ads.zzbrl.zzank();
        throw r1;
    L_0x0254:
        r4 = new java.lang.String;
        r5 = com.google.android.gms.internal.ads.zzbrf.UTF_8;
        r4.<init>(r7, r1, r2, r5);
        r12.add(r4);
        goto L_0x0227;
    L_0x025f:
        r1 = 0;
        if (r2 != r14) goto L_0x0285;
    L_0x0262:
        r12 = (com.google.android.gms.internal.ads.zzbps) r12;
        r2 = com.google.android.gms.internal.ads.zzbpq.zza(r7, r4, r11);
        r3 = r11.zzfld;
        r3 = r3 + r2;
    L_0x026b:
        if (r2 >= r3) goto L_0x027e;
    L_0x026d:
        r2 = com.google.android.gms.internal.ads.zzbpq.zzb(r7, r2, r11);
        r8 = r11.zzfle;
        r4 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1));
        if (r4 == 0) goto L_0x0279;
    L_0x0277:
        r4 = r13;
        goto L_0x027a;
    L_0x0279:
        r4 = r1;
    L_0x027a:
        r12.addBoolean(r4);
        goto L_0x026b;
    L_0x027e:
        if (r2 == r3) goto L_0x0138;
    L_0x0280:
        r1 = com.google.android.gms.internal.ads.zzbrl.zzanc();
        throw r1;
    L_0x0285:
        if (r2 != 0) goto L_0x0405;
    L_0x0287:
        r12 = (com.google.android.gms.internal.ads.zzbps) r12;
        r2 = com.google.android.gms.internal.ads.zzbpq.zzb(r7, r4, r11);
        r3 = r11.zzfle;
        r10 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));
        if (r10 == 0) goto L_0x0295;
    L_0x0293:
        r3 = r13;
        goto L_0x0296;
    L_0x0295:
        r3 = r1;
    L_0x0296:
        r12.addBoolean(r3);
    L_0x0299:
        if (r2 >= r8) goto L_0x0138;
    L_0x029b:
        r3 = com.google.android.gms.internal.ads.zzbpq.zza(r7, r2, r11);
        r4 = r11.zzfld;
        if (r9 != r4) goto L_0x0138;
    L_0x02a3:
        r2 = com.google.android.gms.internal.ads.zzbpq.zzb(r7, r3, r11);
        r3 = r11.zzfle;
        r10 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));
        if (r10 == 0) goto L_0x02af;
    L_0x02ad:
        r3 = r13;
        goto L_0x02b0;
    L_0x02af:
        r3 = r1;
    L_0x02b0:
        r12.addBoolean(r3);
        goto L_0x0299;
    L_0x02b4:
        if (r2 != r14) goto L_0x02d2;
    L_0x02b6:
        r12 = (com.google.android.gms.internal.ads.zzbre) r12;
        r1 = com.google.android.gms.internal.ads.zzbpq.zza(r7, r4, r11);
        r2 = r11.zzfld;
        r2 = r2 + r1;
    L_0x02bf:
        if (r1 >= r2) goto L_0x02cb;
    L_0x02c1:
        r3 = com.google.android.gms.internal.ads.zzbpq.zzg(r7, r1);
        r12.zzfo(r3);
        r1 = r1 + 4;
        goto L_0x02bf;
    L_0x02cb:
        if (r1 == r2) goto L_0x0406;
    L_0x02cd:
        r1 = com.google.android.gms.internal.ads.zzbrl.zzanc();
        throw r1;
    L_0x02d2:
        if (r2 != r3) goto L_0x0405;
    L_0x02d4:
        r12 = (com.google.android.gms.internal.ads.zzbre) r12;
        r1 = com.google.android.gms.internal.ads.zzbpq.zzg(r19, r20);
        r12.zzfo(r1);
        r1 = r4 + 4;
    L_0x02df:
        if (r1 >= r8) goto L_0x0406;
    L_0x02e1:
        r2 = com.google.android.gms.internal.ads.zzbpq.zza(r7, r1, r11);
        r3 = r11.zzfld;
        if (r9 != r3) goto L_0x0406;
    L_0x02e9:
        r1 = com.google.android.gms.internal.ads.zzbpq.zzg(r7, r2);
        r12.zzfo(r1);
        r1 = r2 + 4;
        goto L_0x02df;
    L_0x02f3:
        if (r2 != r14) goto L_0x0311;
    L_0x02f5:
        r12 = (com.google.android.gms.internal.ads.zzbrz) r12;
        r1 = com.google.android.gms.internal.ads.zzbpq.zza(r7, r4, r11);
        r2 = r11.zzfld;
        r2 = r2 + r1;
    L_0x02fe:
        if (r1 >= r2) goto L_0x030a;
    L_0x0300:
        r3 = com.google.android.gms.internal.ads.zzbpq.zzh(r7, r1);
        r12.zzbj(r3);
        r1 = r1 + 8;
        goto L_0x02fe;
    L_0x030a:
        if (r1 == r2) goto L_0x0406;
    L_0x030c:
        r1 = com.google.android.gms.internal.ads.zzbrl.zzanc();
        throw r1;
    L_0x0311:
        if (r2 != r13) goto L_0x0405;
    L_0x0313:
        r12 = (com.google.android.gms.internal.ads.zzbrz) r12;
        r1 = com.google.android.gms.internal.ads.zzbpq.zzh(r19, r20);
        r12.zzbj(r1);
        r1 = r4 + 8;
    L_0x031e:
        if (r1 >= r8) goto L_0x0406;
    L_0x0320:
        r2 = com.google.android.gms.internal.ads.zzbpq.zza(r7, r1, r11);
        r3 = r11.zzfld;
        if (r9 != r3) goto L_0x0406;
    L_0x0328:
        r3 = com.google.android.gms.internal.ads.zzbpq.zzh(r7, r2);
        r12.zzbj(r3);
        r1 = r2 + 8;
        goto L_0x031e;
    L_0x0332:
        if (r2 != r14) goto L_0x033a;
    L_0x0334:
        r1 = com.google.android.gms.internal.ads.zzbpq.zza(r7, r4, r12, r11);
        goto L_0x0406;
    L_0x033a:
        if (r2 != 0) goto L_0x0405;
    L_0x033c:
        r1 = r9;
        r2 = r7;
        r3 = r4;
        r4 = r8;
        r5 = r12;
        r6 = r11;
        r1 = com.google.android.gms.internal.ads.zzbpq.zza(r1, r2, r3, r4, r5, r6);
        goto L_0x0406;
    L_0x0348:
        if (r2 != r14) goto L_0x0366;
    L_0x034a:
        r12 = (com.google.android.gms.internal.ads.zzbrz) r12;
        r1 = com.google.android.gms.internal.ads.zzbpq.zza(r7, r4, r11);
        r2 = r11.zzfld;
        r2 = r2 + r1;
    L_0x0353:
        if (r1 >= r2) goto L_0x035f;
    L_0x0355:
        r1 = com.google.android.gms.internal.ads.zzbpq.zzb(r7, r1, r11);
        r3 = r11.zzfle;
        r12.zzbj(r3);
        goto L_0x0353;
    L_0x035f:
        if (r1 == r2) goto L_0x0406;
    L_0x0361:
        r1 = com.google.android.gms.internal.ads.zzbrl.zzanc();
        throw r1;
    L_0x0366:
        if (r2 != 0) goto L_0x0405;
    L_0x0368:
        r12 = (com.google.android.gms.internal.ads.zzbrz) r12;
        r1 = com.google.android.gms.internal.ads.zzbpq.zzb(r7, r4, r11);
        r2 = r11.zzfle;
        r12.zzbj(r2);
    L_0x0373:
        if (r1 >= r8) goto L_0x0406;
    L_0x0375:
        r2 = com.google.android.gms.internal.ads.zzbpq.zza(r7, r1, r11);
        r3 = r11.zzfld;
        if (r9 != r3) goto L_0x0406;
    L_0x037d:
        r1 = com.google.android.gms.internal.ads.zzbpq.zzb(r7, r2, r11);
        r2 = r11.zzfle;
        r12.zzbj(r2);
        goto L_0x0373;
    L_0x0387:
        if (r2 != r14) goto L_0x03a5;
    L_0x0389:
        r12 = (com.google.android.gms.internal.ads.zzbra) r12;
        r1 = com.google.android.gms.internal.ads.zzbpq.zza(r7, r4, r11);
        r2 = r11.zzfld;
        r2 = r2 + r1;
    L_0x0392:
        if (r1 >= r2) goto L_0x039e;
    L_0x0394:
        r3 = com.google.android.gms.internal.ads.zzbpq.zzj(r7, r1);
        r12.zzh(r3);
        r1 = r1 + 4;
        goto L_0x0392;
    L_0x039e:
        if (r1 == r2) goto L_0x0406;
    L_0x03a0:
        r1 = com.google.android.gms.internal.ads.zzbrl.zzanc();
        throw r1;
    L_0x03a5:
        if (r2 != r3) goto L_0x0405;
    L_0x03a7:
        r12 = (com.google.android.gms.internal.ads.zzbra) r12;
        r1 = com.google.android.gms.internal.ads.zzbpq.zzj(r19, r20);
        r12.zzh(r1);
        r1 = r4 + 4;
    L_0x03b2:
        if (r1 >= r8) goto L_0x0406;
    L_0x03b4:
        r2 = com.google.android.gms.internal.ads.zzbpq.zza(r7, r1, r11);
        r3 = r11.zzfld;
        if (r9 != r3) goto L_0x0406;
    L_0x03bc:
        r1 = com.google.android.gms.internal.ads.zzbpq.zzj(r7, r2);
        r12.zzh(r1);
        r1 = r2 + 4;
        goto L_0x03b2;
    L_0x03c6:
        if (r2 != r14) goto L_0x03e4;
    L_0x03c8:
        r12 = (com.google.android.gms.internal.ads.zzbqn) r12;
        r1 = com.google.android.gms.internal.ads.zzbpq.zza(r7, r4, r11);
        r2 = r11.zzfld;
        r2 = r2 + r1;
    L_0x03d1:
        if (r1 >= r2) goto L_0x03dd;
    L_0x03d3:
        r3 = com.google.android.gms.internal.ads.zzbpq.zzi(r7, r1);
        r12.zzd(r3);
        r1 = r1 + 8;
        goto L_0x03d1;
    L_0x03dd:
        if (r1 == r2) goto L_0x0406;
    L_0x03df:
        r1 = com.google.android.gms.internal.ads.zzbrl.zzanc();
        throw r1;
    L_0x03e4:
        if (r2 != r13) goto L_0x0405;
    L_0x03e6:
        r12 = (com.google.android.gms.internal.ads.zzbqn) r12;
        r1 = com.google.android.gms.internal.ads.zzbpq.zzi(r19, r20);
        r12.zzd(r1);
        r1 = r4 + 8;
    L_0x03f1:
        if (r1 >= r8) goto L_0x0406;
    L_0x03f3:
        r2 = com.google.android.gms.internal.ads.zzbpq.zza(r7, r1, r11);
        r3 = r11.zzfld;
        if (r9 != r3) goto L_0x0406;
    L_0x03fb:
        r3 = com.google.android.gms.internal.ads.zzbpq.zzi(r7, r2);
        r12.zzd(r3);
        r1 = r2 + 8;
        goto L_0x03f1;
    L_0x0405:
        r1 = r4;
    L_0x0406:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbsp.zza(java.lang.Object, byte[], int, int, int, int, int, int, long, int, long, com.google.android.gms.internal.ads.zzbpr):int");
    }

    private final <K, V> int zza(T r8, byte[] r9, int r10, int r11, int r12, long r13, com.google.android.gms.internal.ads.zzbpr r15) throws java.io.IOException {
        /*
        r7 = this;
        r0 = zzfsh;
        r12 = r7.zzfr(r12);
        r1 = r0.getObject(r8, r13);
        r2 = r7.zzfsy;
        r2 = r2.zzy(r1);
        if (r2 == 0) goto L_0x0021;
    L_0x0012:
        r2 = r7.zzfsy;
        r2 = r2.zzaa(r12);
        r3 = r7.zzfsy;
        r3.zzc(r2, r1);
        r0.putObject(r8, r13, r2);
        r1 = r2;
    L_0x0021:
        r8 = r7.zzfsy;
        r8 = r8.zzab(r12);
        r12 = r7.zzfsy;
        r12 = r12.zzw(r1);
        r10 = com.google.android.gms.internal.ads.zzbpq.zza(r9, r10, r15);
        r13 = r15.zzfld;
        if (r13 < 0) goto L_0x0095;
    L_0x0035:
        r14 = r11 - r10;
        if (r13 <= r14) goto L_0x003a;
    L_0x0039:
        goto L_0x0095;
    L_0x003a:
        r13 = r13 + r10;
        r14 = r8.zzfsa;
        r0 = r8.zzfsc;
    L_0x003f:
        if (r10 >= r13) goto L_0x008a;
    L_0x0041:
        r1 = r10 + 1;
        r10 = r9[r10];
        if (r10 >= 0) goto L_0x004d;
    L_0x0047:
        r1 = com.google.android.gms.internal.ads.zzbpq.zza(r10, r9, r1, r15);
        r10 = r15.zzfld;
    L_0x004d:
        r2 = r1;
        r1 = r10 >>> 3;
        r3 = r10 & 7;
        switch(r1) {
            case 1: goto L_0x0070;
            case 2: goto L_0x0056;
            default: goto L_0x0055;
        };
    L_0x0055:
        goto L_0x0085;
    L_0x0056:
        r1 = r8.zzfsb;
        r1 = r1.zzapk();
        if (r3 != r1) goto L_0x0085;
    L_0x005e:
        r4 = r8.zzfsb;
        r10 = r8.zzfsc;
        r5 = r10.getClass();
        r1 = r9;
        r3 = r11;
        r6 = r15;
        r10 = zza(r1, r2, r3, r4, r5, r6);
        r0 = r15.zzflf;
        goto L_0x003f;
    L_0x0070:
        r1 = r8.zzfrz;
        r1 = r1.zzapk();
        if (r3 != r1) goto L_0x0085;
    L_0x0078:
        r4 = r8.zzfrz;
        r5 = 0;
        r1 = r9;
        r3 = r11;
        r6 = r15;
        r10 = zza(r1, r2, r3, r4, r5, r6);
        r14 = r15.zzflf;
        goto L_0x003f;
    L_0x0085:
        r10 = com.google.android.gms.internal.ads.zzbpq.zza(r10, r9, r2, r11, r15);
        goto L_0x003f;
    L_0x008a:
        if (r10 == r13) goto L_0x0091;
    L_0x008c:
        r8 = com.google.android.gms.internal.ads.zzbrl.zzanj();
        throw r8;
    L_0x0091:
        r12.put(r14, r0);
        return r13;
    L_0x0095:
        r8 = com.google.android.gms.internal.ads.zzbrl.zzanc();
        throw r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbsp.zza(java.lang.Object, byte[], int, int, int, long, com.google.android.gms.internal.ads.zzbpr):int");
    }

    /* JADX WARNING: Missing block: B:65:0x019b, code skipped:
            r12.putInt(r1, r13, r8);
     */
    /* JADX WARNING: Missing block: B:67:?, code skipped:
            return r4;
     */
    /* JADX WARNING: Missing block: B:71:?, code skipped:
            return r2;
     */
    private final int zza(T r18, byte[] r19, int r20, int r21, int r22, int r23, int r24, int r25, int r26, long r27, int r29, com.google.android.gms.internal.ads.zzbpr r30) throws java.io.IOException {
        /*
        r17 = this;
        r0 = r17;
        r1 = r18;
        r3 = r19;
        r4 = r20;
        r2 = r22;
        r8 = r23;
        r5 = r24;
        r9 = r27;
        r6 = r29;
        r11 = r30;
        r12 = zzfsh;
        r7 = r0.zzfsi;
        r13 = r6 + 2;
        r7 = r7[r13];
        r13 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r7 = r7 & r13;
        r13 = (long) r7;
        r7 = 5;
        r15 = 2;
        switch(r26) {
            case 51: goto L_0x018b;
            case 52: goto L_0x017b;
            case 53: goto L_0x016b;
            case 54: goto L_0x016b;
            case 55: goto L_0x015b;
            case 56: goto L_0x014a;
            case 57: goto L_0x013a;
            case 58: goto L_0x0121;
            case 59: goto L_0x00ee;
            case 60: goto L_0x00c0;
            case 61: goto L_0x00b3;
            case 62: goto L_0x015b;
            case 63: goto L_0x0085;
            case 64: goto L_0x013a;
            case 65: goto L_0x014a;
            case 66: goto L_0x0070;
            case 67: goto L_0x005b;
            case 68: goto L_0x0028;
            default: goto L_0x0026;
        };
    L_0x0026:
        goto L_0x019f;
    L_0x0028:
        r7 = 3;
        if (r5 != r7) goto L_0x019f;
    L_0x002b:
        r2 = r2 & -8;
        r7 = r2 | 4;
        r2 = r0.zzfq(r6);
        r5 = r21;
        r6 = r7;
        r7 = r11;
        r2 = zza(r2, r3, r4, r5, r6, r7);
        r3 = r12.getInt(r1, r13);
        if (r3 != r8) goto L_0x0046;
    L_0x0041:
        r15 = r12.getObject(r1, r9);
        goto L_0x0047;
    L_0x0046:
        r15 = 0;
    L_0x0047:
        if (r15 != 0) goto L_0x0050;
    L_0x0049:
        r3 = r11.zzflf;
        r12.putObject(r1, r9, r3);
        goto L_0x019b;
    L_0x0050:
        r3 = r11.zzflf;
        r3 = com.google.android.gms.internal.ads.zzbrf.zzb(r15, r3);
        r12.putObject(r1, r9, r3);
        goto L_0x019b;
    L_0x005b:
        if (r5 != 0) goto L_0x019f;
    L_0x005d:
        r2 = com.google.android.gms.internal.ads.zzbpq.zzb(r3, r4, r11);
        r3 = r11.zzfle;
        r3 = com.google.android.gms.internal.ads.zzbqf.zzax(r3);
        r3 = java.lang.Long.valueOf(r3);
        r12.putObject(r1, r9, r3);
        goto L_0x019b;
    L_0x0070:
        if (r5 != 0) goto L_0x019f;
    L_0x0072:
        r2 = com.google.android.gms.internal.ads.zzbpq.zza(r3, r4, r11);
        r3 = r11.zzfld;
        r3 = com.google.android.gms.internal.ads.zzbqf.zzeu(r3);
        r3 = java.lang.Integer.valueOf(r3);
        r12.putObject(r1, r9, r3);
        goto L_0x019b;
    L_0x0085:
        if (r5 != 0) goto L_0x019f;
    L_0x0087:
        r3 = com.google.android.gms.internal.ads.zzbpq.zza(r3, r4, r11);
        r4 = r11.zzfld;
        r5 = r0.zzfs(r6);
        if (r5 == 0) goto L_0x00a9;
    L_0x0093:
        r5 = r5.zzcb(r4);
        if (r5 == 0) goto L_0x009a;
    L_0x0099:
        goto L_0x00a9;
    L_0x009a:
        r1 = zzad(r18);
        r4 = (long) r4;
        r4 = java.lang.Long.valueOf(r4);
        r1.zzc(r2, r4);
        r2 = r3;
        goto L_0x01a0;
    L_0x00a9:
        r2 = java.lang.Integer.valueOf(r4);
        r12.putObject(r1, r9, r2);
        r2 = r3;
        goto L_0x019b;
    L_0x00b3:
        if (r5 != r15) goto L_0x019f;
    L_0x00b5:
        r2 = com.google.android.gms.internal.ads.zzbpq.zze(r3, r4, r11);
        r3 = r11.zzflf;
        r12.putObject(r1, r9, r3);
        goto L_0x019b;
    L_0x00c0:
        if (r5 != r15) goto L_0x019f;
    L_0x00c2:
        r2 = r0.zzfq(r6);
        r5 = r21;
        r2 = zza(r2, r3, r4, r5, r11);
        r3 = r12.getInt(r1, r13);
        if (r3 != r8) goto L_0x00d7;
    L_0x00d2:
        r15 = r12.getObject(r1, r9);
        goto L_0x00d8;
    L_0x00d7:
        r15 = 0;
    L_0x00d8:
        if (r15 != 0) goto L_0x00e0;
    L_0x00da:
        r3 = r11.zzflf;
        r12.putObject(r1, r9, r3);
        goto L_0x00e9;
    L_0x00e0:
        r3 = r11.zzflf;
        r3 = com.google.android.gms.internal.ads.zzbrf.zzb(r15, r3);
        r12.putObject(r1, r9, r3);
    L_0x00e9:
        r12.putInt(r1, r13, r8);
        goto L_0x01a0;
    L_0x00ee:
        if (r5 != r15) goto L_0x019f;
    L_0x00f0:
        r2 = com.google.android.gms.internal.ads.zzbpq.zza(r3, r4, r11);
        r4 = r11.zzfld;
        if (r4 != 0) goto L_0x00fe;
    L_0x00f8:
        r3 = "";
        r12.putObject(r1, r9, r3);
        goto L_0x011c;
    L_0x00fe:
        r5 = 536870912; // 0x20000000 float:1.0842022E-19 double:2.652494739E-315;
        r5 = r25 & r5;
        if (r5 == 0) goto L_0x0111;
    L_0x0104:
        r5 = r2 + r4;
        r5 = com.google.android.gms.internal.ads.zzbuc.zzm(r3, r2, r5);
        if (r5 != 0) goto L_0x0111;
    L_0x010c:
        r1 = com.google.android.gms.internal.ads.zzbrl.zzank();
        throw r1;
    L_0x0111:
        r5 = new java.lang.String;
        r6 = com.google.android.gms.internal.ads.zzbrf.UTF_8;
        r5.<init>(r3, r2, r4, r6);
        r12.putObject(r1, r9, r5);
        r2 = r2 + r4;
    L_0x011c:
        r12.putInt(r1, r13, r8);
        goto L_0x01a0;
    L_0x0121:
        if (r5 != 0) goto L_0x019f;
    L_0x0123:
        r2 = com.google.android.gms.internal.ads.zzbpq.zzb(r3, r4, r11);
        r3 = r11.zzfle;
        r5 = 0;
        r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));
        if (r7 == 0) goto L_0x0131;
    L_0x012f:
        r15 = 1;
        goto L_0x0132;
    L_0x0131:
        r15 = 0;
    L_0x0132:
        r3 = java.lang.Boolean.valueOf(r15);
        r12.putObject(r1, r9, r3);
        goto L_0x019b;
    L_0x013a:
        if (r5 != r7) goto L_0x019f;
    L_0x013c:
        r2 = com.google.android.gms.internal.ads.zzbpq.zzg(r19, r20);
        r2 = java.lang.Integer.valueOf(r2);
        r12.putObject(r1, r9, r2);
        r2 = r4 + 4;
        goto L_0x019b;
    L_0x014a:
        r2 = 1;
        if (r5 != r2) goto L_0x019f;
    L_0x014d:
        r2 = com.google.android.gms.internal.ads.zzbpq.zzh(r19, r20);
        r2 = java.lang.Long.valueOf(r2);
        r12.putObject(r1, r9, r2);
        r2 = r4 + 8;
        goto L_0x019b;
    L_0x015b:
        if (r5 != 0) goto L_0x019f;
    L_0x015d:
        r2 = com.google.android.gms.internal.ads.zzbpq.zza(r3, r4, r11);
        r3 = r11.zzfld;
        r3 = java.lang.Integer.valueOf(r3);
        r12.putObject(r1, r9, r3);
        goto L_0x019b;
    L_0x016b:
        if (r5 != 0) goto L_0x019f;
    L_0x016d:
        r2 = com.google.android.gms.internal.ads.zzbpq.zzb(r3, r4, r11);
        r3 = r11.zzfle;
        r3 = java.lang.Long.valueOf(r3);
        r12.putObject(r1, r9, r3);
        goto L_0x019b;
    L_0x017b:
        if (r5 != r7) goto L_0x019f;
    L_0x017d:
        r2 = com.google.android.gms.internal.ads.zzbpq.zzj(r19, r20);
        r2 = java.lang.Float.valueOf(r2);
        r12.putObject(r1, r9, r2);
        r2 = r4 + 4;
        goto L_0x019b;
    L_0x018b:
        r2 = 1;
        if (r5 != r2) goto L_0x019f;
    L_0x018e:
        r2 = com.google.android.gms.internal.ads.zzbpq.zzi(r19, r20);
        r2 = java.lang.Double.valueOf(r2);
        r12.putObject(r1, r9, r2);
        r2 = r4 + 8;
    L_0x019b:
        r12.putInt(r1, r13, r8);
        goto L_0x01a0;
    L_0x019f:
        r2 = r4;
    L_0x01a0:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbsp.zza(java.lang.Object, byte[], int, int, int, int, int, int, int, long, int, com.google.android.gms.internal.ads.zzbpr):int");
    }

    private final zzbtc zzfq(int i) {
        i = (i / 3) << 1;
        zzbtc zzbtc = (zzbtc) this.zzfsj[i];
        if (zzbtc != null) {
            return zzbtc;
        }
        zzbtc = zzbsy.zzaog().zzf((Class) this.zzfsj[i + 1]);
        this.zzfsj[i] = zzbtc;
        return zzbtc;
    }

    private final Object zzfr(int i) {
        return this.zzfsj[(i / 3) << 1];
    }

    private final zzbri zzfs(int i) {
        return (zzbri) this.zzfsj[((i / 3) << 1) + 1];
    }

    /* JADX WARNING: Removed duplicated region for block: B:142:0x044d  */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x0444  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x0456 A:{LOOP_END, LOOP:1: B:144:0x0452->B:146:0x0456} */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x0467  */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x0477  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x046e  */
    /* JADX WARNING: Missing block: B:36:0x0109, code skipped:
            r3 = r21;
     */
    /* JADX WARNING: Missing block: B:51:0x017c, code skipped:
            r2 = r11;
            r3 = r13;
            r1 = r17;
     */
    /* JADX WARNING: Missing block: B:52:0x0180, code skipped:
            r7 = r25;
            r11 = r36;
            r13 = r35;
     */
    /* JADX WARNING: Missing block: B:53:0x0188, code skipped:
            r3 = r2;
            r26 = r13;
     */
    /* JADX WARNING: Missing block: B:54:0x018b, code skipped:
            r13 = r35;
     */
    /* JADX WARNING: Missing block: B:79:0x022e, code skipped:
            r2 = r11;
            r1 = r17;
            r7 = r25;
     */
    /* JADX WARNING: Missing block: B:83:0x025a, code skipped:
            r26 = r3;
            r3 = r2;
     */
    /* JADX WARNING: Missing block: B:96:0x02d3, code skipped:
            r2 = r11;
            r1 = r17;
     */
    /* JADX WARNING: Missing block: B:99:0x02de, code skipped:
            r2 = r3;
            r18 = r6;
            r29 = r10;
            r19 = r11;
            r14 = r15;
     */
    private final int zza(T r32, byte[] r33, int r34, int r35, int r36, com.google.android.gms.internal.ads.zzbpr r37) throws java.io.IOException {
        /*
        r31 = this;
        r15 = r31;
        r14 = r32;
        r12 = r33;
        r13 = r35;
        r11 = r36;
        r9 = r37;
        r10 = zzfsh;
        r16 = 0;
        r0 = r34;
        r2 = r16;
        r3 = r2;
        r6 = r3;
        r1 = -1;
        r7 = -1;
    L_0x0018:
        if (r0 >= r13) goto L_0x0433;
    L_0x001a:
        r3 = r0 + 1;
        r0 = r12[r0];
        if (r0 >= 0) goto L_0x0029;
    L_0x0020:
        r0 = com.google.android.gms.internal.ads.zzbpq.zza(r0, r12, r3, r9);
        r3 = r9.zzfld;
        r4 = r0;
        r5 = r3;
        goto L_0x002b;
    L_0x0029:
        r5 = r0;
        r4 = r3;
    L_0x002b:
        r3 = r5 >>> 3;
        r0 = r5 & 7;
        r8 = 3;
        if (r3 <= r1) goto L_0x003a;
    L_0x0032:
        r2 = r2 / r8;
        r1 = r15.zzai(r3, r2);
    L_0x0037:
        r2 = r1;
        r1 = -1;
        goto L_0x003f;
    L_0x003a:
        r1 = r15.zzfw(r3);
        goto L_0x0037;
    L_0x003f:
        if (r2 != r1) goto L_0x0051;
    L_0x0041:
        r17 = r3;
        r2 = r4;
        r18 = r6;
        r25 = r7;
        r29 = r10;
        r6 = r11;
        r14 = r15;
        r19 = r16;
        r7 = r5;
        goto L_0x03f4;
    L_0x0051:
        r1 = r15.zzfsi;
        r18 = r2 + 1;
        r1 = r1[r18];
        r18 = 267386880; // 0xff00000 float:2.3665827E-29 double:1.321066716E-315;
        r18 = r1 & r18;
        r8 = r18 >>> 20;
        r18 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r20 = r5;
        r5 = r1 & r18;
        r21 = r4;
        r4 = (long) r5;
        r22 = r4;
        r4 = 17;
        if (r8 > r4) goto L_0x02e8;
    L_0x006d:
        r4 = r15.zzfsi;
        r24 = r2 + 2;
        r4 = r4[r24];
        r24 = r4 >>> 20;
        r5 = 1;
        r24 = r5 << r24;
        r4 = r4 & r18;
        if (r4 == r7) goto L_0x0089;
    L_0x007c:
        r11 = -1;
        if (r7 == r11) goto L_0x0083;
    L_0x007f:
        r11 = (long) r7;
        r10.putInt(r14, r11, r6);
    L_0x0083:
        r6 = (long) r4;
        r6 = r10.getInt(r14, r6);
        r7 = r4;
    L_0x0089:
        r4 = 5;
        switch(r8) {
            case 0: goto L_0x02b9;
            case 1: goto L_0x029e;
            case 2: goto L_0x027b;
            case 3: goto L_0x027b;
            case 4: goto L_0x025f;
            case 5: goto L_0x0235;
            case 6: goto L_0x0214;
            case 7: goto L_0x01f0;
            case 8: goto L_0x01ca;
            case 9: goto L_0x018f;
            case 10: goto L_0x0161;
            case 11: goto L_0x025f;
            case 12: goto L_0x012c;
            case 13: goto L_0x0214;
            case 14: goto L_0x0235;
            case 15: goto L_0x010d;
            case 16: goto L_0x00de;
            case 17: goto L_0x009a;
            default: goto L_0x008d;
        };
    L_0x008d:
        r11 = r2;
        r17 = r3;
        r25 = r7;
        r26 = r20;
        r3 = r21;
        r12 = r33;
        goto L_0x02de;
    L_0x009a:
        r8 = 3;
        if (r0 != r8) goto L_0x00d4;
    L_0x009d:
        r0 = r3 << 3;
        r4 = r0 | 4;
        r0 = r15.zzfq(r2);
        r12 = r33;
        r1 = r12;
        r11 = r2;
        r2 = r21;
        r17 = r3;
        r3 = r13;
        r25 = r7;
        r7 = r22;
        r13 = r20;
        r5 = r9;
        r0 = zza(r0, r1, r2, r3, r4, r5);
        r1 = r6 & r24;
        if (r1 != 0) goto L_0x00c3;
    L_0x00bd:
        r1 = r9.zzflf;
        r10.putObject(r14, r7, r1);
        goto L_0x00d0;
    L_0x00c3:
        r1 = r10.getObject(r14, r7);
        r2 = r9.zzflf;
        r1 = com.google.android.gms.internal.ads.zzbrf.zzb(r1, r2);
        r10.putObject(r14, r7, r1);
    L_0x00d0:
        r6 = r6 | r24;
        goto L_0x017c;
    L_0x00d4:
        r11 = r2;
        r17 = r3;
        r25 = r7;
        r12 = r33;
        r26 = r20;
        goto L_0x0109;
    L_0x00de:
        r11 = r2;
        r17 = r3;
        r25 = r7;
        r13 = r20;
        r7 = r22;
        r12 = r33;
        if (r0 != 0) goto L_0x0107;
    L_0x00eb:
        r2 = r21;
        r18 = com.google.android.gms.internal.ads.zzbpq.zzb(r12, r2, r9);
        r0 = r9.zzfle;
        r4 = com.google.android.gms.internal.ads.zzbqf.zzax(r0);
        r0 = r10;
        r1 = r14;
        r2 = r7;
        r0.putLong(r1, r2, r4);
        r6 = r6 | r24;
        r2 = r11;
        r3 = r13;
        r1 = r17;
        r0 = r18;
        goto L_0x0180;
    L_0x0107:
        r26 = r13;
    L_0x0109:
        r3 = r21;
        goto L_0x018b;
    L_0x010d:
        r11 = r2;
        r17 = r3;
        r25 = r7;
        r13 = r20;
        r2 = r21;
        r7 = r22;
        r12 = r33;
        if (r0 != 0) goto L_0x0188;
    L_0x011c:
        r0 = com.google.android.gms.internal.ads.zzbpq.zza(r12, r2, r9);
        r1 = r9.zzfld;
        r1 = com.google.android.gms.internal.ads.zzbqf.zzeu(r1);
        r10.putInt(r14, r7, r1);
        r6 = r6 | r24;
        goto L_0x017c;
    L_0x012c:
        r11 = r2;
        r17 = r3;
        r25 = r7;
        r13 = r20;
        r2 = r21;
        r7 = r22;
        r12 = r33;
        if (r0 != 0) goto L_0x0188;
    L_0x013b:
        r0 = com.google.android.gms.internal.ads.zzbpq.zza(r12, r2, r9);
        r1 = r9.zzfld;
        r2 = r15.zzfs(r11);
        if (r2 == 0) goto L_0x015b;
    L_0x0147:
        r2 = r2.zzcb(r1);
        if (r2 == 0) goto L_0x014e;
    L_0x014d:
        goto L_0x015b;
    L_0x014e:
        r2 = zzad(r32);
        r3 = (long) r1;
        r1 = java.lang.Long.valueOf(r3);
        r2.zzc(r13, r1);
        goto L_0x017c;
    L_0x015b:
        r10.putInt(r14, r7, r1);
        r6 = r6 | r24;
        goto L_0x017c;
    L_0x0161:
        r11 = r2;
        r17 = r3;
        r25 = r7;
        r13 = r20;
        r2 = r21;
        r7 = r22;
        r1 = 2;
        r12 = r33;
        if (r0 != r1) goto L_0x0188;
    L_0x0171:
        r0 = com.google.android.gms.internal.ads.zzbpq.zze(r12, r2, r9);
        r1 = r9.zzflf;
        r10.putObject(r14, r7, r1);
        r6 = r6 | r24;
    L_0x017c:
        r2 = r11;
        r3 = r13;
        r1 = r17;
    L_0x0180:
        r7 = r25;
        r11 = r36;
        r13 = r35;
        goto L_0x0018;
    L_0x0188:
        r3 = r2;
        r26 = r13;
    L_0x018b:
        r13 = r35;
        goto L_0x02de;
    L_0x018f:
        r11 = r2;
        r17 = r3;
        r25 = r7;
        r13 = r20;
        r2 = r21;
        r7 = r22;
        r1 = 2;
        r12 = r33;
        if (r0 != r1) goto L_0x01c5;
    L_0x019f:
        r0 = r15.zzfq(r11);
        r3 = r13;
        r13 = r35;
        r0 = zza(r0, r12, r2, r13, r9);
        r1 = r6 & r24;
        if (r1 != 0) goto L_0x01b4;
    L_0x01ae:
        r1 = r9.zzflf;
        r10.putObject(r14, r7, r1);
        goto L_0x01c1;
    L_0x01b4:
        r1 = r10.getObject(r14, r7);
        r2 = r9.zzflf;
        r1 = com.google.android.gms.internal.ads.zzbrf.zzb(r1, r2);
        r10.putObject(r14, r7, r1);
    L_0x01c1:
        r6 = r6 | r24;
        goto L_0x022e;
    L_0x01c5:
        r3 = r13;
        r13 = r35;
        goto L_0x025a;
    L_0x01ca:
        r11 = r2;
        r17 = r3;
        r25 = r7;
        r3 = r20;
        r2 = r21;
        r7 = r22;
        r4 = 2;
        r12 = r33;
        if (r0 != r4) goto L_0x025a;
    L_0x01da:
        r0 = 536870912; // 0x20000000 float:1.0842022E-19 double:2.652494739E-315;
        r0 = r0 & r1;
        if (r0 != 0) goto L_0x01e4;
    L_0x01df:
        r0 = com.google.android.gms.internal.ads.zzbpq.zzc(r12, r2, r9);
        goto L_0x01e8;
    L_0x01e4:
        r0 = com.google.android.gms.internal.ads.zzbpq.zzd(r12, r2, r9);
    L_0x01e8:
        r1 = r9.zzflf;
        r10.putObject(r14, r7, r1);
        r6 = r6 | r24;
        goto L_0x022e;
    L_0x01f0:
        r11 = r2;
        r17 = r3;
        r25 = r7;
        r3 = r20;
        r2 = r21;
        r7 = r22;
        r12 = r33;
        if (r0 != 0) goto L_0x025a;
    L_0x01ff:
        r0 = com.google.android.gms.internal.ads.zzbpq.zzb(r12, r2, r9);
        r1 = r9.zzfle;
        r18 = 0;
        r4 = (r1 > r18 ? 1 : (r1 == r18 ? 0 : -1));
        if (r4 == 0) goto L_0x020c;
    L_0x020b:
        goto L_0x020e;
    L_0x020c:
        r5 = r16;
    L_0x020e:
        com.google.android.gms.internal.ads.zzbua.zza(r14, r7, r5);
        r6 = r6 | r24;
        goto L_0x022e;
    L_0x0214:
        r11 = r2;
        r17 = r3;
        r25 = r7;
        r3 = r20;
        r2 = r21;
        r7 = r22;
        r12 = r33;
        if (r0 != r4) goto L_0x025a;
    L_0x0223:
        r0 = com.google.android.gms.internal.ads.zzbpq.zzg(r12, r2);
        r10.putInt(r14, r7, r0);
        r0 = r2 + 4;
        r6 = r6 | r24;
    L_0x022e:
        r2 = r11;
        r1 = r17;
        r7 = r25;
        goto L_0x02da;
    L_0x0235:
        r11 = r2;
        r17 = r3;
        r25 = r7;
        r3 = r20;
        r2 = r21;
        r7 = r22;
        r12 = r33;
        if (r0 != r5) goto L_0x025a;
    L_0x0244:
        r4 = com.google.android.gms.internal.ads.zzbpq.zzh(r12, r2);
        r0 = r10;
        r1 = r14;
        r18 = r2;
        r26 = r3;
        r2 = r7;
        r7 = r18;
        r0.putLong(r1, r2, r4);
        r0 = r7 + 8;
        r6 = r6 | r24;
        goto L_0x02d3;
    L_0x025a:
        r26 = r3;
        r3 = r2;
        goto L_0x02de;
    L_0x025f:
        r11 = r2;
        r17 = r3;
        r25 = r7;
        r26 = r20;
        r3 = r21;
        r7 = r22;
        r12 = r33;
        if (r0 != 0) goto L_0x02de;
    L_0x026e:
        r0 = com.google.android.gms.internal.ads.zzbpq.zza(r12, r3, r9);
        r1 = r9.zzfld;
        r10.putInt(r14, r7, r1);
        r6 = r6 | r24;
        goto L_0x02d3;
    L_0x027b:
        r11 = r2;
        r17 = r3;
        r25 = r7;
        r26 = r20;
        r3 = r21;
        r7 = r22;
        r12 = r33;
        if (r0 != 0) goto L_0x02de;
    L_0x028a:
        r18 = com.google.android.gms.internal.ads.zzbpq.zzb(r12, r3, r9);
        r4 = r9.zzfle;
        r0 = r10;
        r1 = r14;
        r2 = r7;
        r0.putLong(r1, r2, r4);
        r6 = r6 | r24;
        r2 = r11;
        r1 = r17;
        r0 = r18;
        goto L_0x02d6;
    L_0x029e:
        r11 = r2;
        r17 = r3;
        r25 = r7;
        r26 = r20;
        r3 = r21;
        r7 = r22;
        r12 = r33;
        if (r0 != r4) goto L_0x02de;
    L_0x02ad:
        r0 = com.google.android.gms.internal.ads.zzbpq.zzj(r12, r3);
        com.google.android.gms.internal.ads.zzbua.zza(r14, r7, r0);
        r0 = r3 + 4;
        r6 = r6 | r24;
        goto L_0x02d3;
    L_0x02b9:
        r11 = r2;
        r17 = r3;
        r25 = r7;
        r26 = r20;
        r3 = r21;
        r7 = r22;
        r12 = r33;
        if (r0 != r5) goto L_0x02de;
    L_0x02c8:
        r0 = com.google.android.gms.internal.ads.zzbpq.zzi(r12, r3);
        com.google.android.gms.internal.ads.zzbua.zza(r14, r7, r0);
        r0 = r3 + 8;
        r6 = r6 | r24;
    L_0x02d3:
        r2 = r11;
        r1 = r17;
    L_0x02d6:
        r7 = r25;
        r3 = r26;
    L_0x02da:
        r11 = r36;
        goto L_0x0018;
    L_0x02de:
        r2 = r3;
        r18 = r6;
        r29 = r10;
        r19 = r11;
        r14 = r15;
        goto L_0x03d0;
    L_0x02e8:
        r11 = r2;
        r17 = r3;
        r25 = r7;
        r5 = r8;
        r26 = r20;
        r3 = r21;
        r7 = r22;
        r2 = 27;
        if (r5 != r2) goto L_0x0339;
    L_0x02f8:
        r2 = 2;
        if (r0 != r2) goto L_0x032f;
    L_0x02fb:
        r0 = r10.getObject(r14, r7);
        r0 = (com.google.android.gms.internal.ads.zzbrk) r0;
        r1 = r0.zzaki();
        if (r1 != 0) goto L_0x0319;
    L_0x0307:
        r1 = r0.size();
        if (r1 != 0) goto L_0x0310;
    L_0x030d:
        r1 = 10;
        goto L_0x0312;
    L_0x0310:
        r1 = r1 << 1;
    L_0x0312:
        r0 = r0.zzel(r1);
        r10.putObject(r14, r7, r0);
    L_0x0319:
        r5 = r0;
        r0 = r15.zzfq(r11);
        r1 = r26;
        r2 = r12;
        r4 = r13;
        r18 = r6;
        r6 = r9;
        r0 = zza(r0, r1, r2, r3, r4, r5, r6);
        r2 = r11;
        r1 = r17;
        r6 = r18;
        goto L_0x02d6;
    L_0x032f:
        r18 = r6;
        r29 = r10;
        r19 = r11;
        r14 = r15;
        r15 = r3;
        goto L_0x03cf;
    L_0x0339:
        r18 = r6;
        r2 = 49;
        if (r5 > r2) goto L_0x038d;
    L_0x033f:
        r1 = (long) r1;
        r6 = r0;
        r0 = r15;
        r19 = r1;
        r1 = r14;
        r2 = r12;
        r4 = r3;
        r15 = r4;
        r4 = r13;
        r21 = r5;
        r5 = r26;
        r27 = r6;
        r6 = r17;
        r22 = r7;
        r7 = r27;
        r28 = r21;
        r8 = r11;
        r29 = r10;
        r9 = r19;
        r19 = r11;
        r11 = r28;
        r12 = r22;
        r14 = r37;
        r0 = r0.zza(r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r14);
        if (r0 != r15) goto L_0x0373;
    L_0x036a:
        r2 = r0;
        r7 = r26;
        r6 = r36;
        r14 = r31;
        goto L_0x03f4;
    L_0x0373:
        r14 = r32;
        r12 = r33;
        r13 = r35;
        r9 = r37;
        r1 = r17;
        r6 = r18;
        r2 = r19;
        r7 = r25;
        r3 = r26;
        r10 = r29;
        r11 = r36;
        r15 = r31;
        goto L_0x0018;
    L_0x038d:
        r27 = r0;
        r15 = r3;
        r28 = r5;
        r22 = r7;
        r29 = r10;
        r19 = r11;
        r0 = 50;
        r9 = r28;
        if (r9 != r0) goto L_0x03d5;
    L_0x039e:
        r7 = r27;
        r0 = 2;
        if (r7 != r0) goto L_0x03cd;
    L_0x03a3:
        r14 = r31;
        r0 = r14;
        r1 = r32;
        r2 = r33;
        r3 = r15;
        r4 = r35;
        r5 = r19;
        r6 = r22;
        r8 = r37;
        r0 = r0.zza(r1, r2, r3, r4, r5, r6, r8);
        if (r0 != r15) goto L_0x03ba;
    L_0x03b9:
        goto L_0x03f2;
    L_0x03ba:
        r12 = r33;
        r13 = r35;
        r9 = r37;
        r15 = r14;
        r1 = r17;
        r6 = r18;
        r2 = r19;
        r7 = r25;
        r3 = r26;
        goto L_0x042e;
    L_0x03cd:
        r14 = r31;
    L_0x03cf:
        r2 = r15;
    L_0x03d0:
        r7 = r26;
        r6 = r36;
        goto L_0x03f4;
    L_0x03d5:
        r7 = r27;
        r14 = r31;
        r0 = r14;
        r8 = r1;
        r1 = r32;
        r2 = r33;
        r3 = r15;
        r4 = r35;
        r5 = r26;
        r6 = r17;
        r10 = r22;
        r12 = r19;
        r13 = r37;
        r0 = r0.zza(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12, r13);
        if (r0 != r15) goto L_0x041c;
    L_0x03f2:
        r2 = r0;
        goto L_0x03d0;
    L_0x03f4:
        if (r7 != r6) goto L_0x03f8;
    L_0x03f6:
        if (r6 != 0) goto L_0x043d;
    L_0x03f8:
        r0 = r7;
        r1 = r33;
        r3 = r35;
        r4 = r32;
        r5 = r37;
        r0 = zza(r0, r1, r2, r3, r4, r5);
        r12 = r33;
        r13 = r35;
        r9 = r37;
        r11 = r6;
        r3 = r7;
        r15 = r14;
        r1 = r17;
        r6 = r18;
        r2 = r19;
        r7 = r25;
        r10 = r29;
    L_0x0418:
        r14 = r32;
        goto L_0x0018;
    L_0x041c:
        r7 = r26;
        r12 = r33;
        r13 = r35;
        r9 = r37;
        r3 = r7;
        r15 = r14;
        r1 = r17;
        r6 = r18;
        r2 = r19;
        r7 = r25;
    L_0x042e:
        r10 = r29;
        r11 = r36;
        goto L_0x0418;
    L_0x0433:
        r18 = r6;
        r25 = r7;
        r29 = r10;
        r6 = r11;
        r14 = r15;
        r2 = r0;
        r7 = r3;
    L_0x043d:
        r1 = r18;
        r0 = r25;
        r3 = -1;
        if (r0 == r3) goto L_0x044d;
    L_0x0444:
        r3 = (long) r0;
        r0 = r32;
        r5 = r29;
        r5.putInt(r0, r3, r1);
        goto L_0x044f;
    L_0x044d:
        r0 = r32;
    L_0x044f:
        r1 = 0;
        r3 = r14.zzfss;
    L_0x0452:
        r4 = r14.zzfst;
        if (r3 >= r4) goto L_0x0465;
    L_0x0456:
        r4 = r14.zzfsr;
        r4 = r4[r3];
        r5 = r14.zzfsw;
        r1 = r14.zza(r0, r4, r1, r5);
        r1 = (com.google.android.gms.internal.ads.zzbtv) r1;
        r3 = r3 + 1;
        goto L_0x0452;
    L_0x0465:
        if (r1 == 0) goto L_0x046c;
    L_0x0467:
        r3 = r14.zzfsw;
        r3.zzg(r0, r1);
    L_0x046c:
        if (r6 != 0) goto L_0x0477;
    L_0x046e:
        r0 = r35;
        if (r2 == r0) goto L_0x047e;
    L_0x0472:
        r0 = com.google.android.gms.internal.ads.zzbrl.zzanj();
        throw r0;
    L_0x0477:
        r0 = r35;
        if (r2 > r0) goto L_0x047f;
    L_0x047b:
        if (r7 == r6) goto L_0x047e;
    L_0x047d:
        goto L_0x047f;
    L_0x047e:
        return r2;
    L_0x047f:
        r0 = com.google.android.gms.internal.ads.zzbrl.zzanj();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbsp.zza(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.ads.zzbpr):int");
    }

    /* JADX WARNING: Missing block: B:54:0x0116, code skipped:
            r2 = r4;
            r1 = r7;
     */
    /* JADX WARNING: Missing block: B:64:0x0148, code skipped:
            r0 = r6;
     */
    /* JADX WARNING: Missing block: B:71:0x0166, code skipped:
            r1 = r7;
            r2 = r10;
     */
    /* JADX WARNING: Missing block: B:73:0x016b, code skipped:
            r25 = r7;
            r14 = r8;
            r18 = r9;
            r19 = r10;
     */
    /* JADX WARNING: Missing block: B:96:0x0220, code skipped:
            if (r0 == r14) goto L_0x01de;
     */
    /* JADX WARNING: Missing block: B:100:0x0241, code skipped:
            if (r0 == r14) goto L_0x01de;
     */
    public final void zza(T r29, byte[] r30, int r31, int r32, com.google.android.gms.internal.ads.zzbpr r33) throws java.io.IOException {
        /*
        r28 = this;
        r15 = r28;
        r14 = r29;
        r12 = r30;
        r13 = r32;
        r11 = r33;
        r0 = r15.zzfsp;
        if (r0 == 0) goto L_0x026f;
    L_0x000e:
        r9 = zzfsh;
        r10 = -1;
        r16 = 0;
        r0 = r31;
        r1 = r10;
        r2 = r16;
    L_0x0018:
        if (r0 >= r13) goto L_0x0266;
    L_0x001a:
        r3 = r0 + 1;
        r0 = r12[r0];
        if (r0 >= 0) goto L_0x002a;
    L_0x0020:
        r0 = com.google.android.gms.internal.ads.zzbpq.zza(r0, r12, r3, r11);
        r3 = r11.zzfld;
        r8 = r0;
        r17 = r3;
        goto L_0x002d;
    L_0x002a:
        r17 = r0;
        r8 = r3;
    L_0x002d:
        r7 = r17 >>> 3;
        r6 = r17 & 7;
        if (r7 <= r1) goto L_0x003b;
    L_0x0033:
        r2 = r2 / 3;
        r0 = r15.zzai(r7, r2);
    L_0x0039:
        r4 = r0;
        goto L_0x0040;
    L_0x003b:
        r0 = r15.zzfw(r7);
        goto L_0x0039;
    L_0x0040:
        if (r4 != r10) goto L_0x004d;
    L_0x0042:
        r25 = r7;
        r2 = r8;
        r18 = r9;
        r27 = r10;
        r19 = r16;
        goto L_0x0244;
    L_0x004d:
        r0 = r15.zzfsi;
        r1 = r4 + 1;
        r5 = r0[r1];
        r0 = 267386880; // 0xff00000 float:2.3665827E-29 double:1.321066716E-315;
        r0 = r0 & r5;
        r3 = r0 >>> 20;
        r0 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r0 = r0 & r5;
        r1 = (long) r0;
        r0 = 17;
        r10 = 2;
        if (r3 > r0) goto L_0x0174;
    L_0x0062:
        r0 = 1;
        switch(r3) {
            case 0: goto L_0x0159;
            case 1: goto L_0x014a;
            case 2: goto L_0x0139;
            case 3: goto L_0x0139;
            case 4: goto L_0x012b;
            case 5: goto L_0x011a;
            case 6: goto L_0x0109;
            case 7: goto L_0x00f2;
            case 8: goto L_0x00db;
            case 9: goto L_0x00ba;
            case 10: goto L_0x00ad;
            case 11: goto L_0x012b;
            case 12: goto L_0x009e;
            case 13: goto L_0x0109;
            case 14: goto L_0x011a;
            case 15: goto L_0x008b;
            case 16: goto L_0x0071;
            default: goto L_0x0066;
        };
    L_0x0066:
        r19 = r4;
        r25 = r7;
        r14 = r8;
        r18 = r9;
    L_0x006d:
        r27 = -1;
        goto L_0x0224;
    L_0x0071:
        if (r6 != 0) goto L_0x0066;
    L_0x0073:
        r6 = com.google.android.gms.internal.ads.zzbpq.zzb(r12, r8, r11);
        r20 = r1;
        r0 = r11.zzfle;
        r22 = com.google.android.gms.internal.ads.zzbqf.zzax(r0);
        r0 = r9;
        r2 = r20;
        r1 = r14;
        r10 = r4;
        r4 = r22;
        r0.putLong(r1, r2, r4);
        goto L_0x0148;
    L_0x008b:
        r2 = r1;
        r10 = r4;
        if (r6 != 0) goto L_0x016b;
    L_0x008f:
        r0 = com.google.android.gms.internal.ads.zzbpq.zza(r12, r8, r11);
        r1 = r11.zzfld;
        r1 = com.google.android.gms.internal.ads.zzbqf.zzeu(r1);
        r9.putInt(r14, r2, r1);
        goto L_0x0166;
    L_0x009e:
        r2 = r1;
        r10 = r4;
        if (r6 != 0) goto L_0x016b;
    L_0x00a2:
        r0 = com.google.android.gms.internal.ads.zzbpq.zza(r12, r8, r11);
        r1 = r11.zzfld;
        r9.putInt(r14, r2, r1);
        goto L_0x0166;
    L_0x00ad:
        r2 = r1;
        if (r6 != r10) goto L_0x0066;
    L_0x00b0:
        r0 = com.google.android.gms.internal.ads.zzbpq.zze(r12, r8, r11);
        r1 = r11.zzflf;
        r9.putObject(r14, r2, r1);
        goto L_0x0116;
    L_0x00ba:
        r2 = r1;
        if (r6 != r10) goto L_0x0066;
    L_0x00bd:
        r0 = r15.zzfq(r4);
        r0 = zza(r0, r12, r8, r13, r11);
        r1 = r9.getObject(r14, r2);
        if (r1 != 0) goto L_0x00d1;
    L_0x00cb:
        r1 = r11.zzflf;
        r9.putObject(r14, r2, r1);
        goto L_0x0116;
    L_0x00d1:
        r5 = r11.zzflf;
        r1 = com.google.android.gms.internal.ads.zzbrf.zzb(r1, r5);
        r9.putObject(r14, r2, r1);
        goto L_0x0116;
    L_0x00db:
        r2 = r1;
        if (r6 != r10) goto L_0x0066;
    L_0x00de:
        r0 = 536870912; // 0x20000000 float:1.0842022E-19 double:2.652494739E-315;
        r0 = r0 & r5;
        if (r0 != 0) goto L_0x00e8;
    L_0x00e3:
        r0 = com.google.android.gms.internal.ads.zzbpq.zzc(r12, r8, r11);
        goto L_0x00ec;
    L_0x00e8:
        r0 = com.google.android.gms.internal.ads.zzbpq.zzd(r12, r8, r11);
    L_0x00ec:
        r1 = r11.zzflf;
        r9.putObject(r14, r2, r1);
        goto L_0x0116;
    L_0x00f2:
        r2 = r1;
        if (r6 != 0) goto L_0x0066;
    L_0x00f5:
        r1 = com.google.android.gms.internal.ads.zzbpq.zzb(r12, r8, r11);
        r5 = r11.zzfle;
        r19 = 0;
        r8 = (r5 > r19 ? 1 : (r5 == r19 ? 0 : -1));
        if (r8 == 0) goto L_0x0102;
    L_0x0101:
        goto L_0x0104;
    L_0x0102:
        r0 = r16;
    L_0x0104:
        com.google.android.gms.internal.ads.zzbua.zza(r14, r2, r0);
        r0 = r1;
        goto L_0x0116;
    L_0x0109:
        r2 = r1;
        r0 = 5;
        if (r6 != r0) goto L_0x0066;
    L_0x010d:
        r0 = com.google.android.gms.internal.ads.zzbpq.zzg(r12, r8);
        r9.putInt(r14, r2, r0);
        r0 = r8 + 4;
    L_0x0116:
        r2 = r4;
        r1 = r7;
        goto L_0x0168;
    L_0x011a:
        r2 = r1;
        if (r6 != r0) goto L_0x0066;
    L_0x011d:
        r5 = com.google.android.gms.internal.ads.zzbpq.zzh(r12, r8);
        r0 = r9;
        r1 = r14;
        r10 = r4;
        r4 = r5;
        r0.putLong(r1, r2, r4);
        r0 = r8 + 8;
        goto L_0x0166;
    L_0x012b:
        r2 = r1;
        r10 = r4;
        if (r6 != 0) goto L_0x016b;
    L_0x012f:
        r0 = com.google.android.gms.internal.ads.zzbpq.zza(r12, r8, r11);
        r1 = r11.zzfld;
        r9.putInt(r14, r2, r1);
        goto L_0x0166;
    L_0x0139:
        r2 = r1;
        r10 = r4;
        if (r6 != 0) goto L_0x016b;
    L_0x013d:
        r6 = com.google.android.gms.internal.ads.zzbpq.zzb(r12, r8, r11);
        r4 = r11.zzfle;
        r0 = r9;
        r1 = r14;
        r0.putLong(r1, r2, r4);
    L_0x0148:
        r0 = r6;
        goto L_0x0166;
    L_0x014a:
        r2 = r1;
        r10 = r4;
        r0 = 5;
        if (r6 != r0) goto L_0x016b;
    L_0x014f:
        r0 = com.google.android.gms.internal.ads.zzbpq.zzj(r12, r8);
        com.google.android.gms.internal.ads.zzbua.zza(r14, r2, r0);
        r0 = r8 + 4;
        goto L_0x0166;
    L_0x0159:
        r2 = r1;
        r10 = r4;
        if (r6 != r0) goto L_0x016b;
    L_0x015d:
        r0 = com.google.android.gms.internal.ads.zzbpq.zzi(r12, r8);
        com.google.android.gms.internal.ads.zzbua.zza(r14, r2, r0);
        r0 = r8 + 8;
    L_0x0166:
        r1 = r7;
        r2 = r10;
    L_0x0168:
        r10 = -1;
        goto L_0x0018;
    L_0x016b:
        r25 = r7;
        r14 = r8;
        r18 = r9;
        r19 = r10;
        goto L_0x006d;
    L_0x0174:
        r0 = 27;
        if (r3 != r0) goto L_0x01ad;
    L_0x0178:
        if (r6 != r10) goto L_0x0066;
    L_0x017a:
        r0 = r9.getObject(r14, r1);
        r0 = (com.google.android.gms.internal.ads.zzbrk) r0;
        r3 = r0.zzaki();
        if (r3 != 0) goto L_0x0198;
    L_0x0186:
        r3 = r0.size();
        if (r3 != 0) goto L_0x018f;
    L_0x018c:
        r3 = 10;
        goto L_0x0191;
    L_0x018f:
        r3 = r3 << 1;
    L_0x0191:
        r0 = r0.zzel(r3);
        r9.putObject(r14, r1, r0);
    L_0x0198:
        r5 = r0;
        r0 = r15.zzfq(r4);
        r1 = r17;
        r2 = r12;
        r3 = r8;
        r19 = r4;
        r4 = r13;
        r6 = r11;
        r0 = zza(r0, r1, r2, r3, r4, r5, r6);
        r1 = r7;
        r2 = r19;
        goto L_0x0168;
    L_0x01ad:
        r19 = r4;
        r0 = 49;
        if (r3 > r0) goto L_0x01f5;
    L_0x01b3:
        r4 = (long) r5;
        r0 = r15;
        r20 = r1;
        r1 = r14;
        r2 = r12;
        r10 = r3;
        r3 = r8;
        r22 = r4;
        r4 = r13;
        r5 = r17;
        r24 = r6;
        r6 = r7;
        r25 = r7;
        r7 = r24;
        r15 = r8;
        r8 = r19;
        r18 = r9;
        r26 = r10;
        r27 = -1;
        r9 = r22;
        r11 = r26;
        r12 = r20;
        r14 = r33;
        r0 = r0.zza(r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r14);
        if (r0 != r15) goto L_0x01e1;
    L_0x01de:
        r2 = r0;
        goto L_0x0244;
    L_0x01e1:
        r14 = r29;
        r12 = r30;
        r13 = r32;
        r11 = r33;
        r9 = r18;
        r2 = r19;
        r1 = r25;
        r10 = r27;
        r15 = r28;
        goto L_0x0018;
    L_0x01f5:
        r20 = r1;
        r26 = r3;
        r24 = r6;
        r25 = r7;
        r15 = r8;
        r18 = r9;
        r27 = -1;
        r0 = 50;
        r9 = r26;
        if (r9 != r0) goto L_0x0226;
    L_0x0208:
        r7 = r24;
        if (r7 != r10) goto L_0x0223;
    L_0x020c:
        r14 = r15;
        r0 = r28;
        r1 = r29;
        r2 = r30;
        r3 = r14;
        r4 = r32;
        r5 = r19;
        r6 = r20;
        r8 = r33;
        r0 = r0.zza(r1, r2, r3, r4, r5, r6, r8);
        if (r0 != r14) goto L_0x0252;
    L_0x0222:
        goto L_0x01de;
    L_0x0223:
        r14 = r15;
    L_0x0224:
        r2 = r14;
        goto L_0x0244;
    L_0x0226:
        r14 = r15;
        r7 = r24;
        r0 = r28;
        r1 = r29;
        r2 = r30;
        r3 = r14;
        r4 = r32;
        r8 = r5;
        r5 = r17;
        r6 = r25;
        r10 = r20;
        r12 = r19;
        r13 = r33;
        r0 = r0.zza(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12, r13);
        if (r0 != r14) goto L_0x0252;
    L_0x0243:
        goto L_0x01de;
    L_0x0244:
        r0 = r17;
        r1 = r30;
        r3 = r32;
        r4 = r29;
        r5 = r33;
        r0 = zza(r0, r1, r2, r3, r4, r5);
    L_0x0252:
        r15 = r28;
        r14 = r29;
        r12 = r30;
        r13 = r32;
        r11 = r33;
        r9 = r18;
        r2 = r19;
        r1 = r25;
        r10 = r27;
        goto L_0x0018;
    L_0x0266:
        r4 = r13;
        if (r0 == r4) goto L_0x026e;
    L_0x0269:
        r0 = com.google.android.gms.internal.ads.zzbrl.zzanj();
        throw r0;
    L_0x026e:
        return;
    L_0x026f:
        r4 = r13;
        r5 = 0;
        r0 = r28;
        r1 = r29;
        r2 = r30;
        r3 = r31;
        r6 = r33;
        r0.zza(r1, r2, r3, r4, r5, r6);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbsp.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.ads.zzbpr):void");
    }

    public final void zzs(T t) {
        int i;
        for (i = this.zzfss; i < this.zzfst; i++) {
            long zzft = (long) (zzft(this.zzfsr[i]) & 1048575);
            Object zzp = zzbua.zzp(t, zzft);
            if (zzp != null) {
                zzbua.zza((Object) t, zzft, this.zzfsy.zzz(zzp));
            }
        }
        i = this.zzfsr.length;
        for (int i2 = this.zzfst; i2 < i; i2++) {
            this.zzfsv.zzb(t, (long) this.zzfsr[i2]);
        }
        this.zzfsw.zzs(t);
        if (this.zzfsn) {
            this.zzfsx.zzs(t);
        }
    }

    private final <UT, UB> UB zza(Object obj, int i, UB ub, zzbtu<UT, UB> zzbtu) {
        int i2 = this.zzfsi[i];
        obj = zzbua.zzp(obj, (long) (zzft(i) & 1048575));
        if (obj == null) {
            return ub;
        }
        zzbri zzfs = zzfs(i);
        if (zzfs == null) {
            return ub;
        }
        return zza(i, i2, this.zzfsy.zzw(obj), zzfs, (Object) ub, (zzbtu) zzbtu);
    }

    private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzbri zzbri, UB ub, zzbtu<UT, UB> zzbtu) {
        zzbse zzab = this.zzfsy.zzab(zzfr(i));
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            if (!zzbri.zzcb(((Integer) entry.getValue()).intValue())) {
                if (ub == null) {
                    ub = zzbtu.zzaoy();
                }
                zzbqb zzen = zzbpu.zzen(zzbsd.zza(zzab, entry.getKey(), entry.getValue()));
                try {
                    zzbsd.zza(zzen.zzakt(), zzab, entry.getKey(), entry.getValue());
                    zzbtu.zza((Object) ub, i2, zzen.zzaks());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    public final boolean zzae(T t) {
        int i = 0;
        int i2 = -1;
        int i3 = i;
        while (true) {
            int i4 = 1;
            if (i3 >= this.zzfss) {
                return !this.zzfsn || this.zzfsx.zzq(t).isInitialized();
            } else {
                int i5;
                int i6;
                int i7 = this.zzfsr[i3];
                int i8 = this.zzfsi[i7];
                int zzft = zzft(i7);
                if (this.zzfsp) {
                    i5 = 0;
                } else {
                    i5 = this.zzfsi[i7 + 2];
                    i6 = i5 & 1048575;
                    i5 = 1 << (i5 >>> 20);
                    if (i6 != i2) {
                        i = zzfsh.getInt(t, (long) i6);
                        i2 = i6;
                    }
                }
                if (((C.ENCODING_PCM_MU_LAW & zzft) != 0 ? 1 : false) != 0 && !zza((Object) t, i7, i, i5)) {
                    return false;
                }
                i6 = (267386880 & zzft) >>> 20;
                if (i6 != 9 && i6 != 17) {
                    zzbtc zzbtc;
                    if (i6 != 27) {
                        if (i6 != 60 && i6 != 68) {
                            switch (i6) {
                                case 49:
                                    break;
                                case 50:
                                    Map zzx = this.zzfsy.zzx(zzbua.zzp(t, (long) (zzft & 1048575)));
                                    if (!zzx.isEmpty()) {
                                        if (this.zzfsy.zzab(zzfr(i7)).zzfsb.zzapj() == zzbuo.MESSAGE) {
                                            zzbtc = null;
                                            for (Object next : zzx.values()) {
                                                if (zzbtc == null) {
                                                    zzbtc = zzbsy.zzaog().zzf(next.getClass());
                                                }
                                                if (!zzbtc.zzae(next)) {
                                                    i4 = 0;
                                                }
                                            }
                                        }
                                    }
                                    if (i4 == 0) {
                                        return false;
                                    }
                                    continue;
                                default:
                                    continue;
                            }
                        } else if (zza((Object) t, i8, i7) && !zza((Object) t, zzft, zzfq(i7))) {
                            return false;
                        }
                    }
                    List list = (List) zzbua.zzp(t, (long) (zzft & 1048575));
                    if (!list.isEmpty()) {
                        zzbtc = zzfq(i7);
                        zzft = 0;
                        while (zzft < list.size()) {
                            if (zzbtc.zzae(list.get(zzft))) {
                                zzft++;
                            } else {
                                i4 = 0;
                            }
                        }
                    }
                    if (i4 == 0) {
                        return false;
                    }
                } else if (zza((Object) t, i7, i, i5) && !zza((Object) t, zzft, zzfq(i7))) {
                    return false;
                }
                i3++;
            }
        }
    }

    private static boolean zza(Object obj, int i, zzbtc zzbtc) {
        return zzbtc.zzae(zzbua.zzp(obj, (long) (i & 1048575)));
    }

    private static void zza(int i, Object obj, zzbup zzbup) throws IOException {
        if (obj instanceof String) {
            zzbup.zzf(i, (String) obj);
        } else {
            zzbup.zza(i, (zzbpu) obj);
        }
    }

    private final void zza(Object obj, int i, zzbtb zzbtb) throws IOException {
        if (zzfv(i)) {
            zzbua.zza(obj, (long) (i & 1048575), zzbtb.zzalb());
        } else if (this.zzfso) {
            zzbua.zza(obj, (long) (i & 1048575), zzbtb.readString());
        } else {
            zzbua.zza(obj, (long) (i & 1048575), zzbtb.zzalc());
        }
    }

    private final int zzft(int i) {
        return this.zzfsi[i + 1];
    }

    private final int zzfu(int i) {
        return this.zzfsi[i + 2];
    }

    private static <T> double zzf(T t, long j) {
        return ((Double) zzbua.zzp(t, j)).doubleValue();
    }

    private static <T> float zzg(T t, long j) {
        return ((Float) zzbua.zzp(t, j)).floatValue();
    }

    private static <T> int zzh(T t, long j) {
        return ((Integer) zzbua.zzp(t, j)).intValue();
    }

    private static <T> long zzi(T t, long j) {
        return ((Long) zzbua.zzp(t, j)).longValue();
    }

    private static <T> boolean zzj(T t, long j) {
        return ((Boolean) zzbua.zzp(t, j)).booleanValue();
    }

    private final boolean zzc(T t, T t2, int i) {
        return zzd((Object) t, i) == zzd((Object) t2, i);
    }

    private final boolean zza(T t, int i, int i2, int i3) {
        if (this.zzfsp) {
            return zzd((Object) t, i);
        }
        return (i2 & i3) != 0;
    }

    private final boolean zzd(T t, int i) {
        if (this.zzfsp) {
            i = zzft(i);
            long j = (long) (i & 1048575);
            switch ((i & 267386880) >>> 20) {
                case 0:
                    return zzbua.zzo(t, j) != 0.0d;
                case 1:
                    return zzbua.zzn(t, j) != 0.0f;
                case 2:
                    return zzbua.zzl(t, j) != 0;
                case 3:
                    return zzbua.zzl(t, j) != 0;
                case 4:
                    return zzbua.zzk(t, j) != 0;
                case 5:
                    return zzbua.zzl(t, j) != 0;
                case 6:
                    return zzbua.zzk(t, j) != 0;
                case 7:
                    return zzbua.zzm(t, j);
                case 8:
                    Object zzp = zzbua.zzp(t, j);
                    if (zzp instanceof String) {
                        return !((String) zzp).isEmpty();
                    } else {
                        if (zzp instanceof zzbpu) {
                            return !zzbpu.zzfli.equals(zzp);
                        } else {
                            throw new IllegalArgumentException();
                        }
                    }
                case 9:
                    return zzbua.zzp(t, j) != null;
                case 10:
                    return !zzbpu.zzfli.equals(zzbua.zzp(t, j));
                case 11:
                    return zzbua.zzk(t, j) != 0;
                case 12:
                    return zzbua.zzk(t, j) != 0;
                case 13:
                    return zzbua.zzk(t, j) != 0;
                case 14:
                    return zzbua.zzl(t, j) != 0;
                case 15:
                    return zzbua.zzk(t, j) != 0;
                case 16:
                    return zzbua.zzl(t, j) != 0;
                case 17:
                    return zzbua.zzp(t, j) != null;
                default:
                    throw new IllegalArgumentException();
            }
        }
        i = zzfu(i);
        return (zzbua.zzk(t, (long) (i & 1048575)) & (1 << (i >>> 20))) != 0;
    }

    private final void zze(T t, int i) {
        if (!this.zzfsp) {
            i = zzfu(i);
            long j = (long) (i & 1048575);
            zzbua.zzb((Object) t, j, zzbua.zzk(t, j) | (1 << (i >>> 20)));
        }
    }

    private final boolean zza(T t, int i, int i2) {
        return zzbua.zzk(t, (long) (zzfu(i2) & 1048575)) == i;
    }

    private final void zzb(T t, int i, int i2) {
        zzbua.zzb((Object) t, (long) (zzfu(i2) & 1048575), i);
    }

    private final int zzfw(int i) {
        return (i < this.zzfsk || i > this.zzfsl) ? -1 : zzaj(i, 0);
    }

    private final int zzai(int i, int i2) {
        return (i < this.zzfsk || i > this.zzfsl) ? -1 : zzaj(i, i2);
    }

    private final int zzaj(int i, int i2) {
        int length = (this.zzfsi.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzfsi[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }
}
