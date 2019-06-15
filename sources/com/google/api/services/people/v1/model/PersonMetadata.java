package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Data;
import com.google.api.client.util.Key;
import java.util.List;

public final class PersonMetadata extends GenericJson {
    @Key
    private Boolean deleted;
    @Key
    private List<String> linkedPeopleResourceNames;
    @Key
    private String objectType;
    @Key
    private List<String> previousResourceNames;
    @Key
    private List<Source> sources;

    static {
        Data.nullOf(Source.class);
    }

    public Boolean getDeleted() {
        return this.deleted;
    }

    public PersonMetadata setDeleted(Boolean bool) {
        this.deleted = bool;
        return this;
    }

    public List<String> getLinkedPeopleResourceNames() {
        return this.linkedPeopleResourceNames;
    }

    public PersonMetadata setLinkedPeopleResourceNames(List<String> list) {
        this.linkedPeopleResourceNames = list;
        return this;
    }

    public String getObjectType() {
        return this.objectType;
    }

    public PersonMetadata setObjectType(String str) {
        this.objectType = str;
        return this;
    }

    public List<String> getPreviousResourceNames() {
        return this.previousResourceNames;
    }

    public PersonMetadata setPreviousResourceNames(List<String> list) {
        this.previousResourceNames = list;
        return this;
    }

    public List<Source> getSources() {
        return this.sources;
    }

    public PersonMetadata setSources(List<Source> list) {
        this.sources = list;
        return this;
    }

    public PersonMetadata set(String str, Object obj) {
        return (PersonMetadata) super.set(str, obj);
    }

    public PersonMetadata clone() {
        return (PersonMetadata) super.clone();
    }
}
