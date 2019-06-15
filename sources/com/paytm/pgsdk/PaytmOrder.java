package com.paytm.pgsdk;

import java.util.Map;

public class PaytmOrder {
    public String mCustomerId;
    public String mEmail;
    public String mMobileNumber;
    public String mOrderId;
    public String mTransactionAmount;
    private Map<String, String> requestParamMap;

    public PaytmOrder(String str, String str2, String str3, String str4, String str5) {
        this.mOrderId = str;
        this.mCustomerId = str2;
        this.mTransactionAmount = str3;
        this.mEmail = str4;
        this.mMobileNumber = str5;
    }

    public PaytmOrder(Map<String, String> map) {
        this.requestParamMap = map;
    }

    public Map<String, String> getRequestParamMap() {
        return this.requestParamMap;
    }
}
