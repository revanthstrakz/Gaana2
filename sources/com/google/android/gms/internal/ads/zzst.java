package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.PlatformVersion;

@zzark
public final class zzst {
    private final Object zzbxp = new Object();
    private zzsu zzbxq = null;
    private boolean zzbxr = false;

    /* JADX WARNING: Missing block: B:24:0x003b, code skipped:
            return;
     */
    public final void initialize(android.content.Context r5) {
        /*
        r4 = this;
        r0 = r4.zzbxp;
        monitor-enter(r0);
        r1 = r4.zzbxr;	 Catch:{ all -> 0x003c }
        if (r1 != 0) goto L_0x003a;
    L_0x0007:
        r1 = com.google.android.gms.common.util.PlatformVersion.isAtLeastIceCreamSandwich();	 Catch:{ all -> 0x003c }
        if (r1 != 0) goto L_0x000f;
    L_0x000d:
        monitor-exit(r0);	 Catch:{ all -> 0x003c }
        return;
    L_0x000f:
        r1 = 0;
        r2 = r5.getApplicationContext();	 Catch:{ all -> 0x003c }
        if (r2 != 0) goto L_0x0017;
    L_0x0016:
        r2 = r5;
    L_0x0017:
        r3 = r2 instanceof android.app.Application;	 Catch:{ all -> 0x003c }
        if (r3 == 0) goto L_0x001e;
    L_0x001b:
        r1 = r2;
        r1 = (android.app.Application) r1;	 Catch:{ all -> 0x003c }
    L_0x001e:
        if (r1 != 0) goto L_0x0027;
    L_0x0020:
        r5 = "Can not cast Context to Application";
        com.google.android.gms.internal.ads.zzbbd.zzeo(r5);	 Catch:{ all -> 0x003c }
        monitor-exit(r0);	 Catch:{ all -> 0x003c }
        return;
    L_0x0027:
        r2 = r4.zzbxq;	 Catch:{ all -> 0x003c }
        if (r2 != 0) goto L_0x0032;
    L_0x002b:
        r2 = new com.google.android.gms.internal.ads.zzsu;	 Catch:{ all -> 0x003c }
        r2.<init>();	 Catch:{ all -> 0x003c }
        r4.zzbxq = r2;	 Catch:{ all -> 0x003c }
    L_0x0032:
        r2 = r4.zzbxq;	 Catch:{ all -> 0x003c }
        r2.zza(r1, r5);	 Catch:{ all -> 0x003c }
        r5 = 1;
        r4.zzbxr = r5;	 Catch:{ all -> 0x003c }
    L_0x003a:
        monitor-exit(r0);	 Catch:{ all -> 0x003c }
        return;
    L_0x003c:
        r5 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x003c }
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzst.initialize(android.content.Context):void");
    }

    public final void zza(zzsw zzsw) {
        synchronized (this.zzbxp) {
            if (PlatformVersion.isAtLeastIceCreamSandwich()) {
                if (this.zzbxq == null) {
                    this.zzbxq = new zzsu();
                }
                this.zzbxq.zza(zzsw);
                return;
            }
        }
    }

    @Nullable
    public final Activity getActivity() {
        synchronized (this.zzbxp) {
            if (!PlatformVersion.isAtLeastIceCreamSandwich()) {
                return null;
            } else if (this.zzbxq != null) {
                Activity activity = this.zzbxq.getActivity();
                return activity;
            } else {
                return null;
            }
        }
    }

    @Nullable
    public final Context getContext() {
        synchronized (this.zzbxp) {
            if (!PlatformVersion.isAtLeastIceCreamSandwich()) {
                return null;
            } else if (this.zzbxq != null) {
                Context context = this.zzbxq.getContext();
                return context;
            } else {
                return null;
            }
        }
    }
}
