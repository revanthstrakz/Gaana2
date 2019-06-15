package com.til.colombia.android.adapters;

import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAd.OnContentAdLoadedListener;
import com.til.colombia.android.service.AdListener;
import com.til.colombia.android.service.AdRequestParams;
import com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE;
import com.til.colombia.android.service.ItemResponse;
import com.til.colombia.android.service.bl;

final class d implements OnContentAdLoadedListener {
    final /* synthetic */ bl a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ AdListener d;
    final /* synthetic */ GoogleAdsAdapter e;

    d(GoogleAdsAdapter googleAdsAdapter, bl blVar, String str, String str2, AdListener adListener) {
        this.e = googleAdsAdapter;
        this.a = blVar;
        this.b = str;
        this.c = str2;
        this.d = adListener;
    }

    public final void onContentAdLoaded(NativeContentAd nativeContentAd) {
        AdRequestParams adRequestParams = new AdRequestParams();
        adRequestParams.setAdManager(this.a.getAdManager());
        ItemResponse itemResponse = new ItemResponse(adRequestParams, this.b);
        GoogleNativeAd googleNativeAd = new GoogleNativeAd(nativeContentAd, ITEM_TYPE.CONTENT);
        googleNativeAd.setItemResponse(itemResponse);
        itemResponse.setPaidItems(googleNativeAd);
        itemResponse.setRequestCode(this.c);
        this.e.onItemLoadedOnMainThread(this.d, this.a, itemResponse);
        this.e.createCache();
    }
}
