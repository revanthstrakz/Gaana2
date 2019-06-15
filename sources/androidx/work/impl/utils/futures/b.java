package androidx.work.impl.utils.futures;

import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import com.google.common.util.concurrent.ListenableFuture;

@RestrictTo({Scope.LIBRARY_GROUP})
public final class b<V> extends a<V> {
    public static <V> b<V> d() {
        return new b();
    }

    public boolean a(@Nullable V v) {
        return super.a((Object) v);
    }

    public boolean a(Throwable th) {
        return super.a(th);
    }

    public boolean a(ListenableFuture<? extends V> listenableFuture) {
        return super.a((ListenableFuture) listenableFuture);
    }

    private b() {
    }
}
