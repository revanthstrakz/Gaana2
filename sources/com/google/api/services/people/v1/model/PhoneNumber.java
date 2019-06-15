package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class PhoneNumber extends GenericJson {
    @Key
    private String canonicalForm;
    @Key
    private String formattedType;
    @Key
    private FieldMetadata metadata;
    @Key
    private String type;
    @Key
    private String value;

    public String getCanonicalForm() {
        return this.canonicalForm;
    }

    public PhoneNumber setCanonicalForm(String str) {
        this.canonicalForm = str;
        return this;
    }

    public String getFormattedType() {
        return this.formattedType;
    }

    public PhoneNumber setFormattedType(String str) {
        this.formattedType = str;
        return this;
    }

    public FieldMetadata getMetadata() {
        return this.metadata;
    }

    public PhoneNumber setMetadata(FieldMetadata fieldMetadata) {
        this.metadata = fieldMetadata;
        return this;
    }

    public String getType() {
        return this.type;
    }

    public PhoneNumber setType(String str) {
        this.type = str;
        return this;
    }

    public String getValue() {
        return this.value;
    }

    public PhoneNumber setValue(String str) {
        this.value = str;
        return this;
    }

    public PhoneNumber set(String str, Object obj) {
        return (PhoneNumber) super.set(str, obj);
    }

    public PhoneNumber clone() {
        return (PhoneNumber) super.clone();
    }
}
