package com.gaana.models;

import android.text.TextUtils;
import com.constants.Constants;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class SocialFeed extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("feed_data")
    private ArrayList<FeedData> feedData;
    @SerializedName("delta_gaana_ts")
    private String gaanaDeltaTs;
    @SerializedName("pagination_ts")
    private String paginationTs;
    @SerializedName("status")
    private int status;
    @SerializedName("delta_ts")
    private String userDeltaTs;

    public static class FeedData extends BusinessObject {
        private static final long serialVersionUID = 1;
        @SerializedName("feed_description")
        private String feedDescription;
        @SerializedName("feed_entity")
        private ArrayList<Item> feedEntity;
        @SerializedName("ts")
        private String feedTimestamp;
        @SerializedName("feed_type")
        private int feedType;
        @SerializedName("feed_url")
        private String feedUrl;
        @SerializedName("feed_pic")
        private String feed_pic;
        @SerializedName("follow_id")
        private String followId;
        @SerializedName("follow_name")
        private String followName;
        @SerializedName("follow_pic")
        private String followPic;
        @SerializedName("follow_type")
        private int followType = 1;
        @SerializedName("follow_crowned")
        private String follow_crowned;
        private String gaanaDeltaId;
        @SerializedName("gaana_feed_id")
        private String gaanaFeedID;
        private boolean mIsForceRefresh;
        @SerializedName("notify_me")
        private int notify_me;
        @SerializedName("notify_me_url")
        private String notify_me_url;
        private String paginationId;
        @SerializedName("post")
        private String post;
        @SerializedName("refresh_interval")
        private String refreshInterval;
        private String userDeltaId;
        @SerializedName("user_id")
        private String userId;

        public String getFeedDescription() {
            return Constants.b(this.feedDescription);
        }

        public String getEnglishFeedDescription() {
            return Constants.a(this.feedDescription);
        }

        public String getRawFeedDescription() {
            return this.feedDescription;
        }

        public String getFeedTimestamp() {
            return this.feedTimestamp;
        }

        public String getFeed_pic() {
            return this.feed_pic;
        }

        public void setFeed_pic(String str) {
            this.feed_pic = str;
        }

        public String getFollowId() {
            return this.followId;
        }

        public String getFollowPic() {
            if (TextUtils.isEmpty(getAtw())) {
                return this.followPic;
            }
            return getAtw();
        }

        public String getFollowName() {
            return this.followName;
        }

        public int getFollowType() {
            return this.followType;
        }

        public ArrayList<Item> getFeedEntity() {
            return this.feedEntity;
        }

        public int getFeedType() {
            return this.feedType;
        }

        public String getUserId() {
            return this.userId;
        }

        public String getGaanaDeltaId() {
            return this.gaanaDeltaId;
        }

        public String getUserDeltaId() {
            return this.userDeltaId;
        }

        public String getPaginationId() {
            return this.paginationId;
        }

        public void setFeedDescription(String str) {
            this.feedDescription = str;
        }

        public void setFeedTimestamp(String str) {
            this.feedTimestamp = str;
        }

        public void setFollowId(String str) {
            this.followId = str;
        }

        public void setFollowPic(String str) {
            this.followPic = str;
        }

        public void setFollowType(int i) {
            this.followType = i;
        }

        public void setFeedEntity(ArrayList<Item> arrayList) {
            this.feedEntity = arrayList;
        }

        public void setUserId(String str) {
            this.userId = str;
        }

        public void setFeedType(int i) {
            this.feedType = i;
        }

        public void setPaginationId(String str) {
            this.paginationId = str;
        }

        public void setUserDeltaId(String str) {
            this.userDeltaId = str;
        }

        public void setGaanaDeltaId(String str) {
            this.gaanaDeltaId = str;
        }

        public String getFeedUrl() {
            return this.feedUrl;
        }

        public void setFeedUrl(String str) {
            this.feedUrl = str;
        }

        public String getRefreshInterval() {
            return this.refreshInterval;
        }

        public void setRefreshInterval(String str) {
            this.refreshInterval = str;
        }

        public String getGaanaFeedID() {
            return this.gaanaFeedID;
        }

        public void setGaanaFeedID(String str) {
            this.gaanaFeedID = str;
        }

        public int getNotify_me() {
            return this.notify_me;
        }

        public void setNotify_me(int i) {
            this.notify_me = i;
        }

        public String getNotify_me_url() {
            return this.notify_me_url;
        }

        public void setNotify_me_url(String str) {
            this.notify_me_url = str;
        }

        public boolean isForceRefresh() {
            return this.mIsForceRefresh;
        }

        public void setForceRefresh(boolean z) {
            this.mIsForceRefresh = z;
        }

        public String getPost() {
            return this.post;
        }

        public void setPost(String str) {
            this.post = str;
        }

        public String getFollowCrowned() {
            return this.follow_crowned;
        }

        public void setFollowCrowned(String str) {
            this.follow_crowned = str;
        }
    }

    public ArrayList<FeedData> getFeedData() {
        return this.feedData;
    }

    public ArrayList<?> getArrListBusinessObj() {
        return this.feedData;
    }

    public void setFeedData(ArrayList<FeedData> arrayList) {
        this.feedData = arrayList;
    }

    public String getPaginationTs() {
        return this.paginationTs;
    }

    public String getUserDeltaTs() {
        return this.userDeltaTs;
    }

    public String getGaanaDeltaTs() {
        return this.gaanaDeltaTs;
    }
}
