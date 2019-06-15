package com.gaana.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class StreamUrls implements Serializable {
    private static final long serialVersionUID = 6941581795343778315L;
    @SerializedName("auto")
    private StreamUrl auto;
    @SerializedName("extreme")
    private StreamUrl extreme;
    @SerializedName("high")
    private StreamUrl high;
    @SerializedName("higher")
    private StreamUrl higher;
    @SerializedName("low")
    private StreamUrl low;
    @SerializedName("medium")
    private StreamUrl medium;
    @SerializedName("normal")
    private StreamUrl normal;

    public static class StreamUrl implements Serializable {
        private String bitRate;
        private String expiryTime;
        private String message;

        public String getUrl() {
            return this.message;
        }

        public String getBitrate() {
            return this.bitRate;
        }

        public String getExpiry() {
            return this.expiryTime;
        }
    }

    public StreamUrl getMedium() {
        return this.medium;
    }

    public StreamUrl getHigh() {
        return this.high;
    }

    public StreamUrl getNormal() {
        return this.normal;
    }

    public StreamUrl getHigher() {
        return this.higher;
    }

    public StreamUrl getLow() {
        return this.low;
    }

    public StreamUrl getExtreme() {
        return this.extreme;
    }

    public StreamUrl getAuto() {
        return this.auto;
    }
}
