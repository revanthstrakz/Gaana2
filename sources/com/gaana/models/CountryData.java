package com.gaana.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

public class CountryData implements Serializable {
    private static final long serialVersionUID = 1;
    @SerializedName("boarding")
    private Integer boarding;
    @SerializedName("city")
    private String city;
    @SerializedName("consent_header")
    private String consentHeader;
    @SerializedName("consent")
    private ArrayList<Consent> consentList;
    @SerializedName("consent_text")
    private String consentText;
    @SerializedName("country")
    private String country;
    @SerializedName("is_eu")
    private int eu_region;
    @SerializedName("is_consent")
    private int isConsent;
    @SerializedName("region")
    private String region;
    @SerializedName("status")
    private String status;
    private String timeStamp;
    @SerializedName("user_status")
    private int userStatus;

    public class Consent implements Serializable {
        private static final long serialVersionUID = 1;
        @SerializedName("mandatory")
        private int manadatory;
        @SerializedName("consent_id")
        private int tnc_key;
        @SerializedName("msg")
        private String tnc_value;

        public int getTncKey() {
            return this.tnc_key;
        }

        public String getTncValue() {
            return this.tnc_value;
        }

        public int getManadatory() {
            return this.manadatory;
        }
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String str) {
        this.region = str;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public Integer getBoarding() {
        return this.boarding;
    }

    public void setBoarding(Integer num) {
        this.boarding = num;
    }

    public String getCountryName() {
        return this.country;
    }

    public void setCountryName(String str) {
        this.country = str;
    }

    public String getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(String str) {
        this.timeStamp = str;
    }

    public String getConsentHeader() {
        return this.consentHeader;
    }

    public String getConsentText() {
        return this.consentText;
    }

    public int getEuRegion() {
        return this.eu_region;
    }

    public int getIsConsent() {
        return this.isConsent;
    }

    public int getUserStatus() {
        return this.userStatus;
    }

    public ArrayList<Consent> getConsent() {
        return this.consentList;
    }
}
