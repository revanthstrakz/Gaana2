package com.gaana.login;

import android.text.TextUtils;
import com.gaana.models.User.LoginType;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1;
    @SerializedName("callType")
    private String callType;
    private boolean deviceLinkLimitReached = false;
    @SerializedName("Error")
    private String error;
    @SerializedName("error_code")
    private String errorCode;
    @SerializedName("follow_id")
    private String followId;
    @SerializedName("isUberConnected")
    private String isUberConnected;
    @SerializedName("is_email_added")
    private String is_email_added;
    @SerializedName("is_social_active")
    private String is_social;
    private long lastGaanaPlusRefreshTime = 0;
    private Date lastLoginDateTime = null;
    private long lastMyProfileRefreshTime = 0;
    private boolean loginStatus = false;
    private LoginType loginType;
    @SerializedName("loginmode")
    private String loginmode;
    @SerializedName("newuser")
    private String newuser;
    @SerializedName("newuser_with_invalidparameter")
    private String newuser_with_invalidparameter;
    @SerializedName("promo")
    private String promo;
    @SerializedName("promourl")
    private String promourl;
    @SerializedName("refeligible")
    private String refeligible;
    @SerializedName("status")
    private String status;
    @SerializedName("tmp_email")
    private String tmp_email;
    @SerializedName("token")
    private String token;
    @SerializedName("user_info")
    private MyProfile userProfile;
    @SerializedName("gaana_plus_info")
    private UserSubscriptionData userSubscriptionData;
    @SerializedName("userver")
    private String userver;
    @SerializedName("uts")
    private String uts;

    public void setIs_social(String str) {
        this.is_social = str;
    }

    public UserSubscriptionData getUserSubscriptionData() {
        return this.userSubscriptionData;
    }

    public void setUserStatusInfo(UserStatusInfo userStatusInfo) {
        if (userStatusInfo.getStatus() != null) {
            this.userSubscriptionData = userStatusInfo.getUserSubscriptionData();
            this.userSubscriptionData.setLastUpdateTime(new Date().getTime());
            this.userSubscriptionData.updateAccountType();
            this.userSubscriptionData.updateExpiryDateAsPerServer();
            this.userSubscriptionData.updateExpiryDateWithGrace();
        }
    }

    public MyProfile getUserProfile() {
        return this.userProfile;
    }

    public void setUserProfile(MyProfile myProfile) {
        this.userProfile = myProfile;
    }

    public String getStatus() {
        return this.status;
    }

    public void setIsUberConnected(String str) {
        this.isUberConnected = str;
    }

    public String getAuthToken() {
        return !TextUtils.isEmpty(this.token) ? this.token : "";
    }

    public LoginType getLoginType() {
        return this.loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public boolean isNewuser() {
        return Integer.valueOf(this.newuser).intValue() == 1;
    }

    public String getLoginmode() {
        return this.loginmode;
    }

    public String getUserver() {
        return this.userver;
    }

    public String getCallType() {
        return this.callType;
    }

    public String getPromourl() {
        return this.promourl;
    }

    public String getUts() {
        return this.uts;
    }

    public String getPromo() {
        return this.promo;
    }

    public String isNewuserWithInvalidParameter() {
        return this.newuser_with_invalidparameter;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String str) {
        this.error = str;
    }

    public boolean isReferralEligible() {
        try {
            return Integer.valueOf(this.status).intValue() == 1 && Integer.valueOf(this.refeligible).intValue() == 1;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return false;
        }
    }

    public boolean getLoginStatus() {
        return this.loginStatus;
    }

    public void setLoginStatus(boolean z) {
        this.loginStatus = z;
    }

    public Date getLastLoginDateTime() {
        return this.lastLoginDateTime;
    }

    public void setLastLoginDateTime(Date date) {
        this.lastLoginDateTime = date;
    }

    public void setDeviceLinkLimitReached(boolean z) {
        this.deviceLinkLimitReached = z;
    }

    public String getIsUberConnected() {
        return this.isUberConnected;
    }

    public boolean getDeviceLinkLimitReached() {
        return this.deviceLinkLimitReached;
    }

    public String getIs_email_added() {
        return this.is_email_added;
    }

    public String getTempEmail() {
        return this.tmp_email;
    }

    public boolean isSocialEnabled() {
        return !TextUtils.isEmpty(this.is_social) && this.is_social.equals("1");
    }

    public void setFollowId(String str) {
        this.followId = str;
    }

    public String getFollowId() {
        return this.followId;
    }

    public long getLastGaanaPlusRefreshTime() {
        return this.lastGaanaPlusRefreshTime;
    }

    public void setLastGaanaPlusRefreshTime(long j) {
        this.lastGaanaPlusRefreshTime = j;
    }

    public long getLastMyProfileRefreshTime() {
        return this.lastMyProfileRefreshTime;
    }

    public void setLastMyProfileRefreshTime(long j) {
        this.lastMyProfileRefreshTime = j;
    }

    public String getErrorCode() {
        return this.errorCode;
    }
}
