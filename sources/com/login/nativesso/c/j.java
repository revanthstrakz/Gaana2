package com.login.nativesso.c;

import android.util.Log;
import com.android.volley.VolleyError;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.login.nativesso.a.p;
import com.login.nativesso.b.a;
import com.login.nativesso.i.c;
import org.json.JSONObject;

public class j extends a {
    public void onErrorResponse(VolleyError volleyError) {
        super.onErrorResponse(volleyError);
        p pVar = (p) a.a("RenewTicketCallback");
        if (pVar != null) {
            pVar.a(c.a((int) SsoErrorCodes.NETWORK_ERROR, "NETWORK_ERROR"));
            a.b("RenewTicketCallback");
        }
    }

    /* renamed from: a */
    public void onResponse(JSONObject jSONObject) {
        p pVar = (p) a.a("RenewTicketCallback");
        try {
            if (!"SUCCESS".equalsIgnoreCase(jSONObject.getString("status"))) {
                c.a(com.login.nativesso.d.c.a().d());
                if (pVar != null) {
                    pVar.a(c.a(jSONObject.getInt("code"), jSONObject.getString("message")));
                }
            } else if (pVar != null) {
                pVar.a();
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            Log.e("NATIVESSO", "exception in renew ticket");
            if (pVar != null) {
                pVar.a(c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
            }
        }
        a.b("RenewTicketCallback");
    }
}
