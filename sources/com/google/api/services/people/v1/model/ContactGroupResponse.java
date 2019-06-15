package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class ContactGroupResponse extends GenericJson {
    @Key
    private ContactGroup contactGroup;
    @Key
    private String requestedResourceName;
    @Key
    private Status status;

    public ContactGroup getContactGroup() {
        return this.contactGroup;
    }

    public ContactGroupResponse setContactGroup(ContactGroup contactGroup) {
        this.contactGroup = contactGroup;
        return this;
    }

    public String getRequestedResourceName() {
        return this.requestedResourceName;
    }

    public ContactGroupResponse setRequestedResourceName(String str) {
        this.requestedResourceName = str;
        return this;
    }

    public Status getStatus() {
        return this.status;
    }

    public ContactGroupResponse setStatus(Status status) {
        this.status = status;
        return this;
    }

    public ContactGroupResponse set(String str, Object obj) {
        return (ContactGroupResponse) super.set(str, obj);
    }

    public ContactGroupResponse clone() {
        return (ContactGroupResponse) super.clone();
    }
}
