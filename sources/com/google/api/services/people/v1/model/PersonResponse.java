package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class PersonResponse extends GenericJson {
    @Key
    private Integer httpStatusCode;
    @Key
    private Person person;
    @Key
    private String requestedResourceName;
    @Key
    private Status status;

    public Integer getHttpStatusCode() {
        return this.httpStatusCode;
    }

    public PersonResponse setHttpStatusCode(Integer num) {
        this.httpStatusCode = num;
        return this;
    }

    public Person getPerson() {
        return this.person;
    }

    public PersonResponse setPerson(Person person) {
        this.person = person;
        return this;
    }

    public String getRequestedResourceName() {
        return this.requestedResourceName;
    }

    public PersonResponse setRequestedResourceName(String str) {
        this.requestedResourceName = str;
        return this;
    }

    public Status getStatus() {
        return this.status;
    }

    public PersonResponse setStatus(Status status) {
        this.status = status;
        return this;
    }

    public PersonResponse set(String str, Object obj) {
        return (PersonResponse) super.set(str, obj);
    }

    public PersonResponse clone() {
        return (PersonResponse) super.clone();
    }
}
