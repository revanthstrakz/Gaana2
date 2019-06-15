package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class ContactGroupMetadata extends GenericJson {
    @Key
    private Boolean deleted;
    @Key
    private String updateTime;

    public Boolean getDeleted() {
        return this.deleted;
    }

    public ContactGroupMetadata setDeleted(Boolean bool) {
        this.deleted = bool;
        return this;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public ContactGroupMetadata setUpdateTime(String str) {
        this.updateTime = str;
        return this;
    }

    public ContactGroupMetadata set(String str, Object obj) {
        return (ContactGroupMetadata) super.set(str, obj);
    }

    public ContactGroupMetadata clone() {
        return (ContactGroupMetadata) super.clone();
    }
}
