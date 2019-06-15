package com.gaana.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateEmailIdInfo {
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("email_status")
    @Expose
    private Integer email_status;
    @SerializedName("Error")
    @Expose
    private String error;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("uts")
    @Expose
    private Integer uts;

    public Integer getEmailStatus() {
        return this.email_status;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer num) {
        this.status = num;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String str) {
        this.error = str;
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
}
