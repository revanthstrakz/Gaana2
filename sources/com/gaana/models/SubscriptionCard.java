package com.gaana.models;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;

public class SubscriptionCard extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("card_identifier")
    private String card_identifier;
    @SerializedName("img_url")
    private String img_url;
    @SerializedName("is_subs_card")
    private boolean is_subs_card;
    @SerializedName("message")
    private boolean message;
    @SerializedName("result")
    private String result;
    @SerializedName("status")
    private boolean status;
    @SerializedName("uts")
    private String uts;

    public static long getSerialVersionUID() {
        return 1;
    }

    public String getCard_identifier() {
        return this.card_identifier;
    }

    public void setCard_identifier(String str) {
        this.card_identifier = str;
    }

    public boolean getStatus() {
        return this.status;
    }

    public String getResult() {
        return this.result;
    }

    public boolean isMessage() {
        return this.message;
    }

    public boolean getIs_subs_card() {
        return this.is_subs_card;
    }

    public String getUts() {
        return this.uts;
    }

    public String getImg_url() {
        if (TextUtils.isEmpty(getAtw())) {
            return this.img_url;
        }
        return getAtw();
    }
}
