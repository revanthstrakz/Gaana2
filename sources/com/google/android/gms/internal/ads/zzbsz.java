package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;

final class zzbsz<E> extends zzbpo<E> {
    private static final zzbsz<Object> zzfte;
    private final List<E> zzfrq;

    public static <E> zzbsz<E> zzaoh() {
        return zzfte;
    }

    zzbsz() {
        this(new ArrayList(10));
    }

    private zzbsz(List<E> list) {
        this.zzfrq = list;
    }

    public final void add(int i, E e) {
        zzakk();
        this.zzfrq.add(i, e);
        this.modCount++;
    }

    public final E get(int i) {
        return this.zzfrq.get(i);
    }

    public final E remove(int i) {
        zzakk();
        Object remove = this.zzfrq.remove(i);
        this.modCount++;
        return remove;
    }

    public final E set(int i, E e) {
        zzakk();
        Object obj = this.zzfrq.set(i, e);
        this.modCount++;
        return obj;
    }

    public final int size() {
        return this.zzfrq.size();
    }

    public final /* synthetic */ zzbrk zzel(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i);
        arrayList.addAll(this.zzfrq);
        return new zzbsz(arrayList);
    }

    static {
        zzbsz zzbsz = new zzbsz();
        zzfte = zzbsz;
        zzbsz.zzakj();
    }
}
