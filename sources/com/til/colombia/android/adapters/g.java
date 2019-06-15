package com.til.colombia.android.adapters;

import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAd.OnContentAdLoadedListener;
import com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE;

final class g implements OnContentAdLoadedListener {
    final /* synthetic */ GoogleAdsAdapter a;

    g(GoogleAdsAdapter googleAdsAdapter) {
        this.a = googleAdsAdapter;
    }

    public final void onContentAdLoaded(NativeContentAd nativeContentAd) {
        this.a.googleNativeAd = new GoogleNativeAd(nativeContentAd, ITEM_TYPE.CONTENT);
        this.a.cacheTimeStamp = System.currentTimeMillis() / 1000;
        this.a.requestCache = false;
    }
}
