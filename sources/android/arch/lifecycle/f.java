package android.arch.lifecycle;

import android.arch.lifecycle.Lifecycle.Event;
import android.arch.lifecycle.Lifecycle.State;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

public class f extends Lifecycle {
    private android.arch.a.b.a<d, a> a = new android.arch.a.b.a();
    private State b;
    private final WeakReference<e> c;
    private int d = 0;
    private boolean e = false;
    private boolean f = false;
    private ArrayList<State> g = new ArrayList();

    static class a {
        State a;
        GenericLifecycleObserver b;

        a(d dVar, State state) {
            this.b = i.a((Object) dVar);
            this.a = state;
        }

        /* Access modifiers changed, original: 0000 */
        public void a(e eVar, Event event) {
            State b = f.b(event);
            this.a = f.a(this.a, b);
            this.b.a(eVar, event);
            this.a = b;
        }
    }

    public f(@NonNull e eVar) {
        this.c = new WeakReference(eVar);
        this.b = State.INITIALIZED;
    }

    @MainThread
    public void a(@NonNull State state) {
        b(state);
    }

    public void a(@NonNull Event event) {
        b(b(event));
    }

    private void b(State state) {
        if (this.b != state) {
            this.b = state;
            if (this.e || this.d != 0) {
                this.f = true;
                return;
            }
            this.e = true;
            d();
            this.e = false;
        }
    }

    private boolean b() {
        boolean z = true;
        if (this.a.a() == 0) {
            return true;
        }
        State state = ((a) this.a.d().getValue()).a;
        State state2 = ((a) this.a.e().getValue()).a;
        if (!(state == state2 && this.b == state2)) {
            z = false;
        }
        return z;
    }

    private State c(d dVar) {
        Entry d = this.a.d(dVar);
        State state = null;
        State state2 = d != null ? ((a) d.getValue()).a : null;
        if (!this.g.isEmpty()) {
            state = (State) this.g.get(this.g.size() - 1);
        }
        return a(a(this.b, state2), state);
    }

    public void a(@NonNull d dVar) {
        a aVar = new a(dVar, this.b == State.DESTROYED ? State.DESTROYED : State.INITIALIZED);
        if (((a) this.a.a(dVar, aVar)) == null) {
            e eVar = (e) this.c.get();
            if (eVar != null) {
                int i = (this.d != 0 || this.e) ? 1 : 0;
                Enum c = c(dVar);
                this.d++;
                while (aVar.a.compareTo(c) < 0 && this.a.c(dVar)) {
                    c(aVar.a);
                    aVar.a(eVar, e(aVar.a));
                    c();
                    c = c(dVar);
                }
                if (i == 0) {
                    d();
                }
                this.d--;
            }
        }
    }

    private void c() {
        this.g.remove(this.g.size() - 1);
    }

    private void c(State state) {
        this.g.add(state);
    }

    public void b(@NonNull d dVar) {
        this.a.b(dVar);
    }

    @NonNull
    public State a() {
        return this.b;
    }

    static State b(Event event) {
        switch (event) {
            case ON_CREATE:
            case ON_STOP:
                return State.CREATED;
            case ON_START:
            case ON_PAUSE:
                return State.STARTED;
            case ON_RESUME:
                return State.RESUMED;
            case ON_DESTROY:
                return State.DESTROYED;
            default:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Unexpected event value ");
                stringBuilder.append(event);
                throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    private static Event d(State state) {
        switch (state) {
            case INITIALIZED:
                throw new IllegalArgumentException();
            case CREATED:
                return Event.ON_DESTROY;
            case STARTED:
                return Event.ON_STOP;
            case RESUMED:
                return Event.ON_PAUSE;
            case DESTROYED:
                throw new IllegalArgumentException();
            default:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Unexpected state value ");
                stringBuilder.append(state);
                throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    private static Event e(State state) {
        switch (state) {
            case INITIALIZED:
            case DESTROYED:
                return Event.ON_CREATE;
            case CREATED:
                return Event.ON_START;
            case STARTED:
                return Event.ON_RESUME;
            case RESUMED:
                throw new IllegalArgumentException();
            default:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Unexpected state value ");
                stringBuilder.append(state);
                throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    private void a(e eVar) {
        d c = this.a.c();
        while (c.hasNext() && !this.f) {
            Entry entry = (Entry) c.next();
            a aVar = (a) entry.getValue();
            while (aVar.a.compareTo(this.b) < 0 && !this.f && this.a.c(entry.getKey())) {
                c(aVar.a);
                aVar.a(eVar, e(aVar.a));
                c();
            }
        }
    }

    private void b(e eVar) {
        Iterator b = this.a.b();
        while (b.hasNext() && !this.f) {
            Entry entry = (Entry) b.next();
            a aVar = (a) entry.getValue();
            while (aVar.a.compareTo(this.b) > 0 && !this.f && this.a.c(entry.getKey())) {
                Event d = d(aVar.a);
                c(b(d));
                aVar.a(eVar, d);
                c();
            }
        }
    }

    private void d() {
        e eVar = (e) this.c.get();
        if (eVar == null) {
            Log.w("LifecycleRegistry", "LifecycleOwner is garbage collected, you shouldn't try dispatch new events from it.");
            return;
        }
        while (!b()) {
            this.f = false;
            if (this.b.compareTo(((a) this.a.d().getValue()).a) < 0) {
                b(eVar);
            }
            Entry e = this.a.e();
            if (!(this.f || e == null || this.b.compareTo(((a) e.getValue()).a) <= 0)) {
                a(eVar);
            }
        }
        this.f = false;
    }

    static State a(@NonNull State state, @Nullable State state2) {
        return (state2 == null || state2.compareTo(state) >= 0) ? state : state2;
    }
}
