package com.til.colombia.android.adapters;

import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeAppInstallAd.OnAppInstallAdLoadedListener;
import com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE;

final class h implements OnAppInstallAdLoadedListener {
    final /* synthetic */ GoogleAdsAdapter a;

    h(GoogleAdsAdapter googleAdsAdapter) {
        this.a = googleAdsAdapter;
    }

    public final void onAppInstallAdLoaded(NativeAppInstallAd nativeAppInstallAd) {
        this.a.googleNativeAd = new GoogleNativeAd(nativeAppInstallAd, ITEM_TYPE.APP);
        this.a.cacheTimeStamp = System.currentTimeMillis() / 1000;
        this.a.requestCache = false;
    }
}
