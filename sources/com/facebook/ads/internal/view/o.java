package com.facebook.ads.internal.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.text.TextUtils;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.AudienceNetworkActivity.BackButtonInterceptor;
import com.facebook.ads.internal.adapters.a.k;
import com.facebook.ads.internal.s.a.w;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.view.f.b;
import com.facebook.ads.internal.view.g.a;
import com.facebook.ads.internal.view.g.b.c;
import com.facebook.ads.internal.view.g.b.e;
import com.facebook.ads.internal.view.g.b.m;
import com.facebook.ads.internal.view.g.b.n;
import com.facebook.ads.internal.view.g.b.z;
import com.facebook.ads.internal.view.g.c.d;
import com.facebook.ads.internal.view.g.c.f;
import com.facebook.ads.internal.view.g.c.j;
import com.facebook.ads.internal.view.g.c.l;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class o extends RelativeLayout implements a {
    static final /* synthetic */ boolean a = true;
    private static final int b = ((int) (12.0f * y.b));
    private static final int c = ((int) (18.0f * y.b));
    private static final int d = ((int) (16.0f * y.b));
    private static final int e = ((int) (72.0f * y.b));
    private static final int f = ((int) (y.b * 56.0f));
    private static final int g = ((int) (56.0f * y.b));
    private static final int h = ((int) (28.0f * y.b));
    private static final int i = ((int) (20.0f * y.b));
    private static final LayoutParams j = new LayoutParams(-1, -1);
    @Nullable
    private Context A;
    @Nullable
    private a B;
    @Nullable
    private a.a C;
    @Nullable
    private com.facebook.ads.internal.view.f.a D;
    @Nullable
    private d E;
    @Nullable
    private l F;
    @Nullable
    private View G;
    @Nullable
    private j H;
    @Nullable
    private f I;
    @Nullable
    private com.facebook.ads.internal.view.g.a.a J;
    @Nullable
    private Integer K;
    private b L;
    private boolean M = false;
    private boolean N = false;
    private WeakReference<AudienceNetworkActivity> O;
    private final BackButtonInterceptor k = new BackButtonInterceptor() {
        public boolean interceptBackButton() {
            return o.this.M ^ 1;
        }
    };
    private final c l = new c() {
        public void a(com.facebook.ads.internal.view.g.b.b bVar) {
            if (o.this.C != null) {
                o.this.L.d();
                o.this.c();
                o.this.C.a(z.REWARDED_VIDEO_COMPLETE.a(), (com.facebook.ads.internal.l.d) bVar);
            }
        }
    };
    private final e m = new e() {
        public void a(com.facebook.ads.internal.view.g.b.d dVar) {
            if (o.this.C != null) {
                o.this.C.a(z.REWARDED_VIDEO_ERROR.a());
            }
            o.this.a();
        }
    };
    private final m n = new m() {
        public void a(com.facebook.ads.internal.view.g.b.l lVar) {
            if (o.this.B != null) {
                o.this.B.a(com.facebook.ads.internal.view.g.a.a.USER_STARTED);
                o.this.r.a();
                o.this.z.set(o.this.B.k());
                o.this.f();
            }
        }
    };
    private final com.facebook.ads.internal.view.g.b.o o = new com.facebook.ads.internal.view.g.b.o() {
        public void a(n nVar) {
            if (o.this.B != null && o.this.E != null && o.this.B.getDuration() - o.this.B.getCurrentPositionInMillis() <= 3000 && o.this.E.a()) {
                o.this.E.b();
            }
        }
    };
    private final k p;
    private final com.facebook.ads.internal.o.c q;
    private final com.facebook.ads.internal.t.a r;
    private final com.facebook.ads.internal.t.a.a s;
    private final w t = new w();
    private final com.facebook.ads.internal.view.g.c.o u;
    private final com.facebook.ads.internal.view.g.b v;
    private final RelativeLayout w;
    private final f x;
    private final com.facebook.ads.internal.adapters.a.d y;
    private final AtomicBoolean z = new AtomicBoolean(false);

    public o(Context context, com.facebook.ads.internal.o.c cVar, a aVar, a.a aVar2, k kVar) {
        super(context);
        this.A = context;
        this.C = aVar2;
        this.B = aVar;
        this.q = cVar;
        this.p = kVar;
        this.y = this.p.d().a();
        this.w = new RelativeLayout(context);
        this.u = new com.facebook.ads.internal.view.g.c.o(this.A);
        this.x = new f(this.A);
        new com.facebook.ads.internal.view.c.d(this.w, i).a().a(com.facebook.ads.internal.n.a.f(this.A)).a(this.p.e().g());
        this.s = new com.facebook.ads.internal.t.a.a() {
            public void a() {
                if (!o.this.t.b()) {
                    o.this.t.a();
                    Map hashMap = new HashMap();
                    if (!TextUtils.isEmpty(o.this.p.g())) {
                        o.this.r.a(hashMap);
                        hashMap.put("touch", com.facebook.ads.internal.s.a.k.a(o.this.t.e()));
                        o.this.q.a(o.this.p.g(), hashMap);
                    }
                    if (o.this.C != null) {
                        o.this.C.a(z.REWARDED_VIDEO_IMPRESSION.a());
                    }
                }
            }
        };
        this.r = new com.facebook.ads.internal.t.a(this, 1, this.s);
        this.r.a((int) Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
        this.v = new com.facebook.ads.internal.view.g.b(this.A, this.q, this.B, this.p.g());
        this.L = new b(this.A, this.q, this.p, this.C, this.r, this.t);
        if (a || this.B != null) {
            this.B.setVideoProgressReportIntervalMs(kVar.h());
            y.a(this.B, (int) ViewCompat.MEASURED_STATE_MASK);
            this.B.getEventBus().a(this.l, this.m, this.n, this.o);
            return;
        }
        throw new AssertionError();
    }

    private void b() {
        if (this.B != null) {
            a aVar;
            com.facebook.ads.internal.view.g.a.b bVar;
            this.B.d();
            this.B.a(new com.facebook.ads.internal.view.g.c.k(this.A));
            this.B.a(this.x);
            this.B.a(this.u);
            this.F = new l(this.A, true);
            this.G = new View(this.A);
            this.G.setLayoutParams(j);
            y.a(this.G, -1509949440);
            com.facebook.ads.internal.view.g.a.b dVar = new d(this.G, d.a.FADE_OUT_ON_PLAY, true);
            this.B.addView(this.G);
            this.B.a(dVar);
            dVar = new d(this.F, d.a.FADE_OUT_ON_PLAY, true);
            this.B.a(this.F);
            this.B.a(dVar);
            this.D = new com.facebook.ads.internal.view.f.a(this.A, e, this.y, this.q, this.C, this.L.b() == b.a.INFO, this.L.b() == b.a.INFO, this.r, this.t);
            this.D.setInfo(this.p);
            this.E = new d(this.D, d.a.FADE_OUT_ON_PLAY, true);
            this.B.a(this.E);
            if (this.L.a() && this.p.e().c() > 0) {
                this.H = new j(this.A, this.p.e().c(), -12286980);
                this.H.setButtonMode(j.a.SKIP_BUTTON_MODE);
                this.H.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        if (!(o.this.H == null || !o.this.H.a() || o.this.H.getSkipSeconds() == 0 || o.this.B == null)) {
                            o.this.B.f();
                        }
                    }
                });
                aVar = this.B;
                bVar = this.H;
            } else if (!this.L.a()) {
                this.I = new f(this.A, this.C, com.facebook.ads.internal.n.a.x(this.A) ? f.a.ARROWS : f.a.CROSS);
                this.I.a(this.p.a(), this.p.g(), this.p.e().c());
                if (this.p.e().c() <= 0) {
                    this.I.b();
                }
                if (this.L.b() != b.a.INFO) {
                    this.I.c();
                }
                this.I.setToolbarListener(new f.b() {
                    public void a() {
                        if (o.this.M || o.this.B == null) {
                            if (o.this.M && o.this.C != null) {
                                o.this.C.a(z.REWARDED_VIDEO_END_ACTIVITY.a());
                            }
                            return;
                        }
                        o.this.M = true;
                        o.this.B.f();
                    }
                });
                aVar = this.B;
                bVar = this.I;
            } else {
                return;
            }
            aVar.a(bVar);
        }
    }

    private void c() {
        ViewGroup.LayoutParams layoutParams;
        this.M = true;
        e();
        y.a(this.w);
        if (this.B != null) {
            this.B.d();
            this.B.setVisibility(4);
        }
        if (this.I != null) {
            if (this.I.a()) {
                this.I.b();
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    public void run() {
                        if (o.this.I != null) {
                            o.this.I.setCloseButtonStyle(f.a.CROSS);
                            o.this.I.a(true);
                        }
                    }
                }, 1000);
            } else {
                this.I.a(true);
                this.I.setCloseButtonStyle(f.a.CROSS);
            }
            this.I.c();
        }
        y.a(this.B, this.H, this.x, this.u);
        Pair c = this.L.c();
        switch ((b.a) c.first) {
            case MARKUP:
                y.a(this.D);
                this.w.addView((View) c.second, j);
                return;
            case SCREENSHOTS:
                if (this.D != null) {
                    this.D.setVisibility(0);
                    this.D.a();
                }
                layoutParams = new LayoutParams(-1, -1);
                layoutParams.setMargins(0, g, 0, 0);
                layoutParams.addRule(2, this.D.getId());
                break;
            case INFO:
                y.a(this.D);
                layoutParams = new LayoutParams(-1, -2);
                layoutParams.addRule(15);
                layoutParams.setMargins(d, d, d, d);
                break;
            case PLAYABLE:
                d();
                this.w.removeAllViews();
                y.b(this.I);
                this.w.addView((View) c.second, j);
                ((com.facebook.ads.internal.view.e.b) c.second).c();
                return;
            default:
                return;
        }
        this.w.addView((View) c.second, layoutParams);
        this.t.a();
    }

    private void d() {
        AudienceNetworkActivity audienceNetworkActivity = (AudienceNetworkActivity) this.O.get();
        if (audienceNetworkActivity != null) {
            this.K = Integer.valueOf(audienceNetworkActivity.getRequestedOrientation());
            audienceNetworkActivity.setRequestedOrientation(1);
        }
    }

    private void e() {
        if (this.A != null) {
            View frameLayout = new FrameLayout(this.A);
            frameLayout.setLayoutParams(j);
            y.a(frameLayout, -1509949440);
            this.w.addView(frameLayout, 0);
        }
    }

    private void f() {
        this.x.setVisibility(this.z.get() ? 0 : 8);
    }

    private void setUpContentLayoutForVideo(int i) {
        LayoutParams layoutParams;
        this.w.removeAllViews();
        this.w.addView(this.B, j);
        if (this.D != null) {
            y.a(this.D);
            this.D.a(i);
            layoutParams = new LayoutParams(-1, -2);
            layoutParams.addRule(12);
            this.D.setPadding(d, d, d, d);
            this.w.addView(this.D, layoutParams);
        }
        if (this.H != null) {
            layoutParams = new LayoutParams(f, f);
            layoutParams.addRule(10);
            layoutParams.addRule(11);
            this.H.setPadding(d, d, d, d);
            this.w.addView(this.H, layoutParams);
        }
        layoutParams = new LayoutParams(h, h);
        layoutParams.addRule(10);
        layoutParams.addRule(11);
        layoutParams.setMargins(b, b + g, b, c);
        this.w.addView(this.x, layoutParams);
        f();
        layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(12);
        this.w.addView(this.u, layoutParams);
    }

    public void a() {
        if (this.B != null) {
            this.B.g();
            this.B.l();
        }
        if (this.r != null) {
            this.r.c();
        }
    }

    public void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        if (this.B != null && this.C != null) {
            this.O = new WeakReference(audienceNetworkActivity);
            b();
            audienceNetworkActivity.addBackButtonInterceptor(this.k);
            this.B.setVideoURI(!TextUtils.isEmpty(this.p.e().b()) ? this.p.e().b() : this.p.e().a());
            setUpContentLayoutForVideo(audienceNetworkActivity.getResources().getConfiguration().orientation);
            addView(this.w, j);
            if (this.I != null) {
                y.a(this.I);
                this.I.a(this.y, true);
                if (com.facebook.ads.internal.c.a.b(getContext())) {
                    this.I.a(this.p.a(), this.p.g());
                }
                addView(this.I, new LayoutParams(-1, g));
            }
            setLayoutParams(j);
            this.C.a((View) this);
        }
    }

    public void a(Bundle bundle) {
    }

    public void a(boolean z) {
        if (this.B != null && !this.B.m()) {
            this.J = this.B.getVideoStartReason();
            this.N = z;
            this.B.a(false);
        }
    }

    public void b(boolean z) {
        if (!(this.B == null || this.B.n() || this.B.getState() == com.facebook.ads.internal.view.g.d.d.PLAYBACK_COMPLETED || this.J == null || (this.N && !z))) {
            this.B.a(this.J);
        }
    }

    public int getCurrentPosition() {
        return this.B != null ? this.B.getCurrentPositionInMillis() : 0;
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (this.D != null) {
            this.D.a(configuration.orientation);
        }
    }

    public void onDestroy() {
        a();
        if (this.B != null) {
            this.B.getEventBus().b(this.l, this.m, this.n, this.o);
        }
        if (!TextUtils.isEmpty(this.p.g())) {
            Map hashMap = new HashMap();
            this.r.a(hashMap);
            hashMap.put("touch", com.facebook.ads.internal.s.a.k.a(this.t.e()));
            this.q.i(this.p.g(), hashMap);
        }
        if (this.I != null) {
            this.I.setToolbarListener(null);
        }
        if (!(this.K == null || this.O.get() == null)) {
            ((AudienceNetworkActivity) this.O.get()).setRequestedOrientation(this.K.intValue());
        }
        this.v.a();
        this.B = null;
        this.L.e();
        this.H = null;
        this.D = null;
        this.E = null;
        this.C = null;
        this.A = null;
        this.u.a();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.t.a(motionEvent, this, this);
        return super.onInterceptTouchEvent(motionEvent);
    }

    /* Access modifiers changed, original: 0000 */
    @VisibleForTesting
    public void setEndCardController(b bVar) {
        this.L = bVar;
    }

    public void setListener(a.a aVar) {
    }
}
