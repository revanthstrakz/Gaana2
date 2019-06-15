package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public final class zzjn implements zzhz, zzig {
    private static final zzic zzahq = new zzjo();
    private static final int zzasf = zzqe.zzam("qt  ");
    private long zzaan;
    private final zzpx zzahx = new zzpx(zzpu.zzbhi);
    private final zzpx zzahy = new zzpx(4);
    private int zzajm;
    private int zzajn;
    private zzib zzajq;
    private final zzpx zzaqa = new zzpx(16);
    private final Stack<zziw> zzaqc = new Stack();
    private int zzaqe;
    private int zzaqf;
    private long zzaqg;
    private int zzaqh;
    private zzpx zzaqi;
    private zzjp[] zzasg;
    private boolean zzash;

    public final void release() {
    }

    public final boolean zzdw() {
        return true;
    }

    public final boolean zza(zzia zzia) throws IOException, InterruptedException {
        return zzjr.zze(zzia);
    }

    public final void zza(zzib zzib) {
        this.zzajq = zzib;
    }

    public final void zzc(long j, long j2) {
        this.zzaqc.clear();
        int i = 0;
        this.zzaqh = 0;
        this.zzajn = 0;
        this.zzajm = 0;
        if (j == 0) {
            zzei();
            return;
        }
        if (this.zzasg != null) {
            zzjp[] zzjpArr = this.zzasg;
            int length = zzjpArr.length;
            while (i < length) {
                zzjp zzjp = zzjpArr[i];
                zzjv zzjv = zzjp.zzasi;
                int zzu = zzjv.zzu(j2);
                if (zzu == -1) {
                    zzu = zzjv.zzv(j2);
                }
                zzjp.zzapm = zzu;
                i++;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:152:0x0006 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x02bb A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x0006 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x01a4 A:{SYNTHETIC} */
    public final int zza(com.google.android.gms.internal.ads.zzia r25, com.google.android.gms.internal.ads.zzif r26) throws java.io.IOException, java.lang.InterruptedException {
        /*
        r24 = this;
        r0 = r24;
        r1 = r25;
        r2 = r26;
    L_0x0006:
        r3 = r0.zzaqe;
        r4 = 2;
        r5 = 262144; // 0x40000 float:3.67342E-40 double:1.295163E-318;
        r8 = -1;
        r9 = 8;
        r10 = 1;
        switch(r3) {
            case 0: goto L_0x01a6;
            case 1: goto L_0x011d;
            case 2: goto L_0x0019;
            default: goto L_0x0013;
        };
    L_0x0013:
        r1 = new java.lang.IllegalStateException;
        r1.<init>();
        throw r1;
    L_0x0019:
        r12 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
        r9 = r8;
        r3 = 0;
    L_0x0020:
        r14 = r0.zzasg;
        r14 = r14.length;
        if (r3 >= r14) goto L_0x0040;
    L_0x0025:
        r14 = r0.zzasg;
        r14 = r14[r3];
        r15 = r14.zzapm;
        r7 = r14.zzasi;
        r7 = r7.zzapk;
        if (r15 == r7) goto L_0x003d;
    L_0x0031:
        r7 = r14.zzasi;
        r7 = r7.zzagu;
        r14 = r7[r15];
        r7 = (r14 > r12 ? 1 : (r14 == r12 ? 0 : -1));
        if (r7 >= 0) goto L_0x003d;
    L_0x003b:
        r9 = r3;
        r12 = r14;
    L_0x003d:
        r3 = r3 + 1;
        goto L_0x0020;
    L_0x0040:
        if (r9 != r8) goto L_0x0043;
    L_0x0042:
        return r8;
    L_0x0043:
        r3 = r0.zzasg;
        r3 = r3[r9];
        r7 = r3.zzasj;
        r8 = r3.zzapm;
        r9 = r3.zzasi;
        r9 = r9.zzagu;
        r12 = r9[r8];
        r9 = r3.zzasi;
        r9 = r9.zzagt;
        r9 = r9[r8];
        r14 = r3.zzaqu;
        r14 = r14.zzasm;
        if (r14 != r10) goto L_0x0065;
    L_0x005d:
        r14 = 8;
        r16 = r12 + r14;
        r9 = r9 + -8;
        r12 = r16;
    L_0x0065:
        r14 = r25.getPosition();
        r16 = r12 - r14;
        r14 = r0.zzajn;
        r14 = (long) r14;
        r10 = r16 + r14;
        r14 = 0;
        r16 = (r10 > r14 ? 1 : (r10 == r14 ? 0 : -1));
        if (r16 < 0) goto L_0x0119;
    L_0x0076:
        r14 = (r10 > r5 ? 1 : (r10 == r5 ? 0 : -1));
        if (r14 < 0) goto L_0x007c;
    L_0x007a:
        goto L_0x0119;
    L_0x007c:
        r2 = (int) r10;
        r1.zzw(r2);
        r2 = r3.zzaqu;
        r2 = r2.zzakx;
        if (r2 == 0) goto L_0x00de;
    L_0x0086:
        r2 = r0.zzahy;
        r2 = r2.data;
        r5 = 0;
        r2[r5] = r5;
        r6 = 1;
        r2[r6] = r5;
        r2[r4] = r5;
        r2 = r3.zzaqu;
        r2 = r2.zzakx;
        r4 = r3.zzaqu;
        r4 = r4.zzakx;
        r5 = 4;
        r4 = 4 - r4;
    L_0x009d:
        r5 = r0.zzajn;
        if (r5 >= r9) goto L_0x00f6;
    L_0x00a1:
        r5 = r0.zzajm;
        if (r5 != 0) goto L_0x00cc;
    L_0x00a5:
        r5 = r0.zzahy;
        r5 = r5.data;
        r1.readFully(r5, r4, r2);
        r5 = r0.zzahy;
        r6 = 0;
        r5.setPosition(r6);
        r5 = r0.zzahy;
        r5 = r5.zzhg();
        r0.zzajm = r5;
        r5 = r0.zzahx;
        r5.setPosition(r6);
        r5 = r0.zzahx;
        r10 = 4;
        r7.zza(r5, r10);
        r5 = r0.zzajn;
        r5 = r5 + r10;
        r0.zzajn = r5;
        r9 = r9 + r4;
        goto L_0x009d;
    L_0x00cc:
        r6 = 0;
        r5 = r0.zzajm;
        r5 = r7.zza(r1, r5, r6);
        r6 = r0.zzajn;
        r6 = r6 + r5;
        r0.zzajn = r6;
        r6 = r0.zzajm;
        r6 = r6 - r5;
        r0.zzajm = r6;
        goto L_0x009d;
    L_0x00de:
        r2 = r0.zzajn;
        if (r2 >= r9) goto L_0x00f6;
    L_0x00e2:
        r2 = r0.zzajn;
        r2 = r9 - r2;
        r4 = 0;
        r2 = r7.zza(r1, r2, r4);
        r4 = r0.zzajn;
        r4 = r4 + r2;
        r0.zzajn = r4;
        r4 = r0.zzajm;
        r4 = r4 - r2;
        r0.zzajm = r4;
        goto L_0x00de;
    L_0x00f6:
        r20 = r9;
        r1 = r3.zzasi;
        r1 = r1.zzatl;
        r17 = r1[r8];
        r1 = r3.zzasi;
        r1 = r1.zzapr;
        r19 = r1[r8];
        r21 = 0;
        r22 = 0;
        r16 = r7;
        r16.zza(r17, r19, r20, r21, r22);
        r1 = r3.zzapm;
        r4 = 1;
        r1 = r1 + r4;
        r3.zzapm = r1;
        r1 = 0;
        r0.zzajn = r1;
        r0.zzajm = r1;
        return r1;
    L_0x0119:
        r4 = 1;
        r2.zzaha = r12;
        return r4;
    L_0x011d:
        r7 = r0.zzaqg;
        r3 = r0.zzaqh;
        r10 = (long) r3;
        r12 = r7 - r10;
        r7 = r25.getPosition();
        r10 = r7 + r12;
        r3 = r0.zzaqi;
        if (r3 == 0) goto L_0x0181;
    L_0x012e:
        r3 = r0.zzaqi;
        r3 = r3.data;
        r5 = r0.zzaqh;
        r6 = (int) r12;
        r1.readFully(r3, r5, r6);
        r3 = r0.zzaqf;
        r5 = com.google.android.gms.internal.ads.zziv.zzala;
        if (r3 != r5) goto L_0x0164;
    L_0x013e:
        r3 = r0.zzaqi;
        r3.setPosition(r9);
        r5 = r3.readInt();
        r6 = zzasf;
        if (r5 != r6) goto L_0x014d;
    L_0x014b:
        r3 = 1;
        goto L_0x0161;
    L_0x014d:
        r5 = 4;
        r3.zzbl(r5);
    L_0x0151:
        r5 = r3.zzhb();
        if (r5 <= 0) goto L_0x0160;
    L_0x0157:
        r5 = r3.readInt();
        r6 = zzasf;
        if (r5 != r6) goto L_0x0151;
    L_0x015f:
        goto L_0x014b;
    L_0x0160:
        r3 = 0;
    L_0x0161:
        r0.zzash = r3;
        goto L_0x0189;
    L_0x0164:
        r3 = r0.zzaqc;
        r3 = r3.isEmpty();
        if (r3 != 0) goto L_0x0189;
    L_0x016c:
        r3 = r0.zzaqc;
        r3 = r3.peek();
        r3 = (com.google.android.gms.internal.ads.zziw) r3;
        r5 = new com.google.android.gms.internal.ads.zzix;
        r6 = r0.zzaqf;
        r7 = r0.zzaqi;
        r5.<init>(r6, r7);
        r3.zza(r5);
        goto L_0x0189;
    L_0x0181:
        r3 = (r12 > r5 ? 1 : (r12 == r5 ? 0 : -1));
        if (r3 >= 0) goto L_0x018b;
    L_0x0185:
        r3 = (int) r12;
        r1.zzw(r3);
    L_0x0189:
        r3 = 0;
        goto L_0x0194;
    L_0x018b:
        r5 = r25.getPosition();
        r7 = r5 + r12;
        r2.zzaha = r7;
        r3 = 1;
    L_0x0194:
        r0.zzt(r10);
        if (r3 == 0) goto L_0x01a0;
    L_0x0199:
        r3 = r0.zzaqe;
        if (r3 == r4) goto L_0x01a0;
    L_0x019d:
        r23 = 1;
        goto L_0x01a2;
    L_0x01a0:
        r23 = 0;
    L_0x01a2:
        if (r23 == 0) goto L_0x0006;
    L_0x01a4:
        r3 = 1;
        return r3;
    L_0x01a6:
        r3 = r10;
        r4 = r0.zzaqh;
        if (r4 != 0) goto L_0x01d0;
    L_0x01ab:
        r4 = r0.zzaqa;
        r4 = r4.data;
        r5 = 0;
        r4 = r1.zza(r4, r5, r9, r3);
        if (r4 != 0) goto L_0x01b9;
    L_0x01b6:
        r3 = r5;
        goto L_0x02b9;
    L_0x01b9:
        r0.zzaqh = r9;
        r3 = r0.zzaqa;
        r3.setPosition(r5);
        r3 = r0.zzaqa;
        r3 = r3.zzhd();
        r0.zzaqg = r3;
        r3 = r0.zzaqa;
        r3 = r3.readInt();
        r0.zzaqf = r3;
    L_0x01d0:
        r3 = r0.zzaqg;
        r5 = 1;
        r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));
        if (r7 != 0) goto L_0x01ec;
    L_0x01d8:
        r3 = r0.zzaqa;
        r3 = r3.data;
        r1.readFully(r3, r9, r9);
        r3 = r0.zzaqh;
        r3 = r3 + r9;
        r0.zzaqh = r3;
        r3 = r0.zzaqa;
        r3 = r3.zzhh();
        r0.zzaqg = r3;
    L_0x01ec:
        r3 = r0.zzaqf;
        r4 = com.google.android.gms.internal.ads.zziv.zzamb;
        if (r3 == r4) goto L_0x0209;
    L_0x01f2:
        r4 = com.google.android.gms.internal.ads.zziv.zzamd;
        if (r3 == r4) goto L_0x0209;
    L_0x01f6:
        r4 = com.google.android.gms.internal.ads.zziv.zzame;
        if (r3 == r4) goto L_0x0209;
    L_0x01fa:
        r4 = com.google.android.gms.internal.ads.zziv.zzamf;
        if (r3 == r4) goto L_0x0209;
    L_0x01fe:
        r4 = com.google.android.gms.internal.ads.zziv.zzamg;
        if (r3 == r4) goto L_0x0209;
    L_0x0202:
        r4 = com.google.android.gms.internal.ads.zziv.zzamp;
        if (r3 != r4) goto L_0x0207;
    L_0x0206:
        goto L_0x0209;
    L_0x0207:
        r3 = 0;
        goto L_0x020a;
    L_0x0209:
        r3 = 1;
    L_0x020a:
        if (r3 == 0) goto L_0x0238;
    L_0x020c:
        r3 = r25.getPosition();
        r5 = r0.zzaqg;
        r9 = r3 + r5;
        r3 = r0.zzaqh;
        r3 = (long) r3;
        r5 = r9 - r3;
        r3 = r0.zzaqc;
        r4 = new com.google.android.gms.internal.ads.zziw;
        r7 = r0.zzaqf;
        r4.<init>(r7, r5);
        r3.add(r4);
        r3 = r0.zzaqg;
        r7 = r0.zzaqh;
        r9 = (long) r7;
        r7 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1));
        if (r7 != 0) goto L_0x0234;
    L_0x022e:
        r0.zzt(r5);
    L_0x0231:
        r3 = 1;
        goto L_0x02b9;
    L_0x0234:
        r24.zzei();
        goto L_0x0231;
    L_0x0238:
        r3 = r0.zzaqf;
        r4 = com.google.android.gms.internal.ads.zziv.zzamr;
        if (r3 == r4) goto L_0x027d;
    L_0x023e:
        r4 = com.google.android.gms.internal.ads.zziv.zzamc;
        if (r3 == r4) goto L_0x027d;
    L_0x0242:
        r4 = com.google.android.gms.internal.ads.zziv.zzams;
        if (r3 == r4) goto L_0x027d;
    L_0x0246:
        r4 = com.google.android.gms.internal.ads.zziv.zzamt;
        if (r3 == r4) goto L_0x027d;
    L_0x024a:
        r4 = com.google.android.gms.internal.ads.zziv.zzanm;
        if (r3 == r4) goto L_0x027d;
    L_0x024e:
        r4 = com.google.android.gms.internal.ads.zziv.zzann;
        if (r3 == r4) goto L_0x027d;
    L_0x0252:
        r4 = com.google.android.gms.internal.ads.zziv.zzano;
        if (r3 == r4) goto L_0x027d;
    L_0x0256:
        r4 = com.google.android.gms.internal.ads.zziv.zzamq;
        if (r3 == r4) goto L_0x027d;
    L_0x025a:
        r4 = com.google.android.gms.internal.ads.zziv.zzanp;
        if (r3 == r4) goto L_0x027d;
    L_0x025e:
        r4 = com.google.android.gms.internal.ads.zziv.zzanq;
        if (r3 == r4) goto L_0x027d;
    L_0x0262:
        r4 = com.google.android.gms.internal.ads.zziv.zzanr;
        if (r3 == r4) goto L_0x027d;
    L_0x0266:
        r4 = com.google.android.gms.internal.ads.zziv.zzans;
        if (r3 == r4) goto L_0x027d;
    L_0x026a:
        r4 = com.google.android.gms.internal.ads.zziv.zzant;
        if (r3 == r4) goto L_0x027d;
    L_0x026e:
        r4 = com.google.android.gms.internal.ads.zziv.zzamo;
        if (r3 == r4) goto L_0x027d;
    L_0x0272:
        r4 = com.google.android.gms.internal.ads.zziv.zzala;
        if (r3 == r4) goto L_0x027d;
    L_0x0276:
        r4 = com.google.android.gms.internal.ads.zziv.zzaoa;
        if (r3 != r4) goto L_0x027b;
    L_0x027a:
        goto L_0x027d;
    L_0x027b:
        r3 = 0;
        goto L_0x027e;
    L_0x027d:
        r3 = 1;
    L_0x027e:
        if (r3 == 0) goto L_0x02b3;
    L_0x0280:
        r3 = r0.zzaqh;
        if (r3 != r9) goto L_0x0286;
    L_0x0284:
        r3 = 1;
        goto L_0x0287;
    L_0x0286:
        r3 = 0;
    L_0x0287:
        com.google.android.gms.internal.ads.zzpo.checkState(r3);
        r3 = r0.zzaqg;
        r5 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));
        if (r7 > 0) goto L_0x0295;
    L_0x0293:
        r3 = 1;
        goto L_0x0296;
    L_0x0295:
        r3 = 0;
    L_0x0296:
        com.google.android.gms.internal.ads.zzpo.checkState(r3);
        r3 = new com.google.android.gms.internal.ads.zzpx;
        r4 = r0.zzaqg;
        r4 = (int) r4;
        r3.<init>(r4);
        r0.zzaqi = r3;
        r3 = r0.zzaqa;
        r3 = r3.data;
        r4 = r0.zzaqi;
        r4 = r4.data;
        r5 = 0;
        java.lang.System.arraycopy(r3, r5, r4, r5, r9);
        r3 = 1;
        r0.zzaqe = r3;
        goto L_0x02b9;
    L_0x02b3:
        r3 = 1;
        r4 = 0;
        r0.zzaqi = r4;
        r0.zzaqe = r3;
    L_0x02b9:
        if (r3 != 0) goto L_0x0006;
    L_0x02bb:
        return r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzjn.zza(com.google.android.gms.internal.ads.zzia, com.google.android.gms.internal.ads.zzif):int");
    }

    public final long getDurationUs() {
        return this.zzaan;
    }

    public final long zzr(long j) {
        long j2 = Long.MAX_VALUE;
        for (zzjp zzjp : this.zzasg) {
            zzjv zzjv = zzjp.zzasi;
            int zzu = zzjv.zzu(j);
            if (zzu == -1) {
                zzu = zzjv.zzv(j);
            }
            long j3 = zzjv.zzagu[zzu];
            if (j3 < j2) {
                j2 = j3;
            }
        }
        return j2;
    }

    private final void zzei() {
        this.zzaqe = 0;
        this.zzaqh = 0;
    }

    private final void zzt(long j) throws zzfx {
        while (!this.zzaqc.isEmpty() && ((zziw) this.zzaqc.peek()).zzaop == j) {
            zziw zziw = (zziw) this.zzaqc.pop();
            if (zziw.type == zziv.zzamb) {
                long j2 = C.TIME_UNSET;
                ArrayList arrayList = new ArrayList();
                zzki zzki = null;
                zzid zzid = new zzid();
                zzix zzai = zziw.zzai(zziv.zzaoa);
                if (zzai != null) {
                    zzki = zziy.zza(zzai, this.zzash);
                    if (zzki != null) {
                        zzid.zzb(zzki);
                    }
                }
                for (int i = 0; i < zziw.zzaor.size(); i++) {
                    zziw zziw2 = (zziw) zziw.zzaor.get(i);
                    if (zziw2.type == zziv.zzamd) {
                        zzjs zza = zziy.zza(zziw2, zziw.zzai(zziv.zzamc), (long) C.TIME_UNSET, null, this.zzash);
                        if (zza != null) {
                            zzjv zza2 = zziy.zza(zza, zziw2.zzaj(zziv.zzame).zzaj(zziv.zzamf).zzaj(zziv.zzamg), zzid);
                            if (zza2.zzapk != 0) {
                                zzjp zzjp = new zzjp(zza, zza2, this.zzajq.zzb(i, zza.type));
                                zzfs zzj = zza.zzaad.zzj(zza2.zzapp + 30);
                                if (zza.type == 1) {
                                    if (zzid.zzea()) {
                                        zzj = zzj.zza(zzid.zzzw, zzid.zzzx);
                                    }
                                    if (zzki != null) {
                                        zzj = zzj.zza(zzki);
                                    }
                                }
                                zzjp.zzasj.zzf(zzj);
                                j2 = Math.max(j2, zza.zzaan);
                                arrayList.add(zzjp);
                            }
                        }
                    }
                }
                this.zzaan = j2;
                this.zzasg = (zzjp[]) arrayList.toArray(new zzjp[arrayList.size()]);
                this.zzajq.zzdy();
                this.zzajq.zza(this);
                this.zzaqc.clear();
                this.zzaqe = 2;
            } else if (!this.zzaqc.isEmpty()) {
                ((zziw) this.zzaqc.peek()).zza(zziw);
            }
        }
        if (this.zzaqe != 2) {
            zzei();
        }
    }
}
