package com.login.nativesso.h;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import com.android.volley.AuthFailureError;
import com.android.volley.i.b;
import com.android.volley.toolbox.i;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.login.nativesso.d.c;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class a extends i {
    private String a = "application/json";
    private Map<String, String> b;

    public a(int i, String str, JSONObject jSONObject, b<JSONObject> bVar, com.android.volley.i.a aVar) {
        super(i, str, jSONObject, bVar, aVar);
    }

    public void a(Map<String, String> map) {
        this.b = map;
        if (this.b == null) {
            this.b = new HashMap();
        }
        com.login.nativesso.g.b a = com.login.nativesso.g.b.a();
        c a2 = c.a();
        this.b.put("CONTENT_TYPE", this.a);
        Context d = a2.d();
        this.b.put("channel", a.a("channel", d));
        this.b.put("ssec", a.b(d));
        this.b.put("ticketId", a.a("TICKETID", d));
        this.b.put("tgid", a.a("TGID", d));
        try {
            this.b.put("appVersion", d.getPackageManager().getPackageInfo(d.getPackageName(), 0).versionName);
        } catch (NameNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
        }
        this.b.put("platform", "android");
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        return this.b;
    }
}
