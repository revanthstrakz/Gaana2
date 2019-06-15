package com.gaana.models;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;

public class GaanaVideoItem extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("artwork")
    private String artwork;
    @SerializedName("title")
    private String header;
    @SerializedName("message")
    private String primaryText;
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

    public String getArtwork() {
        if (TextUtils.isEmpty(getAtw())) {
            return this.artwork;
        }
        return getAtw();
    }

    public String getHeader() {
        return this.header;
    }

    public String getPrimaryText() {
        return this.primaryText;
    }
}
