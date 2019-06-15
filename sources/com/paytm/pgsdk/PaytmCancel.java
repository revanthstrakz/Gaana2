package com.paytm.pgsdk;

public class PaytmCancel {
    public String mMerchantId;
    public String mOrderId;

    public PaytmCancel(String str, String str2) {
        this.mMerchantId = str;
        this.mOrderId = str2;
    }
}
