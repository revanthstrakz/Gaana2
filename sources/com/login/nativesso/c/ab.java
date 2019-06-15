package com.login.nativesso.c;

import android.util.Log;
import com.android.volley.VolleyError;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.login.nativesso.a.af;
import com.login.nativesso.a.l;
import com.login.nativesso.b.a;
import com.login.nativesso.e.e;
import com.login.nativesso.g.b;
import com.login.nativesso.i.c;
import in.til.core.integrations.TILSDKExceptionDto;
import java.util.Map;
import org.json.JSONObject;

public class ab extends a {
    private String a;

    public ab(String str) {
        this.a = str;
    }

    public void onErrorResponse(VolleyError volleyError) {
        super.onErrorResponse(volleyError);
        af afVar = (af) a.a("VerifyEmailAndMobileCb");
        if (afVar != null) {
            afVar.a(c.a((int) SsoErrorCodes.NETWORK_ERROR, "NETWORK_ERROR"));
            a.b("VerifyEmailAndMobileCb");
        }
    }

    /* renamed from: a */
    public void onResponse(JSONObject jSONObject) {
        af afVar = (af) a.a("VerifyEmailAndMobileCb");
        try {
            if (!jSONObject.getString("status").equalsIgnoreCase("success")) {
                String string = jSONObject.getString("message");
                if ("UNAUTHORIZED_ACCESS".equals(string)) {
                    c.a(com.login.nativesso.d.c.a().d());
                }
                if (afVar != null) {
                    int i = jSONObject.getInt("code");
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("");
                    stringBuilder.append(string);
                    afVar.a(c.a(i, stringBuilder.toString()));
                }
            } else if (afVar != null) {
                if (this.a.contains("@")) {
                    afVar.a();
                    com.login.nativesso.d.c.a().a(new l() {
                        public void onFailure(com.login.nativesso.e.c cVar) {
                        }

                        public void onSdkFailure(TILSDKExceptionDto tILSDKExceptionDto) {
                        }

                        public void onSuccess(e eVar) {
                        }
                    });
                } else {
                    e eVar = (e) com.login.nativesso.g.a.a(com.login.nativesso.d.c.a().d(), "object_prefs", 0).a("USER_INFO", e.class);
                    Map a = eVar.a();
                    a.clear();
                    a.put(this.a, "Verified");
                    eVar.b(a);
                    b.a();
                    b.a(com.login.nativesso.d.c.a().d(), eVar);
                    afVar.a();
                }
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            Log.e("NATIVESSO", "Exception while VerifyEmailAndMobileListener");
            if (afVar != null) {
                afVar.a(c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
            }
        }
        a.b("VerifyEmailAndMobileCb");
        Log.e("NATIVESSO", "VerifyEmailAndMobileCb null");
    }
}
