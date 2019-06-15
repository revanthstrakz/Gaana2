package com.bumptech.glide.load.b;

import android.support.annotation.NonNull;
import com.bumptech.glide.Priority;
import com.bumptech.glide.e.c;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.f;

public class v<Model> implements n<Model, Model> {
    private static final v<?> a = new v();

    public static class a<Model> implements o<Model, Model> {
        private static final a<?> a = new a();

        public static <T> a<T> a() {
            return a;
        }

        public n<Model, Model> a(r rVar) {
            return v.a();
        }
    }

    private static class b<Model> implements com.bumptech.glide.load.a.b<Model> {
        private final Model a;

        public void a() {
        }

        public void b() {
        }

        public b(Model model) {
            this.a = model;
        }

        public void a(Priority priority, com.bumptech.glide.load.a.b.a<? super Model> aVar) {
            aVar.a(this.a);
        }

        @NonNull
        public Class<Model> d() {
            return this.a.getClass();
        }

        @NonNull
        public DataSource c() {
            return DataSource.LOCAL;
        }
    }

    public boolean a(Model model) {
        return true;
    }

    public static <T> v<T> a() {
        return a;
    }

    public com.bumptech.glide.load.b.n.a<Model> a(Model model, int i, int i2, f fVar) {
        return new com.bumptech.glide.load.b.n.a(new c(model), new b(model));
    }
}
