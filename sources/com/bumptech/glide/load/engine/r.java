package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.a.b.a;
import com.bumptech.glide.load.b.n;
import com.bumptech.glide.load.c;
import java.io.File;
import java.util.List;

class r implements a<Object>, d {
    private final d.a a;
    private final e<?> b;
    private int c = 0;
    private int d = -1;
    private c e;
    private List<n<File, ?>> f;
    private int g;
    private volatile n.a<?> h;
    private File i;
    private s j;

    public r(e<?> eVar, d.a aVar) {
        this.b = eVar;
        this.a = aVar;
    }

    public boolean a() {
        List l = this.b.l();
        boolean z = false;
        if (l.isEmpty()) {
            return false;
        }
        List i = this.b.i();
        while (true) {
            if (this.f == null || !c()) {
                this.d++;
                if (this.d >= i.size()) {
                    this.c++;
                    if (this.c >= l.size()) {
                        return false;
                    }
                    this.d = 0;
                }
                c cVar = (c) l.get(this.c);
                Class cls = (Class) i.get(this.d);
                c cVar2 = cVar;
                this.j = new s(cVar2, this.b.f(), this.b.g(), this.b.h(), this.b.c(cls), cls, this.b.e());
                this.i = this.b.b().a(this.j);
                if (this.i != null) {
                    this.e = cVar;
                    this.f = this.b.a(this.i);
                    this.g = 0;
                }
            } else {
                this.h = null;
                while (!z && c()) {
                    l = this.f;
                    int i2 = this.g;
                    this.g = i2 + 1;
                    this.h = ((n) l.get(i2)).a(this.i, this.b.g(), this.b.h(), this.b.e());
                    if (this.h != null && this.b.a(this.h.c.d())) {
                        this.h.c.a(this.b.d(), this);
                        z = true;
                    }
                }
                return z;
            }
        }
    }

    private boolean c() {
        return this.g < this.f.size();
    }

    public void b() {
        n.a aVar = this.h;
        if (aVar != null) {
            aVar.c.b();
        }
    }

    public void a(Object obj) {
        this.a.a(this.e, obj, this.h.c, DataSource.RESOURCE_DISK_CACHE, this.j);
    }

    public void a(Exception exception) {
        this.a.a(this.j, exception, this.h.c, DataSource.RESOURCE_DISK_CACHE);
    }
}
