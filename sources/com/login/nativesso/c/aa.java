package com.login.nativesso.c;

import android.util.Log;
import com.android.volley.VolleyError;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.login.nativesso.a.ae;
import com.login.nativesso.b.a;
import com.login.nativesso.i.c;
import org.json.JSONObject;

public class aa extends a {
    public void onErrorResponse(VolleyError volleyError) {
        super.onErrorResponse(volleyError);
        ae aeVar = (ae) a.a("ValidatePasswordCb");
        if (aeVar != null) {
            aeVar.a(c.a((int) SsoErrorCodes.NETWORK_ERROR, "NETWORK_ERROR"));
            a.b("ValidatePasswordCb");
        }
    }

    /* renamed from: a */
    public void onResponse(JSONObject jSONObject) {
        ae aeVar = (ae) a.a("ValidatePasswordCb");
        try {
            if (!jSONObject.getString("status").equalsIgnoreCase("success")) {
                String string = jSONObject.getString("message");
                if ("UNAUTHORIZED_ACCESS".equals(string)) {
                    c.a(com.login.nativesso.d.c.a().d());
                }
                if (aeVar != null) {
                    int i = jSONObject.getInt("code");
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("");
                    stringBuilder.append(string);
                    aeVar.a(c.a(i, stringBuilder.toString()));
                }
            } else if (aeVar != null) {
                aeVar.a();
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            Log.e("NATIVESSO", "Exception in ValidatePassword");
            if (aeVar != null) {
                aeVar.a(c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
            }
        }
        a.b("ValidatePasswordCb");
        Log.e("NATIVESSO", "ValidateMobileCb null");
    }
}
