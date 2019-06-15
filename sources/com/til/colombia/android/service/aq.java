package com.til.colombia.android.service;

import android.view.View;
import android.view.View.OnClickListener;
import com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE;

final class aq implements OnClickListener {
    final /* synthetic */ ColombiaNativeAdView a;

    aq(ColombiaNativeAdView colombiaNativeAdView) {
        this.a = colombiaNativeAdView;
    }

    public final void onClick(View view) {
        boolean onClick;
        if (this.a.nativeAd.getItemType() == ITEM_TYPE.PRODUCT || this.a.nativeAd.getItemType() == ITEM_TYPE.CONTENT || this.a.nativeAd.getItemType() == ITEM_TYPE.APP || this.a.nativeAd.getItemType() == ITEM_TYPE.VIDEO || this.a.nativeAd.getItemType() == ITEM_TYPE.BANNER) {
            onClick = ((NativeItem) this.a.nativeAd).onClick();
        } else {
            onClick = false;
        }
        bi.a();
        bi.a(this.a.nativeAd, onClick);
    }
}
