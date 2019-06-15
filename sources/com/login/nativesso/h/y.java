package com.login.nativesso.h;

import com.android.volley.i.a;
import com.android.volley.i.b;
import com.gaana.login.LoginManager;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class y extends a {
    public y(int i, JSONObject jSONObject, b<JSONObject> bVar, a aVar, Map<String, String> map) {
        super(i, com.login.nativesso.i.b.s, jSONObject, bVar, aVar);
        a(map);
    }

    public static JSONObject a(String str, String str2, String str3, String str4, String str5) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("email", str);
            jSONObject.put("mobile", str2);
            jSONObject.put("otp", str3);
            jSONObject.put(LoginManager.TAG_PASSWORD, str4);
            jSONObject.put("confirmPassword", str5);
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
        return jSONObject;
    }
}
