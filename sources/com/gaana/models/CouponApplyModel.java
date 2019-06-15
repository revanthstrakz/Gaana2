package com.gaana.models;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class CouponApplyModel extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("c_msg")
    private String c_msg;
    @SerializedName("c_success")
    private String c_success;
    @SerializedName("p_list")
    private ArrayList<ProductCouponItem> p_list;

    public class ProductCouponItem extends BusinessObject {
        private static final long serialVersionUID = 1;
        @SerializedName("p_cost_curr")
        private String p_cost_curr;
        @SerializedName("p_id")
        private String p_id;
        @SerializedName("p_new_cost")
        private String p_new_cost;

        public String getP_id() {
            return this.p_id;
        }

        public String getP_new_cost() {
            return this.p_new_cost;
        }

        public String getP_cost_curr() {
            return this.p_cost_curr;
        }
    }

    public String getC_msg() {
        return this.c_msg;
    }

    public String getC_success() {
        return this.c_success;
    }

    public ArrayList<ProductCouponItem> getP_list() {
        return this.p_list;
    }
}
