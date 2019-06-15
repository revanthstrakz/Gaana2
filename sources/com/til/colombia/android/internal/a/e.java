package com.til.colombia.android.internal.a;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import com.til.colombia.android.internal.a.j.b;
import com.til.colombia.android.internal.i;
import com.til.colombia.android.service.Item;
import com.til.colombia.android.service.bi;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

public final class e {
    private static final int f = 250;
    public final j a;
    public final Map<View, Item> b;
    public final Map<View, i<Item>> c;
    public final Handler d;
    public d e;
    private final a g;
    private final b h;

    class a implements Runnable {
        private final ArrayList<View> b = new ArrayList();

        a() {
        }

        public final void run() {
            try {
                synchronized (e.this.c) {
                    for (Entry entry : e.this.c.entrySet()) {
                        View view = (View) entry.getKey();
                        i iVar = (i) entry.getValue();
                        if ((SystemClock.uptimeMillis() - iVar.b >= ((long) com.til.colombia.android.internal.a.A()) ? 1 : null) != null) {
                            bi.a().b((Item) iVar.a);
                            this.b.add(view);
                        }
                    }
                    Iterator it = this.b.iterator();
                    while (it.hasNext()) {
                        e.this.a((View) it.next());
                    }
                    this.b.clear();
                    if (!e.this.c.isEmpty()) {
                        e.this.b();
                    }
                }
            } catch (Exception e) {
                Log.e(i.f, "", e);
            }
        }
    }

    public e(Context context) {
        this(new WeakHashMap(), new WeakHashMap(), new b(), new j(context), new Handler());
    }

    private e(Map<View, Item> map, Map<View, i<Item>> map2, b bVar, j jVar, Handler handler) {
        this.b = map;
        this.c = map2;
        this.h = bVar;
        this.a = jVar;
        this.e = new f(this);
        this.a.h = this.e;
        this.d = handler;
        this.g = new a();
    }

    public final void a(View view, Item item) {
        if (this.b.get(view) != item) {
            a(view);
            if (!item.isImpressed()) {
                this.b.put(view, item);
                j jVar = this.a;
                int z = com.til.colombia.android.internal.a.z();
                a aVar = (a) jVar.f.get(view);
                if (aVar == null) {
                    aVar = new a();
                    jVar.f.put(view, aVar);
                    jVar.c();
                }
                int min = Math.min(z, z);
                aVar.d = view;
                aVar.a = z;
                aVar.b = min;
                aVar.c = jVar.c;
                jVar.c++;
                if (jVar.c % 50 == 0) {
                    long j = jVar.c - 50;
                    for (Entry entry : jVar.f.entrySet()) {
                        if (((a) entry.getValue()).c < j) {
                            jVar.b.add(entry.getKey());
                        }
                    }
                    Iterator it = jVar.b.iterator();
                    while (it.hasNext()) {
                        jVar.a((View) it.next());
                    }
                    jVar.b.clear();
                }
            }
        }
    }

    public final void a(View view) {
        try {
            this.b.remove(view);
            try {
                synchronized (this.c) {
                    this.c.remove(view);
                }
            } catch (ConcurrentModificationException e) {
                com.til.colombia.android.internal.Log.a(i.f, "", e);
            }
            this.a.a(view);
        } catch (Exception e2) {
            Log.e(i.f, "", e2);
        }
    }

    private void c() {
        this.b.clear();
        this.c.clear();
        this.a.a();
        this.d.removeMessages(0);
    }

    /* Access modifiers changed, original: final */
    public final void b() {
        if (!this.d.hasMessages(0)) {
            this.d.postDelayed(this.g, 250);
        }
    }

    private void b(View view) {
        try {
            synchronized (this.c) {
                this.c.remove(view);
            }
        } catch (ConcurrentModificationException e) {
            com.til.colombia.android.internal.Log.a(i.f, "", e);
        }
    }

    public final void a() {
        this.b.clear();
        this.c.clear();
        this.a.a();
        this.d.removeMessages(0);
        j jVar = this.a;
        jVar.a();
        View view = (View) jVar.e.get();
        if (!(view == null || jVar.d == null)) {
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnScrollChangedListener(jVar.d);
            }
            jVar.d = null;
        }
        jVar.h = null;
        this.e = null;
    }
}
