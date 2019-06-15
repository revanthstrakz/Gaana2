package com.models;

import android.text.TextUtils;
import com.gaana.models.BusinessObject;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class CouponProducts extends BusinessObject {
    @SerializedName("product_gateway")
    private ArrayList<ProductGateway> a;

    public static class PaymentGateway extends BusinessObject {
        @SerializedName("p_cost")
        private String a;
        @SerializedName("p_cost_curr")
        private String b;
        @SerializedName("p_id")
        private String c;
        @SerializedName("p_payment_mode")
        private String d;
        @SerializedName("saved_card_msg")
        private String e;
        @SerializedName("p_pay_desc")
        private String f;
        @SerializedName("default_msg")
        private String g;
        @SerializedName("is_si_msg")
        private String h;

        public String a() {
            return this.g;
        }

        public String b() {
            return this.d;
        }

        public String c() {
            return this.a;
        }

        public String d() {
            return this.f;
        }

        public String e() {
            return this.c;
        }

        public String f() {
            return this.h;
        }

        public String g() {
            return this.b;
        }

        public String h() {
            return this.e;
        }
    }

    public static class ProductGateway extends BusinessObject {
        @SerializedName("item_id")
        private String a;
        @SerializedName("plan_subtitle")
        private String b;
        @SerializedName("action")
        private String c;
        @SerializedName("plan_subtitle_msg")
        private String d;
        @SerializedName("is_default")
        private String e;
        @SerializedName("desc")
        private String f;
        @SerializedName("payment_gateway")
        private ArrayList<PaymentGateway> g;

        public ArrayList<PaymentGateway> a() {
            return this.g;
        }

        public String b() {
            return this.f;
        }

        public String c() {
            return this.c;
        }

        public String d() {
            return this.b;
        }

        public String e() {
            return this.a;
        }

        public String f() {
            return this.d;
        }

        public boolean g() {
            return !TextUtils.isEmpty(this.e) && this.e.equalsIgnoreCase("1");
        }
    }

    public ArrayList<ProductGateway> a() {
        return this.a;
    }
}
