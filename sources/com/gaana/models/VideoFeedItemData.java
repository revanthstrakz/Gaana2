package com.gaana.models;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class VideoFeedItemData extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("count")
    private String count;
    @SerializedName("entities")
    private ArrayList<Item> entities;
    @SerializedName("status")
    private String status;

    public ArrayList<Item> getEntities() {
        return this.entities;
    }

    public void setEntities(ArrayList<Item> arrayList) {
        this.entities = arrayList;
    }

    public String getCount() {
        return this.count;
    }

    public void setCount(String str) {
        this.count = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
