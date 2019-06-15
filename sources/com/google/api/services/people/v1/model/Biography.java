package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class Biography extends GenericJson {
    @Key
    private String contentType;
    @Key
    private FieldMetadata metadata;
    @Key
    private String value;

    public String getContentType() {
        return this.contentType;
    }

    public Biography setContentType(String str) {
        this.contentType = str;
        return this;
    }

    public FieldMetadata getMetadata() {
        return this.metadata;
    }

    public Biography setMetadata(FieldMetadata fieldMetadata) {
        this.metadata = fieldMetadata;
        return this;
    }

    public String getValue() {
        return this.value;
    }

    public Biography setValue(String str) {
        this.value = str;
        return this;
    }

    public Biography set(String str, Object obj) {
        return (Biography) super.set(str, obj);
    }

    public Biography clone() {
        return (Biography) super.clone();
    }
}
