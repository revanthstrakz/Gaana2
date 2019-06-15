package com.login.nativesso.c;

import android.content.Context;
import android.util.Log;
import com.android.volley.VolleyError;
import com.gaana.login.LoginManager;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.login.nativesso.a.ab;
import com.login.nativesso.b.a;
import com.login.nativesso.e.e;
import com.login.nativesso.e.h;
import com.login.nativesso.g.b;
import com.login.nativesso.i.c;
import org.json.JSONObject;

public class y extends a {
    public void onErrorResponse(VolleyError volleyError) {
        super.onErrorResponse(volleyError);
        ab abVar = (ab) a.a("UpdateUserCb");
        if (abVar != null) {
            abVar.a(c.a((int) SsoErrorCodes.NETWORK_ERROR, "NETWORK_ERROR"));
            a.b("UpdateUserCb");
        }
    }

    /* renamed from: a */
    public void onResponse(JSONObject jSONObject) {
        ab abVar = (ab) a.a("UpdateUserCb");
        try {
            if (!"success".equalsIgnoreCase(jSONObject.getString("status"))) {
                String string = jSONObject.getString("message");
                if ("UNAUTHORIZED_ACCESS".equals(string)) {
                    c.a(com.login.nativesso.d.c.a().d());
                }
                if (abVar != null) {
                    abVar.a(c.a(jSONObject.getInt("code"), string));
                }
            } else if (abVar != null) {
                h hVar = new h();
                if (jSONObject.has("data")) {
                    jSONObject = jSONObject.getJSONObject("data");
                    if (jSONObject != null) {
                        hVar.a(com.login.nativesso.i.a.a(jSONObject, "firstName"));
                        hVar.b(com.login.nativesso.i.a.a(jSONObject, "lastName"));
                        hVar.c(com.login.nativesso.i.a.a(jSONObject, "gender"));
                        hVar.d(com.login.nativesso.i.a.a(jSONObject, LoginManager.TAG_DOB));
                        hVar.e(com.login.nativesso.i.a.a(jSONObject, "city"));
                        Context d = com.login.nativesso.d.c.a().d();
                        e eVar = (e) com.login.nativesso.g.a.a(d, "object_prefs", 0).a("USER_INFO", e.class);
                        if (eVar != null) {
                            eVar.b(com.login.nativesso.i.a.a(jSONObject, "firstName"));
                            eVar.c(com.login.nativesso.i.a.a(jSONObject, "lastName"));
                            eVar.d(com.login.nativesso.i.a.a(jSONObject, "gender"));
                            eVar.e(com.login.nativesso.i.a.a(jSONObject, LoginManager.TAG_DOB));
                            eVar.h(com.login.nativesso.i.a.a(jSONObject, "city"));
                            b.a();
                            b.a(d, eVar);
                        }
                    }
                }
                abVar.a(hVar);
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            Log.e("NATIVESSO", "Exception while parsing UpdateEmailMobile response");
            if (abVar != null) {
                abVar.a(c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
            }
        }
        Log.e("NATIVESSO", "UpdateUserCb null");
        a.b("UpdateUserCb");
    }
}
