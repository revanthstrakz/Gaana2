package com.facebook.ads.internal.view.a;

import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.o.c;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.s.c.g;
import com.facebook.ads.internal.view.a;

public class b extends RelativeLayout {
    private static final int a = ((int) (8.0f * y.b));
    private final c b;
    private final a c;
    private final a.a d;
    private final String e;
    private com.facebook.ads.internal.c.b f;
    private int g = 0;
    private com.facebook.ads.internal.c.b.a h = com.facebook.ads.internal.c.b.a.NONE;
    private final c i = new c() {
        public void a() {
            b.this.d.a("com.facebook.ads.adreporting.FINISH_AD_REPORTING_FLOW");
        }

        public void a(com.facebook.ads.internal.c.c cVar) {
            b.this.g = b.this.g - 1;
            if (cVar.e() == null) {
                b.this.e();
            } else {
                b.this.a(cVar.e());
            }
        }

        public void b() {
            if (!TextUtils.isEmpty(com.facebook.ads.internal.c.a.o(b.this.getContext()))) {
                g.a(new g(), b.this.getContext(), Uri.parse(com.facebook.ads.internal.c.a.o(b.this.getContext())), b.this.e);
            }
            b.this.f.c();
        }

        public void b(com.facebook.ads.internal.c.c cVar) {
            b.this.g = b.this.g + 1;
            b.this.f.a(cVar.a());
            if (cVar.d().isEmpty()) {
                b.this.b(cVar);
            } else {
                b.this.a(cVar);
            }
        }
    };

    public b(Context context, c cVar, a aVar, a.a aVar2, String str) {
        super(context);
        this.b = cVar;
        this.c = aVar;
        this.d = aVar2;
        this.e = str;
        y.a((View) this, -1728053248);
        setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                b.this.d();
                b.this.c.b(true);
                b.this.c();
            }
        });
    }

    private static LayoutParams a(boolean z) {
        LayoutParams layoutParams = new LayoutParams(-1, z ? -1 : -2);
        layoutParams.addRule(12);
        return layoutParams;
    }

    private void a(com.facebook.ads.internal.c.c cVar) {
        this.f.a(this.h, this.g);
        boolean z = this.h == com.facebook.ads.internal.c.b.a.REPORT;
        View fVar = new f(getContext(), cVar, this.i, z ? com.facebook.ads.internal.c.a.f(getContext()) : com.facebook.ads.internal.c.a.c(getContext()), z ? com.facebook.ads.internal.s.b.b.REPORT_AD : com.facebook.ads.internal.s.b.b.HIDE_AD);
        fVar.setClickable(true);
        y.a(fVar, -1);
        fVar.setPadding(a * 2, a, a * 2, a);
        f();
        removeAllViews();
        addView(fVar, a(false));
    }

    private void b(com.facebook.ads.internal.c.c cVar) {
        if (this.h != com.facebook.ads.internal.c.b.a.NONE) {
            this.f.a(this.h);
            boolean z = this.h == com.facebook.ads.internal.c.b.a.REPORT;
            a a = new a.a(getContext(), this.i).a(z ? com.facebook.ads.internal.c.a.k(getContext()) : com.facebook.ads.internal.c.a.j(getContext())).b(com.facebook.ads.internal.c.a.l(getContext())).c(cVar.b()).a(z ? com.facebook.ads.internal.s.b.b.REPORT_AD : com.facebook.ads.internal.s.b.b.HIDE_AD).a(z ? -552389 : -13272859).a();
            y.a((ViewGroup) this);
            removeAllViews();
            addView(a, a(true));
        }
    }

    private void c() {
        if (this.f.e()) {
            this.b.k(this.e, this.f.d());
            this.f.f();
        }
    }

    private void d() {
        y.c(this);
        removeAllViews();
        y.b(this);
    }

    private void e() {
        this.f.a();
        final com.facebook.ads.internal.c.c e = com.facebook.ads.internal.c.a.e(getContext());
        e eVar = new e(getContext());
        eVar.a(com.facebook.ads.internal.s.b.b.HIDE_AD, com.facebook.ads.internal.c.a.c(getContext()), com.facebook.ads.internal.c.a.d(getContext()));
        eVar.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                b.this.h = com.facebook.ads.internal.c.b.a.HIDE;
                b.this.g = b.this.g + 1;
                b.this.a(e);
            }
        });
        final com.facebook.ads.internal.c.c h = com.facebook.ads.internal.c.a.h(getContext());
        e eVar2 = new e(getContext());
        eVar2.a(com.facebook.ads.internal.s.b.b.REPORT_AD, com.facebook.ads.internal.c.a.f(getContext()), com.facebook.ads.internal.c.a.g(getContext()));
        eVar2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                b.this.h = com.facebook.ads.internal.c.b.a.REPORT;
                b.this.g = b.this.g + 1;
                b.this.a(h);
            }
        });
        e eVar3 = new e(getContext());
        eVar3.a(com.facebook.ads.internal.s.b.b.INTERSTITIAL_AD_CHOICES, com.facebook.ads.internal.c.a.m(getContext()), "");
        eVar3.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                b.this.d();
                b.this.c.b(true);
                if (!TextUtils.isEmpty(com.facebook.ads.internal.c.a.n(b.this.getContext()))) {
                    g.a(new g(), b.this.getContext(), Uri.parse(com.facebook.ads.internal.c.a.n(b.this.getContext())), b.this.e);
                }
                b.this.f.b();
                b.this.c();
            }
        });
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        View linearLayout = new LinearLayout(getContext());
        linearLayout.setClickable(true);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(a * 2, a, a * 2, a);
        y.a(linearLayout, -1);
        if (e.d().size() > 0) {
            linearLayout.addView(eVar, layoutParams);
        }
        if (h.d().size() > 0) {
            linearLayout.addView(eVar2, layoutParams);
        }
        linearLayout.addView(eVar3, layoutParams);
        f();
        removeAllViews();
        addView(linearLayout, a(false));
    }

    private void f() {
        if (VERSION.SDK_INT >= 21) {
            Transition transitionSet = new TransitionSet();
            transitionSet.setOrdering(0);
            transitionSet.addTransition(new ChangeBounds()).addTransition(new Explode());
            y.a((ViewGroup) this, transitionSet);
            return;
        }
        y.a((ViewGroup) this);
    }

    public void a() {
        this.f = new com.facebook.ads.internal.c.b();
        this.c.a(true);
        e();
    }

    public void b() {
        c();
    }
}
