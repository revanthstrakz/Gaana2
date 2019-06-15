package com.gaana.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class DeleteHash extends BusinessObject implements Serializable {
    private static final long serialVersionUID = 1;
    @SerializedName("delete_hash")
    public String delete_hash;

    public String getDelete_hash() {
        return this.delete_hash;
    }

    public void setDelete_hash(String str) {
        this.delete_hash = str;
    }
}
