package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Data;
import com.google.api.client.util.Key;
import java.util.List;

public final class ListContactGroupsResponse extends GenericJson {
    @Key
    private List<ContactGroup> contactGroups;
    @Key
    private String nextPageToken;
    @Key
    private String nextSyncToken;
    @Key
    private Integer totalItems;

    static {
        Data.nullOf(ContactGroup.class);
    }

    public List<ContactGroup> getContactGroups() {
        return this.contactGroups;
    }

    public ListContactGroupsResponse setContactGroups(List<ContactGroup> list) {
        this.contactGroups = list;
        return this;
    }

    public String getNextPageToken() {
        return this.nextPageToken;
    }

    public ListContactGroupsResponse setNextPageToken(String str) {
        this.nextPageToken = str;
        return this;
    }

    public String getNextSyncToken() {
        return this.nextSyncToken;
    }

    public ListContactGroupsResponse setNextSyncToken(String str) {
        this.nextSyncToken = str;
        return this;
    }

    public Integer getTotalItems() {
        return this.totalItems;
    }

    public ListContactGroupsResponse setTotalItems(Integer num) {
        this.totalItems = num;
        return this;
    }

    public ListContactGroupsResponse set(String str, Object obj) {
        return (ListContactGroupsResponse) super.set(str, obj);
    }

    public ListContactGroupsResponse clone() {
        return (ListContactGroupsResponse) super.clone();
    }
}
