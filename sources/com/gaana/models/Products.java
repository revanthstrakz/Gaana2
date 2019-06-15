package com.gaana.models;

import android.text.TextUtils;
import com.constants.Constants;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

public class Products extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("result")
    private ArrayList<Product> availableProducts;
    @SerializedName("message")
    private String message;
    @SerializedName("offers")
    private Offers offers;
    @SerializedName("status")
    private String status;

    public class Offers implements Serializable {
        @SerializedName("android")
        private String androidOffer;
        @SerializedName("android_title")
        private String androidOfferTitle;
        @SerializedName("android_url")
        private String androidOfferUrl;
        @SerializedName("android_is_offer")
        private String android_is_offer;
        @SerializedName("hermes_android")
        private String hermesOffer;
        @SerializedName("hermes_android_title")
        private String hermesOfferTitle;
        @SerializedName("hermes_android_url")
        private String hermesOfferUrl;
        @SerializedName("hermes_android_is_offer")
        private String hermes_is_offer;
        @SerializedName("paytm")
        private String paytmOffer;
        @SerializedName("paytm_title")
        private String paytmOfferTitle;
        @SerializedName("paytm_url")
        private String paytmOfferUrl;
        @SerializedName("paytm_is_offer")
        private String paytm_is_offer;

        public boolean isPaytmOfferEnabled() {
            return (TextUtils.isEmpty(this.paytm_is_offer) || this.paytm_is_offer.equals("0")) ? false : true;
        }

        public boolean isHermesOfferEnabled() {
            return (TextUtils.isEmpty(this.hermes_is_offer) || this.hermes_is_offer.equals("0")) ? false : true;
        }

        public boolean isAndroidOfferEnabled() {
            return (TextUtils.isEmpty(this.android_is_offer) || this.android_is_offer.equals("0")) ? false : true;
        }

        public String getAndroidOfferUrl() {
            return this.androidOfferUrl;
        }

        public String getPaytmOfferUrl() {
            return this.paytmOfferUrl;
        }

        public String getHermesOfferUrl() {
            return this.hermesOfferUrl;
        }

        public String getAndroidOfferTitle() {
            return this.androidOfferTitle;
        }

        public String getPaytmOfferTitle() {
            return this.paytmOfferTitle;
        }

        public String getHermesOfferTitle() {
            return this.hermesOfferTitle;
        }

        public String getAndroidOffer() {
            return this.androidOffer;
        }

        public String getPaytmOffer() {
            return this.paytmOffer;
        }

        public String getHermesOffer() {
            return this.hermesOffer;
        }
    }

    public static class Product extends BusinessObject implements Serializable {
        private static final long serialVersionUID = 1;
        @SerializedName("cost_currency")
        private String costCurrency;
        @SerializedName("in_application_secret")
        private String in_application_secret;
        @SerializedName("offer")
        private String offer = "0";
        @SerializedName("offer_text")
        private String offer_text;
        @SerializedName("payment_mode")
        private String paymentMode;
        @SerializedName("preference_order")
        private String preference_order;
        @SerializedName("cost")
        private String productCost;
        @SerializedName("duration")
        private String productDuration;
        @SerializedName("id")
        private String productId;
        @SerializedName("name")
        private String productName;
        @SerializedName("product_type")
        private String product_type;
        @SerializedName("recommended")
        private String recommended = "0";
        @SerializedName("recommended_text")
        private String recommended_text;
        @SerializedName("service_id")
        private String service_id;
        @SerializedName("strikeout_flag")
        private String strikeout_flag = "0";
        @SerializedName("strikeout_price")
        private String strikeout_price;

        public String getPaymentMode() {
            return this.paymentMode;
        }

        public String getCostCurrency() {
            return this.costCurrency;
        }

        public String getProductId() {
            return this.productId;
        }

        public String getProductType() {
            return this.product_type;
        }

        public String getProductName() {
            return Constants.b(this.productName);
        }

        public String getServiceId() {
            return this.service_id;
        }

        public String getInApplicationSecret() {
            return this.in_application_secret;
        }

        public String getProductDuration() {
            return this.productDuration;
        }

        public String getRecommended() {
            return this.recommended;
        }

        public String getRecommendedText() {
            return this.recommended_text;
        }

        public String getStrikeoutFlag() {
            return this.strikeout_flag;
        }

        public String getStrikeOutPrice() {
            return this.strikeout_price;
        }

        public String getOfferFlag() {
            return this.offer;
        }

        public String getOfferText() {
            return this.offer_text;
        }

        public String getPreferenceOrder() {
            return this.preference_order;
        }

        public String getProductCost() {
            return this.productCost;
        }
    }

    public Offers getOffers() {
        return this.offers;
    }

    public String getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public ArrayList<Product> getAvailableProducts() {
        return this.availableProducts;
    }
}
