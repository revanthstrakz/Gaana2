package com.google.android.gms.internal.ads;

import java.io.IOException;

final class zzjr {
    private static final int[] zzask = new int[]{zzqe.zzam("isom"), zzqe.zzam("iso2"), zzqe.zzam("iso3"), zzqe.zzam("iso4"), zzqe.zzam("iso5"), zzqe.zzam("iso6"), zzqe.zzam("avc1"), zzqe.zzam("hvc1"), zzqe.zzam("hev1"), zzqe.zzam("mp41"), zzqe.zzam("mp42"), zzqe.zzam("3g2a"), zzqe.zzam("3g2b"), zzqe.zzam("3gr6"), zzqe.zzam("3gs6"), zzqe.zzam("3ge6"), zzqe.zzam("3gg6"), zzqe.zzam("M4V "), zzqe.zzam("M4A "), zzqe.zzam("f4v "), zzqe.zzam("kddi"), zzqe.zzam("M4VP"), zzqe.zzam("qt  "), zzqe.zzam("MSNV")};

    public static boolean zzd(zzia zzia) throws IOException, InterruptedException {
        return zza(zzia, true);
    }

    public static boolean zze(zzia zzia) throws IOException, InterruptedException {
        return zza(zzia, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:71:0x00ab A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00a9 A:{SYNTHETIC} */
    private static boolean zza(com.google.android.gms.internal.ads.zzia r19, boolean r20) throws java.io.IOException, java.lang.InterruptedException {
        /*
        r0 = r19;
        r1 = r19.getLength();
        r3 = -1;
        r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        r3 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        if (r5 == 0) goto L_0x0012;
    L_0x000e:
        r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        if (r5 <= 0) goto L_0x0013;
    L_0x0012:
        r1 = r3;
    L_0x0013:
        r1 = (int) r1;
        r2 = new com.google.android.gms.internal.ads.zzpx;
        r3 = 64;
        r2.<init>(r3);
        r3 = 0;
        r4 = r3;
        r5 = r4;
    L_0x001e:
        if (r4 >= r1) goto L_0x00ba;
    L_0x0020:
        r7 = 8;
        r2.reset(r7);
        r8 = r2.data;
        r0.zza(r8, r3, r7);
        r8 = r2.zzhd();
        r10 = r2.readInt();
        r11 = 1;
        r13 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1));
        r11 = 16;
        if (r13 != 0) goto L_0x0047;
    L_0x003a:
        r8 = r2.data;
        r0.zza(r8, r7, r7);
        r2.zzbk(r11);
        r8 = r2.zzhh();
        goto L_0x0048;
    L_0x0047:
        r11 = r7;
    L_0x0048:
        r12 = (long) r11;
        r14 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1));
        if (r14 >= 0) goto L_0x004e;
    L_0x004d:
        return r3;
    L_0x004e:
        r4 = r4 + r11;
        r11 = com.google.android.gms.internal.ads.zziv.zzamb;
        if (r10 == r11) goto L_0x001e;
    L_0x0053:
        r11 = com.google.android.gms.internal.ads.zziv.zzamk;
        if (r10 == r11) goto L_0x00b8;
    L_0x0057:
        r11 = com.google.android.gms.internal.ads.zziv.zzamm;
        if (r10 != r11) goto L_0x005c;
    L_0x005b:
        goto L_0x00b8;
    L_0x005c:
        r14 = (long) r4;
        r16 = r14 + r8;
        r14 = r16 - r12;
        r6 = (long) r1;
        r11 = (r14 > r6 ? 1 : (r14 == r6 ? 0 : -1));
        if (r11 >= 0) goto L_0x00ba;
    L_0x0066:
        r6 = r8 - r12;
        r6 = (int) r6;
        r4 = r4 + r6;
        r7 = com.google.android.gms.internal.ads.zziv.zzala;
        if (r10 != r7) goto L_0x00b1;
    L_0x006e:
        r7 = 8;
        if (r6 >= r7) goto L_0x0073;
    L_0x0072:
        return r3;
    L_0x0073:
        r2.reset(r6);
        r7 = r2.data;
        r0.zza(r7, r3, r6);
        r6 = r6 / 4;
        r7 = r3;
    L_0x007e:
        if (r7 >= r6) goto L_0x00ae;
    L_0x0080:
        r8 = 1;
        if (r7 != r8) goto L_0x0088;
    L_0x0083:
        r8 = 4;
        r2.zzbl(r8);
        goto L_0x00ab;
    L_0x0088:
        r8 = r2.readInt();
        r9 = r8 >>> 8;
        r10 = "3gp";
        r10 = com.google.android.gms.internal.ads.zzqe.zzam(r10);
        if (r9 != r10) goto L_0x0098;
    L_0x0096:
        r8 = 1;
        goto L_0x00a7;
    L_0x0098:
        r9 = zzask;
        r10 = r9.length;
        r11 = r3;
    L_0x009c:
        if (r11 >= r10) goto L_0x00a6;
    L_0x009e:
        r12 = r9[r11];
        if (r12 != r8) goto L_0x00a3;
    L_0x00a2:
        goto L_0x0096;
    L_0x00a3:
        r11 = r11 + 1;
        goto L_0x009c;
    L_0x00a6:
        r8 = r3;
    L_0x00a7:
        if (r8 == 0) goto L_0x00ab;
    L_0x00a9:
        r5 = 1;
        goto L_0x00ae;
    L_0x00ab:
        r7 = r7 + 1;
        goto L_0x007e;
    L_0x00ae:
        if (r5 != 0) goto L_0x001e;
    L_0x00b0:
        return r3;
    L_0x00b1:
        if (r6 == 0) goto L_0x001e;
    L_0x00b3:
        r0.zzx(r6);
        goto L_0x001e;
    L_0x00b8:
        r0 = 1;
        goto L_0x00bb;
    L_0x00ba:
        r0 = r3;
    L_0x00bb:
        if (r5 == 0) goto L_0x00c3;
    L_0x00bd:
        r1 = r20;
        if (r1 != r0) goto L_0x00c3;
    L_0x00c1:
        r0 = 1;
        return r0;
    L_0x00c3:
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzjr.zza(com.google.android.gms.internal.ads.zzia, boolean):boolean");
    }
}
