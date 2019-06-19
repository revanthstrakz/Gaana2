package com.bumptech.glide.load.b;

import android.support.annotation.Nullable;
import android.support.v4.util.Pools.Pool;
import com.bumptech.glide.Registry.NoModelLoaderAvailableException;
import com.bumptech.glide.f.h;
import com.bumptech.glide.load.f;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class r {
    private static final c a = new c();
    private static final n<Object, Object> b = new a();
    private final List<b<?, ?>> c;
    private final c d;
    private final Set<b<?, ?>> e;
    private final Pool<List<Throwable>> f;

    private static class a implements n<Object, Object> {
        @Nullable
        public com.bumptech.glide.load.b.n.a<Object> a(Object obj, int i, int i2, f fVar) {
            return null;
        }

        public boolean a(Object obj) {
            return false;
        }

        a() {
        }
    }

    private static class b<Model, Data> {
        final Class<Data> a;
        final o<Model, Data> b;
        private final Class<Model> c;

        public b(Class<Model> cls, Class<Data> cls2, o<Model, Data> oVar) {
            this.c = cls;
            this.a = cls2;
            this.b = oVar;
        }

        public boolean a(Class<?> cls, Class<?> cls2) {
            return a(cls) && this.a.isAssignableFrom(cls2);
        }

        public boolean a(Class<?> cls) {
            return this.c.isAssignableFrom(cls);
        }
    }

    static class c {
        c() {
        }

        public <Model, Data> q<Model, Data> a(List<n<Model, Data>> list, Pool<List<Throwable>> pool) {
            return new q(list, pool);
        }
    }

    public r(Pool<List<Throwable>> pool) {
        this(pool, a);
    }

    r(Pool<List<Throwable>> pool, c cVar) {
        this.c = new ArrayList();
        this.e = new HashSet();
        this.f = pool;
        this.d = cVar;
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized <Model, Data> void a(Class<Model> cls, Class<Data> cls2, o<Model, Data> oVar) {
        a(cls, cls2, oVar, true);
    }

    private <Model, Data> void a(Class<Model> cls, Class<Data> cls2, o<Model, Data> oVar, boolean z) {
        this.c.add(z ? this.c.size() : 0, new b(cls, cls2, oVar));
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized <Model> List<n<Model, ?>> a(Class<Model> cls) {
        ArrayList arrayList;
        try {
            arrayList = new ArrayList();
            for (b bVar : this.c) {
                if (!this.e.contains(bVar)) {
                    if (bVar.a(cls)) {
                        this.e.add(bVar);
                        arrayList.add(a(bVar));
                        this.e.remove(bVar);
                    }
                }
            }
        } catch (Throwable th) {
            this.e.clear();
        }
        return arrayList;
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized List<Class<?>> b(Class<?> cls) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (b bVar : this.c) {
            if (!arrayList.contains(bVar.a) && bVar.a(cls)) {
                arrayList.add(bVar.a);
            }
        }
        return arrayList;
    }

    public synchronized <Model, Data> n<Model, Data> a(Class<Model> cls, Class<Data> cls2) {
        try {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            for (b bVar : this.c) {
                if (this.e.contains(bVar)) {
                    i = 1;
                } else if (bVar.a(cls, cls2)) {
                    this.e.add(bVar);
                    arrayList.add(a(bVar));
                    this.e.remove(bVar);
                }
            }
            if (arrayList.size() > 1) {
                return this.d.a(arrayList, this.f);
            } else if (arrayList.size() == 1) {
                return (n) arrayList.get(0);
            } else if (i != 0) {
                return a();
            } else {
                throw new NoModelLoaderAvailableException(cls, cls2);
            }
        } catch (Throwable th) {
            this.e.clear();
        }
    }

    private <Model, Data> n<Model, Data> a(b<?, ?> bVar) {
        return (n) h.a(bVar.b.a(this));
    }

    private static <Model, Data> n<Model, Data> a() {
        return b;
    }
}
