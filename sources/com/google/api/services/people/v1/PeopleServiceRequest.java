package com.google.api.services.people.v1;

import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.util.Key;

public abstract class PeopleServiceRequest<T> extends AbstractGoogleJsonClientRequest<T> {
    @Key("$.xgafv")
    private String $Xgafv;
    @Key("access_token")
    private String accessToken;
    @Key
    private String alt;
    @Key("bearer_token")
    private String bearerToken;
    @Key
    private String callback;
    @Key
    private String fields;
    @Key
    private String key;
    @Key("oauth_token")
    private String oauthToken;
    @Key
    private Boolean pp;
    @Key
    private Boolean prettyPrint;
    @Key
    private String quotaUser;
    @Key("upload_protocol")
    private String uploadProtocol;
    @Key
    private String uploadType;

    public PeopleServiceRequest(PeopleService peopleService, String str, String str2, Object obj, Class<T> cls) {
        super(peopleService, str, str2, obj, cls);
    }

    public String get$Xgafv() {
        return this.$Xgafv;
    }

    public PeopleServiceRequest<T> set$Xgafv(String str) {
        this.$Xgafv = str;
        return this;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public PeopleServiceRequest<T> setAccessToken(String str) {
        this.accessToken = str;
        return this;
    }

    public String getAlt() {
        return this.alt;
    }

    public PeopleServiceRequest<T> setAlt(String str) {
        this.alt = str;
        return this;
    }

    public String getBearerToken() {
        return this.bearerToken;
    }

    public PeopleServiceRequest<T> setBearerToken(String str) {
        this.bearerToken = str;
        return this;
    }

    public String getCallback() {
        return this.callback;
    }

    public PeopleServiceRequest<T> setCallback(String str) {
        this.callback = str;
        return this;
    }

    public String getFields() {
        return this.fields;
    }

    public PeopleServiceRequest<T> setFields(String str) {
        this.fields = str;
        return this;
    }

    public String getKey() {
        return this.key;
    }

    public PeopleServiceRequest<T> setKey(String str) {
        this.key = str;
        return this;
    }

    public String getOauthToken() {
        return this.oauthToken;
    }

    public PeopleServiceRequest<T> setOauthToken(String str) {
        this.oauthToken = str;
        return this;
    }

    public Boolean getPp() {
        return this.pp;
    }

    public PeopleServiceRequest<T> setPp(Boolean bool) {
        this.pp = bool;
        return this;
    }

    public Boolean getPrettyPrint() {
        return this.prettyPrint;
    }

    public PeopleServiceRequest<T> setPrettyPrint(Boolean bool) {
        this.prettyPrint = bool;
        return this;
    }

    public String getQuotaUser() {
        return this.quotaUser;
    }

    public PeopleServiceRequest<T> setQuotaUser(String str) {
        this.quotaUser = str;
        return this;
    }

    public String getUploadType() {
        return this.uploadType;
    }

    public PeopleServiceRequest<T> setUploadType(String str) {
        this.uploadType = str;
        return this;
    }

    public String getUploadProtocol() {
        return this.uploadProtocol;
    }

    public PeopleServiceRequest<T> setUploadProtocol(String str) {
        this.uploadProtocol = str;
        return this;
    }

    public final PeopleService getAbstractGoogleClient() {
        return (PeopleService) super.getAbstractGoogleClient();
    }

    public PeopleServiceRequest<T> setDisableGZipContent(boolean z) {
        return (PeopleServiceRequest) super.setDisableGZipContent(z);
    }

    public PeopleServiceRequest<T> setRequestHeaders(HttpHeaders httpHeaders) {
        return (PeopleServiceRequest) super.setRequestHeaders(httpHeaders);
    }

    public PeopleServiceRequest<T> set(String str, Object obj) {
        return (PeopleServiceRequest) super.set(str, obj);
    }
}
