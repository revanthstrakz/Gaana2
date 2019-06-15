package com.login.nativesso.c;

import android.util.Log;
import com.android.volley.VolleyError;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.login.nativesso.a.aa;
import com.login.nativesso.b.a;
import com.login.nativesso.i.c;
import org.json.JSONObject;

public class x extends a {
    public void onErrorResponse(VolleyError volleyError) {
        super.onErrorResponse(volleyError);
        aa aaVar = (aa) a.a("UpdateEmailAndMobileCb");
        if (aaVar != null) {
            aaVar.a(c.a((int) SsoErrorCodes.NETWORK_ERROR, "NETWORK_ERROR"));
            a.b("UpdateEmailAndMobileCb");
        }
    }

    /* renamed from: a */
    public void onResponse(JSONObject jSONObject) {
        aa aaVar = (aa) a.a("UpdateEmailAndMobileCb");
        try {
            if (!jSONObject.getString("status").equalsIgnoreCase("success")) {
                String string = jSONObject.getString("message");
                if ("UNAUTHORIZED_ACCESS".equals(string)) {
                    c.a(com.login.nativesso.d.c.a().d());
                }
                if (aaVar != null) {
                    aaVar.a(c.a(jSONObject.getInt("code"), string));
                }
            } else if (aaVar != null) {
                aaVar.a();
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            Log.e("NATIVESSO", "Exception while parsing UpdateEmailMobile response");
            if (aaVar != null) {
                aaVar.a(c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
            }
        }
        a.b("UpdateEmailAndMobileCb");
        Log.e("NATIVESSO", "UpdateEmailAndMobileCb null");
    }
}
