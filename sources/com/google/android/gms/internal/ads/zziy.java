package com.google.android.gms.internal.ads;

import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.internal.ads.zzki.zza;
import java.util.ArrayList;
import java.util.List;

final class zziy {
    private static final int zzaob = zzqe.zzam("meta");
    private static final int zzaot = zzqe.zzam("vide");
    private static final int zzaou = zzqe.zzam("soun");
    private static final int zzaov = zzqe.zzam(MimeTypes.BASE_TYPE_TEXT);
    private static final int zzaow = zzqe.zzam("sbtl");
    private static final int zzaox = zzqe.zzam("subt");
    private static final int zzaoy = zzqe.zzam("clcp");
    private static final int zzaoz = zzqe.zzam(C.CENC_TYPE_cenc);

    /* JADX WARNING: Removed duplicated region for block: B:378:0x07ae  */
    /* JADX WARNING: Removed duplicated region for block: B:377:0x0774  */
    /* JADX WARNING: Removed duplicated region for block: B:194:0x03c0  */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x03b6  */
    /* JADX WARNING: Removed duplicated region for block: B:198:0x03c9  */
    /* JADX WARNING: Removed duplicated region for block: B:197:0x03c6  */
    /* JADX WARNING: Removed duplicated region for block: B:233:0x041e  */
    /* JADX WARNING: Removed duplicated region for block: B:283:0x0583  */
    /* JADX WARNING: Removed duplicated region for block: B:404:0x0856  */
    /* JADX WARNING: Removed duplicated region for block: B:403:0x0855 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00f4  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0109  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0119  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0116  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0147  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0145  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0154  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0152  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x01b0  */
    /* JADX WARNING: Removed duplicated region for block: B:382:0x07f0  */
    /* JADX WARNING: Removed duplicated region for block: B:403:0x0855 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:404:0x0856  */
    public static com.google.android.gms.internal.ads.zzjs zza(com.google.android.gms.internal.ads.zziw r57, com.google.android.gms.internal.ads.zzix r58, long r59, com.google.android.gms.internal.ads.zzhp r61, boolean r62) throws com.google.android.gms.internal.ads.zzfx {
        /*
        r0 = r57;
        r15 = r61;
        r1 = com.google.android.gms.internal.ads.zziv.zzame;
        r1 = r0.zzaj(r1);
        r2 = com.google.android.gms.internal.ads.zziv.zzams;
        r2 = r1.zzai(r2);
        r2 = r2.zzaos;
        r14 = 16;
        r2.setPosition(r14);
        r2 = r2.readInt();
        r3 = zzaou;
        r4 = 4;
        r11 = -1;
        if (r2 != r3) goto L_0x0023;
    L_0x0021:
        r10 = 1;
        goto L_0x0043;
    L_0x0023:
        r3 = zzaot;
        if (r2 != r3) goto L_0x0029;
    L_0x0027:
        r10 = 2;
        goto L_0x0043;
    L_0x0029:
        r3 = zzaov;
        if (r2 == r3) goto L_0x0042;
    L_0x002d:
        r3 = zzaow;
        if (r2 == r3) goto L_0x0042;
    L_0x0031:
        r3 = zzaox;
        if (r2 == r3) goto L_0x0042;
    L_0x0035:
        r3 = zzaoy;
        if (r2 != r3) goto L_0x003a;
    L_0x0039:
        goto L_0x0042;
    L_0x003a:
        r3 = zzaob;
        if (r2 != r3) goto L_0x0040;
    L_0x003e:
        r10 = r4;
        goto L_0x0043;
    L_0x0040:
        r10 = r11;
        goto L_0x0043;
    L_0x0042:
        r10 = 3;
    L_0x0043:
        r8 = 0;
        if (r10 != r11) goto L_0x0047;
    L_0x0046:
        return r8;
    L_0x0047:
        r2 = com.google.android.gms.internal.ads.zziv.zzamo;
        r2 = r0.zzai(r2);
        r2 = r2.zzaos;
        r7 = 8;
        r2.setPosition(r7);
        r3 = r2.readInt();
        r3 = com.google.android.gms.internal.ads.zziv.zzaf(r3);
        if (r3 != 0) goto L_0x0060;
    L_0x005e:
        r5 = r7;
        goto L_0x0061;
    L_0x0060:
        r5 = r14;
    L_0x0061:
        r2.zzbl(r5);
        r5 = r2.readInt();
        r2.zzbl(r4);
        r6 = r2.getPosition();
        if (r3 != 0) goto L_0x0073;
    L_0x0071:
        r12 = r4;
        goto L_0x0074;
    L_0x0073:
        r12 = r7;
    L_0x0074:
        r9 = 0;
    L_0x0075:
        if (r9 >= r12) goto L_0x0085;
    L_0x0077:
        r8 = r2.data;
        r17 = r6 + r9;
        r8 = r8[r17];
        if (r8 == r11) goto L_0x0081;
    L_0x007f:
        r6 = 0;
        goto L_0x0086;
    L_0x0081:
        r9 = r9 + 1;
        r8 = 0;
        goto L_0x0075;
    L_0x0085:
        r6 = 1;
    L_0x0086:
        r17 = 0;
        r8 = -9223372036854775807; // 0x8000000000000001 float:1.4E-45 double:-4.9E-324;
        if (r6 == 0) goto L_0x0094;
    L_0x008f:
        r2.zzbl(r12);
    L_0x0092:
        r11 = r8;
        goto L_0x00a6;
    L_0x0094:
        if (r3 != 0) goto L_0x009b;
    L_0x0096:
        r19 = r2.zzhd();
        goto L_0x009f;
    L_0x009b:
        r19 = r2.zzhh();
    L_0x009f:
        r3 = (r19 > r17 ? 1 : (r19 == r17 ? 0 : -1));
        if (r3 != 0) goto L_0x00a4;
    L_0x00a3:
        goto L_0x0092;
    L_0x00a4:
        r11 = r19;
    L_0x00a6:
        r2.zzbl(r14);
        r3 = r2.readInt();
        r6 = r2.readInt();
        r2.zzbl(r4);
        r4 = r2.readInt();
        r2 = r2.readInt();
        r14 = 65536; // 0x10000 float:9.18355E-41 double:3.2379E-319;
        r13 = -65536; // 0xffffffffffff0000 float:NaN double:NaN;
        if (r3 != 0) goto L_0x00cb;
    L_0x00c2:
        if (r6 != r14) goto L_0x00cb;
    L_0x00c4:
        if (r4 != r13) goto L_0x00cb;
    L_0x00c6:
        if (r2 != 0) goto L_0x00cb;
    L_0x00c8:
        r2 = 90;
        goto L_0x00e2;
    L_0x00cb:
        if (r3 != 0) goto L_0x00d6;
    L_0x00cd:
        if (r6 != r13) goto L_0x00d6;
    L_0x00cf:
        if (r4 != r14) goto L_0x00d6;
    L_0x00d1:
        if (r2 != 0) goto L_0x00d6;
    L_0x00d3:
        r2 = 270; // 0x10e float:3.78E-43 double:1.334E-321;
        goto L_0x00e2;
    L_0x00d6:
        if (r3 != r13) goto L_0x00e1;
    L_0x00d8:
        if (r6 != 0) goto L_0x00e1;
    L_0x00da:
        if (r4 != 0) goto L_0x00e1;
    L_0x00dc:
        if (r2 != r13) goto L_0x00e1;
    L_0x00de:
        r2 = 180; // 0xb4 float:2.52E-43 double:8.9E-322;
        goto L_0x00e2;
    L_0x00e1:
        r2 = 0;
    L_0x00e2:
        r14 = new com.google.android.gms.internal.ads.zzje;
        r14.<init>(r5, r11, r2);
        r4 = (r59 > r8 ? 1 : (r59 == r8 ? 0 : -1));
        if (r4 != 0) goto L_0x00f4;
    L_0x00eb:
        r2 = r14.zzcs;
        r19 = r2;
        r2 = r58;
        goto L_0x00f8;
    L_0x00f4:
        r2 = r58;
        r19 = r59;
    L_0x00f8:
        r2 = r2.zzaos;
        r2.setPosition(r7);
        r3 = r2.readInt();
        r3 = com.google.android.gms.internal.ads.zziv.zzaf(r3);
        if (r3 != 0) goto L_0x0109;
    L_0x0107:
        r3 = r7;
        goto L_0x010b;
    L_0x0109:
        r3 = 16;
    L_0x010b:
        r2.zzbl(r3);
        r25 = r2.zzhd();
        r2 = (r19 > r8 ? 1 : (r19 == r8 ? 0 : -1));
        if (r2 != 0) goto L_0x0119;
    L_0x0116:
        r22 = r8;
        goto L_0x0124;
    L_0x0119:
        r21 = 1000000; // 0xf4240 float:1.401298E-39 double:4.940656E-318;
        r23 = r25;
        r2 = com.google.android.gms.internal.ads.zzqe.zza(r19, r21, r23);
        r22 = r2;
    L_0x0124:
        r2 = com.google.android.gms.internal.ads.zziv.zzamf;
        r2 = r1.zzaj(r2);
        r3 = com.google.android.gms.internal.ads.zziv.zzamg;
        r2 = r2.zzaj(r3);
        r3 = com.google.android.gms.internal.ads.zziv.zzamr;
        r1 = r1.zzai(r3);
        r1 = r1.zzaos;
        r1.setPosition(r7);
        r3 = r1.readInt();
        r3 = com.google.android.gms.internal.ads.zziv.zzaf(r3);
        if (r3 != 0) goto L_0x0147;
    L_0x0145:
        r4 = r7;
        goto L_0x0149;
    L_0x0147:
        r4 = 16;
    L_0x0149:
        r1.zzbl(r4);
        r4 = r1.zzhd();
        if (r3 != 0) goto L_0x0154;
    L_0x0152:
        r3 = 4;
        goto L_0x0155;
    L_0x0154:
        r3 = r7;
    L_0x0155:
        r1.zzbl(r3);
        r1 = r1.readUnsignedShort();
        r3 = r1 >> 10;
        r3 = r3 & 31;
        r3 = r3 + 96;
        r3 = (char) r3;
        r6 = r1 >> 5;
        r6 = r6 & 31;
        r6 = r6 + 96;
        r6 = (char) r6;
        r1 = r1 & 31;
        r1 = r1 + 96;
        r1 = (char) r1;
        r8 = new java.lang.StringBuilder;
        r9 = 3;
        r8.<init>(r9);
        r8.append(r3);
        r8.append(r6);
        r8.append(r1);
        r1 = r8.toString();
        r3 = java.lang.Long.valueOf(r4);
        r13 = android.util.Pair.create(r3, r1);
        r1 = com.google.android.gms.internal.ads.zziv.zzamt;
        r1 = r2.zzai(r1);
        r12 = r1.zzaos;
        r11 = r14.id;
        r19 = r14.zzzo;
        r1 = r13.second;
        r9 = r1;
        r9 = (java.lang.String) r9;
        r1 = 12;
        r12.setPosition(r1);
        r8 = r12.readInt();
        r6 = new com.google.android.gms.internal.ads.zzjb;
        r6.<init>(r8);
        r5 = 0;
    L_0x01ae:
        if (r5 >= r8) goto L_0x07dd;
    L_0x01b0:
        r4 = r12.getPosition();
        r3 = r12.readInt();
        if (r3 <= 0) goto L_0x01bc;
    L_0x01ba:
        r1 = 1;
        goto L_0x01bd;
    L_0x01bc:
        r1 = 0;
    L_0x01bd:
        r2 = "childAtomSize should be positive";
        com.google.android.gms.internal.ads.zzpo.checkArgument(r1, r2);
        r1 = r12.readInt();
        r2 = com.google.android.gms.internal.ads.zziv.zzalb;
        if (r1 == r2) goto L_0x05ce;
    L_0x01ca:
        r2 = com.google.android.gms.internal.ads.zziv.zzalc;
        if (r1 == r2) goto L_0x05ce;
    L_0x01ce:
        r2 = com.google.android.gms.internal.ads.zziv.zzamz;
        if (r1 == r2) goto L_0x05ce;
    L_0x01d2:
        r2 = com.google.android.gms.internal.ads.zziv.zzanl;
        if (r1 == r2) goto L_0x05ce;
    L_0x01d6:
        r2 = com.google.android.gms.internal.ads.zziv.zzald;
        if (r1 == r2) goto L_0x05ce;
    L_0x01da:
        r2 = com.google.android.gms.internal.ads.zziv.zzale;
        if (r1 == r2) goto L_0x05ce;
    L_0x01de:
        r2 = com.google.android.gms.internal.ads.zziv.zzalf;
        if (r1 == r2) goto L_0x05ce;
    L_0x01e2:
        r2 = com.google.android.gms.internal.ads.zziv.zzaok;
        if (r1 == r2) goto L_0x05ce;
    L_0x01e6:
        r2 = com.google.android.gms.internal.ads.zziv.zzaol;
        if (r1 != r2) goto L_0x01ec;
    L_0x01ea:
        goto L_0x05ce;
    L_0x01ec:
        r2 = com.google.android.gms.internal.ads.zziv.zzali;
        if (r1 == r2) goto L_0x032e;
    L_0x01f0:
        r2 = com.google.android.gms.internal.ads.zziv.zzana;
        if (r1 == r2) goto L_0x032e;
    L_0x01f4:
        r2 = com.google.android.gms.internal.ads.zziv.zzaln;
        if (r1 == r2) goto L_0x032e;
    L_0x01f8:
        r2 = com.google.android.gms.internal.ads.zziv.zzalp;
        if (r1 == r2) goto L_0x032e;
    L_0x01fc:
        r2 = com.google.android.gms.internal.ads.zziv.zzalr;
        if (r1 == r2) goto L_0x032e;
    L_0x0200:
        r2 = com.google.android.gms.internal.ads.zziv.zzalu;
        if (r1 == r2) goto L_0x032e;
    L_0x0204:
        r2 = com.google.android.gms.internal.ads.zziv.zzals;
        if (r1 == r2) goto L_0x032e;
    L_0x0208:
        r2 = com.google.android.gms.internal.ads.zziv.zzalt;
        if (r1 == r2) goto L_0x032e;
    L_0x020c:
        r2 = com.google.android.gms.internal.ads.zziv.zzany;
        if (r1 == r2) goto L_0x032e;
    L_0x0210:
        r2 = com.google.android.gms.internal.ads.zziv.zzanz;
        if (r1 == r2) goto L_0x032e;
    L_0x0214:
        r2 = com.google.android.gms.internal.ads.zziv.zzall;
        if (r1 == r2) goto L_0x032e;
    L_0x0218:
        r2 = com.google.android.gms.internal.ads.zziv.zzalm;
        if (r1 == r2) goto L_0x032e;
    L_0x021c:
        r2 = com.google.android.gms.internal.ads.zziv.zzalj;
        if (r1 == r2) goto L_0x032e;
    L_0x0220:
        r2 = com.google.android.gms.internal.ads.zziv.zzaoo;
        if (r1 != r2) goto L_0x0226;
    L_0x0224:
        goto L_0x032e;
    L_0x0226:
        r2 = com.google.android.gms.internal.ads.zziv.zzanj;
        if (r1 == r2) goto L_0x027f;
    L_0x022a:
        r2 = com.google.android.gms.internal.ads.zziv.zzanu;
        if (r1 == r2) goto L_0x027f;
    L_0x022e:
        r2 = com.google.android.gms.internal.ads.zziv.zzanv;
        if (r1 == r2) goto L_0x027f;
    L_0x0232:
        r2 = com.google.android.gms.internal.ads.zziv.zzanw;
        if (r1 == r2) goto L_0x027f;
    L_0x0236:
        r2 = com.google.android.gms.internal.ads.zziv.zzanx;
        if (r1 != r2) goto L_0x023b;
    L_0x023a:
        goto L_0x027f;
    L_0x023b:
        r2 = com.google.android.gms.internal.ads.zziv.zzaon;
        if (r1 != r2) goto L_0x0267;
    L_0x023f:
        r1 = java.lang.Integer.toString(r11);
        r2 = "application/x-camera-motion";
        r37 = r5;
        r5 = 0;
        r7 = -1;
        r1 = com.google.android.gms.internal.ads.zzfs.zza(r1, r2, r5, r7, r15);
        r6.zzaad = r1;
        r28 = r3;
        r30 = r4;
        r2 = r6;
        r20 = r7;
        r29 = r8;
        r27 = r9;
        r24 = r10;
        r0 = r11;
        r54 = r12;
        r53 = r13;
        r55 = r14;
        r35 = r37;
        goto L_0x0390;
    L_0x0267:
        r28 = r3;
        r30 = r4;
        r35 = r5;
        r2 = r6;
        r29 = r8;
        r27 = r9;
        r24 = r10;
        r0 = r11;
        r54 = r12;
        r53 = r13;
        r55 = r14;
        r20 = -1;
        goto L_0x0390;
    L_0x027f:
        r37 = r5;
        r5 = 0;
        r7 = -1;
        r2 = r4 + 8;
        r20 = 8;
        r2 = r2 + 8;
        r12.setPosition(r2);
        r20 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
        r2 = com.google.android.gms.internal.ads.zziv.zzanj;
        if (r1 != r2) goto L_0x029f;
    L_0x0295:
        r1 = "application/ttml+xml";
        r2 = r1;
        r27 = r5;
        r5 = 1;
        r7 = 0;
        r24 = 8;
        goto L_0x02dd;
    L_0x029f:
        r2 = com.google.android.gms.internal.ads.zziv.zzanu;
        if (r1 != r2) goto L_0x02ba;
    L_0x02a3:
        r1 = "application/x-quicktime-tx3g";
        r2 = r3 + -8;
        r24 = 8;
        r2 = r2 + -8;
        r5 = new byte[r2];
        r7 = 0;
        r12.zze(r5, r7, r2);
        r2 = java.util.Collections.singletonList(r5);
        r27 = r2;
        r5 = 1;
        r2 = r1;
        goto L_0x02dd;
    L_0x02ba:
        r7 = 0;
        r24 = 8;
        r2 = com.google.android.gms.internal.ads.zziv.zzanv;
        if (r1 != r2) goto L_0x02c8;
    L_0x02c1:
        r1 = "application/x-mp4-vtt";
        r2 = r1;
    L_0x02c4:
        r5 = 1;
    L_0x02c5:
        r27 = 0;
        goto L_0x02dd;
    L_0x02c8:
        r2 = com.google.android.gms.internal.ads.zziv.zzanw;
        if (r1 != r2) goto L_0x02d2;
    L_0x02cc:
        r1 = "application/ttml+xml";
        r2 = r1;
        r20 = r17;
        goto L_0x02c4;
    L_0x02d2:
        r2 = com.google.android.gms.internal.ads.zziv.zzanx;
        if (r1 != r2) goto L_0x0328;
    L_0x02d6:
        r1 = "application/x-mp4-cea-608";
        r5 = 1;
        r6.zzapi = r5;
        r2 = r1;
        goto L_0x02c5;
    L_0x02dd:
        r1 = java.lang.Integer.toString(r11);
        r28 = 0;
        r29 = -1;
        r31 = 0;
        r32 = -1;
        r38 = r14;
        r14 = r3;
        r3 = r28;
        r39 = r4;
        r4 = r29;
        r28 = r5;
        r40 = r37;
        r29 = 0;
        r5 = r31;
        r41 = r6;
        r6 = r9;
        r24 = r7;
        r31 = -1;
        r7 = r32;
        r29 = r8;
        r8 = r15;
        r42 = r9;
        r24 = r10;
        r9 = r20;
        r0 = r11;
        r43 = r13;
        r13 = r31;
        r11 = r27;
        r1 = com.google.android.gms.internal.ads.zzfs.zza(r1, r2, r3, r4, r5, r6, r7, r8, r9, r11);
        r11 = r41;
        r11.zzaad = r1;
        r2 = r11;
        r54 = r12;
        r20 = r13;
        r28 = r14;
        r55 = r38;
        r30 = r39;
        goto L_0x038a;
    L_0x0328:
        r0 = new java.lang.IllegalStateException;
        r0.<init>();
        throw r0;
    L_0x032e:
        r39 = r4;
        r40 = r5;
        r29 = r8;
        r42 = r9;
        r24 = r10;
        r0 = r11;
        r43 = r13;
        r38 = r14;
        r13 = -1;
        r14 = r3;
        r11 = r6;
        r10 = r39;
        r4 = r10 + 8;
        r9 = 8;
        r4 = r4 + r9;
        r12.setPosition(r4);
        if (r62 == 0) goto L_0x0355;
    L_0x034c:
        r2 = r12.readUnsignedShort();
        r3 = 6;
        r12.zzbl(r3);
        goto L_0x0359;
    L_0x0355:
        r12.zzbl(r9);
        r2 = 0;
    L_0x0359:
        if (r2 == 0) goto L_0x0398;
    L_0x035b:
        r8 = 1;
        if (r2 != r8) goto L_0x0360;
    L_0x035e:
        r7 = 2;
        goto L_0x039a;
    L_0x0360:
        r7 = 2;
        if (r2 != r7) goto L_0x037f;
    L_0x0363:
        r2 = 16;
        r12.zzbl(r2);
        r2 = r12.readLong();
        r2 = java.lang.Double.longBitsToDouble(r2);
        r2 = java.lang.Math.round(r2);
        r2 = (int) r2;
        r3 = r12.zzhg();
        r4 = 20;
        r12.zzbl(r4);
        goto L_0x03ae;
    L_0x037f:
        r30 = r10;
        r2 = r11;
        r54 = r12;
        r20 = r13;
        r28 = r14;
        r55 = r38;
    L_0x038a:
        r35 = r40;
        r27 = r42;
        r53 = r43;
    L_0x0390:
        r21 = 0;
    L_0x0392:
        r31 = 3;
    L_0x0394:
        r32 = 16;
        goto L_0x07bf;
    L_0x0398:
        r7 = 2;
        r8 = 1;
    L_0x039a:
        r3 = r12.readUnsignedShort();
        r4 = 6;
        r12.zzbl(r4);
        r4 = r12.zzhf();
        if (r2 != r8) goto L_0x03ad;
    L_0x03a8:
        r2 = 16;
        r12.zzbl(r2);
    L_0x03ad:
        r2 = r4;
    L_0x03ae:
        r4 = r12.getPosition();
        r5 = com.google.android.gms.internal.ads.zziv.zzana;
        if (r1 != r5) goto L_0x03c0;
    L_0x03b6:
        r6 = r40;
        r1 = zza(r12, r10, r14, r11, r6);
        r12.setPosition(r4);
        goto L_0x03c2;
    L_0x03c0:
        r6 = r40;
    L_0x03c2:
        r5 = com.google.android.gms.internal.ads.zziv.zzaln;
        if (r1 != r5) goto L_0x03c9;
    L_0x03c6:
        r1 = "audio/ac3";
        goto L_0x0413;
    L_0x03c9:
        r5 = com.google.android.gms.internal.ads.zziv.zzalp;
        if (r1 != r5) goto L_0x03d0;
    L_0x03cd:
        r1 = "audio/eac3";
        goto L_0x0413;
    L_0x03d0:
        r5 = com.google.android.gms.internal.ads.zziv.zzalr;
        if (r1 != r5) goto L_0x03d7;
    L_0x03d4:
        r1 = "audio/vnd.dts";
        goto L_0x0413;
    L_0x03d7:
        r5 = com.google.android.gms.internal.ads.zziv.zzals;
        if (r1 == r5) goto L_0x0411;
    L_0x03db:
        r5 = com.google.android.gms.internal.ads.zziv.zzalt;
        if (r1 != r5) goto L_0x03e0;
    L_0x03df:
        goto L_0x0411;
    L_0x03e0:
        r5 = com.google.android.gms.internal.ads.zziv.zzalu;
        if (r1 != r5) goto L_0x03e7;
    L_0x03e4:
        r1 = "audio/vnd.dts.hd;profile=lbr";
        goto L_0x0413;
    L_0x03e7:
        r5 = com.google.android.gms.internal.ads.zziv.zzany;
        if (r1 != r5) goto L_0x03ee;
    L_0x03eb:
        r1 = "audio/3gpp";
        goto L_0x0413;
    L_0x03ee:
        r5 = com.google.android.gms.internal.ads.zziv.zzanz;
        if (r1 != r5) goto L_0x03f5;
    L_0x03f2:
        r1 = "audio/amr-wb";
        goto L_0x0413;
    L_0x03f5:
        r5 = com.google.android.gms.internal.ads.zziv.zzall;
        if (r1 == r5) goto L_0x040e;
    L_0x03f9:
        r5 = com.google.android.gms.internal.ads.zziv.zzalm;
        if (r1 != r5) goto L_0x03fe;
    L_0x03fd:
        goto L_0x040e;
    L_0x03fe:
        r5 = com.google.android.gms.internal.ads.zziv.zzalj;
        if (r1 != r5) goto L_0x0405;
    L_0x0402:
        r1 = "audio/mpeg";
        goto L_0x0413;
    L_0x0405:
        r5 = com.google.android.gms.internal.ads.zziv.zzaoo;
        if (r1 != r5) goto L_0x040c;
    L_0x0409:
        r1 = "audio/alac";
        goto L_0x0413;
    L_0x040c:
        r1 = 0;
        goto L_0x0413;
    L_0x040e:
        r1 = "audio/raw";
        goto L_0x0413;
    L_0x0411:
        r1 = "audio/vnd.dts.hd";
    L_0x0413:
        r21 = r2;
        r20 = r3;
        r5 = r4;
        r3 = 0;
        r4 = r1;
    L_0x041a:
        r1 = r5 - r10;
        if (r1 >= r14) goto L_0x056e;
    L_0x041e:
        r12.setPosition(r5);
        r2 = r12.readInt();
        if (r2 <= 0) goto L_0x0429;
    L_0x0427:
        r1 = r8;
        goto L_0x042a;
    L_0x0429:
        r1 = 0;
    L_0x042a:
        r7 = "childAtomSize should be positive";
        com.google.android.gms.internal.ads.zzpo.checkArgument(r1, r7);
        r1 = r12.readInt();
        r7 = com.google.android.gms.internal.ads.zziv.zzamj;
        if (r1 == r7) goto L_0x04e4;
    L_0x0437:
        if (r62 == 0) goto L_0x043f;
    L_0x0439:
        r7 = com.google.android.gms.internal.ads.zziv.zzalk;
        if (r1 != r7) goto L_0x043f;
    L_0x043d:
        goto L_0x04e4;
    L_0x043f:
        r7 = com.google.android.gms.internal.ads.zziv.zzalo;
        if (r1 != r7) goto L_0x0468;
    L_0x0443:
        r1 = r5 + 8;
        r12.setPosition(r1);
        r1 = java.lang.Integer.toString(r0);
        r7 = r42;
        r1 = com.google.android.gms.internal.ads.zzgg.zza(r12, r1, r7, r15);
        r11.zzaad = r1;
    L_0x0454:
        r13 = r2;
        r44 = r3;
        r47 = r4;
        r4 = r5;
        r46 = r6;
        r27 = r7;
        r48 = r10;
        r49 = r14;
        r28 = 2;
        r14 = r11;
    L_0x0465:
        r11 = 0;
        goto L_0x04df;
    L_0x0468:
        r7 = r42;
        r8 = com.google.android.gms.internal.ads.zziv.zzalq;
        if (r1 != r8) goto L_0x047e;
    L_0x046e:
        r1 = r5 + 8;
        r12.setPosition(r1);
        r1 = java.lang.Integer.toString(r0);
        r1 = com.google.android.gms.internal.ads.zzgg.zzb(r12, r1, r7, r15);
        r11.zzaad = r1;
        goto L_0x0454;
    L_0x047e:
        r8 = com.google.android.gms.internal.ads.zziv.zzalv;
        if (r1 != r8) goto L_0x04bc;
    L_0x0482:
        r1 = java.lang.Integer.toString(r0);
        r8 = 0;
        r27 = -1;
        r28 = -1;
        r30 = 0;
        r31 = 0;
        r13 = r2;
        r2 = r4;
        r44 = r3;
        r3 = r8;
        r8 = r4;
        r4 = r27;
        r45 = r5;
        r5 = r28;
        r46 = r6;
        r6 = r20;
        r27 = r7;
        r28 = 2;
        r7 = r21;
        r47 = r8;
        r8 = r30;
        r9 = r15;
        r48 = r10;
        r10 = r31;
        r49 = r14;
        r14 = r11;
        r11 = r27;
        r1 = com.google.android.gms.internal.ads.zzfs.zza(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11);
        r14.zzaad = r1;
    L_0x04b9:
        r4 = r45;
        goto L_0x0465;
    L_0x04bc:
        r13 = r2;
        r44 = r3;
        r47 = r4;
        r45 = r5;
        r46 = r6;
        r27 = r7;
        r48 = r10;
        r49 = r14;
        r28 = 2;
        r14 = r11;
        r2 = com.google.android.gms.internal.ads.zziv.zzaoo;
        if (r1 != r2) goto L_0x04b9;
    L_0x04d2:
        r1 = new byte[r13];
        r4 = r45;
        r12.setPosition(r4);
        r11 = 0;
        r12.zze(r1, r11, r13);
        r3 = r1;
        goto L_0x04e1;
    L_0x04df:
        r3 = r44;
    L_0x04e1:
        r10 = -1;
        goto L_0x0559;
    L_0x04e4:
        r13 = r2;
        r44 = r3;
        r47 = r4;
        r4 = r5;
        r46 = r6;
        r48 = r10;
        r49 = r14;
        r27 = r42;
        r28 = 2;
        r14 = r11;
        r11 = 0;
        r2 = com.google.android.gms.internal.ads.zziv.zzamj;
        if (r1 != r2) goto L_0x04fd;
    L_0x04fa:
        r1 = r4;
    L_0x04fb:
        r10 = -1;
        goto L_0x0523;
    L_0x04fd:
        r1 = r12.getPosition();
    L_0x0501:
        r2 = r1 - r4;
        if (r2 >= r13) goto L_0x0521;
    L_0x0505:
        r12.setPosition(r1);
        r2 = r12.readInt();
        if (r2 <= 0) goto L_0x0510;
    L_0x050e:
        r3 = 1;
        goto L_0x0511;
    L_0x0510:
        r3 = r11;
    L_0x0511:
        r5 = "childAtomSize should be positive";
        com.google.android.gms.internal.ads.zzpo.checkArgument(r3, r5);
        r3 = r12.readInt();
        r5 = com.google.android.gms.internal.ads.zziv.zzamj;
        if (r3 != r5) goto L_0x051f;
    L_0x051e:
        goto L_0x04fb;
    L_0x051f:
        r1 = r1 + r2;
        goto L_0x0501;
    L_0x0521:
        r1 = -1;
        goto L_0x04fb;
    L_0x0523:
        if (r1 == r10) goto L_0x0553;
    L_0x0525:
        r1 = zzb(r12, r1);
        r2 = r1.first;
        r2 = (java.lang.String) r2;
        r1 = r1.second;
        r3 = r1;
        r3 = (byte[]) r3;
        r1 = "audio/mp4a-latm";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x0557;
    L_0x053a:
        r1 = com.google.android.gms.internal.ads.zzpp.zzf(r3);
        r5 = r1.first;
        r5 = (java.lang.Integer) r5;
        r5 = r5.intValue();
        r1 = r1.second;
        r1 = (java.lang.Integer) r1;
        r1 = r1.intValue();
        r20 = r1;
        r21 = r5;
        goto L_0x0557;
    L_0x0553:
        r3 = r44;
        r2 = r47;
    L_0x0557:
        r47 = r2;
    L_0x0559:
        r5 = r4 + r13;
        r13 = r10;
        r11 = r14;
        r42 = r27;
        r7 = r28;
        r6 = r46;
        r4 = r47;
        r10 = r48;
        r14 = r49;
        r8 = 1;
        r9 = 8;
        goto L_0x041a;
    L_0x056e:
        r44 = r3;
        r47 = r4;
        r46 = r6;
        r28 = r7;
        r48 = r10;
        r10 = r13;
        r49 = r14;
        r27 = r42;
        r14 = r11;
        r11 = 0;
        r1 = r14.zzaad;
        if (r1 != 0) goto L_0x05ba;
    L_0x0583:
        r2 = r47;
        if (r2 == 0) goto L_0x05ba;
    L_0x0587:
        r1 = "audio/raw";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x0592;
    L_0x058f:
        r8 = r28;
        goto L_0x0593;
    L_0x0592:
        r8 = r10;
    L_0x0593:
        r1 = java.lang.Integer.toString(r0);
        r3 = 0;
        r4 = -1;
        r5 = -1;
        r6 = r44;
        if (r6 != 0) goto L_0x05a0;
    L_0x059e:
        r9 = 0;
        goto L_0x05a5;
    L_0x05a0:
        r6 = java.util.Collections.singletonList(r6);
        r9 = r6;
    L_0x05a5:
        r13 = 0;
        r6 = r20;
        r7 = r21;
        r20 = r10;
        r10 = r15;
        r21 = r11;
        r11 = r13;
        r13 = r12;
        r12 = r27;
        r1 = com.google.android.gms.internal.ads.zzfs.zza(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12);
        r14.zzaad = r1;
        goto L_0x05bf;
    L_0x05ba:
        r20 = r10;
        r21 = r11;
        r13 = r12;
    L_0x05bf:
        r54 = r13;
        r2 = r14;
        r55 = r38;
        r53 = r43;
        r35 = r46;
        r30 = r48;
        r28 = r49;
        goto L_0x0392;
    L_0x05ce:
        r49 = r3;
        r48 = r4;
        r46 = r5;
        r29 = r8;
        r27 = r9;
        r24 = r10;
        r0 = r11;
        r43 = r13;
        r38 = r14;
        r20 = -1;
        r21 = 0;
        r14 = r6;
        r13 = r12;
        r12 = r48;
        r4 = r12 + 8;
        r11 = 8;
        r4 = r4 + r11;
        r13.setPosition(r4);
        r10 = 16;
        r13.zzbl(r10);
        r6 = r13.readUnsignedShort();
        r7 = r13.readUnsignedShort();
        r2 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r3 = 50;
        r13.zzbl(r3);
        r3 = r13.getPosition();
        r4 = com.google.android.gms.internal.ads.zziv.zzamz;
        if (r1 != r4) goto L_0x0617;
    L_0x060b:
        r9 = r46;
        r8 = r49;
        r1 = zza(r13, r12, r8, r14, r9);
        r13.setPosition(r3);
        goto L_0x061b;
    L_0x0617:
        r9 = r46;
        r8 = r49;
    L_0x061b:
        r30 = r2;
        r32 = r20;
        r4 = r21;
        r2 = 0;
        r28 = 0;
        r31 = 0;
    L_0x0626:
        r5 = r3 - r12;
        if (r5 >= r8) goto L_0x076f;
    L_0x062a:
        r13.setPosition(r3);
        r5 = r13.getPosition();
        r10 = r13.readInt();
        if (r10 != 0) goto L_0x063f;
    L_0x0637:
        r33 = r13.getPosition();
        r11 = r33 - r12;
        if (r11 == r8) goto L_0x076f;
    L_0x063f:
        if (r10 <= 0) goto L_0x0645;
    L_0x0641:
        r50 = r8;
        r11 = 1;
        goto L_0x0649;
    L_0x0645:
        r50 = r8;
        r11 = r21;
    L_0x0649:
        r8 = "childAtomSize should be positive";
        com.google.android.gms.internal.ads.zzpo.checkArgument(r11, r8);
        r8 = r13.readInt();
        r11 = com.google.android.gms.internal.ads.zziv.zzamh;
        if (r8 != r11) goto L_0x067d;
    L_0x0656:
        if (r2 != 0) goto L_0x065a;
    L_0x0658:
        r2 = 1;
        goto L_0x065c;
    L_0x065a:
        r2 = r21;
    L_0x065c:
        com.google.android.gms.internal.ads.zzpo.checkState(r2);
        r2 = "video/avc";
        r5 = r5 + 8;
        r13.setPosition(r5);
        r5 = com.google.android.gms.internal.ads.zzqh.zzg(r13);
        r8 = r5.zzzl;
        r11 = r5.zzakx;
        r14.zzakx = r11;
        if (r4 != 0) goto L_0x0676;
    L_0x0672:
        r5 = r5.zzbhq;
        r30 = r5;
    L_0x0676:
        r51 = r1;
        r28 = r8;
    L_0x067a:
        r11 = 3;
        goto L_0x0764;
    L_0x067d:
        r11 = com.google.android.gms.internal.ads.zziv.zzami;
        if (r8 != r11) goto L_0x069c;
    L_0x0681:
        if (r2 != 0) goto L_0x0685;
    L_0x0683:
        r2 = 1;
        goto L_0x0687;
    L_0x0685:
        r2 = r21;
    L_0x0687:
        com.google.android.gms.internal.ads.zzpo.checkState(r2);
        r2 = "video/hevc";
        r5 = r5 + 8;
        r13.setPosition(r5);
        r5 = com.google.android.gms.internal.ads.zzqn.zzi(r13);
        r8 = r5.zzzl;
        r5 = r5.zzakx;
        r14.zzakx = r5;
        goto L_0x0676;
    L_0x069c:
        r11 = com.google.android.gms.internal.ads.zziv.zzaom;
        if (r8 != r11) goto L_0x06b3;
    L_0x06a0:
        if (r2 != 0) goto L_0x06a4;
    L_0x06a2:
        r2 = 1;
        goto L_0x06a6;
    L_0x06a4:
        r2 = r21;
    L_0x06a6:
        com.google.android.gms.internal.ads.zzpo.checkState(r2);
        r2 = com.google.android.gms.internal.ads.zziv.zzaok;
        if (r1 != r2) goto L_0x06b0;
    L_0x06ad:
        r2 = "video/x-vnd.on2.vp8";
        goto L_0x06c2;
    L_0x06b0:
        r2 = "video/x-vnd.on2.vp9";
        goto L_0x06c2;
    L_0x06b3:
        r11 = com.google.android.gms.internal.ads.zziv.zzalg;
        if (r8 != r11) goto L_0x06c5;
    L_0x06b7:
        if (r2 != 0) goto L_0x06bb;
    L_0x06b9:
        r2 = 1;
        goto L_0x06bd;
    L_0x06bb:
        r2 = r21;
    L_0x06bd:
        com.google.android.gms.internal.ads.zzpo.checkState(r2);
        r2 = "video/3gpp";
    L_0x06c2:
        r51 = r1;
        goto L_0x067a;
    L_0x06c5:
        r11 = com.google.android.gms.internal.ads.zziv.zzamj;
        if (r8 != r11) goto L_0x06e6;
    L_0x06c9:
        if (r2 != 0) goto L_0x06cd;
    L_0x06cb:
        r2 = 1;
        goto L_0x06cf;
    L_0x06cd:
        r2 = r21;
    L_0x06cf:
        com.google.android.gms.internal.ads.zzpo.checkState(r2);
        r2 = zzb(r13, r5);
        r5 = r2.first;
        r5 = (java.lang.String) r5;
        r2 = r2.second;
        r2 = (byte[]) r2;
        r28 = java.util.Collections.singletonList(r2);
        r51 = r1;
        r2 = r5;
        goto L_0x067a;
    L_0x06e6:
        r11 = com.google.android.gms.internal.ads.zziv.zzani;
        if (r8 != r11) goto L_0x0700;
    L_0x06ea:
        r5 = r5 + 8;
        r13.setPosition(r5);
        r4 = r13.zzhg();
        r5 = r13.zzhg();
        r4 = (float) r4;
        r5 = (float) r5;
        r30 = r4 / r5;
        r51 = r1;
        r4 = 1;
        goto L_0x067a;
    L_0x0700:
        r11 = com.google.android.gms.internal.ads.zziv.zzaoi;
        if (r8 != r11) goto L_0x0737;
    L_0x0704:
        r8 = r5 + 8;
    L_0x0706:
        r11 = r8 - r5;
        if (r11 >= r10) goto L_0x072d;
    L_0x070a:
        r13.setPosition(r8);
        r11 = r13.readInt();
        r51 = r1;
        r1 = r13.readInt();
        r52 = r4;
        r4 = com.google.android.gms.internal.ads.zziv.zzaoj;
        if (r1 != r4) goto L_0x0727;
    L_0x071d:
        r1 = r13.data;
        r11 = r11 + r8;
        r8 = java.util.Arrays.copyOfRange(r1, r8, r11);
        r31 = r8;
        goto L_0x0733;
    L_0x0727:
        r8 = r8 + r11;
        r1 = r51;
        r4 = r52;
        goto L_0x0706;
    L_0x072d:
        r51 = r1;
        r52 = r4;
        r31 = 0;
    L_0x0733:
        r4 = r52;
        goto L_0x067a;
    L_0x0737:
        r51 = r1;
        r52 = r4;
        r1 = com.google.android.gms.internal.ads.zziv.zzaoh;
        if (r8 != r1) goto L_0x0761;
    L_0x073f:
        r1 = r13.readUnsignedByte();
        r11 = 3;
        r13.zzbl(r11);
        if (r1 != 0) goto L_0x0762;
    L_0x0749:
        r1 = r13.readUnsignedByte();
        switch(r1) {
            case 0: goto L_0x075e;
            case 1: goto L_0x0759;
            case 2: goto L_0x0754;
            case 3: goto L_0x0751;
            default: goto L_0x0750;
        };
    L_0x0750:
        goto L_0x0762;
    L_0x0751:
        r32 = r11;
        goto L_0x0762;
    L_0x0754:
        r4 = r52;
        r32 = 2;
        goto L_0x0764;
    L_0x0759:
        r4 = r52;
        r32 = 1;
        goto L_0x0764;
    L_0x075e:
        r32 = r21;
        goto L_0x0762;
    L_0x0761:
        r11 = 3;
    L_0x0762:
        r4 = r52;
    L_0x0764:
        r3 = r3 + r10;
        r8 = r50;
        r1 = r51;
        r10 = 16;
        r11 = 8;
        goto L_0x0626;
    L_0x076f:
        r50 = r8;
        r11 = 3;
        if (r2 == 0) goto L_0x07ae;
    L_0x0774:
        r1 = java.lang.Integer.toString(r0);
        r3 = 0;
        r4 = -1;
        r5 = -1;
        r8 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
        r33 = 0;
        r34 = r50;
        r35 = r9;
        r9 = r28;
        r28 = 16;
        r10 = r19;
        r36 = r11;
        r11 = r30;
        r30 = r12;
        r12 = r31;
        r54 = r13;
        r31 = r36;
        r53 = r43;
        r13 = r32;
        r56 = r14;
        r32 = r28;
        r28 = r34;
        r55 = r38;
        r14 = r33;
        r15 = r61;
        r1 = com.google.android.gms.internal.ads.zzfs.zza(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15);
        r2 = r56;
        r2.zzaad = r1;
        goto L_0x07bf;
    L_0x07ae:
        r35 = r9;
        r31 = r11;
        r30 = r12;
        r54 = r13;
        r2 = r14;
        r55 = r38;
        r53 = r43;
        r28 = r50;
        goto L_0x0394;
    L_0x07bf:
        r4 = r30 + r28;
        r1 = r54;
        r1.setPosition(r4);
        r5 = r35 + 1;
        r15 = r61;
        r11 = r0;
        r12 = r1;
        r6 = r2;
        r10 = r24;
        r9 = r27;
        r8 = r29;
        r13 = r53;
        r14 = r55;
        r0 = r57;
        r7 = 8;
        goto L_0x01ae;
    L_0x07dd:
        r2 = r6;
        r24 = r10;
        r53 = r13;
        r55 = r14;
        r21 = 0;
        r0 = com.google.android.gms.internal.ads.zziv.zzamp;
        r1 = r57;
        r0 = r1.zzaj(r0);
        if (r0 == 0) goto L_0x084c;
    L_0x07f0:
        r1 = com.google.android.gms.internal.ads.zziv.zzamq;
        r0 = r0.zzai(r1);
        if (r0 != 0) goto L_0x07f9;
    L_0x07f8:
        goto L_0x084c;
    L_0x07f9:
        r0 = r0.zzaos;
        r1 = 8;
        r0.setPosition(r1);
        r1 = r0.readInt();
        r1 = com.google.android.gms.internal.ads.zziv.zzaf(r1);
        r3 = r0.zzhg();
        r4 = new long[r3];
        r5 = new long[r3];
        r6 = r21;
    L_0x0812:
        if (r6 >= r3) goto L_0x0845;
    L_0x0814:
        r7 = 1;
        if (r1 != r7) goto L_0x081c;
    L_0x0817:
        r8 = r0.zzhh();
        goto L_0x0820;
    L_0x081c:
        r8 = r0.zzhd();
    L_0x0820:
        r4[r6] = r8;
        if (r1 != r7) goto L_0x0829;
    L_0x0824:
        r8 = r0.readLong();
        goto L_0x082e;
    L_0x0829:
        r8 = r0.readInt();
        r8 = (long) r8;
    L_0x082e:
        r5[r6] = r8;
        r8 = r0.readShort();
        if (r8 == r7) goto L_0x083e;
    L_0x0836:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "Unsupported media rate.";
        r0.<init>(r1);
        throw r0;
    L_0x083e:
        r8 = 2;
        r0.zzbl(r8);
        r6 = r6 + 1;
        goto L_0x0812;
    L_0x0845:
        r0 = android.util.Pair.create(r4, r5);
        r1 = r0;
        r0 = 0;
        goto L_0x0851;
    L_0x084c:
        r0 = 0;
        r1 = android.util.Pair.create(r0, r0);
    L_0x0851:
        r3 = r2.zzaad;
        if (r3 != 0) goto L_0x0856;
    L_0x0855:
        return r0;
    L_0x0856:
        r0 = new com.google.android.gms.internal.ads.zzjs;
        r3 = r55;
        r16 = r3.id;
        r3 = r53;
        r3 = r3.first;
        r3 = (java.lang.Long) r3;
        r18 = r3.longValue();
        r3 = r2.zzaad;
        r4 = r2.zzapi;
        r5 = r2.zzaph;
        r2 = r2.zzakx;
        r6 = r1.first;
        r28 = r6;
        r28 = (long[]) r28;
        r1 = r1.second;
        r29 = r1;
        r29 = (long[]) r29;
        r15 = r0;
        r17 = r24;
        r20 = r25;
        r24 = r3;
        r25 = r4;
        r26 = r5;
        r27 = r2;
        r15.<init>(r16, r17, r18, r20, r22, r24, r25, r26, r27, r28, r29);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zziy.zza(com.google.android.gms.internal.ads.zziw, com.google.android.gms.internal.ads.zzix, long, com.google.android.gms.internal.ads.zzhp, boolean):com.google.android.gms.internal.ads.zzjs");
    }

    public static zzjv zza(zzjs zzjs, zziw zziw, zzid zzid) throws zzfx {
        zzja zzjc;
        zzjs zzjs2 = zzjs;
        zziw zziw2 = zziw;
        zzid zzid2 = zzid;
        zzix zzai = zziw2.zzai(zziv.zzanq);
        if (zzai != null) {
            zzjc = new zzjc(zzai);
        } else {
            zzai = zziw2.zzai(zziv.zzanr);
            if (zzai == null) {
                throw new zzfx("Track has no sample table size information");
            }
            zzjc = new zzjd(zzai);
        }
        int zzef = zzjc.zzef();
        if (zzef == 0) {
            return new zzjv(new long[0], new int[0], 0, new long[0], new int[0]);
        }
        boolean z;
        int zzhg;
        int zzhg2;
        Object obj;
        Object obj2;
        long[] jArr;
        Object obj3;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        long j;
        long j2;
        int i6;
        Object obj4;
        Object obj5;
        Object obj6;
        zzix zzai2 = zziw2.zzai(zziv.zzans);
        if (zzai2 == null) {
            zzai2 = zziw2.zzai(zziv.zzant);
            z = true;
        } else {
            z = false;
        }
        zzpx zzpx = zzai2.zzaos;
        zzpx zzpx2 = zziw2.zzai(zziv.zzanp).zzaos;
        zzpx zzpx3 = zziw2.zzai(zziv.zzanm).zzaos;
        zzix zzai3 = zziw2.zzai(zziv.zzann);
        zzpx zzpx4 = zzai3 != null ? zzai3.zzaos : null;
        zzix zzai4 = zziw2.zzai(zziv.zzano);
        zzpx zzpx5 = zzai4 != null ? zzai4.zzaos : null;
        zziz zziz = new zziz(zzpx2, zzpx, z);
        zzpx3.setPosition(12);
        int zzhg3 = zzpx3.zzhg() - 1;
        int zzhg4 = zzpx3.zzhg();
        int zzhg5 = zzpx3.zzhg();
        if (zzpx5 != null) {
            zzpx5.setPosition(12);
            zzhg = zzpx5.zzhg();
        } else {
            zzhg = 0;
        }
        int i7 = -1;
        if (zzpx4 != null) {
            zzpx4.setPosition(12);
            zzhg2 = zzpx4.zzhg();
            if (zzhg2 > 0) {
                i7 = zzpx4.zzhg() - 1;
            } else {
                zzpx4 = null;
            }
        } else {
            zzhg2 = 0;
        }
        int i8 = (zzjc.zzeh() && MimeTypes.AUDIO_RAW.equals(zzjs2.zzaad.zzzj) && zzhg3 == 0 && zzhg == 0 && zzhg2 == 0) ? 1 : 0;
        long j3 = 0;
        long j4;
        if (i8 == 0) {
            int i9;
            obj = new long[zzef];
            obj2 = new int[zzef];
            jArr = new long[zzef];
            int i10 = zzhg2;
            obj3 = new int[zzef];
            zzpx zzpx6 = zzpx3;
            int i11 = zzhg;
            i = i7;
            long j5 = 0;
            i2 = i10;
            zzhg = 0;
            i7 = 0;
            i3 = 0;
            int i12 = 0;
            i4 = zzhg3;
            j4 = j5;
            zzhg3 = 0;
            int i13 = zzhg5;
            zzhg5 = zzhg4;
            zzhg4 = i13;
            while (zzhg3 < zzef) {
                int i14;
                zzpx zzpx7;
                while (i3 == 0) {
                    i5 = zzef;
                    zzpo.checkState(zziz.zzee());
                    i14 = zzhg4;
                    i9 = i4;
                    j = zziz.zzapb;
                    i3 = zziz.zzapa;
                    j4 = j;
                    zzef = i5;
                    zzhg4 = i14;
                    i4 = i9;
                }
                i5 = zzef;
                i14 = zzhg4;
                i9 = i4;
                if (zzpx5 != null) {
                    while (i12 == 0 && i11 > 0) {
                        i12 = zzpx5.zzhg();
                        i7 = zzpx5.readInt();
                        i11--;
                    }
                    i12--;
                }
                zzef = i7;
                obj[zzhg3] = j4;
                obj2[zzhg3] = zzjc.zzeg();
                if (obj2[zzhg3] > zzhg) {
                    zzhg = obj2[zzhg3];
                }
                jArr[zzhg3] = j5 + ((long) zzef);
                obj3[zzhg3] = zzpx4 == null ? 1 : 0;
                if (zzhg3 == i) {
                    obj3[zzhg3] = 1;
                    i2--;
                    if (i2 > 0) {
                        i = zzpx4.zzhg() - 1;
                    }
                }
                zzhg4 = i2;
                int i15 = i;
                int i16 = zzef;
                i2 = i14;
                long j6 = j5 + ((long) i2);
                zzhg5--;
                if (zzhg5 != 0 || i9 <= 0) {
                    zzpx7 = zzpx6;
                    i4 = i9;
                } else {
                    zzpx7 = zzpx6;
                    i4 = i9 - 1;
                    zzhg5 = zzpx7.zzhg();
                    i2 = zzpx7.zzhg();
                }
                zzpx zzpx8 = zzpx7;
                i3--;
                zzhg3++;
                j4 += (long) obj2[zzhg3];
                zzef = i5;
                j5 = j6;
                i7 = i16;
                i = i15;
                zzpx6 = zzpx8;
                i13 = zzhg4;
                zzhg4 = i2;
                i2 = i13;
            }
            i5 = zzef;
            i9 = i4;
            zzpo.checkArgument(i12 == 0);
            while (i11 > 0) {
                zzpo.checkArgument(zzpx5.zzhg() == 0);
                zzpx5.readInt();
                i11--;
            }
            if (i2 == 0 && zzhg5 == 0 && i3 == 0 && i9 == 0) {
                zzjs2 = zzjs;
            } else {
                zzhg4 = i2;
                zzjs2 = zzjs;
                i = zzjs2.id;
                StringBuilder stringBuilder = new StringBuilder(215);
                stringBuilder.append("Inconsistent stbl box for track ");
                stringBuilder.append(i);
                stringBuilder.append(": remainingSynchronizationSamples ");
                stringBuilder.append(zzhg4);
                stringBuilder.append(", remainingSamplesAtTimestampDelta ");
                stringBuilder.append(zzhg5);
                stringBuilder.append(", remainingSamplesInChunk ");
                stringBuilder.append(i3);
                stringBuilder.append(", remainingTimestampDeltaChanges ");
                stringBuilder.append(i9);
                Log.w("AtomParsers", stringBuilder.toString());
            }
            j2 = j5;
            i6 = zzhg;
        } else {
            i5 = zzef;
            long[] jArr2 = new long[zziz.length];
            int[] iArr = new int[zziz.length];
            while (zziz.zzee()) {
                jArr2[zziz.index] = zziz.zzapb;
                iArr[zziz.index] = zziz.zzapa;
            }
            zzef = zzjc.zzeg();
            long j7 = (long) zzhg5;
            zzhg2 = 8192 / zzef;
            zzhg4 = 0;
            for (int i42 : iArr) {
                zzhg4 += zzqe.zzf(i42, zzhg2);
            }
            long[] jArr3 = new long[zzhg4];
            int[] iArr2 = new int[zzhg4];
            long[] jArr4 = new long[zzhg4];
            int[] iArr3 = new int[zzhg4];
            zzhg3 = 0;
            zzhg4 = 0;
            int i17 = 0;
            for (int i18 = 0; i18 < iArr.length; i18++) {
                long[] jArr5;
                int[] iArr4;
                i42 = iArr[i18];
                j4 = jArr2[i18];
                while (i42 > 0) {
                    i7 = Math.min(zzhg2, i42);
                    jArr3[zzhg4] = j4;
                    iArr2[zzhg4] = zzef * i7;
                    jArr5 = jArr2;
                    i17 = Math.max(i17, iArr2[zzhg4]);
                    iArr4 = iArr;
                    jArr4[zzhg4] = ((long) zzhg3) * j7;
                    iArr3[zzhg4] = 1;
                    zzhg3 += i7;
                    i42 -= i7;
                    zzhg4++;
                    j4 += (long) iArr2[zzhg4];
                    jArr2 = jArr5;
                    iArr = iArr4;
                }
                jArr5 = jArr2;
                iArr4 = iArr;
            }
            zzjh zzjh = new zzjh(jArr3, iArr2, i17, jArr4, iArr3);
            obj = zzjh.zzagu;
            obj2 = zzjh.zzagt;
            i = zzjh.zzapp;
            jArr = zzjh.zzapq;
            obj3 = zzjh.zzapr;
            i6 = i;
            j2 = 0;
        }
        if (zzjs2.zzaso != null) {
            zzid zzid3 = zzid;
            if (!zzid.zzea()) {
                long j8;
                long zza;
                int i19;
                Object obj7;
                Object obj8;
                Object obj9;
                long[] jArr6;
                Object obj10;
                if (zzjs2.zzaso.length == 1 && zzjs2.type == 1 && jArr.length >= 2) {
                    j8 = zzjs2.zzasp[0];
                    zza = j8 + zzqe.zza(zzjs2.zzaso[0], zzjs2.zzcr, zzjs2.zzasl);
                    if (jArr[0] <= j8 && j8 < jArr[1] && jArr[jArr.length - 1] < zza && zza <= j2) {
                        long j9 = j2 - zza;
                        j2 = zzqe.zza(j8 - jArr[0], (long) zzjs2.zzaad.zzzu, zzjs2.zzcr);
                        long zza2 = zzqe.zza(j9, (long) zzjs2.zzaad.zzzu, zzjs2.zzcr);
                        if (!(j2 == 0 && zza2 == 0) && j2 <= 2147483647L && zza2 <= 2147483647L) {
                            zzid3.zzzw = (int) j2;
                            zzid3.zzzx = (int) zza2;
                            zzqe.zza(jArr, 1000000, zzjs2.zzcr);
                            return new zzjv(obj, obj2, i6, jArr, obj3);
                        }
                    }
                }
                if (zzjs2.zzaso.length == 1) {
                    int i20 = 0;
                    if (zzjs2.zzaso[0] == 0) {
                        i19 = 0;
                        while (i19 < jArr.length) {
                            jArr[i19] = zzqe.zza(jArr[i19] - zzjs2.zzasp[i20], 1000000, zzjs2.zzcr);
                            i19++;
                            i20 = 0;
                        }
                        return new zzjv(obj, obj2, i6, jArr, obj3);
                    }
                }
                boolean z2 = zzjs2.type == 1;
                i = 0;
                zzef = 0;
                int i21 = 0;
                zzhg3 = 0;
                while (true) {
                    zza = -1;
                    if (i >= zzjs2.zzaso.length) {
                        break;
                    }
                    j8 = zzjs2.zzasp[i];
                    if (j8 != -1) {
                        obj7 = obj2;
                        j = zzqe.zza(zzjs2.zzaso[i], zzjs2.zzcr, zzjs2.zzasl);
                        int zzb = zzqe.zzb(jArr, j8, true, true);
                        obj8 = obj;
                        obj9 = obj3;
                        i8 = zzqe.zzb(jArr, j8 + j, z2, false);
                        i21 += i8 - zzb;
                        zzef |= zzhg3 != zzb ? 1 : 0;
                        zzhg3 = i8;
                    } else {
                        obj8 = obj;
                        obj9 = obj3;
                        obj7 = obj2;
                    }
                    i++;
                    obj2 = obj7;
                    obj = obj8;
                    obj3 = obj9;
                }
                obj8 = obj;
                obj9 = obj3;
                obj7 = obj2;
                i = (i21 != i5 ? 1 : 0) | zzef;
                obj = i != 0 ? new long[i21] : obj8;
                obj2 = i != 0 ? new int[i21] : obj7;
                if (i != 0) {
                    i6 = 0;
                }
                obj3 = i != 0 ? new int[i21] : obj9;
                long[] jArr7 = new long[i21];
                i3 = i6;
                i21 = 0;
                zzhg3 = 0;
                while (i21 < zzjs2.zzaso.length) {
                    int i22;
                    boolean z3;
                    Object obj11;
                    j8 = zzjs2.zzasp[i21];
                    long j10 = zzjs2.zzaso[i21];
                    if (j8 != zza) {
                        jArr6 = jArr7;
                        i22 = i21;
                        j = j8 + zzqe.zza(j10, zzjs2.zzcr, zzjs2.zzasl);
                        i21 = zzqe.zzb(jArr, j8, true, true);
                        zzhg4 = zzqe.zzb(jArr, j, z2, false);
                        if (i != 0) {
                            i42 = zzhg4 - i21;
                            obj4 = obj8;
                            System.arraycopy(obj4, i21, obj, zzhg3, i42);
                            obj5 = obj7;
                            System.arraycopy(obj5, i21, obj2, zzhg3, i42);
                            z3 = z2;
                            obj6 = obj9;
                            System.arraycopy(obj6, i21, obj3, zzhg3, i42);
                        } else {
                            z3 = z2;
                            obj5 = obj7;
                            obj4 = obj8;
                            obj6 = obj9;
                        }
                        i42 = i3;
                        while (i21 < zzhg4) {
                            obj10 = obj;
                            obj11 = obj3;
                            long j11 = j8;
                            jArr6[zzhg3] = zzqe.zza(j3, 1000000, zzjs2.zzasl) + zzqe.zza(jArr[i21] - j8, 1000000, zzjs2.zzcr);
                            if (i != 0 && obj2[zzhg3] > i42) {
                                i42 = obj5[i21];
                            }
                            zzhg3++;
                            i21++;
                            obj = obj10;
                            obj3 = obj11;
                            j8 = j11;
                        }
                        obj10 = obj;
                        obj11 = obj3;
                        i3 = i42;
                    } else {
                        z3 = z2;
                        jArr6 = jArr7;
                        i22 = i21;
                        obj10 = obj;
                        obj11 = obj3;
                        obj5 = obj7;
                        obj4 = obj8;
                        obj6 = obj9;
                    }
                    obj9 = obj6;
                    obj7 = obj5;
                    j3 += j10;
                    i21 = i22 + 1;
                    obj8 = obj4;
                    jArr7 = jArr6;
                    z2 = z3;
                    obj = obj10;
                    obj3 = obj11;
                    zza = -1;
                }
                jArr6 = jArr7;
                obj10 = obj;
                i19 = 0;
                for (i2 = 0; i2 < obj3.length && i19 == 0; i2++) {
                    i19 |= (obj3[i2] & 1) != 0 ? 1 : 0;
                }
                if (i19 != 0) {
                    return new zzjv(obj10, obj2, i3, jArr6, obj3);
                }
                throw new zzfx("The edited sample sequence does not contain a sync sample.");
            }
        }
        obj4 = obj;
        obj6 = obj3;
        obj5 = obj2;
        zzqe.zza(jArr, 1000000, zzjs2.zzcr);
        return new zzjv(obj4, obj5, i6, jArr, obj6);
    }

    public static zzki zza(zzix zzix, boolean z) {
        if (z) {
            return null;
        }
        zzpx zzpx = zzix.zzaos;
        zzpx.setPosition(8);
        while (zzpx.zzhb() >= 8) {
            int position = zzpx.getPosition();
            int readInt = zzpx.readInt();
            if (zzpx.readInt() == zziv.zzaob) {
                zzpx.setPosition(position);
                position += readInt;
                zzpx.zzbl(12);
                while (zzpx.getPosition() < position) {
                    readInt = zzpx.getPosition();
                    int readInt2 = zzpx.readInt();
                    if (zzpx.readInt() == zziv.zzaoc) {
                        zzpx.setPosition(readInt);
                        readInt += readInt2;
                        zzpx.zzbl(8);
                        List arrayList = new ArrayList();
                        while (zzpx.getPosition() < readInt) {
                            zza zzd = zzjm.zzd(zzpx);
                            if (zzd != null) {
                                arrayList.add(zzd);
                            }
                        }
                        if (!arrayList.isEmpty()) {
                            return new zzki(arrayList);
                        }
                        return null;
                    }
                    zzpx.zzbl(readInt2 - 8);
                }
                return null;
            }
            zzpx.zzbl(readInt - 8);
        }
        return null;
    }

    private static Pair<String, byte[]> zzb(zzpx zzpx, int i) {
        zzpx.setPosition((i + 8) + 4);
        zzpx.zzbl(1);
        zzc(zzpx);
        zzpx.zzbl(2);
        int readUnsignedByte = zzpx.readUnsignedByte();
        if ((readUnsignedByte & 128) != 0) {
            zzpx.zzbl(2);
        }
        if ((readUnsignedByte & 64) != 0) {
            zzpx.zzbl(zzpx.readUnsignedShort());
        }
        if ((readUnsignedByte & 32) != 0) {
            zzpx.zzbl(2);
        }
        zzpx.zzbl(1);
        zzc(zzpx);
        Object obj = null;
        switch (zzpx.readUnsignedByte()) {
            case 32:
                obj = MimeTypes.VIDEO_MP4V;
                break;
            case 33:
                obj = MimeTypes.VIDEO_H264;
                break;
            case 35:
                obj = MimeTypes.VIDEO_H265;
                break;
            case 64:
            case 102:
            case 103:
            case 104:
                obj = MimeTypes.AUDIO_AAC;
                break;
            case 107:
                return Pair.create(MimeTypes.AUDIO_MPEG, null);
            case 165:
                obj = MimeTypes.AUDIO_AC3;
                break;
            case 166:
                obj = MimeTypes.AUDIO_E_AC3;
                break;
            case 169:
            case 172:
                return Pair.create(MimeTypes.AUDIO_DTS, null);
            case 170:
            case 171:
                return Pair.create(MimeTypes.AUDIO_DTS_HD, null);
        }
        zzpx.zzbl(12);
        zzpx.zzbl(1);
        i = zzc(zzpx);
        byte[] bArr = new byte[i];
        zzpx.zze(bArr, 0, i);
        return Pair.create(obj, bArr);
    }

    private static int zza(zzpx zzpx, int i, int i2, zzjb zzjb, int i3) {
        zzpx zzpx2 = zzpx;
        int position = zzpx.getPosition();
        while (true) {
            boolean z = false;
            if (position - i >= i2) {
                return 0;
            }
            zzpx2.setPosition(position);
            int readInt = zzpx.readInt();
            zzpo.checkArgument(readInt > 0, "childAtomSize should be positive");
            if (zzpx.readInt() == zziv.zzamv) {
                int i4 = position + 8;
                Pair pair = null;
                boolean z2 = false;
                Object obj = null;
                Object obj2 = obj;
                while (i4 - position < readInt) {
                    zzpx2.setPosition(i4);
                    int readInt2 = zzpx.readInt();
                    int readInt3 = zzpx.readInt();
                    if (readInt3 == zziv.zzanb) {
                        obj = Integer.valueOf(zzpx.readInt());
                    } else if (readInt3 == zziv.zzamw) {
                        zzpx2.zzbl(4);
                        z2 = zzpx.readInt() == zzaoz;
                    } else if (readInt3 == zziv.zzamx) {
                        zzjt zzjt;
                        int i5 = i4 + 8;
                        while (i5 - i4 < readInt2) {
                            zzpx2.setPosition(i5);
                            readInt3 = zzpx.readInt();
                            if (zzpx.readInt() == zziv.zzamy) {
                                zzpx2.zzbl(6);
                                boolean z3 = zzpx.readUnsignedByte() == 1;
                                readInt3 = zzpx.readUnsignedByte();
                                byte[] bArr = new byte[16];
                                zzpx2.zze(bArr, 0, 16);
                                zzjt = new zzjt(z3, readInt3, bArr);
                                obj2 = zzjt;
                            } else {
                                i5 += readInt3;
                            }
                        }
                        zzjt = null;
                        obj2 = zzjt;
                    }
                    i4 += readInt2;
                }
                if (z2) {
                    zzpo.checkArgument(obj != null, "frma atom is mandatory");
                    if (obj2 != null) {
                        z = true;
                    }
                    zzpo.checkArgument(z, "schi->tenc atom is mandatory");
                    pair = Pair.create(obj, obj2);
                }
                if (pair != null) {
                    zzjb.zzaph[i3] = (zzjt) pair.second;
                    return ((Integer) pair.first).intValue();
                }
            }
            zzjb zzjb2 = zzjb;
            position += readInt;
        }
    }

    private static int zzc(zzpx zzpx) {
        int readUnsignedByte = zzpx.readUnsignedByte();
        int i = readUnsignedByte & 127;
        while ((readUnsignedByte & 128) == 128) {
            readUnsignedByte = zzpx.readUnsignedByte();
            i = (i << 7) | (readUnsignedByte & 127);
        }
        return i;
    }
}
