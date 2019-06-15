package com.gaana.models;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class TrialProductFeature extends SubscriptionTrialCard {
    @SerializedName("duration_days")
    private int durationDays;
    @SerializedName("more_option_url")
    private String moreOptionUrl;
    @SerializedName("payment_mode")
    private String paymentMode;
    @SerializedName("user_action_config")
    private ArrayList<UserActionConfig> userActionConfig = null;

    public class UserActionConfig {
        @SerializedName("action_type")
        private String actionType;
        @SerializedName("is_active")
        private int isActive;
        @SerializedName("max_limit")
        private String maxLimit;

        public String getMaxLimit() {
            return this.maxLimit;
        }

        public void setMaxLimit(String str) {
            this.maxLimit = str;
        }

        public String getActionType() {
            return this.actionType;
        }

        public void setActionType(String str) {
            this.actionType = str;
        }

        public boolean isActive() {
            return this.isActive == 1;
        }

        public void setIsActive(int i) {
            this.isActive = i;
        }
    }

    public ArrayList<UserActionConfig> getUserActionConfig() {
        return this.userActionConfig;
    }

    public void setUserActionConfig(ArrayList<UserActionConfig> arrayList) {
        this.userActionConfig = arrayList;
    }

    public int getDurationDays() {
        return this.durationDays;
    }

    public void setDurationDays(int i) {
        this.durationDays = i;
    }

    public String getPaymentMode() {
        return this.paymentMode;
    }

    public void setPaymentMode(String str) {
        this.paymentMode = str;
    }

    public String getMoreOptionUrl() {
        return this.moreOptionUrl;
    }

    public void setMoreOptionUrl(String str) {
        this.moreOptionUrl = str;
    }
}
