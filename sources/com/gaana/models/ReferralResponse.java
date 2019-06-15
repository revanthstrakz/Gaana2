package com.gaana.models;

import com.constants.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReferralResponse extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("gaanaPlusDays")
    @Expose
    private int gaana_plus_days_earned;
    @SerializedName("isEligible")
    @Expose
    private int is_eligible;
    @SerializedName("msg")
    @Expose
    private String message;
    @SerializedName("msg_sms")
    @Expose
    private String msg_sms;
    @SerializedName("msg_sub")
    @Expose
    private String msg_sub;
    @SerializedName("referralCode")
    @Expose
    private String refer_code;
    @SerializedName("referralUrl")
    @Expose
    private String refer_url;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("user-token-status")
    @Expose
    private String user_token_status;

    public String getReferralUrl() {
        return this.refer_url;
    }

    public String getReferralCode() {
        return this.refer_code;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getUserTokenStatus() {
        return this.user_token_status;
    }

    public String getMessage() {
        return Constants.b(this.message);
    }

    public String getMessageSMS() {
        return Constants.b(this.msg_sms);
    }

    public String getMessageSubject() {
        return Constants.b(this.msg_sub);
    }

    public boolean getIsEligble() {
        return this.is_eligible != 0;
    }

    public int getGaanaPlusDaysEarned() {
        return this.gaana_plus_days_earned;
    }
}
