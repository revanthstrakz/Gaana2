package com.appsflyer;

import android.os.Build.VERSION;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

public class a {
    private static a a;
    private Executor b;
    private ScheduledExecutorService c;

    private a() {
    }

    public static a a() {
        if (a == null) {
            a = new a();
        }
        return a;
    }

    public Executor b() {
        Object obj = (this.b == null || ((this.b instanceof ThreadPoolExecutor) && (((ThreadPoolExecutor) this.b).isShutdown() || ((ThreadPoolExecutor) this.b).isTerminated() || ((ThreadPoolExecutor) this.b).isTerminating()))) ? 1 : null;
        if (obj != null) {
            if (VERSION.SDK_INT < 11) {
                return Executors.newSingleThreadExecutor();
            }
            this.b = Executors.newFixedThreadPool(2);
        }
        return this.b;
    }

    /* Access modifiers changed, original: final */
    public final ScheduledThreadPoolExecutor c() {
        Object obj = (this.c == null || this.c.isShutdown() || this.c.isTerminated()) ? 1 : null;
        if (obj != null) {
            this.c = Executors.newScheduledThreadPool(2);
        }
        return (ScheduledThreadPoolExecutor) this.c;
    }

    /* Access modifiers changed, original: final */
    public final void d() {
        try {
            a(this.c);
            if (this.b instanceof ThreadPoolExecutor) {
                a((ThreadPoolExecutor) this.b);
            }
        } catch (Throwable th) {
            AFLogger.a("failed to stop Executors", th);
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0020 */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:9:?, code skipped:
            com.appsflyer.AFLogger.a("InterruptedException!!!");
     */
    /* JADX WARNING: Missing block: B:10:0x0033, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:12:0x0038, code skipped:
            if (r3.isTerminated() == false) goto L_0x003a;
     */
    /* JADX WARNING: Missing block: B:13:0x003a, code skipped:
            com.appsflyer.AFLogger.a("killing non-finished tasks");
     */
    /* JADX WARNING: Missing block: B:14:0x003f, code skipped:
            r3.shutdownNow();
     */
    private static void a(java.util.concurrent.ExecutorService r3) {
        /*
        r0 = "shut downing executor ...";
        com.appsflyer.AFLogger.a(r0);	 Catch:{ InterruptedException -> 0x0020 }
        r3.shutdown();	 Catch:{ InterruptedException -> 0x0020 }
        r0 = 10;
        r2 = java.util.concurrent.TimeUnit.SECONDS;	 Catch:{ InterruptedException -> 0x0020 }
        r3.awaitTermination(r0, r2);	 Catch:{ InterruptedException -> 0x0020 }
        r0 = r3.isTerminated();
        if (r0 != 0) goto L_0x001a;
    L_0x0015:
        r0 = "killing non-finished tasks";
        com.appsflyer.AFLogger.a(r0);
    L_0x001a:
        r3.shutdownNow();
        return;
    L_0x001e:
        r0 = move-exception;
        goto L_0x0034;
    L_0x0020:
        r0 = "InterruptedException!!!";
        com.appsflyer.AFLogger.a(r0);	 Catch:{ all -> 0x001e }
        r0 = r3.isTerminated();
        if (r0 != 0) goto L_0x0030;
    L_0x002b:
        r0 = "killing non-finished tasks";
        com.appsflyer.AFLogger.a(r0);
    L_0x0030:
        r3.shutdownNow();
        return;
    L_0x0034:
        r1 = r3.isTerminated();
        if (r1 != 0) goto L_0x003f;
    L_0x003a:
        r1 = "killing non-finished tasks";
        com.appsflyer.AFLogger.a(r1);
    L_0x003f:
        r3.shutdownNow();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.a.a(java.util.concurrent.ExecutorService):void");
    }
}
