package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

public final class hi implements gq {
    private final gy a;

    private static final class a<E> extends gp<Collection<E>> {
        private final gp<E> a;
        private final hd<? extends Collection<E>> b;

        public a(fz fzVar, Type type, gp<E> gpVar, hd<? extends Collection<E>> hdVar) {
            this.a = new ht(fzVar, gpVar, type);
            this.b = hdVar;
        }

        /* renamed from: a */
        public Collection<E> read(hx hxVar) throws IOException {
            if (hxVar.f() == hy.NULL) {
                hxVar.j();
                return null;
            }
            Collection collection = (Collection) this.b.a();
            hxVar.a();
            while (hxVar.e()) {
                collection.add(this.a.read(hxVar));
            }
            hxVar.b();
            return collection;
        }

        /* renamed from: a */
        public void write(hz hzVar, Collection<E> collection) throws IOException {
            if (collection == null) {
                hzVar.f();
                return;
            }
            hzVar.b();
            for (E write : collection) {
                this.a.write(hzVar, write);
            }
            hzVar.c();
        }
    }

    public hi(gy gyVar) {
        this.a = gyVar;
    }

    public <T> gp<T> a(fz fzVar, hw<T> hwVar) {
        Type b = hwVar.b();
        Class a = hwVar.a();
        if (!Collection.class.isAssignableFrom(a)) {
            return null;
        }
        b = gx.a(b, a);
        return new a(fzVar, b, fzVar.a(hw.a(b)), this.a.a((hw) hwVar));
    }
}
