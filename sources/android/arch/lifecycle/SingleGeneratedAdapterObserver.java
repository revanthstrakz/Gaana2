package android.arch.lifecycle;

import android.arch.lifecycle.Lifecycle.Event;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

@RestrictTo({Scope.LIBRARY_GROUP})
public class SingleGeneratedAdapterObserver implements GenericLifecycleObserver {
    private final c a;

    SingleGeneratedAdapterObserver(c cVar) {
        this.a = cVar;
    }

    public void a(e eVar, Event event) {
        this.a.a(eVar, event, false, null);
        this.a.a(eVar, event, true, null);
    }
}
