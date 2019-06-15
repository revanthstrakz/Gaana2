package com.h;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class a {
    @SerializedName("view")
    @Expose
    private List<b> a = null;
    @SerializedName("eof")
    @Expose
    private int b;

    public List<b> a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }
}
