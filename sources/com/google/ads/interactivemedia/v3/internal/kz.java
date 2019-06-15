package com.google.ads.interactivemedia.v3.internal;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;

public abstract class kz<E> extends AbstractCollection<E> implements Serializable {
    private static final Object[] a = new Object[0];

    public static abstract class a<E> {
        static int a(int i, int i2) {
            if (i2 < 0) {
                throw new AssertionError("cannot store more than MAX_VALUE elements");
            }
            i = (i + (i >> 1)) + 1;
            if (i < i2) {
                i = Integer.highestOneBit(i2 - 1) << 1;
            }
            return i < 0 ? Integer.MAX_VALUE : i;
        }
    }

    kz() {
    }

    /* renamed from: a */
    public abstract ln<E> iterator();

    /* Access modifiers changed, original: 0000 */
    public Object[] b() {
        return null;
    }

    public abstract boolean contains(Object obj);

    public abstract boolean f();

    public final Object[] toArray() {
        return toArray(a);
    }

    public final <T> T[] toArray(T[] tArr) {
        Object[] tArr2;
        kr.a(tArr2);
        int size = size();
        if (tArr2.length < size) {
            Object[] b = b();
            if (b != null) {
                return li.a(b, c(), d(), tArr2);
            }
            tArr2 = lg.a((Object[]) tArr2, size);
        } else if (tArr2.length > size) {
            tArr2[size] = null;
        }
        a(tArr2, 0);
        return tArr2;
    }

    /* Access modifiers changed, original: 0000 */
    public int c() {
        throw new UnsupportedOperationException();
    }

    /* Access modifiers changed, original: 0000 */
    public int d() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean addAll(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public la<E> e() {
        return isEmpty() ? la.g() : la.a(toArray());
    }

    /* Access modifiers changed, original: 0000 */
    public int a(Object[] objArr, int i) {
        ln a = iterator();
        while (a.hasNext()) {
            int i2 = i + 1;
            objArr[i] = a.next();
            i = i2;
        }
        return i;
    }
}
