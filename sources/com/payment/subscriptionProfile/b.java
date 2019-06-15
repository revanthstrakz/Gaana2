package com.payment.subscriptionProfile;

import com.gaana.models.PaymentProductModel.ProductItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class b {
    @SerializedName("p_mode")
    @Expose
    private String a;
    @SerializedName("valid_upto")
    @Expose
    private long b;
    @SerializedName("cta_text")
    @Expose
    private String c;
    @SerializedName("plan_desc")
    @Expose
    private String d;
    @SerializedName("price_label")
    @Expose
    private String e;
    @SerializedName("renew")
    @Expose
    private String f;
    @SerializedName("plan_name")
    @Expose
    private String g;
    @SerializedName("desc")
    @Expose
    private String h;
    @SerializedName("card_identifier")
    @Expose
    private String i;
    @SerializedName("pg_product")
    @Expose
    private ProductItem j;

    public ProductItem a() {
        return this.j;
    }

    public String b() {
        return this.a;
    }

    public long c() {
        return this.b;
    }

    public String d() {
        return this.c;
    }

    public String e() {
        return this.d;
    }

    public String f() {
        return this.e;
    }

    public String g() {
        return this.f;
    }

    public String h() {
        return this.g;
    }

    public String i() {
        return this.h;
    }

    public String j() {
        return this.i;
    }
}
