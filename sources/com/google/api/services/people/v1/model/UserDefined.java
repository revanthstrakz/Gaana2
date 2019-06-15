package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class UserDefined extends GenericJson {
    @Key
    private String key;
    @Key
    private FieldMetadata metadata;
    @Key
    private String value;

    public String getKey() {
        return this.key;
    }

    public UserDefined setKey(String str) {
        this.key = str;
        return this;
    }

    public FieldMetadata getMetadata() {
        return this.metadata;
    }

    public UserDefined setMetadata(FieldMetadata fieldMetadata) {
        this.metadata = fieldMetadata;
        return this;
    }

    public String getValue() {
        return this.value;
    }

    public UserDefined setValue(String str) {
        this.value = str;
        return this;
    }

    public UserDefined set(String str, Object obj) {
        return (UserDefined) super.set(str, obj);
    }

    public UserDefined clone() {
        return (UserDefined) super.clone();
    }
}
