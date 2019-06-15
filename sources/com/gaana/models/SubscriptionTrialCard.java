package com.gaana.models;

import android.text.TextUtils;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.google.gson.annotations.SerializedName;

public class SubscriptionTrialCard extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("additional_text")
    private String additional_text;
    @SerializedName("card_identifier")
    private String card_identifier;
    @SerializedName("country")
    private String country;
    @SerializedName("cta_text")
    private String cta_text;
    @SerializedName("header_text")
    private String header_text;
    @SerializedName("gtrial_img_url")
    private String img_url;
    @SerializedName("is_card")
    private int is_card;
    @SerializedName("is_default_pack")
    private int is_default_pack;
    @SerializedName("is_more_option")
    private int is_more_option;
    @SerializedName("is_trial")
    private boolean is_trial;
    @SerializedName("message_text")
    private String message_text;
    @SerializedName("normal_desc")
    private String normal_desc;
    @SerializedName("normal_header")
    private String normal_header;
    @SerializedName("pg_product")
    private ProductItem pg_product;
    @SerializedName("source_msg")
    private String sourceMessage = null;
    @SerializedName("status")
    private String status;
    @SerializedName("tnc")
    private String termAndCondition;
    @SerializedName("trial_desc")
    private String trial_desc;
    @SerializedName("trial_header")
    private String trial_header;

    public static long getSerialVersionUID() {
        return 1;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public boolean getIs_trial() {
        return this.is_trial;
    }

    public void setIs_trial(boolean z) {
        this.is_trial = z;
    }

    public String getTrial_header() {
        return this.trial_header;
    }

    public void setTrial_header(String str) {
        this.trial_header = str;
    }

    public String getTrial_desc() {
        return this.trial_desc;
    }

    public void setTrial_desc(String str) {
        this.trial_desc = str;
    }

    public String getNormal_header() {
        return this.normal_header;
    }

    public void setNormal_header(String str) {
        this.normal_header = str;
    }

    public String getNormal_desc() {
        return this.normal_desc;
    }

    public void setNormal_desc(String str) {
        this.normal_desc = str;
    }

    public String getCard_identifier() {
        return this.card_identifier;
    }

    public void setCard_identifier(String str) {
        this.card_identifier = str;
    }

    public String getAdditional_text() {
        return this.additional_text;
    }

    public String getCta_text() {
        return this.cta_text;
    }

    public String getTermAndCondition() {
        return this.termAndCondition;
    }

    public int getIs_more_option() {
        return this.is_more_option;
    }

    public int getIs_default_pack() {
        return this.is_default_pack;
    }

    public int getIs_card() {
        return this.is_card;
    }

    public ProductItem getPg_product() {
        return this.pg_product;
    }

    public String getHeader_text() {
        return this.header_text;
    }

    public String getMessage_text() {
        return this.message_text;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getImg_url() {
        if (TextUtils.isEmpty(getAtw())) {
            return this.img_url;
        }
        return getAtw();
    }

    public void setImg_url(String str) {
        this.img_url = str;
    }

    public String getSourceMessage() {
        return this.sourceMessage;
    }

    public void setSourceMessage(String str) {
        this.sourceMessage = str;
    }
}
