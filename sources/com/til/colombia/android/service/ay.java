package com.til.colombia.android.service;

import android.view.View;
import android.view.View.OnClickListener;

final class ay implements OnClickListener {
    final /* synthetic */ ColombiaNativeBannerAdView a;

    ay(ColombiaNativeBannerAdView colombiaNativeBannerAdView) {
        this.a = colombiaNativeBannerAdView;
    }

    public final void onClick(View view) {
        boolean onClick = ((NativeItem) this.a.nativeAd).onClick();
        bi.a();
        bi.a(this.a.nativeAd, onClick);
    }
}
