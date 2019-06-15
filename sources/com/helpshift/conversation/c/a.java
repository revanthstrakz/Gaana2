package com.helpshift.conversation.c;

import com.helpshift.common.c;
import com.helpshift.common.domain.e;
import com.helpshift.common.domain.f;
import com.helpshift.conversation.activeconversation.message.ConversationFooterState;
import com.helpshift.widget.b;
import com.helpshift.widget.i;
import com.helpshift.widget.j;
import com.helpshift.widget.l;

class a implements l {
    com.helpshift.widget.a a;
    com.helpshift.widget.a b;
    com.helpshift.widget.a c;
    i d;
    com.helpshift.widget.a e;
    com.helpshift.widget.a f;
    b g;
    private com.helpshift.conversation.activeconversation.b h;
    private e i;
    private boolean j;

    a(e eVar) {
        this.i = eVar;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(com.helpshift.conversation.activeconversation.b bVar) {
        this.h = bVar;
    }

    public void a(final j jVar) {
        this.i.c(new f() {
            public void a() {
                if (jVar == a.this.a) {
                    a.this.b();
                } else if (jVar == a.this.b) {
                    a.this.c();
                } else if (jVar == a.this.c) {
                    a.this.d();
                } else if (jVar == a.this.d) {
                    a.this.d();
                } else if (jVar == a.this.e) {
                    a.this.e();
                    a.this.b();
                } else if (jVar == a.this.f) {
                    a.this.f();
                } else if (jVar == a.this.g) {
                    a.this.g();
                }
            }
        });
    }

    /* Access modifiers changed, original: 0000 */
    public void a() {
        b();
        c();
        d();
        e();
        f();
        g();
    }

    /* Access modifiers changed, original: 0000 */
    public void a(com.helpshift.widget.a aVar) {
        aVar.a(this);
        this.a = aVar;
    }

    /* Access modifiers changed, original: 0000 */
    public void b(com.helpshift.widget.a aVar) {
        aVar.a(this);
        this.b = aVar;
    }

    /* Access modifiers changed, original: 0000 */
    public void c(com.helpshift.widget.a aVar) {
        aVar.a(this);
        this.c = aVar;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(i iVar) {
        iVar.a(this);
        this.d = iVar;
    }

    /* Access modifiers changed, original: 0000 */
    public void d(com.helpshift.widget.a aVar) {
        aVar.a(this);
        this.e = aVar;
    }

    /* Access modifiers changed, original: 0000 */
    public void e(com.helpshift.widget.a aVar) {
        aVar.a(this);
        this.f = aVar;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(b bVar) {
        bVar.a(this);
        this.g = bVar;
    }

    /* Access modifiers changed, original: 0000 */
    public void b() {
        if (this.h == null) {
            return;
        }
        if (this.a.b() && this.e.b() && !this.j) {
            this.h.c();
        } else {
            this.h.d();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void c() {
        if (this.h == null) {
            return;
        }
        if (this.b.b()) {
            this.h.a();
        } else {
            this.h.b();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void d() {
        if (c.a(this.d.a())) {
            if (this.c.a()) {
                this.c.a(false);
            }
        } else if (!this.c.a()) {
            this.c.a(true);
        }
        if (this.h == null) {
            return;
        }
        if (this.c.a()) {
            this.h.e();
        } else {
            this.h.f();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void e() {
        if (this.h == null) {
            return;
        }
        if (this.e.b()) {
            this.h.i();
        } else {
            this.h.j();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void f() {
        if (this.h == null) {
            return;
        }
        if (this.f.b()) {
            this.h.g();
        } else {
            this.h.h();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void g() {
        if (this.h != null) {
            this.h.a(this.g.a());
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void h() {
        this.e.b(true);
        this.f.b(false);
        this.g.a(ConversationFooterState.NONE);
    }

    /* Access modifiers changed, original: 0000 */
    public void i() {
        this.e.b(false);
        this.f.b(true);
        this.g.a(ConversationFooterState.CONVERSATION_ENDED_MESSAGE);
    }

    /* Access modifiers changed, original: 0000 */
    public void a(ConversationFooterState conversationFooterState) {
        this.e.b(false);
        this.f.b(false);
        this.g.a(conversationFooterState);
    }

    /* Access modifiers changed, original: 0000 */
    public void a(boolean z) {
        this.j = z;
        this.i.c(new f() {
            public void a() {
                a.this.b();
            }
        });
    }
}
