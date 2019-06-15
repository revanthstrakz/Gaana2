package com.paytm.pgsdk;

public class PaytmStatusQuery {
    public String mMerchantId;
    public String mOrderId;

    public PaytmStatusQuery(String str, String str2) {
        this.mOrderId = str;
        this.mMerchantId = str2;
    }
}
