package com.h;

import com.gaana.models.NextGenSearchAutoSuggests.AutoComplete;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class b {
    @SerializedName("gl")
    @Expose
    private String a;
    @SerializedName("gdl")
    @Expose
    private List<AutoComplete> b = null;

    public String a() {
        return this.a;
    }

    public List<AutoComplete> b() {
        return this.b;
    }
}
