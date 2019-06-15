package com.gaana.models;

import com.google.gson.annotations.SerializedName;

public class SessionLoginInfo extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("device_id")
    private String device_id;
    @SerializedName("webtoken")
    private String webtoken;

    public String getDevice_id() {
        return this.device_id;
    }

    public String getWebToken() {
        return this.webtoken;
    }
}
