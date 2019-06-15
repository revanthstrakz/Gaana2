package com.google.api.services.people.v1.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public final class Address extends GenericJson {
    @Key
    private String city;
    @Key
    private String country;
    @Key
    private String countryCode;
    @Key
    private String extendedAddress;
    @Key
    private String formattedType;
    @Key
    private String formattedValue;
    @Key
    private FieldMetadata metadata;
    @Key
    private String poBox;
    @Key
    private String postalCode;
    @Key
    private String region;
    @Key
    private String streetAddress;
    @Key
    private String type;

    public String getCity() {
        return this.city;
    }

    public Address setCity(String str) {
        this.city = str;
        return this;
    }

    public String getCountry() {
        return this.country;
    }

    public Address setCountry(String str) {
        this.country = str;
        return this;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public Address setCountryCode(String str) {
        this.countryCode = str;
        return this;
    }

    public String getExtendedAddress() {
        return this.extendedAddress;
    }

    public Address setExtendedAddress(String str) {
        this.extendedAddress = str;
        return this;
    }

    public String getFormattedType() {
        return this.formattedType;
    }

    public Address setFormattedType(String str) {
        this.formattedType = str;
        return this;
    }

    public String getFormattedValue() {
        return this.formattedValue;
    }

    public Address setFormattedValue(String str) {
        this.formattedValue = str;
        return this;
    }

    public FieldMetadata getMetadata() {
        return this.metadata;
    }

    public Address setMetadata(FieldMetadata fieldMetadata) {
        this.metadata = fieldMetadata;
        return this;
    }

    public String getPoBox() {
        return this.poBox;
    }

    public Address setPoBox(String str) {
        this.poBox = str;
        return this;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public Address setPostalCode(String str) {
        this.postalCode = str;
        return this;
    }

    public String getRegion() {
        return this.region;
    }

    public Address setRegion(String str) {
        this.region = str;
        return this;
    }

    public String getStreetAddress() {
        return this.streetAddress;
    }

    public Address setStreetAddress(String str) {
        this.streetAddress = str;
        return this;
    }

    public String getType() {
        return this.type;
    }

    public Address setType(String str) {
        this.type = str;
        return this;
    }

    public Address set(String str, Object obj) {
        return (Address) super.set(str, obj);
    }

    public Address clone() {
        return (Address) super.clone();
    }
}
