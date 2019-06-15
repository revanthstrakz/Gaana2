package com.til.colombia.android.adapters;

import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeAppInstallAd.OnAppInstallAdLoadedListener;
import com.til.colombia.android.service.AdListener;
import com.til.colombia.android.service.AdRequestParams;
import com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE;
import com.til.colombia.android.service.ItemResponse;
import com.til.colombia.android.service.bl;

final class e implements OnAppInstallAdLoadedListener {
    final /* synthetic */ bl a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ AdListener d;
    final /* synthetic */ GoogleAdsAdapter e;

    e(GoogleAdsAdapter googleAdsAdapter, bl blVar, String str, String str2, AdListener adListener) {
        this.e = googleAdsAdapter;
        this.a = blVar;
        this.b = str;
        this.c = str2;
        this.d = adListener;
    }

    public final void onAppInstallAdLoaded(NativeAppInstallAd nativeAppInstallAd) {
        AdRequestParams adRequestParams = new AdRequestParams();
        adRequestParams.setAdManager(this.a.getAdManager());
        ItemResponse itemResponse = new ItemResponse(adRequestParams, this.b);
        GoogleNativeAd googleNativeAd = new GoogleNativeAd(nativeAppInstallAd, ITEM_TYPE.APP);
        googleNativeAd.setItemResponse(itemResponse);
        itemResponse.setPaidItems(googleNativeAd);
        itemResponse.setRequestCode(this.c);
        this.e.onItemLoadedOnMainThread(this.d, this.a, itemResponse);
        this.e.createCache();
    }
}
