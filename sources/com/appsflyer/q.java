package com.appsflyer;

import android.support.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class q {
    @Nullable
    static JSONObject a(String str) {
        JSONObject jSONObject;
        Throwable th;
        try {
            jSONObject = new JSONObject(str);
            try {
                if (jSONObject.optBoolean("monitor", false)) {
                    ah.a().b();
                } else {
                    ah.a().e();
                    ah.a().c();
                }
            } catch (JSONException unused) {
                ah.a().e();
                ah.a().c();
                return jSONObject;
            } catch (Throwable th2) {
                th = th2;
                AFLogger.a(th.getMessage(), th);
                ah.a().e();
                ah.a().c();
                return jSONObject;
            }
        } catch (JSONException unused2) {
            jSONObject = null;
            ah.a().e();
            ah.a().c();
            return jSONObject;
        } catch (Throwable th3) {
            th = th3;
            jSONObject = null;
            AFLogger.a(th.getMessage(), th);
            ah.a().e();
            ah.a().c();
            return jSONObject;
        }
        return jSONObject;
    }

    public static String b(String str) {
        return String.format(str, new Object[]{h.c().e()});
    }
}
