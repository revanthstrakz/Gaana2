package com.til.colombia.android.commons.a;

import java.util.concurrent.Callable;

final class c implements Callable<Void> {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public final /* synthetic */ Object call() throws Exception {
        return a();
    }

    /* JADX WARNING: Missing block: B:11:0x0027, code skipped:
            return null;
     */
    private java.lang.Void a() throws java.lang.Exception {
        /*
        r4 = this;
        r0 = r4.a;
        monitor-enter(r0);
        r1 = r4.a;	 Catch:{ all -> 0x0028 }
        r1 = r1.u;	 Catch:{ all -> 0x0028 }
        r2 = 0;
        if (r1 != 0) goto L_0x000e;
    L_0x000c:
        monitor-exit(r0);	 Catch:{ all -> 0x0028 }
        return r2;
    L_0x000e:
        r1 = r4.a;	 Catch:{ all -> 0x0028 }
        r1.m();	 Catch:{ all -> 0x0028 }
        r1 = r4.a;	 Catch:{ all -> 0x0028 }
        r1 = r1.j();	 Catch:{ all -> 0x0028 }
        if (r1 == 0) goto L_0x0026;
    L_0x001b:
        r1 = r4.a;	 Catch:{ all -> 0x0028 }
        r1.g();	 Catch:{ all -> 0x0028 }
        r1 = r4.a;	 Catch:{ all -> 0x0028 }
        r3 = 0;
        r1.w = 0;	 Catch:{ all -> 0x0028 }
    L_0x0026:
        monitor-exit(r0);	 Catch:{ all -> 0x0028 }
        return r2;
    L_0x0028:
        r1 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0028 }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.commons.a.c.a():java.lang.Void");
    }
}
