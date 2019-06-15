package com.google.android.gms.internal.ads;

import android.content.Context;
import android.text.TextUtils;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

@zzark
public final class zzapr extends zzaph {
    private final zzaba zzbln;
    private zzalg zzbma;
    private final zzbgg zzdin;
    private zzakr zzdmn;
    @VisibleForTesting
    private zzakp zzdsw;
    protected zzakx zzdsx;
    private boolean zzdsy;

    zzapr(Context context, zzaxg zzaxg, zzalg zzalg, zzapm zzapm, zzaba zzaba, zzbgg zzbgg) {
        super(context, zzaxg, zzapm);
        this.zzbma = zzalg;
        this.zzdmn = zzaxg.zzehj;
        this.zzbln = zzaba;
        this.zzdin = zzbgg;
    }

    /* Access modifiers changed, original: protected|final */
    public final zzaxf zzcr(int i) {
        String str;
        zzaso zzaso;
        zzakr zzakr;
        String str2;
        String str3;
        boolean z;
        long j;
        zzasi zzasi = this.zzdsk.zzeag;
        zzwb zzwb = zzasi.zzdwg;
        zzbgg zzbgg = this.zzdin;
        List list = this.zzdsl.zzdlq;
        List list2 = this.zzdsl.zzdlr;
        List list3 = this.zzdsl.zzdyf;
        int i2 = this.zzdsl.orientation;
        long j2 = this.zzdsl.zzdlx;
        String str4 = zzasi.zzdwj;
        boolean z2 = this.zzdsl.zzdyd;
        zzakq zzakq = this.zzdsx != null ? this.zzdsx.zzdnb : null;
        zzalj zzalj = this.zzdsx != null ? this.zzdsx.zzdnc : null;
        if (this.zzdsx != null) {
            str = this.zzdsx.zzdnd;
        } else {
            str = AdMobAdapter.class.getName();
        }
        String str5 = str;
        zzakr zzakr2 = this.zzdmn;
        zzakt zzakt = this.zzdsx != null ? this.zzdsx.zzdne : null;
        zzakq zzakq2 = zzakq;
        zzalj zzalj2 = zzalj;
        long j3 = this.zzdsl.zzdye;
        zzwf zzwf = this.zzdsk.zzbst;
        long j4 = j3;
        long j5 = this.zzdsl.zzdyc;
        long j6 = this.zzdsk.zzehn;
        j3 = this.zzdsl.zzdyh;
        String str6 = this.zzdsl.zzdyi;
        JSONObject jSONObject = this.zzdsk.zzehh;
        zzawd zzawd = this.zzdsl.zzdyr;
        List list4 = this.zzdsl.zzdys;
        List list5 = this.zzdsl.zzdyt;
        zzwf zzwf2 = zzwf;
        boolean z3 = this.zzdmn != null ? this.zzdmn.zzdmc : false;
        zzaso zzaso2 = this.zzdsl.zzdyv;
        if (this.zzdsw != null) {
            List zzui = this.zzdsw.zzui();
            zzaso = zzaso2;
            Object obj = "";
            if (zzui == null) {
                zzakr = zzakr2;
                str2 = obj.toString();
                str3 = str4;
                z = z2;
                j = j3;
            } else {
                Iterator it = zzui.iterator();
                while (it.hasNext()) {
                    Iterator it2 = it;
                    zzakx zzakx = (zzakx) it.next();
                    if (zzakx != null) {
                        j = j3;
                        if (zzakx.zzdnb == null || TextUtils.isEmpty(zzakx.zzdnb.zzdkx)) {
                            zzakr = zzakr2;
                            str3 = str4;
                            z = z2;
                        } else {
                            int i3;
                            String valueOf = String.valueOf(obj);
                            String str7 = zzakx.zzdnb.zzdkx;
                            switch (zzakx.zzdna) {
                                case -1:
                                    i3 = 4;
                                    break;
                                case 0:
                                    str3 = str4;
                                    z = z2;
                                    i3 = 0;
                                    break;
                                case 1:
                                    str3 = str4;
                                    z = z2;
                                    i3 = 1;
                                    break;
                                case 3:
                                    i3 = 2;
                                    break;
                                case 4:
                                    i3 = 3;
                                    break;
                                case 5:
                                    i3 = 5;
                                    break;
                                default:
                                    i3 = 6;
                                    break;
                            }
                            str3 = str4;
                            z = z2;
                            long j7 = zzakx.zzdng;
                            zzakr = zzakr2;
                            StringBuilder stringBuilder = new StringBuilder(33 + String.valueOf(str7).length());
                            stringBuilder.append(str7);
                            stringBuilder.append(".");
                            stringBuilder.append(i3);
                            stringBuilder.append(".");
                            stringBuilder.append(j7);
                            str = stringBuilder.toString();
                            StringBuilder stringBuilder2 = new StringBuilder((1 + String.valueOf(valueOf).length()) + String.valueOf(str).length());
                            stringBuilder2.append(valueOf);
                            stringBuilder2.append(str);
                            stringBuilder2.append("_");
                            obj = stringBuilder2.toString();
                        }
                    } else {
                        zzakr = zzakr2;
                        str3 = str4;
                        z = z2;
                        j = j3;
                    }
                    it = it2;
                    j3 = j;
                    str4 = str3;
                    z2 = z;
                    zzakr2 = zzakr;
                }
                zzakr = zzakr2;
                str3 = str4;
                z = z2;
                j = j3;
                str2 = obj.substring(0, Math.max(0, obj.length() - 1));
            }
        } else {
            zzakr = zzakr2;
            zzaso = zzaso2;
            str3 = str4;
            z = z2;
            j = j3;
            str2 = null;
        }
        return new zzaxf(zzwb, zzbgg, list, i, list2, list3, i2, j2, str3, z, zzakq2, zzalj2, str5, zzakr, zzakt, j4, zzwf2, j5, j6, j, str6, jSONObject, null, zzawd, list4, list5, z3, zzaso, str2, this.zzdsl.zzdlu, this.zzdsl.zzdyy, this.zzdsk.zzehw, this.zzdsl.zzbph, this.zzdsk.zzehx, this.zzdsl.zzdzc, this.zzdsl.zzdls, this.zzdsl.zzbpi, this.zzdsl.zzdzd, this.zzdsl.zzdzf);
    }

    /* Access modifiers changed, original: protected|final */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00de  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0104  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00fb  */
    public final void zzap(long r33) throws com.google.android.gms.internal.ads.zzapk {
        /*
        r32 = this;
        r1 = r32;
        r2 = r1.zzdsn;
        monitor-enter(r2);
        r3 = r1.zzdmn;	 Catch:{ all -> 0x0177 }
        r3 = r3.zzdma;	 Catch:{ all -> 0x0177 }
        r4 = -1;
        if (r3 == r4) goto L_0x0043;
    L_0x000c:
        r3 = new com.google.android.gms.internal.ads.zzala;	 Catch:{ all -> 0x0177 }
        r6 = r1.mContext;	 Catch:{ all -> 0x0177 }
        r4 = r1.zzdsk;	 Catch:{ all -> 0x0177 }
        r7 = r4.zzeag;	 Catch:{ all -> 0x0177 }
        r8 = r1.zzbma;	 Catch:{ all -> 0x0177 }
        r9 = r1.zzdmn;	 Catch:{ all -> 0x0177 }
        r4 = r1.zzdsl;	 Catch:{ all -> 0x0177 }
        r10 = r4.zzckn;	 Catch:{ all -> 0x0177 }
        r4 = r1.zzdsl;	 Catch:{ all -> 0x0177 }
        r11 = r4.zzckp;	 Catch:{ all -> 0x0177 }
        r4 = r1.zzdsl;	 Catch:{ all -> 0x0177 }
        r12 = r4.zzdyw;	 Catch:{ all -> 0x0177 }
        r4 = com.google.android.gms.internal.ads.zzaan.zzctf;	 Catch:{ all -> 0x0177 }
        r5 = com.google.android.gms.internal.ads.zzwu.zzpz();	 Catch:{ all -> 0x0177 }
        r4 = r5.zzd(r4);	 Catch:{ all -> 0x0177 }
        r4 = (java.lang.Long) r4;	 Catch:{ all -> 0x0177 }
        r15 = r4.longValue();	 Catch:{ all -> 0x0177 }
        r17 = 2;
        r4 = r1.zzdsk;	 Catch:{ all -> 0x0177 }
        r4 = r4.zzehx;	 Catch:{ all -> 0x0177 }
        r5 = r3;
        r13 = r33;
        r18 = r4;
        r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r15, r17, r18);	 Catch:{ all -> 0x0177 }
        goto L_0x008a;
    L_0x0043:
        r3 = new com.google.android.gms.internal.ads.zzald;	 Catch:{ all -> 0x0177 }
        r4 = r1.mContext;	 Catch:{ all -> 0x0177 }
        r5 = r1.zzdsk;	 Catch:{ all -> 0x0177 }
        r5 = r5.zzeag;	 Catch:{ all -> 0x0177 }
        r6 = r1.zzbma;	 Catch:{ all -> 0x0177 }
        r7 = r1.zzdmn;	 Catch:{ all -> 0x0177 }
        r8 = r1.zzdsl;	 Catch:{ all -> 0x0177 }
        r8 = r8.zzckn;	 Catch:{ all -> 0x0177 }
        r9 = r1.zzdsl;	 Catch:{ all -> 0x0177 }
        r9 = r9.zzckp;	 Catch:{ all -> 0x0177 }
        r10 = r1.zzdsl;	 Catch:{ all -> 0x0177 }
        r10 = r10.zzdyw;	 Catch:{ all -> 0x0177 }
        r11 = com.google.android.gms.internal.ads.zzaan.zzctf;	 Catch:{ all -> 0x0177 }
        r12 = com.google.android.gms.internal.ads.zzwu.zzpz();	 Catch:{ all -> 0x0177 }
        r11 = r12.zzd(r11);	 Catch:{ all -> 0x0177 }
        r11 = (java.lang.Long) r11;	 Catch:{ all -> 0x0177 }
        r28 = r11.longValue();	 Catch:{ all -> 0x0177 }
        r11 = r1.zzbln;	 Catch:{ all -> 0x0177 }
        r12 = r1.zzdsk;	 Catch:{ all -> 0x0177 }
        r12 = r12.zzehx;	 Catch:{ all -> 0x0177 }
        r18 = r3;
        r19 = r4;
        r20 = r5;
        r21 = r6;
        r22 = r7;
        r23 = r8;
        r24 = r9;
        r25 = r10;
        r26 = r33;
        r30 = r11;
        r31 = r12;
        r18.<init>(r19, r20, r21, r22, r23, r24, r25, r26, r28, r30, r31);	 Catch:{ all -> 0x0177 }
    L_0x008a:
        r1.zzdsw = r3;	 Catch:{ all -> 0x0177 }
        monitor-exit(r2);	 Catch:{ all -> 0x0177 }
        r2 = new java.util.ArrayList;
        r3 = r1.zzdmn;
        r3 = r3.zzdlp;
        r2.<init>(r3);
        r3 = r1.zzdsk;
        r3 = r3.zzeag;
        r3 = r3.zzdwg;
        r3 = r3.zzcjl;
        r4 = "com.google.ads.mediation.admob.AdMobAdapter";
        r5 = 0;
        if (r3 == 0) goto L_0x00b0;
    L_0x00a3:
        r3 = r3.getBundle(r4);
        if (r3 == 0) goto L_0x00b0;
    L_0x00a9:
        r6 = "_skipMediation";
        r3 = r3.getBoolean(r6);
        goto L_0x00b1;
    L_0x00b0:
        r3 = r5;
    L_0x00b1:
        if (r3 == 0) goto L_0x00cf;
    L_0x00b3:
        r3 = r2.listIterator();
    L_0x00b7:
        r6 = r3.hasNext();
        if (r6 == 0) goto L_0x00cf;
    L_0x00bd:
        r6 = r3.next();
        r6 = (com.google.android.gms.internal.ads.zzakq) r6;
        r6 = r6.zzdkw;
        r6 = r6.contains(r4);
        if (r6 != 0) goto L_0x00b7;
    L_0x00cb:
        r3.remove();
        goto L_0x00b7;
    L_0x00cf:
        r3 = r1.zzdsw;
        r2 = r3.zzh(r2);
        r1.zzdsx = r2;
        r2 = r1.zzdsx;
        r2 = r2.zzdna;
        switch(r2) {
            case 0: goto L_0x0104;
            case 1: goto L_0x00fb;
            default: goto L_0x00de;
        };
    L_0x00de:
        r2 = new com.google.android.gms.internal.ads.zzapk;
        r3 = r1.zzdsx;
        r3 = r3.zzdna;
        r4 = 40;
        r6 = new java.lang.StringBuilder;
        r6.<init>(r4);
        r4 = "Unexpected mediation result: ";
        r6.append(r4);
        r6.append(r3);
        r3 = r6.toString();
        r2.<init>(r3, r5);
        throw r2;
    L_0x00fb:
        r2 = new com.google.android.gms.internal.ads.zzapk;
        r3 = "No fill from any mediation ad networks.";
        r4 = 3;
        r2.<init>(r3, r4);
        throw r2;
    L_0x0104:
        r2 = r1.zzdsx;
        r2 = r2.zzdnb;
        if (r2 == 0) goto L_0x0176;
    L_0x010a:
        r2 = r1.zzdsx;
        r2 = r2.zzdnb;
        r2 = r2.zzdli;
        if (r2 == 0) goto L_0x0176;
    L_0x0112:
        r2 = new java.util.concurrent.CountDownLatch;
        r3 = 1;
        r2.<init>(r3);
        r3 = com.google.android.gms.internal.ads.zzayh.zzelc;
        r4 = new com.google.android.gms.internal.ads.zzaps;
        r4.<init>(r1, r2);
        r3.post(r4);
        r3 = 10;
        r6 = java.util.concurrent.TimeUnit.SECONDS;	 Catch:{ InterruptedException -> 0x014e }
        r2.await(r3, r6);	 Catch:{ InterruptedException -> 0x014e }
        r2 = r1.zzdsn;
        monitor-enter(r2);
        r3 = r1.zzdsy;	 Catch:{ all -> 0x014a }
        if (r3 != 0) goto L_0x0138;
    L_0x0130:
        r3 = new com.google.android.gms.internal.ads.zzapk;	 Catch:{ all -> 0x014a }
        r4 = "View could not be prepared";
        r3.<init>(r4, r5);	 Catch:{ all -> 0x014a }
        throw r3;	 Catch:{ all -> 0x014a }
    L_0x0138:
        r3 = r1.zzdin;	 Catch:{ all -> 0x014a }
        r3 = r3.isDestroyed();	 Catch:{ all -> 0x014a }
        if (r3 == 0) goto L_0x0148;
    L_0x0140:
        r3 = new com.google.android.gms.internal.ads.zzapk;	 Catch:{ all -> 0x014a }
        r4 = "Assets not loaded, web view is destroyed";
        r3.<init>(r4, r5);	 Catch:{ all -> 0x014a }
        throw r3;	 Catch:{ all -> 0x014a }
    L_0x0148:
        monitor-exit(r2);	 Catch:{ all -> 0x014a }
        return;
    L_0x014a:
        r0 = move-exception;
        r3 = r0;
        monitor-exit(r2);	 Catch:{ all -> 0x014a }
        throw r3;
    L_0x014e:
        r0 = move-exception;
        r2 = r0;
        r3 = new com.google.android.gms.internal.ads.zzapk;
        r2 = java.lang.String.valueOf(r2);
        r4 = 38;
        r6 = java.lang.String.valueOf(r2);
        r6 = r6.length();
        r4 = r4 + r6;
        r6 = new java.lang.StringBuilder;
        r6.<init>(r4);
        r4 = "Interrupted while waiting for latch : ";
        r6.append(r4);
        r6.append(r2);
        r2 = r6.toString();
        r3.<init>(r2, r5);
        throw r3;
    L_0x0176:
        return;
    L_0x0177:
        r0 = move-exception;
        r3 = r0;
        monitor-exit(r2);	 Catch:{ all -> 0x0177 }
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzapr.zzap(long):void");
    }

    public final void onStop() {
        synchronized (this.zzdsn) {
            super.onStop();
            if (this.zzdsw != null) {
                this.zzdsw.cancel();
            }
        }
    }
}
