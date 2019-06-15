package com.facebook.ads.internal.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.AudienceNetworkActivity.BackButtonInterceptor;
import com.facebook.ads.internal.adapters.a.j;
import com.facebook.ads.internal.adapters.a.k;
import com.facebook.ads.internal.adapters.s;
import com.facebook.ads.internal.s.a.p;
import com.facebook.ads.internal.s.a.w;
import com.facebook.ads.internal.s.c.e;
import com.facebook.ads.internal.s.c.f;
import com.facebook.ads.internal.view.e.b;
import com.facebook.ads.internal.view.e.b.c;
import com.facebook.ads.internal.view.g.b.z;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

@TargetApi(16)
public class n extends RelativeLayout implements a, c {
    private final com.facebook.ads.internal.o.c a;
    private final k b;
    private final j c;
    private final com.facebook.ads.internal.adapters.a.a d;
    private int e;
    @Nullable
    private Context f;
    @Nullable
    private AudienceNetworkActivity g;
    @Nullable
    private com.facebook.ads.internal.view.a.a h;
    private Executor i = p.a;
    private final BackButtonInterceptor j = new BackButtonInterceptor() {
        public boolean interceptBackButton() {
            return n.this.m ^ 1;
        }
    };
    private boolean k;
    private b l;
    private boolean m;
    private s n;

    private static class a implements com.facebook.ads.internal.s.c.e.a {
        final WeakReference<com.facebook.ads.internal.view.a.a> a;

        private a(WeakReference<com.facebook.ads.internal.view.a.a> weakReference) {
            this.a = weakReference;
        }

        /* synthetic */ a(WeakReference weakReference, AnonymousClass1 anonymousClass1) {
            this(weakReference);
        }

        public void a() {
            if (this.a.get() != null) {
                ((com.facebook.ads.internal.view.a.a) this.a.get()).a(z.REWARD_SERVER_FAILED.a());
            }
        }

        public void a(f fVar) {
            if (this.a.get() != null) {
                com.facebook.ads.internal.view.a.a aVar;
                z zVar;
                if (fVar == null || !fVar.a()) {
                    aVar = (com.facebook.ads.internal.view.a.a) this.a.get();
                    zVar = z.REWARD_SERVER_FAILED;
                } else {
                    aVar = (com.facebook.ads.internal.view.a.a) this.a.get();
                    zVar = z.REWARD_SERVER_SUCCESS;
                }
                aVar.a(zVar.a());
            }
        }
    }

    public n(Context context, com.facebook.ads.internal.o.c cVar, com.facebook.ads.internal.view.a.a aVar, k kVar) {
        super(context);
        this.f = context;
        this.h = aVar;
        this.a = cVar;
        this.b = kVar;
        this.c = kVar.e().j();
        this.d = kVar.d();
    }

    @NonNull
    private com.facebook.ads.internal.view.component.a a(com.facebook.ads.internal.view.c.a aVar) {
        return new com.facebook.ads.internal.view.component.a(this.f, true, false, z.REWARDED_VIDEO_AD_CLICK.a(), this.d.a(), this.a, this.h, aVar.getViewabilityChecker(), aVar.getTouchDataRecorder());
    }

    private void a(AudienceNetworkActivity audienceNetworkActivity) {
        this.e = audienceNetworkActivity.getRequestedOrientation();
        audienceNetworkActivity.setRequestedOrientation(1);
    }

    private void b(com.facebook.ads.internal.t.a aVar, w wVar) {
        c(aVar, wVar).a();
    }

    @NonNull
    private s c(com.facebook.ads.internal.t.a aVar, w wVar) {
        if (this.n != null) {
            return this.n;
        }
        this.n = new s(getContext(), this.a, aVar, wVar, new com.facebook.ads.internal.adapters.c() {
            public void a() {
                n.this.d();
            }
        });
        this.n.a(this.b);
        return this.n;
    }

    private void d() {
        if (this.h != null) {
            this.h.a(z.REWARDED_VIDEO_IMPRESSION.a());
        }
    }

    private void e() {
        String a = this.b.f().a();
        if (this.f != null || !TextUtils.isEmpty(a)) {
            e eVar = new e(this.f, new HashMap());
            eVar.a(new a(new WeakReference(this.h), null));
            eVar.executeOnExecutor(this.i, new String[]{a});
        }
    }

    private void f() {
        if (this.h != null) {
            this.h.a(z.REWARDED_VIDEO_COMPLETE.a(), new com.facebook.ads.internal.view.g.b.b(0, 0));
        }
    }

    public void a() {
        this.m = true;
        e();
        f();
        com.facebook.ads.internal.view.c.a adWebView = this.l.getAdWebView();
        if (this.k && adWebView != null) {
            a(adWebView).b(this.b.c(), this.b.g(), new HashMap());
        }
    }

    public void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        if (this.h != null && this.f != null) {
            this.g = audienceNetworkActivity;
            this.g.addBackButtonInterceptor(this.j);
            a(audienceNetworkActivity);
            b bVar = new b(this.f, this.b, this.a, this.h, this, true);
            this.l = bVar;
            addView(bVar);
            this.h.a((View) this);
            bVar.c();
        }
    }

    public void a(Bundle bundle) {
    }

    public void a(com.facebook.ads.internal.t.a aVar, w wVar) {
        b(aVar, wVar);
    }

    public void a(boolean z) {
        this.l.e();
    }

    public void b() {
        if (this.h != null) {
            this.h.a(z.REWARDED_VIDEO_END_ACTIVITY.a());
        }
    }

    public void b(boolean z) {
        this.l.d();
    }

    public void c() {
        if (this.h != null) {
            this.h.a(z.REWARDED_VIDEO_ERROR.a());
        }
    }

    public void c(boolean z) {
        this.k = true;
        com.facebook.ads.internal.view.c.a adWebView = this.l.getAdWebView();
        if (adWebView != null) {
            com.facebook.ads.internal.view.component.a a = a(adWebView);
            a.a(this.b.c(), this.b.g(), new HashMap(), z);
            a.performClick();
        }
    }

    public void onDestroy() {
        if (this.g != null) {
            this.g.removeBackButtonInterceptor(this.j);
            this.g.setRequestedOrientation(this.e);
        }
        com.facebook.ads.internal.view.c.a adWebView = this.l.getAdWebView();
        if (!(adWebView == null || TextUtils.isEmpty(this.b.g()))) {
            Map hashMap = new HashMap();
            adWebView.getViewabilityChecker().a(hashMap);
            hashMap.put("touch", com.facebook.ads.internal.s.a.k.a(adWebView.getTouchDataRecorder().e()));
            this.a.i(this.b.g(), hashMap);
        }
        this.l.f();
        this.h = null;
        this.g = null;
        this.f = null;
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.l.getAdWebView() != null) {
            if (z) {
                b(false);
            } else {
                a(false);
            }
        }
    }

    public void setListener(com.facebook.ads.internal.view.a.a aVar) {
        this.h = aVar;
    }
}
