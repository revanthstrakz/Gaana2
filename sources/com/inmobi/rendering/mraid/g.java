package com.inmobi.rendering.mraid;

import com.comscore.android.id.IdHelperAndroid;
import org.json.JSONException;
import org.json.JSONObject;

public class g {
    private static String e = "g";
    public boolean a = true;
    public String b = IdHelperAndroid.NO_ID_AVAILABLE;
    public String c = "right";
    public String d = null;

    public static g a(String str, g gVar) {
        g gVar2 = new g();
        gVar2.d = str;
        try {
            JSONObject jSONObject = new JSONObject(str);
            gVar2.b = jSONObject.optString("forceOrientation", gVar.b);
            gVar2.a = jSONObject.optBoolean("allowOrientationChange", gVar.a);
            gVar2.c = jSONObject.optString("direction", gVar.c);
            if (!(gVar2.b.equals("portrait") || gVar2.b.equals("landscape"))) {
                gVar2.b = IdHelperAndroid.NO_ID_AVAILABLE;
            }
            if (gVar2.c.equals("left") || gVar2.c.equals("right")) {
                return gVar2;
            }
            gVar2.c = "right";
            return gVar2;
        } catch (JSONException unused) {
            return null;
        }
    }
}
