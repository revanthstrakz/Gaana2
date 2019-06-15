package com.gaana.models;

import com.google.gson.annotations.SerializedName;

public class TextCardItem extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("message")
    private String message = "";

    public String getMessage() {
        return this.message;
    }
}
