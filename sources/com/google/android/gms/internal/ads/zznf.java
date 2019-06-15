package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class zznf implements zzmq {
    private final zzov zzagy;
    private final zzpk zzbaz;
    private zznj zzbbd;
    private final int[] zzbbf;
    private final long zzbbx;
    private final zzom zzbcf;
    private final zznh[] zzbcg;
    private final int zzbch;
    private IOException zzbci;
    private boolean zzbcj;
    private final int zzwg;
    private int zzyr;

    public zznf(zzpk zzpk, zznj zznj, int i, int[] iArr, zzom zzom, int i2, zzov zzov, long j, int i3, boolean z, boolean z2) {
        zzom zzom2 = zzom;
        this.zzbaz = zzpk;
        this.zzbbd = zznj;
        this.zzbbf = iArr;
        this.zzbcf = zzom2;
        this.zzwg = i2;
        this.zzagy = zzov;
        this.zzyr = i;
        this.zzbbx = j;
        this.zzbch = i3;
        long zzbb = zznj.zzbb(i);
        ArrayList zzgf = zzgf();
        this.zzbcg = new zznh[zzom.length()];
        for (int i4 = 0; i4 < this.zzbcg.length; i4++) {
            this.zzbcg[i4] = new zznh(zzbb, (zznp) zzgf.get(zzom2.zzbd(i4)), z, z2);
        }
    }

    public final void zza(zznj zznj, int i) {
        try {
            this.zzbbd = zznj;
            this.zzyr = i;
            long zzbb = this.zzbbd.zzbb(this.zzyr);
            ArrayList zzgf = zzgf();
            for (int i2 = 0; i2 < this.zzbcg.length; i2++) {
                this.zzbcg[i2].zza(zzbb, (zznp) zzgf.get(this.zzbcf.zzbd(i2)));
            }
        } catch (zzkz e) {
            this.zzbci = e;
        }
    }

    public final void zzev() throws IOException {
        if (this.zzbci != null) {
            throw this.zzbci;
        }
        this.zzbaz.zzev();
    }

    /* JADX WARNING: Missing block: B:71:0x01f2, code skipped:
            if (r0.zzyr < (r0.zzbbd.zzcl() - 1)) goto L_0x01f6;
     */
    public final void zza(com.google.android.gms.internal.ads.zzmo r29, long r30, com.google.android.gms.internal.ads.zzmi r32) {
        /*
        r28 = this;
        r0 = r28;
        r1 = r29;
        r2 = r30;
        r4 = r32;
        r5 = r0.zzbci;
        if (r5 == 0) goto L_0x000d;
    L_0x000c:
        return;
    L_0x000d:
        r5 = 0;
        if (r1 == 0) goto L_0x0016;
    L_0x0011:
        r7 = r1.zzazt;
        r9 = r7 - r2;
        goto L_0x0017;
    L_0x0016:
        r9 = r5;
    L_0x0017:
        r7 = r0.zzbcf;
        r7.zzak(r9);
        r7 = r0.zzbcg;
        r8 = r0.zzbcf;
        r8 = r8.zzgm();
        r7 = r7[r8];
        r8 = r7.zzbat;
        if (r8 == 0) goto L_0x0092;
    L_0x002a:
        r8 = r7.zzbck;
        r9 = r7.zzbat;
        r9 = r9.zzfx();
        r10 = 0;
        if (r9 != 0) goto L_0x003a;
    L_0x0035:
        r9 = r8.zzgh();
        goto L_0x003b;
    L_0x003a:
        r9 = r10;
    L_0x003b:
        r11 = r7.zzbcl;
        if (r11 != 0) goto L_0x0043;
    L_0x003f:
        r10 = r8.zzgi();
    L_0x0043:
        if (r9 != 0) goto L_0x0047;
    L_0x0045:
        if (r10 == 0) goto L_0x0092;
    L_0x0047:
        r12 = r0.zzagy;
        r1 = r0.zzbcf;
        r14 = r1.zzgl();
        r1 = r0.zzbcf;
        r15 = r1.zzgn();
        r1 = r0.zzbcf;
        r16 = r1.zzgo();
        r1 = r7.zzbck;
        r1 = r1.zzbde;
        if (r9 == 0) goto L_0x006a;
    L_0x0061:
        r2 = r9.zza(r10, r1);
        if (r2 != 0) goto L_0x0068;
    L_0x0067:
        goto L_0x006b;
    L_0x0068:
        r9 = r2;
        goto L_0x006b;
    L_0x006a:
        r9 = r10;
    L_0x006b:
        r2 = new com.google.android.gms.internal.ads.zzoz;
        r18 = r9.zzy(r1);
        r5 = r9.zzbdk;
        r8 = r9.zzcc;
        r1 = r7.zzbck;
        r23 = r1.zzf();
        r17 = r2;
        r19 = r5;
        r21 = r8;
        r17.<init>(r18, r19, r21, r23);
        r1 = new com.google.android.gms.internal.ads.zzmn;
        r3 = r7.zzbat;
        r11 = r1;
        r13 = r2;
        r17 = r3;
        r11.<init>(r12, r13, r14, r15, r16, r17);
        r4.zzbaa = r1;
        return;
    L_0x0092:
        r8 = r0.zzbbx;
        r10 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1));
        r5 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        if (r10 == 0) goto L_0x00a4;
    L_0x009a:
        r8 = android.os.SystemClock.elapsedRealtime();
        r10 = r0.zzbbx;
        r12 = r8 + r10;
        r12 = r12 * r5;
        goto L_0x00aa;
    L_0x00a4:
        r8 = java.lang.System.currentTimeMillis();
        r12 = r8 * r5;
    L_0x00aa:
        r8 = r7.zzgg();
        r9 = 0;
        r10 = 1;
        if (r8 != 0) goto L_0x00c7;
    L_0x00b2:
        r1 = r0.zzbbd;
        r1 = r1.zzbcs;
        if (r1 == 0) goto L_0x00c3;
    L_0x00b8:
        r1 = r0.zzyr;
        r2 = r0.zzbbd;
        r2 = r2.zzcl();
        r2 = r2 - r10;
        if (r1 >= r2) goto L_0x00c4;
    L_0x00c3:
        r9 = r10;
    L_0x00c4:
        r4.zzbab = r9;
        return;
    L_0x00c7:
        r11 = r7.zzgd();
        r14 = -1;
        if (r8 != r14) goto L_0x0105;
    L_0x00ce:
        r8 = r0.zzbbd;
        r14 = r8.zzbcq;
        r14 = r14 * r5;
        r16 = r12 - r14;
        r8 = r0.zzbbd;
        r12 = r0.zzyr;
        r8 = r8.zzba(r12);
        r12 = r8.zzbdj;
        r12 = r12 * r5;
        r14 = r16 - r12;
        r8 = r0.zzbbd;
        r12 = r8.zzbcu;
        r16 = -9223372036854775807; // 0x8000000000000001 float:1.4E-45 double:-4.9E-324;
        r8 = (r12 > r16 ? 1 : (r12 == r16 ? 0 : -1));
        if (r8 == 0) goto L_0x00ff;
    L_0x00ef:
        r8 = r0.zzbbd;
        r12 = r8.zzbcu;
        r12 = r12 * r5;
        r5 = r14 - r12;
        r5 = r7.zzaj(r5);
        r5 = java.lang.Math.max(r11, r5);
        r11 = r5;
    L_0x00ff:
        r5 = r7.zzaj(r14);
        r5 = r5 - r10;
        goto L_0x0108;
    L_0x0105:
        r8 = r8 + r11;
        r5 = r8 + -1;
    L_0x0108:
        if (r1 != 0) goto L_0x0114;
    L_0x010a:
        r1 = r7.zzaj(r2);
        r1 = com.google.android.gms.internal.ads.zzqe.zzd(r1, r11, r5);
    L_0x0112:
        r15 = r1;
        goto L_0x0122;
    L_0x0114:
        r1 = r29.zzfz();
        if (r1 >= r11) goto L_0x0112;
    L_0x011a:
        r1 = new com.google.android.gms.internal.ads.zzkz;
        r1.<init>();
        r0.zzbci = r1;
        return;
    L_0x0122:
        if (r15 > r5) goto L_0x01e2;
    L_0x0124:
        r1 = r0.zzbcj;
        if (r1 == 0) goto L_0x012c;
    L_0x0128:
        if (r15 < r5) goto L_0x012c;
    L_0x012a:
        goto L_0x01e2;
    L_0x012c:
        r1 = r0.zzbch;
        r5 = r5 - r15;
        r5 = r5 + r10;
        r1 = java.lang.Math.min(r1, r5);
        r6 = r0.zzagy;
        r2 = r0.zzwg;
        r3 = r0.zzbcf;
        r3 = r3.zzgl();
        r5 = r0.zzbcf;
        r9 = r5.zzgn();
        r5 = r0.zzbcf;
        r11 = r5.zzgo();
        r5 = r7.zzbck;
        r12 = r7.zzay(r15);
        r8 = r7.zzax(r15);
        r14 = r5.zzbde;
        r10 = r7.zzbat;
        if (r10 != 0) goto L_0x018d;
    L_0x015a:
        r16 = r7.zzaz(r15);
        r1 = new com.google.android.gms.internal.ads.zzoz;
        r19 = r8.zzy(r14);
        r25 = r15;
        r14 = r8.zzbdk;
        r7 = r8.zzcc;
        r24 = r5.zzf();
        r18 = r1;
        r20 = r14;
        r22 = r7;
        r18.<init>(r19, r20, r22, r24);
        r18 = new com.google.android.gms.internal.ads.zzmp;
        r5 = r18;
        r7 = r1;
        r8 = r3;
        r10 = r11;
        r11 = r12;
        r13 = r16;
        r15 = r25;
        r16 = r2;
        r17 = r3;
        r5.<init>(r6, r7, r8, r9, r10, r11, r13, r15, r16, r17);
        r2 = r18;
        goto L_0x01df;
    L_0x018d:
        r25 = r15;
        r2 = 1;
        r16 = 1;
    L_0x0192:
        if (r2 >= r1) goto L_0x01a6;
    L_0x0194:
        r15 = r25 + r2;
        r10 = r7.zzax(r15);
        r10 = r8.zza(r10, r14);
        if (r10 == 0) goto L_0x01a6;
    L_0x01a0:
        r16 = r16 + 1;
        r2 = r2 + 1;
        r8 = r10;
        goto L_0x0192;
    L_0x01a6:
        r15 = r25 + r16;
        r1 = 1;
        r15 = r15 - r1;
        r1 = r7.zzaz(r15);
        r10 = new com.google.android.gms.internal.ads.zzoz;
        r18 = r8.zzy(r14);
        r14 = r8.zzbdk;
        r26 = r1;
        r0 = r8.zzcc;
        r23 = r5.zzf();
        r17 = r10;
        r19 = r14;
        r21 = r0;
        r17.<init>(r18, r19, r21, r23);
        r0 = r5.zzbdn;
        r0 = -r0;
        r2 = new com.google.android.gms.internal.ads.zzmm;
        r15 = r7.zzbat;
        r5 = r2;
        r7 = r10;
        r8 = r3;
        r10 = r11;
        r11 = r12;
        r13 = r26;
        r3 = r15;
        r15 = r25;
        r17 = r0;
        r19 = r3;
        r5.<init>(r6, r7, r8, r9, r10, r11, r13, r15, r16, r17, r19);
    L_0x01df:
        r4.zzbaa = r2;
        return;
    L_0x01e2:
        r1 = r0.zzbbd;
        r1 = r1.zzbcs;
        if (r1 == 0) goto L_0x01f5;
    L_0x01e8:
        r1 = r0.zzyr;
        r2 = r0.zzbbd;
        r2 = r2.zzcl();
        r3 = 1;
        r2 = r2 - r3;
        if (r1 >= r2) goto L_0x01f7;
    L_0x01f4:
        goto L_0x01f6;
    L_0x01f5:
        r3 = 1;
    L_0x01f6:
        r9 = r3;
    L_0x01f7:
        r4.zzbab = r9;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zznf.zza(com.google.android.gms.internal.ads.zzmo, long, com.google.android.gms.internal.ads.zzmi):void");
    }

    public final void zza(zzme zzme) {
        if (zzme instanceof zzmn) {
            zznh zznh = this.zzbcg[this.zzbcf.zzi(((zzmn) zzme).zzazp)];
            if (zznh.zzbcl == null) {
                zzig zzfw = zznh.zzbat.zzfw();
                if (zzfw != null) {
                    zznh.zzbcl = new zzne((zzhw) zzfw);
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00d1 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0060  */
    /* JADX WARNING: Missing block: B:23:0x005a, code skipped:
            if (r3 != com.google.android.gms.wallet.WalletConstants.ERROR_CODE_INVALID_TRANSACTION) goto L_0x005d;
     */
    public final boolean zza(com.google.android.gms.internal.ads.zzme r6, boolean r7, java.lang.Exception r8) {
        /*
        r5 = this;
        r0 = 0;
        if (r7 != 0) goto L_0x0004;
    L_0x0003:
        return r0;
    L_0x0004:
        r7 = r5.zzbbd;
        r7 = r7.zzbcs;
        r1 = 404; // 0x194 float:5.66E-43 double:1.996E-321;
        r2 = 1;
        if (r7 != 0) goto L_0x0043;
    L_0x000d:
        r7 = r6 instanceof com.google.android.gms.internal.ads.zzmo;
        if (r7 == 0) goto L_0x0043;
    L_0x0011:
        r7 = r8 instanceof com.google.android.gms.internal.ads.zzpd;
        if (r7 == 0) goto L_0x0043;
    L_0x0015:
        r7 = r8;
        r7 = (com.google.android.gms.internal.ads.zzpd) r7;
        r7 = r7.responseCode;
        if (r7 != r1) goto L_0x0043;
    L_0x001c:
        r7 = r5.zzbcg;
        r3 = r5.zzbcf;
        r4 = r6.zzazp;
        r3 = r3.zzi(r4);
        r7 = r7[r3];
        r3 = r7.zzgg();
        r4 = -1;
        if (r3 == r4) goto L_0x0043;
    L_0x002f:
        if (r3 == 0) goto L_0x0043;
    L_0x0031:
        r7 = r7.zzgd();
        r7 = r7 + r3;
        r7 = r7 - r2;
        r3 = r6;
        r3 = (com.google.android.gms.internal.ads.zzmo) r3;
        r3 = r3.zzfz();
        if (r3 <= r7) goto L_0x0043;
    L_0x0040:
        r5.zzbcj = r2;
        return r2;
    L_0x0043:
        r7 = r5.zzbcf;
        r3 = r5.zzbcf;
        r6 = r6.zzazp;
        r6 = r3.zzi(r6);
        r3 = r8 instanceof com.google.android.gms.internal.ads.zzpd;
        if (r3 == 0) goto L_0x005d;
    L_0x0051:
        r3 = r8;
        r3 = (com.google.android.gms.internal.ads.zzpd) r3;
        r3 = r3.responseCode;
        if (r3 == r1) goto L_0x005e;
    L_0x0058:
        r1 = 410; // 0x19a float:5.75E-43 double:2.026E-321;
        if (r3 != r1) goto L_0x005d;
    L_0x005c:
        goto L_0x005e;
    L_0x005d:
        r2 = r0;
    L_0x005e:
        if (r2 == 0) goto L_0x00d1;
    L_0x0060:
        r0 = 60000; // 0xea60 float:8.4078E-41 double:2.9644E-319;
        r0 = r7.zzf(r6, r0);
        r8 = (com.google.android.gms.internal.ads.zzpd) r8;
        r8 = r8.responseCode;
        if (r0 == 0) goto L_0x009f;
    L_0x006d:
        r1 = "ChunkedTrackBlacklist";
        r6 = r7.zzat(r6);
        r6 = java.lang.String.valueOf(r6);
        r7 = 77;
        r2 = java.lang.String.valueOf(r6);
        r2 = r2.length();
        r7 = r7 + r2;
        r2 = new java.lang.StringBuilder;
        r2.<init>(r7);
        r7 = "Blacklisted: duration=60000, responseCode=";
        r2.append(r7);
        r2.append(r8);
        r7 = ", format=";
        r2.append(r7);
        r2.append(r6);
        r6 = r2.toString();
        android.util.Log.w(r1, r6);
        goto L_0x00d0;
    L_0x009f:
        r1 = "ChunkedTrackBlacklist";
        r6 = r7.zzat(r6);
        r6 = java.lang.String.valueOf(r6);
        r7 = 92;
        r2 = java.lang.String.valueOf(r6);
        r2 = r2.length();
        r7 = r7 + r2;
        r2 = new java.lang.StringBuilder;
        r2.<init>(r7);
        r7 = "Blacklisting failed (cannot blacklist last enabled track): responseCode=";
        r2.append(r7);
        r2.append(r8);
        r7 = ", format=";
        r2.append(r7);
        r2.append(r6);
        r6 = r2.toString();
        android.util.Log.w(r1, r6);
    L_0x00d0:
        return r0;
    L_0x00d1:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zznf.zza(com.google.android.gms.internal.ads.zzme, boolean, java.lang.Exception):boolean");
    }

    private final ArrayList<zznp> zzgf() {
        List list = this.zzbbd.zzba(this.zzyr).zzbbe;
        ArrayList arrayList = new ArrayList();
        for (int i : this.zzbbf) {
            arrayList.addAll(((zzni) list.get(i)).zzbcn);
        }
        return arrayList;
    }
}
