package com.payment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class d {
    @SerializedName("is_active")
    @Expose
    private Integer a;
    @SerializedName("count")
    @Expose
    private Integer b;

    public Integer a() {
        return this.a;
    }

    public Integer b() {
        return this.b;
    }
}
