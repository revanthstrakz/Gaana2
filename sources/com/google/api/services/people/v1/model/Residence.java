package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class Residence extends GenericJson {
    @Key
    private Boolean current;
    @Key
    private FieldMetadata metadata;
    @Key
    private String value;

    public Boolean getCurrent() {
        return this.current;
    }

    public Residence setCurrent(Boolean bool) {
        this.current = bool;
        return this;
    }

    public FieldMetadata getMetadata() {
        return this.metadata;
    }

    public Residence setMetadata(FieldMetadata fieldMetadata) {
        this.metadata = fieldMetadata;
        return this;
    }

    public String getValue() {
        return this.value;
    }

    public Residence setValue(String str) {
        this.value = str;
        return this;
    }

    public Residence set(String str, Object obj) {
        return (Residence) super.set(str, obj);
    }

    public Residence clone() {
        return (Residence) super.clone();
    }
}
