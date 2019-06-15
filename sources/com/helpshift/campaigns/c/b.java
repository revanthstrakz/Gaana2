package com.helpshift.campaigns.c;

import android.annotation.TargetApi;
import com.helpshift.campaigns.l.i;
import com.helpshift.campaigns.l.m;
import com.helpshift.d.c;
import com.helpshift.d.e;
import com.helpshift.q.d;
import com.helpshift.q.f;
import java.util.concurrent.TimeUnit;

public class b {
    public final c a;
    public final e b;
    public final f c;
    public final g d;
    public final a e;
    public final d f;
    public com.helpshift.campaigns.a g;

    private static class a {
        static final b a = new b();
    }

    @TargetApi(9)
    b() {
        com.helpshift.o.a aVar;
        d dVar = f.a().a;
        c cVar = com.helpshift.d.a.a().b;
        e eVar = com.helpshift.d.a.a().a;
        com.helpshift.k.c cVar2 = com.helpshift.k.b.a().b;
        com.helpshift.k.a aVar2 = com.helpshift.k.b.a().a;
        i iVar = m.a().a;
        eVar.a(new com.helpshift.o.b(5, TimeUnit.SECONDS, "data_type_switch_user"));
        this.c = new f(cVar, eVar, dVar, cVar2);
        if (cVar.b()) {
            aVar = new com.helpshift.o.a(4, "data_type_device");
        } else {
            aVar = new com.helpshift.o.b(5, TimeUnit.SECONDS, "data_type_device");
        }
        com.helpshift.o.d dVar2 = aVar;
        eVar.a(dVar2);
        this.a = new c(cVar, eVar, this.c, new com.helpshift.campaigns.models.d(new com.helpshift.campaigns.models.a(), m.a().a, new com.helpshift.util.a.c(false)), dVar2, cVar2, aVar2);
        eVar.a(new com.helpshift.o.b(5, TimeUnit.SECONDS, "data_type_analytics_event"));
        this.e = new a(dVar, new com.helpshift.util.a.c(false), eVar);
        eVar.a(new com.helpshift.o.c(1, 24, TimeUnit.HOURS, "data_type_session"));
        this.b = new e(eVar, new com.helpshift.util.a.c(false), m.a().b, Integer.valueOf(com.helpshift.common.domain.network.i.c));
        eVar.a(new com.helpshift.o.c(1, 24, TimeUnit.HOURS, "data_type_user"));
        this.d = new g(eVar, this.b, this.c, new com.helpshift.util.a.c(false), iVar, Integer.valueOf(com.helpshift.common.domain.network.i.c), cVar2);
        this.f = new d(m.a().c, m.a().d, this.d, dVar);
    }

    public static b a() {
        return a.a;
    }
}
