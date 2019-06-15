package com.google.android.gms.internal.icing;

import java.util.ArrayList;
import java.util.List;

final class zzfd<E> extends zzca<E> {
    private static final zzfd<Object> zznb;
    private final List<E> zzlp;

    public static <E> zzfd<E> zzcz() {
        return zznb;
    }

    zzfd() {
        this(new ArrayList(10));
    }

    private zzfd(List<E> list) {
        this.zzlp = list;
    }

    public final void add(int i, E e) {
        zzak();
        this.zzlp.add(i, e);
        this.modCount++;
    }

    public final E get(int i) {
        return this.zzlp.get(i);
    }

    public final E remove(int i) {
        zzak();
        Object remove = this.zzlp.remove(i);
        this.modCount++;
        return remove;
    }

    public final E set(int i, E e) {
        zzak();
        Object obj = this.zzlp.set(i, e);
        this.modCount++;
        return obj;
    }

    public final int size() {
        return this.zzlp.size();
    }

    public final /* synthetic */ zzdq zzj(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i);
        arrayList.addAll(this.zzlp);
        return new zzfd(arrayList);
    }

    static {
        zzfd zzfd = new zzfd();
        zznb = zzfd;
        zzfd.zzaj();
    }
}
