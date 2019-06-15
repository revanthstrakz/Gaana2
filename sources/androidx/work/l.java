package androidx.work;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.impl.b.j;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public abstract class l {
    @NonNull
    private UUID a;
    @NonNull
    private j b;
    @NonNull
    private Set<String> c;

    public static abstract class a<B extends a, W extends l> {
        boolean a = false;
        UUID b = UUID.randomUUID();
        j c;
        Set<String> d = new HashSet();

        @NonNull
        public abstract B c();

        @NonNull
        public abstract W d();

        a(@NonNull Class<? extends ListenableWorker> cls) {
            this.c = new j(this.b.toString(), cls.getName());
            a(cls.getName());
        }

        @NonNull
        public final B a(@NonNull b bVar) {
            this.c.j = bVar;
            return c();
        }

        @NonNull
        public final B a(@NonNull String str) {
            this.d.add(str);
            return c();
        }

        @NonNull
        public final W e() {
            l d = d();
            this.b = UUID.randomUUID();
            this.c = new j(this.c);
            this.c.a = this.b.toString();
            return d;
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    protected l(@NonNull UUID uuid, @NonNull j jVar, @NonNull Set<String> set) {
        this.a = uuid;
        this.b = jVar;
        this.c = set;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @NonNull
    public String a() {
        return this.a.toString();
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @NonNull
    public j b() {
        return this.b;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @NonNull
    public Set<String> c() {
        return this.c;
    }
}
