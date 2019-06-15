package com.gaana.login;

import com.gaana.models.User.LoginMode;
import com.gaana.models.User.LoginType;
import java.io.Serializable;

public class LoginInfo implements Serializable {
    private static final long serialVersionUID = 1;
    private String dob;
    private String emailId;
    private String fbId;
    private String fbPhoneLoginAuthCode;
    private String fbPhoneloginAccessToken;
    private String fullname;
    private String googleId;
    private String imgUrl;
    private Boolean isLogined = Boolean.valueOf(false);
    private int isManualData = 0;
    private boolean isUnverifiedSSOUser = false;
    private LoginMode loginMode;
    private LoginType loginType;
    private String password;
    private String realToken;
    private String sex;

    public boolean isUnverifiedSSOUser() {
        return this.isUnverifiedSSOUser;
    }

    public void setUnverifiedSSOUser(boolean z) {
        this.isUnverifiedSSOUser = z;
    }

    public String getEmailId() {
        return this.emailId;
    }

    public void setEmailId(String str) {
        this.emailId = str;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public LoginType getLoginType() {
        return this.loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public String getFbId() {
        return this.fbId;
    }

    public void setFbId(String str) {
        this.fbId = str;
    }

    public String getRealToken() {
        return this.realToken;
    }

    public void setRealToken(String str) {
        this.realToken = str;
    }

    public String getGoogleID() {
        return this.googleId;
    }

    public void setGoogleId(String str) {
        this.googleId = str;
    }

    public Boolean getLoginStatus() {
        return this.isLogined;
    }

    public void setLoginStatus(Boolean bool) {
        this.isLogined = bool;
    }

    public String getFullname() {
        return this.fullname;
    }

    public void setFullname(String str) {
        this.fullname = str;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String str) {
        this.sex = str;
    }

    public String getDob() {
        return this.dob;
    }

    public void setDob(String str) {
        this.dob = str;
    }

    public int getIsManualData() {
        return this.isManualData;
    }

    public void setIsManualData(int i) {
        this.isManualData = i;
    }

    public void setFbPhoneLoginAuthCode(String str) {
        this.fbPhoneLoginAuthCode = str;
    }

    public String getFbPhoneLoginAuthCode() {
        return this.fbPhoneLoginAuthCode;
    }

    public void setFbPhoneLoginAccessToken(String str) {
        this.fbPhoneloginAccessToken = str;
    }

    public String getFbPhoneloginAccessToken() {
        return this.fbPhoneloginAccessToken;
    }

    public void setImgUrl(String str) {
        this.imgUrl = str;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public LoginMode getLoginMode() {
        return this.loginMode != null ? this.loginMode : LoginMode.GAANA;
    }

    public void setLoginMode(LoginMode loginMode) {
        this.loginMode = loginMode;
    }
}
