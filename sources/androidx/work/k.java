package androidx.work;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.impl.h;
import java.util.Collections;
import java.util.List;

public abstract class k {
    @NonNull
    public abstract h a(@NonNull String str, @NonNull ExistingWorkPolicy existingWorkPolicy, @NonNull List<g> list);

    @NonNull
    public static k a() {
        h b = h.b();
        if (b != null) {
            return b;
        }
        throw new IllegalStateException("WorkManager is not initialized properly.  The most likely cause is that you disabled WorkManagerInitializer in your manifest but forgot to call WorkManager#initialize in your Application#onCreate or a ContentProvider.");
    }

    public static void a(@NonNull Context context, @NonNull a aVar) {
        h.b(context, aVar);
    }

    @NonNull
    public h a(@NonNull String str, @NonNull ExistingWorkPolicy existingWorkPolicy, @NonNull g gVar) {
        return a(str, existingWorkPolicy, Collections.singletonList(gVar));
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    protected k() {
    }
}
