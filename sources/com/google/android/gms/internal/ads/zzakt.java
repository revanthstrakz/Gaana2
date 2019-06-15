package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;

@zzark
public final class zzakt extends zzaln {
    private final Object mLock = new Object();
    private zzaky zzdmj;
    private zzaks zzdmk;

    public final void onVideoPause() {
    }

    public final void zzc(zzawd zzawd) {
    }

    public final void zzcl(String str) {
    }

    public final void zzul() {
    }

    public final void zza(zzaky zzaky) {
        synchronized (this.mLock) {
            this.zzdmj = zzaky;
        }
    }

    public final void onAdClicked() {
        synchronized (this.mLock) {
            if (this.zzdmk != null) {
                this.zzdmk.zziy();
            }
        }
    }

    public final void onAdClosed() {
        synchronized (this.mLock) {
            if (this.zzdmk != null) {
                this.zzdmk.zziz();
            }
        }
    }

    public final void onAdFailedToLoad(int i) {
        synchronized (this.mLock) {
            if (this.zzdmj != null) {
                this.zzdmj.zzco(i == 3 ? 1 : 2);
                this.zzdmj = null;
            }
        }
    }

    public final void onAdLeftApplication() {
        synchronized (this.mLock) {
            if (this.zzdmk != null) {
                this.zzdmk.zzja();
            }
        }
    }

    public final void onAdOpened() {
        synchronized (this.mLock) {
            if (this.zzdmk != null) {
                this.zzdmk.zzjb();
            }
        }
    }

    /* JADX WARNING: Missing block: B:12:0x001c, code skipped:
            return;
     */
    public final void onAdLoaded() {
        /*
        r3 = this;
        r0 = r3.mLock;
        monitor-enter(r0);
        r1 = r3.zzdmj;	 Catch:{ all -> 0x001d }
        if (r1 == 0) goto L_0x0012;
    L_0x0007:
        r1 = r3.zzdmj;	 Catch:{ all -> 0x001d }
        r2 = 0;
        r1.zzco(r2);	 Catch:{ all -> 0x001d }
        r1 = 0;
        r3.zzdmj = r1;	 Catch:{ all -> 0x001d }
        monitor-exit(r0);	 Catch:{ all -> 0x001d }
        return;
    L_0x0012:
        r1 = r3.zzdmk;	 Catch:{ all -> 0x001d }
        if (r1 == 0) goto L_0x001b;
    L_0x0016:
        r1 = r3.zzdmk;	 Catch:{ all -> 0x001d }
        r1.zzjc();	 Catch:{ all -> 0x001d }
    L_0x001b:
        monitor-exit(r0);	 Catch:{ all -> 0x001d }
        return;
    L_0x001d:
        r1 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x001d }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzakt.onAdLoaded():void");
    }

    /* JADX WARNING: Missing block: B:12:0x001c, code skipped:
            return;
     */
    public final void zza(com.google.android.gms.internal.ads.zzalp r4) {
        /*
        r3 = this;
        r0 = r3.mLock;
        monitor-enter(r0);
        r1 = r3.zzdmj;	 Catch:{ all -> 0x001d }
        if (r1 == 0) goto L_0x0012;
    L_0x0007:
        r1 = r3.zzdmj;	 Catch:{ all -> 0x001d }
        r2 = 0;
        r1.zza(r2, r4);	 Catch:{ all -> 0x001d }
        r4 = 0;
        r3.zzdmj = r4;	 Catch:{ all -> 0x001d }
        monitor-exit(r0);	 Catch:{ all -> 0x001d }
        return;
    L_0x0012:
        r4 = r3.zzdmk;	 Catch:{ all -> 0x001d }
        if (r4 == 0) goto L_0x001b;
    L_0x0016:
        r4 = r3.zzdmk;	 Catch:{ all -> 0x001d }
        r4.zzjc();	 Catch:{ all -> 0x001d }
    L_0x001b:
        monitor-exit(r0);	 Catch:{ all -> 0x001d }
        return;
    L_0x001d:
        r4 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x001d }
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzakt.zza(com.google.android.gms.internal.ads.zzalp):void");
    }

    public final void onAdImpression() {
        synchronized (this.mLock) {
            if (this.zzdmk != null) {
                this.zzdmk.zzjd();
            }
        }
    }

    public final void onAppEvent(String str, String str2) {
        synchronized (this.mLock) {
            if (this.zzdmk != null) {
                this.zzdmk.zzd(str, str2);
            }
        }
    }

    public final void zzb(zzadx zzadx, String str) {
        synchronized (this.mLock) {
            if (this.zzdmk != null) {
                this.zzdmk.zza(zzadx, str);
            }
        }
    }

    public final void onVideoEnd() {
        synchronized (this.mLock) {
            if (this.zzdmk != null) {
                this.zzdmk.zzix();
            }
        }
    }

    public final void zza(@Nullable zzaks zzaks) {
        synchronized (this.mLock) {
            this.zzdmk = zzaks;
        }
    }
}
