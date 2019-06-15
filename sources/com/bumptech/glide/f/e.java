package com.bumptech.glide.f;

import android.support.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class e<T, Y> {
    private final LinkedHashMap<T, Y> a = new LinkedHashMap(100, 0.75f, true);
    private final int b;
    private int c;
    private int d = 0;

    /* Access modifiers changed, original: protected */
    public int a(Y y) {
        return 1;
    }

    /* Access modifiers changed, original: protected */
    public void a(T t, Y y) {
    }

    public e(int i) {
        this.b = i;
        this.c = i;
    }

    public synchronized int b() {
        return this.d;
    }

    @Nullable
    public synchronized Y b(T t) {
        return this.a.get(t);
    }

    public synchronized Y b(T t, Y y) {
        if (a(y) >= this.c) {
            a(t, y);
            return null;
        }
        Object put = this.a.put(t, y);
        if (y != null) {
            this.d += a(y);
        }
        if (put != null) {
            this.d -= a(put);
        }
        c();
        return put;
    }

    @Nullable
    public synchronized Y c(T t) {
        Object remove;
        remove = this.a.remove(t);
        if (remove != null) {
            this.d -= a(remove);
        }
        return remove;
    }

    public void a() {
        b(0);
    }

    /* Access modifiers changed, original: protected|declared_synchronized */
    public synchronized void b(int i) {
        while (this.d > i) {
            Entry entry = (Entry) this.a.entrySet().iterator().next();
            Object value = entry.getValue();
            this.d -= a(value);
            Object key = entry.getKey();
            this.a.remove(key);
            a(key, value);
        }
    }

    private void c() {
        b(this.c);
    }
}
