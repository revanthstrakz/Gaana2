package com.gaana.models;

import com.google.gson.annotations.SerializedName;

public class FontResponseData extends BusinessObject {
    @SerializedName("font_link")
    private String font_link;

    public String getFontLink() {
        return this.font_link;
    }

    public void setFontLink(String str) {
        this.font_link = str;
    }
}
