package com.til.colombia.android.adapters;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.til.colombia.android.service.ColombiaAdManager.AD_NTWK;
import com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE;
import com.til.colombia.android.service.Item;
import com.til.colombia.android.service.ItemResponse;
import com.til.colombia.android.utils.a;

public class GoogleNativeAd extends Item {
    private NativeAppInstallAd appInstallAd = null;
    private NativeContentAd contentAd = null;
    private boolean isImpressed;
    @Nullable
    private transient ItemResponse itemResponse;
    private ITEM_TYPE itemType;

    public String getAdAttributionText() {
        return "Ad";
    }

    public String getAdAttributionUrl() {
        return null;
    }

    public int getAdImgHeight() {
        return 0;
    }

    public int getAdImgWidth() {
        return 0;
    }

    public String getUID() {
        return null;
    }

    public boolean isAd() {
        return true;
    }

    public GoogleNativeAd(NativeAd nativeAd, ITEM_TYPE item_type) {
        if (nativeAd instanceof NativeContentAd) {
            this.contentAd = (NativeContentAd) nativeAd;
        } else if (nativeAd instanceof NativeAppInstallAd) {
            this.appInstallAd = (NativeAppInstallAd) nativeAd;
        }
        this.itemType = item_type;
    }

    public String getTitle() {
        if (this.contentAd != null) {
            return this.contentAd.getHeadline().toString();
        }
        return this.appInstallAd != null ? this.appInstallAd.getHeadline().toString() : null;
    }

    public String getDescription() {
        return getBodyText();
    }

    public String getBrand() {
        return getBrandText();
    }

    public String getBodyText() {
        if (this.contentAd != null) {
            return this.contentAd.getBody().toString();
        }
        return this.appInstallAd != null ? this.appInstallAd.getBody().toString() : null;
    }

    public String getIconUrl() {
        if (this.contentAd == null || this.contentAd.getLogo() == null) {
            return (this.appInstallAd == null || this.appInstallAd.getIcon() == null) ? null : this.appInstallAd.getIcon().getUri().toString();
        } else {
            return this.contentAd.getLogo().getUri().toString();
        }
    }

    public Bitmap getIcon() {
        if (this.contentAd == null || this.contentAd.getLogo() == null) {
            return (this.appInstallAd == null || this.appInstallAd.getIcon() == null) ? null : a.a(this.appInstallAd.getIcon().getDrawable());
        } else {
            return a.a(this.contentAd.getLogo().getDrawable());
        }
    }

    public String getImageUrl() {
        if (this.contentAd == null || this.contentAd.getImages() == null || this.contentAd.getImages().size() <= 0) {
            return (this.appInstallAd == null || this.appInstallAd.getImages() == null || this.appInstallAd.getImages().size() <= 0) ? null : ((Image) this.appInstallAd.getImages().get(0)).getUri().toString();
        } else {
            return ((Image) this.contentAd.getImages().get(0)).getUri().toString();
        }
    }

    public Bitmap getImage() {
        if (this.contentAd == null || this.contentAd.getImages() == null || this.contentAd.getImages().size() <= 0) {
            return (this.appInstallAd == null || this.appInstallAd.getImages() == null || this.appInstallAd.getImages().size() <= 0) ? null : a.a(((Image) this.appInstallAd.getImages().get(0)).getDrawable());
        } else {
            return a.a(((Image) this.contentAd.getImages().get(0)).getDrawable());
        }
    }

    public String getCtaText() {
        if (this.contentAd == null || this.contentAd.getCallToAction() == null) {
            return (this.appInstallAd == null || this.appInstallAd.getCallToAction() == null) ? null : this.appInstallAd.getCallToAction().toString();
        } else {
            return this.contentAd.getCallToAction().toString();
        }
    }

    public Double getStarRating() {
        return this.appInstallAd != null ? this.appInstallAd.getStarRating() : null;
    }

    public String getBrandText() {
        if (this.contentAd == null || this.contentAd.getAdvertiser() == null) {
            return (this.appInstallAd == null || this.appInstallAd.getStore() == null) ? null : this.appInstallAd.getStore().toString();
        } else {
            return this.contentAd.getAdvertiser().toString();
        }
    }

    public String getPrice() {
        return (this.appInstallAd == null || this.appInstallAd.getPrice() == null) ? null : this.appInstallAd.getPrice().toString();
    }

    public ITEM_TYPE getItemType() {
        return this.itemType;
    }

    public NativeAd thirdPartyAd() {
        if (this.contentAd != null) {
            return this.contentAd;
        }
        return this.appInstallAd != null ? this.appInstallAd : null;
    }

    public AD_NTWK getAdNetwork() {
        return AD_NTWK.GOOGLE;
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
