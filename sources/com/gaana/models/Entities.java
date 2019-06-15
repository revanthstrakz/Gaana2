package com.gaana.models;

import com.google.gson.annotations.SerializedName;

public class Entities extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("artwork")
    private String artwork;
    @SerializedName("artwork_medium")
    private String artwork_medium;
    @SerializedName("entity_id")
    private String entity_id;
    @SerializedName("entity_info")
    private EntityInfo[] entity_info;
    @SerializedName("entity_type")
    private String entity_type;
    @SerializedName("language")
    private String language;
    @SerializedName("name")
    private String name;

    public String getEntity_type() {
        return this.entity_type;
    }

    public void setEntity_type(String str) {
        this.entity_type = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public EntityInfo[] getEntity_info() {
        return this.entity_info;
    }

    public void setEntity_info(EntityInfo[] entityInfoArr) {
        this.entity_info = entityInfoArr;
    }

    public String getArtwork() {
        return this.artwork;
    }

    public void setArtwork(String str) {
        this.artwork = str;
    }

    public String getEntity_id() {
        return this.entity_id;
    }

    public void setEntity_id(String str) {
        this.entity_id = str;
    }

    public String getArtwork_medium() {
        return this.artwork_medium;
    }

    public void setArtwork_medium(String str) {
        this.artwork_medium = str;
    }
}
