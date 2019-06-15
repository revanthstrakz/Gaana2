package com.til.colombia.android.adapters;

import com.google.android.gms.ads.AdListener;
import com.til.colombia.android.internal.a;

final class i extends AdListener {
    final /* synthetic */ GoogleAdsAdapter a;

    i(GoogleAdsAdapter googleAdsAdapter) {
        this.a = googleAdsAdapter;
    }

    public final void onAdFailedToLoad(int i) {
        this.a.requestCache = false;
        a.k();
    }
}
