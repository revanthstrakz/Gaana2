package com.bumptech.glide.f.a;

import android.support.v4.util.Pools.Pool;
import android.support.v4.util.Pools.SimplePool;
import android.support.v4.util.Pools.SynchronizedPool;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public final class a {
    private static final d<Object> a = new d<Object>() {
        public void a(Object obj) {
        }
    };

    public interface d<T> {
        void a(T t);
    }

    public interface a<T> {
        T b();
    }

    private static final class b<T> implements Pool<T> {
        private final a<T> a;
        private final d<T> b;
        private final Pool<T> c;

        b(Pool<T> pool, a<T> aVar, d<T> dVar) {
            this.c = pool;
            this.a = aVar;
            this.b = dVar;
        }

        public T acquire() {
            T acquire = this.c.acquire();
            if (acquire == null) {
                acquire = this.a.b();
                if (Log.isLoggable("FactoryPools", 2)) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Created new ");
                    stringBuilder.append(acquire.getClass());
                    Log.v("FactoryPools", stringBuilder.toString());
                }
            }
            if (acquire instanceof c) {
                ((c) acquire).a_().a(false);
            }
            return acquire;
        }

        public boolean release(T t) {
            if (t instanceof c) {
                ((c) t).a_().a(true);
            }
            this.b.a(t);
            return this.c.release(t);
        }
    }

    public interface c {
        b a_();
    }

    public static <T extends c> Pool<T> a(int i, a<T> aVar) {
        return a(new SimplePool(i), (a) aVar);
    }

    public static <T extends c> Pool<T> b(int i, a<T> aVar) {
        return a(new SynchronizedPool(i), (a) aVar);
    }

    public static <T> Pool<List<T>> a() {
        return a(20);
    }

    public static <T> Pool<List<T>> a(int i) {
        return a(new SynchronizedPool(i), new a<List<T>>() {
            /* renamed from: a */
            public List<T> b() {
                return new ArrayList();
            }
        }, new d<List<T>>() {
            public void a(List<T> list) {
                list.clear();
            }
        });
    }

    private static <T extends c> Pool<T> a(Pool<T> pool, a<T> aVar) {
        return a(pool, aVar, b());
    }

    private static <T> Pool<T> a(Pool<T> pool, a<T> aVar, d<T> dVar) {
        return new b(pool, aVar, dVar);
    }

    private static <T> d<T> b() {
        return a;
    }
}
