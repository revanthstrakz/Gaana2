package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class Nickname extends GenericJson {
    @Key
    private FieldMetadata metadata;
    @Key
    private String type;
    @Key
    private String value;

    public FieldMetadata getMetadata() {
        return this.metadata;
    }

    public Nickname setMetadata(FieldMetadata fieldMetadata) {
        this.metadata = fieldMetadata;
        return this;
    }

    public String getType() {
        return this.type;
    }

    public Nickname setType(String str) {
        this.type = str;
        return this;
    }

    public String getValue() {
        return this.value;
    }

    public Nickname setValue(String str) {
        this.value = str;
        return this;
    }

    public Nickname set(String str, Object obj) {
        return (Nickname) super.set(str, obj);
    }

    public Nickname clone() {
        return (Nickname) super.clone();
    }
}
