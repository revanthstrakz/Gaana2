package com.helpshift.campaigns.h;

import com.helpshift.campaigns.c.g;
import com.helpshift.network.a.c;
import com.helpshift.network.i;
import com.helpshift.util.d;
import com.helpshift.util.l;

public class a extends com.helpshift.h.a {
    private i a;
    private g b;
    private c c;
    private d d;
    private com.helpshift.d.c e;

    public boolean a() {
        return false;
    }

    public a(com.helpshift.campaigns.c.a aVar, com.helpshift.d.c cVar, g gVar, c cVar2, d dVar) {
        super("data_type_analytics_event");
        this.a = aVar;
        aVar.c.a(this);
        this.e = cVar;
        this.b = gVar;
        this.c = cVar2;
        this.d = dVar;
    }

    public void b() {
        if (this.e.a(this.b.a().a)) {
            this.a.a(Integer.valueOf(this.d.a()));
            com.helpshift.network.a.a d = this.a.d();
            if (d != null) {
                l.a("Helpshift_AENewtork", "Syncing analytics events properties");
                this.c.a(d);
            }
        }
    }
}
