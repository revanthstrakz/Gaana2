package com.payment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class g {
    @SerializedName("value")
    @Expose
    private Integer a;
    @SerializedName("code")
    @Expose
    private String b;
    @SerializedName("is_active")
    @Expose
    private Integer c;

    public Integer a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public Integer c() {
        return this.c;
    }
}
