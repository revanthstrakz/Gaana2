package com.login.nativesso.c;

import android.content.Context;
import com.android.volley.VolleyError;
import com.gaana.login.LoginManager;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.login.nativesso.a.g;
import com.login.nativesso.b.a;
import com.login.nativesso.e.e;
import com.login.nativesso.g.b;
import com.login.nativesso.i.c;
import org.json.JSONObject;

public class t extends a {
    private static String a;

    public void onErrorResponse(VolleyError volleyError) {
        super.onErrorResponse(volleyError);
        g gVar = (g) a.a("DelinkCb");
        if (gVar != null) {
            gVar.a(c.a((int) SsoErrorCodes.NETWORK_ERROR, "NETWORK_ERROR"));
            a.b("DelinkCb");
        }
    }

    /* renamed from: a */
    public void onResponse(JSONObject jSONObject) {
        g gVar = (g) a.a("DelinkCb");
        try {
            if (!"SUCCESS".equalsIgnoreCase(jSONObject.getString("status"))) {
                String string = jSONObject.getString("message");
                int i = jSONObject.getInt("code");
                if (gVar != null) {
                    gVar.a(c.a(i, string));
                }
            } else if (gVar != null) {
                Context d = com.login.nativesso.d.c.a().d();
                e eVar = (e) com.login.nativesso.g.a.a(d, "object_prefs", 0).a("USER_INFO", e.class);
                if (eVar != null) {
                    if (a.equalsIgnoreCase(LoginManager.SSO_SOCIAL_LOGIN_TYPE_FACEBOOK)) {
                        eVar.a(false);
                    } else if (a.equalsIgnoreCase(LoginManager.SSO_SOCIAL_LOGIN_TYPE_GOOGLE)) {
                        eVar.b(false);
                    }
                    b.a();
                    b.a(d, eVar);
                }
                gVar.a();
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            if (gVar != null) {
                gVar.a(c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
            }
        }
        a.b("DelinkCb");
    }

    public static void a(String str) {
        a = str;
    }
}
