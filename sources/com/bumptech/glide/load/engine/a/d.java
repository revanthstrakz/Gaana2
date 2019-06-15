package com.bumptech.glide.load.engine.a;

import com.bumptech.glide.f.i;
import java.util.Queue;

abstract class d<T extends m> {
    private final Queue<T> a = i.a(20);

    public abstract T b();

    d() {
    }

    /* Access modifiers changed, original: protected */
    public T c() {
        m mVar = (m) this.a.poll();
        return mVar == null ? b() : mVar;
    }

    public void a(T t) {
        if (this.a.size() < 20) {
            this.a.offer(t);
        }
    }
}
