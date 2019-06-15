package com.payment.subscriptionProfile;

import com.gaana.models.PaymentProductModel.ProductItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class c {
    @SerializedName("cta_text")
    @Expose
    private String a;
    @SerializedName("payment_time")
    @Expose
    private Integer b;
    @SerializedName("plan_desc")
    @Expose
    private String c;
    @SerializedName("price_label")
    @Expose
    private String d;
    @SerializedName("renew")
    @Expose
    private String e;
    @SerializedName("pg_product")
    @Expose
    private ProductItem f;
    @SerializedName("card_identifier")
    @Expose
    private String g;
    @SerializedName("p_mode")
    private String h;

    public String a() {
        return this.h;
    }

    public String b() {
        return this.a;
    }

    public Integer c() {
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

    public ProductItem g() {
        return this.f;
    }

    public String h() {
        return this.g;
    }
}
