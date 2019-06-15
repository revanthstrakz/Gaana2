package com.gaana.models;

import com.google.gson.annotations.SerializedName;

public class VideoFeed extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("ga_header")
    private String ga_header;
    @SerializedName("ga_source_name")
    private String ga_source_name;
    @SerializedName("section_name")
    private String section_name;
    @SerializedName("section_type")
    private int section_type;
    @SerializedName("url")
    private String url;

    public int getSection_type() {
        return this.section_type;
    }

    public void setSection_type(int i) {
        this.section_type = i;
    }

    public String getGa_source_name() {
        return this.ga_source_name;
    }

    public void setGa_source_name(String str) {
        this.ga_source_name = str;
    }

    public String getSection_name() {
        return this.section_name;
    }

    public void setSection_name(String str) {
        this.section_name = str;
    }

    public String getGa_header() {
        return this.ga_header;
    }

    public void setGa_header(String str) {
        this.ga_header = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
