package com.gaana.models;

import com.google.gson.annotations.SerializedName;

public class YearVideoItem extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("share_url")
    private String videoShareUrl = "";
    @SerializedName("video_url")
    private String videoStreamingUrl = "";

    public String getVideoShareUrl() {
        return this.videoShareUrl;
    }

    public String getVideoStreamingUrl() {
        return this.videoStreamingUrl;
    }
}
