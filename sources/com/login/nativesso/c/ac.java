package com.login.nativesso.c;

import com.android.volley.VolleyError;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.login.nativesso.a.ag;
import com.login.nativesso.b.a;
import com.login.nativesso.i.c;
import org.json.JSONObject;

public class ac extends a {
    public void onErrorResponse(VolleyError volleyError) {
        super.onErrorResponse(volleyError);
        ag agVar = (ag) a.a("VerifyForgotPassOtpCb");
        if (agVar != null) {
            agVar.a(c.a((int) SsoErrorCodes.NETWORK_ERROR, "NETWORK_ERROR"));
            a.b("VerifyForgotPassOtpCb");
        }
    }

    /* renamed from: a */
    public void onResponse(JSONObject jSONObject) {
        ag agVar = (ag) a.a("VerifyForgotPassOtpCb");
        try {
            if (!"SUCCESS".equalsIgnoreCase(jSONObject.getString("status"))) {
                String string = jSONObject.getString("message");
                int i = jSONObject.getInt("code");
                if (agVar != null) {
                    agVar.a(c.a(i, string));
                }
            } else if (agVar != null) {
                agVar.a();
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            if (agVar != null) {
                agVar.a(c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
            }
        }
        a.b("VerifyForgotPassOtpCb");
    }
}
