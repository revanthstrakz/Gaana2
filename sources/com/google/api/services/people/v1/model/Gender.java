package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class Gender extends GenericJson {
    @Key
    private String formattedValue;
    @Key
    private FieldMetadata metadata;
    @Key
    private String value;

    public String getFormattedValue() {
        return this.formattedValue;
    }

    public Gender setFormattedValue(String str) {
        this.formattedValue = str;
        return this;
    }

    public FieldMetadata getMetadata() {
        return this.metadata;
    }

    public Gender setMetadata(FieldMetadata fieldMetadata) {
        this.metadata = fieldMetadata;
        return this;
    }

    public String getValue() {
        return this.value;
    }

    public Gender setValue(String str) {
        this.value = str;
        return this;
    }

    public Gender set(String str, Object obj) {
        return (Gender) super.set(str, obj);
    }

    public Gender clone() {
        return (Gender) super.clone();
    }
}
