package com.helpshift.common.domain;

import com.helpshift.common.AutoRetryFailedEventDM;
import com.helpshift.common.platform.p;
import com.helpshift.configuration.a.a;
import com.helpshift.f.b;
import java.util.concurrent.Executors;

public class e {
    private final p a;
    private j b;
    private j c;
    private d d;
    private a e;
    private com.helpshift.analytics.a.a f;
    private com.helpshift.meta.a g;
    private b h = new b(this, null);
    private com.helpshift.g.a i;
    private com.helpshift.e.a j;
    private com.helpshift.c.a.a k;
    private com.helpshift.i.a.a l;
    private AutoRetryFailedEventDM m;
    private a n;
    private com.helpshift.cif.a o;

    public e(p pVar) {
        this.a = pVar;
    }

    public j a() {
        if (this.b == null) {
            synchronized (this) {
                if (this.b == null) {
                    this.b = new c(Executors.newSingleThreadExecutor(new g("core-s")));
                }
            }
        }
        return this.b;
    }

    public j b() {
        if (this.c == null) {
            synchronized (this) {
                if (this.c == null) {
                    this.c = new c(Executors.newCachedThreadPool(new g("core-p")));
                }
            }
        }
        return this.c;
    }

    private d n() {
        if (this.d == null) {
            synchronized (this) {
                if (this.d == null) {
                    this.d = new b(Executors.newScheduledThreadPool(1, new g("core-d")));
                }
            }
        }
        return this.d;
    }

    public a c() {
        if (this.e == null) {
            synchronized (this) {
                if (this.e == null) {
                    this.e = new a(this, this.a);
                }
            }
        }
        return this.e;
    }

    public com.helpshift.analytics.a.a d() {
        if (this.f == null) {
            synchronized (this) {
                if (this.f == null) {
                    this.f = new com.helpshift.analytics.a.a(this, this.a);
                }
            }
        }
        return this.f;
    }

    public b e() {
        return this.h;
    }

    public com.helpshift.meta.a f() {
        if (this.g == null) {
            synchronized (this) {
                if (this.g == null) {
                    this.g = new com.helpshift.meta.a(this, this.a, c());
                }
            }
        }
        return this.g;
    }

    public com.helpshift.cif.a g() {
        if (this.o == null) {
            synchronized (this) {
                if (this.o == null) {
                    this.o = new com.helpshift.cif.a(this, this.a);
                }
            }
        }
        return this.o;
    }

    public com.helpshift.e.a h() {
        if (this.j == null) {
            synchronized (this) {
                if (this.j == null) {
                    this.j = new com.helpshift.e.a();
                }
            }
        }
        return this.j;
    }

    public com.helpshift.g.a i() {
        if (this.i == null) {
            synchronized (this) {
                if (this.i == null) {
                    this.i = new com.helpshift.g.a(this, this.a);
                }
            }
        }
        return this.i;
    }

    public com.helpshift.c.a.a j() {
        if (this.k == null) {
            synchronized (this) {
                if (this.k == null) {
                    this.k = new com.helpshift.c.a.a(this, this.a);
                }
            }
        }
        return this.k;
    }

    public com.helpshift.i.a.a k() {
        if (this.l == null) {
            synchronized (this) {
                if (this.l == null) {
                    this.l = new com.helpshift.i.a.a(c(), this.a);
                }
            }
        }
        return this.l;
    }

    public a l() {
        if (this.n == null) {
            synchronized (this) {
                if (this.n == null) {
                    this.n = new a(this, this.a);
                }
            }
        }
        return this.n;
    }

    public void a(f fVar) {
        a().a(fVar).a();
    }

    public void b(f fVar) {
        b().a(fVar).a();
    }

    public void c(f fVar) {
        if (this.a.s()) {
            fVar.a();
        } else {
            this.a.t().a(fVar).a();
        }
    }

    public void a(f fVar, long j) {
        n().a(fVar, j).a();
    }

    public void b(final f fVar, long j) {
        a(new f() {
            public void a() {
                e.this.b(fVar);
            }
        }, j);
    }

    public AutoRetryFailedEventDM m() {
        if (this.m == null) {
            synchronized (this) {
                if (this.m == null) {
                    this.m = new AutoRetryFailedEventDM(this, this.a);
                }
            }
        }
        return this.m;
    }
}
