package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Data;
import com.google.api.client.util.Key;
import java.util.List;

public final class ListConnectionsResponse extends GenericJson {
    @Key
    private List<Person> connections;
    @Key
    private String nextPageToken;
    @Key
    private String nextSyncToken;
    @Key
    private Integer totalItems;
    @Key
    private Integer totalPeople;

    static {
        Data.nullOf(Person.class);
    }

    public List<Person> getConnections() {
        return this.connections;
    }

    public ListConnectionsResponse setConnections(List<Person> list) {
        this.connections = list;
        return this;
    }

    public String getNextPageToken() {
        return this.nextPageToken;
    }

    public ListConnectionsResponse setNextPageToken(String str) {
        this.nextPageToken = str;
        return this;
    }

    public String getNextSyncToken() {
        return this.nextSyncToken;
    }

    public ListConnectionsResponse setNextSyncToken(String str) {
        this.nextSyncToken = str;
        return this;
    }

    public Integer getTotalItems() {
        return this.totalItems;
    }

    public ListConnectionsResponse setTotalItems(Integer num) {
        this.totalItems = num;
        return this;
    }

    public Integer getTotalPeople() {
        return this.totalPeople;
    }

    public ListConnectionsResponse setTotalPeople(Integer num) {
        this.totalPeople = num;
        return this;
    }

    public ListConnectionsResponse set(String str, Object obj) {
        return (ListConnectionsResponse) super.set(str, obj);
    }

    public ListConnectionsResponse clone() {
        return (ListConnectionsResponse) super.clone();
    }
}
