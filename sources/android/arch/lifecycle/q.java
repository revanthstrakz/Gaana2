package android.arch.lifecycle;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

public class q {
    private final a a;
    private final r b;

    public interface a {
        @NonNull
        <T extends p> T create(@NonNull Class<T> cls);
    }

    public q(@NonNull r rVar, @NonNull a aVar) {
        this.a = aVar;
        this.b = rVar;
    }

    @NonNull
    public <T extends p> T a(@NonNull Class<T> cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName == null) {
            throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("android.arch.lifecycle.ViewModelProvider.DefaultKey:");
        stringBuilder.append(canonicalName);
        return a(stringBuilder.toString(), cls);
    }

    @MainThread
    @NonNull
    public <T extends p> T a(@NonNull String str, @NonNull Class<T> cls) {
        p a = this.b.a(str);
        if (cls.isInstance(a)) {
            return a;
        }
        p create = this.a.create(cls);
        this.b.a(str, create);
        return create;
    }
}
