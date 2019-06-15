package com.inmobi.ads;

import android.net.Uri;
import com.google.api.client.http.HttpMethods;
import com.inmobi.a.b.b;
import com.inmobi.a.m;
import com.inmobi.ads.InMobiAdRequest.MonetizationContext;
import com.inmobi.commons.core.network.c;
import com.inmobi.commons.core.utilities.b.a;
import com.inmobi.commons.core.utilities.b.g;
import com.inmobi.commons.core.utilities.uid.d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

public final class f extends c {
    private static final String A = "f";
    public long a;
    String b = "json";
    public String c;
    int d = 1;
    public String e;
    public String f;
    public Map<String, String> g;
    Map<String, String> h;
    public final String i;
    public MonetizationContext j;
    public final r k;
    boolean l = false;

    public f(String str, long j, d dVar, String str2) {
        super(HttpMethods.POST, str, a(str), dVar, a(str), 0);
        this.a = j;
        this.o.put("im-plid", String.valueOf(this.a));
        this.o.putAll(g.d());
        this.o.putAll(com.inmobi.commons.core.utilities.b.c.c());
        this.o.put("u-appIS", a.a().a);
        this.o.putAll(m.a().f());
        this.o.putAll(m.a().e());
        Map map = this.o;
        com.inmobi.a.b.a a = b.a();
        HashMap hashMap = new HashMap();
        if (a != null) {
            hashMap.put("c-ap-bssid", String.valueOf(a.a));
        }
        map.putAll(hashMap);
        map = this.o;
        ArrayList arrayList = (ArrayList) com.inmobi.a.b.c.a();
        hashMap = new HashMap();
        if (arrayList != null && arrayList.size() > 0) {
            hashMap.put("v-ap-bssid", String.valueOf(((com.inmobi.a.b.a) arrayList.get(arrayList.size() - 1)).a));
        }
        map.putAll(hashMap);
        this.o.putAll(com.inmobi.a.a.c.b());
        this.o.putAll(com.inmobi.a.a.c.c());
        this.o.putAll(com.inmobi.a.a.c.a());
        this.i = UUID.randomUUID().toString();
        this.o.put("client-request-id", this.i);
        if (str2 != null) {
            this.o.put("u-appcache", str2);
        }
        this.o.put("sdk-flavor", "row");
        this.k = new r();
        this.o.put("skdv", this.z.c);
        this.o.put("skdm", this.k.a(this.z.b, this.z.a));
    }

    private static boolean a(String str) {
        if (str == null) {
            return true;
        }
        Uri parse = Uri.parse(str);
        if ("http".equals(parse.getScheme()) || !"https".equals(parse.getScheme())) {
            return true;
        }
        return false;
    }

    public final void a() {
        super.a();
        this.o.put("format", this.b);
        this.o.put("mk-ads", String.valueOf(this.d));
        this.o.put("adtype", this.e);
        if (this.f != null) {
            this.o.put("p-keywords", this.f);
        }
        Object obj = this.j != null ? this.j == MonetizationContext.MONETIZATION_CONTEXT_OTHER ? "M10N_CONTEXT_OTHER" : "M10N_CONTEXT_ACTIVITY" : "M10N_CONTEXT_ACTIVITY";
        this.o.put("m10n_context", obj);
        if (this.g != null) {
            for (Entry entry : this.g.entrySet()) {
                if (!this.o.containsKey(entry.getKey())) {
                    this.o.put(entry.getKey(), entry.getValue());
                }
            }
        }
        if (this.h != null) {
            this.o.putAll(this.h);
        }
    }

    public final String c() {
        return this.h.containsKey("preload-request") ? (String) this.h.get("preload-request") : "0";
    }

    public final boolean b() {
        return this.l || super.b();
    }
}
