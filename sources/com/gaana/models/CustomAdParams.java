package com.gaana.models;

import com.google.gson.annotations.SerializedName;

public class CustomAdParams extends BusinessObject {
    private static final String[] arrTopSlugAd = new String[0];
    private static final long serialVersionUID = 1;
    private int mIndex = 3;
    @SerializedName("playerloader")
    private String playerloader;
    @SerializedName("topslug")
    private String topslug;

    public int getIndex() {
        return this.mIndex;
    }

    public void setScreenIndex(int i) {
        this.mIndex = i;
    }

    public String getTopslug() {
        if (this.topslug == null) {
            this.topslug = arrTopSlugAd[this.mIndex];
        }
        return this.topslug;
    }

    public String getPlayerloader() {
        if (this.playerloader == null) {
            this.playerloader = "";
        }
        return this.playerloader;
    }
}
