package com.bumptech.glide.request;

public final class a implements c, d {
    private final d a;
    private c b;
    private c c;

    public a(d dVar) {
        this.a = dVar;
    }

    public void a(c cVar, c cVar2) {
        this.b = cVar;
        this.c = cVar2;
    }

    public void a() {
        if (!this.b.d()) {
            this.b.a();
        }
    }

    public void b() {
        if (!this.b.h()) {
            this.b.b();
        }
        if (this.c.d()) {
            this.c.b();
        }
    }

    public void c() {
        if (this.b.h()) {
            this.c.c();
        } else {
            this.b.c();
        }
    }

    public boolean d() {
        return (this.b.h() ? this.c : this.b).d();
    }

    public boolean e() {
        return (this.b.h() ? this.c : this.b).e();
    }

    public boolean f() {
        return (this.b.h() ? this.c : this.b).f();
    }

    public boolean g() {
        return (this.b.h() ? this.c : this.b).g();
    }

    public boolean h() {
        return this.b.h() && this.c.h();
    }

    public void i() {
        this.b.i();
        this.c.i();
    }

    public boolean a(c cVar) {
        boolean z = false;
        if (!(cVar instanceof a)) {
            return false;
        }
        a aVar = (a) cVar;
        if (this.b.a(aVar.b) && this.c.a(aVar.c)) {
            z = true;
        }
        return z;
    }

    public boolean b(c cVar) {
        return k() && f(cVar);
    }

    private boolean k() {
        return this.a == null || this.a.b(this);
    }

    public boolean c(c cVar) {
        return l() && f(cVar);
    }

    private boolean l() {
        return this.a == null || this.a.c(this);
    }

    private boolean f(c cVar) {
        return cVar.equals(this.b) || (this.b.h() && cVar.equals(this.c));
    }

    public boolean j() {
        return m() || f();
    }

    private boolean m() {
        return this.a != null && this.a.j();
    }

    public void d(c cVar) {
        if (this.a != null) {
            this.a.d(this);
        }
    }

    public void e(c cVar) {
        if (cVar.equals(this.c)) {
            if (this.a != null) {
                this.a.e(this.c);
            }
            return;
        }
        if (!this.c.d()) {
            this.c.a();
        }
    }
}
