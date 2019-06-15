package com.custom_card_response;

import com.constants.Constants;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class a {
    @SerializedName("card_identifier")
    @Expose
    private String a;
    @SerializedName("cta_text")
    @Expose
    private String b;
    @SerializedName("sub_text")
    @Expose
    private String c;
    @SerializedName("payment_mode")
    @Expose
    private String d;
    @SerializedName("pg_product")
    @Expose
    private ProductItem e;
    @SerializedName("tnc_text")
    @Expose
    private String f;
    @SerializedName("tnc_url")
    @Expose
    private String g;
    @SerializedName("inapp_text")
    @Expose
    private String h;
    @SerializedName("inapp_image")
    @Expose
    private String i;

    public String a() {
        return Constants.b(this.h);
    }

    public String b() {
        return this.i;
    }

    public String c() {
        return Constants.b(this.f);
    }

    public String d() {
        return this.g;
    }

    public String e() {
        return this.a;
    }

    public String f() {
        return Constants.b(this.b);
    }

    public String g() {
        return Constants.b(this.c);
    }

    public String h() {
        return this.d;
    }

    public ProductItem i() {
        return this.e;
    }
}
