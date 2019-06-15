package com.til.colombia.android.service;

import android.graphics.Bitmap;
import com.til.colombia.android.service.ColombiaAdManager.AD_NTWK;

public abstract class Item extends CmItem {
    public void destroy() {
    }

    @Deprecated
    public abstract String getAdAttributionText();

    @Deprecated
    public abstract String getAdAttributionUrl();

    @Deprecated
    public String getAdDeepLink() {
        return null;
    }

    public abstract int getAdImgHeight();

    public abstract int getAdImgWidth();

    public abstract AD_NTWK getAdNetwork();

    @Deprecated
    public String getAdUrl() {
        return null;
    }

    @Deprecated
    public abstract String getBodyText();

    @Deprecated
    public abstract String getBrandText();

    public String getCurrency() {
        return null;
    }

    public Integer getDuration() {
        return null;
    }

    @Deprecated
    public abstract Bitmap getIcon();

    @Deprecated
    public abstract Bitmap getImage();

    public abstract Integer getLineItemId();

    public String getMRP() {
        return null;
    }

    public int getMediaAdHeight() {
        return 0;
    }

    public int getMediaAdWidth() {
        return 0;
    }

    public String getOfferCode() {
        return null;
    }

    public String getOfferText() {
        return null;
    }

    public abstract String getPrice();

    public boolean hasFollowUp() {
        return false;
    }

    public abstract boolean isImpressed();

    public boolean isSOVItem() {
        return false;
    }

    @Deprecated
    public boolean returnItemUrl() {
        return false;
    }

    public void show() {
    }

    public abstract Object thirdPartyAd();

    public Long getReviewsCount() {
        return Long.valueOf(0);
    }
}
