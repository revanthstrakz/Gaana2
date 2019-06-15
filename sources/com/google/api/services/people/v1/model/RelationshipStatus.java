package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class RelationshipStatus extends GenericJson {
    @Key
    private String formattedValue;
    @Key
    private FieldMetadata metadata;
    @Key
    private String value;

    public String getFormattedValue() {
        return this.formattedValue;
    }

    public RelationshipStatus setFormattedValue(String str) {
        this.formattedValue = str;
        return this;
    }

    public FieldMetadata getMetadata() {
        return this.metadata;
    }

    public RelationshipStatus setMetadata(FieldMetadata fieldMetadata) {
        this.metadata = fieldMetadata;
        return this;
    }

    public String getValue() {
        return this.value;
    }

    public RelationshipStatus setValue(String str) {
        this.value = str;
        return this;
    }

    public RelationshipStatus set(String str, Object obj) {
        return (RelationshipStatus) super.set(str, obj);
    }

    public RelationshipStatus clone() {
        return (RelationshipStatus) super.clone();
    }
}
