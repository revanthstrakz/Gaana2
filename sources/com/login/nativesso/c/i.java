package com.login.nativesso.c;

import android.content.Context;
import android.util.Log;
import com.android.volley.VolleyError;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.login.nativesso.a.j;
import com.login.nativesso.b.a;
import com.login.nativesso.e.f;
import com.login.nativesso.exception.SecurityException;
import com.login.nativesso.exception.ServerException;
import com.login.nativesso.i.c;
import org.json.JSONException;
import org.json.JSONObject;

public class i extends a {
    public void onErrorResponse(VolleyError volleyError) {
        super.onErrorResponse(volleyError);
        j jVar = (j) a.a("GetGlobalSessionCb");
        if (jVar != null) {
            jVar.a(c.a((int) SsoErrorCodes.NETWORK_ERROR, "NETWORK_ERROR"));
            a.b("GetGlobalSessionCb");
        }
    }

    /* renamed from: a */
    public void onResponse(JSONObject jSONObject) {
        j jVar = (j) a.a("GetGlobalSessionCb");
        try {
            String string = jSONObject.getString("status");
            Context d = com.login.nativesso.d.c.a().d();
            JSONObject a = com.login.nativesso.i.a.a(d);
            String str;
            if ("SUCCESS".equalsIgnoreCase(string)) {
                String str2;
                String str3 = null;
                if (!jSONObject.has("data") || jSONObject.isNull("data")) {
                    str = null;
                    string = str;
                    str2 = string;
                } else {
                    jSONObject = jSONObject.getJSONObject("data");
                    str3 = jSONObject.getString("firstName");
                    string = jSONObject.getString("lastName");
                    str2 = jSONObject.getString("email");
                    str = jSONObject.getString("mobile");
                }
                f fVar = new f();
                if (a != null) {
                    try {
                        String string2 = a.getString("SSECID");
                        String string3 = a.getString("TICKETID");
                        String string4 = a.getString("TGID");
                        fVar.f(string2);
                        fVar.e(string3);
                        fVar.g(string4);
                        fVar.a(str3);
                        fVar.b(string);
                        fVar.c(str2);
                        fVar.d(str);
                        if (jVar != null) {
                            jVar.a(fVar);
                            a.b("GetGlobalSessionCb");
                        }
                    } catch (JSONException e) {
                        ThrowableExtension.printStackTrace(e);
                        if (jVar != null) {
                            jVar.a(c.a(4004, "GLOBAL_SESSION_NOT_EXIST"));
                            a.b("GetGlobalSessionCb");
                        }
                        Log.e("NATIVESSO", "Error while parsing Json in getGlobalSession");
                    }
                }
            } else {
                str = a.getString("TGID");
                if (jVar != null) {
                    jVar.a(c.a(4004, "GLOBAL_SESSION_NOT_EXIST"));
                    a.b("GetGlobalSessionCb");
                }
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("TGID", str);
                jSONObject2.put("SSECID", "");
                jSONObject2.put("SOCIALTYPE", "");
                jSONObject2.put("TICKETID", "");
                com.login.nativesso.i.a.a(d, jSONObject2);
            }
        } catch (ServerException e2) {
            if (jVar != null) {
                ThrowableExtension.printStackTrace(e2);
                jVar.a(c.a((int) SsoErrorCodes.SERVER_ERROR, "SERVER_ERROR"));
                a.b("GetGlobalSessionCb");
            }
        } catch (SecurityException e3) {
            if (jVar != null) {
                ThrowableExtension.printStackTrace(e3);
                jVar.a(c.a(4008, "SECURITY_ISSUE"));
                a.b("GetGlobalSessionCb");
            }
        } catch (Exception e4) {
            ThrowableExtension.printStackTrace(e4);
            if (jVar != null) {
                jVar.a(c.a((int) SsoErrorCodes.REQUEST_FAILED, "REQUEST_FAILED"));
                a.b("GetGlobalSessionCb");
            }
            Log.e("NATIVESSO", "exception in renew ticket");
        }
    }
}
