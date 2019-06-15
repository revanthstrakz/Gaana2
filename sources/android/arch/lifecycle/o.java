package android.arch.lifecycle;

import android.arch.lifecycle.Lifecycle.Event;
import android.os.Handler;
import android.support.annotation.NonNull;

public class o {
    private final f a;
    private final Handler b = new Handler();
    private a c;

    static class a implements Runnable {
        final Event a;
        private final f b;
        private boolean c = false;

        a(@NonNull f fVar, Event event) {
            this.b = fVar;
            this.a = event;
        }

        public void run() {
            if (!this.c) {
                this.b.a(this.a);
                this.c = true;
            }
        }
    }

    public o(@NonNull e eVar) {
        this.a = new f(eVar);
    }

    private void a(Event event) {
        if (this.c != null) {
            this.c.run();
        }
        this.c = new a(this.a, event);
        this.b.postAtFrontOfQueue(this.c);
    }

    public void a() {
        a(Event.ON_CREATE);
    }

    public void b() {
        a(Event.ON_START);
    }

    public void c() {
        a(Event.ON_START);
    }

    public void d() {
        a(Event.ON_STOP);
        a(Event.ON_DESTROY);
    }

    public Lifecycle e() {
        return this.a;
    }
}
