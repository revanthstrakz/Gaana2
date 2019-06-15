package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class Birthday extends GenericJson {
    @Key
    private Date date;
    @Key
    private FieldMetadata metadata;
    @Key
    private String text;

    public Date getDate() {
        return this.date;
    }

    public Birthday setDate(Date date) {
        this.date = date;
        return this;
    }

    public FieldMetadata getMetadata() {
        return this.metadata;
    }

    public Birthday setMetadata(FieldMetadata fieldMetadata) {
        this.metadata = fieldMetadata;
        return this;
    }

    public String getText() {
        return this.text;
    }

    public Birthday setText(String str) {
        this.text = str;
        return this;
    }

    public Birthday set(String str, Object obj) {
        return (Birthday) super.set(str, obj);
    }

    public Birthday clone() {
        return (Birthday) super.clone();
    }
}
