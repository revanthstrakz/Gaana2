package com.facebook.ads.internal.view.g;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.l.f;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.view.g.a.b;
import com.facebook.ads.internal.view.g.b.d;
import com.facebook.ads.internal.view.g.b.h;
import com.facebook.ads.internal.view.g.b.j;
import com.facebook.ads.internal.view.g.b.l;
import com.facebook.ads.internal.view.g.b.n;
import com.facebook.ads.internal.view.g.b.p;
import com.facebook.ads.internal.view.g.b.r;
import com.facebook.ads.internal.view.g.b.s;
import com.facebook.ads.internal.view.g.b.t;
import com.facebook.ads.internal.view.g.b.v;
import com.facebook.ads.internal.view.g.b.x;
import com.facebook.ads.internal.view.g.b.y;
import com.facebook.ads.internal.view.g.c.g;
import com.facebook.ads.internal.view.g.d.c;
import com.facebook.ads.internal.view.g.d.e;
import java.util.ArrayList;
import java.util.List;

public class a extends RelativeLayout implements com.facebook.ads.internal.view.g.c.a, e {
    private static final l b = new l();
    private static final d c = new d();
    private static final r d = new r();
    private static final h e = new h();
    private static final s f = new s();
    private static final j g = new j();
    private static final v h = new v();
    private static final y i = new y();
    private static final x j = new x();
    protected final c a;
    private d k;
    private final List<b> l = new ArrayList();
    private final Handler m = new Handler();
    private final Handler n = new Handler();
    private final com.facebook.ads.internal.l.e<f, com.facebook.ads.internal.l.d> o = new com.facebook.ads.internal.l.e();
    private boolean p;
    private boolean q;
    private boolean r = false;
    private int s = 200;
    private final OnTouchListener t = new OnTouchListener() {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            a.this.o.a(new t(view, motionEvent));
            return false;
        }
    };

    public a(Context context) {
        super(context);
        this.a = com.facebook.ads.internal.n.a.a(context) ? new com.facebook.ads.internal.view.g.d.a(context) : new com.facebook.ads.internal.view.g.d.b(context);
        a();
    }

    public a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = com.facebook.ads.internal.n.a.a(context) ? new com.facebook.ads.internal.view.g.d.a(context, attributeSet) : new com.facebook.ads.internal.view.g.d.b(context, attributeSet);
        a();
    }

    public a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = com.facebook.ads.internal.n.a.a(context) ? new com.facebook.ads.internal.view.g.d.a(context, attributeSet, i) : new com.facebook.ads.internal.view.g.d.b(context, attributeSet, i);
        a();
    }

    @TargetApi(21)
    public a(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.a = com.facebook.ads.internal.n.a.a(context) ? new com.facebook.ads.internal.view.g.d.a(context, attributeSet, i, i2) : new com.facebook.ads.internal.view.g.d.b(context, attributeSet, i, i2);
        a();
    }

    private void a() {
        if (h() && (this.a instanceof com.facebook.ads.internal.view.g.d.a)) {
            ((com.facebook.ads.internal.view.g.d.a) this.a).setTestMode(AdInternalSettings.isTestMode(getContext()));
        }
        this.a.setRequestedVolume(1.0f);
        this.a.setVideoStateChangeListener(this);
        this.k = new d(getContext(), this.a);
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.addRule(13);
        addView(this.k, layoutParams);
        setOnTouchListener(this.t);
    }

    private void a(com.facebook.ads.internal.view.g.a.c cVar) {
        if (cVar.getParent() == null) {
            if (cVar instanceof g) {
                this.k.a(cVar);
                return;
            }
            addView(cVar);
        }
    }

    private void b() {
        this.m.postDelayed(new Runnable() {
            public void run() {
                if (!a.this.p) {
                    a.this.o.a(new n(a.this.getCurrentPositionInMillis()));
                    a.this.m.postDelayed(this, (long) a.this.s);
                }
            }
        }, (long) this.s);
    }

    private void b(com.facebook.ads.internal.view.g.a.c cVar) {
        if (cVar instanceof g) {
            this.k.b(cVar);
        } else {
            com.facebook.ads.internal.s.a.y.b(cVar);
        }
    }

    public void a(int i) {
        this.m.removeCallbacksAndMessages(null);
        this.a.a(i);
    }

    public void a(final int i, final int i2) {
        this.n.post(new Runnable() {
            public void run() {
                a.this.o.a(new p(i, i2));
            }
        });
        b();
    }

    public void a(com.facebook.ads.internal.view.g.a.a aVar) {
        if (this.p && this.a.getState() == com.facebook.ads.internal.view.g.d.d.PLAYBACK_COMPLETED) {
            this.p = false;
        }
        this.a.a(aVar);
    }

    public void a(b bVar) {
        this.l.add(bVar);
    }

    public void a(final com.facebook.ads.internal.view.g.d.d dVar) {
        final int currentPositionInMillis = getCurrentPositionInMillis();
        final int duration = getDuration();
        this.n.post(new Runnable() {
            public void run() {
                com.facebook.ads.internal.l.e b;
                com.facebook.ads.internal.l.d o;
                if (dVar == com.facebook.ads.internal.view.g.d.d.PREPARED) {
                    b = a.this.o;
                    o = a.b;
                } else if (dVar == com.facebook.ads.internal.view.g.d.d.ERROR) {
                    a.this.p = true;
                    b = a.this.o;
                    o = a.c;
                } else if (dVar == com.facebook.ads.internal.view.g.d.d.PLAYBACK_COMPLETED) {
                    a.this.p = true;
                    a.this.m.removeCallbacksAndMessages(null);
                    b = a.this.o;
                    o = new com.facebook.ads.internal.view.g.b.b(currentPositionInMillis, duration);
                } else if (dVar == com.facebook.ads.internal.view.g.d.d.STARTED) {
                    a.this.o.a(a.g);
                    a.this.m.removeCallbacksAndMessages(null);
                    a.this.b();
                    return;
                } else {
                    if (dVar == com.facebook.ads.internal.view.g.d.d.PAUSED) {
                        b = a.this.o;
                        o = a.e;
                    } else if (dVar == com.facebook.ads.internal.view.g.d.d.IDLE) {
                        b = a.this.o;
                        o = a.f;
                    } else {
                        return;
                    }
                    b.a(o);
                    a.this.m.removeCallbacksAndMessages(null);
                    return;
                }
                b.a(o);
            }
        });
    }

    public void a(boolean z) {
        if (!m()) {
            this.a.a(z);
            this.r = z;
        }
    }

    public void c() {
        for (b bVar : this.l) {
            if (bVar instanceof com.facebook.ads.internal.view.g.a.c) {
                a((com.facebook.ads.internal.view.g.a.c) bVar);
            }
            bVar.a(this);
        }
    }

    public void d() {
        for (b bVar : this.l) {
            if (bVar instanceof com.facebook.ads.internal.view.g.a.c) {
                b((com.facebook.ads.internal.view.g.a.c) bVar);
            }
            bVar.b(this);
        }
    }

    public void e() {
        if (!m()) {
            this.a.a();
        }
    }

    public void f() {
        this.n.post(new Runnable() {
            public void run() {
                a.this.getEventBus().a(a.d);
            }
        });
        this.a.b();
    }

    public void g() {
        this.a.c();
    }

    public int getCurrentPositionInMillis() {
        return this.a.getCurrentPosition();
    }

    public int getDuration() {
        return this.a.getDuration();
    }

    @NonNull
    public com.facebook.ads.internal.l.e<f, com.facebook.ads.internal.l.d> getEventBus() {
        return this.o;
    }

    public long getInitialBufferTime() {
        return this.a.getInitialBufferTime();
    }

    public com.facebook.ads.internal.view.g.d.d getState() {
        return this.a.getState();
    }

    /* Access modifiers changed, original: protected */
    public Handler getStateHandler() {
        return this.n;
    }

    public TextureView getTextureView() {
        return (TextureView) this.a;
    }

    public int getVideoHeight() {
        return this.a.getVideoHeight();
    }

    public int getVideoProgressReportIntervalMs() {
        return this.s;
    }

    public com.facebook.ads.internal.view.g.a.a getVideoStartReason() {
        return this.a.getStartReason();
    }

    public View getVideoView() {
        return this.k;
    }

    public int getVideoWidth() {
        return this.a.getVideoWidth();
    }

    public float getVolume() {
        return this.a.getVolume();
    }

    public boolean h() {
        return com.facebook.ads.internal.n.a.a(getContext());
    }

    public boolean i() {
        return this.q;
    }

    public boolean j() {
        return getState() == com.facebook.ads.internal.view.g.d.d.STARTED;
    }

    public boolean k() {
        return this.a.d();
    }

    public void l() {
        this.a.setVideoStateChangeListener(null);
        this.a.e();
    }

    public boolean m() {
        return getState() == com.facebook.ads.internal.view.g.d.d.PAUSED;
    }

    public boolean n() {
        return m() && this.r;
    }

    /* Access modifiers changed, original: protected */
    public void onAttachedToWindow() {
        this.o.a(j);
        super.onAttachedToWindow();
    }

    /* Access modifiers changed, original: protected */
    public void onDetachedFromWindow() {
        this.o.a(i);
        super.onDetachedFromWindow();
    }

    public void setControlsAnchorView(View view) {
        if (this.a != null) {
            this.a.setControlsAnchorView(view);
        }
    }

    public void setIsFullScreen(boolean z) {
        this.q = z;
        this.a.setFullScreen(z);
    }

    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        super.setLayoutParams(layoutParams);
    }

    public void setVideoMPD(@Nullable String str) {
        this.a.setVideoMPD(str);
    }

    public void setVideoProgressReportIntervalMs(int i) {
        this.s = i;
    }

    public void setVideoURI(@Nullable Uri uri) {
        if (uri == null) {
            d();
        } else {
            c();
            this.a.setup(uri);
        }
        this.p = false;
    }

    public void setVideoURI(@Nullable String str) {
        setVideoURI(str != null ? Uri.parse(str) : null);
    }

    public void setVolume(float f) {
        this.a.setRequestedVolume(f);
        getEventBus().a(h);
    }
}
