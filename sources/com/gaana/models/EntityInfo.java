package com.gaana.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class EntityInfo implements Serializable {
    private static final long serialVersionUID = 1;
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("value")
    @Expose
    private Object value;

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object obj) {
        this.value = obj;
    }

    public EntityInfo(String str, Object obj) {
        this.key = str;
        this.value = obj;
    }

    public EntityInfo(com.gaana.models.flatbuffer.EntityInfo entityInfo) {
        this.key = entityInfo.key();
        this.value = entityInfo.value();
    }
}
