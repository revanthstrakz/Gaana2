package com.google.ads.interactivemedia.v3.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class gc extends gf implements Iterable<gf> {
    private final List<gf> a = new ArrayList();

    public void a(gf gfVar) {
        Object gfVar2;
        if (gfVar2 == null) {
            gfVar2 = gh.a;
        }
        this.a.add(gfVar2);
    }

    public Iterator<gf> iterator() {
        return this.a.iterator();
    }

    public Number a() {
        if (this.a.size() == 1) {
            return ((gf) this.a.get(0)).a();
        }
        throw new IllegalStateException();
    }

    public String b() {
        if (this.a.size() == 1) {
            return ((gf) this.a.get(0)).b();
        }
        throw new IllegalStateException();
    }

    public double c() {
        if (this.a.size() == 1) {
            return ((gf) this.a.get(0)).c();
        }
        throw new IllegalStateException();
    }

    public long d() {
        if (this.a.size() == 1) {
            return ((gf) this.a.get(0)).d();
        }
        throw new IllegalStateException();
    }

    public int e() {
        if (this.a.size() == 1) {
            return ((gf) this.a.get(0)).e();
        }
        throw new IllegalStateException();
    }

    public boolean f() {
        if (this.a.size() == 1) {
            return ((gf) this.a.get(0)).f();
        }
        throw new IllegalStateException();
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof gc) && ((gc) obj).a.equals(this.a));
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
