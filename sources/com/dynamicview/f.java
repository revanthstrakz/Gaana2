package com.dynamicview;

import android.text.TextUtils;
import com.constants.Constants;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Map;

public class f {
    @SerializedName("home")
    private ArrayList<a> a;
    @SerializedName("radio")
    private ArrayList<a> b;
    @SerializedName("occasion")
    private ArrayList<a> c;
    @SerializedName("mob_header_image")
    private String d;

    public static class a {
        private long A = 0;
        private boolean B;
        @SerializedName("is_section_shareable")
        private boolean C;
        @SerializedName("section_info_v1")
        private Map<String, String> a;
        @SerializedName("ga_header")
        private String b;
        private String c;
        @SerializedName("url")
        private String d;
        @SerializedName("url_see_all")
        private String e;
        @SerializedName("view_type")
        private String f = "";
        @SerializedName("view_subtype")
        private int g;
        @SerializedName("view_type_see_all")
        private String h = "";
        @SerializedName("show_empty_view")
        private boolean i = true;
        @SerializedName("ga_source_name")
        private String j;
        @SerializedName("ad_code")
        private String k;
        @SerializedName("refresh_interval")
        private String l;
        @SerializedName("carousel_dl_url")
        private String m;
        @SerializedName("entity_description")
        private String n;
        @SerializedName("ad_code_see_all")
        private String o;
        @SerializedName("ad_code_see_all_banner")
        private String p;
        @SerializedName("show_load_more")
        private boolean q = true;
        @SerializedName("view_action")
        private int r;
        @SerializedName("view_size")
        private int s;
        @SerializedName("img_url")
        private String t;
        @SerializedName("bg_img_url")
        private String u;
        @SerializedName("user_type")
        private int v;
        @SerializedName("uid")
        private String w;
        @SerializedName("subtitle")
        private String x;
        @SerializedName("ad_code_dfp")
        private String y;
        private transient boolean z;

        public void a(long j) {
            this.A = j;
        }

        public long a() {
            return this.A;
        }

        public int b() {
            return this.v;
        }

        public String c() {
            return this.u;
        }

        public String d() {
            return this.t;
        }

        public void a(String str) {
            this.t = str;
        }

        public int e() {
            return this.s;
        }

        public void a(int i) {
            this.s = i;
        }

        public int f() {
            return this.r;
        }

        public void b(int i) {
            this.r = i;
        }

        public String g() {
            return this.l;
        }

        public String h() {
            return this.p;
        }

        public String i() {
            return this.o;
        }

        public Map<String, String> j() {
            return this.a;
        }

        public String k() {
            return Constants.b(this.b);
        }

        public void b(String str) {
            this.c = str;
        }

        public String l() {
            return this.d;
        }

        public void c(String str) {
            this.d = str;
        }

        public String m() {
            return this.f;
        }

        public int n() {
            return this.g;
        }

        public int c(int i) {
            this.g = i;
            return i;
        }

        public String o() {
            return this.e;
        }

        public void d(String str) {
            this.e = str;
        }

        public String p() {
            return Constants.a(this.j);
        }

        public void e(String str) {
            this.j = str;
        }

        public String q() {
            return this.k;
        }

        public String r() {
            return this.h;
        }

        public void f(String str) {
            this.h = str;
        }

        public boolean s() {
            return this.i;
        }

        public void a(boolean z) {
            this.i = z;
        }

        public boolean t() {
            return this.q;
        }

        public void b(boolean z) {
            this.q = z;
        }

        public void g(String str) {
            this.m = str;
        }

        public String u() {
            if (TextUtils.isEmpty(this.n)) {
                return Constants.b(this.b);
            }
            return Constants.b(this.n);
        }

        public void c(boolean z) {
            this.B = z;
        }

        public boolean v() {
            return this.B;
        }

        public String w() {
            return this.n;
        }

        public void h(String str) {
            this.n = str;
        }

        public String x() {
            return this.m;
        }

        public String y() {
            return this.w;
        }

        public void d(boolean z) {
            this.z = z;
        }

        public boolean z() {
            return this.z;
        }

        public String A() {
            return Constants.b(this.x);
        }

        public void i(String str) {
            this.x = str;
        }

        public a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
            this.b = str;
            this.d = str2;
            this.f = str3;
            this.e = str4;
            this.j = str5;
            this.k = str6;
            this.l = str8;
        }

        public String B() {
            return this.y;
        }

        public void j(String str) {
            this.w = str;
        }

        public boolean C() {
            return this.C;
        }
    }

    public void a(ArrayList<a> arrayList) {
        this.a = arrayList;
    }

    public void b(ArrayList<a> arrayList) {
        this.b = arrayList;
    }

    public void c(ArrayList<a> arrayList) {
        this.c = arrayList;
    }

    public void a(String str) {
        this.d = str;
    }
}
