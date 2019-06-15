package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Data;
import com.google.api.client.util.Key;
import java.util.List;

public final class GetPeopleResponse extends GenericJson {
    @Key
    private List<PersonResponse> responses;

    static {
        Data.nullOf(PersonResponse.class);
    }

    public List<PersonResponse> getResponses() {
        return this.responses;
    }

    public GetPeopleResponse setResponses(List<PersonResponse> list) {
        this.responses = list;
        return this;
    }

    public GetPeopleResponse set(String str, Object obj) {
        return (GetPeopleResponse) super.set(str, obj);
    }

    public GetPeopleResponse clone() {
        return (GetPeopleResponse) super.clone();
    }
}
