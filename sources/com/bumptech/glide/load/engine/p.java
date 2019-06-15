package com.bumptech.glide.load.engine;

import android.support.v4.util.Pools.Pool;
import com.bumptech.glide.f.a.a;
import com.bumptech.glide.f.a.a.c;
import com.bumptech.glide.f.a.b;

final class p<Z> implements c, q<Z> {
    private static final Pool<p<?>> a = a.b(20, new a.a<p<?>>() {
        /* renamed from: a */
        public p<?> b() {
            return new p();
        }
    });
    private final b b = b.a();
    private q<Z> c;
    private boolean d;
    private boolean e;

    static <Z> p<Z> a(q<Z> qVar) {
        p pVar = (p) a.acquire();
        pVar.b(qVar);
        return pVar;
    }

    p() {
    }

    private void b(q<Z> qVar) {
        this.e = false;
        this.d = true;
        this.c = qVar;
    }

    private void f() {
        this.c = null;
        a.release(this);
    }

    public synchronized void a() {
        this.b.b();
        if (this.d) {
            this.d = false;
            if (this.e) {
                e();
            }
        } else {
            throw new IllegalStateException("Already unlocked");
        }
    }

    public Class<Z> b() {
        return this.c.b();
    }

    public Z c() {
        return this.c.c();
    }

    public int d() {
        return this.c.d();
    }

    public synchronized void e() {
        this.b.b();
        this.e = true;
        if (!this.d) {
            this.c.e();
            f();
        }
    }

    public b a_() {
        return this.b;
    }
}
