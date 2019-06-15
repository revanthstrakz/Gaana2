package com.gaana.models;

import com.google.gson.annotations.SerializedName;

public class SplashData extends BusinessObject {
    @SerializedName("artwork")
    private String artwork;
    @SerializedName("duration")
    private int duration;

    public String getArtwork() {
        return this.artwork;
    }

    public int getDuration() {
        return this.duration;
    }
}
