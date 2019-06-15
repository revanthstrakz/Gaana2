package com.google.android.gms.internal.vision;

import com.google.android.exoplayer2.C;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import sun.misc.Unsafe;

final class zzhj<T> implements zzhw<T> {
    private static final int[] zzzb = new int[0];
    private static final Unsafe zzzc = zziu.zzhj();
    private final int[] zzzd;
    private final Object[] zzze;
    private final int zzzf;
    private final int zzzg;
    private final zzhf zzzh;
    private final boolean zzzi;
    private final boolean zzzj;
    private final boolean zzzk;
    private final boolean zzzl;
    private final int[] zzzm;
    private final int zzzn;
    private final int zzzo;
    private final zzhn zzzp;
    private final zzgp zzzq;
    private final zzio<?, ?> zzzr;
    private final zzfl<?> zzzs;
    private final zzha zzzt;

    private zzhj(int[] iArr, Object[] objArr, int i, int i2, zzhf zzhf, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzhn zzhn, zzgp zzgp, zzio<?, ?> zzio, zzfl<?> zzfl, zzha zzha) {
        this.zzzd = iArr;
        this.zzze = objArr;
        this.zzzf = i;
        this.zzzg = i2;
        this.zzzj = zzhf instanceof zzfy;
        this.zzzk = z;
        boolean z3 = zzfl != null && zzfl.zze(zzhf);
        this.zzzi = z3;
        this.zzzl = false;
        this.zzzm = iArr2;
        this.zzzn = i3;
        this.zzzo = i4;
        this.zzzp = zzhn;
        this.zzzq = zzgp;
        this.zzzr = zzio;
        this.zzzs = zzfl;
        this.zzzh = zzhf;
        this.zzzt = zzha;
    }

    private static boolean zzbm(int i) {
        return (i & C.ENCODING_PCM_A_LAW) != 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:166:0x0378  */
    static <T> com.google.android.gms.internal.vision.zzhj<T> zza(java.lang.Class<T> r39, com.google.android.gms.internal.vision.zzhd r40, com.google.android.gms.internal.vision.zzhn r41, com.google.android.gms.internal.vision.zzgp r42, com.google.android.gms.internal.vision.zzio<?, ?> r43, com.google.android.gms.internal.vision.zzfl<?> r44, com.google.android.gms.internal.vision.zzha r45) {
        /*
        r0 = r40;
        r1 = r0 instanceof com.google.android.gms.internal.vision.zzhu;
        if (r1 == 0) goto L_0x043c;
    L_0x0006:
        r0 = (com.google.android.gms.internal.vision.zzhu) r0;
        r1 = r0.zzge();
        r2 = com.google.android.gms.internal.vision.zzfy.zzg.zzxg;
        r3 = 0;
        r4 = 1;
        if (r1 != r2) goto L_0x0014;
    L_0x0012:
        r11 = r4;
        goto L_0x0015;
    L_0x0014:
        r11 = r3;
    L_0x0015:
        r1 = r0.zzgn();
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
        r9 = zzzb;
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
        r37 = r16;
        r16 = r15;
        r15 = r37;
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
        r37 = r16;
        r16 = r3;
        r3 = r37;
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
        r37 = r14;
        r14 = r3;
        r3 = r37;
        r38 = r9;
        r9 = r8;
        r8 = r16;
        r16 = r15;
        r15 = r38;
    L_0x019e:
        r6 = zzzc;
        r17 = r0.zzgo();
        r7 = r0.zzgg();
        r7 = r7.getClass();
        r22 = r8;
        r8 = r13 * 3;
        r8 = new int[r8];
        r13 = r13 << r4;
        r13 = new java.lang.Object[r13];
        r3 = r3 + r14;
        r23 = r3;
        r20 = r15;
        r15 = r22;
        r18 = 0;
        r19 = 0;
        r22 = r14;
    L_0x01c2:
        if (r15 >= r2) goto L_0x0412;
    L_0x01c4:
        r24 = r15 + 1;
        r15 = r1.charAt(r15);
        r4 = 55296; // 0xd800 float:7.7486E-41 double:2.732E-319;
        if (r15 < r4) goto L_0x01f6;
    L_0x01cf:
        r15 = r15 & 8191;
        r25 = 13;
        r37 = r24;
        r24 = r15;
        r15 = r37;
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
        r37 = r24;
        r24 = r4;
        r4 = r37;
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
        r14 = 51;
        r30 = r11;
        if (r3 < r14) goto L_0x02e1;
    L_0x0244:
        r14 = r2 + 1;
        r2 = r1.charAt(r2);
        r11 = 55296; // 0xd800 float:7.7486E-41 double:2.732E-319;
        if (r2 < r11) goto L_0x026c;
    L_0x024f:
        r2 = r2 & 8191;
        r24 = 13;
    L_0x0253:
        r25 = r14 + 1;
        r14 = r1.charAt(r14);
        if (r14 < r11) goto L_0x0268;
    L_0x025b:
        r11 = r14 & 8191;
        r11 = r11 << r24;
        r2 = r2 | r11;
        r24 = r24 + 13;
        r14 = r25;
        r11 = 55296; // 0xd800 float:7.7486E-41 double:2.732E-319;
        goto L_0x0253;
    L_0x0268:
        r11 = r14 << r24;
        r2 = r2 | r11;
        goto L_0x026e;
    L_0x026c:
        r25 = r14;
    L_0x026e:
        r11 = r3 + -51;
        r14 = 9;
        if (r11 == r14) goto L_0x0293;
    L_0x0274:
        r14 = 17;
        if (r11 != r14) goto L_0x0279;
    L_0x0278:
        goto L_0x0293;
    L_0x0279:
        r14 = 12;
        if (r11 != r14) goto L_0x028f;
    L_0x027d:
        r11 = r5 & 1;
        r14 = 1;
        if (r11 != r14) goto L_0x028f;
    L_0x0282:
        r11 = r19 / 3;
        r11 = r11 << r14;
        r11 = r11 + r14;
        r14 = r20 + 1;
        r20 = r17[r20];
        r13[r11] = r20;
        r24 = r14;
        goto L_0x0291;
    L_0x028f:
        r24 = r20;
    L_0x0291:
        r14 = 1;
        goto L_0x029e;
    L_0x0293:
        r11 = r19 / 3;
        r14 = 1;
        r11 = r11 << r14;
        r11 = r11 + r14;
        r24 = r20 + 1;
        r20 = r17[r20];
        r13[r11] = r20;
    L_0x029e:
        r2 = r2 << r14;
        r11 = r17[r2];
        r14 = r11 instanceof java.lang.reflect.Field;
        if (r14 == 0) goto L_0x02aa;
    L_0x02a5:
        r11 = (java.lang.reflect.Field) r11;
    L_0x02a7:
        r31 = r12;
        goto L_0x02b3;
    L_0x02aa:
        r11 = (java.lang.String) r11;
        r11 = zza(r7, r11);
        r17[r2] = r11;
        goto L_0x02a7;
    L_0x02b3:
        r11 = r6.objectFieldOffset(r11);
        r11 = (int) r11;
        r2 = r2 + 1;
        r12 = r17[r2];
        r14 = r12 instanceof java.lang.reflect.Field;
        if (r14 == 0) goto L_0x02c5;
    L_0x02c0:
        r12 = (java.lang.reflect.Field) r12;
    L_0x02c2:
        r32 = r11;
        goto L_0x02ce;
    L_0x02c5:
        r12 = (java.lang.String) r12;
        r12 = zza(r7, r12);
        r17[r2] = r12;
        goto L_0x02c2;
    L_0x02ce:
        r11 = r6.objectFieldOffset(r12);
        r2 = (int) r11;
        r35 = r9;
        r33 = r10;
        r20 = r24;
        r36 = r25;
        r11 = r32;
        r9 = r2;
        r2 = 0;
        goto L_0x03db;
    L_0x02e1:
        r31 = r12;
        r11 = r20 + 1;
        r12 = r17[r20];
        r12 = (java.lang.String) r12;
        r12 = zza(r7, r12);
        r14 = 9;
        if (r3 == r14) goto L_0x035f;
    L_0x02f1:
        r14 = 17;
        if (r3 != r14) goto L_0x02f7;
    L_0x02f5:
        goto L_0x035f;
    L_0x02f7:
        r14 = 27;
        if (r3 == r14) goto L_0x034f;
    L_0x02fb:
        r14 = 49;
        if (r3 != r14) goto L_0x0300;
    L_0x02ff:
        goto L_0x034f;
    L_0x0300:
        r14 = 12;
        if (r3 == r14) goto L_0x033d;
    L_0x0304:
        r14 = 30;
        if (r3 == r14) goto L_0x033d;
    L_0x0308:
        r14 = 44;
        if (r3 != r14) goto L_0x030d;
    L_0x030c:
        goto L_0x033d;
    L_0x030d:
        r14 = 50;
        if (r3 != r14) goto L_0x0339;
    L_0x0311:
        r14 = r22 + 1;
        r16[r22] = r19;
        r20 = r19 / 3;
        r22 = 1;
        r20 = r20 << 1;
        r22 = r11 + 1;
        r11 = r17[r11];
        r13[r20] = r11;
        r11 = r4 & 2048;
        if (r11 == 0) goto L_0x0332;
    L_0x0325:
        r20 = r20 + 1;
        r11 = r22 + 1;
        r22 = r17[r22];
        r13[r20] = r22;
        r33 = r10;
        r34 = r11;
        goto L_0x0336;
    L_0x0332:
        r33 = r10;
        r34 = r22;
    L_0x0336:
        r22 = r14;
        goto L_0x036e;
    L_0x0339:
        r33 = r10;
        r10 = 1;
        goto L_0x036c;
    L_0x033d:
        r14 = r5 & 1;
        r33 = r10;
        r10 = 1;
        if (r14 != r10) goto L_0x036c;
    L_0x0344:
        r14 = r19 / 3;
        r14 = r14 << r10;
        r14 = r14 + r10;
        r20 = r11 + 1;
        r11 = r17[r11];
        r13[r14] = r11;
        goto L_0x035c;
    L_0x034f:
        r33 = r10;
        r10 = 1;
        r14 = r19 / 3;
        r14 = r14 << r10;
        r14 = r14 + r10;
        r20 = r11 + 1;
        r11 = r17[r11];
        r13[r14] = r11;
    L_0x035c:
        r34 = r20;
        goto L_0x036e;
    L_0x035f:
        r33 = r10;
        r10 = 1;
        r14 = r19 / 3;
        r14 = r14 << r10;
        r14 = r14 + r10;
        r20 = r12.getType();
        r13[r14] = r20;
    L_0x036c:
        r34 = r11;
    L_0x036e:
        r10 = r6.objectFieldOffset(r12);
        r11 = (int) r10;
        r10 = r5 & 1;
        r12 = 1;
        if (r10 != r12) goto L_0x03c5;
    L_0x0378:
        r10 = 17;
        if (r3 > r10) goto L_0x03c5;
    L_0x037c:
        r10 = r2 + 1;
        r2 = r1.charAt(r2);
        r12 = 55296; // 0xd800 float:7.7486E-41 double:2.732E-319;
        if (r2 < r12) goto L_0x03a0;
    L_0x0387:
        r2 = r2 & 8191;
        r14 = 13;
    L_0x038b:
        r20 = r10 + 1;
        r10 = r1.charAt(r10);
        if (r10 < r12) goto L_0x039c;
    L_0x0393:
        r10 = r10 & 8191;
        r10 = r10 << r14;
        r2 = r2 | r10;
        r14 = r14 + 13;
        r10 = r20;
        goto L_0x038b;
    L_0x039c:
        r10 = r10 << r14;
        r2 = r2 | r10;
        r10 = r20;
    L_0x03a0:
        r14 = 1;
        r20 = r9 << 1;
        r21 = r2 / 32;
        r20 = r20 + r21;
        r12 = r17[r20];
        r14 = r12 instanceof java.lang.reflect.Field;
        if (r14 == 0) goto L_0x03b4;
    L_0x03ad:
        r12 = (java.lang.reflect.Field) r12;
    L_0x03af:
        r35 = r9;
        r36 = r10;
        goto L_0x03bd;
    L_0x03b4:
        r12 = (java.lang.String) r12;
        r12 = zza(r7, r12);
        r17[r20] = r12;
        goto L_0x03af;
    L_0x03bd:
        r9 = r6.objectFieldOffset(r12);
        r9 = (int) r9;
        r2 = r2 % 32;
        goto L_0x03cb;
    L_0x03c5:
        r35 = r9;
        r36 = r2;
        r2 = 0;
        r9 = 0;
    L_0x03cb:
        r10 = 18;
        if (r3 < r10) goto L_0x03d9;
    L_0x03cf:
        r10 = 49;
        if (r3 > r10) goto L_0x03d9;
    L_0x03d3:
        r10 = r23 + 1;
        r16[r23] = r11;
        r23 = r10;
    L_0x03d9:
        r20 = r34;
    L_0x03db:
        r10 = r19 + 1;
        r8[r19] = r15;
        r12 = r10 + 1;
        r14 = r4 & 512;
        if (r14 == 0) goto L_0x03e8;
    L_0x03e5:
        r14 = 536870912; // 0x20000000 float:1.0842022E-19 double:2.652494739E-315;
        goto L_0x03e9;
    L_0x03e8:
        r14 = 0;
    L_0x03e9:
        r4 = r4 & 256;
        if (r4 == 0) goto L_0x03f0;
    L_0x03ed:
        r4 = 268435456; // 0x10000000 float:2.5243549E-29 double:1.32624737E-315;
        goto L_0x03f1;
    L_0x03f0:
        r4 = 0;
    L_0x03f1:
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
        r11 = r30;
        r12 = r31;
        r10 = r33;
        r9 = r35;
        r15 = r36;
        r4 = 1;
        goto L_0x01c2;
    L_0x0412:
        r28 = r3;
        r33 = r10;
        r30 = r11;
        r31 = r12;
        r29 = r14;
        r1 = new com.google.android.gms.internal.vision.zzhj;
        r10 = r0.zzgg();
        r12 = 0;
        r5 = r1;
        r6 = r8;
        r7 = r13;
        r8 = r33;
        r9 = r31;
        r13 = r16;
        r15 = r28;
        r16 = r41;
        r17 = r42;
        r18 = r43;
        r19 = r44;
        r20 = r45;
        r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20);
        return r1;
    L_0x043c:
        r0 = (com.google.android.gms.internal.vision.zzij) r0;
        r0.zzge();
        r0 = new java.lang.NoSuchMethodError;
        r0.<init>();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhj.zza(java.lang.Class, com.google.android.gms.internal.vision.zzhd, com.google.android.gms.internal.vision.zzhn, com.google.android.gms.internal.vision.zzgp, com.google.android.gms.internal.vision.zzio, com.google.android.gms.internal.vision.zzfl, com.google.android.gms.internal.vision.zzha):com.google.android.gms.internal.vision.zzhj");
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
        return this.zzzp.newInstance(this.zzzh);
    }

    /* JADX WARNING: Missing block: B:8:0x0038, code skipped:
            if (com.google.android.gms.internal.vision.zzhy.zzd(com.google.android.gms.internal.vision.zziu.zzp(r10, r6), com.google.android.gms.internal.vision.zziu.zzp(r11, r6)) != false) goto L_0x01c2;
     */
    /* JADX WARNING: Missing block: B:14:0x006a, code skipped:
            if (com.google.android.gms.internal.vision.zzhy.zzd(com.google.android.gms.internal.vision.zziu.zzp(r10, r6), com.google.android.gms.internal.vision.zziu.zzp(r11, r6)) != false) goto L_0x01c2;
     */
    /* JADX WARNING: Missing block: B:18:0x007e, code skipped:
            if (com.google.android.gms.internal.vision.zziu.zzl(r10, r6) == com.google.android.gms.internal.vision.zziu.zzl(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Missing block: B:22:0x0090, code skipped:
            if (com.google.android.gms.internal.vision.zziu.zzk(r10, r6) == com.google.android.gms.internal.vision.zziu.zzk(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Missing block: B:26:0x00a4, code skipped:
            if (com.google.android.gms.internal.vision.zziu.zzl(r10, r6) == com.google.android.gms.internal.vision.zziu.zzl(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Missing block: B:30:0x00b6, code skipped:
            if (com.google.android.gms.internal.vision.zziu.zzk(r10, r6) == com.google.android.gms.internal.vision.zziu.zzk(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Missing block: B:34:0x00c8, code skipped:
            if (com.google.android.gms.internal.vision.zziu.zzk(r10, r6) == com.google.android.gms.internal.vision.zziu.zzk(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Missing block: B:38:0x00da, code skipped:
            if (com.google.android.gms.internal.vision.zziu.zzk(r10, r6) == com.google.android.gms.internal.vision.zziu.zzk(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Missing block: B:42:0x00f0, code skipped:
            if (com.google.android.gms.internal.vision.zzhy.zzd(com.google.android.gms.internal.vision.zziu.zzp(r10, r6), com.google.android.gms.internal.vision.zziu.zzp(r11, r6)) != false) goto L_0x01c2;
     */
    /* JADX WARNING: Missing block: B:46:0x0106, code skipped:
            if (com.google.android.gms.internal.vision.zzhy.zzd(com.google.android.gms.internal.vision.zziu.zzp(r10, r6), com.google.android.gms.internal.vision.zziu.zzp(r11, r6)) != false) goto L_0x01c2;
     */
    /* JADX WARNING: Missing block: B:50:0x011c, code skipped:
            if (com.google.android.gms.internal.vision.zzhy.zzd(com.google.android.gms.internal.vision.zziu.zzp(r10, r6), com.google.android.gms.internal.vision.zziu.zzp(r11, r6)) != false) goto L_0x01c2;
     */
    /* JADX WARNING: Missing block: B:54:0x012e, code skipped:
            if (com.google.android.gms.internal.vision.zziu.zzm(r10, r6) == com.google.android.gms.internal.vision.zziu.zzm(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Missing block: B:58:0x0140, code skipped:
            if (com.google.android.gms.internal.vision.zziu.zzk(r10, r6) == com.google.android.gms.internal.vision.zziu.zzk(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Missing block: B:62:0x0154, code skipped:
            if (com.google.android.gms.internal.vision.zziu.zzl(r10, r6) == com.google.android.gms.internal.vision.zziu.zzl(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Missing block: B:66:0x0165, code skipped:
            if (com.google.android.gms.internal.vision.zziu.zzk(r10, r6) == com.google.android.gms.internal.vision.zziu.zzk(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Missing block: B:70:0x0178, code skipped:
            if (com.google.android.gms.internal.vision.zziu.zzl(r10, r6) == com.google.android.gms.internal.vision.zziu.zzl(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Missing block: B:74:0x018b, code skipped:
            if (com.google.android.gms.internal.vision.zziu.zzl(r10, r6) == com.google.android.gms.internal.vision.zziu.zzl(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Missing block: B:78:0x01a4, code skipped:
            if (java.lang.Float.floatToIntBits(com.google.android.gms.internal.vision.zziu.zzn(r10, r6)) == java.lang.Float.floatToIntBits(com.google.android.gms.internal.vision.zziu.zzn(r11, r6))) goto L_0x01c2;
     */
    /* JADX WARNING: Missing block: B:82:0x01bf, code skipped:
            if (java.lang.Double.doubleToLongBits(com.google.android.gms.internal.vision.zziu.zzo(r10, r6)) == java.lang.Double.doubleToLongBits(com.google.android.gms.internal.vision.zziu.zzo(r11, r6))) goto L_0x01c2;
     */
    /* JADX WARNING: Missing block: B:83:0x01c1, code skipped:
            r3 = false;
     */
    public final boolean equals(T r10, T r11) {
        /*
        r9 = this;
        r0 = r9.zzzd;
        r1 = 0;
        r0 = r0.length;
        r2 = r1;
    L_0x0005:
        r3 = 1;
        if (r2 >= r0) goto L_0x01c9;
    L_0x0008:
        r4 = r9.zzbk(r2);
        r5 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r6 = r4 & r5;
        r6 = (long) r6;
        r8 = 267386880; // 0xff00000 float:2.3665827E-29 double:1.321066716E-315;
        r4 = r4 & r8;
        r4 = r4 >>> 20;
        switch(r4) {
            case 0: goto L_0x01a7;
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
        goto L_0x01c2;
    L_0x001c:
        r4 = r9.zzbl(r2);
        r4 = r4 & r5;
        r4 = (long) r4;
        r8 = com.google.android.gms.internal.vision.zziu.zzk(r10, r4);
        r4 = com.google.android.gms.internal.vision.zziu.zzk(r11, r4);
        if (r8 != r4) goto L_0x01c1;
    L_0x002c:
        r4 = com.google.android.gms.internal.vision.zziu.zzp(r10, r6);
        r5 = com.google.android.gms.internal.vision.zziu.zzp(r11, r6);
        r4 = com.google.android.gms.internal.vision.zzhy.zzd(r4, r5);
        if (r4 != 0) goto L_0x01c2;
    L_0x003a:
        goto L_0x01c1;
    L_0x003c:
        r3 = com.google.android.gms.internal.vision.zziu.zzp(r10, r6);
        r4 = com.google.android.gms.internal.vision.zziu.zzp(r11, r6);
        r3 = com.google.android.gms.internal.vision.zzhy.zzd(r3, r4);
        goto L_0x01c2;
    L_0x004a:
        r3 = com.google.android.gms.internal.vision.zziu.zzp(r10, r6);
        r4 = com.google.android.gms.internal.vision.zziu.zzp(r11, r6);
        r3 = com.google.android.gms.internal.vision.zzhy.zzd(r3, r4);
        goto L_0x01c2;
    L_0x0058:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01c1;
    L_0x005e:
        r4 = com.google.android.gms.internal.vision.zziu.zzp(r10, r6);
        r5 = com.google.android.gms.internal.vision.zziu.zzp(r11, r6);
        r4 = com.google.android.gms.internal.vision.zzhy.zzd(r4, r5);
        if (r4 != 0) goto L_0x01c2;
    L_0x006c:
        goto L_0x01c1;
    L_0x006e:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01c1;
    L_0x0074:
        r4 = com.google.android.gms.internal.vision.zziu.zzl(r10, r6);
        r6 = com.google.android.gms.internal.vision.zziu.zzl(r11, r6);
        r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r8 == 0) goto L_0x01c2;
    L_0x0080:
        goto L_0x01c1;
    L_0x0082:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01c1;
    L_0x0088:
        r4 = com.google.android.gms.internal.vision.zziu.zzk(r10, r6);
        r5 = com.google.android.gms.internal.vision.zziu.zzk(r11, r6);
        if (r4 == r5) goto L_0x01c2;
    L_0x0092:
        goto L_0x01c1;
    L_0x0094:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01c1;
    L_0x009a:
        r4 = com.google.android.gms.internal.vision.zziu.zzl(r10, r6);
        r6 = com.google.android.gms.internal.vision.zziu.zzl(r11, r6);
        r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r8 == 0) goto L_0x01c2;
    L_0x00a6:
        goto L_0x01c1;
    L_0x00a8:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01c1;
    L_0x00ae:
        r4 = com.google.android.gms.internal.vision.zziu.zzk(r10, r6);
        r5 = com.google.android.gms.internal.vision.zziu.zzk(r11, r6);
        if (r4 == r5) goto L_0x01c2;
    L_0x00b8:
        goto L_0x01c1;
    L_0x00ba:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01c1;
    L_0x00c0:
        r4 = com.google.android.gms.internal.vision.zziu.zzk(r10, r6);
        r5 = com.google.android.gms.internal.vision.zziu.zzk(r11, r6);
        if (r4 == r5) goto L_0x01c2;
    L_0x00ca:
        goto L_0x01c1;
    L_0x00cc:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01c1;
    L_0x00d2:
        r4 = com.google.android.gms.internal.vision.zziu.zzk(r10, r6);
        r5 = com.google.android.gms.internal.vision.zziu.zzk(r11, r6);
        if (r4 == r5) goto L_0x01c2;
    L_0x00dc:
        goto L_0x01c1;
    L_0x00de:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01c1;
    L_0x00e4:
        r4 = com.google.android.gms.internal.vision.zziu.zzp(r10, r6);
        r5 = com.google.android.gms.internal.vision.zziu.zzp(r11, r6);
        r4 = com.google.android.gms.internal.vision.zzhy.zzd(r4, r5);
        if (r4 != 0) goto L_0x01c2;
    L_0x00f2:
        goto L_0x01c1;
    L_0x00f4:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01c1;
    L_0x00fa:
        r4 = com.google.android.gms.internal.vision.zziu.zzp(r10, r6);
        r5 = com.google.android.gms.internal.vision.zziu.zzp(r11, r6);
        r4 = com.google.android.gms.internal.vision.zzhy.zzd(r4, r5);
        if (r4 != 0) goto L_0x01c2;
    L_0x0108:
        goto L_0x01c1;
    L_0x010a:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01c1;
    L_0x0110:
        r4 = com.google.android.gms.internal.vision.zziu.zzp(r10, r6);
        r5 = com.google.android.gms.internal.vision.zziu.zzp(r11, r6);
        r4 = com.google.android.gms.internal.vision.zzhy.zzd(r4, r5);
        if (r4 != 0) goto L_0x01c2;
    L_0x011e:
        goto L_0x01c1;
    L_0x0120:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01c1;
    L_0x0126:
        r4 = com.google.android.gms.internal.vision.zziu.zzm(r10, r6);
        r5 = com.google.android.gms.internal.vision.zziu.zzm(r11, r6);
        if (r4 == r5) goto L_0x01c2;
    L_0x0130:
        goto L_0x01c1;
    L_0x0132:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01c1;
    L_0x0138:
        r4 = com.google.android.gms.internal.vision.zziu.zzk(r10, r6);
        r5 = com.google.android.gms.internal.vision.zziu.zzk(r11, r6);
        if (r4 == r5) goto L_0x01c2;
    L_0x0142:
        goto L_0x01c1;
    L_0x0144:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01c1;
    L_0x014a:
        r4 = com.google.android.gms.internal.vision.zziu.zzl(r10, r6);
        r6 = com.google.android.gms.internal.vision.zziu.zzl(r11, r6);
        r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r8 == 0) goto L_0x01c2;
    L_0x0156:
        goto L_0x01c1;
    L_0x0157:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01c1;
    L_0x015d:
        r4 = com.google.android.gms.internal.vision.zziu.zzk(r10, r6);
        r5 = com.google.android.gms.internal.vision.zziu.zzk(r11, r6);
        if (r4 == r5) goto L_0x01c2;
    L_0x0167:
        goto L_0x01c1;
    L_0x0168:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01c1;
    L_0x016e:
        r4 = com.google.android.gms.internal.vision.zziu.zzl(r10, r6);
        r6 = com.google.android.gms.internal.vision.zziu.zzl(r11, r6);
        r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r8 == 0) goto L_0x01c2;
    L_0x017a:
        goto L_0x01c1;
    L_0x017b:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01c1;
    L_0x0181:
        r4 = com.google.android.gms.internal.vision.zziu.zzl(r10, r6);
        r6 = com.google.android.gms.internal.vision.zziu.zzl(r11, r6);
        r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r8 == 0) goto L_0x01c2;
    L_0x018d:
        goto L_0x01c1;
    L_0x018e:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01c1;
    L_0x0194:
        r4 = com.google.android.gms.internal.vision.zziu.zzn(r10, r6);
        r4 = java.lang.Float.floatToIntBits(r4);
        r5 = com.google.android.gms.internal.vision.zziu.zzn(r11, r6);
        r5 = java.lang.Float.floatToIntBits(r5);
        if (r4 == r5) goto L_0x01c2;
    L_0x01a6:
        goto L_0x01c1;
    L_0x01a7:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01c1;
    L_0x01ad:
        r4 = com.google.android.gms.internal.vision.zziu.zzo(r10, r6);
        r4 = java.lang.Double.doubleToLongBits(r4);
        r6 = com.google.android.gms.internal.vision.zziu.zzo(r11, r6);
        r6 = java.lang.Double.doubleToLongBits(r6);
        r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r8 == 0) goto L_0x01c2;
    L_0x01c1:
        r3 = r1;
    L_0x01c2:
        if (r3 != 0) goto L_0x01c5;
    L_0x01c4:
        return r1;
    L_0x01c5:
        r2 = r2 + 3;
        goto L_0x0005;
    L_0x01c9:
        r0 = r9.zzzr;
        r0 = r0.zzt(r10);
        r2 = r9.zzzr;
        r2 = r2.zzt(r11);
        r0 = r0.equals(r2);
        if (r0 != 0) goto L_0x01dc;
    L_0x01db:
        return r1;
    L_0x01dc:
        r0 = r9.zzzi;
        if (r0 == 0) goto L_0x01f1;
    L_0x01e0:
        r0 = r9.zzzs;
        r10 = r0.zzc(r10);
        r0 = r9.zzzs;
        r11 = r0.zzc(r11);
        r10 = r10.equals(r11);
        return r10;
    L_0x01f1:
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhj.equals(java.lang.Object, java.lang.Object):boolean");
    }

    public final int hashCode(T t) {
        int i = 0;
        int length = this.zzzd.length;
        int i2 = 0;
        while (i < length) {
            int zzbk = zzbk(i);
            int i3 = this.zzzd[i];
            long j = (long) (1048575 & zzbk);
            int i4 = 37;
            Object zzp;
            switch ((zzbk & 267386880) >>> 20) {
                case 0:
                    i2 = (i2 * 53) + zzga.zzo(Double.doubleToLongBits(zziu.zzo(t, j)));
                    break;
                case 1:
                    i2 = (i2 * 53) + Float.floatToIntBits(zziu.zzn(t, j));
                    break;
                case 2:
                    i2 = (i2 * 53) + zzga.zzo(zziu.zzl(t, j));
                    break;
                case 3:
                    i2 = (i2 * 53) + zzga.zzo(zziu.zzl(t, j));
                    break;
                case 4:
                    i2 = (i2 * 53) + zziu.zzk(t, j);
                    break;
                case 5:
                    i2 = (i2 * 53) + zzga.zzo(zziu.zzl(t, j));
                    break;
                case 6:
                    i2 = (i2 * 53) + zziu.zzk(t, j);
                    break;
                case 7:
                    i2 = (i2 * 53) + zzga.zzj(zziu.zzm(t, j));
                    break;
                case 8:
                    i2 = (i2 * 53) + ((String) zziu.zzp(t, j)).hashCode();
                    break;
                case 9:
                    zzp = zziu.zzp(t, j);
                    if (zzp != null) {
                        i4 = zzp.hashCode();
                    }
                    i2 = (i2 * 53) + i4;
                    break;
                case 10:
                    i2 = (i2 * 53) + zziu.zzp(t, j).hashCode();
                    break;
                case 11:
                    i2 = (i2 * 53) + zziu.zzk(t, j);
                    break;
                case 12:
                    i2 = (i2 * 53) + zziu.zzk(t, j);
                    break;
                case 13:
                    i2 = (i2 * 53) + zziu.zzk(t, j);
                    break;
                case 14:
                    i2 = (i2 * 53) + zzga.zzo(zziu.zzl(t, j));
                    break;
                case 15:
                    i2 = (i2 * 53) + zziu.zzk(t, j);
                    break;
                case 16:
                    i2 = (i2 * 53) + zzga.zzo(zziu.zzl(t, j));
                    break;
                case 17:
                    zzp = zziu.zzp(t, j);
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
                    i2 = (i2 * 53) + zziu.zzp(t, j).hashCode();
                    break;
                case 50:
                    i2 = (i2 * 53) + zziu.zzp(t, j).hashCode();
                    break;
                case 51:
                    if (!zza((Object) t, i3, i)) {
                        break;
                    }
                    i2 = (i2 * 53) + zzga.zzo(Double.doubleToLongBits(zzf(t, j)));
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
                    i2 = (i2 * 53) + zzga.zzo(zzi(t, j));
                    break;
                case 54:
                    if (!zza((Object) t, i3, i)) {
                        break;
                    }
                    i2 = (i2 * 53) + zzga.zzo(zzi(t, j));
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
                    i2 = (i2 * 53) + zzga.zzo(zzi(t, j));
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
                    i2 = (i2 * 53) + zzga.zzj(zzj(t, j));
                    break;
                case 59:
                    if (!zza((Object) t, i3, i)) {
                        break;
                    }
                    i2 = (i2 * 53) + ((String) zziu.zzp(t, j)).hashCode();
                    break;
                case 60:
                    if (!zza((Object) t, i3, i)) {
                        break;
                    }
                    i2 = (i2 * 53) + zziu.zzp(t, j).hashCode();
                    break;
                case 61:
                    if (!zza((Object) t, i3, i)) {
                        break;
                    }
                    i2 = (i2 * 53) + zziu.zzp(t, j).hashCode();
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
                    i2 = (i2 * 53) + zzga.zzo(zzi(t, j));
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
                    i2 = (i2 * 53) + zzga.zzo(zzi(t, j));
                    break;
                case 68:
                    if (!zza((Object) t, i3, i)) {
                        break;
                    }
                    i2 = (i2 * 53) + zziu.zzp(t, j).hashCode();
                    break;
                default:
                    break;
            }
            i += 3;
        }
        i2 = (i2 * 53) + this.zzzr.zzt(t).hashCode();
        return this.zzzi ? (i2 * 53) + this.zzzs.zzc(t).hashCode() : i2;
    }

    public final void zzc(T t, T t2) {
        if (t2 == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < this.zzzd.length; i += 3) {
            int zzbk = zzbk(i);
            long j = (long) (1048575 & zzbk);
            int i2 = this.zzzd[i];
            switch ((zzbk & 267386880) >>> 20) {
                case 0:
                    if (!zza((Object) t2, i)) {
                        break;
                    }
                    zziu.zza((Object) t, j, zziu.zzo(t2, j));
                    zzb((Object) t, i);
                    break;
                case 1:
                    if (!zza((Object) t2, i)) {
                        break;
                    }
                    zziu.zza((Object) t, j, zziu.zzn(t2, j));
                    zzb((Object) t, i);
                    break;
                case 2:
                    if (!zza((Object) t2, i)) {
                        break;
                    }
                    zziu.zza((Object) t, j, zziu.zzl(t2, j));
                    zzb((Object) t, i);
                    break;
                case 3:
                    if (!zza((Object) t2, i)) {
                        break;
                    }
                    zziu.zza((Object) t, j, zziu.zzl(t2, j));
                    zzb((Object) t, i);
                    break;
                case 4:
                    if (!zza((Object) t2, i)) {
                        break;
                    }
                    zziu.zzb((Object) t, j, zziu.zzk(t2, j));
                    zzb((Object) t, i);
                    break;
                case 5:
                    if (!zza((Object) t2, i)) {
                        break;
                    }
                    zziu.zza((Object) t, j, zziu.zzl(t2, j));
                    zzb((Object) t, i);
                    break;
                case 6:
                    if (!zza((Object) t2, i)) {
                        break;
                    }
                    zziu.zzb((Object) t, j, zziu.zzk(t2, j));
                    zzb((Object) t, i);
                    break;
                case 7:
                    if (!zza((Object) t2, i)) {
                        break;
                    }
                    zziu.zza((Object) t, j, zziu.zzm(t2, j));
                    zzb((Object) t, i);
                    break;
                case 8:
                    if (!zza((Object) t2, i)) {
                        break;
                    }
                    zziu.zza((Object) t, j, zziu.zzp(t2, j));
                    zzb((Object) t, i);
                    break;
                case 9:
                    zza((Object) t, (Object) t2, i);
                    break;
                case 10:
                    if (!zza((Object) t2, i)) {
                        break;
                    }
                    zziu.zza((Object) t, j, zziu.zzp(t2, j));
                    zzb((Object) t, i);
                    break;
                case 11:
                    if (!zza((Object) t2, i)) {
                        break;
                    }
                    zziu.zzb((Object) t, j, zziu.zzk(t2, j));
                    zzb((Object) t, i);
                    break;
                case 12:
                    if (!zza((Object) t2, i)) {
                        break;
                    }
                    zziu.zzb((Object) t, j, zziu.zzk(t2, j));
                    zzb((Object) t, i);
                    break;
                case 13:
                    if (!zza((Object) t2, i)) {
                        break;
                    }
                    zziu.zzb((Object) t, j, zziu.zzk(t2, j));
                    zzb((Object) t, i);
                    break;
                case 14:
                    if (!zza((Object) t2, i)) {
                        break;
                    }
                    zziu.zza((Object) t, j, zziu.zzl(t2, j));
                    zzb((Object) t, i);
                    break;
                case 15:
                    if (!zza((Object) t2, i)) {
                        break;
                    }
                    zziu.zzb((Object) t, j, zziu.zzk(t2, j));
                    zzb((Object) t, i);
                    break;
                case 16:
                    if (!zza((Object) t2, i)) {
                        break;
                    }
                    zziu.zza((Object) t, j, zziu.zzl(t2, j));
                    zzb((Object) t, i);
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
                    this.zzzq.zza(t, t2, j);
                    break;
                case 50:
                    zzhy.zza(this.zzzt, (Object) t, (Object) t2, j);
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
                    zziu.zza((Object) t, j, zziu.zzp(t2, j));
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
                    zziu.zza((Object) t, j, zziu.zzp(t2, j));
                    zzb((Object) t, i2, i);
                    break;
                case 68:
                    zzb((Object) t, (Object) t2, i);
                    break;
                default:
                    break;
            }
        }
        if (!this.zzzk) {
            zzhy.zza(this.zzzr, (Object) t, (Object) t2);
            if (this.zzzi) {
                zzhy.zza(this.zzzs, (Object) t, (Object) t2);
            }
        }
    }

    private final void zza(T t, T t2, int i) {
        long zzbk = (long) (zzbk(i) & 1048575);
        if (zza((Object) t2, i)) {
            Object zzp = zziu.zzp(t, zzbk);
            Object zzp2 = zziu.zzp(t2, zzbk);
            if (zzp == null || zzp2 == null) {
                if (zzp2 != null) {
                    zziu.zza((Object) t, zzbk, zzp2);
                    zzb((Object) t, i);
                }
                return;
            }
            zziu.zza((Object) t, zzbk, zzga.zza(zzp, zzp2));
            zzb((Object) t, i);
        }
    }

    private final void zzb(T t, T t2, int i) {
        int zzbk = zzbk(i);
        int i2 = this.zzzd[i];
        long j = (long) (zzbk & 1048575);
        if (zza((Object) t2, i2, i)) {
            Object zzp = zziu.zzp(t, j);
            Object zzp2 = zziu.zzp(t2, j);
            if (zzp == null || zzp2 == null) {
                if (zzp2 != null) {
                    zziu.zza((Object) t, j, zzp2);
                    zzb((Object) t, i2, i);
                }
                return;
            }
            zziu.zza((Object) t, j, zzga.zza(zzp, zzp2));
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
    public final int zzp(T r22) {
        /*
        r21 = this;
        r0 = r21;
        r1 = r22;
        r2 = r0.zzzk;
        r3 = 267386880; // 0xff00000 float:2.3665827E-29 double:1.321066716E-315;
        r6 = 0;
        r7 = 1;
        r8 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r9 = 0;
        r11 = 0;
        if (r2 == 0) goto L_0x055f;
    L_0x0012:
        r2 = zzzc;
        r12 = r11;
        r13 = r12;
    L_0x0016:
        r14 = r0.zzzd;
        r14 = r14.length;
        if (r12 >= r14) goto L_0x0557;
    L_0x001b:
        r14 = r0.zzbk(r12);
        r15 = r14 & r3;
        r15 = r15 >>> 20;
        r3 = r0.zzzd;
        r3 = r3[r12];
        r14 = r14 & r8;
        r4 = (long) r14;
        r14 = com.google.android.gms.internal.vision.zzfs.DOUBLE_LIST_PACKED;
        r14 = r14.id();
        if (r15 < r14) goto L_0x0041;
    L_0x0031:
        r14 = com.google.android.gms.internal.vision.zzfs.SINT64_LIST_PACKED;
        r14 = r14.id();
        if (r15 > r14) goto L_0x0041;
    L_0x0039:
        r14 = r0.zzzd;
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
        r4 = com.google.android.gms.internal.vision.zziu.zzp(r1, r4);
        r4 = (com.google.android.gms.internal.vision.zzhf) r4;
        r5 = r0.zzbh(r12);
        r3 = com.google.android.gms.internal.vision.zzfe.zzc(r3, r4, r5);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x005e:
        r14 = r0.zza(r1, r3, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x0064:
        r4 = zzi(r1, r4);
        r3 = com.google.android.gms.internal.vision.zzfe.zzf(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x006f:
        r14 = r0.zza(r1, r3, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x0075:
        r4 = zzh(r1, r4);
        r3 = com.google.android.gms.internal.vision.zzfe.zzk(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0080:
        r4 = r0.zza(r1, r3, r12);
        if (r4 == 0) goto L_0x0551;
    L_0x0086:
        r3 = com.google.android.gms.internal.vision.zzfe.zzh(r3, r9);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x008d:
        r4 = r0.zza(r1, r3, r12);
        if (r4 == 0) goto L_0x0551;
    L_0x0093:
        r3 = com.google.android.gms.internal.vision.zzfe.zzm(r3, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x009a:
        r14 = r0.zza(r1, r3, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x00a0:
        r4 = zzh(r1, r4);
        r3 = com.google.android.gms.internal.vision.zzfe.zzn(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x00ab:
        r14 = r0.zza(r1, r3, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x00b1:
        r4 = zzh(r1, r4);
        r3 = com.google.android.gms.internal.vision.zzfe.zzj(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x00bc:
        r14 = r0.zza(r1, r3, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x00c2:
        r4 = com.google.android.gms.internal.vision.zziu.zzp(r1, r4);
        r4 = (com.google.android.gms.internal.vision.zzeo) r4;
        r3 = com.google.android.gms.internal.vision.zzfe.zzc(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x00cf:
        r14 = r0.zza(r1, r3, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x00d5:
        r4 = com.google.android.gms.internal.vision.zziu.zzp(r1, r4);
        r5 = r0.zzbh(r12);
        r3 = com.google.android.gms.internal.vision.zzhy.zzc(r3, r4, r5);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x00e4:
        r14 = r0.zza(r1, r3, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x00ea:
        r4 = com.google.android.gms.internal.vision.zziu.zzp(r1, r4);
        r5 = r4 instanceof com.google.android.gms.internal.vision.zzeo;
        if (r5 == 0) goto L_0x00fb;
    L_0x00f2:
        r4 = (com.google.android.gms.internal.vision.zzeo) r4;
        r3 = com.google.android.gms.internal.vision.zzfe.zzc(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x00fb:
        r4 = (java.lang.String) r4;
        r3 = com.google.android.gms.internal.vision.zzfe.zzb(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0104:
        r4 = r0.zza(r1, r3, r12);
        if (r4 == 0) goto L_0x0551;
    L_0x010a:
        r3 = com.google.android.gms.internal.vision.zzfe.zzc(r3, r7);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0111:
        r4 = r0.zza(r1, r3, r12);
        if (r4 == 0) goto L_0x0551;
    L_0x0117:
        r3 = com.google.android.gms.internal.vision.zzfe.zzl(r3, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x011e:
        r4 = r0.zza(r1, r3, r12);
        if (r4 == 0) goto L_0x0551;
    L_0x0124:
        r3 = com.google.android.gms.internal.vision.zzfe.zzg(r3, r9);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x012b:
        r14 = r0.zza(r1, r3, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x0131:
        r4 = zzh(r1, r4);
        r3 = com.google.android.gms.internal.vision.zzfe.zzi(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x013c:
        r14 = r0.zza(r1, r3, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x0142:
        r4 = zzi(r1, r4);
        r3 = com.google.android.gms.internal.vision.zzfe.zze(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x014d:
        r14 = r0.zza(r1, r3, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x0153:
        r4 = zzi(r1, r4);
        r3 = com.google.android.gms.internal.vision.zzfe.zzd(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x015e:
        r4 = r0.zza(r1, r3, r12);
        if (r4 == 0) goto L_0x0551;
    L_0x0164:
        r3 = com.google.android.gms.internal.vision.zzfe.zzb(r3, r6);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x016b:
        r4 = r0.zza(r1, r3, r12);
        if (r4 == 0) goto L_0x0551;
    L_0x0171:
        r4 = 0;
        r3 = com.google.android.gms.internal.vision.zzfe.zzb(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x017a:
        r14 = r0.zzzt;
        r4 = com.google.android.gms.internal.vision.zziu.zzp(r1, r4);
        r5 = r0.zzbi(r12);
        r3 = r14.zzb(r3, r4, r5);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x018b:
        r4 = zze(r1, r4);
        r5 = r0.zzbh(r12);
        r3 = com.google.android.gms.internal.vision.zzhy.zzd(r3, r4, r5);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x019a:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.vision.zzhy.zzs(r4);
        if (r4 <= 0) goto L_0x0551;
    L_0x01a6:
        r5 = r0.zzzl;
        if (r5 == 0) goto L_0x01ae;
    L_0x01aa:
        r14 = (long) r14;
        r2.putInt(r1, r14, r4);
    L_0x01ae:
        r3 = com.google.android.gms.internal.vision.zzfe.zzav(r3);
        r5 = com.google.android.gms.internal.vision.zzfe.zzax(r4);
        r3 = r3 + r5;
        r3 = r3 + r4;
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x01bb:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.vision.zzhy.zzw(r4);
        if (r4 <= 0) goto L_0x0551;
    L_0x01c7:
        r5 = r0.zzzl;
        if (r5 == 0) goto L_0x01cf;
    L_0x01cb:
        r14 = (long) r14;
        r2.putInt(r1, r14, r4);
    L_0x01cf:
        r3 = com.google.android.gms.internal.vision.zzfe.zzav(r3);
        r5 = com.google.android.gms.internal.vision.zzfe.zzax(r4);
        r3 = r3 + r5;
        r3 = r3 + r4;
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x01dc:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.vision.zzhy.zzy(r4);
        if (r4 <= 0) goto L_0x0551;
    L_0x01e8:
        r5 = r0.zzzl;
        if (r5 == 0) goto L_0x01f0;
    L_0x01ec:
        r14 = (long) r14;
        r2.putInt(r1, r14, r4);
    L_0x01f0:
        r3 = com.google.android.gms.internal.vision.zzfe.zzav(r3);
        r5 = com.google.android.gms.internal.vision.zzfe.zzax(r4);
        r3 = r3 + r5;
        r3 = r3 + r4;
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x01fd:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.vision.zzhy.zzx(r4);
        if (r4 <= 0) goto L_0x0551;
    L_0x0209:
        r5 = r0.zzzl;
        if (r5 == 0) goto L_0x0211;
    L_0x020d:
        r14 = (long) r14;
        r2.putInt(r1, r14, r4);
    L_0x0211:
        r3 = com.google.android.gms.internal.vision.zzfe.zzav(r3);
        r5 = com.google.android.gms.internal.vision.zzfe.zzax(r4);
        r3 = r3 + r5;
        r3 = r3 + r4;
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x021e:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.vision.zzhy.zzt(r4);
        if (r4 <= 0) goto L_0x0551;
    L_0x022a:
        r5 = r0.zzzl;
        if (r5 == 0) goto L_0x0232;
    L_0x022e:
        r14 = (long) r14;
        r2.putInt(r1, r14, r4);
    L_0x0232:
        r3 = com.google.android.gms.internal.vision.zzfe.zzav(r3);
        r5 = com.google.android.gms.internal.vision.zzfe.zzax(r4);
        r3 = r3 + r5;
        r3 = r3 + r4;
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x023f:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.vision.zzhy.zzv(r4);
        if (r4 <= 0) goto L_0x0551;
    L_0x024b:
        r5 = r0.zzzl;
        if (r5 == 0) goto L_0x0253;
    L_0x024f:
        r14 = (long) r14;
        r2.putInt(r1, r14, r4);
    L_0x0253:
        r3 = com.google.android.gms.internal.vision.zzfe.zzav(r3);
        r5 = com.google.android.gms.internal.vision.zzfe.zzax(r4);
        r3 = r3 + r5;
        r3 = r3 + r4;
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0260:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.vision.zzhy.zzz(r4);
        if (r4 <= 0) goto L_0x0551;
    L_0x026c:
        r5 = r0.zzzl;
        if (r5 == 0) goto L_0x0274;
    L_0x0270:
        r14 = (long) r14;
        r2.putInt(r1, r14, r4);
    L_0x0274:
        r3 = com.google.android.gms.internal.vision.zzfe.zzav(r3);
        r5 = com.google.android.gms.internal.vision.zzfe.zzax(r4);
        r3 = r3 + r5;
        r3 = r3 + r4;
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0281:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.vision.zzhy.zzx(r4);
        if (r4 <= 0) goto L_0x0551;
    L_0x028d:
        r5 = r0.zzzl;
        if (r5 == 0) goto L_0x0295;
    L_0x0291:
        r14 = (long) r14;
        r2.putInt(r1, r14, r4);
    L_0x0295:
        r3 = com.google.android.gms.internal.vision.zzfe.zzav(r3);
        r5 = com.google.android.gms.internal.vision.zzfe.zzax(r4);
        r3 = r3 + r5;
        r3 = r3 + r4;
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x02a2:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.vision.zzhy.zzy(r4);
        if (r4 <= 0) goto L_0x0551;
    L_0x02ae:
        r5 = r0.zzzl;
        if (r5 == 0) goto L_0x02b6;
    L_0x02b2:
        r14 = (long) r14;
        r2.putInt(r1, r14, r4);
    L_0x02b6:
        r3 = com.google.android.gms.internal.vision.zzfe.zzav(r3);
        r5 = com.google.android.gms.internal.vision.zzfe.zzax(r4);
        r3 = r3 + r5;
        r3 = r3 + r4;
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x02c3:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.vision.zzhy.zzu(r4);
        if (r4 <= 0) goto L_0x0551;
    L_0x02cf:
        r5 = r0.zzzl;
        if (r5 == 0) goto L_0x02d7;
    L_0x02d3:
        r14 = (long) r14;
        r2.putInt(r1, r14, r4);
    L_0x02d7:
        r3 = com.google.android.gms.internal.vision.zzfe.zzav(r3);
        r5 = com.google.android.gms.internal.vision.zzfe.zzax(r4);
        r3 = r3 + r5;
        r3 = r3 + r4;
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x02e4:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.vision.zzhy.zzr(r4);
        if (r4 <= 0) goto L_0x0551;
    L_0x02f0:
        r5 = r0.zzzl;
        if (r5 == 0) goto L_0x02f8;
    L_0x02f4:
        r14 = (long) r14;
        r2.putInt(r1, r14, r4);
    L_0x02f8:
        r3 = com.google.android.gms.internal.vision.zzfe.zzav(r3);
        r5 = com.google.android.gms.internal.vision.zzfe.zzax(r4);
        r3 = r3 + r5;
        r3 = r3 + r4;
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0305:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.vision.zzhy.zzq(r4);
        if (r4 <= 0) goto L_0x0551;
    L_0x0311:
        r5 = r0.zzzl;
        if (r5 == 0) goto L_0x0319;
    L_0x0315:
        r14 = (long) r14;
        r2.putInt(r1, r14, r4);
    L_0x0319:
        r3 = com.google.android.gms.internal.vision.zzfe.zzav(r3);
        r5 = com.google.android.gms.internal.vision.zzfe.zzax(r4);
        r3 = r3 + r5;
        r3 = r3 + r4;
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0326:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.vision.zzhy.zzx(r4);
        if (r4 <= 0) goto L_0x0551;
    L_0x0332:
        r5 = r0.zzzl;
        if (r5 == 0) goto L_0x033a;
    L_0x0336:
        r14 = (long) r14;
        r2.putInt(r1, r14, r4);
    L_0x033a:
        r3 = com.google.android.gms.internal.vision.zzfe.zzav(r3);
        r5 = com.google.android.gms.internal.vision.zzfe.zzax(r4);
        r3 = r3 + r5;
        r3 = r3 + r4;
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0347:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.vision.zzhy.zzy(r4);
        if (r4 <= 0) goto L_0x0551;
    L_0x0353:
        r5 = r0.zzzl;
        if (r5 == 0) goto L_0x035b;
    L_0x0357:
        r14 = (long) r14;
        r2.putInt(r1, r14, r4);
    L_0x035b:
        r3 = com.google.android.gms.internal.vision.zzfe.zzav(r3);
        r5 = com.google.android.gms.internal.vision.zzfe.zzax(r4);
        r3 = r3 + r5;
        r3 = r3 + r4;
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0368:
        r4 = zze(r1, r4);
        r3 = com.google.android.gms.internal.vision.zzhy.zzq(r3, r4, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0373:
        r4 = zze(r1, r4);
        r3 = com.google.android.gms.internal.vision.zzhy.zzu(r3, r4, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x037e:
        r4 = zze(r1, r4);
        r3 = com.google.android.gms.internal.vision.zzhy.zzw(r3, r4, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0389:
        r4 = zze(r1, r4);
        r3 = com.google.android.gms.internal.vision.zzhy.zzv(r3, r4, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0394:
        r4 = zze(r1, r4);
        r3 = com.google.android.gms.internal.vision.zzhy.zzr(r3, r4, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x039f:
        r4 = zze(r1, r4);
        r3 = com.google.android.gms.internal.vision.zzhy.zzt(r3, r4, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x03aa:
        r4 = zze(r1, r4);
        r3 = com.google.android.gms.internal.vision.zzhy.zzd(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x03b5:
        r4 = zze(r1, r4);
        r5 = r0.zzbh(r12);
        r3 = com.google.android.gms.internal.vision.zzhy.zzc(r3, r4, r5);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x03c4:
        r4 = zze(r1, r4);
        r3 = com.google.android.gms.internal.vision.zzhy.zzc(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x03cf:
        r4 = zze(r1, r4);
        r3 = com.google.android.gms.internal.vision.zzhy.zzx(r3, r4, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x03da:
        r4 = zze(r1, r4);
        r3 = com.google.android.gms.internal.vision.zzhy.zzv(r3, r4, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x03e5:
        r4 = zze(r1, r4);
        r3 = com.google.android.gms.internal.vision.zzhy.zzw(r3, r4, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x03f0:
        r4 = zze(r1, r4);
        r3 = com.google.android.gms.internal.vision.zzhy.zzs(r3, r4, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x03fb:
        r4 = zze(r1, r4);
        r3 = com.google.android.gms.internal.vision.zzhy.zzp(r3, r4, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0406:
        r4 = zze(r1, r4);
        r3 = com.google.android.gms.internal.vision.zzhy.zzo(r3, r4, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0411:
        r4 = zze(r1, r4);
        r3 = com.google.android.gms.internal.vision.zzhy.zzv(r3, r4, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x041c:
        r4 = zze(r1, r4);
        r3 = com.google.android.gms.internal.vision.zzhy.zzw(r3, r4, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0427:
        r14 = r0.zza(r1, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x042d:
        r4 = com.google.android.gms.internal.vision.zziu.zzp(r1, r4);
        r4 = (com.google.android.gms.internal.vision.zzhf) r4;
        r5 = r0.zzbh(r12);
        r3 = com.google.android.gms.internal.vision.zzfe.zzc(r3, r4, r5);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x043e:
        r14 = r0.zza(r1, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x0444:
        r4 = com.google.android.gms.internal.vision.zziu.zzl(r1, r4);
        r3 = com.google.android.gms.internal.vision.zzfe.zzf(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x044f:
        r14 = r0.zza(r1, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x0455:
        r4 = com.google.android.gms.internal.vision.zziu.zzk(r1, r4);
        r3 = com.google.android.gms.internal.vision.zzfe.zzk(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0460:
        r4 = r0.zza(r1, r12);
        if (r4 == 0) goto L_0x0551;
    L_0x0466:
        r3 = com.google.android.gms.internal.vision.zzfe.zzh(r3, r9);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x046d:
        r4 = r0.zza(r1, r12);
        if (r4 == 0) goto L_0x0551;
    L_0x0473:
        r3 = com.google.android.gms.internal.vision.zzfe.zzm(r3, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x047a:
        r14 = r0.zza(r1, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x0480:
        r4 = com.google.android.gms.internal.vision.zziu.zzk(r1, r4);
        r3 = com.google.android.gms.internal.vision.zzfe.zzn(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x048b:
        r14 = r0.zza(r1, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x0491:
        r4 = com.google.android.gms.internal.vision.zziu.zzk(r1, r4);
        r3 = com.google.android.gms.internal.vision.zzfe.zzj(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x049c:
        r14 = r0.zza(r1, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x04a2:
        r4 = com.google.android.gms.internal.vision.zziu.zzp(r1, r4);
        r4 = (com.google.android.gms.internal.vision.zzeo) r4;
        r3 = com.google.android.gms.internal.vision.zzfe.zzc(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x04af:
        r14 = r0.zza(r1, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x04b5:
        r4 = com.google.android.gms.internal.vision.zziu.zzp(r1, r4);
        r5 = r0.zzbh(r12);
        r3 = com.google.android.gms.internal.vision.zzhy.zzc(r3, r4, r5);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x04c4:
        r14 = r0.zza(r1, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x04ca:
        r4 = com.google.android.gms.internal.vision.zziu.zzp(r1, r4);
        r5 = r4 instanceof com.google.android.gms.internal.vision.zzeo;
        if (r5 == 0) goto L_0x04db;
    L_0x04d2:
        r4 = (com.google.android.gms.internal.vision.zzeo) r4;
        r3 = com.google.android.gms.internal.vision.zzfe.zzc(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x04db:
        r4 = (java.lang.String) r4;
        r3 = com.google.android.gms.internal.vision.zzfe.zzb(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x04e4:
        r4 = r0.zza(r1, r12);
        if (r4 == 0) goto L_0x0551;
    L_0x04ea:
        r3 = com.google.android.gms.internal.vision.zzfe.zzc(r3, r7);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x04f0:
        r4 = r0.zza(r1, r12);
        if (r4 == 0) goto L_0x0551;
    L_0x04f6:
        r3 = com.google.android.gms.internal.vision.zzfe.zzl(r3, r11);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x04fc:
        r4 = r0.zza(r1, r12);
        if (r4 == 0) goto L_0x0551;
    L_0x0502:
        r3 = com.google.android.gms.internal.vision.zzfe.zzg(r3, r9);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0508:
        r14 = r0.zza(r1, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x050e:
        r4 = com.google.android.gms.internal.vision.zziu.zzk(r1, r4);
        r3 = com.google.android.gms.internal.vision.zzfe.zzi(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0518:
        r14 = r0.zza(r1, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x051e:
        r4 = com.google.android.gms.internal.vision.zziu.zzl(r1, r4);
        r3 = com.google.android.gms.internal.vision.zzfe.zze(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0528:
        r14 = r0.zza(r1, r12);
        if (r14 == 0) goto L_0x0551;
    L_0x052e:
        r4 = com.google.android.gms.internal.vision.zziu.zzl(r1, r4);
        r3 = com.google.android.gms.internal.vision.zzfe.zzd(r3, r4);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0538:
        r4 = r0.zza(r1, r12);
        if (r4 == 0) goto L_0x0551;
    L_0x053e:
        r3 = com.google.android.gms.internal.vision.zzfe.zzb(r3, r6);
        r13 = r13 + r3;
        goto L_0x0551;
    L_0x0544:
        r4 = r0.zza(r1, r12);
        if (r4 == 0) goto L_0x0551;
    L_0x054a:
        r4 = 0;
        r3 = com.google.android.gms.internal.vision.zzfe.zzb(r3, r4);
        r13 = r13 + r3;
    L_0x0551:
        r12 = r12 + 3;
        r3 = 267386880; // 0xff00000 float:2.3665827E-29 double:1.321066716E-315;
        goto L_0x0016;
    L_0x0557:
        r2 = r0.zzzr;
        r1 = zza(r2, r1);
        r13 = r13 + r1;
        return r13;
    L_0x055f:
        r2 = zzzc;
        r3 = -1;
        r5 = r3;
        r3 = r11;
        r4 = r3;
        r12 = r4;
    L_0x0566:
        r13 = r0.zzzd;
        r13 = r13.length;
        if (r3 >= r13) goto L_0x0af8;
    L_0x056b:
        r13 = r0.zzbk(r3);
        r14 = r0.zzzd;
        r14 = r14[r3];
        r15 = 267386880; // 0xff00000 float:2.3665827E-29 double:1.321066716E-315;
        r16 = r13 & r15;
        r15 = r16 >>> 20;
        r6 = 17;
        if (r15 > r6) goto L_0x0592;
    L_0x057d:
        r6 = r0.zzzd;
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
        r6 = r0.zzzl;
        if (r6 == 0) goto L_0x05b0;
    L_0x0596:
        r6 = com.google.android.gms.internal.vision.zzfs.DOUBLE_LIST_PACKED;
        r6 = r6.id();
        if (r15 < r6) goto L_0x05b0;
    L_0x059e:
        r6 = com.google.android.gms.internal.vision.zzfs.SINT64_LIST_PACKED;
        r6 = r6.id();
        if (r15 > r6) goto L_0x05b0;
    L_0x05a6:
        r6 = r0.zzzd;
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
        r6 = (com.google.android.gms.internal.vision.zzhf) r6;
        r9 = r0.zzbh(r3);
        r6 = com.google.android.gms.internal.vision.zzfe.zzc(r14, r6, r9);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x05d2:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x05d8:
        r9 = zzi(r1, r9);
        r6 = com.google.android.gms.internal.vision.zzfe.zzf(r14, r9);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x05e3:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x05e9:
        r6 = zzh(r1, r9);
        r6 = com.google.android.gms.internal.vision.zzfe.zzk(r14, r6);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x05f4:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x05fa:
        r9 = 0;
        r6 = com.google.android.gms.internal.vision.zzfe.zzh(r14, r9);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0603:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x0609:
        r6 = 0;
        r9 = com.google.android.gms.internal.vision.zzfe.zzm(r14, r6);
        r4 = r4 + r9;
        goto L_0x09cb;
    L_0x0611:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x0617:
        r6 = zzh(r1, r9);
        r6 = com.google.android.gms.internal.vision.zzfe.zzn(r14, r6);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0622:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x0628:
        r6 = zzh(r1, r9);
        r6 = com.google.android.gms.internal.vision.zzfe.zzj(r14, r6);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0633:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x0639:
        r6 = r2.getObject(r1, r9);
        r6 = (com.google.android.gms.internal.vision.zzeo) r6;
        r6 = com.google.android.gms.internal.vision.zzfe.zzc(r14, r6);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0646:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x064c:
        r6 = r2.getObject(r1, r9);
        r9 = r0.zzbh(r3);
        r6 = com.google.android.gms.internal.vision.zzhy.zzc(r14, r6, r9);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x065b:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x0661:
        r6 = r2.getObject(r1, r9);
        r9 = r6 instanceof com.google.android.gms.internal.vision.zzeo;
        if (r9 == 0) goto L_0x0672;
    L_0x0669:
        r6 = (com.google.android.gms.internal.vision.zzeo) r6;
        r6 = com.google.android.gms.internal.vision.zzfe.zzc(r14, r6);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0672:
        r6 = (java.lang.String) r6;
        r6 = com.google.android.gms.internal.vision.zzfe.zzb(r14, r6);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x067b:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x0681:
        r6 = com.google.android.gms.internal.vision.zzfe.zzc(r14, r7);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0688:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x068e:
        r6 = 0;
        r9 = com.google.android.gms.internal.vision.zzfe.zzl(r14, r6);
        r4 = r4 + r9;
        goto L_0x09cb;
    L_0x0696:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x069c:
        r9 = 0;
        r6 = com.google.android.gms.internal.vision.zzfe.zzg(r14, r9);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x06a5:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x06ab:
        r6 = zzh(r1, r9);
        r6 = com.google.android.gms.internal.vision.zzfe.zzi(r14, r6);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x06b6:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x06bc:
        r9 = zzi(r1, r9);
        r6 = com.google.android.gms.internal.vision.zzfe.zze(r14, r9);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x06c7:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x06cd:
        r9 = zzi(r1, r9);
        r6 = com.google.android.gms.internal.vision.zzfe.zzd(r14, r9);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x06d8:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x06de:
        r6 = 0;
        r9 = com.google.android.gms.internal.vision.zzfe.zzb(r14, r6);
        r4 = r4 + r9;
        goto L_0x09cb;
    L_0x06e6:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x09cb;
    L_0x06ec:
        r9 = 0;
        r6 = com.google.android.gms.internal.vision.zzfe.zzb(r14, r9);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x06f5:
        r6 = r0.zzzt;
        r9 = r2.getObject(r1, r9);
        r10 = r0.zzbi(r3);
        r6 = r6.zzb(r14, r9, r10);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0706:
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r9 = r0.zzbh(r3);
        r6 = com.google.android.gms.internal.vision.zzhy.zzd(r14, r6, r9);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0717:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.vision.zzhy.zzs(r9);
        if (r9 <= 0) goto L_0x09cb;
    L_0x0723:
        r10 = r0.zzzl;
        if (r10 == 0) goto L_0x072b;
    L_0x0727:
        r10 = (long) r6;
        r2.putInt(r1, r10, r9);
    L_0x072b:
        r6 = com.google.android.gms.internal.vision.zzfe.zzav(r14);
        r10 = com.google.android.gms.internal.vision.zzfe.zzax(r9);
        r6 = r6 + r10;
        r6 = r6 + r9;
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0738:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.vision.zzhy.zzw(r9);
        if (r9 <= 0) goto L_0x09cb;
    L_0x0744:
        r10 = r0.zzzl;
        if (r10 == 0) goto L_0x074c;
    L_0x0748:
        r10 = (long) r6;
        r2.putInt(r1, r10, r9);
    L_0x074c:
        r6 = com.google.android.gms.internal.vision.zzfe.zzav(r14);
        r10 = com.google.android.gms.internal.vision.zzfe.zzax(r9);
        r6 = r6 + r10;
        r6 = r6 + r9;
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0759:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.vision.zzhy.zzy(r9);
        if (r9 <= 0) goto L_0x09cb;
    L_0x0765:
        r10 = r0.zzzl;
        if (r10 == 0) goto L_0x076d;
    L_0x0769:
        r10 = (long) r6;
        r2.putInt(r1, r10, r9);
    L_0x076d:
        r6 = com.google.android.gms.internal.vision.zzfe.zzav(r14);
        r10 = com.google.android.gms.internal.vision.zzfe.zzax(r9);
        r6 = r6 + r10;
        r6 = r6 + r9;
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x077a:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.vision.zzhy.zzx(r9);
        if (r9 <= 0) goto L_0x09cb;
    L_0x0786:
        r10 = r0.zzzl;
        if (r10 == 0) goto L_0x078e;
    L_0x078a:
        r10 = (long) r6;
        r2.putInt(r1, r10, r9);
    L_0x078e:
        r6 = com.google.android.gms.internal.vision.zzfe.zzav(r14);
        r10 = com.google.android.gms.internal.vision.zzfe.zzax(r9);
        r6 = r6 + r10;
        r6 = r6 + r9;
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x079b:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.vision.zzhy.zzt(r9);
        if (r9 <= 0) goto L_0x09cb;
    L_0x07a7:
        r10 = r0.zzzl;
        if (r10 == 0) goto L_0x07af;
    L_0x07ab:
        r10 = (long) r6;
        r2.putInt(r1, r10, r9);
    L_0x07af:
        r6 = com.google.android.gms.internal.vision.zzfe.zzav(r14);
        r10 = com.google.android.gms.internal.vision.zzfe.zzax(r9);
        r6 = r6 + r10;
        r6 = r6 + r9;
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x07bc:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.vision.zzhy.zzv(r9);
        if (r9 <= 0) goto L_0x09cb;
    L_0x07c8:
        r10 = r0.zzzl;
        if (r10 == 0) goto L_0x07d0;
    L_0x07cc:
        r10 = (long) r6;
        r2.putInt(r1, r10, r9);
    L_0x07d0:
        r6 = com.google.android.gms.internal.vision.zzfe.zzav(r14);
        r10 = com.google.android.gms.internal.vision.zzfe.zzax(r9);
        r6 = r6 + r10;
        r6 = r6 + r9;
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x07dd:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.vision.zzhy.zzz(r9);
        if (r9 <= 0) goto L_0x09cb;
    L_0x07e9:
        r10 = r0.zzzl;
        if (r10 == 0) goto L_0x07f1;
    L_0x07ed:
        r10 = (long) r6;
        r2.putInt(r1, r10, r9);
    L_0x07f1:
        r6 = com.google.android.gms.internal.vision.zzfe.zzav(r14);
        r10 = com.google.android.gms.internal.vision.zzfe.zzax(r9);
        r6 = r6 + r10;
        r6 = r6 + r9;
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x07fe:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.vision.zzhy.zzx(r9);
        if (r9 <= 0) goto L_0x09cb;
    L_0x080a:
        r10 = r0.zzzl;
        if (r10 == 0) goto L_0x0812;
    L_0x080e:
        r10 = (long) r6;
        r2.putInt(r1, r10, r9);
    L_0x0812:
        r6 = com.google.android.gms.internal.vision.zzfe.zzav(r14);
        r10 = com.google.android.gms.internal.vision.zzfe.zzax(r9);
        r6 = r6 + r10;
        r6 = r6 + r9;
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x081f:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.vision.zzhy.zzy(r9);
        if (r9 <= 0) goto L_0x09cb;
    L_0x082b:
        r10 = r0.zzzl;
        if (r10 == 0) goto L_0x0833;
    L_0x082f:
        r10 = (long) r6;
        r2.putInt(r1, r10, r9);
    L_0x0833:
        r6 = com.google.android.gms.internal.vision.zzfe.zzav(r14);
        r10 = com.google.android.gms.internal.vision.zzfe.zzax(r9);
        r6 = r6 + r10;
        r6 = r6 + r9;
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0840:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.vision.zzhy.zzu(r9);
        if (r9 <= 0) goto L_0x09cb;
    L_0x084c:
        r10 = r0.zzzl;
        if (r10 == 0) goto L_0x0854;
    L_0x0850:
        r10 = (long) r6;
        r2.putInt(r1, r10, r9);
    L_0x0854:
        r6 = com.google.android.gms.internal.vision.zzfe.zzav(r14);
        r10 = com.google.android.gms.internal.vision.zzfe.zzax(r9);
        r6 = r6 + r10;
        r6 = r6 + r9;
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0861:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.vision.zzhy.zzr(r9);
        if (r9 <= 0) goto L_0x09cb;
    L_0x086d:
        r10 = r0.zzzl;
        if (r10 == 0) goto L_0x0875;
    L_0x0871:
        r10 = (long) r6;
        r2.putInt(r1, r10, r9);
    L_0x0875:
        r6 = com.google.android.gms.internal.vision.zzfe.zzav(r14);
        r10 = com.google.android.gms.internal.vision.zzfe.zzax(r9);
        r6 = r6 + r10;
        r6 = r6 + r9;
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0882:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.vision.zzhy.zzq(r9);
        if (r9 <= 0) goto L_0x09cb;
    L_0x088e:
        r10 = r0.zzzl;
        if (r10 == 0) goto L_0x0896;
    L_0x0892:
        r10 = (long) r6;
        r2.putInt(r1, r10, r9);
    L_0x0896:
        r6 = com.google.android.gms.internal.vision.zzfe.zzav(r14);
        r10 = com.google.android.gms.internal.vision.zzfe.zzax(r9);
        r6 = r6 + r10;
        r6 = r6 + r9;
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x08a3:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.vision.zzhy.zzx(r9);
        if (r9 <= 0) goto L_0x09cb;
    L_0x08af:
        r10 = r0.zzzl;
        if (r10 == 0) goto L_0x08b7;
    L_0x08b3:
        r10 = (long) r6;
        r2.putInt(r1, r10, r9);
    L_0x08b7:
        r6 = com.google.android.gms.internal.vision.zzfe.zzav(r14);
        r10 = com.google.android.gms.internal.vision.zzfe.zzax(r9);
        r6 = r6 + r10;
        r6 = r6 + r9;
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x08c4:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.vision.zzhy.zzy(r9);
        if (r9 <= 0) goto L_0x09cb;
    L_0x08d0:
        r10 = r0.zzzl;
        if (r10 == 0) goto L_0x08d8;
    L_0x08d4:
        r10 = (long) r6;
        r2.putInt(r1, r10, r9);
    L_0x08d8:
        r6 = com.google.android.gms.internal.vision.zzfe.zzav(r14);
        r10 = com.google.android.gms.internal.vision.zzfe.zzax(r9);
        r6 = r6 + r10;
        r6 = r6 + r9;
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x08e5:
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r11 = 0;
        r6 = com.google.android.gms.internal.vision.zzhy.zzq(r14, r6, r11);
        r4 = r4 + r6;
        goto L_0x09bd;
    L_0x08f3:
        r11 = 0;
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.vision.zzhy.zzu(r14, r6, r11);
        r4 = r4 + r6;
        goto L_0x09bd;
    L_0x0901:
        r11 = 0;
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.vision.zzhy.zzw(r14, r6, r11);
        r4 = r4 + r6;
        goto L_0x09bd;
    L_0x090f:
        r11 = 0;
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.vision.zzhy.zzv(r14, r6, r11);
        r4 = r4 + r6;
        goto L_0x09bd;
    L_0x091d:
        r11 = 0;
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.vision.zzhy.zzr(r14, r6, r11);
        r4 = r4 + r6;
        goto L_0x09bd;
    L_0x092b:
        r11 = 0;
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.vision.zzhy.zzt(r14, r6, r11);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0939:
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.vision.zzhy.zzd(r14, r6);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0946:
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r9 = r0.zzbh(r3);
        r6 = com.google.android.gms.internal.vision.zzhy.zzc(r14, r6, r9);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0957:
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.vision.zzhy.zzc(r14, r6);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0963:
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r11 = 0;
        r6 = com.google.android.gms.internal.vision.zzhy.zzx(r14, r6, r11);
        r4 = r4 + r6;
        goto L_0x09bd;
    L_0x0970:
        r11 = 0;
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.vision.zzhy.zzv(r14, r6, r11);
        r4 = r4 + r6;
        goto L_0x09bd;
    L_0x097d:
        r11 = 0;
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.vision.zzhy.zzw(r14, r6, r11);
        r4 = r4 + r6;
        goto L_0x09bd;
    L_0x098a:
        r11 = 0;
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.vision.zzhy.zzs(r14, r6, r11);
        r4 = r4 + r6;
        goto L_0x09bd;
    L_0x0997:
        r11 = 0;
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.vision.zzhy.zzp(r14, r6, r11);
        r4 = r4 + r6;
        goto L_0x09bd;
    L_0x09a4:
        r11 = 0;
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.vision.zzhy.zzo(r14, r6, r11);
        r4 = r4 + r6;
        goto L_0x09bd;
    L_0x09b1:
        r11 = 0;
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.vision.zzhy.zzv(r14, r6, r11);
        r4 = r4 + r6;
    L_0x09bd:
        r6 = r11;
        goto L_0x09cc;
    L_0x09bf:
        r11 = 0;
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.vision.zzhy.zzw(r14, r6, r11);
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
        r6 = (com.google.android.gms.internal.vision.zzhf) r6;
        r9 = r0.zzbh(r3);
        r6 = com.google.android.gms.internal.vision.zzfe.zzc(r14, r6, r9);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x09e7:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x09cb;
    L_0x09eb:
        r9 = r2.getLong(r1, r9);
        r6 = com.google.android.gms.internal.vision.zzfe.zzf(r14, r9);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x09f5:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x09cb;
    L_0x09f9:
        r6 = r2.getInt(r1, r9);
        r6 = com.google.android.gms.internal.vision.zzfe.zzk(r14, r6);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0a03:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x09cb;
    L_0x0a07:
        r9 = 0;
        r6 = com.google.android.gms.internal.vision.zzfe.zzh(r14, r9);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0a0f:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x09cb;
    L_0x0a13:
        r6 = 0;
        r9 = com.google.android.gms.internal.vision.zzfe.zzm(r14, r6);
        r4 = r4 + r9;
        goto L_0x09cb;
    L_0x0a1a:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x09cb;
    L_0x0a1e:
        r6 = r2.getInt(r1, r9);
        r6 = com.google.android.gms.internal.vision.zzfe.zzn(r14, r6);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0a28:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x09cb;
    L_0x0a2c:
        r6 = r2.getInt(r1, r9);
        r6 = com.google.android.gms.internal.vision.zzfe.zzj(r14, r6);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0a36:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x09cb;
    L_0x0a3a:
        r6 = r2.getObject(r1, r9);
        r6 = (com.google.android.gms.internal.vision.zzeo) r6;
        r6 = com.google.android.gms.internal.vision.zzfe.zzc(r14, r6);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0a46:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x09cb;
    L_0x0a4a:
        r6 = r2.getObject(r1, r9);
        r9 = r0.zzbh(r3);
        r6 = com.google.android.gms.internal.vision.zzhy.zzc(r14, r6, r9);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0a59:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x09cb;
    L_0x0a5d:
        r6 = r2.getObject(r1, r9);
        r9 = r6 instanceof com.google.android.gms.internal.vision.zzeo;
        if (r9 == 0) goto L_0x0a6e;
    L_0x0a65:
        r6 = (com.google.android.gms.internal.vision.zzeo) r6;
        r6 = com.google.android.gms.internal.vision.zzfe.zzc(r14, r6);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0a6e:
        r6 = (java.lang.String) r6;
        r6 = com.google.android.gms.internal.vision.zzfe.zzb(r14, r6);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0a77:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x09cb;
    L_0x0a7b:
        r6 = com.google.android.gms.internal.vision.zzfe.zzc(r14, r7);
        r4 = r4 + r6;
        goto L_0x09cb;
    L_0x0a82:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x09cb;
    L_0x0a86:
        r6 = 0;
        r9 = com.google.android.gms.internal.vision.zzfe.zzl(r14, r6);
        r4 = r4 + r9;
        goto L_0x09cc;
    L_0x0a8e:
        r6 = 0;
        r9 = r12 & r16;
        if (r9 == 0) goto L_0x09cc;
    L_0x0a93:
        r9 = 0;
        r11 = com.google.android.gms.internal.vision.zzfe.zzg(r14, r9);
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
        r9 = com.google.android.gms.internal.vision.zzfe.zzi(r14, r9);
        r4 = r4 + r9;
        goto L_0x0acf;
    L_0x0aae:
        r6 = 0;
        r18 = 0;
        r11 = r12 & r16;
        if (r11 == 0) goto L_0x0acf;
    L_0x0ab5:
        r9 = r2.getLong(r1, r9);
        r9 = com.google.android.gms.internal.vision.zzfe.zze(r14, r9);
        r4 = r4 + r9;
        goto L_0x0acf;
    L_0x0abf:
        r6 = 0;
        r18 = 0;
        r11 = r12 & r16;
        if (r11 == 0) goto L_0x0acf;
    L_0x0ac6:
        r9 = r2.getLong(r1, r9);
        r9 = com.google.android.gms.internal.vision.zzfe.zzd(r14, r9);
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
        r10 = com.google.android.gms.internal.vision.zzfe.zzb(r14, r9);
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
        r13 = com.google.android.gms.internal.vision.zzfe.zzb(r14, r10);
        r4 = r4 + r13;
    L_0x0af0:
        r3 = r3 + 3;
        r11 = r6;
        r6 = r9;
        r9 = r18;
        goto L_0x0566;
    L_0x0af8:
        r2 = r0.zzzr;
        r2 = zza(r2, r1);
        r4 = r4 + r2;
        r2 = r0.zzzi;
        if (r2 == 0) goto L_0x0b0e;
    L_0x0b03:
        r2 = r0.zzzs;
        r1 = r2.zzc(r1);
        r1 = r1.zzeq();
        r4 = r4 + r1;
    L_0x0b0e:
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhj.zzp(java.lang.Object):int");
    }

    private static <UT, UB> int zza(zzio<UT, UB> zzio, T t) {
        return zzio.zzp(zzio.zzt(t));
    }

    private static <E> List<E> zze(Object obj, long j) {
        return (List) zziu.zzp(obj, j);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x0511  */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x054f  */
    /* JADX WARNING: Removed duplicated region for block: B:331:0x0a27  */
    public final void zza(T r14, com.google.android.gms.internal.vision.zzjj r15) throws java.io.IOException {
        /*
        r13 = this;
        r0 = r15.zzed();
        r1 = com.google.android.gms.internal.vision.zzfy.zzg.zzxj;
        r2 = 267386880; // 0xff00000 float:2.3665827E-29 double:1.321066716E-315;
        r3 = 0;
        r4 = 1;
        r5 = 0;
        r6 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        if (r0 != r1) goto L_0x0527;
    L_0x0010:
        r0 = r13.zzzr;
        zza(r0, r14, r15);
        r0 = r13.zzzi;
        if (r0 == 0) goto L_0x0030;
    L_0x0019:
        r0 = r13.zzzs;
        r0 = r0.zzc(r14);
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
        r7 = r13.zzzd;
        r7 = r7.length;
        r7 = r7 + -3;
    L_0x0037:
        if (r7 < 0) goto L_0x050f;
    L_0x0039:
        r8 = r13.zzbk(r7);
        r9 = r13.zzzd;
        r9 = r9[r7];
    L_0x0041:
        if (r1 == 0) goto L_0x005f;
    L_0x0043:
        r10 = r13.zzzs;
        r10 = r10.zza(r1);
        if (r10 <= r9) goto L_0x005f;
    L_0x004b:
        r10 = r13.zzzs;
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
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r10 = r13.zzbh(r7);
        r15.zzb(r9, r8, r10);
        goto L_0x050b;
    L_0x007d:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0083:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = zzi(r14, r10);
        r15.zzb(r9, r10);
        goto L_0x050b;
    L_0x008e:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0094:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = zzh(r14, r10);
        r15.zzg(r9, r8);
        goto L_0x050b;
    L_0x009f:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x00a5:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = zzi(r14, r10);
        r15.zzj(r9, r10);
        goto L_0x050b;
    L_0x00b0:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x00b6:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = zzh(r14, r10);
        r15.zzo(r9, r8);
        goto L_0x050b;
    L_0x00c1:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x00c7:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = zzh(r14, r10);
        r15.zzp(r9, r8);
        goto L_0x050b;
    L_0x00d2:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x00d8:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = zzh(r14, r10);
        r15.zzf(r9, r8);
        goto L_0x050b;
    L_0x00e3:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x00e9:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r8 = (com.google.android.gms.internal.vision.zzeo) r8;
        r15.zza(r9, r8);
        goto L_0x050b;
    L_0x00f6:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x00fc:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r10 = r13.zzbh(r7);
        r15.zza(r9, r8, r10);
        goto L_0x050b;
    L_0x010b:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0111:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        zza(r9, r8, r15);
        goto L_0x050b;
    L_0x011c:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0122:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = zzj(r14, r10);
        r15.zzb(r9, r8);
        goto L_0x050b;
    L_0x012d:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0133:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = zzh(r14, r10);
        r15.zzh(r9, r8);
        goto L_0x050b;
    L_0x013e:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0144:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = zzi(r14, r10);
        r15.zzc(r9, r10);
        goto L_0x050b;
    L_0x014f:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0155:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = zzh(r14, r10);
        r15.zze(r9, r8);
        goto L_0x050b;
    L_0x0160:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0166:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = zzi(r14, r10);
        r15.zza(r9, r10);
        goto L_0x050b;
    L_0x0171:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0177:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = zzi(r14, r10);
        r15.zzi(r9, r10);
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
        r15.zza(r9, r10);
        goto L_0x050b;
    L_0x01a4:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r13.zza(r15, r9, r8, r7);
        goto L_0x050b;
    L_0x01af:
        r9 = r13.zzzd;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r8 = (java.util.List) r8;
        r10 = r13.zzbh(r7);
        com.google.android.gms.internal.vision.zzhy.zzb(r9, r8, r15, r10);
        goto L_0x050b;
    L_0x01c4:
        r9 = r13.zzzd;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.vision.zzhy.zze(r9, r8, r15, r4);
        goto L_0x050b;
    L_0x01d5:
        r9 = r13.zzzd;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.vision.zzhy.zzj(r9, r8, r15, r4);
        goto L_0x050b;
    L_0x01e6:
        r9 = r13.zzzd;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.vision.zzhy.zzg(r9, r8, r15, r4);
        goto L_0x050b;
    L_0x01f7:
        r9 = r13.zzzd;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.vision.zzhy.zzl(r9, r8, r15, r4);
        goto L_0x050b;
    L_0x0208:
        r9 = r13.zzzd;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.vision.zzhy.zzm(r9, r8, r15, r4);
        goto L_0x050b;
    L_0x0219:
        r9 = r13.zzzd;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.vision.zzhy.zzi(r9, r8, r15, r4);
        goto L_0x050b;
    L_0x022a:
        r9 = r13.zzzd;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.vision.zzhy.zzn(r9, r8, r15, r4);
        goto L_0x050b;
    L_0x023b:
        r9 = r13.zzzd;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.vision.zzhy.zzk(r9, r8, r15, r4);
        goto L_0x050b;
    L_0x024c:
        r9 = r13.zzzd;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.vision.zzhy.zzf(r9, r8, r15, r4);
        goto L_0x050b;
    L_0x025d:
        r9 = r13.zzzd;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.vision.zzhy.zzh(r9, r8, r15, r4);
        goto L_0x050b;
    L_0x026e:
        r9 = r13.zzzd;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.vision.zzhy.zzd(r9, r8, r15, r4);
        goto L_0x050b;
    L_0x027f:
        r9 = r13.zzzd;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.vision.zzhy.zzc(r9, r8, r15, r4);
        goto L_0x050b;
    L_0x0290:
        r9 = r13.zzzd;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.vision.zzhy.zzb(r9, r8, r15, r4);
        goto L_0x050b;
    L_0x02a1:
        r9 = r13.zzzd;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.vision.zzhy.zza(r9, r8, r15, r4);
        goto L_0x050b;
    L_0x02b2:
        r9 = r13.zzzd;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.vision.zzhy.zze(r9, r8, r15, r5);
        goto L_0x050b;
    L_0x02c3:
        r9 = r13.zzzd;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.vision.zzhy.zzj(r9, r8, r15, r5);
        goto L_0x050b;
    L_0x02d4:
        r9 = r13.zzzd;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.vision.zzhy.zzg(r9, r8, r15, r5);
        goto L_0x050b;
    L_0x02e5:
        r9 = r13.zzzd;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.vision.zzhy.zzl(r9, r8, r15, r5);
        goto L_0x050b;
    L_0x02f6:
        r9 = r13.zzzd;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.vision.zzhy.zzm(r9, r8, r15, r5);
        goto L_0x050b;
    L_0x0307:
        r9 = r13.zzzd;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.vision.zzhy.zzi(r9, r8, r15, r5);
        goto L_0x050b;
    L_0x0318:
        r9 = r13.zzzd;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.vision.zzhy.zzb(r9, r8, r15);
        goto L_0x050b;
    L_0x0329:
        r9 = r13.zzzd;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r8 = (java.util.List) r8;
        r10 = r13.zzbh(r7);
        com.google.android.gms.internal.vision.zzhy.zza(r9, r8, r15, r10);
        goto L_0x050b;
    L_0x033e:
        r9 = r13.zzzd;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.vision.zzhy.zza(r9, r8, r15);
        goto L_0x050b;
    L_0x034f:
        r9 = r13.zzzd;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.vision.zzhy.zzn(r9, r8, r15, r5);
        goto L_0x050b;
    L_0x0360:
        r9 = r13.zzzd;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.vision.zzhy.zzk(r9, r8, r15, r5);
        goto L_0x050b;
    L_0x0371:
        r9 = r13.zzzd;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.vision.zzhy.zzf(r9, r8, r15, r5);
        goto L_0x050b;
    L_0x0382:
        r9 = r13.zzzd;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.vision.zzhy.zzh(r9, r8, r15, r5);
        goto L_0x050b;
    L_0x0393:
        r9 = r13.zzzd;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.vision.zzhy.zzd(r9, r8, r15, r5);
        goto L_0x050b;
    L_0x03a4:
        r9 = r13.zzzd;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.vision.zzhy.zzc(r9, r8, r15, r5);
        goto L_0x050b;
    L_0x03b5:
        r9 = r13.zzzd;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.vision.zzhy.zzb(r9, r8, r15, r5);
        goto L_0x050b;
    L_0x03c6:
        r9 = r13.zzzd;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.vision.zzhy.zza(r9, r8, r15, r5);
        goto L_0x050b;
    L_0x03d7:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x03dd:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r10 = r13.zzbh(r7);
        r15.zzb(r9, r8, r10);
        goto L_0x050b;
    L_0x03ec:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x03f2:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = com.google.android.gms.internal.vision.zziu.zzl(r14, r10);
        r15.zzb(r9, r10);
        goto L_0x050b;
    L_0x03fd:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0403:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzk(r14, r10);
        r15.zzg(r9, r8);
        goto L_0x050b;
    L_0x040e:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0414:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = com.google.android.gms.internal.vision.zziu.zzl(r14, r10);
        r15.zzj(r9, r10);
        goto L_0x050b;
    L_0x041f:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0425:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzk(r14, r10);
        r15.zzo(r9, r8);
        goto L_0x050b;
    L_0x0430:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0436:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzk(r14, r10);
        r15.zzp(r9, r8);
        goto L_0x050b;
    L_0x0441:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0447:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzk(r14, r10);
        r15.zzf(r9, r8);
        goto L_0x050b;
    L_0x0452:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0458:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r8 = (com.google.android.gms.internal.vision.zzeo) r8;
        r15.zza(r9, r8);
        goto L_0x050b;
    L_0x0465:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x046b:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        r10 = r13.zzbh(r7);
        r15.zza(r9, r8, r10);
        goto L_0x050b;
    L_0x047a:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0480:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzp(r14, r10);
        zza(r9, r8, r15);
        goto L_0x050b;
    L_0x048b:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0491:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzm(r14, r10);
        r15.zzb(r9, r8);
        goto L_0x050b;
    L_0x049c:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x04a2:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzk(r14, r10);
        r15.zzh(r9, r8);
        goto L_0x050b;
    L_0x04ac:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x04b2:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = com.google.android.gms.internal.vision.zziu.zzl(r14, r10);
        r15.zzc(r9, r10);
        goto L_0x050b;
    L_0x04bc:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x04c2:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzk(r14, r10);
        r15.zze(r9, r8);
        goto L_0x050b;
    L_0x04cc:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x04d2:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = com.google.android.gms.internal.vision.zziu.zzl(r14, r10);
        r15.zza(r9, r10);
        goto L_0x050b;
    L_0x04dc:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x04e2:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = com.google.android.gms.internal.vision.zziu.zzl(r14, r10);
        r15.zzi(r9, r10);
        goto L_0x050b;
    L_0x04ec:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x04f2:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.vision.zziu.zzn(r14, r10);
        r15.zza(r9, r8);
        goto L_0x050b;
    L_0x04fc:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x050b;
    L_0x0502:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = com.google.android.gms.internal.vision.zziu.zzo(r14, r10);
        r15.zza(r9, r10);
    L_0x050b:
        r7 = r7 + -3;
        goto L_0x0037;
    L_0x050f:
        if (r1 == 0) goto L_0x0526;
    L_0x0511:
        r14 = r13.zzzs;
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
        r0 = r13.zzzk;
        if (r0 == 0) goto L_0x0a42;
    L_0x052b:
        r0 = r13.zzzi;
        if (r0 == 0) goto L_0x0546;
    L_0x052f:
        r0 = r13.zzzs;
        r0 = r0.zzc(r14);
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
        r7 = r13.zzzd;
        r7 = r7.length;
        r8 = r1;
        r1 = r5;
    L_0x054d:
        if (r1 >= r7) goto L_0x0a25;
    L_0x054f:
        r9 = r13.zzbk(r1);
        r10 = r13.zzzd;
        r10 = r10[r1];
    L_0x0557:
        if (r8 == 0) goto L_0x0575;
    L_0x0559:
        r11 = r13.zzzs;
        r11 = r11.zza(r8);
        if (r11 > r10) goto L_0x0575;
    L_0x0561:
        r11 = r13.zzzs;
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
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r11 = r13.zzbh(r1);
        r15.zzb(r10, r9, r11);
        goto L_0x0a21;
    L_0x0593:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x0599:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = zzi(r14, r11);
        r15.zzb(r10, r11);
        goto L_0x0a21;
    L_0x05a4:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x05aa:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = zzh(r14, r11);
        r15.zzg(r10, r9);
        goto L_0x0a21;
    L_0x05b5:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x05bb:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = zzi(r14, r11);
        r15.zzj(r10, r11);
        goto L_0x0a21;
    L_0x05c6:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x05cc:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = zzh(r14, r11);
        r15.zzo(r10, r9);
        goto L_0x0a21;
    L_0x05d7:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x05dd:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = zzh(r14, r11);
        r15.zzp(r10, r9);
        goto L_0x0a21;
    L_0x05e8:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x05ee:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = zzh(r14, r11);
        r15.zzf(r10, r9);
        goto L_0x0a21;
    L_0x05f9:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x05ff:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r9 = (com.google.android.gms.internal.vision.zzeo) r9;
        r15.zza(r10, r9);
        goto L_0x0a21;
    L_0x060c:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x0612:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r11 = r13.zzbh(r1);
        r15.zza(r10, r9, r11);
        goto L_0x0a21;
    L_0x0621:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x0627:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        zza(r10, r9, r15);
        goto L_0x0a21;
    L_0x0632:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x0638:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = zzj(r14, r11);
        r15.zzb(r10, r9);
        goto L_0x0a21;
    L_0x0643:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x0649:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = zzh(r14, r11);
        r15.zzh(r10, r9);
        goto L_0x0a21;
    L_0x0654:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x065a:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = zzi(r14, r11);
        r15.zzc(r10, r11);
        goto L_0x0a21;
    L_0x0665:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x066b:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = zzh(r14, r11);
        r15.zze(r10, r9);
        goto L_0x0a21;
    L_0x0676:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x067c:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = zzi(r14, r11);
        r15.zza(r10, r11);
        goto L_0x0a21;
    L_0x0687:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x068d:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = zzi(r14, r11);
        r15.zzi(r10, r11);
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
        r15.zza(r10, r11);
        goto L_0x0a21;
    L_0x06ba:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r13.zza(r15, r10, r9, r1);
        goto L_0x0a21;
    L_0x06c5:
        r10 = r13.zzzd;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r9 = (java.util.List) r9;
        r11 = r13.zzbh(r1);
        com.google.android.gms.internal.vision.zzhy.zzb(r10, r9, r15, r11);
        goto L_0x0a21;
    L_0x06da:
        r10 = r13.zzzd;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.vision.zzhy.zze(r10, r9, r15, r4);
        goto L_0x0a21;
    L_0x06eb:
        r10 = r13.zzzd;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.vision.zzhy.zzj(r10, r9, r15, r4);
        goto L_0x0a21;
    L_0x06fc:
        r10 = r13.zzzd;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.vision.zzhy.zzg(r10, r9, r15, r4);
        goto L_0x0a21;
    L_0x070d:
        r10 = r13.zzzd;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.vision.zzhy.zzl(r10, r9, r15, r4);
        goto L_0x0a21;
    L_0x071e:
        r10 = r13.zzzd;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.vision.zzhy.zzm(r10, r9, r15, r4);
        goto L_0x0a21;
    L_0x072f:
        r10 = r13.zzzd;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.vision.zzhy.zzi(r10, r9, r15, r4);
        goto L_0x0a21;
    L_0x0740:
        r10 = r13.zzzd;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.vision.zzhy.zzn(r10, r9, r15, r4);
        goto L_0x0a21;
    L_0x0751:
        r10 = r13.zzzd;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.vision.zzhy.zzk(r10, r9, r15, r4);
        goto L_0x0a21;
    L_0x0762:
        r10 = r13.zzzd;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.vision.zzhy.zzf(r10, r9, r15, r4);
        goto L_0x0a21;
    L_0x0773:
        r10 = r13.zzzd;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.vision.zzhy.zzh(r10, r9, r15, r4);
        goto L_0x0a21;
    L_0x0784:
        r10 = r13.zzzd;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.vision.zzhy.zzd(r10, r9, r15, r4);
        goto L_0x0a21;
    L_0x0795:
        r10 = r13.zzzd;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.vision.zzhy.zzc(r10, r9, r15, r4);
        goto L_0x0a21;
    L_0x07a6:
        r10 = r13.zzzd;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.vision.zzhy.zzb(r10, r9, r15, r4);
        goto L_0x0a21;
    L_0x07b7:
        r10 = r13.zzzd;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.vision.zzhy.zza(r10, r9, r15, r4);
        goto L_0x0a21;
    L_0x07c8:
        r10 = r13.zzzd;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.vision.zzhy.zze(r10, r9, r15, r5);
        goto L_0x0a21;
    L_0x07d9:
        r10 = r13.zzzd;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.vision.zzhy.zzj(r10, r9, r15, r5);
        goto L_0x0a21;
    L_0x07ea:
        r10 = r13.zzzd;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.vision.zzhy.zzg(r10, r9, r15, r5);
        goto L_0x0a21;
    L_0x07fb:
        r10 = r13.zzzd;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.vision.zzhy.zzl(r10, r9, r15, r5);
        goto L_0x0a21;
    L_0x080c:
        r10 = r13.zzzd;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.vision.zzhy.zzm(r10, r9, r15, r5);
        goto L_0x0a21;
    L_0x081d:
        r10 = r13.zzzd;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.vision.zzhy.zzi(r10, r9, r15, r5);
        goto L_0x0a21;
    L_0x082e:
        r10 = r13.zzzd;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.vision.zzhy.zzb(r10, r9, r15);
        goto L_0x0a21;
    L_0x083f:
        r10 = r13.zzzd;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r9 = (java.util.List) r9;
        r11 = r13.zzbh(r1);
        com.google.android.gms.internal.vision.zzhy.zza(r10, r9, r15, r11);
        goto L_0x0a21;
    L_0x0854:
        r10 = r13.zzzd;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.vision.zzhy.zza(r10, r9, r15);
        goto L_0x0a21;
    L_0x0865:
        r10 = r13.zzzd;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.vision.zzhy.zzn(r10, r9, r15, r5);
        goto L_0x0a21;
    L_0x0876:
        r10 = r13.zzzd;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.vision.zzhy.zzk(r10, r9, r15, r5);
        goto L_0x0a21;
    L_0x0887:
        r10 = r13.zzzd;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.vision.zzhy.zzf(r10, r9, r15, r5);
        goto L_0x0a21;
    L_0x0898:
        r10 = r13.zzzd;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.vision.zzhy.zzh(r10, r9, r15, r5);
        goto L_0x0a21;
    L_0x08a9:
        r10 = r13.zzzd;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.vision.zzhy.zzd(r10, r9, r15, r5);
        goto L_0x0a21;
    L_0x08ba:
        r10 = r13.zzzd;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.vision.zzhy.zzc(r10, r9, r15, r5);
        goto L_0x0a21;
    L_0x08cb:
        r10 = r13.zzzd;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.vision.zzhy.zzb(r10, r9, r15, r5);
        goto L_0x0a21;
    L_0x08dc:
        r10 = r13.zzzd;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.vision.zzhy.zza(r10, r9, r15, r5);
        goto L_0x0a21;
    L_0x08ed:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x08f3:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r11 = r13.zzbh(r1);
        r15.zzb(r10, r9, r11);
        goto L_0x0a21;
    L_0x0902:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x0908:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = com.google.android.gms.internal.vision.zziu.zzl(r14, r11);
        r15.zzb(r10, r11);
        goto L_0x0a21;
    L_0x0913:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x0919:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzk(r14, r11);
        r15.zzg(r10, r9);
        goto L_0x0a21;
    L_0x0924:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x092a:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = com.google.android.gms.internal.vision.zziu.zzl(r14, r11);
        r15.zzj(r10, r11);
        goto L_0x0a21;
    L_0x0935:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x093b:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzk(r14, r11);
        r15.zzo(r10, r9);
        goto L_0x0a21;
    L_0x0946:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x094c:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzk(r14, r11);
        r15.zzp(r10, r9);
        goto L_0x0a21;
    L_0x0957:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x095d:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzk(r14, r11);
        r15.zzf(r10, r9);
        goto L_0x0a21;
    L_0x0968:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x096e:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r9 = (com.google.android.gms.internal.vision.zzeo) r9;
        r15.zza(r10, r9);
        goto L_0x0a21;
    L_0x097b:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x0981:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        r11 = r13.zzbh(r1);
        r15.zza(r10, r9, r11);
        goto L_0x0a21;
    L_0x0990:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x0996:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzp(r14, r11);
        zza(r10, r9, r15);
        goto L_0x0a21;
    L_0x09a1:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x09a7:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzm(r14, r11);
        r15.zzb(r10, r9);
        goto L_0x0a21;
    L_0x09b2:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x09b8:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzk(r14, r11);
        r15.zzh(r10, r9);
        goto L_0x0a21;
    L_0x09c2:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x09c8:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = com.google.android.gms.internal.vision.zziu.zzl(r14, r11);
        r15.zzc(r10, r11);
        goto L_0x0a21;
    L_0x09d2:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x09d8:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzk(r14, r11);
        r15.zze(r10, r9);
        goto L_0x0a21;
    L_0x09e2:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x09e8:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = com.google.android.gms.internal.vision.zziu.zzl(r14, r11);
        r15.zza(r10, r11);
        goto L_0x0a21;
    L_0x09f2:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x09f8:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = com.google.android.gms.internal.vision.zziu.zzl(r14, r11);
        r15.zzi(r10, r11);
        goto L_0x0a21;
    L_0x0a02:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x0a08:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.vision.zziu.zzn(r14, r11);
        r15.zza(r10, r9);
        goto L_0x0a21;
    L_0x0a12:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0a21;
    L_0x0a18:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = com.google.android.gms.internal.vision.zziu.zzo(r14, r11);
        r15.zza(r10, r11);
    L_0x0a21:
        r1 = r1 + 3;
        goto L_0x054d;
    L_0x0a25:
        if (r8 == 0) goto L_0x0a3c;
    L_0x0a27:
        r1 = r13.zzzs;
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
        r0 = r13.zzzr;
        zza(r0, r14, r15);
        return;
    L_0x0a42:
        r13.zzb(r14, r15);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhj.zza(java.lang.Object, com.google.android.gms.internal.vision.zzjj):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0523  */
    /* JADX WARNING: Missing block: B:105:0x0344, code skipped:
            r14 = r13;
     */
    /* JADX WARNING: Missing block: B:171:0x051d, code skipped:
            r5 = r12 + 3;
     */
    private final void zzb(T r21, com.google.android.gms.internal.vision.zzjj r22) throws java.io.IOException {
        /*
        r20 = this;
        r0 = r20;
        r1 = r21;
        r2 = r22;
        r3 = r0.zzzi;
        if (r3 == 0) goto L_0x0021;
    L_0x000a:
        r3 = r0.zzzs;
        r3 = r3.zzc(r1);
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
        r7 = r0.zzzd;
        r7 = r7.length;
        r9 = zzzc;
        r10 = r5;
        r5 = 0;
        r11 = 0;
    L_0x002c:
        if (r5 >= r7) goto L_0x0521;
    L_0x002e:
        r12 = r0.zzbk(r5);
        r13 = r0.zzzd;
        r13 = r13[r5];
        r14 = 267386880; // 0xff00000 float:2.3665827E-29 double:1.321066716E-315;
        r14 = r14 & r12;
        r14 = r14 >>> 20;
        r15 = r0.zzzk;
        r16 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        if (r15 != 0) goto L_0x0061;
    L_0x0042:
        r15 = 17;
        if (r14 > r15) goto L_0x0061;
    L_0x0046:
        r15 = r0.zzzd;
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
        r4 = r0.zzzs;
        r4 = r4.zza(r10);
        if (r4 > r13) goto L_0x0083;
    L_0x006e:
        r4 = r0.zzzs;
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
        r5 = r0.zzbh(r12);
        r2.zzb(r13, r4, r5);
        goto L_0x008b;
    L_0x00a2:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x00aa:
        r4 = zzi(r1, r4);
        r2.zzb(r13, r4);
        goto L_0x008b;
    L_0x00b2:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x00ba:
        r4 = zzh(r1, r4);
        r2.zzg(r13, r4);
        goto L_0x008b;
    L_0x00c2:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x00ca:
        r4 = zzi(r1, r4);
        r2.zzj(r13, r4);
        goto L_0x008b;
    L_0x00d2:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x00da:
        r4 = zzh(r1, r4);
        r2.zzo(r13, r4);
        goto L_0x008b;
    L_0x00e2:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x00ea:
        r4 = zzh(r1, r4);
        r2.zzp(r13, r4);
        goto L_0x008b;
    L_0x00f2:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x00fa:
        r4 = zzh(r1, r4);
        r2.zzf(r13, r4);
        goto L_0x008b;
    L_0x0102:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x010a:
        r4 = r9.getObject(r1, r4);
        r4 = (com.google.android.gms.internal.vision.zzeo) r4;
        r2.zza(r13, r4);
        goto L_0x008b;
    L_0x0115:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x011d:
        r4 = r9.getObject(r1, r4);
        r5 = r0.zzbh(r12);
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
        r2.zzb(r13, r4);
        goto L_0x008b;
    L_0x014c:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x0154:
        r4 = zzh(r1, r4);
        r2.zzh(r13, r4);
        goto L_0x008b;
    L_0x015d:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x0165:
        r4 = zzi(r1, r4);
        r2.zzc(r13, r4);
        goto L_0x008b;
    L_0x016e:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x0176:
        r4 = zzh(r1, r4);
        r2.zze(r13, r4);
        goto L_0x008b;
    L_0x017f:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x0187:
        r4 = zzi(r1, r4);
        r2.zza(r13, r4);
        goto L_0x008b;
    L_0x0190:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x0198:
        r4 = zzi(r1, r4);
        r2.zzi(r13, r4);
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
        r2.zza(r13, r4);
        goto L_0x008b;
    L_0x01c3:
        r12 = r18;
        r4 = r9.getObject(r1, r4);
        r0.zza(r2, r13, r4, r12);
        goto L_0x008b;
    L_0x01ce:
        r12 = r18;
        r8 = r0.zzzd;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r5 = r0.zzbh(r12);
        com.google.android.gms.internal.vision.zzhy.zzb(r8, r4, r2, r5);
        goto L_0x008b;
    L_0x01e3:
        r12 = r18;
        r8 = r0.zzzd;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r13 = 1;
        com.google.android.gms.internal.vision.zzhy.zze(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x01f5:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzzd;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.vision.zzhy.zzj(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x0207:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzzd;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.vision.zzhy.zzg(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x0219:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzzd;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.vision.zzhy.zzl(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x022b:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzzd;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.vision.zzhy.zzm(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x023d:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzzd;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.vision.zzhy.zzi(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x024f:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzzd;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.vision.zzhy.zzn(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x0261:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzzd;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.vision.zzhy.zzk(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x0273:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzzd;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.vision.zzhy.zzf(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x0285:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzzd;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.vision.zzhy.zzh(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x0297:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzzd;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.vision.zzhy.zzd(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x02a9:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzzd;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.vision.zzhy.zzc(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x02bb:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzzd;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.vision.zzhy.zzb(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x02cd:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzzd;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.vision.zzhy.zza(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x02df:
        r12 = r18;
        r8 = r0.zzzd;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r13 = 0;
        com.google.android.gms.internal.vision.zzhy.zze(r8, r4, r2, r13);
        goto L_0x0344;
    L_0x02f0:
        r12 = r18;
        r13 = 0;
        r8 = r0.zzzd;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.vision.zzhy.zzj(r8, r4, r2, r13);
        goto L_0x0344;
    L_0x0301:
        r12 = r18;
        r13 = 0;
        r8 = r0.zzzd;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.vision.zzhy.zzg(r8, r4, r2, r13);
        goto L_0x0344;
    L_0x0312:
        r12 = r18;
        r13 = 0;
        r8 = r0.zzzd;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.vision.zzhy.zzl(r8, r4, r2, r13);
        goto L_0x0344;
    L_0x0323:
        r12 = r18;
        r13 = 0;
        r8 = r0.zzzd;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.vision.zzhy.zzm(r8, r4, r2, r13);
        goto L_0x0344;
    L_0x0334:
        r12 = r18;
        r13 = 0;
        r8 = r0.zzzd;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.vision.zzhy.zzi(r8, r4, r2, r13);
    L_0x0344:
        r14 = r13;
        goto L_0x051d;
    L_0x0347:
        r12 = r18;
        r8 = r0.zzzd;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.vision.zzhy.zzb(r8, r4, r2);
        goto L_0x008b;
    L_0x0358:
        r12 = r18;
        r8 = r0.zzzd;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r5 = r0.zzbh(r12);
        com.google.android.gms.internal.vision.zzhy.zza(r8, r4, r2, r5);
        goto L_0x008b;
    L_0x036d:
        r12 = r18;
        r8 = r0.zzzd;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.vision.zzhy.zza(r8, r4, r2);
        goto L_0x008b;
    L_0x037e:
        r12 = r18;
        r8 = r0.zzzd;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r14 = 0;
        com.google.android.gms.internal.vision.zzhy.zzn(r8, r4, r2, r14);
        goto L_0x051d;
    L_0x0390:
        r12 = r18;
        r14 = 0;
        r8 = r0.zzzd;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.vision.zzhy.zzk(r8, r4, r2, r14);
        goto L_0x051d;
    L_0x03a2:
        r12 = r18;
        r14 = 0;
        r8 = r0.zzzd;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.vision.zzhy.zzf(r8, r4, r2, r14);
        goto L_0x051d;
    L_0x03b4:
        r12 = r18;
        r14 = 0;
        r8 = r0.zzzd;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.vision.zzhy.zzh(r8, r4, r2, r14);
        goto L_0x051d;
    L_0x03c6:
        r12 = r18;
        r14 = 0;
        r8 = r0.zzzd;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.vision.zzhy.zzd(r8, r4, r2, r14);
        goto L_0x051d;
    L_0x03d8:
        r12 = r18;
        r14 = 0;
        r8 = r0.zzzd;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.vision.zzhy.zzc(r8, r4, r2, r14);
        goto L_0x051d;
    L_0x03ea:
        r12 = r18;
        r14 = 0;
        r8 = r0.zzzd;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.vision.zzhy.zzb(r8, r4, r2, r14);
        goto L_0x051d;
    L_0x03fc:
        r12 = r18;
        r14 = 0;
        r8 = r0.zzzd;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.vision.zzhy.zza(r8, r4, r2, r14);
        goto L_0x051d;
    L_0x040e:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x0414:
        r4 = r9.getObject(r1, r4);
        r5 = r0.zzbh(r12);
        r2.zzb(r13, r4, r5);
        goto L_0x051d;
    L_0x0421:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x0427:
        r4 = r9.getLong(r1, r4);
        r2.zzb(r13, r4);
        goto L_0x051d;
    L_0x0430:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x0436:
        r4 = r9.getInt(r1, r4);
        r2.zzg(r13, r4);
        goto L_0x051d;
    L_0x043f:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x0445:
        r4 = r9.getLong(r1, r4);
        r2.zzj(r13, r4);
        goto L_0x051d;
    L_0x044e:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x0454:
        r4 = r9.getInt(r1, r4);
        r2.zzo(r13, r4);
        goto L_0x051d;
    L_0x045d:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x0463:
        r4 = r9.getInt(r1, r4);
        r2.zzp(r13, r4);
        goto L_0x051d;
    L_0x046c:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x0472:
        r4 = r9.getInt(r1, r4);
        r2.zzf(r13, r4);
        goto L_0x051d;
    L_0x047b:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x0481:
        r4 = r9.getObject(r1, r4);
        r4 = (com.google.android.gms.internal.vision.zzeo) r4;
        r2.zza(r13, r4);
        goto L_0x051d;
    L_0x048c:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x0492:
        r4 = r9.getObject(r1, r4);
        r5 = r0.zzbh(r12);
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
        r4 = com.google.android.gms.internal.vision.zziu.zzm(r1, r4);
        r2.zzb(r13, r4);
        goto L_0x051d;
    L_0x04bc:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x04c2:
        r4 = r9.getInt(r1, r4);
        r2.zzh(r13, r4);
        goto L_0x051d;
    L_0x04ca:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x04d0:
        r4 = r9.getLong(r1, r4);
        r2.zzc(r13, r4);
        goto L_0x051d;
    L_0x04d8:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x04de:
        r4 = r9.getInt(r1, r4);
        r2.zze(r13, r4);
        goto L_0x051d;
    L_0x04e6:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x04ec:
        r4 = r9.getLong(r1, r4);
        r2.zza(r13, r4);
        goto L_0x051d;
    L_0x04f4:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x04fa:
        r4 = r9.getLong(r1, r4);
        r2.zzi(r13, r4);
        goto L_0x051d;
    L_0x0502:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x0508:
        r4 = com.google.android.gms.internal.vision.zziu.zzn(r1, r4);
        r2.zza(r13, r4);
        goto L_0x051d;
    L_0x0510:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x0516:
        r4 = com.google.android.gms.internal.vision.zziu.zzo(r1, r4);
        r2.zza(r13, r4);
    L_0x051d:
        r5 = r12 + 3;
        goto L_0x002c;
    L_0x0521:
        if (r10 == 0) goto L_0x0538;
    L_0x0523:
        r4 = r0.zzzs;
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
        r3 = r0.zzzr;
        zza(r3, r1, r2);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhj.zzb(java.lang.Object, com.google.android.gms.internal.vision.zzjj):void");
    }

    private final <K, V> void zza(zzjj zzjj, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzjj.zza(i, this.zzzt.zzo(zzbi(i2)), this.zzzt.zzk(obj));
        }
    }

    private static <UT, UB> void zza(zzio<UT, UB> zzio, T t, zzjj zzjj) throws IOException {
        zzio.zza(zzio.zzt(t), zzjj);
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
            r10 = r7.zzu(r13);
     */
    /* JADX WARNING: Missing block: B:156:0x05b5, code skipped:
            if (r7.zza(r10, r14) == false) goto L_0x05b7;
     */
    /* JADX WARNING: Missing block: B:157:0x05b7, code skipped:
            r14 = r12.zzzn;
     */
    /* JADX WARNING: Missing block: B:159:0x05bb, code skipped:
            if (r14 < r12.zzzo) goto L_0x05bd;
     */
    /* JADX WARNING: Missing block: B:160:0x05bd, code skipped:
            r10 = zza((java.lang.Object) r13, r12.zzzm[r14], r10, r7);
            r14 = r14 + 1;
     */
    /* JADX WARNING: Missing block: B:161:0x05c8, code skipped:
            if (r10 != null) goto L_0x05ca;
     */
    /* JADX WARNING: Missing block: B:162:0x05ca, code skipped:
            r7.zzf(r13, r10);
     */
    /* JADX WARNING: Missing block: B:163:0x05cd, code skipped:
            return;
     */
    public final void zza(T r13, com.google.android.gms.internal.vision.zzhv r14, com.google.android.gms.internal.vision.zzfk r15) throws java.io.IOException {
        /*
        r12 = this;
        if (r15 != 0) goto L_0x0008;
    L_0x0002:
        r13 = new java.lang.NullPointerException;
        r13.<init>();
        throw r13;
    L_0x0008:
        r7 = r12.zzzr;
        r8 = r12.zzzs;
        r9 = 0;
        r0 = r9;
        r10 = r0;
    L_0x000f:
        r1 = r14.zzcn();	 Catch:{ all -> 0x05ce }
        r2 = r12.zzbn(r1);	 Catch:{ all -> 0x05ce }
        if (r2 >= 0) goto L_0x007f;
    L_0x0019:
        r2 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        if (r1 != r2) goto L_0x0035;
    L_0x001e:
        r14 = r12.zzzn;
    L_0x0020:
        r15 = r12.zzzo;
        if (r14 >= r15) goto L_0x002f;
    L_0x0024:
        r15 = r12.zzzm;
        r15 = r15[r14];
        r10 = r12.zza(r13, r15, r10, r7);
        r14 = r14 + 1;
        goto L_0x0020;
    L_0x002f:
        if (r10 == 0) goto L_0x0034;
    L_0x0031:
        r7.zzf(r13, r10);
    L_0x0034:
        return;
    L_0x0035:
        r2 = r12.zzzi;	 Catch:{ all -> 0x05ce }
        if (r2 != 0) goto L_0x003b;
    L_0x0039:
        r2 = r9;
        goto L_0x0042;
    L_0x003b:
        r2 = r12.zzzh;	 Catch:{ all -> 0x05ce }
        r1 = r8.zza(r15, r2, r1);	 Catch:{ all -> 0x05ce }
        r2 = r1;
    L_0x0042:
        if (r2 == 0) goto L_0x0058;
    L_0x0044:
        if (r0 != 0) goto L_0x004a;
    L_0x0046:
        r0 = r8.zzd(r13);	 Catch:{ all -> 0x05ce }
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
        r1 = r7.zzu(r13);	 Catch:{ all -> 0x05ce }
        r10 = r1;
    L_0x0062:
        r1 = r7.zza(r10, r14);	 Catch:{ all -> 0x05ce }
        if (r1 != 0) goto L_0x000f;
    L_0x0068:
        r14 = r12.zzzn;
    L_0x006a:
        r15 = r12.zzzo;
        if (r14 >= r15) goto L_0x0079;
    L_0x006e:
        r15 = r12.zzzm;
        r15 = r15[r14];
        r10 = r12.zza(r13, r15, r10, r7);
        r14 = r14 + 1;
        goto L_0x006a;
    L_0x0079:
        if (r10 == 0) goto L_0x007e;
    L_0x007b:
        r7.zzf(r13, r10);
    L_0x007e:
        return;
    L_0x007f:
        r3 = r12.zzbk(r2);	 Catch:{ all -> 0x05ce }
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
        r1 = r7.zzhd();	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x0589;
    L_0x0096:
        r3 = r3 & r5;
        r3 = (long) r3;	 Catch:{ zzgg -> 0x05a7 }
        r5 = r12.zzbh(r2);	 Catch:{ zzgg -> 0x05a7 }
        r5 = r14.zzc(r5, r15);	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x00a8:
        r3 = r3 & r5;
        r3 = (long) r3;	 Catch:{ zzgg -> 0x05a7 }
        r5 = r14.zzdc();	 Catch:{ zzgg -> 0x05a7 }
        r5 = java.lang.Long.valueOf(r5);	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x00ba:
        r3 = r3 & r5;
        r3 = (long) r3;	 Catch:{ zzgg -> 0x05a7 }
        r5 = r14.zzdb();	 Catch:{ zzgg -> 0x05a7 }
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x00cc:
        r3 = r3 & r5;
        r3 = (long) r3;	 Catch:{ zzgg -> 0x05a7 }
        r5 = r14.zzda();	 Catch:{ zzgg -> 0x05a7 }
        r5 = java.lang.Long.valueOf(r5);	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x00de:
        r3 = r3 & r5;
        r3 = (long) r3;	 Catch:{ zzgg -> 0x05a7 }
        r5 = r14.zzcz();	 Catch:{ zzgg -> 0x05a7 }
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x00f0:
        r4 = r14.zzcy();	 Catch:{ zzgg -> 0x05a7 }
        r6 = r12.zzbj(r2);	 Catch:{ zzgg -> 0x05a7 }
        if (r6 == 0) goto L_0x0107;
    L_0x00fa:
        r6 = r6.zzh(r4);	 Catch:{ zzgg -> 0x05a7 }
        if (r6 == 0) goto L_0x0101;
    L_0x0100:
        goto L_0x0107;
    L_0x0101:
        r1 = com.google.android.gms.internal.vision.zzhy.zza(r1, r4, r10, r7);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x0368;
    L_0x0107:
        r3 = r3 & r5;
        r5 = (long) r3;	 Catch:{ zzgg -> 0x05a7 }
        r3 = java.lang.Integer.valueOf(r4);	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zza(r13, r5, r3);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x0115:
        r3 = r3 & r5;
        r3 = (long) r3;	 Catch:{ zzgg -> 0x05a7 }
        r5 = r14.zzcx();	 Catch:{ zzgg -> 0x05a7 }
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x0127:
        r3 = r3 & r5;
        r3 = (long) r3;	 Catch:{ zzgg -> 0x05a7 }
        r5 = r14.zzcw();	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x0135:
        r4 = r12.zza(r13, r1, r2);	 Catch:{ zzgg -> 0x05a7 }
        if (r4 == 0) goto L_0x0151;
    L_0x013b:
        r3 = r3 & r5;
        r3 = (long) r3;	 Catch:{ zzgg -> 0x05a7 }
        r5 = com.google.android.gms.internal.vision.zziu.zzp(r13, r3);	 Catch:{ zzgg -> 0x05a7 }
        r6 = r12.zzbh(r2);	 Catch:{ zzgg -> 0x05a7 }
        r6 = r14.zza(r6, r15);	 Catch:{ zzgg -> 0x05a7 }
        r5 = com.google.android.gms.internal.vision.zzga.zza(r5, r6);	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x0161;
    L_0x0151:
        r3 = r3 & r5;
        r3 = (long) r3;	 Catch:{ zzgg -> 0x05a7 }
        r5 = r12.zzbh(r2);	 Catch:{ zzgg -> 0x05a7 }
        r5 = r14.zza(r5, r15);	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
    L_0x0161:
        r12.zzb(r13, r1, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x0166:
        r12.zza(r13, r3, r14);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x016e:
        r3 = r3 & r5;
        r3 = (long) r3;	 Catch:{ zzgg -> 0x05a7 }
        r5 = r14.zzcu();	 Catch:{ zzgg -> 0x05a7 }
        r5 = java.lang.Boolean.valueOf(r5);	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x0180:
        r3 = r3 & r5;
        r3 = (long) r3;	 Catch:{ zzgg -> 0x05a7 }
        r5 = r14.zzct();	 Catch:{ zzgg -> 0x05a7 }
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x0192:
        r3 = r3 & r5;
        r3 = (long) r3;	 Catch:{ zzgg -> 0x05a7 }
        r5 = r14.zzcs();	 Catch:{ zzgg -> 0x05a7 }
        r5 = java.lang.Long.valueOf(r5);	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x01a4:
        r3 = r3 & r5;
        r3 = (long) r3;	 Catch:{ zzgg -> 0x05a7 }
        r5 = r14.zzcr();	 Catch:{ zzgg -> 0x05a7 }
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x01b6:
        r3 = r3 & r5;
        r3 = (long) r3;	 Catch:{ zzgg -> 0x05a7 }
        r5 = r14.zzcp();	 Catch:{ zzgg -> 0x05a7 }
        r5 = java.lang.Long.valueOf(r5);	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x01c8:
        r3 = r3 & r5;
        r3 = (long) r3;	 Catch:{ zzgg -> 0x05a7 }
        r5 = r14.zzcq();	 Catch:{ zzgg -> 0x05a7 }
        r5 = java.lang.Long.valueOf(r5);	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x01da:
        r3 = r3 & r5;
        r3 = (long) r3;	 Catch:{ zzgg -> 0x05a7 }
        r5 = r14.readFloat();	 Catch:{ zzgg -> 0x05a7 }
        r5 = java.lang.Float.valueOf(r5);	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x01ec:
        r3 = r3 & r5;
        r3 = (long) r3;	 Catch:{ zzgg -> 0x05a7 }
        r5 = r14.readDouble();	 Catch:{ zzgg -> 0x05a7 }
        r5 = java.lang.Double.valueOf(r5);	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x01fe:
        r1 = r12.zzbi(r2);	 Catch:{ zzgg -> 0x05a7 }
        r2 = r12.zzbk(r2);	 Catch:{ zzgg -> 0x05a7 }
        r2 = r2 & r5;
        r2 = (long) r2;	 Catch:{ zzgg -> 0x05a7 }
        r4 = com.google.android.gms.internal.vision.zziu.zzp(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        if (r4 != 0) goto L_0x0218;
    L_0x020e:
        r4 = r12.zzzt;	 Catch:{ zzgg -> 0x05a7 }
        r4 = r4.zzn(r1);	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zza(r13, r2, r4);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x022f;
    L_0x0218:
        r5 = r12.zzzt;	 Catch:{ zzgg -> 0x05a7 }
        r5 = r5.zzl(r4);	 Catch:{ zzgg -> 0x05a7 }
        if (r5 == 0) goto L_0x022f;
    L_0x0220:
        r5 = r12.zzzt;	 Catch:{ zzgg -> 0x05a7 }
        r5 = r5.zzn(r1);	 Catch:{ zzgg -> 0x05a7 }
        r6 = r12.zzzt;	 Catch:{ zzgg -> 0x05a7 }
        r6.zzb(r5, r4);	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zza(r13, r2, r5);	 Catch:{ zzgg -> 0x05a7 }
        r4 = r5;
    L_0x022f:
        r2 = r12.zzzt;	 Catch:{ zzgg -> 0x05a7 }
        r2 = r2.zzj(r4);	 Catch:{ zzgg -> 0x05a7 }
        r3 = r12.zzzt;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r3.zzo(r1);	 Catch:{ zzgg -> 0x05a7 }
        r14.zza(r2, r1, r15);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x0240:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r12.zzbh(r2);	 Catch:{ zzgg -> 0x05a7 }
        r2 = r12.zzzq;	 Catch:{ zzgg -> 0x05a7 }
        r2 = r2.zza(r13, r3);	 Catch:{ zzgg -> 0x05a7 }
        r14.zzb(r2, r1, r15);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x0252:
        r1 = r12.zzzq;	 Catch:{ zzgg -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        r14.zzp(r1);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x0260:
        r1 = r12.zzzq;	 Catch:{ zzgg -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        r14.zzo(r1);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x026e:
        r1 = r12.zzzq;	 Catch:{ zzgg -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        r14.zzn(r1);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x027c:
        r1 = r12.zzzq;	 Catch:{ zzgg -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        r14.zzm(r1);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x028a:
        r4 = r12.zzzq;	 Catch:{ zzgg -> 0x05a7 }
        r3 = r3 & r5;
        r5 = (long) r3;	 Catch:{ zzgg -> 0x05a7 }
        r3 = r4.zza(r13, r5);	 Catch:{ zzgg -> 0x05a7 }
        r14.zzl(r3);	 Catch:{ zzgg -> 0x05a7 }
        r2 = r12.zzbj(r2);	 Catch:{ zzgg -> 0x05a7 }
        r1 = com.google.android.gms.internal.vision.zzhy.zza(r1, r3, r2, r10, r7);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x0368;
    L_0x029f:
        r1 = r12.zzzq;	 Catch:{ zzgg -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        r14.zzk(r1);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x02ad:
        r1 = r12.zzzq;	 Catch:{ zzgg -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        r14.zzh(r1);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x02bb:
        r1 = r12.zzzq;	 Catch:{ zzgg -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        r14.zzg(r1);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x02c9:
        r1 = r12.zzzq;	 Catch:{ zzgg -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        r14.zzf(r1);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x02d7:
        r1 = r12.zzzq;	 Catch:{ zzgg -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        r14.zze(r1);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x02e5:
        r1 = r12.zzzq;	 Catch:{ zzgg -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        r14.zzc(r1);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x02f3:
        r1 = r12.zzzq;	 Catch:{ zzgg -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        r14.zzd(r1);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x0301:
        r1 = r12.zzzq;	 Catch:{ zzgg -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        r14.zzb(r1);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x030f:
        r1 = r12.zzzq;	 Catch:{ zzgg -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        r14.zza(r1);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x031d:
        r1 = r12.zzzq;	 Catch:{ zzgg -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        r14.zzp(r1);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x032b:
        r1 = r12.zzzq;	 Catch:{ zzgg -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        r14.zzo(r1);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x0339:
        r1 = r12.zzzq;	 Catch:{ zzgg -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        r14.zzn(r1);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x0347:
        r1 = r12.zzzq;	 Catch:{ zzgg -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        r14.zzm(r1);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x0355:
        r4 = r12.zzzq;	 Catch:{ zzgg -> 0x05a7 }
        r3 = r3 & r5;
        r5 = (long) r3;	 Catch:{ zzgg -> 0x05a7 }
        r3 = r4.zza(r13, r5);	 Catch:{ zzgg -> 0x05a7 }
        r14.zzl(r3);	 Catch:{ zzgg -> 0x05a7 }
        r2 = r12.zzbj(r2);	 Catch:{ zzgg -> 0x05a7 }
        r1 = com.google.android.gms.internal.vision.zzhy.zza(r1, r3, r2, r10, r7);	 Catch:{ zzgg -> 0x05a7 }
    L_0x0368:
        r10 = r1;
        goto L_0x000f;
    L_0x036b:
        r1 = r12.zzzq;	 Catch:{ zzgg -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        r14.zzk(r1);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x0379:
        r1 = r12.zzzq;	 Catch:{ zzgg -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        r14.zzj(r1);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x0387:
        r1 = r12.zzbh(r2);	 Catch:{ zzgg -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzgg -> 0x05a7 }
        r4 = r12.zzzq;	 Catch:{ zzgg -> 0x05a7 }
        r2 = r4.zza(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        r14.zza(r2, r1, r15);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x0399:
        r1 = zzbm(r3);	 Catch:{ zzgg -> 0x05a7 }
        if (r1 == 0) goto L_0x03ad;
    L_0x039f:
        r1 = r12.zzzq;	 Catch:{ zzgg -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        r14.zzi(r1);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x03ad:
        r1 = r12.zzzq;	 Catch:{ zzgg -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        r14.readStringList(r1);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x03bb:
        r1 = r12.zzzq;	 Catch:{ zzgg -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        r14.zzh(r1);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x03c9:
        r1 = r12.zzzq;	 Catch:{ zzgg -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        r14.zzg(r1);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x03d7:
        r1 = r12.zzzq;	 Catch:{ zzgg -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        r14.zzf(r1);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x03e5:
        r1 = r12.zzzq;	 Catch:{ zzgg -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        r14.zze(r1);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x03f3:
        r1 = r12.zzzq;	 Catch:{ zzgg -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        r14.zzc(r1);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x0401:
        r1 = r12.zzzq;	 Catch:{ zzgg -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        r14.zzd(r1);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x040f:
        r1 = r12.zzzq;	 Catch:{ zzgg -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        r14.zzb(r1);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x041d:
        r1 = r12.zzzq;	 Catch:{ zzgg -> 0x05a7 }
        r2 = r3 & r5;
        r2 = (long) r2;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        r14.zza(r1);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x042b:
        r1 = r12.zza(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        if (r1 == 0) goto L_0x0449;
    L_0x0431:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzgg -> 0x05a7 }
        r1 = com.google.android.gms.internal.vision.zziu.zzp(r13, r3);	 Catch:{ zzgg -> 0x05a7 }
        r2 = r12.zzbh(r2);	 Catch:{ zzgg -> 0x05a7 }
        r2 = r14.zzc(r2, r15);	 Catch:{ zzgg -> 0x05a7 }
        r1 = com.google.android.gms.internal.vision.zzga.zza(r1, r2);	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zza(r13, r3, r1);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x0449:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r12.zzbh(r2);	 Catch:{ zzgg -> 0x05a7 }
        r1 = r14.zzc(r1, r15);	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zza(r13, r3, r1);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x045c:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzgg -> 0x05a7 }
        r5 = r14.zzdc();	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x046b:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r14.zzdb();	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zzb(r13, r3, r1);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x047a:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzgg -> 0x05a7 }
        r5 = r14.zzda();	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x0489:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r14.zzcz();	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zzb(r13, r3, r1);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x0498:
        r4 = r14.zzcy();	 Catch:{ zzgg -> 0x05a7 }
        r6 = r12.zzbj(r2);	 Catch:{ zzgg -> 0x05a7 }
        if (r6 == 0) goto L_0x04af;
    L_0x04a2:
        r6 = r6.zzh(r4);	 Catch:{ zzgg -> 0x05a7 }
        if (r6 == 0) goto L_0x04a9;
    L_0x04a8:
        goto L_0x04af;
    L_0x04a9:
        r1 = com.google.android.gms.internal.vision.zzhy.zza(r1, r4, r10, r7);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x0368;
    L_0x04af:
        r1 = r3 & r5;
        r5 = (long) r1;	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zzb(r13, r5, r4);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x04ba:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r14.zzcx();	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zzb(r13, r3, r1);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x04c9:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r14.zzcw();	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zza(r13, r3, r1);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x04d8:
        r1 = r12.zza(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        if (r1 == 0) goto L_0x04f6;
    L_0x04de:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzgg -> 0x05a7 }
        r1 = com.google.android.gms.internal.vision.zziu.zzp(r13, r3);	 Catch:{ zzgg -> 0x05a7 }
        r2 = r12.zzbh(r2);	 Catch:{ zzgg -> 0x05a7 }
        r2 = r14.zza(r2, r15);	 Catch:{ zzgg -> 0x05a7 }
        r1 = com.google.android.gms.internal.vision.zzga.zza(r1, r2);	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zza(r13, r3, r1);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x04f6:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r12.zzbh(r2);	 Catch:{ zzgg -> 0x05a7 }
        r1 = r14.zza(r1, r15);	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zza(r13, r3, r1);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x0509:
        r12.zza(r13, r3, r14);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x0511:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r14.zzcu();	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zza(r13, r3, r1);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x0520:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r14.zzct();	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zzb(r13, r3, r1);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x052f:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzgg -> 0x05a7 }
        r5 = r14.zzcs();	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x053e:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r14.zzcr();	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zzb(r13, r3, r1);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x054d:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzgg -> 0x05a7 }
        r5 = r14.zzcp();	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x055c:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzgg -> 0x05a7 }
        r5 = r14.zzcq();	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x056b:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzgg -> 0x05a7 }
        r1 = r14.readFloat();	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zza(r13, r3, r1);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x057a:
        r1 = r3 & r5;
        r3 = (long) r1;	 Catch:{ zzgg -> 0x05a7 }
        r5 = r14.readDouble();	 Catch:{ zzgg -> 0x05a7 }
        com.google.android.gms.internal.vision.zziu.zza(r13, r3, r5);	 Catch:{ zzgg -> 0x05a7 }
        r12.zzb(r13, r2);	 Catch:{ zzgg -> 0x05a7 }
        goto L_0x000f;
    L_0x0589:
        r10 = r1;
    L_0x058a:
        r1 = r7.zza(r10, r14);	 Catch:{ zzgg -> 0x05a7 }
        if (r1 != 0) goto L_0x000f;
    L_0x0590:
        r14 = r12.zzzn;
    L_0x0592:
        r15 = r12.zzzo;
        if (r14 >= r15) goto L_0x05a1;
    L_0x0596:
        r15 = r12.zzzm;
        r15 = r15[r14];
        r10 = r12.zza(r13, r15, r10, r7);
        r14 = r14 + 1;
        goto L_0x0592;
    L_0x05a1:
        if (r10 == 0) goto L_0x05a6;
    L_0x05a3:
        r7.zzf(r13, r10);
    L_0x05a6:
        return;
    L_0x05a7:
        r7.zza(r14);	 Catch:{ all -> 0x05ce }
        if (r10 != 0) goto L_0x05b1;
    L_0x05ac:
        r1 = r7.zzu(r13);	 Catch:{ all -> 0x05ce }
        r10 = r1;
    L_0x05b1:
        r1 = r7.zza(r10, r14);	 Catch:{ all -> 0x05ce }
        if (r1 != 0) goto L_0x000f;
    L_0x05b7:
        r14 = r12.zzzn;
    L_0x05b9:
        r15 = r12.zzzo;
        if (r14 >= r15) goto L_0x05c8;
    L_0x05bd:
        r15 = r12.zzzm;
        r15 = r15[r14];
        r10 = r12.zza(r13, r15, r10, r7);
        r14 = r14 + 1;
        goto L_0x05b9;
    L_0x05c8:
        if (r10 == 0) goto L_0x05cd;
    L_0x05ca:
        r7.zzf(r13, r10);
    L_0x05cd:
        return;
    L_0x05ce:
        r14 = move-exception;
        r15 = r12.zzzn;
    L_0x05d1:
        r0 = r12.zzzo;
        if (r15 >= r0) goto L_0x05e0;
    L_0x05d5:
        r0 = r12.zzzm;
        r0 = r0[r15];
        r10 = r12.zza(r13, r0, r10, r7);
        r15 = r15 + 1;
        goto L_0x05d1;
    L_0x05e0:
        if (r10 == 0) goto L_0x05e5;
    L_0x05e2:
        r7.zzf(r13, r10);
    L_0x05e5:
        throw r14;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhj.zza(java.lang.Object, com.google.android.gms.internal.vision.zzhv, com.google.android.gms.internal.vision.zzfk):void");
    }

    private static zzip zzq(Object obj) {
        zzfy zzfy = (zzfy) obj;
        zzip zzip = zzfy.zzwj;
        if (zzip != zzip.zzhe()) {
            return zzip;
        }
        zzip = zzip.zzhf();
        zzfy.zzwj = zzip;
        return zzip;
    }

    private static int zza(zzhw zzhw, byte[] bArr, int i, int i2, zzei zzei) throws IOException {
        int i3 = i + 1;
        i = bArr[i];
        if (i < 0) {
            i3 = zzeh.zza(i, bArr, i3, zzei);
            i = zzei.zzro;
        }
        int i4 = i3;
        if (i < 0 || i > i2 - i4) {
            throw zzgf.zzfh();
        }
        Object newInstance = zzhw.newInstance();
        i += i4;
        zzhw.zza(newInstance, bArr, i4, i, zzei);
        zzhw.zze(newInstance);
        zzei.zzrq = newInstance;
        return i;
    }

    private static int zza(zzhw zzhw, byte[] bArr, int i, int i2, int i3, zzei zzei) throws IOException {
        zzhj zzhj = (zzhj) zzhw;
        Object newInstance = zzhj.newInstance();
        int zza = zzhj.zza(newInstance, bArr, i, i2, i3, zzei);
        zzhj.zze(newInstance);
        zzei.zzrq = newInstance;
        return zza;
    }

    private static int zza(zzhw<?> zzhw, int i, byte[] bArr, int i2, int i3, zzge<?> zzge, zzei zzei) throws IOException {
        i2 = zza((zzhw) zzhw, bArr, i2, i3, zzei);
        zzge.add(zzei.zzrq);
        while (i2 < i3) {
            int zza = zzeh.zza(bArr, i2, zzei);
            if (i != zzei.zzro) {
                break;
            }
            i2 = zza((zzhw) zzhw, bArr, zza, i3, zzei);
            zzge.add(zzei.zzrq);
        }
        return i2;
    }

    private static int zza(byte[] bArr, int i, int i2, zzjd zzjd, Class<?> cls, zzei zzei) throws IOException {
        int zzb;
        switch (zzjd) {
            case BOOL:
                zzb = zzeh.zzb(bArr, i, zzei);
                zzei.zzrq = Boolean.valueOf(zzei.zzrp != 0);
                return zzb;
            case BYTES:
                return zzeh.zze(bArr, i, zzei);
            case DOUBLE:
                zzei.zzrq = Double.valueOf(zzeh.zzc(bArr, i));
                return i + 8;
            case FIXED32:
            case SFIXED32:
                zzei.zzrq = Integer.valueOf(zzeh.zza(bArr, i));
                return i + 4;
            case FIXED64:
            case SFIXED64:
                zzei.zzrq = Long.valueOf(zzeh.zzb(bArr, i));
                return i + 8;
            case FLOAT:
                zzei.zzrq = Float.valueOf(zzeh.zzd(bArr, i));
                return i + 4;
            case ENUM:
            case INT32:
            case UINT32:
                zzb = zzeh.zza(bArr, i, zzei);
                zzei.zzrq = Integer.valueOf(zzei.zzro);
                return zzb;
            case INT64:
            case UINT64:
                zzb = zzeh.zzb(bArr, i, zzei);
                zzei.zzrq = Long.valueOf(zzei.zzrp);
                return zzb;
            case MESSAGE:
                return zza(zzhs.zzgl().zzf(cls), bArr, i, i2, zzei);
            case SINT32:
                zzb = zzeh.zza(bArr, i, zzei);
                zzei.zzrq = Integer.valueOf(zzez.zzaq(zzei.zzro));
                return zzb;
            case SINT64:
                zzb = zzeh.zzb(bArr, i, zzei);
                zzei.zzrq = Long.valueOf(zzez.zzd(zzei.zzrp));
                return zzb;
            case STRING:
                return zzeh.zzd(bArr, i, zzei);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private static int zza(int i, byte[] bArr, int i2, int i3, Object obj, zzei zzei) throws IOException {
        return zzeh.zza(i, bArr, i2, i3, zzq(obj), zzei);
    }

    /* JADX WARNING: Removed duplicated region for block: B:114:0x022a  */
    /* JADX WARNING: Missing block: B:270:?, code skipped:
            return r4;
     */
    /* JADX WARNING: Missing block: B:273:?, code skipped:
            return r2;
     */
    private final int zza(T r18, byte[] r19, int r20, int r21, int r22, int r23, int r24, int r25, long r26, int r28, long r29, com.google.android.gms.internal.vision.zzei r31) throws java.io.IOException {
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
        r3 = zzzc;
        r3 = r3.getObject(r1, r5);
        r3 = (com.google.android.gms.internal.vision.zzge) r3;
        r12 = r3.zzch();
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
        r3 = r3.zzah(r12);
        r12 = zzzc;
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
        r10 = r0.zzbh(r10);
        r1 = r9 & -8;
        r13 = r1 | 4;
        r1 = r10;
        r2 = r7;
        r3 = r4;
        r4 = r8;
        r5 = r13;
        r6 = r11;
        r1 = zza(r1, r2, r3, r4, r5, r6);
        r2 = r11.zzrq;
        r12.add(r2);
    L_0x005a:
        if (r1 >= r8) goto L_0x0406;
    L_0x005c:
        r3 = com.google.android.gms.internal.vision.zzeh.zza(r7, r1, r11);
        r2 = r11.zzro;
        if (r9 != r2) goto L_0x0406;
    L_0x0064:
        r1 = r10;
        r2 = r7;
        r4 = r8;
        r5 = r13;
        r6 = r11;
        r1 = zza(r1, r2, r3, r4, r5, r6);
        r2 = r11.zzrq;
        r12.add(r2);
        goto L_0x005a;
    L_0x0073:
        if (r2 != r14) goto L_0x0095;
    L_0x0075:
        r12 = (com.google.android.gms.internal.vision.zzgt) r12;
        r1 = com.google.android.gms.internal.vision.zzeh.zza(r7, r4, r11);
        r2 = r11.zzro;
        r2 = r2 + r1;
    L_0x007e:
        if (r1 >= r2) goto L_0x008e;
    L_0x0080:
        r1 = com.google.android.gms.internal.vision.zzeh.zzb(r7, r1, r11);
        r3 = r11.zzrp;
        r3 = com.google.android.gms.internal.vision.zzez.zzd(r3);
        r12.zzp(r3);
        goto L_0x007e;
    L_0x008e:
        if (r1 == r2) goto L_0x0406;
    L_0x0090:
        r1 = com.google.android.gms.internal.vision.zzgf.zzfh();
        throw r1;
    L_0x0095:
        if (r2 != 0) goto L_0x0405;
    L_0x0097:
        r12 = (com.google.android.gms.internal.vision.zzgt) r12;
        r1 = com.google.android.gms.internal.vision.zzeh.zzb(r7, r4, r11);
        r2 = r11.zzrp;
        r2 = com.google.android.gms.internal.vision.zzez.zzd(r2);
        r12.zzp(r2);
    L_0x00a6:
        if (r1 >= r8) goto L_0x0406;
    L_0x00a8:
        r2 = com.google.android.gms.internal.vision.zzeh.zza(r7, r1, r11);
        r3 = r11.zzro;
        if (r9 != r3) goto L_0x0406;
    L_0x00b0:
        r1 = com.google.android.gms.internal.vision.zzeh.zzb(r7, r2, r11);
        r2 = r11.zzrp;
        r2 = com.google.android.gms.internal.vision.zzez.zzd(r2);
        r12.zzp(r2);
        goto L_0x00a6;
    L_0x00be:
        if (r2 != r14) goto L_0x00e0;
    L_0x00c0:
        r12 = (com.google.android.gms.internal.vision.zzfz) r12;
        r1 = com.google.android.gms.internal.vision.zzeh.zza(r7, r4, r11);
        r2 = r11.zzro;
        r2 = r2 + r1;
    L_0x00c9:
        if (r1 >= r2) goto L_0x00d9;
    L_0x00cb:
        r1 = com.google.android.gms.internal.vision.zzeh.zza(r7, r1, r11);
        r3 = r11.zzro;
        r3 = com.google.android.gms.internal.vision.zzez.zzaq(r3);
        r12.zzbg(r3);
        goto L_0x00c9;
    L_0x00d9:
        if (r1 == r2) goto L_0x0406;
    L_0x00db:
        r1 = com.google.android.gms.internal.vision.zzgf.zzfh();
        throw r1;
    L_0x00e0:
        if (r2 != 0) goto L_0x0405;
    L_0x00e2:
        r12 = (com.google.android.gms.internal.vision.zzfz) r12;
        r1 = com.google.android.gms.internal.vision.zzeh.zza(r7, r4, r11);
        r2 = r11.zzro;
        r2 = com.google.android.gms.internal.vision.zzez.zzaq(r2);
        r12.zzbg(r2);
    L_0x00f1:
        if (r1 >= r8) goto L_0x0406;
    L_0x00f3:
        r2 = com.google.android.gms.internal.vision.zzeh.zza(r7, r1, r11);
        r3 = r11.zzro;
        if (r9 != r3) goto L_0x0406;
    L_0x00fb:
        r1 = com.google.android.gms.internal.vision.zzeh.zza(r7, r2, r11);
        r2 = r11.zzro;
        r2 = com.google.android.gms.internal.vision.zzez.zzaq(r2);
        r12.zzbg(r2);
        goto L_0x00f1;
    L_0x0109:
        if (r2 != r14) goto L_0x0110;
    L_0x010b:
        r2 = com.google.android.gms.internal.vision.zzeh.zza(r7, r4, r12, r11);
        goto L_0x011b;
    L_0x0110:
        if (r2 != 0) goto L_0x0405;
    L_0x0112:
        r2 = r9;
        r3 = r7;
        r5 = r8;
        r6 = r12;
        r7 = r11;
        r2 = com.google.android.gms.internal.vision.zzeh.zza(r2, r3, r4, r5, r6, r7);
    L_0x011b:
        r1 = (com.google.android.gms.internal.vision.zzfy) r1;
        r3 = r1.zzwj;
        r4 = com.google.android.gms.internal.vision.zzip.zzhe();
        if (r3 != r4) goto L_0x0126;
    L_0x0125:
        r3 = 0;
    L_0x0126:
        r4 = r0.zzbj(r10);
        r5 = r0.zzzr;
        r6 = r23;
        r3 = com.google.android.gms.internal.vision.zzhy.zza(r6, r12, r4, r3, r5);
        r3 = (com.google.android.gms.internal.vision.zzip) r3;
        if (r3 == 0) goto L_0x0138;
    L_0x0136:
        r1.zzwj = r3;
    L_0x0138:
        r1 = r2;
        goto L_0x0406;
    L_0x013b:
        if (r2 != r14) goto L_0x0405;
    L_0x013d:
        r1 = com.google.android.gms.internal.vision.zzeh.zza(r7, r4, r11);
        r2 = r11.zzro;
        if (r2 >= 0) goto L_0x014a;
    L_0x0145:
        r1 = com.google.android.gms.internal.vision.zzgf.zzfi();
        throw r1;
    L_0x014a:
        r3 = r7.length;
        r3 = r3 - r1;
        if (r2 <= r3) goto L_0x0153;
    L_0x014e:
        r1 = com.google.android.gms.internal.vision.zzgf.zzfh();
        throw r1;
    L_0x0153:
        if (r2 != 0) goto L_0x015b;
    L_0x0155:
        r2 = com.google.android.gms.internal.vision.zzeo.zzrx;
        r12.add(r2);
        goto L_0x0163;
    L_0x015b:
        r3 = com.google.android.gms.internal.vision.zzeo.zzb(r7, r1, r2);
        r12.add(r3);
        r1 = r1 + r2;
    L_0x0163:
        if (r1 >= r8) goto L_0x0406;
    L_0x0165:
        r2 = com.google.android.gms.internal.vision.zzeh.zza(r7, r1, r11);
        r3 = r11.zzro;
        if (r9 != r3) goto L_0x0406;
    L_0x016d:
        r1 = com.google.android.gms.internal.vision.zzeh.zza(r7, r2, r11);
        r2 = r11.zzro;
        if (r2 >= 0) goto L_0x017a;
    L_0x0175:
        r1 = com.google.android.gms.internal.vision.zzgf.zzfi();
        throw r1;
    L_0x017a:
        r3 = r7.length;
        r3 = r3 - r1;
        if (r2 <= r3) goto L_0x0183;
    L_0x017e:
        r1 = com.google.android.gms.internal.vision.zzgf.zzfh();
        throw r1;
    L_0x0183:
        if (r2 != 0) goto L_0x018b;
    L_0x0185:
        r2 = com.google.android.gms.internal.vision.zzeo.zzrx;
        r12.add(r2);
        goto L_0x0163;
    L_0x018b:
        r3 = com.google.android.gms.internal.vision.zzeo.zzb(r7, r1, r2);
        r12.add(r3);
        r1 = r1 + r2;
        goto L_0x0163;
    L_0x0194:
        if (r2 != r14) goto L_0x0405;
    L_0x0196:
        r1 = r0.zzbh(r10);
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
        r1 = com.google.android.gms.internal.vision.zzeh.zza(r7, r4, r11);
        r2 = r11.zzro;
        if (r2 >= 0) goto L_0x01bd;
    L_0x01b8:
        r1 = com.google.android.gms.internal.vision.zzgf.zzfi();
        throw r1;
    L_0x01bd:
        if (r2 != 0) goto L_0x01c5;
    L_0x01bf:
        r2 = "";
        r12.add(r2);
        goto L_0x01d0;
    L_0x01c5:
        r3 = new java.lang.String;
        r4 = com.google.android.gms.internal.vision.zzga.UTF_8;
        r3.<init>(r7, r1, r2, r4);
        r12.add(r3);
        r1 = r1 + r2;
    L_0x01d0:
        if (r1 >= r8) goto L_0x0406;
    L_0x01d2:
        r2 = com.google.android.gms.internal.vision.zzeh.zza(r7, r1, r11);
        r3 = r11.zzro;
        if (r9 != r3) goto L_0x0406;
    L_0x01da:
        r1 = com.google.android.gms.internal.vision.zzeh.zza(r7, r2, r11);
        r2 = r11.zzro;
        if (r2 >= 0) goto L_0x01e7;
    L_0x01e2:
        r1 = com.google.android.gms.internal.vision.zzgf.zzfi();
        throw r1;
    L_0x01e7:
        if (r2 != 0) goto L_0x01ef;
    L_0x01e9:
        r2 = "";
        r12.add(r2);
        goto L_0x01d0;
    L_0x01ef:
        r3 = new java.lang.String;
        r4 = com.google.android.gms.internal.vision.zzga.UTF_8;
        r3.<init>(r7, r1, r2, r4);
        r12.add(r3);
        r1 = r1 + r2;
        goto L_0x01d0;
    L_0x01fb:
        r1 = com.google.android.gms.internal.vision.zzeh.zza(r7, r4, r11);
        r2 = r11.zzro;
        if (r2 >= 0) goto L_0x0208;
    L_0x0203:
        r1 = com.google.android.gms.internal.vision.zzgf.zzfi();
        throw r1;
    L_0x0208:
        if (r2 != 0) goto L_0x0210;
    L_0x020a:
        r2 = "";
        r12.add(r2);
        goto L_0x0228;
    L_0x0210:
        r3 = r1 + r2;
        r4 = com.google.android.gms.internal.vision.zziw.zzg(r7, r1, r3);
        if (r4 != 0) goto L_0x021d;
    L_0x0218:
        r1 = com.google.android.gms.internal.vision.zzgf.zzfp();
        throw r1;
    L_0x021d:
        r4 = new java.lang.String;
        r5 = com.google.android.gms.internal.vision.zzga.UTF_8;
        r4.<init>(r7, r1, r2, r5);
        r12.add(r4);
    L_0x0227:
        r1 = r3;
    L_0x0228:
        if (r1 >= r8) goto L_0x0406;
    L_0x022a:
        r2 = com.google.android.gms.internal.vision.zzeh.zza(r7, r1, r11);
        r3 = r11.zzro;
        if (r9 != r3) goto L_0x0406;
    L_0x0232:
        r1 = com.google.android.gms.internal.vision.zzeh.zza(r7, r2, r11);
        r2 = r11.zzro;
        if (r2 >= 0) goto L_0x023f;
    L_0x023a:
        r1 = com.google.android.gms.internal.vision.zzgf.zzfi();
        throw r1;
    L_0x023f:
        if (r2 != 0) goto L_0x0247;
    L_0x0241:
        r2 = "";
        r12.add(r2);
        goto L_0x0228;
    L_0x0247:
        r3 = r1 + r2;
        r4 = com.google.android.gms.internal.vision.zziw.zzg(r7, r1, r3);
        if (r4 != 0) goto L_0x0254;
    L_0x024f:
        r1 = com.google.android.gms.internal.vision.zzgf.zzfp();
        throw r1;
    L_0x0254:
        r4 = new java.lang.String;
        r5 = com.google.android.gms.internal.vision.zzga.UTF_8;
        r4.<init>(r7, r1, r2, r5);
        r12.add(r4);
        goto L_0x0227;
    L_0x025f:
        r1 = 0;
        if (r2 != r14) goto L_0x0285;
    L_0x0262:
        r12 = (com.google.android.gms.internal.vision.zzem) r12;
        r2 = com.google.android.gms.internal.vision.zzeh.zza(r7, r4, r11);
        r3 = r11.zzro;
        r3 = r3 + r2;
    L_0x026b:
        if (r2 >= r3) goto L_0x027e;
    L_0x026d:
        r2 = com.google.android.gms.internal.vision.zzeh.zzb(r7, r2, r11);
        r8 = r11.zzrp;
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
        r1 = com.google.android.gms.internal.vision.zzgf.zzfh();
        throw r1;
    L_0x0285:
        if (r2 != 0) goto L_0x0405;
    L_0x0287:
        r12 = (com.google.android.gms.internal.vision.zzem) r12;
        r2 = com.google.android.gms.internal.vision.zzeh.zzb(r7, r4, r11);
        r3 = r11.zzrp;
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
        r3 = com.google.android.gms.internal.vision.zzeh.zza(r7, r2, r11);
        r4 = r11.zzro;
        if (r9 != r4) goto L_0x0138;
    L_0x02a3:
        r2 = com.google.android.gms.internal.vision.zzeh.zzb(r7, r3, r11);
        r3 = r11.zzrp;
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
        r12 = (com.google.android.gms.internal.vision.zzfz) r12;
        r1 = com.google.android.gms.internal.vision.zzeh.zza(r7, r4, r11);
        r2 = r11.zzro;
        r2 = r2 + r1;
    L_0x02bf:
        if (r1 >= r2) goto L_0x02cb;
    L_0x02c1:
        r3 = com.google.android.gms.internal.vision.zzeh.zza(r7, r1);
        r12.zzbg(r3);
        r1 = r1 + 4;
        goto L_0x02bf;
    L_0x02cb:
        if (r1 == r2) goto L_0x0406;
    L_0x02cd:
        r1 = com.google.android.gms.internal.vision.zzgf.zzfh();
        throw r1;
    L_0x02d2:
        if (r2 != r3) goto L_0x0405;
    L_0x02d4:
        r12 = (com.google.android.gms.internal.vision.zzfz) r12;
        r1 = com.google.android.gms.internal.vision.zzeh.zza(r19, r20);
        r12.zzbg(r1);
        r1 = r4 + 4;
    L_0x02df:
        if (r1 >= r8) goto L_0x0406;
    L_0x02e1:
        r2 = com.google.android.gms.internal.vision.zzeh.zza(r7, r1, r11);
        r3 = r11.zzro;
        if (r9 != r3) goto L_0x0406;
    L_0x02e9:
        r1 = com.google.android.gms.internal.vision.zzeh.zza(r7, r2);
        r12.zzbg(r1);
        r1 = r2 + 4;
        goto L_0x02df;
    L_0x02f3:
        if (r2 != r14) goto L_0x0311;
    L_0x02f5:
        r12 = (com.google.android.gms.internal.vision.zzgt) r12;
        r1 = com.google.android.gms.internal.vision.zzeh.zza(r7, r4, r11);
        r2 = r11.zzro;
        r2 = r2 + r1;
    L_0x02fe:
        if (r1 >= r2) goto L_0x030a;
    L_0x0300:
        r3 = com.google.android.gms.internal.vision.zzeh.zzb(r7, r1);
        r12.zzp(r3);
        r1 = r1 + 8;
        goto L_0x02fe;
    L_0x030a:
        if (r1 == r2) goto L_0x0406;
    L_0x030c:
        r1 = com.google.android.gms.internal.vision.zzgf.zzfh();
        throw r1;
    L_0x0311:
        if (r2 != r13) goto L_0x0405;
    L_0x0313:
        r12 = (com.google.android.gms.internal.vision.zzgt) r12;
        r1 = com.google.android.gms.internal.vision.zzeh.zzb(r19, r20);
        r12.zzp(r1);
        r1 = r4 + 8;
    L_0x031e:
        if (r1 >= r8) goto L_0x0406;
    L_0x0320:
        r2 = com.google.android.gms.internal.vision.zzeh.zza(r7, r1, r11);
        r3 = r11.zzro;
        if (r9 != r3) goto L_0x0406;
    L_0x0328:
        r3 = com.google.android.gms.internal.vision.zzeh.zzb(r7, r2);
        r12.zzp(r3);
        r1 = r2 + 8;
        goto L_0x031e;
    L_0x0332:
        if (r2 != r14) goto L_0x033a;
    L_0x0334:
        r1 = com.google.android.gms.internal.vision.zzeh.zza(r7, r4, r12, r11);
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
        r1 = com.google.android.gms.internal.vision.zzeh.zza(r1, r2, r3, r4, r5, r6);
        goto L_0x0406;
    L_0x0348:
        if (r2 != r14) goto L_0x0366;
    L_0x034a:
        r12 = (com.google.android.gms.internal.vision.zzgt) r12;
        r1 = com.google.android.gms.internal.vision.zzeh.zza(r7, r4, r11);
        r2 = r11.zzro;
        r2 = r2 + r1;
    L_0x0353:
        if (r1 >= r2) goto L_0x035f;
    L_0x0355:
        r1 = com.google.android.gms.internal.vision.zzeh.zzb(r7, r1, r11);
        r3 = r11.zzrp;
        r12.zzp(r3);
        goto L_0x0353;
    L_0x035f:
        if (r1 == r2) goto L_0x0406;
    L_0x0361:
        r1 = com.google.android.gms.internal.vision.zzgf.zzfh();
        throw r1;
    L_0x0366:
        if (r2 != 0) goto L_0x0405;
    L_0x0368:
        r12 = (com.google.android.gms.internal.vision.zzgt) r12;
        r1 = com.google.android.gms.internal.vision.zzeh.zzb(r7, r4, r11);
        r2 = r11.zzrp;
        r12.zzp(r2);
    L_0x0373:
        if (r1 >= r8) goto L_0x0406;
    L_0x0375:
        r2 = com.google.android.gms.internal.vision.zzeh.zza(r7, r1, r11);
        r3 = r11.zzro;
        if (r9 != r3) goto L_0x0406;
    L_0x037d:
        r1 = com.google.android.gms.internal.vision.zzeh.zzb(r7, r2, r11);
        r2 = r11.zzrp;
        r12.zzp(r2);
        goto L_0x0373;
    L_0x0387:
        if (r2 != r14) goto L_0x03a5;
    L_0x0389:
        r12 = (com.google.android.gms.internal.vision.zzfv) r12;
        r1 = com.google.android.gms.internal.vision.zzeh.zza(r7, r4, r11);
        r2 = r11.zzro;
        r2 = r2 + r1;
    L_0x0392:
        if (r1 >= r2) goto L_0x039e;
    L_0x0394:
        r3 = com.google.android.gms.internal.vision.zzeh.zzd(r7, r1);
        r12.zzh(r3);
        r1 = r1 + 4;
        goto L_0x0392;
    L_0x039e:
        if (r1 == r2) goto L_0x0406;
    L_0x03a0:
        r1 = com.google.android.gms.internal.vision.zzgf.zzfh();
        throw r1;
    L_0x03a5:
        if (r2 != r3) goto L_0x0405;
    L_0x03a7:
        r12 = (com.google.android.gms.internal.vision.zzfv) r12;
        r1 = com.google.android.gms.internal.vision.zzeh.zzd(r19, r20);
        r12.zzh(r1);
        r1 = r4 + 4;
    L_0x03b2:
        if (r1 >= r8) goto L_0x0406;
    L_0x03b4:
        r2 = com.google.android.gms.internal.vision.zzeh.zza(r7, r1, r11);
        r3 = r11.zzro;
        if (r9 != r3) goto L_0x0406;
    L_0x03bc:
        r1 = com.google.android.gms.internal.vision.zzeh.zzd(r7, r2);
        r12.zzh(r1);
        r1 = r2 + 4;
        goto L_0x03b2;
    L_0x03c6:
        if (r2 != r14) goto L_0x03e4;
    L_0x03c8:
        r12 = (com.google.android.gms.internal.vision.zzfh) r12;
        r1 = com.google.android.gms.internal.vision.zzeh.zza(r7, r4, r11);
        r2 = r11.zzro;
        r2 = r2 + r1;
    L_0x03d1:
        if (r1 >= r2) goto L_0x03dd;
    L_0x03d3:
        r3 = com.google.android.gms.internal.vision.zzeh.zzc(r7, r1);
        r12.zzc(r3);
        r1 = r1 + 8;
        goto L_0x03d1;
    L_0x03dd:
        if (r1 == r2) goto L_0x0406;
    L_0x03df:
        r1 = com.google.android.gms.internal.vision.zzgf.zzfh();
        throw r1;
    L_0x03e4:
        if (r2 != r13) goto L_0x0405;
    L_0x03e6:
        r12 = (com.google.android.gms.internal.vision.zzfh) r12;
        r1 = com.google.android.gms.internal.vision.zzeh.zzc(r19, r20);
        r12.zzc(r1);
        r1 = r4 + 8;
    L_0x03f1:
        if (r1 >= r8) goto L_0x0406;
    L_0x03f3:
        r2 = com.google.android.gms.internal.vision.zzeh.zza(r7, r1, r11);
        r3 = r11.zzro;
        if (r9 != r3) goto L_0x0406;
    L_0x03fb:
        r3 = com.google.android.gms.internal.vision.zzeh.zzc(r7, r2);
        r12.zzc(r3);
        r1 = r2 + 8;
        goto L_0x03f1;
    L_0x0405:
        r1 = r4;
    L_0x0406:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhj.zza(java.lang.Object, byte[], int, int, int, int, int, int, long, int, long, com.google.android.gms.internal.vision.zzei):int");
    }

    private final <K, V> int zza(T r8, byte[] r9, int r10, int r11, int r12, long r13, com.google.android.gms.internal.vision.zzei r15) throws java.io.IOException {
        /*
        r7 = this;
        r0 = zzzc;
        r12 = r7.zzbi(r12);
        r1 = r0.getObject(r8, r13);
        r2 = r7.zzzt;
        r2 = r2.zzl(r1);
        if (r2 == 0) goto L_0x0021;
    L_0x0012:
        r2 = r7.zzzt;
        r2 = r2.zzn(r12);
        r3 = r7.zzzt;
        r3.zzb(r2, r1);
        r0.putObject(r8, r13, r2);
        r1 = r2;
    L_0x0021:
        r8 = r7.zzzt;
        r8 = r8.zzo(r12);
        r12 = r7.zzzt;
        r12 = r12.zzj(r1);
        r10 = com.google.android.gms.internal.vision.zzeh.zza(r9, r10, r15);
        r13 = r15.zzro;
        if (r13 < 0) goto L_0x0095;
    L_0x0035:
        r14 = r11 - r10;
        if (r13 <= r14) goto L_0x003a;
    L_0x0039:
        goto L_0x0095;
    L_0x003a:
        r13 = r13 + r10;
        r14 = r8.zzyw;
        r0 = r8.zzgq;
    L_0x003f:
        if (r10 >= r13) goto L_0x008a;
    L_0x0041:
        r1 = r10 + 1;
        r10 = r9[r10];
        if (r10 >= 0) goto L_0x004d;
    L_0x0047:
        r1 = com.google.android.gms.internal.vision.zzeh.zza(r10, r9, r1, r15);
        r10 = r15.zzro;
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
        r1 = r8.zzyx;
        r1 = r1.zzhp();
        if (r3 != r1) goto L_0x0085;
    L_0x005e:
        r4 = r8.zzyx;
        r10 = r8.zzgq;
        r5 = r10.getClass();
        r1 = r9;
        r3 = r11;
        r6 = r15;
        r10 = zza(r1, r2, r3, r4, r5, r6);
        r0 = r15.zzrq;
        goto L_0x003f;
    L_0x0070:
        r1 = r8.zzyv;
        r1 = r1.zzhp();
        if (r3 != r1) goto L_0x0085;
    L_0x0078:
        r4 = r8.zzyv;
        r5 = 0;
        r1 = r9;
        r3 = r11;
        r6 = r15;
        r10 = zza(r1, r2, r3, r4, r5, r6);
        r14 = r15.zzrq;
        goto L_0x003f;
    L_0x0085:
        r10 = com.google.android.gms.internal.vision.zzeh.zza(r10, r9, r2, r11, r15);
        goto L_0x003f;
    L_0x008a:
        if (r10 == r13) goto L_0x0091;
    L_0x008c:
        r8 = com.google.android.gms.internal.vision.zzgf.zzfo();
        throw r8;
    L_0x0091:
        r12.put(r14, r0);
        return r13;
    L_0x0095:
        r8 = com.google.android.gms.internal.vision.zzgf.zzfh();
        throw r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhj.zza(java.lang.Object, byte[], int, int, int, long, com.google.android.gms.internal.vision.zzei):int");
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
    private final int zza(T r18, byte[] r19, int r20, int r21, int r22, int r23, int r24, int r25, int r26, long r27, int r29, com.google.android.gms.internal.vision.zzei r30) throws java.io.IOException {
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
        r12 = zzzc;
        r7 = r0.zzzd;
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
        r2 = r0.zzbh(r6);
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
        r3 = r11.zzrq;
        r12.putObject(r1, r9, r3);
        goto L_0x019b;
    L_0x0050:
        r3 = r11.zzrq;
        r3 = com.google.android.gms.internal.vision.zzga.zza(r15, r3);
        r12.putObject(r1, r9, r3);
        goto L_0x019b;
    L_0x005b:
        if (r5 != 0) goto L_0x019f;
    L_0x005d:
        r2 = com.google.android.gms.internal.vision.zzeh.zzb(r3, r4, r11);
        r3 = r11.zzrp;
        r3 = com.google.android.gms.internal.vision.zzez.zzd(r3);
        r3 = java.lang.Long.valueOf(r3);
        r12.putObject(r1, r9, r3);
        goto L_0x019b;
    L_0x0070:
        if (r5 != 0) goto L_0x019f;
    L_0x0072:
        r2 = com.google.android.gms.internal.vision.zzeh.zza(r3, r4, r11);
        r3 = r11.zzro;
        r3 = com.google.android.gms.internal.vision.zzez.zzaq(r3);
        r3 = java.lang.Integer.valueOf(r3);
        r12.putObject(r1, r9, r3);
        goto L_0x019b;
    L_0x0085:
        if (r5 != 0) goto L_0x019f;
    L_0x0087:
        r3 = com.google.android.gms.internal.vision.zzeh.zza(r3, r4, r11);
        r4 = r11.zzro;
        r5 = r0.zzbj(r6);
        if (r5 == 0) goto L_0x00a9;
    L_0x0093:
        r5 = r5.zzh(r4);
        if (r5 == 0) goto L_0x009a;
    L_0x0099:
        goto L_0x00a9;
    L_0x009a:
        r1 = zzq(r18);
        r4 = (long) r4;
        r4 = java.lang.Long.valueOf(r4);
        r1.zzb(r2, r4);
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
        r2 = com.google.android.gms.internal.vision.zzeh.zze(r3, r4, r11);
        r3 = r11.zzrq;
        r12.putObject(r1, r9, r3);
        goto L_0x019b;
    L_0x00c0:
        if (r5 != r15) goto L_0x019f;
    L_0x00c2:
        r2 = r0.zzbh(r6);
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
        r3 = r11.zzrq;
        r12.putObject(r1, r9, r3);
        goto L_0x00e9;
    L_0x00e0:
        r3 = r11.zzrq;
        r3 = com.google.android.gms.internal.vision.zzga.zza(r15, r3);
        r12.putObject(r1, r9, r3);
    L_0x00e9:
        r12.putInt(r1, r13, r8);
        goto L_0x01a0;
    L_0x00ee:
        if (r5 != r15) goto L_0x019f;
    L_0x00f0:
        r2 = com.google.android.gms.internal.vision.zzeh.zza(r3, r4, r11);
        r4 = r11.zzro;
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
        r5 = com.google.android.gms.internal.vision.zziw.zzg(r3, r2, r5);
        if (r5 != 0) goto L_0x0111;
    L_0x010c:
        r1 = com.google.android.gms.internal.vision.zzgf.zzfp();
        throw r1;
    L_0x0111:
        r5 = new java.lang.String;
        r6 = com.google.android.gms.internal.vision.zzga.UTF_8;
        r5.<init>(r3, r2, r4, r6);
        r12.putObject(r1, r9, r5);
        r2 = r2 + r4;
    L_0x011c:
        r12.putInt(r1, r13, r8);
        goto L_0x01a0;
    L_0x0121:
        if (r5 != 0) goto L_0x019f;
    L_0x0123:
        r2 = com.google.android.gms.internal.vision.zzeh.zzb(r3, r4, r11);
        r3 = r11.zzrp;
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
        r2 = com.google.android.gms.internal.vision.zzeh.zza(r19, r20);
        r2 = java.lang.Integer.valueOf(r2);
        r12.putObject(r1, r9, r2);
        r2 = r4 + 4;
        goto L_0x019b;
    L_0x014a:
        r2 = 1;
        if (r5 != r2) goto L_0x019f;
    L_0x014d:
        r2 = com.google.android.gms.internal.vision.zzeh.zzb(r19, r20);
        r2 = java.lang.Long.valueOf(r2);
        r12.putObject(r1, r9, r2);
        r2 = r4 + 8;
        goto L_0x019b;
    L_0x015b:
        if (r5 != 0) goto L_0x019f;
    L_0x015d:
        r2 = com.google.android.gms.internal.vision.zzeh.zza(r3, r4, r11);
        r3 = r11.zzro;
        r3 = java.lang.Integer.valueOf(r3);
        r12.putObject(r1, r9, r3);
        goto L_0x019b;
    L_0x016b:
        if (r5 != 0) goto L_0x019f;
    L_0x016d:
        r2 = com.google.android.gms.internal.vision.zzeh.zzb(r3, r4, r11);
        r3 = r11.zzrp;
        r3 = java.lang.Long.valueOf(r3);
        r12.putObject(r1, r9, r3);
        goto L_0x019b;
    L_0x017b:
        if (r5 != r7) goto L_0x019f;
    L_0x017d:
        r2 = com.google.android.gms.internal.vision.zzeh.zzd(r19, r20);
        r2 = java.lang.Float.valueOf(r2);
        r12.putObject(r1, r9, r2);
        r2 = r4 + 4;
        goto L_0x019b;
    L_0x018b:
        r2 = 1;
        if (r5 != r2) goto L_0x019f;
    L_0x018e:
        r2 = com.google.android.gms.internal.vision.zzeh.zzc(r19, r20);
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhj.zza(java.lang.Object, byte[], int, int, int, int, int, int, int, long, int, com.google.android.gms.internal.vision.zzei):int");
    }

    private final zzhw zzbh(int i) {
        i = (i / 3) << 1;
        zzhw zzhw = (zzhw) this.zzze[i];
        if (zzhw != null) {
            return zzhw;
        }
        zzhw = zzhs.zzgl().zzf((Class) this.zzze[i + 1]);
        this.zzze[i] = zzhw;
        return zzhw;
    }

    private final Object zzbi(int i) {
        return this.zzze[(i / 3) << 1];
    }

    private final zzgd zzbj(int i) {
        return (zzgd) this.zzze[((i / 3) << 1) + 1];
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
    private final int zza(T r32, byte[] r33, int r34, int r35, int r36, com.google.android.gms.internal.vision.zzei r37) throws java.io.IOException {
        /*
        r31 = this;
        r15 = r31;
        r14 = r32;
        r12 = r33;
        r13 = r35;
        r11 = r36;
        r9 = r37;
        r10 = zzzc;
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
        r0 = com.google.android.gms.internal.vision.zzeh.zza(r0, r12, r3, r9);
        r3 = r9.zzro;
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
        r1 = r15.zzr(r3, r2);
    L_0x0037:
        r2 = r1;
        r1 = -1;
        goto L_0x003f;
    L_0x003a:
        r1 = r15.zzbn(r3);
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
        r1 = r15.zzzd;
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
        r4 = r15.zzzd;
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
        r0 = r15.zzbh(r2);
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
        r1 = r9.zzrq;
        r10.putObject(r14, r7, r1);
        goto L_0x00d0;
    L_0x00c3:
        r1 = r10.getObject(r14, r7);
        r2 = r9.zzrq;
        r1 = com.google.android.gms.internal.vision.zzga.zza(r1, r2);
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
        r18 = com.google.android.gms.internal.vision.zzeh.zzb(r12, r2, r9);
        r0 = r9.zzrp;
        r4 = com.google.android.gms.internal.vision.zzez.zzd(r0);
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
        r0 = com.google.android.gms.internal.vision.zzeh.zza(r12, r2, r9);
        r1 = r9.zzro;
        r1 = com.google.android.gms.internal.vision.zzez.zzaq(r1);
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
        r0 = com.google.android.gms.internal.vision.zzeh.zza(r12, r2, r9);
        r1 = r9.zzro;
        r2 = r15.zzbj(r11);
        if (r2 == 0) goto L_0x015b;
    L_0x0147:
        r2 = r2.zzh(r1);
        if (r2 == 0) goto L_0x014e;
    L_0x014d:
        goto L_0x015b;
    L_0x014e:
        r2 = zzq(r32);
        r3 = (long) r1;
        r1 = java.lang.Long.valueOf(r3);
        r2.zzb(r13, r1);
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
        r0 = com.google.android.gms.internal.vision.zzeh.zze(r12, r2, r9);
        r1 = r9.zzrq;
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
        r0 = r15.zzbh(r11);
        r3 = r13;
        r13 = r35;
        r0 = zza(r0, r12, r2, r13, r9);
        r1 = r6 & r24;
        if (r1 != 0) goto L_0x01b4;
    L_0x01ae:
        r1 = r9.zzrq;
        r10.putObject(r14, r7, r1);
        goto L_0x01c1;
    L_0x01b4:
        r1 = r10.getObject(r14, r7);
        r2 = r9.zzrq;
        r1 = com.google.android.gms.internal.vision.zzga.zza(r1, r2);
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
        r0 = com.google.android.gms.internal.vision.zzeh.zzc(r12, r2, r9);
        goto L_0x01e8;
    L_0x01e4:
        r0 = com.google.android.gms.internal.vision.zzeh.zzd(r12, r2, r9);
    L_0x01e8:
        r1 = r9.zzrq;
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
        r0 = com.google.android.gms.internal.vision.zzeh.zzb(r12, r2, r9);
        r1 = r9.zzrp;
        r18 = 0;
        r4 = (r1 > r18 ? 1 : (r1 == r18 ? 0 : -1));
        if (r4 == 0) goto L_0x020c;
    L_0x020b:
        goto L_0x020e;
    L_0x020c:
        r5 = r16;
    L_0x020e:
        com.google.android.gms.internal.vision.zziu.zza(r14, r7, r5);
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
        r0 = com.google.android.gms.internal.vision.zzeh.zza(r12, r2);
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
        r4 = com.google.android.gms.internal.vision.zzeh.zzb(r12, r2);
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
        r0 = com.google.android.gms.internal.vision.zzeh.zza(r12, r3, r9);
        r1 = r9.zzro;
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
        r18 = com.google.android.gms.internal.vision.zzeh.zzb(r12, r3, r9);
        r4 = r9.zzrp;
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
        r0 = com.google.android.gms.internal.vision.zzeh.zzd(r12, r3);
        com.google.android.gms.internal.vision.zziu.zza(r14, r7, r0);
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
        r0 = com.google.android.gms.internal.vision.zzeh.zzc(r12, r3);
        com.google.android.gms.internal.vision.zziu.zza(r14, r7, r0);
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
        r0 = (com.google.android.gms.internal.vision.zzge) r0;
        r1 = r0.zzch();
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
        r0 = r0.zzah(r1);
        r10.putObject(r14, r7, r0);
    L_0x0319:
        r5 = r0;
        r0 = r15.zzbh(r11);
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
        r3 = r14.zzzn;
    L_0x0452:
        r4 = r14.zzzo;
        if (r3 >= r4) goto L_0x0465;
    L_0x0456:
        r4 = r14.zzzm;
        r4 = r4[r3];
        r5 = r14.zzzr;
        r1 = r14.zza(r0, r4, r1, r5);
        r1 = (com.google.android.gms.internal.vision.zzip) r1;
        r3 = r3 + 1;
        goto L_0x0452;
    L_0x0465:
        if (r1 == 0) goto L_0x046c;
    L_0x0467:
        r3 = r14.zzzr;
        r3.zzf(r0, r1);
    L_0x046c:
        if (r6 != 0) goto L_0x0477;
    L_0x046e:
        r0 = r35;
        if (r2 == r0) goto L_0x047e;
    L_0x0472:
        r0 = com.google.android.gms.internal.vision.zzgf.zzfo();
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
        r0 = com.google.android.gms.internal.vision.zzgf.zzfo();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhj.zza(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.vision.zzei):int");
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
    public final void zza(T r29, byte[] r30, int r31, int r32, com.google.android.gms.internal.vision.zzei r33) throws java.io.IOException {
        /*
        r28 = this;
        r15 = r28;
        r14 = r29;
        r12 = r30;
        r13 = r32;
        r11 = r33;
        r0 = r15.zzzk;
        if (r0 == 0) goto L_0x026f;
    L_0x000e:
        r9 = zzzc;
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
        r0 = com.google.android.gms.internal.vision.zzeh.zza(r0, r12, r3, r11);
        r3 = r11.zzro;
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
        r0 = r15.zzr(r7, r2);
    L_0x0039:
        r4 = r0;
        goto L_0x0040;
    L_0x003b:
        r0 = r15.zzbn(r7);
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
        r0 = r15.zzzd;
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
        r6 = com.google.android.gms.internal.vision.zzeh.zzb(r12, r8, r11);
        r20 = r1;
        r0 = r11.zzrp;
        r22 = com.google.android.gms.internal.vision.zzez.zzd(r0);
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
        r0 = com.google.android.gms.internal.vision.zzeh.zza(r12, r8, r11);
        r1 = r11.zzro;
        r1 = com.google.android.gms.internal.vision.zzez.zzaq(r1);
        r9.putInt(r14, r2, r1);
        goto L_0x0166;
    L_0x009e:
        r2 = r1;
        r10 = r4;
        if (r6 != 0) goto L_0x016b;
    L_0x00a2:
        r0 = com.google.android.gms.internal.vision.zzeh.zza(r12, r8, r11);
        r1 = r11.zzro;
        r9.putInt(r14, r2, r1);
        goto L_0x0166;
    L_0x00ad:
        r2 = r1;
        if (r6 != r10) goto L_0x0066;
    L_0x00b0:
        r0 = com.google.android.gms.internal.vision.zzeh.zze(r12, r8, r11);
        r1 = r11.zzrq;
        r9.putObject(r14, r2, r1);
        goto L_0x0116;
    L_0x00ba:
        r2 = r1;
        if (r6 != r10) goto L_0x0066;
    L_0x00bd:
        r0 = r15.zzbh(r4);
        r0 = zza(r0, r12, r8, r13, r11);
        r1 = r9.getObject(r14, r2);
        if (r1 != 0) goto L_0x00d1;
    L_0x00cb:
        r1 = r11.zzrq;
        r9.putObject(r14, r2, r1);
        goto L_0x0116;
    L_0x00d1:
        r5 = r11.zzrq;
        r1 = com.google.android.gms.internal.vision.zzga.zza(r1, r5);
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
        r0 = com.google.android.gms.internal.vision.zzeh.zzc(r12, r8, r11);
        goto L_0x00ec;
    L_0x00e8:
        r0 = com.google.android.gms.internal.vision.zzeh.zzd(r12, r8, r11);
    L_0x00ec:
        r1 = r11.zzrq;
        r9.putObject(r14, r2, r1);
        goto L_0x0116;
    L_0x00f2:
        r2 = r1;
        if (r6 != 0) goto L_0x0066;
    L_0x00f5:
        r1 = com.google.android.gms.internal.vision.zzeh.zzb(r12, r8, r11);
        r5 = r11.zzrp;
        r19 = 0;
        r8 = (r5 > r19 ? 1 : (r5 == r19 ? 0 : -1));
        if (r8 == 0) goto L_0x0102;
    L_0x0101:
        goto L_0x0104;
    L_0x0102:
        r0 = r16;
    L_0x0104:
        com.google.android.gms.internal.vision.zziu.zza(r14, r2, r0);
        r0 = r1;
        goto L_0x0116;
    L_0x0109:
        r2 = r1;
        r0 = 5;
        if (r6 != r0) goto L_0x0066;
    L_0x010d:
        r0 = com.google.android.gms.internal.vision.zzeh.zza(r12, r8);
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
        r5 = com.google.android.gms.internal.vision.zzeh.zzb(r12, r8);
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
        r0 = com.google.android.gms.internal.vision.zzeh.zza(r12, r8, r11);
        r1 = r11.zzro;
        r9.putInt(r14, r2, r1);
        goto L_0x0166;
    L_0x0139:
        r2 = r1;
        r10 = r4;
        if (r6 != 0) goto L_0x016b;
    L_0x013d:
        r6 = com.google.android.gms.internal.vision.zzeh.zzb(r12, r8, r11);
        r4 = r11.zzrp;
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
        r0 = com.google.android.gms.internal.vision.zzeh.zzd(r12, r8);
        com.google.android.gms.internal.vision.zziu.zza(r14, r2, r0);
        r0 = r8 + 4;
        goto L_0x0166;
    L_0x0159:
        r2 = r1;
        r10 = r4;
        if (r6 != r0) goto L_0x016b;
    L_0x015d:
        r0 = com.google.android.gms.internal.vision.zzeh.zzc(r12, r8);
        com.google.android.gms.internal.vision.zziu.zza(r14, r2, r0);
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
        r0 = (com.google.android.gms.internal.vision.zzge) r0;
        r3 = r0.zzch();
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
        r0 = r0.zzah(r3);
        r9.putObject(r14, r1, r0);
    L_0x0198:
        r5 = r0;
        r0 = r15.zzbh(r4);
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
        r0 = com.google.android.gms.internal.vision.zzgf.zzfo();
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhj.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.vision.zzei):void");
    }

    public final void zze(T t) {
        int i;
        for (i = this.zzzn; i < this.zzzo; i++) {
            long zzbk = (long) (zzbk(this.zzzm[i]) & 1048575);
            Object zzp = zziu.zzp(t, zzbk);
            if (zzp != null) {
                zziu.zza((Object) t, zzbk, this.zzzt.zzm(zzp));
            }
        }
        i = this.zzzm.length;
        for (int i2 = this.zzzo; i2 < i; i2++) {
            this.zzzq.zzb(t, (long) this.zzzm[i2]);
        }
        this.zzzr.zze(t);
        if (this.zzzi) {
            this.zzzs.zze((Object) t);
        }
    }

    private final <UT, UB> UB zza(Object obj, int i, UB ub, zzio<UT, UB> zzio) {
        int i2 = this.zzzd[i];
        obj = zziu.zzp(obj, (long) (zzbk(i) & 1048575));
        if (obj == null) {
            return ub;
        }
        zzgd zzbj = zzbj(i);
        if (zzbj == null) {
            return ub;
        }
        return zza(i, i2, this.zzzt.zzj(obj), zzbj, (Object) ub, (zzio) zzio);
    }

    private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzgd zzgd, UB ub, zzio<UT, UB> zzio) {
        zzgy zzo = this.zzzt.zzo(zzbi(i));
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            if (!zzgd.zzh(((Integer) entry.getValue()).intValue())) {
                if (ub == null) {
                    ub = zzio.zzhd();
                }
                zzev zzaj = zzeo.zzaj(zzgx.zza(zzo, entry.getKey(), entry.getValue()));
                try {
                    zzgx.zza(zzaj.zzdp(), zzo, entry.getKey(), entry.getValue());
                    zzio.zza((Object) ub, i2, zzaj.zzdo());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    public final boolean zzr(T t) {
        int i = 0;
        int i2 = -1;
        int i3 = i;
        while (true) {
            int i4 = 1;
            if (i3 >= this.zzzn) {
                return !this.zzzi || this.zzzs.zzc(t).isInitialized();
            } else {
                int i5;
                int i6;
                int i7 = this.zzzm[i3];
                int i8 = this.zzzd[i7];
                int zzbk = zzbk(i7);
                if (this.zzzk) {
                    i5 = 0;
                } else {
                    i5 = this.zzzd[i7 + 2];
                    i6 = i5 & 1048575;
                    i5 = 1 << (i5 >>> 20);
                    if (i6 != i2) {
                        i = zzzc.getInt(t, (long) i6);
                        i2 = i6;
                    }
                }
                if (((C.ENCODING_PCM_MU_LAW & zzbk) != 0 ? 1 : false) != 0 && !zza((Object) t, i7, i, i5)) {
                    return false;
                }
                i6 = (267386880 & zzbk) >>> 20;
                if (i6 != 9 && i6 != 17) {
                    zzhw zzhw;
                    if (i6 != 27) {
                        if (i6 != 60 && i6 != 68) {
                            switch (i6) {
                                case 49:
                                    break;
                                case 50:
                                    Map zzk = this.zzzt.zzk(zziu.zzp(t, (long) (zzbk & 1048575)));
                                    if (!zzk.isEmpty()) {
                                        if (this.zzzt.zzo(zzbi(i7)).zzyx.zzho() == zzji.MESSAGE) {
                                            zzhw = null;
                                            for (Object next : zzk.values()) {
                                                if (zzhw == null) {
                                                    zzhw = zzhs.zzgl().zzf(next.getClass());
                                                }
                                                if (!zzhw.zzr(next)) {
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
                        } else if (zza((Object) t, i8, i7) && !zza((Object) t, zzbk, zzbh(i7))) {
                            return false;
                        }
                    }
                    List list = (List) zziu.zzp(t, (long) (zzbk & 1048575));
                    if (!list.isEmpty()) {
                        zzhw = zzbh(i7);
                        zzbk = 0;
                        while (zzbk < list.size()) {
                            if (zzhw.zzr(list.get(zzbk))) {
                                zzbk++;
                            } else {
                                i4 = 0;
                            }
                        }
                    }
                    if (i4 == 0) {
                        return false;
                    }
                } else if (zza((Object) t, i7, i, i5) && !zza((Object) t, zzbk, zzbh(i7))) {
                    return false;
                }
                i3++;
            }
        }
    }

    private static boolean zza(Object obj, int i, zzhw zzhw) {
        return zzhw.zzr(zziu.zzp(obj, (long) (i & 1048575)));
    }

    private static void zza(int i, Object obj, zzjj zzjj) throws IOException {
        if (obj instanceof String) {
            zzjj.zza(i, (String) obj);
        } else {
            zzjj.zza(i, (zzeo) obj);
        }
    }

    private final void zza(Object obj, int i, zzhv zzhv) throws IOException {
        if (zzbm(i)) {
            zziu.zza(obj, (long) (i & 1048575), zzhv.zzcv());
        } else if (this.zzzj) {
            zziu.zza(obj, (long) (i & 1048575), zzhv.readString());
        } else {
            zziu.zza(obj, (long) (i & 1048575), zzhv.zzcw());
        }
    }

    private final int zzbk(int i) {
        return this.zzzd[i + 1];
    }

    private final int zzbl(int i) {
        return this.zzzd[i + 2];
    }

    private static <T> double zzf(T t, long j) {
        return ((Double) zziu.zzp(t, j)).doubleValue();
    }

    private static <T> float zzg(T t, long j) {
        return ((Float) zziu.zzp(t, j)).floatValue();
    }

    private static <T> int zzh(T t, long j) {
        return ((Integer) zziu.zzp(t, j)).intValue();
    }

    private static <T> long zzi(T t, long j) {
        return ((Long) zziu.zzp(t, j)).longValue();
    }

    private static <T> boolean zzj(T t, long j) {
        return ((Boolean) zziu.zzp(t, j)).booleanValue();
    }

    private final boolean zzc(T t, T t2, int i) {
        return zza((Object) t, i) == zza((Object) t2, i);
    }

    private final boolean zza(T t, int i, int i2, int i3) {
        if (this.zzzk) {
            return zza((Object) t, i);
        }
        return (i2 & i3) != 0;
    }

    private final boolean zza(T t, int i) {
        if (this.zzzk) {
            i = zzbk(i);
            long j = (long) (i & 1048575);
            switch ((i & 267386880) >>> 20) {
                case 0:
                    return zziu.zzo(t, j) != 0.0d;
                case 1:
                    return zziu.zzn(t, j) != 0.0f;
                case 2:
                    return zziu.zzl(t, j) != 0;
                case 3:
                    return zziu.zzl(t, j) != 0;
                case 4:
                    return zziu.zzk(t, j) != 0;
                case 5:
                    return zziu.zzl(t, j) != 0;
                case 6:
                    return zziu.zzk(t, j) != 0;
                case 7:
                    return zziu.zzm(t, j);
                case 8:
                    Object zzp = zziu.zzp(t, j);
                    if (zzp instanceof String) {
                        return !((String) zzp).isEmpty();
                    } else {
                        if (zzp instanceof zzeo) {
                            return !zzeo.zzrx.equals(zzp);
                        } else {
                            throw new IllegalArgumentException();
                        }
                    }
                case 9:
                    return zziu.zzp(t, j) != null;
                case 10:
                    return !zzeo.zzrx.equals(zziu.zzp(t, j));
                case 11:
                    return zziu.zzk(t, j) != 0;
                case 12:
                    return zziu.zzk(t, j) != 0;
                case 13:
                    return zziu.zzk(t, j) != 0;
                case 14:
                    return zziu.zzl(t, j) != 0;
                case 15:
                    return zziu.zzk(t, j) != 0;
                case 16:
                    return zziu.zzl(t, j) != 0;
                case 17:
                    return zziu.zzp(t, j) != null;
                default:
                    throw new IllegalArgumentException();
            }
        }
        i = zzbl(i);
        return (zziu.zzk(t, (long) (i & 1048575)) & (1 << (i >>> 20))) != 0;
    }

    private final void zzb(T t, int i) {
        if (!this.zzzk) {
            i = zzbl(i);
            long j = (long) (i & 1048575);
            zziu.zzb((Object) t, j, zziu.zzk(t, j) | (1 << (i >>> 20)));
        }
    }

    private final boolean zza(T t, int i, int i2) {
        return zziu.zzk(t, (long) (zzbl(i2) & 1048575)) == i;
    }

    private final void zzb(T t, int i, int i2) {
        zziu.zzb((Object) t, (long) (zzbl(i2) & 1048575), i);
    }

    private final int zzbn(int i) {
        return (i < this.zzzf || i > this.zzzg) ? -1 : zzs(i, 0);
    }

    private final int zzr(int i, int i2) {
        return (i < this.zzzf || i > this.zzzg) ? -1 : zzs(i, i2);
    }

    private final int zzs(int i, int i2) {
        int length = (this.zzzd.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzzd[i4];
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
