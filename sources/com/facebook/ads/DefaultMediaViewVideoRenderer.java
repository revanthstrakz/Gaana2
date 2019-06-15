package com.facebook.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.facebook.ads.internal.p.c;
import com.facebook.ads.internal.p.e;
import java.lang.ref.WeakReference;

public final class DefaultMediaViewVideoRenderer extends MediaViewVideoRenderer {
    @Nullable
    private c d;

    private static class a implements com.facebook.ads.internal.p.c.a {
        private WeakReference<e> a;

        a(e eVar) {
            this.a = new WeakReference(eVar);
        }

        public void a(boolean z) {
            if (this.a.get() != null) {
                ((e) this.a.get()).a(z, false);
            }
        }
    }

    public DefaultMediaViewVideoRenderer(Context context) {
        super(context);
        this.d = new c(context, this);
        b();
    }

    public DefaultMediaViewVideoRenderer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = new c(context, this);
        b();
    }

    public DefaultMediaViewVideoRenderer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = new c(context, this);
        b();
    }

    @TargetApi(21)
    public DefaultMediaViewVideoRenderer(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.d = new c(context, this);
        b();
    }

    private void b() {
        setVolume(0.0f);
    }

    /* Access modifiers changed, original: protected */
    public void a() {
        super.a();
        if (this.d != null) {
            this.d.a();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.d != null) {
            this.d.c();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onDetachedFromWindow() {
        if (this.d != null) {
            this.d.d();
        }
        super.onDetachedFromWindow();
    }

    public void onPrepared() {
        super.onPrepared();
        setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        if (this.d != null) {
            this.d.b();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (this.d != null) {
            this.d.e();
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.d != null) {
            this.d.f();
        }
    }

    /* Access modifiers changed, original: protected */
    public void setNativeAd(NativeAd nativeAd) {
        super.setNativeAd(nativeAd);
        if (this.d != null) {
            this.d.a(nativeAd.g(), new a(nativeAd.g()));
        }
    }
}
