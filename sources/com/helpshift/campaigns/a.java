package com.helpshift.campaigns;

import com.helpshift.campaigns.d.b;
import com.helpshift.campaigns.i.f;
import com.helpshift.campaigns.l.d;
import com.helpshift.campaigns.models.e;
import java.util.List;

public class a implements f {
    private static a e;
    private d a;
    private List<? extends e> b;
    private com.helpshift.campaigns.d.a c;
    private b d;

    private List<? extends e> b() {
        return com.helpshift.campaigns.m.b.a(this.a, com.helpshift.campaigns.c.b.a().d.a().a);
    }

    public b a() {
        return this.d;
    }

    public void a(com.helpshift.campaigns.models.b bVar) {
        this.b = b();
        if (this.c != null) {
            this.c.a((e) bVar);
        }
    }

    public void a(String str) {
        this.b = b();
        if (this.c != null) {
            this.c.a(str);
        }
    }

    public void b(String str) {
        this.b = b();
        if (this.c != null) {
            this.c.b(str);
        }
    }

    public void c(String str) {
        this.b = b();
        if (this.c != null) {
            this.c.c(str);
        }
    }

    public void d(String str) {
        this.b = b();
        if (this.c != null) {
            this.c.e(str);
        }
    }

    public void e(String str) {
        this.b = b();
        if (this.c != null) {
            this.c.d(str);
        }
    }
}
