package com.login.nativesso.c;

import com.android.volley.VolleyError;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.login.nativesso.a.t;
import com.login.nativesso.b.a;
import com.login.nativesso.i.c;
import org.json.JSONObject;

public class q extends a {
    public void onErrorResponse(VolleyError volleyError) {
        super.onErrorResponse(volleyError);
        t tVar = (t) a.a("SetPasswordCb");
        if (tVar != null) {
            tVar.a(c.a((int) SsoErrorCodes.NETWORK_ERROR, "NETWORK_ERROR"));
            a.b("SetPasswordCb");
        }
    }

    /* renamed from: a */
    public void onResponse(JSONObject jSONObject) {
        t tVar = (t) a.a("SetPasswordCb");
        try {
            if (!"SUCCESS".equalsIgnoreCase(jSONObject.getString("status"))) {
                String string = jSONObject.getString("message");
                if ("UNAUTHORIZED_ACCESS".equals(string)) {
                    c.a(com.login.nativesso.d.c.a().d());
                }
                if (tVar != null) {
                    tVar.a(c.a(jSONObject.getInt("code"), string));
                }
            } else if (tVar != null) {
                tVar.a();
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            if (tVar != null) {
                tVar.a(c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
            }
        }
        a.b("SetPasswordCb");
    }
}
