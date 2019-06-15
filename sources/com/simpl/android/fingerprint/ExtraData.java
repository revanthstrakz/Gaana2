package com.simpl.android.fingerprint;

public class ExtraData {
    private String callBackKey;
    private String endTime;
    private String[] senderList;
    private String startTime;

    public ExtraData(String str, String str2, String[] strArr) {
        this.startTime = str;
        this.endTime = str2;
        this.senderList = strArr;
    }

    public String getCallBackKey() {
        return this.callBackKey;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public String[] getSenderList() {
        return this.senderList;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setCallBackKey(String str) {
        this.callBackKey = str;
    }
}
