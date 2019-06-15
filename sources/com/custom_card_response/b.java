package com.custom_card_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class b {
    @SerializedName("is_active")
    @Expose
    private Integer a;
    @SerializedName("hd_quality")
    @Expose
    private Integer b;

    public Integer a() {
        return this.a;
    }

    public Integer b() {
        return this.b;
    }
}
