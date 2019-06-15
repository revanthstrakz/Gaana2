package com.gaana.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class FbFriend implements Serializable {
    private static final long serialVersionUID = 1;
    @SerializedName("created_time")
    public String created_time;
    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;

    public String getCreatedTime() {
        return this.created_time;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setId(String str) {
        this.id = str;
    }
}
