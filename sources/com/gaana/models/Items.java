package com.gaana.models;

import android.text.TextUtils;
import com.constants.Constants;
import com.gaana.models.flatbuffer.GaanaEntityResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Iterator;

public class Items extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("entities")
    @Expose
    private ArrayList<Item> arrListItem;
    @SerializedName("artwork")
    @Expose
    private String artwork;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("description_details")
    @Expose
    private String descriptionDetails;
    @SerializedName("entityParentId")
    @Expose
    private int entityParentId;
    @SerializedName("page_title")
    @Expose
    private String pageTitle;
    @SerializedName("seokey")
    @Expose
    private String seokey;
    @SerializedName("status")
    @Expose
    private long status;
    @SerializedName("sub_title")
    private String subTitle;
    @SerializedName("entityDescription")
    @Expose
    private String tagDescription;
    @SerializedName("unfavourite")
    private String unfavorite;
    @SerializedName("user-token-status")
    @Expose
    private long userTokenStatus;

    public int getEntityParentId() {
        return this.entityParentId;
    }

    public void setEntityParentId(int i) {
        this.entityParentId = i;
    }

    public ArrayList<Item> getArrListBusinessObj() {
        return this.arrListItem;
    }

    public void setArrListBusinessObj(ArrayList<BusinessObject> arrayList) {
        this.arrListItem = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            BusinessObject businessObject = (BusinessObject) it.next();
            if (businessObject instanceof Item) {
                this.arrListItem.add((Item) businessObject);
            }
        }
    }

    public long getStatus() {
        return this.status;
    }

    public void setStatus(long j) {
        this.status = j;
    }

    public String getTagDescription() {
        return Constants.b(this.tagDescription);
    }

    public String getRawTagDescription() {
        return this.tagDescription;
    }

    public void setTagDescription(String str) {
        this.tagDescription = str;
    }

    public String getSeokey() {
        return this.seokey;
    }

    public void setSeokey(String str) {
        this.seokey = str;
    }

    public String getArtwork() {
        if (TextUtils.isEmpty(getAtw())) {
            return this.artwork;
        }
        return getAtw();
    }

    public void setArtwork(String str) {
        this.artwork = str;
    }

    public long getUserTokenStatus() {
        return this.userTokenStatus;
    }

    public void setUserTokenStatus(long j) {
        this.userTokenStatus = j;
    }

    public String getCategoryName() {
        return Constants.b(this.categoryName);
    }

    public void setCategoryName(String str) {
        this.categoryName = str;
    }

    public String getPageTitle() {
        return Constants.b(this.pageTitle);
    }

    public void setPageTitle(String str) {
        this.pageTitle = str;
    }

    public String getDescriptionDetails() {
        return Constants.b(this.descriptionDetails);
    }

    public void setDescriptionDetails(String str) {
        this.descriptionDetails = str;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public String getUnfavorite() {
        return this.unfavorite;
    }

    public Items(GaanaEntityResponse gaanaEntityResponse) {
        setCount(gaanaEntityResponse.count());
        this.status = (long) gaanaEntityResponse.status();
        this.tagDescription = gaanaEntityResponse.entityDescription();
        this.userTokenStatus = Long.valueOf(gaanaEntityResponse.userTokenStatus()).longValue();
        this.seokey = gaanaEntityResponse.seokey();
        this.pageTitle = gaanaEntityResponse.pageTitle();
        this.entityParentId = gaanaEntityResponse.entityParentId();
        this.hash_value = gaanaEntityResponse.hv();
        if (gaanaEntityResponse.entitiesLength() > 0) {
            this.arrListItem = new ArrayList();
            for (int i = 0; i < gaanaEntityResponse.entitiesLength(); i++) {
                this.arrListItem.add(new Item(gaanaEntityResponse.entities(i)));
            }
        }
        this.subTitle = gaanaEntityResponse.subTitle();
    }
}
