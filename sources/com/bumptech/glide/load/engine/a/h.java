package com.bumptech.glide.load.engine.a;

import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class h<K extends m, V> {
    private final a<K, V> a = new a();
    private final Map<K, a<K, V>> b = new HashMap();

    private static class a<K, V> {
        final K a;
        a<K, V> b;
        a<K, V> c;
        private List<V> d;

        public a() {
            this(null);
        }

        public a(K k) {
            this.c = this;
            this.b = this;
            this.a = k;
        }

        @Nullable
        public V a() {
            int b = b();
            return b > 0 ? this.d.remove(b - 1) : null;
        }

        public int b() {
            return this.d != null ? this.d.size() : 0;
        }

        public void a(V v) {
            if (this.d == null) {
                this.d = new ArrayList();
            }
            this.d.add(v);
        }
    }

    h() {
    }

    public void a(K k, V v) {
        a aVar = (a) this.b.get(k);
        if (aVar == null) {
            aVar = new a(k);
            b(aVar);
            this.b.put(k, aVar);
        } else {
            k.a();
        }
        aVar.a(v);
    }

    @Nullable
    public V a(K k) {
        a aVar = (a) this.b.get(k);
        if (aVar == null) {
            aVar = new a(k);
            this.b.put(k, aVar);
        } else {
            k.a();
        }
        a(aVar);
        return aVar.a();
    }

    @Nullable
    public V a() {
        for (Object obj = this.a.c; !obj.equals(this.a); obj = obj.c) {
            Object a = obj.a();
            if (a != null) {
                return a;
            }
            d(obj);
            this.b.remove(obj.a);
            ((m) obj.a).a();
        }
        return null;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("GroupedLinkedMap( ");
        Object obj = null;
        for (Object obj2 = this.a.b; !obj2.equals(this.a); obj2 = obj2.b) {
            obj = 1;
            stringBuilder.append('{');
            stringBuilder.append(obj2.a);
            stringBuilder.append(':');
            stringBuilder.append(obj2.b());
            stringBuilder.append("}, ");
        }
        if (obj != null) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }
        stringBuilder.append(" )");
        return stringBuilder.toString();
    }

    private void a(a<K, V> aVar) {
        d(aVar);
        aVar.c = this.a;
        aVar.b = this.a.b;
        c(aVar);
    }

    private void b(a<K, V> aVar) {
        d(aVar);
        aVar.c = this.a.c;
        aVar.b = this.a;
        c(aVar);
    }

    private static <K, V> void c(a<K, V> aVar) {
        aVar.b.c = aVar;
        aVar.c.b = aVar;
    }

    private static <K, V> void d(a<K, V> aVar) {
        aVar.c.b = aVar.b;
        aVar.b.c = aVar.c;
    }
}
