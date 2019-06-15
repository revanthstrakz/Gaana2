package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class Interest extends GenericJson {
    @Key
    private FieldMetadata metadata;
    @Key
    private String value;

    public FieldMetadata getMetadata() {
        return this.metadata;
    }

    public Interest setMetadata(FieldMetadata fieldMetadata) {
        this.metadata = fieldMetadata;
        return this;
    }

    public String getValue() {
        return this.value;
    }

    public Interest setValue(String str) {
        this.value = str;
        return this;
    }

    public Interest set(String str, Object obj) {
        return (Interest) super.set(str, obj);
    }

    public Interest clone() {
        return (Interest) super.clone();
    }
}
