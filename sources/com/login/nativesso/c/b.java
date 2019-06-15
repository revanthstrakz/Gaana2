package com.login.nativesso.c;

import com.android.volley.VolleyError;
import com.gaana.login.sso.SsoErrorCodes;
import com.login.nativesso.a.a;
import com.login.nativesso.i.c;
import org.json.JSONObject;

public class b extends a {
    public void onErrorResponse(VolleyError volleyError) {
        super.onErrorResponse(volleyError);
        a aVar = (a) com.login.nativesso.b.a.a("BlockUserChannelCb");
        if (aVar != null) {
            aVar.a(c.a((int) SsoErrorCodes.NETWORK_ERROR, "NETWORK_ERROR"));
            com.login.nativesso.b.a.b("BlockUserChannelCb");
        }
    }

    /* renamed from: a */
    public void onResponse(JSONObject jSONObject) {
        a aVar = (a) com.login.nativesso.b.a.a("BlockUserChannelCb");
        try {
            if ("success".equalsIgnoreCase(jSONObject.getString("status"))) {
                aVar.a();
            } else {
                String string = jSONObject.getString("message");
                if (aVar != null) {
                    aVar.a(c.a(jSONObject.getInt("code"), string));
                }
            }
        } catch (Exception unused) {
            if (aVar != null) {
                aVar.a(c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
            }
        }
        com.login.nativesso.b.a.b("BlockUserChannelCb");
    }
}
