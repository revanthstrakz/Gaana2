package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class CoverPhoto extends GenericJson {
    @Key("default")
    private Boolean default__;
    @Key
    private FieldMetadata metadata;
    @Key
    private String url;

    public Boolean getDefault() {
        return this.default__;
    }

    public CoverPhoto setDefault(Boolean bool) {
        this.default__ = bool;
        return this;
    }

    public FieldMetadata getMetadata() {
        return this.metadata;
    }

    public CoverPhoto setMetadata(FieldMetadata fieldMetadata) {
        this.metadata = fieldMetadata;
        return this;
    }

    public String getUrl() {
        return this.url;
    }

    public CoverPhoto setUrl(String str) {
        this.url = str;
        return this;
    }

    public CoverPhoto set(String str, Object obj) {
        return (CoverPhoto) super.set(str, obj);
    }

    public CoverPhoto clone() {
        return (CoverPhoto) super.clone();
    }
}
