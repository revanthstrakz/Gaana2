package com.gaana.revampeddetail.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class RevampedListAdasCard {
    @SerializedName("ad_cards")
    private ArrayList<AdCard> ad_cards;
    @SerializedName("status")
    private int status;

    public static class AdCard {
        @SerializedName("ad_code")
        private String ad_code;
        @SerializedName("ad_height")
        private int ad_height;
        @SerializedName("ad_type")
        private String ad_type;

        public String getAd_type() {
            return this.ad_type;
        }

        public void setAd_type(String str) {
            this.ad_type = str;
        }

        public int getAd_height() {
            return this.ad_height;
        }

        public void setAd_height(int i) {
            this.ad_height = i;
        }

        public String getAd_code() {
            return this.ad_code;
        }

        public void setAd_code(String str) {
            this.ad_code = str;
        }
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public ArrayList<AdCard> getAd_cards() {
        return this.ad_cards;
    }

    public void setAd_cards(ArrayList<AdCard> arrayList) {
        this.ad_cards = arrayList;
    }
}
