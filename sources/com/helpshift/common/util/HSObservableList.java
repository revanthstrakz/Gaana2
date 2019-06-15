package com.helpshift.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class HSObservableList<T> extends ArrayList<T> {
    private b a;

    public HSObservableList(List<T> list) {
        super(list);
    }

    public void a(b bVar) {
        this.a = bVar;
    }

    public boolean add(T t) {
        boolean add = super.add(t);
        if (add && this.a != null) {
            this.a.a(indexOf(t), 1);
        }
        return add;
    }

    public boolean addAll(Collection<? extends T> collection) {
        boolean addAll = super.addAll(collection);
        if (addAll && this.a != null) {
            this.a.a(indexOf(collection.iterator().next()), collection.size());
        }
        return addAll;
    }

    public T a(int i, T t) {
        Object obj = set(i, t);
        if (!(obj == null || this.a == null)) {
            this.a.b(i, 1);
        }
        return obj;
    }

    public void a(Comparator<T> comparator) {
        ArrayList arrayList = new ArrayList(this);
        Collections.sort(this, comparator);
        HashSet hashSet = new HashSet();
        for (Object next : arrayList) {
            int indexOf = arrayList.indexOf(next);
            int indexOf2 = indexOf(next);
            if (indexOf != indexOf2) {
                hashSet.add(Integer.valueOf(indexOf));
                hashSet.add(Integer.valueOf(indexOf2));
            }
        }
        if (this.a != null && !hashSet.isEmpty()) {
            int intValue = ((Integer) Collections.min(hashSet)).intValue();
            this.a.b(intValue, (((Integer) Collections.max(hashSet)).intValue() - intValue) + 1);
        }
    }
}
