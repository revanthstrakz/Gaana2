package com.login.nativesso.c;

import com.android.volley.VolleyError;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.login.nativesso.a.b;
import com.login.nativesso.b.a;
import org.json.JSONObject;

public class c extends a {
    public void onErrorResponse(VolleyError volleyError) {
        super.onErrorResponse(volleyError);
        b bVar = (b) a.a("ChangePasswordCb");
        if (bVar != null) {
            bVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.NETWORK_ERROR, "NETWORK_ERROR"));
            a.b("ChangePasswordCb");
        }
    }

    /* renamed from: a */
    public void onResponse(JSONObject jSONObject) {
        b bVar = (b) a.a("ChangePasswordCb");
        try {
            if (!"SUCCESS".equalsIgnoreCase(jSONObject.getString("status"))) {
                String string = jSONObject.getString("message");
                int i = jSONObject.getInt("code");
                if ("UNAUTHORIZED_ACCESS".equals(string)) {
                    com.login.nativesso.i.c.a(com.login.nativesso.d.c.a().d());
                }
                if (bVar != null) {
                    bVar.a(com.login.nativesso.i.c.a(i, string));
                }
            } else if (bVar != null) {
                bVar.a();
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            if (bVar != null) {
                bVar.a(com.login.nativesso.i.c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
            }
        }
        a.b("ChangePasswordCb");
    }
}
