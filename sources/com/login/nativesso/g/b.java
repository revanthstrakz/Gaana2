package com.login.nativesso.g;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.login.nativesso.e.e;
import org.json.JSONException;
import org.json.JSONObject;

public class b {
    private static String a = "saveUserLoginInfo";
    private static b b = new b();

    public static b a() {
        return b;
    }

    private b() {
    }

    public void a(Context context, JSONObject jSONObject) {
        Editor edit = context.getSharedPreferences(a, 0).edit();
        try {
            edit.putString("TGID", jSONObject.getString("TGID"));
            edit.putString("TICKETID", jSONObject.getString("TICKETID"));
            edit.putString("SSECID", jSONObject.getString("SSECID"));
            edit.putString("SOCIALTYPE", jSONObject.getString("SOCIALTYPE"));
            edit.apply();
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public JSONObject a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(a, 0);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("TGID", sharedPreferences.getString("TGID", null));
            jSONObject.put("TICKETID", sharedPreferences.getString("TICKETID", null));
            jSONObject.put("SOCIALTYPE", sharedPreferences.getString("SOCIALTYPE", null));
            jSONObject.put("SSECID", sharedPreferences.getString("SSECID", null));
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
        return jSONObject;
    }

    public String b(Context context) {
        return context.getSharedPreferences(a, 0).getString("SSECID", null);
    }

    public String a(String str, Context context) {
        return context.getSharedPreferences(a, 0).getString(str, null);
    }

    public void a(Context context, String str, String str2) {
        Editor edit = context.getSharedPreferences(a, 0).edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public String c(Context context) {
        return context.getSharedPreferences(a, 0).getString("TGID", null);
    }

    public SharedPreferences d(Context context) {
        return context.getSharedPreferences(a, 0);
    }

    public static void a(Context context, e eVar) {
        a a = a.a(context, "object_prefs", 0);
        a.a("USER_INFO", (Object) eVar);
        a.a();
    }
}
