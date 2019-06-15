package com.helpshift.j.c;

import org.json.JSONException;
import org.json.JSONObject;

class f implements a {
    private String a;
    private String b;

    f(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public String a() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.a);
        stringBuilder.append(" : ");
        stringBuilder.append(this.b == null ? "" : this.b.toString());
        return stringBuilder.toString();
    }

    public Object b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(this.a, this.b == null ? "" : this.b.toString());
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
