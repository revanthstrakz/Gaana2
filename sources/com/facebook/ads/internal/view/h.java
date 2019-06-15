package com.facebook.ads.internal.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MotionEvent;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.AudienceNetworkActivity.BackButtonInterceptor;
import com.facebook.ads.internal.adapters.a.g;
import com.facebook.ads.internal.s.a.w;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.view.g.a;
import com.facebook.ads.internal.view.g.b.b;
import com.facebook.ads.internal.view.g.b.c;
import com.facebook.ads.internal.view.g.b.d;
import com.facebook.ads.internal.view.g.b.e;
import com.facebook.ads.internal.view.g.b.i;
import com.facebook.ads.internal.view.g.b.j;
import com.facebook.ads.internal.view.g.b.k;
import com.facebook.ads.internal.view.g.b.l;
import com.facebook.ads.internal.view.g.b.m;
import com.facebook.ads.internal.view.g.c.f;
import com.facebook.ads.internal.view.g.c.o;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class h extends i {
    private boolean A = false;
    private final BackButtonInterceptor e = new BackButtonInterceptor() {
        public boolean interceptBackButton() {
            return h.this.c.a() ^ 1;
        }
    };
    private final e f = new e() {
        public void a(d dVar) {
            if (h.this.getAudienceNetworkListener() != null) {
                h.this.getAudienceNetworkListener().a("videoInterstitalEvent", (com.facebook.ads.internal.l.d) dVar);
            }
            if (!h.this.z) {
                h.this.k.g();
                h.this.k.l();
                h.this.z = true;
            }
            if (h.this.w != null) {
                h.this.w.finish();
            }
        }
    };
    private final k g = new k() {
        public void a(j jVar) {
            if (h.this.getAudienceNetworkListener() != null) {
                h.this.getAudienceNetworkListener().a("videoInterstitalEvent", (com.facebook.ads.internal.l.d) jVar);
            }
        }
    };
    private final i h = new i() {
        public void a(com.facebook.ads.internal.view.g.b.h hVar) {
            if (h.this.getAudienceNetworkListener() != null) {
                h.this.getAudienceNetworkListener().a("videoInterstitalEvent", (com.facebook.ads.internal.l.d) hVar);
            }
        }
    };
    private final c i = new c() {
        public void a(b bVar) {
            h.this.t.set(true);
            if (h.this.getAudienceNetworkListener() != null) {
                h.this.getAudienceNetworkListener().a("videoInterstitalEvent", (com.facebook.ads.internal.l.d) bVar);
            }
        }
    };
    private final m j = new m() {
        public void a(l lVar) {
            if (!h.this.z) {
                h.this.u.set(h.this.k.k());
                h.this.a();
            }
            if (h.this.getAudienceNetworkListener() != null) {
                h.this.getAudienceNetworkListener().a("videoInterstitalEvent", (com.facebook.ads.internal.l.d) lVar);
            }
            h.this.p.a();
        }
    };
    private final a k = new a(getContext());
    private final o l;
    private final f m;
    private final g n;
    private final com.facebook.ads.internal.adapters.a.h o;
    private final com.facebook.ads.internal.t.a p;
    private final com.facebook.ads.internal.t.a.a q;
    private final w r = new w();
    @Nullable
    private final com.facebook.ads.internal.f.b s;
    private final AtomicBoolean t = new AtomicBoolean(false);
    private final AtomicBoolean u = new AtomicBoolean(false);
    private final com.facebook.ads.internal.view.g.c v;
    @Nullable
    private AudienceNetworkActivity w;
    @Nullable
    private com.facebook.ads.internal.view.g.a.a x;
    private long y;
    private boolean z = false;

    public h(Context context, com.facebook.ads.internal.o.c cVar, g gVar, @Nullable com.facebook.ads.internal.f.b bVar, a.a aVar) {
        super(context, cVar, aVar);
        this.k.setVideoProgressReportIntervalMs(gVar.h());
        y.a(this.k);
        y.a(this.k, 0);
        this.n = gVar;
        this.o = (com.facebook.ads.internal.adapters.a.h) this.n.d().get(0);
        this.s = bVar;
        this.l = new o(getContext());
        this.m = new f(context);
        this.k.getEventBus().a(this.g, this.h, this.i, this.f, this.j);
        setupPlugins(this.o);
        this.q = new com.facebook.ads.internal.t.a.a() {
            public void a() {
                if (!h.this.r.b()) {
                    h.this.r.a();
                    Map hashMap = new HashMap();
                    if (!TextUtils.isEmpty(h.this.n.c())) {
                        h.this.p.a(hashMap);
                        hashMap.put("touch", com.facebook.ads.internal.s.a.k.a(h.this.r.e()));
                        h.this.b.a(h.this.n.c(), hashMap);
                        if (h.this.getAudienceNetworkListener() != null) {
                            h.this.getAudienceNetworkListener().a("com.facebook.ads.interstitial.impression.logged");
                        }
                    }
                }
            }
        };
        this.p = new com.facebook.ads.internal.t.a(this, 1, this.q);
        this.p.a(gVar.f());
        this.p.b(gVar.g());
        this.v = new com.facebook.ads.internal.view.g.b(getContext(), this.b, this.k, this.n.c());
        this.k.setVideoURI(a(this.o.c().a()));
    }

    private String a(String str) {
        CharSequence charSequence = "";
        if (!(this.s == null || str == null)) {
            charSequence = this.s.b(str);
        }
        return TextUtils.isEmpty(charSequence) ? str : charSequence;
    }

    private void a() {
        this.m.setVisibility(this.u.get() ? 0 : 8);
    }

    private void setUpContent(int i) {
        com.facebook.ads.internal.view.component.a.b a = com.facebook.ads.internal.view.component.a.c.a(new com.facebook.ads.internal.view.component.a.d.a(getContext(), this.b, getAudienceNetworkListener(), this.n, this.k, this.p, this.r).a(a).b(i).a(this.l).a(this.m).a());
        a();
        a(a, a.a(), i);
    }

    private void setupPlugins(com.facebook.ads.internal.adapters.a.h hVar) {
        com.facebook.ads.internal.view.g.a.b gVar;
        this.k.d();
        this.k.a(this.l);
        this.k.a(this.m);
        if (!TextUtils.isEmpty(hVar.c().g())) {
            gVar = new com.facebook.ads.internal.view.g.c.g(getContext());
            this.k.a(gVar);
            gVar.setImage(hVar.c().g());
        }
        gVar = new com.facebook.ads.internal.view.g.c.l(getContext(), true);
        this.k.a(gVar);
        this.k.a(new com.facebook.ads.internal.view.g.c.d(gVar, hVar.c().e() ? com.facebook.ads.internal.view.g.c.d.a.FADE_OUT_ON_PLAY : com.facebook.ads.internal.view.g.c.d.a.VISIBLE, true));
        this.k.a(new com.facebook.ads.internal.view.g.c.k(getContext()));
        this.k.a(this.c);
    }

    public void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        super.a(audienceNetworkActivity, this.n);
        this.w = audienceNetworkActivity;
        setUpContent(audienceNetworkActivity.getResources().getConfiguration().orientation);
        this.w.addBackButtonInterceptor(this.e);
        com.facebook.ads.internal.adapters.a.h hVar = (com.facebook.ads.internal.adapters.a.h) this.n.d().get(0);
        if (hVar.c().e()) {
            this.k.setVolume(hVar.c().f() ? 1.0f : 0.0f);
            this.k.a(com.facebook.ads.internal.view.g.a.a.AUTO_STARTED);
        }
        this.y = System.currentTimeMillis();
    }

    public void a(Bundle bundle) {
    }

    public void a(boolean z) {
        if (!this.z && !this.k.m()) {
            this.x = this.k.getVideoStartReason();
            this.A = z;
            this.k.a(false);
        }
    }

    public void b(boolean z) {
        if (!(this.z || this.k.n() || ((this.k.getState() == com.facebook.ads.internal.view.g.d.d.PREPARED && this.k.getVideoStartReason() == com.facebook.ads.internal.view.g.a.a.NOT_STARTED) || this.k.getState() == com.facebook.ads.internal.view.g.d.d.PLAYBACK_COMPLETED || this.x == null || (this.A && !z)))) {
            this.k.a(this.x);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        removeAllViews();
        y.b(this.k);
        y.b(this.l);
        y.b(this.m);
        setUpContent(configuration.orientation);
        super.onConfigurationChanged(configuration);
    }

    public void onDestroy() {
        if (!this.z) {
            if (!this.t.get()) {
                this.k.f();
            }
            if (this.n != null) {
                com.facebook.ads.internal.l.b.a(com.facebook.ads.internal.l.a.a(this.y, com.facebook.ads.internal.l.a.a.XOUT, this.n.e()));
                if (!TextUtils.isEmpty(this.n.c())) {
                    Map hashMap = new HashMap();
                    this.p.a(hashMap);
                    hashMap.put("touch", com.facebook.ads.internal.s.a.k.a(this.r.e()));
                    this.b.i(this.n.c(), hashMap);
                }
            }
            this.k.g();
            this.k.l();
            this.z = true;
        }
        this.p.c();
        this.w = null;
        super.onDestroy();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.r.a(motionEvent, this, this);
        return super.onInterceptTouchEvent(motionEvent);
    }
}
