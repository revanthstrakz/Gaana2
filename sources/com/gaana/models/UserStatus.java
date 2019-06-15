package com.gaana.models;

import android.text.TextUtils;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class UserStatus implements Serializable {
    private static final long serialVersionUID = 1;
    private int accountType = 0;
    private Date expiryDate = null;
    private long lastUpdateTime = -1;
    private ArrayList<LinkedDevice> linkedDevices = null;
    @SerializedName("message")
    private String message;
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private UserSubscriptionData userSubscriptionData;
    @SerializedName("uts")
    private String user_token_status;

    public static class LinkedDevice implements Serializable {
        private static final long serialVersionUID = 1;
        @SerializedName("deviceid")
        private String deviceId;
        @SerializedName("devicename")
        private String deviceName;

        public String getDeviceId() {
            return this.deviceId;
        }

        public String getDeviceName() {
            return this.deviceName;
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
    }

    public static class UserSubscriptionData implements Serializable {
        private static final long serialVersionUID = 1;
        @SerializedName("account")
        private String accountType;
        @SerializedName("devicescount")
        private String devicesCount;
        @SerializedName("gaana_plus_message")
        private String gaanaPlusMessage;
        @SerializedName("lastpayment")
        private String lastPayment;
        @SerializedName("last_payment_type")
        private String lastPaymentType;
        private String linkedDevices;
        @SerializedName("productInfo")
        private ProductInfo productInfo;
        @SerializedName("product_properties")
        private ProductProperties productProperties;
        @SerializedName("subscribedOn")
        private String subscribedOn;
        @SerializedName("subscription_type")
        private String subscriptionType;
        @SerializedName("validupto")
        private String validUpTo;

        public String getAccountType() {
            return this.accountType;
        }

        public void setAccountType(String str) {
            this.accountType = str;
        }

        public String getLastPaymentType() {
            return this.lastPaymentType;
        }

        public void setLastPaymentType(String str) {
            this.lastPaymentType = str;
        }

        public String getSubscriptionType() {
            return this.subscriptionType;
        }

        public String getGaanaPlusMessage() {
            return this.gaanaPlusMessage;
        }

        public ProductProperties getProductProperties() {
            return this.productProperties;
        }

        public ProductInfo getProductInfo() {
            return this.productInfo;
        }

        public String getSubscribedOn() {
            return this.subscribedOn;
        }

        public String getValidUpTo() {
            return this.validUpTo;
        }

        public String getLastPayment() {
            return this.lastPayment;
        }

        public String getDevices() {
            return this.linkedDevices;
        }

        public void setDevices(String str) {
            this.linkedDevices = str;
        }

        public String getDevicesCount() {
            return this.devicesCount;
        }
    }

    public String getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public UserSubscriptionData getUserSubscriptionData() {
        return this.userSubscriptionData;
    }

    public long getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public void setLastUpdateTime(long j) {
        this.lastUpdateTime = j;
    }

    public String getUserTokenStatus() {
        return this.user_token_status;
    }

    public Date getExpiryDate() {
        return this.expiryDate;
    }

    public void setExpiryDate(Date date) {
        this.expiryDate = date;
    }

    public ArrayList<LinkedDevice> getLinkedDevices() {
        return this.linkedDevices;
    }

    public void updateLinkedDevices() {
        if (getUserSubscriptionData() != null) {
            String devices = getUserSubscriptionData().getDevices();
            if (devices != null && !devices.equalsIgnoreCase("")) {
                this.linkedDevices = (ArrayList) new GsonBuilder().excludeFieldsWithModifiers(8, 4).create().fromJson(devices, new TypeToken<ArrayList<LinkedDevice>>() {
                }.getType());
            }
        }
    }

    public void updateExpiryDateAsPerServer() {
        if (getUserSubscriptionData() == null) {
            this.expiryDate = null;
        } else if (getUserSubscriptionData().getValidUpTo() != null) {
            this.expiryDate = new Date(Long.parseLong(getUserSubscriptionData().getValidUpTo()) * 1000);
        } else {
            this.expiryDate = null;
        }
    }

    public void updateAccountType() {
        if (getUserSubscriptionData() == null) {
            this.accountType = 0;
        } else if (getUserSubscriptionData().getAccountType() == null) {
            this.accountType = 1;
        } else if (getUserSubscriptionData().getAccountType().equalsIgnoreCase("free")) {
            this.accountType = 1;
        } else if (getUserSubscriptionData().getAccountType().equalsIgnoreCase("paid")) {
            this.accountType = 3;
        } else if (getUserSubscriptionData().getAccountType().equalsIgnoreCase("trial")) {
            this.accountType = 2;
        }
    }

    public int getAccountType() {
        if (this.accountType == 2 || this.accountType == 3) {
            if (this.expiryDate.before(new Date())) {
                this.accountType = 1;
            }
        }
        return this.accountType;
    }

    public void setAccountType(int i) {
        this.accountType = i;
    }
}
