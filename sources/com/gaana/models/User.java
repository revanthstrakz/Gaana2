package com.gaana.models;

import android.text.TextUtils;
import com.constants.Constants;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Date;

public class User extends BusinessObject {
    private static final long serialVersionUID = 1;
    protected String authToken;
    protected String emailId;
    protected String fbId;
    protected String googleId;
    protected Boolean isLogined = Boolean.valueOf(false);
    protected Date lastLoginDateTime;
    protected LoginType loginType;
    protected String password;
    protected String realToken;
    @SerializedName("Status")
    private String status;
    @SerializedName("data")
    private UserData userData;

    public enum LoginMode {
        GAANA,
        SSO,
        GAANA_SSO_NOT_VERIFIED
    }

    public enum LoginType {
        FB,
        GAANA,
        GOOGLE,
        PHONENUMBER
    }

    public static class UserActivities extends BusinessObject {
        private static final long serialVersionUID = 1;
        private String activity_data;
        private String activity_subtype;
        private String activity_type;
        private String created_on;
        private String modified_on;

        public String getActivity_type() {
            return this.activity_type;
        }

        public String getActivity_subtype() {
            return this.activity_subtype;
        }

        public String getActivity_data() {
            return this.activity_data;
        }

        public String getCreated_on() {
            return this.created_on;
        }

        public String getModified_on() {
            return this.modified_on;
        }
    }

    public static class UserData extends BusinessObject {
        private static final long serialVersionUID = 1;
        private String artwork;
        private String dob;
        private String email;
        private String fullname;
        private String lastheardsong;
        private String sex;
        @SerializedName("activities")
        private ArrayList<UserActivities> userActivities;
        @SerializedName("user_id")
        private String userId;

        public String getUserId() {
            return this.userId;
        }

        public void setUserId(String str) {
            this.userId = str;
        }

        public String getFullname() {
            return this.fullname;
        }

        public void setFullname(String str) {
            this.fullname = str;
        }

        public String getEmail() {
            return this.email;
        }

        public void setEmail(String str) {
            this.email = str;
        }

        public String getSex() {
            return Constants.b(this.sex);
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

        public String getLastheardsong() {
            return this.lastheardsong;
        }

        public String getArtwork() {
            if (TextUtils.isEmpty(getAtw())) {
                return this.artwork;
            }
            return getAtw();
        }

        public ArrayList<UserActivities> getUserActivities() {
            return this.userActivities;
        }
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

    public Date getLastLoginDateTime() {
        return this.lastLoginDateTime;
    }

    public void setLastLoginDateTime(Date date) {
        this.lastLoginDateTime = date;
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

    public String getAuthToken() {
        return this.authToken;
    }

    public void setAuthToken(String str) {
        this.authToken = str;
    }

    public Boolean getLoginStatus() {
        return this.isLogined;
    }

    public void setLoginStatus(Boolean bool) {
        this.isLogined = bool;
    }

    public String getStatus() {
        return this.status;
    }

    public UserData getUserData() {
        return this.userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
