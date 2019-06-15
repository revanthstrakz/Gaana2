package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbb;
import java.util.concurrent.Future;

@zzark
public final class zzapt extends zzaxv {
    private final Object mLock;
    private final zzapm zzdsj;
    private final zzaxg zzdsk;
    private final zzasm zzdsl;
    private final zzapw zzdta;
    private Future<zzaxf> zzdtb;

    public zzapt(Context context, zzbb zzbb, zzaxg zzaxg, zzcu zzcu, zzapm zzapm, zzaba zzaba) {
        this(zzaxg, zzapm, new zzapw(context, zzbb, new zzazs(context), zzcu, zzaxg, zzaba));
    }

    private zzapt(zzaxg zzaxg, zzapm zzapm, zzapw zzapw) {
        this.mLock = new Object();
        this.zzdsk = zzaxg;
        this.zzdsl = zzaxg.zzehy;
        this.zzdsj = zzapm;
        this.zzdta = zzapw;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0034  */
    public final void zzki() {
        /*
        r62 = this;
        r1 = r62;
        r2 = 0;
        r3 = 0;
        r4 = r1.mLock;	 Catch:{ TimeoutException -> 0x0025, InterruptedException | CancellationException | ExecutionException -> 0x0031, InterruptedException | CancellationException | ExecutionException -> 0x0031, InterruptedException | CancellationException | ExecutionException -> 0x0031 }
        monitor-enter(r4);	 Catch:{ TimeoutException -> 0x0025, InterruptedException | CancellationException | ExecutionException -> 0x0031, InterruptedException | CancellationException | ExecutionException -> 0x0031, InterruptedException | CancellationException | ExecutionException -> 0x0031 }
        r5 = r1.zzdta;	 Catch:{ all -> 0x0021 }
        r5 = com.google.android.gms.internal.ads.zzayf.zza(r5);	 Catch:{ all -> 0x0021 }
        r1.zzdtb = r5;	 Catch:{ all -> 0x0021 }
        monitor-exit(r4);	 Catch:{ all -> 0x0021 }
        r4 = r1.zzdtb;	 Catch:{ TimeoutException -> 0x0025, InterruptedException | CancellationException | ExecutionException -> 0x0031, InterruptedException | CancellationException | ExecutionException -> 0x0031, InterruptedException | CancellationException | ExecutionException -> 0x0031 }
        r5 = 60000; // 0xea60 float:8.4078E-41 double:2.9644E-319;
        r7 = java.util.concurrent.TimeUnit.MILLISECONDS;	 Catch:{ TimeoutException -> 0x0025, InterruptedException | CancellationException | ExecutionException -> 0x0031, InterruptedException | CancellationException | ExecutionException -> 0x0031, InterruptedException | CancellationException | ExecutionException -> 0x0031 }
        r4 = r4.get(r5, r7);	 Catch:{ TimeoutException -> 0x0025, InterruptedException | CancellationException | ExecutionException -> 0x0031, InterruptedException | CancellationException | ExecutionException -> 0x0031, InterruptedException | CancellationException | ExecutionException -> 0x0031 }
        r4 = (com.google.android.gms.internal.ads.zzaxf) r4;	 Catch:{ TimeoutException -> 0x0025, InterruptedException | CancellationException | ExecutionException -> 0x0031, InterruptedException | CancellationException | ExecutionException -> 0x0031, InterruptedException | CancellationException | ExecutionException -> 0x0031 }
        r2 = -2;
        r8 = r2;
        r3 = r4;
        goto L_0x0032;
    L_0x0021:
        r0 = move-exception;
        r5 = r0;
        monitor-exit(r4);	 Catch:{ all -> 0x0021 }
        throw r5;	 Catch:{ TimeoutException -> 0x0025, InterruptedException | CancellationException | ExecutionException -> 0x0031, InterruptedException | CancellationException | ExecutionException -> 0x0031, InterruptedException | CancellationException | ExecutionException -> 0x0031 }
    L_0x0025:
        r2 = "Timed out waiting for native ad.";
        com.google.android.gms.internal.ads.zzbbd.zzeo(r2);
        r2 = 2;
        r4 = r1.zzdtb;
        r5 = 1;
        r4.cancel(r5);
    L_0x0031:
        r8 = r2;
    L_0x0032:
        if (r3 == 0) goto L_0x0037;
    L_0x0034:
        r2 = r3;
        goto L_0x00f4;
    L_0x0037:
        r2 = new com.google.android.gms.internal.ads.zzaxf;
        r3 = r1.zzdsk;
        r3 = r3.zzeag;
        r5 = r3.zzdwg;
        r3 = r1.zzdsl;
        r11 = r3.orientation;
        r3 = r1.zzdsl;
        r12 = r3.zzdlx;
        r3 = r1.zzdsk;
        r3 = r3.zzeag;
        r14 = r3.zzdwj;
        r16 = 0;
        r17 = 0;
        r18 = 0;
        r19 = 0;
        r20 = 0;
        r3 = r1.zzdsl;
        r3 = r3.zzdye;
        r15 = r1.zzdsk;
        r15 = r15.zzbst;
        r10 = r1.zzdsl;
        r49 = r11;
        r10 = r10.zzdyc;
        r9 = r1.zzdsk;
        r50 = r10;
        r10 = r9.zzehn;
        r9 = r1.zzdsl;
        r52 = r10;
        r10 = r9.zzdyh;
        r9 = r1.zzdsl;
        r9 = r9.zzdyi;
        r7 = r1.zzdsk;
        r7 = r7.zzehh;
        r32 = 0;
        r33 = 0;
        r34 = 0;
        r35 = 0;
        r6 = r1.zzdsk;
        r6 = r6.zzehy;
        r6 = r6.zzdyu;
        r54 = r3;
        r3 = r1.zzdsk;
        r3 = r3.zzehy;
        r3 = r3.zzdyv;
        r38 = 0;
        r39 = 0;
        r4 = r1.zzdsl;
        r4 = r4.zzdyy;
        r56 = r4;
        r4 = r1.zzdsk;
        r4 = r4.zzehw;
        r57 = r4;
        r4 = r1.zzdsk;
        r4 = r4.zzehy;
        r4 = r4.zzbph;
        r43 = 0;
        r58 = r4;
        r4 = r1.zzdsk;
        r4 = r4.zzehy;
        r4 = r4.zzdzc;
        r45 = 0;
        r59 = r4;
        r4 = r1.zzdsk;
        r4 = r4.zzehy;
        r4 = r4.zzbpi;
        r60 = r4;
        r4 = r1.zzdsk;
        r4 = r4.zzehy;
        r4 = r4.zzdzd;
        r61 = r4;
        r4 = r1.zzdsk;
        r4 = r4.zzehy;
        r4 = r4.zzdzf;
        r48 = r4;
        r21 = r54;
        r40 = r56;
        r41 = r57;
        r42 = r58;
        r44 = r59;
        r46 = r60;
        r47 = r61;
        r4 = r2;
        r36 = r6;
        r6 = 0;
        r31 = r7;
        r7 = 0;
        r30 = r9;
        r9 = 0;
        r28 = r10;
        r24 = r50;
        r26 = r52;
        r10 = 0;
        r11 = r49;
        r23 = r15;
        r15 = 0;
        r37 = r3;
        r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r14, r15, r16, r17, r18, r19, r20, r21, r23, r24, r26, r28, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48);
    L_0x00f4:
        r3 = com.google.android.gms.internal.ads.zzayh.zzelc;
        r4 = new com.google.android.gms.internal.ads.zzapu;
        r4.<init>(r1, r2);
        r3.post(r4);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzapt.zzki():void");
    }

    public final void onStop() {
        synchronized (this.mLock) {
            if (this.zzdtb != null) {
                this.zzdtb.cancel(true);
            }
        }
    }
}
