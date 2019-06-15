package com.facebook.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.ads.NativeBannerAdView.Type;
import com.facebook.ads.internal.p.f;
import com.facebook.ads.internal.protocol.e;
import java.util.List;

public class NativeBannerAd extends NativeAdBase {
    public NativeBannerAd(Context context, String str) {
        super(context, str);
        a(e.NATIVE_BANNER);
    }

    /* Access modifiers changed, original: 0000 */
    public Type a() {
        return g().H() == null ? null : Type.a(g().H());
    }

    /* Access modifiers changed, original: 0000 */
    public void a(Type type) {
        g().a(type.a());
    }

    public void registerViewForInteraction(View view, AdIconView adIconView) {
        registerViewForInteraction(view, adIconView, null);
    }

    public void registerViewForInteraction(View view, AdIconView adIconView, @Nullable List<View> list) {
        if (adIconView != null) {
            adIconView.setNativeBannerAd(this);
        }
        if (list != null) {
            g().a(view, (f) adIconView, (List) list);
        } else {
            g().a(view, (f) adIconView);
        }
    }
}
