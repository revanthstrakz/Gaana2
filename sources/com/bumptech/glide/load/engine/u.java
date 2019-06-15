package com.bumptech.glide.load.engine;

import android.util.Log;
import com.bumptech.glide.f.d;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.a.b;
import com.bumptech.glide.load.a.b.a;
import com.bumptech.glide.load.b.n;
import com.bumptech.glide.load.c;
import java.util.Collections;
import java.util.List;

class u implements a<Object>, d, d.a {
    private final e<?> a;
    private final d.a b;
    private int c;
    private a d;
    private Object e;
    private volatile n.a<?> f;
    private b g;

    public u(e<?> eVar, d.a aVar) {
        this.a = eVar;
        this.b = aVar;
    }

    public boolean a() {
        if (this.e != null) {
            Object obj = this.e;
            this.e = null;
            b(obj);
        }
        if (this.d != null && this.d.a()) {
            return true;
        }
        this.d = null;
        this.f = null;
        boolean z = false;
        while (!z && d()) {
            List k = this.a.k();
            int i = this.c;
            this.c = i + 1;
            this.f = (n.a) k.get(i);
            if (this.f != null && (this.a.c().a(this.f.c.c()) || this.a.a(this.f.c.d()))) {
                this.f.c.a(this.a.d(), this);
                z = true;
            }
        }
        return z;
    }

    private boolean d() {
        return this.c < this.a.k().size();
    }

    private void b(Object obj) {
        long a = d.a();
        try {
            com.bumptech.glide.load.a a2 = this.a.a(obj);
            c cVar = new c(a2, obj, this.a.e());
            this.g = new b(this.f.a, this.a.f());
            this.a.b().a(this.g, cVar);
            if (Log.isLoggable("SourceGenerator", 2)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Finished encoding source to cache, key: ");
                stringBuilder.append(this.g);
                stringBuilder.append(", data: ");
                stringBuilder.append(obj);
                stringBuilder.append(", encoder: ");
                stringBuilder.append(a2);
                stringBuilder.append(", duration: ");
                stringBuilder.append(d.a(a));
                Log.v("SourceGenerator", stringBuilder.toString());
            }
            this.f.c.a();
            this.d = new a(Collections.singletonList(this.f.a), this.a, this);
        } catch (Throwable th) {
            this.f.c.a();
        }
    }

    public void b() {
        n.a aVar = this.f;
        if (aVar != null) {
            aVar.c.b();
        }
    }

    public void a(Object obj) {
        g c = this.a.c();
        if (obj == null || !c.a(this.f.c.c())) {
            this.b.a(this.f.a, obj, this.f.c, this.f.c.c(), this.g);
            return;
        }
        this.e = obj;
        this.b.c();
    }

    public void a(Exception exception) {
        this.b.a(this.g, exception, this.f.c, this.f.c.c());
    }

    public void c() {
        throw new UnsupportedOperationException();
    }

    public void a(c cVar, Object obj, b<?> bVar, DataSource dataSource, c cVar2) {
        this.b.a(cVar, obj, bVar, this.f.c.c(), cVar);
    }

    public void a(c cVar, Exception exception, b<?> bVar, DataSource dataSource) {
        this.b.a(cVar, exception, bVar, this.f.c.c());
    }
}
