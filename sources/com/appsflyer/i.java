package com.appsflyer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class i {
    private static i a = new i();
    private Map<String, Object> b = new HashMap();
    private boolean c;
    private boolean d;
    private String e;
    private boolean f = false;

    private i() {
    }

    public static i a() {
        return a;
    }

    public void a(String str, String str2) {
        this.b.put(str, str2);
    }

    public void a(String str, boolean z) {
        this.b.put(str, Boolean.toString(z));
    }

    public String a(String str) {
        return (String) this.b.get(str);
    }

    public boolean b(String str, boolean z) {
        str = a(str);
        if (str == null) {
            return z;
        }
        return Boolean.valueOf(str).booleanValue();
    }

    public int a(String str, int i) {
        str = a(str);
        if (str == null) {
            return i;
        }
        return Integer.valueOf(str).intValue();
    }

    /* Access modifiers changed, original: protected */
    public void b() {
        this.c = true;
    }

    /* Access modifiers changed, original: protected */
    public boolean c() {
        return this.d;
    }

    /* Access modifiers changed, original: protected */
    public void a(boolean z) {
        this.d = z;
    }

    /* Access modifiers changed, original: protected */
    public void d() {
        this.d = true;
    }

    /* Access modifiers changed, original: protected */
    public void b(String str) {
        a("AF_REFERRER", str);
        this.e = str;
    }

    public String a(Context context) {
        if (this.e != null) {
            return this.e;
        }
        if (a("AF_REFERRER") != null) {
            return a("AF_REFERRER");
        }
        if (context == null) {
            return null;
        }
        return context.getSharedPreferences("appsflyer-data", 0).getString("referrer", null);
    }

    public boolean e() {
        return b("disableLogs", false);
    }

    public boolean f() {
        return b("disableOtherSdk", false);
    }

    @SuppressLint({"CommitPrefEdits"})
    public void a(SharedPreferences sharedPreferences) {
        String jSONObject = new JSONObject(this.b).toString();
        Editor edit = sharedPreferences.edit();
        edit.putString("savedProperties", jSONObject);
        if (VERSION.SDK_INT >= 9) {
            edit.apply();
        } else {
            edit.commit();
        }
    }

    public void b(Context context) {
        if (!this.f) {
            String string = context.getSharedPreferences("appsflyer-data", 0).getString("savedProperties", null);
            if (string != null) {
                AFLogger.c("Loading properties..");
                try {
                    JSONObject jSONObject = new JSONObject(string);
                    Iterator keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String str = (String) keys.next();
                        if (this.b.get(str) == null) {
                            this.b.put(str, jSONObject.getString(str));
                        }
                    }
                    this.f = true;
                } catch (JSONException e) {
                    AFLogger.a("Failed loading properties", e);
                }
                StringBuilder stringBuilder = new StringBuilder("Done loading properties: ");
                stringBuilder.append(this.f);
                AFLogger.c(stringBuilder.toString());
            }
        }
    }
}
