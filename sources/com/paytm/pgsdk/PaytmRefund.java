package com.paytm.pgsdk;

public class PaytmRefund {
    public String mChecksumHash;
    public String mComments;
    public String mMerchantId;
    public String mOrderId;
    public String mRefundAmount;
    public String mTransactioType;
    public String mTransactionId;

    public PaytmRefund(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.mTransactionId = str;
        this.mOrderId = str2;
        this.mRefundAmount = str3;
        this.mTransactioType = str4;
        this.mComments = str5;
        this.mChecksumHash = str6;
        this.mMerchantId = str7;
    }
}
