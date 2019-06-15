package com.payment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class e {
    @SerializedName("value")
    @Expose
    private Integer a;
    @SerializedName("code")
    @Expose
    private String b;

    public Integer a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }
}
