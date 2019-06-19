package com.google.ads.interactivemedia.v3.internal;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

public final class hc<K, V> extends AbstractMap<K, V> implements Serializable {
    static final /* synthetic */ boolean f = true;
    private static final Comparator<Comparable> g = new Comparator<Comparable>() {
        /* renamed from: a */
        public int compare(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    };
    Comparator<? super K> a;
    d<K, V> b;
    int c;
    int d;
    final d<K, V> e;
    private a h;
    private b i;

    private abstract class c<T> implements Iterator<T> {
        d<K, V> b = hc.this.e.d;
        d<K, V> c = null;
        int d = hc.this.d;

        c() {
        }

        public final boolean hasNext() {
            return this.b != hc.this.e;
        }

        /* Access modifiers changed, original: final */
        public final d<K, V> b() {
            d dVar = this.b;
            if (dVar == hc.this.e) {
                throw new NoSuchElementException();
            } else if (hc.this.d != this.d) {
                throw new ConcurrentModificationException();
            } else {
                this.b = dVar.d;
                this.c = dVar;
                return dVar;
            }
        }

        public final void remove() {
            if (this.c == null) {
                throw new IllegalStateException();
            }
            hc.this.a(this.c, true);
            this.c = null;
            this.d = hc.this.d;
        }
    }

    class a extends AbstractSet<Entry<K, V>> {
        a() {
        }

        public int size() {
            return hc.this.c;
        }

        public Iterator<Entry<K, V>> iterator() {
            return new c<Entry<K, V>>() {
                {
                    hc hcVar = hc.this;
                }

                /* renamed from: a */
                public Entry<K, V> next() {
                    return b();
                }
            };
        }

        public boolean contains(Object obj) {
            return (obj instanceof Entry) && hc.this.a((Entry) obj) != null;
        }

        public boolean remove(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            d a = hc.this.a((Entry) obj);
            if (a == null) {
                return false;
            }
            hc.this.a(a, true);
            return true;
        }

        public void clear() {
            hc.this.clear();
        }
    }

    final class b extends AbstractSet<K> {
        b() {
        }

        public int size() {
            return hc.this.c;
        }

        public Iterator<K> iterator() {
            return new c<K>() {
                {
                    hc hcVar = hc.this;
                }

                public K next() {
                    return b().f;
                }
            };
        }

        public boolean contains(Object obj) {
            return hc.this.containsKey(obj);
        }

        public boolean remove(Object obj) {
            return hc.this.b(obj) != null;
        }

        public void clear() {
            hc.this.clear();
        }
    }

    static final class d<K, V> implements Entry<K, V> {
        d<K, V> a;
        d<K, V> b;
        d<K, V> c;
        d<K, V> d;
        d<K, V> e;
        final K f;
        V g;
        int h;

        d() {
            this.f = null;
            this.e = this;
            this.d = this;
        }

        d(d<K, V> dVar, K k, d<K, V> dVar2, d<K, V> dVar3) {
            this.a = dVar;
            this.f = k;
            this.h = 1;
            this.d = dVar2;
            this.e = dVar3;
            dVar3.d = this;
            dVar2.e = this;
        }

        public K getKey() {
            return this.f;
        }

        public V getValue() {
            return this.g;
        }

        public V setValue(V v) {
            Object obj = this.g;
            this.g = v;
            return obj;
        }

        public boolean equals(Object obj) {
            boolean z = false;
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            if (this.f != null ? !this.f.equals(entry.getKey()) : entry.getKey() != null) {
                if (this.g != null ? !this.g.equals(entry.getValue()) : entry.getValue() != null) {
                    z = true;
                }
            }
            return z;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = this.f == null ? 0 : this.f.hashCode();
            if (this.g != null) {
                i = this.g.hashCode();
            }
            return hashCode ^ i;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.f);
            stringBuilder.append("=");
            stringBuilder.append(this.g);
            return stringBuilder.toString();
        }

        public d<K, V> a() {
            d<K, V> dVar = this;
            for (d<K, V> dVar2 = this.b; dVar2 != null; dVar2 = dVar2.b) {
                dVar = dVar2;
            }
            return dVar;
        }

        public d<K, V> b() {
            d<K, V> dVar = this;
            for (d<K, V> dVar2 = this.c; dVar2 != null; dVar2 = dVar2.c) {
                dVar = dVar2;
            }
            return dVar;
        }
    }

    public hc() {
        this(g);
    }

    public hc(Comparator<? super K> comparator) {
        Comparator comparator2;
        this.c = 0;
        this.d = 0;
        this.e = new d();
        if (comparator2 == null) {
            comparator2 = g;
        }
        this.a = comparator2;
    }

    public int size() {
        return this.c;
    }

    public V get(Object obj) {
        d a = a(obj);
        return a != null ? a.g : null;
    }

    public boolean containsKey(Object obj) {
        return a(obj) != null;
    }

    public V put(K k, V v) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        d a = a((Object) k, true);
        Object obj = a.g;
        a.g = v;
        return obj;
    }

    public void clear() {
        this.b = null;
        this.c = 0;
        this.d++;
        d dVar = this.e;
        dVar.e = dVar;
        dVar.d = dVar;
    }

    public V remove(Object obj) {
        d b = b(obj);
        return b != null ? b.g : null;
    }

    /* Access modifiers changed, original: 0000 */
    public d<K, V> a(K k, boolean z) {
        int compareTo;
        Comparator comparator = this.a;
        d dVar = this.b;
        if (dVar != null) {
            Comparable comparable = comparator == g ? (Comparable) k : null;
            while (true) {
                if (comparable != null) {
                    compareTo = comparable.compareTo(dVar.f);
                } else {
                    compareTo = comparator.compare(k, dVar.f);
                }
                if (compareTo == 0) {
                    return dVar;
                }
                d dVar2 = compareTo < 0 ? dVar.b : dVar.c;
                if (dVar2 == null) {
                    break;
                }
                dVar = dVar2;
            }
        } else {
            compareTo = 0;
        }
        if (!z) {
            return null;
        }
        d<K, V> dVar3;
        d dVar4 = this.e;
        if (dVar != null) {
            dVar3 = new d(dVar, k, dVar4, dVar4.e);
            if (compareTo < 0) {
                dVar.b = dVar3;
            } else {
                dVar.c = dVar3;
            }
            b(dVar, true);
        } else if (comparator != g || (k instanceof Comparable)) {
            dVar3 = new d(dVar, k, dVar4, dVar4.e);
            this.b = dVar3;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(k.getClass().getName());
            stringBuilder.append(" is not Comparable");
            throw new ClassCastException(stringBuilder.toString());
        }
        this.c++;
        this.d++;
        return dVar3;
    }

    /* Access modifiers changed, original: 0000 */
    public d<K, V> a(Object obj) {
        d<K, V> a;
        if (obj != null) {
            try {
                a = a(obj, false);
            } catch (ClassCastException unused) {
                return null;
            }
        }
        a = null;
        return a;
    }

    /* Access modifiers changed, original: 0000 */
    public d<K, V> a(Entry<?, ?> entry) {
        d<K, V> a = a(entry.getKey());
        Object obj = (a == null || !a(a.g, entry.getValue())) ? null : 1;
        return obj != null ? a : null;
    }

    private boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /* Access modifiers changed, original: 0000 */
    public void a(d<K, V> dVar, boolean z) {
        if (z) {
            dVar.e.d = dVar.d;
            dVar.d.e = dVar.e;
        }
        d dVar2 = dVar.b;
        d dVar3 = dVar.c;
        d dVar4 = dVar.a;
        int i = 0;
        if (dVar2 == null || dVar3 == null) {
            if (dVar2 != null) {
                a((d) dVar, dVar2);
                dVar.b = null;
            } else if (dVar3 != null) {
                a((d) dVar, dVar3);
                dVar.c = null;
            } else {
                a((d) dVar, null);
            }
            b(dVar4, false);
            this.c--;
            this.d++;
            return;
        }
        int i2;
        dVar2 = dVar2.h > dVar3.h ? dVar2.b() : dVar3.a();
        a(dVar2, false);
        dVar3 = dVar.b;
        if (dVar3 != null) {
            i2 = dVar3.h;
            dVar2.b = dVar3;
            dVar3.a = dVar2;
            dVar.b = null;
        } else {
            i2 = 0;
        }
        dVar3 = dVar.c;
        if (dVar3 != null) {
            i = dVar3.h;
            dVar2.c = dVar3;
            dVar3.a = dVar2;
            dVar.c = null;
        }
        dVar2.h = Math.max(i2, i) + 1;
        a((d) dVar, dVar2);
    }

    /* Access modifiers changed, original: 0000 */
    public d<K, V> b(Object obj) {
        d a = a(obj);
        if (a != null) {
            a(a, true);
        }
        return a;
    }

    private void a(d<K, V> dVar, d<K, V> dVar2) {
        d dVar3 = dVar.a;
        dVar.a = null;
        if (dVar2 != null) {
            dVar2.a = dVar3;
        }
        if (dVar3 == null) {
            this.b = dVar2;
        } else if (dVar3.b == dVar) {
            dVar3.b = dVar2;
        } else if (f || dVar3.c == dVar) {
            dVar3.c = dVar2;
        } else {
            throw new AssertionError();
        }
    }

    private void b(d<K, V> dVar, boolean z) {
        d dVar2;
        while (dVar2 != null) {
            d dVar3 = dVar2.b;
            d dVar4 = dVar2.c;
            int i = 0;
            int i2 = dVar3 != null ? dVar3.h : 0;
            int i3 = dVar4 != null ? dVar4.h : 0;
            int i4 = i2 - i3;
            d dVar5;
            if (i4 == -2) {
                dVar3 = dVar4.b;
                dVar5 = dVar4.c;
                i2 = dVar5 != null ? dVar5.h : 0;
                if (dVar3 != null) {
                    i = dVar3.h;
                }
                i -= i2;
                if (i == -1 || (i == 0 && !z)) {
                    a(dVar2);
                } else if (f || i == 1) {
                    b(dVar4);
                    a(dVar2);
                } else {
                    throw new AssertionError();
                }
                if (z) {
                    return;
                }
            } else if (i4 == 2) {
                dVar4 = dVar3.b;
                dVar5 = dVar3.c;
                i2 = dVar5 != null ? dVar5.h : 0;
                if (dVar4 != null) {
                    i = dVar4.h;
                }
                i -= i2;
                if (i == 1 || (i == 0 && !z)) {
                    b(dVar2);
                } else if (f || i == -1) {
                    a(dVar3);
                    b(dVar2);
                } else {
                    throw new AssertionError();
                }
                if (z) {
                    return;
                }
            } else if (i4 == 0) {
                dVar2.h = i2 + 1;
                if (z) {
                    return;
                }
            } else if (f || i4 == -1 || i4 == 1) {
                dVar2.h = Math.max(i2, i3) + 1;
                if (!z) {
                    return;
                }
            } else {
                throw new AssertionError();
            }
            dVar2 = dVar2.a;
        }
    }

    private void a(d<K, V> dVar) {
        d dVar2 = dVar.b;
        d dVar3 = dVar.c;
        d dVar4 = dVar3.b;
        d dVar5 = dVar3.c;
        dVar.c = dVar4;
        if (dVar4 != null) {
            dVar4.a = dVar;
        }
        a((d) dVar, dVar3);
        dVar3.b = dVar;
        dVar.a = dVar3;
        int i = 0;
        dVar.h = Math.max(dVar2 != null ? dVar2.h : 0, dVar4 != null ? dVar4.h : 0) + 1;
        int i2 = dVar.h;
        if (dVar5 != null) {
            i = dVar5.h;
        }
        dVar3.h = Math.max(i2, i) + 1;
    }

    private void b(d<K, V> dVar) {
        d dVar2 = dVar.b;
        d dVar3 = dVar.c;
        d dVar4 = dVar2.b;
        d dVar5 = dVar2.c;
        dVar.b = dVar5;
        if (dVar5 != null) {
            dVar5.a = dVar;
        }
        a((d) dVar, dVar2);
        dVar2.c = dVar;
        dVar.a = dVar2;
        int i = 0;
        dVar.h = Math.max(dVar3 != null ? dVar3.h : 0, dVar5 != null ? dVar5.h : 0) + 1;
        int i2 = dVar.h;
        if (dVar4 != null) {
            i = dVar4.h;
        }
        dVar2.h = Math.max(i2, i) + 1;
    }

    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = this.h;
        if (set != null) {
            return set;
        }
        a aVar = new a();
        this.h = aVar;
        return aVar;
    }

    public Set<K> keySet() {
        Set<K> set = this.i;
        if (set != null) {
            return set;
        }
        b bVar = new b();
        this.i = bVar;
        return bVar;
    }
}
