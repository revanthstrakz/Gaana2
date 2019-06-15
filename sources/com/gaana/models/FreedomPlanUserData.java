package com.gaana.models;

import com.google.gson.annotations.SerializedName;

public class FreedomPlanUserData extends BusinessObject {
    @SerializedName("condition_met")
    private String condition_met;
    @SerializedName("days")
    private int days;
    @SerializedName("message")
    private String message;
    @SerializedName("songs")
    private int songs;
    @SerializedName("status")
    private int status;
    @SerializedName("tid")
    private String tid;
    @SerializedName("title")
    private String title;

    public int getStatus() {
        return this.status;
    }

    public String getCondition_met() {
        return this.condition_met;
    }

    public String getTitle() {
        return this.title;
    }

    public String getMessage() {
        return this.message;
    }

    public int getSongs() {
        return this.songs;
    }

    public int getDays() {
        return this.days;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public void setCondition_met(String str) {
        this.condition_met = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setSongs(int i) {
        this.songs = i;
    }

    public void setDays(int i) {
        this.days = i;
    }

    public void setTid(String str) {
        this.tid = str;
    }

    public String getTid() {
        return this.tid;
    }
}
