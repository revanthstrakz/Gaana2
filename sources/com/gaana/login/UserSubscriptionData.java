package com.gaana.login;

import android.text.TextUtils;
import com.constants.c.c;
import com.google.gson.annotations.SerializedName;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class UserSubscriptionData implements Serializable {
    private static final long serialVersionUID = 1;
    private int accountType = 0;
    @SerializedName("app_notified")
    private String appNotified;
    @SerializedName("app_notify_text")
    private String appNotifyText;
    @SerializedName("devicelinked")
    private String devicelinked;
    @SerializedName("dcnt")
    private String devicesCount;
    @SerializedName("disable_download")
    private ArrayList<DisableDownloadId> disable_download;
    @SerializedName("educate_notify")
    private String educate_notify = "1";
    private Date expiryDate = null;
    private Date expiryDateWithGrace = null;
    @SerializedName("gaana_plus_mini_info")
    private ArrayList<GaanaMiniSubDetails> gaanaMiniSubDetails;
    @SerializedName("grace_msg")
    private String gaanaPlusGraceMessage;
    @SerializedName("gpmsg")
    private String gaanaPlusMessage;
    @SerializedName("is_family_owner")
    private int isFamilyOwner = 0;
    @SerializedName("lastpayment")
    private String lastPayment;
    @SerializedName("last_payment_type")
    private String lastPaymentType;
    private long lastUpdateTime = -1;
    @SerializedName("m_code")
    private int m_code;
    @SerializedName("message")
    private String message;
    @SerializedName("productInfo")
    private ProductInfo productInfo;
    @SerializedName("product_properties")
    private ProductProperties productProperties;
    @SerializedName("redirection_url")
    private String redirection_url;
    @SerializedName("ac")
    private String serverAccountType;
    @SerializedName("subson")
    private String subscribedOn;
    @SerializedName("subtype")
    private String subscriptionType;
    @SerializedName("validupto")
    private String validUpTo;
    @SerializedName("validity_with_grace")
    private String validWithGrace;
    @SerializedName("is_simpl")
    private String zero_click_token = "";

    public static class DisableDownloadId implements Serializable {
        @SerializedName("vgid")
        private String vgid;

        public String getVgid() {
            return this.vgid;
        }

        public void setVgid(String str) {
            this.vgid = str;
        }
    }

    public static class ProductInfo implements Serializable {
        @SerializedName("con")
        private String con;
        @SerializedName("cost")
        private String cost;
        @SerializedName("costcur")
        private String costcurrent;
        @SerializedName("dr")
        private String duration;
        @SerializedName("g_sku_id")
        private String google_skuid;
        @SerializedName("playstore_product_id")
        private String playstore_product_id;
        @SerializedName("name")
        private String productName;

        public String getCost() {
            return this.cost;
        }

        public String getPlaystore_product_id() {
            return this.playstore_product_id;
        }

        public String getProductName() {
            return this.productName;
        }

        public String getCostcurrent() {
            return this.costcurrent;
        }

        public String getCon() {
            return this.con;
        }

        public String getDuration() {
            return this.duration;
        }

        public String getGoogle_skuid() {
            return this.google_skuid;
        }
    }

    public static class ProductProperties implements Serializable {
        private static final long serialVersionUID = 1;
        @SerializedName("audio_ads")
        private String audioAds;
        @SerializedName("devlimit")
        private String deviceLimit;
        @SerializedName("display_ads")
        private String displayAds;
        @SerializedName("download_enable")
        private String downloadEnable;
        @SerializedName("hdstream")
        private String high_quality_stream;
        @SerializedName("interstitials_ads")
        private String interstitialsAds;
        @SerializedName("is_device_linking_enabled")
        private String isDeviceLinkingEnabled;
        @SerializedName("premium_content")
        private PremiumContent premium_content;
        @SerializedName("prodtype")
        private String productType;
        @SerializedName("product_type_name")
        private String product_type_name;
        @SerializedName("songlimit")
        private String songLimit;

        public static class PremiumContent implements Serializable {
            private static final long serialVersionUID = 1;
            @SerializedName("is_pc_enable")
            private int is_pc_enable;
            @SerializedName("pc_threshold_limit")
            private int pc_threshold_limit;

            public int getIs_pc_enable() {
                return this.is_pc_enable;
            }

            public void setIs_pc_enable(int i) {
                this.is_pc_enable = i;
            }

            public int getPc_threshold_limit() {
                return this.pc_threshold_limit;
            }

            public void setPc_threshold_limit(int i) {
                this.pc_threshold_limit = i;
            }
        }

        public String getProductType() {
            return this.productType;
        }

        public boolean isAdEnabled() {
            return TextUtils.isEmpty(this.displayAds) || this.displayAds.equalsIgnoreCase(AvidJSONUtil.KEY_Y);
        }

        public boolean isHighQualityStreamingEnabled() {
            return TextUtils.isEmpty(this.high_quality_stream) || this.high_quality_stream.equalsIgnoreCase(AvidJSONUtil.KEY_Y);
        }

        public boolean isInterstitialAdEnabled() {
            return TextUtils.isEmpty(this.interstitialsAds) || this.interstitialsAds.equalsIgnoreCase(AvidJSONUtil.KEY_Y);
        }

        public boolean isAudioAdEnabled() {
            return TextUtils.isEmpty(this.audioAds) || this.audioAds.equalsIgnoreCase(AvidJSONUtil.KEY_Y);
        }

        public int getDeviceLimit() {
            if (TextUtils.isEmpty(this.deviceLimit)) {
                this.deviceLimit = String.valueOf(5);
            }
            return Integer.parseInt(this.deviceLimit);
        }

        public boolean isDownloadEnabled() {
            return !TextUtils.isEmpty(this.downloadEnable) && this.downloadEnable.equalsIgnoreCase(AvidJSONUtil.KEY_Y);
        }

        public String getProduct_type_name() {
            return this.product_type_name;
        }

        public void setProduct_type_name(String str) {
            this.product_type_name = str;
        }

        public PremiumContent getPremium_content() {
            return this.premium_content;
        }

        public void setPremium_content(PremiumContent premiumContent) {
            this.premium_content = premiumContent;
        }

        public String getSongLimit() {
            return this.songLimit;
        }

        public boolean getIsDeviceLinkingEnabled() {
            return !TextUtils.isEmpty(this.isDeviceLinkingEnabled) && this.isDeviceLinkingEnabled.equalsIgnoreCase("1");
        }
    }

    public ArrayList<GaanaMiniSubDetails> getGaanaMiniSubDetails() {
        return this.gaanaMiniSubDetails;
    }

    public ArrayList<DisableDownloadId> getDisable_download() {
        return this.disable_download;
    }

    public void setDisable_download(ArrayList<DisableDownloadId> arrayList) {
        this.disable_download = arrayList;
    }

    public int getM_code() {
        return this.m_code;
    }

    public String getServerAccountType() {
        return this.serverAccountType;
    }

    public void setServerAccountType(String str) {
        this.serverAccountType = str;
    }

    public String getMessage() {
        return this.message;
    }

    public String getLastPaymentType() {
        return this.lastPaymentType;
    }

    public void setLastPaymentType(String str) {
        this.lastPaymentType = str;
    }

    public String getRedirectionUrl() {
        return this.redirection_url;
    }

    public String getZeroClickToken() {
        return this.zero_click_token;
    }

    public boolean getIsFamilyOwner() {
        return this.isFamilyOwner == 1;
    }

    public ProductInfo getProductInfo() {
        return this.productInfo;
    }

    public String getSubscriptionType() {
        return this.subscriptionType;
    }

    public String getGaanaPlusMessage() {
        return this.gaanaPlusMessage;
    }

    public String getGaanaPlusGraceMessage() {
        return this.gaanaPlusGraceMessage;
    }

    public ProductProperties getProductProperties() {
        return this.productProperties;
    }

    public String getSubscribedOn() {
        return this.subscribedOn;
    }

    public String getValidUpTo() {
        return this.validUpTo;
    }

    public String getValidWithGrace() {
        return this.validWithGrace;
    }

    public String getLastPayment() {
        return this.lastPayment;
    }

    public String getDevicesCount() {
        return this.devicesCount;
    }

    public boolean isDeviceLinked() {
        return (TextUtils.isEmpty(this.devicelinked) || this.devicelinked.equalsIgnoreCase("N")) ? false : true;
    }

    public void setDevicelinked(boolean z) {
        if (z) {
            this.devicelinked = "Y";
        } else {
            this.devicelinked = "N";
        }
    }

    public long getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public void setLastUpdateTime(long j) {
        this.lastUpdateTime = j;
    }

    public Date getExpiryDate() {
        return this.expiryDate;
    }

    public void setExpiryDate(Date date) {
        this.expiryDate = date;
    }

    public void updateExpiryDateAsPerServer() {
        if (this.validUpTo != null) {
            this.expiryDate = new Date(Long.parseLong(this.validUpTo) * 1000);
        } else {
            this.expiryDate = null;
        }
    }

    public Date getExpiryDateWithGrace() {
        return this.expiryDateWithGrace;
    }

    public void setExpiryDateWithGrace(Date date) {
        this.expiryDateWithGrace = date;
    }

    public void updateExpiryDateWithGrace() {
        if (this.validWithGrace != null) {
            this.expiryDateWithGrace = new Date(Long.parseLong(this.validWithGrace) * 1000);
        } else {
            this.expiryDateWithGrace = null;
        }
    }

    public void updateAccountType() {
        if (this.serverAccountType == null) {
            this.accountType = 1;
        } else if (this.serverAccountType.equalsIgnoreCase("free")) {
            this.accountType = 1;
        } else if (this.serverAccountType.equalsIgnoreCase("paid")) {
            this.accountType = 3;
        } else if (this.serverAccountType.equalsIgnoreCase("trial")) {
            this.accountType = 2;
        }
    }

    public int getAccountType() {
        if (this.accountType == 2 || this.accountType == 3) {
            Date date = new Date();
            if (this.expiryDate != null && this.expiryDate.before(date)) {
                if (this.accountType != 3 || this.expiryDateWithGrace == null || this.expiryDateWithGrace.before(date)) {
                    this.accountType = 1;
                } else {
                    this.accountType = 3;
                }
            }
        }
        return this.accountType;
    }

    public void setAccountType(int i) {
        this.accountType = i;
    }

    public String getMiniPacks() {
        String str = "";
        ArrayList gaanaMiniSubDetails = getGaanaMiniSubDetails();
        if (gaanaMiniSubDetails != null && gaanaMiniSubDetails.size() > 0) {
            int i = 0;
            while (i < gaanaMiniSubDetails.size()) {
                StringBuilder stringBuilder;
                if (((GaanaMiniSubDetails) gaanaMiniSubDetails.get(i)).getEntityType().equalsIgnoreCase(c.b) || ((GaanaMiniSubDetails) gaanaMiniSubDetails.get(i)).getEntityType().equalsIgnoreCase(c.a)) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append(",");
                    stringBuilder.append(((GaanaMiniSubDetails) gaanaMiniSubDetails.get(i)).getEntityId());
                    str = stringBuilder.toString();
                }
                if (((GaanaMiniSubDetails) gaanaMiniSubDetails.get(i)).getEntityType().equalsIgnoreCase(c.d)) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append(",");
                    stringBuilder.append(((GaanaMiniSubDetails) gaanaMiniSubDetails.get(i)).getPlaylistId());
                    str = stringBuilder.toString();
                }
                i++;
            }
        }
        return !TextUtils.isEmpty(str) ? str.replaceFirst(",", "") : str;
    }

    public String getMiniPackIdsForDownload() {
        String str = "";
        ArrayList gaanaMiniSubDetails = getGaanaMiniSubDetails();
        if (gaanaMiniSubDetails != null && gaanaMiniSubDetails.size() > 0) {
            int i = 0;
            while (i < gaanaMiniSubDetails.size()) {
                StringBuilder stringBuilder;
                if (((GaanaMiniSubDetails) gaanaMiniSubDetails.get(i)).getEntityType().equalsIgnoreCase(c.b) || ((GaanaMiniSubDetails) gaanaMiniSubDetails.get(i)).getEntityType().equalsIgnoreCase(c.a)) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append(",");
                    stringBuilder.append(((GaanaMiniSubDetails) gaanaMiniSubDetails.get(i)).getEntityType());
                    stringBuilder.append(((GaanaMiniSubDetails) gaanaMiniSubDetails.get(i)).getEntityId());
                    str = stringBuilder.toString();
                }
                if (((GaanaMiniSubDetails) gaanaMiniSubDetails.get(i)).getEntityType().equalsIgnoreCase(c.d)) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append(",");
                    stringBuilder.append(((GaanaMiniSubDetails) gaanaMiniSubDetails.get(i)).getEntityType());
                    stringBuilder.append(((GaanaMiniSubDetails) gaanaMiniSubDetails.get(i)).getPlaylistId());
                    str = stringBuilder.toString();
                }
                i++;
            }
        }
        return !TextUtils.isEmpty(str) ? str.replaceFirst(",", "") : str;
    }

    public String isAppNotified() {
        return this.appNotified;
    }

    public String getAppNotifyText() {
        return this.appNotifyText;
    }

    public boolean isEducate_notify() {
        return !TextUtils.isEmpty(this.educate_notify) && this.educate_notify.equals("1");
    }

    public void setProductProperties(ProductProperties productProperties) {
        this.productProperties = productProperties;
    }
}
