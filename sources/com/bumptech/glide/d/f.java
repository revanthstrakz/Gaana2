package com.bumptech.glide.d;

import android.support.annotation.Nullable;
import com.bumptech.glide.load.h;
import java.util.ArrayList;
import java.util.List;

public class f {
    final List<a<?>> a = new ArrayList();

    private static final class a<T> {
        final h<T> a;
        private final Class<T> b;

        a(Class<T> cls, h<T> hVar) {
            this.b = cls;
            this.a = hVar;
        }

        /* Access modifiers changed, original: 0000 */
        public boolean a(Class<?> cls) {
            return this.b.isAssignableFrom(cls);
        }
    }

    public synchronized <Z> void a(Class<Z> cls, h<Z> hVar) {
        this.a.add(new a(cls, hVar));
    }

    @Nullable
    public synchronized <Z> h<Z> a(Class<Z> cls) {
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            a aVar = (a) this.a.get(i);
            if (aVar.a(cls)) {
                return aVar.a;
            }
        }
        return null;
    }
}
