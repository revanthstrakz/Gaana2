package com.facebook.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.p.f;
import com.facebook.ads.internal.p.g;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.s.d.a;
import com.facebook.ads.internal.s.d.b;
import com.facebook.ads.internal.view.c.d;
import com.facebook.ads.internal.view.c.e;

public class AdIconView extends f {
    @Nullable
    private ImageView a;
    private boolean b;

    public AdIconView(Context context) {
        super(context);
        a();
    }

    public AdIconView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public AdIconView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    @TargetApi(21)
    public AdIconView(Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a();
    }

    private void a() {
        y.b(this.a);
        this.a = new ImageView(getContext());
        this.a.setLayoutParams(new LayoutParams(-1, -1));
        addView(this.a);
        this.b = true;
    }

    private void a(final NativeAdBase nativeAdBase, boolean z) {
        bringChildToFront(this.a);
        nativeAdBase.a(this);
        d a = new d(this.a).a();
        if (z) {
            a.a(new e() {
                public void a(boolean z) {
                    nativeAdBase.g().a(z, true);
                }
            });
        }
        g i = nativeAdBase.g().i();
        if (i != null) {
            a.a(i.a());
            return;
        }
        if (z) {
            nativeAdBase.g().a(false, true);
        }
        int i2 = b.h;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Native Ad Icon is null. Loaded: ");
        stringBuilder.append(nativeAdBase.g().f());
        a.a(getContext(), "api", i2, new Exception(stringBuilder.toString()));
    }

    public void addView(View view) {
        if (!this.b) {
            super.addView(view);
        }
    }

    public void addView(View view, int i) {
        if (!this.b) {
            super.addView(view, i);
        }
    }

    public void addView(View view, int i, int i2) {
        if (!this.b) {
            super.addView(view, i, i2);
        }
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
    }

    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        if (!this.b) {
            super.addView(view, layoutParams);
        }
    }

    public void bringChildToFront(View view) {
        if (view == this.a) {
            super.bringChildToFront(view);
        }
    }

    @VisibleForTesting
    public void forceAddview(View view, ViewGroup.LayoutParams layoutParams) {
        this.b = false;
        addView(view, layoutParams);
        this.b = true;
    }

    /* Access modifiers changed, original: protected */
    public View getAdContentsView() {
        return this.a;
    }

    /* Access modifiers changed, original: 0000 */
    public void setNativeAd(NativeAd nativeAd) {
        a(nativeAd, false);
    }

    /* Access modifiers changed, original: 0000 */
    public void setNativeBannerAd(NativeBannerAd nativeBannerAd) {
        a(nativeBannerAd, true);
    }
}
