package com.facebook.ads.internal.view.e;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceError;
import android.webkit.WebSettings;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.ads.internal.adapters.a.j;
import com.facebook.ads.internal.adapters.a.k;
import com.facebook.ads.internal.s.a.w;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.view.component.i;
import com.facebook.ads.internal.view.f;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class b extends RelativeLayout implements com.facebook.ads.internal.s.a.f.a, com.facebook.ads.internal.view.c.a.d {
    private static final int a = ((int) (64.0f * y.b));
    private static final LayoutParams b = new LayoutParams(-1, -1);
    private static final int c = ((int) (16.0f * y.b));
    private static final int d = ((int) (12.0f * y.b));
    private static final int e = ((int) (10.0f * y.b));
    private static final float f = ((float) ((int) (4.0f * y.b)));
    private final k g;
    private final j h;
    private final com.facebook.ads.internal.adapters.a.a i;
    private final com.facebook.ads.internal.o.c j;
    private final f k;
    private final AtomicBoolean l = new AtomicBoolean();
    private final com.facebook.ads.internal.s.a.f m;
    private final com.facebook.ads.internal.s.a.f n;
    private WeakReference<com.facebook.ads.internal.view.c.a> o;
    private com.facebook.ads.internal.view.c.a.b p;
    private com.facebook.ads.internal.view.component.c q;
    private a r;
    private RelativeLayout s;
    private boolean t;
    private Toast u;
    @Nullable
    private c v;

    private static class a implements OnClickListener {
        final WeakReference<b> a;

        a(b bVar) {
            this.a = new WeakReference(bVar);
        }

        public void onClick(View view) {
            if (this.a.get() != null) {
                ((b) this.a.get()).m();
            }
        }
    }

    private static class b implements OnTouchListener {
        final WeakReference<com.facebook.ads.internal.view.c.a> a;
        final com.facebook.ads.internal.o.c b;
        final k c;

        private b(com.facebook.ads.internal.view.c.a aVar, com.facebook.ads.internal.o.c cVar, k kVar) {
            this.a = new WeakReference(aVar);
            this.b = cVar;
            this.c = kVar;
        }

        /* synthetic */ b(com.facebook.ads.internal.view.c.a aVar, com.facebook.ads.internal.o.c cVar, k kVar, AnonymousClass1 anonymousClass1) {
            this(aVar, cVar, kVar);
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (this.a.get() != null && motionEvent.getAction() == 1) {
                Map hashMap = new HashMap();
                ((com.facebook.ads.internal.view.c.a) this.a.get()).getViewabilityChecker().a(hashMap);
                hashMap.put("touch", com.facebook.ads.internal.s.a.k.a(((com.facebook.ads.internal.view.c.a) this.a.get()).getTouchDataRecorder().e()));
                this.b.d(this.c.g(), hashMap);
            }
            return false;
        }
    }

    public interface c {
        void a();

        void a(com.facebook.ads.internal.t.a aVar, w wVar);

        void b();

        void c();

        void c(boolean z);
    }

    private class d {
        private d() {
        }

        /* synthetic */ d(b bVar, AnonymousClass1 anonymousClass1) {
            this();
        }

        @JavascriptInterface
        public void onCTAClick() {
            b.this.m();
        }
    }

    public b(Context context, k kVar, com.facebook.ads.internal.o.c cVar, com.facebook.ads.internal.view.a.a aVar, c cVar2, boolean z) {
        super(context);
        int i = 0;
        this.t = false;
        this.g = kVar;
        this.h = kVar.e().j();
        this.i = kVar.d();
        this.j = cVar;
        this.v = cVar2;
        this.k = new f(context, aVar, com.facebook.ads.internal.view.f.a.CROSS);
        this.m = new com.facebook.ads.internal.s.a.f(z ? this.h.c() : 0, this);
        if (this.h.f()) {
            i = 3;
        }
        this.n = new com.facebook.ads.internal.s.a.f(i, new com.facebook.ads.internal.s.a.f.a() {
            public void a() {
                b.this.h();
            }

            public void a(int i) {
            }
        });
        g();
    }

    private static TextView a(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof TextView) {
                return (TextView) childAt;
            }
            if (childAt instanceof ViewGroup) {
                a((ViewGroup) childAt);
            }
        }
        return null;
    }

    private i a(com.facebook.ads.internal.view.component.c cVar) {
        View iVar = new i(getContext(), this.g.d().a(), true, 16, 14, 0);
        y.a(iVar);
        iVar.a(this.g.b().a(), this.g.b().b(), false, true);
        iVar.getDescriptionTextView().setAlpha(0.8f);
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(0, cVar.getId());
        layoutParams.setMargins(0, 0, c, 0);
        iVar.setLayoutParams(layoutParams);
        return iVar;
    }

    private void b(int i) {
        if (this.u != null) {
            this.u.setGravity(49, 0, a);
            String valueOf = String.valueOf(i);
            TextView a = a((ViewGroup) this.u.getView());
            if (a != null) {
                a.setText(this.h.e().replace("[secs]", valueOf));
                a.setGravity(17);
            }
        }
    }

    private void g() {
        this.k.a(this.i.a(), true);
        this.k.setShowPageDetails(false);
        this.k.a(this.g.a(), this.g.g(), this.h.c());
        this.k.setToolbarListener(new com.facebook.ads.internal.view.f.b() {
            public void a() {
                if (b.this.v != null) {
                    b.this.v.b();
                }
            }
        });
        y.a(this.k);
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(10);
        this.k.setLayoutParams(layoutParams);
        this.r = new a(getContext(), this.g);
        setLayoutParams(b);
        y.a((View) this, this.i.a().d(true));
        addView(this.r, b);
        y.a((View) this, -14473425);
        setLayoutParams(b);
    }

    private void h() {
        this.s = new RelativeLayout(getContext());
        y.a(this.s);
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.setMargins(c, d, c, d);
        layoutParams.addRule(12);
        this.s.setLayoutParams(layoutParams);
        this.q = i();
        i a = a(this.q);
        LayoutParams layoutParams2 = (LayoutParams) this.q.getLayoutParams();
        layoutParams2.addRule(6, a.getId());
        layoutParams2.addRule(8, a.getId());
        com.facebook.ads.internal.view.c.a j = j();
        j.loadUrl(this.h.a());
        j.setOnTouchListener(new b(j, this.j, this.g, null));
        j.addJavascriptInterface(new d(this, null), "FbPlayableAd");
        j.setCornerRadius(f);
        y.a((View) this, -14473425);
        LayoutParams layoutParams3 = new LayoutParams(-1, -1);
        layoutParams3.setMargins(c, 0, c, 0);
        layoutParams3.addRule(3, this.k.getId());
        layoutParams3.addRule(2, this.s.getId());
        j.setLayoutParams(layoutParams3);
        j.setVisibility(4);
        j.setOnAssetsLoadedListener(this);
        this.s.addView(a);
        this.s.addView(this.q);
        addView(this.k);
        addView(j);
        addView(this.s);
        this.k.setVisibility(4);
        j.setVisibility(4);
        j.setTranslationY(50.0f);
        this.s.setVisibility(4);
        this.s.setTranslationY(200.0f);
    }

    private com.facebook.ads.internal.view.component.c i() {
        View cVar = new com.facebook.ads.internal.view.component.c(getContext(), true, false, this.i.a());
        cVar.setButtonColor(452984831);
        cVar.setText(this.g.c().b());
        cVar.getBackground().setAlpha(0);
        y.a(cVar);
        cVar.setOnClickListener(new a(this));
        cVar.setTextSize(14.0f);
        cVar.setIncludeFontPadding(false);
        cVar.setPadding(e, e, e, e);
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(11);
        cVar.setLayoutParams(layoutParams);
        cVar.setVisibility(4);
        return cVar;
    }

    private com.facebook.ads.internal.view.c.a j() {
        this.p = new com.facebook.ads.internal.view.c.a.c() {
            public void a(@Nullable WebResourceError webResourceError) {
                b.this.t = true;
                if (b.this.o.get() != null) {
                    ((com.facebook.ads.internal.view.c.a) b.this.o.get()).setVisibility(4);
                }
                if (b.this.v != null) {
                    b.this.v.c();
                }
            }

            public void b() {
                if (b.this.l.compareAndSet(false, true) && b.this.o.get() != null && b.this.v != null) {
                    com.facebook.ads.internal.view.c.a aVar = (com.facebook.ads.internal.view.c.a) b.this.o.get();
                    b.this.v.a(aVar.getViewabilityChecker(), aVar.getTouchDataRecorder());
                    b.this.m.a();
                }
            }
        };
        com.facebook.ads.internal.view.c.a aVar = new com.facebook.ads.internal.view.c.a(getContext(), new WeakReference(this.p), 10);
        aVar.setLogMultipleImpressions(false);
        aVar.setWaitForAssetsToLoad(true);
        aVar.setCheckAssetsByJavascriptBridge(false);
        WebSettings settings = aVar.getSettings();
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        this.o = new WeakReference(aVar);
        return aVar;
    }

    private void k() {
        com.facebook.ads.internal.view.c.a adWebView = getAdWebView();
        if (adWebView != null) {
            y.a((ViewGroup) this);
            adWebView.setVisibility(0);
            y.b(this.r);
            this.k.setVisibility(0);
            this.s.setVisibility(0);
            adWebView.animate().setStartDelay(100).setDuration(300).translationYBy(-50.0f);
            this.s.animate().setStartDelay(100).setDuration(300).translationYBy(-200.0f);
        }
    }

    private void l() {
        y.a((ViewGroup) this, 500);
        this.q.setVisibility(0);
    }

    private void m() {
        if (this.v != null) {
            this.v.c(this.m.d() ^ 1);
        }
        if (!this.m.d()) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    b.this.n();
                }
            });
        }
    }

    private void n() {
        if (this.u == null || this.u.getView().getWindowVisibility() != 0) {
            this.u = Toast.makeText(getContext(), this.h.e(), 1);
            b(this.m.e());
            this.u.show();
        }
    }

    public void a() {
        if (this.v != null) {
            this.v.a();
        }
        this.k.a(true);
        l();
    }

    public void a(int i) {
        this.k.setProgress(100.0f * (1.0f - (((float) i) / ((float) this.h.c()))));
        b(i);
    }

    public void b() {
        if (!this.t && this.o.get() != null) {
            k();
        }
    }

    public void c() {
        if (this.h.f()) {
            this.n.a();
            return;
        }
        removeAllViews();
        h();
    }

    public void d() {
        com.facebook.ads.internal.s.a.f fVar;
        if (!this.n.d()) {
            fVar = this.n;
        } else if (!this.m.c()) {
            fVar = this.m;
        } else {
            return;
        }
        fVar.a();
    }

    public void e() {
        this.n.b();
        this.m.b();
    }

    public void f() {
        this.n.b();
        this.m.b();
        this.k.setToolbarListener(null);
        com.facebook.ads.internal.view.c.a aVar = this.o != null ? (com.facebook.ads.internal.view.c.a) this.o.get() : null;
        if (aVar != null) {
            aVar.removeJavascriptInterface("FbPlayableAd");
        }
        this.v = null;
        this.u = null;
    }

    public com.facebook.ads.internal.view.c.a getAdWebView() {
        return this.o != null ? (com.facebook.ads.internal.view.c.a) this.o.get() : null;
    }
}
