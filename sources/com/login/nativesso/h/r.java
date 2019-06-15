package com.login.nativesso.h;

import com.android.volley.i.a;
import com.android.volley.i.b;
import com.gaana.login.LoginManager;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.login.nativesso.e.g;
import java.util.Map;
import org.json.JSONObject;

public class r extends a {
    public r(int i, JSONObject jSONObject, b<JSONObject> bVar, a aVar, Map<String, String> map) {
        super(i, com.login.nativesso.i.b.t, jSONObject, bVar, aVar);
        a(map);
    }

    public static JSONObject a(g gVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("email", gVar.a());
            jSONObject.put("name", gVar.e());
            jSONObject.put("gender", gVar.b());
            jSONObject.put("mobile", gVar.c());
            jSONObject.put(LoginManager.TAG_PASSWORD, gVar.d());
            jSONObject.put("isSendOffer", gVar.f());
            jSONObject.put("termsAccepted", gVar.h());
            jSONObject.put("shareDataAllowed", gVar.i());
            jSONObject.put("timespointsPolicy", gVar.j());
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        return jSONObject;
    }
}
