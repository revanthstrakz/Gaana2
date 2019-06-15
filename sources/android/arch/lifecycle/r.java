package android.arch.lifecycle;

import java.util.HashMap;

public class r {
    private final HashMap<String, p> a = new HashMap();

    /* Access modifiers changed, original: final */
    public final void a(String str, p pVar) {
        p pVar2 = (p) this.a.get(str);
        if (pVar2 != null) {
            pVar2.onCleared();
        }
        this.a.put(str, pVar);
    }

    /* Access modifiers changed, original: final */
    public final p a(String str) {
        return (p) this.a.get(str);
    }

    public final void a() {
        for (p onCleared : this.a.values()) {
            onCleared.onCleared();
        }
        this.a.clear();
    }
}
