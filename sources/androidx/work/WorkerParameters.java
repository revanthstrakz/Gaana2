package androidx.work;

import android.net.Network;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;

public final class WorkerParameters {
    @NonNull
    private UUID a;
    @NonNull
    private d b;
    @NonNull
    private Set<String> c;
    @NonNull
    private a d;
    private int e;
    @NonNull
    private Executor f;
    @NonNull
    private androidx.work.impl.utils.a.a g;
    @NonNull
    private m h;

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static class a {
        @NonNull
        public List<String> a = Collections.emptyList();
        @NonNull
        public List<Uri> b = Collections.emptyList();
        @RequiresApi(28)
        public Network c;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public WorkerParameters(@NonNull UUID uuid, @NonNull d dVar, @NonNull Collection<String> collection, @NonNull a aVar, int i, @NonNull Executor executor, @NonNull androidx.work.impl.utils.a.a aVar2, @NonNull m mVar) {
        this.a = uuid;
        this.b = dVar;
        this.c = new HashSet(collection);
        this.d = aVar;
        this.e = i;
        this.f = executor;
        this.g = aVar2;
        this.h = mVar;
    }

    @NonNull
    public UUID a() {
        return this.a;
    }

    @NonNull
    public d b() {
        return this.b;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @NonNull
    public Executor c() {
        return this.f;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @NonNull
    public m d() {
        return this.h;
    }
}
