package com.gaana.models;

import android.text.TextUtils;
import com.constants.Constants;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class PaymentProductModel extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("banner")
    private ProductItem banner;
    @SerializedName("page_header")
    private PageHeader pageHeader;
    @SerializedName("product")
    private ProductItem product;
    @SerializedName("purchase")
    private Purchase purchase;

    public static class LayoutConfig extends BusinessObject {
        private static final long serialVersionUID = 1;
        @SerializedName("default_option")
        private int defaultOption;
        @SerializedName("seperate_tabs")
        private int seperateTabs;
        @SerializedName("tab_names_1")
        private String tabNames1;
        @SerializedName("tab_names_2")
        private String tabNames2;

        public boolean isSeperateTabs() {
            return this.seperateTabs == 1;
        }

        public String getTabNames1() {
            return this.tabNames1;
        }

        public String getTabNames2() {
            return this.tabNames2;
        }

        public int getDefaultOption() {
            return this.defaultOption;
        }
    }

    public class PageHeader extends BusinessObject {
        private static final long serialVersionUID = 1;
        @SerializedName("page_header_config")
        private PageHeaderConfig pageHeaderConfig;

        public PageHeaderConfig getPageHeaderConfig() {
            return this.pageHeaderConfig;
        }
    }

    public class PageHeaderConfig {
        @SerializedName("carousel_ad_img_url")
        private String carouselAdImgUrl;
        @SerializedName("carousel_img_url")
        private ArrayList<String> carouselImgUrl = null;
        @SerializedName("downgrade_msg")
        private String downgrade_msg;
        @SerializedName("footprint_id")
        private String footPrintId;
        @SerializedName("interval")
        private String interval;
        @SerializedName("is_carousel")
        private String isCarousel;
        @SerializedName("is_carousel_with_ad")
        private String isCarouselWithAd;
        @SerializedName("is_focus_enable")
        private String isFocusEnable;
        @SerializedName("is_downgrade_allowed")
        private String is_downgrade_allowed;
        @SerializedName("is_eligible_gtrial")
        private String is_eligible_gtrial;
        @SerializedName("is_upgrade_allowed")
        private String is_upgrade_allowed;
        @SerializedName("limit")
        private String limit;
        @SerializedName("page_desc")
        private String pageDesc;
        @SerializedName("page_text")
        private String pageText;
        @SerializedName("pg_product")
        private ArrayList<ProductItem> pg_productList;
        @SerializedName("previous_pack_duration")
        private int previous_pack_duration;
        @SerializedName("screen_design")
        private String screenDesign;
        @SerializedName("upgrade_msg")
        private String upgrade_msg;

        public int getPreviousPackDuration() {
            return this.previous_pack_duration;
        }

        public String getIs_upgrade_allowed() {
            return this.is_upgrade_allowed;
        }

        public String getIs_downgrade_allowed() {
            return this.is_downgrade_allowed;
        }

        public String getIs_Eligible_Gtrial() {
            return this.is_eligible_gtrial;
        }

        public String getIsCarouselWithAd() {
            return this.isCarouselWithAd;
        }

        public void setIsCarouselWithAd(String str) {
            this.isCarouselWithAd = str;
        }

        public String getCarouselAdImgUrl() {
            return this.carouselAdImgUrl;
        }

        public void setCarouselAdImgUrl(String str) {
            this.carouselAdImgUrl = str;
        }

        public boolean isCarousel() {
            return !TextUtils.isEmpty(this.isCarousel) && this.isCarousel.equals("1");
        }

        public boolean isFocusEnable() {
            return !TextUtils.isEmpty(this.isFocusEnable) && this.isFocusEnable.equals("1");
        }

        public void setIsCarousel(String str) {
            this.isCarousel = str;
        }

        public String getScreenDesign() {
            return this.screenDesign;
        }

        public void setScreenDesign(String str) {
            this.screenDesign = str;
        }

        public String getInterval() {
            return this.interval;
        }

        public void setInterval(String str) {
            this.interval = str;
        }

        public String getLimit() {
            return this.limit;
        }

        public void setLimit(String str) {
            this.limit = str;
        }

        public String getPageText() {
            return Constants.b(this.pageText);
        }

        public void setPageText(String str) {
            this.pageText = str;
        }

        public String getPageDesc() {
            return Constants.b(this.pageDesc);
        }

        public void setPageDesc(String str) {
            this.pageDesc = str;
        }

        public ArrayList<String> getCarouselImgUrl() {
            return this.carouselImgUrl;
        }

        public ArrayList<ProductItem> getProductList() {
            return this.pg_productList;
        }

        public String getFootPrintId() {
            return this.footPrintId;
        }

        public String getUpgrade_msg() {
            return this.upgrade_msg;
        }

        public String getDowngrade_msg() {
            return this.downgrade_msg;
        }
    }

    public static class ProductItem extends BusinessObject {
        private static final long serialVersionUID = 1;
        @SerializedName("action")
        private String action;
        @SerializedName("adyen_params")
        private ArrayList<AdyenParams> adyenParams;
        @SerializedName("bankcode")
        private String bankCode;
        @SerializedName("card_identifier")
        private String card_identifier;
        private String couponCode = "";
        @SerializedName("desc")
        private String desc;
        @SerializedName("desc_text")
        private String descText;
        @SerializedName("duration_days")
        private String duration_days;
        @SerializedName("header_text")
        private String headerText;
        @SerializedName("img")
        private String img;
        @SerializedName("in_application_secret")
        private String in_application_secret;
        @SerializedName("is_bundle_pack")
        private int isBundlePack;
        @SerializedName("is_focus")
        private int isFocus;
        @SerializedName("is_popular")
        private int isPopular;
        @SerializedName("is_recommended")
        private int isRecommended;
        @SerializedName("is_seperate")
        private int isSeperate;
        @SerializedName("is_webview")
        private String isWebView;
        @SerializedName("is_display")
        private String is_display;
        @SerializedName("is_si")
        private int is_si;
        @SerializedName("is_si_msg")
        private String is_si_msg;
        @SerializedName("is_trial")
        private String is_trial;
        @SerializedName("item_id")
        private String item_id;
        @SerializedName("more_desc")
        private String moreDesc;
        @SerializedName("more_text")
        private String moreText;
        @SerializedName("msg_txt")
        private String msg_txt;
        @SerializedName("o_id")
        private String o_id;
        @SerializedName("o_meta")
        private String o_meta;
        @SerializedName("o_name")
        private String o_name;
        @SerializedName("offer")
        private String offer;
        @SerializedName("offer_desc")
        private String offer_desc;
        @SerializedName("pg_logo")
        private String p_artwork;
        @SerializedName("p_code")
        private String p_code;
        @SerializedName("p_cost")
        private String p_cost;
        @SerializedName("p_cost_curr")
        private String p_cost_curr;
        @SerializedName("p_curr_code")
        private String p_curr_code;
        @SerializedName("p_desc")
        private String p_desc;
        @SerializedName("p_discounted_cost")
        private String p_discounted_cost;
        @SerializedName("p_discounted_text")
        private String p_discounted_text;
        @SerializedName("p_id")
        private String p_id;
        @SerializedName("p_mode")
        private String p_mode;
        @SerializedName("p_name")
        private String p_name;
        @SerializedName("p_orig_cost")
        private String p_orig_cost;
        @SerializedName("p_pay_desc")
        private String p_pay_desc;
        @SerializedName("p_payment_mode")
        private String p_payment_mode;
        @SerializedName("p_spec_offer")
        private String p_spec_offer;
        @SerializedName("p_tc")
        private String p_tc;
        @SerializedName("p_tc_title")
        private String p_tc_title;
        @SerializedName("p_type")
        private String p_type;
        @SerializedName("pg")
        private String paymentGateway;
        @SerializedName("payment_session_id")
        private String payment_session_id;
        @SerializedName("plan_type")
        private String plan_type;
        @SerializedName("popular_text")
        private String popularText;
        @SerializedName("recommended_text")
        private String recommendedText;
        @SerializedName("recommended_cta")
        private String recommended_cta;
        @SerializedName("saved_card_msg")
        private String saved_card_msg;
        @SerializedName("service_id")
        private String service_id;
        @SerializedName("tnc_html")
        private String tncHtml;
        @SerializedName("tnc_text")
        private String tnc_text;
        @SerializedName("tnc_url")
        private String tnc_url;
        @SerializedName("type_of_card")
        private String type_of_card;
        @SerializedName("url")
        private String url;
        @SerializedName("usr_cat_code")
        private String usr_cat_code;

        public static class AdyenParams extends BusinessObject {
            private static final long serialVersionUID = 1;
            @SerializedName("api_url")
            private String api_url;
            @SerializedName("fields")
            private ArrayList<Fields> fields;
            @SerializedName("pg_name")
            private String pg_name;
            @SerializedName("pg_type")
            private String pg_type;
            @SerializedName("webview_url")
            private String webview_url;

            public static class Fields extends BusinessObject {
                private static final long serialVersionUID = 1;
                @SerializedName("key")
                private String key;
                @SerializedName("optional")
                private boolean optional;
                @SerializedName("type")
                private String type;

                public String getType() {
                    return this.type;
                }

                public void setType(String str) {
                    this.type = str;
                }

                public String getKey() {
                    return this.key;
                }

                public void setKey(String str) {
                    this.key = str;
                }

                public boolean isOptional() {
                    return this.optional;
                }

                public void setOptional(boolean z) {
                    this.optional = z;
                }
            }

            public ArrayList<Fields> getFields() {
                return this.fields;
            }

            public void setFields(ArrayList<Fields> arrayList) {
                this.fields = arrayList;
            }

            public String getPg_type() {
                return this.pg_type;
            }

            public void setPg_type(String str) {
                this.pg_type = str;
            }

            public String getWebview_url() {
                return this.webview_url;
            }

            public void setWebview_url(String str) {
                this.webview_url = str;
            }

            public String getPg_name() {
                return this.pg_name;
            }

            public void setPg_name(String str) {
                this.pg_name = str;
            }

            public String getApi_url() {
                return this.api_url;
            }

            public void setApi_url(String str) {
                this.api_url = str;
            }
        }

        public String getP_code() {
            return this.p_code;
        }

        public String getSessionId() {
            return this.payment_session_id;
        }

        public boolean isWebView() {
            return Integer.parseInt(this.isWebView) == 1;
        }

        public String getPaymentGateway() {
            return this.paymentGateway;
        }

        public String getBankCode() {
            return this.bankCode;
        }

        public String getCard_identifier() {
            return this.card_identifier;
        }

        public void setCard_identifier(String str) {
            this.card_identifier = str;
        }

        public ArrayList<AdyenParams> getAdyenParams() {
            return this.adyenParams;
        }

        public void setAdyenParams(ArrayList<AdyenParams> arrayList) {
            this.adyenParams = arrayList;
        }

        public String getMsg_txt() {
            return this.msg_txt;
        }

        public void setMsg_txt(String str) {
            this.msg_txt = str;
        }

        public int getIs_si() {
            return this.is_si;
        }

        public String getSaved_card_msg() {
            return this.saved_card_msg;
        }

        public void setSaved_card_msg(String str) {
            this.saved_card_msg = str;
        }

        public void setItemId(String str) {
            this.item_id = str;
        }

        public void setProductId(String str) {
            this.p_id = str;
        }

        public void setPaymentMode(String str) {
            this.p_payment_mode = str;
        }

        public boolean shouldDisplay() {
            return this.is_display == null || !this.is_display.equalsIgnoreCase("0");
        }

        public String getTnc_url() {
            return this.tnc_url;
        }

        public String getRecommended_cta() {
            return this.recommended_cta;
        }

        public String getTnc_text() {
            return this.tnc_text;
        }

        public String getTncHtml() {
            return this.tncHtml;
        }

        public String getPlanType() {
            return this.plan_type;
        }

        public String getRecommendedText() {
            return Constants.b(this.recommendedText);
        }

        public String getPopularText() {
            return this.popularText;
        }

        public boolean isRecommended() {
            return this.isRecommended != 0;
        }

        public boolean isPopular() {
            return this.isPopular != 0;
        }

        public boolean isFocus() {
            return this.isFocus != 0;
        }

        public String getTypeOfCard() {
            return this.type_of_card;
        }

        public void setAction(String str) {
            this.action = str;
        }

        public void setDescription(String str) {
            this.desc = str;
        }

        public String getIn_application_secret() {
            return this.in_application_secret;
        }

        public String getService_id() {
            return this.service_id;
        }

        public String getCouponCode() {
            return this.couponCode;
        }

        public void setCouponCode(String str) {
            this.couponCode = str;
        }

        public String getImg() {
            return this.img;
        }

        public String getItem_id() {
            return this.item_id;
        }

        public String getDuration_days() {
            return this.duration_days;
        }

        public void setDurationDays(String str) {
            this.duration_days = str;
        }

        public String getUser_cat_code() {
            return this.usr_cat_code;
        }

        public String getP_id() {
            return this.p_id;
        }

        public String getAction() {
            return this.action;
        }

        public String getP_payment_mode() {
            if (this.p_payment_mode == null) {
                this.p_payment_mode = this.p_mode;
            }
            return this.p_payment_mode;
        }

        public String getP_desc() {
            return this.p_desc;
        }

        public String getP_cost() {
            return this.p_cost;
        }

        public void setP_Cost(String str) {
            this.p_cost = str;
        }

        public String getP_orig_cost() {
            return this.p_orig_cost;
        }

        public String getDesc() {
            return Constants.b(this.desc);
        }

        public String getOffer() {
            return this.offer;
        }

        public String getOffer_text() {
            return this.offer_desc;
        }

        public String getP_cost_curr() {
            return this.p_cost_curr;
        }

        public void setP_cost_curr(String str) {
            this.p_cost_curr = str;
        }

        public String getP_curr_code() {
            return this.p_curr_code;
        }

        public String getP_type() {
            return this.p_type;
        }

        public String getWeb_url() {
            return this.url;
        }

        public String getP_name() {
            return this.p_name;
        }

        public String getP_spec_offer() {
            return this.p_spec_offer;
        }

        public String getP_tc() {
            return this.p_tc;
        }

        public String getP_tc_title() {
            return this.p_tc_title;
        }

        public String getO_id() {
            return this.o_id;
        }

        public String getO_name() {
            return this.o_name;
        }

        public String getO_meta() {
            return this.o_meta;
        }

        public String getP_pay_desc() {
            return Constants.b(this.p_pay_desc);
        }

        public String getIs_trial() {
            return this.is_trial;
        }

        public String getP_discounted_cost() {
            return this.p_discounted_cost;
        }

        public String getP_discounted_text() {
            return Constants.b(this.p_discounted_text);
        }

        public String getP_coupon_code() {
            return this.p_code;
        }

        public void setNewCostAfterCoupon(String str) {
            this.p_orig_cost = this.p_cost;
            this.p_cost = str;
        }

        public String getProductArtwork() {
            if (TextUtils.isEmpty(getAtw())) {
                return this.p_artwork;
            }
            return getAtw();
        }

        public void setProductArtwork(String str) {
            this.p_artwork = str;
        }

        public void setP_code(String str) {
            this.p_code = str;
        }

        public String getIs_si_msg() {
            return this.is_si_msg;
        }

        public void setIs_si_msg(String str) {
            this.is_si_msg = str;
        }

        public String getHeaderText() {
            return this.headerText;
        }

        public String getDescText() {
            return this.descText;
        }

        public String getMoreText() {
            return this.moreText;
        }

        public String getMoreDesc() {
            return this.moreDesc;
        }

        public boolean getIsBundlePack() {
            return this.isBundlePack == 1;
        }

        public boolean getIsSeperate() {
            return this.isSeperate == 1;
        }
    }

    public class Purchase extends BusinessObject {
        private static final long serialVersionUID = 1;
        @SerializedName("offers")
        private ArrayList<ProductItem> offers;
        @SerializedName("plan_layout_config")
        private LayoutConfig planLayoutConfig;
        @SerializedName("products")
        private ArrayList<ProductItem> products;

        public ArrayList<ProductItem> getOffers() {
            return this.offers;
        }

        public LayoutConfig getPlanLayoutConfig() {
            return this.planLayoutConfig;
        }

        public ArrayList<ProductItem> getProducts() {
            return this.products;
        }
    }

    public ProductItem getDeepLinkingProduct() {
        return this.product;
    }

    public ProductItem getBanner() {
        return this.banner;
    }

    public Purchase getPurchase() {
        return this.purchase;
    }

    public PageHeader getPageHeader() {
        return this.pageHeader;
    }
}
