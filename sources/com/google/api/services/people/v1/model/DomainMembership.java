package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class DomainMembership extends GenericJson {
    @Key
    private Boolean inViewerDomain;

    public Boolean getInViewerDomain() {
        return this.inViewerDomain;
    }

    public DomainMembership setInViewerDomain(Boolean bool) {
        this.inViewerDomain = bool;
        return this;
    }

    public DomainMembership set(String str, Object obj) {
        return (DomainMembership) super.set(str, obj);
    }

    public DomainMembership clone() {
        return (DomainMembership) super.clone();
    }
}
