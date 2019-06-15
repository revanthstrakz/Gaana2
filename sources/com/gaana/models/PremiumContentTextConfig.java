package com.gaana.models;

import com.gaana.models.PaymentProductModel.ProductItem;
import com.google.gson.annotations.SerializedName;

public class PremiumContentTextConfig {
    @SerializedName("result")
    private PremiumTextConfig result;
    @SerializedName("status")
    private String status;

    public class CtaButtonText {
        @SerializedName("cta_txt")
        private String cta_txt;
        @SerializedName("msg_txt")
        private String msg_txt;

        public String getCta_txt() {
            return this.cta_txt;
        }

        public void setCta_txt(String str) {
            this.cta_txt = str;
        }

        public String getMsg_txt() {
            return this.msg_txt;
        }

        public void setMsg_txt(String str) {
            this.msg_txt = str;
        }
    }

    public class PremiumTextConfig {
        @SerializedName("ad_details")
        private CtaButtonText ad_details;
        @SerializedName("card_identifier")
        private String card_identifier;
        @SerializedName("gplus_details")
        private CtaButtonText gplus_details;
        @SerializedName("header_txt")
        private String header_txt;
        @SerializedName("pg_product")
        private ProductItem pg_product;

        public String getCard_identifier() {
            return this.card_identifier;
        }

        public void setCard_identifier(String str) {
            this.card_identifier = str;
        }

        public String getHeader_txt() {
            return this.header_txt;
        }

        public void setHeader_txt(String str) {
            this.header_txt = str;
        }

        public CtaButtonText getGplus_details() {
            return this.gplus_details;
        }

        public void setGplus_details(CtaButtonText ctaButtonText) {
            this.gplus_details = ctaButtonText;
        }

        public CtaButtonText getAd_details() {
            return this.ad_details;
        }

        public void setAd_details(CtaButtonText ctaButtonText) {
            this.ad_details = ctaButtonText;
        }

        public ProductItem getPg_product() {
            return this.pg_product;
        }

        public void setPg_product(ProductItem productItem) {
            this.pg_product = productItem;
        }
    }

    public String isStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public PremiumTextConfig getPremium_text_config() {
        return this.result;
    }

    public void setPremium_text_config(PremiumTextConfig premiumTextConfig) {
        this.result = premiumTextConfig;
    }
}
