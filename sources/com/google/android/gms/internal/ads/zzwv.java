package com.google.android.gms.internal.ads;

import java.util.Random;

@zzark
public final class zzwv extends zzya {
    private Object mLock = new Object();
    private final Random zzcls = new Random();
    private long zzclt;

    public zzwv() {
        zzqd();
    }

    /* JADX WARNING: Missing block: B:10:0x0023, code skipped:
            r4 = r8;
     */
    public final void zzqd() {
        /*
        r10 = this;
        r0 = r10.mLock;
        monitor-enter(r0);
        r1 = 3;
        r2 = 0;
        r4 = r2;
    L_0x0007:
        r1 = r1 + -1;
        if (r1 <= 0) goto L_0x0029;
    L_0x000b:
        r4 = r10.zzcls;	 Catch:{ all -> 0x0027 }
        r4 = r4.nextInt();	 Catch:{ all -> 0x0027 }
        r4 = (long) r4;	 Catch:{ all -> 0x0027 }
        r6 = 2147483648; // 0x80000000 float:-0.0 double:1.0609978955E-314;
        r8 = r4 + r6;
        r4 = r10.zzclt;	 Catch:{ all -> 0x0027 }
        r6 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1));
        if (r6 == 0) goto L_0x0025;
    L_0x001f:
        r4 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1));
        if (r4 == 0) goto L_0x0025;
    L_0x0023:
        r4 = r8;
        goto L_0x0029;
    L_0x0025:
        r4 = r8;
        goto L_0x0007;
    L_0x0027:
        r1 = move-exception;
        goto L_0x002d;
    L_0x0029:
        r10.zzclt = r4;	 Catch:{ all -> 0x0027 }
        monitor-exit(r0);	 Catch:{ all -> 0x0027 }
        return;
    L_0x002d:
        monitor-exit(r0);	 Catch:{ all -> 0x0027 }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzwv.zzqd():void");
    }

    public final long getValue() {
        return this.zzclt;
    }
}
