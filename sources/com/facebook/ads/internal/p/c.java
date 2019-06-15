package com.facebook.ads.internal.p;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.view.c.e;
import com.facebook.ads.internal.view.g.a.b;
import com.facebook.ads.internal.view.g.c.g;
import com.facebook.ads.internal.view.g.c.h;
import com.facebook.ads.internal.view.g.d;
import com.facebook.ads.internal.view.j;
import java.util.concurrent.atomic.AtomicBoolean;

public class c {
    private static final String a = "c";
    private final g b;
    private final com.facebook.ads.internal.t.a c;
    private final com.facebook.ads.internal.t.a.a d;
    private final View e;
    private final com.facebook.ads.internal.view.g.d.a f = new com.facebook.ads.internal.view.g.d.a() {
        public void a() {
            c.this.n.set(true);
            if (c.this.h != null) {
                c.this.h.a(c.this.m.get());
            }
        }
    };
    @Nullable
    private j g;
    @Nullable
    private a h;
    private Context i;
    private boolean j;
    private boolean k;
    private boolean l;
    private final AtomicBoolean m = new AtomicBoolean(false);
    private final AtomicBoolean n = new AtomicBoolean(false);
    private l o = l.DEFAULT;

    public interface a {
        void a(boolean z);
    }

    public c(Context context, View view) {
        this.i = context;
        this.e = view;
        this.b = new g(context);
        this.d = k();
        this.c = j();
        g();
    }

    private void a(com.facebook.ads.internal.view.g.a.a aVar) {
        if (this.g != null) {
            this.g.a(aVar);
            return;
        }
        if (AdInternalSettings.isDebugBuild()) {
            Log.e(a, "MediaViewVideo is null; unable to find it.");
        }
    }

    private void g() {
        float f = y.b;
        int i = (int) (2.0f * f);
        int i2 = (int) (25.0f * f);
        h hVar = new h(this.i);
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(9);
        layoutParams.addRule(12);
        hVar.setPadding(i, i2, i2, i);
        hVar.setLayoutParams(layoutParams);
        for (i = 0; i < ((ViewGroup) this.e).getChildCount(); i++) {
            View childAt = ((ViewGroup) this.e).getChildAt(0);
            if (childAt instanceof j) {
                this.g = (j) childAt;
                break;
            }
        }
        if (this.g != null) {
            this.g.a((b) this.b);
            this.g.a((b) hVar);
        } else if (AdInternalSettings.isDebugBuild()) {
            Log.e(a, "Unable to find MediaViewVideo child.");
        }
        this.c.a(0);
        this.c.b((int) Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
    }

    private void h() {
        if (this.g != null) {
            ((d) this.g.getVideoView()).setViewImplInflationListener(this.f);
        }
    }

    private void i() {
        if (this.g != null) {
            ((d) this.g.getVideoView()).setViewImplInflationListener(null);
        }
    }

    private com.facebook.ads.internal.t.a j() {
        return new com.facebook.ads.internal.t.a(this.e, 50, true, this.d);
    }

    private com.facebook.ads.internal.t.a.a k() {
        return new com.facebook.ads.internal.t.a.a() {
            public void a() {
                if (c.this.g != null) {
                    if (!c.this.l && (c.this.k || c.this.m())) {
                        c.this.a(com.facebook.ads.internal.view.g.a.a.AUTO_STARTED);
                    }
                    c.this.k = false;
                    c.this.l = false;
                }
            }

            public void b() {
                if (c.this.g != null) {
                    c.this.g.e();
                }
            }
        };
    }

    private void l() {
        if (this.e.getVisibility() == 0 && this.j && this.e.hasWindowFocus()) {
            this.c.a();
            return;
        }
        if (this.g != null && this.g.getState() == com.facebook.ads.internal.view.g.d.d.PAUSED) {
            this.l = true;
        }
        this.c.c();
    }

    private boolean m() {
        boolean z = false;
        if (this.g != null) {
            if (this.g.getState() == com.facebook.ads.internal.view.g.d.d.PLAYBACK_COMPLETED) {
                return false;
            }
            if (this.o == l.ON) {
                z = true;
            }
        }
        return z;
    }

    public void a() {
        this.o = l.DEFAULT;
        i();
    }

    public void a(e eVar) {
        a(eVar, null);
    }

    public void a(e eVar, @Nullable a aVar) {
        this.k = false;
        this.l = false;
        this.h = aVar;
        h();
        g gVar = this.b;
        String a = (eVar == null || eVar.j() == null) ? null : eVar.j().a();
        gVar.a(a, new e() {
            public void a(boolean z) {
                c.this.m.set(z);
                if (c.this.n.get() && c.this.h != null) {
                    c.this.h.a(z);
                }
            }
        });
        this.o = eVar.D();
        this.c.a();
    }

    public void b() {
        if (this.g != null) {
            this.g.getVideoView().setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (c.this.g != null && motionEvent.getAction() == 1) {
                        c.this.g.a();
                    }
                    return true;
                }
            });
        }
    }

    public void c() {
        this.j = true;
        l();
    }

    public void d() {
        this.j = false;
        l();
    }

    public void e() {
        l();
    }

    public void f() {
        l();
    }
}
