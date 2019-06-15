package com.google.android.gms.internal.ads;

import android.content.Context;

@zzark
public abstract class zzaph extends zzaxv {
    protected final Context mContext;
    protected final Object mLock = new Object();
    protected final zzapm zzdsj;
    protected final zzaxg zzdsk;
    protected zzasm zzdsl;
    protected final Object zzdsn = new Object();

    protected zzaph(Context context, zzaxg zzaxg, zzapm zzapm) {
        super(true);
        this.mContext = context;
        this.zzdsk = zzaxg;
        this.zzdsl = zzaxg.zzehy;
        this.zzdsj = zzapm;
    }

    public void onStop() {
    }

    public abstract void zzap(long j) throws zzapk;

    public abstract zzaxf zzcr(int i);

    /* JADX WARNING: Removed duplicated region for block: B:18:0x003b A:{Catch:{ zzapk -> 0x0014 }} */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0033 A:{Catch:{ zzapk -> 0x0014 }} */
    public final void zzki() {
        /*
        r5 = this;
        r0 = r5.mLock;
        monitor-enter(r0);
        r1 = "AdRendererBackgroundTask started.";
        com.google.android.gms.internal.ads.zzbbd.zzdn(r1);	 Catch:{ all -> 0x0061 }
        r1 = r5.zzdsk;	 Catch:{ all -> 0x0061 }
        r1 = r1.errorCode;	 Catch:{ all -> 0x0061 }
        r2 = android.os.SystemClock.elapsedRealtime();	 Catch:{ zzapk -> 0x0014 }
        r5.zzap(r2);	 Catch:{ zzapk -> 0x0014 }
        goto L_0x0051;
    L_0x0014:
        r1 = move-exception;
        r2 = r1.getErrorCode();	 Catch:{ all -> 0x0061 }
        r3 = 3;
        if (r2 == r3) goto L_0x0028;
    L_0x001c:
        r3 = -1;
        if (r2 != r3) goto L_0x0020;
    L_0x001f:
        goto L_0x0028;
    L_0x0020:
        r1 = r1.getMessage();	 Catch:{ all -> 0x0061 }
        com.google.android.gms.internal.ads.zzbbd.zzeo(r1);	 Catch:{ all -> 0x0061 }
        goto L_0x002f;
    L_0x0028:
        r1 = r1.getMessage();	 Catch:{ all -> 0x0061 }
        com.google.android.gms.internal.ads.zzbbd.zzen(r1);	 Catch:{ all -> 0x0061 }
    L_0x002f:
        r1 = r5.zzdsl;	 Catch:{ all -> 0x0061 }
        if (r1 != 0) goto L_0x003b;
    L_0x0033:
        r1 = new com.google.android.gms.internal.ads.zzasm;	 Catch:{ all -> 0x0061 }
        r1.<init>(r2);	 Catch:{ all -> 0x0061 }
        r5.zzdsl = r1;	 Catch:{ all -> 0x0061 }
        goto L_0x0046;
    L_0x003b:
        r1 = new com.google.android.gms.internal.ads.zzasm;	 Catch:{ all -> 0x0061 }
        r3 = r5.zzdsl;	 Catch:{ all -> 0x0061 }
        r3 = r3.zzdlx;	 Catch:{ all -> 0x0061 }
        r1.<init>(r2, r3);	 Catch:{ all -> 0x0061 }
        r5.zzdsl = r1;	 Catch:{ all -> 0x0061 }
    L_0x0046:
        r1 = com.google.android.gms.internal.ads.zzayh.zzelc;	 Catch:{ all -> 0x0061 }
        r3 = new com.google.android.gms.internal.ads.zzapi;	 Catch:{ all -> 0x0061 }
        r3.<init>(r5);	 Catch:{ all -> 0x0061 }
        r1.post(r3);	 Catch:{ all -> 0x0061 }
        r1 = r2;
    L_0x0051:
        r1 = r5.zzcr(r1);	 Catch:{ all -> 0x0061 }
        r2 = com.google.android.gms.internal.ads.zzayh.zzelc;	 Catch:{ all -> 0x0061 }
        r3 = new com.google.android.gms.internal.ads.zzapj;	 Catch:{ all -> 0x0061 }
        r3.<init>(r5, r1);	 Catch:{ all -> 0x0061 }
        r2.post(r3);	 Catch:{ all -> 0x0061 }
        monitor-exit(r0);	 Catch:{ all -> 0x0061 }
        return;
    L_0x0061:
        r1 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0061 }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaph.zzki():void");
    }
}
