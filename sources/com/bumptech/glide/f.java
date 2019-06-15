package com.bumptech.glide;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import com.bumptech.glide.load.engine.a.b;
import com.bumptech.glide.load.engine.a.e;
import com.bumptech.glide.load.engine.a.j;
import com.bumptech.glide.load.engine.b.g;
import com.bumptech.glide.load.engine.b.i;
import com.bumptech.glide.load.engine.c.a;
import com.bumptech.glide.load.engine.h;
import com.bumptech.glide.manager.d;
import com.bumptech.glide.manager.k;
import java.util.Map;

public final class f {
    private final Map<Class<?>, j<?, ?>> a = new ArrayMap();
    private h b;
    private e c;
    private b d;
    private com.bumptech.glide.load.engine.b.h e;
    private a f;
    private a g;
    private com.bumptech.glide.load.engine.b.a.a h;
    private i i;
    private d j;
    private int k = 4;
    private com.bumptech.glide.request.f l = new com.bumptech.glide.request.f();
    @Nullable
    private k.a m;

    public f a(e eVar) {
        this.c = eVar;
        return this;
    }

    public f a(com.bumptech.glide.load.engine.b.h hVar) {
        this.e = hVar;
        return this;
    }

    public f a(com.bumptech.glide.load.engine.b.a.a aVar) {
        this.h = aVar;
        return this;
    }

    public f a(com.bumptech.glide.request.f fVar) {
        this.l = fVar;
        return this;
    }

    /* Access modifiers changed, original: 0000 */
    public f a(@Nullable k.a aVar) {
        this.m = aVar;
        return this;
    }

    public e a(Context context) {
        if (this.f == null) {
            this.f = a.b();
        }
        if (this.g == null) {
            this.g = a.a();
        }
        if (this.i == null) {
            this.i = new i.a(context).a();
        }
        if (this.j == null) {
            this.j = new com.bumptech.glide.manager.f();
        }
        if (this.c == null) {
            int b = this.i.b();
            if (b > 0) {
                this.c = new com.bumptech.glide.load.engine.a.k(b);
            } else {
                this.c = new com.bumptech.glide.load.engine.a.f();
            }
        }
        if (this.d == null) {
            this.d = new j(this.i.c());
        }
        if (this.e == null) {
            this.e = new g(this.i.a());
        }
        if (this.h == null) {
            this.h = new com.bumptech.glide.load.engine.b.f(context);
        }
        if (this.b == null) {
            this.b = new h(this.e, this.h, this.g, this.f, a.c(), a.d());
        }
        return new e(context, this.b, this.e, this.c, this.d, new k(this.m), this.j, this.k, this.l.lock(), this.a);
    }
}
