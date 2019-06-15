package com.payment.subscriptionProfile;

import com.gaana.models.PaymentProductModel.ProductItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class d {
    @SerializedName("cta_text")
    @Expose
    private String a;
    @SerializedName("title")
    @Expose
    private String b;
    @SerializedName("card_desc")
    @Expose
    private String c;
    @SerializedName("sub_text")
    @Expose
    private String d;
    @SerializedName("tnc_text")
    @Expose
    private String e;
    @SerializedName("tnc_url")
    @Expose
    private String f;
    @SerializedName("pg_product")
    @Expose
    private ProductItem g;
    @SerializedName("card_identifier")
    @Expose
    private String h;

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

    public ProductItem g() {
        return this.g;
    }

    public String h() {
        return this.h;
    }
}
