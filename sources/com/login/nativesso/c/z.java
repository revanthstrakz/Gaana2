package com.login.nativesso.c;

import android.content.Context;
import android.util.Log;
import com.android.volley.VolleyError;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.login.nativesso.a.ac;
import com.login.nativesso.b.a;
import com.login.nativesso.e.e;
import com.login.nativesso.e.i;
import com.login.nativesso.g.b;
import com.login.nativesso.i.c;
import org.json.JSONObject;

public class z extends a {
    public void onErrorResponse(VolleyError volleyError) {
        super.onErrorResponse(volleyError);
        ac acVar = (ac) a.a("UpdateUserPermissionsCb");
        if (acVar != null) {
            acVar.a(c.a((int) SsoErrorCodes.NETWORK_ERROR, "NETWORK_ERROR"));
            a.b("UpdateUserPermissionsCb");
        }
    }

    /* renamed from: a */
    public void onResponse(JSONObject jSONObject) {
        ac acVar = (ac) a.a("UpdateUserPermissionsCb");
        try {
            if (!"success".equalsIgnoreCase(jSONObject.getString("status"))) {
                String string = jSONObject.getString("message");
                if ("UNAUTHORIZED_ACCESS".equals(string)) {
                    c.a(com.login.nativesso.d.c.a().d());
                }
                if (acVar != null) {
                    acVar.a(c.a(jSONObject.getInt("code"), string));
                }
            } else if (acVar != null) {
                i iVar = new i();
                if (jSONObject.has("data")) {
                    jSONObject = jSONObject.getJSONObject("data");
                    if (jSONObject != null) {
                        iVar.a(com.login.nativesso.i.a.a(jSONObject, "termsAccepted"));
                        iVar.b(com.login.nativesso.i.a.a(jSONObject, "shareDataAllowed"));
                        iVar.c(com.login.nativesso.i.a.a(jSONObject, "timespointsPolicy"));
                        Context d = com.login.nativesso.d.c.a().d();
                        e eVar = (e) com.login.nativesso.g.a.a(d, "object_prefs", 0).a("USER_INFO", e.class);
                        if (eVar != null) {
                            eVar.k(com.login.nativesso.i.a.a(jSONObject, "termsAccepted"));
                            eVar.j(com.login.nativesso.i.a.a(jSONObject, "shareDataAllowed"));
                            eVar.q(com.login.nativesso.i.a.a(jSONObject, "timespointsPolicy"));
                            b.a();
                            b.a(d, eVar);
                        }
                    }
                }
                acVar.a(iVar);
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            Log.e("NATIVESSO", "Exception while parsing UpdateEmailMobile response");
            if (acVar != null) {
                acVar.a(c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
            }
        }
        Log.e("NATIVESSO", "UpdateUserCb null");
        a.b("UpdateUserPermissionsCb");
    }
}
