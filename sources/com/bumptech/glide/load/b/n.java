package com.bumptech.glide.load.b;

import android.support.annotation.Nullable;
import com.bumptech.glide.f.h;
import com.bumptech.glide.load.a.b;
import com.bumptech.glide.load.c;
import com.bumptech.glide.load.f;
import java.util.Collections;
import java.util.List;

public interface n<Model, Data> {

    public static class a<Data> {
        public final c a;
        public final List<c> b;
        public final b<Data> c;

        public a(c cVar, b<Data> bVar) {
            this(cVar, Collections.emptyList(), bVar);
        }

        public a(c cVar, List<c> list, b<Data> bVar) {
            this.a = (c) h.a((Object) cVar);
            this.b = (List) h.a((Object) list);
            this.c = (b) h.a((Object) bVar);
        }
    }

    @Nullable
    a<Data> a(Model model, int i, int i2, f fVar);

    boolean a(Model model);
}
