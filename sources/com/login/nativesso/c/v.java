package com.login.nativesso.c;

import android.content.Context;
import android.util.Log;
import com.android.volley.VolleyError;
import com.android.volley.g;
import com.android.volley.i.a;
import com.android.volley.i.b;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.login.nativesso.a.x;
import com.login.nativesso.exception.SecurityException;
import com.login.nativesso.exception.ServerException;
import com.login.nativesso.i.c;
import org.json.JSONObject;

public class v implements a, b<String> {
    String a = "";

    public v(String str) {
        this.a = str;
    }

    public void onErrorResponse(VolleyError volleyError) {
        x xVar = (x) com.login.nativesso.b.a.a("SocialLoginCb");
        if (xVar != null) {
            xVar.onLoginFailure(c.a((int) SsoErrorCodes.NETWORK_ERROR, "NETWORK_ERROR"));
            com.login.nativesso.b.a.b("SocialLoginCb");
        }
        if (volleyError != null) {
            ThrowableExtension.printStackTrace(volleyError);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Error cause :");
            stringBuilder.append(volleyError.getCause());
            stringBuilder.append(" ,Error Message :");
            stringBuilder.append(volleyError.getMessage());
            Log.e("NATIVESSO", stringBuilder.toString());
            g gVar = volleyError.a;
            if (gVar != null) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Error Http code :");
                stringBuilder.append(gVar.a);
                Log.e("NATIVESSO", stringBuilder.toString());
            }
        }
    }

    /* renamed from: a */
    public void onResponse(String str) {
        x xVar = (x) com.login.nativesso.b.a.a("SocialLoginCb");
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("ssec")) {
                str = jSONObject.getString("ssec");
                String string = jSONObject.getString("ticketId");
                com.login.nativesso.g.b a = com.login.nativesso.g.b.a();
                JSONObject jSONObject2 = new JSONObject();
                Context d = com.login.nativesso.d.c.a().d();
                jSONObject2.put("TGID", a.c(d));
                jSONObject2.put("SSECID", str);
                jSONObject2.put("TICKETID", string);
                jSONObject2.put("SOCIALTYPE", this.a);
                a.a(d, "LAST_SESSION_SRC", this.a);
                a.a(d, "LAST_SESSION_IDENTIFIER", "");
                com.login.nativesso.i.a.a(d, jSONObject2);
                a.a(d, jSONObject2);
                if (xVar != null) {
                    xVar.onLoginSuccess();
                    com.login.nativesso.b.a.b("SocialLoginCb");
                }
            } else if (xVar != null && jSONObject.has("code") && jSONObject.has("msg")) {
                xVar.onLoginFailure(c.a(jSONObject.getInt("code"), jSONObject.getString("msg")));
                com.login.nativesso.b.a.b("SocialLoginCb");
            }
        } catch (ServerException e) {
            if (xVar != null) {
                ThrowableExtension.printStackTrace(e);
                xVar.onLoginFailure(c.a((int) SsoErrorCodes.SERVER_ERROR, "SERVER_ERROR"));
                com.login.nativesso.b.a.b("SocialLoginCb");
                return;
            }
        } catch (SecurityException e2) {
            if (xVar != null) {
                ThrowableExtension.printStackTrace(e2);
                xVar.onLoginFailure(c.a(4008, "SECURITY_ISSUE"));
                com.login.nativesso.b.a.b("SocialLoginCb");
                return;
            }
        } catch (Exception e3) {
            ThrowableExtension.printStackTrace(e3);
            if (xVar != null) {
                xVar.onLoginFailure(c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
                com.login.nativesso.b.a.b("SocialLoginCb");
            }
        }
        com.login.nativesso.b.a.b("SocialLoginCb");
    }
}
