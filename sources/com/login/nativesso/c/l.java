package com.login.nativesso.c;

import android.content.Context;
import com.android.volley.VolleyError;
import com.facebook.internal.ServerProtocol;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.login.nativesso.a.m;
import com.login.nativesso.b.a;
import com.login.nativesso.exception.SecurityException;
import com.login.nativesso.exception.ServerException;
import com.login.nativesso.g.b;
import com.login.nativesso.i.c;
import org.json.JSONObject;

public class l extends a {
    public void onErrorResponse(VolleyError volleyError) {
        super.onErrorResponse(volleyError);
        m mVar = (m) a.a("LoginCb");
        if (mVar != null) {
            mVar.onLoginFailure(c.a((int) SsoErrorCodes.NETWORK_ERROR, "NETWORK_ERROR"));
            a.b("LoginCb");
        }
    }

    /* renamed from: a */
    public void onResponse(JSONObject jSONObject) {
        m mVar = (m) a.a("LoginCb");
        try {
            String string;
            if ("SUCCESS".equalsIgnoreCase(jSONObject.getString("status"))) {
                jSONObject = jSONObject.getJSONObject("data");
                string = jSONObject.getString("ssec");
                String string2 = jSONObject.getString("ticketId");
                String string3 = jSONObject.getString("identifier");
                b a = b.a();
                JSONObject jSONObject2 = new JSONObject();
                Context d = com.login.nativesso.d.c.a().d();
                jSONObject2.put("TGID", a.c(d));
                jSONObject2.put("SSECID", string);
                jSONObject2.put("TICKETID", string2);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("sso&");
                stringBuilder.append(string3);
                jSONObject2.put("SOCIALTYPE", stringBuilder.toString());
                com.login.nativesso.i.a.a(d, jSONObject2);
                a.a(d, "LAST_SESSION_SRC", ServerProtocol.DIALOG_PARAM_SSO_DEVICE);
                a.a(d, "LAST_SESSION_IDENTIFIER", string3);
                a.a(d, jSONObject2);
                if (mVar != null) {
                    mVar.onLoginSuccess();
                }
            } else {
                string = jSONObject.getString("message");
                int i = jSONObject.getInt("code");
                if (mVar != null) {
                    mVar.onLoginFailure(c.a(i, string));
                }
            }
        } catch (ServerException e) {
            if (mVar != null) {
                ThrowableExtension.printStackTrace(e);
                mVar.onLoginFailure(c.a((int) SsoErrorCodes.SERVER_ERROR, "SERVER_ERROR"));
                a.b("LoginCb");
                return;
            }
        } catch (SecurityException e2) {
            if (mVar != null) {
                ThrowableExtension.printStackTrace(e2);
                mVar.onLoginFailure(c.a(4008, "SECURITY_ISSUE"));
                a.b("LoginCb");
                return;
            }
        } catch (Exception e3) {
            ThrowableExtension.printStackTrace(e3);
            if (mVar != null) {
                mVar.onLoginFailure(c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
            }
        }
        a.b("LoginCb");
    }
}
