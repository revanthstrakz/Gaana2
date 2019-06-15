package com.facebook.ads.internal.l;

import android.support.annotation.Nullable;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    public static String a;
    private String b;
    private Map<String, Object> c;
    private int d;
    private String e;

    public enum a {
        OPEN_STORE(0),
        OPEN_LINK(1),
        XOUT(2),
        OPEN_URL(3),
        SHOW_INTERSTITIAL(4);
        
        int f;

        private a(int i) {
            this.f = i;
        }
    }

    public enum b {
        LOADING_AD(0);
        
        int b;

        private b(int i) {
            this.b = i;
        }
    }

    public a(String str, Map<String, Object> map, int i, String str2) {
        this.b = str;
        this.c = map;
        this.d = i;
        this.e = str2;
    }

    public static a a(long j, a aVar, String str) {
        long currentTimeMillis = System.currentTimeMillis();
        HashMap hashMap = new HashMap();
        hashMap.put("Time", String.valueOf(currentTimeMillis - j));
        hashMap.put("AdAction", String.valueOf(aVar.f));
        return new a("bounceback", hashMap, (int) (currentTimeMillis / 1000), str);
    }

    public static a a(b bVar, String str, long j, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("LatencyType", String.valueOf(bVar.b));
        hashMap.put("AdPlacementType", str);
        hashMap.put("Time", String.valueOf(j));
        str = "latency";
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        if (str2 == null) {
            str2 = a;
        }
        return new a(str, hashMap, currentTimeMillis, str2);
    }

    public static a a(@Nullable Throwable th, @Nullable String str) {
        HashMap hashMap = new HashMap();
        if (th != null) {
            hashMap.put("ex", th.getClass().getSimpleName());
            hashMap.put("ex_msg", th.getMessage());
        }
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        String str2 = "error";
        if (str == null) {
            str = a;
        }
        return new a(str2, hashMap, currentTimeMillis, str);
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", this.b);
            jSONObject.put("data", new JSONObject(this.c));
            jSONObject.put("time", this.d);
            jSONObject.put("request_id", this.e);
            return jSONObject;
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
            return jSONObject;
        }
    }
}
