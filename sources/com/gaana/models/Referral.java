package com.gaana.models;

import com.google.gson.annotations.SerializedName;

public class Referral extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("error")
    private String error;
    @SerializedName("msg")
    private String message;
    @SerializedName("msg_sms")
    private String msg_sms;
    @SerializedName("msg_sub")
    private String msg_sub;
    @SerializedName("refer_url")
    private String refer_url;
    @SerializedName("status")
    private String status;

    public String getReferralUrl() {
        return this.refer_url;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getMessageSub() {
        return this.msg_sub;
    }

    public String getMessageSms() {
        return this.msg_sms;
    }
}
