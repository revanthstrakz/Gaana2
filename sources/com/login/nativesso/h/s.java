package com.login.nativesso.h;

import com.android.volley.i.a;
import com.android.volley.i.b;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import org.json.JSONException;
import org.json.JSONObject;

public class s extends a {
    public s(int i, JSONObject jSONObject, b<JSONObject> bVar, a aVar) {
        super(i, com.login.nativesso.i.b.K, jSONObject, bVar, aVar);
        a(null);
    }

    public static JSONObject a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("oauthSiteId", str);
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
        return jSONObject;
    }
}
