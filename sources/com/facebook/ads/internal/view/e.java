package com.facebook.ads.internal.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.m;
import com.facebook.ads.internal.adapters.n;
import com.facebook.ads.internal.o.c;
import com.facebook.ads.internal.s.a.k;
import com.facebook.ads.internal.view.a.a;
import com.facebook.ads.internal.view.c.a.b;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class e implements a {
    private static final String a = "e";
    private final a b;
    private final com.facebook.ads.internal.view.c.a c;
    private final b d;
    private final n e;
    private final c f;
    private m g;
    private long h = System.currentTimeMillis();
    private long i;
    private com.facebook.ads.internal.l.a.a j;

    public e(final AudienceNetworkActivity audienceNetworkActivity, final c cVar, a aVar) {
        this.b = aVar;
        this.f = cVar;
        this.d = new com.facebook.ads.internal.view.c.a.c() {
            private long d = 0;

            public void a() {
                e.this.e.b();
            }

            public void a(String str, Map<String, String> map) {
                Uri parse = Uri.parse(str);
                if ("fbad".equals(parse.getScheme()) && "close".equals(parse.getAuthority())) {
                    audienceNetworkActivity.finish();
                    return;
                }
                long j = this.d;
                this.d = System.currentTimeMillis();
                if (this.d - j >= 1000) {
                    if ("fbad".equals(parse.getScheme()) && com.facebook.ads.internal.a.c.a(parse.getAuthority())) {
                        e.this.b.a("com.facebook.ads.interstitial.clicked");
                    }
                    com.facebook.ads.internal.a.b a = com.facebook.ads.internal.a.c.a(audienceNetworkActivity, cVar, e.this.g.c(), parse, map);
                    if (a != null) {
                        try {
                            e.this.j = a.a();
                            e.this.i = System.currentTimeMillis();
                            a.b();
                        } catch (Exception e) {
                            Log.e(e.a, "Error executing action", e);
                        }
                    }
                }
            }

            public void b() {
                e.this.e.a();
            }
        };
        this.c = new com.facebook.ads.internal.view.c.a(audienceNetworkActivity, new WeakReference(this.d), 1);
        this.c.setLayoutParams(new LayoutParams(-1, -1));
        Context context = audienceNetworkActivity;
        c cVar2 = cVar;
        this.e = new n(context, cVar2, this.c, this.c.getViewabilityChecker(), new com.facebook.ads.internal.adapters.c() {
            public void a() {
                e.this.b.a("com.facebook.ads.interstitial.impression.logged");
            }
        });
        aVar.a(this.c);
    }

    public void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        if (bundle == null || !bundle.containsKey("dataModel")) {
            this.g = m.b(intent);
            if (this.g != null) {
                this.e.a(this.g);
                this.c.loadDataWithBaseURL(com.facebook.ads.internal.s.c.b.a(), this.g.d(), "text/html", AudienceNetworkActivity.WEBVIEW_ENCODING, null);
                this.c.a(this.g.h(), this.g.i());
            }
            return;
        }
        this.g = m.a(bundle.getBundle("dataModel"));
        if (this.g != null) {
            this.c.loadDataWithBaseURL(com.facebook.ads.internal.s.c.b.a(), this.g.d(), "text/html", AudienceNetworkActivity.WEBVIEW_ENCODING, null);
            this.c.a(this.g.h(), this.g.i());
        }
    }

    public void a(Bundle bundle) {
        if (this.g != null) {
            bundle.putBundle("dataModel", this.g.j());
        }
    }

    public void a(boolean z) {
        this.c.onPause();
    }

    public void b(boolean z) {
        if (!(this.i <= 0 || this.j == null || this.g == null)) {
            com.facebook.ads.internal.l.b.a(com.facebook.ads.internal.l.a.a(this.i, this.j, this.g.g()));
        }
        this.c.onResume();
    }

    public void onDestroy() {
        if (this.g != null) {
            com.facebook.ads.internal.l.b.a(com.facebook.ads.internal.l.a.a(this.h, com.facebook.ads.internal.l.a.a.XOUT, this.g.g()));
            if (!TextUtils.isEmpty(this.g.c())) {
                Map hashMap = new HashMap();
                this.c.getViewabilityChecker().a(hashMap);
                hashMap.put("touch", k.a(this.c.getTouchData()));
                this.f.i(this.g.c(), hashMap);
            }
        }
        com.facebook.ads.internal.s.c.b.a(this.c);
        this.c.destroy();
    }

    public void setListener(a aVar) {
    }
}
