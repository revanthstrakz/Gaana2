package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class Tagline extends GenericJson {
    @Key
    private FieldMetadata metadata;
    @Key
    private String value;

    public FieldMetadata getMetadata() {
        return this.metadata;
    }

    public Tagline setMetadata(FieldMetadata fieldMetadata) {
        this.metadata = fieldMetadata;
        return this;
    }

    public String getValue() {
        return this.value;
    }

    public Tagline setValue(String str) {
        this.value = str;
        return this;
    }

    public Tagline set(String str, Object obj) {
        return (Tagline) super.set(str, obj);
    }

    public Tagline clone() {
        return (Tagline) super.clone();
    }
}
