package com.til.colombia.android.service;

import com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE;
import java.io.Serializable;
import java.util.Date;
import org.json.JSONObject;

public abstract class CmItem implements Serializable {
    public String getAdAttrText() {
        return null;
    }

    public String getAdAttrUrl() {
        return null;
    }

    public abstract String getBrand();

    public abstract String getCtaText();

    public JSONObject getDataTags() {
        return null;
    }

    public String getDeepLink() {
        return null;
    }

    public abstract String getDescription();

    public String getDisplayUrl() {
        return null;
    }

    public Long getDownloadsCount() {
        return null;
    }

    public Date getExpiry() {
        return null;
    }

    public abstract String getIconUrl();

    public abstract String getImageUrl();

    public String getItemId() {
        return null;
    }

    public abstract ITEM_TYPE getItemType();

    public String getRedirectionUrl() {
        return null;
    }

    public abstract Double getStarRating();

    public String getTags() {
        return null;
    }

    public abstract String getTitle();

    public abstract String getUID();

    public Long getViewsCount() {
        return null;
    }

    public abstract boolean isAd();
}
