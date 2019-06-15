package androidx.work.impl.utils.a;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.util.concurrent.Executor;

@RestrictTo({Scope.LIBRARY_GROUP})
public interface a {
    Executor a();

    void a(Runnable runnable);

    @NonNull
    Thread b();

    Executor c();
}
