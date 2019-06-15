package com.gaana.models;

import com.google.gson.annotations.SerializedName;

public class UserJourneyResponse extends BusinessObject {
    @SerializedName("error")
    private String error;
    @SerializedName("status")
    private String status;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String str) {
        this.error = str;
    }
}
