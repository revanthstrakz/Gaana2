package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import java.util.List;

public final class ModifyContactGroupMembersResponse extends GenericJson {
    @Key
    private List<String> notFoundResourceNames;

    public List<String> getNotFoundResourceNames() {
        return this.notFoundResourceNames;
    }

    public ModifyContactGroupMembersResponse setNotFoundResourceNames(List<String> list) {
        this.notFoundResourceNames = list;
        return this;
    }

    public ModifyContactGroupMembersResponse set(String str, Object obj) {
        return (ModifyContactGroupMembersResponse) super.set(str, obj);
    }

    public ModifyContactGroupMembersResponse clone() {
        return (ModifyContactGroupMembersResponse) super.clone();
    }
}
