package com.google.ads.interactivemedia.v3.internal;

import java.util.Set;

public abstract class lc<E> extends kz<E> implements Set<E> {
    private transient la<E> a;

    static int a(int i) {
        i = Math.max(i, 2);
        boolean z = true;
        if (i < 751619276) {
            int highestOneBit = Integer.highestOneBit(i - 1) << 1;
            while (((double) highestOneBit) * 0.7d < ((double) i)) {
                highestOneBit <<= 1;
            }
            return highestOneBit;
        }
        if (i >= 1073741824) {
            z = false;
        }
        kr.a(z, (Object) "collection too large");
        return 1073741824;
    }

    /* renamed from: a */
    public abstract ln<E> iterator();

    /* Access modifiers changed, original: 0000 */
    public boolean g() {
        return false;
    }

    lc() {
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof lc) && g() && ((lc) obj).g() && hashCode() != obj.hashCode()) {
            return false;
        }
        return ll.a(this, obj);
    }

    public int hashCode() {
        return ll.a(this);
    }

    public la<E> e() {
        la<E> laVar = this.a;
        if (laVar != null) {
            return laVar;
        }
        la h = h();
        this.a = h;
        return h;
    }

    /* Access modifiers changed, original: 0000 */
    public la<E> h() {
        return la.a(toArray());
    }
}
