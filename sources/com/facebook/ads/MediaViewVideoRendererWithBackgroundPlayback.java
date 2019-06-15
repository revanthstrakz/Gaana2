package com.facebook.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.facebook.ads.internal.p.c;

public final class MediaViewVideoRendererWithBackgroundPlayback extends MediaViewVideoRenderer {
    private c d;

    public MediaViewVideoRendererWithBackgroundPlayback(Context context) {
        super(context);
        this.d = new c(context, this);
        b();
    }

    public MediaViewVideoRendererWithBackgroundPlayback(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = new c(context, this);
        b();
    }

    public MediaViewVideoRendererWithBackgroundPlayback(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = new c(context, this);
        b();
    }

    @TargetApi(21)
    public MediaViewVideoRendererWithBackgroundPlayback(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.d = new c(context, this);
        b();
    }

    private void b() {
        setVolume(1.0f);
    }

    /* Access modifiers changed, original: protected */
    public void a() {
        super.a();
        this.d.a();
    }

    /* Access modifiers changed, original: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.d.c();
    }

    /* Access modifiers changed, original: protected */
    public void onDetachedFromWindow() {
        this.d.d();
        super.onDetachedFromWindow();
    }

    public void onPrepared() {
        super.onPrepared();
        setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        this.d.b();
    }

    /* Access modifiers changed, original: protected */
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        this.d.e();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        this.d.f();
    }

    /* Access modifiers changed, original: protected */
    public void setNativeAd(NativeAd nativeAd) {
        super.setNativeAd(nativeAd);
        this.d.a(nativeAd.g());
    }

    public boolean shouldAllowBackgroundPlayback() {
        return true;
    }
}
