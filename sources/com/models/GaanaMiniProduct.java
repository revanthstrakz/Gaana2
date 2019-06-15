package com.models;

import com.gaana.models.BusinessObject;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class GaanaMiniProduct extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("payment_options")
    private ArrayList<ProductItem> a;
    @SerializedName("entity_detail")
    private GaanaMiniProductDetail b;
    @SerializedName("msg")
    private String c;

    public class GaanaMiniProductDetail extends BusinessObject {
        private static final long serialVersionUID = 1;
        @SerializedName("title")
        private String a;
        @SerializedName("desc")
        private String b;
        @SerializedName("artwork")
        private String c;
        @SerializedName("entity_type")
        private String d;
        @SerializedName("entity_id")
        private String e;
        @SerializedName("validity")
        private String f;
        @SerializedName("price")
        private String g;
        @SerializedName("add_benefits")
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

        public String g() {
            return this.g;
        }

        public String h() {
            return this.h;
        }
    }

    public ArrayList<ProductItem> a() {
        return this.a;
    }

    public GaanaMiniProductDetail b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }
}
