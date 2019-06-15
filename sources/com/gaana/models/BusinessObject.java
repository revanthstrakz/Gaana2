package com.gaana.models;

import com.android.volley.VolleyError;
import com.constants.Constants;
import com.google.gson.annotations.SerializedName;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.n;
import java.io.Serializable;
import java.util.ArrayList;

public class BusinessObject implements Serializable {
    private static final long serialVersionUID = 1;
    private ArrayList<?> arrListBusObj;
    @SerializedName("atw")
    public String atw;
    private String businessObjId;
    protected BusinessObjectType businessObjType;
    private String datacount;
    protected String emptyMsg;
    @SerializedName("hv")
    public String hash_value;
    protected boolean isFromNetwork = false;
    public boolean isLocalMedia = false;
    protected String language;
    private VolleyError mVolleyError = null;
    protected String name;
    protected BusinessObjectType parentBusinessObjType;
    @SerializedName("response_time")
    private long responseTime = 0;
    @SerializedName("update_home_meta")
    private int update_home_meta = 0;
    protected URLManager urlManager;
    @SerializedName("user_favorite")
    private String user_favorite = "0";
    @SerializedName("user_token_status")
    private String usertokenstatus;
    @SerializedName("uts")
    private String uts;

    public String getDeviceAvailability() {
        return null;
    }

    public String getLocationAvailability() {
        return null;
    }

    public String getHashValue() {
        return this.hash_value;
    }

    public String getUsertokenstatus() {
        return this.usertokenstatus;
    }

    public String getUts() {
        return this.uts;
    }

    public String getEmptyMsg() {
        return this.emptyMsg;
    }

    public void setEmptyMsg(String str) {
        this.emptyMsg = str;
    }

    public URLManager getUrlManager() {
        return this.urlManager;
    }

    public void setUrlManager(URLManager uRLManager) {
        this.urlManager = uRLManager;
    }

    public String getBusinessObjId() {
        return this.businessObjId;
    }

    public void setBusinessObjId(String str) {
        this.businessObjId = str;
    }

    public ArrayList<?> getArrListBusinessObj() {
        return this.arrListBusObj;
    }

    public void setArrListBusinessObj(ArrayList<BusinessObject> arrayList) {
        this.arrListBusObj = arrayList;
    }

    public void setArrList(ArrayList<?> arrayList) {
        this.arrListBusObj = arrayList;
    }

    public String getCount() {
        return this.datacount;
    }

    public void setCount(String str) {
        this.datacount = str;
    }

    public BusinessObjectType getBusinessObjType() {
        return this.businessObjType;
    }

    public void setBusinessObjType(BusinessObjectType businessObjectType) {
        this.businessObjType = businessObjectType;
    }

    public String getName() {
        return Constants.a(this.name, this.language);
    }

    public String getRawName() {
        return this.name;
    }

    public String getEnglishName() {
        return Constants.a(this.name);
    }

    public void setName(String str) {
        this.name = str;
    }

    public BusinessObjectType getParentBusinessObjType() {
        return this.parentBusinessObjType;
    }

    public void setParentBusinessObjType(BusinessObjectType businessObjectType) {
        this.parentBusinessObjType = businessObjectType;
    }

    public Boolean isFavorite() {
        return Boolean.valueOf(n.a().a(this));
    }

    public String getFavoriteStatus() {
        return this.user_favorite;
    }

    public void setFavorite(Boolean bool) {
        if (bool.booleanValue()) {
            this.user_favorite = "1";
            n.a().d(this);
            return;
        }
        this.user_favorite = "0";
        n.a().d(this);
    }

    public void setUserFavorite(boolean z) {
        this.user_favorite = z ? "1" : "0";
    }

    public boolean isUserFavorited() {
        return this.user_favorite.equals("1");
    }

    public long getResponseTime() {
        return this.responseTime;
    }

    public void setResponseTime(long j) {
        this.responseTime = j;
    }

    public String getAtw() {
        return this.atw;
    }

    public void setAtw(String str) {
        this.atw = str;
    }

    public boolean isLocalMedia() {
        return this.isLocalMedia;
    }

    public void setLocalMedia(boolean z) {
        this.isLocalMedia = z;
    }

    public void setVolleyError(VolleyError volleyError) {
        this.mVolleyError = volleyError;
    }

    public VolleyError getVolleyError() {
        return this.mVolleyError;
    }

    public String getNameAndId() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getEnglishName());
        stringBuilder.append("#");
        stringBuilder.append(getBusinessObjId());
        return stringBuilder.toString();
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public boolean isFromNetwork() {
        return this.isFromNetwork;
    }

    public void setFromNetwork(boolean z) {
        this.isFromNetwork = z;
    }
}
