package com.helpshift.conversation.c;

import com.helpshift.common.domain.f;
import com.helpshift.conversation.dto.c;
import com.helpshift.widget.TextWidget;
import com.helpshift.widget.TextWidget.TextWidgetError;
import com.helpshift.widget.a;
import com.helpshift.widget.e;
import com.helpshift.widget.g;
import com.helpshift.widget.h;
import com.helpshift.widget.j;
import com.helpshift.widget.l;

class d implements l {
    TextWidget a;
    TextWidget b;
    com.helpshift.widget.d c;
    a d;
    e e;
    a f;
    h g;
    private final com.helpshift.common.domain.e h;
    private e i;
    private g j;

    d(e eVar, com.helpshift.common.domain.e eVar2) {
        this.i = eVar;
        this.h = eVar2;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(e eVar) {
        this.i = eVar;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(TextWidget textWidget) {
        textWidget.a(this);
        this.a = textWidget;
    }

    /* Access modifiers changed, original: 0000 */
    public void b(TextWidget textWidget) {
        textWidget.a(this);
        this.b = textWidget;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(com.helpshift.widget.d dVar) {
        dVar.a(this);
        this.c = dVar;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(a aVar) {
        aVar.a(this);
        this.d = aVar;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(e eVar) {
        eVar.a(this);
        this.e = eVar;
    }

    /* Access modifiers changed, original: 0000 */
    public void b(a aVar) {
        aVar.a(this);
        this.f = aVar;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(h hVar) {
        hVar.a(this);
        this.g = hVar;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(g gVar) {
        this.j = gVar;
    }

    public void a(final j jVar) {
        this.h.c(new f() {
            public void a() {
                if (jVar == d.this.a) {
                    d.this.e();
                } else if (jVar == d.this.b) {
                    d.this.f();
                } else if (jVar == d.this.c) {
                    d.this.d();
                } else if (jVar == d.this.d) {
                    d.this.c();
                } else if (jVar == d.this.f) {
                    d.this.h();
                } else if (jVar == d.this.g) {
                    d.this.b();
                    d.this.i();
                    d.this.j();
                    d.this.k();
                }
                if (jVar == d.this.e) {
                    d.this.g();
                    d.this.i();
                }
            }
        });
    }

    /* Access modifiers changed, original: 0000 */
    public void a() {
        e();
        f();
        d();
        g();
        h();
        l();
        b();
        c();
    }

    /* Access modifiers changed, original: 0000 */
    public void b() {
        if (this.g.a()) {
            this.i.t();
        } else {
            this.i.u();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void c() {
        if (this.d.b()) {
            this.i.q();
        } else {
            this.i.r();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void d() {
        this.i.c(this.c.d());
        if (TextWidgetError.INVALID_EMAIL.equals(this.c.c())) {
            this.i.h();
        } else if (TextWidgetError.EMPTY.equals(this.c.c())) {
            this.i.i();
        } else {
            this.i.j();
        }
        if (this.c.a()) {
            this.i.p();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void e() {
        this.i.a(this.a.d());
        if (TextWidgetError.EMPTY.equals(this.a.c())) {
            this.i.a();
        } else if (TextWidgetError.ONLY_SPECIAL_CHARACTERS.equals(this.a.c())) {
            this.i.c();
        } else if (TextWidgetError.LESS_THAN_MINIMUM_LENGTH.equals(this.a.c())) {
            this.i.b();
        } else {
            this.i.d();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void f() {
        this.i.b(this.b.d());
        if (TextWidgetError.EMPTY.equals(this.b.c())) {
            this.i.e();
        } else if (TextWidgetError.ONLY_SPECIAL_CHARACTERS.equals(this.b.c())) {
            this.i.f();
        } else {
            this.i.g();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void g() {
        c a = this.e.a();
        if (a == null || com.helpshift.common.c.a(a.b)) {
            this.i.m();
        } else {
            this.i.a(a.b, a.c, a.d);
        }
        if (this.e.c()) {
            this.i.x();
        } else {
            this.i.w();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void h() {
        if (this.f.b()) {
            this.i.l();
        } else {
            this.i.k();
        }
    }

    private void l() {
        if (this.j.a()) {
            this.i.n();
        } else {
            this.i.o();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void i() {
        if (this.g.a()) {
            this.f.b(false);
        } else {
            this.f.b(com.helpshift.common.c.a(this.e.b()));
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void j() {
        if (this.g.a()) {
            this.d.b(false);
        } else {
            this.d.b(true);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void k() {
        this.e.a(this.g.a() ^ 1);
    }
}
