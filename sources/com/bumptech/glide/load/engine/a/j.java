package com.bumptech.glide.load.engine.a;

import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.bumptech.glide.f.h;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public final class j implements b {
    private final h<a, Object> a;
    private final b b;
    private final Map<Class<?>, NavigableMap<Integer, Integer>> c;
    private final Map<Class<?>, a<?>> d;
    private final int e;
    private int f;

    private static final class a implements m {
        int a;
        private final b b;
        private Class<?> c;

        a(b bVar) {
            this.b = bVar;
        }

        /* Access modifiers changed, original: 0000 */
        public void a(int i, Class<?> cls) {
            this.a = i;
            this.c = cls;
        }

        public boolean equals(Object obj) {
            boolean z = false;
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            if (this.a == aVar.a && this.c == aVar.c) {
                z = true;
            }
            return z;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Key{size=");
            stringBuilder.append(this.a);
            stringBuilder.append("array=");
            stringBuilder.append(this.c);
            stringBuilder.append('}');
            return stringBuilder.toString();
        }

        public void a() {
            this.b.a(this);
        }

        public int hashCode() {
            return (31 * this.a) + (this.c != null ? this.c.hashCode() : 0);
        }
    }

    private static final class b extends d<a> {
        b() {
        }

        /* Access modifiers changed, original: 0000 */
        public a a(int i, Class<?> cls) {
            a aVar = (a) c();
            aVar.a(i, cls);
            return aVar;
        }

        /* Access modifiers changed, original: protected */
        /* renamed from: a */
        public a b() {
            return new a(this);
        }
    }

    @VisibleForTesting
    public j() {
        this.a = new h();
        this.b = new b();
        this.c = new HashMap();
        this.d = new HashMap();
        this.e = 4194304;
    }

    public j(int i) {
        this.a = new h();
        this.b = new b();
        this.c = new HashMap();
        this.d = new HashMap();
        this.e = i;
    }

    public synchronized <T> void a(T t, Class<T> cls) {
        a b = b((Class) cls);
        int a = b.a((Object) t);
        int b2 = b.b() * a;
        if (b(b2)) {
            a a2 = this.b.a(a, cls);
            this.a.a(a2, t);
            NavigableMap a3 = a((Class) cls);
            Integer num = (Integer) a3.get(Integer.valueOf(a2.a));
            Integer valueOf = Integer.valueOf(a2.a);
            int i = 1;
            if (num != null) {
                i = 1 + num.intValue();
            }
            a3.put(valueOf, Integer.valueOf(i));
            this.f += b2;
            c();
        }
    }

    public <T> T a(int i, Class<T> cls) {
        Object a;
        a b = b((Class) cls);
        synchronized (this) {
            a a2;
            Integer num = (Integer) a((Class) cls).ceilingKey(Integer.valueOf(i));
            if (a(i, num)) {
                a2 = this.b.a(num.intValue(), cls);
            } else {
                a2 = this.b.a(i, cls);
            }
            a = a(a2);
            if (a != null) {
                this.f -= b.a(a) * b.b();
                b(b.a(a), cls);
            }
        }
        if (a != null) {
            return a;
        }
        if (Log.isLoggable(b.a(), 2)) {
            String a3 = b.a();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Allocated ");
            stringBuilder.append(i);
            stringBuilder.append(" bytes");
            Log.v(a3, stringBuilder.toString());
        }
        return b.a(i);
    }

    @Nullable
    private <T> T a(a aVar) {
        return this.a.a((m) aVar);
    }

    private boolean b(int i) {
        return i <= this.e / 2;
    }

    private boolean a(int i, Integer num) {
        return num != null && (b() || num.intValue() <= 8 * i);
    }

    private boolean b() {
        return this.f == 0 || this.e / this.f >= 2;
    }

    public synchronized void a() {
        c(0);
    }

    public synchronized void a(int i) {
        if (i >= 40) {
            try {
                a();
            } catch (Throwable th) {
            }
        } else if (i >= 20) {
            c(this.e / 2);
        }
    }

    private void c() {
        c(this.e);
    }

    private void c(int i) {
        while (this.f > i) {
            Object a = this.a.a();
            h.a(a);
            a a2 = a(a);
            this.f -= a2.a(a) * a2.b();
            b(a2.a(a), a.getClass());
            if (Log.isLoggable(a2.a(), 2)) {
                String a3 = a2.a();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("evicted: ");
                stringBuilder.append(a2.a(a));
                Log.v(a3, stringBuilder.toString());
            }
        }
    }

    private void b(int i, Class<?> cls) {
        NavigableMap a = a((Class) cls);
        Integer num = (Integer) a.get(Integer.valueOf(i));
        if (num == null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Tried to decrement empty size, size: ");
            stringBuilder.append(i);
            stringBuilder.append(", this: ");
            stringBuilder.append(this);
            throw new NullPointerException(stringBuilder.toString());
        } else if (num.intValue() == 1) {
            a.remove(Integer.valueOf(i));
        } else {
            a.put(Integer.valueOf(i), Integer.valueOf(num.intValue() - 1));
        }
    }

    private NavigableMap<Integer, Integer> a(Class<?> cls) {
        NavigableMap<Integer, Integer> navigableMap = (NavigableMap) this.c.get(cls);
        if (navigableMap != null) {
            return navigableMap;
        }
        TreeMap treeMap = new TreeMap();
        this.c.put(cls, treeMap);
        return treeMap;
    }

    private <T> a<T> a(T t) {
        return b(t.getClass());
    }

    private <T> a<T> b(Class<T> cls) {
        a<T> aVar = (a) this.d.get(cls);
        if (aVar == null) {
            if (cls.equals(int[].class)) {
                aVar = new i();
            } else if (cls.equals(byte[].class)) {
                aVar = new g();
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("No array pool found for: ");
                stringBuilder.append(cls.getSimpleName());
                throw new IllegalArgumentException(stringBuilder.toString());
            }
            this.d.put(cls, aVar);
        }
        return aVar;
    }
}
