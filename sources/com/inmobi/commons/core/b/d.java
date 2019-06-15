package com.inmobi.commons.core.b;

import android.support.annotation.NonNull;
import com.inmobi.commons.core.c.a;
import com.inmobi.commons.core.c.b;
import com.payu.custombrowser.util.CBConstant;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class d implements b {
    private static final String a = "d";
    private AtomicBoolean b = new AtomicBoolean(false);
    private AtomicBoolean c = new AtomicBoolean(false);
    private b d;
    private e e;
    private HashMap<String, a> f = new HashMap(1);
    private List<String> g = new LinkedList();
    private ScheduledExecutorService h;

    public d(@NonNull b bVar, @NonNull e eVar, @NonNull a aVar) {
        this.d = bVar;
        this.e = eVar;
        a(aVar);
    }

    public final void a() {
        if (this.h != null) {
            this.h.shutdownNow();
            this.h = null;
        }
        this.b.set(false);
        this.c.set(true);
        this.g.clear();
        this.f.clear();
    }

    @NonNull
    private a b(@NonNull String str) {
        return (a) this.f.get(str);
    }

    private void a(String str, com.inmobi.commons.core.utilities.uid.d dVar) {
        if (!this.c.get() && !this.b.get()) {
            int i;
            long j;
            this.d.b(b(str).a, str);
            int a = this.d.a(str);
            int a2 = com.inmobi.commons.core.utilities.b.b.a();
            if (a2 != 1) {
                i = b(str).i;
            } else {
                i = b(str).g;
            }
            if (a2 != 1) {
                j = b(str).j;
            } else {
                j = b(str).h;
            }
            if (i <= a || this.d.a(b(str).c, str)) {
                c a3 = this.e.a(str);
                if (a3 != null) {
                    this.b.set(true);
                    a b = b(str);
                    int i2 = b.d + 1;
                    a.a().a(a3, b.e, i2, i2, j, dVar, this);
                }
            }
        }
    }

    public final void a(c cVar) {
        String b = this.d.b(((Integer) cVar.a.get(0)).intValue());
        this.d.a(cVar.a);
        if (b != null) {
            this.d.c(System.currentTimeMillis(), b);
            this.b.set(false);
        }
    }

    public final void a(c cVar, boolean z) {
        String b = this.d.b(((Integer) cVar.a.get(0)).intValue());
        if (cVar.c && z) {
            this.d.a(cVar.a);
        }
        if (b != null) {
            this.d.c(System.currentTimeMillis(), b);
            this.b.set(false);
        }
    }

    public final void a(@NonNull a aVar) {
        Object obj = aVar.b;
        if (obj == null) {
            obj = CBConstant.DEFAULT_VALUE;
        }
        this.f.put(obj, aVar);
    }

    public final void a(@NonNull String str) {
        if (!this.c.get()) {
            if (str == null) {
                str = CBConstant.DEFAULT_VALUE;
            }
            long j = b(str).f;
            if (j <= 0) {
                a(str, null);
            } else if (!this.g.contains(str)) {
                this.g.add(str);
                if (this.h == null) {
                    this.h = Executors.newSingleThreadScheduledExecutor();
                }
                ScheduledExecutorService scheduledExecutorService = this.h;
                AnonymousClass1 anonymousClass1 = new Runnable() {
                    final /* synthetic */ com.inmobi.commons.core.utilities.uid.d b = null;

                    public final void run() {
                        d.this.a(str, this.b);
                    }
                };
                a b = b(str);
                long b2 = this.d.b(str);
                if (b2 == -1) {
                    this.d.c(System.currentTimeMillis(), str);
                }
                long toSeconds = TimeUnit.MILLISECONDS.toSeconds(b2) + b.f;
                scheduledExecutorService.scheduleAtFixedRate(anonymousClass1, toSeconds - TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) > 0 ? toSeconds - TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) : 0, j, TimeUnit.SECONDS);
            }
        }
    }
}
