package com.gaana.login;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class MyProfile implements Serializable {
    private static final long serialVersionUID = 1;
    @SerializedName("dob")
    private String dob;
    @SerializedName("email")
    private String email;
    @SerializedName("email_status")
    private String email_status;
    @SerializedName("fnm")
    private String fullname;
    @SerializedName("img")
    private String img;
    @SerializedName("is_voice_interaction")
    private int is_voice_interaction;
    @SerializedName("mobile_number")
    private String mobileNumber;
    @SerializedName("mobile_country_prefix")
    private String mobile_country_prefix;
    @SerializedName("sex")
    private String sex;
    @SerializedName("ticket_id")
    private String ssoTicketId;
    @SerializedName("Status")
    private String status;
    @SerializedName("uid")
    private String user_id;
    @SerializedName("uts")
    private String uts;

    public String getStatus() {
        return this.status;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String str) {
        this.img = str;
    }

    public String getUserId() {
        return this.user_id;
    }

    public String getFullname() {
        return this.fullname;
    }

    public String getDob() {
        return this.dob;
    }

    public String getSex() {
        return this.sex;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhoneNumber() {
        return this.mobileNumber;
    }

    public String getMobileCountryPrefix() {
        return this.mobile_country_prefix;
    }

    public String getEmail_status() {
        return this.email_status;
    }

    public String getSsoTicketId() {
        return this.ssoTicketId;
    }

    public void setFullname(String str) {
        this.fullname = str;
    }

    public void setSex(String str) {
        this.sex = str;
    }

    public boolean isVoiceInteractionEnabled() {
        return this.is_voice_interaction == 1;
    }

    public void setIs_voice_interaction(int i) {
        this.is_voice_interaction = i;
    }

    public void setDob(String str) {
        this.dob = str;
    }
}
