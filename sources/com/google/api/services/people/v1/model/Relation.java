package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class Relation extends GenericJson {
    @Key
    private String formattedType;
    @Key
    private FieldMetadata metadata;
    @Key
    private String person;
    @Key
    private String type;

    public String getFormattedType() {
        return this.formattedType;
    }

    public Relation setFormattedType(String str) {
        this.formattedType = str;
        return this;
    }

    public FieldMetadata getMetadata() {
        return this.metadata;
    }

    public Relation setMetadata(FieldMetadata fieldMetadata) {
        this.metadata = fieldMetadata;
        return this;
    }

    public String getPerson() {
        return this.person;
    }

    public Relation setPerson(String str) {
        this.person = str;
        return this;
    }

    public String getType() {
        return this.type;
    }

    public Relation setType(String str) {
        this.type = str;
        return this;
    }

    public Relation set(String str, Object obj) {
        return (Relation) super.set(str, obj);
    }

    public Relation clone() {
        return (Relation) super.clone();
    }
}
