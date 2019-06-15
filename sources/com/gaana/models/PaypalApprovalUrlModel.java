package com.gaana.models;

import com.google.gson.annotations.SerializedName;

public class PaypalApprovalUrlModel extends BusinessObject {
    @SerializedName("billing_approval_url")
    private String billing_approval_url;
    @SerializedName("m_code")
    private String m_code;
    @SerializedName("message")
    private String message;
    @SerializedName("p_ref_id")
    private String p_ref_id;
    @SerializedName("status")
    private String status;
    @SerializedName("token_id")
    private String token_id;

    public String getM_code() {
        return this.m_code;
    }

    public void setM_code(String str) {
        this.m_code = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getToken_id() {
        return this.token_id;
    }

    public void setToken_id(String str) {
        this.token_id = str;
    }

    public String getBilling_approval_url() {
        return this.billing_approval_url;
    }

    public void setBilling_approval_url(String str) {
        this.billing_approval_url = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getP_ref_id() {
        return this.p_ref_id;
    }

    public void setP_ref_id(String str) {
        this.p_ref_id = str;
    }
}
