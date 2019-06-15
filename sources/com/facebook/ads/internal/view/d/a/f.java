package com.facebook.ads.internal.view.d.a;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView.Adapter;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.a.d;
import com.facebook.ads.internal.adapters.a.g;
import com.facebook.ads.internal.adapters.a.h;
import com.facebook.ads.internal.f.b;
import com.facebook.ads.internal.o.c;
import com.facebook.ads.internal.s.a.k;
import com.facebook.ads.internal.s.a.w;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.t.a;
import com.facebook.ads.internal.view.component.e;
import com.facebook.ads.internal.view.i;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class f extends i {
    private static final int e = ((int) (48.0f * y.b));
    private static final int f = ((int) (y.b * 8.0f));
    private static final int g = ((int) (8.0f * y.b));
    private static final int h = ((int) (56.0f * y.b));
    private static final int i = ((int) (12.0f * y.b));
    private final w j = new w();
    @Nullable
    private b k;
    @Nullable
    private LinearLayout l;
    private String m;
    private long n;
    private String o;
    private List<b> p;
    private a q;
    @Nullable
    private e r;
    @Nullable
    private c s;
    private a t;
    private a.a u;
    private int v;
    private int w;

    public f(Context context, c cVar, @Nullable b bVar, com.facebook.ads.internal.view.a.a aVar) {
        super(context, cVar, aVar);
        this.k = bVar;
    }

    private void a(g gVar) {
        this.m = gVar.c();
        this.o = gVar.e();
        this.v = gVar.f();
        this.w = gVar.g();
        List d = gVar.d();
        this.p = new ArrayList(d.size());
        for (int i = 0; i < d.size(); i++) {
            this.p.add(new b(i, d.size(), (h) d.get(i)));
        }
    }

    private void a(a aVar) {
        new PagerSnapHelper().attachToRecyclerView(this.s);
        aVar.a(new d.a() {
            public void a(int i) {
                if (f.this.r != null) {
                    f.this.r.a(i);
                }
            }
        });
        this.r = new e(getContext(), this.d.a(), this.p.size());
        LayoutParams layoutParams = new LayoutParams(-1, g);
        layoutParams.setMargins(0, i, 0, 0);
        this.r.setLayoutParams(layoutParams);
    }

    public void a() {
        if (this.l != null) {
            this.l.removeAllViews();
            this.l = null;
        }
        if (this.s != null) {
            this.s.removeAllViews();
            this.s = null;
        }
        if (this.r != null) {
            this.r.removeAllViews();
            this.r = null;
        }
    }

    public void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        g gVar = (g) intent.getSerializableExtra("ad_data_bundle");
        super.a(audienceNetworkActivity, gVar);
        a(gVar);
        setUpLayout(audienceNetworkActivity.getResources().getConfiguration().orientation);
        this.n = System.currentTimeMillis();
    }

    public void a(Bundle bundle) {
    }

    public void a(boolean z) {
        if (this.q != null) {
            this.q.a();
        }
    }

    public void b(boolean z) {
        this.q.b();
    }

    public void onConfigurationChanged(Configuration configuration) {
        a();
        setUpLayout(configuration.orientation);
        super.onConfigurationChanged(configuration);
    }

    public void onDestroy() {
        super.onDestroy();
        com.facebook.ads.internal.l.b.a(com.facebook.ads.internal.l.a.a(this.n, com.facebook.ads.internal.l.a.a.XOUT, this.o));
        if (!TextUtils.isEmpty(this.m)) {
            Map hashMap = new HashMap();
            this.t.a(hashMap);
            hashMap.put("touch", k.a(this.j.e()));
            this.b.i(this.m, hashMap);
        }
        a();
        this.t.c();
        this.t = null;
        this.u = null;
        this.p = null;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.j.a(motionEvent, this, this);
        return super.onInterceptTouchEvent(motionEvent);
    }

    public void setUpLayout(int i) {
        LinearLayout linearLayout;
        int i2;
        int i3;
        int i4;
        int i5;
        f fVar;
        int i6 = i;
        this.l = new LinearLayout(getContext());
        if (i6 == 1) {
            linearLayout = this.l;
            i2 = 17;
        } else {
            linearLayout = this.l;
            i2 = 48;
        }
        linearLayout.setGravity(i2);
        this.l.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this.l.setOrientation(1);
        int i7 = y.a.widthPixels;
        i2 = y.a.heightPixels;
        if (i6 == 1) {
            i2 = Math.min(i7 - (f * 4), i2 / 2);
            i7 = (i7 - i2) / 8;
            i3 = i7;
            i4 = i2;
            i5 = 4 * i7;
        } else {
            i2 -= (h + e) + (f * 2);
            i7 = f;
            i3 = i7;
            i4 = i2;
            i5 = 2 * i7;
        }
        this.u = new a.a() {
            public void a() {
                Map hashMap = new HashMap();
                if (!f.this.j.b()) {
                    f.this.j.a();
                    if (f.this.getAudienceNetworkListener() != null) {
                        f.this.getAudienceNetworkListener().a("com.facebook.ads.interstitial.impression.logged");
                    }
                    if (!TextUtils.isEmpty(f.this.m)) {
                        f.this.t.a(hashMap);
                        hashMap.put("touch", k.a(f.this.j.e()));
                        f.this.b.a(f.this.m, hashMap);
                    }
                }
            }
        };
        this.t = new a(this, 1, this.u);
        this.t.a(this.v);
        this.t.b(this.w);
        this.s = new c(getContext());
        this.s.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        this.q = new a(this.s, i6, this.p, this.t);
        c cVar = this.s;
        List list = this.p;
        c cVar2 = this.b;
        b bVar = this.k;
        a aVar = this.t;
        w wVar = this.j;
        com.facebook.ads.internal.view.a.a audienceNetworkListener = getAudienceNetworkListener();
        d a = i6 == 1 ? this.d.a() : this.d.b();
        String str = this.m;
        a aVar2 = this.q;
        Adapter adapter = r1;
        c cVar3 = cVar;
        Adapter dVar = new d(list, cVar2, bVar, aVar, wVar, audienceNetworkListener, a, str, i4, i3, i5, i6, aVar2);
        cVar3.setAdapter(adapter);
        int i8 = i;
        if (i8 == 1) {
            fVar = this;
            fVar.a(fVar.q);
        } else {
            fVar = this;
        }
        fVar.l.addView(fVar.s);
        if (fVar.r != null) {
            fVar.l.addView(fVar.r);
        }
        fVar.a(fVar.l, false, i8);
    }
}
