package com.gaana.models;

import com.constants.Constants;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class PaymentProductDetailModel extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("pg_offer_dtls")
    private CarouselOfferDetails carouselOfferDetails;
    @SerializedName("coupon_applicable")
    private int coupon_applicable;
    @SerializedName("more_pg")
    private ArrayList<ProductItem> more_pg;
    @SerializedName("offer")
    private OfferDetail offer;
    @SerializedName("payment_sess_id")
    private String payment_sess_id;
    @SerializedName("preferred_pg")
    private ArrayList<ProductItem> preferred_pg;
    @SerializedName("product_desc")
    private ProductDescription product_desc;

    public class CarouselOfferConfig extends BusinessObject {
        private static final long serialVersionUID = 1;
        @SerializedName("app_dpl_link")
        private String appDeepLink;
        @SerializedName("pg_product")
        private ProductItem offerProduct;
        @SerializedName("offer_url")
        private String offerUrl;

        public String getAppDeepLink() {
            return this.appDeepLink;
        }

        public String getOfferUrl() {
            return this.offerUrl;
        }

        public ProductItem getOfferProduct() {
            return this.offerProduct;
        }
    }

    public class CarouselOfferDetails extends BusinessObject {
        private static final long serialVersionUID = 1;
        @SerializedName("pg_config")
        private ArrayList<CarouselOfferConfig> arrCarouselOfferConfig;
        @SerializedName("is_carousel")
        private int isCarousel;

        public ArrayList<CarouselOfferConfig> getArrCarouselOfferConfig() {
            return this.arrCarouselOfferConfig;
        }

        public boolean isCarousel() {
            return this.isCarousel == 1;
        }
    }

    public class OfferDetail extends BusinessObject {
        private static final long serialVersionUID = 1;
        @SerializedName("msg")
        private String msg;
        @SerializedName("t_c_text")
        private String t_c_text;
        @SerializedName("url")
        private String url;

        public String getMsg() {
            return this.msg;
        }

        public String getUrl() {
            return this.url;
        }

        public String getT_c_text() {
            return this.t_c_text;
        }
    }

    public class ProductDescription extends BusinessObject {
        private static final long serialVersionUID = 1;
        @SerializedName("coupon_applicable")
        private int coupon_applicable;
        @SerializedName("message")
        private String message;
        @SerializedName("payment_options")
        private ArrayList<ProductItem> payment_options;

        public OfferDetail getOffer() {
            return PaymentProductDetailModel.this.offer;
        }

        public ArrayList<ProductItem> getPayment_options() {
            return this.payment_options;
        }

        public int getCoupon_applicable() {
            return this.coupon_applicable;
        }

        public String getMessage() {
            return Constants.b(this.message);
        }
    }

    public ProductDescription getProduct_desc() {
        return this.product_desc;
    }

    public CarouselOfferDetails getCarouselOfferDetails() {
        return this.carouselOfferDetails;
    }

    public OfferDetail getOffer() {
        return this.offer;
    }

    public void setOffer(OfferDetail offerDetail) {
        this.offer = offerDetail;
    }

    public String getpaymentSessionId() {
        return this.payment_sess_id;
    }

    public ArrayList<ProductItem> getMore_pg() {
        return this.more_pg;
    }

    public void setMore_pg(ArrayList<ProductItem> arrayList) {
        this.more_pg = arrayList;
    }

    public ArrayList<ProductItem> getPreferred_pg() {
        return this.preferred_pg;
    }

    public void setPreferred_pg(ArrayList<ProductItem> arrayList) {
        this.preferred_pg = arrayList;
    }

    public int getCoupon_applicable() {
        return this.coupon_applicable;
    }

    public void setCoupon_applicable(int i) {
        this.coupon_applicable = i;
    }
}
