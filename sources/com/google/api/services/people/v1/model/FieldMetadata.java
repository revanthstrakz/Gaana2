package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class FieldMetadata extends GenericJson {
    @Key
    private Boolean primary;
    @Key
    private Source source;
    @Key
    private Boolean verified;

    public Boolean getPrimary() {
        return this.primary;
    }

    public FieldMetadata setPrimary(Boolean bool) {
        this.primary = bool;
        return this;
    }

    public Source getSource() {
        return this.source;
    }

    public FieldMetadata setSource(Source source) {
        this.source = source;
        return this;
    }

    public Boolean getVerified() {
        return this.verified;
    }

    public FieldMetadata setVerified(Boolean bool) {
        this.verified = bool;
        return this;
    }

    public FieldMetadata set(String str, Object obj) {
        return (FieldMetadata) super.set(str, obj);
    }

    public FieldMetadata clone() {
        return (FieldMetadata) super.clone();
    }
}
