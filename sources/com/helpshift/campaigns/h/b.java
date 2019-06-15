package com.helpshift.campaigns.h;

import com.helpshift.h.a;
import com.helpshift.network.a.c;
import com.helpshift.network.i;
import com.helpshift.util.l;
import java.util.HashSet;
import java.util.Set;

public class b extends a {
    private i a;
    private c b;
    private Set<String> c;

    public boolean a() {
        return true;
    }

    protected b(com.helpshift.campaigns.c.c cVar, c cVar2) {
        super("data_type_device");
        cVar.a.a(this);
        this.a = cVar;
        this.b = cVar2;
        f();
    }

    private void f() {
        this.c = new HashSet();
        this.c.add("data_type_switch_user");
        this.c.add("data_type_analytics_event");
        this.c.add("data_type_user");
    }

    public Set<String> c() {
        return this.c;
    }

    public void b() {
        com.helpshift.network.a.a d = this.a.d();
        if (d != null) {
            l.a("Helpshift_DPNetwork", "Syncing device properties");
            this.b.a(d);
        }
    }

    public void d() {
        com.helpshift.network.a.a e = this.a.e();
        if (e != null) {
            l.a("Helpshift_DPNetwork", "Full sync device properties");
            this.b.a(e);
        }
    }
}
