package com.login.nativesso.h;

import com.android.volley.i.a;
import com.android.volley.i.b;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import org.json.JSONException;
import org.json.JSONObject;

public class t extends a {
    public t(int i, JSONObject jSONObject, b<JSONObject> bVar, a aVar) {
        super(i, com.login.nativesso.i.b.L, jSONObject, bVar, aVar);
        a(null);
    }

    public static JSONObject a(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("accessToken", str);
            jSONObject.put("oauthId", str2);
            jSONObject.put("oauthSiteId", str3);
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
        return jSONObject;
    }
}
