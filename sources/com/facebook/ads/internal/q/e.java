package com.facebook.ads.internal.q;

import android.text.TextUtils;
import com.facebook.ads.internal.j.a;
import com.facebook.ads.internal.j.c;
import com.facebook.ads.internal.j.d;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class e {
    private static e a = new e();

    public static synchronized e a() {
        e eVar;
        synchronized (e.class) {
            eVar = a;
        }
        return eVar;
    }

    private g a(JSONObject jSONObject) {
        int i = 0;
        JSONObject jSONObject2 = jSONObject.getJSONArray("placements").getJSONObject(0);
        c cVar = new c(d.a(jSONObject2.getJSONObject("definition")), jSONObject2.optString("feature_config"), jSONObject2.optString("ad_reporting_config"));
        if (jSONObject2.has("ads")) {
            JSONArray jSONArray = jSONObject2.getJSONArray("ads");
            while (i < jSONArray.length()) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                cVar.a(new a(jSONObject3.optString("adapter"), jSONObject3.optJSONObject("data"), jSONObject3.optJSONArray("trackers")));
                i++;
            }
        }
        return new g(cVar, jSONObject.optString("server_request_id"), jSONObject.optString("server_response"), jSONObject.optString("an_validation_uuid"));
    }

    private h b(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONArray("placements").getJSONObject(0);
            return new h(jSONObject.optString("message", ""), jSONObject.optInt("code", 0), new c(d.a(jSONObject2.getJSONObject("definition")), jSONObject2.optString("feature_config"), jSONObject2.optString("ad_reporting_config")));
        } catch (JSONException unused) {
            return c(jSONObject);
        }
    }

    private h c(JSONObject jSONObject) {
        return new h(jSONObject.optString("message", ""), jSONObject.optInt("code", 0), null);
    }

    public f a(String str) {
        if (!TextUtils.isEmpty(str)) {
            JSONObject jSONObject = new JSONObject(str);
            str = jSONObject.optString("type");
            Object obj = -1;
            int hashCode = str.hashCode();
            if (hashCode != 96432) {
                if (hashCode == 96784904 && str.equals("error")) {
                    obj = 1;
                }
            } else if (str.equals("ads")) {
                obj = null;
            }
            switch (obj) {
                case null:
                    return a(jSONObject);
                case 1:
                    return b(jSONObject);
                default:
                    JSONObject optJSONObject = jSONObject.optJSONObject("error");
                    if (optJSONObject != null) {
                        return c(optJSONObject);
                    }
                    break;
            }
        }
        return new f(a.UNKNOWN);
    }
}
