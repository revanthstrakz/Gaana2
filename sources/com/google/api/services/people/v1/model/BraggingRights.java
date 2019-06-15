package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class BraggingRights extends GenericJson {
    @Key
    private FieldMetadata metadata;
    @Key
    private String value;

    public FieldMetadata getMetadata() {
        return this.metadata;
    }

    public BraggingRights setMetadata(FieldMetadata fieldMetadata) {
        this.metadata = fieldMetadata;
        return this;
    }

    public String getValue() {
        return this.value;
    }

    public BraggingRights setValue(String str) {
        this.value = str;
        return this;
    }

    public BraggingRights set(String str, Object obj) {
        return (BraggingRights) super.set(str, obj);
    }

    public BraggingRights clone() {
        return (BraggingRights) super.clone();
    }
}
