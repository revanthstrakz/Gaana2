package com.bumptech.glide.manager;

import com.bumptech.glide.request.a.i;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

public final class n implements i {
    private final Set<i<?>> a = Collections.newSetFromMap(new WeakHashMap());

    public void a(i<?> iVar) {
        this.a.add(iVar);
    }

    public void b(i<?> iVar) {
        this.a.remove(iVar);
    }

    public void onStart() {
        for (i onStart : com.bumptech.glide.f.i.a(this.a)) {
            onStart.onStart();
        }
    }

    public void onStop() {
        for (i onStop : com.bumptech.glide.f.i.a(this.a)) {
            onStop.onStop();
        }
    }

    public void onDestroy() {
        for (i onDestroy : com.bumptech.glide.f.i.a(this.a)) {
            onDestroy.onDestroy();
        }
    }

    public List<i<?>> a() {
        return new ArrayList(this.a);
    }

    public void b() {
        this.a.clear();
    }
}
