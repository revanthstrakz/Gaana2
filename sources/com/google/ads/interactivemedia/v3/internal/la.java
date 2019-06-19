package com.google.ads.interactivemedia.v3.internal;

import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

public abstract class la<E> extends kz<E> implements List<E>, RandomAccess {
    private static final lo<Object> a = new a(lj.a, 0);

    static class a<E> extends kt<E> {
        private final la<E> a;

        a(la<E> laVar, int i) {
            super(laVar.size(), i);
            this.a = laVar;
        }

        /* Access modifiers changed, original: protected */
        public E a(int i) {
            return this.a.get(i);
        }
    }

    class b extends la<E> {
        final transient int a;
        final transient int b;

        b(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        /* Access modifiers changed, original: 0000 */
        public boolean f() {
            return true;
        }

        public int size() {
            return this.b;
        }

        /* Access modifiers changed, original: 0000 */
        public Object[] b() {
            return la.this.b();
        }

        /* Access modifiers changed, original: 0000 */
        public int c() {
            return la.this.c() + this.a;
        }

        /* Access modifiers changed, original: 0000 */
        public int d() {
            return (la.this.c() + this.a) + this.b;
        }

        public E get(int i) {
            kr.a(i, this.b);
            return la.this.get(i + this.a);
        }

        /* renamed from: a */
        public la<E> subList(int i, int i2) {
            kr.a(i, i2, this.b);
            return la.this.subList(i + this.a, i2 + this.a);
        }
    }

    public static <E> la<E> g() {
        return lj.a;
    }

    public final la<E> e() {
        return this;
    }

    public static <E> la<E> a(Collection<? extends E> collection) {
        if (!(collection instanceof kz)) {
            return b(collection.toArray());
        }
        la<E> e = ((kz) collection).e();
        if (e.f()) {
            e = a(e.toArray());
        }
        return e;
    }

    private static <E> la<E> b(Object... objArr) {
        return a(lg.a(objArr));
    }

    static <E> la<E> a(Object[] objArr) {
        return b(objArr, objArr.length);
    }

    static <E> la<E> b(Object[] objArr, int i) {
        if (i == 0) {
            return g();
        }
        return new lj(objArr, i);
    }

    la() {
    }

    /* renamed from: a */
    public ln<E> iterator() {
        return listIterator();
    }

    /* renamed from: h */
    public lo<E> listIterator() {
        return listIterator(0);
    }

    /* renamed from: a */
    public lo<E> listIterator(int i) {
        kr.b(i, size());
        if (isEmpty()) {
            return a;
        }
        return new a(this, i);
    }

    public int indexOf(Object obj) {
        return obj == null ? -1 : le.b(this, obj);
    }

    public int lastIndexOf(Object obj) {
        return obj == null ? -1 : le.c(this, obj);
    }

    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    /* renamed from: a */
    public la<E> subList(int i, int i2) {
        kr.a(i, i2, size());
        int i3 = i2 - i;
        if (i3 == size()) {
            return this;
        }
        if (i3 == 0) {
            return g();
        }
        return b(i, i2);
    }

    /* Access modifiers changed, original: 0000 */
    public la<E> b(int i, int i2) {
        return new b(i, i2 - i);
    }

    @Deprecated
    public final boolean addAll(int i, Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final E set(int i, E e) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void add(int i, E e) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final E remove(int i) {
        throw new UnsupportedOperationException();
    }

    /* Access modifiers changed, original: 0000 */
    public int a(Object[] objArr, int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            objArr[i + i2] = get(i2);
        }
        return i + size;
    }

    public boolean equals(Object obj) {
        return le.a(this, obj);
    }

    public int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < size(); i2++) {
            i = (((31 * i) + get(i2).hashCode()) ^ -1) ^ -1;
        }
        return i;
    }
}
