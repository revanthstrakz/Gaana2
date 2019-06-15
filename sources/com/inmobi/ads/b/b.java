package com.inmobi.ads.b;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.inmobi.ads.a;
import com.inmobi.ads.f;
import com.inmobi.ads.r;
import com.inmobi.commons.core.configs.g;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class b {
    @NonNull
    public f a;
    @Nullable
    private List<a> b;

    b(@NonNull f fVar, @Nullable List<a> list) {
        this.a = fVar;
        this.b = list;
    }

    /* Access modifiers changed, original: final */
    public final byte[] a() throws com.inmobi.ads.a.b, JSONException {
        HashMap hashMap = new HashMap();
        hashMap.put("h-user-agent", com.inmobi.commons.a.a.f());
        JSONArray jSONArray = new JSONArray();
        if (this.b != null) {
            com.inmobi.commons.core.configs.a gVar = new g();
            com.inmobi.commons.core.configs.b.a().a(gVar, null);
            for (a aVar : this.b) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("impressionId", aVar.g);
                r rVar = this.a.k;
                float f = aVar.k;
                Object a = com.inmobi.commons.core.utilities.a.b.a(String.valueOf(f), rVar.b, rVar.a, rVar.c, gVar.b, gVar.a);
                String str = "bid";
                if (a == null) {
                    a = "";
                }
                jSONObject.put(str, a);
                Object f2 = aVar.f();
                String str2 = "cachedAdData";
                if (f2 == null) {
                    f2 = new JSONObject();
                }
                jSONObject.put(str2, f2);
                jSONArray.put(jSONObject);
            }
        }
        hashMap.put("cachedAdInfos", jSONArray.toString());
        this.a.c(hashMap);
        this.a.a();
        if (this.a.x == 1) {
            return this.a.f().getBytes();
        }
        throw new com.inmobi.ads.a.b();
    }
}
