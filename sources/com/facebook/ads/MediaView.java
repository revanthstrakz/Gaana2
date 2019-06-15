package com.facebook.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.adapters.e;
import com.facebook.ads.internal.adapters.e.a;
import com.facebook.ads.internal.o.c;
import com.facebook.ads.internal.o.d;
import com.facebook.ads.internal.p.f;
import com.facebook.ads.internal.s.a.j;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.view.c.b;
import com.facebook.ads.internal.view.k;

public class MediaView extends f {
    private static final String a = "MediaView";
    private static final int b = Color.argb(51, 145, 150, 165);
    private b c;
    private com.facebook.ads.internal.view.hscroll.b d;
    private MediaViewVideoRenderer e;
    private View f;
    @Nullable
    private MediaViewListener g;
    private boolean h;
    private boolean i;
    private boolean j;

    public MediaView(Context context) {
        super(context);
        setImageRenderer(new b(context));
        setCarouselRenderer(new com.facebook.ads.internal.view.hscroll.b(context));
        setVideoRenderer(new DefaultMediaViewVideoRenderer(context));
        a();
    }

    public MediaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setImageRenderer(new b(context, attributeSet));
        setCarouselRenderer(new com.facebook.ads.internal.view.hscroll.b(context, attributeSet));
        setVideoRenderer(new DefaultMediaViewVideoRenderer(context, attributeSet));
        a();
    }

    public MediaView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setImageRenderer(new b(context, attributeSet, i));
        setCarouselRenderer(new com.facebook.ads.internal.view.hscroll.b(context, attributeSet, i));
        setVideoRenderer(new DefaultMediaViewVideoRenderer(context, attributeSet, i));
        a();
    }

    @TargetApi(21)
    public MediaView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        setImageRenderer(new b(context, attributeSet, i, i2));
        setCarouselRenderer(new com.facebook.ads.internal.view.hscroll.b(context, attributeSet, i));
        setVideoRenderer(new DefaultMediaViewVideoRenderer(context, attributeSet, i, i2));
        a();
    }

    private void a() {
        y.a((View) this, b);
        j.a(this, j.INTERNAL_AD_MEDIA);
        j.a(this.c, j.INTERNAL_AD_MEDIA);
        j.a(this.e, j.INTERNAL_AD_MEDIA);
        j.a(this.d, j.INTERNAL_AD_MEDIA);
        this.i = true;
    }

    private boolean a(NativeAd nativeAd) {
        return VERSION.SDK_INT >= 14 && !TextUtils.isEmpty(nativeAd.a());
    }

    private boolean b(NativeAd nativeAd) {
        if (nativeAd.e() == null) {
            return false;
        }
        for (NativeAd adCoverImage : nativeAd.e()) {
            if (adCoverImage.getAdCoverImage() == null) {
                return false;
            }
        }
        return true;
    }

    private void setCarouselRenderer(com.facebook.ads.internal.view.hscroll.b bVar) {
        if (this.h) {
            throw new IllegalStateException("Carousel renderer must be set before nativeAd.");
        }
        if (this.d != null) {
            removeView(this.d);
        }
        float f = y.b;
        int round = Math.round(4.0f * f);
        int round2 = Math.round(f * 12.0f);
        bVar.setChildSpacing(round);
        bVar.setPadding(0, round2, 0, round2);
        bVar.setVisibility(8);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        addView((View) bVar, layoutParams);
        this.d = bVar;
    }

    private void setImageRenderer(b bVar) {
        if (this.h) {
            throw new IllegalStateException("Image renderer must be set before nativeAd.");
        }
        if (this.c != null) {
            removeView(this.c);
        }
        bVar.setVisibility(8);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        addView((View) bVar, layoutParams);
        this.c = bVar;
    }

    /* Access modifiers changed, original: 0000 */
    @VisibleForTesting
    public void a(View view, LayoutParams layoutParams) {
        this.i = false;
        addView(view, layoutParams);
        this.i = true;
    }

    public void addView(View view) {
        if (!this.i) {
            super.addView(view);
        }
    }

    public void addView(View view, int i) {
        if (!this.i) {
            super.addView(view, i);
        }
    }

    public void addView(View view, int i, int i2) {
        if (!this.i) {
            super.addView(view, i, i2);
        }
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
        if (!this.i) {
            super.addView(view, i, layoutParams);
        }
    }

    public void addView(View view, LayoutParams layoutParams) {
        if (!this.i) {
            super.addView(view, layoutParams);
        }
    }

    public void bringChildToFront(View view) {
        if (view == this.d || view == this.e || view == this.c) {
            super.bringChildToFront(view);
        }
    }

    public void destroy() {
        this.e.pause(false);
        this.e.destroy();
    }

    /* Access modifiers changed, original: protected */
    public View getAdContentsView() {
        return this.f;
    }

    /* Access modifiers changed, original: protected */
    public c getAdEventManager() {
        return d.a(getContext());
    }

    public void setListener(final MediaViewListener mediaViewListener) {
        this.g = mediaViewListener;
        if (mediaViewListener == null) {
            this.e.setListener(null);
        } else {
            this.e.setListener(new k() {
                public void a() {
                    mediaViewListener.onVolumeChange(MediaView.this, MediaView.this.e.getVolume());
                }

                public void b() {
                    mediaViewListener.onPause(MediaView.this);
                }

                public void c() {
                    mediaViewListener.onPlay(MediaView.this);
                }

                public void d() {
                    mediaViewListener.onFullscreenBackground(MediaView.this);
                }

                public void e() {
                    mediaViewListener.onFullscreenForeground(MediaView.this);
                }

                public void f() {
                    mediaViewListener.onExitFullscreen(MediaView.this);
                }

                public void g() {
                    mediaViewListener.onEnterFullscreen(MediaView.this);
                }

                public void h() {
                    mediaViewListener.onComplete(MediaView.this);
                }
            });
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void setNativeAd(final NativeAd nativeAd) {
        this.h = true;
        nativeAd.a(this);
        if (b(nativeAd)) {
            this.f = this.d;
            this.c.setVisibility(8);
            this.c.a(null, null);
            this.e.setVisibility(8);
            this.e.a();
            bringChildToFront(this.d);
            this.d.setCurrentPosition(0);
            e eVar = new e(this.d, nativeAd.g().E());
            eVar.a(new a() {
                public void a() {
                    nativeAd.g().a(true, true);
                }
            });
            this.d.setAdapter(eVar);
            this.d.setVisibility(0);
        } else if (a(nativeAd)) {
            nativeAd.g().b(this.j);
            this.f = this.e.getVideoView();
            this.c.setVisibility(8);
            this.c.a(null, null);
            this.d.setVisibility(8);
            this.d.setAdapter(null);
            bringChildToFront(this.e);
            this.e.setNativeAd(nativeAd);
            this.e.setVisibility(0);
        } else {
            if (nativeAd.getAdCoverImage() != null) {
                this.f = this.c.getBodyImageView();
                this.e.setVisibility(8);
                this.e.a();
                this.d.setVisibility(8);
                this.d.setAdapter(null);
                bringChildToFront(this.c);
                this.c.setVisibility(0);
                new com.facebook.ads.internal.view.c.d(this.c).a(getHeight(), getWidth()).a(com.facebook.ads.internal.n.a.f(getContext())).a(new com.facebook.ads.internal.view.c.e() {
                    public void a(boolean z) {
                        nativeAd.g().a(z, true);
                    }
                }).a(nativeAd.g().j().a());
            }
        }
    }

    public void setVideoRenderer(MediaViewVideoRenderer mediaViewVideoRenderer) {
        if (this.h) {
            throw new IllegalStateException("Video renderer must be set before nativeAd.");
        }
        if (this.e != null) {
            removeView(this.e);
            this.e.destroy();
        }
        mediaViewVideoRenderer.setAdEventManager(getAdEventManager());
        mediaViewVideoRenderer.setVisibility(8);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        a(mediaViewVideoRenderer, layoutParams);
        this.e = mediaViewVideoRenderer;
        this.j = !(this.e instanceof DefaultMediaViewVideoRenderer);
    }
}
