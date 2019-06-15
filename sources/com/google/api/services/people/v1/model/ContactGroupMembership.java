package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class ContactGroupMembership extends GenericJson {
    @Key
    private String contactGroupId;

    public String getContactGroupId() {
        return this.contactGroupId;
    }

    public ContactGroupMembership setContactGroupId(String str) {
        this.contactGroupId = str;
        return this;
    }

    public ContactGroupMembership set(String str, Object obj) {
        return (ContactGroupMembership) super.set(str, obj);
    }

    public ContactGroupMembership clone() {
        return (ContactGroupMembership) super.clone();
    }
}
