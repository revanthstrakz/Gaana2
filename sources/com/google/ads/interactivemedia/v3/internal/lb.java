package com.google.ads.interactivemedia.v3.internal;

import java.io.Serializable;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

public abstract class lb<K, V> implements Serializable, Map<K, V> {
    static final Entry<?, ?>[] a = new Entry[0];
    private transient lc<Entry<K, V>> b;
    private transient lc<K> c;
    private transient kz<V> d;

    public static class a<K, V> {
        Comparator<? super V> a;
        Object[] b;
        int c;
        boolean d;

        public a() {
            this(4);
        }

        a(int i) {
            this.b = new Object[(2 * i)];
            this.c = 0;
            this.d = false;
        }

        private void a(int i) {
            i *= 2;
            if (i > this.b.length) {
                this.b = Arrays.copyOf(this.b, com.google.ads.interactivemedia.v3.internal.kz.a.a(this.b.length, i));
                this.d = false;
            }
        }

        public a<K, V> a(K k, V v) {
            a(this.c + 1);
            kv.a((Object) k, (Object) v);
            this.b[this.c * 2] = k;
            this.b[(2 * this.c) + 1] = v;
            this.c++;
            return this;
        }

        public lb<K, V> a() {
            b();
            this.d = true;
            return lk.a(this.c, this.b);
        }

        /* Access modifiers changed, original: 0000 */
        public void b() {
            if (this.a != null) {
                if (this.d) {
                    this.b = Arrays.copyOf(this.b, this.c * 2);
                }
                Entry[] entryArr = new Entry[this.c];
                int i = 0;
                for (int i2 = 0; i2 < this.c; i2++) {
                    int i3 = 2 * i2;
                    entryArr[i2] = new SimpleImmutableEntry(this.b[i3], this.b[i3 + 1]);
                }
                Arrays.sort(entryArr, 0, this.c, lh.a(this.a).a(lf.a()));
                while (i < this.c) {
                    int i4 = 2 * i;
                    this.b[i4] = entryArr[i].getKey();
                    this.b[i4 + 1] = entryArr[i].getValue();
                    i++;
                }
            }
        }
    }

    lb() {
    }

    public abstract lc<Entry<K, V>> b();

    public abstract lc<K> d();

    public abstract kz<V> f();

    public abstract V get(Object obj);

    @Deprecated
    public final V put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final V remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    public boolean containsValue(Object obj) {
        return values().contains(obj);
    }

    public final V getOrDefault(Object obj, V v) {
        V v2 = get(obj);
        return v2 != null ? v2 : v;
    }

    /* renamed from: a */
    public lc<Entry<K, V>> entrySet() {
        lc<Entry<K, V>> lcVar = this.b;
        if (lcVar != null) {
            return lcVar;
        }
        lc b = b();
        this.b = b;
        return b;
    }

    /* renamed from: c */
    public lc<K> keySet() {
        lc<K> lcVar = this.c;
        if (lcVar != null) {
            return lcVar;
        }
        lc d = d();
        this.c = d;
        return d;
    }

    /* renamed from: e */
    public kz<V> values() {
        kz<V> kzVar = this.d;
        if (kzVar != null) {
            return kzVar;
        }
        kz f = f();
        this.d = f;
        return f;
    }

    public boolean equals(Object obj) {
        return lf.a(this, obj);
    }

    public int hashCode() {
        return ll.a(entrySet());
    }

    public String toString() {
        return lf.a(this);
    }
}
