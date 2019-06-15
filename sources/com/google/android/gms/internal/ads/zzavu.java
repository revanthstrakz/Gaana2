package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

@zzark
public final class zzavu extends zzaxv implements zzavt {
    private final Context mContext;
    private final Object mLock;
    private final zzaxg zzdsk;
    private final long zzeet;
    private final ArrayList<zzavk> zzefh;
    private final List<zzavn> zzefi;
    private final HashSet<String> zzefj;
    private final zzauk zzefk;

    public zzavu(Context context, zzaxg zzaxg, zzauk zzauk) {
        Context context2 = context;
        zzaxg zzaxg2 = zzaxg;
        zzauk zzauk2 = zzauk;
        this(context2, zzaxg2, zzauk2, ((Long) zzwu.zzpz().zzd(zzaan.zzcrj)).longValue());
    }

    public final void onStop() {
    }

    public final void zza(String str, int i) {
    }

    @VisibleForTesting
    private zzavu(Context context, zzaxg zzaxg, zzauk zzauk, long j) {
        this.zzefh = new ArrayList();
        this.zzefi = new ArrayList();
        this.zzefj = new HashSet();
        this.mLock = new Object();
        this.mContext = context;
        this.zzdsk = zzaxg;
        this.zzefk = zzauk;
        this.zzeet = j;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:95:0x016f */
    public final void zzki() {
        /*
        r20 = this;
        r11 = r20;
        r1 = r11.zzdsk;
        r1 = r1.zzehj;
        r1 = r1.zzdlp;
        r12 = r1.iterator();
    L_0x000c:
        r1 = r12.hasNext();
        if (r1 == 0) goto L_0x00ca;
    L_0x0012:
        r1 = r12.next();
        r13 = r1;
        r13 = (com.google.android.gms.internal.ads.zzakq) r13;
        r14 = r13.zzdle;
        r1 = r13.zzdkw;
        r15 = r1.iterator();
    L_0x0021:
        r1 = r15.hasNext();
        if (r1 == 0) goto L_0x000c;
    L_0x0027:
        r1 = r15.next();
        r1 = (java.lang.String) r1;
        r2 = "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter";
        r2 = r2.equals(r1);
        if (r2 != 0) goto L_0x0040;
    L_0x0035:
        r2 = "com.google.ads.mediation.customevent.CustomEventAdapter";
        r2 = r2.equals(r1);
        if (r2 == 0) goto L_0x003e;
    L_0x003d:
        goto L_0x0040;
    L_0x003e:
        r3 = r1;
        goto L_0x004c;
    L_0x0040:
        r1 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00bd }
        r1.<init>(r14);	 Catch:{ JSONException -> 0x00bd }
        r2 = "class_name";
        r1 = r1.getString(r2);	 Catch:{ JSONException -> 0x00bd }
        goto L_0x003e;
    L_0x004c:
        r9 = r11.mLock;
        monitor-enter(r9);
        r1 = r11.zzefk;	 Catch:{ all -> 0x00b5 }
        r7 = r1.zzdd(r3);	 Catch:{ all -> 0x00b5 }
        if (r7 == 0) goto L_0x008c;
    L_0x0057:
        r1 = r7.zzxo();	 Catch:{ all -> 0x00b5 }
        if (r1 == 0) goto L_0x008c;
    L_0x005d:
        r1 = r7.zzxn();	 Catch:{ all -> 0x00b5 }
        if (r1 != 0) goto L_0x0064;
    L_0x0063:
        goto L_0x008c;
    L_0x0064:
        r10 = new com.google.android.gms.internal.ads.zzavk;	 Catch:{ all -> 0x00b5 }
        r2 = r11.mContext;	 Catch:{ all -> 0x00b5 }
        r6 = r11.zzdsk;	 Catch:{ all -> 0x00b5 }
        r4 = r11.zzeet;	 Catch:{ all -> 0x00b5 }
        r1 = r10;
        r16 = r4;
        r4 = r14;
        r5 = r13;
        r8 = r11;
        r18 = r9;
        r19 = r12;
        r12 = r10;
        r9 = r16;
        r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9);	 Catch:{ all -> 0x00bb }
        r1 = r11.zzefk;	 Catch:{ all -> 0x00bb }
        r1 = r1.zzxb();	 Catch:{ all -> 0x00bb }
        r12.zza(r1);	 Catch:{ all -> 0x00bb }
        r1 = r11.zzefh;	 Catch:{ all -> 0x00bb }
        r1.add(r12);	 Catch:{ all -> 0x00bb }
        monitor-exit(r18);	 Catch:{ all -> 0x00bb }
        goto L_0x00c6;
    L_0x008c:
        r18 = r9;
        r19 = r12;
        r1 = r11.zzefi;	 Catch:{ all -> 0x00bb }
        r2 = new com.google.android.gms.internal.ads.zzavp;	 Catch:{ all -> 0x00bb }
        r2.<init>();	 Catch:{ all -> 0x00bb }
        r4 = r13.zzdkx;	 Catch:{ all -> 0x00bb }
        r2 = r2.zzdg(r4);	 Catch:{ all -> 0x00bb }
        r2 = r2.zzdf(r3);	 Catch:{ all -> 0x00bb }
        r3 = 0;
        r2 = r2.zzar(r3);	 Catch:{ all -> 0x00bb }
        r3 = 7;
        r2 = r2.zzcu(r3);	 Catch:{ all -> 0x00bb }
        r2 = r2.zzxm();	 Catch:{ all -> 0x00bb }
        r1.add(r2);	 Catch:{ all -> 0x00bb }
        monitor-exit(r18);	 Catch:{ all -> 0x00bb }
        goto L_0x00c6;
    L_0x00b5:
        r0 = move-exception;
        r18 = r9;
    L_0x00b8:
        r1 = r0;
        monitor-exit(r18);	 Catch:{ all -> 0x00bb }
        throw r1;
    L_0x00bb:
        r0 = move-exception;
        goto L_0x00b8;
    L_0x00bd:
        r0 = move-exception;
        r19 = r12;
        r1 = r0;
        r2 = "Unable to determine custom event class name, skipping...";
        com.google.android.gms.internal.ads.zzbbd.zzb(r2, r1);
    L_0x00c6:
        r12 = r19;
        goto L_0x0021;
    L_0x00ca:
        r1 = new java.util.HashSet;
        r1.<init>();
        r2 = r11.zzefh;
        r2 = (java.util.ArrayList) r2;
        r3 = r2.size();
        r4 = 0;
        r5 = r4;
    L_0x00d9:
        if (r5 >= r3) goto L_0x00ef;
    L_0x00db:
        r6 = r2.get(r5);
        r5 = r5 + 1;
        r6 = (com.google.android.gms.internal.ads.zzavk) r6;
        r7 = r6.zzdml;
        r7 = r1.add(r7);
        if (r7 == 0) goto L_0x00d9;
    L_0x00eb:
        r6.zzxi();
        goto L_0x00d9;
    L_0x00ef:
        r1 = r11.zzefh;
        r1 = (java.util.ArrayList) r1;
        r2 = r1.size();
    L_0x00f7:
        if (r4 >= r2) goto L_0x01aa;
    L_0x00f9:
        r3 = r1.get(r4);
        r4 = r4 + 1;
        r3 = (com.google.android.gms.internal.ads.zzavk) r3;
        r5 = r3.zzxi();	 Catch:{ InterruptedException -> 0x016f, Exception -> 0x014e }
        r5.get();	 Catch:{ InterruptedException -> 0x016f, Exception -> 0x014e }
        r5 = r11.mLock;
        monitor-enter(r5);
        r6 = r3.zzdml;	 Catch:{ all -> 0x0147 }
        r6 = android.text.TextUtils.isEmpty(r6);	 Catch:{ all -> 0x0147 }
        if (r6 != 0) goto L_0x011c;
    L_0x0113:
        r6 = r11.zzefi;	 Catch:{ all -> 0x0147 }
        r7 = r3.zzxj();	 Catch:{ all -> 0x0147 }
        r6.add(r7);	 Catch:{ all -> 0x0147 }
    L_0x011c:
        monitor-exit(r5);	 Catch:{ all -> 0x0147 }
        r6 = r11.mLock;
        monitor-enter(r6);
        r5 = r11.zzefj;	 Catch:{ all -> 0x0143 }
        r7 = r3.zzdml;	 Catch:{ all -> 0x0143 }
        r5 = r5.contains(r7);	 Catch:{ all -> 0x0143 }
        if (r5 == 0) goto L_0x0141;
    L_0x012a:
        r1 = r3.zzdml;	 Catch:{ all -> 0x0143 }
        r2 = r3.zzxk();	 Catch:{ all -> 0x0143 }
        r3 = -2;
        r1 = r11.zza(r3, r1, r2);	 Catch:{ all -> 0x0143 }
        r2 = com.google.android.gms.internal.ads.zzbat.zztu;	 Catch:{ all -> 0x0143 }
        r3 = new com.google.android.gms.internal.ads.zzavv;	 Catch:{ all -> 0x0143 }
        r3.<init>(r11, r1);	 Catch:{ all -> 0x0143 }
        r2.post(r3);	 Catch:{ all -> 0x0143 }
        monitor-exit(r6);	 Catch:{ all -> 0x0143 }
        return;
    L_0x0141:
        monitor-exit(r6);	 Catch:{ all -> 0x0143 }
        goto L_0x00f7;
    L_0x0143:
        r0 = move-exception;
        r1 = r0;
        monitor-exit(r6);	 Catch:{ all -> 0x0143 }
        throw r1;
    L_0x0147:
        r0 = move-exception;
        r1 = r0;
        monitor-exit(r5);	 Catch:{ all -> 0x0147 }
        throw r1;
    L_0x014b:
        r0 = move-exception;
        r1 = r0;
        goto L_0x0190;
    L_0x014e:
        r0 = move-exception;
        r5 = r0;
        r6 = "Unable to resolve rewarded adapter.";
        com.google.android.gms.internal.ads.zzbbd.zzc(r6, r5);	 Catch:{ all -> 0x014b }
        r5 = r11.mLock;
        monitor-enter(r5);
        r6 = r3.zzdml;	 Catch:{ all -> 0x016b }
        r6 = android.text.TextUtils.isEmpty(r6);	 Catch:{ all -> 0x016b }
        if (r6 != 0) goto L_0x0169;
    L_0x0160:
        r6 = r11.zzefi;	 Catch:{ all -> 0x016b }
        r3 = r3.zzxj();	 Catch:{ all -> 0x016b }
        r6.add(r3);	 Catch:{ all -> 0x016b }
    L_0x0169:
        monitor-exit(r5);	 Catch:{ all -> 0x016b }
        goto L_0x00f7;
    L_0x016b:
        r0 = move-exception;
        r1 = r0;
        monitor-exit(r5);	 Catch:{ all -> 0x016b }
        throw r1;
    L_0x016f:
        r1 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x014b }
        r1.interrupt();	 Catch:{ all -> 0x014b }
        r1 = r11.mLock;
        monitor-enter(r1);
        r2 = r3.zzdml;	 Catch:{ all -> 0x018c }
        r2 = android.text.TextUtils.isEmpty(r2);	 Catch:{ all -> 0x018c }
        if (r2 != 0) goto L_0x018a;
    L_0x0181:
        r2 = r11.zzefi;	 Catch:{ all -> 0x018c }
        r3 = r3.zzxj();	 Catch:{ all -> 0x018c }
        r2.add(r3);	 Catch:{ all -> 0x018c }
    L_0x018a:
        monitor-exit(r1);	 Catch:{ all -> 0x018c }
        goto L_0x01aa;
    L_0x018c:
        r0 = move-exception;
        r2 = r0;
        monitor-exit(r1);	 Catch:{ all -> 0x018c }
        throw r2;
    L_0x0190:
        r2 = r11.mLock;
        monitor-enter(r2);
        r4 = r3.zzdml;	 Catch:{ all -> 0x01a6 }
        r4 = android.text.TextUtils.isEmpty(r4);	 Catch:{ all -> 0x01a6 }
        if (r4 != 0) goto L_0x01a4;
    L_0x019b:
        r4 = r11.zzefi;	 Catch:{ all -> 0x01a6 }
        r3 = r3.zzxj();	 Catch:{ all -> 0x01a6 }
        r4.add(r3);	 Catch:{ all -> 0x01a6 }
    L_0x01a4:
        monitor-exit(r2);	 Catch:{ all -> 0x01a6 }
        throw r1;
    L_0x01a6:
        r0 = move-exception;
        r1 = r0;
        monitor-exit(r2);	 Catch:{ all -> 0x01a6 }
        throw r1;
    L_0x01aa:
        r1 = 3;
        r2 = 0;
        r1 = r11.zza(r1, r2, r2);
        r2 = com.google.android.gms.internal.ads.zzbat.zztu;
        r3 = new com.google.android.gms.internal.ads.zzavw;
        r3.<init>(r11, r1);
        r2.post(r3);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzavu.zzki():void");
    }

    public final void zzde(String str) {
        synchronized (this.mLock) {
            this.zzefj.add(str);
        }
    }

    private final zzaxf zza(int i, @Nullable String str, @Nullable zzakq zzakq) {
        String stringBuilder;
        zzwf zzwf;
        boolean z;
        String str2;
        long j;
        zzwb zzwb = this.zzdsk.zzeag.zzdwg;
        List list = this.zzdsk.zzehy.zzdlq;
        List list2 = this.zzdsk.zzehy.zzdlr;
        List list3 = this.zzdsk.zzehy.zzdyf;
        int i2 = this.zzdsk.zzehy.orientation;
        long j2 = this.zzdsk.zzehy.zzdlx;
        String str3 = this.zzdsk.zzeag.zzdwj;
        boolean z2 = this.zzdsk.zzehy.zzdyd;
        zzakr zzakr = this.zzdsk.zzehj;
        long j3 = this.zzdsk.zzehy.zzdye;
        zzwf zzwf2 = this.zzdsk.zzbst;
        long j4 = j3;
        zzakr zzakr2 = zzakr;
        long j5 = this.zzdsk.zzehy.zzdyc;
        long j6 = this.zzdsk.zzehn;
        long j7 = this.zzdsk.zzehy.zzdyh;
        String str4 = this.zzdsk.zzehy.zzdyi;
        JSONObject jSONObject = this.zzdsk.zzehh;
        zzawd zzawd = this.zzdsk.zzehy.zzdyr;
        List list4 = this.zzdsk.zzehy.zzdys;
        List list5 = this.zzdsk.zzehy.zzdyt;
        boolean z3 = this.zzdsk.zzehy.zzdyu;
        zzaso zzaso = this.zzdsk.zzehy.zzdyv;
        JSONObject jSONObject2 = jSONObject;
        StringBuilder stringBuilder2 = new StringBuilder("");
        if (this.zzefi == null) {
            stringBuilder = stringBuilder2.toString();
            zzwf = zzwf2;
            z = z2;
            str2 = str4;
            j = j7;
        } else {
            Iterator it = this.zzefi.iterator();
            while (true) {
                int i3 = 1;
                zzwf = zzwf2;
                if (it.hasNext()) {
                    zzavn zzavn = (zzavn) it.next();
                    if (zzavn != null) {
                        Iterator it2 = it;
                        if (TextUtils.isEmpty(zzavn.zzdkx)) {
                            zzwf2 = zzwf;
                            it = it2;
                        } else {
                            int i4;
                            String str5 = zzavn.zzdkx;
                            str2 = str4;
                            switch (zzavn.errorCode) {
                                case 3:
                                    break;
                                case 4:
                                    i3 = 2;
                                    break;
                                case 5:
                                    i3 = 4;
                                    break;
                                case 6:
                                    j = j7;
                                    i4 = 0;
                                    break;
                                case 7:
                                    i3 = 3;
                                    break;
                                default:
                                    i3 = 6;
                                    break;
                            }
                            j = j7;
                            i4 = i3;
                            j7 = zzavn.zzdng;
                            z = z2;
                            StringBuilder stringBuilder3 = new StringBuilder(33 + String.valueOf(str5).length());
                            stringBuilder3.append(str5);
                            stringBuilder3.append(".");
                            stringBuilder3.append(i4);
                            stringBuilder3.append(".");
                            stringBuilder3.append(j7);
                            stringBuilder2.append(String.valueOf(stringBuilder3.toString()).concat("_"));
                            zzwf2 = zzwf;
                            it = it2;
                            str4 = str2;
                            j7 = j;
                            z2 = z;
                        }
                    } else {
                        zzwf2 = zzwf;
                    }
                } else {
                    z = z2;
                    str2 = str4;
                    j = j7;
                    stringBuilder = stringBuilder2.substring(0, Math.max(0, stringBuilder2.length() - 1));
                }
            }
        }
        List list6 = this.zzdsk.zzehy.zzdlu;
        String str6 = this.zzdsk.zzehy.zzdyy;
        zzum zzum = this.zzdsk.zzehw;
        z2 = this.zzdsk.zzehy.zzbph;
        boolean z4 = this.zzdsk.zzehx;
        boolean z5 = this.zzdsk.zzehy.zzdzc;
        List list7 = this.zzdsk.zzehy.zzdls;
        boolean z6 = this.zzdsk.zzehy.zzbpi;
        boolean z7 = z5;
        boolean z8 = z4;
        boolean z9 = z2;
        zzum zzum2 = zzum;
        long j8 = j4;
        String str7 = str6;
        zzakr zzakr3 = zzakr2;
        List list8 = list6;
        return new zzaxf(zzwb, null, list, i, list2, list3, i2, j2, str3, z, zzakq, null, str, zzakr3, null, j8, zzwf, j5, j6, j, str2, jSONObject2, null, zzawd, list4, list5, z3, zzaso, stringBuilder, list8, str7, zzum2, z9, z8, z7, list7, z6, this.zzdsk.zzehy.zzdzd, this.zzdsk.zzehy.zzdzf);
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzl(zzaxf zzaxf) {
        this.zzefk.zzxc().zzb(zzaxf);
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzm(zzaxf zzaxf) {
        this.zzefk.zzxc().zzb(zzaxf);
    }
}
