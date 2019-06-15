package com.bumptech.glide.d;

import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import com.bumptech.glide.f.g;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class d {
    private final AtomicReference<g> a = new AtomicReference();
    private final ArrayMap<g, List<Class<?>>> b = new ArrayMap();

    @Nullable
    public List<Class<?>> a(Class<?> cls, Class<?> cls2) {
        List list;
        Object obj = (g) this.a.getAndSet(null);
        if (obj == null) {
            obj = new g(cls, cls2);
        } else {
            obj.a(cls, cls2);
        }
        synchronized (this.b) {
            list = (List) this.b.get(obj);
        }
        this.a.set(obj);
        return list;
    }

    public void a(Class<?> cls, Class<?> cls2, List<Class<?>> list) {
        synchronized (this.b) {
            this.b.put(new g(cls, cls2), list);
        }
    }
}
