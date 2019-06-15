package com.payment;

import com.gaana.models.BusinessObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaytmRenewal extends BusinessObject {
    @SerializedName("title")
    @Expose
    private String a;
    @SerializedName("expires_on")
    @Expose
    private long b;
    @SerializedName("cta_text")
    @Expose
    private a c;
    @SerializedName("grace_period")
    @Expose
    private long d;
    @SerializedName("first_day")
    @Expose
    private long e;
    @SerializedName("fcap")
    @Expose
    private b f;
    @SerializedName("interval")
    @Expose
    private Integer g;
    @SerializedName("max_limit")
    @Expose
    private Integer h;
    @SerializedName("message")
    @Expose
    private String i;
    @SerializedName("status")
    @Expose
    private Integer j;
    @SerializedName("access_key")
    @Expose
    private String k;

    public String a() {
        return this.a;
    }

    public long b() {
        return this.b;
    }

    public a c() {
        return this.c;
    }

    public long d() {
        return this.d;
    }

    public long e() {
        return this.e;
    }

    public b f() {
        return this.f;
    }

    public Integer g() {
        return this.g;
    }

    public Integer h() {
        return this.h;
    }

    public String i() {
        return this.i;
    }

    public Integer j() {
        return this.j;
    }

    public String k() {
        return this.k;
    }
}
