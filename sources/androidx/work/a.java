package androidx.work;

import android.os.Build.VERSION;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public final class a {
    @NonNull
    private final Executor a;
    @NonNull
    private final m b;
    private final int c;
    private final int d;
    private final int e;
    private final int f;

    public static final class a {
        Executor a;
        m b;
        int c = 4;
        int d = 0;
        int e = Integer.MAX_VALUE;
        int f = 20;

        @NonNull
        public a a() {
            return new a(this);
        }
    }

    a(@NonNull a aVar) {
        if (aVar.a == null) {
            this.a = g();
        } else {
            this.a = aVar.a;
        }
        if (aVar.b == null) {
            this.b = m.a();
        } else {
            this.b = aVar.b;
        }
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
    }

    @NonNull
    public Executor a() {
        return this.a;
    }

    @NonNull
    public m b() {
        return this.b;
    }

    @RestrictTo({Scope.LIBRARY})
    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public int e() {
        return this.e;
    }

    @IntRange(from = 20, to = 50)
    @RestrictTo({Scope.LIBRARY_GROUP})
    public int f() {
        if (VERSION.SDK_INT == 23) {
            return this.f / 2;
        }
        return this.f;
    }

    @NonNull
    private Executor g() {
        return Executors.newFixedThreadPool(Math.max(2, Math.min(Runtime.getRuntime().availableProcessors() - 1, 4)));
    }
}
