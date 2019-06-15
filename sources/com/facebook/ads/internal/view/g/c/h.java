package com.facebook.ads.internal.view.g.c;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.view.g.a;
import com.facebook.ads.internal.view.g.a.c;
import com.facebook.ads.internal.view.g.b.b;
import com.facebook.ads.internal.view.g.b.i;
import com.facebook.ads.internal.view.g.b.j;
import com.facebook.ads.internal.view.g.b.k;
import com.facebook.ads.internal.view.g.b.l;
import com.facebook.ads.internal.view.g.b.m;
import com.facebook.ads.internal.view.g.d.d;

public class h extends c implements OnTouchListener {
    private final m a;
    private final i b;
    private final k c;
    private final com.facebook.ads.internal.view.g.b.c d;
    private final m e;

    public h(Context context) {
        this(context, null);
    }

    public h(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public h(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new m() {
            public void a(l lVar) {
                h.this.setVisibility(0);
            }
        };
        this.b = new i() {
            public void a(com.facebook.ads.internal.view.g.b.h hVar) {
                h.this.e.setChecked(true);
            }
        };
        this.c = new k() {
            public void a(j jVar) {
                h.this.e.setChecked(false);
            }
        };
        this.d = new com.facebook.ads.internal.view.g.b.c() {
            public void a(b bVar) {
                h.this.e.setChecked(true);
            }
        };
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.e = new m(context);
        this.e.setChecked(true);
        LayoutParams layoutParams = new LayoutParams((int) (displayMetrics.density * 25.0f), (int) (25.0f * displayMetrics.density));
        setVisibility(8);
        addView(this.e, layoutParams);
        setClickable(true);
        setFocusable(true);
    }

    /* Access modifiers changed, original: protected */
    public void a() {
        super.a();
        this.e.setOnTouchListener(this);
        setOnTouchListener(this);
        if (getVideoView() != null) {
            getVideoView().getEventBus().a(this.a, this.d, this.b, this.c);
        }
    }

    /* Access modifiers changed, original: protected */
    public void b() {
        if (getVideoView() != null) {
            getVideoView().getEventBus().b(this.c, this.b, this.d, this.a);
        }
        setOnTouchListener(null);
        this.e.setOnTouchListener(null);
        super.b();
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 1) {
            return false;
        }
        a videoView = getVideoView();
        if (videoView == null) {
            return false;
        }
        if (videoView.getState() == d.PREPARED || videoView.getState() == d.PAUSED || videoView.getState() == d.PLAYBACK_COMPLETED) {
            videoView.a(com.facebook.ads.internal.view.g.a.a.USER_STARTED);
            return true;
        }
        if (videoView.getState() == d.STARTED) {
            videoView.a(true);
        }
        return false;
    }
}
