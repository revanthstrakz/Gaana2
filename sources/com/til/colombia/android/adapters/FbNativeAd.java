package com.til.colombia.android.adapters;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import com.facebook.ads.NativeAd;
import com.til.colombia.android.service.ColombiaAdManager.AD_NTWK;
import com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE;
import com.til.colombia.android.service.Item;
import com.til.colombia.android.service.ItemResponse;

public class FbNativeAd extends Item {
    private NativeAd ad;
    private transient Bitmap iconBitmap;
    private transient Bitmap imageBitmap;
    private boolean isImpressed;
    @Nullable
    private transient ItemResponse itemResponse;

    public String getAdAttributionText() {
        return "Ad";
    }

    public String getBrandText() {
        return null;
    }

    public String getPrice() {
        return null;
    }

    public boolean isAd() {
        return true;
    }

    public FbNativeAd(NativeAd nativeAd) {
        this.ad = nativeAd;
    }

    public String getTitle() {
        return this.ad.getAdTitle();
    }

    public String getDescription() {
        return getBodyText();
    }

    public String getBrand() {
        return getBrandText();
    }

    public String getBodyText() {
        return this.ad.getAdBody();
    }

    public String getIconUrl() {
        return this.ad.getAdIcon().getUrl();
    }

    /* Access modifiers changed, original: protected */
    public void setIconBitmap(Bitmap bitmap) {
        this.iconBitmap = bitmap;
    }

    public Bitmap getIcon() {
        return this.iconBitmap;
    }

    public String getImageUrl() {
        return this.ad.getAdCoverImage().getUrl();
    }

    /* Access modifiers changed, original: protected */
    public void setImageBitmap(Bitmap bitmap) {
        this.imageBitmap = bitmap;
    }

    public Bitmap getImage() {
        return this.imageBitmap;
    }

    public String getCtaText() {
        return this.ad.getAdCallToAction();
    }

    public Double getStarRating() {
        return this.ad.getAdStarRating() != null ? Double.valueOf((this.ad.getAdStarRating().getValue() / this.ad.getAdStarRating().getScale()) * 5.0d) : null;
    }

    public ITEM_TYPE getItemType() {
        return ITEM_TYPE.APP;
    }

    public String getDisplayUrl() {
        return this.ad.getAdSocialContext();
    }

    public String getAdAttributionUrl() {
        return this.ad.getAdChoicesLinkUrl();
    }

    public Object thirdPartyAd() {
        return this.ad;
    }

    public AD_NTWK getAdNetwork() {
        return AD_NTWK.FACEBOOK;
    }

    public int getAdImgWidth() {
        try {
            return this.ad.getAdCoverImage().getWidth();
        } catch (Exception unused) {
            return this.imageBitmap != null ? this.imageBitmap.getWidth() : 0;
        }
    }

    public int getAdImgHeight() {
        try {
            return this.ad.getAdCoverImage().getHeight();
        } catch (Exception unused) {
            return this.imageBitmap != null ? this.imageBitmap.getHeight() : 0;
        }
    }

    public String getUID() {
        return this.ad.getId();
    }

    public void destroy() {
        this.ad.destroy();
    }

    public boolean isImpressed() {
        return this.isImpressed;
    }

    public Integer getLineItemId() {
        return Integer.valueOf(0);
    }

    public void setItemResponse(@Nullable ItemResponse itemResponse) {
        this.itemResponse = itemResponse;
    }

    public ItemResponse getItemResponse() {
        return this.itemResponse;
    }

    public void recordImpression() {
        try {
            if (!isImpressed()) {
                this.isImpressed = true;
                this.itemResponse.recordItemResponseImpression(null);
            }
        } catch (Exception unused) {
        }
    }
}
