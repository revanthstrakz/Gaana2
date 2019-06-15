package com.bumptech.glide.d;

import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class a {
    private final List<a<?>> a = new ArrayList();

    private static final class a<T> {
        final com.bumptech.glide.load.a<T> a;
        private final Class<T> b;

        public a(Class<T> cls, com.bumptech.glide.load.a<T> aVar) {
            this.b = cls;
            this.a = aVar;
        }

        public boolean a(Class<?> cls) {
            return this.b.isAssignableFrom(cls);
        }
    }

    @Nullable
    public synchronized <T> com.bumptech.glide.load.a<T> a(Class<T> cls) {
        for (a aVar : this.a) {
            if (aVar.a(cls)) {
                return aVar.a;
            }
        }
        return null;
    }

    public synchronized <T> void a(Class<T> cls, com.bumptech.glide.load.a<T> aVar) {
        this.a.add(new a(cls, aVar));
    }
}
