package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class Event extends GenericJson {
    @Key
    private Date date;
    @Key
    private String formattedType;
    @Key
    private FieldMetadata metadata;
    @Key
    private String type;

    public Date getDate() {
        return this.date;
    }

    public Event setDate(Date date) {
        this.date = date;
        return this;
    }

    public String getFormattedType() {
        return this.formattedType;
    }

    public Event setFormattedType(String str) {
        this.formattedType = str;
        return this;
    }

    public FieldMetadata getMetadata() {
        return this.metadata;
    }

    public Event setMetadata(FieldMetadata fieldMetadata) {
        this.metadata = fieldMetadata;
        return this;
    }

    public String getType() {
        return this.type;
    }

    public Event setType(String str) {
        this.type = str;
        return this;
    }

    public Event set(String str, Object obj) {
        return (Event) super.set(str, obj);
    }

    public Event clone() {
        return (Event) super.clone();
    }
}
