package com.til.colombia.android.service;

import android.view.View;
import android.view.View.OnClickListener;

final class az implements OnClickListener {
    final /* synthetic */ ColombiaNativeGeneralAdView a;

    az(ColombiaNativeGeneralAdView colombiaNativeGeneralAdView) {
        this.a = colombiaNativeGeneralAdView;
    }

    public final void onClick(View view) {
        bi.a();
        bi.a(this.a.nativeAd, false);
    }
}
