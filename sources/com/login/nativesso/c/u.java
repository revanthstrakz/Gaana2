package com.login.nativesso.c;

import android.content.Context;
import com.android.volley.VolleyError;
import com.gaana.login.LoginManager;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.login.nativesso.a.w;
import com.login.nativesso.b.a;
import com.login.nativesso.e.e;
import com.login.nativesso.g.b;
import com.login.nativesso.i.c;
import org.json.JSONObject;

public class u extends a {
    private static String a;

    public void onErrorResponse(VolleyError volleyError) {
        super.onErrorResponse(volleyError);
        w wVar = (w) a.a("SocialLinkCb");
        if (wVar != null) {
            wVar.a(c.a((int) SsoErrorCodes.NETWORK_ERROR, "NETWORK_ERROR"));
            a.b("SocialLinkCb");
        }
    }

    /* renamed from: a */
    public void onResponse(JSONObject jSONObject) {
        w wVar = (w) a.a("SocialLinkCb");
        try {
            if (!"SUCCESS".equalsIgnoreCase(jSONObject.getString("status"))) {
                String string = jSONObject.getString("msg");
                int i = jSONObject.getInt("code");
                if (wVar != null) {
                    wVar.a(c.a(i, string));
                }
            } else if (wVar != null) {
                Context d = com.login.nativesso.d.c.a().d();
                e eVar = (e) com.login.nativesso.g.a.a(d, "object_prefs", 0).a("USER_INFO", e.class);
                if (eVar != null) {
                    if (a.equalsIgnoreCase(LoginManager.SSO_SOCIAL_LOGIN_TYPE_FACEBOOK)) {
                        eVar.a(true);
                    } else if (a.equalsIgnoreCase(LoginManager.SSO_SOCIAL_LOGIN_TYPE_GOOGLE)) {
                        eVar.b(true);
                    }
                    b.a();
                    b.a(d, eVar);
                }
                wVar.a();
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            if (wVar != null) {
                wVar.a(c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
            }
        }
        a.b("SocialLinkCb");
    }

    public static void a(String str) {
        a = str;
    }
}
