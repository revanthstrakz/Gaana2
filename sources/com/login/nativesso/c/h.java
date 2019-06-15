package com.login.nativesso.c;

import android.util.Log;
import com.android.volley.VolleyError;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.login.nativesso.a.e;
import com.login.nativesso.b.a;
import com.login.nativesso.g.b;
import com.login.nativesso.i.c;
import org.json.JSONObject;

public class h extends a {
    public void onErrorResponse(VolleyError volleyError) {
        super.onErrorResponse(volleyError);
        e eVar = (e) a.a("CopyGlobalSession");
        if (eVar != null) {
            eVar.a(c.a((int) SsoErrorCodes.NETWORK_ERROR, "NETWORK_ERROR"));
            a.b("CopyGlobalSession");
        }
    }

    /* renamed from: a */
    public void onResponse(JSONObject jSONObject) {
        e eVar = (e) a.a("CopyGlobalSession");
        try {
            if ("SUCCESS".equalsIgnoreCase(jSONObject.getString("status"))) {
                b.a().a(com.login.nativesso.d.c.a().d(), "TICKETID", jSONObject.getJSONObject("data").getString("ticketId"));
                if (eVar != null) {
                    eVar.a();
                }
            } else if (eVar != null) {
                String string = jSONObject.getString("message");
                int i = jSONObject.getInt("code");
                if (eVar != null) {
                    eVar.a(c.a(i, string));
                }
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            Log.e("NATIVESSO", "Exception while parsing GetNewTicket response");
        }
        a.b("CopyGlobalSession");
    }
}
