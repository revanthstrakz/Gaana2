package com.login.nativesso.c;

import com.android.volley.VolleyError;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.login.nativesso.a.k;
import com.login.nativesso.b.a;
import com.login.nativesso.e.d;
import com.login.nativesso.i.c;
import org.json.JSONObject;

public class g extends a {
    public void onErrorResponse(VolleyError volleyError) {
        super.onErrorResponse(volleyError);
        k kVar = (k) a.a("GetLoginOtpCb");
        if (kVar != null) {
            kVar.a(c.a((int) SsoErrorCodes.NETWORK_ERROR, "NETWORK_ERROR"));
            a.b("GetLoginOtpCb");
        }
    }

    /* renamed from: a */
    public void onResponse(JSONObject jSONObject) {
        k kVar = (k) a.a("GetLoginOtpCb");
        try {
            if ("SUCCESS".equalsIgnoreCase(jSONObject.getString("status"))) {
                jSONObject = jSONObject.getJSONObject("data");
                d dVar = new d(jSONObject.getBoolean("newUser"), jSONObject.getBoolean("otpSent"), jSONObject.getBoolean("unverifiedUser"), jSONObject.getBoolean("defunc"));
                if (kVar != null) {
                    kVar.a(dVar);
                }
            } else {
                String string = jSONObject.getString("message");
                int i = jSONObject.getInt("code");
                if (kVar != null) {
                    kVar.a(c.a(i, string));
                }
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            if (kVar != null) {
                kVar.a(c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
            }
        }
        a.b("GetLoginOtpCb");
    }
}
