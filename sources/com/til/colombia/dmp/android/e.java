package com.til.colombia.dmp.android;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public final class e {
    ArrayList<a> a;

    public class a {
        String a;

        public a(String str) {
            this.a = str;
        }

        public final String a() {
            return this.a;
        }
    }

    public final ArrayList<a> a() {
        return this.a;
    }

    public e(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray("applications");
        this.a = new ArrayList(optJSONArray.length());
        for (int i = 0; i < optJSONArray.length(); i++) {
            this.a.add(new a(optJSONArray.optJSONObject(i).optString("pkg")));
        }
    }
}
