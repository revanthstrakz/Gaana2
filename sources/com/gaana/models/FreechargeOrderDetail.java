package com.gaana.models;

import com.google.gson.annotations.SerializedName;

public class FreechargeOrderDetail {
    @SerializedName("channel")
    private String channel;
    @SerializedName("checksum")
    private String checksum;
    @SerializedName("currency")
    private String currency;
    @SerializedName("customNote")
    private String customNote;
    @SerializedName("email")
    private String customer_email;
    @SerializedName("customerName")
    private String customer_name;
    @SerializedName("furl")
    private String furl;
    @SerializedName("merchantId")
    private String merchant_id;
    @SerializedName("mobile")
    private String mobile;
    @SerializedName("amount")
    private String order_amount;
    @SerializedName("os")
    private String os;
    @SerializedName("productInfo")
    private String product_info;
    @SerializedName("surl")
    private String surl;
    @SerializedName("merchantTxnId")
    private String transaction_id;

    public String getTransaction_id() {
        return this.transaction_id;
    }

    public String getMerchant_id() {
        return this.merchant_id;
    }

    public String getOrder_amount() {
        return this.order_amount;
    }

    public String getCurrency() {
        return this.currency;
    }

    public String getCustomer_name() {
        return this.customer_name;
    }

    public String getFurl() {
        return this.furl;
    }

    public String getSurl() {
        return this.surl;
    }

    public String getChecksum() {
        return this.checksum;
    }

    public String getProduct_info() {
        return this.product_info;
    }

    public String getCustomer_email() {
        return this.customer_email;
    }

    public String getOs() {
        return this.os;
    }

    public String getMobile() {
        return this.mobile;
    }

    public String getChannel() {
        return this.channel;
    }

    public String getCustomNote() {
        return this.customNote;
    }
}
