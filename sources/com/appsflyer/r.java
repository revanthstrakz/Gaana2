package com.appsflyer;

import android.content.Context;
import android.support.annotation.NonNull;
import java.lang.ref.WeakReference;

final class r {
    private final Object a;
    private long b;
    private String c;

    r() {
    }

    static void a(Context context) {
        context = context.getApplicationContext();
        AFLogger.d("onBecameBackground");
        h.c().b();
        AFLogger.d("callStatsBackground background call");
        h.c().a(new WeakReference(context));
        ah a = ah.a();
        if (a.f()) {
            a.c();
            if (context != null) {
                ah.a(context.getPackageName(), context.getPackageManager());
            }
            a.d();
        } else {
            AFLogger.c("RD status is OFF");
        }
        a.a().d();
    }

    r(long j, String str) {
        this.a = new Object();
        this.b = 0;
        this.c = "";
        this.b = j;
        this.c = str;
    }

    r(String str) {
        this(System.currentTimeMillis(), str);
    }

    @NonNull
    static r a(String str) {
        if (str == null) {
            return new r(0, "");
        }
        String[] split = str.split(",");
        if (split.length < 2) {
            return new r(0, "");
        }
        return new r(Long.parseLong(split[0]), split[1]);
    }

    /* Access modifiers changed, original: final */
    public final boolean a(r rVar) {
        return a(rVar.b, rVar.c);
    }

    /* JADX WARNING: Missing block: B:19:0x0028, code skipped:
            return false;
     */
    private boolean a(long r8, java.lang.String r10) {
        /*
        r7 = this;
        r0 = r7.a;
        monitor-enter(r0);
        r1 = 0;
        if (r10 == 0) goto L_0x0027;
    L_0x0006:
        r2 = r7.c;	 Catch:{ all -> 0x0024 }
        r2 = r10.equals(r2);	 Catch:{ all -> 0x0024 }
        if (r2 != 0) goto L_0x0027;
    L_0x000e:
        r2 = r7.b;	 Catch:{ all -> 0x0024 }
        r4 = r8 - r2;
        r2 = 2000; // 0x7d0 float:2.803E-42 double:9.88E-321;
        r6 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
        r2 = 1;
        if (r6 <= 0) goto L_0x001b;
    L_0x0019:
        r3 = r2;
        goto L_0x001c;
    L_0x001b:
        r3 = r1;
    L_0x001c:
        if (r3 == 0) goto L_0x0027;
    L_0x001e:
        r7.b = r8;	 Catch:{ all -> 0x0024 }
        r7.c = r10;	 Catch:{ all -> 0x0024 }
        monitor-exit(r0);	 Catch:{ all -> 0x0024 }
        return r2;
    L_0x0024:
        r8 = move-exception;
        monitor-exit(r0);
        throw r8;
    L_0x0027:
        monitor-exit(r0);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.r.a(long, java.lang.String):boolean");
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.b);
        stringBuilder.append(",");
        stringBuilder.append(this.c);
        return stringBuilder.toString();
    }

    /* Access modifiers changed, original: final */
    public final String a() {
        return this.c;
    }
}
