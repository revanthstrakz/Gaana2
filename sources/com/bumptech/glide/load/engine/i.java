package com.bumptech.glide.load.engine;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.support.v4.util.Pools.Pool;
import com.bumptech.glide.f.a.a.c;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.request.g;
import java.util.ArrayList;
import java.util.List;

class i<R> implements c, a<R> {
    private static final a a = new a();
    private static final Handler b = new Handler(Looper.getMainLooper(), new b());
    private final List<g> c;
    private final com.bumptech.glide.f.a.b d;
    private final Pool<i<?>> e;
    private final a f;
    private final j g;
    private final com.bumptech.glide.load.engine.c.a h;
    private final com.bumptech.glide.load.engine.c.a i;
    private final com.bumptech.glide.load.engine.c.a j;
    private final com.bumptech.glide.load.engine.c.a k;
    private com.bumptech.glide.load.c l;
    private boolean m;
    private boolean n;
    private boolean o;
    private q<?> p;
    private DataSource q;
    private boolean r;
    private GlideException s;
    private boolean t;
    private List<g> u;
    private m<?> v;
    private DecodeJob<R> w;
    private volatile boolean x;

    static class a {
        a() {
        }

        public <R> m<R> a(q<R> qVar, boolean z) {
            return new m(qVar, z);
        }
    }

    private static class b implements Callback {
        b() {
        }

        public boolean handleMessage(Message message) {
            i iVar = (i) message.obj;
            switch (message.what) {
                case 1:
                    iVar.b();
                    break;
                case 2:
                    iVar.e();
                    break;
                case 3:
                    iVar.c();
                    break;
                default:
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Unrecognized message: ");
                    stringBuilder.append(message.what);
                    throw new IllegalStateException(stringBuilder.toString());
            }
            return true;
        }
    }

    i(com.bumptech.glide.load.engine.c.a aVar, com.bumptech.glide.load.engine.c.a aVar2, com.bumptech.glide.load.engine.c.a aVar3, com.bumptech.glide.load.engine.c.a aVar4, j jVar, Pool<i<?>> pool) {
        this(aVar, aVar2, aVar3, aVar4, jVar, pool, a);
    }

    i(com.bumptech.glide.load.engine.c.a aVar, com.bumptech.glide.load.engine.c.a aVar2, com.bumptech.glide.load.engine.c.a aVar3, com.bumptech.glide.load.engine.c.a aVar4, j jVar, Pool<i<?>> pool, a aVar5) {
        this.c = new ArrayList(2);
        this.d = com.bumptech.glide.f.a.b.a();
        this.h = aVar;
        this.i = aVar2;
        this.j = aVar3;
        this.k = aVar4;
        this.g = jVar;
        this.e = pool;
        this.f = aVar5;
    }

    /* Access modifiers changed, original: 0000 */
    public i<R> a(com.bumptech.glide.load.c cVar, boolean z, boolean z2, boolean z3) {
        this.l = cVar;
        this.m = z;
        this.n = z2;
        this.o = z3;
        return this;
    }

    public void b(DecodeJob<R> decodeJob) {
        com.bumptech.glide.load.engine.c.a aVar;
        this.w = decodeJob;
        if (decodeJob.a()) {
            aVar = this.h;
        } else {
            aVar = f();
        }
        aVar.execute(decodeJob);
    }

    public void a(g gVar) {
        com.bumptech.glide.f.i.a();
        this.d.b();
        if (this.r) {
            gVar.a(this.v, this.q);
        } else if (this.t) {
            gVar.a(this.s);
        } else {
            this.c.add(gVar);
        }
    }

    public void b(g gVar) {
        com.bumptech.glide.f.i.a();
        this.d.b();
        if (this.r || this.t) {
            c(gVar);
            return;
        }
        this.c.remove(gVar);
        if (this.c.isEmpty()) {
            a();
        }
    }

    private com.bumptech.glide.load.engine.c.a f() {
        if (this.n) {
            return this.j;
        }
        return this.o ? this.k : this.i;
    }

    private void c(g gVar) {
        if (this.u == null) {
            this.u = new ArrayList(2);
        }
        if (!this.u.contains(gVar)) {
            this.u.add(gVar);
        }
    }

    private boolean d(g gVar) {
        return this.u != null && this.u.contains(gVar);
    }

    /* Access modifiers changed, original: 0000 */
    public void a() {
        if (!this.t && !this.r && !this.x) {
            this.x = true;
            this.w.b();
            this.g.a(this, this.l);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void b() {
        this.d.b();
        if (this.x) {
            this.p.e();
            a(false);
        } else if (this.c.isEmpty()) {
            throw new IllegalStateException("Received a resource without any callbacks to notify");
        } else if (this.r) {
            throw new IllegalStateException("Already have resource");
        } else {
            this.v = this.f.a(this.p, this.m);
            this.r = true;
            this.v.f();
            this.g.a(this.l, this.v);
            for (g gVar : this.c) {
                if (!d(gVar)) {
                    this.v.f();
                    gVar.a(this.v, this.q);
                }
            }
            this.v.g();
            a(false);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void c() {
        this.d.b();
        if (this.x) {
            this.g.a(this, this.l);
            a(false);
            return;
        }
        throw new IllegalStateException("Not cancelled");
    }

    private void a(boolean z) {
        com.bumptech.glide.f.i.a();
        this.c.clear();
        this.l = null;
        this.v = null;
        this.p = null;
        if (this.u != null) {
            this.u.clear();
        }
        this.t = false;
        this.x = false;
        this.r = false;
        this.w.a(z);
        this.w = null;
        this.s = null;
        this.q = null;
        this.e.release(this);
    }

    public void a(q<R> qVar, DataSource dataSource) {
        this.p = qVar;
        this.q = dataSource;
        b.obtainMessage(1, this).sendToTarget();
    }

    public void a(GlideException glideException) {
        this.s = glideException;
        b.obtainMessage(2, this).sendToTarget();
    }

    public void a(DecodeJob<?> decodeJob) {
        f().execute(decodeJob);
    }

    /* Access modifiers changed, original: 0000 */
    public void e() {
        this.d.b();
        if (this.x) {
            a(false);
        } else if (this.c.isEmpty()) {
            throw new IllegalStateException("Received an exception without any callbacks to notify");
        } else if (this.t) {
            throw new IllegalStateException("Already failed once");
        } else {
            this.t = true;
            this.g.a(this.l, null);
            for (g gVar : this.c) {
                if (!d(gVar)) {
                    gVar.a(this.s);
                }
            }
            a(false);
        }
    }

    public com.bumptech.glide.f.a.b a_() {
        return this.d;
    }
}
