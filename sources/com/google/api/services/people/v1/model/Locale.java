package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class Locale extends GenericJson {
    @Key
    private FieldMetadata metadata;
    @Key
    private String value;

    public FieldMetadata getMetadata() {
        return this.metadata;
    }

    public Locale setMetadata(FieldMetadata fieldMetadata) {
        this.metadata = fieldMetadata;
        return this;
    }

    public String getValue() {
        return this.value;
    }

    public Locale setValue(String str) {
        this.value = str;
        return this;
    }

    public Locale set(String str, Object obj) {
        return (Locale) super.set(str, obj);
    }

    public Locale clone() {
        return (Locale) super.clone();
    }
}
