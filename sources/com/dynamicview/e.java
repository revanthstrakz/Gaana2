package com.dynamicview;

import com.constants.Constants;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class e {
    @SerializedName("home")
    private List<a> a = null;
    @SerializedName("radio")
    private List<a> b = null;
    @SerializedName("explore")
    private List<a> c = null;
    @SerializedName("meta")
    private List<a> d = null;

    public static class a {
        @SerializedName("section")
        private List<com.dynamicview.f.a> a = null;
        @SerializedName("section_desc")
        private String b;

        public List<com.dynamicview.f.a> a() {
            return this.a;
        }

        public void a(List<com.dynamicview.f.a> list) {
            this.a = list;
        }

        public String b() {
            return Constants.b(this.b);
        }
    }

    public List<a> a() {
        return this.a;
    }

    public void a(List<a> list) {
        this.a = list;
    }

    public List<a> b() {
        return this.b;
    }

    public List<a> c() {
        return this.c;
    }

    public List<a> d() {
        return this.d;
    }
}
