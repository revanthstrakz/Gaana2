package com.login.nativesso.c;

import com.android.volley.VolleyError;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.login.nativesso.a.r;
import com.login.nativesso.b.a;
import com.login.nativesso.i.c;
import org.json.JSONObject;

public class p extends a {
    public void onErrorResponse(VolleyError volleyError) {
        super.onErrorResponse(volleyError);
        r rVar = (r) a.a("ResendSignUpOtpCb");
        if (rVar != null) {
            rVar.onFailure(c.a((int) SsoErrorCodes.NETWORK_ERROR, "NETWORK_ERROR"));
            a.b("ResendSignUpOtpCb");
        }
    }

    /* renamed from: a */
    public void onResponse(JSONObject jSONObject) {
        r rVar = (r) a.a("ResendSignUpOtpCb");
        try {
            if (!"SUCCESS".equalsIgnoreCase(jSONObject.getString("status"))) {
                String string = jSONObject.getString("message");
                int i = jSONObject.getInt("code");
                if (rVar != null) {
                    rVar.onFailure(c.a(i, string));
                }
            } else if (rVar != null) {
                rVar.onSuccess();
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            if (rVar != null) {
                rVar.onFailure(c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
            }
        }
        a.b("ResendSignUpOtpCb");
    }
}
