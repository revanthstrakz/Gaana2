package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import java.util.List;

public final class ModifyContactGroupMembersRequest extends GenericJson {
    @Key
    private List<String> resourceNamesToAdd;
    @Key
    private List<String> resourceNamesToRemove;

    public List<String> getResourceNamesToAdd() {
        return this.resourceNamesToAdd;
    }

    public ModifyContactGroupMembersRequest setResourceNamesToAdd(List<String> list) {
        this.resourceNamesToAdd = list;
        return this;
    }

    public List<String> getResourceNamesToRemove() {
        return this.resourceNamesToRemove;
    }

    public ModifyContactGroupMembersRequest setResourceNamesToRemove(List<String> list) {
        this.resourceNamesToRemove = list;
        return this;
    }

    public ModifyContactGroupMembersRequest set(String str, Object obj) {
        return (ModifyContactGroupMembersRequest) super.set(str, obj);
    }

    public ModifyContactGroupMembersRequest clone() {
        return (ModifyContactGroupMembersRequest) super.clone();
    }
}
