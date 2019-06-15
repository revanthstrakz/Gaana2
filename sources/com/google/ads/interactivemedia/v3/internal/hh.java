package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;

public final class hh<E> extends gp<Object> {
    public static final gq a = new gq() {
        public <T> gp<T> a(fz fzVar, hw<T> hwVar) {
            Type b = hwVar.b();
            if (!(b instanceof GenericArrayType) && (!(b instanceof Class) || !((Class) b).isArray())) {
                return null;
            }
            b = gx.g(b);
            return new hh(fzVar, fzVar.a(hw.a(b)), gx.e(b));
        }
    };
    private final Class<E> b;
    private final gp<E> c;

    public hh(fz fzVar, gp<E> gpVar, Class<E> cls) {
        this.c = new ht(fzVar, gpVar, cls);
        this.b = cls;
    }

    public Object read(hx hxVar) throws IOException {
        if (hxVar.f() == hy.NULL) {
            hxVar.j();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        hxVar.a();
        while (hxVar.e()) {
            arrayList.add(this.c.read(hxVar));
        }
        hxVar.b();
        Object newInstance = Array.newInstance(this.b, arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            Array.set(newInstance, i, arrayList.get(i));
        }
        return newInstance;
    }

    public void write(hz hzVar, Object obj) throws IOException {
        if (obj == null) {
            hzVar.f();
            return;
        }
        hzVar.b();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.c.write(hzVar, Array.get(obj, i));
        }
        hzVar.c();
    }
}
