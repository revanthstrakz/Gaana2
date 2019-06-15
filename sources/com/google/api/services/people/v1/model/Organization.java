package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class Organization extends GenericJson {
    @Key
    private Boolean current;
    @Key
    private String department;
    @Key
    private String domain;
    @Key
    private Date endDate;
    @Key
    private String formattedType;
    @Key
    private String jobDescription;
    @Key
    private String location;
    @Key
    private FieldMetadata metadata;
    @Key
    private String name;
    @Key
    private String phoneticName;
    @Key
    private Date startDate;
    @Key
    private String symbol;
    @Key
    private String title;
    @Key
    private String type;

    public Boolean getCurrent() {
        return this.current;
    }

    public Organization setCurrent(Boolean bool) {
        this.current = bool;
        return this;
    }

    public String getDepartment() {
        return this.department;
    }

    public Organization setDepartment(String str) {
        this.department = str;
        return this;
    }

    public String getDomain() {
        return this.domain;
    }

    public Organization setDomain(String str) {
        this.domain = str;
        return this;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public Organization setEndDate(Date date) {
        this.endDate = date;
        return this;
    }

    public String getFormattedType() {
        return this.formattedType;
    }

    public Organization setFormattedType(String str) {
        this.formattedType = str;
        return this;
    }

    public String getJobDescription() {
        return this.jobDescription;
    }

    public Organization setJobDescription(String str) {
        this.jobDescription = str;
        return this;
    }

    public String getLocation() {
        return this.location;
    }

    public Organization setLocation(String str) {
        this.location = str;
        return this;
    }

    public FieldMetadata getMetadata() {
        return this.metadata;
    }

    public Organization setMetadata(FieldMetadata fieldMetadata) {
        this.metadata = fieldMetadata;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Organization setName(String str) {
        this.name = str;
        return this;
    }

    public String getPhoneticName() {
        return this.phoneticName;
    }

    public Organization setPhoneticName(String str) {
        this.phoneticName = str;
        return this;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public Organization setStartDate(Date date) {
        this.startDate = date;
        return this;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public Organization setSymbol(String str) {
        this.symbol = str;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public Organization setTitle(String str) {
        this.title = str;
        return this;
    }

    public String getType() {
        return this.type;
    }

    public Organization setType(String str) {
        this.type = str;
        return this;
    }

    public Organization set(String str, Object obj) {
        return (Organization) super.set(str, obj);
    }

    public Organization clone() {
        return (Organization) super.clone();
    }
}
