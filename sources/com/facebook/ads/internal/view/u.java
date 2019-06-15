package com.facebook.ads.internal.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.l.d;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.view.g.a;
import com.facebook.ads.internal.view.g.b.b;
import com.facebook.ads.internal.view.g.b.c;
import com.facebook.ads.internal.view.g.b.e;
import com.facebook.ads.internal.view.g.b.f;
import com.facebook.ads.internal.view.g.b.g;
import com.facebook.ads.internal.view.g.b.h;
import com.facebook.ads.internal.view.g.b.i;
import com.facebook.ads.internal.view.g.b.j;
import com.facebook.ads.internal.view.g.b.k;
import com.facebook.ads.internal.view.g.b.p;

public class u implements a {
    private final k a = new k() {
        public void a(j jVar) {
            u.this.h.a("videoInterstitalEvent", (d) jVar);
        }
    };
    private final i b = new i() {
        public void a(h hVar) {
            u.this.h.a("videoInterstitalEvent", (d) hVar);
        }
    };
    private final c c = new c() {
        public void a(b bVar) {
            u.this.h.a("videoInterstitalEvent", (d) bVar);
        }
    };
    private final e d = new e() {
        public void a(com.facebook.ads.internal.view.g.b.d dVar) {
            u.this.e.finish();
        }
    };
    private final AudienceNetworkActivity e;
    private final com.facebook.ads.internal.o.c f;
    private final a g;
    private final a.a h;
    private com.facebook.ads.internal.view.g.b i;
    private int j;

    public u(final AudienceNetworkActivity audienceNetworkActivity, com.facebook.ads.internal.o.c cVar, a.a aVar) {
        this.e = audienceNetworkActivity;
        this.f = cVar;
        this.g = new a(audienceNetworkActivity);
        this.g.a(new com.facebook.ads.internal.view.g.c.b(audienceNetworkActivity));
        this.g.getEventBus().a(this.a, this.b, this.c, this.d);
        this.h = aVar;
        this.g.setIsFullScreen(true);
        this.g.setVolume(1.0f);
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(15);
        this.g.setLayoutParams(layoutParams);
        aVar.a(this.g);
        View dVar = new d(audienceNetworkActivity);
        dVar.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                audienceNetworkActivity.finish();
            }
        });
        aVar.a(dVar);
    }

    public void a(int i) {
        this.g.setVideoProgressReportIntervalMs(i);
    }

    public void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        String stringExtra = intent.getStringExtra("useNativeCtaButton");
        if (!(stringExtra == null || stringExtra.isEmpty())) {
            View bVar = new com.facebook.ads.internal.view.d.b(audienceNetworkActivity, stringExtra);
            LayoutParams layoutParams = new LayoutParams(-2, -2);
            int i = (int) (16.0f * y.b);
            layoutParams.setMargins(i, i, i, i);
            layoutParams.addRule(10);
            layoutParams.addRule(9);
            bVar.setLayoutParams(layoutParams);
            bVar.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    u.this.h.a("performCtaClick");
                }
            });
            this.h.a(bVar);
        }
        this.j = intent.getIntExtra(AudienceNetworkActivity.VIDEO_SEEK_TIME, 0);
        this.i = new com.facebook.ads.internal.view.g.b((Context) audienceNetworkActivity, this.f, this.g, intent.getStringExtra(AudienceNetworkActivity.CLIENT_TOKEN), intent.getBundleExtra(AudienceNetworkActivity.VIDEO_LOGGER));
        this.g.setVideoMPD(intent.getStringExtra(AudienceNetworkActivity.VIDEO_MPD));
        this.g.setVideoURI(intent.getStringExtra(AudienceNetworkActivity.VIDEO_URL));
        if (this.j > 0) {
            this.g.a(this.j);
        }
        if (intent.getBooleanExtra("autoplay", false)) {
            this.g.a(com.facebook.ads.internal.view.g.a.a.USER_STARTED);
        }
    }

    public void a(Bundle bundle) {
    }

    public void a(View view) {
        this.g.setControlsAnchorView(view);
    }

    public void a(boolean z) {
        this.h.a("videoInterstitalEvent", new f());
        this.g.e();
    }

    public void b(boolean z) {
        this.h.a("videoInterstitalEvent", new g());
        this.g.a(com.facebook.ads.internal.view.g.a.a.USER_STARTED);
    }

    public void onDestroy() {
        this.h.a("videoInterstitalEvent", new p(this.j, this.g.getCurrentPositionInMillis()));
        this.i.b(this.g.getCurrentPositionInMillis());
        this.g.g();
        this.g.l();
    }

    public void setListener(a.a aVar) {
    }
}
