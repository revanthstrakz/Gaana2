package com.gaana.models;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;

public class CampaignPromo extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("campaign_code")
    private String campaign_code;
    @SerializedName("campaign_message")
    private String campaign_message;
    @SerializedName("campaign_tc")
    private String campaign_tc;
    @SerializedName("extra_message_1")
    private String extra_message_1;
    @SerializedName("is_textbox_enable")
    private String is_textbox_enable;
    @SerializedName("partner_logo_url")
    private String partner_logo_url;
    @SerializedName("tnc_text")
    private String tnc_text;

    public String getCampaignCode() {
        return this.campaign_code;
    }

    public String getCampaignExtraMessageLine1() {
        return this.extra_message_1;
    }

    public String getCampaignMessage() {
        return this.campaign_message;
    }

    public String getCampaignTermsConditionUrl() {
        return this.campaign_tc;
    }

    public String getTermConditionText() {
        return this.tnc_text;
    }

    public String getPartnerLogoUrl() {
        return this.partner_logo_url;
    }

    public String isTextBoxEnabled() {
        if (TextUtils.isEmpty(this.is_textbox_enable)) {
            this.is_textbox_enable = "0";
        }
        return this.is_textbox_enable;
    }
}
