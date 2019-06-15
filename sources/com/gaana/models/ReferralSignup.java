package com.gaana.models;

import com.constants.Constants;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ReferralSignup implements Serializable {
    @SerializedName("~campaign")
    private String campaign;
    @SerializedName("$deeplink_path")
    private String deeplinkPath;
    @SerializedName("referralCode")
    private String referralCode;
    @SerializedName("user_artwork")
    private String referreeArtwork;
    @SerializedName("fname")
    private String referreeName;

    public String getReferreeName() {
        return Constants.b(this.referreeName);
    }

    public String getReferralCode() {
        return this.referralCode;
    }

    public String getCampaign() {
        return this.campaign;
    }

    public String getReferreeArtwork() {
        return this.referreeArtwork;
    }

    public String getDeeplinkPath() {
        return this.deeplinkPath;
    }
}
