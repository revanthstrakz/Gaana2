package com.til.colombia.android.service;

import android.view.View;
import android.view.View.OnClickListener;

final class bh implements OnClickListener {
    final /* synthetic */ ColombiaNativeVideoAdView a;

    bh(ColombiaNativeVideoAdView colombiaNativeVideoAdView) {
        this.a = colombiaNativeVideoAdView;
    }

    public final void onClick(View view) {
        boolean onClick = ((NativeItem) this.a.nativeAd).onClick();
        bi.a();
        bi.a(this.a.nativeAd, onClick);
    }
}
