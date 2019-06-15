package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class RelationshipInterest extends GenericJson {
    @Key
    private String formattedValue;
    @Key
    private FieldMetadata metadata;
    @Key
    private String value;

    public String getFormattedValue() {
        return this.formattedValue;
    }

    public RelationshipInterest setFormattedValue(String str) {
        this.formattedValue = str;
        return this;
    }

    public FieldMetadata getMetadata() {
        return this.metadata;
    }

    public RelationshipInterest setMetadata(FieldMetadata fieldMetadata) {
        this.metadata = fieldMetadata;
        return this;
    }

    public String getValue() {
        return this.value;
    }

    public RelationshipInterest setValue(String str) {
        this.value = str;
        return this;
    }

    public RelationshipInterest set(String str, Object obj) {
        return (RelationshipInterest) super.set(str, obj);
    }

    public RelationshipInterest clone() {
        return (RelationshipInterest) super.clone();
    }
}
