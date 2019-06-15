package com.bumptech.glide.manager;

import com.bumptech.glide.f.i;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

class a implements h {
    private final Set<i> a = Collections.newSetFromMap(new WeakHashMap());
    private boolean b;
    private boolean c;

    a() {
    }

    public void a(i iVar) {
        this.a.add(iVar);
        if (this.c) {
            iVar.onDestroy();
        } else if (this.b) {
            iVar.onStart();
        } else {
            iVar.onStop();
        }
    }

    public void b(i iVar) {
        this.a.remove(iVar);
    }

    /* Access modifiers changed, original: 0000 */
    public void a() {
        this.b = true;
        for (i onStart : i.a(this.a)) {
            onStart.onStart();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void b() {
        this.b = false;
        for (i onStop : i.a(this.a)) {
            onStop.onStop();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void c() {
        this.c = true;
        for (i onDestroy : i.a(this.a)) {
            onDestroy.onDestroy();
        }
    }
}
