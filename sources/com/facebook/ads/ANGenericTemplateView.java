package com.facebook.ads;

import android.content.Context;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.p.a;
import com.facebook.ads.internal.p.j;
import com.facebook.ads.internal.p.k;

public class ANGenericTemplateView extends RelativeLayout {
    private final a a;

    public ANGenericTemplateView(Context context, NativeAdBase nativeAdBase, j jVar) {
        MediaView mediaView;
        k a;
        super(context);
        AdIconView adIconView = new AdIconView(getContext());
        if (nativeAdBase instanceof NativeAd) {
            MediaView mediaView2 = new MediaView(getContext());
            NativeAd nativeAd = (NativeAd) nativeAdBase;
            mediaView2.setNativeAd(nativeAd);
            adIconView.setNativeAd(nativeAd);
            mediaView = mediaView2;
            a = nativeAd.f().a();
        } else {
            NativeBannerAd nativeBannerAd = (NativeBannerAd) nativeAdBase;
            adIconView.setNativeBannerAd(nativeBannerAd);
            a = nativeBannerAd.a().a();
            mediaView = null;
        }
        this.a = new a(context, nativeAdBase.g(), this, new AdChoicesView(getContext(), nativeAdBase, true), mediaView, adIconView, a, jVar);
    }

    /* Access modifiers changed, original: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.a.a();
    }
}
