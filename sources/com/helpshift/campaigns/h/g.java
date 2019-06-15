package com.helpshift.campaigns.h;

import com.helpshift.h.a;
import com.helpshift.network.a.c;
import com.helpshift.network.i;
import com.helpshift.util.d;
import com.helpshift.util.l;

public class g extends a {
    private i a;
    private c b;
    private d c;
    private com.helpshift.d.c d;

    public boolean a() {
        return true;
    }

    protected g(com.helpshift.campaigns.c.g gVar, com.helpshift.d.c cVar, c cVar2, d dVar) {
        super("data_type_user");
        gVar.a.a(this);
        this.a = gVar;
        this.d = cVar;
        this.b = cVar2;
        this.c = dVar;
    }

    private boolean f() {
        return this.d.a(((com.helpshift.campaigns.c.g) this.a).a().a);
    }

    public void b() {
        if (f()) {
            this.a.a(Integer.valueOf(this.c.a()));
            com.helpshift.network.a.a d = this.a.d();
            if (d != null) {
                l.a("Helpshift_UPNetwork", "Syncing user properties");
                this.b.a(d);
            }
        }
    }

    public void d() {
        if (f()) {
            this.a.a(Integer.valueOf(this.c.a()));
            com.helpshift.network.a.a e = this.a.e();
            if (e != null) {
                l.a("Helpshift_UPNetwork", "Full sync user properties");
                this.b.a(e);
            }
        }
    }
}
