package com.facebook.ads.internal.view;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.AudienceNetworkActivity.BackButtonInterceptor;
import com.facebook.ads.internal.o.c;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.view.b.a;
import com.facebook.ads.internal.view.b.f;

@TargetApi(19)
public class b implements a {
    private static final String a = "b";
    private final AudienceNetworkActivity b;
    private final a c;
    private final f d;
    private final com.facebook.ads.internal.view.b.b e;
    private final c f;
    private final BackButtonInterceptor g = new BackButtonInterceptor() {
        public boolean interceptBackButton() {
            if (!b.this.d.canGoBack()) {
                return false;
            }
            b.this.d.goBack();
            return true;
        }
    };
    private String h;
    private String i;
    private long j;
    private boolean k = true;
    private long l = -1;
    private boolean m = true;

    public b(final AudienceNetworkActivity audienceNetworkActivity, c cVar, a.a aVar) {
        this.b = audienceNetworkActivity;
        this.f = cVar;
        int i = (int) (2.0f * y.b);
        this.c = new a(audienceNetworkActivity);
        this.c.setId(View.generateViewId());
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(10);
        this.c.setLayoutParams(layoutParams);
        this.c.setListener(new a.a() {
            public void a() {
                audienceNetworkActivity.finish();
            }
        });
        aVar.a(this.c);
        this.d = new f(audienceNetworkActivity);
        layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(3, this.c.getId());
        layoutParams.addRule(12);
        this.d.setLayoutParams(layoutParams);
        this.d.setListener(new f.a() {
            public void a(int i) {
                if (b.this.k) {
                    b.this.e.setProgress(i);
                }
            }

            public void a(String str) {
                b.this.k = true;
                b.this.c.setUrl(str);
            }

            public void b(String str) {
                b.this.c.setTitle(str);
            }

            public void c(String str) {
                b.this.e.setProgress(100);
                b.this.k = false;
            }
        });
        aVar.a(this.d);
        this.e = new com.facebook.ads.internal.view.b.b(audienceNetworkActivity, null, 16842872);
        layoutParams = new LayoutParams(-1, i);
        layoutParams.addRule(3, this.c.getId());
        this.e.setLayoutParams(layoutParams);
        this.e.setProgress(0);
        aVar.a(this.e);
        audienceNetworkActivity.addBackButtonInterceptor(this.g);
    }

    public void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        long longExtra;
        if (this.l < 0) {
            this.l = System.currentTimeMillis();
        }
        if (bundle == null) {
            this.h = intent.getStringExtra(AudienceNetworkActivity.BROWSER_URL);
            this.i = intent.getStringExtra(AudienceNetworkActivity.CLIENT_TOKEN);
            longExtra = intent.getLongExtra(AudienceNetworkActivity.HANDLER_TIME, -1);
        } else {
            this.h = bundle.getString(AudienceNetworkActivity.BROWSER_URL);
            this.i = bundle.getString(AudienceNetworkActivity.CLIENT_TOKEN);
            longExtra = bundle.getLong(AudienceNetworkActivity.HANDLER_TIME, -1);
        }
        this.j = longExtra;
        String str = this.h != null ? this.h : "about:blank";
        this.c.setUrl(str);
        this.d.loadUrl(str);
    }

    public void a(Bundle bundle) {
        bundle.putString(AudienceNetworkActivity.BROWSER_URL, this.h);
    }

    public void a(boolean z) {
        this.d.onPause();
        if (this.m) {
            this.m = false;
            this.f.g(this.i, new com.facebook.ads.internal.view.b.c.a(this.d.getFirstUrl()).a(this.j).b(this.l).c(this.d.getResponseEndMs()).d(this.d.getDomContentLoadedMs()).e(this.d.getScrollReadyMs()).f(this.d.getLoadFinishMs()).g(System.currentTimeMillis()).a().a());
        }
    }

    public void b(boolean z) {
        this.d.onResume();
    }

    public void onDestroy() {
        this.b.removeBackButtonInterceptor(this.g);
        com.facebook.ads.internal.s.c.b.a(this.d);
        this.d.destroy();
    }

    public void setListener(a.a aVar) {
    }
}
