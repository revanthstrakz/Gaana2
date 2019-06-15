package com.til.colombia.android.service;

import android.view.View;
import android.view.View.OnClickListener;

final class ba implements OnClickListener {
    final /* synthetic */ ColombiaNativeLeadAdView a;

    ba(ColombiaNativeLeadAdView colombiaNativeLeadAdView) {
        this.a = colombiaNativeLeadAdView;
    }

    public final void onClick(View view) {
        bi.a();
        bi.a(this.a.nativeAd, false);
    }
}
