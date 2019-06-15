package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.IntentFilter;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import com.til.colombia.android.internal.d;

@ShowFirstParty
@VisibleForTesting
final class zzfn extends zzfm {
    private static final Object zzbfx = new Object();
    private static zzfn zzbgi;
    private boolean connected = true;
    private zzcc zzbdr = new zzfo(this);
    private Context zzbfy;
    private zzcb zzbfz;
    private volatile zzby zzbga;
    private int zzbgb = 1800000;
    private boolean zzbgc = true;
    private boolean zzbgd = false;
    private boolean zzbge = true;
    private zzfq zzbgf;
    private zzdn zzbgg;
    private boolean zzbgh = false;

    public static zzfn zzqe() {
        if (zzbgi == null) {
            zzbgi = new zzfn();
        }
        return zzbgi;
    }

    private zzfn() {
    }

    /* Access modifiers changed, original: final|declared_synchronized */
    /* JADX WARNING: Missing block: B:11:0x0014, code skipped:
            return;
     */
    public final synchronized void zza(android.content.Context r2, com.google.android.gms.tagmanager.zzby r3) {
        /*
        r1 = this;
        monitor-enter(r1);
        r0 = r1.zzbfy;	 Catch:{ all -> 0x0015 }
        if (r0 == 0) goto L_0x0007;
    L_0x0005:
        monitor-exit(r1);
        return;
    L_0x0007:
        r2 = r2.getApplicationContext();	 Catch:{ all -> 0x0015 }
        r1.zzbfy = r2;	 Catch:{ all -> 0x0015 }
        r2 = r1.zzbga;	 Catch:{ all -> 0x0015 }
        if (r2 != 0) goto L_0x0013;
    L_0x0011:
        r1.zzbga = r3;	 Catch:{ all -> 0x0015 }
    L_0x0013:
        monitor-exit(r1);
        return;
    L_0x0015:
        r2 = move-exception;
        monitor-exit(r1);
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzfn.zza(android.content.Context, com.google.android.gms.tagmanager.zzby):void");
    }

    /* Access modifiers changed, original: final|declared_synchronized */
    public final synchronized zzcb zzqf() {
        if (this.zzbfz == null) {
            if (this.zzbfy == null) {
                throw new IllegalStateException("Cant get a store unless we have a context");
            }
            this.zzbfz = new zzeb(this.zzbdr, this.zzbfy);
        }
        if (this.zzbgf == null) {
            this.zzbgf = new zzfr(this, null);
            if (this.zzbgb > 0) {
                this.zzbgf.zzh((long) this.zzbgb);
            }
        }
        this.zzbgd = true;
        if (this.zzbgc) {
            dispatch();
            this.zzbgc = false;
        }
        if (this.zzbgg == null && this.zzbge) {
            this.zzbgg = new zzdn(this);
            zzdn zzdn = this.zzbgg;
            Context context = this.zzbfy;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(d.a);
            context.registerReceiver(zzdn, intentFilter);
            intentFilter = new IntentFilter();
            intentFilter.addAction("com.google.analytics.RADIO_POWERED");
            intentFilter.addCategory(context.getPackageName());
            context.registerReceiver(zzdn, intentFilter);
        }
        return this.zzbfz;
    }

    public final synchronized void dispatch() {
        if (this.zzbgd) {
            this.zzbga.zzh(new zzfp(this));
            return;
        }
        zzdi.v("Dispatch call queued. Dispatch will run once initialization is complete.");
        this.zzbgc = true;
    }

    /* Access modifiers changed, original: final|declared_synchronized */
    @VisibleForTesting
    public final synchronized void zzb(boolean z, boolean z2) {
        boolean isPowerSaveMode = isPowerSaveMode();
        this.zzbgh = z;
        this.connected = z2;
        if (isPowerSaveMode() != isPowerSaveMode) {
            if (isPowerSaveMode()) {
                this.zzbgf.cancel();
                zzdi.v("PowerSaveMode initiated.");
                return;
            }
            this.zzbgf.zzh((long) this.zzbgb);
            zzdi.v("PowerSaveMode terminated.");
        }
    }

    public final synchronized void zzp(boolean z) {
        zzb(this.zzbgh, z);
    }

    public final synchronized void zzqd() {
        if (!isPowerSaveMode()) {
            this.zzbgf.zzqh();
        }
    }

    private final boolean isPowerSaveMode() {
        return this.zzbgh || !this.connected || this.zzbgb <= 0;
    }
}
