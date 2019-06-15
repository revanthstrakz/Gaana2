package com.helpshift.campaigns.h;

import com.helpshift.common.domain.g;
import com.helpshift.network.a.b;
import com.helpshift.network.a.c;
import com.helpshift.network.f;
import com.helpshift.util.o;
import java.util.concurrent.Executors;

public class d {
    public b a;
    public c b;
    private e c;
    private g d;
    private f e;
    private a f;

    private static class a {
        static final d a = new d();
    }

    d() {
        c a = b.a(new com.helpshift.network.a(new f()), com.helpshift.network.a.c.a.a, Executors.newCachedThreadPool(new g("cmdat-sy")));
        com.helpshift.campaigns.c.b a2 = com.helpshift.campaigns.c.b.a();
        com.helpshift.util.d dVar = new com.helpshift.util.d(4, 8, o.b());
        c cVar = a;
        com.helpshift.util.d dVar2 = dVar;
        this.c = new e(a2.b, a2.d, com.helpshift.d.a.a().b, cVar, dVar2);
        this.a = new b(a2.a, a);
        this.d = new g(a2.d, com.helpshift.d.a.a().b, a, dVar);
        this.e = new f(a2.c, com.helpshift.d.a.a().b, a, dVar);
        this.f = new a(a2.e, com.helpshift.d.a.a().b, a2.d, cVar, dVar2);
        this.b = new c(a2.f, a);
    }

    public static d a() {
        return a.a;
    }
}
