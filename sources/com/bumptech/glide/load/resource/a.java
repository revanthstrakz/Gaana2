package com.bumptech.glide.load.resource;

import com.bumptech.glide.f.h;
import com.bumptech.glide.load.engine.q;

public class a<T> implements q<T> {
    protected final T a;

    public final int d() {
        return 1;
    }

    public void e() {
    }

    public a(T t) {
        this.a = h.a((Object) t);
    }

    public Class<T> b() {
        return this.a.getClass();
    }

    public final T c() {
        return this.a;
    }
}
