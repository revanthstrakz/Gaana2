package com.til.colombia.android.service;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CmEntity extends ItemResponse {
    @Expose
    private List<CmItem> cmItems;
    @Expose
    private CM_ENTITY_TYPE feedEntityType = CM_ENTITY_TYPE.NONE;
    @Expose
    private String uid = UUID.randomUUID().toString();

    public enum CM_ENTITY_TYPE {
        NONE,
        CONTENT,
        AD,
        MIXED
    }

    public CmEntity() {
        super(new AdRequestParams());
    }

    private boolean hasContents() {
        return getContentCmItem() != null && getContentCmItem().size() > 0;
    }

    private boolean hasAds() {
        return getAdCmItem() != null && getAdCmItem().size() > 0;
    }

    public String getUid() {
        return this.uid;
    }

    public List<CmItem> getContentCmItem() {
        return super.getContentCmItem();
    }

    public List<CmItem> getAdCmItem() {
        return super.getAdCmItem();
    }

    public List<CmItem> getCmItems() {
        if (this.cmItems == null) {
            this.cmItems = new ArrayList();
            if (hasContents()) {
                this.feedEntityType = CM_ENTITY_TYPE.CONTENT;
                this.cmItems.addAll(getContentCmItem());
            }
            if (hasAds()) {
                if (this.feedEntityType == CM_ENTITY_TYPE.CONTENT) {
                    this.feedEntityType = CM_ENTITY_TYPE.MIXED;
                } else {
                    this.feedEntityType = CM_ENTITY_TYPE.AD;
                }
                this.cmItems.addAll(getAdCmItem());
            }
        }
        return this.cmItems;
    }

    public CM_ENTITY_TYPE getFeedEntityType() {
        return this.feedEntityType;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("FeedResponse [feedItems=");
        stringBuilder.append(this.cmItems);
        stringBuilder.append(", feedEntityType=");
        stringBuilder.append(this.feedEntityType);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
