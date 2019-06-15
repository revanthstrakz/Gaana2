package com.google.android.gms.internal.cast;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Locale;

public final class zzed {
    private static final Object zzaat = new Object();
    private static final zzdw zzbf = new zzdw("RequestTracker");
    private final Handler handler = new zzez(Looper.getMainLooper());
    private long zzaar;
    @VisibleForTesting
    private zzec zzaas;
    @VisibleForTesting
    private Runnable zzpz;
    @VisibleForTesting
    private long zzwu = -1;

    public zzed(long j) {
        this.zzaar = j;
    }

    public final void zza(long j, zzec zzec) {
        zzec zzec2;
        long j2;
        synchronized (zzaat) {
            zzec2 = this.zzaas;
            j2 = this.zzwu;
            this.zzwu = j;
            this.zzaas = zzec;
        }
        if (zzec2 != null) {
            zzec2.zzd(j2);
        }
        synchronized (zzaat) {
            if (this.zzpz != null) {
                this.handler.removeCallbacks(this.zzpz);
            }
            this.zzpz = new zzee(this);
            this.handler.postDelayed(this.zzpz, this.zzaar);
        }
    }

    public final boolean zzfd() {
        boolean z;
        synchronized (zzaat) {
            z = this.zzwu != -1;
        }
        return z;
    }

    public final boolean test(long j) {
        boolean z;
        synchronized (zzaat) {
            z = this.zzwu != -1 && this.zzwu == j;
        }
        return z;
    }

    /* JADX WARNING: Missing block: B:11:0x0029, code skipped:
            return false;
     */
    public final boolean zzc(long r7, int r9, java.lang.Object r10) {
        /*
        r6 = this;
        r0 = zzaat;
        monitor-enter(r0);
        r1 = r6.zzwu;	 Catch:{ all -> 0x002a }
        r3 = -1;
        r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        r1 = 0;
        if (r5 == 0) goto L_0x0028;
    L_0x000c:
        r2 = r6.zzwu;	 Catch:{ all -> 0x002a }
        r4 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1));
        if (r4 != 0) goto L_0x0028;
    L_0x0012:
        r2 = java.util.Locale.ROOT;	 Catch:{ all -> 0x002a }
        r3 = "request %d completed";
        r4 = 1;
        r5 = new java.lang.Object[r4];	 Catch:{ all -> 0x002a }
        r7 = java.lang.Long.valueOf(r7);	 Catch:{ all -> 0x002a }
        r5[r1] = r7;	 Catch:{ all -> 0x002a }
        r7 = java.lang.String.format(r2, r3, r5);	 Catch:{ all -> 0x002a }
        r6.zza(r9, r10, r7);	 Catch:{ all -> 0x002a }
        monitor-exit(r0);	 Catch:{ all -> 0x002a }
        return r4;
    L_0x0028:
        monitor-exit(r0);	 Catch:{ all -> 0x002a }
        return r1;
    L_0x002a:
        r7 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x002a }
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.zzed.zzc(long, int, java.lang.Object):boolean");
    }

    public final boolean zzv(int i) {
        return zza(2002, null);
    }

    private final boolean zza(int i, Object obj) {
        synchronized (zzaat) {
            if (this.zzwu != -1) {
                zza(i, null, String.format(Locale.ROOT, "clearing request %d", new Object[]{Long.valueOf(this.zzwu)}));
                return true;
            }
            return false;
        }
    }

    private final void zza(int i, Object obj, String str) {
        zzbf.d(str, new Object[0]);
        synchronized (zzaat) {
            if (this.zzaas != null) {
                this.zzaas.zza(this.zzwu, i, obj);
            }
            this.zzwu = -1;
            this.zzaas = null;
            synchronized (zzaat) {
                if (this.zzpz == null) {
                } else {
                    this.handler.removeCallbacks(this.zzpz);
                    this.zzpz = null;
                }
            }
        }
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzfe() {
        synchronized (zzaat) {
            if (this.zzwu == -1) {
                return;
            }
            zza(15, null);
        }
    }
}
