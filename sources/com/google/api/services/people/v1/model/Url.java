package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class Url extends GenericJson {
    @Key
    private String formattedType;
    @Key
    private FieldMetadata metadata;
    @Key
    private String type;
    @Key
    private String value;

    public String getFormattedType() {
        return this.formattedType;
    }

    public Url setFormattedType(String str) {
        this.formattedType = str;
        return this;
    }

    public FieldMetadata getMetadata() {
        return this.metadata;
    }

    public Url setMetadata(FieldMetadata fieldMetadata) {
        this.metadata = fieldMetadata;
        return this;
    }

    public String getType() {
        return this.type;
    }

    public Url setType(String str) {
        this.type = str;
        return this;
    }

    public String getValue() {
        return this.value;
    }

    public Url setValue(String str) {
        this.value = str;
        return this;
    }

    public Url set(String str, Object obj) {
        return (Url) super.set(str, obj);
    }

    public Url clone() {
        return (Url) super.clone();
    }
}
