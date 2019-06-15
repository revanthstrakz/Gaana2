package com.google.ads.interactivemedia.v3.internal;

import android.view.View;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class g extends c {
    private final e a;
    private final d b;
    private final List<ar> c = new ArrayList();
    private ar d;
    private y e;
    private boolean f = false;
    private boolean g = false;
    private String h;

    g(d dVar, e eVar) {
        this.b = dVar;
        this.a = eVar;
        this.h = UUID.randomUUID().toString();
        e(null);
        if (eVar.f() == f.HTML) {
            this.e = new z(eVar.c());
        } else {
            this.e = new aa(eVar.b(), eVar.e());
        }
        this.e.a();
        p.a().a(this);
        this.e.a(dVar);
    }

    public void a() {
        if (!this.f) {
            this.f = true;
            p.a().b(this);
            this.e.a(t.a().d());
            this.e.a(this, this.a);
        }
    }

    public void a(View view) {
        if (!this.g) {
            af.a((Object) view, "AdView is null");
            if (g() != view) {
                e(view);
                e().f();
                f(view);
            }
        }
    }

    public void b() {
        if (!this.g) {
            this.d.clear();
            c();
            this.g = true;
            e().e();
            p.a().c(this);
            e().b();
            this.e = null;
        }
    }

    public void b(View view) {
        if (!this.g) {
            d(view);
            if (c(view) == null) {
                this.c.add(new ar(view));
            }
        }
    }

    public void c() {
        if (!this.g) {
            this.c.clear();
        }
    }

    private ar c(View view) {
        for (ar arVar : this.c) {
            if (arVar.get() == view) {
                return arVar;
            }
        }
        return null;
    }

    public List<ar> d() {
        return this.c;
    }

    private void d(View view) {
        if (view == null) {
            throw new IllegalArgumentException("FriendlyObstruction is null");
        }
    }

    public y e() {
        return this.e;
    }

    public String f() {
        return this.h;
    }

    public View g() {
        return (View) this.d.get();
    }

    private void e(View view) {
        this.d = new ar(view);
    }

    public boolean h() {
        return this.f && !this.g;
    }

    private void f(View view) {
        Collection<g> b = p.a().b();
        if (b != null && b.size() > 0) {
            for (g gVar : b) {
                if (gVar != this && gVar.g() == view) {
                    gVar.d.clear();
                }
            }
        }
    }
}
