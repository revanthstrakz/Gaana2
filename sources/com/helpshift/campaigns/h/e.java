package com.helpshift.campaigns.h;

import com.helpshift.campaigns.c.g;
import com.helpshift.h.a;
import com.helpshift.network.a.c;
import com.helpshift.network.i;
import com.helpshift.util.d;
import com.helpshift.util.l;

public class e extends a {
    private i a;
    private c b;
    private d c;
    private com.helpshift.d.c d;
    private g e;

    public boolean a() {
        return false;
    }

    protected e(com.helpshift.campaigns.c.e eVar, g gVar, com.helpshift.d.c cVar, c cVar2, d dVar) {
        super("data_type_session");
        this.a = eVar;
        eVar.a.a(this);
        this.d = cVar;
        this.e = gVar;
        this.b = cVar2;
        this.c = dVar;
    }

    public void b() {
        if (this.d.b(this.e.a().a)) {
            this.a.a(Integer.valueOf(this.c.a()));
            com.helpshift.network.a.a d = this.a.d();
            if (d != null) {
                l.a("Helpshift_SessionNtwrk", "Syncing sessions");
                this.b.a(d);
            }
        }
    }
}
