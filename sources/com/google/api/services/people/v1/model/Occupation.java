package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class Occupation extends GenericJson {
    @Key
    private FieldMetadata metadata;
    @Key
    private String value;

    public FieldMetadata getMetadata() {
        return this.metadata;
    }

    public Occupation setMetadata(FieldMetadata fieldMetadata) {
        this.metadata = fieldMetadata;
        return this;
    }

    public String getValue() {
        return this.value;
    }

    public Occupation setValue(String str) {
        this.value = str;
        return this;
    }

    public Occupation set(String str, Object obj) {
        return (Occupation) super.set(str, obj);
    }

    public Occupation clone() {
        return (Occupation) super.clone();
    }
}
