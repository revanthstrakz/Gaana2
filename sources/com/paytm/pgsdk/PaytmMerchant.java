package com.paytm.pgsdk;

public class PaytmMerchant {
    public String mChannelId;
    public String mChecksumGenerationURL;
    public String mChecksumVerificationURL;
    public String mIndustryTypeId;
    public String mMerchantIdentifier;
    public String mTheme;
    public String mWebsite;

    public PaytmMerchant(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.mMerchantIdentifier = str;
        this.mChannelId = str2;
        this.mIndustryTypeId = str3;
        this.mWebsite = str4;
        this.mTheme = str5;
        this.mChecksumGenerationURL = str6;
        this.mChecksumVerificationURL = str7;
    }

    public PaytmMerchant(String str, String str2) {
        this.mChecksumGenerationURL = str;
        this.mChecksumVerificationURL = str2;
    }
}
