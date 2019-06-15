package com.inmobi.a;

import android.content.Context;
import com.inmobi.a.p.a;
import java.util.List;

public class g {
    private static final String a = "g";
    private a b;
    private boolean c = false;
    private a d = new a();
    private e e = new e();

    g() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002f  */
    public final synchronized void a(com.inmobi.a.p.a r8) {
        /*
        r7 = this;
        monitor-enter(r7);
        r7.b = r8;	 Catch:{ all -> 0x0040 }
        r8 = r7.c;	 Catch:{ all -> 0x0040 }
        if (r8 != 0) goto L_0x003e;
    L_0x0007:
        r8 = r7.d;	 Catch:{ all -> 0x0040 }
        r8 = r8.a;	 Catch:{ all -> 0x0040 }
        r0 = "carb_last_update_ts";
        r1 = 0;
        r3 = r8.b(r0, r1);	 Catch:{ all -> 0x0040 }
        r8 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1));
        r0 = 1;
        if (r8 != 0) goto L_0x001a;
    L_0x0018:
        r8 = r0;
        goto L_0x002d;
    L_0x001a:
        r1 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0040 }
        r5 = r1 - r3;
        r8 = r7.b;	 Catch:{ all -> 0x0040 }
        r8 = r8.d;	 Catch:{ all -> 0x0040 }
        r8 = r8 * 1000;
        r1 = (long) r8;	 Catch:{ all -> 0x0040 }
        r8 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1));
        if (r8 < 0) goto L_0x002c;
    L_0x002b:
        goto L_0x0018;
    L_0x002c:
        r8 = 0;
    L_0x002d:
        if (r8 == 0) goto L_0x003e;
    L_0x002f:
        r7.c = r0;	 Catch:{ all -> 0x0040 }
        r8 = new java.lang.Thread;	 Catch:{ all -> 0x0040 }
        r0 = new com.inmobi.a.g$1;	 Catch:{ all -> 0x0040 }
        r0.<init>();	 Catch:{ all -> 0x0040 }
        r8.<init>(r0);	 Catch:{ all -> 0x0040 }
        r8.start();	 Catch:{ all -> 0x0040 }
    L_0x003e:
        monitor-exit(r7);
        return;
    L_0x0040:
        r8 = move-exception;
        monitor-exit(r7);
        throw r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.a.g.a(com.inmobi.a.p$a):void");
    }

    private static boolean a(String str) {
        Context b = com.inmobi.commons.a.a.b();
        boolean z = false;
        if (b == null) {
            return false;
        }
        try {
            b.getPackageManager().getPackageInfo(str, 256);
            z = true;
        } catch (Exception unused) {
        }
        return z;
    }

    static /* synthetic */ void a(g gVar, c cVar, List list) {
        f fVar = new f(gVar.b.c, gVar.b.e, gVar.b.f, o.a().d(), list, cVar);
        fVar.r = gVar.b.g * 1000;
        fVar.s = gVar.b.g * 1000;
        e.a(fVar);
    }
}
