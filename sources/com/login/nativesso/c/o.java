package com.login.nativesso.c;

import com.android.volley.VolleyError;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.login.nativesso.a.q;
import com.login.nativesso.b.a;
import com.login.nativesso.i.c;
import org.json.JSONObject;

public class o extends a {
    public void onErrorResponse(VolleyError volleyError) {
        super.onErrorResponse(volleyError);
        q qVar = (q) a.a("ResendFPOtpCb");
        if (qVar != null) {
            qVar.a(c.a((int) SsoErrorCodes.NETWORK_ERROR, "NETWORK_ERROR"));
            a.b("ResendFPOtpCb");
        }
    }

    /* renamed from: a */
    public void onResponse(JSONObject jSONObject) {
        q qVar = (q) a.a("ResendFPOtpCb");
        try {
            if (!"SUCCESS".equalsIgnoreCase(jSONObject.getString("status"))) {
                String string = jSONObject.getString("message");
                int i = jSONObject.getInt("code");
                if (qVar != null) {
                    qVar.a(c.a(i, string));
                }
            } else if (qVar != null) {
                qVar.a();
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            if (qVar != null) {
                qVar.a(c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
            }
        }
        a.b("ResendFPOtpCb");
    }
}
