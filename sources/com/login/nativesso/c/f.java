package com.login.nativesso.c;

import com.android.volley.VolleyError;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.login.nativesso.a.i;
import com.login.nativesso.b.a;
import com.login.nativesso.i.c;
import org.json.JSONObject;

public class f extends a {
    public void onErrorResponse(VolleyError volleyError) {
        super.onErrorResponse(volleyError);
        i iVar = (i) a.a("GetForgotPassOtpCb");
        if (iVar != null) {
            iVar.a(c.a((int) SsoErrorCodes.NETWORK_ERROR, "NETWORK_ERROR"));
            a.b("GetForgotPassOtpCb");
        }
    }

    /* renamed from: a */
    public void onResponse(JSONObject jSONObject) {
        i iVar = (i) a.a("GetForgotPassOtpCb");
        try {
            if (!"SUCCESS".equalsIgnoreCase(jSONObject.getString("status"))) {
                String string = jSONObject.getString("message");
                int i = jSONObject.getInt("code");
                if (iVar != null) {
                    iVar.a(c.a(i, string));
                }
            } else if (iVar != null) {
                iVar.a();
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            if (iVar != null) {
                iVar.a(c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
            }
        }
        a.b("GetForgotPassOtpCb");
    }
}
