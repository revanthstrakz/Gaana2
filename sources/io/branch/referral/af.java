package io.branch.referral;

import org.json.JSONArray;
import org.json.JSONObject;

public class af {
    private int a;
    private String b;
    private Object c;

    public af(String str, int i) {
        this.b = str;
        this.a = i;
    }

    public int a() {
        return this.a;
    }

    public void a(Object obj) {
        this.c = obj;
    }

    public JSONObject b() {
        return this.c instanceof JSONObject ? (JSONObject) this.c : null;
    }

    public JSONArray c() {
        return this.c instanceof JSONArray ? (JSONArray) this.c : null;
    }

    public String d() {
        String str = "";
        try {
            JSONObject b = b();
            if (b == null || !b.has("error") || !b.getJSONObject("error").has("message")) {
                return str;
            }
            String string = b.getJSONObject("error").getString("message");
            if (string != null) {
                try {
                    if (string.trim().length() > 0) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(string);
                        stringBuilder.append(".");
                        return stringBuilder.toString();
                    }
                } catch (Exception unused) {
                }
            }
            return string;
        } catch (Exception unused2) {
            return str;
        }
    }
}
