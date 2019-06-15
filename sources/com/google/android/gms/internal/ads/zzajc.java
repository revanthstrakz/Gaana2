package com.google.android.gms.internal.ads;

final class zzajc implements Runnable {
    private final /* synthetic */ zzajm zzdjh;
    private final /* synthetic */ zzaii zzdji;
    private final /* synthetic */ zzait zzdjj;

    zzajc(zzait zzait, zzajm zzajm, zzaii zzaii) {
        this.zzdjj = zzait;
        this.zzdjh = zzajm;
        this.zzdji = zzaii;
    }

    /* JADX WARNING: Missing block: B:12:0x0035, code skipped:
            return;
     */
    public final void run() {
        /*
        r3 = this;
        r0 = r3.zzdjj;
        r0 = r0.mLock;
        monitor-enter(r0);
        r1 = r3.zzdjh;	 Catch:{ all -> 0x0036 }
        r1 = r1.getStatus();	 Catch:{ all -> 0x0036 }
        r2 = -1;
        if (r1 == r2) goto L_0x0034;
    L_0x0010:
        r1 = r3.zzdjh;	 Catch:{ all -> 0x0036 }
        r1 = r1.getStatus();	 Catch:{ all -> 0x0036 }
        r2 = 1;
        if (r1 != r2) goto L_0x001a;
    L_0x0019:
        goto L_0x0034;
    L_0x001a:
        r1 = r3.zzdjh;	 Catch:{ all -> 0x0036 }
        r1.reject();	 Catch:{ all -> 0x0036 }
        r1 = com.google.android.gms.internal.ads.zzbcg.zzepo;	 Catch:{ all -> 0x0036 }
        r2 = r3.zzdji;	 Catch:{ all -> 0x0036 }
        r2.getClass();	 Catch:{ all -> 0x0036 }
        r2 = com.google.android.gms.internal.ads.zzajd.zzb(r2);	 Catch:{ all -> 0x0036 }
        r1.execute(r2);	 Catch:{ all -> 0x0036 }
        r1 = "Could not receive loaded message in a timely manner. Rejecting.";
        com.google.android.gms.internal.ads.zzaxz.v(r1);	 Catch:{ all -> 0x0036 }
        monitor-exit(r0);	 Catch:{ all -> 0x0036 }
        return;
    L_0x0034:
        monitor-exit(r0);	 Catch:{ all -> 0x0036 }
        return;
    L_0x0036:
        r1 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0036 }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzajc.run():void");
    }
}
