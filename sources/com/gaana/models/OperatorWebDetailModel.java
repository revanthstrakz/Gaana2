package com.gaana.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class OperatorWebDetailModel implements Serializable {
    private static final long serialVersionUID = 1;
    @SerializedName("action")
    private int action;
    @SerializedName("p_curr_code")
    private String currencyCode;
    @SerializedName("desc")
    private String desc;
    @SerializedName("msg")
    private String message;
    @SerializedName("p_code")
    private String p_code;
    @SerializedName("p_cost")
    private String p_cost;
    @SerializedName("p_cost_curr")
    private String p_cost_curr;
    @SerializedName("p_discounted_cost")
    private String p_discounted_cost;
    @SerializedName("p_discounted_text")
    private String p_discounted_text;
    @SerializedName("p_id")
    private String p_id;
    @SerializedName("p_mode")
    private String p_mode;
    @SerializedName("p_name")
    private String p_name;
    @SerializedName("p_pay_desc")
    private String p_pay_desc;
    @SerializedName("pid")
    private String pid;
    @SerializedName("pt")
    private String pt;
    @SerializedName("status")
    private String status;
    @SerializedName("uts")
    private String uts;
    @SerializedName("val")
    private String val;
    @SerializedName("web_view_url")
    private String web_view_url;

    public String getWeb_view_url() {
        return this.web_view_url;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public String getStatus() {
        return this.status;
    }

    public String getUts() {
        return this.uts;
    }

    public String getMessage() {
        return this.message;
    }

    public String getP_id() {
        return this.p_id;
    }

    public String getP_name() {
        return this.p_name;
    }

    public String getP_pay_desc() {
        return this.p_pay_desc;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getP_cost() {
        return this.p_cost;
    }

    public String getP_cost_curr() {
        return this.p_cost_curr;
    }

    public String getP_discounted_text() {
        return this.p_discounted_text;
    }

    public String getP_discounted_cost() {
        return this.p_discounted_cost;
    }

    public String getP_code() {
        return this.p_code;
    }

    public String getP_mode() {
        return this.p_mode;
    }

    public int getAction() {
        return this.action;
    }

    public String getPt() {
        return this.pt;
    }

    public String getVal() {
        return this.val;
    }

    public String getPid() {
        return this.pid;
    }
}
