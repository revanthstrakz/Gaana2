package com.google.android.gms.internal.vision;

import java.util.ArrayList;
import java.util.List;

final class zzht<E> extends zzef<E> {
    private static final zzht<Object> zzzz;
    private final List<E> zzym;

    public static <E> zzht<E> zzgm() {
        return zzzz;
    }

    zzht() {
        this(new ArrayList(10));
    }

    private zzht(List<E> list) {
        this.zzym = list;
    }

    public final void add(int i, E e) {
        zzcj();
        this.zzym.add(i, e);
        this.modCount++;
    }

    public final E get(int i) {
        return this.zzym.get(i);
    }

    public final E remove(int i) {
        zzcj();
        Object remove = this.zzym.remove(i);
        this.modCount++;
        return remove;
    }

    public final E set(int i, E e) {
        zzcj();
        Object obj = this.zzym.set(i, e);
        this.modCount++;
        return obj;
    }

    public final int size() {
        return this.zzym.size();
    }

    public final /* synthetic */ zzge zzah(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i);
        arrayList.addAll(this.zzym);
        return new zzht(arrayList);
    }

    static {
        zzht zzht = new zzht();
        zzzz = zzht;
        zzht.zzci();
    }
}
