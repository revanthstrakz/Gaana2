package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import java.util.List;

public final class ProfileMetadata extends GenericJson {
    @Key
    private String objectType;
    @Key
    private List<String> userTypes;

    public String getObjectType() {
        return this.objectType;
    }

    public ProfileMetadata setObjectType(String str) {
        this.objectType = str;
        return this;
    }

    public List<String> getUserTypes() {
        return this.userTypes;
    }

    public ProfileMetadata setUserTypes(List<String> list) {
        this.userTypes = list;
        return this;
    }

    public ProfileMetadata set(String str, Object obj) {
        return (ProfileMetadata) super.set(str, obj);
    }

    public ProfileMetadata clone() {
        return (ProfileMetadata) super.clone();
    }
}
