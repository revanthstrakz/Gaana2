package com.helpshift.campaigns.h;

import android.support.annotation.NonNull;
import com.helpshift.d.c;
import com.helpshift.h.a;
import com.helpshift.network.i;
import com.helpshift.util.d;
import com.helpshift.util.l;
import java.util.HashSet;
import java.util.Set;

public class f extends a {
    private i a;
    private c b;
    private com.helpshift.network.a.c c;
    private d d;
    private Set<String> e;

    public boolean a() {
        return false;
    }

    protected f(com.helpshift.campaigns.c.f fVar, c cVar, com.helpshift.network.a.c cVar2, d dVar) {
        super("data_type_switch_user");
        fVar.a.a(this);
        this.a = fVar;
        this.b = cVar;
        this.c = cVar2;
        this.d = dVar;
        f();
    }

    private void f() {
        this.e = new HashSet();
        this.e.add("data_type_analytics_event");
        this.e.add("data_type_user");
    }

    @NonNull
    public Set<String> c() {
        return this.e;
    }

    public void b() {
        if (this.b.b()) {
            this.a.a(Integer.valueOf(this.d.a()));
            com.helpshift.network.a.a d = this.a.d();
            if (d != null) {
                l.a("Helpshift_SUNetwork", "Syncing switch user");
                this.c.a(d);
            }
        }
    }
}
