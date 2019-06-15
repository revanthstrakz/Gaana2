package com.login.nativesso.c;

import android.util.Log;
import com.android.volley.VolleyError;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.login.nativesso.b.a;
import com.login.nativesso.e.b;
import com.login.nativesso.i.c;
import org.json.JSONObject;

public class d extends a {
    String a;
    String b;
    String c;

    public void onErrorResponse(VolleyError volleyError) {
        super.onErrorResponse(volleyError);
        com.login.nativesso.a.d dVar = (com.login.nativesso.a.d) a.a("CheckUserExistCb");
        if (dVar != null) {
            dVar.a(c.a((int) SsoErrorCodes.NETWORK_ERROR, "NETWORK_ERROR"));
            a.b("CheckUserExistCb");
        }
    }

    /* renamed from: a */
    public void onResponse(JSONObject jSONObject) {
        com.login.nativesso.a.d dVar = (com.login.nativesso.a.d) a.a("CheckUserExistCb");
        try {
            if (!jSONObject.getString("status").equalsIgnoreCase("success")) {
                String string = jSONObject.getString("message");
                if (dVar != null) {
                    dVar.a(c.a(jSONObject.getInt("code"), string));
                }
            } else if (dVar != null) {
                jSONObject = jSONObject.getJSONObject("data");
                if (jSONObject != null) {
                    String string2 = jSONObject.getString("status");
                    if (jSONObject.has("termsAccepted")) {
                        this.a = jSONObject.getString("termsAccepted");
                    }
                    if (jSONObject.has("shareDataAllowed")) {
                        this.b = jSONObject.getString("shareDataAllowed");
                    }
                    if (jSONObject.has("timespointsPolicy")) {
                        this.c = jSONObject.getString("timespointsPolicy");
                    }
                    dVar.a(new b(string2, jSONObject.getInt("statusCode"), this.a, this.b, this.c));
                }
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            Log.e("NATIVESSO", "Exception while  checking user exist response");
            if (dVar != null) {
                dVar.a(c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
            }
        }
        Log.e("NATIVESSO", "CheckUserExistCb null");
        a.b("CheckUserExistCb");
    }
}
