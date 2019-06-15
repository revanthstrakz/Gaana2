package com.inmobi.a;

import com.inmobi.commons.core.e.b;
import com.inmobi.commons.core.e.f;
import com.inmobi.commons.core.network.d;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class c {
    private static final String e = "c";
    boolean a = true;
    List<d> b;
    String c;
    int d = 0;
    private d f;

    public c(d dVar) {
        this.f = dVar;
        this.b = new ArrayList();
        a();
        if (this.a) {
            try {
                b.a().a(new f("signals", "InvalidCarbGetResponse"));
            } catch (Exception e) {
                StringBuilder stringBuilder = new StringBuilder("Error in submitting telemetry event : (");
                stringBuilder.append(e.getMessage());
                stringBuilder.append(")");
            }
        }
    }

    private void a() {
        if (this.f.a()) {
            new StringBuilder("Error response for Carb list received. Error code:").append(this.f.b.a);
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(this.f.b());
            if (jSONObject.getBoolean("success")) {
                jSONObject = jSONObject.getJSONObject("data");
                this.c = jSONObject.getString("req_id");
                JSONArray jSONArray = jSONObject.getJSONArray("p_apps");
                int i = 0;
                while (i < jSONArray.length()) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    String optString = jSONObject2.optString("bid", null);
                    String optString2 = jSONObject2.optString("inm_id", null);
                    if (!(optString == null || optString2 == null || optString2.trim().length() <= 0)) {
                        this.b.add(new d(optString, optString2));
                    }
                    i++;
                    this.d = i;
                }
            } else {
                new StringBuilder("Error response for Carb list received. Error code:").append(jSONObject.optInt("errorCode"));
            }
            this.a = false;
        } catch (JSONException unused) {
        }
    }
}
