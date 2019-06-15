package com.gaana.models;

import com.google.gson.annotations.SerializedName;

public class BasicResponse extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("error")
    private String error;
    @SerializedName("message")
    private String message;
    @SerializedName("result")
    private String result;
    @SerializedName("Status")
    private String status;
    @SerializedName("talktime_coupon_code")
    private String talktime_coupon_code;

    public String getCouponCode() {
        return this.talktime_coupon_code;
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

    public String getError() {
        return this.error;
    }

    public void setError(String str) {
        this.error = str;
    }

    public String getResult() {
        return this.result;
    }
}
