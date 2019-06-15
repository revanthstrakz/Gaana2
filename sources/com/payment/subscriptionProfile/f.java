package com.payment.subscriptionProfile;

import com.gaana.models.PaymentProductModel.ProductItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class f {
    @SerializedName("plan_desc")
    private String a;
    @SerializedName("cta_text")
    @Expose
    private String b;
    @SerializedName("title")
    @Expose
    private String c;
    @SerializedName("card_desc")
    @Expose
    private String d;
    @SerializedName("sub_text")
    @Expose
    private String e;
    @SerializedName("tnc_text")
    @Expose
    private String f;
    @SerializedName("tnc_url")
    @Expose
    private String g;
    @SerializedName("pg_product")
    @Expose
    private ProductItem h;
    @SerializedName("card_identifier")
    @Expose
    private String i;

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }

    public String f() {
        return this.f;
    }

    public String g() {
        return this.g;
    }

    public ProductItem h() {
        return this.h;
    }

    public String i() {
        return this.i;
    }
}
