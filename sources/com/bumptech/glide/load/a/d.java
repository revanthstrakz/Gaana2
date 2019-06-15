package com.bumptech.glide.load.a;

import com.bumptech.glide.f.h;
import java.util.HashMap;
import java.util.Map;

public class d {
    private static final com.bumptech.glide.load.a.c.a<?> b = new com.bumptech.glide.load.a.c.a<Object>() {
        public c<Object> a(Object obj) {
            return new a(obj);
        }

        public Class<Object> a() {
            throw new UnsupportedOperationException("Not implemented");
        }
    };
    private final Map<Class<?>, com.bumptech.glide.load.a.c.a<?>> a = new HashMap();

    private static class a implements c<Object> {
        private final Object a;

        public void b() {
        }

        public a(Object obj) {
            this.a = obj;
        }

        public Object a() {
            return this.a;
        }
    }

    public synchronized void a(com.bumptech.glide.load.a.c.a<?> aVar) {
        this.a.put(aVar.a(), aVar);
    }

    public synchronized <T> c<T> a(T t) {
        com.bumptech.glide.load.a.c.a aVar;
        h.a((Object) t);
        aVar = (com.bumptech.glide.load.a.c.a) this.a.get(t.getClass());
        if (aVar == null) {
            for (com.bumptech.glide.load.a.c.a aVar2 : this.a.values()) {
                if (aVar2.a().isAssignableFrom(t.getClass())) {
                    aVar = aVar2;
                    break;
                }
            }
        }
        if (aVar == null) {
            aVar = b;
        }
        return aVar.a(t);
    }
}
