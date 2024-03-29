package com.inmobi.ads;

import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.inmobi.commons.core.configs.a;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class b {
    public static void a() {
        a cVar = new c();
        com.inmobi.commons.core.configs.b.a().a(cVar, null);
        a(cVar);
        JSONArray jSONArray = new JSONArray();
        d.a();
        a(cVar, jSONArray, d.b());
        if (jSONArray.length() > 0) {
            HashMap hashMap = new HashMap();
            hashMap.put("adsArray", jSONArray.toString());
            com.inmobi.commons.core.e.b.a().a("ads", cVar.l);
            com.inmobi.commons.core.e.b.a();
            com.inmobi.commons.core.e.b.a("ads", "AdCacheCachedAds", hashMap);
            return;
        }
        b.class.getName();
    }

    public static void b() {
        a cVar = new c();
        com.inmobi.commons.core.configs.b.a().a(cVar, null);
        a(cVar);
    }

    private static void a(c cVar) {
        r1 = new String[3];
        int i = 0;
        r1[0] = "banner";
        r1[1] = "int";
        r1[2] = AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE;
        JSONArray jSONArray = new JSONArray();
        while (i < 3) {
            String str = r1[i];
            d.a();
            a(cVar, jSONArray, d.a(str, cVar.a(str).d));
            i++;
        }
        if (jSONArray.length() > 0) {
            HashMap hashMap = new HashMap();
            hashMap.put("adsArray", jSONArray.toString());
            com.inmobi.commons.core.e.b.a().a("ads", cVar.l);
            com.inmobi.commons.core.e.b.a();
            com.inmobi.commons.core.e.b.a("ads", "AdCacheAdExpired", hashMap);
            return;
        }
        b.class.getName();
    }

    private static void a(c cVar, JSONArray jSONArray, List<a> list) {
        for (a aVar : list) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(HlsSegmentFormat.TS, System.currentTimeMillis());
                jSONObject.put("impId", aVar.g);
                jSONObject.put("plId", aVar.d);
                jSONObject.put("insTs", aVar.e);
                jSONObject.put("expTs", aVar.c());
                jSONObject.put("expiryDuration", aVar.f);
                jSONObject.put("configTTL", TimeUnit.SECONDS.toMillis(cVar.a(aVar.b).d));
                jSONObject.put("adType", aVar.b);
                jSONArray.put(jSONObject);
            } catch (JSONException unused) {
                b.class.getName();
            }
        }
    }

    public static void c() {
        a cVar = new c();
        com.inmobi.commons.core.configs.b.a().a(cVar, null);
        if (cVar.o) {
            d.a();
            d.c();
        }
    }
}
