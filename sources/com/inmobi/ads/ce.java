package com.inmobi.ads;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

abstract class ce {
    private static final String d = "ce";
    boolean a;
    @NonNull
    final Map<View, d> b;
    @Nullable
    c c;
    @NonNull
    private final ArrayList<View> e;
    private long f;
    @NonNull
    private final a g;
    @NonNull
    private final b h;
    @NonNull
    private final Handler i;
    private boolean j;

    interface a {
        boolean a(@Nullable View view, @Nullable View view2, int i, Object obj);
    }

    interface c {
        void a(List<View> list, List<View> list2);
    }

    static class b implements Runnable {
        @NonNull
        private final ArrayList<View> a = new ArrayList();
        @NonNull
        private final ArrayList<View> b = new ArrayList();
        private WeakReference<ce> c;

        b(ce ceVar) {
            this.c = new WeakReference(ceVar);
        }

        public final void run() {
            ce ceVar = (ce) this.c.get();
            if (ceVar != null) {
                ceVar.j = false;
                for (Entry entry : ceVar.b.entrySet()) {
                    View view = (View) entry.getKey();
                    int i = ((d) entry.getValue()).a;
                    if (ceVar.g.a(((d) entry.getValue()).c, view, i, ((d) entry.getValue()).d)) {
                        this.a.add(view);
                    } else {
                        this.b.add(view);
                    }
                }
            }
            if (ceVar != null) {
                c d = ceVar.c;
                if (d != null) {
                    d.a(this.a, this.b);
                }
            }
            this.a.clear();
            this.b.clear();
            if (ceVar != null) {
                ceVar.b();
            }
        }
    }

    static class d {
        int a;
        long b;
        View c;
        Object d;

        d() {
        }
    }

    public abstract int a();

    public abstract void b();

    ce(a aVar) {
        this(new WeakHashMap(10), aVar, new Handler(Looper.getMainLooper()));
    }

    private ce(@NonNull Map<View, d> map, @NonNull a aVar, @NonNull Handler handler) {
        this.f = 0;
        this.a = true;
        this.b = map;
        this.g = aVar;
        this.i = handler;
        this.h = new b(this);
        this.e = new ArrayList(50);
    }

    public void c() {
        this.h.run();
        this.i.removeCallbacksAndMessages(null);
        this.j = false;
        this.a = true;
    }

    public void d() {
        this.a = false;
        g();
    }

    /* Access modifiers changed, original: protected|final */
    public final void a(@NonNull View view) {
        if (((d) this.b.remove(view)) != null) {
            this.f--;
            if (this.b.size() == 0) {
                c();
            }
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void f() {
        this.b.clear();
        this.i.removeMessages(0);
        this.j = false;
    }

    /* Access modifiers changed, original: final */
    public final View a(@Nullable Object obj) {
        View view = null;
        if (obj == null) {
            return null;
        }
        for (Entry entry : this.b.entrySet()) {
            if (((d) entry.getValue()).d.equals(obj)) {
                view = (View) entry.getKey();
                break;
            }
        }
        if (view != null) {
            a(view);
        }
        return view;
    }

    /* Access modifiers changed, original: protected */
    public void e() {
        f();
        this.c = null;
        this.a = true;
    }

    /* Access modifiers changed, original: final */
    public final void g() {
        if (!this.j && !this.a) {
            this.j = true;
            this.i.postDelayed(this.h, (long) a());
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void a(@NonNull View view, @Nullable Object obj, int i) {
        d dVar = (d) this.b.get(view);
        if (dVar == null) {
            dVar = new d();
            this.b.put(view, dVar);
            this.f++;
        }
        dVar.a = i;
        dVar.b = this.f;
        dVar.c = view;
        dVar.d = obj;
        if (this.f % 50 == 0) {
            long j = this.f - 50;
            for (Entry entry : this.b.entrySet()) {
                if (((d) entry.getValue()).b < j) {
                    this.e.add(entry.getKey());
                }
            }
            Iterator it = this.e.iterator();
            while (it.hasNext()) {
                a((View) it.next());
            }
            this.e.clear();
        }
        if (1 == this.b.size()) {
            d();
        }
    }
}
