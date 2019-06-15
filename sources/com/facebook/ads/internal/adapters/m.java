package com.facebook.ads.internal.adapters;

import android.content.Intent;
import android.os.Bundle;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.a.d;
import com.facebook.ads.internal.a.e.a;
import com.facebook.ads.internal.l.c;
import com.facebook.ads.internal.s.a.k;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.til.colombia.android.internal.e;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class m implements a {
    private final String a;
    private final String b;
    private final d c;
    private final Collection<String> d;
    private final Map<String, String> e;
    private final String f;
    private final int g;
    private final int h;
    private final int i;
    private final String j;

    private m(String str, String str2, d dVar, Collection<String> collection, Map<String, String> map, String str3, int i, int i2, int i3, String str4) {
        this.a = str;
        this.b = str2;
        this.c = dVar;
        this.d = collection;
        this.e = map;
        this.f = str3;
        this.g = i;
        this.h = i2;
        this.i = i3;
        this.j = str4;
    }

    public static m a(Bundle bundle) {
        return new m(c.a(bundle.getByteArray("markup")), null, d.NONE, null, null, bundle.getString("request_id"), bundle.getInt("viewability_check_initial_delay"), bundle.getInt("viewability_check_interval"), bundle.getInt("skip_after_seconds", 0), bundle.getString(e.P));
    }

    public static m a(JSONObject jSONObject) {
        JSONArray jSONArray = null;
        if (jSONObject == null) {
            return null;
        }
        String optString = jSONObject.optString("markup");
        String optString2 = jSONObject.optString("activation_command");
        String optString3 = jSONObject.optString("request_id");
        String a = k.a(jSONObject, e.P);
        d a2 = d.a(jSONObject.optString("invalidation_behavior"));
        try {
            jSONArray = new JSONArray(jSONObject.optString("detection_strings"));
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
        Collection a3 = com.facebook.ads.internal.a.e.a(jSONArray);
        jSONObject = jSONObject.optJSONObject(TtmlNode.TAG_METADATA);
        HashMap hashMap = new HashMap();
        if (jSONObject != null) {
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                hashMap.put(str, jSONObject.optString(str));
            }
        }
        int i = 1000;
        int parseInt = hashMap.containsKey("viewability_check_initial_delay") ? Integer.parseInt((String) hashMap.get("viewability_check_initial_delay")) : 0;
        if (hashMap.containsKey("viewability_check_interval")) {
            i = Integer.parseInt((String) hashMap.get("viewability_check_interval"));
        }
        return new m(optString, optString2, a2, a3, hashMap, optString3, parseInt, i, hashMap.containsKey("skip_after_seconds") ? Integer.parseInt((String) hashMap.get("skip_after_seconds")) : 0, a);
    }

    public static m b(Intent intent) {
        return new m(c.a(intent.getByteArrayExtra("markup")), intent.getStringExtra("activation_command"), d.NONE, null, null, intent.getStringExtra("request_id"), intent.getIntExtra("viewability_check_initial_delay", 0), intent.getIntExtra("viewability_check_interval", 1000), intent.getIntExtra(AudienceNetworkActivity.SKIP_DELAY_SECONDS_KEY, 0), intent.getStringExtra(e.P));
    }

    public d a() {
        return this.c;
    }

    public void a(Intent intent) {
        intent.putExtra("markup", c.a(this.a));
        intent.putExtra("activation_command", this.b);
        intent.putExtra("request_id", this.f);
        intent.putExtra("viewability_check_initial_delay", this.g);
        intent.putExtra("viewability_check_interval", this.h);
        intent.putExtra(AudienceNetworkActivity.SKIP_DELAY_SECONDS_KEY, this.i);
        intent.putExtra(e.P, this.j);
    }

    public Collection<String> b() {
        return this.d;
    }

    public String c() {
        return this.j;
    }

    public String d() {
        return this.a;
    }

    public String e() {
        return this.b;
    }

    public Map<String, String> f() {
        return this.e;
    }

    public String g() {
        return this.f;
    }

    public int h() {
        return this.g;
    }

    public int i() {
        return this.h;
    }

    public Bundle j() {
        Bundle bundle = new Bundle();
        bundle.putByteArray("markup", c.a(this.a));
        bundle.putString("request_id", this.f);
        bundle.putInt("viewability_check_initial_delay", this.g);
        bundle.putInt("viewability_check_interval", this.h);
        bundle.putInt("skip_after_seconds", this.i);
        bundle.putString(e.P, this.j);
        return bundle;
    }
}
