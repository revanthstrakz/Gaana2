package com.login.nativesso.c;

import android.util.Log;
import com.android.volley.VolleyError;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.login.nativesso.a.o;
import com.login.nativesso.b.a;
import com.login.nativesso.g.b;
import com.login.nativesso.i.c;
import org.json.JSONObject;

public class n extends a {
    public void onErrorResponse(VolleyError volleyError) {
        super.onErrorResponse(volleyError);
        o oVar = (o) a.a("RegisterMobileCb");
        if (oVar != null) {
            oVar.a(c.a((int) SsoErrorCodes.NETWORK_ERROR, "NETWORK_ERROR"));
            a.b("RegisterMobileCb");
        }
    }

    /* renamed from: a */
    public void onResponse(JSONObject jSONObject) {
        o oVar = (o) a.a("RegisterMobileCb");
        try {
            if (!jSONObject.getString("status").equalsIgnoreCase("success")) {
                String string = jSONObject.getString("message");
                if (oVar != null) {
                    oVar.a(c.a(jSONObject.getInt("code"), string));
                }
            } else if (oVar != null) {
                jSONObject = jSONObject.getJSONObject("data");
                if (jSONObject != null) {
                    String string2 = jSONObject.getString("ssoid");
                    b.a().a(com.login.nativesso.d.c.a().d(), "ssoid", string2);
                    oVar.a();
                }
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            Log.e("NATIVESSO", "Exception while  signinBy Register Only Mobile response");
            if (oVar != null) {
                oVar.a(c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
            }
        }
        Log.e("NATIVESSO", "RegisterMobileCb null");
        a.b("RegisterMobileCb");
    }
}
