package com.gaana.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PreScreens {
    @SerializedName("hashcode")
    private String hashcode;
    @SerializedName("pre_screen")
    private List<PreScreen> preScreens = null;
    @SerializedName("status")
    private int status;

    public static class PreScreen {
        @SerializedName("entity_description")
        private String entityDescription;
        @SerializedName("ga_header")
        private String gaHeader;
        @SerializedName("ga_source_name")
        private String gaSourceName;
        @SerializedName("entities")
        private List<Item> items = null;
        @SerializedName("refresh_interval")
        private String refreshInterval;
        @SerializedName("show_empty_view")
        private Boolean showEmptyView;
        @SerializedName("show_load_more")
        private Boolean showLoadMore;
        @SerializedName("uid")
        private String uid;
        @SerializedName("url")
        private String url;
        @SerializedName("url_see_all")
        private String urlSeeAll;
        @SerializedName("user_type")
        private int userType;
        @SerializedName("view_action")
        private int viewAction;
        @SerializedName("view_size")
        private int viewSize;
        @SerializedName("view_subtype")
        private int viewSubtype;
        @SerializedName("view_type")
        private String viewType;

        public String getGaHeader() {
            return this.gaHeader;
        }

        public void setGaHeader(String str) {
            this.gaHeader = str;
        }

        public String getViewType() {
            return this.viewType;
        }

        public void setViewType(String str) {
            this.viewType = str;
        }

        public int getViewSubtype() {
            return this.viewSubtype;
        }

        public void setViewSubtype(int i) {
            this.viewSubtype = i;
        }

        public Boolean getShowEmptyView() {
            return this.showEmptyView;
        }

        public void setShowEmptyView(Boolean bool) {
            this.showEmptyView = bool;
        }

        public Boolean getShowLoadMore() {
            return this.showLoadMore;
        }

        public void setShowLoadMore(Boolean bool) {
            this.showLoadMore = bool;
        }

        public String getGaSourceName() {
            return this.gaSourceName;
        }

        public void setGaSourceName(String str) {
            this.gaSourceName = str;
        }

        public String getRefreshInterval() {
            return this.refreshInterval;
        }

        public void setRefreshInterval(String str) {
            this.refreshInterval = str;
        }

        public String getEntityDescription() {
            return this.entityDescription;
        }

        public void setEntityDescription(String str) {
            this.entityDescription = str;
        }

        public int getViewAction() {
            return this.viewAction;
        }

        public void setViewAction(int i) {
            this.viewAction = i;
        }

        public int getViewSize() {
            return this.viewSize;
        }

        public void setViewSize(int i) {
            this.viewSize = i;
        }

        public String getUid() {
            return this.uid;
        }

        public void setUid(String str) {
            this.uid = str;
        }

        public int getUserType() {
            return this.userType;
        }

        public void setUserType(int i) {
            this.userType = i;
        }

        public List<Item> getItems() {
            return this.items;
        }

        public void setItems(List<Item> list) {
            this.items = list;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public String getUrlSeeAll() {
            return this.urlSeeAll;
        }
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public List<PreScreen> getPreScreens() {
        return this.preScreens;
    }

    public void setPreScreens(List<PreScreen> list) {
        this.preScreens = list;
    }

    public String getHashcode() {
        return this.hashcode;
    }

    public void setHashcode(String str) {
        this.hashcode = str;
    }
}
