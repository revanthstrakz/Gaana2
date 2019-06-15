package com.login.nativesso.c;

import com.android.volley.VolleyError;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.login.nativesso.a.v;
import com.login.nativesso.b.a;
import com.login.nativesso.g.b;
import com.login.nativesso.i.c;
import org.json.JSONObject;

public class s extends a {
    public void onErrorResponse(VolleyError volleyError) {
        super.onErrorResponse(volleyError);
        v vVar = (v) a.a("SignUpCb");
        if (vVar != null) {
            vVar.onFailure(c.a((int) SsoErrorCodes.NETWORK_ERROR, "NETWORK_ERROR"));
            a.b("SignUpCb");
        }
    }

    /* renamed from: a */
    public void onResponse(JSONObject jSONObject) {
        v vVar = (v) a.a("SignUpCb");
        try {
            if ("SUCCESS".equalsIgnoreCase(jSONObject.getString("status"))) {
                String string = jSONObject.getJSONObject("data").getString("ssoid");
                b.a().a(com.login.nativesso.d.c.a().d(), "ssoid", string);
                if (vVar != null) {
                    vVar.onSuccess();
                }
            } else {
                String string2 = jSONObject.getString("message");
                int i = jSONObject.getInt("code");
                if (vVar != null) {
                    vVar.onFailure(c.a(i, string2));
                }
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            if (vVar != null) {
                vVar.onFailure(c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
            }
        }
        a.b("SignUpCb");
    }
}
