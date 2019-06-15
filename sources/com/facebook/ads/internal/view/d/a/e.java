package com.facebook.ads.internal.view.d.a;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.o.c;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.view.g.b;
import com.facebook.ads.internal.view.g.c.d;
import com.facebook.ads.internal.view.g.c.d.a;
import com.facebook.ads.internal.view.g.c.f;
import com.facebook.ads.internal.view.g.c.g;
import com.facebook.ads.internal.view.g.c.l;
import com.facebook.ads.internal.view.t;
import java.util.Map;

public class e extends FrameLayout {
    private static final int a = ((int) (16.0f * y.b));
    private final c b;
    private t c;
    private f d;
    private l e;
    private g f;
    @Nullable
    private b g;

    public e(Context context, c cVar) {
        super(context);
        this.b = cVar;
        setUpView(context);
    }

    private void setUpPlugins(Context context) {
        this.c.d();
        this.f = new g(context);
        this.c.a((com.facebook.ads.internal.view.g.a.b) this.f);
        this.d = new f(context);
        this.c.a((com.facebook.ads.internal.view.g.a.b) new com.facebook.ads.internal.view.g.c.b(context));
        this.c.a((com.facebook.ads.internal.view.g.a.b) this.d);
        this.e = new l(context, true);
        this.c.a((com.facebook.ads.internal.view.g.a.b) this.e);
        this.c.a((com.facebook.ads.internal.view.g.a.b) new d(this.e, a.FADE_OUT_ON_PLAY, true, true));
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(10);
        layoutParams.addRule(11);
        layoutParams.setMargins(a, a, a, a);
        this.d.setLayoutParams(layoutParams);
        this.c.addView(this.d);
    }

    private void setUpVideo(Context context) {
        this.c = new t(context);
        this.c.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        y.a(this.c);
        addView(this.c);
        setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                e.this.e.performClick();
            }
        });
    }

    private void setUpView(Context context) {
        setUpVideo(context);
        setUpPlugins(context);
    }

    public void a() {
        this.c.a(true);
    }

    public void a(com.facebook.ads.internal.l.f fVar) {
        this.c.getEventBus().a(fVar);
    }

    public void a(c cVar, String str, Map<String, String> map) {
        c();
        this.g = new b(getContext(), cVar, this.c, str, (Map) map);
    }

    public void a(com.facebook.ads.internal.view.g.a.a aVar) {
        this.c.a(aVar);
    }

    public boolean b() {
        return this.c.j();
    }

    public void c() {
        if (this.g != null) {
            this.g.a();
            this.g = null;
        }
    }

    @VisibleForTesting
    public com.facebook.ads.internal.view.g.a getSimpleVideoView() {
        return this.c;
    }

    public float getVolume() {
        return this.c.getVolume();
    }

    public void setPlaceholderUrl(String str) {
        this.f.setImage(str);
    }

    public void setVideoURI(String str) {
        this.c.setVideoURI(str);
    }

    public void setVolume(float f) {
        this.c.setVolume(f);
        this.d.a();
    }
}
