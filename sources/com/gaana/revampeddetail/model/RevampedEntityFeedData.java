package com.gaana.revampeddetail.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class RevampedEntityFeedData {
    @SerializedName("entity_feed_data")
    ArrayList<EntityFeedData> entity_feed_data;
    @SerializedName("status")
    private int status;
    @SerializedName("user_token_status")
    private String user_token_status;

    public static class EntityFeedData {
        @SerializedName("atw")
        private String atw;
        @SerializedName("feed_card_entity_id")
        private String feed_card_entity_id;
        @SerializedName("feed_card_heading")
        private String feed_card_heading;
        @SerializedName("feed_card_subheading")
        private String feed_card_subheading;
        @SerializedName("feed_card_url")
        private String feed_card_url;

        public String getAtw() {
            return this.atw;
        }

        public void setAtw(String str) {
            this.atw = str;
        }

        public String getFeed_card_heading() {
            return this.feed_card_heading;
        }

        public void setFeed_card_heading(String str) {
            this.feed_card_heading = str;
        }

        public String getFeed_card_subheading() {
            return this.feed_card_subheading;
        }

        public void setFeed_card_subheading(String str) {
            this.feed_card_subheading = str;
        }

        public String getFeed_card_entity_id() {
            return this.feed_card_entity_id;
        }

        public void setFeed_card_entity_id(String str) {
            this.feed_card_entity_id = str;
        }

        public String getFeed_card_url() {
            return this.feed_card_url;
        }

        public void setFeed_card_url(String str) {
            this.feed_card_url = str;
        }
    }

    public ArrayList<EntityFeedData> getEntity_feed_data() {
        return this.entity_feed_data;
    }

    public void setEntity_feed_data(ArrayList<EntityFeedData> arrayList) {
        this.entity_feed_data = arrayList;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String getUser_token_status() {
        return this.user_token_status;
    }

    public void setUser_token_status(String str) {
        this.user_token_status = str;
    }
}
