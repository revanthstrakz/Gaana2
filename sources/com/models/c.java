package com.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class c {
    @SerializedName("help_text")
    private List<a> a = null;

    public static class a {
        @SerializedName("key")
        private String a;
        @SerializedName("value")
        private String b;

        public String a() {
            return this.b;
        }

        public String b() {
            return this.a;
        }
    }

    public List<a> a() {
        return this.a;
    }
}
