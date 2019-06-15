package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class Source extends GenericJson {
    @Key
    private String etag;
    @Key
    private String id;
    @Key
    private ProfileMetadata profileMetadata;
    @Key
    private String type;
    @Key
    private String updateTime;

    public String getEtag() {
        return this.etag;
    }

    public Source setEtag(String str) {
        this.etag = str;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public Source setId(String str) {
        this.id = str;
        return this;
    }

    public ProfileMetadata getProfileMetadata() {
        return this.profileMetadata;
    }

    public Source setProfileMetadata(ProfileMetadata profileMetadata) {
        this.profileMetadata = profileMetadata;
        return this;
    }

    public String getType() {
        return this.type;
    }

    public Source setType(String str) {
        this.type = str;
        return this;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public Source setUpdateTime(String str) {
        this.updateTime = str;
        return this;
    }

    public Source set(String str, Object obj) {
        return (Source) super.set(str, obj);
    }

    public Source clone() {
        return (Source) super.clone();
    }
}
