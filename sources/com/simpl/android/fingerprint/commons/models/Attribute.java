package com.simpl.android.fingerprint.commons.models;

public class Attribute {
    private String key;
    private String value;

    public Attribute(String str, String str2) {
        this.key = str;
        this.value = str2;
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }
}
