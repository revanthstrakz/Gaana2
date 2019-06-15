package com.gaana.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateMobileNumberInfo {
    @SerializedName("Error")
    @Expose
    private String error;
    @SerializedName("mobile_number")
    @Expose
    private String mobileNumber;
    @SerializedName("mobile_country_prefix")
    private String mobile_country_prefix;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("uts")
    @Expose
    private Integer uts;

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer num) {
        this.status = num;
    }

    public String getMobileNumber() {
        return this.mobileNumber;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String str) {
        this.error = str;
    }

    public void setMobileNumber(String str) {
        this.mobileNumber = str;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public Integer getUts() {
        return this.uts;
    }

    public void setUts(Integer num) {
        this.uts = num;
    }

    public String getMobile_country_prefix() {
        return this.mobile_country_prefix;
    }
}
