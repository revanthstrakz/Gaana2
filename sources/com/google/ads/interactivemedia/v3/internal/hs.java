package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

public final class hs<T> extends gp<T> {
    private final gm<T> a;
    private final ge<T> b;
    private final fz c;
    private final hw<T> d;
    private final gq e;
    private final a f = new a();
    private gp<T> g;

    private final class a implements gd, gl {
        private a() {
        }
    }

    private static final class b implements gq {
        private final hw<?> a;
        private final boolean b;
        private final Class<?> c;
        private final gm<?> d;
        private final ge<?> e;

        b(Object obj, hw<?> hwVar, boolean z, Class<?> cls) {
            ge geVar = null;
            this.d = obj instanceof gm ? (gm) obj : null;
            if (obj instanceof ge) {
                geVar = (ge) obj;
            }
            this.e = geVar;
            boolean z2 = (this.d == null && this.e == null) ? false : true;
            gw.a(z2);
            this.a = hwVar;
            this.b = z;
            this.c = cls;
        }

        public <T> gp<T> a(fz fzVar, hw<T> hwVar) {
            boolean isAssignableFrom = this.a != null ? this.a.equals(hwVar) || (this.b && this.a.b() == hwVar.a()) : this.c.isAssignableFrom(hwVar.a());
            return isAssignableFrom ? new hs(this.d, this.e, fzVar, hwVar, this) : null;
        }
    }

    public hs(gm<T> gmVar, ge<T> geVar, fz fzVar, hw<T> hwVar, gq gqVar) {
        this.a = gmVar;
        this.b = geVar;
        this.c = fzVar;
        this.d = hwVar;
        this.e = gqVar;
    }

    public T read(hx hxVar) throws IOException {
        if (this.b == null) {
            return a().read(hxVar);
        }
        gf a = hf.a(hxVar);
        if (a.j()) {
            return null;
        }
        try {
            return this.b.b(a, this.d.b(), this.f);
        } catch (gj e) {
            throw e;
        } catch (Exception e2) {
            throw new gj(e2);
        }
    }

    public void write(hz hzVar, T t) throws IOException {
        if (this.a == null) {
            a().write(hzVar, t);
        } else if (t == null) {
            hzVar.f();
        } else {
            hf.a(this.a.a(t, this.d.b(), this.f), hzVar);
        }
    }

    private gp<T> a() {
        gp<T> gpVar = this.g;
        if (gpVar != null) {
            return gpVar;
        }
        gp a = this.c.a(this.e, this.d);
        this.g = a;
        return a;
    }

    public static gq a(hw<?> hwVar, Object obj) {
        return new b(obj, hwVar, false, null);
    }

    public static gq b(hw<?> hwVar, Object obj) {
        return new b(obj, hwVar, hwVar.b() == hwVar.a(), null);
    }
}
