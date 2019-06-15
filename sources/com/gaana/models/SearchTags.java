package com.gaana.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SearchTags {
    @SerializedName("status")
    private Integer status;
    @SerializedName("tags")
    private List<Tag> tags = null;
    @SerializedName("user_token_status")
    private String userTokenStatus;

    public static class Tag {
        @SerializedName("atw")
        private String atw;
        @SerializedName("color")
        private String color;
        @SerializedName("deeplink")
        private String deeplink;
        @SerializedName("tag_name")
        private String tagName;

        public String getDeeplink() {
            return this.deeplink;
        }

        public void setDeeplink(String str) {
            this.deeplink = str;
        }

        public String getColor() {
            return this.color;
        }

        public void setColor(String str) {
            this.color = str;
        }

        public String getAtw() {
            return this.atw;
        }

        public void setAtw(String str) {
            this.atw = str;
        }

        public String getTagName() {
            return this.tagName;
        }

        public void setTagName(String str) {
            this.tagName = str;
        }
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer num) {
        this.status = num;
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public void setTags(List<Tag> list) {
        this.tags = list;
    }

    public String getUserTokenStatus() {
        return this.userTokenStatus;
    }

    public void setUserTokenStatus(String str) {
        this.userTokenStatus = str;
    }
}
