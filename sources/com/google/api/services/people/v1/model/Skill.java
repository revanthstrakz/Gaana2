package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class Skill extends GenericJson {
    @Key
    private FieldMetadata metadata;
    @Key
    private String value;

    public FieldMetadata getMetadata() {
        return this.metadata;
    }

    public Skill setMetadata(FieldMetadata fieldMetadata) {
        this.metadata = fieldMetadata;
        return this;
    }

    public String getValue() {
        return this.value;
    }

    public Skill setValue(String str) {
        this.value = str;
        return this;
    }

    public Skill set(String str, Object obj) {
        return (Skill) super.set(str, obj);
    }

    public Skill clone() {
        return (Skill) super.clone();
    }
}
