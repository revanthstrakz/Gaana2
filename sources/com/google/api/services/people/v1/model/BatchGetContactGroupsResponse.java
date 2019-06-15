package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Data;
import com.google.api.client.util.Key;
import java.util.List;

public final class BatchGetContactGroupsResponse extends GenericJson {
    @Key
    private List<ContactGroupResponse> responses;

    static {
        Data.nullOf(ContactGroupResponse.class);
    }

    public List<ContactGroupResponse> getResponses() {
        return this.responses;
    }

    public BatchGetContactGroupsResponse setResponses(List<ContactGroupResponse> list) {
        this.responses = list;
        return this;
    }

    public BatchGetContactGroupsResponse set(String str, Object obj) {
        return (BatchGetContactGroupsResponse) super.set(str, obj);
    }

    public BatchGetContactGroupsResponse clone() {
        return (BatchGetContactGroupsResponse) super.clone();
    }
}
