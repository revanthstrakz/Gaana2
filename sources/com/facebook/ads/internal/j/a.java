package com.facebook.ads.internal.j;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class a {
    private final String a;
    private final JSONObject b;
    private final Map<e, List<String>> c = new HashMap();

    public a(String str, JSONObject jSONObject, @Nullable JSONArray jSONArray) {
        this.a = str;
        this.b = jSONObject;
        if (jSONArray != null && jSONArray.length() != 0) {
            int i = 0;
            for (Object put : e.values()) {
                this.c.put(put, new LinkedList());
            }
            while (i < jSONArray.length()) {
                try {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    String string = jSONObject2.getString("type");
                    str = jSONObject2.getString("url");
                    e valueOf = e.valueOf(string.toUpperCase(Locale.US));
                    if (!(valueOf == null || TextUtils.isEmpty(str))) {
                        ((List) this.c.get(valueOf)).add(str);
                    }
                } catch (Exception unused) {
                }
                i++;
            }
        }
    }

    public String a() {
        return this.a;
    }

    public List<String> a(e eVar) {
        return (List) this.c.get(eVar);
    }

    public JSONObject b() {
        return this.b;
    }
}
