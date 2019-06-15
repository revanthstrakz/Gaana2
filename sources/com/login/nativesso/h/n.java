package com.login.nativesso.h;

import com.android.volley.i.a;
import com.android.volley.i.b;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class n extends a {
    public n(int i, JSONObject jSONObject, b<JSONObject> bVar, a aVar, Map<String, String> map) {
        super(i, com.login.nativesso.i.b.l, jSONObject, bVar, aVar);
        a(map);
    }

    public static JSONObject a(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("email", str);
            jSONObject.put("mobile", str2);
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
        return jSONObject;
    }
}
