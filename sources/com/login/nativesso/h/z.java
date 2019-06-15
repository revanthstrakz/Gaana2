package com.login.nativesso.h;

import com.android.volley.i.a;
import com.android.volley.i.b;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import org.json.JSONObject;

public class z extends a {
    public z(int i, JSONObject jSONObject, b<JSONObject> bVar, a aVar) {
        super(i, com.login.nativesso.i.b.v, jSONObject, bVar, aVar);
        a(null);
    }

    public static JSONObject a(String str, String str2, String str3, String str4) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("email", str);
            jSONObject.put("mobile", str2);
            jSONObject.put("otp", str3);
            jSONObject.put("ssoid", str4);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        return jSONObject;
    }
}
