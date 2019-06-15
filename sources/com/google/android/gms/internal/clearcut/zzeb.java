package com.google.android.gms.internal.clearcut;

import java.util.ArrayList;
import java.util.List;

final class zzeb<E> extends zzav<E> {
    private static final zzeb<Object> zznf;
    private final List<E> zzls;

    static {
        zzeb zzeb = new zzeb();
        zznf = zzeb;
        zzeb.zzv();
    }

    zzeb() {
        this(new ArrayList(10));
    }

    private zzeb(List<E> list) {
        this.zzls = list;
    }

    public static <E> zzeb<E> zzcn() {
        return zznf;
    }

    public final void add(int i, E e) {
        zzw();
        this.zzls.add(i, e);
        this.modCount++;
    }

    public final E get(int i) {
        return this.zzls.get(i);
    }

    public final E remove(int i) {
        zzw();
        Object remove = this.zzls.remove(i);
        this.modCount++;
        return remove;
    }

    public final E set(int i, E e) {
        zzw();
        Object obj = this.zzls.set(i, e);
        this.modCount++;
        return obj;
    }

    public final int size() {
        return this.zzls.size();
    }

    public final /* synthetic */ zzcn zzi(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i);
        arrayList.addAll(this.zzls);
        return new zzeb(arrayList);
    }
}
