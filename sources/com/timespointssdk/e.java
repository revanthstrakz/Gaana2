package com.timespointssdk;

import org.json.JSONArray;

class e {
    private static e h;
    private Integer a = a.d;
    private Integer b;
    private String c;
    private JSONArray d;
    private String e;
    private String f;
    private String g;

    private e() {
    }

    protected static e a() {
        if (h == null) {
            synchronized (e.class) {
                if (h == null) {
                    h = new e();
                }
            }
        }
        return h;
    }

    /* Access modifiers changed, original: protected */
    public String b() {
        return this.e;
    }

    /* Access modifiers changed, original: protected */
    public void a(String str) {
        this.e = str;
    }

    /* Access modifiers changed, original: protected */
    public String c() {
        return this.f;
    }

    /* Access modifiers changed, original: protected */
    public void b(String str) {
        this.f = str;
    }

    /* Access modifiers changed, original: protected */
    public String d() {
        return this.g;
    }

    /* Access modifiers changed, original: protected */
    public void c(String str) {
        this.g = str;
    }

    /* Access modifiers changed, original: protected */
    public String e() {
        return this.c;
    }

    /* Access modifiers changed, original: protected */
    public void d(String str) {
        this.c = str;
    }

    /* Access modifiers changed, original: protected */
    public Integer f() {
        return this.a;
    }

    /* Access modifiers changed, original: protected */
    public void a(Integer num) {
        this.a = num;
    }

    /* Access modifiers changed, original: protected */
    public Integer g() {
        return this.b;
    }

    /* Access modifiers changed, original: protected */
    public void b(Integer num) {
        this.b = num;
    }

    /* Access modifiers changed, original: protected */
    public JSONArray h() {
        return this.d;
    }

    /* Access modifiers changed, original: protected */
    public void a(JSONArray jSONArray) {
        this.d = jSONArray;
    }
}
