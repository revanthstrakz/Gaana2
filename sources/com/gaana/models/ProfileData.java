package com.gaana.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ProfileData implements Serializable {
    private static final long serialVersionUID = 1;
    @SerializedName("dob")
    private String dob;
    @SerializedName("email")
    private String email;
    @SerializedName("fullname")
    private String fullname;
    @SerializedName("artwork")
    private String img;
    @SerializedName("is_social_active")
    private String is_social_active;
    @SerializedName("is_voice_interaction")
    private int is_voice_interaction;
    @SerializedName("sex")
    private String sex;
    @SerializedName("Status")
    private String status;
    @SerializedName("user_id")
    private String user_id;

    public String getImg() {
        return this.img;
    }

    public void setImg(String str) {
        this.img = str;
    }

    public String getUser_id() {
        return this.user_id;
    }

    public void setUser_id(String str) {
        this.user_id = str;
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

    public String getDob() {
        return this.dob;
    }

    public void setDob(String str) {
        this.dob = str;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String str) {
        this.sex = str;
    }

    public int isVoiceInteractionEnabled() {
        return this.is_voice_interaction;
    }

    public String getIs_social_active() {
        return this.is_social_active;
    }

    public void setIs_social_active(String str) {
        this.is_social_active = str;
    }
}
