package com.gaana.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class UpdateServerResponse implements Serializable {
    private static final long serialVersionUID = 1;
    @SerializedName("status")
    private int status;
    @SerializedName("user_token_status")
    private String userTokenStatus;

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String getUserTokenStatus() {
        return this.userTokenStatus;
    }

    public void setUserTokenStatus(String str) {
        this.userTokenStatus = str;
    }
}
