package com.facebook.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.facebook.ads.internal.view.g.b.b;
import com.facebook.ads.internal.view.g.b.c;
import com.facebook.ads.internal.view.g.b.d;
import com.facebook.ads.internal.view.g.b.e;
import com.facebook.ads.internal.view.g.b.h;
import com.facebook.ads.internal.view.g.b.i;
import com.facebook.ads.internal.view.g.b.k;
import com.facebook.ads.internal.view.g.b.l;
import com.facebook.ads.internal.view.g.b.m;
import com.facebook.ads.internal.view.g.b.p;
import com.facebook.ads.internal.view.g.b.q;
import com.facebook.ads.internal.view.g.b.v;
import com.facebook.ads.internal.view.g.b.w;
import com.facebook.ads.internal.view.j;

public abstract class MediaViewVideoRenderer extends FrameLayout {
    private static final String d = "MediaViewVideoRenderer";
    @Nullable
    protected NativeAd a;
    protected VideoAutoplayBehavior b;
    final j c;
    private final m e = new m() {
        public void a(l lVar) {
            MediaViewVideoRenderer.this.onPrepared();
        }
    };
    private final k f = new k() {
        public void a(com.facebook.ads.internal.view.g.b.j jVar) {
            if (MediaViewVideoRenderer.this.a != null) {
                MediaViewVideoRenderer.this.a.g().a(true, true);
            }
            MediaViewVideoRenderer.this.onPlayed();
        }
    };
    private final i g = new i() {
        public void a(h hVar) {
            MediaViewVideoRenderer.this.onPaused();
        }
    };
    private final q h = new q() {
        public void a(p pVar) {
            MediaViewVideoRenderer.this.onSeek();
        }
    };
    private final c i = new c() {
        public void a(b bVar) {
            MediaViewVideoRenderer.this.onCompleted();
        }
    };
    private final w j = new w() {
        public void a(v vVar) {
            MediaViewVideoRenderer.this.onVolumeChanged();
        }
    };
    private final e k = new e() {
        public void a(d dVar) {
            if (MediaViewVideoRenderer.this.a != null) {
                MediaViewVideoRenderer.this.a.g().a(false, true);
            }
            MediaViewVideoRenderer.this.onError();
        }
    };
    private boolean l;
    private boolean m;

    public MediaViewVideoRenderer(Context context) {
        super(context);
        this.c = new j(context);
        b();
    }

    public MediaViewVideoRenderer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = new j(context, attributeSet);
        b();
    }

    public MediaViewVideoRenderer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new j(context, attributeSet, i);
        b();
    }

    @TargetApi(21)
    public MediaViewVideoRenderer(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.c = new j(context, attributeSet, i, i2);
        b();
    }

    private void b() {
        this.c.setEnableBackgroundVideo(shouldAllowBackgroundPlayback());
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        this.c.setLayoutParams(layoutParams);
        super.addView(this.c, -1, layoutParams);
        com.facebook.ads.internal.s.a.j.a(this.c, com.facebook.ads.internal.s.a.j.INTERNAL_AD_MEDIA);
        this.c.getEventBus().a(this.e, this.f, this.g, this.h, this.i, this.j, this.k);
    }

    /* Access modifiers changed, original: protected */
    public void a() {
        pause(false);
        this.c.a(null, null);
        this.c.setVideoMPD(null);
        this.c.setVideoURI((Uri) null);
        this.c.setVideoCTA(null);
        this.c.setNativeAd(null);
        this.b = VideoAutoplayBehavior.DEFAULT;
        if (this.a != null) {
            this.a.g().a(false, false);
        }
        this.a = null;
    }

    public void addView(View view) {
    }

    public void addView(View view, int i) {
    }

    public void addView(View view, int i, int i2) {
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
    }

    public void addView(View view, LayoutParams layoutParams) {
    }

    public void destroy() {
        this.c.l();
    }

    public final void disengageSeek(VideoStartReason videoStartReason) {
        if (this.l) {
            this.l = false;
            if (this.m) {
                this.c.a(videoStartReason.a());
            }
            onSeekDisengaged();
            return;
        }
        Log.w(d, "disengageSeek called without engageSeek.");
    }

    public final void engageSeek() {
        if (this.l) {
            Log.w(d, "engageSeek called without disengageSeek.");
            return;
        }
        this.l = true;
        this.m = com.facebook.ads.internal.view.g.d.d.STARTED.equals(this.c.getState());
        this.c.a(false);
        onSeekEngaged();
    }

    @IntRange(from = 0)
    public final int getCurrentTimeMs() {
        return this.c.getCurrentPositionInMillis();
    }

    @IntRange(from = 0)
    public final int getDuration() {
        return this.c.getDuration();
    }

    /* Access modifiers changed, original: final */
    public final View getVideoView() {
        return this.c.getVideoView();
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public final float getVolume() {
        return this.c.getVolume();
    }

    public void onCompleted() {
    }

    public void onError() {
    }

    public void onPaused() {
    }

    public void onPlayed() {
    }

    public void onPrepared() {
    }

    public void onSeek() {
    }

    public void onSeekDisengaged() {
    }

    public void onSeekEngaged() {
    }

    public void onVolumeChanged() {
    }

    public final void pause(boolean z) {
        this.c.a(z);
    }

    public final void play(VideoStartReason videoStartReason) {
        this.c.a(videoStartReason.a());
    }

    public final void seekTo(@IntRange(from = 0) int i) {
        if (this.l) {
            this.c.a(i);
        } else {
            Log.w(d, "Seeking must be preceded by a call to engageSeek, and followed by a call to disengageSeek.");
        }
    }

    /* Access modifiers changed, original: final */
    public final void setAdEventManager(com.facebook.ads.internal.o.c cVar) {
        this.c.setAdEventManager(cVar);
    }

    /* Access modifiers changed, original: final */
    public final void setListener(com.facebook.ads.internal.view.k kVar) {
        this.c.setListener(kVar);
    }

    /* Access modifiers changed, original: protected */
    public void setNativeAd(NativeAd nativeAd) {
        this.a = nativeAd;
        this.c.a(nativeAd.c(), nativeAd.i());
        this.c.setVideoMPD(nativeAd.b());
        this.c.setVideoURI(nativeAd.a());
        this.c.setVideoProgressReportIntervalMs(nativeAd.h().G());
        this.c.setVideoCTA(nativeAd.getAdCallToAction());
        this.c.setNativeAd(nativeAd);
        this.b = nativeAd.d();
    }

    public final void setVolume(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        this.c.setVolume(f);
    }

    public boolean shouldAllowBackgroundPlayback() {
        return false;
    }

    public final boolean shouldAutoplay() {
        boolean z = false;
        if (this.c != null) {
            if (this.c.getState() == com.facebook.ads.internal.view.g.d.d.PLAYBACK_COMPLETED) {
                return false;
            }
            if (this.b == VideoAutoplayBehavior.ON) {
                z = true;
            }
        }
        return z;
    }
}
