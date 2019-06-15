package com.moat.analytics.mobile.inm;

import android.graphics.Rect;
import android.view.View;
import com.moat.analytics.mobile.inm.NativeDisplayTracker.MoatUserInteractionType;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

class t extends b implements NativeDisplayTracker {
    private final Map<String, String> g;
    private final Set<MoatUserInteractionType> h = new HashSet();

    t(View view, Map<String, String> map) {
        super(view, true, false);
        p.a(3, "NativeDisplayTracker", (Object) this, "Initializing.");
        this.g = map;
        String str;
        StringBuilder stringBuilder;
        if (view == null) {
            str = "Target view is null";
            stringBuilder = new StringBuilder("NativeDisplayTracker initialization not successful, ");
            stringBuilder.append(str);
            p.a("[ERROR] ", 3, "NativeDisplayTracker", this, stringBuilder.toString());
            this.a = new m(str);
        } else if (map == null || map.isEmpty()) {
            stringBuilder = new StringBuilder("NativeDisplayTracker initialization not successful, ");
            stringBuilder.append("AdIds is null or empty");
            p.a("[ERROR] ", 3, "NativeDisplayTracker", this, stringBuilder.toString());
            this.a = new m("AdIds is null or empty");
        } else {
            g gVar = ((k) MoatAnalytics.getInstance()).d;
            if (gVar == null) {
                str = "prepareNativeDisplayTracking was not called successfully";
                stringBuilder = new StringBuilder("NativeDisplayTracker initialization not successful, ");
                stringBuilder.append(str);
                p.a("[ERROR] ", 3, "NativeDisplayTracker", this, stringBuilder.toString());
                this.a = new m(str);
                return;
            }
            super.a(gVar.b);
            try {
                super.a(gVar.a);
                i();
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(a());
                stringBuilder2.append(" created for ");
                stringBuilder2.append(g());
                stringBuilder2.append(", with adIds:");
                stringBuilder2.append(map.toString());
                p.a("[SUCCESS] ", stringBuilder2.toString());
            } catch (m e) {
                this.a = e;
            }
        }
    }

    private static String a(Map<String, String> map) {
        String stringBuilder;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i = 0;
        for (int i2 = 0; i2 < 8; i2++) {
            StringBuilder stringBuilder2 = new StringBuilder("moatClientLevel");
            stringBuilder2.append(i2);
            String stringBuilder3 = stringBuilder2.toString();
            if (map.containsKey(stringBuilder3)) {
                linkedHashMap.put(stringBuilder3, map.get(stringBuilder3));
            }
        }
        while (i < 8) {
            StringBuilder stringBuilder4 = new StringBuilder("moatClientSlicer");
            stringBuilder4.append(i);
            stringBuilder = stringBuilder4.toString();
            if (map.containsKey(stringBuilder)) {
                linkedHashMap.put(stringBuilder, map.get(stringBuilder));
            }
            i++;
        }
        for (String stringBuilder5 : map.keySet()) {
            if (!linkedHashMap.containsKey(stringBuilder5)) {
                linkedHashMap.put(stringBuilder5, (String) map.get(stringBuilder5));
            }
        }
        return new JSONObject(linkedHashMap).toString();
    }

    private void i() {
        if (this.c != null) {
            this.c.a(j());
        }
    }

    private String j() {
        String str = "";
        try {
            String a = a(this.g);
            StringBuilder stringBuilder = new StringBuilder("Parsed ad ids = ");
            stringBuilder.append(a);
            p.a(3, "NativeDisplayTracker", (Object) this, stringBuilder.toString());
            StringBuilder stringBuilder2 = new StringBuilder("{\"adIds\":");
            stringBuilder2.append(a);
            stringBuilder2.append(", \"adKey\":\"");
            stringBuilder2.append(this.e);
            stringBuilder2.append("\", \"adSize\":");
            stringBuilder2.append(k());
            stringBuilder2.append("}");
            return stringBuilder2.toString();
        } catch (Exception e) {
            m.a(e);
            return str;
        }
    }

    private String k() {
        try {
            Rect a = z.a(super.f());
            int width = a.width();
            int height = a.height();
            HashMap hashMap = new HashMap();
            hashMap.put("width", Integer.toString(width));
            hashMap.put("height", Integer.toString(height));
            return new JSONObject(hashMap).toString();
        } catch (Exception e) {
            m.a(e);
            return null;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public String a() {
        return "NativeDisplayTracker";
    }

    public void reportUserInteractionEvent(MoatUserInteractionType moatUserInteractionType) {
        try {
            StringBuilder stringBuilder = new StringBuilder("reportUserInteractionEvent:");
            stringBuilder.append(moatUserInteractionType.name());
            p.a(3, "NativeDisplayTracker", (Object) this, stringBuilder.toString());
            if (!this.h.contains(moatUserInteractionType)) {
                this.h.add(moatUserInteractionType);
                JSONObject jSONObject = new JSONObject();
                jSONObject.accumulate("adKey", this.e);
                jSONObject.accumulate("event", moatUserInteractionType.name().toLowerCase());
                if (this.c != null) {
                    this.c.b(jSONObject.toString());
                }
            }
        } catch (JSONException e) {
            p.b(2, "NativeDisplayTracker", this, "Got JSON exception");
            m.a(e);
        } catch (Exception e2) {
            m.a(e2);
        }
    }
}
