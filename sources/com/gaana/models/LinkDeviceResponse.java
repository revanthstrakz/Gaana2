package com.gaana.models;

import com.gaana.login.UserSubscriptionData;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class LinkDeviceResponse implements Serializable {
    private static final long serialVersionUID = 1;
    @SerializedName("message")
    private String message;
    @SerializedName("result")
    private String result;
    @SerializedName("status")
    private String status;
    @SerializedName("user_gplus_status")
    private UserSubscriptionData userSubscriptionData;

    public String getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public String getResult() {
        return this.result;
    }

    public UserSubscriptionData getUserSubscriptionData() {
        return this.userSubscriptionData;
    }
}
