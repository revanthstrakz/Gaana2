package com.gaana.models;

import com.google.gson.annotations.SerializedName;

public class FreedomPlanUserNotifyData extends BusinessObject {
    @SerializedName("message")
    private String message;
    @SerializedName("status")
    private int status;

    public void setStatus(int i) {
        this.status = i;
    }

    public int getStatus() {
        return this.status;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getMessage() {
        return this.message;
    }
}
