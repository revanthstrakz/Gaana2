package com.gaana.models;

import com.constants.Constants;
import com.google.gson.annotations.SerializedName;

public class Uber {
    private static final long serialVersionUID = 1;
    @SerializedName("isUberConnected")
    private String isUberConnected;
    @SerializedName("message")
    private String message;

    public String getMessage() {
        return Constants.b(this.message);
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getIsUberConnected() {
        return this.isUberConnected;
    }

    public void setIsUberConnected(String str) {
        this.isUberConnected = str;
    }
}
