package com.bumptech.glide.manager;

import com.bumptech.glide.f.i;
import com.bumptech.glide.request.c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

public class m {
    private final Set<c> a = Collections.newSetFromMap(new WeakHashMap());
    private final List<c> b = new ArrayList();
    private boolean c;

    public void a(c cVar) {
        this.a.add(cVar);
        if (this.c) {
            this.b.add(cVar);
        } else {
            cVar.a();
        }
    }

    public boolean b(c cVar) {
        boolean z = false;
        if (cVar == null) {
            return false;
        }
        boolean remove = this.a.remove(cVar);
        if (this.b.remove(cVar) || remove) {
            z = true;
        }
        if (z) {
            cVar.c();
            cVar.i();
        }
        return z;
    }

    public boolean a() {
        return this.c;
    }

    public void b() {
        this.c = true;
        for (c cVar : i.a(this.a)) {
            if (cVar.d()) {
                cVar.b();
                this.b.add(cVar);
            }
        }
    }

    public void c() {
        this.c = false;
        for (c cVar : i.a(this.a)) {
            if (!(cVar.e() || cVar.g() || cVar.d())) {
                cVar.a();
            }
        }
        this.b.clear();
    }

    public void d() {
        for (c b : i.a(this.a)) {
            b(b);
        }
        this.b.clear();
    }

    public void e() {
        for (c cVar : i.a(this.a)) {
            if (!(cVar.e() || cVar.g())) {
                cVar.b();
                if (this.c) {
                    this.b.add(cVar);
                } else {
                    cVar.a();
                }
            }
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString());
        stringBuilder.append("{numRequests=");
        stringBuilder.append(this.a.size());
        stringBuilder.append(", isPaused=");
        stringBuilder.append(this.c);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
