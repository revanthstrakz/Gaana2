package com.facebook.ads.internal.view;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.a.d;
import com.facebook.ads.internal.adapters.a.g;
import com.facebook.ads.internal.adapters.a.h;
import com.facebook.ads.internal.o.c;
import com.facebook.ads.internal.s.a.t;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.view.f.a;
import com.facebook.ads.internal.view.f.b;

public abstract class i extends RelativeLayout implements a {
    protected static final int a = ((int) (56.0f * y.b));
    protected final c b;
    protected final f c = new f(getContext(), getAudienceNetworkListener(), a.CROSS);
    protected com.facebook.ads.internal.adapters.a.a d;
    private final a.a e;
    private final t f = new t(this);

    protected i(Context context, c cVar, a.a aVar) {
        super(context.getApplicationContext());
        this.b = cVar;
        this.e = aVar;
    }

    private void a() {
        removeAllViews();
        y.b(this);
    }

    /* Access modifiers changed, original: protected */
    public void a(View view, boolean z, int i) {
        f fVar;
        d a;
        this.f.a(t.a.DEFAULT);
        a();
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.setMargins(0, z ? 0 : a, 0, 0);
        addView(view, layoutParams);
        LayoutParams layoutParams2 = new LayoutParams(-1, a);
        layoutParams2.addRule(10);
        if (i == 1) {
            i = this.d.a().d(z);
            fVar = this.c;
            a = this.d.a();
        } else {
            i = this.d.b().d(z);
            fVar = this.c;
            a = this.d.b();
        }
        fVar.a(a, z);
        addView(this.c, layoutParams2);
        y.a((View) this, i);
        if (this.e != null) {
            this.e.a((View) this, 0);
            if (z && VERSION.SDK_INT >= 19) {
                this.f.a(t.a.FULL_SCREEN);
            }
        }
    }

    public void a(final AudienceNetworkActivity audienceNetworkActivity, g gVar) {
        this.f.a(audienceNetworkActivity.getWindow());
        this.d = gVar.b();
        this.c.a(gVar.a(), gVar.c(), ((h) gVar.d().get(0)).c().c());
        this.c.setToolbarListener(new b() {
            public void a() {
                audienceNetworkActivity.finish();
            }
        });
        if (com.facebook.ads.internal.c.a.b(getContext())) {
            this.c.a(gVar.a(), gVar.c());
        }
    }

    /* Access modifiers changed, original: protected */
    public a.a getAudienceNetworkListener() {
        return this.e;
    }

    /* Access modifiers changed, original: protected */
    public void onConfigurationChanged(Configuration configuration) {
        this.c.d();
        super.onConfigurationChanged(configuration);
        final ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            @RequiresApi(api = 16)
            public void onGlobalLayout() {
                i.this.c.e();
                if (VERSION.SDK_INT >= 14) {
                    viewTreeObserver.removeOnGlobalLayoutListener(this);
                }
            }
        });
    }

    public void onDestroy() {
        this.f.a();
        this.c.setToolbarListener(null);
        a();
    }

    public void setListener(a.a aVar) {
    }
}
