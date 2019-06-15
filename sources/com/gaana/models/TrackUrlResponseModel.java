package com.gaana.models;

import com.google.gson.annotations.SerializedName;

public class TrackUrlResponseModel extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("av_ad")
    private int avAdFlag;
    @SerializedName("bitrate")
    private String bitrate;
    @SerializedName("content_source")
    private String contentSource;
    @SerializedName("data")
    private String data;
    @SerializedName("error_code")
    private String errorCode;
    @SerializedName("et")
    private long expiryTime;
    @SerializedName("parental_warning")
    private String parentalWarning;
    @SerializedName("premium_content")
    private String premiumContent;
    @SerializedName("protocol")
    private String protocol;
    @SerializedName("quality")
    private String quality;
    @SerializedName("status")
    private int status;
    @SerializedName("track_format")
    private String trackFormat;

    public int getStatus() {
        return this.status;
    }

    public String getData() {
        return this.data;
    }

    public String getBitrate() {
        return this.bitrate;
    }

    public String getQuality() {
        return this.quality;
    }

    public String getProtocol() {
        return this.protocol;
    }

    public String getContentSource() {
        return this.contentSource;
    }

    public String getTrackFormat() {
        return this.trackFormat;
    }

    public String getPremiumContent() {
        return this.premiumContent;
    }

    public String getParentalWarning() {
        return this.parentalWarning;
    }

    public long getExpiryTime() {
        return this.expiryTime;
    }

    public int getAvAdFlag() {
        return this.avAdFlag;
    }

    public String getErrorCode() {
        return this.errorCode;
    }
}
