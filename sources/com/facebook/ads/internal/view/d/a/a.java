package com.facebook.ads.internal.view.d.a;

import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.SmoothScroller;
import android.view.View;
import com.facebook.ads.internal.view.component.a.a.b;
import com.facebook.ads.internal.view.component.a.a.b.c;
import com.facebook.ads.internal.view.component.a.a.b.d;
import com.facebook.ads.internal.view.component.a.a.b.e;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class a extends OnScrollListener {
    private final LinearLayoutManager a;
    private final int b;
    private final SmoothScroller c;
    private final Set<Integer> d = new HashSet();
    private List<b> e;
    private final com.facebook.ads.internal.t.a f;
    private boolean g = true;
    @Nullable
    private com.facebook.ads.internal.view.d.a.d.a h;
    private boolean i = true;
    private boolean j = true;
    private boolean k;
    private int l = -1;
    private final e m = new e() {
        private float b = 0.0f;

        public float a() {
            return this.b;
        }

        public void a(float f) {
            this.b = f;
        }
    };
    private final c n = new c() {
        public void a(int i) {
            a.this.a(i, true);
            if (a.this.h()) {
                a.this.f();
            } else {
                a.this.a(i);
            }
        }
    };
    private final d o = new d() {
        public void a(View view) {
            b bVar = (b) view;
            bVar.j();
            if (a.this.k) {
                a.this.j = true;
            }
            if (a.this.f.b() && ((Integer) bVar.getTag(-1593835536)).intValue() == 0) {
                a.this.f.a();
            }
        }

        public void b(View view) {
            if (a.this.k) {
                a.this.j = false;
            }
        }
    };

    a(c cVar, int i, List<b> list, com.facebook.ads.internal.t.a aVar) {
        this.a = cVar.getLayoutManager();
        this.b = i;
        this.e = list;
        this.f = aVar;
        this.c = new LinearSmoothScroller(cVar.getContext());
        cVar.addOnScrollListener(this);
    }

    @Nullable
    private b a(int i, int i2) {
        return a(i, i2, true);
    }

    @Nullable
    private b a(int i, int i2, boolean z) {
        b bVar = null;
        while (i <= i2) {
            b bVar2 = (b) this.a.findViewByPosition(i);
            if (bVar2.g()) {
                return null;
            }
            boolean a = a((View) bVar2);
            if (bVar == null && bVar2.f() && a && !this.d.contains(Integer.valueOf(i)) && (!z || b(bVar2))) {
                bVar = bVar2;
            }
            if (bVar2.f() && !a) {
                a(i, false);
            }
            i++;
        }
        return bVar;
    }

    private void a(int i) {
        b a = a(i + 1, this.a.findLastVisibleItemPosition(), false);
        if (a != null) {
            a.h();
            b(((Integer) a.getTag(-1593835536)).intValue());
        }
    }

    private void a(int i, int i2, int i3) {
        if (h() && this.h != null) {
            int findFirstCompletelyVisibleItemPosition = this.a.findFirstCompletelyVisibleItemPosition();
            if (findFirstCompletelyVisibleItemPosition != -1) {
                i = findFirstCompletelyVisibleItemPosition;
            } else if (i3 >= 0) {
                i = i2;
            }
            this.h.a(i);
        }
    }

    private void a(int i, boolean z) {
        if (z) {
            this.d.add(Integer.valueOf(i));
        } else {
            this.d.remove(Integer.valueOf(i));
        }
    }

    private static void a(View view, boolean z) {
        view.setAlpha(z ? 1.0f : 0.5f);
    }

    private void a(b bVar, boolean z) {
        if (h()) {
            a((View) bVar, z);
        }
        if (!z && bVar.g()) {
            bVar.i();
        }
    }

    private static boolean a(View view) {
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        return ((float) rect.width()) / ((float) view.getWidth()) >= 0.15f;
    }

    private boolean a(b bVar) {
        if (!this.g || !bVar.f()) {
            return false;
        }
        this.g = false;
        return true;
    }

    private void b(int i) {
        this.c.setTargetPosition(i);
        this.a.startSmoothScroll(this.c);
    }

    private void b(int i, int i2) {
        while (i <= i2) {
            c(i);
            i++;
        }
    }

    private static boolean b(b bVar) {
        return ((int) (bVar.getX() + ((float) bVar.getWidth()))) <= ((int) (((float) bVar.getWidth()) * 1.3f));
    }

    private void c(int i) {
        b bVar = (b) this.a.findViewByPosition(i);
        if (a((View) bVar)) {
            a(bVar, true);
        }
        if (a(bVar)) {
            this.m.a(((b) this.e.get(((Integer) bVar.getTag(-1593835536)).intValue())).c().c().f() ? 1.0f : 0.0f);
        }
    }

    private void c(int i, int i2) {
        d(i);
        d(i2);
    }

    private void d(int i) {
        b bVar = (b) this.a.findViewByPosition(i);
        if (!a((View) bVar)) {
            a(bVar, false);
        }
    }

    private void f() {
        int findFirstCompletelyVisibleItemPosition = this.a.findFirstCompletelyVisibleItemPosition();
        if (findFirstCompletelyVisibleItemPosition != -1 && findFirstCompletelyVisibleItemPosition < this.e.size() - 1) {
            b(findFirstCompletelyVisibleItemPosition + 1);
        }
    }

    private void g() {
        if (this.j) {
            b a = a(this.a.findFirstVisibleItemPosition(), this.a.findLastVisibleItemPosition());
            if (a != null) {
                a.h();
            }
        }
    }

    private boolean h() {
        return this.b == 1;
    }

    public void a() {
        this.l = -1;
        int findLastVisibleItemPosition = this.a.findLastVisibleItemPosition();
        for (int findFirstVisibleItemPosition = this.a.findFirstVisibleItemPosition(); findFirstVisibleItemPosition <= findLastVisibleItemPosition; findFirstVisibleItemPosition++) {
            b bVar = (b) this.a.findViewByPosition(findFirstVisibleItemPosition);
            if (bVar.g()) {
                this.l = findFirstVisibleItemPosition;
                bVar.i();
                return;
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(com.facebook.ads.internal.view.d.a.d.a aVar) {
        this.h = aVar;
    }

    public void b() {
        b bVar = (b) this.a.findViewByPosition(this.l);
        if (this.l >= 0) {
            bVar.h();
        }
    }

    public e c() {
        return this.m;
    }

    public c d() {
        return this.n;
    }

    public d e() {
        return this.o;
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        super.onScrollStateChanged(recyclerView, i);
        if (i == 0) {
            this.k = true;
            g();
        }
    }

    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        super.onScrolled(recyclerView, i, i2);
        this.k = false;
        if (this.i) {
            this.k = true;
            g();
            this.i = false;
        }
        int findFirstVisibleItemPosition = this.a.findFirstVisibleItemPosition();
        i2 = this.a.findLastVisibleItemPosition();
        c(findFirstVisibleItemPosition, i2);
        b(findFirstVisibleItemPosition, i2);
        a(findFirstVisibleItemPosition, i2, i);
    }
}
