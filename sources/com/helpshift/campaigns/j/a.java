package com.helpshift.campaigns.j;

import android.support.annotation.Nullable;
import com.helpshift.common.b.c;
import com.helpshift.common.b.c.b;
import com.helpshift.common.domain.g;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class a extends com.helpshift.m.a {
    private final c a = new com.helpshift.common.b.c.a().a(com.helpshift.common.b.a.a(3, TimeUnit.MINUTES)).b(com.helpshift.common.b.a.a(3, TimeUnit.MINUTES)).a(0.0f).b(1.0f).a();
    private final c b = new com.helpshift.common.b.c.a().a(com.helpshift.common.b.a.a(5, TimeUnit.SECONDS)).b(com.helpshift.common.b.a.a(10, TimeUnit.MINUTES)).a(b.a).a();

    public a(Callable callable) {
        super(callable, Executors.newSingleThreadExecutor(new g("cmpoll-a")), Executors.newSingleThreadScheduledExecutor(new g("cmpoll-b")));
    }

    @Nullable
    public com.helpshift.common.b.a a(Object obj) {
        this.b.a();
        long a = this.a.a(200);
        return a != -100 ? com.helpshift.common.b.a.a(a, TimeUnit.MILLISECONDS) : null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x002a  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    @android.support.annotation.Nullable
    public com.helpshift.common.b.a a(java.lang.Exception r6) {
        /*
        r5 = this;
        r0 = r5.a;
        r0.a();
        r0 = r6 instanceof com.helpshift.network.errors.NetworkError;
        r1 = -100;
        if (r0 == 0) goto L_0x001e;
    L_0x000b:
        r6 = (com.helpshift.network.errors.NetworkError) r6;
        r6 = r6.a();
        if (r6 == 0) goto L_0x001e;
    L_0x0013:
        r0 = r5.b;
        r6 = r6.intValue();
        r3 = r0.a(r6);
        goto L_0x001f;
    L_0x001e:
        r3 = r1;
    L_0x001f:
        r6 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1));
        if (r6 == 0) goto L_0x002a;
    L_0x0023:
        r6 = java.util.concurrent.TimeUnit.MILLISECONDS;
        r6 = com.helpshift.common.b.a.a(r3, r6);
        return r6;
    L_0x002a:
        r6 = 0;
        return r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.campaigns.j.a.a(java.lang.Exception):com.helpshift.common.b.a");
    }
}
