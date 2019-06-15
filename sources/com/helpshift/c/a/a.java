package com.helpshift.c.a;

import com.helpshift.common.domain.e;
import com.helpshift.common.domain.network.d;
import com.helpshift.common.domain.network.f;
import com.helpshift.common.exception.RootAPIException;
import com.helpshift.common.platform.network.h;
import com.helpshift.common.platform.p;
import com.helpshift.util.l;
import java.util.HashMap;
import java.util.Map;

public class a {
    private final Object a = new Object();
    private com.helpshift.c.b.a b;
    private e c;
    private p d;
    private h e;

    public a(e eVar, p pVar) {
        this.c = eVar;
        this.d = pVar;
        this.e = pVar.j();
    }

    public com.helpshift.c.b.a a() {
        if (this.b == null) {
            c();
        }
        return this.b;
    }

    public com.helpshift.c.b.a b() {
        c();
        return this.b;
    }

    private void c() {
        this.b = null;
        synchronized (this.a) {
            if (this.b == null) {
                l.a("Helpshift_WebSocketAuthDM", "Fetching auth token");
                try {
                    this.b = this.e.l(new f(new d("/ws-config/", this.c, this.d)).c(d()).b);
                    l.a("Helpshift_WebSocketAuthDM", "Auth token fetch successful");
                } catch (RootAPIException e) {
                    l.c("Helpshift_WebSocketAuthDM", "Exception in fetching auth token", e);
                    this.b = null;
                }
            }
        }
    }

    private Map<String, String> d() {
        HashMap hashMap = new HashMap();
        hashMap.put("platform-id", this.d.c());
        return hashMap;
    }
}
