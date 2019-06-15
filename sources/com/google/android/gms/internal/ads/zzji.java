package com.google.android.gms.internal.ads;

import android.util.Log;
import android.util.SparseArray;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.internal.ads.zzhp.zza;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.UUID;

public final class zzji implements zzhz {
    private static final zzic zzahq = new zzjj();
    private static final int zzaps = zzqe.zzam("seig");
    private static final byte[] zzapt = new byte[]{(byte) -94, (byte) 57, (byte) 79, (byte) 82, (byte) 90, (byte) -101, (byte) 79, (byte) 20, (byte) -94, (byte) 68, (byte) 108, (byte) 66, (byte) 124, (byte) 100, (byte) -115, (byte) -12};
    private final int flags;
    private long zzaan;
    private final zzpx zzahx;
    private int zzajm;
    private int zzajn;
    private zzib zzajq;
    private final zzjs zzapu;
    private final SparseArray<zzjl> zzapv;
    private final zzpx zzapw;
    private final zzpx zzapx;
    private final zzpx zzapy;
    private final zzqb zzapz;
    private final zzpx zzaqa;
    private final byte[] zzaqb;
    private final Stack<zziw> zzaqc;
    private final LinkedList<zzjk> zzaqd;
    private int zzaqe;
    private int zzaqf;
    private long zzaqg;
    private int zzaqh;
    private zzpx zzaqi;
    private long zzaqj;
    private int zzaqk;
    private long zzaql;
    private zzjl zzaqm;
    private int zzaqn;
    private boolean zzaqo;
    private zzii zzaqp;
    private zzii[] zzaqq;
    private boolean zzaqr;

    public zzji() {
        this(0);
    }

    public final void release() {
    }

    public zzji(int i) {
        this(i, null);
    }

    private zzji(int i, zzqb zzqb) {
        this(i, null, null);
    }

    private zzji(int i, zzqb zzqb, zzjs zzjs) {
        this.flags = i;
        this.zzapz = null;
        this.zzapu = null;
        this.zzaqa = new zzpx(16);
        this.zzahx = new zzpx(zzpu.zzbhi);
        this.zzapw = new zzpx(5);
        this.zzapx = new zzpx();
        this.zzapy = new zzpx(1);
        this.zzaqb = new byte[16];
        this.zzaqc = new Stack();
        this.zzaqd = new LinkedList();
        this.zzapv = new SparseArray();
        this.zzaan = C.TIME_UNSET;
        this.zzaql = C.TIME_UNSET;
        zzei();
    }

    public final boolean zza(zzia zzia) throws IOException, InterruptedException {
        return zzjr.zzd(zzia);
    }

    public final void zza(zzib zzib) {
        this.zzajq = zzib;
    }

    public final void zzc(long j, long j2) {
        int size = this.zzapv.size();
        for (int i = 0; i < size; i++) {
            ((zzjl) this.zzapv.valueAt(i)).reset();
        }
        this.zzaqd.clear();
        this.zzaqk = 0;
        this.zzaqc.clear();
        zzei();
    }

    /* JADX WARNING: Removed duplicated region for block: B:256:0x062e A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:264:0x0397 A:{SYNTHETIC} */
    public final int zza(com.google.android.gms.internal.ads.zzia r28, com.google.android.gms.internal.ads.zzif r29) throws java.io.IOException, java.lang.InterruptedException {
        /*
        r27 = this;
        r0 = r27;
        r1 = r28;
    L_0x0004:
        r2 = r0.zzaqe;
        r3 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
        r5 = 3;
        r6 = 2;
        r7 = 4;
        r8 = 0;
        r9 = 8;
        r11 = 0;
        switch(r2) {
            case 0: goto L_0x01f3;
            case 1: goto L_0x0082;
            case 2: goto L_0x002a;
            default: goto L_0x0015;
        };
    L_0x0015:
        r10 = r0;
        r0 = r1;
        r1 = r10.zzaqe;
        if (r1 != r5) goto L_0x0485;
    L_0x001b:
        r1 = r10.zzaqm;
        if (r1 != 0) goto L_0x03f8;
    L_0x001f:
        r1 = r10.zzapv;
        r2 = r1.size();
        r11 = r3;
        r4 = r8;
        r3 = 0;
        goto L_0x0399;
    L_0x002a:
        r2 = r0.zzapv;
        r2 = r2.size();
        r6 = r3;
        r3 = r11;
    L_0x0032:
        if (r3 >= r2) goto L_0x0056;
    L_0x0034:
        r4 = r0.zzapv;
        r4 = r4.valueAt(r3);
        r4 = (com.google.android.gms.internal.ads.zzjl) r4;
        r4 = r4.zzaqt;
        r9 = r4.zzatj;
        if (r9 == 0) goto L_0x0053;
    L_0x0042:
        r9 = r4.zzasw;
        r12 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1));
        if (r12 >= 0) goto L_0x0053;
    L_0x0048:
        r6 = r4.zzasw;
        r4 = r0.zzapv;
        r4 = r4.valueAt(r3);
        r4 = (com.google.android.gms.internal.ads.zzjl) r4;
        r8 = r4;
    L_0x0053:
        r3 = r3 + 1;
        goto L_0x0032;
    L_0x0056:
        if (r8 != 0) goto L_0x005b;
    L_0x0058:
        r0.zzaqe = r5;
        goto L_0x0004;
    L_0x005b:
        r2 = r28.getPosition();
        r4 = r6 - r2;
        r2 = (int) r4;
        if (r2 >= 0) goto L_0x006c;
    L_0x0064:
        r1 = new com.google.android.gms.internal.ads.zzfx;
        r2 = "Offset to encryption data was negative.";
        r1.<init>(r2);
        throw r1;
    L_0x006c:
        r1.zzw(r2);
        r2 = r8.zzaqt;
        r3 = r2.zzati;
        r3 = r3.data;
        r4 = r2.zzath;
        r1.readFully(r3, r11, r4);
        r3 = r2.zzati;
        r3.setPosition(r11);
        r2.zzatj = r11;
        goto L_0x0004;
    L_0x0082:
        r2 = r0.zzaqg;
        r2 = (int) r2;
        r3 = r0.zzaqh;
        r2 = r2 - r3;
        r3 = r0.zzaqi;
        if (r3 == 0) goto L_0x01e3;
    L_0x008c:
        r3 = r0.zzaqi;
        r3 = r3.data;
        r1.readFully(r3, r9, r2);
        r2 = new com.google.android.gms.internal.ads.zzix;
        r3 = r0.zzaqf;
        r4 = r0.zzaqi;
        r2.<init>(r3, r4);
        r3 = r28.getPosition();
        r5 = r0.zzaqc;
        r5 = r5.isEmpty();
        if (r5 != 0) goto L_0x00b7;
    L_0x00a8:
        r3 = r0.zzaqc;
        r3 = r3.peek();
        r3 = (com.google.android.gms.internal.ads.zziw) r3;
        r3.zza(r2);
        r10 = r0;
        r0 = r1;
        goto L_0x01e8;
    L_0x00b7:
        r5 = r2.type;
        r8 = com.google.android.gms.internal.ads.zziv.zzama;
        if (r5 != r8) goto L_0x0184;
    L_0x00bd:
        r2 = r2.zzaos;
        r2.setPosition(r9);
        r5 = r2.readInt();
        r5 = com.google.android.gms.internal.ads.zziv.zzaf(r5);
        r2.zzbl(r7);
        r8 = r2.zzhd();
        if (r5 != 0) goto L_0x00e1;
    L_0x00d3:
        r12 = r2.zzhd();
        r14 = r2.zzhd();
        r16 = r3 + r14;
    L_0x00dd:
        r3 = r12;
        r18 = r16;
        goto L_0x00ec;
    L_0x00e1:
        r12 = r2.zzhh();
        r14 = r2.zzhh();
        r16 = r3 + r14;
        goto L_0x00dd;
    L_0x00ec:
        r14 = 1000000; // 0xf4240 float:1.401298E-39 double:4.940656E-318;
        r12 = r3;
        r16 = r8;
        r14 = com.google.android.gms.internal.ads.zzqe.zza(r12, r14, r16);
        r2.zzbl(r6);
        r5 = r2.readUnsignedShort();
        r6 = new int[r5];
        r12 = new long[r5];
        r13 = new long[r5];
        r11 = new long[r5];
        r20 = r3;
        r16 = r14;
        r3 = 0;
    L_0x010a:
        if (r3 >= r5) goto L_0x015b;
    L_0x010c:
        r4 = r2.readInt();
        r22 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r22 = r22 & r4;
        if (r22 == 0) goto L_0x011e;
    L_0x0116:
        r1 = new com.google.android.gms.internal.ads.zzfx;
        r2 = "Unhandled indirect reference";
        r1.<init>(r2);
        throw r1;
    L_0x011e:
        r22 = r2.zzhd();
        r24 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r4 = r24 & r4;
        r6[r3] = r4;
        r12[r3] = r18;
        r11[r3] = r16;
        r24 = r20 + r22;
        r16 = 1000000; // 0xf4240 float:1.401298E-39 double:4.940656E-318;
        r4 = r12;
        r10 = r13;
        r12 = r24;
        r0 = r14;
        r14 = r16;
        r16 = r8;
        r16 = com.google.android.gms.internal.ads.zzqe.zza(r12, r14, r16);
        r12 = r11[r3];
        r14 = r16 - r12;
        r10[r3] = r14;
        r2.zzbl(r7);
        r12 = r6[r3];
        r12 = (long) r12;
        r14 = r18 + r12;
        r3 = r3 + 1;
        r12 = r4;
        r13 = r10;
        r18 = r14;
        r20 = r24;
        r14 = r0;
        r0 = r27;
        r1 = r28;
        goto L_0x010a;
    L_0x015b:
        r4 = r12;
        r10 = r13;
        r0 = r14;
        r0 = java.lang.Long.valueOf(r0);
        r1 = new com.google.android.gms.internal.ads.zzhw;
        r1.<init>(r6, r4, r10, r11);
        r0 = android.util.Pair.create(r0, r1);
        r1 = r0.first;
        r1 = (java.lang.Long) r1;
        r1 = r1.longValue();
        r10 = r27;
        r10.zzaql = r1;
        r1 = r10.zzajq;
        r0 = r0.second;
        r0 = (com.google.android.gms.internal.ads.zzig) r0;
        r1.zza(r0);
        r0 = 1;
        r10.zzaqr = r0;
        goto L_0x01e0;
    L_0x0184:
        r10 = r0;
        r0 = r2.type;
        r1 = com.google.android.gms.internal.ads.zziv.zzaog;
        if (r0 != r1) goto L_0x01e0;
    L_0x018b:
        r0 = r2.zzaos;
        r1 = r10.zzaqp;
        if (r1 == 0) goto L_0x01e0;
    L_0x0191:
        r1 = 12;
        r0.setPosition(r1);
        r0.zzhi();
        r0.zzhi();
        r6 = r0.zzhd();
        r2 = r0.zzhd();
        r4 = 1000000; // 0xf4240 float:1.401298E-39 double:4.940656E-318;
        r2 = com.google.android.gms.internal.ads.zzqe.zza(r2, r4, r6);
        r0.setPosition(r1);
        r15 = r0.zzhb();
        r1 = r10.zzaqp;
        r1.zza(r0, r15);
        r0 = r10.zzaql;
        r4 = -9223372036854775807; // 0x8000000000000001 float:1.4E-45 double:-4.9E-324;
        r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r6 == 0) goto L_0x01d1;
    L_0x01c2:
        r11 = r10.zzaqp;
        r0 = r10.zzaql;
        r12 = r0 + r2;
        r14 = 1;
        r16 = 0;
        r17 = 0;
        r11.zza(r12, r14, r15, r16, r17);
        goto L_0x01e0;
    L_0x01d1:
        r0 = r10.zzaqd;
        r1 = new com.google.android.gms.internal.ads.zzjk;
        r1.<init>(r2, r15);
        r0.addLast(r1);
        r0 = r10.zzaqk;
        r0 = r0 + r15;
        r10.zzaqk = r0;
    L_0x01e0:
        r0 = r28;
        goto L_0x01e8;
    L_0x01e3:
        r10 = r0;
        r0 = r1;
        r0.zzw(r2);
    L_0x01e8:
        r1 = r28.getPosition();
        r10.zzt(r1);
    L_0x01ef:
        r1 = r0;
        r0 = r10;
        goto L_0x0004;
    L_0x01f3:
        r10 = r0;
        r0 = r1;
        r1 = r10.zzaqh;
        if (r1 != 0) goto L_0x021e;
    L_0x01f9:
        r1 = r10.zzaqa;
        r1 = r1.data;
        r2 = 0;
        r3 = 1;
        r1 = r0.zza(r1, r2, r9, r3);
        if (r1 != 0) goto L_0x0207;
    L_0x0205:
        goto L_0x0395;
    L_0x0207:
        r10.zzaqh = r9;
        r1 = r10.zzaqa;
        r1.setPosition(r2);
        r1 = r10.zzaqa;
        r1 = r1.zzhd();
        r10.zzaqg = r1;
        r1 = r10.zzaqa;
        r1 = r1.readInt();
        r10.zzaqf = r1;
    L_0x021e:
        r1 = r10.zzaqg;
        r3 = 1;
        r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        if (r5 != 0) goto L_0x023a;
    L_0x0226:
        r1 = r10.zzaqa;
        r1 = r1.data;
        r0.readFully(r1, r9, r9);
        r1 = r10.zzaqh;
        r1 = r1 + r9;
        r10.zzaqh = r1;
        r1 = r10.zzaqa;
        r1 = r1.zzhh();
        r10.zzaqg = r1;
    L_0x023a:
        r1 = r10.zzaqg;
        r3 = r10.zzaqh;
        r3 = (long) r3;
        r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        if (r5 >= 0) goto L_0x024b;
    L_0x0243:
        r0 = new com.google.android.gms.internal.ads.zzfx;
        r1 = "Atom size less than header length (unsupported).";
        r0.<init>(r1);
        throw r0;
    L_0x024b:
        r1 = r28.getPosition();
        r3 = r10.zzaqh;
        r3 = (long) r3;
        r11 = r1 - r3;
        r1 = r10.zzaqf;
        r2 = com.google.android.gms.internal.ads.zziv.zzamk;
        if (r1 != r2) goto L_0x0276;
    L_0x025a:
        r1 = r10.zzapv;
        r1 = r1.size();
        r2 = 0;
    L_0x0261:
        if (r2 >= r1) goto L_0x0276;
    L_0x0263:
        r3 = r10.zzapv;
        r3 = r3.valueAt(r2);
        r3 = (com.google.android.gms.internal.ads.zzjl) r3;
        r3 = r3.zzaqt;
        r3.zzasu = r11;
        r3.zzasw = r11;
        r3.zzasv = r11;
        r2 = r2 + 1;
        goto L_0x0261;
    L_0x0276:
        r1 = r10.zzaqf;
        r2 = com.google.android.gms.internal.ads.zziv.zzalh;
        if (r1 != r2) goto L_0x029b;
    L_0x027c:
        r10.zzaqm = r8;
        r1 = r10.zzaqg;
        r3 = r11 + r1;
        r10.zzaqj = r3;
        r1 = r10.zzaqr;
        if (r1 != 0) goto L_0x0297;
    L_0x0288:
        r1 = r10.zzajq;
        r2 = new com.google.android.gms.internal.ads.zzih;
        r3 = r10.zzaan;
        r2.<init>(r3);
        r1.zza(r2);
        r1 = 1;
        r10.zzaqr = r1;
    L_0x0297:
        r10.zzaqe = r6;
        goto L_0x0394;
    L_0x029b:
        r1 = r10.zzaqf;
        r2 = com.google.android.gms.internal.ads.zziv.zzamb;
        if (r1 == r2) goto L_0x02c4;
    L_0x02a1:
        r2 = com.google.android.gms.internal.ads.zziv.zzamd;
        if (r1 == r2) goto L_0x02c4;
    L_0x02a5:
        r2 = com.google.android.gms.internal.ads.zziv.zzame;
        if (r1 == r2) goto L_0x02c4;
    L_0x02a9:
        r2 = com.google.android.gms.internal.ads.zziv.zzamf;
        if (r1 == r2) goto L_0x02c4;
    L_0x02ad:
        r2 = com.google.android.gms.internal.ads.zziv.zzamg;
        if (r1 == r2) goto L_0x02c4;
    L_0x02b1:
        r2 = com.google.android.gms.internal.ads.zziv.zzamk;
        if (r1 == r2) goto L_0x02c4;
    L_0x02b5:
        r2 = com.google.android.gms.internal.ads.zziv.zzaml;
        if (r1 == r2) goto L_0x02c4;
    L_0x02b9:
        r2 = com.google.android.gms.internal.ads.zziv.zzamm;
        if (r1 == r2) goto L_0x02c4;
    L_0x02bd:
        r2 = com.google.android.gms.internal.ads.zziv.zzamp;
        if (r1 != r2) goto L_0x02c2;
    L_0x02c1:
        goto L_0x02c4;
    L_0x02c2:
        r1 = 0;
        goto L_0x02c5;
    L_0x02c4:
        r1 = 1;
    L_0x02c5:
        if (r1 == 0) goto L_0x02f2;
    L_0x02c7:
        r1 = r28.getPosition();
        r3 = r10.zzaqg;
        r5 = r1 + r3;
        r1 = 8;
        r3 = r5 - r1;
        r1 = r10.zzaqc;
        r2 = new com.google.android.gms.internal.ads.zziw;
        r5 = r10.zzaqf;
        r2.<init>(r5, r3);
        r1.add(r2);
        r1 = r10.zzaqg;
        r5 = r10.zzaqh;
        r5 = (long) r5;
        r7 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1));
        if (r7 != 0) goto L_0x02ed;
    L_0x02e8:
        r10.zzt(r3);
        goto L_0x0394;
    L_0x02ed:
        r27.zzei();
        goto L_0x0394;
    L_0x02f2:
        r1 = r10.zzaqf;
        r2 = com.google.android.gms.internal.ads.zziv.zzams;
        if (r1 == r2) goto L_0x0347;
    L_0x02f8:
        r2 = com.google.android.gms.internal.ads.zziv.zzamr;
        if (r1 == r2) goto L_0x0347;
    L_0x02fc:
        r2 = com.google.android.gms.internal.ads.zziv.zzamc;
        if (r1 == r2) goto L_0x0347;
    L_0x0300:
        r2 = com.google.android.gms.internal.ads.zziv.zzama;
        if (r1 == r2) goto L_0x0347;
    L_0x0304:
        r2 = com.google.android.gms.internal.ads.zziv.zzamt;
        if (r1 == r2) goto L_0x0347;
    L_0x0308:
        r2 = com.google.android.gms.internal.ads.zziv.zzalw;
        if (r1 == r2) goto L_0x0347;
    L_0x030c:
        r2 = com.google.android.gms.internal.ads.zziv.zzalx;
        if (r1 == r2) goto L_0x0347;
    L_0x0310:
        r2 = com.google.android.gms.internal.ads.zziv.zzamo;
        if (r1 == r2) goto L_0x0347;
    L_0x0314:
        r2 = com.google.android.gms.internal.ads.zziv.zzaly;
        if (r1 == r2) goto L_0x0347;
    L_0x0318:
        r2 = com.google.android.gms.internal.ads.zziv.zzalz;
        if (r1 == r2) goto L_0x0347;
    L_0x031c:
        r2 = com.google.android.gms.internal.ads.zziv.zzamu;
        if (r1 == r2) goto L_0x0347;
    L_0x0320:
        r2 = com.google.android.gms.internal.ads.zziv.zzanc;
        if (r1 == r2) goto L_0x0347;
    L_0x0324:
        r2 = com.google.android.gms.internal.ads.zziv.zzand;
        if (r1 == r2) goto L_0x0347;
    L_0x0328:
        r2 = com.google.android.gms.internal.ads.zziv.zzanh;
        if (r1 == r2) goto L_0x0347;
    L_0x032c:
        r2 = com.google.android.gms.internal.ads.zziv.zzang;
        if (r1 == r2) goto L_0x0347;
    L_0x0330:
        r2 = com.google.android.gms.internal.ads.zziv.zzane;
        if (r1 == r2) goto L_0x0347;
    L_0x0334:
        r2 = com.google.android.gms.internal.ads.zziv.zzanf;
        if (r1 == r2) goto L_0x0347;
    L_0x0338:
        r2 = com.google.android.gms.internal.ads.zziv.zzamq;
        if (r1 == r2) goto L_0x0347;
    L_0x033c:
        r2 = com.google.android.gms.internal.ads.zziv.zzamn;
        if (r1 == r2) goto L_0x0347;
    L_0x0340:
        r2 = com.google.android.gms.internal.ads.zziv.zzaog;
        if (r1 != r2) goto L_0x0345;
    L_0x0344:
        goto L_0x0347;
    L_0x0345:
        r1 = 0;
        goto L_0x0348;
    L_0x0347:
        r1 = 1;
    L_0x0348:
        r2 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        if (r1 == 0) goto L_0x0381;
    L_0x034d:
        r1 = r10.zzaqh;
        if (r1 == r9) goto L_0x0359;
    L_0x0351:
        r0 = new com.google.android.gms.internal.ads.zzfx;
        r1 = "Leaf atom defines extended atom size (unsupported).";
        r0.<init>(r1);
        throw r0;
    L_0x0359:
        r4 = r10.zzaqg;
        r1 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
        if (r1 <= 0) goto L_0x0367;
    L_0x035f:
        r0 = new com.google.android.gms.internal.ads.zzfx;
        r1 = "Leaf atom with length > 2147483647 (unsupported).";
        r0.<init>(r1);
        throw r0;
    L_0x0367:
        r1 = new com.google.android.gms.internal.ads.zzpx;
        r2 = r10.zzaqg;
        r2 = (int) r2;
        r1.<init>(r2);
        r10.zzaqi = r1;
        r1 = r10.zzaqa;
        r1 = r1.data;
        r2 = r10.zzaqi;
        r2 = r2.data;
        r3 = 0;
        java.lang.System.arraycopy(r1, r3, r2, r3, r9);
        r1 = 1;
        r10.zzaqe = r1;
        goto L_0x0394;
    L_0x0381:
        r1 = 1;
        r4 = r10.zzaqg;
        r6 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
        if (r6 <= 0) goto L_0x0390;
    L_0x0388:
        r0 = new com.google.android.gms.internal.ads.zzfx;
        r1 = "Skipping atom with length > 2147483647 (unsupported).";
        r0.<init>(r1);
        throw r0;
    L_0x0390:
        r10.zzaqi = r8;
        r10.zzaqe = r1;
    L_0x0394:
        r2 = 1;
    L_0x0395:
        if (r2 != 0) goto L_0x01ef;
    L_0x0397:
        r0 = -1;
        return r0;
    L_0x0399:
        if (r3 >= r2) goto L_0x03ba;
    L_0x039b:
        r13 = r1.valueAt(r3);
        r13 = (com.google.android.gms.internal.ads.zzjl) r13;
        r14 = r13.zzaqy;
        r15 = r13.zzaqt;
        r15 = r15.zzasx;
        if (r14 == r15) goto L_0x03b7;
    L_0x03a9:
        r14 = r13.zzaqt;
        r14 = r14.zzasy;
        r15 = r13.zzaqy;
        r15 = r14[r15];
        r14 = (r15 > r11 ? 1 : (r15 == r11 ? 0 : -1));
        if (r14 >= 0) goto L_0x03b7;
    L_0x03b5:
        r4 = r13;
        r11 = r15;
    L_0x03b7:
        r3 = r3 + 1;
        goto L_0x0399;
    L_0x03ba:
        if (r4 != 0) goto L_0x03da;
    L_0x03bc:
        r1 = r10.zzaqj;
        r3 = r28.getPosition();
        r5 = r1 - r3;
        r1 = (int) r5;
        if (r1 >= 0) goto L_0x03cf;
    L_0x03c7:
        r0 = new com.google.android.gms.internal.ads.zzfx;
        r1 = "Offset to end of mdat was negative.";
        r0.<init>(r1);
        throw r0;
    L_0x03cf:
        r0.zzw(r1);
        r27.zzei();
        r2 = 0;
        r20 = 0;
        goto L_0x062c;
    L_0x03da:
        r1 = r4.zzaqt;
        r1 = r1.zzasy;
        r2 = r4.zzaqy;
        r2 = r1[r2];
        r11 = r28.getPosition();
        r13 = r2 - r11;
        r11 = (int) r13;
        if (r11 >= 0) goto L_0x03f3;
    L_0x03eb:
        r1 = "FragmentedMp4Extractor";
        r2 = "Ignoring negative offset to sample data.";
        android.util.Log.w(r1, r2);
        r11 = 0;
    L_0x03f3:
        r0.zzw(r11);
        r10.zzaqm = r4;
    L_0x03f8:
        r1 = r10.zzaqm;
        r1 = r1.zzaqt;
        r1 = r1.zzata;
        r2 = r10.zzaqm;
        r2 = r2.zzaqw;
        r1 = r1[r2];
        r10.zzaqn = r1;
        r1 = r10.zzaqm;
        r1 = r1.zzaqt;
        r1 = r1.zzate;
        if (r1 == 0) goto L_0x046c;
    L_0x040e:
        r1 = r10.zzaqm;
        r2 = r1.zzaqt;
        r3 = r2.zzati;
        r4 = r2.zzast;
        r4 = r4.zzapo;
        r11 = r2.zzatg;
        if (r11 == 0) goto L_0x041f;
    L_0x041c:
        r4 = r2.zzatg;
        goto L_0x0425;
    L_0x041f:
        r11 = r1.zzaqu;
        r11 = r11.zzasn;
        r4 = r11[r4];
    L_0x0425:
        r4 = r4.zzasr;
        r2 = r2.zzatf;
        r11 = r1.zzaqw;
        r2 = r2[r11];
        r11 = r10.zzapy;
        r11 = r11.data;
        if (r2 == 0) goto L_0x0436;
    L_0x0433:
        r12 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        goto L_0x0437;
    L_0x0436:
        r12 = 0;
    L_0x0437:
        r12 = r12 | r4;
        r12 = (byte) r12;
        r13 = 0;
        r11[r13] = r12;
        r11 = r10.zzapy;
        r11.setPosition(r13);
        r1 = r1.zzakw;
        r11 = r10.zzapy;
        r12 = 1;
        r1.zza(r11, r12);
        r1.zza(r3, r4);
        if (r2 != 0) goto L_0x0451;
    L_0x044e:
        r4 = r4 + 1;
        goto L_0x0462;
    L_0x0451:
        r2 = r3.readUnsignedShort();
        r11 = -2;
        r3.zzbl(r11);
        r2 = r2 * 6;
        r2 = r2 + r6;
        r1.zza(r3, r2);
        r4 = r4 + 1;
        r4 = r4 + r2;
    L_0x0462:
        r10.zzajn = r4;
        r1 = r10.zzaqn;
        r2 = r10.zzajn;
        r1 = r1 + r2;
        r10.zzaqn = r1;
        goto L_0x046f;
    L_0x046c:
        r1 = 0;
        r10.zzajn = r1;
    L_0x046f:
        r1 = r10.zzaqm;
        r1 = r1.zzaqu;
        r1 = r1.zzasm;
        r2 = 1;
        if (r1 != r2) goto L_0x0480;
    L_0x0478:
        r1 = r10.zzaqn;
        r1 = r1 - r9;
        r10.zzaqn = r1;
        r0.zzw(r9);
    L_0x0480:
        r10.zzaqe = r7;
        r1 = 0;
        r10.zzajm = r1;
    L_0x0485:
        r1 = r10.zzaqm;
        r1 = r1.zzaqt;
        r2 = r10.zzaqm;
        r2 = r2.zzaqu;
        r3 = r10.zzaqm;
        r11 = r3.zzakw;
        r3 = r10.zzaqm;
        r3 = r3.zzaqw;
        r4 = r2.zzakx;
        r12 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        if (r4 == 0) goto L_0x055f;
    L_0x049b:
        r4 = r10.zzapw;
        r4 = r4.data;
        r9 = 0;
        r4[r9] = r9;
        r14 = 1;
        r4[r14] = r9;
        r4[r6] = r9;
        r6 = r2.zzakx;
        r6 = r6 + r14;
        r9 = r2.zzakx;
        r9 = 4 - r9;
    L_0x04ae:
        r14 = r10.zzajn;
        r15 = r10.zzaqn;
        if (r14 >= r15) goto L_0x0575;
    L_0x04b4:
        r14 = r10.zzajm;
        if (r14 != 0) goto L_0x04fc;
    L_0x04b8:
        r0.readFully(r4, r9, r6);
        r14 = r10.zzapw;
        r15 = 0;
        r14.setPosition(r15);
        r14 = r10.zzapw;
        r14 = r14.zzhg();
        r5 = 1;
        r14 = r14 - r5;
        r10.zzajm = r14;
        r14 = r10.zzahx;
        r14.setPosition(r15);
        r14 = r10.zzahx;
        r11.zza(r14, r7);
        r14 = r10.zzapw;
        r11.zza(r14, r5);
        r5 = r10.zzaqq;
        if (r5 == 0) goto L_0x04ec;
    L_0x04de:
        r5 = r2.zzaad;
        r5 = r5.zzzj;
        r14 = r4[r7];
        r5 = com.google.android.gms.internal.ads.zzpu.zza(r5, r14);
        if (r5 == 0) goto L_0x04ec;
    L_0x04ea:
        r5 = 1;
        goto L_0x04ed;
    L_0x04ec:
        r5 = 0;
    L_0x04ed:
        r10.zzaqo = r5;
        r5 = r10.zzajn;
        r5 = r5 + 5;
        r10.zzajn = r5;
        r5 = r10.zzaqn;
        r5 = r5 + r9;
        r10.zzaqn = r5;
        r5 = 3;
        goto L_0x04ae;
    L_0x04fc:
        r5 = r10.zzaqo;
        if (r5 == 0) goto L_0x0549;
    L_0x0500:
        r5 = r10.zzapx;
        r14 = r10.zzajm;
        r5.reset(r14);
        r5 = r10.zzapx;
        r5 = r5.data;
        r14 = r10.zzajm;
        r15 = 0;
        r0.readFully(r5, r15, r14);
        r5 = r10.zzapx;
        r14 = r10.zzajm;
        r11.zza(r5, r14);
        r5 = r10.zzajm;
        r14 = r10.zzapx;
        r14 = r14.data;
        r15 = r10.zzapx;
        r15 = r15.limit();
        r14 = com.google.android.gms.internal.ads.zzpu.zzb(r14, r15);
        r15 = r10.zzapx;
        r7 = "video/hevc";
        r8 = r2.zzaad;
        r8 = r8.zzzj;
        r7 = r7.equals(r8);
        r15.setPosition(r7);
        r7 = r10.zzapx;
        r7.zzbk(r14);
        r7 = r1.zzal(r3);
        r7 = r7 * r12;
        r14 = r10.zzapx;
        r15 = r10.zzaqq;
        com.google.android.gms.internal.ads.zzoc.zza(r7, r14, r15);
        goto L_0x0550;
    L_0x0549:
        r5 = r10.zzajm;
        r7 = 0;
        r5 = r11.zza(r0, r5, r7);
    L_0x0550:
        r7 = r10.zzajn;
        r7 = r7 + r5;
        r10.zzajn = r7;
        r7 = r10.zzajm;
        r7 = r7 - r5;
        r10.zzajm = r7;
        r5 = 3;
        r7 = 4;
        r8 = 0;
        goto L_0x04ae;
    L_0x055f:
        r4 = r10.zzajn;
        r5 = r10.zzaqn;
        if (r4 >= r5) goto L_0x0575;
    L_0x0565:
        r4 = r10.zzaqn;
        r5 = r10.zzajn;
        r4 = r4 - r5;
        r5 = 0;
        r4 = r11.zza(r0, r4, r5);
        r5 = r10.zzajn;
        r5 = r5 + r4;
        r10.zzajn = r5;
        goto L_0x055f;
    L_0x0575:
        r4 = r1.zzal(r3);
        r4 = r4 * r12;
        r6 = r10.zzapz;
        if (r6 == 0) goto L_0x0584;
    L_0x057e:
        r0 = new java.lang.NoSuchMethodError;
        r0.<init>();
        throw r0;
    L_0x0584:
        r6 = r1.zzate;
        if (r6 == 0) goto L_0x058b;
    L_0x0588:
        r6 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        goto L_0x058c;
    L_0x058b:
        r6 = 0;
    L_0x058c:
        r7 = r1.zzatd;
        r3 = r7[r3];
        r14 = r6 | r3;
        r3 = r1.zzate;
        if (r3 == 0) goto L_0x05bb;
    L_0x0596:
        r3 = r1.zzatg;
        if (r3 == 0) goto L_0x059e;
    L_0x059a:
        r2 = r1.zzatg;
    L_0x059c:
        r8 = r2;
        goto L_0x05a7;
    L_0x059e:
        r2 = r2.zzasn;
        r3 = r1.zzast;
        r3 = r3.zzapo;
        r2 = r2[r3];
        goto L_0x059c;
    L_0x05a7:
        r2 = r10.zzaqm;
        r2 = r2.zzara;
        if (r8 == r2) goto L_0x05b6;
    L_0x05ad:
        r2 = new com.google.android.gms.internal.ads.zzij;
        r3 = r8.zzass;
        r6 = 1;
        r2.<init>(r6, r3);
        goto L_0x05bd;
    L_0x05b6:
        r2 = r10.zzaqm;
        r2 = r2.zzaqz;
        goto L_0x05bd;
    L_0x05bb:
        r2 = 0;
        r8 = 0;
    L_0x05bd:
        r3 = r10.zzaqm;
        r3.zzaqz = r2;
        r3 = r10.zzaqm;
        r3.zzara = r8;
        r15 = r10.zzaqn;
        r16 = 0;
        r12 = r4;
        r17 = r2;
        r11.zza(r12, r14, r15, r16, r17);
    L_0x05cf:
        r2 = r10.zzaqd;
        r2 = r2.isEmpty();
        if (r2 != 0) goto L_0x05f9;
    L_0x05d7:
        r2 = r10.zzaqd;
        r2 = r2.removeFirst();
        r2 = (com.google.android.gms.internal.ads.zzjk) r2;
        r3 = r10.zzaqk;
        r6 = r2.size;
        r3 = r3 - r6;
        r10.zzaqk = r3;
        r11 = r10.zzaqp;
        r6 = r2.zzaqs;
        r12 = r4 + r6;
        r14 = 1;
        r15 = r2.size;
        r2 = r10.zzaqk;
        r17 = 0;
        r16 = r2;
        r11.zza(r12, r14, r15, r16, r17);
        goto L_0x05cf;
    L_0x05f9:
        r2 = r10.zzaqm;
        r3 = r2.zzaqw;
        r4 = 1;
        r3 = r3 + r4;
        r2.zzaqw = r3;
        r2 = r10.zzaqm;
        r3 = r2.zzaqx;
        r3 = r3 + r4;
        r2.zzaqx = r3;
        r2 = r10.zzaqm;
        r2 = r2.zzaqx;
        r1 = r1.zzasz;
        r3 = r10.zzaqm;
        r3 = r3.zzaqy;
        r1 = r1[r3];
        if (r2 != r1) goto L_0x0626;
    L_0x0616:
        r1 = r10.zzaqm;
        r2 = r1.zzaqy;
        r2 = r2 + r4;
        r1.zzaqy = r2;
        r1 = r10.zzaqm;
        r2 = 0;
        r1.zzaqx = r2;
        r1 = 0;
        r10.zzaqm = r1;
        goto L_0x0627;
    L_0x0626:
        r2 = 0;
    L_0x0627:
        r1 = 3;
        r10.zzaqe = r1;
        r20 = r4;
    L_0x062c:
        if (r20 == 0) goto L_0x01ef;
    L_0x062e:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzji.zza(com.google.android.gms.internal.ads.zzia, com.google.android.gms.internal.ads.zzif):int");
    }

    private final void zzei() {
        this.zzaqe = 0;
        this.zzaqh = 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:162:0x03fa  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x03f3  */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x0404  */
    /* JADX WARNING: Removed duplicated region for block: B:258:0x062a  */
    /* JADX WARNING: Removed duplicated region for block: B:293:0x0622 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:265:0x0658  */
    private final void zzt(long r54) throws com.google.android.gms.internal.ads.zzfx {
        /*
        r53 = this;
        r0 = r53;
    L_0x0002:
        r1 = r0.zzaqc;
        r1 = r1.isEmpty();
        if (r1 != 0) goto L_0x0703;
    L_0x000a:
        r1 = r0.zzaqc;
        r1 = r1.peek();
        r1 = (com.google.android.gms.internal.ads.zziw) r1;
        r1 = r1.zzaop;
        r5 = (r1 > r54 ? 1 : (r1 == r54 ? 0 : -1));
        if (r5 != 0) goto L_0x0703;
    L_0x0018:
        r1 = r0.zzaqc;
        r1 = r1.pop();
        r1 = (com.google.android.gms.internal.ads.zziw) r1;
        r2 = r1.type;
        r5 = com.google.android.gms.internal.ads.zziv.zzamb;
        r7 = 12;
        r9 = 8;
        r11 = 1;
        if (r2 != r5) goto L_0x01c8;
    L_0x002b:
        r2 = "Unexpected moov box.";
        com.google.android.gms.internal.ads.zzpo.checkState(r11, r2);
        r2 = r1.zzaoq;
        r2 = zzb(r2);
        r5 = com.google.android.gms.internal.ads.zziv.zzamm;
        r5 = r1.zzaj(r5);
        r14 = new android.util.SparseArray;
        r14.<init>();
        r12 = -9223372036854775807; // 0x8000000000000001 float:1.4E-45 double:-4.9E-324;
        r15 = r5.zzaoq;
        r15 = r15.size();
        r18 = r12;
        r12 = 0;
    L_0x004f:
        if (r12 >= r15) goto L_0x00bd;
    L_0x0051:
        r13 = r5.zzaoq;
        r13 = r13.get(r12);
        r13 = (com.google.android.gms.internal.ads.zzix) r13;
        r10 = r13.type;
        r6 = com.google.android.gms.internal.ads.zziv.zzaly;
        if (r10 != r6) goto L_0x0096;
    L_0x005f:
        r6 = r13.zzaos;
        r6.setPosition(r7);
        r10 = r6.readInt();
        r13 = r6.zzhg();
        r13 = r13 - r11;
        r7 = r6.zzhg();
        r11 = r6.zzhg();
        r6 = r6.readInt();
        r10 = java.lang.Integer.valueOf(r10);
        r8 = new com.google.android.gms.internal.ads.zzjf;
        r8.<init>(r13, r7, r11, r6);
        r6 = android.util.Pair.create(r10, r8);
        r7 = r6.first;
        r7 = (java.lang.Integer) r7;
        r7 = r7.intValue();
        r6 = r6.second;
        r6 = (com.google.android.gms.internal.ads.zzjf) r6;
        r14.put(r7, r6);
        goto L_0x00b7;
    L_0x0096:
        r6 = r13.type;
        r7 = com.google.android.gms.internal.ads.zziv.zzamn;
        if (r6 != r7) goto L_0x00b7;
    L_0x009c:
        r6 = r13.zzaos;
        r6.setPosition(r9);
        r7 = r6.readInt();
        r7 = com.google.android.gms.internal.ads.zziv.zzaf(r7);
        if (r7 != 0) goto L_0x00b2;
    L_0x00ab:
        r6 = r6.zzhd();
    L_0x00af:
        r18 = r6;
        goto L_0x00b7;
    L_0x00b2:
        r6 = r6.zzhh();
        goto L_0x00af;
    L_0x00b7:
        r12 = r12 + 1;
        r7 = 12;
        r11 = 1;
        goto L_0x004f;
    L_0x00bd:
        r5 = new android.util.SparseArray;
        r5.<init>();
        r6 = r1.zzaor;
        r6 = r6.size();
        r7 = 0;
    L_0x00c9:
        if (r7 >= r6) goto L_0x00f8;
    L_0x00cb:
        r8 = r1.zzaor;
        r8 = r8.get(r7);
        r12 = r8;
        r12 = (com.google.android.gms.internal.ads.zziw) r12;
        r8 = r12.type;
        r10 = com.google.android.gms.internal.ads.zziv.zzamd;
        if (r8 != r10) goto L_0x00f3;
    L_0x00da:
        r8 = com.google.android.gms.internal.ads.zziv.zzamc;
        r13 = r1.zzai(r8);
        r17 = 0;
        r8 = r14;
        r14 = r18;
        r16 = r2;
        r10 = com.google.android.gms.internal.ads.zziy.zza(r12, r13, r14, r16, r17);
        if (r10 == 0) goto L_0x00f4;
    L_0x00ed:
        r11 = r10.id;
        r5.put(r11, r10);
        goto L_0x00f4;
    L_0x00f3:
        r8 = r14;
    L_0x00f4:
        r7 = r7 + 1;
        r14 = r8;
        goto L_0x00c9;
    L_0x00f8:
        r8 = r14;
        r1 = r5.size();
        r2 = r0.zzapv;
        r2 = r2.size();
        if (r2 != 0) goto L_0x0199;
    L_0x0105:
        r2 = 0;
    L_0x0106:
        if (r2 >= r1) goto L_0x013a;
    L_0x0108:
        r6 = r5.valueAt(r2);
        r6 = (com.google.android.gms.internal.ads.zzjs) r6;
        r7 = new com.google.android.gms.internal.ads.zzjl;
        r10 = r0.zzajq;
        r11 = r6.type;
        r10 = r10.zzb(r2, r11);
        r7.<init>(r10);
        r10 = r6.id;
        r10 = r8.get(r10);
        r10 = (com.google.android.gms.internal.ads.zzjf) r10;
        r7.zza(r6, r10);
        r10 = r0.zzapv;
        r11 = r6.id;
        r10.put(r11, r7);
        r10 = r0.zzaan;
        r6 = r6.zzaan;
        r6 = java.lang.Math.max(r10, r6);
        r0.zzaan = r6;
        r2 = r2 + 1;
        goto L_0x0106;
    L_0x013a:
        r1 = r0.flags;
        r2 = 4;
        r1 = r1 & r2;
        if (r1 == 0) goto L_0x0163;
    L_0x0140:
        r1 = r0.zzaqp;
        if (r1 != 0) goto L_0x0163;
    L_0x0144:
        r1 = r0.zzajq;
        r5 = r0.zzapv;
        r5 = r5.size();
        r1 = r1.zzb(r5, r2);
        r0.zzaqp = r1;
        r1 = r0.zzaqp;
        r2 = "application/x-emsg";
        r5 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
        r7 = 0;
        r2 = com.google.android.gms.internal.ads.zzfs.zza(r7, r2, r5);
        r1.zzf(r2);
    L_0x0163:
        r1 = r0.flags;
        r1 = r1 & r9;
        if (r1 == 0) goto L_0x0192;
    L_0x0168:
        r1 = r0.zzaqq;
        if (r1 != 0) goto L_0x0192;
    L_0x016c:
        r1 = r0.zzajq;
        r2 = r0.zzapv;
        r2 = r2.size();
        r5 = 1;
        r2 = r2 + r5;
        r5 = 3;
        r1 = r1.zzb(r2, r5);
        r5 = 0;
        r6 = "application/cea-608";
        r7 = 0;
        r8 = -1;
        r9 = 0;
        r10 = 0;
        r11 = 0;
        r2 = com.google.android.gms.internal.ads.zzfs.zza(r5, r6, r7, r8, r9, r10, r11);
        r1.zzf(r2);
        r2 = 1;
        r2 = new com.google.android.gms.internal.ads.zzii[r2];
        r5 = 0;
        r2[r5] = r1;
        r0.zzaqq = r2;
    L_0x0192:
        r1 = r0.zzajq;
        r1.zzdy();
        goto L_0x0002;
    L_0x0199:
        r2 = r0.zzapv;
        r2 = r2.size();
        if (r2 != r1) goto L_0x01a3;
    L_0x01a1:
        r2 = 1;
        goto L_0x01a4;
    L_0x01a3:
        r2 = 0;
    L_0x01a4:
        com.google.android.gms.internal.ads.zzpo.checkState(r2);
        r2 = 0;
    L_0x01a8:
        if (r2 >= r1) goto L_0x0002;
    L_0x01aa:
        r6 = r5.valueAt(r2);
        r6 = (com.google.android.gms.internal.ads.zzjs) r6;
        r7 = r0.zzapv;
        r9 = r6.id;
        r7 = r7.get(r9);
        r7 = (com.google.android.gms.internal.ads.zzjl) r7;
        r9 = r6.id;
        r9 = r8.get(r9);
        r9 = (com.google.android.gms.internal.ads.zzjf) r9;
        r7.zza(r6, r9);
        r2 = r2 + 1;
        goto L_0x01a8;
    L_0x01c8:
        r7 = 0;
        r2 = r1.type;
        r5 = com.google.android.gms.internal.ads.zziv.zzamk;
        if (r2 != r5) goto L_0x06ec;
    L_0x01cf:
        r2 = r0.zzapv;
        r5 = r0.flags;
        r6 = r0.zzaqb;
        r8 = r1.zzaor;
        r8 = r8.size();
        r10 = 0;
    L_0x01dc:
        if (r10 >= r8) goto L_0x06be;
    L_0x01de:
        r11 = r1.zzaor;
        r11 = r11.get(r10);
        r11 = (com.google.android.gms.internal.ads.zziw) r11;
        r12 = r11.type;
        r13 = com.google.android.gms.internal.ads.zziv.zzaml;
        if (r12 != r13) goto L_0x069e;
    L_0x01ec:
        r12 = com.google.android.gms.internal.ads.zziv.zzalx;
        r12 = r11.zzai(r12);
        r12 = r12.zzaos;
        r12.setPosition(r9);
        r13 = r12.readInt();
        r13 = com.google.android.gms.internal.ads.zziv.zzag(r13);
        r14 = r12.readInt();
        r15 = r5 & 16;
        if (r15 != 0) goto L_0x0208;
    L_0x0207:
        goto L_0x0209;
    L_0x0208:
        r14 = 0;
    L_0x0209:
        r14 = r2.get(r14);
        r14 = (com.google.android.gms.internal.ads.zzjl) r14;
        if (r14 != 0) goto L_0x0215;
    L_0x0211:
        r14 = r7;
        r20 = r8;
        goto L_0x0265;
    L_0x0215:
        r15 = r13 & 1;
        if (r15 == 0) goto L_0x0228;
    L_0x0219:
        r20 = r8;
        r7 = r12.zzhh();
        r15 = r14.zzaqt;
        r15.zzasv = r7;
        r15 = r14.zzaqt;
        r15.zzasw = r7;
        goto L_0x022a;
    L_0x0228:
        r20 = r8;
    L_0x022a:
        r7 = r14.zzaqv;
        r8 = r13 & 2;
        if (r8 == 0) goto L_0x0237;
    L_0x0230:
        r8 = r12.zzhg();
        r15 = 1;
        r8 = r8 - r15;
        goto L_0x0239;
    L_0x0237:
        r8 = r7.zzapo;
    L_0x0239:
        r15 = r13 & 8;
        if (r15 == 0) goto L_0x0242;
    L_0x023d:
        r15 = r12.zzhg();
        goto L_0x0244;
    L_0x0242:
        r15 = r7.duration;
    L_0x0244:
        r16 = r13 & 16;
        if (r16 == 0) goto L_0x024f;
    L_0x0248:
        r16 = r12.zzhg();
        r9 = r16;
        goto L_0x0251;
    L_0x024f:
        r9 = r7.size;
    L_0x0251:
        r13 = r13 & 32;
        if (r13 == 0) goto L_0x025a;
    L_0x0255:
        r7 = r12.zzhg();
        goto L_0x025c;
    L_0x025a:
        r7 = r7.flags;
    L_0x025c:
        r12 = r14.zzaqt;
        r13 = new com.google.android.gms.internal.ads.zzjf;
        r13.<init>(r8, r15, r9, r7);
        r12.zzast = r13;
    L_0x0265:
        if (r14 == 0) goto L_0x0690;
    L_0x0267:
        r7 = r14.zzaqt;
        r8 = r7.zzatk;
        r14.reset();
        r12 = com.google.android.gms.internal.ads.zziv.zzalw;
        r12 = r11.zzai(r12);
        if (r12 == 0) goto L_0x029b;
    L_0x0276:
        r12 = r5 & 2;
        if (r12 != 0) goto L_0x029b;
    L_0x027a:
        r8 = com.google.android.gms.internal.ads.zziv.zzalw;
        r8 = r11.zzai(r8);
        r8 = r8.zzaos;
        r9 = 8;
        r8.setPosition(r9);
        r9 = r8.readInt();
        r9 = com.google.android.gms.internal.ads.zziv.zzaf(r9);
        r12 = 1;
        if (r9 != r12) goto L_0x0297;
    L_0x0292:
        r8 = r8.zzhh();
        goto L_0x029b;
    L_0x0297:
        r8 = r8.zzhd();
    L_0x029b:
        r12 = r11.zzaoq;
        r13 = r12.size();
        r21 = r2;
        r2 = 0;
        r3 = 0;
        r15 = 0;
    L_0x02a6:
        if (r15 >= r13) goto L_0x02ce;
    L_0x02a8:
        r4 = r12.get(r15);
        r4 = (com.google.android.gms.internal.ads.zzix) r4;
        r22 = r8;
        r8 = r4.type;
        r9 = com.google.android.gms.internal.ads.zziv.zzalz;
        if (r8 != r9) goto L_0x02c7;
    L_0x02b6:
        r4 = r4.zzaos;
        r8 = 12;
        r4.setPosition(r8);
        r4 = r4.zzhg();
        if (r4 <= 0) goto L_0x02c9;
    L_0x02c3:
        r3 = r3 + r4;
        r2 = r2 + 1;
        goto L_0x02c9;
    L_0x02c7:
        r8 = 12;
    L_0x02c9:
        r15 = r15 + 1;
        r8 = r22;
        goto L_0x02a6;
    L_0x02ce:
        r22 = r8;
        r4 = 0;
        r8 = 12;
        r14.zzaqy = r4;
        r14.zzaqx = r4;
        r14.zzaqw = r4;
        r4 = r14.zzaqt;
        r4.zzasx = r2;
        r4.zzapk = r3;
        r9 = r4.zzasz;
        if (r9 == 0) goto L_0x02e8;
    L_0x02e3:
        r9 = r4.zzasz;
        r9 = r9.length;
        if (r9 >= r2) goto L_0x02f0;
    L_0x02e8:
        r9 = new long[r2];
        r4.zzasy = r9;
        r2 = new int[r2];
        r4.zzasz = r2;
    L_0x02f0:
        r2 = r4.zzata;
        if (r2 == 0) goto L_0x02f9;
    L_0x02f4:
        r2 = r4.zzata;
        r2 = r2.length;
        if (r2 >= r3) goto L_0x0311;
    L_0x02f9:
        r3 = r3 * 125;
        r3 = r3 / 100;
        r2 = new int[r3];
        r4.zzata = r2;
        r2 = new int[r3];
        r4.zzatb = r2;
        r2 = new long[r3];
        r4.zzatc = r2;
        r2 = new boolean[r3];
        r4.zzatd = r2;
        r2 = new boolean[r3];
        r4.zzatf = r2;
    L_0x0311:
        r2 = 0;
        r3 = 0;
        r4 = 0;
    L_0x0314:
        if (r2 >= r13) goto L_0x04b2;
    L_0x0316:
        r17 = r12.get(r2);
        r8 = r17;
        r8 = (com.google.android.gms.internal.ads.zzix) r8;
        r9 = r8.type;
        r15 = com.google.android.gms.internal.ads.zziv.zzalz;
        if (r9 != r15) goto L_0x0482;
    L_0x0324:
        r9 = r3 + 1;
        r8 = r8.zzaos;
        r15 = 8;
        r8.setPosition(r15);
        r15 = r8.readInt();
        r15 = com.google.android.gms.internal.ads.zziv.zzag(r15);
        r24 = r9;
        r9 = r14.zzaqu;
        r25 = r12;
        r12 = r14.zzaqt;
        r26 = r13;
        r13 = r12.zzast;
        r0 = r12.zzasz;
        r16 = r8.zzhg();
        r0[r3] = r16;
        r0 = r12.zzasy;
        r27 = r6;
        r28 = r7;
        r6 = r12.zzasv;
        r0[r3] = r6;
        r0 = r15 & 1;
        if (r0 == 0) goto L_0x036b;
    L_0x0357:
        r0 = r12.zzasy;
        r6 = r0[r3];
        r29 = r1;
        r1 = r8.readInt();
        r30 = r10;
        r31 = r11;
        r10 = (long) r1;
        r16 = r6 + r10;
        r0[r3] = r16;
        goto L_0x0371;
    L_0x036b:
        r29 = r1;
        r30 = r10;
        r31 = r11;
    L_0x0371:
        r0 = r15 & 4;
        if (r0 == 0) goto L_0x0377;
    L_0x0375:
        r0 = 1;
        goto L_0x0378;
    L_0x0377:
        r0 = 0;
    L_0x0378:
        r1 = r13.flags;
        if (r0 == 0) goto L_0x0380;
    L_0x037c:
        r1 = r8.zzhg();
    L_0x0380:
        r6 = r15 & 256;
        if (r6 == 0) goto L_0x0386;
    L_0x0384:
        r6 = 1;
        goto L_0x0387;
    L_0x0386:
        r6 = 0;
    L_0x0387:
        r7 = r15 & 512;
        if (r7 == 0) goto L_0x038d;
    L_0x038b:
        r7 = 1;
        goto L_0x038e;
    L_0x038d:
        r7 = 0;
    L_0x038e:
        r10 = r15 & 1024;
        if (r10 == 0) goto L_0x0394;
    L_0x0392:
        r10 = 1;
        goto L_0x0395;
    L_0x0394:
        r10 = 0;
    L_0x0395:
        r11 = r15 & 2048;
        if (r11 == 0) goto L_0x039b;
    L_0x0399:
        r11 = 1;
        goto L_0x039c;
    L_0x039b:
        r11 = 0;
    L_0x039c:
        r15 = r9.zzaso;
        if (r15 == 0) goto L_0x03c4;
    L_0x03a0:
        r15 = r9.zzaso;
        r15 = r15.length;
        r32 = r1;
        r1 = 1;
        if (r15 != r1) goto L_0x03c6;
    L_0x03a8:
        r1 = r9.zzaso;
        r15 = 0;
        r16 = r1[r15];
        r18 = 0;
        r1 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1));
        if (r1 != 0) goto L_0x03c6;
    L_0x03b3:
        r1 = r9.zzasp;
        r33 = r1[r15];
        r35 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r39 = r14;
        r14 = r9.zzcr;
        r37 = r14;
        r15 = com.google.android.gms.internal.ads.zzqe.zza(r33, r35, r37);
        goto L_0x03ca;
    L_0x03c4:
        r32 = r1;
    L_0x03c6:
        r39 = r14;
        r15 = 0;
    L_0x03ca:
        r1 = r12.zzata;
        r14 = r12.zzatb;
        r40 = r2;
        r2 = r12.zzatc;
        r41 = r1;
        r1 = r12.zzatd;
        r42 = r1;
        r1 = r9.type;
        r43 = r2;
        r2 = 2;
        if (r1 != r2) goto L_0x03e5;
    L_0x03df:
        r1 = r5 & 1;
        if (r1 == 0) goto L_0x03e5;
    L_0x03e3:
        r1 = 1;
        goto L_0x03e6;
    L_0x03e5:
        r1 = 0;
    L_0x03e6:
        r2 = r12.zzasz;
        r2 = r2[r3];
        r2 = r2 + r4;
        r45 = r4;
        r44 = r5;
        r4 = r9.zzcr;
        if (r3 <= 0) goto L_0x03fa;
    L_0x03f3:
        r48 = r14;
        r46 = r15;
        r14 = r12.zzatk;
        goto L_0x0400;
    L_0x03fa:
        r48 = r14;
        r46 = r15;
        r14 = r22;
    L_0x0400:
        r3 = r45;
    L_0x0402:
        if (r3 >= r2) goto L_0x0479;
    L_0x0404:
        if (r6 == 0) goto L_0x040b;
    L_0x0406:
        r9 = r8.zzhg();
        goto L_0x040d;
    L_0x040b:
        r9 = r13.duration;
    L_0x040d:
        if (r7 == 0) goto L_0x0416;
    L_0x040f:
        r16 = r8.zzhg();
        r49 = r2;
        goto L_0x041c;
    L_0x0416:
        r49 = r2;
        r2 = r13.size;
        r16 = r2;
    L_0x041c:
        if (r3 != 0) goto L_0x0423;
    L_0x041e:
        if (r0 == 0) goto L_0x0423;
    L_0x0420:
        r2 = r32;
        goto L_0x042c;
    L_0x0423:
        if (r10 == 0) goto L_0x042a;
    L_0x0425:
        r2 = r8.readInt();
        goto L_0x042c;
    L_0x042a:
        r2 = r13.flags;
    L_0x042c:
        if (r11 == 0) goto L_0x0440;
    L_0x042e:
        r50 = r0;
        r0 = r8.readInt();
        r0 = r0 * 1000;
        r51 = r6;
        r52 = r7;
        r6 = (long) r0;
        r6 = r6 / r4;
        r0 = (int) r6;
        r48[r3] = r0;
        goto L_0x0449;
    L_0x0440:
        r50 = r0;
        r51 = r6;
        r52 = r7;
        r0 = 0;
        r48[r3] = r0;
    L_0x0449:
        r35 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r33 = r14;
        r37 = r4;
        r6 = com.google.android.gms.internal.ads.zzqe.zza(r33, r35, r37);
        r17 = r6 - r46;
        r43[r3] = r17;
        r41[r3] = r16;
        r0 = 16;
        r2 = r2 >> r0;
        r0 = 1;
        r2 = r2 & r0;
        if (r2 != 0) goto L_0x0466;
    L_0x0460:
        if (r1 == 0) goto L_0x0464;
    L_0x0462:
        if (r3 != 0) goto L_0x0466;
    L_0x0464:
        r0 = 1;
        goto L_0x0467;
    L_0x0466:
        r0 = 0;
    L_0x0467:
        r42[r3] = r0;
        r6 = (long) r9;
        r16 = r14 + r6;
        r3 = r3 + 1;
        r14 = r16;
        r2 = r49;
        r0 = r50;
        r6 = r51;
        r7 = r52;
        goto L_0x0402;
    L_0x0479:
        r49 = r2;
        r12.zzatk = r14;
        r3 = r24;
        r4 = r49;
        goto L_0x0498;
    L_0x0482:
        r29 = r1;
        r40 = r2;
        r45 = r4;
        r44 = r5;
        r27 = r6;
        r28 = r7;
        r30 = r10;
        r31 = r11;
        r25 = r12;
        r26 = r13;
        r39 = r14;
    L_0x0498:
        r2 = r40 + 1;
        r12 = r25;
        r13 = r26;
        r6 = r27;
        r7 = r28;
        r1 = r29;
        r10 = r30;
        r11 = r31;
        r14 = r39;
        r5 = r44;
        r0 = r53;
        r8 = 12;
        goto L_0x0314;
    L_0x04b2:
        r29 = r1;
        r44 = r5;
        r27 = r6;
        r28 = r7;
        r30 = r10;
        r31 = r11;
        r39 = r14;
        r0 = com.google.android.gms.internal.ads.zziv.zzanc;
        r0 = r11.zzai(r0);
        if (r0 == 0) goto L_0x0546;
    L_0x04c8:
        r14 = r39;
        r1 = r14.zzaqu;
        r1 = r1.zzasn;
        r2 = r28;
        r3 = r2.zzast;
        r3 = r3.zzapo;
        r1 = r1[r3];
        r0 = r0.zzaos;
        r1 = r1.zzasr;
        r3 = 8;
        r0.setPosition(r3);
        r4 = r0.readInt();
        r4 = com.google.android.gms.internal.ads.zziv.zzag(r4);
        r5 = 1;
        r4 = r4 & r5;
        if (r4 != r5) goto L_0x04ee;
    L_0x04eb:
        r0.zzbl(r3);
    L_0x04ee:
        r3 = r0.readUnsignedByte();
        r4 = r0.zzhg();
        r5 = r2.zzapk;
        if (r4 == r5) goto L_0x051d;
    L_0x04fa:
        r0 = new com.google.android.gms.internal.ads.zzfx;
        r1 = r2.zzapk;
        r2 = 41;
        r3 = new java.lang.StringBuilder;
        r3.<init>(r2);
        r2 = "Length mismatch: ";
        r3.append(r2);
        r3.append(r4);
        r2 = ", ";
        r3.append(r2);
        r3.append(r1);
        r1 = r3.toString();
        r0.<init>(r1);
        throw r0;
    L_0x051d:
        if (r3 != 0) goto L_0x0534;
    L_0x051f:
        r3 = r2.zzatf;
        r5 = 0;
        r6 = 0;
    L_0x0523:
        if (r5 >= r4) goto L_0x0542;
    L_0x0525:
        r7 = r0.readUnsignedByte();
        r6 = r6 + r7;
        if (r7 <= r1) goto L_0x052e;
    L_0x052c:
        r7 = 1;
        goto L_0x052f;
    L_0x052e:
        r7 = 0;
    L_0x052f:
        r3[r5] = r7;
        r5 = r5 + 1;
        goto L_0x0523;
    L_0x0534:
        if (r3 <= r1) goto L_0x0538;
    L_0x0536:
        r0 = 1;
        goto L_0x0539;
    L_0x0538:
        r0 = 0;
    L_0x0539:
        r3 = r3 * r4;
        r1 = 0;
        r6 = r1 + r3;
        r3 = r2.zzatf;
        java.util.Arrays.fill(r3, r1, r4, r0);
    L_0x0542:
        r2.zzak(r6);
        goto L_0x0548;
    L_0x0546:
        r2 = r28;
    L_0x0548:
        r0 = com.google.android.gms.internal.ads.zziv.zzand;
        r0 = r11.zzai(r0);
        if (r0 == 0) goto L_0x059a;
    L_0x0550:
        r0 = r0.zzaos;
        r1 = 8;
        r0.setPosition(r1);
        r3 = r0.readInt();
        r4 = com.google.android.gms.internal.ads.zziv.zzag(r3);
        r5 = 1;
        r4 = r4 & r5;
        if (r4 != r5) goto L_0x0566;
    L_0x0563:
        r0.zzbl(r1);
    L_0x0566:
        r1 = r0.zzhg();
        if (r1 == r5) goto L_0x0585;
    L_0x056c:
        r0 = new com.google.android.gms.internal.ads.zzfx;
        r2 = 40;
        r3 = new java.lang.StringBuilder;
        r3.<init>(r2);
        r2 = "Unexpected saio entry count: ";
        r3.append(r2);
        r3.append(r1);
        r1 = r3.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0585:
        r1 = com.google.android.gms.internal.ads.zziv.zzaf(r3);
        r3 = r2.zzasw;
        if (r1 != 0) goto L_0x0592;
    L_0x058d:
        r0 = r0.zzhd();
        goto L_0x0596;
    L_0x0592:
        r0 = r0.zzhh();
    L_0x0596:
        r5 = r3 + r0;
        r2.zzasw = r5;
    L_0x059a:
        r0 = com.google.android.gms.internal.ads.zziv.zzanh;
        r0 = r11.zzai(r0);
        if (r0 == 0) goto L_0x05a8;
    L_0x05a2:
        r0 = r0.zzaos;
        r1 = 0;
        zza(r0, r1, r2);
    L_0x05a8:
        r0 = com.google.android.gms.internal.ads.zziv.zzane;
        r0 = r11.zzai(r0);
        r1 = com.google.android.gms.internal.ads.zziv.zzanf;
        r1 = r11.zzai(r1);
        if (r0 == 0) goto L_0x064d;
    L_0x05b6:
        if (r1 == 0) goto L_0x064d;
    L_0x05b8:
        r0 = r0.zzaos;
        r1 = r1.zzaos;
        r3 = 8;
        r0.setPosition(r3);
        r3 = r0.readInt();
        r4 = r0.readInt();
        r5 = zzaps;
        if (r4 != r5) goto L_0x064d;
    L_0x05cd:
        r3 = com.google.android.gms.internal.ads.zziv.zzaf(r3);
        r4 = 1;
        if (r3 != r4) goto L_0x05d8;
    L_0x05d4:
        r3 = 4;
        r0.zzbl(r3);
    L_0x05d8:
        r0 = r0.readInt();
        if (r0 == r4) goto L_0x05e6;
    L_0x05de:
        r0 = new com.google.android.gms.internal.ads.zzfx;
        r1 = "Entry count in sbgp != 1 (unsupported).";
        r0.<init>(r1);
        throw r0;
    L_0x05e6:
        r0 = 8;
        r1.setPosition(r0);
        r0 = r1.readInt();
        r3 = r1.readInt();
        r5 = zzaps;
        if (r3 != r5) goto L_0x064b;
    L_0x05f7:
        r0 = com.google.android.gms.internal.ads.zziv.zzaf(r0);
        if (r0 != r4) goto L_0x0611;
    L_0x05fd:
        r3 = r1.zzhd();
        r5 = 0;
        r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));
        if (r0 != 0) goto L_0x060f;
    L_0x0607:
        r0 = new com.google.android.gms.internal.ads.zzfx;
        r1 = "Variable length decription in sgpd found (unsupported)";
        r0.<init>(r1);
        throw r0;
    L_0x060f:
        r0 = 4;
        goto L_0x0618;
    L_0x0611:
        r3 = 2;
        if (r0 < r3) goto L_0x060f;
    L_0x0614:
        r0 = 4;
        r1.zzbl(r0);
    L_0x0618:
        r3 = r1.zzhd();
        r5 = 1;
        r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));
        if (r7 == 0) goto L_0x062a;
    L_0x0622:
        r0 = new com.google.android.gms.internal.ads.zzfx;
        r1 = "Entry count in sgpd != 1 (unsupported).";
        r0.<init>(r1);
        throw r0;
    L_0x062a:
        r3 = 2;
        r1.zzbl(r3);
        r3 = r1.readUnsignedByte();
        r4 = 1;
        if (r3 != r4) goto L_0x064f;
    L_0x0635:
        r3 = r1.readUnsignedByte();
        r5 = 16;
        r6 = new byte[r5];
        r7 = 0;
        r1.zze(r6, r7, r5);
        r2.zzate = r4;
        r1 = new com.google.android.gms.internal.ads.zzjt;
        r1.<init>(r4, r3, r6);
        r2.zzatg = r1;
        goto L_0x064f;
    L_0x064b:
        r0 = 4;
        goto L_0x064f;
    L_0x064d:
        r0 = 4;
        r4 = 1;
    L_0x064f:
        r1 = r11.zzaoq;
        r1 = r1.size();
        r3 = 0;
    L_0x0656:
        if (r3 >= r1) goto L_0x068d;
    L_0x0658:
        r5 = r11.zzaoq;
        r5 = r5.get(r3);
        r5 = (com.google.android.gms.internal.ads.zzix) r5;
        r6 = r5.type;
        r7 = com.google.android.gms.internal.ads.zziv.zzang;
        if (r6 != r7) goto L_0x0681;
    L_0x0666:
        r5 = r5.zzaos;
        r6 = 8;
        r5.setPosition(r6);
        r7 = r27;
        r8 = 0;
        r9 = 16;
        r5.zze(r7, r8, r9);
        r10 = zzapt;
        r10 = java.util.Arrays.equals(r7, r10);
        if (r10 == 0) goto L_0x0688;
    L_0x067d:
        zza(r5, r9, r2);
        goto L_0x0688;
    L_0x0681:
        r7 = r27;
        r6 = 8;
        r8 = 0;
        r9 = 16;
    L_0x0688:
        r3 = r3 + 1;
        r27 = r7;
        goto L_0x0656;
    L_0x068d:
        r7 = r27;
        goto L_0x069b;
    L_0x0690:
        r29 = r1;
        r21 = r2;
        r44 = r5;
        r7 = r6;
        r30 = r10;
        r0 = 4;
        r4 = 1;
    L_0x069b:
        r6 = 8;
        goto L_0x06ac;
    L_0x069e:
        r29 = r1;
        r21 = r2;
        r44 = r5;
        r7 = r6;
        r20 = r8;
        r6 = r9;
        r30 = r10;
        r0 = 4;
        r4 = 1;
    L_0x06ac:
        r8 = 0;
        r10 = r30 + 1;
        r9 = r6;
        r6 = r7;
        r8 = r20;
        r2 = r21;
        r1 = r29;
        r5 = r44;
        r0 = r53;
        r7 = 0;
        goto L_0x01dc;
    L_0x06be:
        r8 = 0;
        r0 = r1.zzaoq;
        r0 = zzb(r0);
        if (r0 == 0) goto L_0x06e9;
    L_0x06c7:
        r2 = r53;
        r1 = r2.zzapv;
        r1 = r1.size();
    L_0x06cf:
        if (r8 >= r1) goto L_0x0700;
    L_0x06d1:
        r3 = r2.zzapv;
        r3 = r3.valueAt(r8);
        r3 = (com.google.android.gms.internal.ads.zzjl) r3;
        r4 = r3.zzakw;
        r3 = r3.zzaqu;
        r3 = r3.zzaad;
        r3 = r3.zza(r0);
        r4.zzf(r3);
        r8 = r8 + 1;
        goto L_0x06cf;
    L_0x06e9:
        r2 = r53;
        goto L_0x0700;
    L_0x06ec:
        r2 = r0;
        r0 = r2.zzaqc;
        r0 = r0.isEmpty();
        if (r0 != 0) goto L_0x0700;
    L_0x06f5:
        r0 = r2.zzaqc;
        r0 = r0.peek();
        r0 = (com.google.android.gms.internal.ads.zziw) r0;
        r0.zza(r1);
    L_0x0700:
        r0 = r2;
        goto L_0x0002;
    L_0x0703:
        r2 = r0;
        r53.zzei();
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzji.zzt(long):void");
    }

    private static void zza(zzpx zzpx, int i, zzju zzju) throws zzfx {
        zzpx.setPosition(i + 8);
        i = zziv.zzag(zzpx.readInt());
        if ((i & 1) != 0) {
            throw new zzfx("Overriding TrackEncryptionBox parameters is unsupported.");
        }
        boolean z = (i & 2) != 0;
        int zzhg = zzpx.zzhg();
        if (zzhg != zzju.zzapk) {
            i = zzju.zzapk;
            StringBuilder stringBuilder = new StringBuilder(41);
            stringBuilder.append("Length mismatch: ");
            stringBuilder.append(zzhg);
            stringBuilder.append(", ");
            stringBuilder.append(i);
            throw new zzfx(stringBuilder.toString());
        }
        Arrays.fill(zzju.zzatf, 0, zzhg, z);
        zzju.zzak(zzpx.zzhb());
        zzpx.zze(zzju.zzati.data, 0, zzju.zzath);
        zzju.zzati.setPosition(0);
        zzju.zzatj = false;
    }

    private static zzhp zzb(List<zzix> list) {
        int size = list.size();
        List list2 = null;
        for (int i = 0; i < size; i++) {
            zzix zzix = (zzix) list.get(i);
            if (zzix.type == zziv.zzamu) {
                if (list2 == null) {
                    list2 = new ArrayList();
                }
                byte[] bArr = zzix.zzaos.data;
                UUID zze = zzjq.zze(bArr);
                if (zze == null) {
                    Log.w("FragmentedMp4Extractor", "Skipped pssh atom (failed to extract uuid)");
                } else {
                    list2.add(new zza(zze, MimeTypes.VIDEO_MP4, bArr));
                }
            }
        }
        if (list2 == null) {
            return null;
        }
        return new zzhp(list2);
    }
}
