package com.facebook.ads;

import android.content.Context;
import android.view.View;
import com.facebook.ads.internal.p.k;
import com.facebook.ads.internal.s.a.l;

public class NativeBannerAdView {

    public enum Type {
        HEIGHT_100(k.HEIGHT_100),
        HEIGHT_120(k.HEIGHT_120);
        
        private final k a;

        private Type(k kVar) {
            this.a = kVar;
        }

        static Type a(k kVar) {
            return kVar == k.HEIGHT_100 ? HEIGHT_100 : kVar == k.HEIGHT_120 ? HEIGHT_120 : null;
        }

        /* Access modifiers changed, original: 0000 */
        public k a() {
            return this.a;
        }

        public int getHeight() {
            return this.a.b();
        }

        public int getValue() {
            return this.a.b();
        }

        public int getWidth() {
            return this.a.a();
        }
    }

    public static View render(Context context, NativeBannerAd nativeBannerAd, Type type) {
        return render(context, nativeBannerAd, type, null);
    }

    public static View render(Context context, NativeBannerAd nativeBannerAd, Type type, NativeAdViewAttributes nativeAdViewAttributes) {
        l.a(context, "context must be not null");
        l.a(nativeBannerAd, "nativeBannerAd must be not null");
        l.a(type, "type must be not null");
        if (nativeBannerAd.isNativeConfigEnabled()) {
            nativeAdViewAttributes = nativeBannerAd.getAdViewAttributes();
        }
        if (nativeAdViewAttributes == null) {
            nativeAdViewAttributes = new NativeAdViewAttributes();
        }
        nativeBannerAd.a(type);
        return new ANGenericTemplateView(context, nativeBannerAd, nativeAdViewAttributes.a());
    }
}
