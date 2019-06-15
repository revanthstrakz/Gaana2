package com.gaana.login;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class UserStatusInfo implements Serializable {
    private static final long serialVersionUID = 1;
    @SerializedName("message")
    private String message;
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private UserSubscriptionData userSubscriptionData;
    @SerializedName("uts")
    private String user_token_status;

    public String getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public UserSubscriptionData getUserSubscriptionData() {
        return this.userSubscriptionData;
    }

    public String getUserTokenStatus() {
        return this.user_token_status;
    }
}
