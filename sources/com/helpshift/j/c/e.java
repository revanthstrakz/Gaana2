package com.helpshift.j.c;

import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

class e implements a {
    private String a;
    private Map b;

    e(String str, Map map) {
        this.a = str;
        this.b = map;
    }

    public String a() {
        if (this.b == null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.a);
            stringBuilder.append(" : ");
            stringBuilder.append(this.b);
            return stringBuilder.toString();
        }
        JSONObject jSONObject = new JSONObject(this.b);
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(this.a);
        stringBuilder2.append(" : ");
        stringBuilder2.append(jSONObject.toString());
        return stringBuilder2.toString();
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
