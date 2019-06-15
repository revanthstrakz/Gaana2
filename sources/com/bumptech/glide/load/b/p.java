package com.bumptech.glide.load.b;

import android.support.v4.util.Pools.Pool;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class p {
    private final r a;
    private final a b;

    private static class a {
        private final Map<Class<?>, a<?>> a = new HashMap();

        private static class a<Model> {
            final List<n<Model, ?>> a;

            public a(List<n<Model, ?>> list) {
                this.a = list;
            }
        }

        a() {
        }

        public void a() {
            this.a.clear();
        }

        public <Model> void a(Class<Model> cls, List<n<Model, ?>> list) {
            if (((a) this.a.put(cls, new a(list))) != null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Already cached loaders for model: ");
                stringBuilder.append(cls);
                throw new IllegalStateException(stringBuilder.toString());
            }
        }

        public <Model> List<n<Model, ?>> a(Class<Model> cls) {
            a aVar = (a) this.a.get(cls);
            if (aVar == null) {
                return null;
            }
            return aVar.a;
        }
    }

    public p(Pool<List<Throwable>> pool) {
        this(new r(pool));
    }

    p(r rVar) {
        this.b = new a();
        this.a = rVar;
    }

    public synchronized <Model, Data> void a(Class<Model> cls, Class<Data> cls2, o<Model, Data> oVar) {
        this.a.a(cls, cls2, oVar);
        this.b.a();
    }

    public synchronized <A> List<n<A, ?>> a(A a) {
        ArrayList arrayList;
        List b = b(b((Object) a));
        int size = b.size();
        arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            n nVar = (n) b.get(i);
            if (nVar.a(a)) {
                arrayList.add(nVar);
            }
        }
        return arrayList;
    }

    public synchronized List<Class<?>> a(Class<?> cls) {
        return this.a.b(cls);
    }

    private <A> List<n<A, ?>> b(Class<A> cls) {
        List<n<A, ?>> a = this.b.a(cls);
        if (a != null) {
            return a;
        }
        List unmodifiableList = Collections.unmodifiableList(this.a.a((Class) cls));
        this.b.a(cls, unmodifiableList);
        return unmodifiableList;
    }

    private static <A> Class<A> b(A a) {
        return a.getClass();
    }
}
