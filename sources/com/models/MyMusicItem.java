package com.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class MyMusicItem implements Serializable {
    private static final long serialVersionUID = 1;
    @SerializedName("label")
    private String a;
    @SerializedName("imgResId")
    private int b;
    @SerializedName("id")
    private int c;
    @SerializedName("identifier")
    private String d;

    public MyMusicItem(int i, String str, int i2, String str2) {
        this.c = i;
        this.a = str;
        this.b = i2;
        this.d = str2;
    }

    public String a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }
}
