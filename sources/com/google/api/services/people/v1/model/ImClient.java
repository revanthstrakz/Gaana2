package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class ImClient extends GenericJson {
    @Key
    private String formattedProtocol;
    @Key
    private String formattedType;
    @Key
    private FieldMetadata metadata;
    @Key
    private String protocol;
    @Key
    private String type;
    @Key
    private String username;

    public String getFormattedProtocol() {
        return this.formattedProtocol;
    }

    public ImClient setFormattedProtocol(String str) {
        this.formattedProtocol = str;
        return this;
    }

    public String getFormattedType() {
        return this.formattedType;
    }

    public ImClient setFormattedType(String str) {
        this.formattedType = str;
        return this;
    }

    public FieldMetadata getMetadata() {
        return this.metadata;
    }

    public ImClient setMetadata(FieldMetadata fieldMetadata) {
        this.metadata = fieldMetadata;
        return this;
    }

    public String getProtocol() {
        return this.protocol;
    }

    public ImClient setProtocol(String str) {
        this.protocol = str;
        return this;
    }

    public String getType() {
        return this.type;
    }

    public ImClient setType(String str) {
        this.type = str;
        return this;
    }

    public String getUsername() {
        return this.username;
    }

    public ImClient setUsername(String str) {
        this.username = str;
        return this;
    }

    public ImClient set(String str, Object obj) {
        return (ImClient) super.set(str, obj);
    }

    public ImClient clone() {
        return (ImClient) super.clone();
    }
}
