package io.branch.referral;

import android.util.DisplayMetrics;
import io.branch.referral.Defines.Jsonkey;
import org.json.JSONException;
import org.json.JSONObject;

class k {
    private static k k;
    private final String a;
    private final boolean b;
    private final String c;
    private final String d;
    private final int e;
    private final int f;
    private final int g;
    private final boolean h;
    private final String i;
    private final int j;

    public static k a(boolean z, ag agVar, boolean z2) {
        if (k == null) {
            k = new k(z, agVar, z2);
        }
        return k;
    }

    private k(boolean z, ag agVar, boolean z2) {
        if (z2) {
            this.a = "bnc_no_value";
        } else {
            this.a = agVar.a(z);
        }
        this.b = agVar.a();
        this.c = agVar.e();
        this.d = agVar.f();
        DisplayMetrics i = agVar.i();
        this.e = i.densityDpi;
        this.f = i.heightPixels;
        this.g = i.widthPixels;
        this.h = agVar.j();
        this.i = agVar.g();
        this.j = agVar.h();
    }

    public void a(JSONObject jSONObject) {
        try {
            if (!this.a.equals("bnc_no_value")) {
                jSONObject.put(Jsonkey.HardwareID.getKey(), this.a);
                jSONObject.put(Jsonkey.IsHardwareIDReal.getKey(), this.b);
            }
            if (!this.c.equals("bnc_no_value")) {
                jSONObject.put(Jsonkey.Brand.getKey(), this.c);
            }
            if (!this.d.equals("bnc_no_value")) {
                jSONObject.put(Jsonkey.Model.getKey(), this.d);
            }
            jSONObject.put(Jsonkey.ScreenDpi.getKey(), this.e);
            jSONObject.put(Jsonkey.ScreenHeight.getKey(), this.f);
            jSONObject.put(Jsonkey.ScreenWidth.getKey(), this.g);
            jSONObject.put(Jsonkey.WiFi.getKey(), this.h);
            if (!this.i.equals("bnc_no_value")) {
                jSONObject.put(Jsonkey.OS.getKey(), this.i);
            }
            jSONObject.put(Jsonkey.OSVersion.getKey(), this.j);
        } catch (JSONException unused) {
        }
    }
}
