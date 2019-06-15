package com.dynamicview;

import com.constants.Constants;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class d {
    @SerializedName("categories")
    private List<a> a;
    @SerializedName("entityDescription")
    private String b;
    @SerializedName("is_shareable")
    private int c;
    @SerializedName("is_sponsored")
    private int d;
    @SerializedName("mob_header_image")
    private String e;
    @SerializedName("mob_header_image_white")
    private String f;
    @SerializedName("occasion_id")
    private int g;
    @SerializedName("seokey")
    private String h;
    @SerializedName("user_favourite")
    private int i;
    @SerializedName("wap_header_image")
    private String j;
    @SerializedName("bottom_banner_off")
    private int k;
    @SerializedName("share_text")
    private String l;

    public static class a {
        @SerializedName("cat_see_all")
        private String a;
        @SerializedName("section")
        private List<com.dynamicview.f.a> b;
        @SerializedName("show_see_all")
        private boolean c;
        @SerializedName("title")
        private String d;
        @SerializedName("txt_see_all")
        private String e;

        public String a() {
            return this.a;
        }

        public String b() {
            return Constants.b(this.e);
        }

        public List<com.dynamicview.f.a> c() {
            return this.b;
        }

        public boolean d() {
            return this.c;
        }

        public String e() {
            return Constants.b(this.d);
        }
    }

    public List<a> a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }

    public String f() {
        return this.f;
    }

    public int g() {
        return this.g;
    }

    public String h() {
        return this.h;
    }

    public int i() {
        return this.i;
    }

    public String j() {
        return this.j;
    }

    public boolean k() {
        return this.k == 1;
    }

    public String l() {
        return this.l;
    }
}
