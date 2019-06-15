package com.bumptech.glide.request;

import android.support.annotation.Nullable;

public class h implements c, d {
    private c a;
    private c b;
    @Nullable
    private d c;
    private boolean d;

    public h() {
        this(null);
    }

    public h(d dVar) {
        this.c = dVar;
    }

    public void a(c cVar, c cVar2) {
        this.a = cVar;
        this.b = cVar2;
    }

    public boolean b(c cVar) {
        return k() && (cVar.equals(this.a) || !this.a.f());
    }

    private boolean k() {
        return this.c == null || this.c.b(this);
    }

    public boolean c(c cVar) {
        return l() && cVar.equals(this.a) && !j();
    }

    private boolean l() {
        return this.c == null || this.c.c(this);
    }

    public boolean j() {
        return m() || f();
    }

    public void d(c cVar) {
        if (!cVar.equals(this.b)) {
            if (this.c != null) {
                this.c.d(this);
            }
            if (!this.b.e()) {
                this.b.c();
            }
        }
    }

    public void e(c cVar) {
        if (cVar.equals(this.a) && this.c != null) {
            this.c.e(this);
        }
    }

    private boolean m() {
        return this.c != null && this.c.j();
    }

    public void a() {
        this.d = true;
        if (!this.b.d()) {
            this.b.a();
        }
        if (this.d && !this.a.d()) {
            this.a.a();
        }
    }

    public void b() {
        this.d = false;
        this.a.b();
        this.b.b();
    }

    public void c() {
        this.d = false;
        this.b.c();
        this.a.c();
    }

    public boolean d() {
        return this.a.d();
    }

    public boolean e() {
        return this.a.e() || this.b.e();
    }

    public boolean f() {
        return this.a.f() || this.b.f();
    }

    public boolean g() {
        return this.a.g();
    }

    public boolean h() {
        return this.a.h();
    }

    public void i() {
        this.a.i();
        this.b.i();
    }

    public boolean a(c cVar) {
        boolean z = false;
        if (!(cVar instanceof h)) {
            return false;
        }
        h hVar = (h) cVar;
        if (this.a != null ? !this.a.a(hVar.a) : hVar.a != null) {
            if (this.b != null ? !this.b.a(hVar.b) : hVar.b != null) {
                z = true;
            }
        }
        return z;
    }
}
