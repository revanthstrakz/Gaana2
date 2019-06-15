package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

@zzark
final class zzbcc {
    private final Object zzepk = new Object();
    private final List<Runnable> zzepl = new ArrayList();
    private boolean zzepm = false;

    public final void zza(Runnable runnable, Executor executor) {
        synchronized (this.zzepk) {
            if (this.zzepm) {
                executor.execute(runnable);
            } else {
                this.zzepl.add(new zzbcd(executor, runnable));
            }
        }
    }

    /* JADX WARNING: Missing block: B:9:0x001c, code skipped:
            r0 = r0;
            r1 = r0.size();
            r2 = 0;
     */
    /* JADX WARNING: Missing block: B:10:0x0023, code skipped:
            if (r2 >= r1) goto L_0x0031;
     */
    /* JADX WARNING: Missing block: B:11:0x0025, code skipped:
            r3 = r0.get(r2);
            r2 = r2 + 1;
            ((java.lang.Runnable) r3).run();
     */
    /* JADX WARNING: Missing block: B:12:0x0031, code skipped:
            return;
     */
    public final void zzaaw() {
        /*
        r4 = this;
        r0 = new java.util.ArrayList;
        r0.<init>();
        r1 = r4.zzepk;
        monitor-enter(r1);
        r2 = r4.zzepm;	 Catch:{ all -> 0x0032 }
        if (r2 == 0) goto L_0x000e;
    L_0x000c:
        monitor-exit(r1);	 Catch:{ all -> 0x0032 }
        return;
    L_0x000e:
        r2 = r4.zzepl;	 Catch:{ all -> 0x0032 }
        r0.addAll(r2);	 Catch:{ all -> 0x0032 }
        r2 = r4.zzepl;	 Catch:{ all -> 0x0032 }
        r2.clear();	 Catch:{ all -> 0x0032 }
        r2 = 1;
        r4.zzepm = r2;	 Catch:{ all -> 0x0032 }
        monitor-exit(r1);	 Catch:{ all -> 0x0032 }
        r0 = (java.util.ArrayList) r0;
        r1 = r0.size();
        r2 = 0;
    L_0x0023:
        if (r2 >= r1) goto L_0x0031;
    L_0x0025:
        r3 = r0.get(r2);
        r2 = r2 + 1;
        r3 = (java.lang.Runnable) r3;
        r3.run();
        goto L_0x0023;
    L_0x0031:
        return;
    L_0x0032:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0032 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbcc.zzaaw():void");
    }
}
