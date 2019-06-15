package com.login.nativesso.e;

import in.til.core.integrations.a;
import java.util.Map;

public class e extends a {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private Map<String, String> f;
    private String g;
    private Map<String, String> h;
    private boolean i;
    private boolean j;
    private boolean k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private String v;

    public void a(String str) {
        this.a = str;
    }

    public void b(String str) {
        this.b = str;
    }

    public void c(String str) {
        this.c = str;
    }

    public void d(String str) {
        this.d = str;
    }

    public void e(String str) {
        this.e = str;
    }

    public void a(Map<String, String> map) {
        this.f = map;
    }

    public void f(String str) {
        this.g = str;
    }

    public void b(Map<String, String> map) {
        this.h = map;
    }

    public void a(boolean z) {
        this.i = z;
    }

    public void b(boolean z) {
        this.j = z;
    }

    public void c(boolean z) {
        this.k = z;
    }

    public Map<String, String> a() {
        return this.h;
    }

    public String b() {
        return this.l;
    }

    public void g(String str) {
        this.l = str;
    }

    public void h(String str) {
        this.m = str;
    }

    public void i(String str) {
        this.n = str;
    }

    public void j(String str) {
        this.o = str;
    }

    public void k(String str) {
        this.p = str;
    }

    public void l(String str) {
        this.u = str;
    }

    public void m(String str) {
        this.q = str;
    }

    public void n(String str) {
        this.r = str;
    }

    public void o(String str) {
        this.s = str;
    }

    public void p(String str) {
        this.t = str;
    }

    public void q(String str) {
        this.v = str;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("GetUserDetailDTO{dp='");
        stringBuilder.append(this.a);
        stringBuilder.append('\'');
        stringBuilder.append(", firstName='");
        stringBuilder.append(this.b);
        stringBuilder.append('\'');
        stringBuilder.append(", lastName='");
        stringBuilder.append(this.c);
        stringBuilder.append('\'');
        stringBuilder.append(", gender='");
        stringBuilder.append(this.d);
        stringBuilder.append('\'');
        stringBuilder.append(", dob='");
        stringBuilder.append(this.e);
        stringBuilder.append('\'');
        stringBuilder.append(", emailList=");
        stringBuilder.append(this.f);
        stringBuilder.append(", primaryEmail='");
        stringBuilder.append(this.g);
        stringBuilder.append('\'');
        stringBuilder.append(", mobileList=");
        stringBuilder.append(this.h);
        stringBuilder.append(", isFbConnected=");
        stringBuilder.append(this.i);
        stringBuilder.append(", isGpConnected=");
        stringBuilder.append(this.j);
        stringBuilder.append(", passwordExists=");
        stringBuilder.append(this.k);
        stringBuilder.append(", ssoid='");
        stringBuilder.append(this.l);
        stringBuilder.append('\'');
        stringBuilder.append(", city='");
        stringBuilder.append(this.m);
        stringBuilder.append('\'');
        stringBuilder.append(", isEuUser='");
        stringBuilder.append(this.u);
        stringBuilder.append('\'');
        stringBuilder.append(", termsAccepted='");
        stringBuilder.append(this.p);
        stringBuilder.append('\'');
        stringBuilder.append(", shareDataAllowed='");
        stringBuilder.append(this.o);
        stringBuilder.append('\'');
        stringBuilder.append(", primeProfile='");
        stringBuilder.append(this.n);
        stringBuilder.append('\'');
        stringBuilder.append(", verifiedMobileCountryCode='");
        stringBuilder.append(this.q);
        stringBuilder.append('\'');
        stringBuilder.append(", verifiedMobileNumber='");
        stringBuilder.append(this.r);
        stringBuilder.append('\'');
        stringBuilder.append(", unverifiedMobileCountryCode='");
        stringBuilder.append(this.s);
        stringBuilder.append('\'');
        stringBuilder.append(", unverifiedMobileNumber='");
        stringBuilder.append(this.t);
        stringBuilder.append('\'');
        stringBuilder.append(", timespointsPolicy='");
        stringBuilder.append(this.v);
        stringBuilder.append('\'');
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
