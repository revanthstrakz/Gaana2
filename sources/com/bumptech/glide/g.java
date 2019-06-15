package com.bumptech.glide;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.widget.ImageView;
import com.bumptech.glide.load.engine.h;
import com.bumptech.glide.request.a.e;
import com.bumptech.glide.request.a.i;
import com.bumptech.glide.request.f;
import java.util.Map;
import java.util.Map.Entry;

@TargetApi(14)
public class g extends ContextWrapper {
    @VisibleForTesting
    static final j<?, ?> a = new d();
    private final Handler b = new Handler(Looper.getMainLooper());
    private final Registry c;
    private final e d;
    private final f e;
    private final Map<Class<?>, j<?, ?>> f;
    private final h g;
    private final int h;

    public g(Context context, Registry registry, e eVar, f fVar, Map<Class<?>, j<?, ?>> map, h hVar, int i) {
        super(context.getApplicationContext());
        this.c = registry;
        this.d = eVar;
        this.e = fVar;
        this.f = map;
        this.g = hVar;
        this.h = i;
    }

    public f a() {
        return this.e;
    }

    @NonNull
    public <T> j<?, T> a(Class<T> cls) {
        j<?, T> jVar = (j) this.f.get(cls);
        if (jVar == null) {
            for (Entry entry : this.f.entrySet()) {
                if (((Class) entry.getKey()).isAssignableFrom(cls)) {
                    j jVar2 = (j) entry.getValue();
                }
            }
        }
        return jVar == null ? a : jVar;
    }

    public <X> i<X> a(ImageView imageView, Class<X> cls) {
        return this.d.a(imageView, cls);
    }

    public Handler b() {
        return this.b;
    }

    public h c() {
        return this.g;
    }

    public Registry d() {
        return this.c;
    }

    public int e() {
        return this.h;
    }
}
