package com.gaana.models;

import java.io.Serializable;

public class AdsUJData implements Serializable {
    private String adType;
    private String adUnitCode;
    private String sectionId;
    private String sectionIndex;
    private String sectionName;

    public String getSectionIndex() {
        return this.sectionIndex;
    }

    public void setSectionIndex(String str) {
        this.sectionIndex = str;
    }

    public String getSectionId() {
        return this.sectionId;
    }

    public void setSectionId(String str) {
        this.sectionId = str;
    }

    public String getAdType() {
        return this.adType;
    }

    public void setAdType(String str) {
        this.adType = str;
    }

    public String getSectionName() {
        return this.sectionName;
    }

    public void setSectionName(String str) {
        this.sectionName = str;
    }

    public String getAdUnitCode() {
        return this.adUnitCode;
    }

    public void setAdUnitCode(String str) {
        this.adUnitCode = str;
    }
}
