package com.simpl.android.zeroClickSdk;

public class SimplZeroClickTokenAuthorization {
    private String zeroClickToken;
    private String zeroClickVerificationUrl;

    public SimplZeroClickTokenAuthorization(String str) {
        this.zeroClickToken = str;
    }

    public String getZeroClickToken() {
        return this.zeroClickToken;
    }

    public String getZeroClickVerificationUrl() {
        return this.zeroClickVerificationUrl;
    }

    public void setZeroClickVerificationUrl(String str) {
        this.zeroClickVerificationUrl = str;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("SimplZeroClickTokenAuthorization{mZeroClickToken='");
        stringBuilder.append(this.zeroClickToken);
        stringBuilder.append('\'');
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
