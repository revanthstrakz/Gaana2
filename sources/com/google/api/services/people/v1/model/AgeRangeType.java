package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class AgeRangeType extends GenericJson {
    @Key
    private String ageRange;
    @Key
    private FieldMetadata metadata;

    public String getAgeRange() {
        return this.ageRange;
    }

    public AgeRangeType setAgeRange(String str) {
        this.ageRange = str;
        return this;
    }

    public FieldMetadata getMetadata() {
        return this.metadata;
    }

    public AgeRangeType setMetadata(FieldMetadata fieldMetadata) {
        this.metadata = fieldMetadata;
        return this;
    }

    public AgeRangeType set(String str, Object obj) {
        return (AgeRangeType) super.set(str, obj);
    }

    public AgeRangeType clone() {
        return (AgeRangeType) super.clone();
    }
}
