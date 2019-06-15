package com.helpshift.conversation.c;

import com.helpshift.common.domain.e;
import com.helpshift.common.exception.RootAPIException;
import com.helpshift.common.platform.p;
import com.helpshift.configuration.a.a;
import com.helpshift.conversation.b.a.b;
import com.helpshift.util.l;
import com.helpshift.widget.c;
import com.helpshift.widget.d;
import com.helpshift.widget.g;
import com.helpshift.widget.h;
import com.helpshift.widget.k;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class f implements b {
    final e a;
    final a b;
    final com.helpshift.conversation.b.a c;
    final k d;
    final d e;
    final c f;
    final com.helpshift.widget.f g;
    final d h;
    final com.helpshift.widget.e i;
    final h j;
    WeakReference<e> k;
    boolean l = false;

    public f(p pVar, e eVar, com.helpshift.conversation.b.a aVar, e eVar2) {
        this.a = eVar;
        this.b = eVar.c();
        this.c = aVar;
        this.d = new k(this.b, aVar);
        this.f = this.d.e();
        this.g = this.d.f();
        this.h = this.d.g();
        this.i = this.d.h();
        this.j = this.d.i();
        g a = this.d.a(this.g, this.h);
        com.helpshift.widget.a a2 = this.d.a(this.i);
        com.helpshift.widget.a a3 = this.d.a();
        this.e = new d(eVar2, eVar);
        this.e.a(this.f);
        this.e.b(this.g);
        this.e.a(this.h);
        this.e.a(this.i);
        this.e.a(a3);
        this.e.b(a2);
        this.e.a(this.j);
        this.e.a(a);
        aVar.a((b) this);
        this.k = new WeakReference(eVar2);
        this.e.a(eVar2);
    }

    public void a(final String str) {
        this.a.a(new com.helpshift.common.domain.f() {
            public void a() {
                String d = f.this.f.d();
                f.this.f.a(str);
                if (!d.equals(str)) {
                    f.this.d.a(f.this.f);
                }
            }
        });
    }

    public void b(final String str) {
        this.a.a(new com.helpshift.common.domain.f() {
            public void a() {
                f.this.g.a(str);
            }
        });
    }

    public void a(final boolean z) {
        this.a.a(new com.helpshift.common.domain.f() {
            public void a() {
                f.this.l = z;
                if (f.this.c()) {
                    f.this.c.i();
                }
            }
        });
    }

    public void a() {
        c(true);
    }

    public void b() {
        c(false);
    }

    private void c(final boolean z) {
        this.a.a(new com.helpshift.common.domain.f() {
            public void a() {
                if (f.this.d()) {
                    if (z && f.this.c()) {
                        ArrayList d = f.this.c.d(f.this.f.d());
                        if (d.size() > 0) {
                            if (f.this.k.get() != null) {
                                ((e) f.this.k.get()).a(d);
                            }
                            return;
                        }
                    }
                    l.a("Helpshift_NewConvVM", "Creating new conversation");
                    f.this.j.a(true);
                    f.this.c.a(f.this.f.d(), f.this.g.d(), f.this.h.d(), f.this.i.a());
                }
            }
        });
    }

    /* Access modifiers changed, original: 0000 */
    public boolean c() {
        return !this.l && this.b.a("showSearchOnNewConversation");
    }

    public void a(final long j) {
        this.j.a(false);
        this.f.a(null);
        this.i.a(null);
        this.a.c(new com.helpshift.common.domain.f() {
            public void a() {
                if (f.this.k.get() != null) {
                    e eVar = (e) f.this.k.get();
                    if (!f.this.b.a("gotoConversationAfterContactUs") || f.this.b.a("disableInAppConversation")) {
                        eVar.v();
                        eVar.s();
                        return;
                    }
                    eVar.a(j);
                }
            }
        });
    }

    public void a(Exception exception) {
        this.j.a(false);
        b(exception);
    }

    public void a(e eVar) {
        if (this.k != null && this.k.get() == eVar) {
            this.k = new WeakReference(null);
        }
        this.c.b((b) this);
    }

    private void b(final Exception exception) {
        this.a.c(new com.helpshift.common.domain.f() {
            public void a() {
                if (exception instanceof RootAPIException) {
                    RootAPIException rootAPIException = (RootAPIException) exception;
                    if (f.this.k.get() != null) {
                        ((e) f.this.k.get()).a(rootAPIException.c);
                    }
                }
            }
        });
    }

    /* Access modifiers changed, original: 0000 */
    public boolean d() {
        this.f.a();
        this.g.a();
        this.h.b();
        return this.f.c() == null && this.g.c() == null && this.h.c() == null;
    }

    public void c(final String str) {
        this.a.a(new com.helpshift.common.domain.f() {
            public void a() {
                f.this.h.a(str);
            }
        });
    }

    public void d(final String str) {
        this.a.a(new com.helpshift.common.domain.f() {
            public void a() {
                if (com.helpshift.common.c.a(f.this.f.d()) && !com.helpshift.common.c.a(str)) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(str.substring(0, 1).toUpperCase());
                    stringBuilder.append(str.substring(1));
                    f.this.f.a(stringBuilder.toString());
                }
            }
        });
    }

    public void e() {
        if (!this.j.a()) {
            this.a.a(new com.helpshift.common.domain.f() {
                public void a() {
                    com.helpshift.conversation.dto.c a = f.this.i.a();
                    if (a != null && a.b != null) {
                        f.this.a.l().a(a);
                    }
                }
            });
            a(null);
        }
    }

    public void a(final com.helpshift.conversation.dto.c cVar) {
        this.a.a(new com.helpshift.common.domain.f() {
            public void a() {
                f.this.i.a(cVar);
                f.this.d.b(f.this.i);
            }
        });
    }

    public void f() {
        if (!this.j.a()) {
            this.a.a(new com.helpshift.common.domain.f() {
                public void a() {
                    final com.helpshift.conversation.dto.c a = f.this.i.a();
                    if (a != null && !com.helpshift.common.c.a(a.b)) {
                        f.this.a.c(new com.helpshift.common.domain.f() {
                            public void a() {
                                if (f.this.k.get() != null) {
                                    ((e) f.this.k.get()).a(a);
                                }
                            }
                        });
                    }
                }
            });
        }
    }

    public void g() {
        this.a.a(new com.helpshift.common.domain.f() {
            public void a() {
                f.this.a.c(new com.helpshift.common.domain.f() {
                    public void a() {
                        f.this.e.a();
                    }
                });
            }
        });
    }

    public void b(final boolean z) {
        this.a.a(new com.helpshift.common.domain.f() {
            public void a() {
                f.this.c.b(z);
            }
        });
    }

    public void a(final int i) {
        this.a.a(new com.helpshift.common.domain.f() {
            public void a() {
                f.this.c.a(i);
            }
        });
    }
}
