package com.bumptech.glide.load.b;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pools.Pool;
import com.bumptech.glide.Priority;
import com.bumptech.glide.f.h;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.a.b;
import com.bumptech.glide.load.c;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.f;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

class q<Model, Data> implements n<Model, Data> {
    private final List<n<Model, Data>> a;
    private final Pool<List<Throwable>> b;

    static class a<Data> implements b<Data>, com.bumptech.glide.load.a.b.a<Data> {
        private final List<b<Data>> a;
        private final Pool<List<Throwable>> b;
        private int c = 0;
        private Priority d;
        private com.bumptech.glide.load.a.b.a<? super Data> e;
        @Nullable
        private List<Throwable> f;

        a(List<b<Data>> list, Pool<List<Throwable>> pool) {
            this.b = pool;
            h.a((Collection) list);
            this.a = list;
        }

        public void a(Priority priority, com.bumptech.glide.load.a.b.a<? super Data> aVar) {
            this.d = priority;
            this.e = aVar;
            this.f = (List) this.b.acquire();
            ((b) this.a.get(this.c)).a(priority, this);
        }

        public void a() {
            if (this.f != null) {
                this.b.release(this.f);
            }
            this.f = null;
            for (b a : this.a) {
                a.a();
            }
        }

        public void b() {
            for (b b : this.a) {
                b.b();
            }
        }

        @NonNull
        public Class<Data> d() {
            return ((b) this.a.get(0)).d();
        }

        @NonNull
        public DataSource c() {
            return ((b) this.a.get(0)).c();
        }

        public void a(Data data) {
            if (data != null) {
                this.e.a((Object) data);
            } else {
                e();
            }
        }

        public void a(Exception exception) {
            this.f.add(exception);
            e();
        }

        private void e() {
            if (this.c < this.a.size() - 1) {
                this.c++;
                a(this.d, this.e);
                return;
            }
            this.e.a(new GlideException("Fetch failed", new ArrayList(this.f)));
        }
    }

    q(List<n<Model, Data>> list, Pool<List<Throwable>> pool) {
        this.a = list;
        this.b = pool;
    }

    public com.bumptech.glide.load.b.n.a<Data> a(Model model, int i, int i2, f fVar) {
        int size = this.a.size();
        ArrayList arrayList = new ArrayList(size);
        c cVar = null;
        for (int i3 = 0; i3 < size; i3++) {
            n nVar = (n) this.a.get(i3);
            if (nVar.a(model)) {
                com.bumptech.glide.load.b.n.a a = nVar.a(model, i, i2, fVar);
                if (a != null) {
                    cVar = a.a;
                    arrayList.add(a.c);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new com.bumptech.glide.load.b.n.a(cVar, new a(arrayList, this.b));
    }

    public boolean a(Model model) {
        for (n a : this.a) {
            if (a.a(model)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MultiModelLoader{modelLoaders=");
        stringBuilder.append(Arrays.toString(this.a.toArray(new n[this.a.size()])));
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
