package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class UpdateContactGroupRequest extends GenericJson {
    @Key
    private ContactGroup contactGroup;

    public ContactGroup getContactGroup() {
        return this.contactGroup;
    }

    public UpdateContactGroupRequest setContactGroup(ContactGroup contactGroup) {
        this.contactGroup = contactGroup;
        return this;
    }

    public UpdateContactGroupRequest set(String str, Object obj) {
        return (UpdateContactGroupRequest) super.set(str, obj);
    }

    public UpdateContactGroupRequest clone() {
        return (UpdateContactGroupRequest) super.clone();
    }
}
