package com.til.colombia.android.internal.a;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import com.til.colombia.android.commons.CommonUtil;
import com.til.colombia.android.commons.CommonUtil.InlineVideoVisiblity;
import com.til.colombia.android.internal.i;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

public final class j {
    static final int a = 50;
    private static final int j = 100;
    public final ArrayList<View> b;
    public long c;
    public OnScrollChangedListener d;
    public final WeakReference<View> e;
    public final Map<View, a> f;
    final b g;
    public d h;
    boolean i;
    private final c k;
    private final Handler l;

    static class a {
        public int a;
        public int b;
        public long c;
        public View d;
    }

    public static class b {
        private final Rect a = new Rect();

        static boolean a(long j, int i) {
            return SystemClock.uptimeMillis() - j >= ((long) i);
        }

        public final boolean a(View view, int i) {
            return view != null && view.getVisibility() == 0 && view.getParent() != null && b(view, i);
        }

        private boolean b(View view, int i) {
            try {
                if (!view.getGlobalVisibleRect(this.a)) {
                    return false;
                }
                long height = ((long) this.a.height()) * ((long) this.a.width());
                long height2 = ((long) view.getHeight()) * ((long) view.getWidth());
                if (height == height2) {
                    int[] iArr = new int[2];
                    view.getLocationInWindow(iArr);
                    if (iArr[1] < 0 || iArr[1] > CommonUtil.b()) {
                        return false;
                    }
                }
                if (height2 <= 0 || 100 * height < ((long) i) * height2) {
                    return false;
                }
                return true;
            } catch (Exception unused) {
                return false;
            }
        }

        public final int a(View view, View view2, int i) {
            if (view2 != null) {
                try {
                    if (view2.getVisibility() == 0 && view2.getParent() != null) {
                        if (view.getParent() != null) {
                            if (!view2.getGlobalVisibleRect(this.a)) {
                                return InlineVideoVisiblity.OUT_OF_VIEW.ordinal();
                            }
                            long height = ((long) this.a.height()) * ((long) this.a.width());
                            long height2 = ((long) view2.getHeight()) * ((long) view2.getWidth());
                            int[] iArr = new int[2];
                            view2.getLocationInWindow(iArr);
                            if (height == height2 && (iArr[1] <= 0 || iArr[1] > CommonUtil.b())) {
                                return InlineVideoVisiblity.OUT_OF_VIEW.ordinal();
                            }
                            if (iArr[1] == 0) {
                                return InlineVideoVisiblity.NONE.ordinal();
                            }
                            if (height2 <= 0 || 100 * height < 60 * height2) {
                                return InlineVideoVisiblity.OUT_OF_VIEW.ordinal();
                            }
                            return InlineVideoVisiblity.VISIBLE.ordinal();
                        }
                    }
                } catch (Exception unused) {
                    return InlineVideoVisiblity.OUT_OF_VIEW.ordinal();
                }
            }
            return InlineVideoVisiblity.OUT_OF_VIEW.ordinal();
        }
    }

    class c implements Runnable {
        private final ArrayList<View> b = new ArrayList();
        private final ArrayList<View> c = new ArrayList();

        c() {
        }

        public final void run() {
            try {
                synchronized (j.this.f) {
                    j.this.i = false;
                    for (Entry entry : j.this.f.entrySet()) {
                        View view = (View) entry.getKey();
                        int i = ((a) entry.getValue()).a;
                        int i2 = ((a) entry.getValue()).b;
                        if (j.this.g.a(view, i)) {
                            this.b.add(view);
                        } else if (!j.this.g.a(view, i2)) {
                            this.c.add(view);
                        }
                    }
                    if (j.this.h != null) {
                        j.this.h.a(this.b, this.c);
                    }
                    this.b.clear();
                    this.c.clear();
                }
            } catch (Exception e) {
                Log.e(i.f, "", e);
            }
        }
    }

    interface d {
        void a(List<View> list, List<View> list2);
    }

    public j(Context context) {
        this(context, new WeakHashMap(10), new b(), new Handler());
    }

    private j(Context context, Map<View, a> map, b bVar, Handler handler) {
        this.c = 0;
        this.f = map;
        this.g = bVar;
        this.l = handler;
        this.k = new c();
        this.b = new ArrayList(50);
        View decorView = ((Activity) context).getWindow().getDecorView();
        this.e = new WeakReference(decorView);
        ViewTreeObserver viewTreeObserver = decorView.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            this.d = new k(this);
            viewTreeObserver.addOnScrollChangedListener(this.d);
            return;
        }
        com.til.colombia.android.internal.Log.a("", "Visibility Tracker was unable to track views because the root view tree observer was not alive");
    }

    /* Access modifiers changed, original: final */
    public final void a(d dVar) {
        this.h = dVar;
    }

    private void a(View view, View view2, int i, int i2) {
        a aVar = (a) this.f.get(view2);
        if (aVar == null) {
            aVar = new a();
            this.f.put(view2, aVar);
            c();
        }
        int min = Math.min(i2, i);
        aVar.d = view;
        aVar.a = i;
        aVar.b = min;
        aVar.c = this.c;
        this.c++;
        if (this.c % 50 == 0) {
            long j = this.c - 50;
            for (Entry entry : this.f.entrySet()) {
                if (((a) entry.getValue()).c < j) {
                    this.b.add(entry.getKey());
                }
            }
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                a((View) it.next());
            }
            this.b.clear();
        }
    }

    private void a(long j) {
        for (Entry entry : this.f.entrySet()) {
            if (((a) entry.getValue()).c < j) {
                this.b.add(entry.getKey());
            }
        }
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            a((View) it.next());
        }
        this.b.clear();
    }

    public final void a(View view) {
        try {
            synchronized (this.f) {
                this.f.remove(view);
            }
        } catch (ConcurrentModificationException e) {
            Log.e(i.f, "", e);
        }
    }

    public final void a() {
        this.f.clear();
        this.l.removeMessages(0);
        this.i = false;
    }

    /* Access modifiers changed, original: final */
    public final void b() {
        a();
        View view = (View) this.e.get();
        if (!(view == null || this.d == null)) {
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnScrollChangedListener(this.d);
            }
            this.d = null;
        }
        this.h = null;
    }

    public final void c() {
        if (!this.i) {
            this.i = true;
            this.l.postDelayed(this.k, 100);
        }
    }

    /* Access modifiers changed, original: final */
    public final void a(View view, int i) {
        a aVar = (a) this.f.get(view);
        if (aVar == null) {
            aVar = new a();
            this.f.put(view, aVar);
            c();
        }
        int min = Math.min(i, i);
        aVar.d = view;
        aVar.a = i;
        aVar.b = min;
        aVar.c = this.c;
        this.c++;
        if (this.c % 50 == 0) {
            long j = this.c - 50;
            for (Entry entry : this.f.entrySet()) {
                if (((a) entry.getValue()).c < j) {
                    this.b.add(entry.getKey());
                }
            }
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                a((View) it.next());
            }
            this.b.clear();
        }
    }

    private void a(View view, View view2, int i) {
        a aVar = (a) this.f.get(view2);
        if (aVar == null) {
            aVar = new a();
            this.f.put(view2, aVar);
            c();
        }
        int min = Math.min(i, i);
        aVar.d = view;
        aVar.a = i;
        aVar.b = min;
        aVar.c = this.c;
        this.c++;
        if (this.c % 50 == 0) {
            long j = this.c - 50;
            for (Entry entry : this.f.entrySet()) {
                if (((a) entry.getValue()).c < j) {
                    this.b.add(entry.getKey());
                }
            }
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                a((View) it.next());
            }
            this.b.clear();
        }
    }
}
