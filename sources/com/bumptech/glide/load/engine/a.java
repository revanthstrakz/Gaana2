package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.b.n;
import com.bumptech.glide.load.c;
import java.io.File;
import java.util.List;

class a implements com.bumptech.glide.load.a.b.a<Object>, d {
    private List<c> a;
    private final e<?> b;
    private final com.bumptech.glide.load.engine.d.a c;
    private int d;
    private c e;
    private List<n<File, ?>> f;
    private int g;
    private volatile com.bumptech.glide.load.b.n.a<?> h;
    private File i;

    a(e<?> eVar, com.bumptech.glide.load.engine.d.a aVar) {
        this(eVar.l(), eVar, aVar);
    }

    a(List<c> list, e<?> eVar, com.bumptech.glide.load.engine.d.a aVar) {
        this.d = -1;
        this.a = list;
        this.b = eVar;
        this.c = aVar;
    }

    public boolean a() {
        while (true) {
            boolean z = false;
            if (this.f == null || !c()) {
                this.d++;
                if (this.d >= this.a.size()) {
                    return false;
                }
                c cVar = (c) this.a.get(this.d);
                this.i = this.b.b().a(new b(cVar, this.b.f()));
                if (this.i != null) {
                    this.e = cVar;
                    this.f = this.b.a(this.i);
                    this.g = 0;
                }
            } else {
                this.h = null;
                while (!z && c()) {
                    List list = this.f;
                    int i = this.g;
                    this.g = i + 1;
                    this.h = ((n) list.get(i)).a(this.i, this.b.g(), this.b.h(), this.b.e());
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
        com.bumptech.glide.load.b.n.a aVar = this.h;
        if (aVar != null) {
            aVar.c.b();
        }
    }

    public void a(Object obj) {
        this.c.a(this.e, obj, this.h.c, DataSource.DATA_DISK_CACHE, this.e);
    }

    public void a(Exception exception) {
        this.c.a(this.e, exception, this.h.c, DataSource.DATA_DISK_CACHE);
    }
}
