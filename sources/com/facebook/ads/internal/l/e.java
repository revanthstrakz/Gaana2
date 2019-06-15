package com.facebook.ads.internal.l;

import android.support.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class e<T extends f, E extends d> {
    private final Map<Class<E>, List<WeakReference<T>>> a = new HashMap();
    private final Queue<E> b = new ArrayDeque();

    private void a(List<WeakReference<T>> list) {
        if (list != null) {
            int i = 0;
            int i2 = 0;
            while (i < list.size()) {
                WeakReference weakReference = (WeakReference) list.get(i);
                if (weakReference.get() != null) {
                    int i3 = i2 + 1;
                    list.set(i2, weakReference);
                    i2 = i3;
                }
                i++;
            }
            for (i = list.size() - 1; i >= i2; i--) {
                list.remove(i);
            }
        }
    }

    private void b(E e) {
        if (this.a != null) {
            List list = (List) this.a.get(e.getClass());
            if (list != null) {
                a(list);
                if (!list.isEmpty()) {
                    for (WeakReference weakReference : new ArrayList(list)) {
                        f fVar = (f) weakReference.get();
                        if (fVar != null && fVar.b(e)) {
                            fVar.a(e);
                        }
                    }
                }
            }
        }
    }

    public synchronized void a(E e) {
        if (this.b.isEmpty()) {
            this.b.add(e);
            while (!this.b.isEmpty()) {
                b((d) this.b.peek());
                this.b.remove();
            }
        } else {
            this.b.add(e);
        }
    }

    public synchronized void a(T... tArr) {
        if (tArr != null) {
            for (f a : tArr) {
                a(a);
            }
        }
    }

    public synchronized boolean a(T t) {
        if (t == null) {
            return false;
        }
        Class a = t.a();
        if (this.a.get(a) == null) {
            this.a.put(a, new ArrayList());
        }
        List list = (List) this.a.get(a);
        a(list);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (((WeakReference) list.get(i)).get() == t) {
                return false;
            }
        }
        return list.add(new WeakReference(t));
    }

    public synchronized void b(T... tArr) {
        if (tArr != null) {
            for (f b : tArr) {
                b(b);
            }
        }
    }

    public synchronized boolean b(@Nullable T t) {
        if (t == null) {
            return false;
        }
        List list = (List) this.a.get(t.a());
        if (list == null) {
            return false;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (((WeakReference) list.get(i)).get() == t) {
                ((WeakReference) list.get(i)).clear();
                return true;
            }
        }
        return false;
    }
}
