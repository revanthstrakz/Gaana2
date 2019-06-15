package com.login.nativesso.h;

import com.android.volley.i.a;
import com.android.volley.i.b;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import org.json.JSONObject;

public class o extends a {
    public o(int i, JSONObject jSONObject, b<JSONObject> bVar, a aVar) {
        super(i, com.login.nativesso.i.b.u, jSONObject, bVar, aVar);
        a(null);
    }

    public static JSONObject a(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("email", str);
            jSONObject.put("mobile", str2);
            jSONObject.put("ssoid", str3);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        return jSONObject;
    }
}
