package com.login.nativesso.c;

import android.content.Context;
import com.android.volley.VolleyError;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.login.nativesso.a.f;
import com.login.nativesso.b.a;
import com.login.nativesso.exception.SecurityException;
import com.login.nativesso.exception.ServerException;
import com.login.nativesso.g.b;
import com.login.nativesso.i.c;
import org.json.JSONObject;

public class e extends a {
    public void onErrorResponse(VolleyError volleyError) {
        super.onErrorResponse(volleyError);
        f fVar = (f) a.a("CreateUnverfiedSessCb");
        if (fVar != null) {
            fVar.onFailure(c.a((int) SsoErrorCodes.NETWORK_ERROR, "NETWORK_ERROR"));
            a.b("CreateUnverfiedSessCb");
        }
    }

    /* renamed from: a */
    public void onResponse(JSONObject jSONObject) {
        f fVar = (f) a.a("CreateUnverfiedSessCb");
        try {
            String string;
            if ("SUCCESS".equalsIgnoreCase(jSONObject.getString("status"))) {
                jSONObject = jSONObject.getJSONObject("data");
                string = jSONObject.getString("ssec");
                String string2 = jSONObject.getString("ticketId");
                String string3 = jSONObject.getString("type");
                String string4 = jSONObject.getString("identifier");
                Context d = com.login.nativesso.d.c.a().d();
                b.a().a(d, "SSECID", string);
                b.a().a(d, "TICKETID", string2);
                b.a().a(d, "LAST_SESSION_SRC", string3);
                b.a().a(d, "LAST_SESSION_IDENTIFIER", string4);
                if (fVar != null) {
                    fVar.onSuccess();
                    a.b("CreateUnverfiedSessCb");
                    return;
                }
            }
            string = jSONObject.getString("message");
            int i = jSONObject.getInt("code");
            if (fVar != null) {
                fVar.onFailure(c.a(i, string));
                a.b("CreateUnverfiedSessCb");
                return;
            }
        } catch (ServerException e) {
            if (fVar != null) {
                ThrowableExtension.printStackTrace(e);
                fVar.onFailure(c.a((int) SsoErrorCodes.SERVER_ERROR, "SERVER_ERROR"));
                a.b("CreateUnverfiedSessCb");
                return;
            }
        } catch (SecurityException e2) {
            if (fVar != null) {
                ThrowableExtension.printStackTrace(e2);
                fVar.onFailure(c.a(4008, "SECURITY_ISSUE"));
                a.b("CreateUnverfiedSessCb");
                return;
            }
        } catch (Exception e3) {
            ThrowableExtension.printStackTrace(e3);
            if (fVar != null) {
                fVar.onFailure(c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
            }
        }
        a.b("CreateUnverfiedSessCb");
    }
}
