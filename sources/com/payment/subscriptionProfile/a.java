package com.payment.subscriptionProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class a {
    @SerializedName("current_plan")
    @Expose
    private String a;
    @SerializedName("recommded_plan")
    @Expose
    private String b;
    @SerializedName("gaana_card")
    @Expose
    private String c;
    @SerializedName("expired_card")
    private String d;

    public String a() {
        return this.d;
    }

    public String b() {
        return this.a;
    }

    public String c() {
        return this.b;
    }

    public String d() {
        return this.c;
    }
}
