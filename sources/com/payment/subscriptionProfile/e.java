package com.payment.subscriptionProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class e {
    @SerializedName("current_plan")
    @Expose
    private b a;
    @SerializedName("recommded_plan")
    @Expose
    private f b;
    @SerializedName("gaana_card")
    @Expose
    private d c;
    @SerializedName("expired_card")
    @Expose
    private c d;
    @SerializedName("card_position")
    @Expose
    private a e;

    public b a() {
        return this.a;
    }

    public f b() {
        return this.b;
    }

    public d c() {
        return this.c;
    }

    public c d() {
        return this.d;
    }

    public a e() {
        return this.e;
    }
}
