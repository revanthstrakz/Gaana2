package com.custom_card_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class c {
    @SerializedName("is_active")
    @Expose
    private Integer a;
    @SerializedName("starting_from")
    @Expose
    private Integer b;
    @SerializedName("end_date")
    @Expose
    private Integer c;

    public Integer a() {
        return this.a;
    }

    public Integer b() {
        return this.b;
    }

    public Integer c() {
        return this.c;
    }
}
