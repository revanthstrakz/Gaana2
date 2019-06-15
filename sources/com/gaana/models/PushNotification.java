package com.gaana.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class PushNotification implements Serializable {
    private static final long serialVersionUID = 1;
    @SerializedName("action")
    private String action;
    @SerializedName("alert")
    private String alert;
    @SerializedName("id")
    private String busObjId;
    @SerializedName("title")
    private String title;
    @SerializedName("t")
    private String type;
    @SerializedName("url")
    private String url;

    public String getTitle() {
        return this.title;
    }

    public String getBusinessObjId() {
        return this.busObjId;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getAlert() {
        return this.alert;
    }

    public void setAlert(String str) {
        this.alert = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String str) {
        this.action = str;
    }

    public void setBusObjId(String str) {
        this.busObjId = str;
    }
}
