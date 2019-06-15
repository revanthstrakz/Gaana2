package com.til.colombia.android.network;

public final class c implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ a b;

    public c(a aVar, String str) {
        this.b = aVar;
        this.a = str;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x0050 */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    public final void run() {
        /*
        r6 = this;
        r0 = r6.b;
        r0 = r0.b;
        monitor-enter(r0);
        r1 = r6.b;	 Catch:{ Exception -> 0x0050 }
        r1 = r1.b;	 Catch:{ Exception -> 0x0050 }
        r2 = r6.a;	 Catch:{ Exception -> 0x0050 }
        r1.add(r2);	 Catch:{ Exception -> 0x0050 }
        r1 = r6.b;	 Catch:{ Exception -> 0x0050 }
        r1 = r1.b;	 Catch:{ Exception -> 0x0050 }
        r1 = r1.size();	 Catch:{ Exception -> 0x0050 }
        if (r1 <= 0) goto L_0x0050;
    L_0x0018:
        r1 = r6.b;	 Catch:{ Exception -> 0x0050 }
        r1 = r1.c;	 Catch:{ Exception -> 0x0050 }
        r1 = r1.isEmpty();	 Catch:{ Exception -> 0x0050 }
        if (r1 == 0) goto L_0x0050;
    L_0x0022:
        r1 = r6.b;	 Catch:{ Exception -> 0x0050 }
        r2 = r1.a;	 Catch:{ Exception -> 0x0050 }
        if (r2 == 0) goto L_0x0038;
    L_0x0028:
        r2 = r1.a;	 Catch:{ Exception -> 0x0050 }
        r2 = r2.isShutdown();	 Catch:{ Exception -> 0x0050 }
        if (r2 != 0) goto L_0x0038;
    L_0x0030:
        r2 = r1.a;	 Catch:{ Exception -> 0x0050 }
        r2 = r2.isTerminated();	 Catch:{ Exception -> 0x0050 }
        if (r2 == 0) goto L_0x003f;
    L_0x0038:
        r2 = 1;
        r2 = java.util.concurrent.Executors.newScheduledThreadPool(r2);	 Catch:{ Exception -> 0x0050 }
        r1.a = r2;	 Catch:{ Exception -> 0x0050 }
    L_0x003f:
        r2 = r1.a;	 Catch:{ Exception -> 0x0050 }
        r3 = new com.til.colombia.android.network.b;	 Catch:{ Exception -> 0x0050 }
        r3.<init>(r1);	 Catch:{ Exception -> 0x0050 }
        r4 = 3000; // 0xbb8 float:4.204E-42 double:1.482E-320;
        r1 = java.util.concurrent.TimeUnit.MILLISECONDS;	 Catch:{ Exception -> 0x0050 }
        r2.schedule(r3, r4, r1);	 Catch:{ Exception -> 0x0050 }
        goto L_0x0050;
    L_0x004e:
        r1 = move-exception;
        goto L_0x0052;
    L_0x0050:
        monitor-exit(r0);	 Catch:{ all -> 0x004e }
        return;
    L_0x0052:
        monitor-exit(r0);	 Catch:{ all -> 0x004e }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.network.c.run():void");
    }
}
