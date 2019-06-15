package com.helpshift;

import com.helpshift.common.c;
import com.helpshift.common.domain.e;
import com.helpshift.common.domain.j;
import com.helpshift.common.platform.p;
import com.helpshift.configuration.a.a;
import com.helpshift.conversation.ConversationInboxPoller;
import com.helpshift.conversation.b.b;
import com.helpshift.conversation.c.f;
import java.util.Map;

public class d implements b {
    final e a;
    final a b;
    final com.helpshift.analytics.a.a c;
    private final p d;
    private final j e;
    private final j f;
    private final com.helpshift.meta.a g;
    private com.helpshift.account.a.a h;
    private b i;
    private boolean j = false;

    public d(p pVar) {
        this.d = pVar;
        this.a = new e(pVar);
        this.e = this.a.a();
        this.f = this.a.b();
        this.b = this.a.c();
        this.c = this.a.d();
        this.g = this.a.f();
    }

    public f a(com.helpshift.conversation.c.e eVar) {
        return new f(this.d, this.a, v().a(), eVar);
    }

    public com.helpshift.conversation.c.b a(Long l, com.helpshift.conversation.activeconversation.b bVar, boolean z) {
        return new com.helpshift.conversation.c.b(this.d, this.a, v().a(), v().a().a(l), bVar, z);
    }

    public com.helpshift.conversation.activeconversation.a a() {
        return v().a().l();
    }

    public void a(final String str) {
        a(new com.helpshift.common.domain.f() {
            public void a() {
                d.this.j().a().b(str);
                if (c.a(str)) {
                    d.this.q().a(str);
                }
            }
        });
    }

    public void b(final String str) {
        a(new com.helpshift.common.domain.f() {
            public void a() {
                d.this.j().a().c(str);
                if (c.a(str)) {
                    d.this.q().b(str);
                }
            }
        });
    }

    public void c(final String str) {
        a(new com.helpshift.common.domain.f() {
            public void a() {
                d.this.j().a(str);
            }
        });
    }

    public void b() {
        this.j = true;
        a(new com.helpshift.common.domain.f() {
            public void a() {
                d.this.h().a();
            }
        });
    }

    public void c() {
        this.j = false;
        a(new com.helpshift.common.domain.f() {
            public void a() {
                d.this.h().b();
            }
        });
    }

    public void d() {
        b(new com.helpshift.common.domain.f() {
            public void a() {
                d.this.b.a();
            }
        });
    }

    public void a(final Map<String, Object> map) {
        a(new com.helpshift.common.domain.f() {
            public void a() {
                d.this.b.a(map);
            }
        });
    }

    public void b(final Map<String, Object> map) {
        a(new com.helpshift.common.domain.f() {
            public void a() {
                d.this.b.b(map);
                if (map.containsKey("enableFullPrivacy") && ((Boolean) map.get("enableFullPrivacy")).booleanValue()) {
                    d.this.j().a().b(null);
                    d.this.j().a().c(null);
                    d.this.q().a(null);
                    d.this.q().b(null);
                }
            }
        });
    }

    public void e() {
        b(new com.helpshift.common.domain.f() {
            public void a() {
                if (d.this.c != null) {
                    d.this.c.a(d.this.j().a());
                }
            }
        });
    }

    public com.helpshift.analytics.a.a f() {
        return this.c;
    }

    public void g() {
        b(new com.helpshift.common.domain.f() {
            public void a() {
                d.this.c.b(d.this.j().a());
            }
        });
    }

    public com.helpshift.f.b h() {
        return this.a.e();
    }

    public void i() {
        this.a.a(new com.helpshift.common.domain.f() {
            public void a() {
                d.this.r();
                d.this.j();
                d.this.v().a();
                d.this.f();
                d.this.a.m().a();
            }
        });
    }

    public com.helpshift.account.a.a j() {
        if (this.h == null) {
            synchronized (this) {
                if (this.h == null) {
                    this.h = new com.helpshift.account.a.a(this.d, this.a);
                }
            }
        }
        return this.h;
    }

    public com.helpshift.meta.a k() {
        return this.g;
    }

    public com.helpshift.cif.a l() {
        return this.a.g();
    }

    public a m() {
        return this.b;
    }

    public ConversationInboxPoller n() {
        return v().a().a();
    }

    public void a(final String str, final String str2) {
        this.a.a(new com.helpshift.common.domain.f() {
            public void a() {
                d.this.v().a().a(str, str2);
            }
        });
    }

    public int o() {
        return v().a().n();
    }

    public com.helpshift.conversation.b.a q() {
        return v().a();
    }

    public com.helpshift.g.a r() {
        return this.a.i();
    }

    public com.helpshift.e.a s() {
        return this.a.h();
    }

    public com.helpshift.i.a.a t() {
        return this.a.k();
    }

    public com.helpshift.common.domain.a u() {
        return this.a.l();
    }

    public void p() {
        this.a.j().b();
    }

    /* Access modifiers changed, original: 0000 */
    public b v() {
        if (this.i == null) {
            synchronized (this) {
                if (this.i == null) {
                    this.i = new b(this.d, this.a, j());
                }
            }
        }
        return this.i;
    }

    private void a(com.helpshift.common.domain.f fVar) {
        this.e.a(fVar).a();
    }

    private void b(com.helpshift.common.domain.f fVar) {
        this.f.a(fVar).a();
    }
}
