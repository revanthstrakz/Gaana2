package com.login.nativesso.c;

import android.content.Context;
import com.android.volley.VolleyError;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.login.nativesso.a.y;
import com.login.nativesso.b.a;
import com.login.nativesso.e.e;
import com.login.nativesso.g.b;
import com.login.nativesso.i.c;
import org.json.JSONObject;

public class w extends a {
    public void onErrorResponse(VolleyError volleyError) {
        super.onErrorResponse(volleyError);
        y yVar = (y) a.a("SocialPicUploadCb");
        if (yVar != null) {
            yVar.a(c.a((int) SsoErrorCodes.NETWORK_ERROR, "NETWORK_ERROR"));
            a.b("SocialPicUploadCb");
        }
    }

    /* renamed from: a */
    public void onResponse(JSONObject jSONObject) {
        y yVar = (y) a.a("SocialPicUploadCb");
        try {
            if (!"SUCCESS".equalsIgnoreCase(jSONObject.getString("status"))) {
                String string = jSONObject.getString("msg");
                int i = jSONObject.getInt("code");
                if (yVar != null) {
                    yVar.a(c.a(i, string));
                }
            } else if (yVar != null) {
                jSONObject = jSONObject.getJSONObject("data");
                if (jSONObject != null) {
                    String string2 = jSONObject.getString("picUrl");
                    Context d = com.login.nativesso.d.c.a().d();
                    e eVar = (e) com.login.nativesso.g.a.a(d, "object_prefs", 0).a("USER_INFO", e.class);
                    if (eVar != null) {
                        eVar.a(string2);
                        b.a();
                        b.a(d, eVar);
                    }
                    yVar.a(string2);
                }
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            if (yVar != null) {
                yVar.a(c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
            }
        }
        a.b("SocialPicUploadCb");
    }
}
