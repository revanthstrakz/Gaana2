package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class Membership extends GenericJson {
    @Key
    private ContactGroupMembership contactGroupMembership;
    @Key
    private DomainMembership domainMembership;
    @Key
    private FieldMetadata metadata;

    public ContactGroupMembership getContactGroupMembership() {
        return this.contactGroupMembership;
    }

    public Membership setContactGroupMembership(ContactGroupMembership contactGroupMembership) {
        this.contactGroupMembership = contactGroupMembership;
        return this;
    }

    public DomainMembership getDomainMembership() {
        return this.domainMembership;
    }

    public Membership setDomainMembership(DomainMembership domainMembership) {
        this.domainMembership = domainMembership;
        return this;
    }

    public FieldMetadata getMetadata() {
        return this.metadata;
    }

    public Membership setMetadata(FieldMetadata fieldMetadata) {
        this.metadata = fieldMetadata;
        return this;
    }

    public Membership set(String str, Object obj) {
        return (Membership) super.set(str, obj);
    }

    public Membership clone() {
        return (Membership) super.clone();
    }
}
