package com.facebook.ads.internal.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.a.h;
import com.facebook.ads.internal.o.c;
import com.facebook.ads.internal.s.a.k;
import com.facebook.ads.internal.s.a.w;
import com.facebook.ads.internal.t.a;
import com.facebook.ads.internal.view.c.d;
import com.facebook.ads.internal.view.c.e;
import com.facebook.ads.internal.view.component.a.b;
import java.util.HashMap;
import java.util.Map;

public class g extends i {
    private final com.facebook.ads.internal.adapters.a.g e;
    private final a f;
    private final w g = new w();
    private final a.a h;
    private long i;

    public g(Context context, com.facebook.ads.internal.adapters.a.g gVar, c cVar, a.a aVar) {
        super(context, cVar, aVar);
        this.e = gVar;
        this.h = new a.a() {
            public void a() {
                if (!g.this.g.b()) {
                    g.this.g.a();
                    Map hashMap = new HashMap();
                    g.this.f.a(hashMap);
                    hashMap.put("touch", k.a(g.this.g.e()));
                    g.this.b.a(g.this.e.c(), hashMap);
                    if (g.this.getAudienceNetworkListener() != null) {
                        g.this.getAudienceNetworkListener().a("com.facebook.ads.interstitial.impression.logged");
                    }
                }
            }
        };
        this.f = new a(this, 100, this.h);
        this.f.a(gVar.f());
    }

    private void setUpContent(int i) {
        h hVar = (h) this.e.d().get(0);
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ScaleType.CENTER);
        imageView.setAdjustViewBounds(true);
        d a = new d(imageView).a(hVar.c().i(), hVar.c().h());
        a.a(new e() {
            public void a(boolean z) {
                if (z) {
                    g.this.f.a();
                }
            }
        });
        a.a(hVar.c().g());
        b a2 = com.facebook.ads.internal.view.component.a.c.a(new com.facebook.ads.internal.view.component.a.d.a(getContext(), this.b, getAudienceNetworkListener(), this.e, imageView, this.f, this.g).a(a).b(i).a());
        a(a2, a2.a(), i);
    }

    public void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        super.a(audienceNetworkActivity, this.e);
        setUpContent(audienceNetworkActivity.getResources().getConfiguration().orientation);
        this.i = System.currentTimeMillis();
    }

    public void a(Bundle bundle) {
    }

    public void a(boolean z) {
    }

    public void b(boolean z) {
    }

    public void onConfigurationChanged(Configuration configuration) {
        removeAllViews();
        setUpContent(configuration.orientation);
        super.onConfigurationChanged(configuration);
    }

    public void onDestroy() {
        if (this.e != null) {
            com.facebook.ads.internal.l.b.a(com.facebook.ads.internal.l.a.a(this.i, com.facebook.ads.internal.l.a.a.XOUT, this.e.e()));
            if (!TextUtils.isEmpty(this.e.c())) {
                Map hashMap = new HashMap();
                this.f.a(hashMap);
                hashMap.put("touch", k.a(this.g.e()));
                this.b.i(this.e.c(), hashMap);
            }
        }
        this.f.c();
        super.onDestroy();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.g.a(motionEvent, this, this);
        return super.onInterceptTouchEvent(motionEvent);
    }
}
