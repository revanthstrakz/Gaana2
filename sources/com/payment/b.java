package com.payment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class b {
    @SerializedName("in_a_day")
    @Expose
    private c a;
    @SerializedName("in_a_session")
    @Expose
    private d b;

    public c a() {
        return this.a;
    }

    public d b() {
        return this.b;
    }
}
