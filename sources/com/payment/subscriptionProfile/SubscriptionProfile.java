package com.payment.subscriptionProfile;

import com.gaana.models.BusinessObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubscriptionProfile extends BusinessObject {
    @SerializedName("profile_card")
    @Expose
    private e a;

    public e a() {
        return this.a;
    }
}
