package com.til.colombia.android.service;

import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import com.til.colombia.android.network.n;

final class ap implements OnClickListener {
    final /* synthetic */ ColombiaNativeAdView a;

    ap(ColombiaNativeAdView colombiaNativeAdView) {
        this.a = colombiaNativeAdView;
    }

    public final void onClick(View view) {
        try {
            n.a(this.a.context, Uri.parse(((NativeItem) this.a.nativeAd).getAdChoiceClickUrl()));
        } catch (Exception unused) {
        }
    }
}
