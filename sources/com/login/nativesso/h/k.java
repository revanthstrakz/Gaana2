package com.login.nativesso.h;

import com.android.volley.i.a;
import com.android.volley.i.b;
import com.gaana.login.LoginManager;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class k extends a {
    public k(int i, JSONObject jSONObject, b<JSONObject> bVar, a aVar, Map<String, String> map, String str) {
        super(i, str, jSONObject, bVar, aVar);
        a(map);
    }

    public static JSONObject a(String str, String str2, String str3, String str4, String str5) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("email", str);
            jSONObject.put(LoginManager.TAG_PASSWORD, str2);
            if (str3 != null) {
                jSONObject.put("termsAccepted", str3);
            }
            if (str4 != null) {
                jSONObject.put("shareDataAllowed", str4);
            }
            if (str5 != null) {
                jSONObject.put("timespointsPolicy", str5);
            }
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
        return jSONObject;
    }

    public static JSONObject b(String str, String str2, String str3, String str4, String str5) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mobile", str);
            jSONObject.put(LoginManager.TAG_PASSWORD, str2);
            if (str3 != null) {
                jSONObject.put("termsAccepted", str3);
            }
            if (str4 != null) {
                jSONObject.put("shareDataAllowed", str4);
            }
            if (str5 != null) {
                jSONObject.put("timespointsPolicy", str5);
            }
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
        return jSONObject;
    }
}
