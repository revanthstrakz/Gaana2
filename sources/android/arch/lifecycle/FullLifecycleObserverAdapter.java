package android.arch.lifecycle;

import android.arch.lifecycle.Lifecycle.Event;

class FullLifecycleObserverAdapter implements GenericLifecycleObserver {
    private final FullLifecycleObserver a;

    FullLifecycleObserverAdapter(FullLifecycleObserver fullLifecycleObserver) {
        this.a = fullLifecycleObserver;
    }

    public void a(e eVar, Event event) {
        switch (event) {
            case ON_CREATE:
                this.a.a(eVar);
                return;
            case ON_START:
                this.a.b(eVar);
                return;
            case ON_RESUME:
                this.a.c(eVar);
                return;
            case ON_PAUSE:
                this.a.d(eVar);
                return;
            case ON_STOP:
                this.a.e(eVar);
                return;
            case ON_DESTROY:
                this.a.f(eVar);
                return;
            case ON_ANY:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
            default:
                return;
        }
    }
}
